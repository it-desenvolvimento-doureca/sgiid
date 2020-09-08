package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_ESTADOS_FUNC;

public class RH_ESTADOS_FUNCDao extends GenericDaoJpaImpl<RH_ESTADOS_FUNC, Integer>
		implements GenericDao<RH_ESTADOS_FUNC, Integer> {
	public RH_ESTADOS_FUNCDao() {
		super(RH_ESTADOS_FUNC.class);
	}

	public List<RH_ESTADOS_FUNC> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_ESTADOS_FUNC a where a.COD_ESTADO = :id ");
		query.setParameter("id", id);
		List<RH_ESTADOS_FUNC> data = query.getResultList();
		return data;

	}

	public List<RH_ESTADOS_FUNC> getall() {

		Query query = entityManager.createQuery("Select a from RH_ESTADOS_FUNC a ");
		List<RH_ESTADOS_FUNC> data = query.getResultList();
		return data;

	}

}
