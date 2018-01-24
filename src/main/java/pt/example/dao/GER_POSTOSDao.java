package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_POSTOS;

public class GER_POSTOSDao extends GenericDaoJpaImpl<GER_POSTOS, Integer>
		implements GenericDao<GER_POSTOS, Integer> {
	public GER_POSTOSDao() {
		super(GER_POSTOS.class);
	}

	public List<GER_POSTOS> getall() {
		Query query = entityManager
				.createQuery("select a from GER_POSTOS a");
		List<GER_POSTOS> data = query.getResultList();
		return data;

	}
	
	public List<GER_POSTOS> getByIp(String ip) {
		Query query = entityManager
				.createQuery("select a from GER_POSTOS a where a.IP_POSTO = :ip");
		query.setParameter("ip", ip);
		List<GER_POSTOS> data = query.getResultList();
		return data;

	}

}
