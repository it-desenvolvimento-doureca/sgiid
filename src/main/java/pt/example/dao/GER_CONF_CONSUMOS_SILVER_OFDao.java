package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_CONF_CONSUMOS_SILVER_OF;

public class GER_CONF_CONSUMOS_SILVER_OFDao extends GenericDaoJpaImpl<GER_CONF_CONSUMOS_SILVER_OF,Integer> implements GenericDao<GER_CONF_CONSUMOS_SILVER_OF,Integer> {
	public GER_CONF_CONSUMOS_SILVER_OFDao() {
		super(GER_CONF_CONSUMOS_SILVER_OF.class);
	}

	
	public List<GER_CONF_CONSUMOS_SILVER_OF> getbyidlinha(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_CONF_CONSUMOS_SILVER_OF a where a.ID_CONF = :id ");
		query.setParameter("id", id);
		List<GER_CONF_CONSUMOS_SILVER_OF> data = query.getResultList();
		return data;

	}
	
	public List<GER_CONF_CONSUMOS_SILVER_OF> getall() {

		Query query = entityManager.createQuery("Select a from GER_CONF_CONSUMOS_SILVER_OF a");
		List<GER_CONF_CONSUMOS_SILVER_OF> data = query.getResultList();
		return data;

	}

}
