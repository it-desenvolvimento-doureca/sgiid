package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COMU_DIC_CATEGORIAS;

public class COMU_DIC_CATEGORIASDao extends GenericDaoJpaImpl<COMU_DIC_CATEGORIAS, Integer>
		implements GenericDao<COMU_DIC_CATEGORIAS, Integer> {
	public COMU_DIC_CATEGORIASDao() {
		super(COMU_DIC_CATEGORIAS.class);
	}

	public List<COMU_DIC_CATEGORIAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from COMU_DIC_CATEGORIAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<COMU_DIC_CATEGORIAS> data = query.getResultList();
		return data;

	}

	public List<COMU_DIC_CATEGORIAS> getall() {

		Query query = entityManager.createQuery("Select a from COMU_DIC_CATEGORIAS a where a.ATIVO = 1");
		List<COMU_DIC_CATEGORIAS> data = query.getResultList();
		return data;

	}

}
