-- =============================================================================
-- BusinessAnalyticsReporting: suporte a multiplos reports Power BI por modulo
-- Adiciona a coluna 'chave' usada pelo endpoint /rest/sirb/getembedUrl/{chave}
-- =============================================================================

-- 1) Nova coluna identificadora do report
ALTER TABLE BusinessAnalyticsReporting ADD chave VARCHAR(50) NULL;

-- 2) A linha que ja existia passa a ser o report de Engenharia (endpoint legado)
--    O endpoint sem chave (/getembedUrl) passa a filtrar por chave = 'ENGENHARIA'
UPDATE BusinessAnalyticsReporting SET chave = 'ENGENHARIA' WHERE chave IS NULL;

-- 3) Report de Reclamacoes
--    Substituir os valores de report_id / group_id / dataset pelos do report
--    Power BI de Reclamacoes. As credenciais (tenantId, clientId, etc.) podem
--    ser as mesmas do report de Engenharia.
INSERT INTO BusinessAnalyticsReporting
    (chave, tenantId, clientId, clientSecret, username, password, resource, endpoint, report_id, group_id, dataset)
SELECT
    'RECLAMACOES', tenantId, clientId, clientSecret, username, password, resource, endpoint,
    '<<REPORT_ID_RECLAMACOES>>', group_id, '<<DATASET_RECLAMACOES>>'
FROM BusinessAnalyticsReporting
WHERE chave = 'ENGENHARIA';

-- Apos confirmar, tornar a coluna obrigatoria (opcional):
-- ALTER TABLE BusinessAnalyticsReporting ALTER COLUMN chave VARCHAR(50) NOT NULL;
