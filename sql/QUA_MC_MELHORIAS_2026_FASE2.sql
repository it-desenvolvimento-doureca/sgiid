-- =============================================================================
-- Meios de Controlo - Fase 2 (Declarações NC + Derrogações + Em calibração externa)
-- Alterações de BD para: (A) extensão da declaração a gabaritos,
-- (B) ligação derrogações ao meio (equipamento/gabarito), (C) em calibração externa
--
-- Data: 2026-08-03
-- =============================================================================

-- ====== A) Declaração de Não Conformidade — suporte a gabaritos ======
IF COL_LENGTH('QUA_MC_DECLARACOES_NC', 'TIPO_MEIO') IS NULL
  ALTER TABLE QUA_MC_DECLARACOES_NC ADD TIPO_MEIO CHAR(1) DEFAULT 'E';

IF COL_LENGTH('QUA_MC_DECLARACOES_NC', 'ID_GABARITO') IS NULL
  ALTER TABLE QUA_MC_DECLARACOES_NC ADD ID_GABARITO INT NULL;

IF COL_LENGTH('QUA_MC_DECLARACOES_NC', 'COD_GABARITO') IS NULL
  ALTER TABLE QUA_MC_DECLARACOES_NC ADD COD_GABARITO NVARCHAR(100) NULL;

IF COL_LENGTH('QUA_MC_DECLARACOES_NC', 'ID_DERROGACAO') IS NULL
  ALTER TABLE QUA_MC_DECLARACOES_NC ADD ID_DERROGACAO INT NULL;

-- ====== B) Derrogações MC — ligação ao meio (equipamento/gabarito) ======
IF COL_LENGTH('QUA_MC_DERROGACOES', 'TIPO_MEIO') IS NULL
  ALTER TABLE QUA_MC_DERROGACOES ADD TIPO_MEIO CHAR(1) NULL;

IF COL_LENGTH('QUA_MC_DERROGACOES', 'ID_EQUIPAMENTO') IS NULL
  ALTER TABLE QUA_MC_DERROGACOES ADD ID_EQUIPAMENTO INT NULL;

IF COL_LENGTH('QUA_MC_DERROGACOES', 'ID_GABARITO') IS NULL
  ALTER TABLE QUA_MC_DERROGACOES ADD ID_GABARITO INT NULL;

-- ====== Verificação ======
-- SELECT * FROM QUA_MC_DECLARACOES_NC WHERE TIPO_MEIO = 'G' OR ID_GABARITO IS NOT NULL;
-- SELECT * FROM QUA_MC_DERROGACOES WHERE TIPO_MEIO IS NOT NULL OR ID_EQUIPAMENTO IS NOT NULL OR ID_GABARITO IS NOT NULL;
