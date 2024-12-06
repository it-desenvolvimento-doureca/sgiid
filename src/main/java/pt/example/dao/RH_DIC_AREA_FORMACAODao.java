package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_AREA_FORMACAO;

public class RH_DIC_AREA_FORMACAODao extends GenericDaoJpaImpl<RH_DIC_AREA_FORMACAO, Integer>
		implements GenericDao<RH_DIC_AREA_FORMACAO, Integer> {
	public RH_DIC_AREA_FORMACAODao() {
		super(RH_DIC_AREA_FORMACAO.class);
	}

	public List<RH_DIC_AREA_FORMACAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_AREA_FORMACAO a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_DIC_AREA_FORMACAO> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_AREA_FORMACAO> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_AREA_FORMACAO a ");
		List<RH_DIC_AREA_FORMACAO> data = query.getResultList();
		return data;

	}

}
