package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_GRUPO_UTZ;
import pt.example.entity.GER_PERFIL_CAB;
import pt.example.entity.GER_UTILIZADORES;

public class GER_GRUPO_UTZDao extends GenericDaoJpaImpl<GER_GRUPO_UTZ, Integer>
		implements GenericDao<GER_GRUPO_UTZ, Integer> {
	public GER_GRUPO_UTZDao() {
		super(GER_GRUPO_UTZ.class);
	}

	public List<GER_GRUPO_UTZ> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_GRUPO_UTZ a where a.ID = :id ");
		query.setParameter("id", id);
		List<GER_GRUPO_UTZ> data = query.getResultList();
		return data;

	}
	
	public List<GER_GRUPO_UTZ> getbyidgrupo(Integer id) {

		Query query = entityManager.createQuery("Select a,b from GER_GRUPO_UTZ a, GER_UTILIZADORES b where a.ID_GRUPO = :id  and a.ID_UTZ = b.ID_UTILIZADOR");
		query.setParameter("id", id);
		List<GER_GRUPO_UTZ> data = query.getResultList();
		return data;

	}
	
	public List<GER_UTILIZADORES> getUtilizadores(Integer id) {
		Query query = entityManager.createQuery("select a from GER_UTILIZADORES a "
				+ "where  a.ID_UTILIZADOR not in (select b.ID_UTZ from GER_GRUPO_UTZ b where b.ID_GRUPO = :id) "
				+ "and a.INATIVO != 1 ");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}

	public List<GER_GRUPO_UTZ> getall() {

		Query query = entityManager.createQuery("Select a from GER_GRUPO_UTZ a ");
		List<GER_GRUPO_UTZ> data = query.getResultList();
		return data;

	}

}
