# PLANO — Módulo Qualidade › Laboratório Cromagem (`QUA_CR_*`)

**Data:** 2026-09-02 (implementação em curso — ver §11 para o estado)
**Origem:** `FAMÍLIA 5_2026_Tabelas.accdb` (dados) + `FAMÍLIA 5_2026 - Carla.accdb` (forms/relatórios/consultas), ambos em `C:\Users\it2\Downloads\`
**Módulo:** SGIID → Qualidade → Laboratório Cromagem
**Decisões já tomadas:** migrar só 2026 · modelo normalizado · resultado em coluna única ·
tabelas novas (não estender as de Meios de Controlo) · nº de relatório automático ·
anexos em base64 redimensionados · patamares de corrosão como dados (ver §10)

---

## 0. Contexto

A "FAMÍLIA 5" é a base de dados Access do laboratório de qualidade da cromagem. Cada
registo é um boletim de controlo (`N_RELAT_CR` = `1086/2026`) com as condições dos banhos,
medições de espessura Cu/Ni/Cr, pautas de ensaio e ensaios de corrosão (CaCl₂ e NSS).
Está em produção diária — o último registo é de hoje, 02-09-2026, às 10:54.

Três razões para migrar, todas verificadas nos dados:

1. **O Access não chega para o que a qualidade já exige.** A ficha técnica pede
   `N_DE AMOSTRAS` = 6 em 1103 das 1423 referências, 8 em 158, 10 em 109 e até 15 em 9 —
   mas o formulário só tem **5** colunas de amostra. Quem controla uma referência de 10
   amostras não tem onde registar metade.
2. **Um ficheiro por ano, ligado a um `.accdb` numa pasta de rede** (`P:\Qualidade\3_BASES
   DE DADOS ACCESS\...\FAMÍLIA 5\2026\`), sem histórico consolidado, sem permissões e sem
   integração com as referências do SILVER nem com os meios de controlo do SGIID.
3. **A lógica de negócio vive dentro de consultas Access.** Não há praticamente VBA: os
   resultados estão guardados em trios de booleanos que 5 consultas achatam com cadeias de
   `IIf()` de centenas de caracteres. Cada relatório reimplementa a mesma regra.

Resultado pretendido: o registo e a impressão passam para o SGIID, com os 1086 relatórios
e 1423 referências de 2026 migrados, sem perder o boletim ID127_06 tal como o cliente o
conhece.

---

## 1. O que existe no Access (levantamento)

### 1.1 Tabelas (`FAMÍLIA 5_2026_Tabelas.accdb`)

| Tabela | Regs | Cols | Papel |
|---|---|---|---|
| `TABELA DE REFERENCIAS` | 1423 | 57 | Ficha técnica por referência (o mestre) |
| `T - TABELA RESULTADOS CROMAGEM` | 1086 | 14 | Cabeçalho do boletim |
| `T - TABELA SUBFORM CONDICOES` | 970 | 19 | Corrente + tempo dos 7 banhos |
| `T - TABELA SUBFORM ESPESSURAS` | 1063 | 70 | 5 amostras × Cu/Ni/Cr + poros/fissuras/step |
| `T - TABELA SUBFORM PAUTAS ENSAIO` | 501 | 127 | 7 testes × 4 leituras |
| `T - TABELA SUBFORM NSS E CACL2` | 126 | 138 | CaCl₂ 7 patamares × 4 pesos; NSS 4 × 5 CLs |
| `TABELA EQUIPAMENTOS` | 47 | 2 | Equipamentos com código `EQ*` |
| `TABELA CONTROLADORES` | 4 | 2 | Técnicas de laboratório |
| `T - TABELA RESULTADOS CR APOIO` | 22 | 2 | `OK` / `NÃO OK` / `CL5..CL10` ± ciclos |

FK única em todas as filhas: `ID_RESULTCOULOSC` → cabeçalho. Integridade limpa
(0 relatórios órfãos de referência).

### 1.2 Forms e relatórios (`FAMÍLIA 5_2026 - Carla.accdb`)

Frontend de tabelas ligadas por rede, **sem um único módulo VBA**. Painel `PAINEL CROMAGEM`
com dois grupos: *Inserir Dados* (abre `F - FORM RESULTADOS CROMAGEM` + 4 subforms) e
*Relatórios*:

| Relatório Access | Consulta-fonte | Parâmetros | Destino no SGIID |
|---|---|---|---|
| `ID127_06_PAG1/2/3` | `Consulta_ID127_06_PAG1/2/3` | `ID` do relatório | Boletim Jasper `cr_boletim_ensaio` — 3 impressões independentes (§2.1) |
| `SEG PÇS CROMADAS` (+ `-1teste`, `-4testes`, `-5testes`) | `Consulta_Seguimentos peças cromadas` | referência | Ecrã "Seguimento por referência" |
| `EVOLUCAO RESULTADOS` | `Consulta_EVOLUCAO_RESULTADOS` | intervalo de datas | Ecrã "Análises" |
| `EVOLUCAO ENSAIOS POR REF` | `Consulta_ID_EVOLUCAO ENSAIOS` | datas + `Local="Doureca"` | Ecrã "Análises" |

As 4 variantes de "SEG PÇS CROMADAS" são o **mesmo** relatório com 1/2-3/4/5 blocos de
teste visíveis — no SGIID é um só relatório com as secções condicionais.

### 1.3 O snapshot que tenho está atrasado face ao frontend

As consultas da Carla referem `TABELA LOCAL DE PRODUCAO` e a coluna
`RESULTADOS CROMAGEM.[Local de Producao]`, que **não existem** no `.accdb` de tabelas que
recebi (confirmado: ambas as queries dão "table/field missing"). Os `Tipo` conhecidos pelas
consultas são 6 (`E`, `P`, `P.N.`, `A`, `A.I.`, `P.S.`) e o snapshot só tem 4 em uso.
→ **Antes de migrar é preciso um export fresco de `P:\Qualidade\...\FAMÍLIA 5\2026\`** (o P:
está-me inacessível). O modelo abaixo já contempla os dois campos.

---

## 2. Achados que moldam o modelo

Tudo medido sobre os dados reais, não inferido:

1. **As filhas não são 1:1.** 6 relatórios têm mais de uma linha de `CONDICOES`, 2 de
   `ESPESSURAS` e 2 de `PAUTAS ENSAIO`. O formulário Access mostra só a primeira — há
   registos invisíveis na aplicação atual. O modelo tem de ser **1:N** e o ecrã tem de os
   mostrar.
2. **Estados contraditórios são raros; estados vazios são comuns.** Nos trios de booleanos:
   0 linhas com mais de uma flag ativa em espessuras e testes, **1** em CaCl₂. Mas **287**
   linhas de espessuras, 14 de testes e 9 de CaCl₂ têm as três flags a `False` — ou seja,
   *sem resultado atribuído*. A coluna única tem de aceitar `NULL` (= em curso / não
   avaliado); não é um domínio de 3 valores obrigatórios.
3. **As amostras esgotam-se de forma decrescente**, não em bloco: 771 registos com Cr na
   amostra 1, 740 na 2, 629 na 3, 526 na 4, 243 na 5. Confirma linhas por amostra em vez de
   colunas fixas.
4. **Os acabamentos são mutuamente exclusivos.** Os 9 booleanos (`SAT F`, `SAT I`, `BR HEX`,
   `BR TRI`, `GR BR`, `GR SAT I`, `GR SAT F`, `FUM`, `SM5077`) são sempre testados nas
   consultas como "um `=Yes` e todos os outros `<>Yes`". → uma FK, não 9 bits. O mesmo para
   `Barra`/`Revisao`/`Esniagem`/`Pintura` e para `LINHA 1`/`LINHA 2`.
5. **A referência do Access não é chave limpa para o SILVER.** Das 1423: 964 com prefixo
   `PC`, 70 com `/` (referências compostas, ex. `PC78663690/PC78663517`), 87 com espaços,
   13 duplicadas, 2 vazias. Só 327 são efetivamente usadas em relatórios.
   → A ligação ao SILVER é **opcional e por campo próprio**, não FK obrigatória (§3.2).
6. **Correntes e tempos são texto com sentinela.** `CORR_*` vem como `1,5` / `10,25`
   (decimal com vírgula) e `TEMPO_*` como `00:45:06`. **703 das 970** linhas de condições
   usam `-` em `CORR_SAT`/`TEMPO_SAT` para "não aplicável" — o banho SAT só se usa em parte
   das referências. Na migração: `-` e `''` → `NULL`, vírgula → ponto, `CORR_*` →
   `DECIMAL(8,3)`, `TEMPO_*` → `TIME(0)`.
7. **O bloco NSS está praticamente vazio e as etiquetas não batem com as colunas.**
   Só **7 dos 126** registos têm dados NSS, e apenas no primeiro patamar (`CL1_NSS_200H`=7,
   os de 500H/800H/1000H a zero). Pior: o formulário rotula os patamares **250 / 500 / 750 /
   1000 horas**, mas as colunas chamam-se `DIA200HORAS_NSS`, `DIA500HORAS_NSS`,
   `DIA800HORAS_NSS`, `DIA1000HORAS_NSS`. Alguém mudou as etiquetas sem renomear as colunas
   (ou o inverso). Comparação: o CaCl₂ tem 119 registos com dados. Ver §10.8.
8. **As colunas de data dos patamares CaCl₂ estão todas vazias.** `DIA24HORAS`, `DIA48HORAS`,
   … `DIA28DIAS` têm **0** registos preenchidos em 126 — só se guarda o OK/NÃO OK por peso e
   patamar, nunca a data em que cada patamar foi lido. Não migrar essas 7 colunas.
9. **`TABELA EQUIPAMENTOS` não é uma lista de equipamentos — é uma lista de _combinações_.**
   Das 47 linhas, **24 são combinações** (`"Estufa Altas Temperaturas (EQ39) + Estufa Fria
   (EQ40) + Q-FOG (EQ622)"`) e existem apenas **25 equipamentos `EQ*` distintos**:
   EQ4, EQ10, EQ12, EQ15, EQ24, EQ25, EQ39, EQ40, EQ56, EQ65, EQ92, EQ106, EQ129, EQ130,
   EQ220, EQ238, EQ252, EQ266, EQ285, EQ286, EQ512, EQ567, EQ570, EQ594, EQ622.
   → A relação medição↔equipamento é **N:N**, não uma FK simples: cada campo `Equip_*` do
   Access aponta para um *conjunto* de equipamentos. Modela-se com uma tabela de ligação
   (§3.1), não com `ID_EQUIPAMENTO` na linha de medição.

### 2.1 O formulário atual (dos screenshots do cliente)

`F - FORM RESULTADOS CROMAGEM`, título **"ENSAIOS - PEÇAS CROMADAS"**. Confirma o desenho
cabeçalho + tabs e acrescenta detalhes que as tabelas não revelavam:

**Cabeçalho — grupo "DADOS DO PRODUTO"**
`RELATÓRIO Nº` (`1/2026`) · `TIPO ENSAIO` com a legenda dos 6 tipos impressa ao lado
(`E`-Ensaios, `P`-Protótipos, `A`-Amostras, `A.I.`-Amostras Iniciais, `P.S.`-Pré-Série,
`P.N.`-Produção) · `REFERÊNCIA PEÇA` (dropdown) · `DATA ENT. LAB.` · `DATA/HORA CROMAG.` ·
`LOTE` · `DATA RECEP. DOURECA` · checkboxes `LINHA 1`/`LINHA 2` · designação da peça em
destaque (`MONOGRAMME CAPTUR HJB`) · **a fotografia da referência aparece no cabeçalho** —
ou seja a imagem é consumida no ecrã de ensaio, não só na ficha da referência.

**"TESTES PREVISTOS"** — checkboxes `ESPESSURAS` / `PAUTAS` / `NSS/CACL2` que ativam as
tabs, ao lado de **três botões separados `PAG. 1` / `PAG. 2` / `PAG. 3`**. Não é um
relatório de 3 páginas com um botão: são três impressões independentes, e o botão está
desativado quando o teste respetivo não está previsto. → no SGIID, `cr_boletim_ensaio` com
parâmetro de página e três botões com o mesmo `[disabled]`.

**Tab 1 · ESPESSURAS/POROS/FISSURAS** — mostra `PAUTA C27` e as espessuras exigidas
`Cr 0,15-0,3` / `Ni 14` / `Cu 25` vindas da ficha técnica (read-only). Amostras
**identificadas por letra `A`, `B`, `C`…**, não por número, cada uma com um campo de texto
livre (`Peça Nº155`) + `Peso (g)` + `Cavidade` + `Cr`/`Ni`/`Cu` + os 3 checkboxes
`OK` / `NÃO OK RES.` / `NÃO OK`. → `NUM_AMOSTRA` inteiro no modelo, letra só na
apresentação; o `Peça Nº` é o `Amostra Na` do Access, guardar como `IDENT_AMOSTRA`.

**Tab 2 · PAUTAS DE ENSAIO** — `PAUTA ENSAIO C02`; por bloco, o **nome do teste vindo da
ficha técnica** (`Incisão 2letrs;CASS-T`), um dropdown `EQUIP. UTILIZ` (o conjunto de
equipamento, §3.5), `PESO (g)`, `CAVIDADE` e **4 dropdowns `RESULTADO`** por bloco.

**Tab 3 · NSS/CACL2** — dois grupos. NSS: `Equipamento` + grelha 5 linhas × 4 colunas
rotuladas **250 / 500 / 750 / 1000 HORAS** (≠ nomes das colunas, §2.7), cada célula `CL`.
CaCl₂: `TIPO DE CACL2` com os 3 checkboxes `CACL2 RENAULT`/`PEUGEOT`/`JAPÃO`, `Equipamento`,
`PESO (g)`/`CAVIDADE` por linha e colunas **24 / 48 / 72 / 96 / 120 HORAS / 14 DIAS /
28 DIAS**, cada célula com `OK` / `NÃO OK`.

**Tab 4 · CONDICOES** — "CONDIÇÕES DA **LMEP**" (o nome da linha de cromagem):
`Nº PEÇAS` (312), `SUPERFÍCIE` (2,02), e a matriz 7 banhos
(`Cu`, `SB`, `B`, `SAT`, `P`, `Cr`, `Pass`) × 2 linhas (`CORRENTE (A)`, `TEMPO (h:m:s)`),
com `-` nas células não aplicáveis (§2.6). `OBSERVAÇÕES` em texto longo.

**Ordem das tabs no ecrã:** Espessuras → Pautas → NSS/CaCl₂ → Condições. Manter esta ordem
(o plano tinha Condições em primeiro).

---

## 3. Tabelas necessárias

### 3.1 Novas — `QUA_CR_*` (20 tabelas: 10 dicionários, 9 movimentos, 1 de ficheiros)

Convenção nova de auditoria, a mesma do `QUA_MC_*`/`QUA_EPI_*`:
`UTZ_CRIA/DATA_CRIA`, `UTZ_MODIF/DATA_MODIF`, `UTZ_ANULA/DATA_ANULA`, `ATIVO BIT DEFAULT 1`
(**`ATIVO`, não `INATIVO`** — a convenção antiga do `QUA_DERROGACOES` não se usa em código novo).

**Dicionários**

| Tabela | Origem Access | Notas |
|---|---|---|
| `QUA_CR_DIC_REFERENCIA` | `TABELA DE REFERENCIAS` | Ficha técnica. `REFERENCIA` texto livre + `PROREF_SILVER` opcional (§3.2). Os 9 bits de acabamento → `ID_ACABAMENTO`; `Barra/Revisao/Esniagem/Pintura` → `ID_TIPO_SUPERFICIE`; `NAO AUTOMOVEL`/`AUTOMOVEL INTERIOR`/`AUTOMOVEL EXTERIOR` → `ID_TIPO_PECA` + `NORMA_CLIENTE` |
| `QUA_CR_DIC_REF_TESTE` | `TESTE 1..7` / `TESTE 11..77` | Os 7 pares nome+procedimento viram N linhas: `ID_REFERENCIA`, `ORDEM`, `DESIGNACAO`, `PROCEDIMENTO`. Remove o teto de 7 |
| `QUA_CR_DIC_ACABAMENTO` | 9 booleanos | `SAT F`, `SAT I`, `BR HEX`, `BR TRI`, `GR BR`, `GR SAT I`, `GR SAT F`, `FUM`, `SM5077` |
| `QUA_CR_DIC_TIPO_PECA` | `TIPO_PECA` (IIf) | Não automóvel / Automóvel interior / Automóvel exterior |
| `QUA_CR_DIC_TIPO_ENSAIO` | `Tipo` (IIf) | `P.N.`→Produção, `E`→Ensaios, `A`→Amostras, `A.I.`→Amostras iniciais, `P.S.`→Pré-séries, `P`→Protótipos |
| `QUA_CR_DIC_RESULTADO` | `T - TABELA RESULTADOS CR APOIO` | Os 22 valores `CL5..CL10` ± ciclos |
| `QUA_CR_DIC_LOCAL_PRODUCAO` | `TABELA LOCAL DE PRODUCAO` | **Conteúdo em falta** — depende do export fresco (§1.3) |
| `QUA_CR_DIC_CONJ_EQUIP` | `TABELA EQUIPAMENTOS` | Conjuntos de equipamento por ensaio — 47 linhas, das quais 24 são combinações (§2.9) |
| `QUA_CR_DIC_CONJ_EQUIP_LIN` | idem (parse do `EQ*`) | N:N para `QUA_MC_EQUIPAMENTOS` — 25 equipamentos reais |
| `QUA_CR_DIC_PATAMAR` | nomes das colunas `DIA*` | Patamares de corrosão como **dados, não como colunas** (§3.6) |

**Movimentos**

| Tabela | Origem | Granularidade |
|---|---|---|
| `QUA_CR_MOV_RELATORIO` | `RESULTADOS CROMAGEM` | 1 linha por boletim. `ID_LINHA` (FK `AB_DIC_LINHA`) em vez de `LINHA 1`/`LINHA 2`; `ID_TIPO_ENSAIO`, `ID_LOCAL_PRODUCAO`, `N_RELAT_CR`, `LOTE`, datas, flags de âmbito |
| `QUA_CR_MOV_CONDICOES` | `SUBFORM CONDICOES` | **1 linha por banho** (`BANHO` ∈ CU/SB/B/SAT/P/CR/PASS, `CORRENTE`, `TEMPO`) + cabeçalho `NUM_PECAS`/`SUPERFICIE`/`OBS` em `QUA_CR_MOV_CONDICOES_CAB` |
| `QUA_CR_MOV_ESPESSURA` | `SUBFORM ESPESSURAS` (5 amostras) | **1 linha por amostra**: `NUM_AMOSTRA`, `CAVIDADE`, `PESO`, `ESP_CU`, `ESP_NI`, `ESP_CR`, `RESULTADO` |
| `QUA_CR_MOV_ESPESSURA_CAB` | idem (bloco comum) | 1:1 com o relatório: poros, fissuras, step (6 medições), 3 equipamentos, operador, data, `RESULTADO_TOTAL` |
| `QUA_CR_MOV_ENSAIO` | `SUBFORM PAUTAS ENSAIO` | **1 linha por teste × leitura**: `NUM_TESTE`, `NUM_LEITURA`, `ID_RESULTADO`, `OBS`, `CAVIDADE`, `PESO`, `ID_EQUIPAMENTO` |
| `QUA_CR_MOV_ENSAIO_CAB` | idem | 1:1: `DATA_TESTES`, `OPERADOR`, `OBSERVACOES`, `RESULTADO_TOTAL` |
| `QUA_CR_MOV_CORROSAO` | `SUBFORM NSS E CACL2` | **1 linha por amostra × patamar**, com `TIPO` ∈ `CACL2`\|`NSS`: `NUM_AMOSTRA`, `PATAMAR` (24H..28D / 200H..1000H), `CAVIDADE`, `PESO`, `RESULTADO`, `OBS` |
| `QUA_CR_MOV_CORROSAO_CAB` | idem | 1:1: normas (`CACL2_RENAULT`/`_PEUGEOT`/`_JAPAO`), equipamentos, operador, datas dos patamares, `RESULTADO_TOTAL_CACL2`, `RESULTADO_TOTAL_NSS` |

As colunas `RESULTADO*` são `NVARCHAR(10) NULL` com domínio `OK` / `OK_COND` / `NOK`,
substituindo os trios `_OK`/`_OKRES`/`_NOK`. `NULL` = ainda não avaliado (§2.2).

### 3.2 Reutilizadas — **nada a criar**

| Necessidade | Fonte | Como se lê |
|---|---|---|
| **Referência de peça** | SILVER `SDTPRA` | `GET /rest/sirb/getReferencias` (`PROREF`, `PRODES1`, `FAMCOD`) e `getReferenciaSearch/{q}`, já consumidos por [ab-dic-componente.service.ts](src/app/servicos/ab-dic-componente.service.ts#L90-L96) |
| **Cliente** | SILVER `SDTCLI`+`SDTCLE` | `GET /rest/sirb/getClientes` (`CLICOD`, `ADRNOM`), mesmo serviço |
| **Linhas de cromagem** | `AB_DIC_LINHA` | [ab-dic-linha.service.ts](src/app/servicos/ab-dic-linha.service.ts) — substitui `LINHA 1`/`LINHA 2` |
| **Controladores / operadores** | `GER_UTILIZADORES` | [ger-utilizadores.service.ts](src/app/servicos/ger-utilizadores.service.ts), `getAllOrder()`. As 4 controladoras passam a utilizadoras SGIID; `OPERADOR_ESPESSURAS`/`_PAUTAS`/`_CACL2` viram FK ao utilizador (só 3 IDs distintos em uso nos dados) |
| **Equipamentos (`EQ*`)** | `QUA_MC_EQUIPAMENTOS` | [qua-mc-equipamentos.service.ts](src/app/servicos/qua-mc-equipamentos.service.ts). Os **25 equipamentos reais** mapeiam por `COD_INTERNO`; as combinações ficam em dicionário local (§3.5) |
| **Permissões** | `GER_PERFIL_LIN` | Nada estrutural: o catálogo de ecrãs é código (§5.1) |

> **A verificar antes de criar `QUA_CR_DIC_REFERENCIA`:** já existe
> `QUA_MC_DIC_PECAS_CROMADAS` (`REFERENCIA` + `DESIGNACAO`, ecrã em
> `meios-controlo/parametrizacao/pecas-cromadas/`). É um dicionário de peças cromadas com
> propósito sobreposto. Decidir se se estende essa ou se fica separada — ver §8.

### 3.3 Ligação ao SILVER

`QUA_CR_DIC_REFERENCIA` guarda:
- `REFERENCIA NVARCHAR(50)` — o código do laboratório, tal como hoje (texto livre, aceita
  os 70 casos compostos com `/`);
- `PROREF_SILVER NVARCHAR(18) NULL` — preenchido por um picker sobre `getReferenciaSearch`.

Não há FK. Na migração tenta-se casar `REFERENCIA` (e a variante sem o prefixo `PC`) contra
`SDTPRA.PROREF`; o que não casar fica a `NULL` e é resolvido à mão no ecrã. O ecrã mostra a
descrição do SILVER quando `PROREF_SILVER` está preenchido, e cai para a `DESIGNACAO` local
quando não está.

### 3.4 O que vem do SILVER e o que é preenchido pela qualidade

Só 3 das 57 colunas existem no SILVER. **As restantes são necessárias** — são a
especificação contra a qual o laboratório decide OK/NOK, e não existem em nenhum outro
sistema. Taxas de preenchimento medidas sobre as **1346 referências ativas**:

**Lidas do SILVER** (`SDTPRA` / `SDTCLI`+`SDTCLE`) — read-only no ecrã:

| Coluna Access | Fonte SILVER |
|---|---|
| `REFERENCIA` (100%) | `SDTPRA.PROREF` via `PROREF_SILVER` |
| `REFERENCIA PECA PLASTICA` (98%) | `SDTPRA.PROREF` da peça em bruto (2.ª ligação) |
| `CLIENTE` (100%) | `SDTCLE.ADRNOM` |

**Preenchidas pela qualidade** — o form novo, agrupado como no boletim:

| Grupo | Campos (com % de preenchimento) |
|---|---|
| Identificação | `DESIGNACAO` 100%, `MATERIAL` 98%, `CAVIDADES` 66%, `FICHA TECNICA` 90%, `CARTELA` 29%, `N_DE AMOSTRAS` 100% |
| Classificação | `ID_TIPO_PECA` (não-automóvel 1% / int. 20% / ext. 78%), `ID_TIPO_SUPERFICIE` (Revisão 47%, Esniagem 23%, Barra 16%, Pintura 11%), `ID_ACABAMENTO` (BR TRI 30%, BR HEX 23%, SAT I 21%, SAT F 15%, FUM 8%, GR BR/GR SAT I/GR SAT F/SM5077 ≤1%) |
| Espessuras exigidas | `ESPESSURA Cu`/`Ni`/`Cr` 100% |
| Estrutura de camada | `MICROPOROSO` 99%, `MICROFISSURADO` 0.3%, `N_EXIG_POROS` 97%, `N_EXIG_FISSURAS` 0.4% |
| Pautas | `PAUTA_ESPESSURAS` 100%, `PAUTA ENSAIO` 100%, `PAUTA_STEP` 99%, `PAUTA_POROS` 99%, `PAUTA_CACL2` 51% |
| Corrosão | `TESTE_CACL2` 49% + `PROC_CACL2` 48% |
| Testes (→ `QUA_CR_DIC_REF_TESTE`) | teste 1 100%, 2 53%, 3 46%, 4 11%, 5 8%, 6 0.4%, 7 0% |

**Colunas mortas — não migrar** (0 registos ativos preenchidos): `PAUTA_NSS`, `PROC_NSS`,
`TESTE 7`/`TESTE 77`. Quase-mortas, a confirmar antes de descartar: `TESTE_NSS` (5),
`N_EXIG_FISSURAS` (5), `TESTE 6` (6), `MICROFISSURADO` (4), `SM5077` (2), `GR SAT F` (5).
O bloco NSS existe no formulário de registo mas **nenhuma referência ativa tem pauta NSS
definida** — vale a pena perguntar se o ensaio NSS ainda se faz.

**`FOTOGRAFIA` é um campo Attachment do Access** (DAO `Type=101`), não texto. O ODBC não o
lê — expõe apenas um handle interno de 1-4 dígitos, um por linha, o que faz o campo parecer
preenchido a 100% quando não está. Números reais, medidos por DAO:

| | |
|---|---|
| Referências com fotografia | **833** de 1423 (590 sem) |
| Volume total de imagens | **249,9 MB** — praticamente todo o `.accdb` de 270 MB |
| Maior imagem / média | 4,4 MB / ~300 KB |
| Formatos | 832 `.jpg`, 1 `.png` |

`FICHA TECNICA` (ex. `CRO-1024`, 90%) e `CARTELA` (29%) são **códigos** em texto no Access.
Por decisão (§10) passam a **anexo**, na mesma tabela de ficheiros das fotografias, com o
código a servir de `DESCRICAO` — ver §10.2 quanto à origem dos PDFs.

**Armazenamento no SGIID: base64 na BD**, que é a convenção documentada do sistema — data
URL partido em `FICHEIRO_1` + `FICHEIRO_2`, ambos `NVARCHAR(MAX)`, com listagem só de
metadados e um endpoint `...content/{id}` que faz `CONCAT` em lazy load. Ver
[QUA_MC_EQUIPAMENTOS_FICHEIROSDao.java](file:///C:/work/Workspace/sgiid/src/main/java/pt/example/dao/QUA_MC_EQUIPAMENTOS_FICHEIROSDao.java)
e o comentário explícito em `sql\AT_MELHORIAS_ACIDENTES_2026-08-12.sql:53-57`.

→ Tabela `QUA_CR_DIC_REFERENCIA_FICHEIROS` com a estrutura exata de
`QUA_MC_EQUIPAMENTOS_FICHEIROS` (`NOME`, `CAMINHO`, `TIPO`, `DATATYPE`, `TAMANHO`,
`DESCRICAO`, `FICHEIRO_1`, `FICHEIRO_2` + auditoria). Uma tabela filha, não uma coluna na
referência — assim a listagem de referências nunca arrasta os blobs.

**Dimensionamento (decidido):** 249,9 MB → base64 são ~333 M caracteres, e `NVARCHAR(MAX)`
guarda 2 bytes por carácter → seriam **~666 MB na base de dados**. São fotografias de
máquina a resolução total usadas como ilustração no cabeçalho do ensaio, pelo que se
**redimensionam na importação** para lado maior 1200 px / JPEG 80 → **~15-25 MB**. As
originais ficam no `.accdb` de arquivo, portanto não se perde nada.

**Exclusividade confirmada nos dados**, o que valida as FKs em vez dos bits:
acabamento — 1337 refs com exatamente 1 flag, 6 com nenhuma, **3 com mais de uma**;
superfície — 1309 com 1, 36 com nenhuma, **1 com mais de uma**. As ~46 exceções resolvem-se
à mão na migração.

### 3.5 Equipamentos — mesmo padrão

Igual às referências, e pela mesma razão do §2.6: o que o Access chama "equipamentos" são
**combinações** de equipamento por tipo de ensaio, não equipamentos.

- Os **25 equipamentos `EQ*` reais** leem-se de `QUA_MC_EQUIPAMENTOS` (Meios de Controlo),
  casando por `COD_INTERNO`. Nada a criar — **a confirmar com o cliente se os 25 já lá
  estão todos** (§10.7).
- As **combinações** ficam num dicionário local `QUA_CR_DIC_CONJ_EQUIP` +
  `QUA_CR_DIC_CONJ_EQUIP_LIN` (N:N para `QUA_MC_EQUIPAMENTOS`), com um form próprio onde a
  qualidade escolhe os equipamentos do conjunto a partir dos Meios de Controlo.
  As linhas de medição referenciam o conjunto, não o equipamento.

Se algum dos 25 não existir em Meios de Controlo, a decisão é criá-lo lá (é o registo
metrológico correto) e não duplicar no módulo novo.

---

### 3.6 Patamares de corrosão como dados

A discrepância do NSS (§2.7 — formulário diz 250/500/750/1000, colunas dizem
200/500/800/1000) existe **porque o patamar está codificado no nome da coluna**. Mudar uma
etiqueta obriga a renomear colunas, e foi exatamente isso que ninguém fez.

→ `QUA_CR_DIC_PATAMAR`: `ID_PATAMAR`, `TIPO` (`NSS`\|`CACL2`), `ORDEM`, `DESIGNACAO`,
`ATIVO`. `QUA_CR_MOV_CORROSAO.ID_PATAMAR` é FK para aqui. Assim:

- renomear um patamar é um `UPDATE` numa linha, não uma migração de schema;
- acrescentar um 5.º patamar NSS é um `INSERT`;
- o formulário mostra sempre `DESIGNACAO` — o que eles têm — sem que o modelo tome partido;
- desativar um patamar não apaga o histórico já medido.

Sementeira inicial: NSS com a série **das colunas** (200/500/800/1000, é o que está
gravado) e CaCl₂ com 24H/48H/72H/96H/120H/14D/28D. As designações corrigem-se em `UPDATE`
quando a Carla confirmar, sem tocar em código.

## 4. Backend — `C:\work\Workspace\sgiid`

Padrão exato do `QUA_MC_*`/`QUA_EPI_*` (Set/2026), o mais recente do repo.

1. **DDL** — `sql\QUA_CROMAGEM_MODULO_COMPLETO.sql`, idempotente, com
   `IF OBJECT_ID('...','U') IS NULL CREATE TABLE`, secções em banners `-- ====`, `GO` entre
   statements, PK `INT IDENTITY(1,1)`, FKs só como comentário + `CREATE INDEX IX_*`
   (convenção do repo: sem `FOREIGN KEY` declarada). Modelo a copiar:
   [sql\QUA_EPIS_MODULO_COMPLETO.sql](file:///C:/work/Workspace/sgiid/sql/QUA_EPIS_MODULO_COMPLETO.sql).
2. **Entidades** — `src\main\java\pt\example\entity\QUA_CR_*.java`, uma por tabela:
   `@Entity`, `@Table(name=...)`, `@Id @GeneratedValue(strategy=IDENTITY)`, `@JsonProperty`
   em cada campo, getters/setters à mão. Modelo: `entity\QUA_MC_GABARITOS.java`.
3. **DAOs** — `dao\QUA_CR_*Dao.java`, `extends GenericDaoJpaImpl<T,Integer>`, com `getall()`,
   `getbyid(id)` e um `getbyRelatorio(id)` por filha. Modelo:
   `dao\QUA_MC_MOV_CALIB_EQUIP_DETDao.java`. Listagens denormalizadas em
   `createNativeQuery` com aliases posicionais `C0..Cn` (`QUA_MC_EQUIPAMENTOSDao.getlista()`).
4. **REST** — adicionar em `rest\SIRB_4.java` (é onde vive todo o `QUA_MC_*`/`QUA_EPI_*`),
   `@Injects` novos + blocos `create/get/getbyid/update/delete` por tabela, separados por
   `/******** QUA_CR_... */`. **Sem** `@Path` novo — a classe já é `@Path("/sirb")`.
5. **Gravação em bloco** — um `QUA_CR_RELATORIO_DTO` (POJO não-JPA, como
   `entity\QUA_EPI_ENTREGA_DTO.java`) e um `POST /createQUA_CR_RELATORIO_COMPLETO` que grava
   cabeçalho → apaga filhas → recria filhas, tudo na transação JTA do `@Stateless`.
   **Isto é uma melhoria deliberada face ao padrão dominante** do frontend (N chamadas HTTP
   independentes, sem fronteira transacional — um boletim meio-gravado é representável hoje).
   Precedente existente: `createQUA_EPI_ENTREGA_COMPLETA` em `SIRB_4.java:1773`.

---

## 5. Frontend — `c:\work\Projects\GITLAB PROJECTS\sgiid`

PrimeNG `p-table`, **não AG-Grid** (não há um único `ag-grid-angular` em
`paginas/modulo-qualidade/`). Tudo declarado no módulo raiz — não há feature modules na
Qualidade.

### 5.1 Os 5 ficheiros centrais que cada ecrã novo obriga a tocar

| Ficheiro | O que acrescentar |
|---|---|
| [app.module.ts](src/app/app.module.ts) | `import` + entrada em `declarations` (componentes) e em `providers` (serviços — não usam `providedIn:'root'`) |
| [app-routing.module.ts](src/app/app-routing.module.ts) | Bloco `RouterComponent` + filhos `''`/`view`/`editar`/`novo`, `canActivate:[LoginService]` em **todos**, `data:{breadcrumb}`. Antes das entradas `login`/`''`/`**` |
| [LoginService.ts](src/app/servicos/LoginService.ts) | `node<ID>: '<rota>'` no mapa `nodes` — **sem isto o guard nunca valida o ecrã e ele fica público** |
| [permissoes.ts](src/app/paginas/configuracoes/permissoes.ts) | `TreeNode` dentro de `permissoes_modulo_5`, com filhos `criar`/`editar`/`apagar` |
| [menu.component.html](src/app/menu/menu.component.html) | `<li id="node<ID>">` dentro de `#collapsereclamacoes` + `\|\| isLinkActive('/<rota>',true)` no `[ngClass]` do painel Qualidade |

O `id="node..."` no `<li>` é funcional, não cosmético: o `LoginService` procura-o por
`document.getElementById` para desbloquear a entrada de menu.

### 5.2 Nós e rotas novos

Ocupados no ramo Qualidade: `node50`, `52`, `53`, `55`, `56`, `561`…`565`. Livre: **`node566`**.

| Nó | Rota | Ecrã |
|---|---|---|
| `node566` | — | "Laboratório Cromagem" (submenu, sem rota) |
| `node5660` (+`criar`/`editar`/`apagar`) | `cr_relatorios` | Lista + ficha do boletim |
| `node5661` (+`criar`/`editar`/`apagar`) | `cr_referencias` | Lista + ficha técnica da referência |
| `node5662` | `cr_analises` | Evolução de resultados / ensaios por referência |
| `node5663` (+`criar`/`editar`/`apagar`) | `cr_conj_equipamentos` | Conjuntos de equipamento (§3.5) |

### 5.3 Componentes — `src/app/paginas/modulo-qualidade/lab-cromagem/`

```
relatorios/relatorios.component.{ts,html,css}                 lista (p-table, filtros, stateKey)
relatorios/ficha-relatorio/ficha-relatorio.component.{ts,html,css}   cabeçalho + 4 tabs
referencias/referencias.component.*                           lista de referências
referencias/ficha-referencia/ficha-referencia.component.*      ficha técnica + N testes
conj-equipamentos/conj-equipamentos.component.*                conjuntos de equipamento
analises/analises.component.*                                 evolução + seguimento
```

**Ficha da referência** (`ficha-referencia`) — o form que descreveste. Cabeçalho com os 3
campos vindos do SILVER, o resto editável:

- `REFERENCIA` e `REFERENCIA PECA PLASTICA` por picker `p-autoComplete` sobre
  `getReferenciaSearch/{q}` (server-side, são ~milhares de artigos — não carregar tudo);
  `CLIENTE` por `p-dropdown` sobre `getClientes`. Os três mostram-se read-only depois de
  escolhidos, com a descrição do SILVER ao lado.
- Restantes grupos conforme §3.4: identificação, classificação (3 dropdowns em vez de 16
  checkboxes), espessuras exigidas, estrutura de camada, pautas, corrosão.
- Sub-grelha de testes (`QUA_CR_DIC_REF_TESTE`), linhas adicionáveis, sem o teto de 7.

**Conjuntos de equipamento** (`conj-equipamentos`) — mesmo padrão: lista + `p-dialog` com
designação do conjunto e um `p-multiSelect` sobre `QUA_MC_EQUIPAMENTOS` para as linhas.

A ficha do relatório é o ecrã central, tal como descreveste: **cabeçalho + tabs**. Tabs no
padrão do módulo — `div`s Bootstrap alternadas por uma variável `ativobt`, **não**
`p-tabView` (é o que `ficha-equipamento.component.html` faz):

Layout fiel ao ecrã Access (§2.1), mesma ordem de tabs:

- **Cabeçalho** — nº relatório (automático, §10.3), tipo de ensaio, referência + descrição
  do SILVER, cliente, lote, linha, local de produção, datas de entrada/cromagem/receção,
  **a fotografia da referência**, e as 3 checkboxes "Testes previstos" que ativam as tabs e
  os 3 botões de impressão `PAG. 1`/`PAG. 2`/`PAG. 3`.
- **Tab 1 · Espessuras / Poros / Fissuras** — pauta e espessuras exigidas Cr/Ni/Cu em
  read-only da ficha técnica; grelha de N amostras rotuladas `A`, `B`, `C`… com
  identificação da peça, peso, cavidade, Cr/Ni/Cu e resultado; N proposto por
  `N_DE AMOSTRAS` e linhas adicionáveis (é aqui que se resolve o teto de 5 do Access);
  bloco poros / fissuras / step; conjunto de equipamento e operador.
- **Tab 2 · Pautas de ensaio** — um bloco por teste da ficha técnica
  (`QUA_CR_DIC_REF_TESTE`), com o nome do teste, conjunto de equipamento, peso, cavidade e
  as leituras de resultado.
- **Tab 3 · NSS / CaCl₂** — dois grupos como no Access: NSS (5 amostras × 4 patamares, `CL`)
  e CaCl₂ (tipo Renault/Peugeot/Japão, amostras × 7 patamares, OK/NÃO OK).
- **Tab 4 · Condições** — nº peças, superfície, matriz 7 banhos × corrente/tempo, observações.

Convenções obrigatórias, verificadas em `ficha-equipamento.component.ts`:
- modo por segmento de URL (`novo`/`editar`/`view`), submit por botão escondido
  `#buttongravar` acionado pela toolbar partilhada `<app-controlos>`;
- flags de toolbar via `AppGlobals` ([sidebar.metadata.ts](src/app/menu/sidebar.metadata.ts)),
  lidas de `localStorage['acessos']` no `ngOnInit`;
- navegação sempre `queryParams:{id}`;
- **apagar é soft delete** (`ATIVO=false` + `UTZ_ANULA`/`DATA_ANULA` via `update`), nunca
  `delete()`, confirmado com `ConfirmationService` + `<p-confirmDialog key="...">`;
- datas re-hidratadas com `new Date(...)` depois do load, senão o `p-calendar` não pinta.

### 5.4 Serviços e entidades

`src/app/entidades/QUA_CR_*.ts` (classes simples, campos UPPER_SNAKE) e
`src/app/servicos/qua-cr-*.service.ts` (`create`/`getAll`/`getById`/`getByRelatorio`/
`update`/`delete`), copiando [qua-mc-mov-calib-equip.service.ts](src/app/servicos/qua-mc-mov-calib-equip.service.ts).
Mais um `gravarCompleto(dto)` para o endpoint de bloco.

---

## 6. Relatórios Jasper

Os `.jrxml` **não estão no repo** — vivem em `C:/SGIID/relatorios/jasperfiles/`. Do frontend
chamam-se por [relatorios.service.ts](src/app/servicos/relatorios.service.ts) →
`downloadPDF('pdf', filename, id, '<relatorio>', 'nenhuma')` + `FileSaver.saveAs`.

| Novo relatório | Substitui | Parâmetro |
|---|---|---|
| `cr_boletim_ensaio` | `ID127_06_PAG1/2/3` | `ID_RELATORIO` + nº de página (o ecrã tem 3 botões independentes, §2.1) |
| `cr_seguimento_pecas` | as 4 variantes de `SEG PÇS CROMADAS` | referência (via `downloadPDFPOST`) |

Os `IIf()` gigantes das consultas Access **desaparecem**: com `RESULTADO` numa coluna única
e as amostras em linhas, o `.jrxml` faz um `SELECT` simples com sub-relatórios por secção.
Precedente de cromagem para a variante com filtros: `downloadPDFPOST('xlsx', ..., 'historico_analises_banho', 'engenharia_cromagem', data)`
em `gestao-banhos.component.ts:566`.

As análises de evolução (`EVOLUCAO RESULTADOS`, `EVOLUCAO ENSAIOS POR REF`) ficam como ecrã
`cr_analises` com `p-table` + export por `xlsx-with-styles`, como
`analise-geral-muro.component.ts:470-550`.

---

## 7. Migração dos dados

Script `sql\QUA_CROMAGEM_IMPORT_ACCESS.ps1` (há precedente: `QUA_MC_IMPORT_ACCESS.ps1`).

**Dois modos de leitura, ambos validados nesta sessão, sem instalar nada:**
- **ODBC** (`Microsoft Access Driver (*.mdb, *.accdb)`) para todas as colunas normais;
- **DAO** (`DAO.DBEngine.120`) obrigatoriamente para as fotografias — o ODBC não lê campos
  Attachment. O acesso é `rs.Fields("FOTOGRAFIA").Value` → `Recordset2` filho →
  `Fields("FileData").SaveToFile(...)` → `Convert::ToBase64String` → partir em duas metades.
  **Testado ponta a ponta:** referência `SD9294A` extraiu `Texte Alstom.jpg`, 113 KB,
  header `FF D8 FF E0` (JPEG válido), 154 048 chars de base64 → `FICHEIRO_1`/`FICHEIRO_2`
  de 77 024 cada. Sim, importa-se tudo.

Ordem e transformações:

1. **Pré-requisito:** export fresco de `P:\Qualidade\...\FAMÍLIA 5\2026\` (§1.3).
2. Dicionários `ACABAMENTO`, `TIPO_PECA`, `TIPO_ENSAIO`, `RESULTADO`, `LOCAL_PRODUCAO`.
3. **Mapeamentos manuais, feitos uma vez e guardados em CSV** (são a parte que dá trabalho):
   - 4 controladoras → `GER_UTILIZADORES.ID`;
   - 47 equipamentos → `QUA_MC_EQUIPAMENTOS.ID_EQUIPAMENTO` por `COD_INTERNO`/`EQ*`;
   - linhas 1/2 → `AB_DIC_LINHA.id_LINHA`.
4. `QUA_CR_DIC_REFERENCIA` (1423) + `QUA_CR_DIC_REF_TESTE` (pivot dos 7 pares). Resolver as
   13 referências duplicadas e as 2 vazias **antes** de importar; tentar casar
   `PROREF_SILVER` (com e sem prefixo `PC`).
5. `QUA_CR_MOV_RELATORIO` (1086), guardando `ID_RESULTCOULOSC` numa coluna
   `ID_ACCESS_LEGADO` para poder reconciliar.
6. Filhas, com *unpivot* das colunas em linhas; descartar amostras/leituras sem nenhum valor.
7. `RESULTADO`: `OK`→`OK`, `OKRES`/`OKCOND`→`OK_COND`, `NOK`→`NOK`, três-a-`False`→`NULL`.
   O único registo CaCl₂ com flags contraditórias é reportado e decidido à mão.
   **Correntes e tempos** (§2.6): `-`/`''`→`NULL`, vírgula→ponto, `CORR_*`→`DECIMAL(8,3)`,
   `TEMPO_*`→`TIME(0)`. **Não migrar** `DIA24HORAS`…`DIA28DIAS` (vazias, §2.8).
   `N_RELAT_CR` mantém-se tal como está no Access; a sequência automática (§10.3) arranca no
   máximo já existente por ano.
8. **Fotografias** (833 imagens) para `QUA_CR_DIC_REFERENCIA_FICHEIROS`, por DAO, com
   redimensionamento a 1200 px / JPEG 80 (`System.Drawing` já vem com o .NET do Windows).
   Correr **depois** das referências e em passagem separada, para poder repetir só esta
   parte sem reimportar o resto — é a etapa longa (~250 MB a ler do `.accdb`).

---

## 8. Faseamento

| Fase | Conteúdo |
|---|---|
| **1** | DDL + entidades/DAOs/REST + script de migração + ecrãs de referências (`cr_referencias`) e conjuntos de equipamento (`cr_conj_equipamentos`) |
| **2** | Ficha do relatório com as 4 tabs + lista (`cr_relatorios`) + boletim `cr_boletim_ensaio` |
| **3** | `cr_analises` (evolução) + `cr_seguimento_pecas` |

No fim da fase 2 a Carla trabalha só no SGIID; a fase 3 substitui os relatórios de análise.

---

## 9. Verificação

1. **Build:** `npm run build` na raiz do frontend (a memória do projeto confirma que este é
   o gate usado neste repo) e `mvn package` no backend.
2. **Migração:** contagens origem vs destino — 1423 referências, 1086 relatórios, e por
   filha o total de amostras/leituras não-vazias. Confirmar que os 6+2+2 relatórios com
   filhas duplicadas (§2.1) trazem **todas** as linhas, não só a primeira.
3. **Boletim:** gerar `cr_boletim_ensaio` para um punhado de `N_RELAT_CR` e comparar página
   a página com o PDF do `ID127_06` gerado no Access sobre o mesmo registo — em particular
   um caso `OK`, um `OK_COND`, um `NOK` e um sem resultado.
4. **Permissões:** com um perfil sem `node5660criar`, confirmar que o botão Novo aparece
   desativado e que navegar direto para `#/cr_relatorios/novo` redireciona para `/home`.
5. **Ecrã:** criar um boletim de ponta a ponta numa referência com `N_DE AMOSTRAS`=10 —
   o caso que o Access não consegue registar.

---

## 10. Decisões tomadas e pontos ainda abertos

**Decidido (2026-09-02):**

| # | Ponto | Decisão |
|---|---|---|
| 2 | `QUA_MC_DIC_PECAS_CROMADAS` | **Tabela nova.** `QUA_CR_DIC_REFERENCIA` fica independente; não se estende a de Meios de Controlo |
| 3 | `N_RELAT_CR` | **Automático** — sequência por ano, formato `<n>/<ano>`, atribuída na gravação |
| 4 | As 4 controladoras | Já são utilizadoras SGIID (a confirmar na migração) |
| 5 | Filhas duplicadas | **São medições repetidas legítimas** → confirma o modelo 1:N e obriga o ecrã a mostrar todas |
| 7 | Equipamentos | **Tabela nova** para os conjuntos (`QUA_CR_DIC_CONJ_EQUIP`), com as linhas a apontar para `QUA_MC_EQUIPAMENTOS` |
| 8 | Ensaio NSS | **Manter.** Fica implementado; se o cliente confirmar que está descontinuado, retira-se depois |
| 9 | Colunas quase-mortas | **Manter** todas (`TESTE 6`, `N_EXIG_FISSURAS`, `MICROFISSURADO`, `SM5077`, `GR SAT F`, `GR SAT I`) |
| — | Fotografias | **base64** na convenção do SGIID (`FICHEIRO_1`+`FICHEIRO_2`), **redimensionadas** a 1200 px / JPEG 80 na importação |
| — | `FICHA TECNICA` / `CARTELA` | **Passam a anexo base64**, na mesma tabela de ficheiros das fotografias |
| — | Patamares de corrosão | **Não codificar no nome da coluna.** Dicionário `QUA_CR_DIC_PATAMAR`, formulário mostra a designação (§3.6) |

**Ainda abertos:**

1. **Export fresco de 2026 do `P:`** — **bloqueia a migração** (§1.3). Faltam-me
   `TABELA LOCAL DE PRODUCAO` e a coluna `Local de Producao`. O `P:` está-me inacessível.
   Onde é usado: `Consulta_ID_EVOLUCAO ENSAIOS` filtra por
   `WHERE [TABELA LOCAL DE PRODUCAO].[Local de Producao]="Doureca"` — é o que distingue o
   relatório **"EVOLUÇÃO DE ENSAIOS - DOURECA"** do "AVALIAÇÃO GERAL DE RESULTADOS", que não
   filtra. Serve para separar a cromagem feita na Doureca da feita em subcontratação.
2. **Os PDFs das fichas técnicas e cartelas.** Decidiu-se que passam a anexo (§10), mas o
   Access só guarda o **código** (`CRO-1024`), não o ficheiro. Para a migração os poder
   importar preciso de saber onde vivem os PDFs — provavelmente numa pasta do `P:`. Sem
   isso, migra-se o código como `DESCRICAO` do anexo e os ficheiros anexam-se depois, à mão
   ou por um segundo passe do script.
3. **Designações dos patamares NSS.** Já não bloqueia nada (§3.6 — são dados). A sementeira
   arranca com a série das colunas (200/500/800/1000) e corrige-se por `UPDATE` quando a
   Carla confirmar se o correto é 250/500/750/1000.

---

## 11. Estado da implementação

### 11.1 Feito — backend fase 1 (compila limpo, `javac` exit 0, 52 classes)

| Artefacto | Ficheiro |
|---|---|
| DDL, 23 tabelas + 22 índices + seeds | `sql\QUA_CROMAGEM_MODULO_COMPLETO.sql` |
| 23 entidades JPA | `src\main\java\pt\example\entity\QUA_CR_*.java` |
| 3 DTOs de gravação em bloco | `...\entity\QUA_CR_RELATORIO_DTO.java`, `..._ENSAIO_TESTE_DTO.java`, `..._CORROSAO_AMOSTRA_DTO.java` |
| 23 DAOs (4 escritos à mão) | `src\main\java\pt\example\dao\QUA_CR_*Dao.java` |
| 126 endpoints CRUD + 10 especiais | `src\main\java\pt\example\rest\SIRB_5.java` |

Seeds incluídos: 6 tipos de ensaio, 3 tipos de peça, 4 tipos de superfície, 9 aspectos,
11 patamares (7 CaCl₂ + 4 NSS), 22 resultados, `Doureca` como local de produção.

### 11.2 O que mudou face ao plano, ao implementar

1. **`ASPECTO` e `ACABAMENTO` estavam trocados no plano.** O vocabulário do laboratório,
   confirmado nos aliases de `Consulta_ID127_06_PAG1` e no ecrã: **`ASPECTO`** é o
   dicionário dos 9 bits (`TRIVALENTE BRILHANTE`, `SATINADO ICE`, `FUMÉ`…) e
   **`ACABAMENTO`** é `MICROPOROSO`/`MICROFISSURADO`, calculado dos dois `BIT` da
   referência (pode ser `MICROPOROSO/MICROFISSURADO` quando ambos estão ativos), e por isso
   não tem tabela. A tabela passou a `QUA_CR_DIC_ASPECTO` e as designações são
   literalmente as do laboratório — é este o texto que sai impresso no boletim.
2. **Pautas de ensaio e corrosão precisam de 3 níveis, não 2.** No Access `Equip_11` é um
   por teste mas `Peso_11`/`_111`/`_1111`/`_11111` são quatro: o equipamento é por teste, o
   peso/cavidade/resultado por leitura. Idem na corrosão — `PESO1_CACL..PESO4_CACL` são por
   amostra, `PESO1_48H_OK` é por amostra × patamar. Ficou
   `ENSAIO_CAB → ENSAIO_TESTE → ENSAIO_LEITURA` e
   `CORROSAO_CAB → CORROSAO_AMOSTRA → CORROSAO_LEITURA`. Achatar num nível repetiria o peso
   sete vezes por amostra.
3. **Faltava `QUA_CR_DIC_TIPO_SUPERFICIE`** na lista do plano — estava referida nas notas da
   referência mas sem tabela. Total: **23** tabelas, não 20.
4. **Auditoria em `DATETIME`, não `DATE`.** Corrige a incoerência que já existia no repo
   (DDL do `QUA_MC` com `DATE`, entidade com `Timestamp`). As datas de negócio
   (`DATA_ENTRADA_LAB`, `DATA_RECECAO`, `DATA_MEDICAO`, `DATA_TESTES`, `DATA_CACL2`)
   ficam `DATE` → `java.sql.Date`; `DATA_HORA_PRODUCAO` e `DATA_REGISTO` são `DATETIME`.
5. **Classe REST nova `SIRB_5.java`** em vez de crescer o `SIRB_4` (2210 linhas, 307
   caminhos). O JAX-RS junta todas as classes `@Path("/sirb")`, portanto os endpoints saem
   em `/rest/sirb/...` como os restantes — é como o repo cresceu de `SIRB` a `SIRB_4`.
6. **Não existe endpoint `getall` para `QUA_CR_DIC_REFERENCIA_FICHEIROS`** — seriam ~250 MB
   de fotografias numa resposta. Listagem por `...FICHEIROSbyReferencia` (só metadados) e
   conteúdo por `...FICHEIROScontent/{id}`.
7. **O número do relatório é atribuído no servidor**, dentro de
   `createQUA_CR_RELATORIO_COMPLETO`, e não no ecrã: se fosse no ecrã, duas técnicas a
   gravar ao mesmo tempo ficavam com o mesmo `N_RELAT_CR`.

### 11.3 Detalhes do formulário confirmados pelos screenshots do cliente

Acrescentam-se ao §2.1, e já estão no modelo:

- O `PARECER FINAL DO LABORATÓRIO` **repete-se em cada tab** (espessuras, pautas,
  NSS/CaCl₂), cada um com a sua data e o seu `Realizado por` — é exatamente o que os
  `*_CAB` guardam.
- A definição de `OK_COND` está impressa no ecrã: *"Sempre que algum dos valores
  mencionados acima não cumpra com os requisitos do cliente, pode ser validado, caso se
  obtenham resultados OK nos restantes testes especificados ou em testemunhos de testes
  realizados."* Não é um "quase OK" — é uma decisão explícita do laboratório.
- O bloco de **Step** tem 4 colunas `(1) (2-1) (3-2) (4-3)`, com 3 valores de espessura e 4
  de potencial, e os valores são **pares em texto** (`2.12/2.26`), não números.
- O bloco de **Poros/Fissuras** mostra `ACABAMENTO` e `ASPECTO` em read-only da referência,
  e `Exigido` vem de `N_EXIG_POROS`/`N_EXIG_FISSURAS`.
- As amostras estão rotuladas `A`, `B`, `C`, **`E`** — a letra `D` está saltada no
  formulário Access. É um lapso de etiqueta, não semântica: no SGIID as letras derivam de
  `NUM_AMOSTRA`, sem reproduzir o salto.

### 11.4 Ambiente

- **Não há Maven instalado** nesta máquina. A validação foi por `javac` com classpath do
  `.m2` local (`javaee-api-7.0.jar` + `jackson-annotations`), que é o que o `pom.xml`
  declara.
- **O repo tem encodings mistos:** 42 ficheiros em Cp1252 com acentos e 43 em UTF-8 com
  BOM, e o `pom.xml` não declara `sourceEncoding` — nenhum dos dois encodings compila o
  projeto todo. Não foi alterado. Os ficheiros de `QUA_CR` são **ASCII puro** de propósito,
  para compilarem em qualquer um.

### 11.5 O que o ensaio a seco da migração descobriu (2026-09-03)

`sql\QUA_CROMAGEM_IMPORT_ACCESS.ps1` corre com `-DryRun`: lê o Access, faz todas as
transformações, reporta, e não escreve nada no SQL Server. Foi assim que se encontrou o
seguinte.

**O laboratório contornou o limite de 5 amostras escrevendo duas medições no mesmo campo.**
É a prova do problema que motivou a migração. No 5.º slot de espessuras, 73% dos registos
têm pares separados por `/`; nos slots 1 e 2, **zero**:

```
Amostra 5a : Peça Nº81/ Peça Nº61        Amostra 5a : Peça Nº8- CC1 Esquerdo/CC1 Direito
Cavidade5a : 1/1                          Cavidade5a : 4
Peso 5a    : 75.88/75.90                  Peso 5a    : 70.80
Cr 5a      : 0.20/0.19                    Cr 5a      : 0.11/0.09
```

O mesmo padrão aparece nos pesos do CaCl₂ (4 slots) e nos das pautas de ensaio. A migração
desdobra-os em linhas — o modelo novo não tem teto. **Recuperou 193 amostras de espessura e
98 de corrosão** que de outra forma se perdiam (eram texto não-numérico). Nas pautas não se
pode desdobrar (há um só `ID_RESULTADO` por leitura): fica o primeiro valor e o texto
original vai para a observação da leitura.

**As medições vinham anotadas.** 3683 valores no formato `9.96 (140)`, `28.16 (Min)*`,
`11.21*` — o número entre parênteses parece ser a cavidade daquela medição. Descartá-los
perdia as medições todas. O número vai para as colunas `ESP_*` e a anotação para uma coluna
`OBSERVACOES` nova em `QUA_CR_MOV_ESPESSURA`.

**Guardar a descrição do SILVER.** Só o código `PROREF` estava a ser guardado, portanto ao
reabrir a ficha a descrição aparecia vazia. Acrescentadas `PROREF_SILVER_DESC` e
`PROREF_PLASTICA_DESC`, como em `COM_REFERENCIAS_SILVER` e `QUA_EPI_DIC_EPI`.

**Contagens do ensaio a seco** (1423 referências, 1086 relatórios de 2026):

| Tabela | Linhas |
|---|---|
| `QUA_CR_DIC_REFERENCIA` / `_REF_TESTE` | 1423 / 3099 |
| `QUA_CR_MOV_RELATORIO` | 1086 |
| `QUA_CR_MOV_CONDICOES_CAB` / `_CONDICOES` | 970 / 5761 |
| `QUA_CR_MOV_ESPESSURA_CAB` / `_ESPESSURA` | 1063 / 3691 |
| `QUA_CR_MOV_ENSAIO_CAB` / `_TESTE` / `_LEITURA` | 501 / 1072 / 3326 |
| `QUA_CR_MOV_CORROSAO_CAB` / `_AMOSTRA` / `_LEITURA` | 127 / 598 / 1164 |
| `QUA_CR_DIC_REFERENCIA_FICHEIROS` | 833 |

**Fotografias — a estimativa de §3.4 estava otimista.** 249,8 MB de originais reduzidos a
1200 px / JPEG 80 dão **52,7 MB**, e em base64 numa coluna `NVARCHAR(MAX)` ocupam
**~140 MB** na base de dados, não os 15-25 MB estimados.

**Anomalias a resolver à mão.** O script escreve o detalhe completo em
`ANOMALIAS_CR_IMPORT.csv`, ao lado do `.accdb`:

| Anomalia | N.º | Notas |
|---|---|---|
| `referencia-duplicada` | 12 | Migram todas; o ID do Access distingue-as. Resolver no ecrã |
| `tempo-corrigido` | 13 | `0;42:06` → `0:42:06` e afins — o `;` e o `.` estão ao lado do `:` |
| `tempo-nao-parseavel` | 4 | `0:3050`, `00:.12:04` — ficam a `NULL` |
| `aspecto-multiplo` | 3 | Fica o primeiro por ordem de frequência |
| `referencia-vazia` | 2 | IDs Access 376 e 689 |
| `resultado-contraditorio` | 1 | `CORR 89`: OK e NOK ambos ativos → `NULL`, para alguém decidir |
| `valor-malformado` | 1 | `21,23 869)` |
| `superficie-multipla` | 1 | |

**Cuidado a ler o relatório do ensaio a seco:** sem ligação à base de dados, as anomalias
`resultado-desconhecido` (3203) e `tipo-ensaio-desconhecido` (1084) são **falsos
positivos** — os dicionários leem-se do destino, que em ensaio a seco não está ligado.

**Ausência de `LOCAL DE PRODUCAO`.** O script deteta se a tabela e a coluna existem na
origem: quando faltam (como no snapshot atual), todos os relatórios ficam com `Doureca` e
isso é reportado. Deixou de ser um bloqueador.

### 11.6 Import feito (2026-09-03) e o que falhou pelo caminho

Importado para **`192.168.40.126 / SGIID`** (testes), com autenticação do Windows
(`-AutWindows`) — a password do `sa` não é necessária. Verificado na base de dados, não pelo
output do script: **todas as 16 contagens batem** com o ensaio a seco, integridade
referencial limpa (zero órfãos em espessuras, leituras de ensaio e de corrosão, zero
relatórios com referência inválida, zero linhas de conjunto sem equipamento).

Os poucos nulos que restam vêm da origem, não de falha de mapeamento — contados nos dois
lados: 3 relatórios sem linha (não tinham `LINHA 1` nem `LINHA 2`), 2 sem tipo de ensaio
(`Tipo` vazio), 2 blocos de espessuras sem operador.

**Fotografias validadas de ponta a ponta.** Descodificadas da base de dados: a maior sai a
1167×1019 px (abaixo do limite de 1200), header `FF D8 FF E0`, zero anexos com conteúdo em
falta. O `CONCAT(FICHEIRO_1, FICHEIRO_2)` reconstrói exatamente os bytes.
Ocupação real: **70,3 MB**, não os ~140 MB estimados em §11.5 — a estimativa assumia 2 bytes
por carácter, mas o SQL Server comprime `NVARCHAR` em páginas de dados e o base64 é ASCII.

**Duas falhas no primeiro import, ambas "o valor não cabe no tipo de destino":**

1. **Texto de 3635 caracteres numa coluna de 255.** O `TESTE 3` da referência `PC23845811`
   tinha `"Ciclo Térmi"` + **3612 espaços** + `"o suplementar"`. O `.Trim()` não os apanha
   porque são interiores.
2. **`TEMPO_SB = '9'` a virar 9 dias.** Bug de parsing, não dos dados: o
   `TimeSpan.TryParse` do .NET interpreta um número isolado como **dias**, e o `TIME` do SQL
   Server só aceita menos de 24 horas.

E um terceiro problema descoberto à conta disso: a sentinela de "não aplicável" só apanhava
o hífen `-`, mas os dados têm **travessões** (`U+2013`) — o Access substitui o hífen sem se
dar por isso.

**O que mudou para não se repetir:**

- **O ensaio a seco passou a ver o que o import vê.** O `Ins` devolvia a chave falsa *antes*
  de olhar para os valores, e por isso o ensaio não podia apanhar o problema 1. As larguras
  são agora verificadas primeiro, nos dois modos. Um ensaio que não vê o que o import vê não
  serve de ensaio.
- **O import não aborta por largura.** O `Ins` lê as larguras reais do destino do
  `INFORMATION_SCHEMA` (75 colunas de texto) e, quando um valor não cabe, primeiro colapsa
  corridas de 3+ espaços — que é o defeito real quando um campo estoura por milhares de
  caracteres — e só corta se ainda não couber, reportando sempre. Os `\r\n\t` ficam intactos,
  para não destruir a formatação dos procedimentos. Neste caso o colapso resolveu por
  completo: 3635 → 25 caracteres, sem truncar nada.
- **Um número isolado num campo `h:m:s` fica `NULL` e reportado** (`tempo-sem-unidade`), em
  vez de se adivinhar a unidade de uma medição. Mais uma guarda para tempos ≥ 24h.
- **Se um insert falhar, o erro diz onde.** Antes dizia só "SqlDbType.Time excedida", sem
  tabela nem valores; agora imprime a linha inteira com os tipos antes de rebentar.
- **Salvaguarda de produção.** O alvo aparece sempre no arranque (servidor, base, testes ou
  produção) e um import apontado ao `192.168.40.101` para antes de abrir a ligação, a não
  ser que se passe `-EmProducao`. O valor por omissão passou a ser o servidor de testes.
- **`-AutWindows`** para autenticação integrada, e as falhas de ligação explicam a causa
  (credenciais recusadas vs base inacessível vs servidor inalcançável).

**Anomalias finais: 529.** Cerca de 500 são informativas (291 slots desdobrados em várias
amostras, ~198 pesos em par preservados na observação, 13 tempos corrigidos). As 24 de
decisão humana estão em §11.5, e o detalhe completo em `ANOMALIAS_CR_IMPORT.csv`.

Onde estão as duas bases no `126`: o DDL está em **`SGIID`**. A `SGIID_DEV` existe mas tem
**0** tabelas `QUA_CR_*` — é antiga, apesar de ser a que o `ConnectionSQL.java:53` cita.

### 11.7 Fase 2 — ficha do relatório

Feito: **lista** (`cr_relatorios`) e **ficha** (`ficha-relatorio`) com o cabeçalho e as 4
tabs na ordem do formulário Access, mais o wiring nos 5 ficheiros centrais e o `node5660`
com criar/editar/apagar.

- **Lista:** filtro por ano (o Access tinha uma base de dados por ano), filtro "só com Não
  OK ou reservas", e um semáforo por bloco (espessuras, pautas, CaCl₂, NSS) com a bola só a
  aparecer quando o teste está previsto — um teste não previsto não tem resultado nem por
  avaliar. Export para Excel.
- **Ficha:** grava tudo numa chamada (`createQUA_CR_RELATORIO_COMPLETO`); o número do
  relatório é mostrado antes de gravar mas atribuído pelo servidor, senão duas técnicas a
  gravar ao mesmo tempo ficavam com o mesmo. A ficha técnica da referência **propõe** o nº
  de amostras e os testes, sem impor: as grelhas aceitam linhas a mais ou a menos, que é o
  que resolve o problema que originou a migração.
- **Impressão:** três botões independentes (`cr_boletim_pag1/2/3`), como no Access, cada um
  ativo só se o teste da respetiva página estiver previsto.

### 11.8 Boletim Jasper (2026-09-04)

Os três `.jrxml` estão em `C:\sgiid\relatorios\jasperfiles\qualidade\`
(`cr_boletim_pag1`, `_pag2`, `_pag3`) e passam a validação estrutural do
`JRXmlLoader`. A `subPasta` no frontend passou de `'nenhuma'` para `'qualidade'` — sem isso
o servidor não os encontrava.

**O layout não foi reconstruído de memória.** As definições dos três relatórios Access
exportadas por `SaveAsText` no início do trabalho contêm a geometria completa: escrevi um
parser (`scratchpad\parse_report.ps1`) que extraiu **139, 154 e 184 controlos** com
posições, tamanhos, fontes e `ControlSource`, e converteu twips para pontos (1 twip =
1/20 pt). Coordenadas reais: largura 10830 twips = 541 pt em A4 retrato (margens 27 pt),
fotografia a 373,17 com 108×69, rótulos a 0 e valores a 134, as 4 colunas de leitura da
página 2 em 104/210/315/420 com 100 pt, e as larguras da tabela de amostras da página 1
(PEÇA 28, PESO 45, CAV 44, Cr/Ni/Cu 48, RESULT 76).

O código do documento é **`ID127.07`** — `ID127_06` era só o nome interno do relatório Access.

**Três diferenças deliberadas face ao Access:**

1. **A página 1 cresce com o número de amostras.** Eram 5 blocos de controlos em posições
   fixas; passou a uma banda `detail` com uma linha por amostra.
2. **Os patamares da página 3 vêm do dicionário.** O cabeçalho imprime a `DESIGNACAO` de
   `QUA_CR_DIC_PATAMAR`, o que resolve a divergência 250/500/750/1000 (formulário) vs
   200/500/800/1000 (colunas) — renomear passou a ser um `UPDATE`.
3. **As leituras acima da 4.ª não se perdem.** A página 2 mantém as 4 colunas do original,
   mas leituras adicionais saem numa linha de continuação.

As cadeias de `IIf()` das consultas Access desapareceram: com o `RESULTADO` numa coluna, a
tradução para o texto do laboratório é uma expressão de três ramos.

**Bugs corrigidos ao revisitar os ficheiros:** uma cor com 7 dígitos hex (má conversão do
`14277081` do Access, que é `0xD9D9D9`); `javax.xml.bind.DatatypeConverter` para a
fotografia, removido do JDK a partir do Java 11, trocado por `java.util.Base64`; e o
`<group>` colocado entre o título e o detalhe quando o esquema exige que venha antes do
título (este apanhado pelo validador).

**Ferramenta de validação:** `scratchpad\ValidaJrxml.java` carrega um `.jrxml` com
`JRXmlLoader` usando o JasperReports 6.20 do `.m2` local. Valida a estrutura sem precisar
de groovy (que não está no `.m2`; no servidor está, e é o que todos os relatórios usam).

### 11.9 Fase 3 — análises

Ecrã `cr_analises` (`node5662`), com três tabs, que substitui quatro relatórios Access:

| Relatório Access | Onde ficou |
|---|---|
| `EVOLUCAO RESULTADOS` | tab "Evolução por Mês" |
| `EVOLUCAO ENSAIOS POR REF` | tab "Por Referência" |
| `SEG PÇS CROMADAS` (4 variantes) | tab "Seguimento de uma Peça" |

As 4 variantes de "SEG PÇS CROMADAS" eram o mesmo relatório com 1, 2-3, 4 ou 5 blocos de
teste visíveis, porque o Access não conseguia esconder blocos vazios. Aqui é um ecrã só.

Backend novo em `QUA_CR_MOV_RELATORIODao`: `getanaliseevolucao` e
`getanaliseporreferencia`, com as contagens de OK / OK_COND / NOK / sem resultado por
bloco, mais os endpoints `getQUA_CR_ANALISE_EVOLUCAO` e
`getQUA_CR_ANALISE_POR_REFERENCIA` em `SIRB_5`. Compila limpo.

**Decisão que muda a leitura dos números:** a taxa de OK é calculada sobre os ensaios
**avaliados**, não sobre o total. Incluir os que ainda não têm resultado dava uma
percentagem que descia com o atraso do laboratório em vez de com a qualidade — mediria a
coisa errada. Os "sem resultado" aparecem à parte, em cinzento e entre parênteses.

### 11.10 Queries validadas (2026-09-04, rede de volta)

As cinco queries que estavam sem execução correram contra `192.168.40.126 / SGIID`:

| Query | Resultado |
|---|---|
| `cr_boletim_pag1` | 4 amostras, fotografia (28 KB), parecer, operador |
| `cr_boletim_pag2` | pivot das 4 leituras correto |
| `cr_boletim_pag3` | patamares do dicionário, grelha de CaCl₂ com 5 amostras |
| `getanaliseevolucao` | 9 meses, 1073 boletins |
| `getanaliseporreferencia` | 324 referências |
| `getlistabyReferencia` | 160 boletins na referência mais usada |

**Reconciliação contra o Access**, contando os trios de booleanos na origem e as
colunas `RESULTADO` no destino:

| Bloco | Access (OK/OKRES/NOK) | SGIID (OK/OK_COND/NOK) |
|---|---|---|
| Espessuras | 389 / 386 / 1 | 389 / 386 / 1 |
| Pautas | 214 / 215 / 58 | 214 / 215 / 58 |
| CaCl₂ | 36 / 57 / 25 | 35 / 57 / 24 |

As duas diferenças do CaCl₂ são o registo contraditório `CORR 89` (OK e NOK ambos ativos),
que ficou `NULL` por decisão — falta exatamente um OK e um NOK.

Números que parecem errados e não são: as espessuras têm **um único "Não OK" em todo o
ano** contra 386 "com reservas", daí os zeros nas colunas mensais. Confirmado nos dois lados.

Os totais da análise excluem, por construção, os **13 relatórios sem data de produção** (não
se colocam num mês): 1073 de 1086 boletins, e 324 de 327 referências — as 3 em falta só têm
boletins sem data.

**Defeito que só correr revelou: os testes da página 2 saíam sem nome.** A migração criava
`QUA_CR_MOV_ENSAIO_TESTE` com o número e o equipamento mas não preenchia `DESIGNACAO` nem
`ID_REF_TESTE` — o nome do teste, no Access, só existia na `TABELA DE REFERENCIAS`. O
boletim ia para o cliente com os testes anónimos. Corrigido em dois sítios:

- **Na query do `cr_boletim_pag2`**, com `COALESCE(t.DESIGNACAO, rt.DESIGNACAO,
  rt2.DESIGNACAO)`, onde `rt2` é o teste da ficha técnica com a mesma `ORDEM`. Resolve os
  1086 boletins migrados sem lhes tocar.
- **No script de migração**, que passou a gravar `ID_REF_TESTE` e `DESIGNACAO`. **Não é
  preciso reimportar** — é só para uma reimportação futura não repetir a lacuna.

### 11.11 A fazer

- **Reimprimir as três páginas do boletim `2181` (`9/2026`).** Já compilaram e saíram em
  PDF uma vez, portanto as expressões groovy, a fotografia em `java.util.Base64` e as
  bandas estão confirmadas. Mas isso foi antes de duas mudanças: a correção do layout
  (§11.12) e o reimport que corrigiu os valores (§11.13). Uma só impressão valida as duas.
- **Recompilar e redeployar o `.war`.** O `getlistabyReferencia` está corrigido no código
  (§11.12) mas não no servidor, portanto o separador "Seguimento de uma Peça" das análises
  continua a dar 500. É o único bloqueio funcional em aberto.
- **As duas correções do form de ensaios** (§11.15).
- **Copiar `C:\sgiid\relatorios\jasperfiles\qualidade\*.jrxml` para o servidor.**
- **Decidir as 24 anomalias** de §11.5 — sobretudo as 12 referências duplicadas e as 2
  vazias, que deixam 14 referências para consolidar no ecrã.
- **Importar em produção** depois de validado em testes:
  `-SqlServer 192.168.40.101,1433 -EmProducao`.
- **Confirmar com o cliente** se o ensaio NSS ainda se faz (nenhuma das 1346 referências
  ativas tem `PAUTA_NSS`) e qual a série correta de patamares NSS — 250/500/750/1000 do
  formulário ou 200/500/800/1000 das colunas. Esta segunda deixou de ser urgente: os
  patamares são dados, e corrigir é um `UPDATE` em `QUA_CR_DIC_PATAMAR`.

### 11.12 Correções de 2026-09-04

**Layout dos 3 boletins.** Escrevi um verificador
(`scratchpad\verifica_layout.ps1`) que lê o `.jrxml` e assinala, por banda,
elementos sobrepostos, elementos fora da largura útil e espaço morto no fim da
banda. Apanhou 4 defeitos em cada uma das três páginas — três deles invisíveis
até ao PDF sair:

| Defeito | Correção |
|---|---|
| "Página N do REL" (`y=14 h=12`, acaba em 26) por cima de `TIPO DE ENSAIO` (`y=22`) | bloco de dados desceu 8 pt em todas as três. Era isto que fazia parecer que faltava o título na pág. 2 — estava lá, por baixo |
| rótulo da página com 380 pt de largura a tocar a fotografia (`x=373`) | largura para 360 |
| fundo da fotografia (`y=86`) a tocar o topo de `DESIGNACAO` (`y=85`) na pág. 2/3 | `DESIGNACAO` para `y=92` |
| banda `title` da pág. 1 com 365 pt para 228 de conteúdo — **137 pt mortos** | altura para 240 (foi o buraco que o cliente apontou entre o cabeçalho da tabela e a 1.ª linha) |
| rótulo `REALIZADO POR` com `width=65`, saía "REALIZADO" | 78 pt, valor deslocado para `x=425 w=116` |

As alterações fizeram-se por `uuid` (`scratchpad\corrige_layout.ps1`), não por
coordenada, porque as coordenadas repetem-se entre elementos. Depois: validação
estrutural com `JRXmlLoader` OK nas três, e o verificador de layout a zero
problemas — restam só as sobreposições intencionais da barra de secção com a
`PAUTA` alinhada à direita, que o verificador distingue e não conta.

**`NonUniqueDiscoveredSqlAliasException` no seguimento por referência.**
`getlistabyReferencia` (`QUA_CR_MOV_RELATORIODao.java:74`) selecionava
`esp.RESULTADO_TOTAL` e `ens.RESULTADO_TOTAL` sem alias. Os três cabeçalhos de
bloco — `ESPESSURA_CAB`, `ENSAIO_CAB`, `CORROSAO_CAB` — têm todos uma coluna com
esse nome, e o Hibernate rejeita a query nativa quando o `ResultSet` traz o
mesmo alias duas vezes. O `getlista()` ao lado já usava `AS C0..C17` e por isso
nunca falhou; foi só esta que escapou à convenção. Corrigido com `AS C0..C7`,
que é a ordem que o `analises.component.ts:140` já lia por índice — sem
alteração no frontend.

**Não consegui compilar o backend:** o `mvn` não está no PATH desta máquina nem
nos locais habituais. A alteração é apenas dentro do literal SQL, mas o
`.war` tem de ser reconstruído no IDE e redeployado antes de o seguimento
funcionar.

### 11.13 Verificação das espessuras (o reimport #3 confirmado)

Escrevi `sql\QUA_CROMAGEM_VERIFICA_ESPESSURAS.ps1` e corri-o depois do reimport.
Faz a leitura do Access numa **segunda implementação independente** da do
import — de propósito: se as duas concordarem, o erro de indexação de caracteres
não pode estar presente. Compara contagem, soma, mínimo e máximo por metal e
conta as identificações de peça com um único caractere, que era o sintoma
visível (`'Minima'` → `'M'`).

```
OK  Cu: n 3100 vs 3100 | soma 119753,20 vs 119753,20 | min 2 vs 2,000   | max 237  vs 237,000
OK  Ni: n 3100 vs 3100 | soma  79344,55 vs  79344,55 | min 3,2 vs 3,200 | max 83,1 vs 83,100
OK  Cr: n 2923 vs 2923 | soma    909,11 vs    909,11 | min 0 vs 0,000   | max 2    vs 2,000
Access: 3691 identificacoes, 0 com 1 caractere
SQL   : 3691 identificacoes, 0 com 1 caractere
```

As médias são as esperadas — Cr 0,31, Ni 25,6, Cu 38,6 µm. Antes da correção o
Cr vinha a zero em quase todas as linhas, e eu tinha olhado para
`PESO 2,000 Cr 0,000` e dito que "fazia sentido": o formato estava certo e o
valor estava errado, e validei o formato. É por isso que a verificação agora
compara valores contra a origem, e não a forma.

**IDs mudaram com o reimport** (as tabelas foram esvaziadas e o `IDENTITY`
reatribuído). Para testar os boletins: `9/2026` é agora `ID_RELATORIO=2181`
(referência `PC628901564R`, 6 amostras) e `10/2026` é `2182`. O `1095/2026` que
tinha sido usado antes não existe nesta importação.

### 11.14 Ecrã de Parametrização — FEITO (2026-09-04)

Sete dicionários existem com sementeira no DDL, serviço no frontend e CRUD no
`SIRB_5`, mas **não têm onde ser editados** — só são lidos como dropdown:

| Dicionário | n | Conteúdo |
|---|---|---|
| `QUA_CR_DIC_PATAMAR` | 11 | 24H…28D (CaCl₂) + 200/500/800/1000H (NSS) |
| `QUA_CR_DIC_RESULTADO` | 22 | OK/NOK ± ciclos, CL5…CL10 |
| `QUA_CR_DIC_ASPECTO` | 9 | BR TRI, BR HEX, SAT I, SAT F, FUM, GR BR, GR SAT I, GR SAT F, SM5077 |
| `QUA_CR_DIC_LOCAL_PRODUCAO` | 1 | Doureca |
| `QUA_CR_DIC_TIPO_SUPERFICIE` | 4 | Revisão, Esniagem, Barra, Pintura |
| `QUA_CR_DIC_TIPO_PECA` | 3 | Automóvel Ext./Int., Não Automóvel |
| `QUA_CR_DIC_TIPO_ENSAIO` | 6 | P.N., E, A, A.I., P.S., P |

**Decisão (2026-09-04): um único ecrã `cr_parametrizacao`**, em `node5664` (o
próximo livre no ramo `node566`), com separadores no padrão `ativobt` que o
módulo já usa — não sete entradas de menu. Cada separador é uma `p-table` com
`p-dialog` de edição, soft delete por `ATIVO=0`, e `ORDEM` para controlar a
sequência nos dropdowns. O backend e os serviços já existem, portanto **não é
preciso tocar em Java**: é o componente mais os 5 ficheiros centrais de ligação
(§5.1).

Razões concretas por dicionário, para quem retomar isto saber o que é essencial:

- **`PATAMAR` é o mais importante, porque sem ele o §3.6 fica meio-cumprido.**
  Os patamares saíram dos nomes das colunas precisamente para que renomear
  "200 Horas" → "250 Horas" fosse um `UPDATE` numa linha; e continua pendente
  saber com a Carla qual das duas séries NSS é a correta (§10, aberto 3). Sem
  ecrã, esse `UPDATE` é à mão na base de dados.
- **`LOCAL_PRODUCAO` tem uma só linha.** Serve para separar a cromagem feita na
  Doureca da subcontratada — era o filtro `Local="Doureca"` que distinguia os
  dois relatórios de evolução do Access (§1.3). Enquanto não se puder
  acrescentar o subcontratado, o filtro das análises não distingue nada.
- **`RESULTADO` e `ASPECTO` crescem com os clientes.** As 22 combinações `CL`
  dependem da norma do cliente, e o `SM5077` já é um código de cliente, não um
  acabamento genérico.
- `TIPO_ENSAIO` e `TIPO_PECA` são domínios fechados do próprio boletim (os 6
  tipos estão impressos na legenda do relatório). Ficam no ecrã por
  uniformidade, mas editá-los parte o boletim — vale a pena mostrá-los sem
  permitir apagar.

### 11.15 Análise do form de ensaios (2026-09-04) — os dois problemas de dados, CORRIGIDOS

O cabeçalho, a ordem das tabs, os três botões de impressão independentes e os campos
de especificação em leitura da ficha técnica estão fiéis ao formulário Access (§2.1).
O dropdown de tipo de ensaio já traz a legenda ("P.N. — Produção"), portanto a legenda
impressa ao lado no Access deixou de ser precisa. Ficaram dois problemas que mexem em
dados e ainda não estão corrigidos:

**1. A checkbox "Testes Previstos" esconde dados em vez de os governar — nos dois sentidos.**
`gravar()` só mete o bloco no DTO se a flag estiver ligada
(`ficha-relatorio.component.ts:413`), e `createQUA_CR_RELATORIO_COMPLETO` ignora um bloco
a `null` em vez de o limpar. Daí saem dois caminhos maus:

- *Desmarcar depois de gravar:* as amostras já medidas ficam na base de dados, a tab
  deixa de as mostrar e o botão da página desliga. Nada indica que existem. E a listagem
  faz `OUTER APPLY` ao `ESPESSURA_CAB` sem olhar ao `FAZ_ESPESSURAS`, portanto mostra a
  coluna Espessuras preenchida num boletim que diz não fazer espessuras.
- *Escrever numa tab não prevista:* as tabs continuam navegáveis (de propósito, para se
  poder ver o que lá está) e o que se escreve é descartado na gravação sem aviso. É o pior
  dos dois: perda silenciosa de dados acabados de escrever.

Acresce que `gravar()` valida referência e tipo de ensaio mas não exige nenhum teste
previsto: um boletim com as três desmarcadas grava e não imprime nada.

Correção proposta: ligar a flag automaticamente quando se escrevem dados (corresponde a
como o laboratório pensa — "registei espessuras, logo faço espessuras"), fazer a listagem
respeitar a flag, e pedir confirmação ao desmarcar uma caixa que já tem dados gravados,
dizendo quantas linhas ficam ocultas. Bloquear as tabs resolvia a segunda metade, mas
obriga a marcar a caixa antes de poder escrever — atrito sem ganho.

**2. O NSS nunca é proposto.** `proporDaFichaTecnica` semeia 4 amostras de CaCl₂ e zero de
NSS (`ficha-relatorio.component.ts:253`); quem fizer NSS acrescenta as linhas à mão de cada
vez. Liga-se à pergunta aberta com a Carla: nenhuma referência ativa tem `PAUTA_NSS`. O mais
honesto é semear NSS só quando a ficha técnica tem `PAUTA_NSS` ou `TESTE_NSS` — orientado
pelos dados em vez de por um pressuposto.

Menores, sem decisão pendente:

- `letra()` devolve `[`, `\`, `]` a partir da 26.ª amostra. As fichas pedem até 15, portanto
  não é urgente — mas o módulo existe para tirar o teto das 5 e este é um teto novo.
- As 4 amostras de CaCl₂ são um número mágico vindo dos 4 pesos do Access; não há campo na
  ficha técnica que o diga.
- O `00:00:00` cinzento nas colunas SAT e Pass das condições é um *placeholder*, coerente
  com a nota "deixa em branco o banho que não se aplica", mas lê-se como valor.
- Na tab NSS/CaCl₂, "Tipo de CaCl₂: Renault Peugeot Japão" mostra as três etiquetas sem
  checkbox visível em leitura; o mais claro seria mostrar só o tipo marcado.

### 11.16 Alinhamentos, alturas e cores (2026-09-04)

Corrigido na ficha do relatório, na ficha da referência, na lista e nas análises:

- **Roxo `#7b1fa2`** — existia só neste módulo em toda a aplicação. Letras das amostras a
  preto, círculo do número do teste a `#333`, tracejado e fundo lilás dos blocos neutros.
- **Azul herdado** do tema `lara-light-blue` e dos globais do repo: cabeçalhos de tabela,
  valores de leitura (`.exigido`) e o botão do `p-calendar`. Declarados a preto / `#333`.
  O `.form-control2` já estava tratado; faltavam estes.
- **Tab ativa rosa** — era `.ativobt` (brown) × `.tab-off` (opacity 0.55). A cor que marca
  "estou aqui" dependia de o teste estar previsto. `.ativobt.tab-off { opacity: 1 }`.
- **Alturas a 25px numa regra por ecrã.** Havia 44 alturas inline `'height':'25px'`
  espalhadas pelos HTML; o inline vencia o CSS e só cobria os dropdowns, pelo que os
  `p-calendar` e os inputs ficavam mais altos.
- **Cabeçalho desalinhado** era consequência disso: as colunas são floats do Bootstrap sem
  `.row`, e com três alturas diferentes na primeira linha a segunda prendia-se na mais alta
  — "Data de Entrada no Lab." aparecia debaixo de "Tipo de Ensaio". Alturas iguais mais
  `clear: left` no primeiro campo de cada linha.

### 11.17 Trabalho de 2026-09-04, parte final

**Os dois problemas de dados do form (§11.15) estão corrigidos.** A regra que ficou é
uma só: **um bloco com linhas obriga a flag.** Não se pode dizer "não faço espessuras"
com seis espessuras registadas.

- `gravar()` liga a flag de qualquer bloco que tenha linhas e diz quais ligou, para não
  ser uma alteração silenciosa à escolha do utilizador. Isto fecha o caminho da perda
  silenciosa: o que se escreve numa tab não prevista passa a ser gravado.
- Desmarcar uma caixa com linhas registadas é revertido na hora, com um aviso que diz
  quantas linhas existem e que é preciso apagá-las na tab primeiro. Sem isto o utilizador
  só descobriria na gravação, porque o `gravar()` volta a ligar a flag.
- Gravar sem nenhum teste previsto passa a ser recusado — um boletim assim não imprime nada.

  Nota sobre a proposta inicial: eu tinha proposto também "pedir confirmação ao desmarcar,
  dizendo quantas linhas ficam ocultas" e "fazer a listagem respeitar a flag". As duas
  eram incompatíveis com ligar a flag automaticamente na gravação — se a gravação religa a
  flag, "ficam ocultas" é falso. A regra única acima resolve os dois caminhos maus sem a
  contradição, e torna a correção da listagem desnecessária: a incoerência deixa de poder
  existir a partir da próxima gravação de cada boletim.

- **NSS**: `proporDaFichaTecnica` passa a semear as 5 amostras de NSS só quando a ficha
  técnica tem `PAUTA_NSS` ou `TESTE_NSS`. Antes não semeava nenhuma (obrigava a
  acrescentá-las à mão de cada vez) e semear sempre encheria o boletim de linhas que
  ninguém preenche, porque nenhuma das 1346 referências ativas tem `PAUTA_NSS`.

**Ecrã de Parametrização (§11.14) implementado** em `cr_parametrizacao` / `node5664`:
`paginas/modulo-qualidade/lab-cromagem/parametrizacao/`. Os sete dicionários têm a mesma
forma (id + um ou dois campos de texto + `ORDEM` + auditoria), portanto há **um descritor
por dicionário e um único conjunto de operações**, em vez de sete blocos quase iguais.
Soft delete por `ATIVO=0`; `ORDEM` controla a sequência nos dropdowns e, nos patamares,
conta por tipo, porque as grelhas de CaCl₂ e de NSS são independentes. Os `TIPO_ENSAIO` e
`TIPO_PECA` estão marcados `fixo`: renomeiam-se, mas não se apagam nem se acrescentam —
os seis tipos de ensaio estão impressos na legenda do boletim. Backend e serviços já
existiam, portanto não se tocou em Java. Os 5 ficheiros centrais (§5.1) estão ligados,
incluindo o `isLinkActive('/cr_parametrizacao')` no painel Qualidade. `npm run build` OK.

### 11.18 As 24 anomalias de referências — estado da decisão

Levantamento sobre o Access: **nenhuma das 12 referências duplicadas tem um único
boletim** (os 1086 boletins usam 327 referências distintas, e nenhuma é destas). Logo a
consolidação não custa dados e o soft delete é reversível.

| Referência | As duas linhas | Decisão |
|---|---|---|
| `24620079`, `PC9835869480D`, `PC9835869480S`, `PCJK83000A14AA` | idênticas em designação, cliente e ficha técnica | duplicação pura — apagar uma de cada |
| `SD1383C` | "MONO 16V" sem ficha / "MONO 16 V" com `CRO-081` | erro de escrita; fica a que tem ficha |
| `SD9007B` | "MONO 1007" com `CRO-172` / sem ficha | idem |
| `PC4900970` | mesma peça, uma com "(IUPILON…)"; ficha `CRO-246` nas duas | variante de material — fundir |
| `32795192` | `HUF` / `HUF (ROMENIA)`; ficha `CRO-450` nas duas | mesma peça, dois destinos — fundir |
| `628909470R` | "EMBLEME X98"/`TURQUIA` sem ficha / "EMBLEM AV X98"/`RENAULT` com `CRO-1132` | **clientes diferentes — perguntar** |
| `PC990423030RA` | "EDITION ONE RH" `CRO-1165` / "INITIALE PARIS RH" `CRO-1163` | **fichas diferentes: duas peças com o mesmo código — perguntar** |
| `PCPA70105020` | mesma designação, fichas `DAL-018E` / `DAL-018` | **revisão da ficha — confirmar qual vale** |
| ` - - - ` | "DEKORING GRANDE - CR6" / "DEKORING PEQUENA - CR6" | não são duplicados: duas peças **sem código atribuído** |
| *(2 vazias)* | "PEÇA NOVA (MORA)" sem nada / "BADGE L" `JAGUAR` `CRO-640` | idem |

8 dos 24 resolvem-se sem perguntar nada; 3 esperam a Carla; 4 não têm referência atribuída
e ou recebem código ou ficam inativos. **Tem de ficar decidido antes de importar em
produção**, senão o problema vai para lá.

**Ensaio NSS**: o cliente decidiu deixar como está por agora (2026-09-04). Fica
implementado e semeado pela ficha técnica; a série de patamares corrige-se por `UPDATE`
no ecrã de Parametrização quando confirmarem.

### 11.19 Correção ao §11.18 — a contagem de boletins estava errada

**O §11.18 afirma que nenhuma das 12 referências duplicadas tem boletins. É falso.** A
contagem foi feita no Access com `GROUP BY REFERENCIA` na tabela de boletins juntando pelo
**texto** da referência, mas `RESULTADOS CROMAGEM.REFERENCIA` é um **id numérico** para a
tabela de referências. A junção comparava texto com ids, não encontrava nada, e devolvia
zero em todas as linhas. Dei o resultado por bom porque estava limpo; um zero uniforme em
24 registos devia ter levantado a suspeita, sobretudo quando uma das linhas tem 47
boletins. A migração usa um mapa próprio, portanto os dados em SQL estão bem ligados — o
erro foi só na minha verificação.

Números reais, lidos em SQL:

| Referência | IDs (boletins) | Estado corrigido |
|---|---|---|
| `24620079` | **5188 (47 bol.)** / **5336 (3 bol.)** | **as duas em uso.** Fundir obriga a repontar 3 boletins — não é soft delete, é decisão do laboratório |
| `628909470R` | 5251 (2 bol., `RENAULT`, `CRO-1132`) / 4262 (0, `TURQUIA`, sem ficha) | continua a precisar da Carla: os clientes são diferentes, pode ser entrada legítima |
| `PC9835869480D` | 4843 / 4882, 0 boletins | **não são duplicados**: `CAVIDADES` `2` vs `2+2` |
| `PC9835869480S` | 4842 / 4883, 0 boletins | idem, `2` vs `2+2` |
| `PCJK83000A14AA` | 4646 / 4657, 0 boletins | **duplicado verificado**: as 27 colunas de negócio batem, só difere o `ID_ACCESS_LEGADO` |
| `32795192` | 4438 / 4416, 0 boletins | `HUF` vs `HUF (ROMENIA)` e designação mais específica numa — fundir é escolher campos, não apagar |
| `PC4900970` | 4474 / 3966, 0 boletins | variante de material no nome — idem |
| `PC990423030RA` | 5320 / 5326, 0 boletins | fichas `CRO-1163` / `CRO-1165` — duas peças |
| `PCPA70105020` | 4352 / 4149, 0 boletins | fichas `DAL-018` / `DAL-018E` |
| ` - - - ` | 4516 / 4517, 0 boletins | duas peças sem código |
| `SD1383C`, `SD9007B` | — | **já não são duplicados em SQL**; a migração consolidou-os ou uma das linhas não passou |

**Comparação campo a campo antes de apagar** é o que fez a diferença: os dois pares que eu
tinha classificado como "duplicação pura" pelas cinco colunas visíveis na listagem diferem
em `CAVIDADES`, que é o número de cavidades do molde — especificação, não ruído.

**Aplicado:** só `ID_REFERENCIA = 4657` (`PCJK83000A14AA`) ficou `ATIVO=0`, com um
`NOT EXISTS` sobre `QUA_CR_MOV_RELATORIO` na própria condição do `UPDATE`, para não
depender da contagem que já me tinha enganado uma vez. Reversível: `ATIVO=1`.

**Tudo o resto passa para o laboratório.** O conjunto que eu podia resolver sozinho era 1
registo, não 8: de cada vez que verifiquei melhor, encolheu (8 → 4 → 1). O caso a decidir
primeiro é o `24620079`, porque é o único em que fundir mexe em boletins já registados.

### 11.20 CaCl₂ verificado — e o mesmo erro, dentro do verificador

O `Partes()/Parte()` é usado em **dois** sítios no import: nas espessuras
(`:1091-1096`) e nos **pesos e cavidades do CaCl₂** (`:1256-1257`). O §11.13 só provou o
primeiro, portanto metade do erro ficava por verificar. O verificador passa a cobrir os dois.

**Os dados do CaCl₂ estão corretos.** Confirmado registo a registo antes de qualquer
conclusão agregada:

| Access | SQL |
|---|---|
| `'28,13g (143)'` + cavidade `'1/2'` | duas amostras de 28,13 nas cavidades 1 e 2 |
| `'16.58(251)/17.06(201)'` | duas amostras, 16,58 e 17,06 |
| `'16.91(361)'` + cavidade `'-/-'` | 16,91 difundido por duas amostras |

Agregados: pesos 566 vs 566, soma 17 978,77 vs 17 978,77, mín. 1,83, máx. 590,86;
cavidades 559 vs 559.

**A primeira versão do verificador acusou falsamente o CaCl₂, por duas razões — as duas
minhas:**

1. Contava ocorrências no campo em vez de amostras, ignorando a **difusão**: um slot com
   um peso só e duas cavidades gera duas amostras com o mesmo peso. Daí "7 pesos e 216
   cavidades a mais".
2. Pior, e mais instrutivo: o `PedacosDoCampo` fazia `return @($s)` **sem a vírgula**. O
   PowerShell desenrolou o array de um elemento numa string, o `PedacoNoIndice` passou a
   indexar caracteres, e `'13.12(126)'` virou `'1'`. **É exactamente o erro que este
   script existe para apanhar, reproduzido dentro dele.** Sintoma: mínimo 1 e máximo 189
   em vez de 1,83 e 590,86 — valores plausíveis, que é o que torna este erro perigoso.

O `Parte()` do import tem o guarda `if ($partes -is [string])`; o verificador não tinha.
Agora tem, e o `[Math]::Max` usa `@($pp).Count`, porque `.Count` numa string dá 1 e
esconde o problema.

O que evitou uma conclusão errada foi comparar **registo a registo** um caso concreto
antes de acreditar no agregado. O agregado dizia "os dados estão mal"; o caso concreto
mostrou que os dados estavam bem e o medidor mal. As contagens 559 vs 566 reconciliam-se
exactamente com os 171,15 dos 7 valores difundidos — quando os números fecham à unidade,
é sinal de que o modelo está certo.

### 11.21 `cr_boletim_pag3` nunca foi compilado

Em `C:\sgiid\relatorios\jasperfiles\qualidade\` existem `cr_boletim_pag1.jasper` e
`cr_boletim_pag2.jasper` (11:51 de 2026-09-04), mas **não existe `cr_boletim_pag3.jasper`**,
apesar de os três `.jrxml` terem sido escritos às 11:50. O `.jasper` é o resultado da
compilação na primeira utilização, portanto a página 3 não foi gerada depois da correção
do layout — e é justamente aquela onde o `<group name="TIPO_CORR">` teve de passar para
antes do `<title>`. **Imprimir a página 3 do boletim `2181` é o teste que falta.**

### 11.22 Migração verificada de ponta a ponta (2026-09-04)

`sql\QUA_CROMAGEM_VERIFICA_ESPESSURAS.ps1` cobre agora os três blocos com parsing de
risco. Todos passam contra o Access:

```
Espessuras   Cu n 3100 soma 119753,20 | Ni n 3100 soma 79344,55 | Cr n 2923 soma 909,11
CaCl2        pesos n 566 soma 17978,77 (min 1,83 max 590,86) | cavidades n 559
Condicoes    linhas n 5760 | corrente n 5760 soma 19196,54 (min 0 max 25)
Identificacao da peca  3691, nenhuma truncada a 1 caractere
```

Contagens de cabeçalho, todas exactas: relatórios **1086**, referências **1423**,
condições **970**, espessuras **1063**, pautas **501**, corrosão **127**, fotografias
**833** (140,7 MB em base64).

**As 4 divergências das condições eram todas do verificador, não do import:**
`',175'` e `',275'` são correntes sem o zero à esquerda que a regex ignorava por exigir
dígito no início — e somavam exactamente os 0,45 A que faltavam; `TEMPO='9'` é um número
isolado num campo h:m:s, que o import rejeita com razão porque o .NET o leria como 9 dias;
e um travessão longo a que faltava o escape na sentinela. O padrão do dia repetiu-se três
vezes: **o agregado acusava os dados, o caso concreto absolvia-os e condenava o medidor.**

**Falta verificar as pautas de ensaio** — os pesos e cavidades por leitura (`Peso_11`,
`Peso_111`, …) usam o `ToDec`, mas não o `Partes/Parte`, portanto o risco é menor; a
estrutura de três níveis com repetição de dígito no sufixo torna a comparação mais
trabalhosa. Contagens actuais em SQL: 1073 testes, 3327 leituras (3203 com resultado).

**O `.war` já está redeployado** — o `getQUA_CR_MOV_RELATORIO_SEGUIMENTO` responde 200.
**As três páginas do boletim compilam** — `cr_boletim_pag3.jasper` foi gerado às 15:54.

**Estado: a migração em testes está provada.** O que falta é operacional — copiar os
`.jrxml` para o servidor, decidir as referências duplicadas (o cliente ficou de as alterar
ele mesmo) e correr a importação em produção com
`-SqlServer 192.168.40.101,1433 -EmProducao`, seguida do verificador apontado a produção.

### 11.23 Pautas de ensaio verificadas — a migração está completa

Último bloco em falta, agora dentro do verificador. Estrutura de três níveis, com o dígito
do teste repetido k+1 vezes no sufixo da coluna (`Peso_11`, `Peso_111`, `Peso_1111`):

```
Testes         n 1073 vs 1073
Leituras       n 3327 vs 3327
Com resultado  n 3203 vs 3203
Pesos          n 3202 vs 3201  soma 110199,66 vs 110178,43
```

A diferença é **um** peso e 21,23 — exactamente o `valor-malformado [Peso_1111] '21,23 869)'`
do relatório de anomalias (um parêntesis perdido). O import recusou-se a adivinhar e guardou
o texto original: a leitura 5556 tem `PESO = NULL` e
`OBSERVACOES = "Alg.'s P.C.; emp letra\"S\". | Peso: 21,23 869)"`. Nada se perdeu, e alguém
com o boletim à frente pode decidir se são 21,23.

**O teste no script não é uma tolerância cega:** conta as leituras com `PESO IS NULL` e
`OBSERVACOES LIKE '%Peso:%'` e só aceita a diferença se cada peso ausente tiver mesmo o
texto guardado. Uma tolerância fixa deixaria passar uma perda real.

**Estado final da verificação** — `sql\QUA_CROMAGEM_VERIFICA_ESPESSURAS.ps1` cobre os quatro
blocos com parsing de risco e passa todos. Correr contra produção depois da importação:

```powershell
& "C:\work\Workspace\sgiid\sql\QUA_CROMAGEM_VERIFICA_ESPESSURAS.ps1" `
    -SqlServer "192.168.40.101,1433" -SqlDb SGIID -AutWindows
```

### 11.24 Sequência para produção

**Produção tem 0 tabelas `QUA_CR_*`** (verificado a 2026-09-04 em `192.168.40.101/SGIID`,
486 tabelas no total), portanto o DDL é o passo zero — sem ele o import falha na primeira
tabela.

0. **DDL**: correr `sql\QUA_CROMAGEM_MODULO_COMPLETO.sql` em `192.168.40.101 / SGIID`
   (idempotente).
1. **Ensaio a seco** — lê, transforma, imprime anomalias, não escreve:
   ```powershell
   & "...\QUA_CROMAGEM_IMPORT_ACCESS.ps1" -SqlServer "192.168.40.101,1433" `
       -SqlDb SGIID -AutWindows -EmProducao -DryRun
   ```
   O `-EmProducao` é preciso mesmo aqui, porque a ligação abre-se para resolver os
   mapeamentos. **É o passo a ler com atenção:** controladoras, equipamentos (por
   `COD_INTERNO`) e linhas são resolvidos contra a base de destino, e os IDs de produção
   não são os de testes. Um equipamento ou utilizador em falta aparece como anomalia aqui.
2. **Import** — o mesmo sem `-DryRun`.
3. **Verificar** — o comando do §11.23.

Avisos:

- **Nunca `-Reimportar` numa primeira passagem em produção.** É a flag destrutiva (apaga
  tudo o que tenha `ID_ACCESS_LEGADO`); numa base vazia não serve para nada e foi o que
  reatribuiu os IDENTITY em testes, invalidando os IDs que já tinham sido usados a testar.
- As fotografias são o passo longo (250 MB a ler do `.accdb`); podem ir à parte com
  `-Fases dicionarios,conjequip,referencias,relatorios` e depois `-Fases fotografias`.
- O `webUrl.ts` do frontend aponta para `192.168.40.126`; o build de produção precisa do
  bloco do `101` descomentado.
