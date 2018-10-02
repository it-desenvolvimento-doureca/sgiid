package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_DIC_GRAU_IMPORTANCIA;

public class RC_DIC_GRAU_IMPORTANCIADao extends GenericDaoJpaImpl<RC_DIC_GRAU_IMPORTANCIA, Integer>
		implements GenericDao<RC_DIC_GRAU_IMPORTANCIA, Integer> {
	public RC_DIC_GRAU_IMPORTANCIADao() {
		super(RC_DIC_GRAU_IMPORTANCIA.class);
	}

	public List<RC_DIC_GRAU_IMPORTANCIA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_DIC_GRAU_IMPORTANCIA a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_DIC_GRAU_IMPORTANCIA> data = query.getResultList();
		return data;

	}

	public List<RC_DIC_GRAU_IMPORTANCIA> getall() {

		Query query = entityManager.createQuery("Select a from RC_DIC_GRAU_IMPORTANCIA a ");
		List<RC_DIC_GRAU_IMPORTANCIA> data = query.getResultList();
		return data;

	}

}
