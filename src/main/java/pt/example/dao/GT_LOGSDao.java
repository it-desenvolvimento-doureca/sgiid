package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GT_LOGS;

public class GT_LOGSDao extends GenericDaoJpaImpl<GT_LOGS, Integer> implements GenericDao<GT_LOGS, Integer> {
	public GT_LOGSDao() {
		super(GT_LOGS.class);
	}

	public List<GT_LOGS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GT_LOGS a where a.ID = :id ");
		query.setParameter("id", id);
		List<GT_LOGS> data = query.getResultList();
		return data;

	}

	public List<GT_LOGS> getall() {

		Query query = entityManager.createQuery("Select a from GT_LOGS a ");
		List<GT_LOGS> data = query.getResultList();
		return data;

	}

}
