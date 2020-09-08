package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_DIC_OEM;

public class GER_DIC_OEMDao extends GenericDaoJpaImpl<GER_DIC_OEM, Integer>
		implements GenericDao<GER_DIC_OEM, Integer> {
	public GER_DIC_OEMDao() {
		super(GER_DIC_OEM.class);
	}

	public List<GER_DIC_OEM> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_DIC_OEM a where a.ID_OEM = :id ");
		query.setParameter("id", id);
		List<GER_DIC_OEM> data = query.getResultList();
		return data;

	}

	public List<GER_DIC_OEM> getall() {

		Query query = entityManager.createQuery("Select a from GER_DIC_OEM a");
		List<GER_DIC_OEM> data = query.getResultList();
		return data;

	}

}
