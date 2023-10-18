package pt.example.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.PathParam;

import pt.example.entity.PIN_MOV_PREPARACAO;

public class PIN_MOV_PREPARACAODao extends GenericDaoJpaImpl<PIN_MOV_PREPARACAO, Integer>
		implements GenericDao<PIN_MOV_PREPARACAO, Integer> {
	public PIN_MOV_PREPARACAODao() {
		super(PIN_MOV_PREPARACAO.class);
	}

	public List<PIN_MOV_PREPARACAO> getall(Integer linha, ArrayList<String> query2, String classif, String classif2) {
		// System.out.println(query2);
		Integer varquery = 1;
		String Squery = ""; 
		if (query2 == null) {
			varquery = 0;
		}
	
		
		Query query = entityManager.createNativeQuery(
				"Select a.ID_PREPARACAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,b.COR,b.NOME_LINHA,d.NOME_TURNO,a.ESTADO, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
						+ "WHEN a.ESTADO = 'Planeado' THEN '3' " + "WHEN a.ESTADO = 'Preparado' THEN '5' "
						+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '2' "
						+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B " + Squery
						+ "  ,CASE WHEN a.CLASSIF = 'D' THEN  a.DATA_PLANEAMENTO ELSE cast(cc.data as date) END as dt_prev "
						+ " ,CASE WHEN a.CLASSIF = 'D' THEN  a.HORA_PLANEAMENTO ELSE cast(cc.data as time) END as h_prev, "
						+ "(select top 1 TEMPO_PLANEADAS from GER_PARAMETROS) as temp_pla, "
						+ "(select top 1 TEMPO_MAX_PLANEADAS from GER_PARAMETROS) as temp_max_pla,null as cortipo,a.CLASSIF,a.ID_LINHA  "
						+ "from PIN_MOV_PREPARACAO a " + "left join AB_DIC_LINHA b on b.ID_LINHA = a.ID_LINHA "
						+ " left join AB_DIC_TURNO d on  a.ID_TURNO = d.ID_TURNO "
						+ "left join (select ID_PREPARACAO,MIN(cast(x.DATA_PREVISTA as datetime) + cast(x.HORA_PREVISTA as datetime)) as data from PIN_MOV_PREPARACAO_CAB x where   INATIVO != 1 GROUP BY ID_PREPARACAO) cc on cc.ID_PREPARACAO = a.ID_PREPARACAO "
						+ "where  a.INATIVO != 1" + "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not "
						+ varquery + " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF in ('" + classif + "','"
						+ classif2 + "')  order by B,a.ID_PREPARACAO desc");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);
		// query.setParameter("classif", classif);

		List<PIN_MOV_PREPARACAO> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO> getallmanu(Integer linha, List<HashMap<String, String>> data2) {
		// System.out.println(query2);
		HashMap<String, String> firstMap = data2.get(0);

		ArrayList<String> query2 = new ArrayList<String>(Arrays.asList(firstMap.get("query").split(",")));
		String classif = firstMap.get("classif");
		String querybanho = "";
		Integer varquery = 1;

		/*if (firstMap.get("querybanho") != null) {
			querybanho = " and a.ID_PREPARACAO in (SELECT t.ID_PREPARACAO FROM PIN_MOV_PREPARACAO_CAB t where t.ID_POTE = "
					+ firstMap.get("querybanho") + " and t.INATIVO != 1 )";
		}*/

		if (query2 == null) {
			varquery = 0;
		}
		

		Query query = entityManager.createNativeQuery(
				"Select a.ID_PREPARACAO, "
						+ "a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,b.COR,b.NOME_LINHA,d.NOME_TURNO,a.ESTADO, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
						+ "WHEN a.ESTADO = 'Planeado' THEN '3' " + "WHEN a.ESTADO = 'Preparado' THEN '5' "
						+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '2' "
						+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B, a.CLASSIF "
						+ " ,CASE WHEN a.CLASSIF = 'D' THEN  a.DATA_PLANEAMENTO ELSE cast(cc.data as date) END as dt_prev "
						+ ",CASE WHEN a.CLASSIF = 'D' THEN  a.HORA_PLANEAMENTO ELSE cast(cc.data as time) END as h_prev "
						+ ",(select top 1 TEMPO_PLANEADAS from GER_PARAMETROS) as temp_pla,"
						+ "(select top 1 TEMPO_MAX_PLANEADAS from GER_PARAMETROS) as temp_max_pla,null as cortipo,a.ID_LINHA  "
						+ "from PIN_MOV_PREPARACAO a " + "left join AB_DIC_LINHA b on b.ID_LINHA = a.ID_LINHA "
						+ "left join AB_DIC_TURNO d on  a.ID_TURNO = d.ID_TURNO "
						+ "left join (select ID_PREPARACAO,MIN(cast(x.DATA_PREVISTA as datetime) + cast(x.HORA_PREVISTA as datetime)) as data from PIN_MOV_PREPARACAO_CAB x where   INATIVO != 1 GROUP BY ID_PREPARACAO) cc on cc.ID_PREPARACAO = a.ID_PREPARACAO "
						+ "where a.INATIVO != 1" + "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not "
						+ varquery + " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF in (" + classif + ") "
						+ querybanho + " order by B,a.ID_PREPARACAO desc");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);

		List<PIN_MOV_PREPARACAO> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO> getallAnaliseConsumos(List<HashMap<String, String>> querydata) {
		HashMap<String, String> firstMap = querydata.get(0);
		String ESTADO = "''";
		String ESTADO2 = "''";
		if (!firstMap.get("ESTADO").equals("'null'")) {
			ESTADO = firstMap.get("ESTADO");
			ESTADO2 = "'estado'";
		}

		String NUMEROSEMANA = "''";
		String NUMEROSEMANA2 = "''";
		if (!firstMap.get("NUMEROSEMANA").equals("'null'")) {
			NUMEROSEMANA = firstMap.get("NUMEROSEMANA");
			NUMEROSEMANA2 = "'NUMEROSEMANA'";
		}

		Query query = entityManager
				.createNativeQuery("select a.ID_PREPARACAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,a.ESTADO,a.CLASSIF, "
						+ " g.NOME_LINHA,h.NOME_TURNO, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_PLANEAMENTO) as UTZ_PLAN, "
						+ "c.ID_POTE,f.NOME,t.NOME_CABINE,b.OBS_EXECUCAO,b.OBS_PLANEAMENTO, "
						+ "b.OBS_PREPARACAO,b.DATA_EXECUCAO,b.HORA_EXECUCAO,b.DATA_PREPARACAO,b.HORA_PREPARACAO,b.DATA_PREVISTA,b.HORA_PREVISTA, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = b.UTZ_EXECUCAO) as UTZ_EXE, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = b.UTZ_PREPARACAO) as UTZ_PREP, "
						+ "c.ID_PRODUTO, e.NOME_REF,e.COD_REF,e.NOME_COMPONENTE,c.STOCK,c.STKUNIT,c.VALOR, "
						+ "(select top 1 x.MEDIDA from AB_DIC_UNIDADE_MEDIDA x where x.ID_MEDIDA = c.ID_UNIDADE) as unid, "
						+ "c.LIECOD,d.ETQNUM,d.QUANT,d.QUANT_FINAL,e.FACTOR_CONVERSAO,d.CONSUMIR,DATEPART( wk,b.DATA_PREPARACAO) as numerosemana "
						+ "from PIN_MOV_PREPARACAO a left join PIN_MOV_PREPARACAO_CAB b on a.ID_PREPARACAO = b.ID_PREPARACAO "
						+ " left join PIN_MOV_PREPARACAO_LINHA c on b.ID_PREPARACAO_CAB = c.ID_PREPARACAO_CAB "
						+ "left join PIN_MOV_PREPARACAO_ETIQ d on c.ID_PREPARACAO_LIN = d.ID_PREPARACAO_LIN "
						+ "left join PIN_DIC_PRODUTOS e on c.ID_PRODUTO = e.ID "
						+ "left join PIN_DIC_POTES f on c.ID_POTE = f.ID "
						+ "left join AB_DIC_LINHA g on a.ID_LINHA = g.ID_LINHA "
						+ "left join AB_DIC_TURNO h on a.ID_TURNO = h.ID_TURNO "
						+ "left join PIN_DIC_CABINES t on f.ID_CABINE = t.ID " + "where "						
						+ "((not ('" + firstMap.get("DATA_PLANEAMENTO") + "' != 'null' and '"
						+ firstMap.get("DATA_PLANEAMENTO") + "' != '')) or (b.DATA_PREPARACAO <= '"
						+ firstMap.get("DATA_PLANEAMENTO") + "')) " + "and ((not ('" + firstMap.get("DATA_PLANEAMENTO2")
						+ "' != 'null' and '" + firstMap.get("DATA_PLANEAMENTO2")
						+ "' != '')) or (b.DATA_PREPARACAO >= '" + firstMap.get("DATA_PLANEAMENTO2") + "')) "
						+ "and ((not ('" + firstMap.get("DATA_PREVISTA") + "' != 'null' and '"
						+ firstMap.get("DATA_PREVISTA") + "' != '')) or (b.DATA_PREVISTA <= '"
						+ firstMap.get("DATA_PREVISTA") + "')) " + "and ((not ('" + firstMap.get("DATA_PREVISTA2")
						+ "' != 'null' and '" + firstMap.get("DATA_PREVISTA2") + "' != '')) or (b.DATA_PREVISTA >= '"
						+ firstMap.get("DATA_PREVISTA2") + "')) " + "and ((not (" + ESTADO2 + " != 'null' and "
						+ ESTADO2 + " != '')) or (a.ESTADO in (" + ESTADO + "))) " + "and ((not (" + NUMEROSEMANA2
						+ " != 'null' and " + NUMEROSEMANA2 + " != '')) or (DATEPART( wk,b.DATA_PREPARACAO) in ("
						+ NUMEROSEMANA + "))) " + "and ((not ('" + firstMap.get("CLASSIF") + "' != 'null' and '"
						+ firstMap.get("CLASSIF") + "' != '')) or (a.CLASSIF = '" + firstMap.get("CLASSIF") + "')) "
						+ "and ( ((not ('" + firstMap.get("NOME_REF")
						+ "' != 'null' and '" + firstMap.get("NOME_REF") + "' != '')) or (e.NOME_REF like '%"
						+ firstMap.get("NOME_REF") + "%')) " + "and ((not ('" + firstMap.get("COD_REF")
						+ "' != 'null' and '" + firstMap.get("COD_REF") + "' != '')) or (e.COD_REF like '%"
						+ firstMap.get("COD_REF") + "%')) " + "and ((not ('" + firstMap.get("NOME_COMPONENTE")
						+ "' != 'null' and '" + firstMap.get("NOME_COMPONENTE")
						+ "' != '')) or (e.NOME_COMPONENTE like '%" + firstMap.get("NOME_COMPONENTE") + "%')) ) "
						+ "and a.INATIVO != 1 and b.INATIVO != 1 "
						+ "order by a.DATA_PLANEAMENTO desc, a.HORA_PLANEAMENTO desc");

		List<PIN_MOV_PREPARACAO> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO> getallAnaliseConsumos2(List<HashMap<String, String>> querydata) {
		HashMap<String, String> firstMap = querydata.get(0);
		String ESTADO = "''";
		String ESTADO2 = "''";
		if (!firstMap.get("ESTADO").equals("'null'")) {
			ESTADO = firstMap.get("ESTADO");
			ESTADO2 = "'estado'";
		}

		String NUMEROSEMANA = "''";
		String NUMEROSEMANA2 = "''";
		if (!firstMap.get("NUMEROSEMANA").equals("'null'")) {
			NUMEROSEMANA = firstMap.get("NUMEROSEMANA");
			NUMEROSEMANA2 = "'NUMEROSEMANA'";
		}

		Query query = entityManager
				.createNativeQuery("select a.ID_PREPARACAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,a.ESTADO,a.CLASSIF, "
						+ " g.NOME_LINHA,h.NOME_TURNO, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_PLANEAMENTO) as UTZ_PLAN, "
						+ "c.ID_POTE,f.NOME,t.NOME_CABINE,b.OBS_EXECUCAO,b.OBS_PLANEAMENTO, "
						+ "b.OBS_PREPARACAO,b.DATA_EXECUCAO,b.HORA_EXECUCAO,b.DATA_PREPARACAO,b.HORA_PREPARACAO,b.DATA_PREVISTA,b.HORA_PREVISTA, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = b.UTZ_EXECUCAO) as UTZ_EXE, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = b.UTZ_PREPARACAO) as UTZ_PREP, "
						+ "c.ID_PRODUTO, e.NOME_REF,e.COD_REF,e.NOME_COMPONENTE,c.STOCK,c.STKUNIT,c.VALOR, "
						+ "(select top 1 x.MEDIDA from AB_DIC_UNIDADE_MEDIDA x where x.ID_MEDIDA = c.ID_UNIDADE) as unid, "
						+ "c.LIECOD,e.FACTOR_CONVERSAO,DATEPART( wk,b.DATA_PREPARACAO) as numerosemana "
						+ "from PIN_MOV_PREPARACAO a left join PIN_MOV_PREPARACAO_CAB b on a.ID_PREPARACAO = b.ID_PREPARACAO "
						+ " left join PIN_MOV_PREPARACAO_LINHA c on b.ID_PREPARACAO_CAB = c.ID_PREPARACAO_CAB "
						+ "left join PIN_DIC_PRODUTOS e on c.ID_PRODUTO = e.ID "
						+ "left join PIN_DIC_POTES f on c.ID_POTE = f.ID_POTE "
						+ "left join AB_DIC_LINHA g on a.ID_LINHA = g.ID_LINHA "
						+ "left join AB_DIC_TURNO h on a.ID_TURNO = h.ID_TURNO "
						+ "left join PIN_DIC_CABINES t on f.ID_CABINE = t.ID  " + "where " + "((not ('"
						+ firstMap.get("DATA_PLANEAMENTO") + "' != 'null' and '" + firstMap.get("DATA_PLANEAMENTO")
						+ "' != '')) or (b.DATA_PREPARACAO <= '" + firstMap.get("DATA_PLANEAMENTO") + "')) "
						+ "and ((not ('" + firstMap.get("DATA_PLANEAMENTO2") + "' != 'null' and '"
						+ firstMap.get("DATA_PLANEAMENTO2") + "' != '')) or (b.DATA_PREPARACAO >= '"
						+ firstMap.get("DATA_PLANEAMENTO2") + "')) " + "and ((not ('" + firstMap.get("DATA_PREVISTA")
						+ "' != 'null' and '" + firstMap.get("DATA_PREVISTA") + "' != '')) or (b.DATA_PREVISTA <= '"
						+ firstMap.get("DATA_PREVISTA") + "')) " + "and ((not ('" + firstMap.get("DATA_PREVISTA2")
						+ "' != 'null' and '" + firstMap.get("DATA_PREVISTA2") + "' != '')) or (b.DATA_PREVISTA >= '"
						+ firstMap.get("DATA_PREVISTA2") + "')) " + "and ((not (" + ESTADO2 + " != 'null' and "
						+ ESTADO2 + " != '')) or (a.ESTADO in (" + ESTADO + "))) " + "and ((not (" + NUMEROSEMANA2
						+ " != 'null' and " + NUMEROSEMANA2 + " != '')) or (DATEPART( wk,b.DATA_PREPARACAO) in ("
						+ NUMEROSEMANA + "))) " + "and ((not ('" + firstMap.get("CLASSIF") + "' != 'null' and '"
						+ firstMap.get("CLASSIF") + "' != '')) or (a.CLASSIF = '" + firstMap.get("CLASSIF") + "')) "
						+ "and ( ((not ('" + firstMap.get("NOME_REF")
						+ "' != 'null' and '" + firstMap.get("NOME_REF") + "' != '')) or (e.NOME_REF like '%"
						+ firstMap.get("NOME_REF") + "%')) " + "and ((not ('" + firstMap.get("COD_REF")
						+ "' != 'null' and '" + firstMap.get("COD_REF") + "' != '')) or (e.COD_REF like '%"
						+ firstMap.get("COD_REF") + "%')) " + "and ((not ('" + firstMap.get("NOME_COMPONENTE")
						+ "' != 'null' and '" + firstMap.get("NOME_COMPONENTE")
						+ "' != '')) or (e.NOME_COMPONENTE like '%" + firstMap.get("NOME_COMPONENTE") + "%')) ) "
						+ "and a.INATIVO != 1 and b.INATIVO != 1 "
						+ "order by a.DATA_PLANEAMENTO desc, a.HORA_PLANEAMENTO desc");

		List<PIN_MOV_PREPARACAO> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO> getallidabanho(Integer linha, ArrayList<String> query2, String classif,
			Integer idpote) {
		// System.out.println(query2);
		Integer varquery = 1;
		String Squery = "";
		if (query2 == null) {
			varquery = 0;
		}
		
		Query query = entityManager.createNativeQuery(
				"Select a.ID_PREPARACAO,null as NOME_TIPO_PREPARACAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,b.COR,b.NOME_LINHA,d.NOME_TURNO,a.ESTADO, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
						+ "WHEN a.ESTADO = 'Planeado' THEN '3' " + "WHEN a.ESTADO = 'Preparado' THEN '5' "
						+ "WHEN a.ESTADO = 'Em Preparação' THEN '3' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '2' "
						+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B " + Squery
						+ " ,(select top 1 DATA_PREVISTA from PIN_MOV_PREPARACAO_CAB x where  x.ID_PREPARACAO = a.ID_PREPARACAO  and INATIVO != 1 order by x.DATA_PREVISTA asc, HORA_PREVISTA asc) as dt_prev,"
						+ " (select top 1 HORA_PREVISTA from PIN_MOV_PREPARACAO_CAB x where  x.ID_PREPARACAO = a.ID_PREPARACAO and INATIVO != 1 order by x.DATA_PREVISTA asc, HORA_PREVISTA asc) as h_prev, "
						+ "(select top 1 TEMPO_PLANEADAS from GER_PARAMETROS) as temp_pla, "
						+ "(select top 1 TEMPO_MAX_PLANEADAS from GER_PARAMETROS) as temp_max_pla, null as cortipo "
						+ " from PIN_MOV_PREPARACAO a,AB_DIC_LINHA b,AB_DIC_TURNO d "
						+ "where  a.ID_LINHA = b.ID_LINHA and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1"
						+ "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not " + varquery
						+ " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF = :classif "
						+ "/*and a.ID_PREPARACAO in (SELECT t.ID_PREPARACAO FROM PIN_MOV_PREPARACAO_CAB t where t.ID_POTE = :idpote and t.INATIVO != 1)*/"
						+ " order by B,a.ID_PREPARACAO desc");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);
		query.setParameter("classif", classif);
		query.setParameter("idpote", idpote);

		List<PIN_MOV_PREPARACAO> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO> getallsortid(Integer linha, ArrayList<String> query2, String classif, String classif2) {
		// System.out.println(query2);
		Integer varquery = 1;
		String Squery = "";
		if (query2 == null) {
			varquery = 0;
		}

	

		Query query = entityManager.createNativeQuery(
				"Select a.ID_PREPARACAO,null as NOME_TIPO_PREPARACAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,b.COR,b.NOME_LINHA,d.NOME_TURNO,a.ESTADO, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
						+ "WHEN a.ESTADO = 'Planeado' THEN '3' " + "WHEN a.ESTADO = 'Preparado' THEN '5' "
						+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '2' "
						+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B " + Squery
						+ " ,cast(cc.data as date) as dt_prev," + "cast(cc.data as time) as h_prev, "
						+ "(select top 1 TEMPO_PLANEADAS from GER_PARAMETROS) as temp_pla, "
						+ "(select top 1 TEMPO_MAX_PLANEADAS from GER_PARAMETROS) as temp_max_pla,null as cortipo  "
						+ " from PIN_MOV_PREPARACAO a " + "left join AB_DIC_LINHA b on b.ID_LINHA = a.ID_LINHA "
						+ "left join AB_DIC_TURNO d on  a.ID_TURNO = d.ID_TURNO "
						+ "left join (select ID_PREPARACAO,MIN(cast(x.DATA_PREVISTA as datetime) + cast(x.HORA_PREVISTA as datetime)) as data from PIN_MOV_PREPARACAO_CAB x where   INATIVO != 1 GROUP BY ID_PREPARACAO) cc on cc.ID_PREPARACAO = a.ID_PREPARACAO "
						+ "where   a.INATIVO != 1" + "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not "
						+ varquery
						+ " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF in ('" + classif + "','"
						+ classif2 + "')   order by a.ID_PREPARACAO");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);
		//query.setParameter("classif", classif);

		List<PIN_MOV_PREPARACAO> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO> getbyid(Integer id, Integer linha) {
		Query query = entityManager.createQuery("Select a,b,d,e, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
				+ "WHEN a.ESTADO = 'Planeado' THEN '3' " + "WHEN a.ESTADO = 'Preparado' THEN '5' "
				+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '2' "
				+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B from PIN_MOV_PREPARACAO a,AB_DIC_LINHA b,AB_DIC_TURNO d,GER_UTILIZADORES e where a.UTZ_PLANEAMENTO = e.ID_UTILIZADOR and a.ID_LINHA = b.ID_LINHA and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1 and a.ID_PREPARACAO = :id "
				+ "and ((not :linha != 0) or (a.ID_LINHA = :linha))  order by B,a.DATA_PLANEAMENTO");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<PIN_MOV_PREPARACAO> data = query.getResultList();
		return data;

	}
}
