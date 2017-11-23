package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_PARAMETROS;

public class GER_PARAMETROSDao extends GenericDaoJpaImpl<GER_PARAMETROS,Integer> implements GenericDao<GER_PARAMETROS,Integer> {
	public GER_PARAMETROSDao() {
		super(GER_PARAMETROS.class);
	}
	
	public List<GER_PARAMETROS> getall() {
		Query query = entityManager
				.createQuery("select a from GER_PARAMETROS a");
		List<GER_PARAMETROS> data = query.getResultList();
		return data;

	}
}
