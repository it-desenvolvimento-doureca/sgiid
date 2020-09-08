package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_LINHAS;

public class PR_PLANEAMENTO_PRODUCAO_LINHASDao extends GenericDaoJpaImpl<PR_PLANEAMENTO_PRODUCAO_LINHAS, Integer> implements GenericDao<PR_PLANEAMENTO_PRODUCAO_LINHAS, Integer> {
	public PR_PLANEAMENTO_PRODUCAO_LINHASDao() {
		super(PR_PLANEAMENTO_PRODUCAO_LINHAS.class);
	}

	public List<PR_PLANEAMENTO_PRODUCAO_LINHAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_LINHAS a where a.ID_PLANEAMENTO_PRODUCAO_CAB = :id ");
		query.setParameter("id", id);
		List<PR_PLANEAMENTO_PRODUCAO_LINHAS> data = query.getResultList();
		return data;

	}


	public List<PR_PLANEAMENTO_PRODUCAO_LINHAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_LINHAS a");
		List<PR_PLANEAMENTO_PRODUCAO_LINHAS> data = query.getResultList();
		return data;

	}

}
