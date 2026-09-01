-- =============================================================================
-- Meios de Controlo - destinatários dos alertas por email
-- Pedido cliente (melhorias.pdf, 2026-07-29): os alertas de calibração de
-- equipamentos e de verificação de gabaritos devem ir para a Maria Venade e o
-- Rui Ribeiro (quem tem acesso à gestão de equipamentos).
--
-- Os destinatários são lidos da coluna email_para de GER_EVENTOS_CONF pelo
-- serviço de eventos (EVENTOS.cs -> getEventoMeiosControlo), separados por ';'.
-- Não há alteração de código; é apenas configuração de dados.
--
-- ⚠️ Confirmar os endereços e que existem linhas ESTADO = 1 para os 2 MOMENTOs
--    antes de correr. Se não existirem, criar (ver bloco comentado no fim).
-- =============================================================================

UPDATE GER_EVENTOS_CONF
   SET email_para = 'maria.venade@doureca.pt;rui.ribeiro@doureca.pt'
 WHERE ESTADO = 1
   AND MOMENTO IN ('MC_CALIBRACAO_EQUIPAMENTOS', 'MC_VERIFICACAO_GABARITOS');

-- Verificação:
-- SELECT MOMENTO, ESTADO, email_para FROM GER_EVENTOS_CONF
--  WHERE MOMENTO IN ('MC_CALIBRACAO_EQUIPAMENTOS','MC_VERIFICACAO_GABARITOS');
