package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_INCIDENTES_PESSOAS;

public class AT_INCIDENTES_PESSOASDao extends GenericDaoJpaImpl<AT_INCIDENTES_PESSOAS,Integer> implements GenericDao<AT_INCIDENTES_PESSOAS,Integer> {
	public AT_INCIDENTES_PESSOASDao() {
		super(AT_INCIDENTES_PESSOAS.class);
	}

	public List<AT_INCIDENTES_PESSOAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_INCIDENTES_PESSOAS a where a.ID_INCIDENTE = :id order by a.ID ");
		query.setParameter("id", id);
		List<AT_INCIDENTES_PESSOAS> data = query.getResultList();
		return data;

	}

	public List<AT_INCIDENTES_PESSOAS> getall() {

		Query query = entityManager.createQuery("Select a from AT_INCIDENTES_PESSOAS a ");
		List<AT_INCIDENTES_PESSOAS> data = query.getResultList();
		return data;

	}

}
