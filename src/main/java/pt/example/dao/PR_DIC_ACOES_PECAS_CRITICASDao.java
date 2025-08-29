package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_ACOES_PECAS_CRITICAS;

public class PR_DIC_ACOES_PECAS_CRITICASDao extends GenericDaoJpaImpl<PR_DIC_ACOES_PECAS_CRITICAS, Integer>
		implements GenericDao<PR_DIC_ACOES_PECAS_CRITICAS, Integer> {
	public PR_DIC_ACOES_PECAS_CRITICASDao() {
		super(PR_DIC_ACOES_PECAS_CRITICAS.class);
	}

	public List<PR_DIC_ACOES_PECAS_CRITICAS> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from PR_DIC_ACOES_PECAS_CRITICAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_DIC_ACOES_PECAS_CRITICAS> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_ACOES_PECAS_CRITICAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_ACOES_PECAS_CRITICAS a where a.ATIVO = 1");
		List<PR_DIC_ACOES_PECAS_CRITICAS> data = query.getResultList();
		return data;

	}	
	

}
