-- =============================================================================
-- Alertas Meios de Controlo (Qualidade) - calibrações/verificações a expirar
-- Executados pelo EVENTOS_DOURECA (C:\work\source\repos\EVENTOS_DOURECA):
--   getAlertasCalibracaoEquipamentos() -> MOMENTO = 'MC_CALIBRACAO_EQUIPAMENTOS' (ID_EVENTO 87)
--   getAlertasVerificacaoGabaritos()   -> MOMENTO = 'MC_VERIFICACAO_GABARITOS'   (ID_EVENTO 88)
-- Placeholders disponíveis no assunto/mensagem: {TOTAL} {DATA} {TABELA}
-- (registados em GER_CAMPOS_DISP para aparecerem na configuração de eventos)
-- NOTA: ajustar EMAIL_PARA (separador ';') antes de executar.
-- Limiar "expira brevemente" = 30 dias (definido na query do EVENTOS_DOURECA).
-- =============================================================================

SET IDENTITY_INSERT GER_EVENTOS_CONF ON;

INSERT INTO GER_EVENTOS_CONF (ID_EVENTO, MODULO, MOMENTO, PAGINA, ESTADO, EMAIL_PARA, EMAIL_ANEXO, EMAIL_ASSUNTO, EMAIL_MENSAGEM, OBS)
VALUES (
    87,
    5,
    'MC_CALIBRACAO_EQUIPAMENTOS',
    'mc_equipamentos',
    1,
    'maria.venade@doureca.pt',
    0,
    'SGIID - {TOTAL} equipamento(s) com calibração expirada ou a expirar',
    '<p>Bom dia,</p><p>À data de {DATA} existem <b>{TOTAL}</b> equipamento(s) com a calibração expirada ou a expirar nos próximos 30 dias:</p>{TABELA}<p>Email automático do SGIID - por favor não responder.</p>',
    'Alerta automático Meios de Controlo - calibração de equipamentos'
);

INSERT INTO GER_EVENTOS_CONF (ID_EVENTO, MODULO, MOMENTO, PAGINA, ESTADO, EMAIL_PARA, EMAIL_ANEXO, EMAIL_ASSUNTO, EMAIL_MENSAGEM, OBS)
VALUES (
    88,
    5,
    'MC_VERIFICACAO_GABARITOS',
    'mc_gabaritos',
    1,
    'maria.venade@doureca.pt',
    0,
    'SGIID - {TOTAL} gabarito(s) com verificação expirada ou a expirar',
    '<p>Bom dia,</p><p>À data de {DATA} existem <b>{TOTAL}</b> gabarito(s) com a verificação expirada ou a expirar nos próximos 30 dias:</p>{TABELA}<p>Email automático do SGIID - por favor não responder.</p>',
    'Alerta automático Meios de Controlo - verificação de gabaritos'
);

SET IDENTITY_INSERT GER_EVENTOS_CONF OFF;

-- Campos disponíveis para os templates (GER_CAMPOS_DISP)
INSERT INTO GER_CAMPOS_DISP (ID_EVENTO_CONF, DESCRICAO_CAMPO, NOME_CAMPO) VALUES
    (87, 'Total de equipamentos', 'TOTAL'),
    (87, 'Data do alerta', 'DATA'),
    (87, 'Tabela de equipamentos', 'TABELA'),
    (88, 'Total de gabaritos', 'TOTAL'),
    (88, 'Data do alerta', 'DATA'),
    (88, 'Tabela de gabaritos', 'TABELA');
