package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_ENTIDADE_MEDICA;

public class RH_DIC_ENTIDADE_MEDICADao extends GenericDaoJpaImpl<RH_DIC_ENTIDADE_MEDICA, Integer>
		implements GenericDao<RH_DIC_ENTIDADE_MEDICA, Integer> {
	public RH_DIC_ENTIDADE_MEDICADao() {
		super(RH_DIC_ENTIDADE_MEDICA.class);
	}

	public List<RH_DIC_ENTIDADE_MEDICA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_ENTIDADE_MEDICA a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_DIC_ENTIDADE_MEDICA> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_ENTIDADE_MEDICA> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_ENTIDADE_MEDICA a where a.ATIVO = 1");
		List<RH_DIC_ENTIDADE_MEDICA> data = query.getResultList();
		return data;

	}

}
