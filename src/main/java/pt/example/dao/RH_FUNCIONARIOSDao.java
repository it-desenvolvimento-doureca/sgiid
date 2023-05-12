package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_FUNCIONARIOS;

public class RH_FUNCIONARIOSDao extends GenericDaoJpaImpl<RH_FUNCIONARIOS, Integer>
		implements GenericDao<RH_FUNCIONARIOS, Integer> {
	public RH_FUNCIONARIOSDao() {
		super(RH_FUNCIONARIOS.class);
	}

	public List<RH_FUNCIONARIOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_FUNCIONARIOS a where a.COD_FUNCIONARIO = :id ");
		query.setParameter("id", id);
		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getall() {

		Query query = entityManager.createQuery(
				"Select a,(select CODIGO from RH_DIC_CACIFOS where ID = a.NUM_CACIFO) from RH_FUNCIONARIOS a order by CASE WHEN a.COD_FUNCIONARIO >= 0 THEN a.COD_FUNCIONARIO ELSE (a.COD_FUNCIONARIO * -1) END asc");
		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getall2(String Ativo) {

		String querywhere = "";
		if (Ativo != null && Ativo.equals("true")) {
			querywhere = "where a.ATIVO = 1";
		} else if (Ativo != null && Ativo.equals("false")) {
			querywhere = "where a.ATIVO = 0";
		}

		Query query = entityManager.createQuery(
				"Select a,(select CODIGO from RH_DIC_CACIFOS where ID = a.NUM_CACIFO) from RH_FUNCIONARIOS a "
						+ querywhere
						+ " order by CASE WHEN a.COD_FUNCIONARIO >= 0 THEN a.COD_FUNCIONARIO ELSE (a.COD_FUNCIONARIO * -1) END asc");
		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getallativos() {

		Query query = entityManager.createQuery(
				"Select a from RH_FUNCIONARIOS a where a.ATIVO = 1 AND a.NUM_CACIFO = null order by CASE WHEN a.COD_FUNCIONARIO >= 0 THEN a.COD_FUNCIONARIO ELSE (a.COD_FUNCIONARIO * -1) END asc");
		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getGeral(String datageral, String Ativo, String Operario, String SECTOR_ACESSO,
			Boolean ADMIN) {

		String querywhere = "";
		if (Ativo != null && Ativo.equals("true")) {
			querywhere = "and a.ATIVO = 1";
		} else if (Ativo != null && Ativo.equals("false")) {
			querywhere = "and a.ATIVO = 0";
		}

		String queryallsector = "";
		if (ADMIN)
			queryallsector = "or (a.COD_SECTOR is null)";

		/*
		 * Query query =
		 * entityManager.createNativeQuery("DECLARE @DATA date = '" + datageral
		 * + "'; " +
		 * "select a.COD_FUNCIONARIO,a.NOME as nome_func,a.EMPRESA,a.DATA_ADMISSAO,a.DATA_DEMISSAO,a.ATIVO,a.LOCAL,a.RESPONSAVEL,b.DES_SECTOR,c.DES_TURNO,d.NOME,a.NUM_CACIFO,a.DATA_INICIO,a.DATA_FIM,e.DESIGNACAO,a.DATA_PREV_RET,	"
		 * +
		 * " inicial.data_hora hora_entra, final.data_hora hora_saida,a.COD_SECTOR,b.COD_TURNO from RH_FUNCIONARIOS a "
		 * + "left join RH_SECTORES b on a.COD_SECTOR = b.COD_SECTOR " +
		 * "left join RH_TURNOS c on b.COD_TURNO = c.COD_TURNO " +
		 * "left join RH_FUNCIONARIOS d on b.CHEFE1 = d.COD_FUNCIONARIO " +
		 * "left join RH_ESTADOS_FUNC e on a.ESTADO = e.COD_ESTADO " +
		 * "left join (SELECT a.NFuncionario,a.data ,(SELECT top 1 c.Data_Hora from RH_DIC_COLETA c where a.data = c.data and a.NFuncionario = c.NFuncionario order by Data_Hora) as data_hora "
		 * +
		 * "from RH_DIC_COLETA a where a.data = @DATA GROUP BY a.NFuncionario,a.data) as inicial on inicial.NFuncionario = a.COD_FUNCIONARIO "
		 * +
		 * "left join (SELECT a.NFuncionario,a.data ,(SELECT top 1 c.Data_Hora from RH_DIC_COLETA c where a.data = c.data and a.NFuncionario = c.NFuncionario order by Data_Hora desc) as data_hora "
		 * +
		 * "from RH_DIC_COLETA a where a.data = @DATA GROUP BY a.NFuncionario,a.data) as final on final.NFuncionario = a.COD_FUNCIONARIO "
		 * + "where (a.COD_SECTOR in (" + SECTOR_ACESSO + ") " + queryallsector
		 * + ") and ((not " + Operario +
		 * " is not null) or (a.COD_FUNCIONARIO  = " + Operario + " )) " +
		 * querywhere +
		 * " order by CASE WHEN a.COD_FUNCIONARIO > 0 THEN a.COD_FUNCIONARIO ELSE a.COD_FUNCIONARIO * -1 END"
		 * );
		 */
		Query query = entityManager.createNativeQuery("DECLARE @DATA date = '" + datageral + "'; "
				+ "Declare @TABELATEMP  TABLE (NFuncionario int,CheckDate date,Time_In datetime,Time_Out datetime) "
				+ "Insert @TABELATEMP Exec   [dbo].[QUERY_GERAL] @DATA "
				+ "select a.COD_FUNCIONARIO,a.NOME as nome_func,a.EMPRESA,a.DATA_ADMISSAO,a.DATA_DEMISSAO,a.ATIVO,a.LOCAL,a.RESPONSAVEL,b.DES_SECTOR,c.DES_TURNO,d.NOME,(select top 1 CODIGO from RH_DIC_CACIFOS where ID = a.NUM_CACIFO),a.DATA_INICIO,a.DATA_FIM,e.DESIGNACAO,a.DATA_PREV_RET,	"
				+ " f.Time_In hora_entra, (CASE WHEN f.Time_In = f.Time_Out or DATEDIFF(MINUTE, f.Time_In, f.Time_Out ) < 5 THEN null ELSE f.Time_Out  END) hora_saida, "
				+ " a.COD_SECTOR,b.COD_TURNO from RH_FUNCIONARIOS a "
				+ "left join RH_SECTORES b on a.COD_SECTOR = b.COD_SECTOR "
				+ "left join RH_TURNOS c on b.COD_TURNO = c.COD_TURNO "
				+ "left join RH_FUNCIONARIOS d on b.CHEFE1 = d.COD_FUNCIONARIO "
				+ "left join RH_ESTADOS_FUNC e on a.ESTADO = e.COD_ESTADO "
				+ "left join  @TABELATEMP f on f.NFuncionario = a.COD_FUNC_ORIGEM " + "where (a.COD_SECTOR in ("
				+ SECTOR_ACESSO + ") " + queryallsector + ") and ((not " + Operario
				+ " is not null) or (a.COD_FUNCIONARIO  = " + Operario + " )) " + querywhere
				+ " order by CASE WHEN a.COD_FUNCIONARIO > 0 THEN a.COD_FUNCIONARIO ELSE a.COD_FUNCIONARIO * -1 END");

		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getAssiduidade(String datageral, String Ativo, String Operario, String SECTOR_ACESSO,
			Boolean ADMIN, String Exclusao) {

		String querywhere = "";
		if (Ativo != null && Ativo.equals("true")) {
			querywhere = "and ATIVO = 1";
		} else if (Ativo != null && Ativo.equals("false")) {
			querywhere = "and ATIVO = 0";
		}
		String queryallsector = "";
		if (ADMIN)
			queryallsector = "or (a.COD_SECTOR is null)";

		Query query = entityManager.createNativeQuery("DECLARE @DATASEMANA date = '" + datageral + "'; "
				+ "DECLARE @SEGUNDA date = (SELECT DATEADD(dd, 0 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "DECLARE @TERCA date = (SELECT DATEADD(dd, 1 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "DECLARE @QUARTA date = (SELECT DATEADD(dd, 2 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "DECLARE @QUINTA date =(SELECT DATEADD(dd, 3 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "DECLARE @SEXTA date = (SELECT DATEADD(dd, 4 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "DECLARE @SABADO date = (SELECT DATEADD(dd, 5 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "DECLARE @DOMINGO date = (SELECT DATEADD(dd, 6 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "SELECT  a.COD_FUNCIONARIO,a.NOME,a.ATIVO,a.LOCAL,a.RESPONSAVEL,a.COD_SECTOR,c.DES_SECTOR,tabCrono.* "
				+ "from ( SELECT NumeroEmpregado, (select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 0 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @SEGUNDA  GROUP BY NumeroEmpregado) as total_horas_segunda, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 1 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @SEGUNDA  GROUP BY NumeroEmpregado) as total_faltas_segunda, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 2 and CodigoExtra not in ("
				+ Exclusao
				+ ") and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @SEGUNDA  GROUP BY NumeroEmpregado) as total_extras_segunda, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 0 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @TERCA  GROUP BY NumeroEmpregado) as total_horas_terca, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 1 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @TERCA  GROUP BY NumeroEmpregado) as total_faltas_terca, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 2 and CodigoExtra not in ("
				+ Exclusao
				+ ") and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @TERCA  GROUP BY NumeroEmpregado) as total_extras_terca, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 0 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @QUARTA  GROUP BY NumeroEmpregado) as total_horas_quarta, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 1 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @QUARTA  GROUP BY NumeroEmpregado) as total_faltas_quarta, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 2 and CodigoExtra not in ("
				+ Exclusao
				+ ") and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @QUARTA  GROUP BY NumeroEmpregado) as total_extras_quarta, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 0 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @QUINTA  GROUP BY NumeroEmpregado) as total_horas_quinta, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 1 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @QUINTA  GROUP BY NumeroEmpregado) as total_faltas_quinta, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 2 and CodigoExtra not in ("
				+ Exclusao
				+ ") and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @QUINTA  GROUP BY NumeroEmpregado) as total_extras_quinta, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 0 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @SEXTA  GROUP BY NumeroEmpregado) as total_horas_sexta, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 1 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @SEXTA  GROUP BY NumeroEmpregado) as total_faltas_sexta, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 2 and CodigoExtra not in ("
				+ Exclusao
				+ ") and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @SEXTA  GROUP BY NumeroEmpregado) as total_extras_sexta, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 0 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @SABADO  GROUP BY NumeroEmpregado) as total_horas_sabado, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 1 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @SABADO  GROUP BY NumeroEmpregado) as total_faltas_sabado, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 2 and CodigoExtra not in ("
				+ Exclusao
				+ ") and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @SABADO  GROUP BY NumeroEmpregado) as total_extras_sabado, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 0 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @DOMINGO  GROUP BY NumeroEmpregado) as total_horas_domingo, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 1 and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @DOMINGO  GROUP BY NumeroEmpregado) as total_faltas_domingo, "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) from Cronos.dbo.HorasEmpregado where TipoHora = 2 and CodigoExtra not in ("
				+ Exclusao
				+ ") and NumeroEmpregado = b.NumeroEmpregado and cast(DataInicio as date) = @DOMINGO  GROUP BY NumeroEmpregado) as total_extras_domingo "
				+ "from Cronos.dbo.HorasEmpregado b WHERE DataInicio > = DATEADD(dd, 0 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA) AND DataInicio < = DATEADD(dd, 6 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA) GROUP BY NumeroEmpregado ) tabCrono "
				+ "left join RH_FUNCIONARIOS a on a.COD_FUNC_ORIGEM = tabCrono.NumeroEmpregado left join RH_SECTORES c on a.COD_SECTOR = c.COD_SECTOR where (a.COD_SECTOR in ("
				+ SECTOR_ACESSO + ") " + queryallsector + ") and((not " + Operario
				+ " is not null) or (a.COD_FUNCIONARIO  = " + Operario + " )) " + querywhere);
		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getProdutividade(String data1, String data2, String Ativo, String Operario,
			String SECTOR_ACESSO, Boolean ADMIN) {

		String querywhere = "";
		if (Ativo != null && Ativo.equals("true")) {
			querywhere = "and ATIVO = 1";
		} else if (Ativo != null && Ativo.equals("false")) {
			querywhere = "and ATIVO = 0";
		}

		String queryallsector = "";
		if (ADMIN)
			queryallsector = "or (c.COD_SECTOR is null)";

		Query query = entityManager.createNativeQuery("DECLARE @DATA date = '" + data1 + "'; DECLARE @DATA3 date = '"
				+ data2 + "'" + "DECLARE @DATA2 date = DATEADD(DAY,1,@DATA3); "
				+ " select c.COD_FUNCIONARIO,c.NOME,c.ATIVO,c.LOCAL,c.RESPONSAVEL,c.COD_SECTOR,d.DES_SECTOR ,cast(a.DataHora as date), "
				+ "(select top 1 DataHora from  Cronos.dbo.Ponto where NumeroEmpregado = a.NumeroEmpregado and cast(a.DataHora as date) = cast(DataHora as date) and  OrdemEntradaDia = 1 and Anulado = 0  order by DataHora asc) hora_entra, "
				+ "(select top 1 DataHora from  Cronos.dbo.Ponto t where t.NumeroEmpregado = a.NumeroEmpregado and cast(a.DataHora as date) = cast(t.Data as date)  and t.Anulado = 0 order by t.DataHora desc)  hora_saida,"
				+ " t1.total_horas,t2.total_faltas, t3.total_extras "
				+ ",tx.Descricao as tipoFalta,tt.Descricao as tipoExtra " + ",b.total,d.RACIO_MIN,d.RACIO_MAX, "
				+ "case when  t1.total_horas > '00:00:00.000' then ( ((DateDiff(ss,  (CASE WHEN (select rht.TURNO_CONTINUO from RH_TURNOS rht where rht.COD_TURNO = d.COD_TURNO) = 1 THEN (select top 1 TEMPO_PAUSA_TURNOS_CONTINUOS from GER_PARAMETROS ) ELSE '00:00:00' END ), t1.total_horas )*1000)) -  ISNULL(t4.totalpausas , 0 ) * 3600000 ) else 0 end as totalhoras_racio "
				+ "from ("
				+ "(select b.DataHora,a.NumeroEmpregado from (select NumeroEmpregado from Cronos.dbo.Ponto GROUP BY NumeroEmpregado) a "
				+ "left join (select cast(DataHora as date) as DataHora,NumeroEmpregado from Cronos.dbo.Ponto "
				+ "where cast(DataHora as date) >= @DATA and cast(DataHora as date) <= @DATA2 GROUP BY NumeroEmpregado,cast(DataHora as date)) b on a.NumeroEmpregado = b.NumeroEmpregado) "
				+ ") a " + "left join (select  SUM((total*totalproref)) as total,rescod," + " datdeb  from ( "
				+ "select (stpse - atpse)+(stpsp-atpsp) as total, rescod, "
				+ "CASE WHEN CAST(s.datdeb AS DATETIME) + CAST(s.heudeb AS DATETIME) < (select MAX(Time_Out) from RH_DIC_PONTO where cast(Time_Out as date) =  cast(s.datdeb as date) and NFuncionario = rescod) and "
				+ " CAST(s.datdeb AS DATE) <>  CAST((select MAX(Time_In) from RH_DIC_PONTO where cast(Time_Out as date) =  cast(s.datdeb as date) and NFuncionario = rescod)AS DATE) "
				+ "  /*or  (select MAX(Time_In) from RH_DIC_PONTO where cast(Time_Out as date) =  cast(s.datdeb as date) and NFuncionario = rescod) is null */ "
				+ " THEN  cast(DATEADD(DAY, -1, s.datdeb) as date)  ELSE  cast(s.datdeb as date) END datdeb "
				+ ",(CASE WHEN ISDATE(s.heudeb) = 0 THEN '00:00:00.000' ELSE s.heudeb END) heudeb, case when gescod in ('OFCF','OFCF2')	or (SELECT f.c FROM (SELECT prorefout, COUNT (prorefout) c FROM SILVER_BI.dbo.XCOUPON  GROUP BY prorefout) f "
				+ "left join SILVER_BI.dbo.XCOUPON g on f.prorefout = g.prorefout WHERE f.c>1 and proref = q.proref) > 1 then (q.qterr+q.qterb) / (CASE WHEN total > 0 THEN total ELSE 1 END) else 1 END as totalproref "
				+ "from	(select  count(distinct rescod) numero_pessoas,xa.ofdnumenr,xc.svanumenr,xa.svanumori from SILVER_BI.dbo.SCPSVA xa left join (select xa.ofdnumenr,xa.svanumori,xq.svanumenr,xa.heudeb	"
				+ "from SILVER_BI.dbo.SCPSVA xa right join SILVER_BI.dbo.SCPSVQ xq on xq.SVANUMENR = (CASE WHEN xa.SVANUMORI = 0 THEN xa.SVANUMENR ELSE xa.SVANUMORI END) "
				+ "where xa.restypcod='MO' and xa.datdeb>=@DATA and xa.datdeb<= /*@DATA2*/ DATEADD(DAY,3,@DATA2)  ) xc on xa.ofdnumenr = xc.ofdnumenr and xc.svanumori = xa.svanumori	"
				+ "where xa.restypcod='MO' and xa.datdeb>=@DATA and xa.datdeb<= /*@DATA2*/ DATEADD(DAY,3,@DATA2) and xc.svanumenr is not null	and xc.heudeb = xa.heudeb group by xa.ofdnumenr,xc.svanumenr,xa.svanumori)  txa "
				+ "left join SILVER_BI.dbo.SOFD td ON td.ofdnumenr= txa.ofdnumenr "
				+ " LEFT JOIN SILVER_BI.dbo.SCPSVQ q on td.ofdnumenr=q.ofdnumenr "
				+ "left join (select ofanumenr,ofref,typof from SILVER_BI.dbo.SOFA bb)  ta on ta.ofanumenr = td.ofanumenr "
				+ "left join  SILVER_BI.dbo.SCPSVA s ON "
				+ "CASE  WHEN ta.typof IN ('OFC1', 'OFC2','OFCF') THEN s.ofdnumenr  ELSE (CASE WHEN s.SVANUMORI = 0 THEN s.SVANUMENR ELSE s.SVANUMORI END) END = "
				+ "CASE  WHEN ta.typof IN ('OFC1', 'OFC2','OFCF') THEN td.ofdnumenr  ELSE q.SVANUMENR END   and  txa.svanumenr  = q.svanumenr "
				+ "	left join (select proref,prodes1,gescod from SILVER_BI.dbo.SDTPRA) p on p.proref=q.proref "
				+ "left join (select SUM(ty.qterr + ty.qterb) total,ofref,heudeb,datdeb,th.svanumenr "
				+ "from SILVER_BI.dbo.SCPSVQ ty inner join (select svanumenr,ofanumenr,heudeb,datdeb from SILVER_BI.dbo.SCPSVA) th on ty.svanumenr = th.svanumenr "
				+ "inner join SILVER_BI.dbo.SOFA tj on tj.ofanumenr = th.ofanumenr GROUP BY ofref,heudeb,datdeb,th.svanumenr	) j on j.datdeb = s.datdeb /*and j.heudeb = t.heudeb*/ and j.ofref = ta.ofref and j.svanumenr = q.svanumenr "
				+ "where s.datdeb >= cast(@DATA as date) and  s.datdeb <= cast(@DATA2 as date) /*and (CASE WHEN ISDATE(s.heudeb) = 0 THEN '00:00:00.000' ELSE s.heudeb END) >= (select top 1 cast(DataHora as time) from  Cronos.dbo.Ponto where NumeroEmpregado = cast(rescod as int) and s.datdeb = cast(DataHora as date))*/ "
				+ "	and restypcod = 'MO' and q.ofbnumenr is not null and 1 = CASE WHEN (ta.typof IN ('OFC1', 'OFC2','OFCF') and (ta.ofref is null or ta.ofref = ''))THEN 0 ELSE 1 END ) tg GROUP BY cast(datdeb as date),rescod "
				+ ") as b on a.NumeroEmpregado = b.rescod  and  b.datdeb = cast(a.DataHora as date) "
				+ "left join RH_FUNCIONARIOS c on c.COD_FUNC_ORIGEM = a.NumeroEmpregado left join RH_SECTORES d on d.COD_SECTOR = c.COD_SECTOR "
				+ "left join "
				+ "(select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) as total_horas,cast(DataInicio as date) as horas,NumeroEmpregado from  Cronos.dbo.HorasEmpregado where TipoHora = 0 and cast(DataInicio as date) >= @DATA  and cast(DataInicio as date) <= @DATA2   GROUP BY NumeroEmpregado,cast(DataInicio as date))  t1 on  t1.NumeroEmpregado = a.NumeroEmpregado and  t1.horas = cast(a.DataHora as date) "
				+ "left join (select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) as total_faltas,cast(DataInicio as date) as horas,NumeroEmpregado,CodigoFalta from  Cronos.dbo.HorasEmpregado where TipoHora = 1 and cast(DataInicio as date) >= @DATA  and cast(DataInicio as date) <= @DATA2   GROUP BY NumeroEmpregado,cast(DataInicio as date),CodigoFalta)  t2 on  t2.NumeroEmpregado = a.NumeroEmpregado and  t2.horas = cast(a.DataHora as date) "
				+ "left join (select cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time) as total_extras,cast(DataInicio as date) as horas,NumeroEmpregado,CodigoExtra from  Cronos.dbo.HorasEmpregado where TipoHora = 2 and cast(DataInicio as date) >= @DATA  and cast(DataInicio as date) <= @DATA2  and CodigoExtra not in( select TIPO_EXTRA from RH_EXCLUSAO_TIPO_EXTRA)  GROUP BY NumeroEmpregado,cast(DataInicio as date),CodigoExtra)  t3 on  t3.NumeroEmpregado = a.NumeroEmpregado and  t3.horas = cast(a.DataHora as date) "
				+ "left join  Cronos.dbo.EspecialidadesEscaloes tt on t3.CodigoExtra = tt.Escalao "
				+ "left join Cronos.dbo.Faltas tx on t2.CodigoFalta = tx.CodigoFalta "
				+ "  left join (select rescod,SCPSVB.datdeb ,SUM(SCPSVB.ATPSE+SCPSVB.ATPSP) as totalpausas from  SILVER.dbo.SCPSVA SCPSVA "
				+ "inner join SILVER.dbo.SCPSVB  SCPSVB on SCPSVB.SVANUMENR = SCPSVA.SVANUMENR   	where  SCPSVB.datdeb >= @DATA "
				+ "and SCPSVB.datdeb <= @DATA2 and SCPSVB.ARRCOD in (select ID_PAUSA_SILVER from RH_PAUSAS where ID_TIPO_PAUSA  = 2) and SCPSVA.restypcod = 'MO' "
				+ "GROUP BY rescod ,SCPSVB.datdeb ) t4 on   a.NumeroEmpregado = cast(t4.rescod as int) and t4.DATDEB = cast(a.DataHora as date) "
				+ "where b.datdeb >= @DATA and b.datdeb <= @DATA3 and (c.COD_SECTOR in (" + SECTOR_ACESSO + ") "
				+ queryallsector + ") and ((not " + Operario + " is not null) or (a.NumeroEmpregado  = " + Operario
				+ " )) " + querywhere
				+ " order by CASE WHEN c.COD_FUNCIONARIO > 0 THEN c.COD_FUNCIONARIO ELSE c.COD_FUNCIONARIO * -1 END, cast(a.DataHora as date) desc");
		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getOperacoes(String data1, String data2, String Ativo, String Operario,
			String SECTOR_ACESSO, Boolean ADMIN, String tipo_cadencia, String Sector) {

		String querywhere = "";
		if (Ativo != null && Ativo.equals("true")) {
			querywhere = "and ATIVO = 1";
		} else if (Ativo != null && Ativo.equals("false")) {
			querywhere = "and ATIVO = 0";
		}
		String query_tipo_cadencia = " left join (SELECT d.opecod, d.opeqteref PecasHora, b.proref,a.ofnum,d.ofdnumenr FROM SILVER_BI.dbo.SOFB b LEFT JOIN SILVER_BI.dbo.SOFA a ON a.ofanumenr=b.ofanumenr LEFT JOIN SILVER_BI.dbo.SOFD d ON b.ofanumenr=d.ofanumenr) k on k.ofnum = a.ofnum and k.opecod = a.opecod and k.ofdnumenr = a.ofdnumenr and k.proref =  a.proref ";
		if (tipo_cadencia.equals("standard")) {
			query_tipo_cadencia = " left join (SELECT prc.proref, gop.opeqteref PecasHora, gop.opecod, gop.openum FROM SILVER_BI.dbo.SDTPRC prc LEFT JOIN SILVER_BI.dbo.SDTPRD prd ON prd.indnumenr=prc.indnumenr LEFT JOIN SILVER_BI.dbo.SDTGOP gop ON gop.gamcod=prd.gamcod group by prc.proref, gop.opeqteref , gop.opecod, gop.openum ) k on k.proref = a.proref and k.opecod = a.opecod  and k.openum = a.openum";
		}

		String queryallsector = "";
		if (ADMIN)
			queryallsector = "or (b.COD_SECTOR is null)";

		Integer sectornull = null;
		if (Sector != null)
			sectornull = 1;

		Query query = entityManager
				.createNativeQuery("DECLARE @DATA date = '" + data1 + "'; " + "DECLARE @DATA2 date = '" + data2 + "';  "
						+ "select COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,chefe,datdeb,proref,prodes1,opecod,opedes,quant,TempoPrep,TempoExec,CASE WHEN TempoPausas/COUNT(proref) OVER(PARTITION BY COD_FUNCIONARIO,datdeb,heudeb) + TempoPrep + TempoExec !=  TempoTotal THEN TempoTotal + TempoPausas/COUNT(proref) OVER(PARTITION BY COD_FUNCIONARIO,datdeb,heudeb) ELSE TempoTotal END TempoTotal,heudeb,heufin,ofnum,cadencia,TempoPausas/COUNT(proref) OVER(PARTITION BY COD_FUNCIONARIO,datdeb,heudeb)  as TempoPausas "
						+ " from ( select COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,chefe,datdeb,proref,prodes1,opecod,opedes,"
						+ " SUM(quant) quant,SUM(TempoPrep) TempoPrep,SUM(TempoExec) TempoExec,SUM(TempoTotal)  TempoTotal,heudeb,heufin,ofnum,"
						+ " CASE WHEN SUM(TempoTotal) < 0 and SUM(quant) > 0 THEN 0 ELSE CONVERT(DECIMAL(19,2), CASE WHEN  PecasHora ='' or (SUM(TempoExec)) = 0 or ( totalproref)=0 THEN 0 ELSE (((((SUM(quant)) * 60) / ((( SUM(TempoExec) )* totalproref)* 60)) /  PecasHora))*100  END) END cadencia "
						+ ",AVG(TempoPausas) TempoPausas /*AVG(cadencia)  cadencia*/ "
						+ " from ( select COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,chefe,datdeb,proref,prodes1,opecod,opedes, SUM(quant) /*OVER(PARTITION BY COD_FUNCIONARIO,proref,datdeb,ofnum,ofdnumenr)*/ quant,"
						+ " SUM(TempoPrep) TempoPrep,SUM(TempoExec) TempoExec,SUM(TempoTotal)  TempoTotal,SUM(TempoPausas)OVER(PARTITION BY COD_FUNCIONARIO,datdeb,heudeb,opecod) TempoPausas,heudeb,heufin,ofnum, "
						+ " /*SUM(cadencia) OVER(PARTITION BY COD_FUNCIONARIO,proref,datdeb,ofnum,ofdnumenr) cadencia*/ PecasHora,typof,totalproref  "
						+ " from ( select b.COD_FUNCIONARIO,b.NOME,b.ATIVO,b.LOCAL,b.RESPONSAVEL,b.COD_SECTOR,c.DES_SECTOR,(select NOME from RH_FUNCIONARIOS where COD_FUNCIONARIO = c.CHEFE1) chefe ,a.datdeb,a.proref,a.prodes1,a.opecod "
						+ ",a.opedes,CASE WHEN a.TempoTotal < 0 and a.total > 0 THEN 0 ELSE  CONVERT(decimal(10,1),(a.total)) END quant, "
						+ "(CASE  WHEN a.typof IN ('OFC1', 'OFC2','OFCF') THEN (a.TempoPrep/total_ofref)*a.totalproref ELSE a.TempoPrep*a.totalproref END)   TempoPrep, "
						+ "(CASE  WHEN a.typof IN ('OFC1', 'OFC2','OFCF') THEN (a.TempoExec/total_ofref)*a.totalproref ELSE a.TempoExec*a.totalproref END)   TempoExec, "
						+ "(CASE  WHEN a.typof IN ('OFC1', 'OFC2','OFCF') THEN (a.TempoTotal/total_ofref)*a.totalproref ELSE a.TempoTotal*a.totalproref END)   TempoTotal, "
						+ "CASE WHEN a.TempoTotal = 0 THEN 0 ELSE(CASE  WHEN a.typof IN ('OFC1', 'OFC2','OFCF') THEN (a.TempoPausas/total_ofref)*a.totalproref ELSE a.TempoPausas*a.totalproref END)END   TempoPausas "
						+ ",a.heudeb,a.heufin,a.ofnum,a.ofdnumenr "
						+ "/*,CASE WHEN a.TempoTotal < 0 and a.total > 0 THEN 0 ELSE CONVERT(DECIMAL(19,2), CASE WHEN  k.PecasHora ='' or (a.TempoExec) = 0 or (a.totalproref)=0 THEN 0 ELSE (((((total) * 60) / (((CASE  WHEN a.typof IN ('OFC1', 'OFC2','OFCF') THEN ((a.TempoExec)*a.totalproref)/(total_ofref) ELSE (a.TempoExec)*a.totalproref END)*a.totalproref)* 60)) / k.PecasHora))*100  END) END cadencia*/ "
						+ " ,PecasHora,typof,totalproref "
						+ "from ( "
						+ "(SELECT d.ofdnumenr,typof,(select COUNT(tfa.ofref) AS total_ofref   from SILVER_BI.dbo.SOFA tfa where tfa.ofref = a.ofref Group by tfa.ofref) total_ofref "
						+ ",CASE WHEN ISNUMERIC(rescod)=1 THEN t.rescod ELSE null END rescod, t.datdeb, q.proref,p.prodes1,d.opecod,d.openum,d.opedes,(total_pecas /*q.qterr+q.qterb*/) quant,t.heudeb,t.heufin,( /*t.atpse+*/ t.stpse - (t.atpse + t.atpsp)) TempoExec,(t.atpse + t.atpsp) TempoPausas,"
						+ " CONVERT(DECIMAL(19,0),CASE WHEN (total_horas) = 0 THEN 0 ELSE ((total_pecas*(t.stpse + t.stpsp))/(total_horas)) END) total, (/*t.atpsp+*/ t.stpsp) TempoPrep, ( /*t.atpse+*/ t.stpse+ /*t.atpsp+*/ t.stpsp) TempoTotal,case when gescod in ('OFCF','OFCF2')	or (SELECT f.c FROM (SELECT prorefout, COUNT (prorefout) c FROM SILVER_BI.dbo.XCOUPON  GROUP BY prorefout) f "
						+ "left join SILVER_BI.dbo.XCOUPON g on f.prorefout = g.prorefout WHERE f.c>1 and proref = q.proref) > 1 then (total_pecas) / (CASE WHEN total_pecas > 0 THEN total_pecas ELSE 1 END)	else 1 END as totalproref ,a.ofnum    "
						+ "FROM	"
						+ "	( select xva.SVANUMENR as SVANUMENR2,datdeb,xva.ofdnumenr,SVANUMORI,stpsp,rescod,heudeb,heufin,stpse,restypcod,atpse,atpsp"
						+ "	,CASE WHEN xq.svanumenr is null THEN isnull((select top 1 svanumenr from SILVER_BI.dbo.SCPSVQ where ofdnumenr = xva.ofdnumenr and svanumenr in (select vaa.svanumenr from SILVER_BI.dbo.SCPSVA vaa where vaa.datdeb = xva.datdeb and vaa.heudeb = xva.heudeb )),xva.svanumenr) ELSE xq.svanumenr END as svanumenr"
						+ "	from SILVER_BI.dbo.SCPSVA xva"
						+ "	left join SILVER_BI.dbo.SCPSVQ xq on xq.SVANUMENR = (CASE WHEN xva.SVANUMORI = 0 THEN xva.SVANUMENR ELSE xva.SVANUMORI END)"
						+ "	where restypcod='MO' and  ISNUMERIC(RESCOD) = 1 and datdeb>=@DATA and datdeb<= @DATA2 /*and not EXISTS(select svanumenr from  SILVER.dbo.SCPSVB  svb where svb.svanumenr in (xva.svanumenr))*/ ) t "
						+ ""
						+ "	LEFT JOIN (select proref,ofbnumenr,ofdnumenr,svanumenr,qterb,qterr from SILVER_BI.dbo.SCPSVQ) q on  q.SVANUMENR = (CASE WHEN t.SVANUMORI = 0 THEN t.SVANUMENR ELSE t.SVANUMORI END)"
						+ "	left join (select ofdnumenr,opecod,openum,opedes,ofanumenr from SILVER_BI.dbo.SOFD) d ON d.ofdnumenr= t.ofdnumenr"
						+ "	left join (select ofanumenr,ofref,typof,ofnum from SILVER_BI.dbo.SOFA bb)  a on a.ofanumenr = d.ofanumenr"
						+ "	" + "	left join 	(select  ofdnumenr,svanumenr,svanumori,"
						+ "	case when total_pecas = 0 and total_horas <> 0 and svanumori= 0  then	(select SUM(qterr+ qterb) from SILVER_BI.dbo.SCPSVA xva1 inner join  SILVER_BI.dbo.SCPSVA xva2 on xva1.datdeb = xva2.datdeb and xva1.heudeb = xva2.heudeb"
						+ "	left join SILVER_BI.dbo.SCPSVQ xq on xq.SVANUMENR = (CASE WHEN xva1.SVANUMORI = 0 THEN xva1.SVANUMENR ELSE xva1.SVANUMORI END) "
						+ "where xva1.ofdnumenr = tabx.ofdnumenr  and tabx.svanumenr = xva2.svanumenr) else total_pecas END total_pecas,total_horas "
						+ "	from (select  ofdnumenr,svanumenr,svanumori/*,AVG(CASE  WHEN typof IN ('OFC1', 'OFC2','OFCF') THEN  total_operarios_linha ELSE total_operarios END)total_operarios */"
						+ "	 ,AVG(CASE  WHEN typof IN ('OFC1', 'OFC2','OFCF') THEN  total_pecas_linha ELSE total_pecas END)total_pecas "
						+ "	,AVG(CASE  WHEN typof IN ('OFC1', 'OFC2','OFCF') THEN  total_horas_linha ELSE total_horas END)total_horas"
						+ "	from (	select ofdnumenr,svanumori,isnull(svanumenr,svanumenr2) svanumenr,typof"
						+ "/*,count(xa.rescod) over(partition by xa.ofdnumenr) total_operarios_linha,count(xa.rescod) over(partition by xa.svanumori,) total_operarios*/"
						+ " ,sum(tag.qterr+tag.qterb) over(partition by tag.ofdnumenr) total_pecas_linha,tag.qterr+tag.qterb total_pecas "
						+ ",sum(tag.stpse+ tag.stpsp) over(partition by tag.ofdnumenr) total_horas_linha,sum(tag.stpse+ tag.stpsp) over(partition by tag.svanumori,tag.svanumenr)total_horas "
						+ "from (select xq.qterr,xq.qterb,xa.stpse, xa.stpsp,xa.ofdnumenr,xa.svanumori "
						+ ",CASE WHEN xq.svanumenr is null THEN (select top 1 svanumenr from SILVER_BI.dbo.SCPSVQ where ofdnumenr = xa.ofdnumenr and svanumenr in (select vaa.svanumenr from SILVER_BI.dbo.SCPSVA vaa where vaa.datdeb = xa.datdeb and vaa.heudeb = xa.heudeb )) ELSE xq.svanumenr END as svanumenr "
						+ ",xfa.typof,xa.svanumenr as svanumenr2 from SILVER_BI.dbo.SCPSVA xa "
						+ "left join SILVER_BI.dbo.SCPSVQ xq on xq.SVANUMENR = (CASE WHEN xa.SVANUMORI = 0 THEN xa.SVANUMENR ELSE xa.SVANUMORI END) "
						+ "left JOIN SILVER_BI.dbo.SOFA xfa on xa.ofanumenr = xfa.ofanumenr "
						+ "	where xa.restypcod='MO' and xa.datdeb>=@DATA and xa.datdeb<= DATEADD(DAY,3,@DATA2) AND (NOT EXISTS (SELECT svanumenr FROM SILVER.dbo.SCPSVB WHERE svanumenr IN (xa.svanumenr)) OR (xa.atpse + xa.atpsp) = 0 )"
						+ "	) tag) taby where (typof in ('OFC1', 'OFC2','OFCF') or (total_pecas is not null) and total_horas > 0) group by ofdnumenr,svanumenr,svanumori) tabx )  txa on txa.SVANUMENR = (CASE WHEN t.SVANUMORI = 0 THEN t.SVANUMENR2 ELSE t.SVANUMORI END) "
						+ "left join SILVER_BI.dbo.SDTPRA p on p.proref=q.proref "
						+ "/*left join (select SUM(ty.qterr + ty.qterb) total,ofref,heudeb,datdeb,th.svanumenr "
						+ "from SILVER_BI.dbo.SCPSVQ ty "
						+ "inner join (select svanumenr,ofanumenr,heudeb,datdeb from SILVER_BI.dbo.SCPSVA) th on ty.svanumenr = th.svanumenr "
						+ "inner join SILVER_BI.dbo.SOFA tj on tj.ofanumenr = th.ofanumenr GROUP BY ofref,heudeb,datdeb,th.svanumenr	) j on j.datdeb = t.datdeb /*and j.heudeb = t.heudeb*/ and j.ofref = a.ofref and j.svanumenr = q.svanumenr*/ WHERE t.datdeb>=@DATA and t.datdeb<=@DATA2"
						+ "	and t.restypcod='MO' /*and p.proref is not null*/ /*and ((((stpse - atpse)+(stpsp-atpsp)) > 0.1) or (((stpse - atpse)+(stpsp-atpsp)) < -0.1) )*/ ) ) as a  "
						+ "left join RH_FUNCIONARIOS b on  cast(a.rescod as int) = b.COD_FUNCIONARIO left join RH_SECTORES c on b.COD_SECTOR = c.COD_SECTOR "
						+ query_tipo_cadencia + " " + "where /*a.proref is not null and total is not null and*/ (b.COD_SECTOR in ("
						+ SECTOR_ACESSO + ") " + queryallsector + ") and ((not " + sectornull
						+ " is not null) or (c.COD_SECTOR  in (" + Sector + ") )) and ((not " + Operario
						+ " is not null) or (b.COD_FUNCIONARIO  = " + Operario + " )) " + querywhere + ""						
						+ " ) a group by  COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,chefe,datdeb,proref,prodes1,opecod ,opedes ,heudeb,heufin,ofnum,ofdnumenr,quant,PecasHora,typof,totalproref,TempoPausas "
						+ ") b where b.proref is not null  group by  COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,chefe,datdeb,proref,prodes1,opecod ,opedes ,heudeb,heufin,ofnum,PecasHora,typof,totalproref "
						+ ") c where (TempoTotal <> 0 AND (TempoPrep+TempoExec) <> 0)    order by datdeb desc ,heudeb desc,heufin desc");
		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getpausas(String data1, String data2, String Ativo, String Operario,
			String SECTOR_ACESSO, Boolean ADMIN) {

		String querywhere = "";
		if (Ativo != null && Ativo.equals("true")) {
			querywhere = "and ATIVO = 1";
		} else if (Ativo != null && Ativo.equals("false")) {
			querywhere = "and ATIVO = 0";
		}

		String queryallsector = "";
		if (ADMIN)
			queryallsector = "or (b.COD_SECTOR is null)";
		Query query = entityManager
				.createNativeQuery("DECLARE @DATA date = '" + data1 + "'; " + "DECLARE @DATA2 date = '" + data2
						+ "';   select b.COD_FUNCIONARIO,b.NOME,b.ATIVO,b.LOCAL,b.RESPONSAVEL,b.COD_SECTOR,c.DES_SECTOR, "
						+ "(select NOME from RH_FUNCIONARIOS where COD_FUNCIONARIO = c.CHEFE1) as CHEFE,a.datdeb,a.TempoPrep , "
						+ "a.TempoExec,a.TempoTotal TempoTotal,a.heudeb,a.heufin,codigo,descricao from ( "
						+ "SELECT t.rescod, t.datdeb,x.heudeb,x.heufin,(t.stpse) TempoExec, (t.stpsp) TempoPrep, (t.stpse+t.stpsp) TempoTotal,x.ARRCOD as codigo,p.ARRLIB as descricao "
						+ "	FROM SILVER.dbo.SCPSVB x	INNER JOIN SILVER.dbo.SCPSVA t  on t.SVANUMENR=  x.SVANUMENR "
						+ "	INNER JOIN SILVER.dbo.SPAARR p  on p.ARRCOD=  x.ARRCOD "
						+ "	WHERE x.datdeb>=@DATA and x.datdeb<=@DATA2 /*and t.heudeb>= (select top 1 cast(DataHora as time) from  Cronos.dbo.Ponto where NumeroEmpregado = cast(rescod as int) and t.datdeb = cast(DataHora as date))*/ "
						+ "and t.restypcod='MO'and (t.stpse<>0 or t.stpsp<>0) ) as a "
						+ " left join RH_FUNCIONARIOS b on  CASE WHEN IsNumeric(REPLACE(a.rescod,'.','')) = 1 THEN  cast(REPLACE(a.rescod,'.','') as int) ELSE null END = b.COD_FUNCIONARIO left join RH_SECTORES c on b.COD_SECTOR = c.COD_SECTOR  "
						+ "where (b.COD_SECTOR in (" + SECTOR_ACESSO + ") " + queryallsector + ") and ((not " + Operario
						+ " is not null) or (b.COD_FUNCIONARIO  = " + Operario + " )) " + querywhere
						+ " order by a.datdeb desc ,a.heudeb desc,heufin desc");
		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getSectores(String data1, String Ativo, String Operario, String Sector,
			String SECTOR_ACESSO, String tipo_cadencia) {

		String querywhere = "";
		if (Ativo != null && Ativo.equals("true")) {
			querywhere = "and ATIVO = 1";
		} else if (Ativo != null && Ativo.equals("false")) {
			querywhere = "and ATIVO = 0";
		}

		String query_tipo_cadencia = " left join (SELECT d.opecod, d.opeqteref PecasHora, b.proref,a.ofnum,d.ofdnumenr FROM SILVER_BI.dbo.SOFB b LEFT JOIN SILVER_BI.dbo.SOFA a ON a.ofanumenr=b.ofanumenr LEFT JOIN SILVER_BI.dbo.SOFD d ON b.ofanumenr=d.ofanumenr) k on k.ofnum = ta.ofnum and k.opecod = td.opecod  and k.ofdnumenr = td.ofdnumenr ";
		if (tipo_cadencia.equals("standard")) {
			query_tipo_cadencia = " left join (SELECT prc.proref, gop.opeqteref PecasHora, gop.opecod, gop.openum FROM SILVER_BI.dbo.SDTPRC prc LEFT JOIN SILVER_BI.dbo.SDTPRD prd ON prd.indnumenr=prc.indnumenr LEFT JOIN SILVER_BI.dbo.SDTGOP gop ON gop.gamcod=prd.gamcod group by prc.proref, gop.opeqteref , gop.opecod, gop.openum ) k on k.proref = q.proref and k.opecod = td.opecod  and k.openum = td.openum ";
		}

		Integer sectornull = null;
		if (Sector != null)
			sectornull = 1;

		Query query = entityManager.createNativeQuery("DECLARE @DATASEMANA date = '" + data1 + "'; "
				+ "DECLARE @DATA date = (SELECT DATEADD(dd, 0 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "DECLARE @DATA2 date =(SELECT DATEADD(dd, 6 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "select c.COD_FUNCIONARIO,c.NOME,c.ATIVO,c.LOCAL,c.RESPONSAVEL,c.COD_SECTOR,d.DES_SECTOR ,cast(a.DataHora as date), "
				+ "case when  (e.horas - (CASE WHEN TURNO_CONTINUO = 1 THEN dd.TEMPO_PAUSA_TURNOS_CONTINUOS ELSE 0 END) ) > 0 then (b.total/ (e.horas - (CASE WHEN TURNO_CONTINUO = 1 THEN dd.TEMPO_PAUSA_TURNOS_CONTINUOS ELSE 0 END)  - ISNULL(totalpausas , 0) ))*100 else 0 end as racio "
				+ ",coalesce(CONVERT(DECIMAL(19,2),cadencia),0) cadencia from (select cast(DataHora as date) as DataHora,NumeroEmpregado from Cronos.dbo.Ponto "
				+ "where cast(DataHora as date) >= @DATA and cast(DataHora as date) <= @DATA2 GROUP BY NumeroEmpregado,cast(DataHora as date) ) a left join ( select  SUM((total*totalproref)) as total "
				+ ",cast(rescod as int) as rescod,cast(datdeb as date) as datdeb ,AVG( CASE WHEN  PecasHora ='' or total = 0 or totalproref = 0 THEN null "
				+ " ELSE ((((totalquant * 60) / ((total*totalproref)* 60)) / PecasHora))*100  END   )cadencia from ( select "
				+ "	/*(stpse - atpse)+(stpsp-atpsp) as total,*/ "
				+ " CASE  WHEN ta.typof IN ('OFC1', 'OFC2','OFCF') THEN ((stpse - atpse)+(stpsp-atpsp))/(select COUNT(tfa.ofref) AS total_ofref   from SILVER_BI.dbo.SOFA tfa where tfa.ofref = ta.ofref Group by tfa.ofref) ELSE (stpse - atpse)+(stpsp-atpsp) END as total, "
				+ " (totalquant/(numero_pessoas))  totalquant,(q.qterr+q.qterb) quant,cast(rescod as int) as rescod,CASE WHEN CAST(s.datdeb AS DATETIME) + CAST(s.heudeb AS DATETIME) < (select MAX(Time_Out) from RH_DIC_PONTO where cast(Time_Out as date) =  cast(s.datdeb as date) and NFuncionario = rescod) and"
				+ " CAST(s.datdeb AS DATE) <>  CAST((select MAX(Time_In) from RH_DIC_PONTO where cast(Time_Out as date) =  cast(s.datdeb as date) and NFuncionario = rescod)AS DATE) "
				+ " /*or  (select MAX(Time_In) from RH_DIC_PONTO where cast(Time_Out as date) =  cast(s.datdeb as date) and NFuncionario = rescod) is null*/ "
				+ "THEN  cast(DATEADD(DAY, -1, s.datdeb) as date)  ELSE  cast(s.datdeb as date) END datdeb  ,(CASE WHEN ISDATE(s.heudeb) = 0 THEN '00:00:00.000' ELSE s.heudeb END) heudeb "
				+ " ,case when gescod in ('OFCF','OFCF2') or (SELECT f.c FROM (SELECT prorefout, COUNT (prorefout) c FROM SILVER_BI.dbo.XCOUPON  GROUP BY prorefout) f left join SILVER_BI.dbo.XCOUPON g on f.prorefout = g.prorefout WHERE f.c>1 and proref = q.proref) > 1 then (q.qterr+q.qterb) / (CASE WHEN totalquant > 0 THEN totalquant ELSE 1 END) else 1 END as totalproref,PecasHora "
				+ " FROM   (select  count(distinct rescod) numero_pessoas,xa.ofdnumenr,xc.svanumenr,xa.svanumori from SILVER_BI.dbo.SCPSVA xa left join (select xa.ofdnumenr,xa.svanumori,xq.svanumenr,xa.heudeb "
				+ "	from SILVER_BI.dbo.SCPSVA xa right join SILVER_BI.dbo.SCPSVQ xq on xq.SVANUMENR = (CASE WHEN xa.SVANUMORI = 0 THEN xa.SVANUMENR ELSE xa.SVANUMORI END) "
				+ "	where xa.restypcod='MO' and xa.datdeb>=@DATA and xa.datdeb<= /*@DATA2*/ DATEADD(DAY,3,@DATA2)  ) xc on xa.ofdnumenr = xc.ofdnumenr and xc.svanumori = xa.svanumori "
				+ "where xa.restypcod='MO' and xa.datdeb>=@DATA and xa.datdeb<= /*@DATA2*/ DATEADD(DAY,3,@DATA2) and xc.svanumenr is not null and xc.heudeb = xa.heudeb group by xa.ofdnumenr,xc.svanumenr,xa.svanumori)  txa "
				+ " left join (select ofdnumenr,opecod,openum,opedes,ofanumenr from SILVER_BI.dbo.SOFD) td ON td.ofdnumenr= txa.ofdnumenr "
				+ " LEFT JOIN (select proref,ofbnumenr,ofdnumenr,svanumenr,qterb,qterr from SILVER_BI.dbo.SCPSVQ) q on td.ofdnumenr=q.ofdnumenr "
				+ " left join (select proref,prodes1,gescod from SILVER_BI.dbo.SDTPRA) p on p.proref=q.proref /*LEFT JOIN SILVER_BI.dbo.SCPSVA s ON q.SVANUMENR= (CASE WHEN s.SVANUMORI = 0 THEN s.SVANUMENR ELSE s.SVANUMORI END) 	*/"
				+ " left join (select ofanumenr,ofref,ofnum,typof from SILVER_BI.dbo.SOFA bb)  ta on ta.ofanumenr = td.ofanumenr"
				+ " LEFT JOIN ( select * from SILVER_BI.dbo.SCPSVA where restypcod='MO' and datdeb>=@DATA and datdeb<= @DATA2) s ON"
				+ " CASE  WHEN ta.typof IN ('OFC1', 'OFC2','OFCF') THEN s.ofdnumenr  ELSE (CASE WHEN s.SVANUMORI = 0 THEN s.SVANUMENR ELSE s.SVANUMORI END) END = "
				+ " CASE  WHEN ta.typof IN ('OFC1', 'OFC2','OFCF') THEN td.ofdnumenr  ELSE q.SVANUMENR END and txa.svanumenr = q.svanumenr "
				+ " left join (select SUM(ty.qterr + ty.qterb) totalquant,ofref,heudeb,datdeb,th.svanumenr"
				+ "	from SILVER_BI.dbo.SCPSVQ ty	inner join (select svanumenr,ofanumenr,heudeb,datdeb from SILVER_BI.dbo.SCPSVA) th on ty.svanumenr = th.svanumenr  "
				+ " inner join SILVER_BI.dbo.SOFA tj on tj.ofanumenr = th.ofanumenr GROUP BY ofref,heudeb,datdeb,th.svanumenr	) j on j.datdeb = s.datdeb and j.ofref = ta.ofref and j.svanumenr = q.svanumenr      "
				+ " " + query_tipo_cadencia
				+ "where s.datdeb >= cast(@DATA as date) and  s.datdeb <= cast(@DATA2 as date) /*and (CASE WHEN ISDATE(s.heudeb) = 0 THEN '00:00:00.000' ELSE s.heudeb END) >= (select top 1 cast(DataHora as time) from  Cronos.dbo.Ponto where NumeroEmpregado = cast(rescod as int) and s.datdeb = cast(DataHora as date))"
				+ "*/	and restypcod = 'MO' and 1 = CASE WHEN (ta.typof IN ('OFC1', 'OFC2','OFCF') and (ta.ofref is null or ta.ofref = ''))THEN 0 ELSE 1 END ) tg GROUP BY cast(datdeb as date),cast(rescod as int) "
				+ ") as b " + "	on a.NumeroEmpregado = rescod  and  b.datdeb = cast(a.DataHora as date) "
				+ "left join RH_FUNCIONARIOS c on c.COD_FUNC_ORIGEM = a.NumeroEmpregado "
				+ "left join RH_SECTORES d on d.COD_SECTOR = c.COD_SECTOR "
				+ "inner join ( select *, (select top 1 (datediff(SECOND,0,cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(TEMPO_PAUSA_TURNOS_CONTINUOS as time ))), '00:00:00.000') as time))/ (60.0 * 60.0)) TEMPO_PAUSA_TURNOS_CONTINUOS from GER_PARAMETROS ) as TEMPO_PAUSA_TURNOS_CONTINUOS  from  RH_TURNOS) dd on dd.COD_TURNO =d.COD_TURNO "
				+ "left join ( select SCPSVB.datdeb,rescod ,SUM(SCPSVB.ATPSE+SCPSVB.ATPSP) as totalpausas from  SILVER.dbo.SCPSVA SCPSVA "
				+ "inner join SILVER.dbo.SCPSVB  SCPSVB on SCPSVB.SVANUMENR = SCPSVA.SVANUMENR "
				+ "where  SCPSVB.datdeb >= @DATA and SCPSVB.datdeb <= @DATA2 and SCPSVB.ARRCOD in (select ID_PAUSA_SILVER from RH_PAUSAS where ID_TIPO_PAUSA  = 2) and SCPSVA.restypcod = 'MO' "
				+ "GROUP BY rescod ,SCPSVB.datdeb ) t4 on   a.NumeroEmpregado = cast(t4.rescod as int) and t4.DATDEB = cast(a.DataHora as date) left join "
				+ "(select cast(DataInicio as date) as data,NumeroEmpregado,(datediff(SECOND,0,cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time))/ (60.0 * 60.0)) horas from  Cronos.dbo.HorasEmpregado "
				+ " where TipoHora = 0  and cast(DataInicio as date) >= @DATA and cast(DataInicio as date) <= @DATA2 GROUP BY NumeroEmpregado,cast(DataInicio as date)) e on  e.NumeroEmpregado = a.NumeroEmpregado and e.data = cast(a.DataHora as date) "
				+ "where c.COD_SECTOR in (" + SECTOR_ACESSO + ") and c.COD_SECTOR is not null and ((not " + Operario
				+ " is not null) or (c.COD_FUNCIONARIO  = " + Operario + " )) " + "and ((not " + sectornull
				+ " is not null) or (c.COD_SECTOR  in (" + Sector + ") )) " + querywhere
				+ " order by cast(a.DataHora as date), CASE WHEN c.COD_FUNCIONARIO > 0 THEN c.COD_FUNCIONARIO ELSE c.COD_FUNCIONARIO * -1 END ");
		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getCadencias(String data1, String Ativo, String Operario, String Sector,
			String SECTOR_ACESSO, String tipo_cadencia, String tipo_analise) {

		String querywhere = "";
		String queryselect = "";
		String querydatas = "";
		String querygroupby = "";

		if (Ativo != null && Ativo.equals("true")) {
			querywhere = "and ATIVO = 1";
		} else if (Ativo != null && Ativo.equals("false")) {
			querywhere = "and ATIVO = 0";
		}

		String query_tipo_cadencia = " left join (SELECT d.opecod, d.opeqteref PecasHora, b.proref,a.ofnum,d.ofdnumenr FROM SILVER_BI.dbo.SOFB b LEFT JOIN SILVER_BI.dbo.SOFA a ON a.ofanumenr=b.ofanumenr LEFT JOIN SILVER_BI.dbo.SOFD d ON b.ofanumenr=d.ofanumenr) k on k.ofnum = a.ofnum and k.opecod = a.opecod  and k.ofdnumenr = a.ofdnumenr ";
		if (tipo_cadencia.equals("standard")) {
			query_tipo_cadencia = " left join (SELECT prc.proref, gop.opeqteref PecasHora, gop.opecod, gop.openum FROM SILVER_BI.dbo.SDTPRC prc LEFT JOIN SILVER_BI.dbo.SDTPRD prd ON prd.indnumenr=prc.indnumenr LEFT JOIN SILVER_BI.dbo.SDTGOP gop ON gop.gamcod=prd.gamcod group by prc.proref, gop.opeqteref , gop.opecod, gop.openum ) k on k.proref = a.proref and k.opecod = a.opecod  and k.openum = a.openum ";
		}

		if (tipo_analise.equals("semanal")) {
			querydatas = "DECLARE @DATASEMANA date = '" + data1 + "'; "
					+ "DECLARE @DATA date = (SELECT DATEADD(dd, 0 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
					+ "DECLARE @DATA2 date =(SELECT DATEADD(dd, 6 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); ";

			queryselect = " select MES,COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,CHEFE ,datdeb,proref,prodes1,opecod,opedes "
					+ ",cadencia from ( "
					+ "select MES,COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,CHEFE ,datdeb,proref,prodes1,opecod,opedes,SUM(total) quant,SUM(TempoExec*totalproref) total ,PecasHora/*,heudeb,heufin*/,AVG(CONVERT(DECIMAL(20,2), CASE WHEN PecasHora ='' or TempoExec = 0  or total = 0  THEN 0 ELSE ((((total * 60) / (((TempoExec*totalproref))* 60)) / PecasHora))*100  END)) cadencia ";
			querygroupby = " group by MES,COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,CHEFE ,datdeb,proref,prodes1,opecod,opedes ,PecasHora /*,heudeb,heufin*/ "
					+ "  )thu order by proref asc,datdeb asc ,COD_FUNCIONARIO/*,heudeb desc,heufin desc*/";
		} else if (tipo_analise.equals("mensal")) {
			querydatas = "DECLARE @DATASEMANA date = '" + data1 + "'; "
					+ "DECLARE @DATA date = (SELECT DATEADD(month, DATEDIFF(month, 0,DATEADD(MONTH, -7, @DATASEMANA)),0));"
					+ "DECLARE @DATA2 date =(SELECT EOMONTH(@DATASEMANA));";

			queryselect = " select MES,COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,CHEFE ,datdeb,proref,prodes1,opecod,opedes "
					+ ",cadencia  from ( "
					+ "select MES,COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,CHEFE ,datdeb,proref,prodes1,opecod,opedes,SUM(total) quant,SUM(TempoExec*totalproref) total ,PecasHora,AVG(CONVERT(DECIMAL(20,2), CASE WHEN PecasHora ='' or TempoExec = 0 or total = 0  THEN 0 ELSE ((((total * 60) / (((TempoExec*totalproref))* 60)) / PecasHora))*100  END)) cadencia ";

			querygroupby = " GROUP by  MES,COD_FUNCIONARIO,NOME,ATIVO,LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,CHEFE ,datdeb,proref,prodes1,opecod,opedes ,PecasHora "
					+ "  )thu order by proref asc,MES asc ,COD_FUNCIONARIO";
		}

		Integer sectornull = null;
		if (Sector != null)
			sectornull = 1;

		Query query = entityManager.createNativeQuery(querydatas + queryselect
				+ " from( select b.COD_FUNCIONARIO,b.NOME,b.ATIVO,b.LOCAL,b.RESPONSAVEL,b.COD_SECTOR,c.DES_SECTOR, (select NOME from RH_FUNCIONARIOS where COD_FUNCIONARIO = c.CHEFE1) CHEFE ,a.datdeb,a.proref,a.prodes1,a.opecod,a.opedes, PecasHora, "
				+ "CASE  WHEN a.typof IN ('OFC1', 'OFC2','OFCF') THEN (a.TempoExec) /*/total_ofref*/ ELSE a.TempoExec /*a.TempoPrep*/ END   TempoExec,quant,totalproref  ,MONTH ( a.datdeb )  MES,heudeb,heufin,total "
				+ " from ( (SELECT d.ofdnumenr,typof,(select COUNT(tfa.ofref) AS total_ofref   from SILVER_BI.dbo.SOFA tfa where tfa.ofref = a.ofref Group by tfa.ofref) total_ofref,"
				+ " REPLACE(REPLACE(t.rescod,'T',''),'L','') rescod, t.datdeb, q.proref,p.prodes1,d.opecod,d.openum,d.opedes,(q.qterr+q.qterb) quant,t.heudeb,t.heufin,( /*t.atpse+*/ t.stpse) TempoExec, (/*t.atpsp+*/ t.stpsp) TempoPrep,(total/( numero_pessoas )) total, ( /*t.atpse+*/ t.stpse+ /*t.atpsp+*/ t.stpsp) TempoTotal,case when gescod in ('OFCF','OFCF2')	or (SELECT f.c FROM (SELECT prorefout, COUNT (prorefout) c FROM SILVER_BI.dbo.XCOUPON  GROUP BY prorefout) f left join SILVER_BI.dbo.XCOUPON g on f.prorefout = g.prorefout WHERE f.c>1 and proref = q.proref) > 1 then (q.qterr+q.qterb) / (CASE WHEN total > 0 THEN total ELSE 1 END) else 1 END as totalproref ,a.ofnum	"
				+ " FROM (select  count(distinct rescod) numero_pessoas,xa.ofdnumenr,xc.svanumenr,xa.svanumori from SILVER_BI.dbo.SCPSVA xa left join (select xa.ofdnumenr,xa.svanumori,xq.svanumenr,xa.heudeb from SILVER_BI.dbo.SCPSVA xa right join SILVER_BI.dbo.SCPSVQ xq on xq.SVANUMENR = (CASE WHEN xa.SVANUMORI = 0 THEN xa.SVANUMENR ELSE xa.SVANUMORI END) "
				+ " where xa.restypcod='MO' and xa.datdeb>=@DATA and xa.datdeb<= /*@DATA2*/ DATEADD(DAY,3,@DATA2)  ) xc on xa.ofdnumenr = xc.ofdnumenr and xc.svanumori = xa.svanumori "
				+ " where xa.restypcod='MO' and xa.datdeb>=@DATA and xa.datdeb<= /*@DATA2*/ DATEADD(DAY,3,@DATA2) and xc.svanumenr is not null and xc.heudeb = xa.heudeb group by xa.ofdnumenr,xc.svanumenr,xa.svanumori)  txa "
				+ " left join (select ofdnumenr,tsd.opecod,tsd.openum,tsb.opedes,ofanumenr from SILVER_BI.dbo.SOFD  tsd inner join SILVER_BI.dbo.SDTOPP  tsb on tsd.stecod = tsb.stecod and tsd.opecod = tsb.opecod) d ON d.ofdnumenr= txa.ofdnumenr "
				+ " LEFT JOIN (select proref,ofbnumenr,ofdnumenr,svanumenr,qterb,qterr from SILVER_BI.dbo.SCPSVQ) q on d.ofdnumenr=q.ofdnumenr "
				+ " left join (select proref,prodes1,gescod from SILVER_BI.dbo.SDTPRA) p on p.proref=q.proref "
				+ " left join (select ofanumenr,ofref,ofnum,typof from SILVER_BI.dbo.SOFA bb)  a on a.ofanumenr = d.ofanumenr "
				+ "	LEFT JOIN ( select * from SILVER_BI.dbo.SCPSVA where restypcod='MO' and datdeb>=@DATA and datdeb<= @DATA2) t ON "
				+ "	CASE  WHEN a.typof IN ('OFC1', 'OFC2','OFCF') THEN t.ofdnumenr  ELSE (CASE WHEN t.SVANUMORI = 0 THEN t.SVANUMENR ELSE t.SVANUMORI END) END = "
				+ "	CASE  WHEN a.typof IN ('OFC1', 'OFC2','OFCF') THEN d.ofdnumenr  ELSE q.SVANUMENR END and txa.svanumenr = q.svanumenr left join (select SUM(ty.qterr + ty.qterb) total,ofref,heudeb,datdeb,th.svanumenr "
				+ "from SILVER_BI.dbo.SCPSVQ ty inner join (select svanumenr,ofanumenr,heudeb,datdeb from SILVER_BI.dbo.SCPSVA) th on ty.svanumenr = th.svanumenr "
				+ " inner join SILVER_BI.dbo.SOFA tj on tj.ofanumenr = th.ofanumenr GROUP BY ofref,heudeb,datdeb,th.svanumenr	) j on j.datdeb = t.datdeb /*and j.heudeb = t.heudeb*/ and j.ofref = a.ofref and j.svanumenr = q.svanumenr WHERE t.datdeb>=@DATA and t.datdeb<=@DATA2 "
				+ "and t.restypcod='MO' and (t.stpse<>0 or t.atpse<>0) ) ) as a left join RH_FUNCIONARIOS b on  cast(a.rescod as int) = b.COD_FUNCIONARIO left join RH_SECTORES c on b.COD_SECTOR = c.COD_SECTOR  "
				+ " " + query_tipo_cadencia + "where c.COD_SECTOR in (" + SECTOR_ACESSO
				+ ") and c.COD_SECTOR is not null and ((not " + Operario + " is not null) or (b.COD_FUNCIONARIO  = "
				+ Operario + " )) " + "and ((not " + sectornull + " is not null) or (c.COD_SECTOR  in (" + Sector
				+ ") )) " + querywhere + " ) th" + querygroupby);
		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

	public List<RH_FUNCIONARIOS> getSectoresComparativa(String data1, String Ativo, String Sector, String SECTOR_ACESSO,
			Boolean ADMIN, String tipo_cadencia) {

		String querywhere = "";
		if (Ativo != null && Ativo.equals("true")) {
			querywhere = "and ATIVO = 1";
		} else if (Ativo != null && Ativo.equals("false")) {
			querywhere = "and ATIVO = 0";
		}

		String query_tipo_cadencia = " left join (SELECT d.opecod, d.opeqteref PecasHora, b.proref,a.ofnum,d.ofdnumenr FROM SILVER_BI.dbo.SOFB b LEFT JOIN SILVER_BI.dbo.SOFA a ON a.ofanumenr=b.ofanumenr LEFT JOIN SILVER_BI.dbo.SOFD d ON b.ofanumenr=d.ofanumenr) k on k.ofnum = ta.ofnum and k.opecod = td.opecod  and k.ofdnumenr = td.ofdnumenr ";
		if (tipo_cadencia.equals("standard")) {
			query_tipo_cadencia = " left join (SELECT prc.proref, gop.opeqteref PecasHora, gop.opecod, gop.openum FROM SILVER_BI.dbo.SDTPRC prc LEFT JOIN SILVER_BI.dbo.SDTPRD prd ON prd.indnumenr=prc.indnumenr LEFT JOIN SILVER_BI.dbo.SDTGOP gop ON gop.gamcod=prd.gamcod group by prc.proref, gop.opeqteref , gop.opecod,gop.openum ) k on k.proref = p.proref and k.opecod = td.opecod and  k.openum = td.openum ";
		}

		Integer sectornull = null;
		if (Sector != null)
			sectornull = 1;

		String queryallsector = "";
		if (ADMIN)
			queryallsector = "or (c.COD_SECTOR is null)";

		Query query = entityManager.createNativeQuery("DECLARE @DATASEMANA date = '" + data1 + "'; "
				+ "DECLARE @DATA date = (SELECT DATEADD(dd, 0 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "DECLARE @DATA2 date =(SELECT DATEADD(dd, 6 - (@@DATEFIRST + 5 + DATEPART(dw, @DATASEMANA)) % 7, @DATASEMANA)); "
				+ "select COD_SECTOR,DES_SECTOR,LOCAL,CHEFE,DataHora,AVG (CASE WHEN racio <> 0 THEN racio ELSE NULL END) racio,AVG(cadencia)  cadencia from (  "
				+ "select c.COD_FUNCIONARIO,c.NOME,c.ATIVO,c.LOCAL,c.RESPONSAVEL,c.COD_SECTOR,d.DES_SECTOR ,cast(a.DataHora as date) DataHora,  "
				+ "case when  (e.horas - (CASE WHEN TURNO_CONTINUO = 1 THEN dd.TEMPO_PAUSA_TURNOS_CONTINUOS ELSE 0 END) ) > 0 then (b.total/ (e.horas - (CASE WHEN TURNO_CONTINUO = 1 THEN dd.TEMPO_PAUSA_TURNOS_CONTINUOS ELSE 0 END)  - ISNULL(totalpausas , 0) ))*100 else 0 end as racio  "
				+ ",coalesce(CONVERT(DECIMAL(19,2),cadencia),0) cadencia,(select NOME from RH_FUNCIONARIOS where COD_FUNCIONARIO = CHEFE1) as CHEFE from (select cast(DataHora as date) as DataHora,NumeroEmpregado "
				+ "from Cronos.dbo.Ponto where cast(DataHora as date) >= @DATA and cast(DataHora as date) <= @DATA2 GROUP BY NumeroEmpregado,cast(DataHora as date) ) a "
				+ " left join ( select  SUM((total*totalproref)) as total,cast(rescod as int) as rescod"
				+ ",datdeb  ,AVG( CASE WHEN  PecasHora ='' or total = 0 or totalproref = 0 THEN null ELSE ((((quant * 60) / ((total*totalproref)* 60)) / PecasHora))*100  END   )cadencia "
				+ "from ( select " + "/*(stpse - atpse)+(stpsp-atpsp) as total,*/ "
				+ " CASE  WHEN ta.typof IN ('OFC1', 'OFC2','OFCF') THEN ((stpse - atpse)+(stpsp-atpsp))/(select COUNT(tfa.ofref) AS total_ofref   from SILVER_BI.dbo.SOFA tfa where tfa.ofref = ta.ofref Group by tfa.ofref) ELSE (stpse - atpse)+(stpsp-atpsp) END as total, "
				+ "((q.qterr+q.qterb)/(numero_pessoas))  quant,cast(rescod as int) as rescod,"
				+ "CASE WHEN CAST(s.datdeb AS DATETIME) + CAST(s.heudeb AS DATETIME) < (select MAX(Time_Out) from RH_DIC_PONTO where cast(Time_Out as date) =  cast(s.datdeb as date) and NFuncionario = rescod) and "
				+ " CAST(s.datdeb AS DATE) <>  CAST((select MAX(Time_In) from RH_DIC_PONTO where cast(Time_Out as date) =  cast(s.datdeb as date) and NFuncionario = rescod)AS DATE) "
				+ " /*or  (select MAX(Time_In) from RH_DIC_PONTO where cast(Time_Out as date) =  cast(s.datdeb as date) and NFuncionario = rescod) is null*/ "
				+ " THEN  cast(DATEADD(DAY, -1, s.datdeb) as date)  ELSE  cast(s.datdeb as date) END datdeb ,(CASE WHEN ISDATE(s.heudeb) = 0 THEN '00:00:00.000' ELSE s.heudeb END) heudeb,case when gescod in ('OFCF','OFCF2') or (SELECT f.c FROM (SELECT prorefout, COUNT (prorefout) c FROM SILVER_BI.dbo.XCOUPON  GROUP BY prorefout) f "
				+ " left join SILVER_BI.dbo.XCOUPON g on f.prorefout = g.prorefout WHERE f.c>1 and proref = q.proref) > 1 then (q.qterr+q.qterb) / (CASE WHEN total > 0 THEN total ELSE 1 END) else 1 END as totalproref,PecasHora FROM"
				+ " (select  count(distinct rescod) numero_pessoas,xa.ofdnumenr,xc.svanumenr,xa.svanumori from SILVER_BI.dbo.SCPSVA xa left join (select xa.ofdnumenr,xa.svanumori,xq.svanumenr,xa.heudeb from SILVER_BI.dbo.SCPSVA xa right join SILVER_BI.dbo.SCPSVQ xq on xq.SVANUMENR = (CASE WHEN xa.SVANUMORI = 0 THEN xa.SVANUMENR ELSE xa.SVANUMORI END) "
				+ "	where xa.restypcod='MO' and xa.datdeb>=@DATA and xa.datdeb<= /*@DATA2*/ DATEADD(DAY,3,@DATA2)  ) xc on xa.ofdnumenr = xc.ofdnumenr and xc.svanumori = xa.svanumori "
				+ "where xa.restypcod='MO' and xa.datdeb>=@DATA and xa.datdeb<= /*@DATA2*/ DATEADD(DAY,3,@DATA2) and xc.svanumenr is not null and xc.heudeb = xa.heudeb group by xa.ofdnumenr,xc.svanumenr,xa.svanumori)  txa "
				+ "left join (select ofdnumenr,opecod,openum,opedes,ofanumenr from SILVER_BI.dbo.SOFD) td ON td.ofdnumenr= txa.ofdnumenr"
				+ " LEFT JOIN (select proref,ofbnumenr,ofdnumenr,svanumenr,qterb,qterr from SILVER_BI.dbo.SCPSVQ) q on td.ofdnumenr=q.ofdnumenr"
				+ " left join (select proref,prodes1,gescod from SILVER_BI.dbo.SDTPRA) p on p.proref=q.proref "
				+ " /*LEFT JOIN SILVER_BI.dbo.SCPSVA s ON q.SVANUMENR= (CASE WHEN s.SVANUMORI = 0 THEN s.SVANUMENR ELSE s.SVANUMORI END)*/ "
				+ "left join (select ofanumenr,ofref,ofnum,typof from SILVER_BI.dbo.SOFA bb)  ta on ta.ofanumenr = td.ofanumenr "
				+ " LEFT JOIN ( select * from SILVER_BI.dbo.SCPSVA where restypcod='MO' and datdeb>=@DATA and datdeb<= @DATA2) s ON  "
				+ "CASE  WHEN ta.typof IN ('OFC1', 'OFC2','OFCF') THEN s.ofdnumenr  ELSE (CASE WHEN s.SVANUMORI = 0 THEN s.SVANUMENR ELSE s.SVANUMORI END) END = CASE  WHEN ta.typof IN ('OFC1', 'OFC2','OFCF') "
				+ " THEN td.ofdnumenr  ELSE q.SVANUMENR END and txa.svanumenr = q.svanumenr "
				+ "left join (select SUM(ty.qterr + ty.qterb) total,ofref,heudeb,datdeb,th.svanumenr "
				+ "from SILVER_BI.dbo.SCPSVQ ty "
				+ "inner join (select svanumenr,ofanumenr,heudeb,datdeb from SILVER_BI.dbo.SCPSVA) th on ty.svanumenr = th.svanumenr "
				+ "inner join SILVER_BI.dbo.SOFA tj on tj.ofanumenr = th.ofanumenr GROUP BY ofref,heudeb,datdeb,th.svanumenr	) j on j.datdeb = s.datdeb and j.ofref = ta.ofref and j.svanumenr = q.svanumenr "
				+ " " + query_tipo_cadencia
				+ "where s.datdeb >= cast(@DATA as date) and  s.datdeb <= cast(@DATA2 as date) /*and (CASE WHEN ISDATE(s.heudeb) = 0 THEN '00:00:00.000' ELSE s.heudeb END) >= (select top 1 cast(DataHora as time) from  Cronos.dbo.Ponto where NumeroEmpregado = cast(rescod as int) and s.datdeb = cast(DataHora as date))"
				+ "*/	and restypcod = 'MO' and 1 = CASE WHEN (ta.typof IN ('OFC1', 'OFC2','OFCF') and (ta.ofref is null or ta.ofref = ''))THEN 0 ELSE 1 END ) tg GROUP BY cast(datdeb as date),cast(rescod as int) "
				+ ") as b " + "	on a.NumeroEmpregado = rescod  and  b.datdeb = cast(a.DataHora as date) "
				+ "left join RH_FUNCIONARIOS c on c.COD_FUNC_ORIGEM = a.NumeroEmpregado "
				+ "left join RH_SECTORES d on d.COD_SECTOR = c.COD_SECTOR "
				+ "inner join ( select *, (select top 1 (datediff(SECOND,0,cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(TEMPO_PAUSA_TURNOS_CONTINUOS as time ))), '00:00:00.000') as time))/ (60.0 * 60.0)) TEMPO_PAUSA_TURNOS_CONTINUOS from GER_PARAMETROS ) as TEMPO_PAUSA_TURNOS_CONTINUOS  from  RH_TURNOS) dd on dd.COD_TURNO =d.COD_TURNO "
				+ "left join ( select SCPSVB.datdeb,rescod ,SUM(SCPSVB.ATPSE+SCPSVB.ATPSP) as totalpausas from  SILVER.dbo.SCPSVA SCPSVA "
				+ "inner join SILVER.dbo.SCPSVB  SCPSVB on SCPSVB.SVANUMENR = SCPSVA.SVANUMENR "
				+ "where  SCPSVB.datdeb >= @DATA and SCPSVB.datdeb <= @DATA2 and SCPSVB.ARRCOD in (select ID_PAUSA_SILVER from RH_PAUSAS where ID_TIPO_PAUSA  = 2) and SCPSVA.restypcod = 'MO' "
				+ "GROUP BY rescod ,SCPSVB.datdeb ) t4 on   a.NumeroEmpregado = cast(t4.rescod as int) and t4.DATDEB = cast(a.DataHora as date) left join "
				+ "(select cast(DataInicio as date) as data,NumeroEmpregado,(datediff(SECOND,0,cast(DATEADD(ms, SUM(DATEDIFF(ms, '00:00:00.000', cast(horas as time ))), '00:00:00.000') as time))/ (60.0 * 60.0)) horas from  Cronos.dbo.HorasEmpregado "
				+ " where TipoHora = 0  and cast(DataInicio as date) >= @DATA and cast(DataInicio as date) <= @DATA2 GROUP BY NumeroEmpregado,cast(DataInicio as date)) e on  e.NumeroEmpregado = a.NumeroEmpregado and e.data = cast(a.DataHora as date) "
				+ "where (c.COD_SECTOR in (" + SECTOR_ACESSO + ") " + queryallsector + ") and c.COD_SECTOR is not null "
				+ "and ((not " + sectornull + " is not null) or (c.COD_SECTOR  in (" + Sector + ") )) " + querywhere
				+ " ) tbg GROUP by LOCAL,RESPONSAVEL,COD_SECTOR,DES_SECTOR,DataHora,CHEFE   order by DataHora,COD_SECTOR");

		List<RH_FUNCIONARIOS> data = query.getResultList();
		return data;

	}

}
