package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_TIPOLOGIA_ENSAIO;

public class PR_DIC_TIPOLOGIA_ENSAIODao extends GenericDaoJpaImpl<PR_DIC_TIPOLOGIA_ENSAIO, Integer> implements GenericDao<PR_DIC_TIPOLOGIA_ENSAIO, Integer> {
	public PR_DIC_TIPOLOGIA_ENSAIODao() {
		super(PR_DIC_TIPOLOGIA_ENSAIO.class);
	}

	public List<PR_DIC_TIPOLOGIA_ENSAIO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_DIC_TIPOLOGIA_ENSAIO a where a.ID_TIPOLOGIA_ENSAIO = :id ");
		query.setParameter("id", id);
		List<PR_DIC_TIPOLOGIA_ENSAIO> data = query.getResultList();
		return data;

	}


	public List<PR_DIC_TIPOLOGIA_ENSAIO> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_TIPOLOGIA_ENSAIO a");
		List<PR_DIC_TIPOLOGIA_ENSAIO> data = query.getResultList();
		return data;

	}

}
