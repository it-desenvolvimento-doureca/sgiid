package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_ANALISES;

public class GER_ANALISESDao extends GenericDaoJpaImpl<GER_ANALISES, Integer>
		implements GenericDao<GER_ANALISES, Integer> {
	public GER_ANALISESDao() {
		super(GER_ANALISES.class);
	}

	public List<GER_ANALISES> getbyId(Integer id) {
		Query query = entityManager.createQuery("select a from GER_ANALISES a "
				+ "where a.ID = :id");
		query.setParameter("id", id);
		List<GER_ANALISES> data = query.getResultList();
		return data;

	}
	
	public List<GER_ANALISES> getbyId_modulo( Integer id_modulo) {
		Query query = entityManager.createQuery("select a from GER_ANALISES a "
				+ "where a.MODULO = :id_modulo");
		query.setParameter("id_modulo", id_modulo);
		List<GER_ANALISES> data = query.getResultList();
		return data;

	}
	
	public List<GER_ANALISES> getall() {
		Query query = entityManager
				.createQuery("select a from GER_ANALISES a");
		List<GER_ANALISES> data = query.getResultList();
		return data;

	}

}
