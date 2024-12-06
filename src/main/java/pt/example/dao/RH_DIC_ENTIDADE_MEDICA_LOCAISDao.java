package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_ENTIDADE_MEDICA_LOCAIS;

public class RH_DIC_ENTIDADE_MEDICA_LOCAISDao extends GenericDaoJpaImpl<RH_DIC_ENTIDADE_MEDICA_LOCAIS, Integer>
		implements GenericDao<RH_DIC_ENTIDADE_MEDICA_LOCAIS, Integer> {
	public RH_DIC_ENTIDADE_MEDICA_LOCAISDao() {
		super(RH_DIC_ENTIDADE_MEDICA_LOCAIS.class);
	}

	public List<RH_DIC_ENTIDADE_MEDICA_LOCAIS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_ENTIDADE_MEDICA_LOCAIS a where a.ID_ENTIDADE_MEDIA = :id ");
		query.setParameter("id", id);
		List<RH_DIC_ENTIDADE_MEDICA_LOCAIS> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_ENTIDADE_MEDICA_LOCAIS> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_ENTIDADE_MEDICA_LOCAIS a ");
		List<RH_DIC_ENTIDADE_MEDICA_LOCAIS> data = query.getResultList();
		return data;

	}

}
