-- =============================================================================
-- Notificação aos responsáveis quando são geradas manutenções preventivas
--
-- Configurável POR ÂMBITO DE MANUTENÇÃO (MAN_DIC_AMBITOS.NOTIFICA_PREVENTIVAS).
-- Destinatários = "Responsáveis Manutenções" do âmbito (MAN_DIC_AMBITO_UTILIZADORES).
-- Um email por cada manutenção preventiva criada.
--
-- Fluxo:
--   Job MAN_CRIAR_MANUTENCOES_PREVENTIVAS_PREDITIVAS
--     -> SP MAN_CRIAR_MANUTENCOES_PREVENTIVAS  (recolhe @IDS criados)
--     -> POST /sgiid/rest/sirb/getAlertasManutencoesPreventivas  {"IDS": "..."}
--     -> SP GET_ALERTA_MANUTENCOES_PREVENTIVAS_CRIADAS @IDS  (1 linha por manutenção)
--     -> GER_EVENTOS_CONF (MODULO 14 / MOMENTO 'PREVENTIVAS CRIADAS')
--
-- Campos disponíveis no assunto/mensagem:
--   {N_MANUTENCAO} {AMBITO} {LOCALIZACAO} {EQUIPAMENTO} {DESCRICAO_MANUTENCAO}
--   {DATA_REALIZACAO} {EQUIPA_UTILIZADOR} {NIVEL} {LINK}
-- =============================================================================

USE [SGIID]
GO

-- -----------------------------------------------------------------------------
-- 1. Flag por âmbito
-- -----------------------------------------------------------------------------
IF NOT EXISTS (SELECT 1 FROM sys.columns
               WHERE object_id = OBJECT_ID('MAN_DIC_AMBITOS')
                 AND name = 'NOTIFICA_PREVENTIVAS')
BEGIN
    ALTER TABLE MAN_DIC_AMBITOS
        ADD NOTIFICA_PREVENTIVAS bit NOT NULL
        CONSTRAINT DF_MAN_DIC_AMBITOS_NOTIFICA_PREVENTIVAS DEFAULT (0)
END
GO

-- -----------------------------------------------------------------------------
-- 2. SP que devolve os alertas a enviar (uma linha por manutenção criada)
--    Colunas (ordem usada pelo SIRB.getAlertasManutencoesPreventivas):
--      0 EMAILS | 1 N_MANUTENCAO | 2 AMBITO | 3 LOCALIZACAO | 4 EQUIPAMENTO
--      5 DESCRICAO_MANUTENCAO | 6 DATA_REALIZACAO | 7 EQUIPA_UTILIZADOR
--      8 NIVEL | 9 LINK
-- -----------------------------------------------------------------------------
IF OBJECT_ID('GET_ALERTA_MANUTENCOES_PREVENTIVAS_CRIADAS', 'P') IS NOT NULL
    DROP PROCEDURE GET_ALERTA_MANUTENCOES_PREVENTIVAS_CRIADAS
GO

CREATE PROCEDURE [dbo].[GET_ALERTA_MANUTENCOES_PREVENTIVAS_CRIADAS]
    @IDS varchar(MAX)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @URL varchar(255) = 'http://192.168.40.101:8080/sgiid'

    SELECT
        -- Responsáveis Manutenções do âmbito (separador ';')
        STUFF((
            SELECT DISTINCT ';' + u.EMAIL
            FROM MAN_DIC_AMBITO_UTILIZADORES au
            INNER JOIN GER_UTILIZADORES u ON u.ID_UTILIZADOR = au.ID_UTILIZADOR
            WHERE au.ID_EQUIPA = amb.ID
              AND ISNULL(u.INATIVO, 0) = 0
              AND ISNULL(u.EMAIL, '') <> ''
            FOR XML PATH(''), TYPE).value('.', 'varchar(MAX)'), 1, 1, '')  AS EMAILS,
        CAST(c.ID_MANUTENCAO_CAB AS varchar(20))                           AS N_MANUTENCAO,
        ISNULL(amb.NOME, '')                                               AS AMBITO,
        ISNULL(loc.DESCRICAO, '')                                          AS LOCALIZACAO,
        ISNULL(e.NOME, '')                                                 AS EQUIPAMENTO,
        ISNULL(e.DESCRICAO_MANUTENCAO, '')                                 AS DESCRICAO_MANUTENCAO,
        ISNULL(FORMAT(c.DATA_REALIZACAO, 'dd/MM/yyyy'), '')                AS DATA_REALIZACAO,
        CASE
            WHEN c.TIPO_RESPONSAVEL = 'U' AND c.UTILIZADOR IS NOT NULL
                THEN (SELECT xy.NOME_UTILIZADOR FROM GER_UTILIZADORES xy WHERE xy.ID_UTILIZADOR = c.UTILIZADOR)
            WHEN c.TIPO_RESPONSAVEL = 'EX'
                THEN ISNULL(c.NOME_FORNECEDOR, '')
            ELSE ISNULL(eq.NOME_EQUIPA, '')
        END                                                                AS EQUIPA_UTILIZADOR,
        ISNULL(CAST(c.NIVEL AS varchar(10)), '')                           AS NIVEL,
        @URL + '/#/lista_preventivas/view?id='
            + CAST(c.ID_MANUTENCAO_CAB AS varchar(20))                     AS LINK
    FROM MAN_MOV_MANUTENCAO_CAB c
    INNER JOIN MAN_DIC_AMBITOS amb ON amb.ID = c.AMBITO_MANUTENCAO
    LEFT JOIN MAN_MOV_MANUTENCAO_EQUIPAMENTOS e ON e.ID_MANUTENCAO = c.EQUIPAMENTO
    LEFT JOIN (
        SELECT CONCAT('E', ID) AS ID, DESCRICAO FROM MAN_DIC_EDIFICIOS
        UNION ALL
        SELECT CONCAT('P', a.ID) AS ID, b.DESCRICAO + '/' + a.DESCRICAO
        FROM MAN_DIC_PISOS a INNER JOIN MAN_DIC_EDIFICIOS b ON a.ID_EDIFICIO = b.ID
        UNION ALL
        SELECT CONCAT('D', a.ID) AS ID, c.DESCRICAO + '/' + b.DESCRICAO + '/' + a.DESCRICAO
        FROM MAN_DIC_DIVISOES a
        INNER JOIN MAN_DIC_PISOS b ON a.ID_PISO = b.ID
        INNER JOIN MAN_DIC_EDIFICIOS c ON b.ID_EDIFICIO = c.ID
    ) loc ON loc.ID = CONCAT(ISNULL(c.TIPO_LOCALIZACAO, 'E'), c.LOCALIZACAO)
    LEFT JOIN MAN_DIC_EQUIPAS eq ON eq.ID = CASE WHEN c.TIPO_RESPONSAVEL = 'E' THEN c.UTILIZADOR ELSE 0 END
    WHERE c.ID_MANUTENCAO_CAB IN (SELECT TRY_CAST(value AS int) FROM STRING_SPLIT(@IDS, ','))
      AND ISNULL(amb.NOTIFICA_PREVENTIVAS, 0) = 1
      AND ISNULL(amb.ATIVO, 1) = 1
    ORDER BY amb.NOME, c.ID_MANUTENCAO_CAB
END
GO

-- -----------------------------------------------------------------------------
-- 3. Configuração do evento
-- -----------------------------------------------------------------------------
IF NOT EXISTS (SELECT 1 FROM GER_EVENTOS_CONF WHERE MOMENTO = 'PREVENTIVAS CRIADAS')
BEGIN
    SET IDENTITY_INSERT GER_EVENTOS_CONF ON;

    INSERT INTO GER_EVENTOS_CONF (ID_EVENTO, MODULO, MOMENTO, PAGINA, ESTADO, EMAIL_PARA, EMAIL_DE, EMAIL_ANEXO, EMAIL_ASSUNTO, EMAIL_MENSAGEM, OBS)
    VALUES (
        89,
        14,
        'PREVENTIVAS CRIADAS',
        'MANUTENCOES PREVENTIVAS',
        1,
        '',
        'alertas.it.doureca@gmail.com',
        0,
        'MANUTENÇÃO PREVENTIVA CRIADA - {EQUIPAMENTO} - {AMBITO}',
        '<p><b>Nº MANUTENÇÃO</b>: {N_MANUTENCAO}<br>'
        + '<b>ÂMBITO</b>: {AMBITO}<br>'
        + '<b>LOCALIZACAO</b>: {LOCALIZACAO}<br>'
        + '<b>EQUIPAMENTO</b>: {EQUIPAMENTO}<br>'
        + '<b>DESCRICAO MANUTENÇÃO</b>: {DESCRICAO_MANUTENCAO}<br>'
        + '<b>DATA REALIZAÇÃO</b>: {DATA_REALIZACAO}<br>'
        + '<b>NÍVEL</b>: {NIVEL}<br>'
        + '<b>EQUIPA/UTILIZADOR/FORNECEDOR (responsável pela realização da manutenção)</b>: {EQUIPA_UTILIZADOR}</p>'
        + '<p><b>LINK</b>: {LINK}</p>'
        + '<p>Email automático do SGIID - por favor não responder.</p>',
        'Alerta automático - manutenções preventivas geradas (activado por âmbito em MAN_DIC_AMBITOS.NOTIFICA_PREVENTIVAS)'
    );

    SET IDENTITY_INSERT GER_EVENTOS_CONF OFF;

    INSERT INTO GER_CAMPOS_DISP (ID_EVENTO_CONF, DESCRICAO_CAMPO, NOME_CAMPO) VALUES
        (89, 'Número Manutenção', 'N_MANUTENCAO'),
        (89, 'Âmbito Manutenção', 'AMBITO'),
        (89, 'Localização', 'LOCALIZACAO'),
        (89, 'Equipamento', 'EQUIPAMENTO'),
        (89, 'Descrição Manutenção', 'DESCRICAO_MANUTENCAO'),
        (89, 'Data Realização', 'DATA_REALIZACAO'),
        (89, 'Nível', 'NIVEL'),
        (89, 'Equipa/Utlizador', 'EQUIPA_UTILIZADOR'),
        (89, 'Link', 'LINK');
END
GO

-- -----------------------------------------------------------------------------
-- 4. Chamada do alerta no fim de MAN_CRIAR_MANUTENCOES_PREVENTIVAS
--    (aplicar este bloco a seguir ao alerta de stock já existente)
-- -----------------------------------------------------------------------------
/*
    IF @IDS IS NOT NULL BEGIN
        DECLARE @ObjectP int
        DECLARE @ResponseTextP varchar(8000)
        DECLARE @BodyP varchar(MAX) = '[{"IDS": "' + @IDS + '"}]'
        EXEC sp_OACreate 'MSXML2.XMLHTTP', @ObjectP OUT
        EXEC sp_OAMethod @ObjectP, 'open', NULL, 'post',
            'http://192.168.40.101:8080/sgiid/rest/sirb/getAlertasManutencoesPreventivas', 'false'
        EXEC sp_OAMethod @ObjectP, 'setRequestHeader', null, 'Content-Type', 'application/json'
        EXEC sp_OAMethod @ObjectP, 'send', null, @BodyP
        EXEC sp_OAMethod @ObjectP, 'responseText', @ResponseTextP OUTPUT
        EXEC sp_OADestroy @ObjectP
    END
*/
