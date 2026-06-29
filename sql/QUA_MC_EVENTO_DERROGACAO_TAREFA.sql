/* =====================================================================
   Evento de notificação "Ao Criar Tarefa" para
   DERROGAÇÕES - MEIOS DE CONTROLO
   Espelha o evento ID_EVENTO = 39 (Derrogações da Qualidade)
   Novo ID_EVENTO = 86
   ===================================================================== */

SET IDENTITY_INSERT GER_EVENTOS_CONF ON;

INSERT INTO GER_EVENTOS_CONF
  (ID_EVENTO, PAGINA, MODULO, MOMENTO, ESTADO,
   EMAIL_PARA, EMAIL_DE, EMAIL_ANEXO,
   EMAIL_ASSUNTO, EMAIL_MENSAGEM, OBS,
   UTZ_ULT_MODIF, DATA_ULT_MODIF,
   EMAIL_ASSUNTO_ENG, EMAIL_MENSAGEM_ENG,
   EMAIL_ASSUNTO_FR, EMAIL_MENSAGEM_FR)
VALUES
  (86, N'Derrogações - Meios de Controlo', 5, N'Ao Criar Tarefa', 1,
   N'', NULL, 0,
   N'Nova Ação - Derrogação nº {numero_derrogacao}',
   N'<p>Foi-lhe atribuída a responsabilidade de uma Ação relacionada com a Derrogação nº <strong>{numero_derrogacao}</strong> de <strong>{data_derrogacao}</strong>.</p><p><br></p><p><strong>Ação atribuída:</strong> {numero_tarefa} - {accao}</p><p><strong>Prazo para realização:</strong> {data_prevista}</p><p><strong>Cliente:</strong> {cliente}</p><p><strong>Referência:</strong> {referencia}</p><p><br></p><p>Para consultar a Derrogação, Clique aqui: {link}</p><p><br></p><p>{observacao}</p>',
   N'', NULL, GETDATE(),
   NULL, NULL, NULL, NULL);

SET IDENTITY_INSERT GER_EVENTOS_CONF OFF;

/* Campos disponíveis para o evento 86 (mesma lista do evento 39) */
INSERT INTO GER_CAMPOS_DISP (ID_EVENTO_CONF, DESCRICAO_CAMPO, NOME_CAMPO) VALUES
  (86, N'Observação',                 N'observacao'),
  (86, N'Número Derrogação',          N'numero_derrogacao'),
  (86, N'Cliente',                    N'cliente'),
  (86, N'Data Derrogação',            N'data_derrogacao'),
  (86, N'Data Prevista',              N'data_prevista'),
  (86, N'Referência',                 N'referencia'),
  (86, N'Número Tarefa',              N'numero_tarefa'),
  (86, N'Acção',                      N'accao'),
  (86, N'Link + Número Derrogação',   N'link');
