package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_DIC_CAUSAS_ACIDENTE;

public class AT_DIC_CAUSAS_ACIDENTEDao extends GenericDaoJpaImpl<AT_DIC_CAUSAS_ACIDENTE, Integer> implements GenericDao<AT_DIC_CAUSAS_ACIDENTE, Integer> {
	public AT_DIC_CAUSAS_ACIDENTEDao() {
		super(AT_DIC_CAUSAS_ACIDENTE.class);
	}

	public List<AT_DIC_CAUSAS_ACIDENTE> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_DIC_CAUSAS_ACIDENTE a where a.ID_CAUSAS_ACIDENTE = :id ");
		query.setParameter("id", id);
		List<AT_DIC_CAUSAS_ACIDENTE> data = query.getResultList();
		return data;

	}


	public List<AT_DIC_CAUSAS_ACIDENTE> getall() {

		Query query = entityManager.createQuery("Select a from AT_DIC_CAUSAS_ACIDENTE a ");
		List<AT_DIC_CAUSAS_ACIDENTE> data = query.getResultList();
		return data;

	}

}
