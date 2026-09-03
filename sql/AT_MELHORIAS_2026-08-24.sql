-- ============================================================
-- SEGURANCA NO TRABALHO - Melhorias 2026-08-24
-- ============================================================
-- Relatorio de Investigacao de Acidentes de Trabalho/Quase Acidentes
-- e Relatorio de Investigacao de Incidentes Industriais/Tecnologicos.
--
-- Script idempotente: pode ser corrido as vezes que forem precisas.
-- ============================================================


-- ============================================================
-- 1. ENTREVISTAS - n mecanografico
-- ============================================================
-- Nas entrevistas a testemunhas/envolvidos e ao responsavel/colegas de
-- seccao, a "Funcao" foi substituida no ecra pelo n mecanografico, para
-- o nome poder ser preenchido automaticamente a partir dele.
--
-- A coluna FUNCAO NAO e apagada: guarda o que ja foi escrito e os
-- relatorios antigos continuam a poder mostra-la.

IF COL_LENGTH('AT_ENTREVISTAS', 'NUMERO') IS NULL
    ALTER TABLE AT_ENTREVISTAS ADD NUMERO NVARCHAR(20);
GO

IF COL_LENGTH('AT_ENTREVISTAS_RESPONSAVEL', 'NUMERO') IS NULL
    ALTER TABLE AT_ENTREVISTAS_RESPONSAVEL ADD NUMERO NVARCHAR(20);
GO


-- ============================================================
-- 2. INCIDENTES - assinaturas de trabalhadores similares
-- ============================================================
-- O relatorio de incidentes passa a ter, no fecho, a mesma tabela de
-- assinaturas que ja existia nos acidentes (AT_ASSINATURAS_SIMILARES).
-- E tabela propria e nao reaproveitamento daquela: as chaves sao
-- diferentes (ID_INCIDENTE vs ID_OCORRENCIA) e os dois relatorios sao
-- independentes.
--
-- Sem FUNCAO de proposito: foi retirada dos dois ecras.

IF OBJECT_ID('AT_INCIDENTES_ASSINATURAS', 'U') IS NULL
CREATE TABLE AT_INCIDENTES_ASSINATURAS (
    ID                  INT IDENTITY(1,1) PRIMARY KEY,
    ID_INCIDENTE        INT,
    NOME                NVARCHAR(200),
    NUMERO              NVARCHAR(20),
    DATA_CONHECIMENTO   DATE,
    UTZ_CRIA            INT, DATA_CRIA   DATE,
    UTZ_MODIF           INT, DATA_MODIF  DATE,
    UTZ_ANULA           INT, DATA_ANULA  DATE,
    INATIVO             BIT DEFAULT 0
);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_AT_INCIDENTES_ASSIN_INC')
    CREATE INDEX IX_AT_INCIDENTES_ASSIN_INC ON AT_INCIDENTES_ASSINATURAS (ID_INCIDENTE);
GO

