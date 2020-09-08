package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_DIC_FABRICA;

public class GER_DIC_FABRICADao extends GenericDaoJpaImpl<GER_DIC_FABRICA, Integer>
		implements GenericDao<GER_DIC_FABRICA, Integer> {
	public GER_DIC_FABRICADao() {
		super(GER_DIC_FABRICA.class);
	}

	public List<GER_DIC_FABRICA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_DIC_FABRICA a where a.ID_FABRICA = :id ");
		query.setParameter("id", id);
		List<GER_DIC_FABRICA> data = query.getResultList();
		return data;

	}

	public List<GER_DIC_FABRICA> getall() {

		Query query = entityManager.createQuery("Select a from GER_DIC_FABRICA a");
		List<GER_DIC_FABRICA> data = query.getResultList();
		return data;

	}

}
