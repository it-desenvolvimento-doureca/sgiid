package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_TURNOS;

public class RH_TURNOSDao extends GenericDaoJpaImpl<RH_TURNOS, Integer>
		implements GenericDao<RH_TURNOS, Integer> {
	public RH_TURNOSDao() {
		super(RH_TURNOS.class);
	}

	public List<RH_TURNOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_TURNOS a where a.COD_TURNO = :id ");
		query.setParameter("id", id);
		List<RH_TURNOS> data = query.getResultList();
		return data;

	}

	public List<RH_TURNOS> getall() {

		Query query = entityManager.createQuery("Select a from RH_TURNOS a ");
		List<RH_TURNOS> data = query.getResultList();
		return data;

	}

}
