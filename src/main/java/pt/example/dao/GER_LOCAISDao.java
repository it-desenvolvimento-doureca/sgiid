package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_LOCAIS;

public class GER_LOCAISDao extends GenericDaoJpaImpl<GER_LOCAIS, Integer>
		implements GenericDao<GER_LOCAIS, Integer> {
	public GER_LOCAISDao() {
		super(GER_LOCAIS.class);
	}

	public List<GER_LOCAIS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_LOCAIS a where a.ID = :id and a.INATIVO != 1");
		query.setParameter("id", id);
		List<GER_LOCAIS> data = query.getResultList();
		return data;

	}

	public List<GER_LOCAIS> getall() {

		Query query = entityManager.createQuery("Select a from GER_LOCAIS a where a.INATIVO != 1");
		List<GER_LOCAIS> data = query.getResultList();
		return data;

	}
	
	public List<GER_LOCAIS> getall2() {

		Query query = entityManager.createQuery("Select a,b from GER_LOCAIS a, GER_UTILIZADORES b where a.INATIVO != 1 and a.ID_UTZ = b.ID_UTILIZADOR");
		List<GER_LOCAIS> data = query.getResultList();
		return data;

	}
}
