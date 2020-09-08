package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_CAPACIDADE_RACKS;

public class PR_DIC_CAPACIDADE_RACKSDao extends GenericDaoJpaImpl<PR_DIC_CAPACIDADE_RACKS, Integer> implements GenericDao<PR_DIC_CAPACIDADE_RACKS, Integer> {
	public PR_DIC_CAPACIDADE_RACKSDao() {
		super(PR_DIC_CAPACIDADE_RACKS.class);
	}

	public List<PR_DIC_CAPACIDADE_RACKS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_DIC_CAPACIDADE_RACKS a where a.ID_TIPOLOGIA_ENSAIO = :id ");
		query.setParameter("id", id);
		List<PR_DIC_CAPACIDADE_RACKS> data = query.getResultList();
		return data;

	}


	public List<PR_DIC_CAPACIDADE_RACKS> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_CAPACIDADE_RACKS a ");
		List<PR_DIC_CAPACIDADE_RACKS> data = query.getResultList();
		return data;

	}

}
