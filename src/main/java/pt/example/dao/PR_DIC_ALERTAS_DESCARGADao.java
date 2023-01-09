package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_ALERTAS_DESCARGA;

public class PR_DIC_ALERTAS_DESCARGADao extends GenericDaoJpaImpl<PR_DIC_ALERTAS_DESCARGA, Integer>
		implements GenericDao<PR_DIC_ALERTAS_DESCARGA, Integer> {
	public PR_DIC_ALERTAS_DESCARGADao() {
		super(PR_DIC_ALERTAS_DESCARGA.class);
	}

	public List<PR_DIC_ALERTAS_DESCARGA> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_ALERTAS_DESCARGA a where a.ATIVO = 1 ");
		List<PR_DIC_ALERTAS_DESCARGA> data = query.getResultList();
		return data;

	}

	
	public List<PR_DIC_ALERTAS_DESCARGA> getbyId(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_DIC_ALERTAS_DESCARGA a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_DIC_ALERTAS_DESCARGA> data = query.getResultList();
		return data;

	}
}
