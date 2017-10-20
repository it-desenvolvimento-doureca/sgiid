package pt.example.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_BANHO;
import pt.example.entity.AB_MOV_ANALISE;

public class AB_MOV_ANALISEDao extends GenericDaoJpaImpl<AB_MOV_ANALISE, Integer>
		implements GenericDao<AB_MOV_ANALISE, Integer> {
	public AB_MOV_ANALISEDao() {
		super(AB_MOV_ANALISE.class);
	}

	public List<AB_MOV_ANALISE> getbyid(Integer id, Integer linha) {

		Query query = entityManager.createQuery(
				"Select a,b,c from AB_MOV_ANALISE a,AB_DIC_LINHA b,AB_DIC_BANHO c where  a.ID_LINHA = b.ID_LINHA and a.ID_BANHO = c.ID_BANHO and a.ID_ANALISE = :id "
						+ "and ((not :linha != 0) or (a.ID_LINHA = :linha)) ");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<AB_MOV_ANALISE> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_ANALISE> getall(Integer linha) {
		Query query = entityManager.createQuery(
				"Select a,b,c,d from AB_MOV_ANALISE a,AB_DIC_LINHA b,AB_DIC_BANHO c,AB_DIC_TINA d where  a.ID_LINHA = b.ID_LINHA and a.ID_BANHO = c.ID_BANHO and c.ID_TINA = d.ID_TINA and a.INATIVO not in (1) "
						+ "and ((not :linha != 0) or (a.ID_LINHA = :linha)) order by a.ID_ANALISE desc");
		query.setParameter("linha", linha);
		List<AB_MOV_ANALISE> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_ANALISE> getallmanu(Integer linha, Integer idbanho, Date datas) {
		Query query = entityManager
				.createQuery("Select a,b,c,d from AB_MOV_ANALISE a,AB_DIC_LINHA b,AB_DIC_BANHO c,AB_DIC_TINA d where  "
						+ "a.ID_BANHO = :idbanho and a.ID_ANALISE not in(select e.ID_ANALISE from AB_MOV_MANUTENCAO_CAB e where e.ID_ANALISE is not null) and a.ESTADO = 1 and  "
						+ "a.ID_LINHA = b.ID_LINHA and a.ID_BANHO = c.ID_BANHO and c.ID_TINA = d.ID_TINA and a.INATIVO not in (1) "
						+ "and ((not :linha != 0) or (a.ID_LINHA = :linha))  and a.DATA_ANALISE > :datas  order by a.ID_ANALISE desc");
		query.setParameter("linha", linha);
		query.setParameter("idbanho", idbanho);
		query.setParameter("datas", datas);
		List<AB_MOV_ANALISE> data = query.getResultList();
		return data;

	}

}
