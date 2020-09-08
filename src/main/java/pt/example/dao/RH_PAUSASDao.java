package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_PAUSAS;

public class RH_PAUSASDao extends GenericDaoJpaImpl<RH_PAUSAS, Integer>
		implements GenericDao<RH_PAUSAS, Integer> {
	public RH_PAUSASDao() {
		super(RH_PAUSAS.class);
	}

	public List<RH_PAUSAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_PAUSAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_PAUSAS> data = query.getResultList();
		return data;

	}

	public List<RH_PAUSAS> getall() {

		Query query = entityManager.createQuery("Select a from RH_PAUSAS a ");
		List<RH_PAUSAS> data = query.getResultList();
		return data;

	}

}
