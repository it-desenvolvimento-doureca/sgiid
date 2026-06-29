-- ============================================================
-- MELHORIAS 2026 - Meios de Controlo (Metrologia)
-- Alterações a tabelas JÁ EXISTENTES (idempotente via ALTER) e novas tabelas.
-- Executar DEPOIS de QUA_MC_CREATE_TABLES.sql (tabelas base já existem em produção).
-- ============================================================

-- Fase 1.2 - Erro Máximo no registo de calibrações
IF COL_LENGTH('QUA_MC_MOV_CALIB_EQUIP_DET', 'ERRO_MAXIMO') IS NULL
    ALTER TABLE QUA_MC_MOV_CALIB_EQUIP_DET ADD ERRO_MAXIMO DECIMAL(18,3);
GO

-- Fase 1.3 - Dicionários Estado Metrológico e MSA (com cor)
IF OBJECT_ID('QUA_MC_DIC_ESTADO_METROLOGICO', 'U') IS NULL
CREATE TABLE QUA_MC_DIC_ESTADO_METROLOGICO (
    ID_ESTADO_METROLOGICO   INT IDENTITY(1,1) PRIMARY KEY,
    DESIGNACAO              NVARCHAR(255),
    COR                     NVARCHAR(20),
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO

IF OBJECT_ID('QUA_MC_DIC_MSA', 'U') IS NULL
CREATE TABLE QUA_MC_DIC_MSA (
    ID_MSA                  INT IDENTITY(1,1) PRIMARY KEY,
    DESIGNACAO              NVARCHAR(255),
    COR                     NVARCHAR(20),
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO

-- Fase 1.3 - Estado Metrológico / MSA ao nível da CALIBRAÇÃO (equipamentos) e VERIFICAÇÃO (gabaritos).
-- NOTA: pertencem a cada registo de calibração/verificação (não ao mestre).
IF COL_LENGTH('QUA_MC_MOV_CALIB_EQUIP_DET', 'ID_ESTADO_METROLOGICO') IS NULL
    ALTER TABLE QUA_MC_MOV_CALIB_EQUIP_DET ADD ID_ESTADO_METROLOGICO INT NULL;
GO
IF COL_LENGTH('QUA_MC_MOV_CALIB_EQUIP_DET', 'ID_MSA') IS NULL
    ALTER TABLE QUA_MC_MOV_CALIB_EQUIP_DET ADD ID_MSA INT NULL;
GO
IF COL_LENGTH('QUA_MC_MOV_VERIF_GABARITO', 'ID_ESTADO_METROLOGICO') IS NULL
    ALTER TABLE QUA_MC_MOV_VERIF_GABARITO ADD ID_ESTADO_METROLOGICO INT NULL;
GO
IF COL_LENGTH('QUA_MC_MOV_VERIF_GABARITO', 'ID_MSA') IS NULL
    ALTER TABLE QUA_MC_MOV_VERIF_GABARITO ADD ID_MSA INT NULL;
GO

-- Remover as colunas (criadas anteriormente) dos mestres - já não são usadas aí
IF COL_LENGTH('QUA_MC_EQUIPAMENTOS', 'ID_ESTADO_METROLOGICO') IS NOT NULL
    ALTER TABLE QUA_MC_EQUIPAMENTOS DROP COLUMN ID_ESTADO_METROLOGICO;
GO
IF COL_LENGTH('QUA_MC_EQUIPAMENTOS', 'ID_MSA') IS NOT NULL
    ALTER TABLE QUA_MC_EQUIPAMENTOS DROP COLUMN ID_MSA;
GO
IF COL_LENGTH('QUA_MC_GABARITOS', 'ID_ESTADO_METROLOGICO') IS NOT NULL
    ALTER TABLE QUA_MC_GABARITOS DROP COLUMN ID_ESTADO_METROLOGICO;
GO
IF COL_LENGTH('QUA_MC_GABARITOS', 'ID_MSA') IS NOT NULL
    ALTER TABLE QUA_MC_GABARITOS DROP COLUMN ID_MSA;
GO

-- Fase 1.4 - REFERENCIA do gabarito passa a texto (era INT)
IF COL_LENGTH('QUA_MC_GABARITOS', 'REFERENCIA') IS NOT NULL
    ALTER TABLE QUA_MC_GABARITOS ALTER COLUMN REFERENCIA NVARCHAR(250);
GO

-- Fase 1.5 - Denormalizar a referência da peça cromada no gabarito.
-- No Access REFERENCIA era um lookup que guardava o ID_PECA_CROMADA;
-- passamos a guardar o texto da REFERENCIA + a DESIGNACAO, e QUA_MC_DIC_PECAS_CROMADAS deixa de existir.
IF COL_LENGTH('QUA_MC_GABARITOS', 'REFERENCIA_DESIGNACAO') IS NULL
    ALTER TABLE QUA_MC_GABARITOS ADD REFERENCIA_DESIGNACAO NVARCHAR(255);
GO

-- Copiar REFERENCIA (texto) e DESIGNACAO da peça cromada para o gabarito.
-- Nesta fase REFERENCIA ainda contém o ID_PECA_CROMADA (era lookup no Access).
IF OBJECT_ID('QUA_MC_DIC_PECAS_CROMADAS', 'U') IS NOT NULL
    UPDATE g
       SET g.REFERENCIA_DESIGNACAO = p.DESIGNACAO,
           g.REFERENCIA            = p.REFERENCIA
      FROM QUA_MC_GABARITOS g
      JOIN QUA_MC_DIC_PECAS_CROMADAS p
        ON TRY_CONVERT(INT, g.REFERENCIA) = p.ID_PECA_CROMADA;
GO

-- QUA_MC_DIC_PECAS_CROMADAS deixa de existir (dados já denormalizados nos gabaritos)
IF OBJECT_ID('QUA_MC_DIC_PECAS_CROMADAS', 'U') IS NOT NULL
    DROP TABLE QUA_MC_DIC_PECAS_CROMADAS;
GO

-- ============================================================
-- Fase 2 - Documentos / anexos (BASE64, padrão como nas reclamações)
-- ============================================================

-- 2.1 Documentos da Entidade de Calibração
IF OBJECT_ID('QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS', 'U') IS NULL
CREATE TABLE QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS (
    ID                          INT IDENTITY(1,1) PRIMARY KEY,
    ID_ENTIDADE_CALIBRACAO      INT REFERENCES QUA_MC_ENTIDADES_CALIBRACAO(ID_ENTIDADE_CALIBRACAO),
    DESCRICAO                   NVARCHAR(500),
    NOME                        NVARCHAR(500),
    CAMINHO                     NVARCHAR(500),
    TIPO                        NVARCHAR(20),
    DATATYPE                    NVARCHAR(100),
    TAMANHO                     INT,
    FICHEIRO_1                  NVARCHAR(MAX),
    FICHEIRO_2                  NVARCHAR(MAX),
    UTZ_CRIA                    INT, DATA_CRIA   DATE,
    UTZ_MODIF                   INT, DATA_MODIF  DATE,
    UTZ_ANULA                   INT, DATA_ANULA  DATE,
    ATIVO                       BIT DEFAULT 1
);
GO

-- 2.2 Documentos dos Dados Gerais do Equipamento
IF OBJECT_ID('QUA_MC_EQUIPAMENTOS_FICHEIROS', 'U') IS NULL
CREATE TABLE QUA_MC_EQUIPAMENTOS_FICHEIROS (
    ID                          INT IDENTITY(1,1) PRIMARY KEY,
    ID_EQUIPAMENTO              INT REFERENCES QUA_MC_EQUIPAMENTOS(ID_EQUIPAMENTO),
    DESCRICAO                   NVARCHAR(500),
    NOME                        NVARCHAR(500),
    CAMINHO                     NVARCHAR(500),
    TIPO                        NVARCHAR(20),
    DATATYPE                    NVARCHAR(100),
    TAMANHO                     INT,
    FICHEIRO_1                  NVARCHAR(MAX),
    FICHEIRO_2                  NVARCHAR(MAX),
    UTZ_CRIA                    INT, DATA_CRIA   DATE,
    UTZ_MODIF                   INT, DATA_MODIF  DATE,
    UTZ_ANULA                   INT, DATA_ANULA  DATE,
    ATIVO                       BIT DEFAULT 1
);
GO

-- 2.2b Documentos dos Dados Gerais do Gabarito
IF OBJECT_ID('QUA_MC_GABARITOS_FICHEIROS', 'U') IS NULL
CREATE TABLE QUA_MC_GABARITOS_FICHEIROS (
    ID                          INT IDENTITY(1,1) PRIMARY KEY,
    ID_GABARITO                 INT REFERENCES QUA_MC_GABARITOS(ID_GABARITO),
    DESCRICAO                   NVARCHAR(500),
    NOME                        NVARCHAR(500),
    CAMINHO                     NVARCHAR(500),
    TIPO                        NVARCHAR(20),
    DATATYPE                    NVARCHAR(100),
    TAMANHO                     INT,
    FICHEIRO_1                  NVARCHAR(MAX),
    FICHEIRO_2                  NVARCHAR(MAX),
    UTZ_CRIA                    INT, DATA_CRIA   DATE,
    UTZ_MODIF                   INT, DATA_MODIF  DATE,
    UTZ_ANULA                   INT, DATA_ANULA  DATE,
    ATIVO                       BIT DEFAULT 1
);
GO

-- 2.3 Anexos do Registo de Calibrações (1 p/ Nº Certificado Externo, 1 p/ Validação)
IF OBJECT_ID('QUA_MC_MOV_CALIB_EQUIP_FICHEIROS', 'U') IS NULL
CREATE TABLE QUA_MC_MOV_CALIB_EQUIP_FICHEIROS (
    ID                          INT IDENTITY(1,1) PRIMARY KEY,
    ID_CALIB_EQUIP_DET          INT REFERENCES QUA_MC_MOV_CALIB_EQUIP_DET(ID_CALIB_EQUIP_DET),
    TIPO_ANEXO                  NVARCHAR(20),   -- 'CERT_EXT' | 'VALIDACAO'
    DESCRICAO                   NVARCHAR(500),
    NOME                        NVARCHAR(500),
    CAMINHO                     NVARCHAR(500),
    TIPO                        NVARCHAR(20),
    DATATYPE                    NVARCHAR(100),
    TAMANHO                     INT,
    FICHEIRO_1                  NVARCHAR(MAX),
    FICHEIRO_2                  NVARCHAR(MAX),
    UTZ_CRIA                    INT, DATA_CRIA   DATE,
    UTZ_MODIF                   INT, DATA_MODIF  DATE,
    UTZ_ANULA                   INT, DATA_ANULA  DATE,
    ATIVO                       BIT DEFAULT 1
);
GO

-- ============================================================
-- Fase 3 - Novos menus: Derrogações (DIAPOC 802) e Declarações de Não Conformidade (EQ12)
-- ============================================================

-- 3.1 Derrogações (DIAPOC 802)
IF OBJECT_ID('QUA_MC_DERROGACOES', 'U') IS NULL
CREATE TABLE QUA_MC_DERROGACOES (
    ID_DERROGACAO           INT IDENTITY(1,1) PRIMARY KEY,
    DATA                    DATE,
    N_DERROGACAO            NVARCHAR(100),
    INTERNA_EXTERNA         NVARCHAR(1),       -- 'I' | 'E'
    EMISSOR                 NVARCHAR(255),
    DEPARTAMENTO            NVARCHAR(255),
    REFERENCIA              NVARCHAR(255),
    DESIGNACAO              NVARCHAR(500),
    PROJETO_VEICULO         NVARCHAR(255),
    LOTE_NUM_OF             NVARCHAR(255),
    CONSTATACAO             NVARCHAR(MAX),
    MOTIVO_DERROGACAO       NVARCHAR(MAX),
    IMPACTO_DESCRICAO       NVARCHAR(MAX),
    SEGURANCA               BIT,
    REGULAMENTACAO          BIT,
    CLIENTE                 NVARCHAR(255),
    QTD_PECAS_IMPACTADAS    INT,
    REPERAGEM               NVARCHAR(255),
    NOMINAL                 NVARCHAR(255),
    PRIMEIRO_NUM_BL         NVARCHAR(100),
    DECISAO_INTERNA         NVARCHAR(MAX),
    DECISAO_CLIENTE         NVARCHAR(20),      -- 'ACEITE' | 'REJEITADO'
    DATA_INICIO             DATE,
    DATA_FIM                DATE,
    ESTADO                  NVARCHAR(20),
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO

-- 3.1 Plano de ações (PDCA) das Derrogações
IF OBJECT_ID('QUA_MC_DERROGACOES_ACOES', 'U') IS NULL
CREATE TABLE QUA_MC_DERROGACOES_ACOES (
    ID                      INT IDENTITY(1,1) PRIMARY KEY,
    ID_DERROGACAO           INT REFERENCES QUA_MC_DERROGACOES(ID_DERROGACAO),
    NUM                     INT,
    ACAO                    NVARCHAR(MAX),
    RESPONSAVEL             NVARCHAR(255),
    DATA_P                  DATE,              -- Planificação
    DATA_D                  DATE,              -- Realização (Do)
    DATA_C                  DATE,              -- Verificação (Check)
    DATA_A                  DATE,              -- Fecho (Act)
    ESTADO                  NVARCHAR(20),
    ID_TAREFA               INT,               -- ligação a GT_MOV_TAREFAS
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO

-- 3.1 Anexos das Derrogações
IF OBJECT_ID('QUA_MC_DERROGACOES_FICHEIROS', 'U') IS NULL
CREATE TABLE QUA_MC_DERROGACOES_FICHEIROS (
    ID                      INT IDENTITY(1,1) PRIMARY KEY,
    ID_DERROGACAO           INT REFERENCES QUA_MC_DERROGACOES(ID_DERROGACAO),
    DESCRICAO               NVARCHAR(500),
    NOME                    NVARCHAR(500),
    CAMINHO                 NVARCHAR(500),
    TIPO                    NVARCHAR(20),
    DATATYPE                NVARCHAR(100),
    TAMANHO                 INT,
    FICHEIRO_1              NVARCHAR(MAX),
    FICHEIRO_2              NVARCHAR(MAX),
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO

-- 3.2 Declarações de Não Conformidade de Meios de Controlo (EQ12)
IF OBJECT_ID('QUA_MC_DECLARACOES_NC', 'U') IS NULL
CREATE TABLE QUA_MC_DECLARACOES_NC (
    ID_DECLARACAO           INT IDENTITY(1,1) PRIMARY KEY,
    EMITIDA_POR             NVARCHAR(255),
    DATA_EMISSAO            DATE,
    ID_EQUIPAMENTO          INT REFERENCES QUA_MC_EQUIPAMENTOS(ID_EQUIPAMENTO),
    COD_EQUIPAMENTO         NVARCHAR(100),
    DEFEITO_CONSTATADO      NVARCHAR(MAX),
    DEFEITO_CONSTATADO_POR  INT,
    DESCRICAO_DEFEITO       NVARCHAR(MAX),
    PODE_TRABALHAR_DERROGACAO BIT,
    DATA_LIMITE_VALIDADE    DATE,
    CONDICOES_DERROGACAO    NVARCHAR(MAX),
    ACOES_A_TOMAR           NVARCHAR(MAX),
    DEFEITO_A_CONSERTAR     NVARCHAR(MAX),
    ESTADO                  NVARCHAR(20),
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO

-- 3.2.1 Coluna nova (caso a tabela já exista): quem constatou o defeito
IF COL_LENGTH('QUA_MC_DECLARACOES_NC', 'DEFEITO_CONSTATADO_POR') IS NULL
    ALTER TABLE QUA_MC_DECLARACOES_NC ADD DEFEITO_CONSTATADO_POR INT;
GO

-- =============================================================
-- 3.3 Derrogações: campos adicionais p/ relatório DIAPOC 802 (2026-06-26)
--     (ALTER idempotente nas colunas em falta)
-- =============================================================
IF COL_LENGTH('QUA_MC_DERROGACOES', 'IMPACTO_CLIENTE') IS NULL
    ALTER TABLE QUA_MC_DERROGACOES ADD IMPACTO_CLIENTE BIT;           -- Impacto: Cliente Sim/Nao
GO
IF COL_LENGTH('QUA_MC_DERROGACOES', 'ACOES_CONTENCAO') IS NULL
    ALTER TABLE QUA_MC_DERROGACOES ADD ACOES_CONTENCAO NVARCHAR(MAX); -- Acoes de Contencao
GO
IF COL_LENGTH('QUA_MC_DERROGACOES', 'DECISAO_INTERNA_RESULTADO') IS NULL
    ALTER TABLE QUA_MC_DERROGACOES ADD DECISAO_INTERNA_RESULTADO NVARCHAR(20); -- 'ACEITE' | 'REJEITADO'
GO
