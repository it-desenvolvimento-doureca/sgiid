package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_SECTORES_SECCAO;

public class PR_DIC_SECTORES_SECCAODao extends GenericDaoJpaImpl<PR_DIC_SECTORES_SECCAO, Integer>
		implements GenericDao<PR_DIC_SECTORES_SECCAO, Integer> {
	public PR_DIC_SECTORES_SECCAODao() {
		super(PR_DIC_SECTORES_SECCAO.class);
	}

	public List<PR_DIC_SECTORES_SECCAO> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from PR_DIC_SECTORES_SECCAO a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_DIC_SECTORES_SECCAO> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_SECTORES_SECCAO> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_SECTORES_SECCAO a ");
		List<PR_DIC_SECTORES_SECCAO> data = query.getResultList();
		return data;

	}	
	

}
