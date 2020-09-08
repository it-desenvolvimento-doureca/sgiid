package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_OBJETIVOS_PLANOS;

public class PR_DIC_OBJETIVOS_PLANOSDao extends GenericDaoJpaImpl<PR_DIC_OBJETIVOS_PLANOS, Integer>
		implements GenericDao<PR_DIC_OBJETIVOS_PLANOS, Integer> {
	public PR_DIC_OBJETIVOS_PLANOSDao() {
		super(PR_DIC_OBJETIVOS_PLANOS.class);
	}

	public List<PR_DIC_OBJETIVOS_PLANOS> getall() {
		Query query = entityManager
				.createQuery("select a from PR_DIC_OBJETIVOS_PLANOS a");
		List<PR_DIC_OBJETIVOS_PLANOS> data = query.getResultList();
		return data;

	}
	
	public List<PR_DIC_OBJETIVOS_PLANOS> getById(Integer id) {
		Query query = entityManager
				.createQuery("select a from PR_DIC_OBJETIVOS_PLANOS a where a.ID = :id");
		query.setParameter("id", id);
		List<PR_DIC_OBJETIVOS_PLANOS> data = query.getResultList();
		return data;

	}

}
