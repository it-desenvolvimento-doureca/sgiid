-- ============================================================
-- QUERY_GERAL @DATA
-- ============================================================

DECLARE @DATA          date = '2026-05-20';
DECLARE @MaxBlockHours int  = 13;
DECLARE @DedupMinutes  int  = 10;

-- ============================================================
-- 1. Picagens brutas com turno base
--    - Filtra dia anterior: só turno nocturno >= 20:00
--    - Filtra dia seguinte: só turno nocturno < 12:00
-- ============================================================
IF OBJECT_ID('tempdb..#RawPunches') IS NOT NULL DROP TABLE #RawPunches;

SELECT
    CAST(LTRIM(RTRIM(c.CODIGO)) AS int) AS NFuncionario,
    c.DATAHORA                          AS Data_Hora,
    l.COD_TURNO,
    l.HORA_INICIO,
    l.HORA_FIM,
    CASE
        WHEN CAST(l.HORA_FIM AS time) < CAST(l.HORA_INICIO AS time)
        THEN 1 ELSE 0
    END AS IsNightShift
INTO #RawPunches
FROM SERVCOM_net.dbo.Coleta c
LEFT JOIN RH_FUNCIONARIOS k ON k.COD_FUNC_ORIGEM = CAST(LTRIM(RTRIM(c.CODIGO)) AS int)
LEFT JOIN RH_SECTORES     j ON j.COD_SECTOR      = k.COD_SECTOR
LEFT JOIN RH_TURNOS       l ON l.COD_TURNO       = j.COD_TURNO
WHERE
    -- Próprio dia: turno nocturno sempre; diurno só a partir das 04:00
    -- (punches < 04:00 de turnos diurnos são saídas tardias do dia anterior, já capturadas pela query desse dia)
    (CAST(c.DATAHORA AS date) = @DATA
     AND (CAST(l.HORA_FIM AS time) < CAST(l.HORA_INICIO AS time)
          OR CAST(c.DATAHORA   AS time) >= '04:00:00'))
    OR (
        -- Dia anterior: só turno nocturno com entrada >= 20:00
        CAST(c.DATAHORA AS date) = DATEADD(DAY, -1, @DATA)
        AND CAST(l.HORA_FIM   AS time) < CAST(l.HORA_INICIO AS time)
        AND CAST(c.DATAHORA   AS time) >= '20:00:00'
    )
    OR (
        -- Dia seguinte: turno nocturno com saída < 12:00
        CAST(c.DATAHORA AS date) = DATEADD(DAY, 1, @DATA)
        AND CAST(l.HORA_FIM   AS time) < CAST(l.HORA_INICIO AS time)
        AND CAST(c.DATAHORA   AS time) < '12:00:00'
    )
    OR (
        -- Dia seguinte: saída tardia de qualquer turno (ex: tarde 14h-22h com saída após meia-noite)
        CAST(c.DATAHORA AS date) = DATEADD(DAY, 1, @DATA)
        AND CAST(c.DATAHORA   AS time) < '04:00:00'
    );


-- ============================================================
-- 2. Deduplicar: elimina picagens com < 10 min de diferença
-- ============================================================
IF OBJECT_ID('tempdb..#Deduped') IS NOT NULL DROP TABLE #Deduped;

SELECT
    NFuncionario,
    Data_Hora,
    ISNULL(
        DATEDIFF(MINUTE,
            LAG(Data_Hora) OVER (PARTITION BY NFuncionario ORDER BY Data_Hora),
            Data_Hora),
        9999
    ) AS MinFromPrev
INTO #Deduped
FROM #RawPunches;

DELETE FROM #Deduped WHERE MinFromPrev < @DedupMinutes;


-- ============================================================
-- 3. Identificar blocos de trabalho
--    Nova pausa > @MaxBlockHours = novo bloco
-- ============================================================
IF OBJECT_ID('tempdb..#BlockGroups') IS NOT NULL DROP TABLE #BlockGroups;

SELECT
    NFuncionario,
    Data_Hora,
    SUM(
        CASE WHEN MinFromPrev > @MaxBlockHours * 60 OR MinFromPrev = 9999
             THEN 1 ELSE 0 END
    ) OVER (
        PARTITION BY NFuncionario
        ORDER BY Data_Hora
        ROWS UNBOUNDED PRECEDING
    ) AS BlockNum
INTO #BlockGroups
FROM #Deduped;


-- ============================================================
-- 4. Agregar blocos: Time_In = MIN, Time_Out = MAX
-- ============================================================
IF OBJECT_ID('tempdb..#WorkBlocks') IS NOT NULL DROP TABLE #WorkBlocks;

SELECT
    bg.NFuncionario,
    MIN(bg.Data_Hora)                                        AS Time_In_Real,
    MAX(bg.Data_Hora)                                        AS Time_Out_Real,
    CAST(MIN(bg.Data_Hora) AS date)                          AS CheckDate,
    DATEDIFF(MINUTE, MIN(bg.Data_Hora), MAX(bg.Data_Hora))   AS MinutosBloco
INTO #WorkBlocks
FROM #BlockGroups bg
GROUP BY bg.NFuncionario, bg.BlockNum;


-- ============================================================
-- 5. Juntar turno base ao bloco
--    TurnoInicio: tiebreaker para escolher o bloco mais próximo do turno
--    TurnoFim: fallback quando só existe 1 picagem (sem saída real)
-- ============================================================
IF OBJECT_ID('tempdb..#WorkBlocksFinal') IS NOT NULL DROP TABLE #WorkBlocksFinal;

SELECT
    wb.NFuncionario,
    -- Saída tardia sem entrada (único punch não-nocturno < 04:00): pertence ao dia anterior
    CASE
        WHEN wb.MinutosBloco = 0
             AND CAST(wb.Time_In_Real AS time) < '04:00:00'
             AND CAST(l.HORA_FIM AS time) >= CAST(l.HORA_INICIO AS time)
        THEN DATEADD(DAY, -1, wb.CheckDate)
        ELSE wb.CheckDate
    END AS CheckDate,
    wb.Time_In_Real,
    wb.Time_Out_Real,
    wb.MinutosBloco,
    l.HORA_INICIO,
    l.HORA_FIM,
    CASE
        WHEN CAST(l.HORA_FIM AS time) < CAST(l.HORA_INICIO AS time)
        THEN 1 ELSE 0
    END AS IsNightShift,
    -- Início de turno absoluto (tiebreaker: preferir bloco mais próximo do turno)
    CAST(
        CASE
            WHEN wb.MinutosBloco = 0
                 AND CAST(wb.Time_In_Real AS time) < '04:00:00'
                 AND CAST(l.HORA_FIM AS time) >= CAST(l.HORA_INICIO AS time)
            THEN DATEADD(DAY, -1, wb.CheckDate)
            ELSE wb.CheckDate
        END
    AS datetime) + CAST(l.HORA_INICIO AS datetime)
        AS TurnoInicio,
    -- Fim de turno absoluto (fallback quando só existe 1 picagem)
    CASE
        WHEN CAST(l.HORA_FIM AS time) < CAST(l.HORA_INICIO AS time)
        THEN DATEADD(DAY, 1, CAST(
            CASE
                WHEN wb.MinutosBloco = 0
                     AND CAST(wb.Time_In_Real AS time) < '04:00:00'
                     AND CAST(l.HORA_FIM AS time) >= CAST(l.HORA_INICIO AS time)
                THEN DATEADD(DAY, -1, wb.CheckDate)
                ELSE wb.CheckDate
            END
        AS datetime)) + CAST(l.HORA_FIM AS datetime)
        ELSE CAST(
            CASE
                WHEN wb.MinutosBloco = 0
                     AND CAST(wb.Time_In_Real AS time) < '04:00:00'
                     AND CAST(l.HORA_FIM AS time) >= CAST(l.HORA_INICIO AS time)
                THEN DATEADD(DAY, -1, wb.CheckDate)
                ELSE wb.CheckDate
            END
        AS datetime) + CAST(l.HORA_FIM AS datetime)
    END AS TurnoFim
INTO #WorkBlocksFinal
FROM #WorkBlocks wb
LEFT JOIN RH_FUNCIONARIOS k ON k.COD_FUNC_ORIGEM = wb.NFuncionario
LEFT JOIN RH_SECTORES     j ON j.COD_SECTOR      = k.COD_SECTOR
LEFT JOIN RH_TURNOS       l ON l.COD_TURNO       = j.COD_TURNO;


-- ============================================================
-- 6. Aplicar regras Time_In / Time_Out + 1 linha por funcionário
--    Regra: usa sempre a picagem real.
--           Só usa o turno quando existe apenas 1 picagem (sem saída real).
-- ============================================================
SELECT
    NFuncionario,
    CheckDate,
    Time_In,
    Time_Out
FROM (
    SELECT
        wb.NFuncionario,
        wb.CheckDate,
        wb.MinutosBloco,

        -- TIME_IN
        CASE
            WHEN wb.Time_In_Real = wb.Time_Out_Real
            -- Única picagem: se estiver mais próxima do TurnoFim é uma saída → usa TurnoInicio
            AND ABS(DATEDIFF(MINUTE, wb.Time_In_Real, wb.TurnoFim)) <
                ABS(DATEDIFF(MINUTE, wb.Time_In_Real, wb.TurnoInicio))
            THEN wb.TurnoInicio
            -- Entrada em falta: só turno 08:15-17:15 (único que faz pausa de almoço)
            WHEN wb.TurnoInicio IS NOT NULL
                 AND CAST(wb.HORA_INICIO AS time) = '08:15:00'
                 AND CAST(wb.HORA_FIM    AS time) = '17:15:00'
                 AND DATEDIFF(MINUTE, wb.TurnoInicio, wb.Time_In_Real) > 120
            THEN wb.TurnoInicio
            ELSE wb.Time_In_Real
        END AS Time_In,

        -- TIME_OUT
        CASE
            WHEN wb.Time_In_Real = wb.Time_Out_Real
            THEN
                CASE
                    -- Única picagem é saída (mais próxima do TurnoFim) → usa picagem real
                    WHEN ABS(DATEDIFF(MINUTE, wb.Time_In_Real, wb.TurnoFim)) <
                         ABS(DATEDIFF(MINUTE, wb.Time_In_Real, wb.TurnoInicio))
                    THEN CASE WHEN wb.Time_In_Real > GETDATE() THEN NULL ELSE wb.Time_In_Real END
                    -- Única picagem é entrada → usa TurnoFim
                    ELSE CASE
                             WHEN wb.TurnoFim > GETDATE()        THEN NULL
                             WHEN wb.TurnoFim <= wb.Time_In_Real THEN NULL
                             ELSE wb.TurnoFim
                         END
                END
            ELSE CASE WHEN wb.Time_Out_Real > GETDATE() THEN NULL ELSE wb.Time_Out_Real END
        END AS Time_Out,

        -- Numera blocos: 1º mais horas, em empate prefere o mais próximo do turno
        ROW_NUMBER() OVER (
            PARTITION BY wb.NFuncionario, wb.CheckDate
            ORDER BY wb.MinutosBloco DESC,
                     ABS(DATEDIFF(MINUTE, wb.Time_In_Real, wb.TurnoInicio)) ASC
        ) AS rn

    FROM #WorkBlocksFinal wb
    WHERE wb.CheckDate = @DATA
      AND wb.MinutosBloco <= @MaxBlockHours * 60
      -- Exclui blocos fantasma: único punch NA data de @DATA antes das 04:00, turno não-nocturno
      -- (saídas tardias do dia anterior já capturadas pela query desse dia)
      AND NOT (wb.MinutosBloco = 0
               AND CAST(wb.Time_In_Real AS date) = @DATA
               AND CAST(wb.Time_In_Real AS time) < '04:00:00'
               AND wb.IsNightShift = 0)
) x
WHERE rn = 1;


-- ============================================================
-- Limpeza
-- ============================================================
IF OBJECT_ID('tempdb..#RawPunches')       IS NOT NULL DROP TABLE #RawPunches;
IF OBJECT_ID('tempdb..#Deduped')          IS NOT NULL DROP TABLE #Deduped;
IF OBJECT_ID('tempdb..#BlockGroups')      IS NOT NULL DROP TABLE #BlockGroups;
IF OBJECT_ID('tempdb..#WorkBlocks')       IS NOT NULL DROP TABLE #WorkBlocks;
IF OBJECT_ID('tempdb..#WorkBlocksFinal')  IS NOT NULL DROP TABLE #WorkBlocksFinal;
