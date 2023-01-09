package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_CONF_CONSUMOS_SILVER;

public class GER_CONF_CONSUMOS_SILVERDao extends GenericDaoJpaImpl<GER_CONF_CONSUMOS_SILVER,Integer> implements GenericDao<GER_CONF_CONSUMOS_SILVER,Integer> {
	public GER_CONF_CONSUMOS_SILVERDao() {
		super(GER_CONF_CONSUMOS_SILVER.class);
	}

	
	public List<GER_CONF_CONSUMOS_SILVER> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_CONF_CONSUMOS_SILVER a where a.ID_CONF = :id ");
		query.setParameter("id", id);
		List<GER_CONF_CONSUMOS_SILVER> data = query.getResultList();
		return data;

	}
	
	public List<GER_CONF_CONSUMOS_SILVER> getall() {

		Query query = entityManager.createQuery("Select a from GER_CONF_CONSUMOS_SILVER a ");
		List<GER_CONF_CONSUMOS_SILVER> data = query.getResultList();
		return data;

	}

}
