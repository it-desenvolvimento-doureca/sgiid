package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_AMOSTRAS_ACCOES;

public class PR_AMOSTRAS_ACCOESDao extends GenericDaoJpaImpl<PR_AMOSTRAS_ACCOES, Integer> implements GenericDao<PR_AMOSTRAS_ACCOES, Integer> {
	public PR_AMOSTRAS_ACCOESDao() {
		super(PR_AMOSTRAS_ACCOES.class);
	}

	public List<PR_AMOSTRAS_ACCOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_AMOSTRAS_ACCOES a where a.ID_AMOSTRA = :id ");
		query.setParameter("id", id);
		List<PR_AMOSTRAS_ACCOES> data = query.getResultList();
		return data;

	}


	public List<PR_AMOSTRAS_ACCOES> getall() {

		Query query = entityManager.createQuery("Select a from PR_AMOSTRAS_ACCOES a");
		List<PR_AMOSTRAS_ACCOES> data = query.getResultList();
		return data;

	}

}
