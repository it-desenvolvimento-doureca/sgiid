package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_SECCAO;

public class GER_SECCAODao extends GenericDaoJpaImpl<GER_SECCAO, Integer>
		implements GenericDao<GER_SECCAO, Integer> {
	public GER_SECCAODao() {
		super(GER_SECCAO.class);
	}

	public List<GER_SECCAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_SECCAO a where a.ID = :id ");
		query.setParameter("id", id);
		List<GER_SECCAO> data = query.getResultList();
		return data;

	}

	public List<GER_SECCAO> getall() {

		Query query = entityManager.createQuery("Select a from GER_SECCAO a where a.INATIVO = 0");
		List<GER_SECCAO> data = query.getResultList();
		return data;

	}
	
	public List<GER_SECCAO> getall2() {

		Query query = entityManager.createQuery("Select a,b from GER_SECCAO a,GER_DEPARTAMENTO b where a.ID_DEPARTAMENTO = b.id and a.INATIVO = 0");
		List<GER_SECCAO> data = query.getResultList();
		return data;

	}

}
