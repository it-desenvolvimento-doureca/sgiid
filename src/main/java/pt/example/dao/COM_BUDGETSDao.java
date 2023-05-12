package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_BUDGETS; 

public class COM_BUDGETSDao extends GenericDaoJpaImpl<COM_BUDGETS, Integer>
		implements GenericDao<COM_BUDGETS, Integer> {
	public COM_BUDGETSDao() {
		super(COM_BUDGETS.class);
	}

	public List<COM_BUDGETS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from COM_BUDGETS a where a.ID = :id ");
		query.setParameter("id", id);
		List<COM_BUDGETS> data = query.getResultList();
		return data;

	}

	public List<COM_BUDGETS> getall() {

		Query query = entityManager.createQuery("Select a,(select SUM(b.BUDGET_YEAR) as TOTAL FROM COM_BUDGETS_LINHAS b where B.ID_BUDGET = a.ID) as TOTAL_BUDGET from COM_BUDGETS a where a.ATIVO = 1 ORDER BY a.ANO DESC");
		List<COM_BUDGETS> data = query.getResultList();
		return data;

	}
	
	public List<COM_BUDGETS> verificaseexiste(Integer id, Integer ano) {
		Query query = entityManager.createNativeQuery(
				"select ID from COM_BUDGETS where ANO = " + ano + " AND ATIVO = 1 AND ID not in (" + id + ")");
		List<COM_BUDGETS> data = query.getResultList();
		return data;

	}

}
