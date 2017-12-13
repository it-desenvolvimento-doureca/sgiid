package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_EVENTOS_CONF;

public class GER_EVENTOS_CONFDao extends GenericDaoJpaImpl<GER_EVENTOS_CONF, Integer>
		implements GenericDao<GER_EVENTOS_CONF, Integer> {
	public GER_EVENTOS_CONFDao() {
		super(GER_EVENTOS_CONF.class);
	}

	public List<GER_EVENTOS_CONF> getbyId(Integer id) {
		Query query = entityManager.createQuery("select a,b from GER_EVENTOS_CONF a, GER_MODULO b "
				+ "where  a.ID_EVENTO = :id and  a.MODULO = b.ID_MODULO");
		query.setParameter("id", id);
		List<GER_EVENTOS_CONF> data = query.getResultList();
		return data;

	}

	public List<GER_EVENTOS_CONF> getall() {
		Query query = entityManager
				.createQuery("select a,b from GER_EVENTOS_CONF a, GER_MODULO b " + "where  a.MODULO = b.ID_MODULO");
		List<GER_EVENTOS_CONF> data = query.getResultList();
		return data;

	}

}
