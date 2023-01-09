package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_ENTREVISTAS_RESPONSAVEL;

public class AT_ENTREVISTAS_RESPONSAVELDao extends GenericDaoJpaImpl<AT_ENTREVISTAS_RESPONSAVEL,Integer> implements GenericDao<AT_ENTREVISTAS_RESPONSAVEL,Integer> {
	public AT_ENTREVISTAS_RESPONSAVELDao() {
		super(AT_ENTREVISTAS_RESPONSAVEL.class);
	}

	
	public List<AT_ENTREVISTAS_RESPONSAVEL> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_ENTREVISTAS_RESPONSAVEL a where a.ID_OCORRENCIA = :id ");
		query.setParameter("id", id);
		List<AT_ENTREVISTAS_RESPONSAVEL> data = query.getResultList();
		return data;

	}
	
	public List<AT_ENTREVISTAS_RESPONSAVEL> getall() {

		Query query = entityManager.createQuery("Select a from AT_ENTREVISTAS_RESPONSAVEL a ");
		List<AT_ENTREVISTAS_RESPONSAVEL> data = query.getResultList();
		return data;

	}

}
