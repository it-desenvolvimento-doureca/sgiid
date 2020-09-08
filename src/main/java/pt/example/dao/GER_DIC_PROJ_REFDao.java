package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_DIC_PROJ_REF;

public class GER_DIC_PROJ_REFDao extends GenericDaoJpaImpl<GER_DIC_PROJ_REF, Integer>
		implements GenericDao<GER_DIC_PROJ_REF, Integer> {
	public GER_DIC_PROJ_REFDao() {
		super(GER_DIC_PROJ_REF.class);
	}

	public List<GER_DIC_PROJ_REF> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_DIC_PROJ_REF a where a.ID_PROJ_CAB = :id ");
		query.setParameter("id", id);
		List<GER_DIC_PROJ_REF> data = query.getResultList();
		return data;

	}

	public List<GER_DIC_PROJ_REF> getall() {

		Query query = entityManager.createQuery("Select a from GER_DIC_PROJ_REF a");
		List<GER_DIC_PROJ_REF> data = query.getResultList();
		return data;

	}

}
