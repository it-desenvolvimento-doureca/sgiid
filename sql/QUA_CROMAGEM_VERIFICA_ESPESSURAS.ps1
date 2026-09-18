# Verifica os valores importados contra o Access: espessuras, CaCl2, condicoes
# e pautas de ensaio - todos os blocos com parsing de risco.
#
# Existe por causa de um erro concreto: a primeira versao do import devolvia
# um array de um elemento sem a virgula de protecao (",@(...)"), o PowerShell
# desenrolava-o numa string, e o Parte() passava a indexar caracteres em vez de
# elementos. 'Minima' virou 'M', '67.39' virou '6', '0.15' virou '0'. Cerca de
# 3400 das 3691 linhas de espessura ficaram corrompidas e nada no import deu
# erro - os valores continuavam a ser numeros validos.
#
# A licao e que uma verificacao de formato nao apanha um erro de valor. Este
# script compara valores, e faz a analise do Access numa **segunda
# implementacao independente** da do import: se as duas concordarem no total,
# na soma, no minimo e no maximo de cada metal, o erro acima nao pode estar
# presente.
#
# Precisa de $env:SGIID_SQL_PASS quando nao usa autenticacao Windows.

# Este ficheiro fica em ASCII puro, como o script de import: o PowerShell 5.1
# le os .ps1 como Windows-1252, e o "I" acentuado de "FAMILIA" no caminho
# chegava corrompido ao driver ODBC. O caminho resolve-se por wildcard.
param(
    [string] $AccessFile = "",
    [string] $SqlServer  = "192.168.40.126,1433",
    [string] $SqlDb      = "SGIID",
    [string] $SqlUser    = "sa",
    [switch] $AutWindows
)

$ErrorActionPreference = "Stop"

if ($AccessFile -eq "") {
    $cand = @(Get-Item "$env:USERPROFILE\Downloads\FAM*5_2026_Tabelas.accdb" -ErrorAction SilentlyContinue)
    if ($cand.Count -eq 0) {
        Write-Host "Nao encontrei o .accdb em Downloads; passe -AccessFile." -ForegroundColor Red
        exit 1
    }
    $AccessFile = $cand[0].FullName
}

if (-not $AutWindows -and -not $env:SGIID_SQL_PASS) {
    Write-Host "Falta \$env:SGIID_SQL_PASS (ou use -AutWindows)." -ForegroundColor Red
    exit 1
}
$cs = if ($AutWindows) {
    "Server=$SqlServer;Database=$SqlDb;Integrated Security=True;Connect Timeout=30;"
} else {
    "Server=$SqlServer;Database=$SqlDb;User Id=$SqlUser;Password=$($env:SGIID_SQL_PASS);Connect Timeout=30;"
}

Write-Host "Access : $AccessFile"
Write-Host "SQL    : $SqlServer / $SqlDb"
Write-Host ""

# ---------------------------------------------------------------- analise Access

# Reimplementacao deliberadamente ingenua: sem funcoes partilhadas com o
# import, para que um erro no import nao se repita aqui.
function ValoresDoCampo([string] $texto) {
    if ($null -eq $texto) { return @() }
    $t = "$texto".Trim()
    if ($t -eq "") { return @() }
    $saida = New-Object System.Collections.ArrayList
    foreach ($pedaco in $t.Split("/")) {
        $p = $pedaco.Trim()
        if ($p -eq "") { continue }
        # sentinelas de "nao aplicavel"
        if ($p -match '^[-~.\s\u2013\u2014\u2212]*$') { continue }
        # numero no inicio, com virgula ou ponto decimal; ignora anotacoes
        if ($p -match '^\s*(\d*[.,]?\d+)') {
            [void]$saida.Add([decimal]($Matches[1].Replace(",", ".")))
        }
    }
    return $saida.ToArray()
}

$acc = New-Object System.Data.Odbc.OdbcConnection(
    "Driver={Microsoft Access Driver (*.mdb, *.accdb)};Dbq=$AccessFile;")
$acc.Open()
$cmd = $acc.CreateCommand()
$cmd.CommandText = "SELECT * FROM [T - TABELA SUBFORM ESPESSURAS]"
$da = New-Object System.Data.Odbc.OdbcDataAdapter($cmd)
$tab = New-Object System.Data.DataTable
[void]$da.Fill($tab)
$acc.Close()

$metais = @{ "Cu" = "ESP_CU"; "Ni" = "ESP_NI"; "Cr" = "ESP_CR" }
$espAcc = @{}
foreach ($m in $metais.Keys) { $espAcc[$m] = New-Object System.Collections.ArrayList }
$identUmChar = 0
$identTotal  = 0

foreach ($r in $tab.Rows) {
    for ($n = 1; $n -le 5; $n++) {
        foreach ($m in $metais.Keys) {
            $col = "$m ${n}a"
            if (-not $tab.Columns.Contains($col)) { continue }
            foreach ($v in (ValoresDoCampo $r[$col])) { [void]$espAcc[$m].Add($v) }
        }
        $colId = "Amostra ${n}a"
        if ($tab.Columns.Contains($colId) -and $r[$colId] -isnot [DBNull]) {
            foreach ($pedaco in "$($r[$colId])".Split("/")) {
                $p = $pedaco.Trim()
                if ($p -eq "") { continue }
                $identTotal++
                if ($p.Length -eq 1) { $identUmChar++ }
            }
        }
    }
}

# ------------------------------------------------------------------ leitura SQL

function Escalares([string] $sql) {
    $c = New-Object System.Data.SqlClient.SqlConnection($cs)
    $c.Open()
    $cm = $c.CreateCommand(); $cm.CommandText = $sql
    $rd = $cm.ExecuteReader()
    $out = @{}
    if ($rd.Read()) { for ($i = 0; $i -lt $rd.FieldCount; $i++) {
        $out[$rd.GetName($i)] = $(if ($rd.IsDBNull($i)) { $null } else { $rd.GetValue($i) }) } }
    $rd.Close(); $c.Close()
    return $out
}

Write-Host "--- Espessuras: Access vs SQL ---" -ForegroundColor Cyan
$falhas = 0
foreach ($m in @("Cu", "Ni", "Cr")) {
    $col = $metais[$m]
    $a = @($espAcc[$m])
    $aN = $a.Count
    $aS = if ($aN) { ($a | Measure-Object -Sum).Sum } else { 0 }
    $aMin = if ($aN) { ($a | Measure-Object -Minimum).Minimum } else { 0 }
    $aMax = if ($aN) { ($a | Measure-Object -Maximum).Maximum } else { 0 }

    $q = Escalares @"
SELECT COUNT($col) AS N, ISNULL(SUM($col),0) AS S,
       ISNULL(MIN($col),0) AS MN, ISNULL(MAX($col),0) AS MX
FROM QUA_CR_MOV_ESPESSURA WHERE ATIVO = 1
"@
    $ok = ($q.N -eq $aN) -and ([math]::Abs([decimal]$q.S - [decimal]$aS) -lt 0.01) -and
          ([math]::Abs([decimal]$q.MN - [decimal]$aMin) -lt 0.001) -and
          ([math]::Abs([decimal]$q.MX - [decimal]$aMax) -lt 0.001)
    if (-not $ok) { $falhas++ }
    $marca = if ($ok) { "OK " } else { "ERRO" }
    $cor   = if ($ok) { "Green" } else { "Red" }
    Write-Host ("  {0} {1}: n {2} vs {3} | soma {4:N2} vs {5:N2} | min {6} vs {7} | max {8} vs {9}" -f `
        $marca, $m, $aN, $q.N, $aS, $q.S, $aMin, $q.MN, $aMax, $q.MX) -ForegroundColor $cor
}


# --------------------------------------------------- CaCl2: a outra metade
#
# O Partes()/Parte() e usado em dois sitios no import: nas espessuras e nos
# pesos e cavidades do CaCl2. Verificar so as espessuras deixava metade do erro
# por provar.
#
# Este bloco tem de replicar a *difusao*: quando um slot traz um peso so e duas
# cavidades ("16.91" com "-/-"), o import gera duas amostras com o mesmo peso.
# A primeira versao deste verificador contava ocorrencias no campo em vez de
# amostras e acusava falsamente 7 pesos e 216 cavidades a mais. Contar o que o
# import conta e o que faz disto uma verificacao e nao um alarme.

$acc2 = New-Object System.Data.Odbc.OdbcConnection(
    "Driver={Microsoft Access Driver (*.mdb, *.accdb)};Dbq=$AccessFile;")
$acc2.Open()
$cmd2 = $acc2.CreateCommand()
$cmd2.CommandText = "SELECT * FROM [T - TABELA SUBFORM NSS E CACL2]"
$da2 = New-Object System.Data.Odbc.OdbcDataAdapter($cmd2)
$tab2 = New-Object System.Data.DataTable
[void]$da2.Fill($tab2)
$acc2.Close()

# A virgula do ",@(...)" e obrigatoria nos tres returns, e nao e estilo: sem
# ela o PowerShell desenrola um array de UM elemento numa string, e o
# PedacoNoIndice abaixo passa a indexar caracteres em vez de elementos -
# '13.12(126)' vira '1'. E exactamente o erro que este verificador existe para
# apanhar, e a primeira versao dele reproduziu-o: acusava o CaCl2 de ter somas
# erradas quando o errado era a leitura do Access.
function PedacosDoCampo($v) {
    if ($v -is [DBNull] -or $null -eq $v) { return ,@() }
    $s = "$v".Trim()
    if ($s -eq "") { return ,@() }
    if (-not $s.Contains("/")) { return ,@($s) }
    return ,@($s.Split("/") | ForEach-Object { $_.Trim() } | Where-Object { $_ -ne "" })
}

# A difusao: um valor unico serve todas as amostras do slot.
function PedacoNoIndice($arr, [int] $i) {
    # Cinto e suspensorios, como no import: se ainda assim vier uma string, e
    # um valor unico e nao uma sequencia de caracteres.
    if ($arr -is [string]) { return $arr }
    if ($null -eq $arr) { return $null }
    $a = @($arr)
    if ($a.Count -eq 0) { return $null }
    if ($a.Count -eq 1) { return $a[0] }
    if ($i -lt $a.Count) { return $a[$i] }
    return $null
}

function ValorUnico($s) {
    if ($null -eq $s) { return $null }
    if ("$s" -match '^[-~.\s\u2013\u2014\u2212]*$') { return $null }
    if ("$s" -match '^\s*(\d*[.,]?\d+)') {
        return [decimal]::Parse($Matches[1].Replace(",", "."),
                                [System.Globalization.CultureInfo]::InvariantCulture)
    }
    return $null
}

$PATAMARES_CACL = @("24H", "48H", "72H", "96H", "120H", "14D", "28D")
$pesosAcc = New-Object System.Collections.ArrayList
$cavAcc = 0
foreach ($r in $tab2.Rows) {
    for ($n = 1; $n -le 4; $n++) {
        $pp = PedacosDoCampo $r["PESO${n}_CACL"]
        $pc = PedacosDoCampo $r["CAVIDADE${n}_CACL"]

        # Um slot sem peso e sem cavidade ainda gera amostra se algum patamar
        # tiver leitura - e a mesma condicao do import.
        $temLeitura = $false
        foreach ($p in $PATAMARES_CACL) {
            $colOk = "PESO${n}_${p}_OK"; $colNok = "PESO${n}_${p}_NOK"
            if (-not $tab2.Columns.Contains($colOk)) { continue }
            $a = $(if ($r[$colOk] -is [DBNull]) { 0 } else { [int][bool]$r[$colOk] })
            $b = $(if ($r[$colNok] -is [DBNull]) { 0 } else { [int][bool]$r[$colNok] })
            if (($a + $b) -eq 1) { $temLeitura = $true }
        }

        $k = [Math]::Max(@($pp).Count, @($pc).Count)
        for ($i = 0; $i -lt $k; $i++) {
            $peso = ValorUnico (PedacoNoIndice $pp $i)
            $cav = PedacoNoIndice $pc $i
            if ($null -eq $peso -and [string]::IsNullOrWhiteSpace("$cav") -and -not $temLeitura) { continue }
            if ($null -ne $peso) { [void]$pesosAcc.Add($peso) }
            if (-not [string]::IsNullOrWhiteSpace("$cav")) { $cavAcc++ }
        }
    }
}

Write-Host ""
Write-Host "--- CaCl2 (mesma funcao Partes/Parte que as espessuras) ---" -ForegroundColor Cyan
$ap = @($pesosAcc)
$apN = $ap.Count
$apS = $(if ($apN) { ($ap | Measure-Object -Sum).Sum } else { 0 })
$apMin = $(if ($apN) { ($ap | Measure-Object -Minimum).Minimum } else { 0 })
$apMax = $(if ($apN) { ($ap | Measure-Object -Maximum).Maximum } else { 0 })
$qp = Escalares @"
SELECT COUNT(PESO) AS N, ISNULL(SUM(PESO),0) AS S,
       ISNULL(MIN(PESO),0) AS MN, ISNULL(MAX(PESO),0) AS MX
FROM QUA_CR_MOV_CORROSAO_AMOSTRA WHERE ATIVO = 1 AND TIPO = 'CACL2'
"@
$okp = ($qp.N -eq $apN) -and ([math]::Abs([decimal]$qp.S - [decimal]$apS) -lt 0.01) -and
       ([math]::Abs([decimal]$qp.MN - [decimal]$apMin) -lt 0.001) -and
       ([math]::Abs([decimal]$qp.MX - [decimal]$apMax) -lt 0.001)
if (-not $okp) { $falhas++ }
Write-Host ("  {0} Peso: n {1} vs {2} | soma {3:N2} vs {4:N2} | min {5} vs {6} | max {7} vs {8}" -f `
    $(if ($okp) { "OK " } else { "ERRO" }), $apN, $qp.N, $apS, $qp.S, $apMin, $qp.MN, $apMax, $qp.MX) `
    -ForegroundColor $(if ($okp) { "Green" } else { "Red" })

# As cavidades sao digitos unicos por natureza ("1", "2"), portanto contar as de
# um caractere nao diz nada aqui - ao contrario da identificacao da peca nas
# espessuras, onde 'Minima' truncado em 'M' era o sintoma. O que se compara e o
# numero de amostras com cavidade.
$qc = Escalares @"
SELECT COUNT(*) AS TOTAL FROM QUA_CR_MOV_CORROSAO_AMOSTRA
WHERE ATIVO = 1 AND TIPO = 'CACL2' AND CAVIDADE IS NOT NULL AND CAVIDADE <> ''
"@
$okc = ([int]$qc.TOTAL -eq $cavAcc)
if (-not $okc) { $falhas++ }
Write-Host ("  {0} Cavidades: n {1} vs {2}" -f `
    $(if ($okc) { "OK " } else { "ERRO" }), $cavAcc, $qc.TOTAL) `
    -ForegroundColor $(if ($okc) { "Green" } else { "Red" })

Write-Host ""
Write-Host ""
# ------------------------------------------------- Condicoes: CORRENTE e TEMPO
#
# Terceiro bloco com parsing de risco: o ToDec para a corrente e o ToTime para
# o tempo. Verificado a 2026-09-04 - as 4 divergencias que apareceram eram
# todas defeito deste verificador e nao do import:
#   ',175' e ',275'  - correntes sem o zero a esquerda, que a regex antiga
#                      ignorava por exigir digito no inicio (somavam os 0,45 A
#                      de diferenca na soma total);
#   TEMPO '9'        - numero isolado num campo h:m:s, que o import rejeita com
#                      razao porque o .NET o leria como 9 dias;
#   TEMPO en-dash    - sentinela que faltava no escape desta regex.

$acc3 = New-Object System.Data.Odbc.OdbcConnection(
    "Driver={Microsoft Access Driver (*.mdb, *.accdb)};Dbq=$AccessFile;")
$acc3.Open()
$cmd3 = $acc3.CreateCommand()
$cmd3.CommandText = "SELECT * FROM [T - TABELA SUBFORM CONDICOES]"
$da3 = New-Object System.Data.Odbc.OdbcDataAdapter($cmd3)
$tab3 = New-Object System.Data.DataTable
[void]$da3.Fill($tab3)
$acc3.Close()

$BANHOS = @("CU", "SB", "B", "SAT", "P", "CR", "PASS")
$corrAcc = New-Object System.Collections.ArrayList
$linhasAcc = 0
foreach ($r in $tab3.Rows) {
    foreach ($b in $BANHOS) {
        $cv = "$($r["CORR_$b"])"
        $tv = "$($r["TEMPO_$b"])"
        $corr = ValorUnico $cv
        # O tempo conta so para decidir se ha linha; um numero isolado nao e um
        # h:m:s valido, portanto nao conta - e o que o import faz.
        $temTempo = $false
        if (-not ($tv -match '^[-~.\s\u2013\u2014\u2212]*$') -and $tv.Trim() -ne "" -and $tv.Contains(":")) {
            $temTempo = $true
        }
        if ($null -eq $corr -and -not $temTempo) { continue }
        $linhasAcc++
        if ($null -ne $corr) { [void]$corrAcc.Add($corr) }
    }
}

Write-Host ""
Write-Host "--- Condicoes: corrente dos 7 banhos ---" -ForegroundColor Cyan
$ca = @($corrAcc)
$caN = $ca.Count
$caS = $(if ($caN) { ($ca | Measure-Object -Sum).Sum } else { 0 })
$caMin = $(if ($caN) { ($ca | Measure-Object -Minimum).Minimum } else { 0 })
$caMax = $(if ($caN) { ($ca | Measure-Object -Maximum).Maximum } else { 0 })
$qco = Escalares @"
SELECT COUNT(*) AS LINHAS, COUNT(CORRENTE) AS N, ISNULL(SUM(CORRENTE),0) AS S,
       ISNULL(MIN(CORRENTE),0) AS MN, ISNULL(MAX(CORRENTE),0) AS MX
FROM QUA_CR_MOV_CONDICOES WHERE ATIVO = 1
"@
$okl = ([int]$qco.LINHAS -eq $linhasAcc)
if (-not $okl) { $falhas++ }
Write-Host ("  {0} Linhas: n {1} vs {2}" -f $(if ($okl) { "OK " } else { "ERRO" }), $linhasAcc, $qco.LINHAS) `
    -ForegroundColor $(if ($okl) { "Green" } else { "Red" })
$okc2 = ($qco.N -eq $caN) -and ([math]::Abs([decimal]$qco.S - [decimal]$caS) -lt 0.01) -and
        ([math]::Abs([decimal]$qco.MN - [decimal]$caMin) -lt 0.001) -and
        ([math]::Abs([decimal]$qco.MX - [decimal]$caMax) -lt 0.001)
if (-not $okc2) { $falhas++ }
Write-Host ("  {0} Corrente: n {1} vs {2} | soma {3:N2} vs {4:N2} | min {5} vs {6} | max {7} vs {8}" -f `
    $(if ($okc2) { "OK " } else { "ERRO" }), $caN, $qco.N, $caS, $qco.S, $caMin, $qco.MN, $caMax, $qco.MX) `
    -ForegroundColor $(if ($okc2) { "Green" } else { "Red" })

Write-Host ""
# --------------------------------------------- Pautas de ensaio: peso por leitura
#
# O peso das leituras usa o ToDecAnot mas nao o Partes/Parte, portanto o risco
# e menor; a estrutura e que e trabalhosa - tres niveis, com o digito do teste
# repetido k+1 vezes no sufixo da coluna (Peso_11, Peso_111, Peso_1111...).
#
# Um valor malformado nao conta como falta: o import recusa-se a adivinhar
# ("21,23 869)" - um parentesis perdido) e guarda o texto original nas
# OBSERVACOES da leitura. Este bloco verifica que cada peso ausente tem mesmo o
# texto guardado, em vez de assumir uma tolerancia.

$acc4 = New-Object System.Data.Odbc.OdbcConnection(
    "Driver={Microsoft Access Driver (*.mdb, *.accdb)};Dbq=$AccessFile;")
$acc4.Open()
$cmd4 = $acc4.CreateCommand()
$cmd4.CommandText = "SELECT * FROM [T - TABELA SUBFORM PAUTAS ENSAIO]"
$da4 = New-Object System.Data.Odbc.OdbcDataAdapter($cmd4)
$tab4 = New-Object System.Data.DataTable
[void]$da4.Fill($tab4)
$acc4.Close()

function TextoOuNulo($v) {
    if ($v -is [DBNull] -or $null -eq $v) { return $null }
    $s = "$v".Trim()
    if ($s -eq "") { return $null }
    return $s
}

$testesAcc = 0
$leiturasAcc = 0
$comResAcc = 0
$pesosPauta = New-Object System.Collections.ArrayList
foreach ($r in $tab4.Rows) {
    for ($n = 1; $n -le 7; $n++) {
        $equipCol = "Equip_" + ("$n" * 2)
        if (-not $tab4.Columns.Contains($equipCol)) { continue }
        $ls = @()
        for ($k = 1; $k -le 4; $k++) {
            $suf = "$n" * ($k + 1)
            $resCol = "Result_${suf}_CL"
            if (-not $tab4.Columns.Contains($resCol)) { continue }
            $idRes = $null
            if ($r[$resCol] -isnot [DBNull]) {
                $tmp = 0
                if ([int]::TryParse("$($r[$resCol])", [ref]$tmp)) { $idRes = $tmp }
            }
            $obs = TextoOuNulo $r["${resCol}_OBS"]
            $cav = TextoOuNulo $r["Cavidade_$suf"]
            $peso = ValorUnico (TextoOuNulo $r["Peso_$suf"])
            if ($null -eq $idRes -and $null -eq $obs -and $null -eq $cav -and $null -eq $peso) { continue }
            $ls += @{ R = $idRes; P = $peso }
        }
        $temEquip = ($null -ne (TextoOuNulo $r[$equipCol]))
        if ($ls.Count -eq 0 -and -not $temEquip) { continue }
        $testesAcc++
        foreach ($l in $ls) {
            $leiturasAcc++
            if ($null -ne $l.R) { $comResAcc++ }
            if ($null -ne $l.P) { [void]$pesosPauta.Add($l.P) }
        }
    }
}

Write-Host ""
Write-Host "--- Pautas de ensaio ---" -ForegroundColor Cyan
$qpt = Escalares @"
SELECT
  (SELECT COUNT(*) FROM QUA_CR_MOV_ENSAIO_TESTE WHERE ATIVO=1) AS TESTES,
  (SELECT COUNT(*) FROM QUA_CR_MOV_ENSAIO_LEITURA WHERE ATIVO=1) AS LEITURAS,
  (SELECT COUNT(ID_RESULTADO) FROM QUA_CR_MOV_ENSAIO_LEITURA WHERE ATIVO=1) AS COM_RES,
  (SELECT COUNT(PESO) FROM QUA_CR_MOV_ENSAIO_LEITURA WHERE ATIVO=1) AS PESOS,
  (SELECT ISNULL(SUM(PESO),0) FROM QUA_CR_MOV_ENSAIO_LEITURA WHERE ATIVO=1) AS SOMA,
  (SELECT COUNT(*) FROM QUA_CR_MOV_ENSAIO_LEITURA
    WHERE ATIVO=1 AND PESO IS NULL AND OBSERVACOES LIKE '%Peso:%') AS PRESERVADOS
"@
foreach ($p in @(@("Testes", $testesAcc, [int]$qpt.TESTES),
                 @("Leituras", $leiturasAcc, [int]$qpt.LEITURAS),
                 @("Com resultado", $comResAcc, [int]$qpt.COM_RES))) {
    $ok = ($p[1] -eq $p[2])
    if (-not $ok) { $falhas++ }
    Write-Host ("  {0} {1}: n {2} vs {3}" -f $(if ($ok) { "OK " } else { "ERRO" }), $p[0], $p[1], $p[2]) `
        -ForegroundColor $(if ($ok) { "Green" } else { "Red" })
}

$pp2 = @($pesosPauta)
$pres = [int]$qpt.PRESERVADOS
$faltam = $pp2.Count - [int]$qpt.PESOS
# Cada peso a menos tem de ter o texto original nas OBSERVACOES; se tiver, nada
# se perdeu e a diferenca esta explicada.
$okp2 = ($faltam -eq 0) -or ($faltam -gt 0 -and $faltam -le $pres)
if (-not $okp2) { $falhas++ }
$nota = ""
if ($faltam -gt 0 -and $okp2) {
    $nota = "  ($faltam malformado(s), texto preservado nas OBSERVACOES de $pres leitura(s))"
}
Write-Host ("  {0} Pesos: n {1} vs {2} | soma {3:N2} vs {4:N2}{5}" -f `
    $(if ($okp2) { "OK " } else { "ERRO" }), $pp2.Count, $qpt.PESOS,
    ($pp2 | Measure-Object -Sum).Sum, $qpt.SOMA, $nota) `
    -ForegroundColor $(if ($okp2) { "Green" } else { "Red" })

Write-Host ""
Write-Host "--- Identificacao da peca (o sintoma do erro: 'Minima' -> 'M') ---" -ForegroundColor Cyan
$qi = Escalares @"
SELECT COUNT(*) AS TOTAL,
       SUM(CASE WHEN LEN(IDENT_AMOSTRA) = 1 THEN 1 ELSE 0 END) AS UM_CHAR
FROM QUA_CR_MOV_ESPESSURA
WHERE ATIVO = 1 AND IDENT_AMOSTRA IS NOT NULL AND IDENT_AMOSTRA <> ''
"@
Write-Host ("  Access: {0} identificacoes, {1} com 1 caracter" -f $identTotal, $identUmChar)
Write-Host ("  SQL   : {0} identificacoes, {1} com 1 caracter" -f $qi.TOTAL, $qi.UM_CHAR)
if ([int]$qi.UM_CHAR -gt $identUmChar) {
    Write-Host ("  ERRO: o SQL tem {0} identificacoes de 1 caracter a mais que o Access." -f `
        ([int]$qi.UM_CHAR - $identUmChar)) -ForegroundColor Red
    $falhas++
} else {
    Write-Host "  OK  sem truncagem a um caracter." -ForegroundColor Green
}

Write-Host ""
if ($falhas -eq 0) {
    Write-Host "VERIFICACAO PASSOU - espessuras, CaCl2, condicoes e pautas batem com o Access." -ForegroundColor Green
    exit 0
}
Write-Host "VERIFICACAO FALHOU em $falhas ponto(s)." -ForegroundColor Red
exit 1