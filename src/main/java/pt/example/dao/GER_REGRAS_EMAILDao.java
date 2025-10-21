package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_REGRAS_EMAIL; 

public class GER_REGRAS_EMAILDao extends GenericDaoJpaImpl<GER_REGRAS_EMAIL,Integer> implements GenericDao<GER_REGRAS_EMAIL,Integer> {
	public GER_REGRAS_EMAILDao() {
		super(GER_REGRAS_EMAIL.class);
	}
	
	public List<GER_REGRAS_EMAIL> getall() {
		Query query = entityManager.createQuery("select a  from GER_REGRAS_EMAIL a  ");
		List<GER_REGRAS_EMAIL> data = query.getResultList();
		return data;

	}
	
	public List<GER_REGRAS_EMAIL> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_REGRAS_EMAIL a where a.ID_CAIXA = :id ");
		query.setParameter("id", id);
		List<GER_REGRAS_EMAIL> data = query.getResultList();
		return data;

	}

}
