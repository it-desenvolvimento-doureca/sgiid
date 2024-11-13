package pt.example.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.PathParam;

import pt.example.entity.AB_MOV_MANUTENCAO;

public class AB_MOV_MANUTENCAODao extends GenericDaoJpaImpl<AB_MOV_MANUTENCAO, Integer>
		implements GenericDao<AB_MOV_MANUTENCAO, Integer> {
	public AB_MOV_MANUTENCAODao() {
		super(AB_MOV_MANUTENCAO.class);
	}

	public List<AB_MOV_MANUTENCAO> getall(Integer linha, ArrayList<String> query2, String classif, String classif2) {
		// System.out.println(query2);
		Integer varquery = 1;
		String Squery = ""; 
		if (query2 == null) {
			varquery = 0;
		}
		if (classif.equals("B")) {
			Squery = " ,(select top 1 x.ID_BANHO from AB_DIC_BANHO x where x.ID_BANHO = (select top 1 y.ID_BANHO from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO and y.INATIVO != 1)) as id_banho "
					+ ",(select top 1 x.NOME_BANHO from AB_DIC_BANHO x where x.ID_BANHO = (select top 1 y.ID_BANHO from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO and y.INATIVO != 1)) as nome_banho "
					+ ",(select top 1 x.COD_TINA from AB_DIC_TINA x where x.ID_TINA = (select top 1 y.ID_TINA from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO and y.INATIVO != 1)) as nome_tina";
		}

		/*
		 * Query query = entityManager.createNativeQuery(
		 * "Select a.ID_MANUTENCAO,c.NOME_TIPO_MANUTENCAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,b.COR,b.NOME_LINHA,d.NOME_TURNO,a.ESTADO, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
		 * + "WHEN a.ESTADO = 'Planeado' THEN '3' " +
		 * "WHEN a.ESTADO = 'Preparado' THEN '5' " +
		 * "WHEN a.ESTADO = 'Em Preparação' THEN '4' " +
		 * "WHEN a.ESTADO = 'Em Planeamento' THEN '2' " +
		 * "WHEN a.ESTADO = 'Executado' THEN '6' END AS B " + Squery +
		 * " ,(select top 1 DATA_PREVISTA from AB_MOV_MANUTENCAO_CAB x where  x.ID_MANUTENCAO = a.ID_MANUTENCAO and INATIVO != 1 order by x.DATA_PREVISTA asc, HORA_PREVISTA asc) as dt_prev,"
		 * +
		 * " (select top 1 HORA_PREVISTA from AB_MOV_MANUTENCAO_CAB x where  x.ID_MANUTENCAO = a.ID_MANUTENCAO and INATIVO != 1 order by x.DATA_PREVISTA asc, HORA_PREVISTA asc) as h_prev, "
		 * + "(select top 1 TEMPO_PLANEADAS from GER_PARAMETROS) as temp_pla, "
		 * +
		 * "(select top 1 TEMPO_MAX_PLANEADAS from GER_PARAMETROS) as temp_max_pla,c.COR as cortipo "
		 * +
		 * "from AB_MOV_MANUTENCAO a,AB_DIC_LINHA b,AB_DIC_TIPO_MANUTENCAO c,AB_DIC_TURNO d "
		 * +
		 * "where  a.ID_LINHA = b.ID_LINHA and a.ID_TIPO_MANUTENCAO = c.ID_TIPO_MANUTENCAO and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1"
		 * + "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not " +
		 * varquery +
		 * " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF = :classif  order by B,a.ID_MANUTENCAO desc"
		 * );
		 */
		Query query = entityManager.createNativeQuery(
				"Select a.ID_MANUTENCAO,CASE WHEN a.ID_TIPO_TIPOLOGIA_DOSIFICADORES IS NULL THEN c.NOME_TIPO_MANUTENCAO ELSE c.NOME_TIPO_MANUTENCAO +' - '+ f.NOME END as NOME_TIPO_MANUTENCAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,b.COR,b.NOME_LINHA,d.NOME_TURNO,a.ESTADO, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
						+ "WHEN a.ESTADO = 'Planeado' THEN '3' " + "WHEN a.ESTADO = 'Preparado' THEN '5' "
						+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '2' "
						+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B " + Squery
						+ "  ,CASE WHEN a.CLASSIF = 'D' THEN  a.DATA_PLANEAMENTO ELSE cast(cc.data as date) END as dt_prev "
						+ " ,CASE WHEN a.CLASSIF = 'D' THEN  a.HORA_PLANEAMENTO ELSE cast(cc.data as time) END as h_prev, "
						+ "(select top 1 TEMPO_PLANEADAS from GER_PARAMETROS) as temp_pla, "
						+ "(select top 1 TEMPO_MAX_PLANEADAS from GER_PARAMETROS) as temp_max_pla,c.COR as cortipo,a.CLASSIF,a.ID_LINHA  "
						+ "from AB_MOV_MANUTENCAO a " + "left join AB_DIC_LINHA b on b.ID_LINHA = a.ID_LINHA "
						+ " left join AB_DIC_TIPO_MANUTENCAO c on c.ID_TIPO_MANUTENCAO = a.ID_TIPO_MANUTENCAO "
						+ " left join AB_DIC_TURNO d on  a.ID_TURNO = d.ID_TURNO "
						+ "left join (select ID_MANUTENCAO,MIN(cast(x.DATA_PREVISTA as datetime) + cast(x.HORA_PREVISTA as datetime)) as data from AB_MOV_MANUTENCAO_CAB x where   INATIVO != 1 GROUP BY ID_MANUTENCAO) cc on cc.ID_MANUTENCAO = a.ID_MANUTENCAO "
						+ "left join AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES f on a.ID_TIPO_TIPOLOGIA_DOSIFICADORES = f.ID "
						+ "where  a.INATIVO != 1" + "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not "
						+ varquery + " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF in ('" + classif + "','"
						+ classif2 + "')  order by B,a.ID_MANUTENCAO desc");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);
		// query.setParameter("classif", classif);

		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO> getallmanu(Integer linha, List<HashMap<String, String>> data2) {
		// System.out.println(query2);
		HashMap<String, String> firstMap = data2.get(0);

		ArrayList<String> query2 = new ArrayList<String>(Arrays.asList(firstMap.get("query").split(",")));
		String classif = firstMap.get("classif");
		String querybanho = "";
		Integer varquery = 1;

		if (firstMap.get("querybanho") != null) {
			querybanho = " and a.ID_MANUTENCAO in (SELECT t.ID_MANUTENCAO FROM AB_MOV_MANUTENCAO_CAB t where t.ID_BANHO = "
					+ firstMap.get("querybanho") + " and t.INATIVO != 1 )";
		}

		if (query2 == null) {
			varquery = 0;
		}

		/*
		 * Query query = entityManager.createNativeQuery(
		 * "Select a.ID_MANUTENCAO,c.NOME_TIPO_MANUTENCAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,b.COR,b.NOME_LINHA,d.NOME_TURNO,a.ESTADO, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
		 * + "WHEN a.ESTADO = 'Planeado' THEN '3' " +
		 * "WHEN a.ESTADO = 'Preparado' THEN '5' " +
		 * "WHEN a.ESTADO = 'Em Preparação' THEN '4' " +
		 * "WHEN a.ESTADO = 'Em Planeamento' THEN '2' " +
		 * "WHEN a.ESTADO = 'Executado' THEN '6' END AS B, a.CLASSIF " +
		 * " ,(select top 1 DATA_PREVISTA from AB_MOV_MANUTENCAO_CAB x where  x.ID_MANUTENCAO = a.ID_MANUTENCAO  and INATIVO != 1 order by x.DATA_PREVISTA asc, HORA_PREVISTA asc) as dt_prev,"
		 * +
		 * " (select top 1 HORA_PREVISTA from AB_MOV_MANUTENCAO_CAB x where  x.ID_MANUTENCAO = a.ID_MANUTENCAO and INATIVO != 1 order by x.DATA_PREVISTA asc, HORA_PREVISTA asc) as h_prev, "
		 * + "(select top 1 TEMPO_PLANEADAS from GER_PARAMETROS) as temp_pla," +
		 * "(select top 1 TEMPO_MAX_PLANEADAS from GER_PARAMETROS) as temp_max_pla,c.COR as cortipo "
		 * +
		 * "from AB_MOV_MANUTENCAO a,AB_DIC_LINHA b,AB_DIC_TIPO_MANUTENCAO c,AB_DIC_TURNO d "
		 * +
		 * "where  a.ID_LINHA = b.ID_LINHA and a.ID_TIPO_MANUTENCAO = c.ID_TIPO_MANUTENCAO and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1"
		 * + "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not " +
		 * varquery +
		 * " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF in (" + classif
		 * + ") " + querybanho + " order by B,a.ID_MANUTENCAO desc");
		 */

		Query query = entityManager.createNativeQuery(
				"Select a.ID_MANUTENCAO,CASE WHEN a.ID_TIPO_TIPOLOGIA_DOSIFICADORES IS NULL THEN c.NOME_TIPO_MANUTENCAO ELSE c.NOME_TIPO_MANUTENCAO +' - '+ f.NOME END as NOME_TIPO_MANUTENCAO, "
						+ "a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,b.COR,b.NOME_LINHA,d.NOME_TURNO,a.ESTADO, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
						+ "WHEN a.ESTADO = 'Planeado' THEN '3' " + "WHEN a.ESTADO = 'Preparado' THEN '5' "
						+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '2' "
						+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B, a.CLASSIF "
						+ " ,CASE WHEN a.CLASSIF = 'D' THEN  a.DATA_PLANEAMENTO ELSE cast(cc.data as date) END as dt_prev "
						+ ",CASE WHEN a.CLASSIF = 'D' THEN  a.HORA_PLANEAMENTO ELSE cast(cc.data as time) END as h_prev "
						+ ",(select top 1 TEMPO_PLANEADAS from GER_PARAMETROS) as temp_pla,"
						+ "(select top 1 TEMPO_MAX_PLANEADAS from GER_PARAMETROS) as temp_max_pla,c.COR as cortipo,a.ID_LINHA  "
						+ "from AB_MOV_MANUTENCAO a " + "left join AB_DIC_LINHA b on b.ID_LINHA = a.ID_LINHA "
						+ "left join AB_DIC_TIPO_MANUTENCAO c on c.ID_TIPO_MANUTENCAO = a.ID_TIPO_MANUTENCAO "
						+ "left join AB_DIC_TURNO d on  a.ID_TURNO = d.ID_TURNO "
						+ "left join (select ID_MANUTENCAO,MIN(cast(x.DATA_PREVISTA as datetime) + cast(x.HORA_PREVISTA as datetime)) as data from AB_MOV_MANUTENCAO_CAB x where   INATIVO != 1 GROUP BY ID_MANUTENCAO) cc on cc.ID_MANUTENCAO = a.ID_MANUTENCAO "
						+ "left join AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES f on a.ID_TIPO_TIPOLOGIA_DOSIFICADORES = f.ID "
						+ "where a.INATIVO != 1" + "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not "
						+ varquery + " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF in (" + classif + ") "
						+ querybanho + " order by B,a.ID_MANUTENCAO desc");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);

		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO> getallAnaliseConsumos(List<HashMap<String, String>> querydata) {
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
				.createNativeQuery("select a.ID_MANUTENCAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,a.ESTADO,a.CLASSIF, "
						+ "k.NOME_TIPO_MANUTENCAO, g.NOME_LINHA,h.NOME_TURNO, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_PLANEAMENTO) as UTZ_PLAN, "
						+ "b.ID_BANHO,f.NOME_BANHO,t.COD_TINA,l.NOME_TIPO_OPERACAO,j.NOME_TIPO_ADICAO,b.OBS_EXECUCAO,b.OBS_PLANEAMENTO, "
						+ "b.OBS_PREPARACAO,b.DATA_EXECUCAO,b.HORA_EXECUCAO,b.DATA_PREPARACAO,b.HORA_PREPARACAO,b.DATA_PREVISTA,b.HORA_PREVISTA, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = b.UTZ_EXECUCAO) as UTZ_EXE, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = b.UTZ_PREPARACAO) as UTZ_PREP, "
						+ "(CASE b.DOSEADOR  WHEN '1' THEN f.DOSE1  WHEN '2' THEN f.DOSE2  WHEN '3' THEN f.DOSE3  WHEN '4' THEN f.DOSE4  WHEN '5' THEN f.DOSE5 WHEN '6' THEN f.DOSE6 ELSE '' END) as doses, "
						+ "c.ID_ADITIVO, e.NOME_REF,e.COD_REF,e.NOME_COMPONENTE,c.STOCK,c.STKUNIT,c.VALOR1, "
						+ "(select top 1 x.MEDIDA from AB_DIC_UNIDADE_MEDIDA x where x.ID_MEDIDA = c.ID_UNIDADE1) as unid1, "
						+ "c.VALOR2, "
						+ "(select top 1 x.MEDIDA from AB_DIC_UNIDADE_MEDIDA x where x.ID_MEDIDA = c.ID_UNIDADE2) as unid2, "
						+ "c.VALOR_AGUA,c.LIECOD,d.ETQNUM,d.QUANT,d.QUANT_FINAL,e.FACTOR_CONVERSAO,d.CONSUMIR,DATEPART( wk,b.DATA_PREPARACAO) as numerosemana "
						+ "from AB_MOV_MANUTENCAO a left join AB_MOV_MANUTENCAO_CAB b on a.ID_MANUTENCAO = b.ID_MANUTENCAO "
						+ " left join AB_MOV_MANUTENCAO_LINHA c on b.ID_MANUTENCAO_CAB = c.ID_MANUTENCAO_CAB "
						+ "left join AB_MOV_MANUTENCAO_ETIQ d on c.ID_MANUTENCAO_LIN = d.ID_MANUTENCAO_LIN "
						+ "left join AB_DIC_COMPONENTE e on c.ID_ADITIVO = e.ID_COMPONENTE "
						+ "left join AB_DIC_BANHO f on b.ID_BANHO = f.ID_BANHO "
						+ "left join AB_DIC_LINHA g on a.ID_LINHA = g.ID_LINHA "
						+ "left join AB_DIC_TURNO h on a.ID_TURNO = h.ID_TURNO "
						+ "left join AB_DIC_TIPO_ADICAO j on b.ID_TIPO_ADICAO = j.ID_TIPO_ADICAO "
						+ "left join AB_DIC_TIPO_MANUTENCAO k on  a.ID_TIPO_MANUTENCAO = k.ID_TIPO_MANUTENCAO "
						+ "left join AB_DIC_TIPO_OPERACAO l on b.ID_TIPO_OPERACAO = l.ID_TIPO_OPERACAO "
						+ "left join AB_DIC_TINA t on b.ID_TINA = t.ID_TINA " + "where "
						// + "((not ('"+ firstMap.get("DATA_PLANEAMENTO") + "'
						// != 'null' and '" + firstMap.get("DATA_PLANEAMENTO") +
						// "' != '')) or (a.DATA_PLANEAMENTO <= '" +
						// firstMap.get("DATA_PLANEAMENTO") + "')) "
						// + "and ((not ('"+ firstMap.get("DATA_PLANEAMENTO2") +
						// "' != 'null' and '" +
						// firstMap.get("DATA_PLANEAMENTO2") + "' != '')) or
						// (a.DATA_PLANEAMENTO >= '" +
						// firstMap.get("DATA_PLANEAMENTO2") + "')) "
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
						+ "and ((not ('" + firstMap.get("ID_TIPO_MANUTENCAO") + "' != 'null' and '"
						+ firstMap.get("ID_TIPO_MANUTENCAO") + "' != '')) or (a.ID_TIPO_MANUTENCAO = '"
						+ firstMap.get("ID_TIPO_MANUTENCAO") + "')) " + "and ( ((not ('" + firstMap.get("NOME_REF")
						+ "' != 'null' and '" + firstMap.get("NOME_REF") + "' != '')) or (e.NOME_REF like '%"
						+ firstMap.get("NOME_REF") + "%')) " + "and ((not ('" + firstMap.get("COD_REF")
						+ "' != 'null' and '" + firstMap.get("COD_REF") + "' != '')) or (e.COD_REF like '%"
						+ firstMap.get("COD_REF") + "%')) " + "and ((not ('" + firstMap.get("NOME_COMPONENTE")
						+ "' != 'null' and '" + firstMap.get("NOME_COMPONENTE")
						+ "' != '')) or (e.NOME_COMPONENTE like '%" + firstMap.get("NOME_COMPONENTE") + "%')) ) "
						+ "and a.INATIVO != 1 and b.INATIVO != 1 "
						+ "order by a.DATA_PLANEAMENTO desc, a.HORA_PLANEAMENTO desc");

		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO> getallAnaliseConsumos2(List<HashMap<String, String>> querydata) {
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
				.createNativeQuery("select a.ID_MANUTENCAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,a.ESTADO,a.CLASSIF, "
						+ "k.NOME_TIPO_MANUTENCAO, g.NOME_LINHA,h.NOME_TURNO, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_PLANEAMENTO) as UTZ_PLAN, "
						+ "b.ID_BANHO,f.NOME_BANHO,t.COD_TINA,l.NOME_TIPO_OPERACAO,j.NOME_TIPO_ADICAO,b.OBS_EXECUCAO,b.OBS_PLANEAMENTO, "
						+ "b.OBS_PREPARACAO,b.DATA_EXECUCAO,b.HORA_EXECUCAO,b.DATA_PREPARACAO,b.HORA_PREPARACAO,b.DATA_PREVISTA,b.HORA_PREVISTA, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = b.UTZ_EXECUCAO) as UTZ_EXE, "
						+ "(select top 1 x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = b.UTZ_PREPARACAO) as UTZ_PREP, "
						+ "(CASE b.DOSEADOR  WHEN '1' THEN f.DOSE1  WHEN '2' THEN f.DOSE2  WHEN '3' THEN f.DOSE3  WHEN '4' THEN f.DOSE4  WHEN '5' THEN f.DOSE5  WHEN '6' THEN f.DOSE6  ELSE '' END) as doses, "
						+ "c.ID_ADITIVO, e.NOME_REF,e.COD_REF,e.NOME_COMPONENTE,c.STOCK,c.STKUNIT,c.VALOR1, "
						+ "(select top 1 x.MEDIDA from AB_DIC_UNIDADE_MEDIDA x where x.ID_MEDIDA = c.ID_UNIDADE1) as unid1, "
						+ "c.VALOR2, "
						+ "(select top 1 x.MEDIDA from AB_DIC_UNIDADE_MEDIDA x where x.ID_MEDIDA = c.ID_UNIDADE2) as unid2, "
						+ "c.VALOR_AGUA,c.LIECOD,e.FACTOR_CONVERSAO,DATEPART( wk,b.DATA_PREPARACAO) as numerosemana "
						+ "from AB_MOV_MANUTENCAO a left join AB_MOV_MANUTENCAO_CAB b on a.ID_MANUTENCAO = b.ID_MANUTENCAO "
						+ " left join AB_MOV_MANUTENCAO_LINHA c on b.ID_MANUTENCAO_CAB = c.ID_MANUTENCAO_CAB "
						+ "left join AB_DIC_COMPONENTE e on c.ID_ADITIVO = e.ID_COMPONENTE "
						+ "left join AB_DIC_BANHO f on b.ID_BANHO = f.ID_BANHO "
						+ "left join AB_DIC_LINHA g on a.ID_LINHA = g.ID_LINHA "
						+ "left join AB_DIC_TURNO h on a.ID_TURNO = h.ID_TURNO "
						+ "left join AB_DIC_TIPO_ADICAO j on b.ID_TIPO_ADICAO = j.ID_TIPO_ADICAO "
						+ "left join AB_DIC_TIPO_MANUTENCAO k on  a.ID_TIPO_MANUTENCAO = k.ID_TIPO_MANUTENCAO "
						+ "left join AB_DIC_TIPO_OPERACAO l on b.ID_TIPO_OPERACAO = l.ID_TIPO_OPERACAO "
						+ "left join AB_DIC_TINA t on b.ID_TINA = t.ID_TINA " + "where " + "((not ('"
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
						+ "and ((not ('" + firstMap.get("ID_TIPO_MANUTENCAO") + "' != 'null' and '"
						+ firstMap.get("ID_TIPO_MANUTENCAO") + "' != '')) or (a.ID_TIPO_MANUTENCAO = '"
						+ firstMap.get("ID_TIPO_MANUTENCAO") + "')) " + "and ( ((not ('" + firstMap.get("NOME_REF")
						+ "' != 'null' and '" + firstMap.get("NOME_REF") + "' != '')) or (e.NOME_REF like '%"
						+ firstMap.get("NOME_REF") + "%')) " + "and ((not ('" + firstMap.get("COD_REF")
						+ "' != 'null' and '" + firstMap.get("COD_REF") + "' != '')) or (e.COD_REF like '%"
						+ firstMap.get("COD_REF") + "%')) " + "and ((not ('" + firstMap.get("NOME_COMPONENTE")
						+ "' != 'null' and '" + firstMap.get("NOME_COMPONENTE")
						+ "' != '')) or (e.NOME_COMPONENTE like '%" + firstMap.get("NOME_COMPONENTE") + "%')) ) "
						+ "and a.INATIVO != 1 and b.INATIVO != 1 "
						+ "order by a.DATA_PLANEAMENTO desc, a.HORA_PLANEAMENTO desc");

		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO> getallidabanho(Integer linha, ArrayList<String> query2, String classif,
			Integer idbanho) {
		// System.out.println(query2);
		Integer varquery = 1;
		String Squery = "";
		if (query2 == null) {
			varquery = 0;
		}
		if (classif.equals("B")) {
			Squery = " ,(select top 1 x.ID_BANHO from AB_DIC_BANHO x where x.ID_BANHO = (select top 1 y.ID_BANHO from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO and y.INATIVO != 1)) as id_banho "
					+ ",(select top 1 x.NOME_BANHO from AB_DIC_BANHO x where x.ID_BANHO = (select top 1 y.ID_BANHO from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO and y.INATIVO != 1)) as nome_banho "
					+ ",(select top 1 x.COD_TINA from AB_DIC_TINA x where x.ID_TINA = (select top 1 distinct y.ID_TINA from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO and y.INATIVO != 1)) as nome_tina";
		}

		Query query = entityManager.createNativeQuery(
				"Select a.ID_MANUTENCAO,c.NOME_TIPO_MANUTENCAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,b.COR,b.NOME_LINHA,d.NOME_TURNO,a.ESTADO, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
						+ "WHEN a.ESTADO = 'Planeado' THEN '3' " + "WHEN a.ESTADO = 'Preparado' THEN '5' "
						+ "WHEN a.ESTADO = 'Em Preparação' THEN '3' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '2' "
						+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B " + Squery
						+ " ,(select top 1 DATA_PREVISTA from AB_MOV_MANUTENCAO_CAB x where  x.ID_MANUTENCAO = a.ID_MANUTENCAO  and INATIVO != 1 order by x.DATA_PREVISTA asc, HORA_PREVISTA asc) as dt_prev,"
						+ " (select top 1 HORA_PREVISTA from AB_MOV_MANUTENCAO_CAB x where  x.ID_MANUTENCAO = a.ID_MANUTENCAO and INATIVO != 1 order by x.DATA_PREVISTA asc, HORA_PREVISTA asc) as h_prev, "
						+ "(select top 1 TEMPO_PLANEADAS from GER_PARAMETROS) as temp_pla, "
						+ "(select top 1 TEMPO_MAX_PLANEADAS from GER_PARAMETROS) as temp_max_pla, c.COR as cortipo "
						+ " from AB_MOV_MANUTENCAO a,AB_DIC_LINHA b,AB_DIC_TIPO_MANUTENCAO c,AB_DIC_TURNO d "
						+ "where  a.ID_LINHA = b.ID_LINHA and a.ID_TIPO_MANUTENCAO = c.ID_TIPO_MANUTENCAO and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1"
						+ "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not " + varquery
						+ " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF = :classif "
						+ "and a.ID_MANUTENCAO in (SELECT t.ID_MANUTENCAO FROM AB_MOV_MANUTENCAO_CAB t where t.ID_BANHO = :idbanho and t.INATIVO != 1)"
						+ " order by B,a.ID_MANUTENCAO desc");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);
		query.setParameter("classif", classif);
		query.setParameter("idbanho", idbanho);

		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO> getallsortid(Integer linha, ArrayList<String> query2, String classif, String classif2) {
		// System.out.println(query2);
		Integer varquery = 1;
		String Squery = "";
		if (query2 == null) {
			varquery = 0;
		}

		if (classif.equals("B")) {
			Squery = " ,(select top 1 x.ID_BANHO from AB_DIC_BANHO x where x.ID_BANHO = (select top 1 y.ID_BANHO from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO and y.INATIVO != 1)) as id_banho "
					+ ",(select top 1 x.NOME_BANHO from AB_DIC_BANHO x where x.ID_BANHO = (select top 1 y.ID_BANHO from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO and y.INATIVO != 1)) as nome_banho "
					+ ",(select top 1 x.COD_TINA from AB_DIC_TINA x where x.ID_TINA = (select top 1 y.ID_TINA from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO and y.INATIVO != 1)) as nome_tina";
		}

		Query query = entityManager.createNativeQuery(
				"Select a.ID_MANUTENCAO,c.NOME_TIPO_MANUTENCAO,a.DATA_PLANEAMENTO,a.HORA_PLANEAMENTO,b.COR,b.NOME_LINHA,d.NOME_TURNO,a.ESTADO, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
						+ "WHEN a.ESTADO = 'Planeado' THEN '3' " + "WHEN a.ESTADO = 'Preparado' THEN '5' "
						+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '2' "
						+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B " + Squery
						+ " ,cast(cc.data as date) as dt_prev," + "cast(cc.data as time) as h_prev, "
						+ "(select top 1 TEMPO_PLANEADAS from GER_PARAMETROS) as temp_pla, "
						+ "(select top 1 TEMPO_MAX_PLANEADAS from GER_PARAMETROS) as temp_max_pla,c.COR as cortipo "
						+ " from AB_MOV_MANUTENCAO a " + "left join AB_DIC_LINHA b on b.ID_LINHA = a.ID_LINHA "
						+ "left join AB_DIC_TIPO_MANUTENCAO c on c.ID_TIPO_MANUTENCAO = a.ID_TIPO_MANUTENCAO "
						+ "left join AB_DIC_TURNO d on  a.ID_TURNO = d.ID_TURNO "
						+ "left join (select ID_MANUTENCAO,MIN(cast(x.DATA_PREVISTA as datetime) + cast(x.HORA_PREVISTA as datetime)) as data from AB_MOV_MANUTENCAO_CAB x where   INATIVO != 1 GROUP BY ID_MANUTENCAO) cc on cc.ID_MANUTENCAO = a.ID_MANUTENCAO "
						+ "where   a.INATIVO != 1" + "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not "
						+ varquery
						+ " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF in ('" + classif + "','"
						+ classif2 + "')   order by a.ID_MANUTENCAO");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);
		//query.setParameter("classif", classif);

		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO> getbyid(Integer id, Integer linha) {
		Query query = entityManager.createQuery("Select a,b,c,d,e, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
				+ "WHEN a.ESTADO = 'Planeado' THEN '3' " + "WHEN a.ESTADO = 'Preparado' THEN '5' "
				+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '2' "
				+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B from AB_MOV_MANUTENCAO a,AB_DIC_LINHA b,AB_DIC_TIPO_MANUTENCAO c,AB_DIC_TURNO d,GER_UTILIZADORES e where a.UTZ_PLANEAMENTO = e.ID_UTILIZADOR and a.ID_LINHA = b.ID_LINHA and a.ID_TIPO_MANUTENCAO = c.ID_TIPO_MANUTENCAO and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1 and a.ID_MANUTENCAO = :id "
				+ "and ((not :linha != 0) or (a.ID_LINHA = :linha))  order by B,a.DATA_PLANEAMENTO");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}
}
