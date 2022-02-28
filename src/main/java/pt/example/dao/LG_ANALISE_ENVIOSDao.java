package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.LG_ANALISE_ENVIOS;

public class LG_ANALISE_ENVIOSDao extends GenericDaoJpaImpl<LG_ANALISE_ENVIOS,Integer> implements GenericDao<LG_ANALISE_ENVIOS,Integer> {
	public LG_ANALISE_ENVIOSDao() {
		super(LG_ANALISE_ENVIOS.class);
	}

	
	public List<LG_ANALISE_ENVIOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from LG_ANALISE_ENVIOS a where a.ID = :id ");
		query.setParameter("id", id);
		List<LG_ANALISE_ENVIOS> data = query.getResultList();
		return data;

	}
	
	public List<LG_ANALISE_ENVIOS> getall() {

		Query query = entityManager.createQuery("Select a from LG_ANALISE_ENVIOS a order by a.CON_DELIVERED_DATE desc");
		List<LG_ANALISE_ENVIOS> data = query.getResultList();
		return data;

	}

}
