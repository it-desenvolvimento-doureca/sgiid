package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.DOC_DIC_TIPOS_DOCUMENTO_UTZ;
import pt.example.entity.DOC_DIC_TIPOS_DOCUMENTO_UTZ;
import pt.example.entity.GER_UTILIZADORES;

public class DOC_DIC_TIPOS_DOCUMENTO_UTZDao extends GenericDaoJpaImpl<DOC_DIC_TIPOS_DOCUMENTO_UTZ, Integer>
		implements GenericDao<DOC_DIC_TIPOS_DOCUMENTO_UTZ, Integer> {
	public DOC_DIC_TIPOS_DOCUMENTO_UTZDao() {
		super(DOC_DIC_TIPOS_DOCUMENTO_UTZ.class);
	}

	public List<DOC_DIC_TIPOS_DOCUMENTO_UTZ> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from DOC_DIC_TIPOS_DOCUMENTO_UTZ a where a.ID = :id ");
		query.setParameter("id", id);
		List<DOC_DIC_TIPOS_DOCUMENTO_UTZ> data = query.getResultList();
		return data;

	}

	public List<DOC_DIC_TIPOS_DOCUMENTO_UTZ> getall() {

		Query query = entityManager.createQuery("Select a from DOC_DIC_TIPOS_DOCUMENTO_UTZ a ");
		List<DOC_DIC_TIPOS_DOCUMENTO_UTZ> data = query.getResultList();
		return data;

	}

	public List<DOC_DIC_TIPOS_DOCUMENTO_UTZ> getbyidgrupo(Integer id) {

		Query query = entityManager.createQuery(
				"Select a,b from DOC_DIC_TIPOS_DOCUMENTO_UTZ a, GER_UTILIZADORES b where a.ID_TIPO_DOCUMENTO = :id  and a.ID_UTZ = b.ID_UTILIZADOR");
		query.setParameter("id", id);
		List<DOC_DIC_TIPOS_DOCUMENTO_UTZ> data = query.getResultList();
		return data;

	}

	public List<GER_UTILIZADORES> getUtilizadores(Integer id) {
		Query query = entityManager.createQuery("select a from GER_UTILIZADORES a "
				+ "where  a.ID_UTILIZADOR not in (select b.ID_UTZ from DOC_DIC_TIPOS_DOCUMENTO_UTZ b where b.ID_TIPO_DOCUMENTO = :id) "
				+ "and a.INATIVO != 1 ");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}

}