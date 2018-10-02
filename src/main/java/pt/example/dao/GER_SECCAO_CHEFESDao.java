package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_GRUPO_UTZ;
import pt.example.entity.GER_SECCAO_CHEFES;
import pt.example.entity.GER_UTILIZADORES;

public class GER_SECCAO_CHEFESDao extends GenericDaoJpaImpl<GER_SECCAO_CHEFES, Integer>
		implements GenericDao<GER_SECCAO_CHEFES, Integer> {
	public GER_SECCAO_CHEFESDao() {
		super(GER_SECCAO_CHEFES.class);
	}

	public List<GER_SECCAO_CHEFES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_SECCAO_CHEFES a where a.ID = :id ");
		query.setParameter("id", id);
		List<GER_SECCAO_CHEFES> data = query.getResultList();
		return data;

	}

	public List<GER_SECCAO_CHEFES> getall() {

		Query query = entityManager.createQuery("Select a from GER_SECCAO_CHEFES a ");
		List<GER_SECCAO_CHEFES> data = query.getResultList();
		return data;

	}

	public List<GER_SECCAO_CHEFES> getbyidgrupo(Integer id) {

		Query query = entityManager.createQuery(
				"Select a,b from GER_SECCAO_CHEFES a, GER_UTILIZADORES b where a.ID_SECCAO = :id  and a.ID_UTZ = b.ID_UTILIZADOR");
		query.setParameter("id", id);
		List<GER_SECCAO_CHEFES> data = query.getResultList();
		return data;

	}

	public List<GER_UTILIZADORES> getUtilizadores(Integer id) {
		Query query = entityManager.createQuery("select a from GER_UTILIZADORES a "
				+ "where  a.ID_UTILIZADOR not in (select b.ID_UTZ from GER_SECCAO_CHEFES b where b.ID_SECCAO = :id) "
				+ "and a.INATIVO != 1 ");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}

}
