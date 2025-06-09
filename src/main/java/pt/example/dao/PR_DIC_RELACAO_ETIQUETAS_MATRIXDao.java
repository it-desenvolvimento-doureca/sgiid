package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_RELACAO_ETIQUETAS_MATRIX;

public class PR_DIC_RELACAO_ETIQUETAS_MATRIXDao extends GenericDaoJpaImpl<PR_DIC_RELACAO_ETIQUETAS_MATRIX, Integer>
		implements GenericDao<PR_DIC_RELACAO_ETIQUETAS_MATRIX, Integer> {
	public PR_DIC_RELACAO_ETIQUETAS_MATRIXDao() {
		super(PR_DIC_RELACAO_ETIQUETAS_MATRIX.class);
	}

	public List<PR_DIC_RELACAO_ETIQUETAS_MATRIX> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from PR_DIC_RELACAO_ETIQUETAS_MATRIX a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_DIC_RELACAO_ETIQUETAS_MATRIX> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_RELACAO_ETIQUETAS_MATRIX> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_RELACAO_ETIQUETAS_MATRIX a ");
		List<PR_DIC_RELACAO_ETIQUETAS_MATRIX> data = query.getResultList();
		return data;

	}	
	

}
