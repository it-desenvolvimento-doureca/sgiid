USE [SGIID]
GO
-- =============================================================================
-- Alterações aos SPs de Planos Estratégicos
-- Objectivo: excluir linhas anuladas (PA_MOV_LINHA.INATIVO=1)
--            e associações anuladas (PE_PLANOS_ASSOCIADOS.INATIVO=1)
-- =============================================================================


-- -----------------------------------------------------------------------------
-- 1. PE_GET_ACOES_EM_ATRASO
--    Adicionado: AND ISNULL(ab.INATIVO,0)=0  (associações)
--                AND ISNULL(c.INATIVO,0)=0   (linhas)
-- -----------------------------------------------------------------------------
ALTER PROCEDURE [dbo].[PE_GET_ACOES_EM_ATRASO]
    @ANO        INT,
    @PageNumber INT
AS
BEGIN
    DECLARE @RowsOfPage AS INT = 10

    SELECT
        e.DESCRICAO_PT AS ACAO,
        (SELECT NOME_UTILIZADOR FROM GER_UTILIZADORES y WHERE y.ID_UTILIZADOR = c.RESPONSAVEL) AS RESPONSAVEL,
        f.DESCRICAO AS DEPARTAMENTO,
        DATA_ACCAO AS DATA_OBJETIVO,
        DATEDIFF(DAY, c.DATA_ACCAO, GETDATE()),
        c.DATA_ACCAO
    FROM PE_MOV_CAB a
    LEFT JOIN PE_PLANOS_ASSOCIADOS ab ON ab.ID_PLANO_ESTRATEGICO = a.ID
    LEFT JOIN PA_MOV_CAB b ON b.ID_PLANO_CAB = ab.ID_PLANO_CAB
    LEFT JOIN PA_MOV_LINHA c ON b.ID_PLANO_CAB = c.ID_PLANO_CAB
    LEFT JOIN GT_MOV_TAREFAS d ON d.ID_CAMPO = c.ID_PLANO_LINHA AND d.ID_MODULO = 13 AND d.SUB_MODULO = 'PA'
    LEFT JOIN GT_DIC_TAREFAS e ON e.ID = d.ID_ACCAO
    LEFT JOIN GER_DEPARTAMENTO f ON a.DEPARTAMENTO = f.ID
    WHERE a.ANO_PLANO = @ANO
      AND c.DATA_ACCAO < GETDATE()
      AND d.ESTADO IN ('P','L','E')
      AND a.ESTADO != 'A'
      AND ISNULL(ab.INATIVO, 0) = 0   -- excluir associações anuladas
      AND ISNULL(c.INATIVO, 0) = 0    -- excluir linhas anuladas
    ORDER BY DATA_ACCAO ASC
    OFFSET (@PageNumber - 1) * @RowsOfPage ROWS
    FETCH NEXT @RowsOfPage ROWS ONLY
END
GO


-- -----------------------------------------------------------------------------
-- 2. PE_GET_ANALISE_CONTADORES
--    Adicionado em cada bloco: AND ISNULL(ab.INATIVO,0)=0
--                              AND ISNULL(c.INATIVO,0)=0
-- -----------------------------------------------------------------------------
ALTER PROCEDURE [dbo].[PE_GET_ANALISE_CONTADORES]
    @ANO INT
AS
BEGIN
    DECLARE @RowsOfPage       AS INT = 10
    DECLARE @PLANEADAS        DECIMAL(19,2) = 0
    DECLARE @EXECUCAO_PLANEADA DECIMAL(19,2) = 0
    DECLARE @EXECUCAO_ATUAL   DECIMAL(19,2) = 0
    DECLARE @EM_ATRASO        DECIMAL(19,2) = 0

    -- PLANEADAS
    SELECT @PLANEADAS = COUNT(*)
    FROM PE_MOV_CAB a
    LEFT JOIN PE_PLANOS_ASSOCIADOS ab ON ab.ID_PLANO_ESTRATEGICO = a.ID
    LEFT JOIN PA_MOV_CAB b ON b.ID_PLANO_CAB = ab.ID_PLANO_CAB
    LEFT JOIN PA_MOV_LINHA c ON b.ID_PLANO_CAB = c.ID_PLANO_CAB
    LEFT JOIN GT_MOV_TAREFAS d ON d.ID_CAMPO = c.ID_PLANO_LINHA AND d.ID_MODULO = 13 AND d.SUB_MODULO = 'PA'
    WHERE a.ANO_PLANO = @ANO
      AND a.ESTADO != 'A'
      AND d.ID_TAREFA_PAI IS NULL
      AND d.ID_TAREFA IS NOT NULL
      AND ISNULL(ab.INATIVO, 0) = 0
      AND ISNULL(c.INATIVO, 0) = 0

    -- EXECUCAO PLANEADA
    SELECT @EXECUCAO_PLANEADA = COUNT(*)
    FROM PE_MOV_CAB a
    LEFT JOIN PE_PLANOS_ASSOCIADOS ab ON ab.ID_PLANO_ESTRATEGICO = a.ID
    LEFT JOIN PA_MOV_CAB b ON b.ID_PLANO_CAB = ab.ID_PLANO_CAB
    LEFT JOIN PA_MOV_LINHA c ON b.ID_PLANO_CAB = c.ID_PLANO_CAB
    LEFT JOIN GT_MOV_TAREFAS d ON d.ID_CAMPO = c.ID_PLANO_LINHA AND d.ID_MODULO = 13 AND d.SUB_MODULO = 'PA'
    WHERE a.ANO_PLANO = @ANO
      AND a.ESTADO != 'A'
      AND c.DATA_ACCAO < GETDATE()
      AND d.ID_TAREFA_PAI IS NULL
      AND d.ID_TAREFA IS NOT NULL
      AND ISNULL(ab.INATIVO, 0) = 0
      AND ISNULL(c.INATIVO, 0) = 0

    -- EXECUCAO ATUAL
    SELECT @EXECUCAO_ATUAL = COUNT(*)
    FROM PE_MOV_CAB a
    LEFT JOIN PE_PLANOS_ASSOCIADOS ab ON ab.ID_PLANO_ESTRATEGICO = a.ID
    LEFT JOIN PA_MOV_CAB b ON b.ID_PLANO_CAB = ab.ID_PLANO_CAB
    LEFT JOIN PA_MOV_LINHA c ON b.ID_PLANO_CAB = c.ID_PLANO_CAB
    LEFT JOIN GT_MOV_TAREFAS d ON d.ID_CAMPO = c.ID_PLANO_LINHA AND d.ID_MODULO = 13 AND d.SUB_MODULO = 'PA'
    WHERE a.ANO_PLANO = @ANO
      AND a.ESTADO != 'A'
      AND d.ESTADO = 'C'
      AND d.ID_TAREFA_PAI IS NULL
      AND d.ID_TAREFA IS NOT NULL
      AND ISNULL(ab.INATIVO, 0) = 0
      AND ISNULL(c.INATIVO, 0) = 0

    -- EM ATRASO
    SELECT @EM_ATRASO = COUNT(*)
    FROM PE_MOV_CAB a
    LEFT JOIN PE_PLANOS_ASSOCIADOS ab ON ab.ID_PLANO_ESTRATEGICO = a.ID
    LEFT JOIN PA_MOV_CAB b ON b.ID_PLANO_CAB = ab.ID_PLANO_CAB
    LEFT JOIN PA_MOV_LINHA c ON b.ID_PLANO_CAB = c.ID_PLANO_CAB
    LEFT JOIN GT_MOV_TAREFAS d ON d.ID_CAMPO = c.ID_PLANO_LINHA AND d.ID_MODULO = 13 AND d.SUB_MODULO = 'PA'
    WHERE a.ANO_PLANO = @ANO
      AND c.DATA_ACCAO < GETDATE()
      AND d.ESTADO IN ('P','L','E')
      AND a.ESTADO != 'A'
      AND d.ID_TAREFA_PAI IS NULL
      AND d.ID_TAREFA IS NOT NULL
      AND ISNULL(ab.INATIVO, 0) = 0
      AND ISNULL(c.INATIVO, 0) = 0

    SELECT
        CAST(@PLANEADAS AS DECIMAL(19,0)) AS PLANEADAS,
        CAST(CASE WHEN @PLANEADAS = 0 THEN 0 ELSE (@EXECUCAO_PLANEADA / @PLANEADAS) * 100 END AS DECIMAL(19,0)) AS EXECUCAO_PLANEADA,
        CAST(CASE WHEN @PLANEADAS = 0 THEN 0 ELSE (@EXECUCAO_ATUAL / @PLANEADAS) * 100 END AS DECIMAL(19,0)) AS EXECUCAO_ATUAL,
        CAST(@EM_ATRASO AS DECIMAL(19,0)) AS EM_ATRASO
END
GO


-- -----------------------------------------------------------------------------
-- 3. PE_GET_ANALISE_GRAFICO
--    Adicionado na query interna: AND ISNULL(ab.INATIVO,0)=0
--                                 AND ISNULL(c.INATIVO,0)=0
--    (já existia AND b.ESTADO != 'A' — mantido)
-- -----------------------------------------------------------------------------
ALTER PROCEDURE [dbo].[PE_GET_ANALISE_GRAFICO]
    @ANO INT
AS
BEGIN
    SELECT
        TOTAL, DEPARTAMENTO,
        CAST(((TOTAL - CONCLUIDAS - EMATRASO) / TOTAL) * 100 AS DECIMAL(19,2)) AS PERCENTAGEMPLANEADAS,
        CAST((CONCLUIDAS / TOTAL) * 100 AS DECIMAL(19,2)) AS PERCENTAGEMCONCLUIDAS,
        CAST((EMATRASO / TOTAL) * 100 AS DECIMAL(19,2)) AS PERCENTAGEMEMATRASO,
        CONCLUIDAS, EMATRASO, TOTAL - CONCLUIDAS - EMATRASO
    FROM (
        SELECT
            CAST(COUNT(*) AS DECIMAL(19,2)) TOTAL,
            CAST(SUM(CASE WHEN d.ESTADO IN ('C') THEN 1 ELSE 0 END) AS DECIMAL(19,2)) CONCLUIDAS,
            CAST(SUM(CASE WHEN c.DATA_ACCAO < GETDATE() AND d.ESTADO IN ('P','L','E') THEN 1 ELSE 0 END) AS DECIMAL(19,2)) EMATRASO,
            ISNULL(f.DESCRICAO, 'Sem Departamento') AS DEPARTAMENTO
        FROM PE_MOV_CAB a
        LEFT JOIN PE_PLANOS_ASSOCIADOS ab ON ab.ID_PLANO_ESTRATEGICO = a.ID
        LEFT JOIN PA_MOV_CAB b ON b.ID_PLANO_CAB = ab.ID_PLANO_CAB
        LEFT JOIN PA_MOV_LINHA c ON b.ID_PLANO_CAB = c.ID_PLANO_CAB
        LEFT JOIN GT_MOV_TAREFAS d ON d.ID_CAMPO = c.ID_PLANO_LINHA AND d.ID_MODULO = 13 AND d.SUB_MODULO = 'PA'
        LEFT JOIN GT_DIC_TAREFAS e ON e.ID = d.ID_ACCAO
        LEFT JOIN GER_DEPARTAMENTO f ON a.DEPARTAMENTO = f.ID
        WHERE a.ANO_PLANO = @ANO
          AND d.ESTADO IN ('P','L','E','C')
          AND a.ESTADO != 'A'
          AND b.ESTADO != 'A'
          AND d.ID_TAREFA_PAI IS NULL
          AND d.ID_TAREFA IS NOT NULL
          AND ISNULL(ab.INATIVO, 0) = 0   -- excluir associações anuladas
          AND ISNULL(c.INATIVO, 0) = 0    -- excluir linhas anuladas
        GROUP BY ISNULL(f.DESCRICAO, 'Sem Departamento')
    ) tab
    ORDER BY DEPARTAMENTO ASC
END
GO


-- -----------------------------------------------------------------------------
-- 4. PE_GET_ULTIMAS_ACOES_CONCLUIDAS
--    Adicionado: AND ISNULL(ab.INATIVO,0)=0
--                AND ISNULL(c.INATIVO,0)=0
-- -----------------------------------------------------------------------------
ALTER PROCEDURE [dbo].[PE_GET_ULTIMAS_ACOES_CONCLUIDAS]
    @ANO        INT,
    @PageNumber INT
AS
BEGIN
    DECLARE @RowsOfPage AS INT = 10

    SELECT
        e.DESCRICAO_PT AS ACAO,
        (SELECT NOME_UTILIZADOR FROM GER_UTILIZADORES y WHERE y.ID_UTILIZADOR = c.RESPONSAVEL) AS RESPONSAVEL,
        f.DESCRICAO AS DEPARTAMENTO,
        DATA_ACCAO AS DATA_OBJETIVO,
        d.DATA_CONCLUSAO,
        d.ID_TAREFA
    FROM PE_MOV_CAB a
    LEFT JOIN PE_PLANOS_ASSOCIADOS ab ON ab.ID_PLANO_ESTRATEGICO = a.ID
    LEFT JOIN PA_MOV_CAB b ON b.ID_PLANO_CAB = ab.ID_PLANO_CAB
    LEFT JOIN PA_MOV_LINHA c ON b.ID_PLANO_CAB = c.ID_PLANO_CAB
    LEFT JOIN GT_MOV_TAREFAS d ON d.ID_CAMPO = c.ID_PLANO_LINHA AND d.ID_MODULO = 13 AND d.SUB_MODULO = 'PA'
    LEFT JOIN GT_DIC_TAREFAS e ON e.ID = d.ID_ACCAO
    LEFT JOIN GER_DEPARTAMENTO f ON a.DEPARTAMENTO = f.ID
    WHERE a.ANO_PLANO = @ANO
      AND d.ESTADO IN ('C')
      AND a.ESTADO != 'A'
      AND ISNULL(ab.INATIVO, 0) = 0   -- excluir associações anuladas
      AND ISNULL(c.INATIVO, 0) = 0    -- excluir linhas anuladas
    ORDER BY DATA_CONCLUSAO DESC
    OFFSET (@PageNumber - 1) * @RowsOfPage ROWS
    FETCH NEXT @RowsOfPage ROWS ONLY
END
GO
