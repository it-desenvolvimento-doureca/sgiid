package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_PARAMETROS;

public class RH_DIC_PARAMETROSDao extends GenericDaoJpaImpl<RH_DIC_PARAMETROS, Integer>
		implements GenericDao<RH_DIC_PARAMETROS, Integer> {

	public RH_DIC_PARAMETROSDao() {
		super(RH_DIC_PARAMETROS.class);
	}

	public List<RH_DIC_PARAMETROS> getById(Integer id) {
		Query query = entityManager.createQuery("SELECT p FROM RH_DIC_PARAMETROS p WHERE p.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<RH_DIC_PARAMETROS> getAll() {
		Query query = entityManager.createQuery("SELECT p FROM RH_DIC_PARAMETROS p where p.ATIVO = 1");
		return query.getResultList();
	}
	
	 
}