package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_BUDGETS_LINHAS;

public class COM_BUDGETS_LINHASDao extends GenericDaoJpaImpl<COM_BUDGETS_LINHAS, Integer>
		implements GenericDao<COM_BUDGETS_LINHAS, Integer> {
	public COM_BUDGETS_LINHASDao() {
		super(COM_BUDGETS_LINHAS.class);
	}

	public List<COM_BUDGETS_LINHAS> getbyid(Integer id,Integer versao) {

		Query query = entityManager.createQuery("Select a from COM_BUDGETS_LINHAS a where a.ID_BUDGET = :id AND a.VERSAO_BUDGET = :versao ");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_BUDGETS_LINHAS> data = query.getResultList();
		return data;

	}

	public List<COM_BUDGETS_LINHAS> getbyid2(Integer id,Integer versao) {

		Query query = entityManager.createNativeQuery("EXEC [COM_GET_BUDGETS_LINHAS]  :id,:versao ");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_BUDGETS_LINHAS> data = query.getResultList();
		return data;

	}
	
	public List<COM_BUDGETS_LINHAS> getall() {

		Query query = entityManager.createQuery("Select a from COM_BUDGETS_LINHAS a ");
		List<COM_BUDGETS_LINHAS> data = query.getResultList();
		return data;

	}

}
