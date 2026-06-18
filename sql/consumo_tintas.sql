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
    COD_REF                     NVARCHAR(MAX) NULL,
    DESC_REF                    NVARCHAR(MAX) NULL,
    CONSUMO_COR_A_RACK          DECIMAL(18,4) NULL,
    CONSUMO_COR_B_RACK          DECIMAL(18,4) NULL,
    CONSUMO_CARGA_COR_A         DECIMAL(18,4) NULL,
    CONSUMO_CARGA_CATALIZADOR_B DECIMAL(18,4) NULL,
    NUM_RACKS                   INT NULL,
    QUANTIDADE_CARGAS           INT NULL DEFAULT 2,
    CONSUMO_MISTURA             DECIMAL(18,4) NULL,
    QUANT_EXISTENTE_COR_A       DECIMAL(18,4) NULL DEFAULT 0,
    CONSUMO_TOTAL_COR_A         DECIMAL(18,4) NULL,
    CONSUMO_TOTAL_CATALIZADOR_B DECIMAL(18,4) NULL,
    QUANTIDADE_ADICIONAL        DECIMAL(18,4) NULL DEFAULT 7,
    QUANT_NECESSARIA            DECIMAL(18,4) NULL,
    PERC_DILUICAO               DECIMAL(18,4) NULL DEFAULT 0,
    ORDEM                       INT NULL,
    CONSTRAINT FK_CONSUMO_TINTAS_LINHAS FOREIGN KEY (ID_CONSUMO_TINTAS)
        REFERENCES PIN_MOV_CONSUMO_TINTAS(ID_CONSUMO_TINTAS)
);

-- Se a tabela já existir, adicionar colunas novas:
-- ALTER TABLE PIN_MOV_CONSUMO_TINTAS_LINHAS ADD QUANTIDADE_CARGAS INT NULL DEFAULT 2;
-- ALTER TABLE PIN_MOV_CONSUMO_TINTAS_LINHAS ADD PERC_DILUICAO DECIMAL(18,4) NULL DEFAULT 0;

GO

-- Drop (ordem inversa por causa da FK)
IF OBJECT_ID('PIN_MOV_CONSUMO_TINTAS_LINHAS', 'U') IS NOT NULL DROP TABLE PIN_MOV_CONSUMO_TINTAS_LINHAS;
IF OBJECT_ID('PIN_MOV_CONSUMO_TINTAS', 'U') IS NOT NULL DROP TABLE PIN_MOV_CONSUMO_TINTAS;

GO

-- ============================================================
-- SP: idempotente — uma linha por peça (COD_REFERENCIA)
-- CONSUMO_RACK: valor da receita por peça (/ 1000 → L)
-- CONSUMO_CARGA: média por Mistura (/ 1000 → L), mesmo valor em todas as linhas da mistura
-- PERC_DILUICAO, QUANTIDADE_CARGAS: por Mistura, igual em todas as linhas da mistura
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
            c.NOME                                              AS CABINE,
            rl.REFERENCIA_A                                     AS MISTURA,
            a.COD_REFERENCIA                                    AS COD_REF,
            a.REFERENCIA                                        AS DESC_REF,
            ISNULL(rl.CONSUMO_RACK_COR, 0) / 1000.0            AS CONSUMO_COR_A_RACK,
            ISNULL(rl.CONSUMO_RACK_DILUENTE, 0) / 1000.0       AS CONSUMO_COR_B_RACK,
            ISNULL(rl.CONSUMO_CARGA_COR, 0) / 1000.0           AS CONSUMO_CARGA_COR_A_RAW,
            ISNULL(rl.CONSUMO_CARGA_CATALISADOR, 0) / 1000.0   AS CONSUMO_CARGA_CATALIZADOR_B_RAW,
            a.NUM_RACKS,
            CASE WHEN c.NOME LIKE '%primário%' THEN 1
                 WHEN c.NOME LIKE '%base%'     THEN 2
                 WHEN c.NOME LIKE '%verniz%'   THEN 3
                 ELSE 4 END                                     AS ORDEM_CABINE
        FROM PlanoLinhas a
        LEFT JOIN ReceitasAtivas r ON r.REFERENCIA_PINTURA = a.COD_REFERENCIA
        LEFT JOIN PIN_MOV_RECEITAS_LINHAS rl ON rl.ID_RECEITA = r.ID AND rl.VERSAO = r.VERSAO
        LEFT JOIN PIN_DIC_TIPO_ACABAMENTO c ON c.ID = rl.ID_TIPO_ACABAMENTO
    ),
    MediaCargaPorMistura AS (
        SELECT
            CABINE,
            MISTURA,
            AVG(CONSUMO_CARGA_COR_A_RAW)         AS CONSUMO_CARGA_COR_A,
            AVG(CONSUMO_CARGA_CATALIZADOR_B_RAW) AS CONSUMO_CARGA_CATALIZADOR_B
        FROM Detalhes
        WHERE CABINE IS NOT NULL AND MISTURA IS NOT NULL
        GROUP BY CABINE, MISTURA
    ),
    DiluicaoPorMistura AS (
        SELECT p.COD_REF, SUM(ISNULL(pr.PERC_DILUICAO, 0)) AS PERC_DILUICAO
        FROM PIN_DIC_PRODUTOS p
        LEFT JOIN PIN_DIC_PRODUTOS_RELACIONADOS pr ON p.ID = pr.ID_PRODUTO
        WHERE p.INATIVO = 0
        GROUP BY p.COD_REF
    )
    INSERT INTO PIN_MOV_CONSUMO_TINTAS_LINHAS (
        ID_CONSUMO_TINTAS, CABINE, MISTURA, COD_REF, DESC_REF,
        CONSUMO_COR_A_RACK, CONSUMO_COR_B_RACK, CONSUMO_CARGA_COR_A, CONSUMO_CARGA_CATALIZADOR_B,
        NUM_RACKS, QUANTIDADE_CARGAS, QUANT_EXISTENTE_COR_A, QUANTIDADE_ADICIONAL,
        PERC_DILUICAO, ORDEM
    )
    SELECT
        @ID_CONSUMO_TINTAS,
        d.CABINE,
        d.MISTURA,
        d.COD_REF,
        d.DESC_REF,
        d.CONSUMO_COR_A_RACK,
        d.CONSUMO_COR_B_RACK,
        ISNULL(m.CONSUMO_CARGA_COR_A, 0)         AS CONSUMO_CARGA_COR_A,
        ISNULL(m.CONSUMO_CARGA_CATALIZADOR_B, 0) AS CONSUMO_CARGA_CATALIZADOR_B,
        d.NUM_RACKS,
        2                               AS QUANTIDADE_CARGAS,
        0                               AS QUANT_EXISTENTE_COR_A,
        7                               AS QUANTIDADE_ADICIONAL,
        ISNULL(dil.PERC_DILUICAO, 0)   AS PERC_DILUICAO,
        ROW_NUMBER() OVER (ORDER BY d.ORDEM_CABINE, d.MISTURA, d.COD_REF) AS ORDEM
    FROM Detalhes d
    LEFT JOIN MediaCargaPorMistura m ON m.CABINE = d.CABINE AND m.MISTURA = d.MISTURA
    LEFT JOIN DiluicaoPorMistura dil ON dil.COD_REF = d.MISTURA;
END;
GO
