package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_TESTEMUNHAS;

public class AT_TESTEMUNHASDao extends GenericDaoJpaImpl<AT_TESTEMUNHAS,Integer> implements GenericDao<AT_TESTEMUNHAS,Integer> {
	public AT_TESTEMUNHASDao() {
		super(AT_TESTEMUNHAS.class);
	}

	
	public List<AT_TESTEMUNHAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_TESTEMUNHAS a where a.ID_OCORRENCIA = :id ");
		query.setParameter("id", id);
		List<AT_TESTEMUNHAS> data = query.getResultList();
		return data;

	}
	
	public List<AT_TESTEMUNHAS> getall() {

		Query query = entityManager.createQuery("Select a from AT_TESTEMUNHAS a ");
		List<AT_TESTEMUNHAS> data = query.getResultList();
		return data;

	}

}
