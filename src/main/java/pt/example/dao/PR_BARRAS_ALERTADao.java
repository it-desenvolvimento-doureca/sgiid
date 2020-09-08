package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_BARRAS_ALERTA;

public class PR_BARRAS_ALERTADao extends GenericDaoJpaImpl<PR_BARRAS_ALERTA, Integer> implements GenericDao<PR_BARRAS_ALERTA, Integer> {
	public PR_BARRAS_ALERTADao() {
		super(PR_BARRAS_ALERTA.class);
	}

	public List<PR_BARRAS_ALERTA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_BARRAS_ALERTA a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_BARRAS_ALERTA> data = query.getResultList();
		return data;

	}


	public List<PR_BARRAS_ALERTA> getall() {

		Query query = entityManager.createQuery("Select a from PR_BARRAS_ALERTA a");
		List<PR_BARRAS_ALERTA> data = query.getResultList();
		return data;

	}

}
