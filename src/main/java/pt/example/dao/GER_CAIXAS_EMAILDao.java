package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_CAIXAS_EMAIL;
import pt.example.entity.GER_CAIXAS_EMAIL;

public class GER_CAIXAS_EMAILDao extends GenericDaoJpaImpl<GER_CAIXAS_EMAIL,Integer> implements GenericDao<GER_CAIXAS_EMAIL,Integer> {
	public GER_CAIXAS_EMAILDao() {
		super(GER_CAIXAS_EMAIL.class);
	}
	 
	
	public List<GER_CAIXAS_EMAIL> getall() {
		Query query = entityManager.createQuery("select a  from GER_CAIXAS_EMAIL a  ");
		List<GER_CAIXAS_EMAIL> data = query.getResultList();
		return data;

	}
	
	
	public List<GER_CAIXAS_EMAIL> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from GER_CAIXAS_EMAIL a where a.ID = :id ");
		query.setParameter("id", id);
		List<GER_CAIXAS_EMAIL> data = query.getResultList();
		return data;
	}

}
