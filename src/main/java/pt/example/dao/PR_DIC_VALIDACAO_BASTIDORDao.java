package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_VALIDACAO_BASTIDOR;

public class PR_DIC_VALIDACAO_BASTIDORDao extends GenericDaoJpaImpl<PR_DIC_VALIDACAO_BASTIDOR, Integer>
		implements GenericDao<PR_DIC_VALIDACAO_BASTIDOR, Integer> {
	public PR_DIC_VALIDACAO_BASTIDORDao() {
		super(PR_DIC_VALIDACAO_BASTIDOR.class);
	}

	public List<PR_DIC_VALIDACAO_BASTIDOR> getbyid(Integer id) {

		Query query = entityManager
				.createQuery("Select a from PR_DIC_VALIDACAO_BASTIDOR a where a.ID_VALIDACAO_BASTIDOR = :id ");
		query.setParameter("id", id);
		List<PR_DIC_VALIDACAO_BASTIDOR> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_VALIDACAO_BASTIDOR> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_VALIDACAO_BASTIDOR a where a.ATIVO = 1");
		List<PR_DIC_VALIDACAO_BASTIDOR> data = query.getResultList();
		return data;

	}

}
