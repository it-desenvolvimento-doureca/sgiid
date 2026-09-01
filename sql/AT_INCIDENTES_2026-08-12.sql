-- ============================================================
-- F6 - Relatório de Investigação de Incidentes Industriais/Tecnológicos
-- Base: PLANO_MELHORIAS_ACIDENTES_2026-08-10.md (secção 6)
-- Origem: formulário em papel ID288.03 anotado pelo cliente
--         ("Novo form no SGIID")
--
-- Executar DEPOIS de AT_MELHORIAS_ACIDENTES_2026-08-12.sql, porque o
-- Diagrama de Ishikawa é partilhado: os incidentes reutilizam o
-- dicionário AT_DIC_CAUSAS_ACIDENTE, que só ganha a coluna CATEGORIA
-- nesse script.
--
-- Idempotente. SGBD: SQL Server
-- ============================================================


-- ============================================================
-- CABEÇALHO
-- ============================================================
IF OBJECT_ID('AT_INCIDENTES', 'U') IS NULL
CREATE TABLE AT_INCIDENTES (
    ID_INCIDENTE            INT IDENTITY(1,1) PRIMARY KEY,
    -- "Ficha n.º ____ / ano". Sequencial por ano, reinicia em cada ano.
    NUMERO                  INT,
    ANO                     INT,

    LOCAL_ZONA              NVARCHAR(255),
    DATA_HORA_INICIO        DATETIME,
    DATA_HORA_FIM           DATETIME,

    -- TIPO DE INCIDENTE (o formulário dizia "acidente"; foi corrigido)
    TP_DERRAME              BIT,
    TP_INCENDIO             BIT,
    TP_EXPLOSAO             BIT,
    TP_FUGA                 BIT,
    TP_OUTRO                BIT,
    TP_OUTRO_TEXTO          NVARCHAR(255),

    DESCRICAO               NVARCHAR(MAX),

    -- Substâncias perigosas
    SUBSTANCIAS_PERIGOSAS   BIT,
    SUBSTANCIAS_QUAIS       NVARCHAR(500),
    QUANTIDADE_ESTIMADA     NVARCHAR(255),

    -- Análise das causas: texto livre. As causas assinaladas ficam em
    -- AT_INCIDENTES_CAUSAS (Diagrama de Ishikawa).
    DESCRICAO_CAUSAS        NVARCHAR(MAX),

    -- Contexto
    TRABALHOS_REPARACAO     BIT,
    TRABALHOS_QUAIS         NVARCHAR(500),

    -- Meio | Sistema utilizado
    MEIO_EXTINTORES         BIT,
    MEIO_MANTAS             BIT,
    MEIO_CARRETEIS          BIT,
    MEIO_HIDRANTES          BIT,
    MEIO_OUTRO              BIT,
    MEIO_OUTRO_TEXTO        NVARCHAR(255),

    PROVIDENCIAS            BIT,
    PROVIDENCIAS_QUAIS      NVARCHAR(500),
    CONSEQUENCIAS           NVARCHAR(MAX),

    -- Feridos. Havendo feridos, o formulário remete para o relatório de
    -- Quase Acidente / Acidente de Trabalho: daí a ligação a AT_OCORRENCIAS.
    FERIDOS                 BIT,
    ID_OCORRENCIA           INT NULL,

    -- Tempos estimados (texto livre: o formulário não impõe formato)
    TEMPO_DETECAO           NVARCHAR(50),
    TEMPO_1A_INTERVENCAO    NVARCHAR(50),
    TEMPO_CHEGADA_MEIOS     NVARCHAR(50),

    -- Fecho
    LICOES_APRENDIDAS       NVARCHAR(MAX),
    REQUER_ATUALIZACAO_DOC  BIT,
    DOCUMENTOS_ATUALIZAR    NVARCHAR(500),
    ACOES_CORRETIVAS        NVARCHAR(MAX),
    MEDIDAS_PREVENTIVAS     NVARCHAR(MAX),
    EQUIPA_INVESTIGACAO     NVARCHAR(MAX),
    DATA_RELATORIO          DATE,
    DIFUSAO_RELATORIO       NVARCHAR(MAX),

    -- Estado, com os mesmos códigos de AT_OCORRENCIAS: E=Aberta, F=Fechada, A=Anulada
    ESTADO                  NVARCHAR(5),
    UTZ_CRIA                INT,
    DATA_CRIA               DATETIME,
    UTZ_MODIF               INT,
    DATA_MODIF              DATETIME,
    UTZ_ANULACAO            INT,
    DATA_ANULACAO           DATETIME,
    INATIVO                 BIT DEFAULT 0
);
GO

IF OBJECT_ID('IX_AT_INCIDENTES_ANO_NUMERO', 'I') IS NULL
    CREATE INDEX IX_AT_INCIDENTES_ANO_NUMERO ON AT_INCIDENTES (ANO, NUMERO);
GO

IF OBJECT_ID('IX_AT_INCIDENTES_OCORRENCIA', 'I') IS NULL
    CREATE INDEX IX_AT_INCIDENTES_OCORRENCIA ON AT_INCIDENTES (ID_OCORRENCIA);
GO


-- ============================================================
-- PESSOAS PRESENTES
-- ============================================================
IF OBJECT_ID('AT_INCIDENTES_PESSOAS', 'U') IS NULL
CREATE TABLE AT_INCIDENTES_PESSOAS (
    ID              INT IDENTITY(1,1) PRIMARY KEY,
    ID_INCIDENTE    INT NOT NULL,
    NOME            NVARCHAR(255),
    SETOR           NVARCHAR(255),
    CARGO_FUNCAO    NVARCHAR(255)
);
GO

IF OBJECT_ID('IX_AT_INCIDENTES_PESSOAS_INCIDENTE', 'I') IS NULL
    CREATE INDEX IX_AT_INCIDENTES_PESSOAS_INCIDENTE
        ON AT_INCIDENTES_PESSOAS (ID_INCIDENTE);
GO

-- Nº mecanográfico da pessoa. Acrescentado depois da primeira execução do
-- script, daí vir como ALTER e não na criação da tabela.
IF COL_LENGTH('AT_INCIDENTES_PESSOAS', 'NUMERO') IS NULL
    ALTER TABLE AT_INCIDENTES_PESSOAS ADD NUMERO NVARCHAR(50);
GO


-- ============================================================
-- ANÁLISE DAS CAUSAS
--
-- Tabela de linhas livres, que o utilizador acrescenta e remove.
-- Substitui as 5 linhas fixas "Porquê" do formulário em papel, que o
-- cliente riscou. NÃO usa o Diagrama de Ishikawa: esse é só dos
-- acidentes de trabalho.
-- ============================================================
IF OBJECT_ID('AT_INCIDENTES_CAUSAS', 'U') IS NULL
CREATE TABLE AT_INCIDENTES_CAUSAS (
    ID              INT IDENTITY(1,1) PRIMARY KEY,
    ID_INCIDENTE    INT NOT NULL,
    ORDEM           INT,
    DESCRICAO       NVARCHAR(MAX)
);
GO

IF OBJECT_ID('IX_AT_INCIDENTES_CAUSAS_INCIDENTE', 'I') IS NULL
    CREATE INDEX IX_AT_INCIDENTES_CAUSAS_INCIDENTE
        ON AT_INCIDENTES_CAUSAS (ID_INCIDENTE);
GO


-- ============================================================
-- AÇÕES CORRETIVAS E MEDIDAS PREVENTIVAS
--
-- Mesma forma das ações do módulo de acidentes (AT_ACCOES): descrição,
-- responsável pela implementação e data. TIPO distingue as duas listas,
-- para não duplicar tabela.
-- ============================================================
IF OBJECT_ID('AT_INCIDENTES_ACOES', 'U') IS NULL
CREATE TABLE AT_INCIDENTES_ACOES (
    ID                  INT IDENTITY(1,1) PRIMARY KEY,
    ID_INCIDENTE        INT NOT NULL,
    -- 'C' = Ação Corretiva | 'P' = Medida Preventiva
    TIPO                NVARCHAR(1) NOT NULL,
    DESCRICAO           NVARCHAR(MAX),
    RESPONSAVEL         NVARCHAR(255),
    DATA_IMPLEMENTACAO  DATE
);
GO

IF OBJECT_ID('IX_AT_INCIDENTES_ACOES_INCIDENTE', 'I') IS NULL
    CREATE INDEX IX_AT_INCIDENTES_ACOES_INCIDENTE
        ON AT_INCIDENTES_ACOES (ID_INCIDENTE, TIPO);
GO


-- ============================================================
-- ANEXOS
-- ============================================================
IF OBJECT_ID('AT_INCIDENTES_ANEXOS', 'U') IS NULL
CREATE TABLE AT_INCIDENTES_ANEXOS (
    ID              INT IDENTITY(1,1) PRIMARY KEY,
    ID_INCIDENTE    INT NOT NULL,
    NOME            NVARCHAR(255),
    CAMINHO         NVARCHAR(500),
    TIPO            NVARCHAR(100),
    DATATYPE        NVARCHAR(255),
    TAMANHO         FLOAT,
    DESCRICAO       NVARCHAR(500),
    CATEGORIA       NVARCHAR(50),
    FICHEIRO_1      NVARCHAR(MAX),
    FICHEIRO_2      NVARCHAR(MAX),
    UTZ_CRIA        INT,
    DATA_CRIA       DATETIME,
    UTZ_ULT_MODIF   INT,
    DATA_ULT_MODIF  DATETIME,
    INATIVO         BIT DEFAULT 0
);
GO

IF OBJECT_ID('IX_AT_INCIDENTES_ANEXOS_INCIDENTE', 'I') IS NULL
    CREATE INDEX IX_AT_INCIDENTES_ANEXOS_INCIDENTE
        ON AT_INCIDENTES_ANEXOS (ID_INCIDENTE);
GO


-- ============================================================
-- NUMERAÇÃO AUTOMÁTICA
-- Devolve o próximo número da ficha para um dado ano.
-- ============================================================
IF OBJECT_ID('AT_PROXIMO_NUMERO_INCIDENTE', 'P') IS NOT NULL
    DROP PROCEDURE AT_PROXIMO_NUMERO_INCIDENTE;
GO

CREATE PROCEDURE AT_PROXIMO_NUMERO_INCIDENTE
    @ANO INT
AS
BEGIN
    SET NOCOUNT ON;
    SELECT ISNULL(MAX(NUMERO), 0) + 1 AS PROXIMO
      FROM AT_INCIDENTES
     WHERE ANO = @ANO;
END
GO


-- ============================================================
-- VERIFICAÇÃO
-- ============================================================
PRINT '--- Tabelas de incidentes criadas ---';
SELECT TABLE_NAME
  FROM INFORMATION_SCHEMA.TABLES
 WHERE TABLE_NAME IN ('AT_INCIDENTES','AT_INCIDENTES_PESSOAS',
                      'AT_INCIDENTES_CAUSAS','AT_INCIDENTES_ANEXOS')
 ORDER BY TABLE_NAME;

PRINT '--- O dicionario Ishikawa esta preenchido? (deve dar 43) ---';
SELECT COUNT(*) AS CAUSAS_ISHIKAWA
  FROM AT_DIC_CAUSAS_ACIDENTE
 WHERE CATEGORIA IS NOT NULL AND ISNULL(INATIVO,0) = 0;
GO
