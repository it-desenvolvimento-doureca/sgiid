package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_DIC_TIPO_OCORRENCIA;

public class RC_DIC_TIPO_OCORRENCIADao extends GenericDaoJpaImpl<RC_DIC_TIPO_OCORRENCIA, Integer>
		implements GenericDao<RC_DIC_TIPO_OCORRENCIA, Integer> {
	public RC_DIC_TIPO_OCORRENCIADao() {
		super(RC_DIC_TIPO_OCORRENCIA.class);
	}

	public List<RC_DIC_TIPO_OCORRENCIA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_DIC_TIPO_OCORRENCIA a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_DIC_TIPO_OCORRENCIA> data = query.getResultList();
		return data;

	}

	public List<RC_DIC_TIPO_OCORRENCIA> getall() {

		Query query = entityManager.createQuery("Select a from RC_DIC_TIPO_OCORRENCIA a ");
		List<RC_DIC_TIPO_OCORRENCIA> data = query.getResultList();
		return data;

	}

}
