package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.DOC_DOCUMENTOS;

public class DOC_DOCUMENTOSDao extends GenericDaoJpaImpl<DOC_DOCUMENTOS, Integer>
		implements GenericDao<DOC_DOCUMENTOS, Integer> {
	public DOC_DOCUMENTOSDao() {
		super(DOC_DOCUMENTOS.class);
	}

	public List<DOC_DOCUMENTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from DOC_DOCUMENTOS a where a.ID = :id ");
		query.setParameter("id", id);
		List<DOC_DOCUMENTOS> data = query.getResultList();
		return data;

	}

	public List<DOC_DOCUMENTOS> getbylocalizacao(Integer id) {

		Query query = entityManager.createQuery("Select a from DOC_DOCUMENTOS a where a.ID_CAMINHO = :id ");
		query.setParameter("id", id);
		List<DOC_DOCUMENTOS> data = query.getResultList();
		return data;

	}

	public List<DOC_DOCUMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from DOC_DOCUMENTOS a where a.INATIVO != 1 ");
		List<DOC_DOCUMENTOS> data = query.getResultList();
		return data;

	}

	public List<DOC_DOCUMENTOS> getallTipo(Integer id) {

		Query query = entityManager
				.createQuery("Select a from DOC_DOCUMENTOS a where a.INATIVO != 1  and TIPO_DOCUMENTO = :id");
		query.setParameter("id", id);
		List<DOC_DOCUMENTOS> data = query.getResultList();
		return data;

	}

}
