package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_MOTIVO_CRITICIDADE;

public class PR_DIC_MOTIVO_CRITICIDADEDao extends GenericDaoJpaImpl<PR_DIC_MOTIVO_CRITICIDADE, Integer>
		implements GenericDao<PR_DIC_MOTIVO_CRITICIDADE, Integer> {
	public PR_DIC_MOTIVO_CRITICIDADEDao() {
		super(PR_DIC_MOTIVO_CRITICIDADE.class);
	}

	public List<PR_DIC_MOTIVO_CRITICIDADE> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from PR_DIC_MOTIVO_CRITICIDADE a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_DIC_MOTIVO_CRITICIDADE> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_MOTIVO_CRITICIDADE> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_MOTIVO_CRITICIDADE a where a.ATIVO = 1");
		List<PR_DIC_MOTIVO_CRITICIDADE> data = query.getResultList();
		return data;

	}	
	

}
