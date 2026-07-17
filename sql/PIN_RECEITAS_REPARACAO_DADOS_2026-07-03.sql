USE [SGIID]
GO
/******
 Diagnóstico + reparação de dados perdidos ao gerar novas versões de receitas
 (causado pela SP PIN_UPDATE_PIN_DIC_PRODUTOS_PERC_DILUICAO antes do fix de 2026-07-03).

 Correr primeiro as queries de DIAGNÓSTICO; rever os resultados; só depois
 executar os blocos de REPARAÇÃO (estão comentados).
******/

-- =====================================================================
-- 1. DIAGNÓSTICO: versões ativas SEM registos UV mas com UV numa versão anterior
-- =====================================================================
select c.ID, c.VERSAO, c.NOME_PROJETO,
       (select MAX(u.VERSAO) from PIN_MOV_UV_RADIATION u where u.ID_RECEITA = c.ID) as ULTIMA_VERSAO_COM_UV
from PIN_MOV_RECEITAS c
where c.VERSAO_ATIVA = 1 and c.INATIVO = 0 and ISNULL(c.RECEITA_INATIVA,0) = 0
  and not exists (select 1 from PIN_MOV_UV_RADIATION u where u.ID_RECEITA = c.ID and u.VERSAO = c.VERSAO)
  and exists (select 1 from PIN_MOV_UV_RADIATION u where u.ID_RECEITA = c.ID)
order by c.ID;

-- =====================================================================
-- 2. DIAGNÓSTICO: versões ativas com CICLOS a null mas preenchidos numa versão anterior
-- =====================================================================
select c.ID, c.VERSAO, c.NOME_PROJETO,
       c.CICLO_PRIMARIO, c.CICLO_BASE, c.CICLO_VERNIZ,
       ant.VERSAO as VERSAO_ORIGEM, ant.CICLO_PRIMARIO as CICLO_PRIMARIO_ANT, ant.CICLO_BASE as CICLO_BASE_ANT, ant.CICLO_VERNIZ as CICLO_VERNIZ_ANT
from PIN_MOV_RECEITAS c
cross apply (
    select top 1 x.VERSAO, x.CICLO_PRIMARIO, x.CICLO_BASE, x.CICLO_VERNIZ
    from PIN_MOV_RECEITAS x
    where x.ID = c.ID and x.VERSAO < c.VERSAO
      and (x.CICLO_PRIMARIO is not null or x.CICLO_BASE is not null or x.CICLO_VERNIZ is not null)
    order by x.VERSAO desc
) ant
where c.VERSAO_ATIVA = 1 and c.INATIVO = 0 and ISNULL(c.RECEITA_INATIVA,0) = 0
  and c.CICLO_PRIMARIO is null and c.CICLO_BASE is null and c.CICLO_VERNIZ is null
order by c.ID;

-- =====================================================================
-- 3. REPARAÇÃO (rever o diagnóstico antes de descomentar!)
-- =====================================================================

-- 3.1 Copiar registos UV da última versão que os tem para a versão ativa
/*
insert into PIN_MOV_UV_RADIATION (VERSAO, ID_RECEITA, DATA_CRIA, UTZ_CRIA, DATA_MODIF, UTZ_MODIF, UV_RADIATION, RECOMMENDED_VALUE)
select c.VERSAO, u.ID_RECEITA, GETDATE(), u.UTZ_CRIA, GETDATE(), u.UTZ_MODIF, u.UV_RADIATION, u.RECOMMENDED_VALUE
from PIN_MOV_RECEITAS c
inner join PIN_MOV_UV_RADIATION u
        on u.ID_RECEITA = c.ID
       and u.VERSAO = (select MAX(x.VERSAO) from PIN_MOV_UV_RADIATION x where x.ID_RECEITA = c.ID)
where c.VERSAO_ATIVA = 1 and c.INATIVO = 0 and ISNULL(c.RECEITA_INATIVA,0) = 0
  and not exists (select 1 from PIN_MOV_UV_RADIATION x where x.ID_RECEITA = c.ID and x.VERSAO = c.VERSAO);
*/

-- 3.2 Repor CICLOS na versão ativa a partir da última versão anterior com valores
/*
update c set
    c.CICLO_PRIMARIO = ant.CICLO_PRIMARIO,
    c.CICLO_BASE     = ant.CICLO_BASE,
    c.CICLO_VERNIZ   = ant.CICLO_VERNIZ
from PIN_MOV_RECEITAS c
cross apply (
    select top 1 x.CICLO_PRIMARIO, x.CICLO_BASE, x.CICLO_VERNIZ
    from PIN_MOV_RECEITAS x
    where x.ID = c.ID and x.VERSAO < c.VERSAO
      and (x.CICLO_PRIMARIO is not null or x.CICLO_BASE is not null or x.CICLO_VERNIZ is not null)
    order by x.VERSAO desc
) ant
where c.VERSAO_ATIVA = 1 and c.INATIVO = 0 and ISNULL(c.RECEITA_INATIVA,0) = 0
  and c.CICLO_PRIMARIO is null and c.CICLO_BASE is null and c.CICLO_VERNIZ is null;
*/
