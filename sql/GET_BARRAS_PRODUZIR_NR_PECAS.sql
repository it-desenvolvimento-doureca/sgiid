USE [SGIID]
GO
/****** Object:  StoredProcedure [dbo].[GET_BARRAS_PRODUZIR] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Alteracao 2026-07-29: acrescentada coluna NR_PECAS (soma de QUANT_PLANO),
-- que corresponde as pecas que determinam o NUM_BARRAS_PLANO.
-- A coluna e devolvida na posicao 7 (indice 6) para nao alterar as anteriores.
-- =============================================
ALTER PROCEDURE [dbo].[GET_BARRAS_PRODUZIR]
	@IDS varchar(max)
AS
BEGIN
	select a.ID_LINHA,c.NOME_LINHA,b.ANO,b.SEMANA,sum(b.NUM_BARRAS_PLANO) barras_necessarias
	,ISNULL(CASE WHEN (select top 1 xf.N_DIAS_PRODUCAO from PR_DIC_PRODUCAO_SEMANA xf where xf.ATIVO = 1 and xf.ANO =  b.ANO and xf.SEMANA = b.SEMANA and xf.ID_LINHA = a.ID_LINHA) < 1 THEN 0 ELSE
	sum(b.NUM_BARRAS_PLANO)/ (select top 1 xf.N_DIAS_PRODUCAO from PR_DIC_PRODUCAO_SEMANA xf where xf.ATIVO = 1 and xf.ANO =  b.ANO and xf.SEMANA = b.SEMANA and xf.ID_LINHA = a.ID_LINHA) END,0) media_dia
	,ISNULL(sum(b.QUANT_PLANO),0) nr_pecas
	from PR_PLANEAMENTO_PRODUCAO_CAB a
	inner join PR_PLANEAMENTO_PRODUCAO_LINHAS b on a.ID_PLANEAMENTO_PRODUCAO_CAB = b.ID_PLANEAMENTO_PRODUCAO_CAB
	inner join AB_DIC_LINHA c on a.ID_LINHA = c.ID_LINHA
	where a.ID_PLANEAMENTO_PRODUCAO_CAB in  ( SELECT value FROM STRING_SPLIT(@IDS, ','))
	group by a.ID_LINHA,c.NOME_LINHA,b.ANO,b.SEMANA
	order by  a.ID_LINHA

END
GO
