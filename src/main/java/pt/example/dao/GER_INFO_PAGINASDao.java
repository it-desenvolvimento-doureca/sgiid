package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_ANALISES;
import pt.example.entity.GER_INFO_PAGINAS;

public class GER_INFO_PAGINASDao extends GenericDaoJpaImpl<GER_INFO_PAGINAS, Integer>
		implements GenericDao<GER_INFO_PAGINAS, Integer> {
	public GER_INFO_PAGINASDao() {
		super(GER_INFO_PAGINAS.class);
	}

	public List<GER_INFO_PAGINAS> getbyId(String url) {
		Query query = entityManager.createQuery("select a from GER_INFO_PAGINAS a where a.URL = :url");
		query.setParameter("url", url);
		List<GER_INFO_PAGINAS> data = query.getResultList();
		return data;

	}

}
