package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_SECTORES_AGREGADORES;

public class PR_DIC_SECTORES_AGREGADORESDao extends GenericDaoJpaImpl<PR_DIC_SECTORES_AGREGADORES, Integer>
		implements GenericDao<PR_DIC_SECTORES_AGREGADORES, Integer> {
	public PR_DIC_SECTORES_AGREGADORESDao() {
		super(PR_DIC_SECTORES_AGREGADORES.class);
	}

	public List<PR_DIC_SECTORES_AGREGADORES> getbyid(Integer id) {

		Query query = entityManager
				.createQuery("Select a from PR_DIC_SECTORES_AGREGADORES a where a.ID_SECTOR_AGREGADOR = :id ");
		query.setParameter("id", id);
		List<PR_DIC_SECTORES_AGREGADORES> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_SECTORES_AGREGADORES> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_SECTORES_AGREGADORES a where a.ATIVO = 1");
		List<PR_DIC_SECTORES_AGREGADORES> data = query.getResultList();
		return data;

	}

}
