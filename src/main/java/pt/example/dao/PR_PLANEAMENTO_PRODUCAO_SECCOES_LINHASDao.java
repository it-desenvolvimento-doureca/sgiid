package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS;

public class PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHASDao extends GenericDaoJpaImpl<PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS, Integer> implements GenericDao<PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS, Integer> {
	public PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHASDao() {
		super(PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS.class);
	}

	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS a where a.ID_PLANEAMENTO_PRODUCAO_CAB = :id ");
		query.setParameter("id", id);
		List<PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS> data = query.getResultList();
		return data;

	}


	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS a");
		List<PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS> data = query.getResultList();
		return data;

	}

}
