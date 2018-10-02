package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_DEPARTAMENTO;

public class GER_DEPARTAMENTODao extends GenericDaoJpaImpl<GER_DEPARTAMENTO, Integer>
		implements GenericDao<GER_DEPARTAMENTO, Integer> {
	public GER_DEPARTAMENTODao() {
		super(GER_DEPARTAMENTO.class);
	}

	public List<GER_DEPARTAMENTO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_DEPARTAMENTO a where a.ID = :id and a.INATIVO != 1");
		query.setParameter("id", id);
		List<GER_DEPARTAMENTO> data = query.getResultList();
		return data;

	}

	public List<GER_DEPARTAMENTO> getall() {

		Query query = entityManager.createQuery("Select a from GER_DEPARTAMENTO a where a.INATIVO != 1");
		List<GER_DEPARTAMENTO> data = query.getResultList();
		return data;

	}
	
	public List<GER_DEPARTAMENTO> getall2() {

		Query query = entityManager.createQuery("Select a,b from GER_DEPARTAMENTO a, GER_UTILIZADORES b where a.INATIVO != 1 and a.ID_UTZ = b.ID_UTILIZADOR");
		List<GER_DEPARTAMENTO> data = query.getResultList();
		return data;

	}
}
