-- ============================================================
-- MODULO EPI's - Etiqueta de teste no SILVER
-- ============================================================
-- ATENCAO: os passos 3 e 4 ESCREVEM na base de dados do SILVER, que e o
-- ERP de producao. As etiquetas criadas aparecem nos ecras de stock e de
-- consumo da pintura. O passo 5 apaga-as no fim.
--
-- Objectivo: ter uma etiqueta valida para o artigo de um EPI, para poder
-- testar a leitura no levantamento.
-- ============================================================

DECLARE @DESTINO NVARCHAR(50) = '01DOU02234';      -- artigo do EPI a testar
DECLARE @ORIGEM  NVARCHAR(50) = 'FA000S0010113';   -- artigo que ja tem etiquetas
DECLARE @NOVA    NVARCHAR(10) = '9999999903';      -- numero livre, 10 digitos


-- ============================================================
-- 1. O QUE JA EXISTE
-- ============================================================
-- Se ja houver etiquetas do artigo destino, nao e preciso criar nada.

SELECT b.ETQNUM, b.PROREF, b.ETQEMBQTE, b.LIECOD, b.EMPCOD,
       b.ETQORILOT1 AS LOTE, c.LOTDATVLF AS VALIDADE,
       b.ETQETAT, b.ETQSITSTO, b.DATCRE
FROM SILVER.dbo.SETQDE b
LEFT JOIN SILVER.dbo.STOLOT c ON c.PROREF = b.PROREF AND c.LOTREF = b.ETQORILOT1
WHERE b.PROREF IN (@DESTINO, @ORIGEM)
ORDER BY b.PROREF, c.LOTDATVLF, b.ETQNUM;

-- O artigo existe no catalogo do SILVER?
SELECT PROREF, PRODES1, UNISTO FROM SILVER.dbo.SDTPRA WHERE PROREF = @DESTINO;


-- ============================================================
-- 2. O NUMERO ESCOLHIDO ESTA LIVRE?
-- ============================================================

SELECT COUNT(*) AS JA_EXISTE FROM SILVER.dbo.SETQDE WHERE ETQNUM = @NOVA;


-- ============================================================
-- 3. CRIAR A ETIQUETA  (ESCREVE NO SILVER)
-- ============================================================
-- Clona-se uma etiqueta do artigo de origem, mudando o numero, o artigo,
-- a quantidade e as datas. As 27 colunas sao as reais desta instalacao.
--
-- ETQNUMENR e o numero de registo interno: tem de ser novo.
-- ETQETAT = 1 (ativa) e ETQSITSTO = 2 (em stock) sao o que o modulo filtra.
--
-- Descomentar para executar.

/*
INSERT INTO SILVER.dbo.SETQDE
    (ETQNUM, ETQORIDOC1, ETQEMBQTE, VA1REF, VA2REF, INDREF, PROREF,
     DATMOD, INSERTDATE, UPDATEDATE, INDNUMENR, SETQDE, LIECOD, UNICOD,
     ETQORILOT1, ETQNUMENR, EMPCOD, DATCRE, ETQETAT, ETQORIQTE1, ETQSITSTO,
     ORINUMENR, ETQCONDOC, ETQEDTDAT, ETQCLIREF, ETQINFO, ETQNUMUM)
SELECT TOP 1
     @NOVA, b.ETQORIDOC1,
     10,                                             -- quantidade na etiqueta
     b.VA1REF, b.VA2REF, b.INDREF,
     @DESTINO,                                       -- o artigo do EPI
     GETDATE(), GETDATE(), GETDATE(),
     b.INDNUMENR, b.SETQDE, b.LIECOD, b.UNICOD, b.ETQORILOT1,
     (SELECT ISNULL(MAX(ETQNUMENR), 0) + 1 FROM SILVER.dbo.SETQDE),
     b.EMPCOD, GETDATE(),
     1,                                              -- ativa
     b.ETQORIQTE1,
     2,                                              -- em stock
     b.ORINUMENR, b.ETQCONDOC, b.ETQEDTDAT, b.ETQCLIREF, b.ETQINFO, b.ETQNUMUM
FROM SILVER.dbo.SETQDE b
WHERE b.PROREF = @ORIGEM AND b.ETQETAT = 1 AND b.ETQSITSTO = 2
  AND NOT EXISTS (SELECT 1 FROM SILVER.dbo.SETQDE WHERE ETQNUM = @NOVA)
ORDER BY b.DATCRE DESC;
*/


-- ============================================================
-- 4. CONFIRMAR
-- ============================================================

SELECT ETQNUM, PROREF, ETQEMBQTE, LIECOD, EMPCOD, ETQORILOT1, ETQETAT, ETQSITSTO
FROM SILVER.dbo.SETQDE WHERE ETQNUM = @NOVA;

-- E o que o modulo devolve na leitura (o mesmo filtro do getDadosEtiquetaEPI)
SELECT b.ETQNUM, b.PROREF, a.PRODES1, b.ETQEMBQTE, b.LIECOD, b.EMPCOD
FROM SILVER.dbo.SETQDE b
LEFT JOIN SILVER.dbo.SDTPRA a ON a.PROREF = b.PROREF
WHERE b.ETQNUM = @NOVA AND b.ETQETAT = 1 AND b.ETQSITSTO = 2;


-- ============================================================
-- 5. LIMPAR O TESTE
-- ============================================================
/*
DELETE FROM SILVER.dbo.SETQDE WHERE ETQNUM = '9999999903';
*/
