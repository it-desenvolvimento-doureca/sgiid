-- =============================================================================
-- ALTERAÇÕES BD — Planos Estratégicos / Planos de Ação
-- Data: 2026-03-27
-- =============================================================================

-- -----------------------------------------------------------------------------
-- 1. PE_PLANOS_ASSOCIADOS — soft delete da associação PE ↔ PA
-- -----------------------------------------------------------------------------
ALTER TABLE PE_PLANOS_ASSOCIADOS ADD INATIVO    BIT      NOT NULL DEFAULT 0;
ALTER TABLE PE_PLANOS_ASSOCIADOS ADD DATA_ANULA DATETIME NULL;
ALTER TABLE PE_PLANOS_ASSOCIADOS ADD UTZ_ANULA  INT      NULL;

-- -----------------------------------------------------------------------------
-- 2. PA_MOV_LINHA — soft delete de linhas
-- -----------------------------------------------------------------------------
ALTER TABLE PA_MOV_LINHA ADD INATIVO    BIT      NOT NULL DEFAULT 0;
ALTER TABLE PA_MOV_LINHA ADD DATA_ANULA DATETIME NULL;
ALTER TABLE PA_MOV_LINHA ADD UTZ_ANULA  INT      NULL;

-- -----------------------------------------------------------------------------
-- 3. PA_MOV_CAB_HISTORICO — histórico de alterações ao plano de ação
-- -----------------------------------------------------------------------------
CREATE TABLE PA_MOV_CAB_HISTORICO (
    ID              INT          IDENTITY(1,1) PRIMARY KEY,
    ID_PLANO_CAB    INT          NOT NULL,
    TIPO_ALTERACAO  VARCHAR(50)  NOT NULL,   -- 'DATA_OBJETIVO' | 'ESTADO' | 'ADICAO_LINHA'
    VALOR_ANTERIOR  VARCHAR(200) NULL,
    VALOR_NOVO      VARCHAR(200) NULL,
    JUSTIFICACAO    VARCHAR(500) NULL,
    ESTADO_PE       VARCHAR(5)   NULL,       -- estado do PE_MOV_CAB no momento
    DATA_CRIA       DATETIME     NOT NULL DEFAULT GETDATE(),
    UTZ_CRIA        INT          NOT NULL
);

-- -----------------------------------------------------------------------------
-- 4. PA_MOV_LINHA_HISTORICO — histórico de alterações às linhas
-- -----------------------------------------------------------------------------
CREATE TABLE PA_MOV_LINHA_HISTORICO (
    ID                   INT          IDENTITY(1,1) PRIMARY KEY,
    ID_PLANO_CAB         INT          NOT NULL,
    ID_PLANO_LINHA       INT          NULL,      -- null para adições antes de gravar
    TIPO_ALTERACAO       VARCHAR(50)  NOT NULL,  -- 'ADICAO' | 'ANULACAO' | 'ALTERACAO_DATA'
    DESCRICAO            VARCHAR(500) NULL,
    ESTADO_PLANO_MOMENTO VARCHAR(5)   NULL,      -- estado do PE_MOV_CAB no momento
    DATA_CRIA            DATETIME     NOT NULL DEFAULT GETDATE(),
    UTZ_CRIA             INT          NOT NULL
);

-- =============================================================================
-- STORED PROCEDURES — alterações necessárias
-- (obter definição actual com: sp_helptext 'PE_GET_PLANOS_ESTRATEGICOS')
-- =============================================================================

-- -----------------------------------------------------------------------------
-- 5. PE_GET_PLANOS_ESTRATEGICOS
--    Alterações necessárias no corpo do SP:
--
--    a) Excluir associações anuladas — na JOIN a PE_PLANOS_ASSOCIADOS:
--       ANTES:  left join PE_PLANOS_ASSOCIADOS xb ... on xa.ID = xb.ID_PLANO_ESTRATEGICO
--       DEPOIS: left join PE_PLANOS_ASSOCIADOS xb ... on xa.ID = xb.ID_PLANO_ESTRATEGICO
--                         AND ISNULL(xb.INATIVO, 0) = 0
--
--    b) Excluir linhas anuladas — na JOIN a PA_MOV_LINHA:
--       ANTES:  left join PA_MOV_LINHA b ... on a.ID_PLANO_CAB = b.ID_PLANO_CAB
--       DEPOIS: left join PA_MOV_LINHA b ... on a.ID_PLANO_CAB = b.ID_PLANO_CAB
--                         AND ISNULL(b.INATIVO, 0) = 0
--
--    c) Adicionar 2 colunas no SELECT (índices 36 e 37 no frontend):
--       (já devem vir DEPOIS do índice 35 = subtarefas count)
-- -----------------------------------------------------------------------------

-- Fragmento das colunas a adicionar no SELECT final do SP:
/*
,CASE WHEN EXISTS (
    SELECT 1 FROM PA_MOV_CAB_HISTORICO h
    WHERE h.ID_PLANO_CAB = a.ID_PLANO_CAB
      AND h.TIPO_ALTERACAO = 'DATA_OBJETIVO'
) THEN 1 ELSE 0 END AS TEM_ALTERACAO_DATA   -- índice [36]

,CASE WHEN EXISTS (
    SELECT 1 FROM PA_MOV_LINHA l
    WHERE l.ID_PLANO_CAB = a.ID_PLANO_CAB
      AND ISNULL(l.INATIVO, 0) = 1
) THEN 1 ELSE 0 END AS TEM_LINHA_ANULADA     -- índice [37]
*/

-- d) Garantir ordenação: ORDER BY xa.DATA_CRIA DESC (já existe — confirmar)

-- -----------------------------------------------------------------------------
-- 6. PE_GET_ANALISE_CONTADORES
--    Excluir linhas/associações anuladas dos contadores:
--    • Nas JOINs a PA_MOV_LINHA: adicionar AND ISNULL(b.INATIVO,0)=0
--    • Nas JOINs a PE_PLANOS_ASSOCIADOS: adicionar AND ISNULL(xb.INATIVO,0)=0
-- -----------------------------------------------------------------------------

-- -----------------------------------------------------------------------------
-- 7. PE_GET_ANALISE_GRAFICO
--    Mesmas alterações de exclusão que PE_GET_ANALISE_CONTADORES.
-- -----------------------------------------------------------------------------

-- -----------------------------------------------------------------------------
-- 8. PE_GET_ACOES_EM_ATRASO
--    Excluir linhas anuladas:
--    • Na JOIN a PA_MOV_LINHA: adicionar AND ISNULL(b.INATIVO,0)=0
--    • No WHERE (se existir filtro directo): adicionar AND ISNULL(b.INATIVO,0)=0
-- -----------------------------------------------------------------------------

-- -----------------------------------------------------------------------------
-- 9. PE_GET_ULTIMAS_ACOES_CONCLUIDAS
--    Excluir linhas anuladas:
--    • Na JOIN a PA_MOV_LINHA: adicionar AND ISNULL(b.INATIVO,0)=0
-- -----------------------------------------------------------------------------

-- -----------------------------------------------------------------------------
-- 10. PE_GRAVAR_SNAPSHOT_SEMANAL
--    Verificar:
--    • Cálculos de % conclusão excluem linhas com INATIVO=1
--    • Cálculos excluem associações PE_PLANOS_ASSOCIADOS com INATIVO=1
--    • O SQL Agent Job está agendado e ativo (verificar via SQL Server Agent)
-- -----------------------------------------------------------------------------

-- Script de diagnóstico — extrai definição dos SPs para análise:
/*
SELECT OBJECT_NAME(object_id) AS SP_NAME, definition
FROM sys.sql_modules
WHERE OBJECT_NAME(object_id) IN (
    'PE_GET_PLANOS_ESTRATEGICOS',
    'PE_GET_ANALISE_CONTADORES',
    'PE_GET_ANALISE_GRAFICO',
    'PE_GET_ACOES_EM_ATRASO',
    'PE_GET_ULTIMAS_ACOES_CONCLUIDAS',
    'PE_GRAVAR_SNAPSHOT_SEMANAL'
)
ORDER BY SP_NAME;
*/
