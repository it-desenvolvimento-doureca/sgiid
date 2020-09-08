package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_ACCOES;

public class AT_ACCOESDao extends GenericDaoJpaImpl<AT_ACCOES,Integer> implements GenericDao<AT_ACCOES,Integer> {
	public AT_ACCOESDao() {
		super(AT_ACCOES.class);
	}

	
	public List<AT_ACCOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_ACCOES a where a.ID_OCORRENCIA = :id ");
		query.setParameter("id", id);
		List<AT_ACCOES> data = query.getResultList();
		return data;

	}
	
	public List<AT_ACCOES> getall() {

		Query query = entityManager.createQuery("Select a from AT_ACCOES a  ");
		List<AT_ACCOES> data = query.getResultList();
		return data;

	}

}
