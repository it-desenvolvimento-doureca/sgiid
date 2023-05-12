package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_BUDGETS_ANALISES;

public class COM_BUDGETS_ANALISESDao extends GenericDaoJpaImpl<COM_BUDGETS_ANALISES, Integer>
		implements GenericDao<COM_BUDGETS_ANALISES, Integer> {
	public COM_BUDGETS_ANALISESDao() {
		super(COM_BUDGETS_ANALISES.class);
	}

	public List<COM_BUDGETS_ANALISES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from COM_BUDGETS_ANALISES a where a.ID = :id ");
		query.setParameter("id", id);
		List<COM_BUDGETS_ANALISES> data = query.getResultList();
		return data;

	}

	public List<COM_BUDGETS_ANALISES> getall() {

		Query query = entityManager.createQuery("Select a from COM_BUDGETS_ANALISES a where a.ATIVO = 1");
		List<COM_BUDGETS_ANALISES> data = query.getResultList();
		return data;

	}

}
