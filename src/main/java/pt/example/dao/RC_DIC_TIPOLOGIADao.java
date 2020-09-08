package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_DIC_TIPOLOGIA;

public class RC_DIC_TIPOLOGIADao extends GenericDaoJpaImpl<RC_DIC_TIPOLOGIA, Integer>
		implements GenericDao<RC_DIC_TIPOLOGIA, Integer> {
	public RC_DIC_TIPOLOGIADao() {
		super(RC_DIC_TIPOLOGIA.class);
	}

	public List<RC_DIC_TIPOLOGIA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_DIC_TIPOLOGIA a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_DIC_TIPOLOGIA> data = query.getResultList();
		return data;

	}

	public List<RC_DIC_TIPOLOGIA> getall() {

		Query query = entityManager.createQuery("Select a from RC_DIC_TIPOLOGIA a ");
		List<RC_DIC_TIPOLOGIA> data = query.getResultList();
		return data;

	}

}
