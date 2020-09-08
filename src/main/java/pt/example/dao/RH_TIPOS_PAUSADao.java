package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_TIPOS_PAUSA;

public class RH_TIPOS_PAUSADao extends GenericDaoJpaImpl<RH_TIPOS_PAUSA, Integer>
		implements GenericDao<RH_TIPOS_PAUSA, Integer> {
	public RH_TIPOS_PAUSADao() {
		super(RH_TIPOS_PAUSA.class);
	}

	public List<RH_TIPOS_PAUSA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_TIPOS_PAUSA a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_TIPOS_PAUSA> data = query.getResultList();
		return data;

	}

	public List<RH_TIPOS_PAUSA> getall() {

		Query query = entityManager.createQuery("Select a from RH_TIPOS_PAUSA a ");
		List<RH_TIPOS_PAUSA> data = query.getResultList();
		return data;

	}

}
