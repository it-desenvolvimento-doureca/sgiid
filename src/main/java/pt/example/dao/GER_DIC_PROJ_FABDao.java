package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_DIC_PROJ_FAB;

public class GER_DIC_PROJ_FABDao extends GenericDaoJpaImpl<GER_DIC_PROJ_FAB, Integer>
		implements GenericDao<GER_DIC_PROJ_FAB, Integer> {
	public GER_DIC_PROJ_FABDao() {
		super(GER_DIC_PROJ_FAB.class);
	}

	public List<GER_DIC_PROJ_FAB> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_DIC_PROJ_FAB a where a.ID_PROJ_CAB = :id ");
		query.setParameter("id", id);
		List<GER_DIC_PROJ_FAB> data = query.getResultList();
		return data;

	}

	public List<GER_DIC_PROJ_FAB> getall() {

		Query query = entityManager.createQuery("Select a from GER_DIC_PROJ_FAB a");
		List<GER_DIC_PROJ_FAB> data = query.getResultList();
		return data;

	}

}
