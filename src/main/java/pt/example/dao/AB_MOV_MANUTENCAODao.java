package pt.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.PathParam;

import pt.example.entity.AB_MOV_MANUTENCAO;

public class AB_MOV_MANUTENCAODao extends GenericDaoJpaImpl<AB_MOV_MANUTENCAO, Integer>
		implements GenericDao<AB_MOV_MANUTENCAO, Integer> {
	public AB_MOV_MANUTENCAODao() {
		super(AB_MOV_MANUTENCAO.class);
	}

	public List<AB_MOV_MANUTENCAO> getall(Integer linha, ArrayList<String> query2, String classif) {
		// System.out.println(query2);
		Integer varquery = 1;
		String Squery = "";
		if (query2 == null) {
			varquery = 0;
		}
		if (classif.equals("B")) {
			Squery = " ,(select distinct x.ID_BANHO from AB_DIC_BANHO x where x.ID_BANHO = (select distinct y.ID_BANHO from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO)) as id_banho "
					+ ",(select distinct x.NOME_BANHO from AB_DIC_BANHO x where x.ID_BANHO = (select distinct y.ID_BANHO from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO)) as nome_banho "
					+ ",(select distinct x.COD_TINA from AB_DIC_TINA x where x.ID_TINA = (select distinct y.ID_TINA from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO)) as nome_banho";
		}

		Query query = entityManager.createQuery("Select a,b,c,d,CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
				+ "WHEN a.ESTADO = 'Planeado' THEN '2' " + "WHEN a.ESTADO = 'Preparado' THEN '3' "
				+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '5' "
				+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B " + Squery
				+ " from AB_MOV_MANUTENCAO a,AB_DIC_LINHA b,AB_DIC_TIPO_MANUTENCAO c,AB_DIC_TURNO d "
				+ "where  a.ID_LINHA = b.ID_LINHA and a.ID_TIPO_MANUTENCAO = c.ID_TIPO_MANUTENCAO and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1"
				+ "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not " + varquery
				+ " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF = :classif  order by B,a.ID_MANUTENCAO desc");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);
		query.setParameter("classif", classif);

		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO> getallsortid(Integer linha, ArrayList<String> query2, String classif) {
		// System.out.println(query2);
		Integer varquery = 1;
		String Squery = "";
		if (query2 == null) {
			varquery = 0;
		}

		if (classif.equals("B")) {
			Squery = " ,(select distinct x.ID_BANHO from AB_DIC_BANHO x where x.ID_BANHO = (select distinct y.ID_BANHO from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO)) as id_banho "
					+ ",(select distinct x.NOME_BANHO from AB_DIC_BANHO x where x.ID_BANHO = (select distinct y.ID_BANHO from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO)) as nome_banho "
					+ ",(select distinct x.COD_TINA from AB_DIC_TINA x where x.ID_TINA = (select distinct y.ID_TINA from AB_MOV_MANUTENCAO_CAB y where y.ID_MANUTENCAO = a.ID_MANUTENCAO)) as nome_banho";
		}

		Query query = entityManager.createQuery("Select a,b,c,d,CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
				+ "WHEN a.ESTADO = 'Planeado' THEN '2' " + "WHEN a.ESTADO = 'Preparado' THEN '3' "
				+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '5' "
				+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B " + Squery
				+ " from AB_MOV_MANUTENCAO a,AB_DIC_LINHA b,AB_DIC_TIPO_MANUTENCAO c,AB_DIC_TURNO d "
				+ "where  a.ID_LINHA = b.ID_LINHA and a.ID_TIPO_MANUTENCAO = c.ID_TIPO_MANUTENCAO and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1"
				+ "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not " + varquery
				+ " != 0) or (a.ESTADO not in (:query2))) and a.CLASSIF = :classif   order by a.ID_MANUTENCAO");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);
		query.setParameter("classif", classif);

		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO> getbyid(Integer id, Integer linha) {
		Query query = entityManager.createQuery("Select a,b,c,d,e, CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
				+ "WHEN a.ESTADO = 'Planeado' THEN '2' " + "WHEN a.ESTADO = 'Preparado' THEN '3' "
				+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '5' "
				+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B from AB_MOV_MANUTENCAO a,AB_DIC_LINHA b,AB_DIC_TIPO_MANUTENCAO c,AB_DIC_TURNO d,GER_UTILIZADORES e where a.UTZ_PLANEAMENTO = e.ID_UTILIZADOR and a.ID_LINHA = b.ID_LINHA and a.ID_TIPO_MANUTENCAO = c.ID_TIPO_MANUTENCAO and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1 and a.ID_MANUTENCAO = :id "
				+ "and ((not :linha != 0) or (a.ID_LINHA = :linha))  order by B,a.DATA_PLANEAMENTO");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}
}
