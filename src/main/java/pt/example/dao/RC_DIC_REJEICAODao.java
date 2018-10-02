package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_DIC_REJEICAO;

public class RC_DIC_REJEICAODao extends GenericDaoJpaImpl<RC_DIC_REJEICAO, Integer>
		implements GenericDao<RC_DIC_REJEICAO, Integer> {
	public RC_DIC_REJEICAODao() {
		super(RC_DIC_REJEICAO.class);
	}

	public List<RC_DIC_REJEICAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_DIC_REJEICAO a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_DIC_REJEICAO> data = query.getResultList();
		return data;

	}

	public List<RC_DIC_REJEICAO> getall() {

		Query query = entityManager.createQuery("Select a from RC_DIC_REJEICAO a ");
		List<RC_DIC_REJEICAO> data = query.getResultList();
		return data;

	}

}
