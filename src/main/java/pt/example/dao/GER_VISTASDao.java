package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_VISTAS;

public class GER_VISTASDao extends GenericDaoJpaImpl<GER_VISTAS,Integer> implements GenericDao<GER_VISTAS,Integer> {
	public GER_VISTASDao() {
		super(GER_VISTAS.class);
	}
	
	public List<GER_VISTAS> getall() {
		Query query = entityManager
				.createQuery("select a from GER_VISTAS a order by a.ID");
		List<GER_VISTAS> data = query.getResultList();
		return data;

	}
}
