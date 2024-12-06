package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_PERIOCIDADE_ALERTAS;

public class RH_DIC_PERIOCIDADE_ALERTASDao extends GenericDaoJpaImpl<RH_DIC_PERIOCIDADE_ALERTAS, Integer>
		implements GenericDao<RH_DIC_PERIOCIDADE_ALERTAS, Integer> {
	public RH_DIC_PERIOCIDADE_ALERTASDao() {
		super(RH_DIC_PERIOCIDADE_ALERTAS.class);
	}

	public List<RH_DIC_PERIOCIDADE_ALERTAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_PERIOCIDADE_ALERTAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_DIC_PERIOCIDADE_ALERTAS> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_PERIOCIDADE_ALERTAS> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_PERIOCIDADE_ALERTAS a ");
		List<RH_DIC_PERIOCIDADE_ALERTAS> data = query.getResultList();
		return data;

	}

}
