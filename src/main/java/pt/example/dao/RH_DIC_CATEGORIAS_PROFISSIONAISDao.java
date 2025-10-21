package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_CATEGORIAS_PROFISSIONAIS;

public class RH_DIC_CATEGORIAS_PROFISSIONAISDao extends GenericDaoJpaImpl<RH_DIC_CATEGORIAS_PROFISSIONAIS, Integer>
		implements GenericDao<RH_DIC_CATEGORIAS_PROFISSIONAIS, Integer> {

	public RH_DIC_CATEGORIAS_PROFISSIONAISDao() {
		super(RH_DIC_CATEGORIAS_PROFISSIONAIS.class);
	}

	public List<RH_DIC_CATEGORIAS_PROFISSIONAIS> getById(Integer id) {
		Query query = entityManager.createQuery("SELECT g FROM RH_DIC_CATEGORIAS_PROFISSIONAIS g WHERE g.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<RH_DIC_CATEGORIAS_PROFISSIONAIS> getAll() {
		Query query = entityManager.createQuery("SELECT g FROM RH_DIC_CATEGORIAS_PROFISSIONAIS g where g.ATIVO = 1");
		return query.getResultList();
	}
}
