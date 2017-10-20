package pt.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_MOV_ANALISE;
import pt.example.entity.AB_MOV_MANUTENCAO;

public class AB_MOV_MANUTENCAODao extends GenericDaoJpaImpl<AB_MOV_MANUTENCAO, Integer>
		implements GenericDao<AB_MOV_MANUTENCAO, Integer> {
	public AB_MOV_MANUTENCAODao() {
		super(AB_MOV_MANUTENCAO.class);
	}

	public List<AB_MOV_MANUTENCAO> getall(Integer linha, ArrayList<String> query2) {
		// System.out.println(query2);
		Integer varquery = 1;
		if (query2 == null) {
			varquery = 0;
		}

		Query query = entityManager.createQuery("Select a,b,c,d,CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
				+ "WHEN a.ESTADO = 'Planeado' THEN '2' " + "WHEN a.ESTADO = 'Preparado' THEN '3' "
				+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '5' "
				+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B from AB_MOV_MANUTENCAO a,AB_DIC_LINHA b,AB_DIC_TIPO_MANUTENCAO c,AB_DIC_TURNO d "
				+ "where  a.ID_LINHA = b.ID_LINHA and a.ID_TIPO_MANUTENCAO = c.ID_TIPO_MANUTENCAO and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1"
				+ "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not " + varquery
				+ " != 0) or (a.ESTADO not in (:query2)))   order by B,a.ID_MANUTENCAO desc");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);

		List<AB_MOV_MANUTENCAO> data = query.getResultList();
		return data;

	}
	
	public List<AB_MOV_MANUTENCAO> getallsortid(Integer linha, ArrayList<String> query2) {
		// System.out.println(query2);
		Integer varquery = 1;
		if (query2 == null) {
			varquery = 0;
		}

		Query query = entityManager.createQuery("Select a,b,c,d,CASE WHEN a.ESTADO = 'Em Execução' THEN '1' "
				+ "WHEN a.ESTADO = 'Planeado' THEN '2' " + "WHEN a.ESTADO = 'Preparado' THEN '3' "
				+ "WHEN a.ESTADO = 'Em Preparação' THEN '4' " + "WHEN a.ESTADO = 'Em Planeamento' THEN '5' "
				+ "WHEN a.ESTADO = 'Executado' THEN '6' END AS B from AB_MOV_MANUTENCAO a,AB_DIC_LINHA b,AB_DIC_TIPO_MANUTENCAO c,AB_DIC_TURNO d "
				+ "where  a.ID_LINHA = b.ID_LINHA and a.ID_TIPO_MANUTENCAO = c.ID_TIPO_MANUTENCAO and a.ID_TURNO = d.ID_TURNO and a.INATIVO != 1"
				+ "and ((not :linha != 0) or (a.ID_LINHA = :linha)) and ((not " + varquery
				+ " != 0) or (a.ESTADO not in (:query2)))   order by a.ID_MANUTENCAO");

		query.setParameter("linha", linha);
		query.setParameter("query2", query2);

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
