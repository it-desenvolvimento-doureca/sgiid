-- ============================================================
-- MODULO EPI's (Equipamentos de Protecao Individual)
-- Script unico e consolidado - estado final do modelo
-- ============================================================
-- Substitui os scripts parcelares anteriores (movidos para _historico) e
-- nao inclui a fusao em RH_DIC_EPI, que foi revertida: o modulo tem
-- dicionario proprio.
--
-- Idempotente: pode ser corrido as vezes que forem precisas.
--
-- Notas de reuniao 2025-09-19 (Raquel + David), refinadas em 2026-08.
--
-- Para produção basta este ficheiro. Cobre instalação de raiz e
-- atualização de uma instalação anterior (secção 4B).
-- ============================================================


-- ============================================================
-- 1. PARAMETRIZACAO
-- ============================================================

-- Familias de EPI (Sapatos, Camisola, Luvas, Mascara, Oculos, ...)
-- DURACAO_USO_DIAS = 0 significa duracao infinita (nunca alerta)
IF OBJECT_ID('QUA_EPI_DIC_FAMILIA', 'U') IS NULL
CREATE TABLE QUA_EPI_DIC_FAMILIA (
    ID_FAMILIA          INT IDENTITY(1,1) PRIMARY KEY,
    DESCRICAO           NVARCHAR(255),
    DURACAO_USO_DIAS    INT DEFAULT 0,
    UTZ_CRIA            INT, DATA_CRIA   DATE,
    UTZ_MODIF           INT, DATA_MODIF  DATE,
    UTZ_ANULA           INT, DATA_ANULA  DATE,
    ATIVO               BIT DEFAULT 1
);
GO

-- Dicionario de EPIs do modulo ("Sapatos 40", "Luvas L", ...).
-- Proprio de proposito: RH_DIC_EPI serve o lookup dos Acidentes de
-- Trabalho e nao deve ganhar estes campos.
-- PROREF/PROREF_DESCRICAO = artigo no SILVER (SDTPRA); o stock e lido de la.
IF OBJECT_ID('QUA_EPI_DIC_EPI', 'U') IS NULL
CREATE TABLE QUA_EPI_DIC_EPI (
    ID_EPI              INT IDENTITY(1,1) PRIMARY KEY,
    DESCRICAO           NVARCHAR(255),
    ID_FAMILIA          INT,
    OBRIGA_DEVOLUCAO    BIT DEFAULT 0,
    PROREF              NVARCHAR(50),
    PROREF_DESCRICAO    NVARCHAR(255),
    UTZ_CRIA            INT, DATA_CRIA   DATE,
    UTZ_MODIF           INT, DATA_MODIF  DATE,
    UTZ_ANULA           INT, DATA_ANULA  DATE,
    ATIVO               BIT DEFAULT 1
);
GO

-- Locais EPI: quais dos GER_LOCAIS da empresa (os mesmos de
-- RH_SECTORES.local) sao locais de entrega e levantamento de EPI.
IF OBJECT_ID('QUA_EPI_LOCAL', 'U') IS NULL
CREATE TABLE QUA_EPI_LOCAL (
    ID                  INT IDENTITY(1,1) PRIMARY KEY,
    ID_LOCAL            INT,   -- FK -> GER_LOCAIS.ID
    UTZ_CRIA            INT, DATA_CRIA   DATE,
    UTZ_MODIF           INT, DATA_MODIF  DATE,
    UTZ_ANULA           INT, DATA_ANULA  DATE,
    ATIVO               BIT DEFAULT 1
);
GO

-- Responsaveis por local EPI (varios por local).
-- Define quem ve os pedidos e quem faz os levantamentos desse local.
IF OBJECT_ID('QUA_EPI_LOCAL_RESP', 'U') IS NULL
CREATE TABLE QUA_EPI_LOCAL_RESP (
    ID                  INT IDENTITY(1,1) PRIMARY KEY,
    ID_LOCAL            INT,   -- FK -> QUA_EPI_LOCAL.ID
    ID_UTZ              INT,   -- FK -> GER_UTILIZADORES.ID_UTILIZADOR
    UTZ_CRIA            INT, DATA_CRIA   DATE,
    UTZ_MODIF           INT, DATA_MODIF  DATE,
    UTZ_ANULA           INT, DATA_ANULA  DATE,
    ATIVO               BIT DEFAULT 1
);
GO

-- Familias de EPI aplicaveis a cada sector.
-- Alimenta as linhas do separador EPI's da ficha do funcionario.
IF OBJECT_ID('RH_SECTORES_EPI_FAMILIA', 'U') IS NULL
CREATE TABLE RH_SECTORES_EPI_FAMILIA (
    ID                  INT IDENTITY(1,1) PRIMARY KEY,
    COD_SECTOR          INT,   -- FK -> RH_SECTORES.COD_SECTOR
    ID_FAMILIA          INT,
    UTZ_CRIA            INT, DATA_CRIA   DATE,
    UTZ_MODIF           INT, DATA_MODIF  DATE,
    UTZ_ANULA           INT, DATA_ANULA  DATE,
    ATIVO               BIT DEFAULT 1
);
GO

-- EPIs atribuidos a cada funcionario: uma linha por familia do sector,
-- com o EPI e tamanho escolhidos para essa pessoa.
IF OBJECT_ID('QUA_EPI_FUNC', 'U') IS NULL
CREATE TABLE QUA_EPI_FUNC (
    ID                  INT IDENTITY(1,1) PRIMARY KEY,
    COD_FUNCIONARIO     INT,   -- FK -> RH_FUNCIONARIOS.COD_FUNCIONARIO
    ID_FAMILIA          INT,
    ID_EPI              INT,
    TAMANHO             NVARCHAR(50),
    UTZ_CRIA            INT, DATA_CRIA   DATE,
    UTZ_MODIF           INT, DATA_MODIF  DATE,
    UTZ_ANULA           INT, DATA_ANULA  DATE,
    ATIVO               BIT DEFAULT 1
);
GO


-- ============================================================
-- 2. PEDIDOS
-- ============================================================

-- ID_REQUERENTE   -> GER_UTILIZADORES.ID_UTILIZADOR (quem faz o pedido)
-- ID_DESTINATARIO -> RH_FUNCIONARIOS.COD_FUNCIONARIO (quem recebe)
-- A conversao entre os dois faz-se por
--   GER_UTILIZADORES.COD_UTZ = RH_FUNCIONARIOS.COD_FUNC_ORIGEM
IF OBJECT_ID('QUA_EPI_MOV_PEDIDO', 'U') IS NULL
CREATE TABLE QUA_EPI_MOV_PEDIDO (
    ID_PEDIDO               INT IDENTITY(1,1) PRIMARY KEY,
    ID_REQUERENTE           INT,
    COD_SECTOR              INT,
    ID_DESTINATARIO         INT,
    COD_TURNO               INT,
    DESCRICAO               NVARCHAR(MAX),
    DATA_PEDIDO             DATETIME,
    ESTADO                  NVARCHAR(20),  -- SUBMETIDO | ACEITE | REJEITADO | ENTREGUE
    DATA_ENTREGA_AGENDADA   DATE,
    HORA_ENTREGA_AGENDADA   NVARCHAR(5),
    ID_LOCAL_ENTREGA        INT,           -- FK -> QUA_EPI_LOCAL.ID
    MOTIVO_REJEICAO         NVARCHAR(MAX),
    UTZ_ANALISE             INT,
    DATA_ANALISE            DATETIME,
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO

-- MOTIVO_ATRASO e obrigatorio (validado na UI) quando a duracao de uso
-- da familia ja foi ultrapassada desde a ultima entrega.
IF OBJECT_ID('QUA_EPI_MOV_PEDIDO_LIN', 'U') IS NULL
CREATE TABLE QUA_EPI_MOV_PEDIDO_LIN (
    ID_LINHA                INT IDENTITY(1,1) PRIMARY KEY,
    ID_PEDIDO               INT,
    ID_EPI                  INT,
    TAMANHO                 NVARCHAR(50),
    QTD_PEDIDA              INT DEFAULT 1,
    MOTIVO_ATRASO           NVARCHAR(MAX),
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO

IF OBJECT_ID('QUA_EPI_MOV_PEDIDO_HIST', 'U') IS NULL
CREATE TABLE QUA_EPI_MOV_PEDIDO_HIST (
    ID                      INT IDENTITY(1,1) PRIMARY KEY,
    ID_PEDIDO               INT,
    DATA_HORA               DATETIME,
    ID_UTILIZADOR           INT,
    ESTADO_ANTERIOR         NVARCHAR(20),
    ESTADO_NOVO             NVARCHAR(20),
    OBSERVACOES             NVARCHAR(MAX),
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO


-- ============================================================
-- 3. ENTREGAS (levantamento)
-- ============================================================

-- Assinaturas em base64, so o payload (sem o prefixo data:image/png;base64,)
IF OBJECT_ID('QUA_EPI_MOV_ENTREGA', 'U') IS NULL
CREATE TABLE QUA_EPI_MOV_ENTREGA (
    ID_ENTREGA              INT IDENTITY(1,1) PRIMARY KEY,
    ID_PEDIDO               INT,
    ID_LOCAL                INT,           -- FK -> QUA_EPI_LOCAL.ID
    DATA_HORA_ENTREGA       DATETIME,
    ID_FUNC_ENTREGA         INT,
    ASSINATURA_REQUERENTE   VARCHAR(MAX),
    ASSINATURA_ENTREGA      VARCHAR(MAX),
    DATA_SINCRO_SILVER      DATETIME,
    -- RASCUNHO = gravado a meio; CONCLUIDA = entregue e stock debitado.
    -- Todas as verificacoes de 'ja foi entregue' exigem CONCLUIDA.
    ESTADO                  NVARCHAR(20),
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO

-- Espelha PIN_MOV_PREPARACAO_ETIQ (padrao do consumo da pintura).
-- ETQNUM = SETQDE.ETQNUM no SILVER; LIECOD = armazem; EMPCOD = localizacao.
IF OBJECT_ID('QUA_EPI_MOV_ENTREGA_ETIQ', 'U') IS NULL
CREATE TABLE QUA_EPI_MOV_ENTREGA_ETIQ (
    ID_LINHA                INT IDENTITY(1,1) PRIMARY KEY,
    ID_ENTREGA              INT,
    ID_PEDIDO_LIN           INT,
    ID_EPI                  INT,
    ETQNUM                  NVARCHAR(20),
    PROREF                  NVARCHAR(50),
    LIECOD                  NVARCHAR(20),
    EMPCOD                  NVARCHAR(20),
    ETQORILOT1              NVARCHAR(50),
    LOTNUMENR               NVARCHAR(50),
    UNISTO                  NVARCHAR(20),
    QUANT                   DECIMAL(18,4),   -- qtd na etiqueta ao ler
    CONSUMIR                DECIMAL(18,4),   -- qtd entregue
    QUANT_FINAL             DECIMAL(18,4),
    OBRIGA_DEVOLUCAO        BIT DEFAULT 0,
    DEVOLVIDO               BIT DEFAULT 0,
    DATA_DEVOLUCAO          DATETIME,
    UTZ_CRIA                INT, DATA_CRIA   DATE,
    UTZ_MODIF               INT, DATA_MODIF  DATE,
    UTZ_ANULA               INT, DATA_ANULA  DATE,
    ATIVO                   BIT DEFAULT 1
);
GO


-- ============================================================
-- 4. CONFIGURACAO DOS CONSUMOS PARA O SILVER
-- ============================================================
-- As entregas de EPI nao tem OF, tal como a manutencao. A pintura usa a OF
-- real; a manutencao resolve com valores fixos em GER_CONF_CONSUMOS_SILVER.
-- Esta tabela e o equivalente para os EPIs. Sem estes valores preenchidos
-- o ficheiro de consumo nao e gerado (a entrega grava e o stock e debitado
-- na mesma).
IF OBJECT_ID('GER_CONF_CONSUMOS_EPIS_SILVER', 'U') IS NULL
CREATE TABLE GER_CONF_CONSUMOS_EPIS_SILVER (
    ID_CONF             INT IDENTITY(1,1) PRIMARY KEY,
    SECCAO_EPI          NVARCHAR(50),
    SUBSECCAO_EPI       NVARCHAR(50),
    REF_COMPOSTO_EPI    NVARCHAR(50),
    OF_EPI              NVARCHAR(50),
    PASTA_FICHEIRO      NVARCHAR(255),
    UTZ_CRIA            INT, DATA_CRIA   DATE,
    UTZ_MODIF           INT, DATA_MODIF  DATE,
    UTZ_ANULA           INT, DATA_ANULA  DATE,
    ATIVO               BIT DEFAULT 1
);
GO

IF NOT EXISTS (SELECT 1 FROM GER_CONF_CONSUMOS_EPIS_SILVER)
    INSERT INTO GER_CONF_CONSUMOS_EPIS_SILVER
        (SECCAO_EPI, SUBSECCAO_EPI, REF_COMPOSTO_EPI, OF_EPI, DATA_CRIA, ATIVO)
    VALUES (NULL, NULL, NULL, NULL, GETDATE(), 1);
GO


-- ============================================================
-- 4B. ATUALIZACOES PARA INSTALACOES QUE JA CORRERAM ESTE SCRIPT
-- ============================================================
-- Os CREATE TABLE acima so correm se a tabela nao existir, por isso as
-- colunas acrescentadas depois da primeira instalacao precisam de ALTER.
-- Unica ate hoje: QUA_EPI_MOV_ENTREGA.ESTADO.

IF COL_LENGTH('QUA_EPI_MOV_ENTREGA', 'ESTADO') IS NULL
    ALTER TABLE QUA_EPI_MOV_ENTREGA ADD ESTADO NVARCHAR(20);
GO

-- As entregas anteriores a coluna foram todas concluidas
UPDATE QUA_EPI_MOV_ENTREGA SET ESTADO = 'CONCLUIDA' WHERE ESTADO IS NULL;
GO


-- ============================================================
-- 5. INDICES
-- ============================================================

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_DIC_EPI_FAMILIA')
    CREATE INDEX IX_QUA_EPI_DIC_EPI_FAMILIA ON QUA_EPI_DIC_EPI (ID_FAMILIA);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_DIC_EPI_PROREF')
    CREATE INDEX IX_QUA_EPI_DIC_EPI_PROREF ON QUA_EPI_DIC_EPI (PROREF);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_LOCAL_LOCAL')
    CREATE INDEX IX_QUA_EPI_LOCAL_LOCAL ON QUA_EPI_LOCAL (ID_LOCAL);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_LOCAL_RESP_LOCAL')
    CREATE INDEX IX_QUA_EPI_LOCAL_RESP_LOCAL ON QUA_EPI_LOCAL_RESP (ID_LOCAL);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_LOCAL_RESP_UTZ')
    CREATE INDEX IX_QUA_EPI_LOCAL_RESP_UTZ ON QUA_EPI_LOCAL_RESP (ID_UTZ);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_RH_SECTORES_EPI_FAM_SECT')
    CREATE INDEX IX_RH_SECTORES_EPI_FAM_SECT ON RH_SECTORES_EPI_FAMILIA (COD_SECTOR);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_FUNC_FUNCIONARIO')
    CREATE INDEX IX_QUA_EPI_FUNC_FUNCIONARIO ON QUA_EPI_FUNC (COD_FUNCIONARIO);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_PEDIDO_DEST')
    CREATE INDEX IX_QUA_EPI_PEDIDO_DEST ON QUA_EPI_MOV_PEDIDO (ID_DESTINATARIO);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_PEDIDO_REQ')
    CREATE INDEX IX_QUA_EPI_PEDIDO_REQ ON QUA_EPI_MOV_PEDIDO (ID_REQUERENTE);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_PEDIDO_ESTADO')
    CREATE INDEX IX_QUA_EPI_PEDIDO_ESTADO ON QUA_EPI_MOV_PEDIDO (ESTADO);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_PEDIDO_LOCAL')
    CREATE INDEX IX_QUA_EPI_PEDIDO_LOCAL ON QUA_EPI_MOV_PEDIDO (ID_LOCAL_ENTREGA);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_PEDIDO_LIN_PED')
    CREATE INDEX IX_QUA_EPI_PEDIDO_LIN_PED ON QUA_EPI_MOV_PEDIDO_LIN (ID_PEDIDO);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_PEDIDO_HIST_PED')
    CREATE INDEX IX_QUA_EPI_PEDIDO_HIST_PED ON QUA_EPI_MOV_PEDIDO_HIST (ID_PEDIDO);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_ENTREGA_PEDIDO')
    CREATE INDEX IX_QUA_EPI_ENTREGA_PEDIDO ON QUA_EPI_MOV_ENTREGA (ID_PEDIDO);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_ENT_ETIQ_ENT')
    CREATE INDEX IX_QUA_EPI_ENT_ETIQ_ENT ON QUA_EPI_MOV_ENTREGA_ETIQ (ID_ENTREGA);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_ENT_ETIQ_EPI')
    CREATE INDEX IX_QUA_EPI_ENT_ETIQ_EPI ON QUA_EPI_MOV_ENTREGA_ETIQ (ID_EPI);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'UX_QUA_EPI_ENT_ETIQ')
    CREATE UNIQUE INDEX UX_QUA_EPI_ENT_ETIQ ON QUA_EPI_MOV_ENTREGA_ETIQ (ID_ENTREGA, ETQNUM);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_QUA_EPI_ENTREGA_ESTADO')
    CREATE INDEX IX_QUA_EPI_ENTREGA_ESTADO ON QUA_EPI_MOV_ENTREGA (ID_PEDIDO, ESTADO);
GO


-- ============================================================
-- 6. EVENTO DE EMAIL: pedido aceite
-- ============================================================
-- Notifica o REQUERENTE com a data, hora e local agendados. E o unico email
-- do modulo: a rejeicao pede motivo mas nao notifica.
--
-- CAMPOS DO TEMPLATE (contrato com o codigo - manter em sincronia):
--   {N_PEDIDO} {REQUERENTE} {DESTINATARIO} {SECTOR}
--   {DATA_ENTREGA} {HORA_ENTREGA} {LOCAL} {EPIS} {OBSERVACOES}

IF NOT EXISTS (
    SELECT 1 FROM GER_EVENTOS_CONF WHERE PAGINA = 'epis_pedidos' AND MOMENTO = 'ACEITAR'
)
BEGIN
    INSERT INTO GER_EVENTOS_CONF
        (MODULO, MOMENTO, PAGINA, ESTADO, EMAIL_PARA, EMAIL_DE, EMAIL_ANEXO,
         EMAIL_ASSUNTO, EMAIL_MENSAGEM, OBS, DATA_ULT_MODIF)
    VALUES
        (
            (SELECT TOP 1 ID_MODULO FROM GER_MODULO WHERE NOME_MODULO LIKE '%Qualidade%'),
            'ACEITAR', 'epis_pedidos', 1,
            NULL,   -- destinatario e o requerente, resolvido em runtime
            'alertas.it.doureca@gmail.com', 0,
            'Pedido de EPI n {N_PEDIDO} aceite',
            '<p>Ola {REQUERENTE},</p>'
          + '<p>O pedido de EPI n <b>{N_PEDIDO}</b> para <b>{DESTINATARIO}</b> ({SECTOR}) foi aceite.</p>'
          + '<p><b>Entrega agendada para {DATA_ENTREGA} as {HORA_ENTREGA}</b><br/>Local: {LOCAL}</p>'
          + '<p>EPIs:<br/>{EPIS}</p><p>{OBSERVACOES}</p>'
          + '<p style="color:#888;">Mensagem automatica do SGIID.</p>',
            'Notificacao ao requerente quando um pedido de EPI e aceite',
            GETDATE()
        );
END
GO

-- Campos disponiveis, para aparecerem no ecra de gestao de eventos
INSERT INTO GER_CAMPOS_DISP (ID_EVENTO_CONF, DESCRICAO_CAMPO, NOME_CAMPO)
SELECT e.ID_EVENTO, v.DESCRICAO_CAMPO, v.NOME_CAMPO
FROM GER_EVENTOS_CONF e
CROSS JOIN (VALUES
        ('Numero do pedido',            'N_PEDIDO'),
        ('Nome de quem fez o pedido',   'REQUERENTE'),
        ('Nome de quem recebe o EPI',   'DESTINATARIO'),
        ('Sector do pedido',            'SECTOR'),
        ('Data de entrega agendada',    'DATA_ENTREGA'),
        ('Hora de entrega agendada',    'HORA_ENTREGA'),
        ('Local de entrega',            'LOCAL'),
        ('Lista dos EPIs pedidos',      'EPIS'),
        ('Observacoes de quem aceitou', 'OBSERVACOES')
    ) AS v(DESCRICAO_CAMPO, NOME_CAMPO)
WHERE e.PAGINA = 'epis_pedidos' AND e.MOMENTO = 'ACEITAR'
  AND NOT EXISTS (
        SELECT 1 FROM GER_CAMPOS_DISP c
        WHERE c.ID_EVENTO_CONF = e.ID_EVENTO AND c.NOME_CAMPO = v.NOME_CAMPO
  );
GO


-- ============================================================
-- 7. SEEDS - familias base indicadas nas notas
-- ============================================================

INSERT INTO QUA_EPI_DIC_FAMILIA (DESCRICAO, DURACAO_USO_DIAS, DATA_CRIA, ATIVO)
SELECT v.DESCRICAO, 0, GETDATE(), 1
FROM (VALUES ('Sapatos'), ('Camisola'), ('Luvas'), ('Mascara'), ('Oculos')) AS v(DESCRICAO)
WHERE NOT EXISTS (SELECT 1 FROM QUA_EPI_DIC_FAMILIA f WHERE f.DESCRICAO = v.DESCRICAO);
GO
