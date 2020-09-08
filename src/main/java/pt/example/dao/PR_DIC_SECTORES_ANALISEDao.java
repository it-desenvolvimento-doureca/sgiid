package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_SECTORES_ANALISE;

public class PR_DIC_SECTORES_ANALISEDao extends GenericDaoJpaImpl<PR_DIC_SECTORES_ANALISE, Integer> implements GenericDao<PR_DIC_SECTORES_ANALISE, Integer> {
	public PR_DIC_SECTORES_ANALISEDao() {
		super(PR_DIC_SECTORES_ANALISE.class);
	}

	public List<PR_DIC_SECTORES_ANALISE> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_DIC_SECTORES_ANALISE a where a.ID_SECTOR_ANALISE = :id ");
		query.setParameter("id", id);
		List<PR_DIC_SECTORES_ANALISE> data = query.getResultList();
		return data;

	}


	public List<PR_DIC_SECTORES_ANALISE> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_SECTORES_ANALISE a");
		List<PR_DIC_SECTORES_ANALISE> data = query.getResultList();
		return data;

	}

}
