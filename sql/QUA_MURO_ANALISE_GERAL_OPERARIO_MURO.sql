USE [SGIID]
GO

-- ============================================================
--  Analise Geral por Operario do Muro                2026-07-31
--
--  Novo separador (4o) na pagina "Analises Muro de Qualidade".
--
--  A Analise Geral existente atribui as pecas a operaria
--  DECLARADA NA CAIXA (RP_OF_OPERARIOS_CAIXA) -- a revisao.
--  Esta nova analise atribui as pecas ao operario que
--  REGISTOU O TRABALHO NO MURO (RP_OF_OP_FUNC.ID_UTZ_CRIA).
--
--  Objectos criados:
--    1. VW_QUA_MURO_BASE_TRABALHO
--    2. SP_QUA_MURO_QUALIDADE_ANALISE_GERAL_MURO
--
--  NAO altera VW_QUA_MURO_BASE nem
--  SP_QUA_MURO_QUALIDADE_ANALISE_GERAL/_PROGRESSO/_REFERENCIA
--  /_CADENCIAS -- essas continuam a servir os separadores 1-3 e 5.
-- ============================================================


-- ============================================================
--  1. VW_QUA_MURO_BASE_TRABALHO
--
--  Clone de VW_QUA_MURO_BASE com tres diferencas:
--
--   a) func_por_opcab (nova CTE): colapsa RP_OF_OP_FUNC a uma
--      linha por (ID_OP_CAB, ID_UTZ_CRIA). Indispensavel -- um
--      ID_OP_CAB pode ter varias linhas em RP_OF_OP_FUNC (varias
--      sessoes de trabalho do mesmo operario, com DATA_INI/HORA_INI
--      ... DATA_FIM/HORA_FIM); sem colapsar, as pecas duplicavam.
--
--   b) ops_por_of: mantem-se ao nivel do ID_OF_CAB (como na view
--      original), mas conta operarios distintos de RP_OF_OP_FUNC
--      via RP_OF_OP_CAB em vez de RP_OF_OPERARIOS_CAIXA.
--      Pre-calculado antes dos JOINs de referencias/defeitos,
--      senao o COUNT contaria linhas em vez de operarios.
--
--   c) eixo do operario: f.ID_UTZ_CRIA / f.NOME_UTZ_CRIA, ligado
--      por ID_OP_CAB -- dentro da cadeia de JOINs ja existente,
--      ao contrario de RP_OF_OPERARIOS_CAIXA que era um cartesiano
--      ao nivel do ID_OF_CAB.
--
--  Mantem-se tudo o resto (flags tristate, datas de reclamacao/
--  devolucao, mes, SEC_NUM, ETIQUETA, tempo_seg, WHERE) para que
--  a SP possa ser copia literal da original.
-- ============================================================
IF OBJECT_ID('dbo.VW_QUA_MURO_BASE_TRABALHO', 'V') IS NOT NULL
    DROP VIEW [dbo].[VW_QUA_MURO_BASE_TRABALHO];
GO

CREATE VIEW [dbo].[VW_QUA_MURO_BASE_TRABALHO]
AS
    -- uma linha por (operacao, operario): elimina as multiplas
    -- sessoes de trabalho do mesmo operario na mesma operacao
    WITH func_por_opcab AS (
        SELECT
            ID_OP_CAB,
            ID_UTZ_CRIA,
            MAX(NOME_UTZ_CRIA) AS NOME_UTZ_CRIA
        FROM RP_OF_OP_FUNC
        WHERE ISNULL(ESTADO, '') <> 'A'
        GROUP BY ID_OP_CAB, ID_UTZ_CRIA
    ),
    -- nr de operarios distintos por OF (mesmo nivel da view original)
    ops_por_of AS (
        SELECT
            a.ID_OF_CAB,
            COUNT(DISTINCT b.ID_UTZ_CRIA) AS num_operarios
        FROM RP_OF_OP_CAB a
        INNER JOIN RP_OF_OP_FUNC b ON b.ID_OP_CAB = a.ID_OP_CAB
        WHERE ISNULL(b.ESTADO, '') <> 'A'
        GROUP BY a.ID_OF_CAB
    )
    SELECT
        -- identificadores
        c.ID_OF_CAB,
        c.DATA_HORA_CRIA,
        c.ETIQUETA,
        c.DATCRE_ETIQUETA                                                             AS data_hora_cria_etiqueta,
        CONVERT(char(7), c.DATA_HORA_CRIA, 120)                                       AS mes,
        c.SEC_NUM,

        -- flags de contexto da OF
        c.TESTE_DIMENSIONAL,
        c.OPERARIO_FORMACAO,
        c.ORIGEM_RECLAMACAO,
        c.STOCK_ETIQUETA_30,
        c.DEFEITOS_INJECAO,
        c.DEVOLUCAO_CLIENTE,
        c.GAMA_EMBALAGEM_INCORRETA,
        c.MODO_DEGRADADO,
        c.ENSAIO_DIA,
        c.VERIFICACAO_QUANT_EMBALAGEM,
        c.DATA_ORIGEM_RECLAMACAO,
        c.DATA_DEVOLUCAO_CLIENTE,
        c.MODO_DEGRADADO_MOTIVO,

        -- operario: quem REGISTOU O TRABALHO no muro
        -- (na VW_QUA_MURO_BASE era f.ID_UTZ / f.NOME_UTZ de
        --  RP_OF_OPERARIOS_CAIXA -- a operaria declarada na caixa)
        f.ID_UTZ_CRIA                                                                 AS id_colaborador,
        f.NOME_UTZ_CRIA                                                               AS colaborador,
        r.COD_SECTOR,

        -- referencia / linha de operacao
        l.REF_NUM                                                                     AS referencia,
        l.REF_DES                                                                     AS referencia_des,
        l.ID_OP_LIN,

        -- pecas controladas ao nivel da referencia (ID_OP_LIN):
        -- atribuidas apenas a primeira ocorrencia de cada ID_OP_LIN
        -- para evitar duplicacao quando existem varios defeitos.
        CASE
            WHEN ROW_NUMBER() OVER (PARTITION BY l.ID_OP_LIN, f.ID_UTZ_CRIA ORDER BY d.COD_DEF) = 1
            THEN ISNULL(l.QUANT_BOAS_TOTAL_M2, 0) + ISNULL(l.QUANT_DEF_TOTAL_M2, 0)
            ELSE 0
        END                                                                           AS pecas_controladas_lin,

        -- pecas rejeitadas ao nivel do defeito (cada COD_DEF tem o seu valor)
        ISNULL(d.QUANT_DEF_M2, 0)                                                     AS pecas_rejeitadas,

        CASE
            WHEN ROW_NUMBER() OVER (PARTITION BY l.ID_OP_LIN, f.ID_UTZ_CRIA ORDER BY d.COD_DEF) = 1
            THEN ISNULL(l.QUANT_BOAS_TOTAL_M2, 0)
            ELSE 0
        END                                                                           AS pecas_boas,

        -- defeito
        d.COD_DEF,
        d.DESC_DEF,

        -- tempo ao nivel da referencia (mesmo criterio das pecas controladas)
        CASE
            WHEN ROW_NUMBER() OVER (PARTITION BY l.ID_OP_LIN, f.ID_UTZ_CRIA ORDER BY d.COD_DEF) = 1
            THEN dbo.ConvertExceedingTimeToFloat(o.TEMPO_EXEC_TOTAL_M2)
            ELSE 0
        END                                                                           AS tempo_seg,

        -- flag: OF com exatamente 1 operario distinto a registar trabalho
        CASE
            WHEN op.num_operarios = 1 THEN 1
            ELSE 0
        END                                                                           AS operario_unico,
        o.ID_OP_CAB
    FROM RP_OF_CAB c
        -- flag de operario unico ja pre-calculado
        LEFT JOIN ops_por_of op        ON op.ID_OF_CAB       = c.ID_OF_CAB
        LEFT JOIN RP_OF_OP_CAB o       ON o.ID_OF_CAB        = c.ID_OF_CAB
        LEFT JOIN RP_OF_OP_LIN l       ON l.ID_OP_CAB        = o.ID_OP_CAB
        LEFT JOIN RP_OF_DEF_LIN d      ON d.ID_OP_LIN        = l.ID_OP_LIN
        -- eixo do operario: por ID_OP_CAB, dentro da cadeia existente
        LEFT JOIN func_por_opcab f     ON f.ID_OP_CAB        = o.ID_OP_CAB
        LEFT JOIN RH_FUNCIONARIOS r    ON r.COD_FUNCIONARIO  = f.ID_UTZ_CRIA
    WHERE c.OP_COD_ORIGEM = '100'
      AND c.ESTADO <> 'A'
      AND l.REF_NUM IS NOT NULL;
GO


-- ============================================================
--  2. SP_QUA_MURO_QUALIDADE_ANALISE_GERAL_MURO
--
--  Copia literal de SP_QUA_MURO_QUALIDADE_ANALISE_GERAL:
--  mesmos 23 parametros na mesma ordem, mesmas janelas de
--  ordenacao, mesmo ORDER BY e mesmas colunas de saida na mesma
--  posicao (o frontend le o resultado por indice).
--
--  Unica alteracao: FROM VW_QUA_MURO_BASE_TRABALHO.
--
--  Agrupamento: mes -> colaborador -> referencia -> etiqueta -> defeito
--
--  Nota sobre ordem_defeito:
--    A taxa de rejeicao de um defeito e calculada como
--      rejeitadas do defeito / controladas da etiqueta (nivel acima)
--    porque as controladas pertencem a referencia/etiqueta, nao ao
--    defeito -- usar controladas do proprio defeito inflaria o
--    denominador quando uma etiqueta tem varios defeitos.
-- ============================================================
IF OBJECT_ID('dbo.SP_QUA_MURO_QUALIDADE_ANALISE_GERAL_MURO', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[SP_QUA_MURO_QUALIDADE_ANALISE_GERAL_MURO];
GO

CREATE PROCEDURE [dbo].[SP_QUA_MURO_QUALIDADE_ANALISE_GERAL_MURO]
    @DATA_INI                     VARCHAR(10),
    @DATA_FIM                     VARCHAR(10),
    @UNIDADE                      VARCHAR(MAX),
    @SECTOR                       VARCHAR(MAX),
    @FUNCIONARIO                  VARCHAR(MAX),
    @FAMILIA_DEFEITO              VARCHAR(MAX),
    @REFERENCIA                   VARCHAR(MAX),
    @ETIQUETA                     VARCHAR(MAX),
    @TESTE_DIMENSIONAL            BIT,
    @OPERARIO_FORMACAO            BIT,
    @ORIGEM_RECLAMACAO            BIT,
    @STOCK_ETIQUETA_30            BIT,
    @DEFEITOS_INJECAO             BIT,
    @DEVOLUCAO_CLIENTE            BIT,
    @GAMA_EMBALAGEM_INCORRETA     BIT,
    @MODO_DEGRADADO               BIT,
    @ENSAIO_DIA                   BIT,
    @VERIFICACAO_QUANT_EMBALAGEM  BIT,
    @DATA_ORIGEM_RECLAMACAO_INI   DATE,
    @DATA_ORIGEM_RECLAMACAO_FIM   DATE,
    @DATA_DEVOLUCAO_CLIENTE_INI   DATE,
    @DATA_DEVOLUCAO_CLIENTE_FIM   DATE,
    @MOTIVO_MODO_DEGRADADO        VARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @DT_INI DATETIME = CASE WHEN @DATA_INI IS NOT NULL AND @DATA_INI <> '' THEN CAST(@DATA_INI AS DATETIME) ELSE NULL END;
    DECLARE @DT_FIM DATETIME = CASE WHEN @DATA_FIM IS NOT NULL AND @DATA_FIM <> '' THEN DATEADD(SECOND, 86399, CAST(@DATA_FIM AS DATETIME)) ELSE NULL END;

    SELECT *
    FROM (
        -- Nivel 3: janelas de ordenacao por taxa de rejeicao em cada nivel.
        SELECT *,
            SUM(pecas_rejeitadas) OVER (PARTITION BY mes)
                / NULLIF(SUM(pecas_controladas) OVER (PARTITION BY mes), 0)
                                                                                              AS ordem_mes,
            SUM(pecas_rejeitadas) OVER (PARTITION BY mes, id_colaborador)
                / NULLIF(SUM(pecas_controladas) OVER (PARTITION BY mes, id_colaborador), 0)
                                                                                              AS ordem_colaborador,
            SUM(pecas_rejeitadas) OVER (PARTITION BY mes, id_colaborador, referencia)
                / NULLIF(SUM(pecas_controladas) OVER (PARTITION BY mes, id_colaborador, referencia), 0)
                                                                                              AS ordem_referencia,
            SUM(pecas_rejeitadas) OVER (PARTITION BY mes, id_colaborador, referencia, ETIQUETA)
                / NULLIF(SUM(pecas_controladas) OVER (PARTITION BY mes, id_colaborador, referencia, ETIQUETA), 0)
                                                                                              AS ordem_etiqueta,
            -- defeito: rejeitadas do defeito / controladas da etiqueta (denominador correto)
            SUM(pecas_rejeitadas) OVER (PARTITION BY mes, id_colaborador, referencia, ETIQUETA, COD_DEF)
                / NULLIF(SUM(pecas_controladas) OVER (PARTITION BY mes, id_colaborador, referencia, ETIQUETA), 0)
                                                                                              AS ordem_defeito
        FROM (
            -- Nivel 2: agrega por mes + colaborador + referencia + etiqueta + defeito
            SELECT
                mes,
                id_colaborador,
                colaborador,
                referencia,
                referencia_des,
                ETIQUETA,
                COD_DEF,
                DESC_DEF,
                SUM(pecas_controladas_lin) AS pecas_controladas,
                SUM(pecas_rejeitadas)      AS pecas_rejeitadas,
                SUM(tempo_seg)             AS tempo,
                data_hora_cria_etiqueta
            FROM VW_QUA_MURO_BASE_TRABALHO      -- <<< UNICA ALTERACAO face a SP original
            WHERE operario_unico = 1
              AND (@DT_INI IS NULL OR @DT_FIM IS NULL OR DATA_HORA_CRIA BETWEEN @DT_INI AND @DT_FIM)
              AND (@UNIDADE IS NULL OR @UNIDADE = ''
                   OR SEC_NUM IN (SELECT value FROM STRING_SPLIT(@UNIDADE, ',')))
              AND (@SECTOR IS NULL OR @SECTOR = ''
                   OR COD_SECTOR IN (SELECT value FROM STRING_SPLIT(@SECTOR, ',')))
              AND (@REFERENCIA IS NULL OR @REFERENCIA = ''
                   OR referencia IN (SELECT value FROM STRING_SPLIT(@REFERENCIA, ',')))
              AND (@FUNCIONARIO IS NULL OR @FUNCIONARIO = ''
                   OR id_colaborador IN (SELECT value FROM STRING_SPLIT(@FUNCIONARIO, ',')))
              AND (@FAMILIA_DEFEITO IS NULL OR @FAMILIA_DEFEITO = ''
                   OR LEFT(COD_DEF, 2) IN (SELECT value FROM STRING_SPLIT(@FAMILIA_DEFEITO, ',')))
              AND (@ETIQUETA IS NULL OR @ETIQUETA = ''
                   OR ETIQUETA LIKE '%' + @ETIQUETA + '%')
              AND (@MOTIVO_MODO_DEGRADADO IS NULL OR @MOTIVO_MODO_DEGRADADO = ''
                   OR MODO_DEGRADADO_MOTIVO LIKE '%' + @MOTIVO_MODO_DEGRADADO + '%')
              AND (@TESTE_DIMENSIONAL           IS NULL OR ISNULL(TESTE_DIMENSIONAL,0)           = @TESTE_DIMENSIONAL)
              AND (@OPERARIO_FORMACAO           IS NULL OR ISNULL(OPERARIO_FORMACAO,0)           = @OPERARIO_FORMACAO)
              AND (@ORIGEM_RECLAMACAO           IS NULL OR ISNULL(ORIGEM_RECLAMACAO,0)           = @ORIGEM_RECLAMACAO)
              AND (@STOCK_ETIQUETA_30           IS NULL OR ISNULL(STOCK_ETIQUETA_30,0)           = @STOCK_ETIQUETA_30)
              AND (@DEFEITOS_INJECAO            IS NULL OR ISNULL(DEFEITOS_INJECAO,0)            = @DEFEITOS_INJECAO)
              AND (@DEVOLUCAO_CLIENTE           IS NULL OR ISNULL(DEVOLUCAO_CLIENTE,0)           = @DEVOLUCAO_CLIENTE)
              AND (@GAMA_EMBALAGEM_INCORRETA    IS NULL OR ISNULL(GAMA_EMBALAGEM_INCORRETA,0)    = @GAMA_EMBALAGEM_INCORRETA)
              AND (@MODO_DEGRADADO              IS NULL OR ISNULL(MODO_DEGRADADO,0)              = @MODO_DEGRADADO)
              AND (@ENSAIO_DIA                  IS NULL OR ISNULL(ENSAIO_DIA,0)                  = @ENSAIO_DIA)
              AND (@VERIFICACAO_QUANT_EMBALAGEM IS NULL OR ISNULL(VERIFICACAO_QUANT_EMBALAGEM,0) = @VERIFICACAO_QUANT_EMBALAGEM)
              AND (@DATA_ORIGEM_RECLAMACAO_INI IS NULL OR @DATA_ORIGEM_RECLAMACAO_FIM IS NULL
                   OR DATA_ORIGEM_RECLAMACAO BETWEEN @DATA_ORIGEM_RECLAMACAO_INI AND @DATA_ORIGEM_RECLAMACAO_FIM)
              AND (@DATA_DEVOLUCAO_CLIENTE_INI IS NULL OR @DATA_DEVOLUCAO_CLIENTE_FIM IS NULL
                   OR DATA_DEVOLUCAO_CLIENTE BETWEEN @DATA_DEVOLUCAO_CLIENTE_INI AND @DATA_DEVOLUCAO_CLIENTE_FIM)
            GROUP BY
                mes,
                id_colaborador,
                colaborador,
                referencia,
                referencia_des,
                ETIQUETA,
                COD_DEF,
                DESC_DEF,
                data_hora_cria_etiqueta
        ) b
    ) c
    ORDER BY
        ordem_mes         DESC,
        ordem_colaborador DESC,
        ordem_referencia  DESC,
        ordem_etiqueta    DESC,
        ordem_defeito     DESC;

END
GO


-- ============================================================
--  VALIDACOES (executar manualmente apos criar os objectos)
-- ============================================================
/*
-- 1. Nao-duplicacao: o total de pecas da view nova nao pode exceder
--    o total da base. Se exceder, func_por_opcab nao colapsou tudo.
SELECT ID_OP_CAB, ID_UTZ_CRIA, COUNT(*) AS n
FROM RP_OF_OP_FUNC
GROUP BY ID_OP_CAB, ID_UTZ_CRIA
HAVING COUNT(*) > 1;

SELECT SUM(pecas_controladas_lin) AS controladas,
       SUM(pecas_rejeitadas)      AS rejeitadas,
       SUM(tempo_seg)             AS tempo
FROM VW_QUA_MURO_BASE_TRABALHO;

-- 2. Cobertura: quanto e que o filtro operario_unico = 1 exclui,
--    comparado com a view original.
SELECT 'TRABALHO' AS view_, operario_unico, COUNT(DISTINCT ID_OF_CAB) AS ofs
FROM VW_QUA_MURO_BASE_TRABALHO GROUP BY operario_unico
UNION ALL
SELECT 'CAIXA', operario_unico, COUNT(DISTINCT ID_OF_CAB)
FROM VW_QUA_MURO_BASE GROUP BY operario_unico;

-- 3. Dominio do ID: id_colaborador tem de casar com RH_FUNCIONARIOS.
--    Se devolver > 0, ID_UTZ_CRIA nao e COD_FUNCIONARIO (sera
--    GER_UTILIZADORES.ID_UTILIZADOR) e os joins tem de mudar.
SELECT COUNT(*) AS orfaos
FROM (SELECT DISTINCT id_colaborador FROM VW_QUA_MURO_BASE_TRABALHO WHERE id_colaborador IS NOT NULL) v
LEFT JOIN RH_FUNCIONARIOS f ON f.COD_FUNCIONARIO = v.id_colaborador
WHERE f.COD_FUNCIONARIO IS NULL;

-- 4. SP: comparar com a original nos mesmos filtros.
EXEC SP_QUA_MURO_QUALIDADE_ANALISE_GERAL_MURO
    '2026-01-01','2026-07-31',NULL,NULL,NULL,NULL,NULL,NULL,
    NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
    NULL,NULL,NULL,NULL,NULL;
*/
