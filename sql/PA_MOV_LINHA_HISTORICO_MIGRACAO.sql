USE [SGIID]
GO
-- =============================================================================
-- Migração GT_LOGS → PA_MOV_LINHA_HISTORICO
-- Objectivo: popular PA_MOV_LINHA_HISTORICO com histórico de alterações de data
--            já existente em GT_LOGS, filtrando apenas registos com justificação
-- =============================================================================

-- Pré-visualização (não insere — correr primeiro para validar)
/*
SELECT
    pl.ID_PLANO_CAB,
    pl.ID_PLANO_LINHA,
    'ALTERACAO_DATA'                                    AS TIPO_ALTERACAO,
    gl.DESCRICAO + ISNULL(' — ' + gl.JUSTIFICACAO, '') AS DESCRICAO,
    NULL                                                AS ESTADO_PLANO_MOMENTO,
    gl.DATA_CRIA,
    gl.UTZ_CRIA
FROM GT_LOGS gl
INNER JOIN GT_MOV_TAREFAS gt ON gt.ID_TAREFA = gl.ID_TAREFA
    AND gt.ID_MODULO = 13
    AND gt.SUB_MODULO = 'PA'
    AND gt.ID_TAREFA_PAI IS NULL
INNER JOIN PA_MOV_LINHA pl ON pl.ID_PLANO_LINHA = gt.ID_CAMPO
WHERE gl.JUSTIFICACAO IS NOT NULL
  AND LTRIM(RTRIM(gl.JUSTIFICACAO)) != ''
ORDER BY gl.DATA_CRIA;
*/

-- Inserção efectiva
INSERT INTO PA_MOV_LINHA_HISTORICO
    (ID_PLANO_CAB, ID_PLANO_LINHA, TIPO_ALTERACAO, DESCRICAO, ESTADO_PLANO_MOMENTO, DATA_CRIA, UTZ_CRIA)
SELECT
    pl.ID_PLANO_CAB,
    pl.ID_PLANO_LINHA,
    'ALTERACAO_DATA',
    gl.DESCRICAO + ISNULL(' — ' + gl.JUSTIFICACAO, ''),
    NULL,
    gl.DATA_CRIA,
    gl.UTZ_CRIA
FROM GT_LOGS gl
INNER JOIN GT_MOV_TAREFAS gt ON gt.ID_TAREFA = gl.ID_TAREFA
    AND gt.ID_MODULO = 13
    AND gt.SUB_MODULO = 'PA'
    AND gt.ID_TAREFA_PAI IS NULL
INNER JOIN PA_MOV_LINHA pl ON pl.ID_PLANO_LINHA = gt.ID_CAMPO
WHERE gl.JUSTIFICACAO IS NOT NULL
  AND LTRIM(RTRIM(gl.JUSTIFICACAO)) != '';

PRINT CAST(@@ROWCOUNT AS VARCHAR) + ' registos inseridos em PA_MOV_LINHA_HISTORICO.';
GO
