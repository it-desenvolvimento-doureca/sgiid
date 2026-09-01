-- ============================================================
-- MELHORIAS 2026 - Segurança no Trabalho / Acidentes e Incidentes
-- Base: PLANO_MELHORIAS_ACIDENTES_2026-08-10.md (docs/)
-- Origem: anotações do cliente em Downloads\doureca\acidentes
--
-- Cobre as fases F2 a F5. A F1 (terminologia) foi só frontend + Jasper.
-- A F6 (novo módulo de Incidentes) fica em ficheiro próprio.
--
-- Idempotente: pode ser re-executado sem efeitos colaterais.
-- SGBD: SQL Server
-- ============================================================


-- ============================================================
-- F2 - CAMPOS NOVOS EM AT_OCORRENCIAS
-- ============================================================

-- Ficha de Aptidão (anotação: "F.Aptidão: Sim [] Não [] DATA:")
IF COL_LENGTH('AT_OCORRENCIAS', 'FICHA_APTIDAO') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD FICHA_APTIDAO BIT NULL;
GO
IF COL_LENGTH('AT_OCORRENCIAS', 'DATA_FICHA_APTIDAO') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD DATA_FICHA_APTIDAO DATE NULL;
GO

-- Hora de início do afastamento (TIME, para casar com HORA_ACIDENTE que é java.sql.Time)
IF COL_LENGTH('AT_OCORRENCIAS', 'HORA_INICIO_AFASTAMENTO') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD HORA_INICIO_AFASTAMENTO TIME NULL;
GO

-- Primeiros socorros + avaliação de desempenho da equipa
IF COL_LENGTH('AT_OCORRENCIAS', 'PRIMEIROS_SOCORROS') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD PRIMEIROS_SOCORROS BIT NULL;
GO
IF COL_LENGTH('AT_OCORRENCIAS', 'AVALIACAO_EQUIPA_SOCORROS') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD AVALIACAO_EQUIPA_SOCORROS NVARCHAR(MAX) NULL;
GO

-- Nº de dias de Incapacidade Temporária.
-- NOTA: distinto de DIAS_PERDIDOS, que já existe. No relatório o cliente
-- renomeou "Dias perdidos" para "Duração (dias)" — a coluna DIAS_PERDIDOS
-- mantém-se e é essa que passa a ter o rótulo "Duração (dias)".
IF COL_LENGTH('AT_OCORRENCIAS', 'NR_DIAS_IT') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD NR_DIAS_IT INT NULL;
GO


-- ============================================================
-- F2 - ANEXOS (tabela filha)
-- Serve o "Relatório Assinado - Anexar" e qualquer anexo futuro
-- (fotografias, esquema do local, ficha de aptidão digitalizada).
--
-- O conteúdo fica NA BASE DE DADOS, como nas restantes tabelas de
-- ficheiros do SGIID (ex.: QUA_MC_DERROGACOES_FICHEIROS): o ficheiro é
-- guardado como data URL base64 partida em duas colunas NVARCHAR(MAX).
-- A divisão em FICHEIRO_1/FICHEIRO_2 é a convenção já usada em todo o
-- sistema e é o que o endpoint genérico getFILE espera.
-- ============================================================
IF OBJECT_ID('AT_OCORRENCIAS_ANEXOS', 'U') IS NULL
CREATE TABLE AT_OCORRENCIAS_ANEXOS (
    ID              INT IDENTITY(1,1) PRIMARY KEY,
    ID_OCORRENCIA   INT NOT NULL,
    NOME            NVARCHAR(255),
    CAMINHO         NVARCHAR(500),
    -- TIPO é a classe usada no preview: 'pdf' | 'img' | 'excel' | 'word' | 'txt' | extensão
    TIPO            NVARCHAR(100),
    -- DATATYPE é o mime type original devolvido pelo browser
    DATATYPE        NVARCHAR(255),
    TAMANHO         FLOAT,
    DESCRICAO       NVARCHAR(500),
    -- Classifica o anexo. 'REL_ASSINADO' = Relatório Assinado (F2).
    CATEGORIA       NVARCHAR(50),
    FICHEIRO_1      NVARCHAR(MAX),
    FICHEIRO_2      NVARCHAR(MAX),
    UTZ_CRIA        INT,
    DATA_CRIA       DATETIME,
    UTZ_ULT_MODIF   INT,
    DATA_ULT_MODIF  DATETIME,
    INATIVO         BIT DEFAULT 0
);
GO

-- Se a tabela já tinha sido criada por uma versão anterior deste script,
-- acrescenta as colunas de conteúdo.
IF COL_LENGTH('AT_OCORRENCIAS_ANEXOS', 'DATATYPE') IS NULL
    ALTER TABLE AT_OCORRENCIAS_ANEXOS ADD DATATYPE NVARCHAR(255);
GO
IF COL_LENGTH('AT_OCORRENCIAS_ANEXOS', 'FICHEIRO_1') IS NULL
    ALTER TABLE AT_OCORRENCIAS_ANEXOS ADD FICHEIRO_1 NVARCHAR(MAX);
GO
IF COL_LENGTH('AT_OCORRENCIAS_ANEXOS', 'FICHEIRO_2') IS NULL
    ALTER TABLE AT_OCORRENCIAS_ANEXOS ADD FICHEIRO_2 NVARCHAR(MAX);
GO

IF OBJECT_ID('IX_AT_OCORRENCIAS_ANEXOS_OCORRENCIA', 'I') IS NULL
    CREATE INDEX IX_AT_OCORRENCIAS_ANEXOS_OCORRENCIA
        ON AT_OCORRENCIAS_ANEXOS (ID_OCORRENCIA);
GO


-- ============================================================
-- F3 - PARTES DO CORPO: dividir opções agregadas
--
-- Anotações: "DIVIDIR" em Ombro/braço/cotovelo, Joelho/perna/tornozelo
-- e Antebraço/pulso; adicionar "Dedos da mão".
--
-- As colunas antigas (PA_OMBRO, PA_JOELHO, PA_ANTEBRACO) MANTÊM-SE para
-- não perder histórico. A migração abaixo propaga o valor antigo para a
-- primeira das novas colunas, que é a leitura mais conservadora.
-- ============================================================

-- Ombro/braço/cotovelo -> Ombro + Braço + Cotovelo
IF COL_LENGTH('AT_OCORRENCIAS', 'PA_BRACO') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD PA_BRACO BIT NULL;
GO
IF COL_LENGTH('AT_OCORRENCIAS', 'PA_COTOVELO') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD PA_COTOVELO BIT NULL;
GO

-- Joelho/perna/tornozelo -> Joelho + Perna + Tornozelo
IF COL_LENGTH('AT_OCORRENCIAS', 'PA_PERNA') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD PA_PERNA BIT NULL;
GO
IF COL_LENGTH('AT_OCORRENCIAS', 'PA_TORNOZELO') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD PA_TORNOZELO BIT NULL;
GO

-- Antebraço/pulso -> Antebraço + Pulso
IF COL_LENGTH('AT_OCORRENCIAS', 'PA_PULSO') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD PA_PULSO BIT NULL;
GO

-- Nova opção: Dedos da mão
IF COL_LENGTH('AT_OCORRENCIAS', 'PA_DEDOS_MAO') IS NULL
    ALTER TABLE AT_OCORRENCIAS ADD PA_DEDOS_MAO BIT NULL;
GO


-- ============================================================
-- F3 - MIGRAÇÃO DE DADOS
--
-- ATENÇÃO: correr UMA única vez. As instruções são idempotentes na forma
-- (só actualizam o que ainda está NULL), mas confirme o resultado antes
-- de dar por concluída a migração.
-- ============================================================

-- Grau da lesão: "Muito Grave" (MG) -> "Grave" (G).
-- Decisão do cliente em 2026-08-12. "Moderado" (MO) passa a existir
-- apenas para registos novos.
UPDATE AT_OCORRENCIAS
   SET GRAU_LESAO = 'G'
 WHERE GRAU_LESAO = 'MG';
GO

-- Propagação das partes do corpo agregadas para as novas colunas.
-- Só toca em linhas ainda não migradas (novas colunas a NULL).
UPDATE AT_OCORRENCIAS
   SET PA_BRACO     = 0,
       PA_COTOVELO  = 0
 WHERE PA_BRACO IS NULL;
GO

UPDATE AT_OCORRENCIAS
   SET PA_PERNA     = 0,
       PA_TORNOZELO = 0
 WHERE PA_PERNA IS NULL;
GO

UPDATE AT_OCORRENCIAS
   SET PA_PULSO     = 0
 WHERE PA_PULSO IS NULL;
GO

UPDATE AT_OCORRENCIAS
   SET PA_DEDOS_MAO = 0
 WHERE PA_DEDOS_MAO IS NULL;
GO


-- ============================================================
-- F4 - DIAGRAMA DE ISHIKAWA
--
-- O dicionário AT_DIC_CAUSAS_ACIDENTE já existe e já está ligado às
-- ocorrências por AT_OCORRENCIAS_CAUSAS_ACIDENTE. Só precisa de:
--   1) CATEGORIA  -> a espinha do diagrama
--   2) ORDEM      -> ordem de apresentação dentro da categoria
--   3) INATIVO    -> retirar as causas antigas SEM apagar histórico
--   4) PERMITE_TEXTO -> marca as opções "Outro" que abrem campo livre
-- ============================================================

IF COL_LENGTH('AT_DIC_CAUSAS_ACIDENTE', 'CATEGORIA') IS NULL
    ALTER TABLE AT_DIC_CAUSAS_ACIDENTE ADD CATEGORIA NVARCHAR(30) NULL;
GO
IF COL_LENGTH('AT_DIC_CAUSAS_ACIDENTE', 'ORDEM') IS NULL
    ALTER TABLE AT_DIC_CAUSAS_ACIDENTE ADD ORDEM INT NULL;
GO
IF COL_LENGTH('AT_DIC_CAUSAS_ACIDENTE', 'INATIVO') IS NULL
    ALTER TABLE AT_DIC_CAUSAS_ACIDENTE ADD INATIVO BIT NULL;
GO
IF COL_LENGTH('AT_DIC_CAUSAS_ACIDENTE', 'PERMITE_TEXTO') IS NULL
    ALTER TABLE AT_DIC_CAUSAS_ACIDENTE ADD PERMITE_TEXTO BIT NULL;
GO

-- Texto livre da opção "Outro" de cada espinha, por ocorrência.
IF COL_LENGTH('AT_OCORRENCIAS_CAUSAS_ACIDENTE', 'TEXTO_OUTRO') IS NULL
    ALTER TABLE AT_OCORRENCIAS_CAUSAS_ACIDENTE ADD TEXTO_OUTRO NVARCHAR(500) NULL;
GO

-- Retirar as causas antigas de circulação. NÃO são apagadas: continuam
-- ligadas a ocorrências históricas e têm de continuar a imprimir.
UPDATE AT_DIC_CAUSAS_ACIDENTE
   SET INATIVO = 1
 WHERE CATEGORIA IS NULL
   AND (INATIVO IS NULL OR INATIVO = 0);
GO

-- Tudo o que é inserido a seguir fica ativo.
-- ------------------------------------------------------------
-- Seed das causas do Diagrama de Ishikawa (Diagrama_Ishikawa.pdf)
-- ------------------------------------------------------------
IF OBJECT_ID('tempdb..#ISHIKAWA') IS NOT NULL DROP TABLE #ISHIKAWA;
CREATE TABLE #ISHIKAWA (
    CATEGORIA NVARCHAR(30),
    DESCRICAO NVARCHAR(255),
    ORDEM INT,
    PERMITE_TEXTO BIT
);

INSERT INTO #ISHIKAWA (CATEGORIA, DESCRICAO, ORDEM, PERMITE_TEXTO) VALUES
-- PESSOAS
('PESSOAS',     'Falta de Formação',                              1, 0),
('PESSOAS',     'Utilização Inadequada de EPI',                   2, 0),
('PESSOAS',     'Postura Incorreta',                              3, 0),
('PESSOAS',     'Comportamento de Risco',                         4, 0),
('PESSOAS',     'Falta de Experiência',                           5, 0),
('PESSOAS',     'Falta de atenção',                               6, 0),
('PESSOAS',     'Utilização de Vestuário/Acessórios',             7, 0),
('PESSOAS',     'Outro',                                         99, 1),
-- MÉTODOS
('METODOS',     'Procedimentos Desatualizados',                   1, 0),
('METODOS',     'Método de Trabalho',                             2, 0),
('METODOS',     'Falta de Planeamento',                           3, 0),
('METODOS',     'Instruções Pouco Claras',                        4, 0),
('METODOS',     'Etapas trocadas',                                5, 0),
('METODOS',     'Armazenamento Inseguro',                         6, 0),
('METODOS',     'Outro',                                         99, 1),
-- ORGANIZAÇÃO
('ORGANIZACAO', 'Falta de Supervisão',                            1, 0),
('ORGANIZACAO', 'Comunicação Ineficaz',                           2, 0),
('ORGANIZACAO', 'Falta de Liderança',                             3, 0),
('ORGANIZACAO', 'Inexistência de procedimentos sobre a segurança',4, 0),
('ORGANIZACAO', 'Formação insuficiente',                          5, 0),
('ORGANIZACAO', 'Prioridade à produção',                          6, 0),
('ORGANIZACAO', 'Outro',                                         99, 1),
-- MÁQUINAS
('MAQUINAS',    'Falta de manutenção',                            1, 0),
('MAQUINAS',    'Equipamento com defeito',                        2, 0),
('MAQUINAS',    'Máquina sem proteção',                           3, 0),
('MAQUINAS',    'Comandos não identificados',                     4, 0),
('MAQUINAS',    'Arranque Inesperado',                            5, 0),
('MAQUINAS',    'Cabos Expostos',                                 6, 0),
('MAQUINAS',    'Outro',                                         99, 1),
-- AMBIENTE
('AMBIENTE',    'Iluminação inadequada',                          1, 0),
('AMBIENTE',    'Temperatura extrema',                            2, 0),
('AMBIENTE',    'Ruído elevado',                                  3, 0),
('AMBIENTE',    'Presença de substâncias perigosas',              4, 0),
('AMBIENTE',    'Limpeza Inadequada',                             5, 0),
('AMBIENTE',    'Pavimento danificado',                           6, 0),
('AMBIENTE',    'Outro',                                         99, 1),
-- MATERIAIS
('MATERIAIS',   'Ferramentas Inadequadas',                        1, 0),
('MATERIAIS',   'Derrames',                                       2, 0),
('MATERIAIS',   'Carga Excessiva',                                3, 0),
('MATERIAIS',   'Transporte inseguro',                            4, 0),
('MATERIAIS',   'Falta de Identificação',                         5, 0),
('MATERIAIS',   'Ausência de sinalização',                        6, 0),
('MATERIAIS',   'Outro',                                         99, 1);
GO

-- Insere só o que ainda não existe (categoria + descrição).
INSERT INTO AT_DIC_CAUSAS_ACIDENTE
       (DESCRICAO, CATEGORIA, ORDEM, PERMITE_TEXTO, INATIVO, DATA_CRIA)
SELECT i.DESCRICAO, i.CATEGORIA, i.ORDEM, i.PERMITE_TEXTO, 0, GETDATE()
  FROM #ISHIKAWA i
 WHERE NOT EXISTS (
       SELECT 1 FROM AT_DIC_CAUSAS_ACIDENTE d
        WHERE d.CATEGORIA = i.CATEGORIA
          AND d.DESCRICAO = i.DESCRICAO);
GO

DROP TABLE #ISHIKAWA;
GO


-- ============================================================
-- F5 - ASSINATURAS DE TRABALHADORES EM POSTOS/TAREFAS SIMILARES
--
-- Anotação na pág. 3 do relatório: nova secção com "Tomei Conhecimento"
-- e Data, em várias linhas.
-- ============================================================
IF OBJECT_ID('AT_ASSINATURAS_SIMILARES', 'U') IS NULL
CREATE TABLE AT_ASSINATURAS_SIMILARES (
    ID                  INT IDENTITY(1,1) PRIMARY KEY,
    ID_OCORRENCIA       INT NOT NULL,
    NOME                NVARCHAR(255),
    -- Nº mecanográfico, para casar com o padrão de AT_TESTEMUNHAS.NUMERO
    NUMERO              NVARCHAR(50),
    FUNCAO              NVARCHAR(255),
    DATA_CONHECIMENTO   DATE,
    UTZ_CRIA            INT,
    DATA_CRIA           DATETIME,
    UTZ_MODIF           INT,
    DATA_MODIF          DATETIME
);
GO

IF OBJECT_ID('IX_AT_ASSINATURAS_SIMILARES_OCORRENCIA', 'I') IS NULL
    CREATE INDEX IX_AT_ASSINATURAS_SIMILARES_OCORRENCIA
        ON AT_ASSINATURAS_SIMILARES (ID_OCORRENCIA);
GO


-- ============================================================
-- VERIFICAÇÃO
-- ============================================================
PRINT '--- Colunas novas em AT_OCORRENCIAS ---';
SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE
  FROM INFORMATION_SCHEMA.COLUMNS
 WHERE TABLE_NAME = 'AT_OCORRENCIAS'
   AND COLUMN_NAME IN ('FICHA_APTIDAO','DATA_FICHA_APTIDAO','HORA_INICIO_AFASTAMENTO',
                       'PRIMEIROS_SOCORROS','AVALIACAO_EQUIPA_SOCORROS','NR_DIAS_IT',
                       'PA_BRACO','PA_COTOVELO','PA_PERNA','PA_TORNOZELO','PA_PULSO','PA_DEDOS_MAO')
 ORDER BY COLUMN_NAME;

PRINT '--- Causas Ishikawa por categoria (deve dar 8/7/7/7/7/7) ---';
SELECT CATEGORIA, COUNT(*) AS TOTAL
  FROM AT_DIC_CAUSAS_ACIDENTE
 WHERE INATIVO = 0 AND CATEGORIA IS NOT NULL
 GROUP BY CATEGORIA
 ORDER BY CATEGORIA;

PRINT '--- Causas antigas retiradas (mantidas para historico) ---';
SELECT COUNT(*) AS CAUSAS_INATIVAS
  FROM AT_DIC_CAUSAS_ACIDENTE
 WHERE INATIVO = 1;

PRINT '--- Ainda existe algum grau MG? (deve dar 0) ---';
SELECT COUNT(*) AS GRAU_MG_RESTANTE
  FROM AT_OCORRENCIAS
 WHERE GRAU_LESAO = 'MG';
GO
