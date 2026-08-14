package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_INCIDENTES_ACOES;

public class AT_INCIDENTES_ACOESDao extends GenericDaoJpaImpl<AT_INCIDENTES_ACOES,Integer> implements GenericDao<AT_INCIDENTES_ACOES,Integer> {
	public AT_INCIDENTES_ACOESDao() {
		super(AT_INCIDENTES_ACOES.class);
	}

	public List<AT_INCIDENTES_ACOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_INCIDENTES_ACOES a where a.ID_INCIDENTE = :id order by a.ID ");
		query.setParameter("id", id);
		List<AT_INCIDENTES_ACOES> data = query.getResultList();
		return data;

	}

	public List<AT_INCIDENTES_ACOES> getall() {

		Query query = entityManager.createQuery("Select a from AT_INCIDENTES_ACOES a ");
		List<AT_INCIDENTES_ACOES> data = query.getResultList();
		return data;

	}

}
