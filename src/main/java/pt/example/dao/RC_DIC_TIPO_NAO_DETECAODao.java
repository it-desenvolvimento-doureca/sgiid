package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_DIC_TIPO_NAO_DETECAO;

public class RC_DIC_TIPO_NAO_DETECAODao extends GenericDaoJpaImpl<RC_DIC_TIPO_NAO_DETECAO, Integer>
		implements GenericDao<RC_DIC_TIPO_NAO_DETECAO, Integer> {
	public RC_DIC_TIPO_NAO_DETECAODao() {
		super(RC_DIC_TIPO_NAO_DETECAO.class);
	}

	public List<RC_DIC_TIPO_NAO_DETECAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_DIC_TIPO_NAO_DETECAO a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_DIC_TIPO_NAO_DETECAO> data = query.getResultList();
		return data;

	}

	public List<RC_DIC_TIPO_NAO_DETECAO> getall() {

		Query query = entityManager.createQuery("Select a from RC_DIC_TIPO_NAO_DETECAO a ");
		List<RC_DIC_TIPO_NAO_DETECAO> data = query.getResultList();
		return data;

	}

}
