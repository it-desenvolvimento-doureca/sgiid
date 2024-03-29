package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_GRUPO;

public class GER_GRUPODao extends GenericDaoJpaImpl<GER_GRUPO, Integer> implements GenericDao<GER_GRUPO, Integer> {
	public GER_GRUPODao() {
		super(GER_GRUPO.class);
	}

	public List<GER_GRUPO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_GRUPO a where a.ID = :id and a.INATIVO != 1");
		query.setParameter("id", id);
		List<GER_GRUPO> data = query.getResultList();
		return data;

	}

	public List<GER_GRUPO> getbyidbyuser(Integer id) {

		Query query = entityManager
				.createNativeQuery("select c.* from GER_GRUPO a inner join GER_GRUPO_UTZ b on a.ID = b.ID_GRUPO "
						+ "inner join GER_UTILIZADORES c on b.ID_UTZ = c.ID_UTILIZADOR " + "where a.ID = " + id);

		List<GER_GRUPO> data = query.getResultList();
		return data;

	}

	public List<GER_GRUPO> getall() {

		Query query = entityManager.createQuery("Select a from GER_GRUPO a where a.INATIVO != 1");
		List<GER_GRUPO> data = query.getResultList();
		return data;

	}

}
