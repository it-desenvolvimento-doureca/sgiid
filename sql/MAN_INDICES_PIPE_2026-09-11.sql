-- Indices em falta no caminho quente do quadro pipe de Manutencao
-- Data: 2026-09-11
--
-- Contexto: MAN_GET_ALL_PIPE com @UNIDADE = NULL (o caso real: "todas as
-- unidades") e um utilizador admin custa ~27-34 s de CPU e ~4.000.000 de
-- leituras logicas. O quadro faz refresh a cada 60 s, logo cada admin com o
-- ecra aberto mantem uma execucao destas quase permanentemente em curso.
--
-- Diagnostico (SET STATISTICS IO, @ID=1, @UNIDADE=NULL):
--   MAN_MOV_MANUTENCAO_NOTAS ... scan count 7564, logical reads 3.418.928
-- Sao 85% de todo o I/O da procedure: 452 leituras por scan, ou seja um scan
-- completo da tabela (39.183 linhas) por cada uma das ~7.620 linhas candidatas.
--
-- Causa: a tabela so tem a PK clustered em ID. Nao ha indice em
-- ID_MANUTENCAO_CAB, que e a coluna pela qual CTE_NOTAS agrupa e faz join,
-- por isso o optimizador nao tem alternativa a re-agregar a tabela inteira.
--
-- As duas tabelas de lookup sofrem do mesmo (7.519 e 7.565 scans, uma por
-- linha candidata, vindas dos EXISTS do filtro de utilizador/equipa). Custam
-- pouco hoje por serem pequenas, mas o padrao e o mesmo e o indice e barato.

USE SGIID;
GO

-- Principal: elimina ~3,4 M de leituras por execucao.
IF NOT EXISTS (SELECT 1 FROM sys.indexes
               WHERE name = 'IX_MAN_MOV_MANUTENCAO_NOTAS_ID_MANUTENCAO_CAB'
                 AND object_id = OBJECT_ID('dbo.MAN_MOV_MANUTENCAO_NOTAS'))
    CREATE NONCLUSTERED INDEX IX_MAN_MOV_MANUTENCAO_NOTAS_ID_MANUTENCAO_CAB
        ON dbo.MAN_MOV_MANUTENCAO_NOTAS (ID_MANUTENCAO_CAB);
GO

-- Secundarios: EXISTS do filtro de utilizador/equipa, avaliados por linha.
IF NOT EXISTS (SELECT 1 FROM sys.indexes
               WHERE name = 'IX_MAN_DIC_AMBITO_UTILIZADORES_UTZ_EQUIPA'
                 AND object_id = OBJECT_ID('dbo.MAN_DIC_AMBITO_UTILIZADORES'))
    CREATE NONCLUSTERED INDEX IX_MAN_DIC_AMBITO_UTILIZADORES_UTZ_EQUIPA
        ON dbo.MAN_DIC_AMBITO_UTILIZADORES (ID_UTILIZADOR, ID_EQUIPA);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes
               WHERE name = 'IX_MAN_DIC_EQUIPAS_UTILIZADORES_EQUIPA_UTZ'
                 AND object_id = OBJECT_ID('dbo.MAN_DIC_EQUIPAS_UTILIZADORES'))
    CREATE NONCLUSTERED INDEX IX_MAN_DIC_EQUIPAS_UTILIZADORES_EQUIPA_UTZ
        ON dbo.MAN_DIC_EQUIPAS_UTILIZADORES (ID_EQUIPA, ID_UTILIZADOR);
GO

-- Forca recompilacao para que a SP passe a considerar os novos indices.
EXEC sp_recompile 'dbo.MAN_GET_ALL_PIPE';
GO

-- ---------------------------------------------------------------------------
-- ROLLBACK, se necessario:
--   DROP INDEX IX_MAN_MOV_MANUTENCAO_NOTAS_ID_MANUTENCAO_CAB ON dbo.MAN_MOV_MANUTENCAO_NOTAS;
--   DROP INDEX IX_MAN_DIC_AMBITO_UTILIZADORES_UTZ_EQUIPA ON dbo.MAN_DIC_AMBITO_UTILIZADORES;
--   DROP INDEX IX_MAN_DIC_EQUIPAS_UTILIZADORES_EQUIPA_UTZ ON dbo.MAN_DIC_EQUIPAS_UTILIZADORES;
--   EXEC sp_recompile 'dbo.MAN_GET_ALL_PIPE';
-- ---------------------------------------------------------------------------

-- VERIFICACAO, depois de correr:
--   SET STATISTICS IO ON; SET STATISTICS TIME ON;
--   EXEC dbo.MAN_GET_ALL_PIPE @ID = 1, @UNIDADE = NULL;
-- Baseline antes do indice: CPU 26.797 ms, 4.024.122 leituras,
--                           MAN_MOV_MANUTENCAO_NOTAS com 3.418.928.
