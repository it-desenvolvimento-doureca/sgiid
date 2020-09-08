package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.FIN_DIC_TIPO_DOC;

public class FIN_DIC_TIPO_DOCDao extends GenericDaoJpaImpl<FIN_DIC_TIPO_DOC, Integer>
		implements GenericDao<FIN_DIC_TIPO_DOC, Integer> {
	public FIN_DIC_TIPO_DOCDao() {
		super(FIN_DIC_TIPO_DOC.class);
	}

	public List<FIN_DIC_TIPO_DOC> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from FIN_DIC_TIPO_DOC a where a.ID = :id");
		query.setParameter("id", id);
		List<FIN_DIC_TIPO_DOC> data = query.getResultList();
		return data;
	}

	public List<FIN_DIC_TIPO_DOC> getall() {
		Query query = entityManager.createQuery("Select a from FIN_DIC_TIPO_DOC a ");
		List<FIN_DIC_TIPO_DOC> data = query.getResultList();
		return data;
	}

}
