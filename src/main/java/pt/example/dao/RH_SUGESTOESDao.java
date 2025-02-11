package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_SUGESTOES;

public class RH_SUGESTOESDao extends GenericDaoJpaImpl<RH_SUGESTOES, Integer>
		implements GenericDao<RH_SUGESTOES, Integer> {
	public RH_SUGESTOESDao() {
		super(RH_SUGESTOES.class);
	}

	public List<RH_SUGESTOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_SUGESTOES a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_SUGESTOES> data = query.getResultList();
		return data;

	}

	public List<RH_SUGESTOES> getall() {

		Query query = entityManager.createQuery("Select a from RH_SUGESTOES a where a.ATIVO = 1");
		List<RH_SUGESTOES> data = query.getResultList();
		return data;

	}

}
