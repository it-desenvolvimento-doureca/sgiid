package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.TIPOS_OCORRENCIA;

public class TIPOS_OCORRENCIADao extends GenericDaoJpaImpl<TIPOS_OCORRENCIA,Integer> implements GenericDao<TIPOS_OCORRENCIA,Integer> {
	public TIPOS_OCORRENCIADao() {
		super(TIPOS_OCORRENCIA.class);
	}

	
	public List<TIPOS_OCORRENCIA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from TIPOS_OCORRENCIA a where a.ID_TIPO = :id ");
		query.setParameter("id", id);
		List<TIPOS_OCORRENCIA> data = query.getResultList();
		return data;

	}
	
	public List<TIPOS_OCORRENCIA> getall() {

		Query query = entityManager.createQuery("Select a from TIPOS_OCORRENCIA a  ");
		List<TIPOS_OCORRENCIA> data = query.getResultList();
		return data;

	}

}
