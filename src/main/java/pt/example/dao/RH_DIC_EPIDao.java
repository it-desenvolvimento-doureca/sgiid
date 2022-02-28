package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_EPI;

public class RH_DIC_EPIDao extends GenericDaoJpaImpl<RH_DIC_EPI, Integer> implements GenericDao<RH_DIC_EPI, Integer> {
	public RH_DIC_EPIDao() {
		super(RH_DIC_EPI.class);
	}

	public List<RH_DIC_EPI> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_EPI a where a.ID_EPI = :id ");
		query.setParameter("id", id);
		List<RH_DIC_EPI> data = query.getResultList();
		return data;

	}


	public List<RH_DIC_EPI> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_EPI a ");
		List<RH_DIC_EPI> data = query.getResultList();
		return data;

	}

}
