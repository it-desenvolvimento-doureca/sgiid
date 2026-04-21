-- =============================================================================
-- SGIID - Melhorias Módulo Manutenção IT - Controlos de Segurança da Informação
-- Data: 2026-04-09
-- Pedido: Natércia Oliveira (Qualidade) -> Tiago Pereira (XpertGo)
-- =============================================================================
-- DESCRIÇÃO: Adiciona campos de classificação de pedidos IT (Incidente, Pedido
-- de Intervenção, Pedido de Melhoria, Evento) com sistema de prioridade,
-- tempos de resposta/resolução SLA e alarmes de cumprimento.
-- =============================================================================


-- =============================================================================
-- 1. NOVAS COLUNAS EM MAN_MOV_MANUTENCAO_CAB
-- =============================================================================
-- Adiciona campos de classificação ao cabeçalho de manutenção (tabela principal
-- usada pelo formulário ficha-manutencao)
-- TIPO_CLASSIFICACAO_PEDIDO:
--   'IN' = Incidente
--   'PI' = Pedido de Intervenção
--   'PM' = Pedido de Melhoria
--   'EV' = Evento
-- =============================================================================

ALTER TABLE MAN_MOV_MANUTENCAO_CAB ADD
    -- Tipo de classificação (aba Classificação de Pedido)
    TIPO_CLASSIFICACAO_PEDIDO   VARCHAR(2)   NULL,

    -- Para PEDIDO DE INTERVENÇÃO (PI): nível de prioridade
    -- 1 = Alto (4h úteis), 2 = Médio (16h úteis), 3 = Baixo (40h úteis)
    PRIORIDADE_INTERVENCAO      INT          NULL,

    -- Para INCIDENTE (IN): nível de impacto
    -- 1 = Alto (toda empresa / violação RGPD)
    -- 2 = Médio (alguma área ou grupo de utilizadores)
    -- 3 = Baixo (apenas um utilizador / impacto mínimo)
    NIVEL_IMPACTO               INT          NULL,

    -- Para INCIDENTE (IN): nível de urgência
    -- 1 = Alto (requer ação imediata; danos sérios se atraso)
    -- 2 = Médio (requer ação rápida; problemas moderados)
    -- 3 = Baixo (pode tratar em tempo normal; sem prejuízo significativo)
    NIVEL_URGENCIA              INT          NULL,

    -- Prioridade calculada automaticamente para INCIDENTE
    -- Fórmula: NIVEL_IMPACTO + NIVEL_URGENCIA - 1  (resultado: 1 a 5)
    -- P1=Crítico(red), P2=Alto(orange), P3=Médio(yellow), P4=Baixo(green), P5=Mínimo(bright green)
    PRIORIDADE_INCIDENTE        INT          NULL,

    -- Tempo de resposta (horas úteis) atribuído automaticamente pela prioridade
    -- Incidente: P1=0(imediata), P2=1h, P3=4h, P4=8h, P5=16h
    -- Intervenção: Nv1=4h, Nv2=16h, Nv3=40h
    TEMPO_RESPOSTA_HORAS        INT          NULL,

    -- Tempo máximo de resolução (horas úteis) atribuído automaticamente
    -- Incidente: P1=4h, P2=8h, P3=12h, P4=24h, P5=48h
    -- Intervenção: sem tempo máximo de resolução (apenas resposta)
    TEMPO_RESOLUCAO_HORAS       INT          NULL,

    -- Data/hora em que o DTSI deu a primeira resposta ao pedido
    DATA_HORA_RESPOSTA          DATETIME     NULL,

    -- Data/hora em que o pedido foi efetivamente resolvido/fechado
    DATA_HORA_RESOLUCAO_REAL    DATETIME     NULL;

GO

-- =============================================================================
-- 1b. NOVA COLUNA EM MAN_DIC_AMBITOS — identifica âmbitos de IT
-- =============================================================================
-- Quando AMBITO_IT = 1, o painel "Classificação de Pedido" fica visível
-- na ficha de manutenção para esse âmbito.
-- =============================================================================

IF NOT EXISTS (
    SELECT 1 FROM sys.columns
    WHERE object_id = OBJECT_ID('MAN_DIC_AMBITOS') AND name = 'AMBITO_IT'
)
    ALTER TABLE MAN_DIC_AMBITOS ADD AMBITO_IT BIT NOT NULL DEFAULT 0;
GO

-- =============================================================================
-- 2. TABELA DE REFERÊNCIA - TIPOS DE CLASSIFICAÇÃO
-- =============================================================================

IF OBJECT_ID('MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO', 'U') IS NULL
BEGIN
    CREATE TABLE MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO (
        ID              INT          NOT NULL IDENTITY(1,1),
        CODIGO          VARCHAR(2)   NOT NULL,   -- 'IN', 'PI', 'PM', 'EV'
        DESCRICAO       VARCHAR(100) NOT NULL,
        ATIVO           BIT          NOT NULL DEFAULT 1,
        UTZ_CRIA        INT          NULL,
        DATA_CRIA       DATETIME     NULL DEFAULT GETDATE(),
        UTZ_ULT_MODIF   INT          NULL,
        DATA_ULT_MODIF  DATETIME     NULL,
        CONSTRAINT PK_MAN_DIC_TIPO_CLASS_PEDIDO PRIMARY KEY (ID),
        CONSTRAINT UQ_MAN_DIC_TIPO_CLASS_CODIGO UNIQUE (CODIGO)
    );

    INSERT INTO MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO (CODIGO, DESCRICAO) VALUES
        ('IN', 'Incidente'),
        ('PI', 'Pedido de Intervenção'),
        ('PM', 'Pedido de Melhoria'),
        ('EV', 'Evento');

    PRINT 'Tabela MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO criada e populada.';
END
ELSE
    PRINT 'Tabela MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO já existe.';

GO

-- =============================================================================
-- 3. TABELA DE REFERÊNCIA - PRIORIDADE PARA PEDIDO DE INTERVENÇÃO
-- =============================================================================

IF OBJECT_ID('MAN_DIC_PRIORIDADE_INTERVENCAO', 'U') IS NULL
BEGIN
    CREATE TABLE MAN_DIC_PRIORIDADE_INTERVENCAO (
        ID                      INT          NOT NULL IDENTITY(1,1),
        NIVEL                   INT          NOT NULL,    -- 1, 2, 3
        DESCRICAO               VARCHAR(10)  NOT NULL,    -- 'Alto', 'Médio', 'Baixo'
        DESC_CONDICAO           VARCHAR(300) NOT NULL,    -- Descrição da condição
        TEMPO_RESPOSTA_HORAS    INT          NOT NULL,    -- Horas úteis para resposta
        ATIVO                   BIT          NOT NULL DEFAULT 1,
        CONSTRAINT PK_MAN_DIC_PRIOR_INTERV PRIMARY KEY (ID),
        CONSTRAINT UQ_MAN_DIC_PRIOR_INTERV_NIVEL UNIQUE (NIVEL)
    );

    INSERT INTO MAN_DIC_PRIORIDADE_INTERVENCAO (NIVEL, DESCRICAO, DESC_CONDICAO, TEMPO_RESPOSTA_HORAS) VALUES
        (1, 'Alto',  'O(s) utilizador(s) não podem realizar o trabalho sem conclusão do pedido.', 4),
        (2, 'Médio', 'O(s) utilizador(s) não podem realizar parte das funções sem conclusão do pedido.', 16),
        (3, 'Baixo', 'Pedido importante, mas não afeta o desempenho das funções do(s) utilizador(es).', 40);

    PRINT 'Tabela MAN_DIC_PRIORIDADE_INTERVENCAO criada e populada.';
END
ELSE
    PRINT 'Tabela MAN_DIC_PRIORIDADE_INTERVENCAO já existe.';

GO

-- =============================================================================
-- 4. TABELA DE REFERÊNCIA - NÍVEL DE IMPACTO (Incidente)
-- =============================================================================

IF OBJECT_ID('MAN_DIC_NIVEL_IMPACTO_INCIDENTE', 'U') IS NULL
BEGIN
    CREATE TABLE MAN_DIC_NIVEL_IMPACTO_INCIDENTE (
        ID          INT          NOT NULL IDENTITY(1,1),
        NIVEL       INT          NOT NULL,    -- 1, 2, 3
        DESCRICAO   VARCHAR(10)  NOT NULL,    -- 'Alto', 'Médio', 'Baixo'
        DESC_CONDICAO VARCHAR(300) NOT NULL,
        ATIVO       BIT          NOT NULL DEFAULT 1,
        CONSTRAINT PK_MAN_DIC_NIVEL_IMPACTO PRIMARY KEY (ID),
        CONSTRAINT UQ_MAN_DIC_NIVEL_IMPACTO_NIVEL UNIQUE (NIVEL)
    );

    INSERT INTO MAN_DIC_NIVEL_IMPACTO_INCIDENTE (NIVEL, DESCRICAO, DESC_CONDICAO) VALUES
        (1, 'Alto',  'Toda a empresa é afetada e/ou é uma violação de dados pessoais (RGPD).'),
        (2, 'Médio', 'Alguma área ou grupo de utilizadores é afetado.'),
        (3, 'Baixo', 'Apenas um utilizador afetado ou impacto mínimo.');

    PRINT 'Tabela MAN_DIC_NIVEL_IMPACTO_INCIDENTE criada e populada.';
END
ELSE
    PRINT 'Tabela MAN_DIC_NIVEL_IMPACTO_INCIDENTE já existe.';

GO

-- =============================================================================
-- 5. TABELA DE REFERÊNCIA - NÍVEL DE URGÊNCIA (Incidente)
-- =============================================================================

IF OBJECT_ID('MAN_DIC_NIVEL_URGENCIA_INCIDENTE', 'U') IS NULL
BEGIN
    CREATE TABLE MAN_DIC_NIVEL_URGENCIA_INCIDENTE (
        ID          INT          NOT NULL IDENTITY(1,1),
        NIVEL       INT          NOT NULL,    -- 1, 2, 3
        DESCRICAO   VARCHAR(10)  NOT NULL,    -- 'Alto', 'Médio', 'Baixo'
        DESC_CONDICAO VARCHAR(300) NOT NULL,
        ATIVO       BIT          NOT NULL DEFAULT 1,
        CONSTRAINT PK_MAN_DIC_NIVEL_URGENCIA PRIMARY KEY (ID),
        CONSTRAINT UQ_MAN_DIC_NIVEL_URGENCIA_NIVEL UNIQUE (NIVEL)
    );

    INSERT INTO MAN_DIC_NIVEL_URGENCIA_INCIDENTE (NIVEL, DESCRICAO, DESC_CONDICAO) VALUES
        (1, 'Alto',  'Requer ação imediata; atraso pode causar danos sérios à operação ou reputação.'),
        (2, 'Médio', 'Requer ação rápida; atraso causa problemas moderados.'),
        (3, 'Baixo', 'Pode ser tratado em tempo normal; atraso não causa prejuízo significativo.');

    PRINT 'Tabela MAN_DIC_NIVEL_URGENCIA_INCIDENTE criada e populada.';
END
ELSE
    PRINT 'Tabela MAN_DIC_NIVEL_URGENCIA_INCIDENTE já existe.';

GO

-- =============================================================================
-- 6. TABELA DE REFERÊNCIA - PRIORIDADE DO INCIDENTE (tempos SLA)
-- Calculada automaticamente: PRIORIDADE = NIVEL_IMPACTO + NIVEL_URGENCIA - 1
-- =============================================================================

IF OBJECT_ID('MAN_DIC_PRIORIDADE_INCIDENTE', 'U') IS NULL
BEGIN
    CREATE TABLE MAN_DIC_PRIORIDADE_INCIDENTE (
        ID                      INT          NOT NULL IDENTITY(1,1),
        PRIORIDADE              INT          NOT NULL,    -- 1 a 5
        COR                     VARCHAR(20)  NOT NULL,    -- Para UI: 'vermelho', 'laranja', 'amarelo', 'verde-claro', 'verde'
        DESC_PRIORIDADE         VARCHAR(300) NOT NULL,
        TEMPO_RESPOSTA_HORAS    INT          NOT NULL,    -- 0 = ação imediata
        TEMPO_RESOLUCAO_HORAS   INT          NOT NULL,
        ATIVO                   BIT          NOT NULL DEFAULT 1,
        CONSTRAINT PK_MAN_DIC_PRIOR_INCIDENTE PRIMARY KEY (ID),
        CONSTRAINT UQ_MAN_DIC_PRIOR_INCIDENTE_PRIOR UNIQUE (PRIORIDADE)
    );

    -- Prioridade 1: Impacto Alto + Urgência Alto
    -- Prioridade 2: Impacto Alto+Médio / Médio+Alto
    -- Prioridade 3: Impacto Alto+Baixo / Médio+Médio / Baixo+Alto
    -- Prioridade 4: Impacto Médio+Baixo / Baixo+Médio
    -- Prioridade 5: Impacto Baixo + Urgência Baixo
    INSERT INTO MAN_DIC_PRIORIDADE_INCIDENTE
        (PRIORIDADE, COR, DESC_PRIORIDADE, TEMPO_RESPOSTA_HORAS, TEMPO_RESOLUCAO_HORAS)
    VALUES
        (1, '#c36a6a',   'Incidentes críticos que afetam toda a empresa ou dados críticos; ação imediata necessária.', 0,  4),
        (2, '#995400',    'Incidentes que afetam áreas importantes ou grupos de utilizadores; risco significativo se não tratado rapidamente.', 1, 8),
        (3, '#664d00',    'Incidentes que afetam algumas áreas ou utilizadores; impacto moderado.', 4, 12),
        (4, '#334d00','Incidentes com impacto menor, afetam poucas funções ou utilizadores; risco limitado.', 8, 24),
        (5, '#008000',      'Incidentes de baixo impacto, inconvenientes isolados.', 16, 48);

    PRINT 'Tabela MAN_DIC_PRIORIDADE_INCIDENTE criada e populada.';
END
ELSE
    PRINT 'Tabela MAN_DIC_PRIORIDADE_INCIDENTE já existe.';

GO

-- =============================================================================
-- 7. VIEW AUXILIAR - SLA STATUS (para consultas de alarme)
-- Calcula automaticamente se o pedido está dentro ou fora do prazo
-- Usa horas úteis (Seg-Sex, 8h-18h) excluindo feriados de GER_FERIADOS
-- DATA_HORA_RESPOSTA = DATA_HORA_PEDIDO (momento de submissão)
-- DATA_HORA_RESOLUCAO_REAL = DATA_FIM (data fim de trabalho)
-- =============================================================================

-- Função: horas úteis entre dois datetimes (Seg-Sex, 08:00-18:00, excl. feriados)
IF OBJECT_ID('dbo.fn_HorasUteis', 'FN') IS NOT NULL
    DROP FUNCTION dbo.fn_HorasUteis;
GO

CREATE FUNCTION dbo.fn_HorasUteis(@inicio DATETIME, @fim DATETIME)
RETURNS DECIMAL(10,2)
AS
BEGIN
    IF @inicio IS NULL OR @fim IS NULL OR @fim <= @inicio RETURN 0;

    DECLARE @horas    DECIMAL(10,2) = 0;
    DECLARE @diaAtual DATE          = CAST(@inicio AS DATE);
    DECLARE @diaFim   DATE          = CAST(@fim    AS DATE);

    WHILE @diaAtual <= @diaFim
    BEGIN
        -- Apenas dias úteis (Seg-Sex) independente de SET DATEFIRST
        -- DATEDIFF(DAY,'2001-01-01',...) % 7: 0=Seg,1=Ter,2=Qua,3=Qui,4=Sex,5=Sab,6=Dom
        IF DATEDIFF(DAY, '2001-01-01', @diaAtual) % 7 < 5
           AND NOT EXISTS (
               SELECT 1 FROM GER_FERIADOS
               WHERE CAST(DATA AS DATE) = @diaAtual)
        BEGIN
            DECLARE @dInicio DATETIME = DATEADD(HOUR,  8, CAST(@diaAtual AS DATETIME));
            DECLARE @dFim    DATETIME = DATEADD(HOUR, 18, CAST(@diaAtual AS DATETIME));

            -- Intersecção com o intervalo pedido
            DECLARE @pInicio DATETIME = CASE WHEN @inicio > @dInicio THEN @inicio ELSE @dInicio END;
            DECLARE @pFim    DATETIME = CASE WHEN @fim    < @dFim    THEN @fim    ELSE @dFim    END;

            IF @pFim > @pInicio
                SET @horas = @horas + CAST(DATEDIFF(MINUTE, @pInicio, @pFim) AS DECIMAL) / 60.0;
        END

        SET @diaAtual = DATEADD(DAY, 1, @diaAtual);
    END

    RETURN @horas;
END
GO

PRINT 'Função dbo.fn_HorasUteis criada.';
GO

IF OBJECT_ID('VW_MAN_PEDIDOS_SLA_STATUS', 'V') IS NOT NULL
    DROP VIEW VW_MAN_PEDIDOS_SLA_STATUS;
GO

CREATE VIEW VW_MAN_PEDIDOS_SLA_STATUS AS
SELECT
    p.ID_MANUTENCAO_CAB,
    p.TIPO_CLASSIFICACAO_PEDIDO,
    p.DATA_HORA_PEDIDO,
    p.ESTADO,
    p.NIVEL_IMPACTO,
    p.NIVEL_URGENCIA,
    p.PRIORIDADE_INCIDENTE,
    p.PRIORIDADE_INTERVENCAO,
    p.TEMPO_RESPOSTA_HORAS,
    p.TEMPO_RESOLUCAO_HORAS,
    -- DATA_HORA_RESPOSTA = DATA_HORA_PEDIDO (automático)
    p.DATA_HORA_PEDIDO        AS DATA_HORA_RESPOSTA,
    -- DATA_HORA_RESOLUCAO_REAL = DATA_FIM (automático)
    p.DATA_FIM                AS DATA_HORA_RESOLUCAO_REAL,

    -- Estado Tempo de Resposta
    -- Para P1 (TEMPO_RESPOSTA=0): ação imediata, limite 15 min
    CASE
        WHEN p.TEMPO_RESPOSTA_HORAS = 0 THEN
            CASE WHEN dbo.fn_HorasUteis(p.DATA_HORA_PEDIDO, ISNULL(DATA_INICIO,GETDATE())) * 60.0
                      > 15.0 THEN 'vermelho' ELSE 'verde' END
        WHEN p.TEMPO_RESPOSTA_HORAS IS NULL THEN 'pendente'
        WHEN dbo.fn_HorasUteis(p.DATA_HORA_PEDIDO,ISNULL(DATA_INICIO,GETDATE()))
             <= p.TEMPO_RESPOSTA_HORAS THEN 'verde'
        ELSE 'vermelho'
    END AS ESTADO_SLA_RESPOSTA,

    -- Estado Tempo de Resolução
    CASE
        WHEN p.TEMPO_RESOLUCAO_HORAS IS NULL THEN 'pendente'
        WHEN p.DATA_FIM IS NULL THEN
            CASE WHEN dbo.fn_HorasUteis(p.DATA_HORA_PEDIDO, GETDATE())
                      > p.TEMPO_RESOLUCAO_HORAS THEN 'vermelho' ELSE 'pendente' END
        WHEN dbo.fn_HorasUteis(p.DATA_HORA_PEDIDO, p.DATA_FIM)
             <= p.TEMPO_RESOLUCAO_HORAS THEN 'verde'
        ELSE 'vermelho'
    END AS ESTADO_SLA_RESOLUCAO,

    -- Horas úteis decorridas
    dbo.fn_HorasUteis(p.DATA_HORA_PEDIDO, GETDATE()) AS HORAS_UTEIS_DECORRIDAS

FROM MAN_MOV_MANUTENCAO_CAB p
WHERE p.TIPO_CLASSIFICACAO_PEDIDO IS NOT NULL;

GO

PRINT 'View VW_MAN_PEDIDOS_SLA_STATUS criada.';
GO

-- =============================================================================
-- 8. STORED PROCEDURE - ATRIBUIR PRIORIDADE E TEMPOS SLA
-- Chamada pelo backend ao gravar a classificação do pedido
-- =============================================================================

IF OBJECT_ID('MAN_ATRIBUIR_PRIORIDADE_PEDIDO', 'P') IS NOT NULL
    DROP PROCEDURE MAN_ATRIBUIR_PRIORIDADE_PEDIDO;
GO

CREATE PROCEDURE MAN_ATRIBUIR_PRIORIDADE_PEDIDO
    @ID_MANUTENCAO_CAB          INT,
    @TIPO_CLASSIFICACAO         VARCHAR(2),   -- 'IN', 'PI', 'PM', 'EV'
    @NIVEL_IMPACTO              INT = NULL,   -- Para IN: 1, 2, 3
    @NIVEL_URGENCIA             INT = NULL,   -- Para IN: 1, 2, 3
    @PRIORIDADE_INTERVENCAO     INT = NULL    -- Para PI: 1, 2, 3
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @PRIORIDADE_INCIDENTE   INT = NULL;
    DECLARE @TEMPO_RESPOSTA         INT = NULL;
    DECLARE @TEMPO_RESOLUCAO        INT = NULL;

    IF @TIPO_CLASSIFICACAO = 'IN'
    BEGIN
        -- Calcular prioridade do incidente: Impacto + Urgência - 1
        SET @PRIORIDADE_INCIDENTE = @NIVEL_IMPACTO + @NIVEL_URGENCIA - 1;

        -- Obter tempos SLA da tabela de referência
        SELECT
            @TEMPO_RESPOSTA  = TEMPO_RESPOSTA_HORAS,
            @TEMPO_RESOLUCAO = TEMPO_RESOLUCAO_HORAS
        FROM MAN_DIC_PRIORIDADE_INCIDENTE
        WHERE PRIORIDADE = @PRIORIDADE_INCIDENTE;
    END

    ELSE IF @TIPO_CLASSIFICACAO = 'PI'
    BEGIN
        -- Obter tempo de resposta da tabela de referência de intervenção
        SELECT @TEMPO_RESPOSTA = TEMPO_RESPOSTA_HORAS
        FROM MAN_DIC_PRIORIDADE_INTERVENCAO
        WHERE NIVEL = @PRIORIDADE_INTERVENCAO;

        -- Pedidos de intervenção não têm tempo máximo de resolução definido no SLA
        SET @TEMPO_RESOLUCAO = NULL;
    END

    -- Atualizar o pedido
    UPDATE MAN_MOV_MANUTENCAO_CAB SET
        TIPO_CLASSIFICACAO_PEDIDO   = @TIPO_CLASSIFICACAO,
        NIVEL_IMPACTO               = @NIVEL_IMPACTO,
        NIVEL_URGENCIA              = @NIVEL_URGENCIA,
        PRIORIDADE_INCIDENTE        = @PRIORIDADE_INCIDENTE,
        PRIORIDADE_INTERVENCAO      = @PRIORIDADE_INTERVENCAO,
        TEMPO_RESPOSTA_HORAS        = @TEMPO_RESPOSTA,
        TEMPO_RESOLUCAO_HORAS       = @TEMPO_RESOLUCAO,
        DATA_ULT_MODIF              = GETDATE()
    WHERE ID_MANUTENCAO_CAB = @ID_MANUTENCAO_CAB;

    -- Retornar os dados calculados
    SELECT
        @PRIORIDADE_INCIDENTE   AS PRIORIDADE_INCIDENTE,
        @TEMPO_RESPOSTA         AS TEMPO_RESPOSTA_HORAS,
        @TEMPO_RESOLUCAO        AS TEMPO_RESOLUCAO_HORAS;
END;

GO

PRINT 'Stored Procedure MAN_ATRIBUIR_PRIORIDADE_PEDIDO criada.';
GO

-- =============================================================================
-- FIM DO SCRIPT
-- =============================================================================
PRINT 'Script MAN_IT_CLASSIFICACAO_PEDIDOS.sql executado com sucesso.';
