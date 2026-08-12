package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_ASSINATURAS_SIMILARES;

public class AT_ASSINATURAS_SIMILARESDao extends GenericDaoJpaImpl<AT_ASSINATURAS_SIMILARES,Integer> implements GenericDao<AT_ASSINATURAS_SIMILARES,Integer> {
	public AT_ASSINATURAS_SIMILARESDao() {
		super(AT_ASSINATURAS_SIMILARES.class);
	}


	public List<AT_ASSINATURAS_SIMILARES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_ASSINATURAS_SIMILARES a where a.ID_OCORRENCIA = :id order by a.ID ");
		query.setParameter("id", id);
		List<AT_ASSINATURAS_SIMILARES> data = query.getResultList();
		return data;

	}

	public List<AT_ASSINATURAS_SIMILARES> getall() {

		Query query = entityManager.createQuery("Select a from AT_ASSINATURAS_SIMILARES a ");
		List<AT_ASSINATURAS_SIMILARES> data = query.getResultList();
		return data;

	}

}
