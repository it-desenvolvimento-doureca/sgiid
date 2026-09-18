-- ============================================================
-- MODULO QUALIDADE > LABORATORIO CROMAGEM (QUA_CR)
-- Script unico e consolidado - estado final do modelo
-- ============================================================
-- Migrado de: "FAMILIA 5_2026_Tabelas.accdb" (dados) +
--             "FAMILIA 5_2026 - Carla.accdb" (forms/relatorios/consultas)
--             P:\Qualidade\3_BASES DE DADOS ACCESS\Laboratorio\FAMILIA 5\2026\
--
-- Idempotente: pode ser corrido as vezes que forem precisas.
--
-- Plano: docs\PLANO_QUA_CROMAGEM_2026-09-02.md
--
-- Decisoes de modelacao que se afastam do Access (e porque):
--
--  1. As "colunas por amostra" do Access viram LINHAS. O Access tem 5 colunas
--     de amostra de espessura, mas a ficha tecnica pede 6 amostras em 1103 das
--     1423 referencias e ate 15 em 9 delas - metade das medicoes nao tinha
--     onde ser registada. Aqui o numero de amostras e livre.
--
--  2. Os trios de booleanos _OK / _OKRES / _NOK viram UMA coluna RESULTADO
--     com dominio 'OK' | 'OK_COND' | 'NOK'. NULL = ainda nao avaliado, que e
--     um estado real: 287 das 1063 linhas de espessuras tinham as tres flags
--     a False. Isto elimina as cadeias de IIf() das consultas Access.
--
--     OK_COND e o "NAO OK COM RESERVAS" do formulario. A definicao esta
--     impressa no proprio ecra, no grupo PARECER FINAL DO LABORATORIO:
--       "Sempre que algum dos valores mencionados acima nao cumpra com os
--        requisitos do cliente, pode ser validado, caso se obtenham
--        resultados OK nos restantes testes especificados ou em testemunhos
--        de testes realizados."
--     Ou seja: reprovou num valor, mas foi validado pelos restantes. Nao e
--     um "quase OK" - e uma decisao explicita do laboratorio.
--
--  3. Os grupos de booleanos exclusivos viram FK. Verificado nos dados: 1337
--     das 1346 referencias ativas tinham exatamente uma flag de acabamento
--     ativa, 1309 exatamente uma de superficie. Sao dicionarios, nao bits.
--
--  4. Os patamares de corrosao sao DADOS (QUA_CR_DIC_PATAMAR), nao nomes de
--     coluna. No Access o patamar estava no nome da coluna, e por isso o
--     formulario passou a rotular 250/500/750/1000 horas enquanto as colunas
--     continuaram DIA200HORAS_NSS / DIA500 / DIA800 / DIA1000. Assim,
--     renomear um patamar e um UPDATE e acrescentar outro e um INSERT.
--
--  5. A "TABELA EQUIPAMENTOS" do Access nao era uma lista de equipamentos:
--     das 47 linhas, 24 eram combinacoes ("Estufa (EQ39) + Estufa Fria
--     (EQ40) + Q-FOG (EQ622)") sobre apenas 25 equipamentos reais. Fica
--     CONJ_EQUIP + CONJ_EQUIP_LIN, com as linhas a apontar para os
--     equipamentos de Meios de Controlo (QUA_MC_EQUIPAMENTOS).
--
--  6. Cabecalho e linhas separados em cada bloco de ensaio. As tabelas-filhas
--     do Access nao eram 1:1 como o formulario fazia crer: 6 relatorios tinham
--     mais de uma linha de condicoes, 2 de espessuras e 2 de pautas, e o
--     formulario mostrava so a primeira. Sao medicoes repetidas legitimas.
--
-- Auditoria: convencao nova (UTZ_CRIA/DATA_CRIA, UTZ_MODIF/DATA_MODIF,
-- UTZ_ANULA/DATA_ANULA, ATIVO), a mesma de QUA_MC_* e QUA_EPI_*.
-- FKs por comentario + indice, como no resto do repo.
--
-- ID_ACCESS_LEGADO guarda a chave da tabela Access de origem, para poder
-- reconciliar a migracao. Nao usar em logica de negocio.
-- ============================================================


-- ============================================================
-- 1. DICIONARIOS DE CLASSIFICACAO
-- ============================================================

-- Tipo de ensaio. Codigos e designacoes tal como o formulario Access os
-- lista ao lado do campo (IIf de Consulta_ID127_06_PAG1).
IF OBJECT_ID('QUA_CR_DIC_TIPO_ENSAIO', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_TIPO_ENSAIO (
    ID_TIPO_ENSAIO      INT IDENTITY(1,1) PRIMARY KEY,
    CODIGO              NVARCHAR(10),
    DESCRICAO           NVARCHAR(100),
    ORDEM               INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Tipo de peca. Substitui as 3 colunas NAO AUTOMOVEL / AUTOMOVEL INTERIOR /
-- AUTOMOVEL EXTERIOR, que no Access guardavam a norma do cliente na coluna
-- correspondente ao tipo (o tipo estava implicito em qual delas tinha valor).
IF OBJECT_ID('QUA_CR_DIC_TIPO_PECA', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_TIPO_PECA (
    ID_TIPO_PECA        INT IDENTITY(1,1) PRIMARY KEY,
    DESCRICAO           NVARCHAR(100),
    ORDEM               INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Tipo de superficie. Substitui Barra / Revisao / Esniagem / Pintura.
IF OBJECT_ID('QUA_CR_DIC_TIPO_SUPERFICIE', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_TIPO_SUPERFICIE (
    ID_TIPO_SUPERFICIE  INT IDENTITY(1,1) PRIMARY KEY,
    DESCRICAO           NVARCHAR(100),
    ORDEM               INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- ASPECTO. Substitui SAT F / SAT I / BR HEX / BR TRI / GR BR / GR SAT I /
-- GR SAT F / FUM / SM5077.
--
-- Atencao ao vocabulario do laboratorio, que nao e obvio:
--   ASPECTO    = este dicionario ("TRIVALENTE BRILHANTE", "SATINADO ICE", ...)
--   ACABAMENTO = MICROPOROSO / MICROFISSURADO, calculado dos dois BIT da
--                referencia (pode ser "MICROPOROSO/MICROFISSURADO" quando os
--                dois estao ativos), e por isso nao tem tabela.
-- E como o formulario e as consultas Access os designam
-- (Consulta_ID127_06_PAG1: AS ACABAMENTO vs AS ASPECTO/ASPECTO_1/ASPECTO_2 -
-- os 3 aliases de ASPECTO sao so um contorno ao limite de IIf encadeados do
-- Access, nao tres campos diferentes).
IF OBJECT_ID('QUA_CR_DIC_ASPECTO', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_ASPECTO (
    ID_ASPECTO          INT IDENTITY(1,1) PRIMARY KEY,
    CODIGO              NVARCHAR(20),
    DESCRICAO           NVARCHAR(100),
    ORDEM               INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Resultados das pautas de ensaio (ex-"T - TABELA RESULTADOS CR APOIO").
-- Os IDs sao os do Access, preservados de proposito para a migracao poder
-- mapear ID -> ID sem tabela de traducao.
IF OBJECT_ID('QUA_CR_DIC_RESULTADO', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_RESULTADO (
    ID_RESULTADO        INT IDENTITY(1,1) PRIMARY KEY,
    DESIGNACAO          NVARCHAR(255),
    ORDEM               INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Local de producao. Distingue a cromagem feita na Doureca da feita fora:
-- e o filtro do relatorio "EVOLUCAO DE ENSAIOS - DOURECA"
-- (Consulta_ID_EVOLUCAO ENSAIOS: WHERE Local de Producao = 'Doureca').
IF OBJECT_ID('QUA_CR_DIC_LOCAL_PRODUCAO', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_LOCAL_PRODUCAO (
    ID_LOCAL_PRODUCAO   INT IDENTITY(1,1) PRIMARY KEY,
    DESIGNACAO          NVARCHAR(255),
    ORDEM               INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Patamares dos ensaios de corrosao (ver nota 4 no topo).
-- TIPO: 'NSS' | 'CACL2'
IF OBJECT_ID('QUA_CR_DIC_PATAMAR', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_PATAMAR (
    ID_PATAMAR          INT IDENTITY(1,1) PRIMARY KEY,
    TIPO                NVARCHAR(10),
    DESIGNACAO          NVARCHAR(50),
    ORDEM               INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO


-- ============================================================
-- 2. CONJUNTOS DE EQUIPAMENTO
-- ============================================================
-- Ver nota 5 no topo. Um conjunto e o que o operador escolhe no ecra
-- ("Estufa Altas Temperaturas (EQ39) + Estufa Fria (EQ40)"); as linhas
-- dizem de que equipamentos de Meios de Controlo ele e feito.

IF OBJECT_ID('QUA_CR_DIC_CONJ_EQUIP', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_CONJ_EQUIP (
    ID_CONJ_EQUIP       INT IDENTITY(1,1) PRIMARY KEY,
    DESIGNACAO          NVARCHAR(500),
    ID_ACCESS_LEGADO    INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- COD_INTERNO guarda o codigo EQ* lido da designacao do Access, para o caso
-- de o equipamento ainda nao existir em Meios de Controlo no momento da
-- migracao (ID_EQUIPAMENTO fica NULL e resolve-se depois pelo codigo).
IF OBJECT_ID('QUA_CR_DIC_CONJ_EQUIP_LIN', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_CONJ_EQUIP_LIN (
    ID_CONJ_EQUIP_LIN   INT IDENTITY(1,1) PRIMARY KEY,
    ID_CONJ_EQUIP       INT,            -- FK -> QUA_CR_DIC_CONJ_EQUIP
    ID_EQUIPAMENTO      INT,            -- FK -> QUA_MC_EQUIPAMENTOS
    COD_INTERNO         NVARCHAR(20),
    ORDEM               INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO


-- ============================================================
-- 3. FICHA TECNICA DA REFERENCIA
-- ============================================================
-- Das 57 colunas do Access, so 3 existem no SILVER (referencia, referencia da
-- peca plastica e cliente). As restantes sao a especificacao contra a qual o
-- laboratorio decide OK/NOK e nao existem em nenhum outro sistema.
--
-- REFERENCIA e texto livre, nao FK para o SILVER: das 1423 referencias, 70
-- tem '/' (compostas, ex. 'PC78663690/PC78663517'), 87 tem espacos e 13 estao
-- duplicadas. A ligacao ao SILVER e opcional, por PROREF_SILVER.
--
-- As espessuras exigidas ficam em texto porque sao intervalos ('0,15-0,30').
IF OBJECT_ID('QUA_CR_DIC_REFERENCIA', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_REFERENCIA (
    ID_REFERENCIA           INT IDENTITY(1,1) PRIMARY KEY,

    -- Identificacao (as 3 primeiras vem do SILVER)
    REFERENCIA              NVARCHAR(50),
    PROREF_SILVER           NVARCHAR(50),   -- SDTPRA.PROREF
    -- A descricao do SILVER fica guardada, como em COM_REFERENCIAS_SILVER e
    -- QUA_EPI_DIC_EPI: assim o ecra mostra-a sem uma chamada ao SILVER por
    -- cada abertura, e continua a mostra-la se o SILVER estiver indisponivel.
    PROREF_SILVER_DESC      NVARCHAR(255),  -- SDTPRA.PRODES1
    REF_PECA_PLASTICA       NVARCHAR(255),
    PROREF_PLASTICA_SILVER  NVARCHAR(50),   -- SDTPRA.PROREF da peca em bruto
    PROREF_PLASTICA_DESC    NVARCHAR(255),
    CLIENTE                 NVARCHAR(150),  -- SDTCLE.ADRNOM
    CLICOD_SILVER           NVARCHAR(20),   -- SDTCLI.CLICOD
    DESIGNACAO              NVARCHAR(255),
    MATERIAL                NVARCHAR(250),
    CAVIDADES               NVARCHAR(255),
    FICHA_TECNICA           NVARCHAR(255),  -- codigo documental, ex. 'CRO-1024'
    CARTELA                 NVARCHAR(255),
    NUM_AMOSTRAS            INT,

    -- Classificacao
    ID_TIPO_PECA            INT,            -- FK -> QUA_CR_DIC_TIPO_PECA
    NORMA_CLIENTE           NVARCHAR(250),
    ID_TIPO_SUPERFICIE      INT,            -- FK -> QUA_CR_DIC_TIPO_SUPERFICIE
    ID_ASPECTO              INT,            -- FK -> QUA_CR_DIC_ASPECTO

    -- Espessuras exigidas (intervalos em texto)
    ESPESSURA_CU            NVARCHAR(250),
    ESPESSURA_NI            NVARCHAR(250),
    ESPESSURA_CR            NVARCHAR(250),

    -- Estrutura da camada
    MICROPOROSO             BIT DEFAULT 0,
    MICROFISSURADO          BIT DEFAULT 0,
    N_EXIG_POROS            NVARCHAR(255),
    N_EXIG_FISSURAS         NVARCHAR(255),

    -- Pautas aplicaveis
    PAUTA_ENSAIO            NVARCHAR(250),
    PAUTA_ESPESSURAS        NVARCHAR(50),
    PAUTA_POROS             NVARCHAR(255),
    PAUTA_STEP              NVARCHAR(255),
    PAUTA_CACL2             NVARCHAR(50),
    PAUTA_NSS               NVARCHAR(255),

    -- Ensaios de corrosao
    TESTE_CACL2             NVARCHAR(50),
    PROC_CACL2              NVARCHAR(MAX),
    TESTE_NSS               NVARCHAR(50),
    PROC_NSS                NVARCHAR(MAX),

    ID_ACCESS_LEGADO        INT,
    UTZ_CRIA                INT, DATA_CRIA   DATETIME,
    UTZ_MODIF               INT, DATA_MODIF  DATETIME,
    UTZ_ANULA               INT, DATA_ANULA  DATETIME,
    ATIVO                   BIT DEFAULT 1
);
GO

-- Testes previstos por referencia. No Access eram 7 pares de colunas
-- (TESTE 1..7 = designacao, TESTE 11..77 = procedimento), com o 7. par sempre
-- vazio. Em linhas nao ha teto: a referencia pode ter os testes que precisar.
IF OBJECT_ID('QUA_CR_DIC_REF_TESTE', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_REF_TESTE (
    ID_REF_TESTE        INT IDENTITY(1,1) PRIMARY KEY,
    ID_REFERENCIA       INT,            -- FK -> QUA_CR_DIC_REFERENCIA
    ORDEM               INT,
    DESIGNACAO          NVARCHAR(255),
    PROCEDIMENTO        NVARCHAR(MAX),
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Anexos da referencia: fotografia da peca, ficha tecnica, cartela.
--
-- No Access a FOTOGRAFIA era um campo Attachment (DAO Type=101) - 833 imagens
-- em 1423 referencias, 249,9 MB, que e praticamente todo o .accdb de 270 MB.
-- Aqui seguem a convencao do SGIID: data URL base64 partido em FICHEIRO_1 +
-- FICHEIRO_2, com a listagem a trazer so metadados e o conteudo a ser lido
-- em lazy load (ver QUA_MC_EQUIPAMENTOS_FICHEIROS).
--
-- Tabela filha e nao coluna na referencia, para que a listagem de referencias
-- nunca arraste os blobs.
--
-- CATEGORIA: 'FOTOGRAFIA' | 'FICHA_TECNICA' | 'CARTELA' | outro
IF OBJECT_ID('QUA_CR_DIC_REFERENCIA_FICHEIROS', 'U') IS NULL
CREATE TABLE QUA_CR_DIC_REFERENCIA_FICHEIROS (
    ID                  INT IDENTITY(1,1) PRIMARY KEY,
    ID_REFERENCIA       INT,            -- FK -> QUA_CR_DIC_REFERENCIA
    NOME                NVARCHAR(255),
    CAMINHO             NVARCHAR(500),
    -- TIPO e a classe usada no preview: 'img' | 'pdf' | 'excel' | 'word' | ext
    TIPO                NVARCHAR(100),
    -- DATATYPE e o mime type original
    DATATYPE            NVARCHAR(255),
    TAMANHO             FLOAT,
    DESCRICAO           NVARCHAR(500),
    CATEGORIA           NVARCHAR(50),
    FICHEIRO_1          NVARCHAR(MAX),
    FICHEIRO_2          NVARCHAR(MAX),
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO


-- ============================================================
-- 4. RELATORIO DE ENSAIO (cabecalho)
-- ============================================================
-- Ex-"T - TABELA RESULTADOS CROMAGEM". O ecra e este cabecalho + 4 tabs.
--
-- ANO e NUM_SEQ existem para a numeracao automatica: N_RELAT_CR e
-- CONCAT(NUM_SEQ, '/', ANO), mas guarda-se tambem em texto porque os 1086
-- registos migrados de 2026 tem de manter o numero exato que ja foi impresso.
--
-- ID_LINHA substitui as duas colunas LINHA 1 / LINHA 2 (mutuamente
-- exclusivas) e aponta para as linhas de cromagem ja existentes.
--
-- FAZ_ESPESSURAS / FAZ_PAUTAS / FAZ_CORROSAO sao os "Testes previstos" do
-- formulario: ativam as tabs e os botoes de impressao PAG. 1/2/3.
IF OBJECT_ID('QUA_CR_MOV_RELATORIO', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_RELATORIO (
    ID_RELATORIO        INT IDENTITY(1,1) PRIMARY KEY,
    N_RELAT_CR          NVARCHAR(20),
    ANO                 INT,
    NUM_SEQ             INT,
    ID_REFERENCIA       INT,            -- FK -> QUA_CR_DIC_REFERENCIA
    ID_TIPO_ENSAIO      INT,            -- FK -> QUA_CR_DIC_TIPO_ENSAIO
    ID_LINHA            INT,            -- FK -> AB_DIC_LINHA.id_LINHA
    ID_LOCAL_PRODUCAO   INT,            -- FK -> QUA_CR_DIC_LOCAL_PRODUCAO
    LOTE                NVARCHAR(30),
    DATA_HORA_PRODUCAO  DATETIME,
    DATA_REGISTO        DATETIME,
    DATA_ENTRADA_LAB    DATE,
    DATA_RECECAO        DATE,
    FAZ_ESPESSURAS      BIT DEFAULT 0,
    FAZ_PAUTAS          BIT DEFAULT 0,
    FAZ_CORROSAO        BIT DEFAULT 0,
    ID_ACCESS_LEGADO    INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO


-- ============================================================
-- 5. TAB CONDICOES (condicoes da linha de cromagem)
-- ============================================================

IF OBJECT_ID('QUA_CR_MOV_CONDICOES_CAB', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_CONDICOES_CAB (
    ID_CONDICOES_CAB    INT IDENTITY(1,1) PRIMARY KEY,
    ID_RELATORIO        INT,            -- FK -> QUA_CR_MOV_RELATORIO
    NUM_PECAS           INT,
    SUPERFICIE          DECIMAL(10,3),
    OBSERVACOES         NVARCHAR(MAX),
    ID_ACCESS_LEGADO    INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Uma linha por banho, em vez das 14 colunas CORR_*/TEMPO_* do Access.
-- BANHO: 'CU' | 'SB' | 'B' | 'SAT' | 'P' | 'CR' | 'PASS'
--
-- No Access CORRENTE e TEMPO eram texto ('1,5' e '00:45:06') e 703 das 970
-- linhas usavam '-' no banho SAT para dizer "nao aplicavel". Aqui NULL diz o
-- mesmo, e os valores sao numericos/temporais de verdade.
IF OBJECT_ID('QUA_CR_MOV_CONDICOES', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_CONDICOES (
    ID_CONDICAO         INT IDENTITY(1,1) PRIMARY KEY,
    ID_CONDICOES_CAB    INT,            -- FK -> QUA_CR_MOV_CONDICOES_CAB
    BANHO               NVARCHAR(10),
    ORDEM               INT,
    CORRENTE            DECIMAL(8,3),
    TEMPO               TIME(0),
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO


-- ============================================================
-- 6. TAB ESPESSURAS / POROS / FISSURAS
-- ============================================================

-- O bloco comum a todas as amostras: poros, fissuras, step, equipamentos,
-- operador e o resultado global do bloco.
IF OBJECT_ID('QUA_CR_MOV_ESPESSURA_CAB', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_ESPESSURA_CAB (
    ID_ESPESSURA_CAB    INT IDENTITY(1,1) PRIMARY KEY,
    ID_RELATORIO        INT,            -- FK -> QUA_CR_MOV_RELATORIO
    DATA_MEDICAO        DATE,
    ID_OPERADOR         INT,            -- FK -> GER_UTILIZADORES.ID_UTILIZADOR

    POROS               DECIMAL(12,3),
    RESULTADO_POROS     NVARCHAR(10),   -- OK | OK_COND | NOK | NULL
    FISSURAS            NVARCHAR(255),
    RESULTADO_FISSURAS  NVARCHAR(10),

    TEM_STEP            BIT DEFAULT 0,
    STEP_ESP_1          NVARCHAR(50),
    STEP_ESP_2_1        NVARCHAR(50),
    STEP_ESP_3_2        NVARCHAR(50),
    STEP_POT_1          NVARCHAR(50),
    STEP_POT_2_1        NVARCHAR(50),
    STEP_POT_3_2        NVARCHAR(50),
    STEP_POT_4_3        NVARCHAR(50),

    ID_CONJ_EQUIP_ESP   INT,            -- FK -> QUA_CR_DIC_CONJ_EQUIP
    ID_CONJ_EQUIP_POROS INT,            -- FK -> QUA_CR_DIC_CONJ_EQUIP
    ID_CONJ_EQUIP_STEP  INT,            -- FK -> QUA_CR_DIC_CONJ_EQUIP

    RESULTADO_TOTAL     NVARCHAR(10),
    OBSERVACOES         NVARCHAR(MAX),
    ID_ACCESS_LEGADO    INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Uma linha por amostra (ver nota 1 no topo).
-- NUM_AMOSTRA e o numero; o ecra mostra-o como letra (A, B, C, ...) tal como
-- o formulario Access. IDENT_AMOSTRA e o texto livre de identificacao da peca
-- ('Peca N155'), que no Access era a coluna 'Amostra Na'.
IF OBJECT_ID('QUA_CR_MOV_ESPESSURA', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_ESPESSURA (
    ID_ESPESSURA        INT IDENTITY(1,1) PRIMARY KEY,
    ID_ESPESSURA_CAB    INT,            -- FK -> QUA_CR_MOV_ESPESSURA_CAB
    NUM_AMOSTRA         INT,
    IDENT_AMOSTRA       NVARCHAR(100),
    CAVIDADE            NVARCHAR(50),
    PESO                DECIMAL(10,3),
    ESP_CU              DECIMAL(10,3),
    ESP_NI              DECIMAL(10,3),
    ESP_CR              DECIMAL(10,3),
    RESULTADO           NVARCHAR(10),   -- OK | OK_COND | NOK | NULL
    -- No Access as espessuras eram texto e vinham muitas vezes anotadas:
    -- "9.96 (140)", "28.16 (Min)*", "11.21*". O numero passa para as colunas
    -- ESP_*, e a anotacao fica aqui - eram 3683 valores, nao se descartam.
    OBSERVACOES         NVARCHAR(255),
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Instalacoes que ja correram uma versao anterior deste script.
IF COL_LENGTH('QUA_CR_MOV_ESPESSURA', 'OBSERVACOES') IS NULL
    ALTER TABLE QUA_CR_MOV_ESPESSURA ADD OBSERVACOES NVARCHAR(255);
GO
IF COL_LENGTH('QUA_CR_DIC_REFERENCIA', 'PROREF_SILVER_DESC') IS NULL
    ALTER TABLE QUA_CR_DIC_REFERENCIA ADD PROREF_SILVER_DESC NVARCHAR(255);
GO
IF COL_LENGTH('QUA_CR_DIC_REFERENCIA', 'PROREF_PLASTICA_DESC') IS NULL
    ALTER TABLE QUA_CR_DIC_REFERENCIA ADD PROREF_PLASTICA_DESC NVARCHAR(255);
GO


-- ============================================================
-- 7. TAB PAUTAS DE ENSAIO
-- ============================================================
-- Tres niveis, porque e assim que os dados estao: o equipamento e por teste,
-- mas o peso, a cavidade e o resultado sao por leitura (no Access, Peso_11,
-- Peso_111, Peso_1111, Peso_11111 = 4 leituras do teste 1, mas Equip_11 = 1).

IF OBJECT_ID('QUA_CR_MOV_ENSAIO_CAB', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_ENSAIO_CAB (
    ID_ENSAIO_CAB       INT IDENTITY(1,1) PRIMARY KEY,
    ID_RELATORIO        INT,            -- FK -> QUA_CR_MOV_RELATORIO
    DATA_TESTES         DATE,
    ID_OPERADOR         INT,            -- FK -> GER_UTILIZADORES.ID_UTILIZADOR
    RESULTADO_TOTAL     NVARCHAR(10),   -- OK | OK_COND | NOK | NULL
    OBSERVACOES         NVARCHAR(MAX),
    ID_ACCESS_LEGADO    INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Um teste do relatorio. ID_REF_TESTE liga ao teste da ficha tecnica; a
-- DESIGNACAO fica copiada para o relatorio nao mudar de conteudo se a ficha
-- tecnica for editada depois de o ensaio estar feito.
IF OBJECT_ID('QUA_CR_MOV_ENSAIO_TESTE', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_ENSAIO_TESTE (
    ID_ENSAIO_TESTE     INT IDENTITY(1,1) PRIMARY KEY,
    ID_ENSAIO_CAB       INT,            -- FK -> QUA_CR_MOV_ENSAIO_CAB
    NUM_TESTE           INT,
    ID_REF_TESTE        INT,            -- FK -> QUA_CR_DIC_REF_TESTE
    DESIGNACAO          NVARCHAR(255),
    ID_CONJ_EQUIP       INT,            -- FK -> QUA_CR_DIC_CONJ_EQUIP
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

IF OBJECT_ID('QUA_CR_MOV_ENSAIO_LEITURA', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_ENSAIO_LEITURA (
    ID_ENSAIO_LEITURA   INT IDENTITY(1,1) PRIMARY KEY,
    ID_ENSAIO_TESTE     INT,            -- FK -> QUA_CR_MOV_ENSAIO_TESTE
    NUM_LEITURA         INT,
    CAVIDADE            NVARCHAR(50),
    PESO                DECIMAL(10,3),
    ID_RESULTADO        INT,            -- FK -> QUA_CR_DIC_RESULTADO
    OBSERVACOES         NVARCHAR(255),
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO


-- ============================================================
-- 8. TAB NSS / CACL2 (ensaios de corrosao)
-- ============================================================
-- Tambem tres niveis, e pela mesma razao: no Access o peso e a cavidade sao
-- por amostra (PESO1_CACL..PESO4_CACL, PESO1_NSS..PESO5_NSS) e o resultado e
-- por amostra x patamar (PESO1_48H_OK, CL1_NSS_200H).
--
-- Nota: as 7 colunas de data dos patamares CaCl2 (DIA24HORAS .. DIA28DIAS)
-- nao existem aqui porque estavam vazias nos 126 registos - nunca se
-- registou a data de leitura de cada patamar, so o OK/NOK.

IF OBJECT_ID('QUA_CR_MOV_CORROSAO_CAB', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_CORROSAO_CAB (
    ID_CORROSAO_CAB     INT IDENTITY(1,1) PRIMARY KEY,
    ID_RELATORIO        INT,            -- FK -> QUA_CR_MOV_RELATORIO
    ID_OPERADOR         INT,            -- FK -> GER_UTILIZADORES.ID_UTILIZADOR
    DATA_CACL2          DATE,

    -- Norma do ensaio CaCl2 (checkboxes "TIPO DE CACL2" no formulario)
    CACL2_RENAULT       BIT DEFAULT 0,
    CACL2_PEUGEOT       BIT DEFAULT 0,
    CACL2_JAPAO         BIT DEFAULT 0,

    ID_CONJ_EQUIP_CACL2 INT,            -- FK -> QUA_CR_DIC_CONJ_EQUIP
    ID_CONJ_EQUIP_NSS   INT,            -- FK -> QUA_CR_DIC_CONJ_EQUIP

    RESULTADO_TOTAL_CACL2 NVARCHAR(10), -- OK | OK_COND | NOK | NULL
    RESULTADO_TOTAL_NSS   NVARCHAR(10),
    OBSERVACOES         NVARCHAR(MAX),
    ID_ACCESS_LEGADO    INT,
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- TIPO: 'CACL2' | 'NSS' - a mesma amostra pode ser ensaiada nos dois.
IF OBJECT_ID('QUA_CR_MOV_CORROSAO_AMOSTRA', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_CORROSAO_AMOSTRA (
    ID_CORROSAO_AMOSTRA INT IDENTITY(1,1) PRIMARY KEY,
    ID_CORROSAO_CAB     INT,            -- FK -> QUA_CR_MOV_CORROSAO_CAB
    TIPO                NVARCHAR(10),
    NUM_AMOSTRA         INT,
    CAVIDADE            NVARCHAR(50),
    PESO                DECIMAL(10,3),
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO

-- Uma leitura = uma amostra num patamar.
-- RESULTADO para o CaCl2 (OK/NOK); CLASSIFICACAO para o NSS (o valor 'CL').
IF OBJECT_ID('QUA_CR_MOV_CORROSAO_LEITURA', 'U') IS NULL
CREATE TABLE QUA_CR_MOV_CORROSAO_LEITURA (
    ID_CORROSAO_LEITURA INT IDENTITY(1,1) PRIMARY KEY,
    ID_CORROSAO_AMOSTRA INT,            -- FK -> QUA_CR_MOV_CORROSAO_AMOSTRA
    ID_PATAMAR          INT,            -- FK -> QUA_CR_DIC_PATAMAR
    RESULTADO           NVARCHAR(10),   -- OK | OK_COND | NOK | NULL
    CLASSIFICACAO       NVARCHAR(50),
    OBSERVACOES         NVARCHAR(255),
    UTZ_CRIA            INT, DATA_CRIA   DATETIME,
    UTZ_MODIF           INT, DATA_MODIF  DATETIME,
    UTZ_ANULA           INT, DATA_ANULA  DATETIME,
    ATIVO               BIT DEFAULT 1
);
GO


-- ============================================================
-- 9. INDICES
-- ============================================================

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_REFERENCIA_REF')
    CREATE INDEX IX_QUA_CR_REFERENCIA_REF ON QUA_CR_DIC_REFERENCIA (REFERENCIA);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_REFERENCIA_PROREF')
    CREATE INDEX IX_QUA_CR_REFERENCIA_PROREF ON QUA_CR_DIC_REFERENCIA (PROREF_SILVER);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_REFERENCIA_LEGADO')
    CREATE INDEX IX_QUA_CR_REFERENCIA_LEGADO ON QUA_CR_DIC_REFERENCIA (ID_ACCESS_LEGADO);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_REF_TESTE_REF')
    CREATE INDEX IX_QUA_CR_REF_TESTE_REF ON QUA_CR_DIC_REF_TESTE (ID_REFERENCIA);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_REF_FICH_REF')
    CREATE INDEX IX_QUA_CR_REF_FICH_REF ON QUA_CR_DIC_REFERENCIA_FICHEIROS (ID_REFERENCIA);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_CONJ_EQUIP_LIN_CONJ')
    CREATE INDEX IX_QUA_CR_CONJ_EQUIP_LIN_CONJ ON QUA_CR_DIC_CONJ_EQUIP_LIN (ID_CONJ_EQUIP);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_CONJ_EQUIP_LIN_EQUIP')
    CREATE INDEX IX_QUA_CR_CONJ_EQUIP_LIN_EQUIP ON QUA_CR_DIC_CONJ_EQUIP_LIN (ID_EQUIPAMENTO);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_PATAMAR_TIPO')
    CREATE INDEX IX_QUA_CR_PATAMAR_TIPO ON QUA_CR_DIC_PATAMAR (TIPO, ORDEM);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_RELATORIO_REF')
    CREATE INDEX IX_QUA_CR_RELATORIO_REF ON QUA_CR_MOV_RELATORIO (ID_REFERENCIA);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_RELATORIO_DATA')
    CREATE INDEX IX_QUA_CR_RELATORIO_DATA ON QUA_CR_MOV_RELATORIO (DATA_HORA_PRODUCAO);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_RELATORIO_ANO')
    CREATE INDEX IX_QUA_CR_RELATORIO_ANO ON QUA_CR_MOV_RELATORIO (ANO, NUM_SEQ);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_RELATORIO_LEGADO')
    CREATE INDEX IX_QUA_CR_RELATORIO_LEGADO ON QUA_CR_MOV_RELATORIO (ID_ACCESS_LEGADO);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_COND_CAB_REL')
    CREATE INDEX IX_QUA_CR_COND_CAB_REL ON QUA_CR_MOV_CONDICOES_CAB (ID_RELATORIO);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_COND_CAB')
    CREATE INDEX IX_QUA_CR_COND_CAB ON QUA_CR_MOV_CONDICOES (ID_CONDICOES_CAB);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_ESP_CAB_REL')
    CREATE INDEX IX_QUA_CR_ESP_CAB_REL ON QUA_CR_MOV_ESPESSURA_CAB (ID_RELATORIO);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_ESP_CAB')
    CREATE INDEX IX_QUA_CR_ESP_CAB ON QUA_CR_MOV_ESPESSURA (ID_ESPESSURA_CAB);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_ENSAIO_CAB_REL')
    CREATE INDEX IX_QUA_CR_ENSAIO_CAB_REL ON QUA_CR_MOV_ENSAIO_CAB (ID_RELATORIO);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_ENSAIO_TESTE_CAB')
    CREATE INDEX IX_QUA_CR_ENSAIO_TESTE_CAB ON QUA_CR_MOV_ENSAIO_TESTE (ID_ENSAIO_CAB);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_ENSAIO_LEIT_TESTE')
    CREATE INDEX IX_QUA_CR_ENSAIO_LEIT_TESTE ON QUA_CR_MOV_ENSAIO_LEITURA (ID_ENSAIO_TESTE);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_CORR_CAB_REL')
    CREATE INDEX IX_QUA_CR_CORR_CAB_REL ON QUA_CR_MOV_CORROSAO_CAB (ID_RELATORIO);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_CORR_AMOSTRA_CAB')
    CREATE INDEX IX_QUA_CR_CORR_AMOSTRA_CAB ON QUA_CR_MOV_CORROSAO_AMOSTRA (ID_CORROSAO_CAB);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_CR_CORR_LEIT_AMOSTRA')
    CREATE INDEX IX_QUA_CR_CORR_LEIT_AMOSTRA ON QUA_CR_MOV_CORROSAO_LEITURA (ID_CORROSAO_AMOSTRA);
GO


-- ============================================================
-- 10. SEEDS
-- ============================================================

-- Tipos de ensaio (formulario "ENSAIOS - PECAS CROMADAS")
INSERT INTO QUA_CR_DIC_TIPO_ENSAIO (CODIGO, DESCRICAO, ORDEM, DATA_CRIA, ATIVO)
SELECT v.CODIGO, v.DESCRICAO, v.ORDEM, GETDATE(), 1
FROM (VALUES
    ('P.N.', 'Producao',          1),
    ('E',    'Ensaios',           2),
    ('A',    'Amostras',          3),
    ('A.I.', 'Amostras Iniciais', 4),
    ('P.S.', 'Pre-Series',        5),
    ('P',    'Prototipos',        6)
) AS v(CODIGO, DESCRICAO, ORDEM)
WHERE NOT EXISTS (SELECT 1 FROM QUA_CR_DIC_TIPO_ENSAIO t WHERE t.CODIGO = v.CODIGO);
GO

-- Tipos de peca
INSERT INTO QUA_CR_DIC_TIPO_PECA (DESCRICAO, ORDEM, DATA_CRIA, ATIVO)
SELECT v.DESCRICAO, v.ORDEM, GETDATE(), 1
FROM (VALUES
    ('Automovel Exterior', 1),
    ('Automovel Interior', 2),
    ('Nao Automovel',      3)
) AS v(DESCRICAO, ORDEM)
WHERE NOT EXISTS (SELECT 1 FROM QUA_CR_DIC_TIPO_PECA t WHERE t.DESCRICAO = v.DESCRICAO);
GO

-- Tipos de superficie
INSERT INTO QUA_CR_DIC_TIPO_SUPERFICIE (DESCRICAO, ORDEM, DATA_CRIA, ATIVO)
SELECT v.DESCRICAO, v.ORDEM, GETDATE(), 1
FROM (VALUES
    ('Revisao',  1),
    ('Esniagem', 2),
    ('Barra',    3),
    ('Pintura',  4)
) AS v(DESCRICAO, ORDEM)
WHERE NOT EXISTS (SELECT 1 FROM QUA_CR_DIC_TIPO_SUPERFICIE t WHERE t.DESCRICAO = v.DESCRICAO);
GO

-- Aspectos. As DESCRICAO sao literalmente as do laboratorio, extraidas dos
-- IIf de Consulta_ID127_06_PAG1 - nao inventar sinonimos, e este o texto que
-- sai impresso no boletim. ORDEM por frequencia de uso nas 1346 referencias
-- ativas (BR TRI 30%, BR HEX 23%, SAT I 21%, SAT F 15%, FUM 8%, resto <=1%).
INSERT INTO QUA_CR_DIC_ASPECTO (CODIGO, DESCRICAO, ORDEM, DATA_CRIA, ATIVO)
SELECT v.CODIGO, v.DESCRICAO, v.ORDEM, GETDATE(), 1
FROM (VALUES
    ('BR TRI',   'TRIVALENTE BRILHANTE',    1),
    ('BR HEX',   'HEXAVALENTE BRILHANTE',   2),
    ('SAT I',    'SATINADO ICE',            3),
    ('SAT F',    'SATINADO FORTE',          4),
    ('FUM',      'FUME',                    5),
    ('GR BR',    'GRAPHITE BRILHANTE',      6),
    ('GR SAT I', 'GRAPHITE SATINADO ICE',   7),
    ('GR SAT F', 'GRAPHITE SATINADO FORTE', 8),
    ('SM5077',   'SM5077',                  9)
) AS v(CODIGO, DESCRICAO, ORDEM)
WHERE NOT EXISTS (SELECT 1 FROM QUA_CR_DIC_ASPECTO t WHERE t.CODIGO = v.CODIGO);
GO

-- Patamares de corrosao.
--
-- CaCl2: a serie do formulario e das colunas coincidem.
--
-- NSS: as designacoes abaixo sao as dos NOMES DAS COLUNAS do Access
-- (DIA200HORAS_NSS / DIA500 / DIA800 / DIA1000), que e o que esta gravado.
-- O formulario rotulava 250 / 500 / 750 / 1000 horas, e so 7 dos 126 registos
-- tinham dados (todos no primeiro patamar), portanto os dados nao desempatam.
-- Se a qualidade confirmar que o correto e a serie do formulario, corrige-se
-- com um UPDATE nestas linhas - e por isso que o patamar e dado e nao coluna.
INSERT INTO QUA_CR_DIC_PATAMAR (TIPO, DESIGNACAO, ORDEM, DATA_CRIA, ATIVO)
SELECT v.TIPO, v.DESIGNACAO, v.ORDEM, GETDATE(), 1
FROM (VALUES
    ('CACL2', '24 Horas',   1),
    ('CACL2', '48 Horas',   2),
    ('CACL2', '72 Horas',   3),
    ('CACL2', '96 Horas',   4),
    ('CACL2', '120 Horas',  5),
    ('CACL2', '14 Dias',    6),
    ('CACL2', '28 Dias',    7),
    ('NSS',   '200 Horas',  1),
    ('NSS',   '500 Horas',  2),
    ('NSS',   '800 Horas',  3),
    ('NSS',   '1000 Horas', 4)
) AS v(TIPO, DESIGNACAO, ORDEM)
WHERE NOT EXISTS (
    SELECT 1 FROM QUA_CR_DIC_PATAMAR p
    WHERE p.TIPO = v.TIPO AND p.ORDEM = v.ORDEM
);
GO

-- Resultados das pautas de ensaio. ORDEM = ID do Access, para a migracao
-- poder traduzir os IDs antigos sem tabela auxiliar.
INSERT INTO QUA_CR_DIC_RESULTADO (DESIGNACAO, ORDEM, DATA_CRIA, ATIVO)
SELECT v.DESIGNACAO, v.ORDEM, GETDATE(), 1
FROM (VALUES
    ('OK ciclos',          1),
    ('NOK ciclos',         2),
    ('CL5 - OK ciclos',    3),
    ('CL5 - NOK ciclos',   4),
    ('CL6 - OK ciclos',    5),
    ('CL6 - NOK ciclos',   6),
    ('CL7 - OK ciclos',    7),
    ('CL7 - NOK ciclos',   8),
    ('CL8 - OK ciclos',    9),
    ('CL8 - NOK ciclos',  10),
    ('CL9 - OK ciclos',   11),
    ('CL9 - NOK ciclos',  12),
    ('CL10 - OK ciclos',  13),
    ('CL10 - NOK ciclos', 14),
    ('CL5',               15),
    ('CL6',               16),
    ('CL7',               17),
    ('CL8',               18),
    ('CL9',               19),
    ('CL10',              20),
    ('OK',                21),
    ('NAO OK',            22)
) AS v(DESIGNACAO, ORDEM)
WHERE NOT EXISTS (SELECT 1 FROM QUA_CR_DIC_RESULTADO r WHERE r.ORDEM = v.ORDEM);
GO

-- Local de producao. 'Doureca' e o valor que o relatorio de evolucao filtra;
-- os restantes locais vem do export do Access (a tabela original nao estava
-- no snapshot recebido).
INSERT INTO QUA_CR_DIC_LOCAL_PRODUCAO (DESIGNACAO, ORDEM, DATA_CRIA, ATIVO)
SELECT v.DESIGNACAO, v.ORDEM, GETDATE(), 1
FROM (VALUES ('Doureca', 1)) AS v(DESIGNACAO, ORDEM)
WHERE NOT EXISTS (SELECT 1 FROM QUA_CR_DIC_LOCAL_PRODUCAO l WHERE l.DESIGNACAO = v.DESIGNACAO);
GO
