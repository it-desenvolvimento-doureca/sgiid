package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_GRUPOS_COLABORADORES;

public class RH_DIC_GRUPOS_COLABORADORESDao extends GenericDaoJpaImpl<RH_DIC_GRUPOS_COLABORADORES, Integer>
		implements GenericDao<RH_DIC_GRUPOS_COLABORADORES, Integer> {

	public RH_DIC_GRUPOS_COLABORADORESDao() {
		super(RH_DIC_GRUPOS_COLABORADORES.class);
	}

	public List<RH_DIC_GRUPOS_COLABORADORES> getById(Integer id) {
		Query query = entityManager.createQuery("SELECT g FROM RH_DIC_GRUPOS_COLABORADORES g WHERE g.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<RH_DIC_GRUPOS_COLABORADORES> getAll() {
		Query query = entityManager.createQuery("SELECT g FROM RH_DIC_GRUPOS_COLABORADORES g where g.ATIVO = 1");
		return query.getResultList();
	}
}
