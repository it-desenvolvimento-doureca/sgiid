package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.DOC_DIC_TIPOS_DOCUMENTO;

public class DOC_DIC_TIPOS_DOCUMENTODao extends GenericDaoJpaImpl<DOC_DIC_TIPOS_DOCUMENTO, Integer>
		implements GenericDao<DOC_DIC_TIPOS_DOCUMENTO, Integer> {
	public DOC_DIC_TIPOS_DOCUMENTODao() {
		super(DOC_DIC_TIPOS_DOCUMENTO.class);
	}

	public List<DOC_DIC_TIPOS_DOCUMENTO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from DOC_DIC_TIPOS_DOCUMENTO a where a.ID = :id ");
		query.setParameter("id", id);
		List<DOC_DIC_TIPOS_DOCUMENTO> data = query.getResultList();
		return data;

	}

	public List<DOC_DIC_TIPOS_DOCUMENTO> getall() {

		Query query = entityManager.createQuery("Select a from DOC_DIC_TIPOS_DOCUMENTO a where a.INATIVO != 1 ");
		List<DOC_DIC_TIPOS_DOCUMENTO> data = query.getResultList();
		return data;

	}
	
	public List<DOC_DIC_TIPOS_DOCUMENTO> getallUser(Integer id) {

		Query query = entityManager.createNativeQuery("Select a.* from DOC_DIC_TIPOS_DOCUMENTO a inner join DOC_DIC_TIPOS_DOCUMENTO_UTZ b on a.ID = b.ID_TIPO_DOCUMENTO "
				+ "where a.INATIVO != 1 and (b.ID_UTZ = :id or (select top 1 ADMIN from GER_UTILIZADORES c where c.ID_UTILIZADOR = :id) = 1)",DOC_DIC_TIPOS_DOCUMENTO.class);
		query.setParameter("id", id);
		List<DOC_DIC_TIPOS_DOCUMENTO> data = query.getResultList();
		return data;

	}

}
