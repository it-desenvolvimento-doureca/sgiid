package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_INCIDENTES_CAUSAS;

public class AT_INCIDENTES_CAUSASDao extends GenericDaoJpaImpl<AT_INCIDENTES_CAUSAS,Integer> implements GenericDao<AT_INCIDENTES_CAUSAS,Integer> {
	public AT_INCIDENTES_CAUSASDao() {
		super(AT_INCIDENTES_CAUSAS.class);
	}

	public List<AT_INCIDENTES_CAUSAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_INCIDENTES_CAUSAS a where a.ID_INCIDENTE = :id order by a.ID ");
		query.setParameter("id", id);
		List<AT_INCIDENTES_CAUSAS> data = query.getResultList();
		return data;

	}

	public List<AT_INCIDENTES_CAUSAS> getall() {

		Query query = entityManager.createQuery("Select a from AT_INCIDENTES_CAUSAS a ");
		List<AT_INCIDENTES_CAUSAS> data = query.getResultList();
		return data;

	}

}
