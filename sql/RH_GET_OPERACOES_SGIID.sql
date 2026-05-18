USE [sgiid]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE OR ALTER PROCEDURE [dbo].[RH_GET_OPERACOES_SGIID]
    @DATA          DATE,
    @DATA2         DATE,
    @Ativo         VARCHAR(5)   = NULL,   -- 'true', 'false' ou NULL
    @TipoCadencia  VARCHAR(20)  = NULL,   -- 'standard' ou 'definidas'
    @Sector        VARCHAR(MAX) = NULL,   -- ex: '1,2,3'
    @Operario      INT          = NULL,
    @Admin         BIT          = 0,
    @SectorAcesso  VARCHAR(MAX) = NULL,   -- ex: '1,2,3'
    @HORA          VARCHAR(5)   = NULL,
    @HORA2         VARCHAR(5)   = NULL
AS
BEGIN
    SET NOCOUNT ON;

    IF OBJECT_ID('tempdb..#ResultBase') IS NOT NULL DROP TABLE #ResultBase;

    DECLARE @HoraIni VARCHAR(5) = ISNULL(@HORA,  '00:00');
    DECLARE @HoraFim VARCHAR(5) = ISNULL(@HORA2, '23:59');

    -- JOIN dinâmico apenas para o tipo de cadência (única parte variável)
    DECLARE @joinTipoCadencia NVARCHAR(MAX) = N'';

    IF @TipoCadencia = 'standard'
        SET @joinTipoCadencia = N'
        LEFT JOIN (
            SELECT prc.proref, gop.opeqteref AS PecasHora, gop.opecod, gop.openum
            FROM SILVER_BI.dbo.SDTPRC prc
            LEFT JOIN SILVER_BI.dbo.SDTPRD prd ON prd.indnumenr = prc.indnumenr
            LEFT JOIN SILVER_BI.dbo.SDTGOP gop ON gop.gamcod = prd.gamcod
            GROUP BY prc.proref, gop.opeqteref, gop.opecod, gop.openum
        ) k ON k.proref = r.REF_NUM
           AND CAST(k.opecod AS VARCHAR(20)) = r.OP_COD_ORIGEM
           AND CAST(k.openum AS VARCHAR(20)) = r.OP_NUM ';
    ELSE
        SET @joinTipoCadencia = N'
        LEFT JOIN SILVER.dbo.SOFA sofa ON sofa.OFNUM = r.OF_NUM
        LEFT JOIN SILVER.dbo.SOFD sofd ON sofd.OFANUMENR = sofa.OFANUMENR
                                      AND sofd.OPECOD    = r.OP_COD_ORIGEM
                                      AND CAST(sofd.OPENUM AS VARCHAR(20)) = r.OP_NUM
        LEFT JOIN (
            SELECT CAST(d.opecod AS VARCHAR(20)) AS opecod, MIN(d.opeqteref) AS PecasHora, CAST(b.proref AS VARCHAR(50)) AS proref, CAST(a.ofnum AS VARCHAR(20)) AS ofnum, d.ofdnumenr, CAST(d.openum AS VARCHAR(20)) AS openum
            FROM SILVER_BI.dbo.SOFB b
            LEFT JOIN SILVER_BI.dbo.SOFA a ON a.ofanumenr = b.ofanumenr
            LEFT JOIN SILVER_BI.dbo.SOFD d ON b.ofanumenr = d.ofanumenr
            GROUP BY CAST(d.opecod AS VARCHAR(20)), CAST(b.proref AS VARCHAR(50)), CAST(a.ofnum AS VARCHAR(20)), d.ofdnumenr, CAST(d.openum AS VARCHAR(20))
        ) k ON k.ofnum     = r.OF_NUM
           AND k.opecod    = r.OP_COD_ORIGEM
           AND k.ofdnumenr = sofd.ofdnumenr
           AND k.proref    = r.REF_NUM
           AND k.openum    = r.OP_NUM ';

    -- ═══════════════════════════════════════════════════════════════════════════
    -- PARTE ESTÁTICA: RP_ e PR_WINROBOT_ → #ResultBase
    -- M2 = modificação mais recente, M1 = anterior, original = base
    -- Duração RP_: dbo.ConvertExceedingTimeToFloat (TIME → segundos)
    -- Duração PR_: DATEDIFF (DATETIME → segundos, directo)
    -- ═══════════════════════════════════════════════════════════════════════════
    ;WITH
    -- ─── RP_ : Linhas agrupadas ao nível do OF (várias OP_CABs do mesmo OF) ────────
    LinPorOf AS (
        SELECT
            o.ID_OF_CAB,
            l.REF_NUM,
            l.REF_DES,
            MAX(l.QUANT_BOAS_TOTAL_M2) AS QUANT_BOAS_TOTAL_M2,
            MAX(l.QUANT_BOAS_TOTAL_M1) AS QUANT_BOAS_TOTAL_M1,
            MAX(l.QUANT_BOAS_TOTAL)    AS QUANT_BOAS_TOTAL,
            MAX(l.QUANT_DEF_TOTAL_M2)  AS QUANT_DEF_TOTAL_M2,
            MAX(l.QUANT_DEF_TOTAL_M1)  AS QUANT_DEF_TOTAL_M1,
            MAX(l.QUANT_DEF_TOTAL)     AS QUANT_DEF_TOTAL
        FROM RP_OF_OP_LIN l
        INNER JOIN RP_OF_OP_CAB o ON o.ID_OP_CAB = l.ID_OP_CAB
        WHERE ISNULL(l.TIPO_PECA, '') NOT IN ('COM', 'COMP', 'COMS')
        GROUP BY o.ID_OF_CAB, l.REF_NUM, l.REF_DES
    ),
    -- ─── RP_ : Nº de referências finais por OF ──────────────────────────────────
    LinContagem AS (
        SELECT ID_OF_CAB, COUNT(*) AS NUM_REFS
        FROM LinPorOf
        GROUP BY ID_OF_CAB
    ),
    -- ─── RP_ : Sessão efectiva por worker (M2 > M1 > original) ─────────────────
    FuncSessions AS (
        SELECT
            f.ID_OP_FUNC,
            f.ID_OP_CAB,
            f.ID_UTZ_CRIA,
            COALESCE(f.DATA_INI_M2, f.DATA_INI_M1, f.DATA_INI) AS DATA_INI,
            COALESCE(f.HORA_INI_M2, f.HORA_INI_M1, f.HORA_INI) AS HORA_INI,
            COALESCE(f.DATA_FIM_M2, f.DATA_FIM_M1, f.DATA_FIM) AS DATA_FIM,
            COALESCE(f.HORA_FIM_M2, f.HORA_FIM_M1, f.HORA_FIM) AS HORA_FIM
        FROM RP_OF_OP_FUNC f
        WHERE f.ESTADO != 'A'
          AND COALESCE(f.DATA_INI_M2, f.DATA_INI_M1, f.DATA_INI) IS NOT NULL
          AND COALESCE(f.DATA_FIM_M2, f.DATA_FIM_M1, f.DATA_FIM) IS NOT NULL
    ),
    -- ─── RP_ : Soma dos tempos pré-calculados de todos os OP_CABs do mesmo OF ────
    TotalSessoesPorOf AS (
        SELECT
            ID_OF_CAB,
            SUM(
                dbo.ConvertExceedingTimeToFloat(COALESCE(TEMPO_EXEC_TOTAL_M2, TEMPO_EXEC_TOTAL_M1, TEMPO_EXEC_TOTAL, '00:00:00'))
              + dbo.ConvertExceedingTimeToFloat(COALESCE(TEMPO_PREP_TOTAL_M2, TEMPO_PREP_TOTAL_M1, TEMPO_PREP_TOTAL, '00:00:00'))
              + dbo.ConvertExceedingTimeToFloat(COALESCE(TEMPO_PARA_TOTAL_M2, TEMPO_PARA_TOTAL_M1, TEMPO_PARA_TOTAL, '00:00:00'))
            ) AS TOTAL_SEGUNDOS
        FROM RP_OF_OP_CAB
        GROUP BY ID_OF_CAB
    ),
    -- ─── PR_ : Nº de artigos por CAB robot ──────────────────────────────────────
    RW_ArticlesPorCab AS (
        SELECT ID_CAB, COUNT(*) AS NUM_ARTICLES
        FROM PR_WINROBOT_ARTICLES
        GROUP BY ID_CAB
    ),
    -- ─── PR_ : Tempo total de todos workers por CAB robot (em segundos) ────────
    RW_TotalTempoPorCab AS (
        SELECT
            ID_CAB,
            SUM(CAST(dbo.ConvertExceedingTimeToFloat(ISNULL(TEMP_TOTAL, '00:00:00')) AS DECIMAL(19,4))) AS TOTAL_SEGUNDOS
        FROM PR_WINROBOT_USERS
        WHERE ESTADO NOT IN ('D')
        GROUP BY ID_CAB
    ),
    -- ─── RP_ rows: uma linha por (sessão worker × referência final) ─────────────
    RP_Rows AS (
        SELECT
            rh.COD_FUNCIONARIO,
            rh.NOME,
            rh.ATIVO,
            rh.LOCAL,
            rh.RESPONSAVEL,
            rh.COD_SECTOR,
            sec.DES_SECTOR,
            (SELECT NOME FROM RH_FUNCIONARIOS WHERE COD_FUNCIONARIO = sec.CHEFE1) AS CHEFE,
            fs.DATA_INI,
            lin.REF_NUM,
            lin.REF_DES,
            CAST(cab.OP_COD_ORIGEM AS VARCHAR(20)) AS OP_COD_ORIGEM,
            cab.OP_DES,
            -- [13] QUANT proporcional: (boas + def) × (TEMPO_worker / TOTAL_TEMPO_OF)
            CAST(ROUND(
                (COALESCE(NULLIF(lin.QUANT_BOAS_TOTAL_M2,0), NULLIF(lin.QUANT_BOAS_TOTAL_M1,0), lin.QUANT_BOAS_TOTAL, 0)
               + COALESCE(NULLIF(lin.QUANT_DEF_TOTAL_M2, 0), NULLIF(lin.QUANT_DEF_TOTAL_M1, 0), lin.QUANT_DEF_TOTAL, 0))
                * (t.exec_h + t.prep_h + t.para_h)
                / NULLIF(ts.TOTAL_SEGUNDOS / 3600.0, 0)
            , 0) AS INT) AS QUANT,
            -- [14] TEMPO_PREP: total op (RP_OF_OP_CAB) / refs (tempo é igual por pessoa)
            CAST(IIF(t.prep_h < 0, 0, t.prep_h)
                / ISNULL(NULLIF(lc.NUM_REFS, 0), 1)
            AS DECIMAL(19,6)) AS TEMPO_PREP,
            -- [15] TEMPO_EXEC: total op / refs
            CAST(IIF(t.exec_h < 0, 0, t.exec_h)
                / ISNULL(NULLIF(lc.NUM_REFS, 0), 1)
            AS DECIMAL(19,6)) AS TEMPO_EXEC,
            -- [16] TEMPO_TOTAL = prep + exec + pausas / refs
            CAST((IIF(t.prep_h < 0, 0, t.prep_h) + IIF(t.exec_h < 0, 0, t.exec_h) + IIF(t.para_h < 0, 0, t.para_h))
                / ISNULL(NULLIF(lc.NUM_REFS, 0), 1)
            AS DECIMAL(19,6)) AS TEMPO_TOTAL,
            LEFT(CONVERT(VARCHAR(8), fs.HORA_INI, 108), 5)    AS HORA_INI, -- [17]
            LEFT(CONVERT(VARCHAR(8), fs.HORA_FIM, 108), 5)    AS HORA_FIM, -- [18]
            cab.OF_NUM,                                                      -- [19]
            -- [21] TEMPO_PAUSAS: total op / refs
            CAST(IIF(t.para_h < 0, 0, t.para_h)
                / ISNULL(NULLIF(lc.NUM_REFS, 0), 1)
            AS DECIMAL(19,6)) AS TEMPO_PAUSAS,
            cab.OP_NUM,
            -- para filtro de hora no WHERE exterior
            DATEADD(SECOND, DATEDIFF(SECOND,0,fs.HORA_INI), CAST(fs.DATA_INI AS DATETIME)) AS DATA_HORA_INI_FULL
        FROM FuncSessions fs
        INNER JOIN RP_OF_OP_CAB opcab ON fs.ID_OP_CAB = opcab.ID_OP_CAB
        INNER JOIN RP_OF_CAB    cab   ON opcab.ID_OF_CAB = cab.ID_OF_CAB
        LEFT JOIN  LinPorOf         lin ON lin.ID_OF_CAB = cab.ID_OF_CAB
        LEFT JOIN  RH_FUNCIONARIOS  rh  ON TRY_CAST(fs.ID_UTZ_CRIA AS INT) = rh.COD_FUNCIONARIO
        LEFT JOIN  RH_SECTORES      sec ON rh.COD_SECTOR = sec.COD_SECTOR
        LEFT JOIN  TotalSessoesPorOf ts ON ts.ID_OF_CAB  = cab.ID_OF_CAB
        LEFT JOIN  LinContagem       lc ON lc.ID_OF_CAB  = cab.ID_OF_CAB
        CROSS APPLY (VALUES(
            dbo.ConvertExceedingTimeToFloat(COALESCE(opcab.TEMPO_EXEC_TOTAL_M2, opcab.TEMPO_EXEC_TOTAL_M1, opcab.TEMPO_EXEC_TOTAL, '00:00:00')) / 3600.0,
            dbo.ConvertExceedingTimeToFloat(COALESCE(opcab.TEMPO_PREP_TOTAL_M2, opcab.TEMPO_PREP_TOTAL_M1, opcab.TEMPO_PREP_TOTAL, '00:00:00')) / 3600.0,
            dbo.ConvertExceedingTimeToFloat(COALESCE(opcab.TEMPO_PARA_TOTAL_M2, opcab.TEMPO_PARA_TOTAL_M1, opcab.TEMPO_PARA_TOTAL, '00:00:00')) / 3600.0
        )) AS t(exec_h, prep_h, para_h)
        WHERE lin.REF_NUM IS NOT NULL
          AND (DATEDIFF(DAY, fs.DATA_INI, fs.DATA_FIM) * 86400.0
               + dbo.ConvertExceedingTimeToFloat(CONVERT(VARCHAR(8), fs.HORA_FIM, 108))
               - dbo.ConvertExceedingTimeToFloat(CONVERT(VARCHAR(8), fs.HORA_INI, 108))
              ) > 0
    ),
    -- ─── PR_WINROBOT_ rows: uma linha por (sessão worker × artigo) ───────────────
    PR_Rows AS (
        SELECT
            rh.COD_FUNCIONARIO,
            ISNULL(rh.NOME, f.NOME_UTZ)                            AS NOME,
            rh.ATIVO,
            rh.LOCAL,
            rh.RESPONSAVEL,
            rh.COD_SECTOR,
            sec.DES_SECTOR,
            (SELECT NOME FROM RH_FUNCIONARIOS WHERE COD_FUNCIONARIO = sec.CHEFE1) AS CHEFE,
            CAST(f.DATA_HORA_INICIO AS DATE)                       AS DATA_INI,
            c.COD_REF                                               AS REF_NUM,
            ISNULL(c.DESIGN_REF, '')                               AS REF_DES,
            CASE a.TIPO_POSTO
                WHEN 'CARGA'    THEN '54'
                WHEN 'DESCARGA' THEN '87'
                ELSE a.TIPO_POSTO
            END                                                     AS OP_COD_ORIGEM,
            NULL                                                    AS OP_DES,
            -- [13] QUANT proporcional ao tempo deste worker
            CAST(ROUND(
                ISNULL(c.TOTAL_BOAS, 0)
                * CAST(dbo.ConvertExceedingTimeToFloat(ISNULL(f.TEMP_TOTAL, '00:00:00')) AS DECIMAL(19,4))
                / NULLIF(rw_tt.TOTAL_SEGUNDOS, 0)
            , 0) AS INT)                                           AS QUANT,
            CAST(dbo.ConvertExceedingTimeToFloat(ISNULL(f.TEMP_PREP,  '00:00:00')) / 3600.0 / ISNULL(NULLIF(rw_lc.NUM_ARTICLES, 0), 1) AS DECIMAL(19,6)) AS TEMPO_PREP,
            -- [15] TEMPO_EXEC / nº artigos
            CAST(dbo.ConvertExceedingTimeToFloat(ISNULL(f.TEMP_EXEC,  '00:00:00')) / 3600.0 / ISNULL(NULLIF(rw_lc.NUM_ARTICLES, 0), 1) AS DECIMAL(19,6)) AS TEMPO_EXEC,
            -- [16] TEMPO_TOTAL / nº artigos
            CAST(dbo.ConvertExceedingTimeToFloat(ISNULL(f.TEMP_TOTAL, '00:00:00')) / 3600.0 / ISNULL(NULLIF(rw_lc.NUM_ARTICLES, 0), 1) AS DECIMAL(19,6)) AS TEMPO_TOTAL,
            LEFT(CONVERT(VARCHAR(8), f.DATA_HORA_INICIO, 108), 5) AS HORA_INI, -- [17]
            LEFT(CONVERT(VARCHAR(8), f.DATA_HORA_FIM,    108), 5) AS HORA_FIM, -- [18]
            CAST(sofa_of.OFNUM AS VARCHAR(10))                     AS OF_NUM,   -- [19]
            CAST(dbo.ConvertExceedingTimeToFloat(ISNULL(f.TEMP_PAUSA, '00:00:00')) / 3600.0 / ISNULL(NULLIF(rw_lc.NUM_ARTICLES, 0), 1) AS DECIMAL(19,6)) AS TEMPO_PAUSAS,
            CASE a.TIPO_POSTO
                WHEN 'CARGA'    THEN '10'
                WHEN 'DESCARGA' THEN '30'
                ELSE NULL
            END                                                     AS OP_NUM,
            f.DATA_HORA_INICIO                                     AS DATA_HORA_INI_FULL
        FROM PR_WINROBOT_USERS     f
        INNER JOIN PR_WINROBOT_CAB      a     ON a.ID     = f.ID_CAB
        LEFT JOIN  PR_WINROBOT_ARTICLES c     ON c.ID_CAB = a.ID
        LEFT JOIN  RH_FUNCIONARIOS      rh    ON TRY_CAST(f.ID_UTZ AS INT) = rh.COD_FUNCIONARIO
        LEFT JOIN  RH_SECTORES          sec   ON rh.COD_SECTOR = sec.COD_SECTOR
        LEFT JOIN  RW_ArticlesPorCab    rw_lc   ON rw_lc.ID_CAB = a.ID
        LEFT JOIN  RW_TotalTempoPorCab  rw_tt   ON rw_tt.ID_CAB = a.ID
        LEFT JOIN  SILVER.dbo.SOFA      sofa_of ON sofa_of.OFREF = a.NUMERO_IDENTICACAO_CARGA
        WHERE f.ESTADO NOT IN ('D')
          AND dbo.ConvertExceedingTimeToFloat(ISNULL(f.TEMP_TOTAL, '00:00:00')) > 0
          AND c.COD_REF IS NOT NULL
    )

    SELECT DISTINCT
        COD_FUNCIONARIO, NOME, ATIVO, LOCAL, RESPONSAVEL,
        COD_SECTOR, DES_SECTOR, CHEFE, DATA_INI,
        REF_NUM, REF_DES, OP_COD_ORIGEM, OP_DES, QUANT,
        TEMPO_PREP, TEMPO_EXEC, TEMPO_TOTAL,
        HORA_INI, HORA_FIM, OF_NUM, TEMPO_PAUSAS, OP_NUM
    INTO #ResultBase
    FROM (
        SELECT * FROM RP_Rows
        UNION ALL
        SELECT * FROM PR_Rows
    ) AS AllRows
    WHERE
        DATA_INI BETWEEN @DATA AND @DATA2

        -- acesso por sector via STRING_SPLIT (sem injecção de string)
        AND (
            (@Admin = 1 AND COD_SECTOR IS NULL)
            OR @SectorAcesso IS NULL
            OR EXISTS (
                SELECT 1 FROM STRING_SPLIT(@SectorAcesso, ',')
                WHERE LTRIM(RTRIM(value)) = CAST(COD_SECTOR AS VARCHAR(20))
            )
        )

        -- filtro de sector opcional
        AND (
            @Sector IS NULL
            OR EXISTS (
                SELECT 1 FROM STRING_SPLIT(@Sector, ',')
                WHERE LTRIM(RTRIM(value)) = CAST(COD_SECTOR AS VARCHAR(20))
            )
        )

        -- filtro ativo opcional
        AND (@Ativo IS NULL OR ATIVO = CASE WHEN @Ativo = 'true' THEN 1 ELSE 0 END)

        -- filtro operário opcional
        AND (@Operario IS NULL OR COD_FUNCIONARIO = @Operario)

        -- filtro de hora de início de sessão
        AND DATA_HORA_INI_FULL >= CAST(@DATA  AS DATETIME) + CONVERT(DATETIME, @HoraIni, 108)
        AND DATA_HORA_INI_FULL <= CAST(@DATA2 AS DATETIME) + CONVERT(DATETIME, @HoraFim, 108);

    -- ═══════════════════════════════════════════════════════════════════════════
    -- PARTE DINÂMICA: juntar cadência e retornar resultado final
    -- ═══════════════════════════════════════════════════════════════════════════
    DECLARE @sqlCadencia NVARCHAR(MAX) = N'
    SELECT 
        COD_FUNCIONARIO, NOME, ATIVO, LOCAL, RESPONSAVEL,
        COD_SECTOR, DES_SECTOR, CHEFE, DATA_INI,
        REF_NUM, REF_DES, OP_COD_ORIGEM, OP_DES, QUANT,
        TEMPO_PREP, TEMPO_EXEC, TEMPO_TOTAL,
        HORA_INI, HORA_FIM, OF_NUM,
        -- [20] cadência: (taxa real deste worker) / (taxa standard) × 100
        CAST(
            CASE
                WHEN NULLIF(NULLIF(k.PecasHora, ''''), ''0'') IS NULL
                     OR r.TEMPO_EXEC <= 0
                THEN 0
                ELSE r.QUANT
                     / r.TEMPO_EXEC
                     / TRY_CAST(NULLIF(k.PecasHora, '''') AS DECIMAL(19,6))
                     * 100
            END
        AS DECIMAL(19,2)) AS CADENCIA,
        TEMPO_PAUSAS
    FROM #ResultBase r
    ' + @joinTipoCadencia + '
    ORDER BY DATA_INI DESC, HORA_INI DESC, HORA_FIM DESC, COD_FUNCIONARIO desc
    ';

    EXEC sp_executesql @sqlCadencia;

    DROP TABLE #ResultBase;
END;
GO
