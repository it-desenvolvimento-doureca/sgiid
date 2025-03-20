package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COMU_FICHEIROS;

public class COMU_FICHEIROSDao extends GenericDaoJpaImpl<COMU_FICHEIROS, Integer>
		implements GenericDao<COMU_FICHEIROS, Integer> {
	public COMU_FICHEIROSDao() {
		super(COMU_FICHEIROS.class);
	}

	public List<COMU_FICHEIROS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from COMU_FICHEIROS a where a.ID = :id ");
		query.setParameter("id", id);
		List<COMU_FICHEIROS> data = query.getResultList();
		return data;

	}

	public List<COMU_FICHEIROS> getall() {

		Query query = entityManager.createQuery("Select a from COMU_FICHEIROS a where a.ATIVO = 1");
		List<COMU_FICHEIROS> data = query.getResultList();
		return data;

	}

}
