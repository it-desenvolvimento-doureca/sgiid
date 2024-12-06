package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.DOC_DOCUMENTOS_VERSOES;

public class DOC_DOCUMENTOS_VERSOESDao extends GenericDaoJpaImpl<DOC_DOCUMENTOS_VERSOES, Integer>
		implements GenericDao<DOC_DOCUMENTOS_VERSOES, Integer> {
	public DOC_DOCUMENTOS_VERSOESDao() {
		super(DOC_DOCUMENTOS_VERSOES.class);
	}

	public List<DOC_DOCUMENTOS_VERSOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from DOC_DOCUMENTOS_VERSOES a where a.ID = :id ");
		query.setParameter("id", id);
		List<DOC_DOCUMENTOS_VERSOES> data = query.getResultList();
		return data;

	}

	public List<DOC_DOCUMENTOS_VERSOES> getall() {

		Query query = entityManager.createQuery("Select a from DOC_DOCUMENTOS_VERSOES a where a.INATIVO != 1 ");
		List<DOC_DOCUMENTOS_VERSOES> data = query.getResultList();
		return data;

	}


}
