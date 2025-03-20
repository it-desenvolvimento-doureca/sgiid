package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_ENTIDADE_FORMADORA;

public class RH_DIC_ENTIDADE_FORMADORADao extends GenericDaoJpaImpl<RH_DIC_ENTIDADE_FORMADORA, Integer>
		implements GenericDao<RH_DIC_ENTIDADE_FORMADORA, Integer> {
	public RH_DIC_ENTIDADE_FORMADORADao() {
		super(RH_DIC_ENTIDADE_FORMADORA.class);
	}

	public List<RH_DIC_ENTIDADE_FORMADORA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_ENTIDADE_FORMADORA a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_DIC_ENTIDADE_FORMADORA> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_ENTIDADE_FORMADORA> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_ENTIDADE_FORMADORA a where a.ATIVO = 1");
		List<RH_DIC_ENTIDADE_FORMADORA> data = query.getResultList();
		return data;

	}

}
