package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS;

public class PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHASDao extends GenericDaoJpaImpl<PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS, Integer> implements GenericDao<PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS, Integer> {
	public PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHASDao() {
		super(PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS.class);
	}

	public List<PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS a where a.ID_PLANEAMENTO_PRODUCAO_CAB = :id ");
		query.setParameter("id", id);
		List<PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS> data = query.getResultList();
		return data;

	}


	public List<PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS a");
		List<PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS> data = query.getResultList();
		return data;

	}

}
