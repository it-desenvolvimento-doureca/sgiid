USE [SILVER_BI]
GO
/****** EXPORT_Analise_Rejeicoes_Por_Referencia — ENRIQUECIDA ******/
-- Mantém as 6 colunas originais (Referencia, DescReferencia, Lote, TipoDefeito, DescDefeito, defeito)
-- para o export de Excel existente não partir, e ACRESCENTA colunas ricas para o relatório
-- (base visual Cromadas): Linha, Produzidas, totaldefeitosref, ObjetivoGeral, Objetivo, PercDefeito,
-- AreaPeca, AreaPecaDefeito, NumBarras, FamiliaDef (nome), Fase, PMPMes, PrecoRejeicao, defeitos.
--
-- NOTA: Produzidas/totaldefeitosref/ObjetivoGeral são CONSTANTES por referência (SUM OVER PARTITION).
--       No relatório devem ser lidos diretamente (não somar no Jasper) para não multiplicar.
--
-- ATENÇÃO: precisa de ser testada na BD (interações SUM/GROUP BY/OVER, joins de preço SDTPRC/SDTPRP).
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[EXPORT_Analise_Rejeicoes_Por_Referencia]
	@LINHA varchar(250),
	@DATA_INI date ,
	@DATA_FIM date ,
	@PROREF varchar(250) ,
	@TIPO_AREA int ,
	@HORA_INI varchar(MAX),
	@HORA_FIM varchar(MAX),
	@CHECK1 int
AS
BEGIN
	DECLARE @FAM varchar(250) = null;
	DECLARE @CHECK bit = 1;
	DECLARE @LINHAS int = null;
	DECLARE @PROREFS varchar(MAX) = null;

IF @CHECK1 = 0
	SET @CHECK = 0;

IF @LINHA = 'NULL'
	SET @CHECK = 0;

IF @PROREF = 'NULL'
	SET @PROREFS = null;

select Referencia,DescReferencia,Lote,TipoDefeito,DescDefeito,defeito,
	-- >>> colunas novas (ricas) <<<
	Linha, Produzidas, totaldefeitosref, ObjetivoGeral, Objetivo, PercDefeito,
	AreaPeca, AreaPecaDefeito, NumBarras, FamiliaDef, Fase, PMPMes,
	(PMPMes * defeito) PrecoRejeicao,
	defeito AS defeitos
from (

	select  Referencia,DescReferencia,AreaPeca,AreaPecaDefeito,
	Produzidas,
	totaldefeitosref,
	NumBarras,
	coalesce(defeito,0) defeito,
	CASE WHEN ObjetivoGeral = 0 THEN objetivoPROREF ELSE ObjetivoGeral END ObjetivoGeral,
	Objetivo,
	FamiliaDef,
	Fase,
	Linha,
	PMPMes,
	case when Produzidas = 0 then 0 else coalesce(CONVERT(DECIMAL(10,3),((defeito/Produzidas)*100)),0) end PercDefeito
	,Lote,TipoDefeito,DescDefeito
	from (
	select Referencia,DescReferencia,AreaPeca,
	SUM(AreaPecaDefeito) AreaPecaDefeito,
	SUM(Produzidas) OVER (PARTITION BY  Referencia ORDER BY Referencia) Produzidas,
	SUM(defeito) OVER (PARTITION BY  Referencia ORDER BY Referencia)totaldefeitosref
	,SUM( CASE WHEN @FAM is not null THEN CASE WHEN LEFT(TipoDefeito,2)=@FAM THEN defeito ELSE 0  END  ELSE defeito END) defeito,
	SUM(NumBarras) OVER (PARTITION BY  Referencia ORDER BY Referencia) NumBarras,
	AVG(ObjetivoGeral) ObjetivoGeral,
	objetivoPROREF,
	Objetivo,
	FamiliaDef,
	Fase,
	Linha,
	PMPMes,
	Lote,TipoDefeito ,DescDefeito
	from
	(SELECT  sc.seccod,
	 (CASE WHEN sc.seccod like '1%' THEN 'Linha 1' ELSE 'Linha 2' END) Linha,
	 g.protypcod,g.lotqtebon, g.ofanumenr,g.TipoDefeito,DescDefeito,objetivoPROREF,
	 g.Referencia, g.DescReferencia, g.TipoRef, g.AreaPeca, g.AreaProd,(case when TipoDefeito != ''   then g.AreaPecaDefeito else null end) AreaPecaDefeito, g.NumBarras,g.boas,g.Produzidas,g.defeito,
	 g.FamiliaDef, g.Objetivo, g.PMPMes,
	 (SELECT top 1 n.prslib FROM SDTPRO m LEFT JOIN SPAPRS n ON n.prscod=m.prscod WHERE m.indnumenr = g.indnumenr AND m.datcre<= sd.datfr ORDER BY m.datcre desc ) Fase,
	 (case when TipoDefeito != ''  then g.PercDefeito else null end) PercDefeito,
	 CASE WHEN @FAM is null THEN ObjetivoGeral ELSE CASE WHEN g.fam = @FAM THEN ObjetivoGeral ELSE null END END ObjetivoGeral ,Lote
	FROM
	(SELECT b.proref Referencia, b.prodes1 DescReferencia, b.protypcod TipoRef, z.zpavalnum AreaPeca, z.zpavalnum*c.qterb AreaProd, z.zpavalnum*c.qterr AreaPecaDefeito,
		s.datfr DataProd, c.datsuivi,a.ofnum,i.indnumenr,a.ofanumenr, c.qterb boas,d.qterr defeito,
		b.zpanum, b.protypcod,l.lotqtebon, (CASE WHEN s.opecod in ('54') then c.qterb ELSE 0 END ) Produzidas, (case when (CASE WHEN s.opecod='54' THEN c.qterb ELSE 0 END)>0 then 1 else 0 end) NumBarras,
		(CASE WHEN c.qterb=0 THEN 0 ELSE CASE WHEN c.qterb IS NULL THEN 0 ELSE (c.qterr/(c.qterb+c.qterr)*100) END END ) PercDefeito,
		(LEFT(d.quacod,2)) fam,a.ofref Lote,
		(SELECT q.opedesmin FROM sdtopp q WHERE q.opecod=(LEFT(d.quacod,2))) FamiliaDef,
		(r.prpprx/b.cfusua) PMPMes,
		(CASE WHEN @FAM is null  THEN w.zpavalnum ELSE (case when (SELECT q.opecod FROM sdtopp q WHERE q.opecod=(LEFT(d.quacod,2)))='57' then (SELECT w.zpavalnum FROM SDTZPA w WHERE w.zpanum = b.zpanum AND w.zpacod='OBJC') else case when (SELECT q.opecod FROM sdtopp q WHERE q.opecod=(LEFT(d.quacod,2)))='53' then (SELECT w.zpavalnum FROM SDTZPA w WHERE w.zpanum = b.zpanum AND w.zpacod='OBJI') end end) END) ObjetivoGeral
		 ,w.zpavalnum objetivoPROREF
		 ,(case when (SELECT q.opecod FROM sdtopp q WHERE q.opecod=(LEFT(d.quacod,2)))='57' then (SELECT w2.zpavalnum FROM SDTZPA w2 WHERE w2.zpanum = b.zpanum AND w2.zpacod='OBJC') else case when (SELECT q.opecod FROM sdtopp q WHERE q.opecod=(LEFT(d.quacod,2)))='53' then (SELECT w2.zpavalnum FROM SDTZPA w2 WHERE w2.zpanum = b.zpanum AND w2.zpacod='OBJI') end end) Objetivo
		 ,(case when d.quacod is null then '' else d.quacod end ) TipoDefeito,e.qualib DescDefeito, typof
			FROM SCPSVQ c
			LEFT JOIN SCPSVR d ON d.svanumenr = c.svanumenr AND d.indnumenr = c.indnumenr AND d.ofbchrono = c.ofbchrono
			LEFT JOIN SPAQUA e ON e.quacod = d.quacod
		LEFT JOIN SDTPRA b ON b.proref = c.proref
		LEFT JOIN (select zpanum,zpavalnum from SDTZPA where zpacod='SUP') z ON z.zpanum = b.zpanum
		LEFT JOIN SOFD s ON s.ofdnumenr=c.ofdnumenr
		LEFT JOIN (select typof,ofanumenr,ofref,ofnum from SOFA where typof in ('OFCF','ofcf','OFC1','OFC2','ofc1','ofc2','OFPP','ofpp','ofp3','OFP3') )a  on a.ofanumenr=s.ofanumenr
		left join SOFB i on i.ofanumenr=s.ofanumenr
		left join STOLOT l ON l.lotref= a.ofref and l.proref=b.proref
		left join (select * from SDTZPA where zpacod='OBJ')  w on w.zpanum = b.zpanum
		LEFT JOIN SDTPRC prc ON b.proref = prc.proref
		LEFT JOIN SDTPRP r ON prc.indnumenr = r.indnumenr and r.prptyp=4  /* Prix de Revient */
							and r.prpdatvld=(SELECT top 1 p1.prpdatvld FROM SDTPRP p1 WHERE p1.indnumenr = prc.indnumenr AND p1.prpdatvld<=@DATA_FIM and p1.prptyp=4 ORDER BY p1.prpdatvld desc)
		WHERE a.ofref is not null and ((not @PROREFS is not null) or (c.proref in ( SELECT value FROM STRING_SPLIT(@PROREFS, ',') )) )
		and  (( @TIPO_AREA != 1) or ( z.zpavalnum < 10 ) ) and  (( @TIPO_AREA != 2) or ( z.zpavalnum >= 10 ) )
		) g
		LEFT JOIN SOFD sd ON sd.ofanumenr=g.ofanumenr
		left join SDTSEC sc on sc.secnumenr=sd.secnumenr1
		WHERE
		CASE WHEN typof in ('OFPP','ofpp') THEN  TRY_CONVERT(date,SUBSTRING(datfc, 0, CHARINDEX(';', datfc)))   ELSE  sd.datfr END >= @DATA_INI
		AND CASE WHEN typof in ('OFPP','ofpp') THEN  TRY_CONVERT(date,SUBSTRING(datfc, 0, CHARINDEX(';', datfc)))  ELSE  sd.datfr END <= @DATA_FIM

		AND (   typof in ('OFPP','ofpp') or ( CAST(CAST(sd.datfr AS DATETIME) + CAST(CAST(sd.heufr AS TIME) as DATETIME)as DATETIME)  >=  CAST(CAST(@DATA_INI AS DATETIME) + CAST(CAST(@HORA_INI AS TIME) as DATETIME)  as DATETIME)
		and CAST(CAST(sd.datfr AS DATETIME) + CAST(CAST(sd.heufr AS TIME) as DATETIME)as DATETIME) <= CAST(CAST(@DATA_FIM AS DATETIME) + CAST(CAST(@HORA_FIM AS TIME) as DATETIME)  as DATETIME)))
		AND (case when @LINHAS IS NULL then left(sc.seccod,1) ELSE @LINHAS END = left(sc.seccod,1))
		and sd.opecod='87'
		) po where
		(((po.lotqtebon=0 or po.lotqtebon is null) and po.protypcod IN ('PSO','PSOP') ) or (  po.protypcod IN ('PCF','PSOF','PSOP','PFPP','PPSF'))	) AND
		((not @PROREFS is not null) or (po.Referencia in ( SELECT value FROM STRING_SPLIT(@PROREFS, ',') )) )
		and ((po.ofanumenr in (select y.ofanumenr from SOFD y where y.opecod in ('87','60','')) and po.protypcod IN ('PSO','PSOP') ) or po.protypcod IN ('PCF','PSOF','PSOP','PFPP','PPSF'))
		GROUP BY Referencia,DescReferencia,AreaPeca,objetivoPROREF,Objetivo,FamiliaDef,Fase,Linha,PMPMes,Lote,TipoDefeito,DescDefeito,Produzidas,defeito,NumBarras
		) tb WHERE ((not @CHECK != 0) OR (case when Produzidas = 0 then 0 else coalesce(CONVERT(DECIMAL(10,3),((totaldefeitosref/Produzidas)*100)),0) end>ObjetivoGeral ))
			and TipoDefeito <> ''
) thss
	order by
	Linha,
	(case when Produzidas = 0 then 0 else CONVERT(DECIMAL(10,3), (totaldefeitosref/Produzidas)*100) end) desc,
	Referencia,FamiliaDef,TipoDefeito,DescDefeito,Lote
END
GO
