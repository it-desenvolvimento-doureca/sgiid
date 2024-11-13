package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_REGISTO_PINTURA;

public class PR_REGISTO_PINTURADao extends GenericDaoJpaImpl<PR_REGISTO_PINTURA, Integer>
		implements GenericDao<PR_REGISTO_PINTURA, Integer> {
	public PR_REGISTO_PINTURADao() {
		super(PR_REGISTO_PINTURA.class);
	}

	public List<PR_REGISTO_PINTURA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_REGISTO_PINTURA a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_REGISTO_PINTURA> data = query.getResultList();
		return data;

	}

	public List<PR_REGISTO_PINTURA> getall() {

		Query query = entityManager.createQuery("Select a from PR_REGISTO_PINTURA a");
		List<PR_REGISTO_PINTURA> data = query.getResultList();
		return data;

	}

}
