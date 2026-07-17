USE [SGIID]
GO
/******
 Revisão cliente 08-07-2026 (item amarelo): "Quando atualizamos a versão de uma
 receita, a P. entrada, a P. retorno e a TºC desaparecem."

 Campos afetados: PIN_MOV_RECEITAS_LINHAS.PRESSAO_ENTRADA / PRESSAO_RETORNO / TEMPERATURA.

 Estado do código:
 - Frontend (receitasForm): carrega e grava os 3 campos — OK, não é a causa.
 - SPs de versão nos scripts PENDENTES (PIN_MELHORIAS_FASE2_2026-07-03.sql e
   PIN_UPDATE_PIN_DIC_PRODUTOS_PERC_DILUICAO_2026-07-03.sql): os INSERTs das
   linhas já incluem os 3 campos — depois de executados, o problema não se
   repete por essa via.
 - Falta confirmar a definição ATUALMENTE em produção (secção 0) e reparar as
   versões já danificadas (secções 1/2).

 Correr primeiro o DIAGNÓSTICO; rever; só depois executar a REPARAÇÃO (comentada).
******/

-- =====================================================================
-- 0. VERIFICAÇÃO: as SPs em produção copiam os 3 campos nas linhas?
--    (se alguma linha devolver COPIA = 'NAO', a SP em produção perde os campos
--     ao versionar — executar os scripts de 2026-07-03 resolve)
-- =====================================================================
select o.name as SP,
       case when m.definition like '%PRESSAO_ENTRADA%'
             and m.definition like '%PRESSAO_RETORNO%'
             and m.definition like '%,TEMPERATURA%' then 'SIM' else 'NAO' end as COPIA_PRESSOES_TEMPERATURA
from sys.objects o
inner join sys.sql_modules m on m.object_id = o.object_id
where o.name in ('PIN_UPDATE_PIN_DIC_PRODUTOS_PERC_DILUICAO','PIN_UPDATE_PIN_DIC_PRE_SET');

-- =====================================================================
-- 1. DIAGNÓSTICO: linhas da versão ativa com os 3 campos a null mas com
--    valores na mesma linha (mesmo tipo de acabamento) de uma versão anterior
-- =====================================================================
select c.ID, c.VERSAO, c.NOME_PROJETO, l.ID_TIPO_ACABAMENTO,
       l.PRESSAO_ENTRADA, l.PRESSAO_RETORNO, l.TEMPERATURA,
       ant.VERSAO as VERSAO_ORIGEM,
       ant.PRESSAO_ENTRADA as PRESSAO_ENTRADA_ANT,
       ant.PRESSAO_RETORNO as PRESSAO_RETORNO_ANT,
       ant.TEMPERATURA as TEMPERATURA_ANT
from PIN_MOV_RECEITAS c
inner join PIN_MOV_RECEITAS_LINHAS l on l.ID_RECEITA = c.ID and l.VERSAO = c.VERSAO
cross apply (
    select top 1 x.VERSAO, x.PRESSAO_ENTRADA, x.PRESSAO_RETORNO, x.TEMPERATURA
    from PIN_MOV_RECEITAS_LINHAS x
    where x.ID_RECEITA = l.ID_RECEITA and x.VERSAO < l.VERSAO
      and x.ID_TIPO_ACABAMENTO = l.ID_TIPO_ACABAMENTO
      and (x.PRESSAO_ENTRADA is not null or x.PRESSAO_RETORNO is not null or x.TEMPERATURA is not null)
    order by x.VERSAO desc
) ant
where c.VERSAO_ATIVA = 1 and c.INATIVO = 0 and ISNULL(c.RECEITA_INATIVA,0) = 0
  and l.PRESSAO_ENTRADA is null and l.PRESSAO_RETORNO is null and l.TEMPERATURA is null
order by c.ID, l.ID_TIPO_ACABAMENTO;

-- =====================================================================
-- 2. REPARAÇÃO (rever o diagnóstico antes de descomentar!)
--    Repõe os 3 campos na versão ativa a partir da última versão anterior
--    com valores, linha a linha por tipo de acabamento.
--    Nota: assume 1 linha por tipo de acabamento em cada versão (Primário/
--    Base/Verniz). Se o diagnóstico mostrar duplicados, tratar esses IDs à mão.
-- =====================================================================
/*
update l set
    l.PRESSAO_ENTRADA = ant.PRESSAO_ENTRADA,
    l.PRESSAO_RETORNO = ant.PRESSAO_RETORNO,
    l.TEMPERATURA     = ant.TEMPERATURA
from PIN_MOV_RECEITAS c
inner join PIN_MOV_RECEITAS_LINHAS l on l.ID_RECEITA = c.ID and l.VERSAO = c.VERSAO
cross apply (
    select top 1 x.PRESSAO_ENTRADA, x.PRESSAO_RETORNO, x.TEMPERATURA
    from PIN_MOV_RECEITAS_LINHAS x
    where x.ID_RECEITA = l.ID_RECEITA and x.VERSAO < l.VERSAO
      and x.ID_TIPO_ACABAMENTO = l.ID_TIPO_ACABAMENTO
      and (x.PRESSAO_ENTRADA is not null or x.PRESSAO_RETORNO is not null or x.TEMPERATURA is not null)
    order by x.VERSAO desc
) ant
where c.VERSAO_ATIVA = 1 and c.INATIVO = 0 and ISNULL(c.RECEITA_INATIVA,0) = 0
  and l.PRESSAO_ENTRADA is null and l.PRESSAO_RETORNO is null and l.TEMPERATURA is null;
*/
