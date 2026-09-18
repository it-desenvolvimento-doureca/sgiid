# ============================================================
# Import de dados: Access -> SQL Server - QUA_CR (Laboratorio Cromagem)
# Origem: "FAMILIA 5_2026_Tabelas.accdb" (pasta Downloads)
# ============================================================
# Corre primeiro o DDL: sql\QUA_CROMAGEM_MODULO_COMPLETO.sql
# Plano e decisoes:     docs\PLANO_QUA_CROMAGEM_2026-09-02.md
#
# Ensaio a seco (le o Access, transforma tudo, nao escreve nada no SQL):
#   powershell -ExecutionPolicy Bypass -File "C:\work\Workspace\sgiid\sql\QUA_CROMAGEM_IMPORT_ACCESS.ps1" -DryRun
#
# Import a serio:
#   $env:SGIID_SQL_PASS = "..."
#   powershell -ExecutionPolicy Bypass -File "C:\work\Workspace\sgiid\sql\QUA_CROMAGEM_IMPORT_ACCESS.ps1"
#
# NOTA DE ENCODING: este ficheiro e ASCII de proposito. O PowerShell 5.1 le
# .ps1 como Windows-1252 e corrompe UTF-8 multibyte, portanto nao levar acentos
# para aqui (o mesmo cuidado esta em QUA_MC_IMPORT_ACCESS.ps1).
#
# NOTA DE LEITURA: as colunas normais leem-se por ODBC; as FOTOGRAFIAS TEM de
# ser lidas por DAO, porque sao um campo Attachment do Access (DAO Type=101) e
# o ODBC nao o le - devolve so um handle interno de 1-4 digitos, o que faz o
# campo parecer preenchido a 100% quando nao esta.
# ============================================================

param(
    # Ficheiro de origem. Por omissao procura-o na pasta Downloads, sem
    # depender dos acentos do nome ("FAMILIA" vs "FAMILIA").
    [string] $AccessFile = "",

    # 192.168.40.126 = TESTES (instancia DEVDOURECA, ver ConnectionSQL.java:53,
    # que aponta 192.168.40.126:54447 / SGIID_DEV)
    # 192.168.40.101 = PRODUCAO (instancia DOURECA)
    #
    # O valor por omissao e o de TESTES, de proposito: um import destes escreve
    # dezenas de milhares de linhas, e nao deve ser possivel acertar em
    # producao por distraccao. Para producao e preciso -EmProducao (ver abaixo).
    [string] $SqlServer  = "192.168.40.126,1433",
    [string] $SqlDb      = "SGIID",
    [string] $SqlUser    = "sa",

    # Autenticacao integrada do Windows em vez de utilizador/password.
    # Mais simples do que passar -SqlUser "" na linha de comandos.
    [switch] $AutWindows,

    # dicionarios | conjequip | referencias | relatorios | fotografias
    [string[]] $Fases = @("dicionarios","conjequip","referencias","relatorios","fotografias"),

    # Le e transforma tudo, mostra o relatorio, nao escreve no SQL Server.
    [switch] $DryRun,

    # Apaga o que ja tinha sido importado (linhas com ID_ACCESS_LEGADO) antes
    # de importar de novo. Sem isto, uma segunda passagem duplica.
    [switch] $Reimportar,

    # Obrigatorio para escrever em producao (192.168.40.101). Sem isto, um
    # import apontado a producao para antes de abrir a ligacao.
    [switch] $EmProducao,

    # As fotografias sao 833 imagens / 249,9 MB de originais. Em base64 numa
    # coluna NVARCHAR(MAX) (2 bytes por caracter) seriam ~666 MB na BD. Como
    # servem de ilustracao no cabecalho do ensaio, reduzem-se na importacao.
    # -FotoMaxPx 0 importa no tamanho original.
    [int] $FotoMaxPx = 1200,
    [int] $FotoQualidade = 80
)

$ErrorActionPreference = "Stop"
Add-Type -AssemblyName System.Drawing

# Chamado com -File, o PowerShell passa "-Fases a,b,c" como UM token e nao
# como array, o que fazia as fases serem todas ignoradas em silencio. Aceita
# as duas formas.
$Fases = @($Fases | ForEach-Object { $_ -split '\s*,\s*' } | Where-Object { $_ } | ForEach-Object { $_.Trim().ToLower() })
$fasesValidas = @("dicionarios","conjequip","referencias","relatorios","fotografias")
$fasesMas = @($Fases | Where-Object { $fasesValidas -notcontains $_ })
if ($fasesMas.Count -gt 0) {
    Write-Error "Fase(s) desconhecida(s): $($fasesMas -join ', '). Validas: $($fasesValidas -join ', ')"
    exit 1
}

# ============================================================
# 0. ORIGEM
# ============================================================

if (-not $AccessFile) {
    $cand = Get-ChildItem "$env:USERPROFILE\Downloads" -Filter "*.accdb" -ErrorAction SilentlyContinue |
            Where-Object { $_.Name -match '5_\d{4}_Tabelas' } | Select-Object -First 1
    if (-not $cand) {
        $cand = Get-ChildItem "$env:USERPROFILE\Downloads" -Filter "*Tabelas.accdb" -ErrorAction SilentlyContinue |
                Select-Object -First 1
    }
    if (-not $cand) { Write-Error "Ficheiro .accdb de tabelas nao encontrado em Downloads."; exit 1 }
    $AccessFile = $cand.FullName
}
if (-not (Test-Path $AccessFile)) { Write-Error "Nao existe: $AccessFile"; exit 1 }
Write-Host "Origem : $AccessFile" -ForegroundColor Cyan
Write-Host "Modo   : $(if ($DryRun) { 'ENSAIO A SECO (nao escreve no SQL)' } else { 'IMPORT' })" -ForegroundColor Cyan

# O destino aparece sempre, e nao so quando a ligacao abre: e a diferenca
# entre escrever em testes e escrever em producao.
$ehProducao = ($SqlServer -match '192\.168\.40\.101')
Write-Host "Alvo   : $SqlServer / $SqlDb  ($(if ($ehProducao) { 'PRODUCAO' } else { 'testes' }))" `
    -ForegroundColor $(if ($ehProducao) { "Red" } else { "Cyan" })

if ($ehProducao -and -not $DryRun -and -not $EmProducao) {
    Write-Error ("Alvo de PRODUCAO sem -EmProducao. Este import escreve dezenas de milhares de " +
                 "linhas; se e mesmo em producao que queres escrever, acrescenta -EmProducao.")
    exit 1
}

$accConnStr = "Driver={Microsoft Access Driver (*.mdb, *.accdb)};Dbq=$AccessFile;"
$acc = New-Object System.Data.Odbc.OdbcConnection($accConnStr)
try { $acc.Open() } catch { Write-Error "Erro ao abrir o Access: $_"; exit 1 }

# O ",$t" e obrigatorio: um DataTable e enumeravel, e o PowerShell desenrola-o
# em DataRow quando sai de uma funcao - a virgula devolve-o como objeto unico.
function AccTable([string] $sql) {
    $a = New-Object System.Data.Odbc.OdbcDataAdapter($sql, $acc)
    $t = New-Object System.Data.DataTable
    [void]$a.Fill($t)
    return ,$t
}

function AccTemTabela([string] $nome) {
    $c = $acc.CreateCommand()
    $c.CommandText = "SELECT COUNT(*) FROM [$nome]"
    try { [void]$c.ExecuteScalar(); return $true } catch { return $false }
}

function AccTemColuna([string] $tabela, [string] $coluna) {
    $c = $acc.CreateCommand()
    $c.CommandText = "SELECT COUNT([$coluna]) FROM [$tabela]"
    try { [void]$c.ExecuteScalar(); return $true } catch { return $false }
}

# ============================================================
# 1. DESTINO
# ============================================================

$sql = $null

# A connection string e a mesma para o ensaio a seco e para o import: se
# -SqlUser vier vazio usa-se autenticacao integrada do Windows, senao
# utilizador/password com a password em SGIID_SQL_PASS.
function ConnStr([int] $timeout) {
    if ($SqlUser -and -not $AutWindows) {
        return "Server=$SqlServer;Database=$SqlDb;User Id=$SqlUser;Password=$($env:SGIID_SQL_PASS);Connect Timeout=$timeout;"
    }
    return "Server=$SqlServer;Database=$SqlDb;Integrated Security=True;Connect Timeout=$timeout;"
}

function ExplicaFalha($ex) {
    $m = $ex.Exception.Message
    # O SQL Server distingue estes casos, e a causa e diferente em cada um.
    if ($m -match 'Login failed') {
        Write-Host "         Credenciais recusadas pelo servidor (a ligacao chegou la)." -ForegroundColor Yellow
        Write-Host "         Confirma a password em SGIID_SQL_PASS, ou usa autenticacao" -ForegroundColor Yellow
        Write-Host "         do Windows com -AutWindows" -ForegroundColor Yellow
    } elseif ($m -match 'Cannot open database|4060') {
        Write-Host "         O login e valido mas a base de dados '$SqlDb' nao esta acessivel." -ForegroundColor Yellow
        Write-Host "         Em testes pode chamar-se SGIID_DEV: usa -SqlDb SGIID_DEV" -ForegroundColor Yellow
    } elseif ($m -match 'network-related|not accessible|timeout|Timeout') {
        Write-Host "         Nao se chegou ao servidor. Confirma -SqlServer (host,porta)." -ForegroundColor Yellow
    }
}

# Em ensaio a seco tambem se liga, para os dicionarios e os mapeamentos serem
# lidos do destino: sem eles, as anomalias "resultado-desconhecido" e
# "tipo-ensaio-desconhecido" sao falsos positivos - nao ha com que comparar.
# So se le, nunca se escreve (ver Ins(), SqlExec()).
if ($DryRun) {
    if ($SqlUser -and -not $AutWindows -and -not $env:SGIID_SQL_PASS) {
        Write-Host "Destino: SGIID_SQL_PASS nao definida - o ensaio corre so com o Access;" -ForegroundColor Yellow
        Write-Host "         as verificacoes de dicionarios e mapeamentos ficam por fazer" -ForegroundColor Yellow
    } else {
        try {
            $sql = New-Object System.Data.SqlClient.SqlConnection((ConnStr 15))
            $sql.Open()
            Write-Host "Destino: $SqlServer / $SqlDb  OK (so leitura)" -ForegroundColor Cyan
        } catch {
            $sql = $null
            Write-Host "Destino: sem ligacao ($($_.Exception.Message.Split([char]10)[0]))" -ForegroundColor Yellow
            ExplicaFalha $_
            Write-Host "         o ensaio corre so com o Access; as verificacoes de" -ForegroundColor Yellow
            Write-Host "         dicionarios e mapeamentos ficam por fazer" -ForegroundColor Yellow
        }
    }
}

if (-not $DryRun) {
    if ($SqlUser -and -not $AutWindows -and -not $env:SGIID_SQL_PASS) {
        Write-Error "Variavel de ambiente SGIID_SQL_PASS nao definida (ou usa -AutWindows)."
        exit 1
    }
    $sql = New-Object System.Data.SqlClient.SqlConnection((ConnStr 30))
    try { $sql.Open(); Write-Host "Destino: $SqlServer / $SqlDb  OK" -ForegroundColor Cyan }
    catch {
        Write-Host "Erro ao ligar ao SQL Server: $($_.Exception.Message.Split([char]10)[0])" -ForegroundColor Red
        ExplicaFalha $_
        exit 1
    }
}

$script:contagens = @{}
$script:anomalias = New-Object System.Collections.ArrayList
$script:idFalso = 0

function Anomalia([string] $tipo, [string] $texto) {
    [void]$script:anomalias.Add([pscustomobject]@{ Tipo = $tipo; Detalhe = $texto })
}

function Conta([string] $tabela) {
    if (-not $script:contagens.ContainsKey($tabela)) { $script:contagens[$tabela] = 0 }
    $script:contagens[$tabela]++
}

function EnsureSqlOpen {
    if ($sql -and $sql.State -ne [System.Data.ConnectionState]::Open) { $sql.Open() }
}

# Larguras reais das colunas de texto do destino, lidas do schema. Servem para
# nunca abortar o import por "String or binary data would be truncated": um
# unico valor mau numa origem de 1423 linhas matava a corrida a meio e deixava
# dados parciais - foi o que aconteceu com um 'TESTE 3' que tinha 3612 espacos
# a mais no meio do texto.
$script:larguras = @{}

function CarregarLarguras {
    if (-not $sql) { return }
    $q = "SELECT TABLE_NAME, COLUMN_NAME, CHARACTER_MAXIMUM_LENGTH " +
         "FROM INFORMATION_SCHEMA.COLUMNS " +
         "WHERE TABLE_NAME LIKE 'QUA_CR!_%' ESCAPE '!' AND CHARACTER_MAXIMUM_LENGTH > 0"
    foreach ($r in (SqlTabela $q).Rows) {
        $script:larguras["$($r['TABLE_NAME']).$($r['COLUMN_NAME'])"] = [int]$r["CHARACTER_MAXIMUM_LENGTH"]
    }
}

# Ajusta um texto a largura da coluna. Primeiro tenta colapsar corridas de 3+
# espacos (que e o defeito real quando um campo estoura por milhares de
# caracteres); so corta se ainda nao couber. Os \r \n \t ficam intactos, para
# nao destruir a formatacao dos procedimentos.
function Cabe([string] $tabela, [string] $coluna, $valor) {
    if ($valor -isnot [string]) { return $valor }
    $k = "$tabela.$coluna"
    if (-not $script:larguras.ContainsKey($k)) { return $valor }
    $max = $script:larguras[$k]
    if ($valor.Length -le $max) { return $valor }

    $v = [regex]::Replace($valor, ' {3,}', ' ')
    if ($v.Length -le $max) {
        Anomalia "espacos-colapsados [$k]" "$($valor.Length) -> $($v.Length) caracteres"
        return $v
    }
    Anomalia "texto-truncado [$k]" "$($v.Length) -> $max caracteres: '$($v.Substring(0, [Math]::Min(60, $v.Length)))...'"
    return $v.Substring(0, $max)
}

# Insere uma linha e devolve a chave gerada. Em ensaio a seco devolve uma
# chave falsa, para os filhos poderem ser transformados da mesma maneira.
function Ins([string] $tabela, [hashtable] $vals) {
    Conta $tabela

    # As larguras sao verificadas ANTES do desvio do ensaio a seco, de
    # proposito: a primeira versao devolvia a chave falsa primeiro, e por isso
    # o ensaio nao apanhou um texto de 3635 caracteres que abortou o import a
    # meio. Um ensaio que nao ve o que o import ve nao serve de ensaio.
    foreach ($k in @($vals.Keys)) {
        if ($vals[$k] -is [string]) { $vals[$k] = Cabe $tabela $k $vals[$k] }
    }

    if ($DryRun) { $script:idFalso++; return $script:idFalso }

    EnsureSqlOpen
    $cols = @($vals.Keys)
    $params = @()
    for ($i = 0; $i -lt $cols.Count; $i++) { $params += "@p$i" }
    $cmd = $sql.CreateCommand()
    $cmd.CommandText = "INSERT INTO $tabela (" + ($cols -join ", ") + ") VALUES (" + ($params -join ", ") +
                       "); SELECT CAST(SCOPE_IDENTITY() AS INT);"
    for ($i = 0; $i -lt $cols.Count; $i++) {
        $v = $vals[$cols[$i]]
        if ($null -eq $v) { $v = [System.DBNull]::Value }
        [void]$cmd.Parameters.AddWithValue("@p$i", $v)
    }
    try {
        return [int]$cmd.ExecuteScalar()
    } catch {
        # Um erro de tipo dizia so "SqlDbType.Time excedida" sem dizer em que
        # tabela nem com que valores, o que obrigava a procurar a agulha no
        # palheiro. Aqui a linha inteira vai para o ecra antes de rebentar.
        Write-Host ""
        Write-Host "FALHA AO INSERIR EM $tabela" -ForegroundColor Red
        foreach ($k in ($cols | Sort-Object)) {
            $vv = $vals[$k]
            $desc = if ($null -eq $vv) { "<null>" } else { "$vv  ($($vv.GetType().Name))" }
            if ($desc.Length -gt 120) { $desc = $desc.Substring(0, 120) + "..." }
            Write-Host ("  {0,-24} {1}" -f $k, $desc) -ForegroundColor DarkGray
        }
        Write-Host "  Corrige a causa e volta a correr com -Reimportar." -ForegroundColor Yellow
        throw
    }
}

function SqlTabela([string] $q) {
    if ($DryRun -and -not $sql) { return ,(New-Object System.Data.DataTable) }
    EnsureSqlOpen
    $a = New-Object System.Data.SqlClient.SqlDataAdapter($q, $sql)
    $t = New-Object System.Data.DataTable
    [void]$a.Fill($t)
    return ,$t
}

function SqlExec([string] $q) {
    if ($DryRun) { return 0 }
    EnsureSqlOpen
    $c = $sql.CreateCommand(); $c.CommandText = $q
    return $c.ExecuteNonQuery()
}

# ============================================================
# 2. CONVERSORES
# ============================================================

# No Access os valores numericos eram todos texto, e vinham de tres formas
# alem do numero simples:
#   "1,5"            - virgula decimal
#   "-" / "--" / "~" - sentinela de "nao aplicavel" (703 das 970 linhas de
#                      condicoes usavam-no no banho SAT)
#   "9.96 (140)"     - numero anotado; o valor entre parentesis parece ser a
#                      cavidade daquela medicao
#   "28.16 (Min)*"   - leitura minima/maxima; o '*' marca alguma coisa
#   "13.54(469) / 13.56(261)" - duas medicoes no mesmo campo
#
# ToDec extrai o numero e descarta a anotacao; ToDecAnot devolve as duas, para
# quem tem onde guardar a anotacao (as amostras de espessura, na OBSERVACOES).
# $ctx e so para o relatorio dizer de que coluna veio o problema.

# Sentinelas de "nao aplicavel". Alem do hifen normal, os dados tem travessoes
# (U+2013) e o sinal de menos tipografico (U+2212) - o Word/Access substituem
# o hifen por eles sem se dar por isso. Escritos em escape para o ficheiro
# continuar ASCII.
function EhSentinela([string] $s) {
    return ($s -match '^[-~.\s\u2013\u2014\u2212]*$')
}

# Numero anotado -> devolve @{ Num; Resto } ou $null se nao der.
function NumeroEAnotacao([string] $s) {
    if ($s -match '^\s*(\d+(?:[.,]\d+)?)\s*(.*)$') {
        $num = $Matches[1].Replace(",", ".")
        $resto = $Matches[2].Trim()
        # Se o que sobra comeca por digito ou ponto, o numero esta malformado
        # (ex. "1.0.97(133)" e um 10.97 mal digitado) - nao se adivinha.
        if ($resto -match '^[\d.]') { return $null }
        $d = 0.0
        if ([double]::TryParse($num, [System.Globalization.NumberStyles]::Float,
                               [System.Globalization.CultureInfo]::InvariantCulture, [ref]$d)) {
            return @{ Num = [decimal]$d; Resto = $resto }
        }
    }
    return $null
}

function ToDec($v, [string] $ctx = "") {
    $r = ToDecAnot $v $ctx
    return $r.Valor
}

# Devolve @{ Valor; Anotacao }. A Anotacao traz o texto original completo
# quando havia mais do que o numero, para nao se perder informacao.
function ToDecAnot($v, [string] $ctx = "") {
    $s = ToStr $v
    if ($null -eq $s) { return @{ Valor = $null; Anotacao = $null } }
    if (EhSentinela $s) { return @{ Valor = $null; Anotacao = $null } }
    $orig = $s
    $sufCtx = $(if ($ctx) { " [$ctx]" } else { "" })

    if ($s.Contains("/")) {
        Anomalia "valor-com-par$sufCtx" "'$orig' (migrado o primeiro)"
        $s = $s.Split("/")[0].Trim()
        if (EhSentinela $s) { return @{ Valor = $null; Anotacao = $orig } }
    }

    $d = 0.0
    if ([double]::TryParse($s.Replace(",", "."), [System.Globalization.NumberStyles]::Float,
                           [System.Globalization.CultureInfo]::InvariantCulture, [ref]$d)) {
        return @{ Valor = [decimal]$d; Anotacao = $(if ($orig -ne $s) { $orig } else { $null }) }
    }

    $na = NumeroEAnotacao $s
    if ($null -ne $na) {
        return @{ Valor = $na.Num; Anotacao = $orig }
    }

    if ($s -match '^\s*\d') { Anomalia "valor-malformado$sufCtx" "'$orig'" }
    else { Anomalia "valor-nao-numerico$sufCtx" "'$orig'" }
    return @{ Valor = $null; Anotacao = $orig }
}

# "00:45:06" e tambem "0:45:04", como aparece no formulario.
#
# Ha 18 valores com erros de digitacao ('0;42:06', '0-:02:13', '0:45.00') que
# se corrigem sem ambiguidade - o ';' e o '.' estao ao lado do ':' no teclado.
# Os que continuarem ilegiveis ficam NULL e reportados.
function ToTime($v) {
    if ($null -eq $v -or $v -is [System.DBNull]) { return $null }
    $s = ([string]$v).Trim()
    if (EhSentinela $s) { return $null }

    # Um par "00:12:38 / 00:20:23" sao dois banhos seguidos; fica o primeiro,
    # com o par reportado para nao se perder a informacao em silencio.
    if ($s.Contains("/")) {
        Anomalia "tempo-com-par" "'$s' (migrado o primeiro)"
        $s = $s.Split("/")[0].Trim()
        if (EhSentinela $s) { return $null }
    }

    # Um numero isolado num campo h:m:s e ambiguo (9 horas? minutos?
    # segundos?), e o TimeSpan.TryParse do .NET interpreta-o como DIAS - foi
    # assim que um TEMPO_SB = '9' virou 9 dias e estourou o SqlDbType.Time.
    # Fica NULL e reportado; nao se adivinha a unidade de uma medicao.
    if ($s -notmatch ':') {
        Anomalia "tempo-sem-unidade" "'$s' (numero isolado num campo h:m:s)"
        return $null
    }

    $ts = [TimeSpan]::Zero
    $ok = [TimeSpan]::TryParse($s, [ref]$ts)
    if (-not $ok) {
        $lim = $s -replace '[;.]', ':' -replace '-', '' -replace '\s', ''
        $ok = [TimeSpan]::TryParse($lim, [ref]$ts)
        if ($ok) { Anomalia "tempo-corrigido" "'$s' -> '$lim'" }
    }
    if (-not $ok) {
        Anomalia "tempo-nao-parseavel" "'$s'"
        return $null
    }

    # O TIME do SQL Server so vai de 00:00:00 a 23:59:59.9999999. Um tempo de
    # banho maior do que isso e erro de digitacao, nao um banho de dois dias.
    if ($ts.TotalHours -ge 24 -or $ts.Ticks -lt 0) {
        Anomalia "tempo-fora-de-intervalo" "'$s' -> $ts (o TIME do SQL nao aceita >= 24h)"
        return $null
    }
    return $ts
}

# Divide um valor que traga varias medicoes no mesmo campo, separadas por '/'.
#
# O formulario Access so tem 5 slots de amostra, mas a ficha tecnica pede 6 em
# 1103 das 1423 referencias. O laboratorio contornou-o a escrever duas
# medicoes no ultimo campo: 'Peca N81/ Peca N61', '0.20/0.19', '1/1'. No 5.
# campo isso acontece em 73% dos registos; nos dois primeiros, nunca.
#
# Aqui voltam a ser amostras separadas, que e o que sempre foram.
function Partes($v) {
    $s = ToStr $v
    # O ",@(...)" e obrigatorio nos tres returns. Sem a virgula o PowerShell
    # desenrola um array de UM elemento numa string, e o Parte() abaixo passa
    # a indexar caracteres em vez de elementos: 'Minima' virava 'M' e '67.39'
    # virava '6'. Foi o que aconteceu no primeiro import, e so as amostras com
    # '/' escaparam, porque essas devolviam um array de dois.
    if ($null -eq $s) { return ,@($null) }
    if (-not $s.Contains("/")) { return ,@($s) }
    $p = @($s.Split("/") | ForEach-Object { $_.Trim() } | Where-Object { $_ -ne "" })
    if ($p.Count -eq 0) { return ,@($null) }
    return ,$p
}

# Devolve a parte i, ou a unica parte quando o campo nao foi desdobrado
# (ex.: mesma peca medida em dois pontos - 'CC1 Esquerdo/CC1 Direito' tem duas
# leituras mas uma so cavidade e um so peso).
function Parte($partes, [int] $i) {
    # Cinto e suspensorios: se ainda assim vier uma string, e um valor unico.
    if ($partes -is [string]) { return $partes }
    if ($null -eq $partes) { return $null }
    $arr = @($partes)
    if ($arr.Count -eq 1) { return $arr[0] }
    if ($i -lt $arr.Count) { return $arr[$i] }
    return $null
}

function ToStr($v) {
    if ($null -eq $v -or $v -is [System.DBNull]) { return $null }
    $s = ([string]$v).Trim()
    if ($s -eq "") { return $null }
    return $s
}

function ToInt($v) {
    if ($null -eq $v -or $v -is [System.DBNull]) { return $null }
    $s = ([string]$v).Trim()
    if ($s -eq "") { return $null }
    $i = 0
    if ([int]::TryParse($s, [ref]$i)) { return $i }
    return $null
}

function ToBool($v) {
    if ($null -eq $v -or $v -is [System.DBNull]) { return $false }
    return ([bool]$v)
}

function ToDate($v) {
    if ($null -eq $v -or $v -is [System.DBNull]) { return $null }
    return [datetime]$v
}

# Os trios _OK / _OKRES(_OKCOND) / _NOK do Access passam a uma coluna.
# NULL = ainda nao avaliado, que e um estado real: 287 das 1063 linhas de
# espessuras tinham as tres flags a False.
#
# Um registo com mais de uma flag ativa e contraditorio (havia exactamente 1,
# no CaCl2). Fica NULL e e reportado, para alguem decidir - inventar aqui um
# resultado num boletim de qualidade seria pior do que deixar por avaliar.
function Res3($ok, $cond, $nok, [string] $ref) {
    $a = [int](ToBool $ok); $b = [int](ToBool $cond); $c = [int](ToBool $nok)
    $t = $a + $b + $c
    if ($t -eq 0) { return $null }
    if ($t -gt 1) { Anomalia "resultado-contraditorio" "$ref (OK=$a OK_COND=$b NOK=$c)"; return $null }
    if ($a -eq 1) { return "OK" }
    if ($b -eq 1) { return "OK_COND" }
    return "NOK"
}

function Res2($ok, $nok, [string] $ref) {
    $a = [int](ToBool $ok); $c = [int](ToBool $nok)
    $t = $a + $c
    if ($t -eq 0) { return $null }
    if ($t -gt 1) { Anomalia "resultado-contraditorio" "$ref (OK=$a NOK=$c)"; return $null }
    if ($a -eq 1) { return "OK" }
    return "NOK"
}

# O Access guardava a norma do cliente na coluna correspondente ao tipo de
# peca, ficando o tipo implicito em qual delas tinha valor. "Evazio" e o
# sentinela de vazio que aparece nas consultas (IIf([...]<>"Evazio")).
function TemValor($v) {
    $s = ToStr $v
    if ($null -eq $s) { return $false }
    if ($s -match '^.?vazio$') { return $false }
    return $true
}

$AUD_CRIA = @{ UTZ_CRIA = $null; DATA_CRIA = (Get-Date) }
function Aud([hashtable] $h) {
    $h["DATA_CRIA"]  = (Get-Date)
    $h["DATA_MODIF"] = (Get-Date)
    $h["ATIVO"]      = $true
    return $h
}

# ============================================================
# 3. PREFLIGHT
# ============================================================

Write-Host ""
Write-Host "=== PREFLIGHT ===" -ForegroundColor Yellow

$tabelasOrigem = @(
    "TABELA DE REFERENCIAS",
    "T - TABELA RESULTADOS CROMAGEM",
    "T - TABELA SUBFORM CONDICOES",
    "T - TABELA SUBFORM ESPESSURAS",
    "T - TABELA SUBFORM PAUTAS ENSAIO",
    "T - TABELA SUBFORM NSS E CACL2",
    "TABELA EQUIPAMENTOS",
    "TABELA CONTROLADORES",
    "T - TABELA RESULTADOS CR APOIO"
)
$falta = @()
foreach ($t in $tabelasOrigem) {
    if (AccTemTabela $t) {
        $n = (AccTable "SELECT COUNT(*) AS N FROM [$t]").Rows[0]["N"]
        Write-Host ("  {0,-36} {1,6} registos" -f $t, $n)
    } else { $falta += $t; Write-Host ("  {0,-36} AUSENTE" -f $t) -ForegroundColor Red }
}
if ($falta.Count -gt 0) { Write-Error "Tabelas de origem em falta: $($falta -join ', ')"; exit 1 }

# O snapshot de 02-09-2026 nao tem estes dois. As consultas do ficheiro da
# Carla ja os usam (o relatorio "EVOLUCAO DE ENSAIOS - DOURECA" filtra por
# Local de Producao = 'Doureca'), portanto num export mais recente existem.
# O script funciona nos dois casos.
$temTabLocal = AccTemTabela "TABELA LOCAL DE PRODUCAO"
$temColLocal = AccTemColuna "T - TABELA RESULTADOS CROMAGEM" "Local de Producao"
Write-Host ("  {0,-36} {1}" -f "TABELA LOCAL DE PRODUCAO", $(if ($temTabLocal) { "presente" } else { "ausente - usa-se 'Doureca'" }))
Write-Host ("  {0,-36} {1}" -f "coluna [Local de Producao]", $(if ($temColLocal) { "presente" } else { "ausente - usa-se 'Doureca'" }))
if (-not $temColLocal) {
    Anomalia "origem-sem-local-producao" "Todos os relatorios ficam com Local de Producao = 'Doureca'"
}

if ($sql) {
    $t = SqlTabela "SELECT COUNT(*) AS N FROM sys.tables WHERE name LIKE 'QUA_CR_%'"
    $n = [int]$t.Rows[0]["N"]
    Write-Host "  Tabelas QUA_CR_* no destino: $n"
    if ($n -lt 23) { Write-Error "Faltam tabelas no destino. Corre primeiro QUA_CROMAGEM_MODULO_COMPLETO.sql"; exit 1 }
    CarregarLarguras
    Write-Host "  Colunas de texto com largura conhecida: $($script:larguras.Count)"
}

# ============================================================
# 4. REIMPORTACAO
# ============================================================

if ($Reimportar -and -not $DryRun) {
    Write-Host ""
    Write-Host "=== A APAGAR O IMPORT ANTERIOR ===" -ForegroundColor Yellow
    # Ordem inversa das dependencias. Apaga so o que veio do Access
    # (ID_ACCESS_LEGADO preenchido no cabecalho), nunca o que foi criado no SGIID.
    $apagar = @(
        "DELETE l FROM QUA_CR_MOV_CORROSAO_LEITURA l INNER JOIN QUA_CR_MOV_CORROSAO_AMOSTRA a ON a.ID_CORROSAO_AMOSTRA = l.ID_CORROSAO_AMOSTRA INNER JOIN QUA_CR_MOV_CORROSAO_CAB c ON c.ID_CORROSAO_CAB = a.ID_CORROSAO_CAB WHERE c.ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE a FROM QUA_CR_MOV_CORROSAO_AMOSTRA a INNER JOIN QUA_CR_MOV_CORROSAO_CAB c ON c.ID_CORROSAO_CAB = a.ID_CORROSAO_CAB WHERE c.ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE FROM QUA_CR_MOV_CORROSAO_CAB WHERE ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE l FROM QUA_CR_MOV_ENSAIO_LEITURA l INNER JOIN QUA_CR_MOV_ENSAIO_TESTE t ON t.ID_ENSAIO_TESTE = l.ID_ENSAIO_TESTE INNER JOIN QUA_CR_MOV_ENSAIO_CAB c ON c.ID_ENSAIO_CAB = t.ID_ENSAIO_CAB WHERE c.ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE t FROM QUA_CR_MOV_ENSAIO_TESTE t INNER JOIN QUA_CR_MOV_ENSAIO_CAB c ON c.ID_ENSAIO_CAB = t.ID_ENSAIO_CAB WHERE c.ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE FROM QUA_CR_MOV_ENSAIO_CAB WHERE ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE e FROM QUA_CR_MOV_ESPESSURA e INNER JOIN QUA_CR_MOV_ESPESSURA_CAB c ON c.ID_ESPESSURA_CAB = e.ID_ESPESSURA_CAB WHERE c.ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE FROM QUA_CR_MOV_ESPESSURA_CAB WHERE ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE d FROM QUA_CR_MOV_CONDICOES d INNER JOIN QUA_CR_MOV_CONDICOES_CAB c ON c.ID_CONDICOES_CAB = d.ID_CONDICOES_CAB WHERE c.ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE FROM QUA_CR_MOV_CONDICOES_CAB WHERE ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE FROM QUA_CR_MOV_RELATORIO WHERE ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE f FROM QUA_CR_DIC_REFERENCIA_FICHEIROS f INNER JOIN QUA_CR_DIC_REFERENCIA r ON r.ID_REFERENCIA = f.ID_REFERENCIA WHERE r.ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE t FROM QUA_CR_DIC_REF_TESTE t INNER JOIN QUA_CR_DIC_REFERENCIA r ON r.ID_REFERENCIA = t.ID_REFERENCIA WHERE r.ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE FROM QUA_CR_DIC_REFERENCIA WHERE ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE l FROM QUA_CR_DIC_CONJ_EQUIP_LIN l INNER JOIN QUA_CR_DIC_CONJ_EQUIP c ON c.ID_CONJ_EQUIP = l.ID_CONJ_EQUIP WHERE c.ID_ACCESS_LEGADO IS NOT NULL",
        "DELETE FROM QUA_CR_DIC_CONJ_EQUIP WHERE ID_ACCESS_LEGADO IS NOT NULL"
    )
    foreach ($q in $apagar) { $n = SqlExec $q; Write-Host "  -$n" }
}

# ============================================================
# 5. DICIONARIOS (leitura dos que o DDL semeou + local de producao)
# ============================================================

$mapTipoEnsaio  = @{}
$mapTipoPeca    = @{}
$mapTipoSuperf  = @{}
$mapAspecto     = @{}
$mapResultado   = @{}   # ID do Access -> ID novo (a semente usou ORDEM = ID do Access)
$mapPatamar     = @{}   # "TIPO|ORDEM" -> ID
$mapLocal       = @{}

if ($Fases -contains "dicionarios") {
    Write-Host ""
    Write-Host "=== DICIONARIOS ===" -ForegroundColor Yellow

    if ($sql) {
        foreach ($r in (SqlTabela "SELECT ID_TIPO_ENSAIO, CODIGO FROM QUA_CR_DIC_TIPO_ENSAIO").Rows) {
            $mapTipoEnsaio[[string]$r["CODIGO"]] = [int]$r["ID_TIPO_ENSAIO"] }
        foreach ($r in (SqlTabela "SELECT ID_TIPO_PECA, DESCRICAO FROM QUA_CR_DIC_TIPO_PECA").Rows) {
            $mapTipoPeca[[string]$r["DESCRICAO"]] = [int]$r["ID_TIPO_PECA"] }
        foreach ($r in (SqlTabela "SELECT ID_TIPO_SUPERFICIE, DESCRICAO FROM QUA_CR_DIC_TIPO_SUPERFICIE").Rows) {
            $mapTipoSuperf[[string]$r["DESCRICAO"]] = [int]$r["ID_TIPO_SUPERFICIE"] }
        foreach ($r in (SqlTabela "SELECT ID_ASPECTO, CODIGO FROM QUA_CR_DIC_ASPECTO").Rows) {
            $mapAspecto[[string]$r["CODIGO"]] = [int]$r["ID_ASPECTO"] }
        foreach ($r in (SqlTabela "SELECT ID_RESULTADO, ORDEM FROM QUA_CR_DIC_RESULTADO").Rows) {
            $mapResultado[[int]$r["ORDEM"]] = [int]$r["ID_RESULTADO"] }
        foreach ($r in (SqlTabela "SELECT ID_PATAMAR, TIPO, ORDEM FROM QUA_CR_DIC_PATAMAR").Rows) {
            $mapPatamar["$($r["TIPO"])|$($r["ORDEM"])"] = [int]$r["ID_PATAMAR"] }
        foreach ($r in (SqlTabela "SELECT ID_LOCAL_PRODUCAO, DESIGNACAO FROM QUA_CR_DIC_LOCAL_PRODUCAO").Rows) {
            $mapLocal[[string]$r["DESIGNACAO"]] = [int]$r["ID_LOCAL_PRODUCAO"] }

        Write-Host "  tipos de ensaio: $($mapTipoEnsaio.Count)  tipos de peca: $($mapTipoPeca.Count)  superficies: $($mapTipoSuperf.Count)"
        Write-Host "  aspectos: $($mapAspecto.Count)  resultados: $($mapResultado.Count)  patamares: $($mapPatamar.Count)"

        # Os locais de producao que faltarem, do export do Access.
        if ($temTabLocal) {
            foreach ($r in (AccTable "SELECT * FROM [TABELA LOCAL DE PRODUCAO]").Rows) {
                $des = ToStr $r["Local de Producao"]
                if ($des -and -not $mapLocal.ContainsKey($des)) {
                    $id = Ins "QUA_CR_DIC_LOCAL_PRODUCAO" (Aud @{ DESIGNACAO = $des; ORDEM = ($mapLocal.Count + 1) })
                    $mapLocal[$des] = $id
                    Write-Host "  + local de producao: $des"
                }
            }
        }
    } else {
        Write-Host "  (sem ligacao ao destino: dicionarios nao verificados)" -ForegroundColor Yellow
    }
    if ($mapLocal.Count -eq 0) { $mapLocal["Doureca"] = $null }
}

$idLocalDefeito = if ($mapLocal.ContainsKey("Doureca")) { $mapLocal["Doureca"] } else { $null }

# ============================================================
# 6. MAPEAMENTOS MANUAIS (controladores, equipamentos, linhas)
# ============================================================
# Estes tres nao se resolvem por codigo: os controladores sao nomes de pessoas,
# e os equipamentos e as linhas dependem do que existe no SGIID. O script
# tenta casar sozinho e escreve o que faltar num CSV ao lado do .accdb, para
# ser preenchido a mao e reaproveitado nas passagens seguintes.

$pastaMapas = Split-Path $AccessFile -Parent
$csvControladores = Join-Path $pastaMapas "MAPA_CR_CONTROLADORES.csv"
$csvLinhas        = Join-Path $pastaMapas "MAPA_CR_LINHAS.csv"

$mapControlador = @{}   # ID do Access -> ID_UTILIZADOR
$mapEquipamento = @{}   # codigo EQ*    -> ID_EQUIPAMENTO
$mapLinha       = @{}   # "1" | "2"     -> id_LINHA

Write-Host ""
Write-Host "=== MAPEAMENTOS ===" -ForegroundColor Yellow

$controladores = AccTable "SELECT ID, Controlador FROM [TABELA CONTROLADORES]"
if ($sql) {
    $utz = SqlTabela "SELECT ID_UTILIZADOR, NOME_UTILIZADOR FROM GER_UTILIZADORES"
    foreach ($r in $controladores.Rows) {
        $nome = (ToStr $r["Controlador"])
        if (-not $nome) { continue }
        $alvo = $utz.Rows | Where-Object {
            $u = ([string]$_["NOME_UTILIZADOR"]).Trim()
            $u -eq $nome -or $u.ToUpper() -eq $nome.ToUpper()
        } | Select-Object -First 1
        if ($alvo) {
            $mapControlador[[int]$r["ID"]] = [int]$alvo["ID_UTILIZADOR"]
        } else {
            Anomalia "controlador-sem-utilizador" "$nome (ID Access $($r["ID"]))"
        }
    }
    Write-Host "  controladores casados: $($mapControlador.Count) de $($controladores.Rows.Count)"

    # Fallback manual, se existir CSV preenchido (ID_ACCESS;ID_UTILIZADOR)
    if (Test-Path $csvControladores) {
        foreach ($l in (Import-Csv $csvControladores -Delimiter ";")) {
            if ($l.ID_UTILIZADOR) { $mapControlador[[int]$l.ID_ACCESS] = [int]$l.ID_UTILIZADOR }
        }
        Write-Host "  + CSV de controladores aplicado"
    } elseif ($mapControlador.Count -lt $controladores.Rows.Count) {
        $controladores.Rows | ForEach-Object {
            [pscustomobject]@{ ID_ACCESS = $_["ID"]; NOME = $_["Controlador"]
                               ID_UTILIZADOR = $(if ($mapControlador.ContainsKey([int]$_["ID"])) { $mapControlador[[int]$_["ID"]] } else { "" }) }
        } | Export-Csv $csvControladores -Delimiter ";" -NoTypeInformation -Encoding UTF8
        Write-Host "  -> preenche $csvControladores e volta a correr" -ForegroundColor Yellow
    }

    # Equipamentos: por COD_INTERNO (os codigos EQ* do Access).
    foreach ($r in (SqlTabela "SELECT ID_EQUIPAMENTO, COD_INTERNO FROM QUA_MC_EQUIPAMENTOS WHERE COD_INTERNO IS NOT NULL").Rows) {
        $cod = ([string]$r["COD_INTERNO"]).Trim().ToUpper().Replace(" ", "")
        if ($cod) { $mapEquipamento[$cod] = [int]$r["ID_EQUIPAMENTO"] }
    }
    Write-Host "  equipamentos em Meios de Controlo: $($mapEquipamento.Count)"

    # Linhas de cromagem.
    $linhas = SqlTabela "SELECT ID_LINHA, NOME_LINHA FROM AB_DIC_LINHA WHERE INATIVO = 0 OR INATIVO IS NULL"
    foreach ($n in @("1","2")) {
        $alvo = $linhas.Rows | Where-Object { ([string]$_["NOME_LINHA"]) -match "(^|\D)$n(\D|$)" } | Select-Object -First 1
        if ($alvo) { $mapLinha[$n] = [int]$alvo["ID_LINHA"] }
        else { Anomalia "linha-sem-correspondencia" "LINHA $n" }
    }
    if (Test-Path $csvLinhas) {
        foreach ($l in (Import-Csv $csvLinhas -Delimiter ";")) {
            if ($l.ID_LINHA) { $mapLinha[[string]$l.LINHA_ACCESS] = [int]$l.ID_LINHA }
        }
        Write-Host "  + CSV de linhas aplicado"
    }
    Write-Host "  linhas casadas: $($mapLinha.Count) de 2"
} else {
    Write-Host "  (sem ligacao ao destino: mapeamentos nao verificados)" -ForegroundColor Yellow
}

function IdOperador($v) {
    $i = ToInt $v
    if ($null -eq $i) { return $null }
    if ($mapControlador.ContainsKey($i)) { return $mapControlador[$i] }
    return $null
}

# ============================================================
# 7. CONJUNTOS DE EQUIPAMENTO
# ============================================================
# A "TABELA EQUIPAMENTOS" do Access nao era uma lista de equipamentos: das 47
# linhas, 24 eram combinacoes ("Estufa (EQ39) + Estufa Fria (EQ40) + Q-FOG
# (EQ622)") sobre apenas 25 equipamentos reais. Cada linha vira um conjunto, e
# os codigos EQ* que a designacao contem viram as linhas do conjunto.

$mapConjEquip = @{}   # ID do Access -> ID_CONJ_EQUIP

if ($Fases -contains "conjequip") {
    Write-Host ""
    Write-Host "=== CONJUNTOS DE EQUIPAMENTO ===" -ForegroundColor Yellow
    $semEquip = 0
    foreach ($r in (AccTable "SELECT ID, Designacao FROM [TABELA EQUIPAMENTOS]").Rows) {
        $des = ToStr $r["Designacao"]
        if (-not $des) { continue }
        $idConj = Ins "QUA_CR_DIC_CONJ_EQUIP" (Aud @{
            DESIGNACAO = $des; ID_ACCESS_LEGADO = [int]$r["ID"] })
        $mapConjEquip[[int]$r["ID"]] = $idConj

        $ordem = 0
        foreach ($m in [regex]::Matches($des, 'EQ\s?(\d+)')) {
            $ordem++
            $cod = "EQ" + $m.Groups[1].Value
            $idEquip = $null
            if ($mapEquipamento.ContainsKey($cod)) { $idEquip = $mapEquipamento[$cod] }
            else { $semEquip++; Anomalia "equipamento-sem-meios-controlo" "$cod (conjunto: $des)" }
            [void](Ins "QUA_CR_DIC_CONJ_EQUIP_LIN" (Aud @{
                ID_CONJ_EQUIP = $idConj; ID_EQUIPAMENTO = $idEquip
                COD_INTERNO = $cod; ORDEM = $ordem }))
        }
    }
    Write-Host "  conjuntos: $($mapConjEquip.Count)   linhas sem equipamento em Meios de Controlo: $semEquip"
}

function IdConj($v) {
    $i = ToInt $v
    if ($null -eq $i) { return $null }
    if ($mapConjEquip.ContainsKey($i)) { return $mapConjEquip[$i] }
    return $null
}

# ============================================================
# 8. REFERENCIAS + TESTES PREVISTOS
# ============================================================

$mapReferencia = @{}   # ID_REFERENCIAS_CR -> ID_REFERENCIA
# Para dar nome aos testes do ensaio: "<idRef novo>|<ordem>" -> teste da ficha
# tecnica. No Access a linha do ensaio so guardava o numero do teste.
$mapRefTeste   = @{}
$mapRelRef     = @{}   # ID_RELATORIO novo -> ID_REFERENCIA novo

if ($Fases -contains "referencias") {
    Write-Host ""
    Write-Host "=== REFERENCIAS ===" -ForegroundColor Yellow

    $refs = AccTable "SELECT * FROM [TABELA DE REFERENCIAS] ORDER BY ID_REFERENCIAS_CR"

    # 13 referencias repetidas e 2 vazias no snapshot. Nao se apagam aqui:
    # migram-se todas (o ID do Access distingue-as) e ficam reportadas para
    # serem resolvidas no ecra, que e onde se sabe qual e a boa.
    $vistas = @{}
    $aspectoCols = @(
        @("SAT F","SAT F"), @("SAT I","SAT I"), @("BR HEX","BR HEX"), @("BR TRI","BR TRI"),
        @("GR BR","GR BR"), @("GR SAT I","GR SAT I"), @("GR SAT F","GR SAT F"),
        @("FUM","FUM"), @("SM5077","SM5077")
    )
    $superfCols = @(
        @("Revisao","Revisao"), @("Esniagem","Esniagem"), @("Barra","Barra"), @("Pintura","Pintura")
    )

    foreach ($r in $refs.Rows) {
        $idAcc = [int]$r["ID_REFERENCIAS_CR"]
        $refCod = ToStr $r["REFERENCIA"]
        if (-not $refCod) { Anomalia "referencia-vazia" "ID Access $idAcc" }
        elseif ($vistas.ContainsKey($refCod)) { Anomalia "referencia-duplicada" "$refCod (IDs Access $($vistas[$refCod]) e $idAcc)" }
        else { $vistas[$refCod] = $idAcc }

        # Tipo de peca: o tipo estava implicito em qual das 3 colunas tinha valor,
        # e o valor era a norma do cliente.
        $idTipoPeca = $null; $norma = $null
        if (TemValor $r["AUTOMOVEL EXTERIOR"]) { $idTipoPeca = $mapTipoPeca["Automovel Exterior"]; $norma = ToStr $r["AUTOMOVEL EXTERIOR"] }
        elseif (TemValor $r["AUTOMOVEL INTERIOR"]) { $idTipoPeca = $mapTipoPeca["Automovel Interior"]; $norma = ToStr $r["AUTOMOVEL INTERIOR"] }
        elseif (TemValor $r["NAO AUTOMOVEL"]) { $idTipoPeca = $mapTipoPeca["Nao Automovel"]; $norma = ToStr $r["NAO AUTOMOVEL"] }

        # Superficie e aspecto: grupos de booleanos exclusivos (1309 e 1337 das
        # 1346 referencias ativas tinham exactamente um ativo).
        $idSuperf = $null; $nSuperf = 0
        foreach ($c in $superfCols) { if (ToBool $r[$c[0]]) { $nSuperf++; if (-not $idSuperf) { $idSuperf = $mapTipoSuperf[$c[1]] } } }
        if ($nSuperf -gt 1) { Anomalia "superficie-multipla" "$refCod ($nSuperf ativas)" }

        $idAspecto = $null; $nAsp = 0
        foreach ($c in $aspectoCols) { if (ToBool $r[$c[0]]) { $nAsp++; if (-not $idAspecto) { $idAspecto = $mapAspecto[$c[1]] } } }
        if ($nAsp -gt 1) { Anomalia "aspecto-multiplo" "$refCod ($nAsp ativos)" }

        # Ligacao ao SILVER: propoe-se o codigo quando ele e simples. Nao e FK
        # e nao se confirma contra o SDTPRA aqui - 70 referencias tem '/'
        # (compostas) e nao existem como artigo unico. O que ficar NULL, ou
        # errado, resolve-se no ecra, que tem um filtro "sem SILVER".
        #
        # A descricao fica vazia de proposito: preenche-se quando alguem
        # confirmar a ligacao no picker, e nao com um palpite da migracao.
        $proref = $null
        if ($refCod -and $refCod -notmatch '[/ ]') {
            $proref = $refCod
        }

        $vals = Aud @{
            REFERENCIA             = $refCod
            PROREF_SILVER          = $proref
            REF_PECA_PLASTICA      = ToStr $r["REFERENCIA PECA PLASTICA"]
            CLIENTE                = ToStr $r["CLIENTE"]
            DESIGNACAO             = ToStr $r["DESIGNACAO"]
            MATERIAL               = ToStr $r["MATERIAL"]
            CAVIDADES              = ToStr $r["CAVIDADES"]
            FICHA_TECNICA          = ToStr $r["FICHA TECNICA"]
            CARTELA                = ToStr $r["CARTELA"]
            NUM_AMOSTRAS           = ToInt $r["N_DE AMOSTRAS"]
            ID_TIPO_PECA           = $idTipoPeca
            NORMA_CLIENTE          = $norma
            ID_TIPO_SUPERFICIE     = $idSuperf
            ID_ASPECTO             = $idAspecto
            ESPESSURA_CU           = ToStr $r["ESPESSURA Cu"]
            ESPESSURA_NI           = ToStr $r["ESPESSURA Ni"]
            ESPESSURA_CR           = ToStr $r["ESPESSURA Cr"]
            MICROPOROSO            = ToBool $r["MICROPOROSO"]
            MICROFISSURADO         = ToBool $r["MICROFISSURADO"]
            N_EXIG_POROS           = ToStr $r["N_EXIG_POROS"]
            N_EXIG_FISSURAS        = ToStr $r["N_EXIG_FISSURAS"]
            PAUTA_ENSAIO           = ToStr $r["PAUTA ENSAIO"]
            PAUTA_ESPESSURAS       = ToStr $r["PAUTA_ESPESSURAS"]
            PAUTA_POROS            = ToStr $r["PAUTA_POROS"]
            PAUTA_STEP             = ToStr $r["PAUTA_STEP"]
            PAUTA_CACL2            = ToStr $r["PAUTA_CACL2"]
            PAUTA_NSS              = ToStr $r["PAUTA_NSS"]
            TESTE_CACL2            = ToStr $r["TESTE_CACL2"]
            PROC_CACL2             = ToStr $r["PROC_CACL2"]
            TESTE_NSS              = ToStr $r["TESTE_NSS"]
            PROC_NSS               = ToStr $r["PROC_NSS"]
            ID_ACCESS_LEGADO       = $idAcc
        }
        $vals["ATIVO"] = (ToBool $r["ACTIVO"])
        $idRef = Ins "QUA_CR_DIC_REFERENCIA" $vals
        $mapReferencia[$idAcc] = $idRef

        # Os 7 pares TESTE n / TESTE nn viram linhas. Os pares 6 e 7 nao tinham
        # coluna de procedimento no Access (eram VARCHAR simples).
        for ($n = 1; $n -le 7; $n++) {
            $desCol = "TESTE $n"
            $procCol = "TESTE " + ("$n" * 2)
            $des = ToStr $r[$desCol]
            $proc = $null
            if ($refs.Columns.Contains($procCol)) { $proc = ToStr $r[$procCol] }
            if (-not $des -and -not $proc) { continue }
            [void](Ins "QUA_CR_DIC_REF_TESTE" (Aud @{
                ID_REFERENCIA = $idRef; ORDEM = $n; DESIGNACAO = $des; PROCEDIMENTO = $proc }))
        }
    }
    Write-Host "  referencias: $($mapReferencia.Count)"
}

# ============================================================
# 9. RELATORIOS E OS QUATRO BLOCOS
# ============================================================

if ($Fases -contains "relatorios") {
    Write-Host ""
    Write-Host "=== RELATORIOS ===" -ForegroundColor Yellow

    $selLocal = if ($temColLocal) { ", [Local de Producao] AS LOCAL_PROD" } else { "" }
    $rels = AccTable "SELECT *$selLocal FROM [T - TABELA RESULTADOS CROMAGEM] ORDER BY ID_RESULTCOULOSC"
    $mapRelatorio = @{}

    foreach ($r in $rels.Rows) {
        $idAcc = [int]$r["ID_RESULTCOULOSC"]
        $nrel = ToStr $r["N_RELAT_CR"]
        $ano = $null; $seq = $null
        if ($nrel -and $nrel -match '^\s*(\d+)\s*/\s*(\d+)\s*$') {
            $seq = [int]$Matches[1]; $ano = [int]$Matches[2]
        } else {
            $d = ToDate $r["Data/ Hora Producao"]
            if ($d) { $ano = $d.Year }
            Anomalia "n-relatorio-nao-parseavel" "'$nrel' (ID Access $idAcc)"
        }

        $idRef = $null
        $refAcc = ToInt $r["REFERENCIA"]
        if ($null -ne $refAcc -and $mapReferencia.ContainsKey($refAcc)) { $idRef = $mapReferencia[$refAcc] }
        elseif ($null -ne $refAcc) { Anomalia "relatorio-sem-referencia" "ID Access $idAcc -> ref $refAcc" }

        $tipo = ToStr $r["Tipo"]
        $idTipo = $null
        if ($tipo -and $mapTipoEnsaio.ContainsKey($tipo)) {
            $idTipo = $mapTipoEnsaio[$tipo]
        } elseif ($tipo) {
            # Ha um registo com 'P.N' em vez de 'P.N.' - falta-lhe o ponto
            # final. Sem os pontos os seis codigos continuam distintos
            # (E, P, PN, A, AI, PS), portanto casa-se sem ambiguidade.
            $norm = $tipo.Replace(".", "").Replace(" ", "").ToUpper()
            $alvo = $mapTipoEnsaio.Keys | Where-Object {
                $_.Replace(".", "").Replace(" ", "").ToUpper() -eq $norm } | Select-Object -First 1
            if ($alvo) {
                $idTipo = $mapTipoEnsaio[$alvo]
                Anomalia "tipo-ensaio-normalizado" "'$tipo' -> '$alvo' (ID Access $idAcc)"
            } else {
                Anomalia "tipo-ensaio-desconhecido" "'$tipo' (ID Access $idAcc)"
            }
        }

        # LINHA 1 / LINHA 2 eram dois booleanos exclusivos.
        $idLinha = $null
        if (ToBool $r["LINHA 1"]) { if ($mapLinha.ContainsKey("1")) { $idLinha = $mapLinha["1"] } }
        elseif (ToBool $r["LINHA 2"]) { if ($mapLinha.ContainsKey("2")) { $idLinha = $mapLinha["2"] } }

        $idLocal = $idLocalDefeito
        if ($temColLocal) {
            $lp = ToInt $r["LOCAL_PROD"]
            if ($null -ne $lp) {
                # A coluna do Access e a FK para TABELA LOCAL DE PRODUCAO; a
                # designacao veio para o dicionario, portanto casa-se por nome.
                $des = $null
                if ($temTabLocal) {
                    $t = AccTable "SELECT [Local de Producao] AS D FROM [TABELA LOCAL DE PRODUCAO] WHERE ID = $lp"
                    if ($t.Rows.Count -gt 0) { $des = ToStr $t.Rows[0]["D"] }
                }
                if ($des -and $mapLocal.ContainsKey($des)) { $idLocal = $mapLocal[$des] }
            }
        }

        $idRel = Ins "QUA_CR_MOV_RELATORIO" (Aud @{
            N_RELAT_CR         = $nrel
            ANO                = $ano
            NUM_SEQ            = $seq
            ID_REFERENCIA      = $idRef
            ID_TIPO_ENSAIO     = $idTipo
            ID_LINHA           = $idLinha
            ID_LOCAL_PRODUCAO  = $idLocal
            LOTE               = ToStr $r["LOTE"]
            DATA_HORA_PRODUCAO = ToDate $r["Data/ Hora Producao"]
            DATA_REGISTO       = ToDate $r["Data registo"]
            DATA_ENTRADA_LAB   = ToDate $r["DATA_ENTRADA"]
            DATA_RECECAO       = ToDate $r["DATA_RECEPCAO"]
            FAZ_ESPESSURAS     = ToBool $r["ESPESSURAS_REALIZADO"]
            FAZ_PAUTAS         = ToBool $r["PAUTAS_REALIZADO"]
            FAZ_CORROSAO       = ToBool $r["CACL2_REALIZADO"]
            ID_ACCESS_LEGADO   = $idAcc
        })
        $mapRelatorio[$idAcc] = $idRel
        if ($null -ne $idRef) { $mapRelRef[$idRel] = $idRef }
    }
    Write-Host "  relatorios: $($mapRelatorio.Count)"

    # ---------- Condicoes ----------
    Write-Host "  condicoes..." -NoNewline
    $banhos = @("CU","SB","B","SAT","P","CR","PASS")
    foreach ($r in (AccTable "SELECT * FROM [T - TABELA SUBFORM CONDICOES] ORDER BY ID_CONDICOES").Rows) {
        $idAccPai = ToInt $r["ID_RESULTCOULOSC"]
        if ($null -eq $idAccPai -or -not $mapRelatorio.ContainsKey($idAccPai)) {
            Anomalia "condicoes-orfa" "ID_CONDICOES $($r["ID_CONDICOES"])"; continue
        }
        $idCab = Ins "QUA_CR_MOV_CONDICOES_CAB" (Aud @{
            ID_RELATORIO     = $mapRelatorio[$idAccPai]
            NUM_PECAS        = ToInt $r["NUM_PECAS"]
            SUPERFICIE       = ToDec $r["SUPERFICIE"] "SUPERFICIE"
            OBSERVACOES      = ToStr $r["OBS_COND"]
            ID_ACCESS_LEGADO = ToInt $r["ID_CONDICOES"]
        })
        $ordem = 0
        foreach ($b in $banhos) {
            $ordem++
            $corr = ToDec $r["CORR_$b"] "CORR_$b"
            $temp = ToTime $r["TEMPO_$b"]
            # Banho sem corrente nem tempo nao gera linha: 703 das 970 linhas
            # tinham '-' no SAT, ou seja o banho nao se aplica aquela peca.
            if ($null -eq $corr -and $null -eq $temp) { continue }
            [void](Ins "QUA_CR_MOV_CONDICOES" (Aud @{
                ID_CONDICOES_CAB = $idCab; BANHO = $b; ORDEM = $ordem
                CORRENTE = $corr; TEMPO = $temp }))
        }
    }
    Write-Host " ok"

    # ---------- Espessuras ----------
    Write-Host "  espessuras..." -NoNewline
    foreach ($r in (AccTable "SELECT * FROM [T - TABELA SUBFORM ESPESSURAS] ORDER BY ID_ESPESSURAS").Rows) {
        $idAccPai = ToInt $r["ID_RESULTCOULOSC"]
        if ($null -eq $idAccPai -or -not $mapRelatorio.ContainsKey($idAccPai)) {
            Anomalia "espessuras-orfa" "ID_ESPESSURAS $($r["ID_ESPESSURAS"])"; continue
        }
        $refAnom = "ESP $($r["ID_ESPESSURAS"])"
        $idCab = Ins "QUA_CR_MOV_ESPESSURA_CAB" (Aud @{
            ID_RELATORIO        = $mapRelatorio[$idAccPai]
            DATA_MEDICAO        = ToDate $r["Data medicao"]
            ID_OPERADOR         = IdOperador $r["OPERADOR_ESPESSURAS"]
            POROS               = ToDec $r["Poros"] "Poros"
            RESULTADO_POROS     = Res2 $r["POROS_OK"] $r["POROS_NOK"] "$refAnom poros"
            FISSURAS            = ToStr $r["FISSURAS"]
            RESULTADO_FISSURAS  = Res2 $r["FISSURAS_OK"] $r["FISSURAS_NOK"] "$refAnom fissuras"
            TEM_STEP            = ToBool $r["Step"]
            STEP_ESP_1          = ToStr $r["Step_Esp_1"]
            STEP_ESP_2_1        = ToStr $r["Step_Esp_2-1"]
            STEP_ESP_3_2        = ToStr $r["Step_Esp_3-2"]
            STEP_POT_1          = ToStr $r["Step_Pot_1"]
            STEP_POT_2_1        = ToStr $r["Step_Pot_2-1"]
            STEP_POT_3_2        = ToStr $r["Step_Pot_3-2"]
            STEP_POT_4_3        = ToStr $r["Step_Pot_4-3"]
            ID_CONJ_EQUIP_ESP   = IdConj $r["Equip_Espessuras"]
            ID_CONJ_EQUIP_POROS = IdConj $r["Equip_Poros"]
            ID_CONJ_EQUIP_STEP  = IdConj $r["Equip_Step"]
            RESULTADO_TOTAL     = Res3 $r["RESULT_TOTAL_ESP_OK"] $r["RESULT_TOTAL_ESP_OKRES"] $r["RESULT_TOTAL_ESP_NOK"] "$refAnom total"
            OBSERVACOES         = ToStr $r["Observacoes"]
            ID_ACCESS_LEGADO    = ToInt $r["ID_ESPESSURAS"]
        })

        # 5 slots de coluna -> uma linha por amostra, e um slot pode conter
        # mais de uma amostra (ver Partes()). NUM_AMOSTRA e um contador
        # sequencial do que sai, nao o numero do slot: um 5. slot com duas
        # medicoes vira as amostras 5 e 6.
        #
        # Slot sem nenhum valor nao gera linha - as amostras esgotam-se de
        # forma decrescente (771 registos com Cr no slot 1, 243 no 5).
        $numAmostra = 0
        for ($n = 1; $n -le 5; $n++) {
            $pIdent = Partes $r["Amostra ${n}a"]
            $pCav   = Partes $r["Cavidade${n}a"]
            $pPeso  = Partes $r["Peso ${n}a"]
            $pCu    = Partes $r["Cu ${n}a"]
            $pNi    = Partes $r["Ni ${n}a"]
            $pCr    = Partes $r["Cr ${n}a"]
            $res    = Res3 $r["ESPESSURA${n}A_OK"] $r["ESPESSURA${n}A_OKCOND"] $r["ESPESSURA${n}A_NOK"] "$refAnom slot $n"

            # Quantas amostras este slot traz: manda a maior contagem entre as
            # tres medicoes e a identificacao da peca.
            $k = @($pCr.Count, $pNi.Count, $pCu.Count, $pIdent.Count) |
                 Measure-Object -Maximum | Select-Object -ExpandProperty Maximum
            if ($k -gt 1) { Anomalia "slot-com-varias-amostras" "$refAnom slot $n -> $k amostras" }

            for ($i = 0; $i -lt $k; $i++) {
                $ident = Parte $pIdent $i
                $cav   = Parte $pCav   $i
                $aPeso = ToDecAnot (Parte $pPeso $i) "Peso slot $n"
                $aCu   = ToDecAnot (Parte $pCu   $i) "Cu slot $n"
                $aNi   = ToDecAnot (Parte $pNi   $i) "Ni slot $n"
                $aCr   = ToDecAnot (Parte $pCr   $i) "Cr slot $n"

                # As anotacoes que vinham com as medicoes ("(140)", "(Min)*")
                # nao se perdem: ficam na OBSERVACOES da amostra, identificadas
                # pelo metal a que pertenciam.
                $notas = @()
                if ($aCu.Anotacao) { $notas += "Cu: $($aCu.Anotacao)" }
                if ($aNi.Anotacao) { $notas += "Ni: $($aNi.Anotacao)" }
                if ($aCr.Anotacao) { $notas += "Cr: $($aCr.Anotacao)" }
                if ($aPeso.Anotacao) { $notas += "Peso: $($aPeso.Anotacao)" }
                $obs = $null
                if ($notas.Count -gt 0) {
                    $obs = $notas -join " | "
                    if ($obs.Length -gt 255) { $obs = $obs.Substring(0, 255) }
                }

                if (-not $ident -and -not $cav -and $null -eq $aPeso.Valor -and
                    $null -eq $aCu.Valor -and $null -eq $aNi.Valor -and $null -eq $aCr.Valor -and
                    $null -eq $res -and $null -eq $obs) { continue }
                $numAmostra++
                [void](Ins "QUA_CR_MOV_ESPESSURA" (Aud @{
                    ID_ESPESSURA_CAB = $idCab; NUM_AMOSTRA = $numAmostra; IDENT_AMOSTRA = $ident
                    CAVIDADE = $cav; PESO = $aPeso.Valor
                    ESP_CU = $aCu.Valor; ESP_NI = $aNi.Valor; ESP_CR = $aCr.Valor
                    RESULTADO = $res; OBSERVACOES = $obs }))
            }
        }
    }
    Write-Host " ok"

    # ---------- Pautas de ensaio ----------
    Write-Host "  pautas de ensaio..." -NoNewline
    foreach ($r in (AccTable "SELECT * FROM [T - TABELA SUBFORM PAUTAS ENSAIO] ORDER BY ID_PAUTAS_ENSAIO").Rows) {
        $idAccPai = ToInt $r["ID_RESULTCOULOSC"]
        if ($null -eq $idAccPai -or -not $mapRelatorio.ContainsKey($idAccPai)) {
            Anomalia "pautas-orfa" "ID_PAUTAS_ENSAIO $($r["ID_PAUTAS_ENSAIO"])"; continue
        }
        $refAnom = "PAUTAS $($r["ID_PAUTAS_ENSAIO"])"
        $idCab = Ins "QUA_CR_MOV_ENSAIO_CAB" (Aud @{
            ID_RELATORIO     = $mapRelatorio[$idAccPai]
            DATA_TESTES      = ToDate $r["DATA_TESTES"]
            ID_OPERADOR      = IdOperador $r["OPERADOR_PAUTAS"]
            RESULTADO_TOTAL  = Res3 $r["RESULT_TOTAL_TEST_OK"] $r["RESULT_TOTAL_TEST_OKRES"] $r["RESULT_TOTAL_TEST_NOK"] "$refAnom total"
            OBSERVACOES      = ToStr $r["OBSERVACOES_TESTES"]
            ID_ACCESS_LEGADO = ToInt $r["ID_PAUTAS_ENSAIO"]
        })

        # Tres niveis: o equipamento e por teste (Equip_11), mas o peso, a
        # cavidade e o resultado sao por leitura (Peso_11, Peso_111, ...).
        # A leitura r usa o digito do teste repetido r+1 vezes.
        for ($n = 1; $n -le 7; $n++) {
            $sufTeste = "$n" * 2
            $equipCol = "Equip_$sufTeste"
            if (-not $r.Table.Columns.Contains($equipCol)) { continue }

            $leituras = @()
            for ($k = 1; $k -le 4; $k++) {
                $suf = "$n" * ($k + 1)
                $resCol = "Result_${suf}_CL"
                if (-not $r.Table.Columns.Contains($resCol)) { continue }
                $idResAcc = ToInt $r[$resCol]
                $idRes = $null
                if ($null -ne $idResAcc) {
                    if ($mapResultado.ContainsKey($idResAcc)) { $idRes = $mapResultado[$idResAcc] }
                    else { Anomalia "resultado-desconhecido" "$refAnom teste $n leitura $k -> $idResAcc" }
                }
                $obs = ToStr $r["${resCol}_OBS"]
                $cav = ToStr $r["Cavidade_$suf"]
                # O peso vinha anotado ("13,54(469)") e as vezes com duas
                # medicoes no mesmo campo. Aqui nao se pode desdobrar a leitura
                # como nas amostras - o resultado (ID_RESULTADO) e um so por
                # leitura -, portanto fica o primeiro valor e o texto original
                # junta-se a observacao, para nao se perder.
                $aPeso = ToDecAnot $r["Peso_$suf"] "Peso_$suf"
                $peso = $aPeso.Valor
                if ($aPeso.Anotacao) {
                    $obs = $(if ($obs) { "$obs | Peso: $($aPeso.Anotacao)" } else { "Peso: $($aPeso.Anotacao)" })
                    if ($obs.Length -gt 255) { $obs = $obs.Substring(0, 255) }
                }
                if ($null -eq $idRes -and -not $obs -and -not $cav -and $null -eq $peso) { continue }
                $leituras += @{ K = $k; ID_RES = $idRes; OBS = $obs; CAV = $cav; PESO = $peso }
            }
            $idConj = IdConj $r[$equipCol]
            # Teste sem leituras nem equipamento nao existiu neste relatorio.
            if ($leituras.Count -eq 0 -and $null -eq $idConj) { continue }

            # O nome do teste esta na ficha tecnica da referencia, nao na
            # tabela do ensaio - no Access a linha do ensaio so tinha o numero.
            # Liga-se aqui pela ordem, para o boletim nao sair com os testes
            # anonimos e para o relatorio nao ter de adivinhar.
            $idRefTeste = $null; $desTeste = $null
            if ($null -ne $idRel -and $mapRelRef.ContainsKey($idRel)) {
                $k = "$($mapRelRef[$idRel])|$n"
                if ($mapRefTeste.ContainsKey($k)) {
                    $idRefTeste = $mapRefTeste[$k].Id
                    $desTeste   = $mapRefTeste[$k].Designacao
                }
            }
            $idTeste = Ins "QUA_CR_MOV_ENSAIO_TESTE" (Aud @{
                ID_ENSAIO_CAB = $idCab; NUM_TESTE = $n; ID_CONJ_EQUIP = $idConj
                ID_REF_TESTE = $idRefTeste; DESIGNACAO = $desTeste })
            foreach ($l in $leituras) {
                [void](Ins "QUA_CR_MOV_ENSAIO_LEITURA" (Aud @{
                    ID_ENSAIO_TESTE = $idTeste; NUM_LEITURA = $l.K
                    CAVIDADE = $l.CAV; PESO = $l.PESO
                    ID_RESULTADO = $l.ID_RES; OBSERVACOES = $l.OBS }))
            }
        }
    }
    Write-Host " ok"

    # ---------- NSS / CaCl2 ----------
    Write-Host "  corrosao (NSS/CaCl2)..." -NoNewline
    $patCacl = @("24H","48H","72H","96H","120H","14D","28D")
    $patNss  = @("200H","500H","800H","1000H")
    foreach ($r in (AccTable "SELECT * FROM [T - TABELA SUBFORM NSS E CACL2] ORDER BY ID_CACL2").Rows) {
        $idAccPai = ToInt $r["ID_RESULTCOULOSC"]
        if ($null -eq $idAccPai -or -not $mapRelatorio.ContainsKey($idAccPai)) {
            Anomalia "corrosao-orfa" "ID_CACL2 $($r["ID_CACL2"])"; continue
        }
        $refAnom = "CORR $($r["ID_CACL2"])"
        $idCab = Ins "QUA_CR_MOV_CORROSAO_CAB" (Aud @{
            ID_RELATORIO          = $mapRelatorio[$idAccPai]
            ID_OPERADOR           = IdOperador $r["OPERADOR_CACL2"]
            DATA_CACL2            = ToDate $r["DATA_CACL2"]
            CACL2_RENAULT         = ToBool $r["CACL2_RENAULT"]
            CACL2_PEUGEOT         = ToBool $r["CACL2_PEUGEOT"]
            CACL2_JAPAO           = ToBool $r["CACL2_JAPAO"]
            ID_CONJ_EQUIP_CACL2   = IdConj $r["EQUIP_CACL2"]
            ID_CONJ_EQUIP_NSS     = IdConj $r["EQUIP_NSS"]
            RESULTADO_TOTAL_CACL2 = Res3 $r["RESULT_TOTAL_CACL2_OK"] $r["RESULT_TOTAL_CACL2_OKRES"] $r["RESULT_TOTAL_CACL2_NOK"] "$refAnom total"
            RESULTADO_TOTAL_NSS   = $null
            OBSERVACOES           = ToStr $r["OBSERVACOES_CACL2"]
            ID_ACCESS_LEGADO      = ToInt $r["ID_CACL2"]
        })

        # CaCl2: 4 slots de amostra, 7 patamares. Peso e cavidade sao do slot;
        # o OK/NOK e de cada patamar.
        #
        # Como nas espessuras, um slot pode trazer duas amostras no mesmo
        # campo ("125.79g (51) / 126.79 (3)") - o formulario tinha 4 slots e
        # foi preciso mais. Aqui voltam a ser amostras separadas; os resultados
        # por patamar sao do slot, portanto aplicam-se as duas.
        $numAm = 0
        for ($n = 1; $n -le 4; $n++) {
            $pPeso = Partes $r["PESO${n}_CACL"]
            $pCav  = Partes $r["CAVIDADE${n}_CACL"]
            $leituras = @()
            for ($p = 0; $p -lt $patCacl.Count; $p++) {
                $pat = $patCacl[$p]
                $okCol = "PESO${n}_${pat}_OK"; $nokCol = "PESO${n}_${pat}_NOK"
                if (-not $r.Table.Columns.Contains($okCol)) { continue }
                $res = Res2 $r[$okCol] $r[$nokCol] "$refAnom CACL2 slot $n $pat"
                if ($null -eq $res) { continue }
                $leituras += @{ ORDEM = ($p + 1); RES = $res }
            }
            $k = @($pPeso.Count, $pCav.Count) | Measure-Object -Maximum | Select-Object -ExpandProperty Maximum
            if ($k -gt 1) { Anomalia "slot-com-varias-amostras" "$refAnom CACL2 slot $n -> $k amostras" }

            for ($i = 0; $i -lt $k; $i++) {
                $peso = ToDec (Parte $pPeso $i) "PESO_CACL"
                $cav  = Parte $pCav $i
                if ($null -eq $peso -and -not $cav -and $leituras.Count -eq 0) { continue }
                $numAm++
                $idAm = Ins "QUA_CR_MOV_CORROSAO_AMOSTRA" (Aud @{
                    ID_CORROSAO_CAB = $idCab; TIPO = "CACL2"; NUM_AMOSTRA = $numAm
                    CAVIDADE = $cav; PESO = $peso })
                foreach ($l in $leituras) {
                    $idPat = $null
                    $key = "CACL2|$($l.ORDEM)"
                    if ($mapPatamar.ContainsKey($key)) { $idPat = $mapPatamar[$key] }
                    [void](Ins "QUA_CR_MOV_CORROSAO_LEITURA" (Aud @{
                        ID_CORROSAO_AMOSTRA = $idAm; ID_PATAMAR = $idPat; RESULTADO = $l.RES }))
                }
            }
        }

        # NSS: 5 amostras, 4 patamares, e o resultado e uma classificacao 'CL'
        # em texto, nao um OK/NOK. So 7 dos 126 registos tinham dados NSS.
        for ($n = 1; $n -le 5; $n++) {
            $peso = ToDec $r["PESO${n}_NSS"] "PESO_NSS"
            $cav  = ToStr $r["CAVIDADE${n}_NSS"]
            $leituras = @()
            for ($p = 0; $p -lt $patNss.Count; $p++) {
                $pat = $patNss[$p]
                $clCol = "CL${n}_NSS_${pat}"; $obsCol = "CL${n}_NSS_OBS_${pat}"
                if (-not $r.Table.Columns.Contains($clCol)) { continue }
                $cl = ToStr $r[$clCol]
                $obs = $null
                if ($r.Table.Columns.Contains($obsCol)) { $obs = ToStr $r[$obsCol] }
                if (-not $cl -and -not $obs) { continue }
                $leituras += @{ ORDEM = ($p + 1); CL = $cl; OBS = $obs }
            }
            if ($null -eq $peso -and -not $cav -and $leituras.Count -eq 0) { continue }
            $idAm = Ins "QUA_CR_MOV_CORROSAO_AMOSTRA" (Aud @{
                ID_CORROSAO_CAB = $idCab; TIPO = "NSS"; NUM_AMOSTRA = $n
                CAVIDADE = $cav; PESO = $peso })
            foreach ($l in $leituras) {
                $idPat = $null
                $k = "NSS|$($l.ORDEM)"
                if ($mapPatamar.ContainsKey($k)) { $idPat = $mapPatamar[$k] }
                [void](Ins "QUA_CR_MOV_CORROSAO_LEITURA" (Aud @{
                    ID_CORROSAO_AMOSTRA = $idAm; ID_PATAMAR = $idPat
                    CLASSIFICACAO = $l.CL; OBSERVACOES = $l.OBS }))
            }
        }
    }
    Write-Host " ok"
}

# ============================================================
# 10. FOTOGRAFIAS
# ============================================================
# Unica parte que NAO pode usar ODBC: FOTOGRAFIA e um campo Attachment do
# Access (DAO Type=101). Corre em passagem separada de proposito - e a etapa
# longa (~250 MB a ler) e pode ser repetida sem reimportar o resto.

function RedimensionarJpeg([byte[]] $bytes, [int] $maxPx, [int] $qualidade) {
    if ($maxPx -le 0) { return $bytes }
    $ms = New-Object System.IO.MemoryStream($bytes, $false)
    try {
        $img = [System.Drawing.Image]::FromStream($ms)
    } catch { $ms.Dispose(); return $bytes }
    try {
        if ($img.Width -le $maxPx -and $img.Height -le $maxPx) { return $bytes }
        $escala = [Math]::Min($maxPx / $img.Width, $maxPx / $img.Height)
        $w = [int]($img.Width * $escala); $h = [int]($img.Height * $escala)
        $bmp = New-Object System.Drawing.Bitmap($w, $h)
        $g = [System.Drawing.Graphics]::FromImage($bmp)
        $g.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic
        $g.DrawImage($img, 0, 0, $w, $h)
        $g.Dispose()
        $enc = [System.Drawing.Imaging.ImageCodecInfo]::GetImageEncoders() |
               Where-Object { $_.MimeType -eq "image/jpeg" } | Select-Object -First 1
        $prm = New-Object System.Drawing.Imaging.EncoderParameters(1)
        $prm.Param[0] = New-Object System.Drawing.Imaging.EncoderParameter(
            [System.Drawing.Imaging.Encoder]::Quality, [int64]$qualidade)
        $out = New-Object System.IO.MemoryStream
        $bmp.Save($out, $enc, $prm)
        $bmp.Dispose()
        return $out.ToArray()
    } finally { $img.Dispose(); $ms.Dispose() }
}

if ($Fases -contains "fotografias") {
    Write-Host ""
    Write-Host "=== FOTOGRAFIAS ===" -ForegroundColor Yellow
    if ($mapReferencia.Count -eq 0 -and -not $DryRun) {
        Write-Host "  (sem mapa de referencias nesta passagem - corre com a fase 'referencias')" -ForegroundColor Yellow
    }

    $dao = New-Object -ComObject DAO.DBEngine.120
    $db = $dao.OpenDatabase($AccessFile, $false, $true)
    $rs = $db.OpenRecordset("SELECT ID_REFERENCIAS_CR, REFERENCIA, FOTOGRAFIA FROM [TABELA DE REFERENCIAS]", 2)

    $tmp = Join-Path $env:TEMP ("cr_foto_" + [guid]::NewGuid().ToString("N") + ".bin")
    $nFotos = 0; $bytesOrig = 0; $bytesFinal = 0

    while (-not $rs.EOF) {
        $idAcc = [int]$rs.Fields("ID_REFERENCIAS_CR").Value
        $child = $rs.Fields("FOTOGRAFIA").Value
        if ($null -ne $child) {
            while (-not $child.EOF) {
                $nomeOrig = [string]$child.Fields("FileName").Value
                if (Test-Path $tmp) { Remove-Item $tmp -Force }
                $child.Fields("FileData").SaveToFile($tmp)
                $raw = [System.IO.File]::ReadAllBytes($tmp)
                $bytesOrig += $raw.Length
                $final = RedimensionarJpeg $raw $FotoMaxPx $FotoQualidade
                $bytesFinal += $final.Length
                $nFotos++

                if (-not $DryRun -and $mapReferencia.ContainsKey($idAcc)) {
                    $b64 = "data:image/jpeg;base64," + [Convert]::ToBase64String($final)
                    $meio = [int][Math]::Ceiling($b64.Length / 2)
                    [void](Ins "QUA_CR_DIC_REFERENCIA_FICHEIROS" (Aud @{
                        ID_REFERENCIA = $mapReferencia[$idAcc]
                        NOME       = $nomeOrig
                        DESCRICAO  = "Importado do Access"
                        CATEGORIA  = "FOTOGRAFIA"
                        TIPO       = "img"
                        DATATYPE   = "image/jpeg"
                        TAMANHO    = [double]$final.Length
                        FICHEIRO_1 = $b64.Substring(0, $meio)
                        FICHEIRO_2 = $b64.Substring($meio)
                    }))
                } elseif ($DryRun) {
                    Conta "QUA_CR_DIC_REFERENCIA_FICHEIROS"
                }
                $child.MoveNext()
            }
            $child.Close()
        }
        $rs.MoveNext()
    }
    $rs.Close(); $db.Close()
    if (Test-Path $tmp) { Remove-Item $tmp -Force }

    $mbO = [Math]::Round($bytesOrig / 1MB, 1); $mbF = [Math]::Round($bytesFinal / 1MB, 1)
    Write-Host "  fotografias: $nFotos   originais: $mbO MB   apos redimensionar: $mbF MB"
    if ($FotoMaxPx -gt 0) {
        $b64mb = [Math]::Round(($bytesFinal * 4 / 3) * 2 / 1MB, 1)
        Write-Host "  ocupacao estimada na BD (base64 em NVARCHAR): $b64mb MB"
    }
}

# ============================================================
# 11. RELATORIO FINAL
# ============================================================

Write-Host ""
Write-Host "=== RESUMO ===" -ForegroundColor Green
$script:contagens.GetEnumerator() | Sort-Object Name | ForEach-Object {
    Write-Host ("  {0,-38} {1,7}" -f $_.Key, $_.Value)
}

if ($script:anomalias.Count -gt 0) {
    Write-Host ""
    Write-Host "=== ANOMALIAS ($($script:anomalias.Count)) ===" -ForegroundColor Yellow
    $script:anomalias | Group-Object Tipo | Sort-Object Count -Descending | ForEach-Object {
        Write-Host ("  {0,-34} {1,6}" -f $_.Name, $_.Count)
        $_.Group | Select-Object -First 5 | ForEach-Object { Write-Host "      $($_.Detalhe)" -ForegroundColor DarkGray }
        if ($_.Count -gt 5) { Write-Host "      ... e mais $($_.Count - 5)" -ForegroundColor DarkGray }
    }
    $csvAnom = Join-Path $pastaMapas "ANOMALIAS_CR_IMPORT.csv"
    $script:anomalias | Export-Csv $csvAnom -Delimiter ";" -NoTypeInformation -Encoding UTF8
    Write-Host ""
    Write-Host "  detalhe completo: $csvAnom"
} else {
    Write-Host ""
    Write-Host "Sem anomalias." -ForegroundColor Green
}

$acc.Close()
if ($sql) { $sql.Close() }
Write-Host ""
Write-Host $(if ($DryRun) { "Ensaio a seco terminado - nada foi escrito no SQL Server." } else { "Import terminado." }) -ForegroundColor Green
