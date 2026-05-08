# ============================================================
# Import de dados: Access -> SQL Server - QUA_MC
# Ficheiro: GESTAO DE MEIOS DE CONTROLO_23-01-2026 (1).accdb
# ============================================================
#powershell -ExecutionPolicy Bypass -File "C:\work\Workspace\sgiid\sql\QUA_MC_IMPORT_ACCESS.ps1"

# Encontra o ficheiro Access sem depender de caracteres especiais no path
$AccessFile = (Get-ChildItem "C:\Users\it2\Downloads\" -Filter "*MEIOS*CONTROLO*.accdb" -ErrorAction SilentlyContinue | Select-Object -First 1).FullName
if (-not $AccessFile) {
    $AccessFile = (Get-ChildItem "C:\Users\it2\Downloads\" -Filter "*.accdb" -ErrorAction SilentlyContinue | Select-Object -First 1).FullName
}
if (-not $AccessFile) { Write-Error "Ficheiro .accdb não encontrado em Downloads!"; exit 1 }
Write-Host "Ficheiro Access: $AccessFile"

# -- Ajusta estas variáveis conforme o teu servidor --
$SqlServer  = "192.168.40.101,1433"
$SqlDb      = "SGIID"
$SqlUser    = "sa"
$SqlPass    = "DourecA2@"

# ============================================================
# Conexões
$accConn = New-Object -ComObject ADODB.Connection
try {
    $accConn.Open("Provider=Microsoft.ACE.OLEDB.12.0;Data Source=$AccessFile;Persist Security Info=False;")
    Write-Host "Access: ligação OK"
} catch {
    Write-Error "Erro ao abrir Access: $_"; exit 1
}

$connStr = if ($SqlUser) { "Server=$SqlServer;Database=$SqlDb;User Id=$SqlUser;Password=$SqlPass;Connect Timeout=30;" } `
           else          { "Server=$SqlServer;Database=$SqlDb;Integrated Security=True;Connect Timeout=30;" }
$sqlConn = New-Object System.Data.SqlClient.SqlConnection($connStr)
try {
    $sqlConn.Open()
    Write-Host "SQL Server: ligação OK ($SqlServer / $SqlDb)"
} catch {
    Write-Error "Erro ao ligar SQL Server: $_"; exit 1
}

# Reconecta se necessário
function EnsureSqlOpen {
    if ($sqlConn.State -ne [System.Data.ConnectionState]::Open) {
        Write-Warning "  Reconectando SQL Server..."
        $sqlConn.Open()
    }
}

# Caracteres especiais - construídos em runtime para evitar corrupção de encoding
# (PS 5.1 lê o ficheiro como Windows-1252; UTF-8 multibyte fica corrompido)
$cc = [char]0x00C7  # Ç (c-cedilha)
$ot = [char]0x00D5  # Õ (o-tilde)
$oa = [char]0x00D3  # Ó (o-agudo)
$at = [char]0x00C3  # Ã (a-tilde)

# ============================================================
# Helper: formata valor para SQL
function Fmt($v) {
    if ($v -eq $null -or $v -is [System.DBNull] -or "$v" -eq "") { return "NULL" }
    if ($v -is [bool])     { return if ($v) { "1" } else { "0" } }
    # Access boolean: -1=true, 0=false
    if ($v -is [int] -and ($v -eq -1 -or $v -eq 0) -and $false) { }
    if ($v -is [DateTime]) { return "'" + $v.ToString("yyyy-MM-dd") + "'" }
    if ($v -is [string])   { return "'" + $v.Replace("'","''").Trim() + "'" }
    return "$v"
}
# Para colunas BIT vindas do Access (tipo 11 = boolean, -1=true, 0=false)
function FmtBit($v) {
    if ($v -eq $null -or $v -is [System.DBNull]) { return "0" }
    if ($v -eq $true  -or $v -eq -1) { return "1" }
    return "0"
}

function ExecSql($sql) {
    $cmd = $sqlConn.CreateCommand()
    $cmd.CommandText = $sql
    $cmd.CommandTimeout = 120
    try { $cmd.ExecuteNonQuery() | Out-Null }
    catch { Write-Warning "SQL ERRO: $_`n  -> $sql" }
}

# ============================================================
# Importa uma tabela Access para SQL Server com mapeamento explícito
# $rows: array de hashtables { sqlCol = accessFieldName|"LITERAL:val" }
# $idCol: coluna IDENTITY (para SET IDENTITY_INSERT)
function Import-Mapped($accTable, $sqlTable, [hashtable[]]$colMap, $idCol, [string[]]$skipNullFKs = @()) {
    Write-Host "A importar [$accTable] -> $sqlTable ..." -NoNewline

    if ($idCol) { ExecSql "SET IDENTITY_INSERT [$sqlTable] ON" }

    $rs = New-Object -ComObject ADODB.Recordset
    try {
        # adOpenStatic=3, adLockReadOnly=1
        $rs.Open("SELECT * FROM [$accTable]", $accConn, 3, 1)
    } catch {
        Write-Warning "`n  Erro ao abrir [$accTable]: $_"
        if ($idCol) { ExecSql "SET IDENTITY_INSERT [$sqlTable] OFF" }
        return
    }

    # Encontra o campo Access que mapeia para a coluna ID (para detetar NULLs)
    $idAccFld = $null
    if ($idCol) {
        foreach ($map in $colMap) {
            foreach ($kv in $map.GetEnumerator()) {
                if ($kv.Key -eq $idCol) { $idAccFld = $kv.Value; break }
            }
            if ($idAccFld) { break }
        }
    }

    $count = 0; $erros = 0
    while (-not $rs.EOF) {
        # Salta linhas onde o ID é NULL (linhas em branco no Access)
        if ($idAccFld -and $idAccFld -notlike "LITERAL:*" -and $idAccFld -notlike "BIT:*") {
            $idVal = try { $rs.Fields[$idAccFld].Value } catch { $null }
            if ($idVal -eq $null -or $idVal -is [System.DBNull]) {
                $erros++
                try { $rs.MoveNext() } catch { break }
                continue
            }
        }

        # Salta linhas onde FKs obrigatórias são NULL (dados em branco na fonte Access)
        $skipRow = $false; $skipFld = $null
        foreach ($fkFld in $skipNullFKs) {
            $fkVal = try { $rs.Fields[$fkFld].Value } catch { $null }
            if ($fkVal -eq $null -or $fkVal -is [System.DBNull]) { $skipRow = $true; $skipFld = $fkFld; break }
        }
        if ($skipRow) {
            if ($erros -lt 3) { Write-Warning "`n  Linha ignorada (FK nula ou campo inexistente: '$skipFld')" }
            $erros++
            try { $rs.MoveNext() } catch { break }
            continue
        }

        $cols = @(); $vals = @()
        foreach ($map in $colMap) {
            foreach ($kv in $map.GetEnumerator()) {
                $sqlCol  = $kv.Key
                $accFld  = $kv.Value
                $cols   += "[$sqlCol]"

                if ($accFld -like "LITERAL:*") {
                    $vals += $accFld.Substring(8)
                } elseif ($accFld -like "BIT:*") {
                    $fn = $accFld.Substring(4)
                    $v  = try { $rs.Fields[$fn].Value } catch { $null }
                    $vals += FmtBit $v
                } else {
                    $v = try { $rs.Fields[$accFld].Value } catch { $null }
                    # Detecta colunas BIT pelo tipo ADODB (11=boolean)
                    $fType = try { $rs.Fields[$accFld].Type } catch { 0 }
                    if ($fType -eq 11) { $vals += FmtBit $v }
                    else               { $vals += Fmt $v }
                }
            }
        }
        $sql = "INSERT INTO [$sqlTable] ($($cols -join ',')) VALUES ($($vals -join ','))"
        $cmd = $sqlConn.CreateCommand(); $cmd.CommandText = $sql; $cmd.CommandTimeout = 30
        try   { $cmd.ExecuteNonQuery() | Out-Null; $count++ }
        catch { Write-Warning "`n  Linha ignorada: $_"; $erros++ }

        try { $rs.MoveNext() } catch { Write-Warning "  MoveNext falhou, a terminar tabela."; break }
    }
    try { $rs.Close() } catch { }

    if ($idCol) { ExecSql "SET IDENTITY_INSERT [$sqlTable] OFF" }
    Write-Host "  $count ok, $erros erros."
}

# ============================================================
# 1. QUA_MC_DIC_SECCOES
Import-Mapped "T - LISTA DE SEC${cc}${ot}ES" "QUA_MC_DIC_SECCOES" @(
    @{ ID_SECCAO    = "ID_LISTASECCOES" },
    @{ LOCAL_SECCAO = "LOCAL_SECCAO" },
    @{ ATIVO        = "LITERAL:1" }
) "ID_SECCAO"

# 2. QUA_MC_DIC_RESP_VALIDACAO
Import-Mapped "T - LISTA RESP VALID EQ_MAQ" "QUA_MC_DIC_RESP_VALIDACAO" @(
    @{ ID_RESP_VALIDACAO = "ID_RESPON_VALIDACAO" },
    @{ RESP_VALIDACAO    = "RESP_VALIDACAO" },
    @{ DEPARTAMENTO      = "DEPARTAMENTO" },
    @{ ATIVO             = "LITERAL:1" }
) "ID_RESP_VALIDACAO"

# 3. QUA_MC_DIC_TIPO_CALIBRACAO
Import-Mapped "T - TIPO CALIBRA${cc}${at}O" "QUA_MC_DIC_TIPO_CALIBRACAO" @(
    @{ ID_TIPO_CALIBRACAO = "ID_TIPO_CALIBRACAO" },
    @{ TIPO_CALIB_EQUP    = "TIPO_CALIB_EQUP" },
    @{ ATIVO              = "LITERAL:1" }
) "ID_TIPO_CALIBRACAO"

# 4. QUA_MC_DIC_TIPO_ACEITACAO
Import-Mapped "T - TIPO ACEITA${cc}${at}O" "QUA_MC_DIC_TIPO_ACEITACAO" @(
    @{ ID_TIPO_ACEITACAO       = "ID_TIPO_ACEITACAO" },
    @{ TIPO_ACEIT_CALIB_EQUIP  = "TIPO_ACEIT_CALIB_EQUIP" },
    @{ ATIVO                   = "LITERAL:1" }
) "ID_TIPO_ACEITACAO"

# 5. QUA_MC_DIC_TIPO_VERIF_GABARIT
Import-Mapped "T - TIPO VERIF GABARIT" "QUA_MC_DIC_TIPO_VERIF_GABARIT" @(
    @{ ID_TIPO_VERIF_GABARIT = "ID_VERIF_GABARIT" },
    @{ TIPO_VERIF_GABARIT    = "TIPO_VERIF_GABARIT" },
    @{ ATIVO                 = "LITERAL:1" }
) "ID_TIPO_VERIF_GABARIT"

# 6. QUA_MC_DIC_PECAS_CROMADAS
Import-Mapped "T - LISTA DE PE${cc}AS CROMADAS" "QUA_MC_DIC_PECAS_CROMADAS" @(
    @{ ID_PECA_CROMADA = "ID_LISTA_PECAS_CROMADAS" },
    @{ REFERENCIA      = "REFERENCIA" },
    @{ DESIGNACAO      = "DESIGNACAO" },
    @{ ATIVO           = "LITERAL:1" }
) "ID_PECA_CROMADA"

# 7. QUA_MC_ENTIDADES_CALIBRACAO
Import-Mapped "T - ENTIDADES CALIBRAD" "QUA_MC_ENTIDADES_CALIBRACAO" @(
    @{ ID_ENTIDADE_CALIBRACAO = "ID_ENTIDADECALIBRA" },
    @{ NOME_ENTIDADE          = "NOME_ENTIDADECALIB" },
    @{ ABREVIATURA            = "ABREVIATURA_ENTIDADE" },
    @{ MORADA                 = "MORADA_ENTIDCALIB" },
    @{ TELEFONE               = "TELEF_ENTIDADECALIB" },
    @{ FAX                    = "FAX_ENTIDADECALIB" },
    @{ EMAIL                  = "EMAIL_ENTIDADECALIB" },
    @{ TECNICO                = "TECNICO_ENTIDADECALIB" },
    @{ OBSERVACOES            = "OBSERVACOES_ENTIDCALIB" },
    @{ ATIVO                  = "LITERAL:1" }
) "ID_ENTIDADE_CALIBRACAO"

# 8. QUA_MC_ENTIDADES_CALIBRACAO_CERTIF
Import-Mapped "T - SUBFORM ENTIDADES" "QUA_MC_ENTIDADES_CALIBRACAO_CERTIF" @(
    @{ ID_CERTIF              = "ID" },
    @{ ID_ENTIDADE_CALIBRACAO = "ID_ENTIDADECALIBRA" },
    @{ NUM                    = "NUM" },
    @{ DATA_CERTIF            = "DATA_CERTIF" },
    @{ NUM_CERTIF             = "NUM_CERTIF" },
    @{ CERTIFICADO            = "CERTIFICADO" },
    @{ TIPO_ENSAIO            = "TIPO_ENSAIO" },
    @{ VALIDADE               = "VALIDADE" },
    @{ ATIVO                  = "LITERAL:1" }
) "ID_CERTIF"

# 9. QUA_MC_EQUIPAMENTOS
Import-Mapped "T - LISTA EQUIPAMENTOS" "QUA_MC_EQUIPAMENTOS" @(
    @{ ID_EQUIPAMENTO       = "ID_LISTAEQUIP" },
    @{ DESIGNACAO           = "DESIGNACAOEQUIP" },
    @{ COD_INTERNO          = "COD_INT_EQUIP" },
    @{ N_SERIE              = "N_SERIE_EQUIP" },
    @{ MODELO               = "MODELO_EQUIP" },
    @{ FABRICANTE           = "FABRICANTE_EQUP" },
    @{ FORNECEDOR           = "FORNECEDOR" },
    @{ FUNCAO               = "FUNCAO_EQUIP" },
    @{ DATA_AQUISICAO       = "DATA_AQUIS_EQUIP" },
    @{ ID_SECCAO            = "LOCAL_SECCAO" },
    @{ ID_RESP_ENTRADA      = "NOME_RESP_ENTRADA" },
    @{ CALIBRACAO_SIM       = "BIT:CALIBRCAO_EQUIP_SIM" },
    @{ CALIBRACAO_NAO       = "BIT:CALIBRACAO_EQUIP_NAO" },
    @{ CALIBR_INTERNA       = "BIT:CALIBR_EQUIP_INTERNA" },
    @{ CALIBR_EXTERNA       = "BIT:CALIBR_EQUIP_EXTERNA" },
    @{ INTERCALIB_INT_MESES = "INTERCALIB_EQUIP_INT" },
    @{ INTERCALIB_EXT_MESES = "INTERCALIB_EQUIP_EXT" },
    @{ ESTUDOS_R_R          = "BIT:ESTUDOS_R_R" },
    @{ FREQ_ESTUDO_R_R      = "FREQ_ESTUDO_R_R" },
    @{ EM_UTILIZACAO        = "BIT:EQUIP_EM_UTILIZACAO" },
    @{ OBSOLETO             = "BIT:EQUIP_OBSOLETO" },
    @{ OBSERVACOES          = "OBSERVACOES_EQUIP" },
    @{ ERRO                 = "ERRO_EQUIP" },
    @{ GAMA_UTILIZACAO      = "GAMA_UTILIZ_EQUIP" },
    @{ NORMAS               = "NORMAS_EQUIP" },
    @{ IMAGEM_IT_OPERACIONAL= "IMAGEM_EQUIP_IT_OPERAT" },
    @{ FOTO                 = "FOTO_EQUIP" },
    @{ ATIVO                = "LITERAL:1" }
) "ID_EQUIPAMENTO"

# 10. QUA_MC_GABARITOS
Import-Mapped "T - LISTA DE GABARITS" "QUA_MC_GABARITOS" @(
    @{ ID_GABARITO            = "ID_LISTA GABARITS" },
    @{ NOME_GABARITO          = "NOME_GABARIT" },
    @{ CODIGO_GABARITO        = "CODIGO_GABARIT" },
    @{ COD_DESENHO_TECNICO    = "COD_D_TECNICO" },
    @{ REFERENCIA             = "REFERENCIA" },
    @{ ID_SECCAO              = "SECCAO" },
    @{ DATA_ENTRADA           = "DATA_ENTR_GABRIT" },
    @{ ID_RESP_ENTRADA        = "RESP_ENTRAD_GABARIT" },
    @{ EM_UTILIZACAO          = "BIT:GABARIT_EM_UTIZACAO" },
    @{ OBSOLETO               = "BIT:GABARIT_OBSOLETO" },
    @{ OBSERVACOES            = "OBSERVACOES_GABARIT" },
    @{ FOTOGRAFIA             = "FOTOGRAFIA_GABARIT" },
    @{ G_DIMENSIONAL          = "BIT:G_DIMENSIONAL" },
    @{ G_CONTROLO             = "BIT:G_CONTROLO" },
    @{ G_ISOSTATICO           = "BIT:G_ISOSTATICO" },
    @{ FREQ_VERIFICACAO_MESES = "FREQ_VERIF_GABARIT" },
    @{ TOLERANCIA             = "TOLERANCIA" },
    @{ UNID_MEDIDA_TOLERANCIA = "UNID_MEDIDA_TOLER" },
    @{ JAN = "BIT:JAN" }, @{ FEV = "BIT:FEV" }, @{ MAR = "BIT:MAR" },
    @{ ABR = "BIT:ABR" }, @{ MAI = "BIT:MAI" }, @{ JUN = "BIT:JUN" },
    @{ JUL = "BIT:JUL" }, @{ AGO = "BIT:AGO" }, @{ SET = "BIT:SET" },
    @{ OUT = "BIT:OUT" }, @{ NOV = "BIT:NOV" }, @{ DEZ = "BIT:DEZ" },
    @{ ATIVO = "LITERAL:1" }
) "ID_GABARITO"

# 11. QUA_MC_MAQUINAS
Import-Mapped "T - HIST${oa}RICO DE MAQUINAS" "QUA_MC_MAQUINAS" @(
    @{ ID_MAQUINA        = "ID_MAQ" },
    @{ DESIGNACAO        = "DESIG_MAQ" },
    @{ NUM_MAQUINA       = "NUM_MAQ" },
    @{ ID_SECCAO         = "SECCAO" },
    @{ PARAMETROS        = "PARAMETROS" },
    @{ TOLERANCIA        = "TOLERANCIA" },
    @{ FREQ_VERIFICACAO  = "FREQ_VERIF" },
    @{ REL_ANEXO         = "REL_ANEXO" },
    @{ VALOR_EXIGIDO     = "VALOR EXIGIDO" },
    @{ JAN = "BIT:JAN" }, @{ FEV = "BIT:FEV" }, @{ MAR = "BIT:MAR" },
    @{ ABR = "BIT:ABR" }, @{ MAI = "BIT:MAI" }, @{ JUN = "BIT:JUN" },
    @{ JUL = "BIT:JUL" }, @{ AGO = "BIT:AGO" }, @{ SET = "BIT:SET" },
    @{ OUT = "BIT:OUT" }, @{ NOV = "BIT:NOV" }, @{ DEZ = "BIT:DEZ" },
    @{ ATIVO = "LITERAL:1" }
) "ID_MAQUINA"

# 12. QUA_MC_SALAS
Import-Mapped "T - HIST${oa}RICO POR SEC${cc}${at}O" "QUA_MC_SALAS" @(
    @{ ID_SALA             = "ID_SALAS" },
    @{ DESIGNACAO_ESPACO   = "DESIG_ESPACO" },
    @{ ID_SECCAO           = "SECCAO" },
    @{ PARAMETROS          = "PARAMETROS" },
    @{ RESULTADOS_EXIGIDOS = "RESULTADOS EXIGIDOS" },
    @{ OBSERVACOES         = "OBSERVACOES" },
    @{ FREQ_TEORICA        = "FREQ_TEORICA" },
    @{ JAN = "BIT:JAN" }, @{ FEV = "BIT:FEV" }, @{ MAR = "BIT:MAR" },
    @{ ABR = "BIT:ABR" }, @{ MAI = "BIT:MAI" }, @{ JUN = "BIT:JUN" },
    @{ JUL = "BIT:JUL" }, @{ AGO = "BIT:AGO" }, @{ SET = "BIT:SET" },
    @{ OUT = "BIT:OUT" }, @{ NOV = "BIT:NOV" }, @{ DEZ = "BIT:DEZ" },
    @{ ATIVO = "LITERAL:1" }
) "ID_SALA"

# 13. QUA_MC_MOV_CALIB_EQUIP
Import-Mapped "T - REGISTO INDIVIDUAL CALIB EQUIP" "QUA_MC_MOV_CALIB_EQUIP" @(
    @{ ID_CALIB_EQUIP          = "ID_REGIST_IND_CALIBR" },
    @{ ID_EQUIPAMENTO          = "DESIGNACAOEQUIP" },
    @{ IT_METODO_OPERACIONAL   = "IT_METODO_OPERAT_EQUIP" },
    @{ IT_CALIBRACAO           = "IT_CALIBR_EQUIP" },
    @{ IT_VERIFICACAO_INTERNA  = "IT_VERIFIC_INTERN_EQUIP" },
    @{ ATIVO                   = "LITERAL:1" }
) "ID_CALIB_EQUIP"

# 14. QUA_MC_MOV_CALIB_EQUIP_DET
Import-Mapped "T - SUB FORM - REG CALIB EQUIP" "QUA_MC_MOV_CALIB_EQUIP_DET" @(
    @{ ID_CALIB_EQUIP_DET          = "ID_SUBFORM_REG_IND_CALIB_EQUIP" },
    @{ ID_CALIB_EQUIP              = "ID_REGIST_IND_CALBRICAO" },
    @{ NUM                         = "NUM" },
    @{ ID_TIPO_CALIBRACAO          = "TIPO_CALIB_EQUP" },
    @{ ID_TIPO_ACEITACAO           = "TIPO_ACEIT_CALIB_EQUIP" },
    @{ NUM_CERTIF_EXTERNO          = "NUM_CERTIF_CALIB_EXTERNO" },
    @{ ANEXO_CERTIF_EXTERNO        = "ANEXO_CERTIF_EXT" },
    @{ ID_ENTIDADE_CALIBRACAO      = "ABREVIATURA_ENTIDADE" },
    @{ DATA_CALIBRACAO             = "DATA_CALIB_EQUP" },
    @{ VALIDADE_CALIBRACAO         = "VALID_CALIBR" },
    @{ NUM_VALIDADE_CALIBRACAO     = "Nº_VALID_CALIB" },
    @{ ID_RESP_VALIDACAO           = "RESP_VALIDACAO" },
    @{ ID_RESULTADO_VALIDACAO      = "RESULTADO_VALIDACAO" },
    @{ PERIODO_INTERCALIB_ANTERIOR = "PER_INTERCA_ANTE" },
    @{ OBSERVACOES                 = "OBSERVACOES_SUB_CALIB" },
    @{ ATIVO                       = "LITERAL:1" }
) "ID_CALIB_EQUIP_DET" @("ID_REGIST_IND_CALBRICAO")

# 15. QUA_MC_MOV_VERIF_GABARITO
Import-Mapped "T - SUB FORM LISTA GABARIT" "QUA_MC_MOV_VERIF_GABARITO" @(
    @{ ID_VERIF_GABARITO     = "ID_SUBF_LISTA GABARITS" },
    @{ ID_GABARITO           = "ID_LISTA GABARITS" },
    @{ NUM                   = "NUM_" },
    @{ DATA_VERIFICACAO      = "DATA_VERIFIC" },
    @{ NUM_VERIFICACAO       = "NUM_VERIF" },
    @{ REL_VERIFICACAO       = "REL_VERIF" },
    @{ ID_RESP_VERIFICACAO   = "RESP_VERIFICAÇÃO" },
    @{ PERIODO_INTERCALIB    = "PERIOD_INTERCALB_VERFI" },
    @{ ID_TIPO_VERIF_GABARIT = "TIPO_VERIF_GABARIT" },
    @{ ID_TIPO_ACEITACAO     = "TIPO_ACEIT_CALIB_EQUIP" },
    @{ PERC_R_R              = "PERC_RR" },
    @{ ATIVO                 = "LITERAL:1" }
) "ID_VERIF_GABARITO" @("ID_LISTA GABARITS")

# 16. QUA_MC_MOV_VERIF_MAQUINA
Import-Mapped "T - SUB_FORM HISTOR MAQ" "QUA_MC_MOV_VERIF_MAQUINA" @(
    @{ ID_VERIF_MAQUINA    = "ID_SUB_MAQ" },
    @{ ID_MAQUINA          = "ID_MAQ" },
    @{ NUM                 = "NUM" },
    @{ DATA_VERIFICACAO    = "DATA_V" },
    @{ ID_TIPO_VERIFICACAO = "TIPO_VERIFICACAO" },
    @{ NUM_VERIFICACAO     = "NUME_VERIF" },
    @{ REL_VERIFICACAO     = "REL_VERIF" },
    @{ FREQUENCIA          = "FREQUENCIA_V" },
    @{ ID_RESP_VERIFICACAO = "RESP_V" },
    @{ ID_TIPO_ACEITACAO   = "TIPO_ACEIT_CALIB_EQUIP" },
    @{ ATIVO               = "LITERAL:1" }
) "ID_VERIF_MAQUINA"

# 17. QUA_MC_MOV_VERIF_SALA
Import-Mapped "T - SUB FORM _ SALAS" "QUA_MC_MOV_VERIF_SALA" @(
    @{ ID_VERIF_SALA       = "ID_SUBFORM_SALAS" },
    @{ ID_SALA             = "ID_SALAS" },
    @{ NUM                 = "NUM" },
    @{ DATA_VERIFICACAO    = "DATA_VER" },
    @{ ID_TIPO_VERIFICACAO = "TIPO_VERIF" },
    @{ NUM_VERIFICACAO     = "NUM_VERIF" },
    @{ REL_ANEXO           = "REL_ANEXO" },
    @{ FREQ_VERIFICACAO    = "FREQ_VERF" },
    @{ ID_RESP_VERIFICACAO = "RESP_VERIF" },
    @{ ID_TIPO_ACEITACAO   = "TIPO_ACEIT_CALIB_EQUIP" },
    @{ ATIVO               = "LITERAL:1" }
) "ID_VERIF_SALA"

# ============================================================
$accConn.Close()
$sqlConn.Close()
Write-Host ""
Write-Host "=== Import concluído! ==="
