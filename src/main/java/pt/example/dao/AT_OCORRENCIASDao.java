package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_OCORRENCIAS;

public class AT_OCORRENCIASDao extends GenericDaoJpaImpl<AT_OCORRENCIAS, Integer>
		implements GenericDao<AT_OCORRENCIAS, Integer> {
	public AT_OCORRENCIASDao() {
		super(AT_OCORRENCIAS.class);
	}

	public List<AT_OCORRENCIAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_OCORRENCIAS a where a.ID_OCORRENCIA = :id ");
		query.setParameter("id", id);
		List<AT_OCORRENCIAS> data = query.getResultList();
		return data;

	}

	public List<AT_OCORRENCIAS> getbydata(String dataac) {

		Query query = entityManager.createQuery("Select a from AT_OCORRENCIAS a  where a.DATA_ACIDENTE = '"+dataac+"' ");

		List<AT_OCORRENCIAS> data = query.getResultList();
		return data;

	}

	public List<AT_OCORRENCIAS> getall() {

		Query query = entityManager.createQuery("Select a from AT_OCORRENCIAS a");
		List<AT_OCORRENCIAS> data = query.getResultList();
		return data;

	}

}
