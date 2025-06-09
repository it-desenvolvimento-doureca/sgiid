package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_MAQUINAS_MATRIX;

public class PR_DIC_MAQUINAS_MATRIXDao extends GenericDaoJpaImpl<PR_DIC_MAQUINAS_MATRIX, Integer>
		implements GenericDao<PR_DIC_MAQUINAS_MATRIX, Integer> {
	public PR_DIC_MAQUINAS_MATRIXDao() {
		super(PR_DIC_MAQUINAS_MATRIX.class);
	}

	public List<PR_DIC_MAQUINAS_MATRIX> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from PR_DIC_MAQUINAS_MATRIX a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_DIC_MAQUINAS_MATRIX> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_MAQUINAS_MATRIX> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_MAQUINAS_MATRIX a ");
		List<PR_DIC_MAQUINAS_MATRIX> data = query.getResultList();
		return data;

	}	
	

}
