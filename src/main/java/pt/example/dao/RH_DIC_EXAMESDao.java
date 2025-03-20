package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_EXAMES;

public class RH_DIC_EXAMESDao extends GenericDaoJpaImpl<RH_DIC_EXAMES, Integer>
		implements GenericDao<RH_DIC_EXAMES, Integer> {
	public RH_DIC_EXAMESDao() {
		super(RH_DIC_EXAMES.class);
	}

	public List<RH_DIC_EXAMES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_EXAMES a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_DIC_EXAMES> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_EXAMES> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_EXAMES a where a.ATIVO = 1");
		List<RH_DIC_EXAMES> data = query.getResultList();
		return data;

	}

}
