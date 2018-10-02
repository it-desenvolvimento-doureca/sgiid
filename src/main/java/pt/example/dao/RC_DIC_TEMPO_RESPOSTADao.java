package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_DIC_TEMPO_RESPOSTA;

public class RC_DIC_TEMPO_RESPOSTADao extends GenericDaoJpaImpl<RC_DIC_TEMPO_RESPOSTA, Integer>
		implements GenericDao<RC_DIC_TEMPO_RESPOSTA, Integer> {
	public RC_DIC_TEMPO_RESPOSTADao() {
		super(RC_DIC_TEMPO_RESPOSTA.class);
	}

	public List<RC_DIC_TEMPO_RESPOSTA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_DIC_TEMPO_RESPOSTA a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_DIC_TEMPO_RESPOSTA> data = query.getResultList();
		return data;

	}

	public List<RC_DIC_TEMPO_RESPOSTA> getall() {

		Query query = entityManager.createQuery("Select a from RC_DIC_TEMPO_RESPOSTA a ");
		List<RC_DIC_TEMPO_RESPOSTA> data = query.getResultList();
		return data;

	}

}
