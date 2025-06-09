package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_TIPOS_DOCUMENTO;

public class RH_DIC_TIPOS_DOCUMENTODao extends GenericDaoJpaImpl<RH_DIC_TIPOS_DOCUMENTO, Integer>
		implements GenericDao<RH_DIC_TIPOS_DOCUMENTO, Integer> {
	public RH_DIC_TIPOS_DOCUMENTODao() {
		super(RH_DIC_TIPOS_DOCUMENTO.class);
	}

	public List<RH_DIC_TIPOS_DOCUMENTO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_TIPOS_DOCUMENTO a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_DIC_TIPOS_DOCUMENTO> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_TIPOS_DOCUMENTO> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_TIPOS_DOCUMENTO a where a.INATIVO != 1 ");
		List<RH_DIC_TIPOS_DOCUMENTO> data = query.getResultList();
		return data;

	}
	
	public List<RH_DIC_TIPOS_DOCUMENTO> getallUser(Integer id) {

		Query query = entityManager.createNativeQuery("Select a.* from RH_DIC_TIPOS_DOCUMENTO a where a.INATIVO != 1 and "
				+ "a.ID in (select b.ID_TIPO_DOCUMENTO from RH_DIC_TIPOS_DOCUMENTO_UTZ b where b.ID_UTZ = :id or (select top 1 ADMIN from GER_UTILIZADORES c where c.ID_UTILIZADOR = :id) = 1)",RH_DIC_TIPOS_DOCUMENTO.class);
		query.setParameter("id", id);
		List<RH_DIC_TIPOS_DOCUMENTO> data = query.getResultList();
		return data;

	}

}
