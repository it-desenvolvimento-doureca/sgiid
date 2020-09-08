package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.LG_DIC_OBJETIVOS;

public class LG_DIC_OBJETIVOSDao extends GenericDaoJpaImpl<LG_DIC_OBJETIVOS, Integer>
		implements GenericDao<LG_DIC_OBJETIVOS, Integer> {
	public LG_DIC_OBJETIVOSDao() {
		super(LG_DIC_OBJETIVOS.class);
	}

	public List<LG_DIC_OBJETIVOS> getall() {
		Query query = entityManager
				.createQuery("select a from LG_DIC_OBJETIVOS a");
		List<LG_DIC_OBJETIVOS> data = query.getResultList();
		return data;

	}
	
	public List<LG_DIC_OBJETIVOS> getById(Integer id) {
		Query query = entityManager
				.createQuery("select a from LG_DIC_OBJETIVOS a where a.ID = :id");
		query.setParameter("id", id);
		List<LG_DIC_OBJETIVOS> data = query.getResultList();
		return data;

	}

}
