package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_SEMANAS_ANALISE;

public class PR_DIC_SEMANAS_ANALISEDao extends GenericDaoJpaImpl<PR_DIC_SEMANAS_ANALISE, Integer> implements GenericDao<PR_DIC_SEMANAS_ANALISE, Integer> {
	public PR_DIC_SEMANAS_ANALISEDao() {
		super(PR_DIC_SEMANAS_ANALISE.class);
	}

	public List<PR_DIC_SEMANAS_ANALISE> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_DIC_SEMANAS_ANALISE a where a.ID_TIPOLOGIA_ENSAIO = :id ");
		query.setParameter("id", id);
		List<PR_DIC_SEMANAS_ANALISE> data = query.getResultList();
		return data;

	}


	public List<PR_DIC_SEMANAS_ANALISE> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_SEMANAS_ANALISE a where a.ATIVO = 1");
		List<PR_DIC_SEMANAS_ANALISE> data = query.getResultList();
		return data;

	}

}
