package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_UTILIZADORES;

public class GER_UTILIZADORESDao extends GenericDaoJpaImpl<GER_UTILIZADORES, Integer>
		implements GenericDao<GER_UTILIZADORES, Integer> {
	public GER_UTILIZADORESDao() {
		super(GER_UTILIZADORES.class);
	}

	public List<GER_UTILIZADORES> getbylogin(String login) {
		Query query = entityManager.createQuery("Select a from GER_UTILIZADORES a where a.LOGIN = :login and  a.INATIVO != 1 ");
		query.setParameter("login", login);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}
	
	public List<GER_UTILIZADORES> getbycode(String code_user) {
		Query query = entityManager.createQuery("Select a from GER_UTILIZADORES a where a.COD_UTZ = :code and  a.INATIVO != 1 ");
		query.setParameter("code", code_user);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}
	
	
	public List<GER_UTILIZADORES> getAll() {
		Query query = entityManager.createQuery("Select a from GER_UTILIZADORES a where a.INATIVO != 1 ");
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}
	
	public List<GER_UTILIZADORES> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from GER_UTILIZADORES a where a.ID_UTILIZADOR = :id and  a.INATIVO != 1");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}
	
	public List<GER_UTILIZADORES> verifica_login(Integer id, String login) {
		Query query = entityManager.createQuery(
				"Select a from GER_UTILIZADORES a where a.LOGIN = :login and a.ID_UTILIZADOR != :id and  a.INATIVO != 1 ");
		query.setParameter("id", id);
		query.setParameter("login", login);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}
	
	public List<GER_UTILIZADORES> verifica_code(Integer id, String code) {
		Query query = entityManager.createQuery(
				"Select a from GER_UTILIZADORES a where a.COD_UTZ = :code and a.ID_UTILIZADOR != :id and  a.INATIVO != 1 ");
		query.setParameter("id", id);
		query.setParameter("code", code);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}

}
