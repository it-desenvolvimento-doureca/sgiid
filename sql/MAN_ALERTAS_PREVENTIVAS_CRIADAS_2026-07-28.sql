-- =============================================================================
-- Notificação aos responsáveis quando são geradas manutenções preventivas
--
-- Configurável POR ÂMBITO DE MANUTENÇÃO (MAN_DIC_AMBITOS.NOTIFICA_PREVENTIVAS).
-- Destinatários = "Responsáveis Manutenções" do âmbito (MAN_DIC_AMBITO_UTILIZADORES).
--
-- Fluxo:
--   Job MAN_CRIAR_MANUTENCOES_PREVENTIVAS_PREDITIVAS
--     -> SP MAN_CRIAR_MANUTENCOES_PREVENTIVAS  (recolhe @IDS criados)
--     -> POST /sgiid/rest/sirb/getAlertasManutencoesPreventivas  {"IDS": "..."}
--     -> SP GET_ALERTA_MANUTENCOES_PREVENTIVAS_CRIADAS @IDS  (1 linha por âmbito)
--     -> GER_EVENTOS_CONF (MODULO 14 / MOMENTO 'Alertas Preventivas Criadas')
--
-- Placeholders do assunto/mensagem: {ambito} {total} {data} {tabela} {link}
-- NOTA: ID_EVENTO 89 assumido livre - confirmar com
--       SELECT MAX(ID_EVENTO) FROM GER_EVENTOS_CONF antes de executar.
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
-- 2. SP que devolve os alertas a enviar (uma linha por âmbito)
--    Colunas (ordem usada pelo SIRB.getAlertasManutencoesPreventivas):
--      0 EMAILS | 1 AMBITO | 2 TOTAL | 3 DATA | 4 TABELA | 5 LINK
-- -----------------------------------------------------------------------------
IF OBJECT_ID('GET_ALERTA_MANUTENCOES_PREVENTIVAS_CRIADAS', 'P') IS NOT NULL
    DROP PROCEDURE GET_ALERTA_MANUTENCOES_PREVENTIVAS_CRIADAS
GO

CREATE PROCEDURE [dbo].[GET_ALERTA_MANUTENCOES_PREVENTIVAS_CRIADAS]
    @IDS varchar(MAX)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @URL varchar(255) = (SELECT TOP 1 URL_SILVER FROM GER_PARAMETROS)

    -- Manutenções criadas, apenas de âmbitos com notificação activa
    DECLARE @MAN table (
        ID_MANUTENCAO_CAB int,
        ID_AMBITO int,
        AMBITO varchar(255),
        COD_MAQUINA varchar(255),
        DESC_MAQUINA varchar(255),
        DESCRICAO_MANUTENCAO varchar(255),
        DATA_REALIZACAO datetime
    )

    INSERT INTO @MAN
    SELECT
        c.ID_MANUTENCAO_CAB,
        amb.ID,
        amb.NOME,
        e.COD_MAQUINA,
        e.DESC_MAQUINA,
        e.DESCRICAO_MANUTENCAO,
        c.DATA_REALIZACAO
    FROM MAN_MOV_MANUTENCAO_CAB c
    INNER JOIN MAN_DIC_AMBITOS amb ON amb.ID = c.AMBITO_MANUTENCAO
    LEFT JOIN MAN_MOV_MANUTENCAO_EQUIPAMENTOS e ON e.ID_MANUTENCAO = c.EQUIPAMENTO
    WHERE c.ID_MANUTENCAO_CAB IN (SELECT TRY_CAST(value AS int) FROM STRING_SPLIT(@IDS, ','))
      AND ISNULL(amb.NOTIFICA_PREVENTIVAS, 0) = 1
      AND ISNULL(amb.ATIVO, 1) = 1

    SELECT
        -- Responsáveis Manutenções do âmbito
        STUFF((
            SELECT DISTINCT ';' + u.EMAIL
            FROM MAN_DIC_AMBITO_UTILIZADORES au
            INNER JOIN GER_UTILIZADORES u ON u.ID_UTILIZADOR = au.ID_UTILIZADOR
            WHERE au.ID_EQUIPA = m.ID_AMBITO
              AND ISNULL(u.INATIVO, 0) = 0
              AND ISNULL(u.EMAIL, '') <> ''
            FOR XML PATH(''), TYPE).value('.', 'varchar(MAX)'), 1, 1, '') AS EMAILS,
        m.AMBITO,
        CAST(COUNT(*) AS varchar(10)) AS TOTAL,
        FORMAT(GETDATE(), 'dd/MM/yyyy') AS DATA,
        -- Tabela HTML com as manutenções criadas
        '<table border="1" cellspacing="0" cellpadding="4" style="border-collapse:collapse;font-family:Arial;font-size:12px">'
        + '<tr style="background-color:#f2f2f2"><th>Nº Manutenção</th><th>Equipamento</th><th>Descrição</th><th>Data Realização</th></tr>'
        + ISNULL((
            SELECT STRING_AGG(CAST(
                '<tr><td>' + CAST(x.ID_MANUTENCAO_CAB AS varchar(20)) + '</td>'
                + '<td>' + ISNULL(x.COD_MAQUINA, '') + ' - ' + ISNULL(x.DESC_MAQUINA, '') + '</td>'
                + '<td>' + ISNULL(x.DESCRICAO_MANUTENCAO, '') + '</td>'
                + '<td>' + ISNULL(FORMAT(x.DATA_REALIZACAO, 'dd/MM/yyyy'), '') + '</td></tr>'
                AS varchar(MAX)), '')
                WITHIN GROUP (ORDER BY x.ID_MANUTENCAO_CAB ASC)
            FROM @MAN x WHERE x.ID_AMBITO = m.ID_AMBITO), '')
        + '</table>' AS TABELA,
        ISNULL(@URL, '') + '/#/man_manutencoes_preventivas' AS LINK
    FROM @MAN m
    GROUP BY m.ID_AMBITO, m.AMBITO
END
GO

-- -----------------------------------------------------------------------------
-- 3. Configuração do evento
-- -----------------------------------------------------------------------------
IF NOT EXISTS (SELECT 1 FROM GER_EVENTOS_CONF WHERE MOMENTO = 'Alertas Preventivas Criadas')
BEGIN
    SET IDENTITY_INSERT GER_EVENTOS_CONF ON;

    INSERT INTO GER_EVENTOS_CONF (ID_EVENTO, MODULO, MOMENTO, PAGINA, ESTADO, EMAIL_PARA, EMAIL_ANEXO, EMAIL_ASSUNTO, EMAIL_MENSAGEM, OBS)
    VALUES (
        89,
        14,
        'Alertas Preventivas Criadas',
        'Manutenções Preventivas',
        1,
        '',
        0,
        'SGIID - {total} manutenção(ões) preventiva(s) criada(s) - {ambito}',
        '<p>Bom dia,</p><p>Foram criadas em {data} <b>{total}</b> manutenção(ões) preventiva(s) no âmbito <b>{ambito}</b>:</p>{tabela}<p><a href="{link}">Abrir no SGIID</a></p><p>Email automático do SGIID - por favor não responder.</p>',
        'Alerta automático - manutenções preventivas geradas (por âmbito)'
    );

    SET IDENTITY_INSERT GER_EVENTOS_CONF OFF;

    INSERT INTO GER_CAMPOS_DISP (ID_EVENTO_CONF, DESCRICAO_CAMPO, NOME_CAMPO) VALUES
        (89, 'Âmbito de manutenção', 'ambito'),
        (89, 'Total de manutenções criadas', 'total'),
        (89, 'Data de criação', 'data'),
        (89, 'Tabela de manutenções', 'tabela'),
        (89, 'Link para o SGIID', 'link');
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
