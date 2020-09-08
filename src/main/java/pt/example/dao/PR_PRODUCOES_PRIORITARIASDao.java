package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_PRODUCOES_PRIORITARIAS;

public class PR_PRODUCOES_PRIORITARIASDao extends GenericDaoJpaImpl<PR_PRODUCOES_PRIORITARIAS, Integer> implements GenericDao<PR_PRODUCOES_PRIORITARIAS, Integer> {
	public PR_PRODUCOES_PRIORITARIASDao() {
		super(PR_PRODUCOES_PRIORITARIAS.class);
	}

	public List<PR_PRODUCOES_PRIORITARIAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_PRODUCOES_PRIORITARIAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_PRODUCOES_PRIORITARIAS> data = query.getResultList();
		return data;

	}


	public List<PR_PRODUCOES_PRIORITARIAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_PRODUCOES_PRIORITARIAS a");
		List<PR_PRODUCOES_PRIORITARIAS> data = query.getResultList();
		return data;

	}

}
