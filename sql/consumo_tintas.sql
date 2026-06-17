-- ============================================================
-- CONSUMOS DE TINTAS - DDL e Stored Procedure
-- ============================================================

-- Tabela cabeçalho
CREATE TABLE PIN_MOV_CONSUMO_TINTAS (
    ID_CONSUMO_TINTAS       INT IDENTITY(1,1) PRIMARY KEY,
    ANO                     INT NOT NULL,
    SEMANA                  INT NOT NULL,
    DIA                     INT NOT NULL,
    DATA                    DATE NULL,
    ID_PLANO_DIARIO_PINTURA INT NULL,
    UTZ_CRIA                INT NULL,
    DATA_CRIA               DATETIME NULL,
    UTZ_MODIF               INT NULL,
    DATA_MODIF              DATETIME NULL,
    UTZ_ANULA               INT NULL,
    DATA_ANULA              DATETIME NULL,
    ATIVO                   BIT NOT NULL DEFAULT 1
);

-- Tabela linhas — uma linha por CABINE + MISTURA (agrupado)
CREATE TABLE PIN_MOV_CONSUMO_TINTAS_LINHAS (
    ID_CONSUMO_TINTAS_LINHA     INT IDENTITY(1,1) PRIMARY KEY,
    ID_CONSUMO_TINTAS           INT NOT NULL,
    CABINE                      NVARCHAR(100) NULL,
    MISTURA                     NVARCHAR(100) NULL,
    COD_REF                     NVARCHAR(MAX) NULL,   -- STRING_AGG dos COD_REFERENCIA
    DESC_REF                    NVARCHAR(MAX) NULL,   -- STRING_AGG das descrições REFERENCIA
    CONSUMO_COR_A_RACK          DECIMAL(18,4) NULL,
    CONSUMO_COR_B_RACK          DECIMAL(18,4) NULL,
    CONSUMO_CARGA_COR_A         DECIMAL(18,4) NULL,
    CONSUMO_CARGA_CATALIZADOR_B DECIMAL(18,4) NULL,
    NUM_RACKS                   INT NULL,
    CONSUMO_MISTURA             DECIMAL(18,4) NULL,
    QUANT_EXISTENTE_COR_A       DECIMAL(18,4) NULL DEFAULT 0,
    CONSUMO_TOTAL_COR_A         DECIMAL(18,4) NULL,
    CONSUMO_TOTAL_CATALIZADOR_B DECIMAL(18,4) NULL,
    QUANTIDADE_ADICIONAL        DECIMAL(18,4) NULL DEFAULT 7,
    QUANT_NECESSARIA            DECIMAL(18,4) NULL,
    ORDEM                       INT NULL,
    CONSTRAINT FK_CONSUMO_TINTAS_LINHAS FOREIGN KEY (ID_CONSUMO_TINTAS)
        REFERENCES PIN_MOV_CONSUMO_TINTAS(ID_CONSUMO_TINTAS)
);

GO

-- Drop (ordem inversa por causa da FK)
IF OBJECT_ID('PIN_MOV_CONSUMO_TINTAS_LINHAS', 'U') IS NOT NULL DROP TABLE PIN_MOV_CONSUMO_TINTAS_LINHAS;
IF OBJECT_ID('PIN_MOV_CONSUMO_TINTAS', 'U') IS NOT NULL DROP TABLE PIN_MOV_CONSUMO_TINTAS;

GO

-- ============================================================
-- SP: idempotente — uma linha por referência (peça)
-- CABINE e MISTURA agrupadas visualmente no frontend (rowspan)
-- ============================================================
CREATE OR ALTER PROCEDURE SP_CREATE_CONSUMO_TINTAS_LINHAS
    @ID_CONSUMO_TINTAS      INT,
    @ID_PLANO_DIARIO_PINTURA INT,
    @DIA                    INT
AS
BEGIN
    SET NOCOUNT ON;

    DELETE FROM PIN_MOV_CONSUMO_TINTAS_LINHAS
    WHERE ID_CONSUMO_TINTAS = @ID_CONSUMO_TINTAS;

    WITH PlanoLinhas AS (
        SELECT REFERENCIA, COD_REFERENCIA, SUM(NUM_RACKS) AS NUM_RACKS
        FROM PIN_PLANO_DIARIO_PINTURA_LINHAS
        WHERE ID_PLANO_DIARIO_PINTURA = @ID_PLANO_DIARIO_PINTURA
          AND DIA_SEMANA = @DIA
          AND ISNULL(TIPO_LINHA, 'REFERENCIA') NOT IN ('TEXTO')
        GROUP BY REFERENCIA, COD_REFERENCIA
    ),
    ReceitasAtivas AS (
        SELECT rr.REFERENCIA_PINTURA, r.ID, r.VERSAO
        FROM PIN_MOV_RECEITAS r
        INNER JOIN (
            SELECT DISTINCT ID_RECEITA, VERSAO, REFERENCIA_PINTURA
            FROM PIN_MOV_RECEITAS_REFERENCIAS
        ) rr ON r.ID = rr.ID_RECEITA AND r.VERSAO = rr.VERSAO
        WHERE r.VERSAO_ATIVA = 1 AND r.INATIVO = 0 AND r.RECEITA_INATIVA = 0
    ),
    Detalhes AS (
        SELECT
            c.NOME                                          AS CABINE,
            rl.REFERENCIA_A                                 AS MISTURA,
            a.COD_REFERENCIA                                AS COD_REF,
            a.REFERENCIA                                    AS DESC_REF,
            ISNULL(rl.CONSUMO_RACK_COR, 0)/ 1000.0                                AS CONSUMO_COR_A_RACK,      -- mL/rack
            ISNULL(rl.CONSUMO_RACK_DILUENTE, 0)/ 1000.0                          AS CONSUMO_COR_B_RACK,      -- mL/rack
            ISNULL(rl.QTD_RACK_REFERENCIA_A, 0)                          AS CONSUMO_CARGA_COR_A,     -- L/rack
            ISNULL(rl.QTD_RACK_REFERENCIA_B, 0)                          AS CONSUMO_CARGA_CATALIZADOR_B, -- L/rack
            a.NUM_RACKS,
            CASE WHEN c.NOME LIKE '%primário%' THEN 1
                 WHEN c.NOME LIKE '%base%'     THEN 2
                 WHEN c.NOME LIKE '%verniz%'   THEN 3
                 ELSE 4 END                                 AS ORDEM_CABINE
        FROM PlanoLinhas a
        LEFT JOIN ReceitasAtivas r ON r.REFERENCIA_PINTURA = a.COD_REFERENCIA
        LEFT JOIN PIN_MOV_RECEITAS_LINHAS rl ON rl.ID_RECEITA = r.ID AND rl.VERSAO = r.VERSAO
        LEFT JOIN PIN_DIC_TIPO_ACABAMENTO c ON c.ID = rl.ID_TIPO_ACABAMENTO
    )
    INSERT INTO PIN_MOV_CONSUMO_TINTAS_LINHAS (
        ID_CONSUMO_TINTAS, CABINE, MISTURA, COD_REF, DESC_REF,
        CONSUMO_COR_A_RACK, CONSUMO_COR_B_RACK, CONSUMO_CARGA_COR_A, CONSUMO_CARGA_CATALIZADOR_B,
        NUM_RACKS, CONSUMO_MISTURA, QUANT_EXISTENTE_COR_A,
        CONSUMO_TOTAL_COR_A, CONSUMO_TOTAL_CATALIZADOR_B,
        QUANTIDADE_ADICIONAL, QUANT_NECESSARIA, ORDEM
    )
    SELECT
        @ID_CONSUMO_TINTAS,
        CABINE, MISTURA, COD_REF, DESC_REF,
        CONSUMO_COR_A_RACK * NUM_RACKS,
        CONSUMO_COR_B_RACK * NUM_RACKS,
        CONSUMO_CARGA_COR_A,
        CONSUMO_CARGA_CATALIZADOR_B,
        NUM_RACKS,
        CONSUMO_COR_A_RACK  * NUM_RACKS                             AS CONSUMO_MISTURA,
        0                                                                   AS QUANT_EXISTENTE_COR_A,
        CONSUMO_COR_A_RACK  + CONSUMO_CARGA_COR_A * NUM_RACKS      AS CONSUMO_TOTAL_COR_A,
        CONSUMO_COR_B_RACK  + CONSUMO_CARGA_CATALIZADOR_B * NUM_RACKS AS CONSUMO_TOTAL_CATALIZADOR_B,
        7                                                                   AS QUANTIDADE_ADICIONAL,
        CONSUMO_COR_A_RACK  + CONSUMO_CARGA_COR_A * NUM_RACKS + 7  AS QUANT_NECESSARIA,
        ROW_NUMBER() OVER (ORDER BY ORDEM_CABINE, MISTURA, COD_REF) AS ORDEM
    FROM Detalhes;
END;
GO
