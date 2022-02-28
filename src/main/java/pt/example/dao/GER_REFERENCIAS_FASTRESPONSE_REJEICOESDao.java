package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_REFERENCIAS_FASTRESPONSE_REJEICOES;
import pt.example.entity.GER_REFERENCIAS_FASTRESPONSE_REJEICOES;

public class GER_REFERENCIAS_FASTRESPONSE_REJEICOESDao
		extends GenericDaoJpaImpl<GER_REFERENCIAS_FASTRESPONSE_REJEICOES, Integer>
		implements GenericDao<GER_REFERENCIAS_FASTRESPONSE_REJEICOES, Integer> {
	public GER_REFERENCIAS_FASTRESPONSE_REJEICOESDao() {
		super(GER_REFERENCIAS_FASTRESPONSE_REJEICOES.class);
	}

	public List<GER_REFERENCIAS_FASTRESPONSE_REJEICOES> getall() {
		Query query = entityManager.createQuery("select a from GER_REFERENCIAS_FASTRESPONSE_REJEICOES a");
		List<GER_REFERENCIAS_FASTRESPONSE_REJEICOES> data = query.getResultList();
		return data;

	}

	public List<GER_REFERENCIAS_FASTRESPONSE_REJEICOES> getbyid(Integer id) {
		Query query = entityManager
				.createQuery("select a from GER_REFERENCIAS_FASTRESPONSE_REJEICOES a where a.ID = :id");
		query.setParameter("id", id);
		List<GER_REFERENCIAS_FASTRESPONSE_REJEICOES> data = query.getResultList();
		return data;

	}

	public List<GER_REFERENCIAS_FASTRESPONSE_REJEICOES> getByData(String data) {
		Query query = entityManager
				.createQuery("select a from GER_REFERENCIAS_FASTRESPONSE_REJEICOES a where a.DATA = '" + data + "'");

		List<GER_REFERENCIAS_FASTRESPONSE_REJEICOES> data_d = query.getResultList();
		return data_d;

	}

}
