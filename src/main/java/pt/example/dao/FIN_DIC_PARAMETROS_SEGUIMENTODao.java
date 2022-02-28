package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.FIN_DIC_PARAMETROS_SEGUIMENTO;

public class FIN_DIC_PARAMETROS_SEGUIMENTODao extends GenericDaoJpaImpl<FIN_DIC_PARAMETROS_SEGUIMENTO, Integer>
		implements GenericDao<FIN_DIC_PARAMETROS_SEGUIMENTO, Integer> {
	public FIN_DIC_PARAMETROS_SEGUIMENTODao() {
		super(FIN_DIC_PARAMETROS_SEGUIMENTO.class);
	}

	public List<FIN_DIC_PARAMETROS_SEGUIMENTO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from FIN_DIC_PARAMETROS_SEGUIMENTO a where a.ID_ANALISE = :id  ");
		query.setParameter("id", id);
		List<FIN_DIC_PARAMETROS_SEGUIMENTO> data = query.getResultList();
		return data;

	}
	
	public List<FIN_DIC_PARAMETROS_SEGUIMENTO> getall() {

		Query query = entityManager.createQuery("Select a from FIN_DIC_PARAMETROS_SEGUIMENTO a ");
		List<FIN_DIC_PARAMETROS_SEGUIMENTO> data = query.getResultList();
		return data;

	}

	

}
