package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_DIC_TIPO_DEFEITO;

public class RC_DIC_TIPO_DEFEITODao extends GenericDaoJpaImpl<RC_DIC_TIPO_DEFEITO, Integer>
		implements GenericDao<RC_DIC_TIPO_DEFEITO, Integer> {
	public RC_DIC_TIPO_DEFEITODao() {
		super(RC_DIC_TIPO_DEFEITO.class);
	}

	public List<RC_DIC_TIPO_DEFEITO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_DIC_TIPO_DEFEITO a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_DIC_TIPO_DEFEITO> data = query.getResultList();
		return data;

	}

	public List<RC_DIC_TIPO_DEFEITO> getall() {

		Query query = entityManager.createQuery("Select a from RC_DIC_TIPO_DEFEITO a ");
		List<RC_DIC_TIPO_DEFEITO> data = query.getResultList();
		return data;

	}

}
