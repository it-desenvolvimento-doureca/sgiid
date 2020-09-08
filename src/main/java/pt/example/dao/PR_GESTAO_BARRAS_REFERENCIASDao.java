package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_GESTAO_BARRAS_REFERENCIAS;

public class PR_GESTAO_BARRAS_REFERENCIASDao extends GenericDaoJpaImpl<PR_GESTAO_BARRAS_REFERENCIAS, Integer>
		implements GenericDao<PR_GESTAO_BARRAS_REFERENCIAS, Integer> {
	public PR_GESTAO_BARRAS_REFERENCIASDao() {
		super(PR_GESTAO_BARRAS_REFERENCIAS.class);
	}

	public List<PR_GESTAO_BARRAS_REFERENCIAS> getbyid(Integer id) {

		Query query = entityManager
				.createQuery("Select a from PR_GESTAO_BARRAS_REFERENCIAS a where a.ID_GESTAO_BARRAS = :id ");
		query.setParameter("id", id);
		List<PR_GESTAO_BARRAS_REFERENCIAS> data = query.getResultList();
		return data;

	}

	public List<PR_GESTAO_BARRAS_REFERENCIAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_GESTAO_BARRAS_REFERENCIAS a ");
		List<PR_GESTAO_BARRAS_REFERENCIAS> data = query.getResultList();
		return data;

	}

}
