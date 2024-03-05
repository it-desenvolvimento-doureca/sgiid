package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_CORES;

public class PIN_DIC_CORESDao extends GenericDaoJpaImpl<PIN_DIC_CORES, Integer>
		implements GenericDao<PIN_DIC_CORES, Integer> {
	public PIN_DIC_CORESDao() {
		super(PIN_DIC_CORES.class);
	}

	public List<PIN_DIC_CORES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_CORES a where a.ID = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_CORES> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_CORES> getall() {

		Query query = entityManager.createQuery("Select a from PIN_DIC_CORES a where a.ATIVO = 1 ");
		List<PIN_DIC_CORES> data = query.getResultList();
		return data;

	}

}
