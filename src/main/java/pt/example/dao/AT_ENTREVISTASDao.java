package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_ENTREVISTAS;

public class AT_ENTREVISTASDao extends GenericDaoJpaImpl<AT_ENTREVISTAS,Integer> implements GenericDao<AT_ENTREVISTAS,Integer> {
	public AT_ENTREVISTASDao() {
		super(AT_ENTREVISTAS.class);
	}

	
	public List<AT_ENTREVISTAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_ENTREVISTAS a where a.ID_OCORRENCIA = :id ");
		query.setParameter("id", id);
		List<AT_ENTREVISTAS> data = query.getResultList();
		return data;

	}
	
	public List<AT_ENTREVISTAS> getall() {

		Query query = entityManager.createQuery("Select a from AT_ENTREVISTAS a ");
		List<AT_ENTREVISTAS> data = query.getResultList();
		return data;

	}

}
