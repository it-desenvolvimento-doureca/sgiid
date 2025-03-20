package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COMU_TICKERS;

public class COMU_TICKERSDao extends GenericDaoJpaImpl<COMU_TICKERS, Integer>
		implements GenericDao<COMU_TICKERS, Integer> {
	public COMU_TICKERSDao() {
		super(COMU_TICKERS.class);
	}

	public List<COMU_TICKERS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from COMU_TICKERS a where a.ID = :id ");
		query.setParameter("id", id);
		List<COMU_TICKERS> data = query.getResultList();
		return data;

	}

	public List<COMU_TICKERS> getall() {

		Query query = entityManager.createQuery("Select a from COMU_TICKERS a where a.ATIVO = 1");
		List<COMU_TICKERS> data = query.getResultList();
		return data;

	}

}
