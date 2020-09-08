package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_REVISOES_PRIORITARIAS;

public class PR_REVISOES_PRIORITARIASDao extends GenericDaoJpaImpl<PR_REVISOES_PRIORITARIAS, Integer>
		implements GenericDao<PR_REVISOES_PRIORITARIAS, Integer> {
	public PR_REVISOES_PRIORITARIASDao() {
		super(PR_REVISOES_PRIORITARIAS.class);
	}

	public List<PR_REVISOES_PRIORITARIAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_REVISOES_PRIORITARIAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_REVISOES_PRIORITARIAS> data = query.getResultList();
		return data;

	}

	public List<PR_REVISOES_PRIORITARIAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_REVISOES_PRIORITARIAS a");
		List<PR_REVISOES_PRIORITARIAS> data = query.getResultList();
		return data;

	}

}
