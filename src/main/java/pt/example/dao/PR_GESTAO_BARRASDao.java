package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_GESTAO_BARRAS;

public class PR_GESTAO_BARRASDao extends GenericDaoJpaImpl<PR_GESTAO_BARRAS, Integer>
		implements GenericDao<PR_GESTAO_BARRAS, Integer> {
	public PR_GESTAO_BARRASDao() {
		super(PR_GESTAO_BARRAS.class);
	}

	public List<PR_GESTAO_BARRAS> getbyid(Integer id) {

		Query query = entityManager
				.createQuery("Select a from PR_GESTAO_BARRAS a where a.ID_GESTAO_BARRAS = :id ");
		query.setParameter("id", id);
		List<PR_GESTAO_BARRAS> data = query.getResultList();
		return data;

	}

	public List<PR_GESTAO_BARRAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_GESTAO_BARRAS a ");
		List<PR_GESTAO_BARRAS> data = query.getResultList();
		return data;

	}

}
