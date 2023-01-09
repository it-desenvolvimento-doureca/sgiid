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
		Query query = entityManager
				.createQuery("Select a from GER_UTILIZADORES a where (a.LOGIN = :login or a.USER_WINDOWS = :login) and  a.INATIVO != 1 and  a.BLOQUEADO != 1 ");
		query.setParameter("login", login);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}

	public List<GER_UTILIZADORES> getbycode(String code_user) {
		Query query = entityManager
				.createQuery("Select a from GER_UTILIZADORES a where a.COD_UTZ = :code and  a.INATIVO != 1 and  a.BLOQUEADO != 1 ");
		query.setParameter("code", code_user);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}

	public List<GER_UTILIZADORES> getbyLoginLDAP(String code_user) {
		Query query = entityManager
				.createQuery("Select a from GER_UTILIZADORES a where a.USER_WINDOWS = :code and  a.INATIVO != 1 and  a.BLOQUEADO != 1 ");
		query.setParameter("code", code_user);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}

	public List<GER_UTILIZADORES> getAll() {
		Query query = entityManager.createQuery("Select a from GER_UTILIZADORES a where a.INATIVO != 1 ");
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}
	
	public List<GER_UTILIZADORES> getAllORDERNAME() {
		Query query = entityManager.createQuery("Select a from GER_UTILIZADORES a where a.INATIVO != 1 order by a.NOME_UTILIZADOR ");
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}
	
	public List<GER_UTILIZADORES> getAllInativo() {
		Query query = entityManager.createQuery("Select a from GER_UTILIZADORES a where a.INATIVO = 1 ");
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}
	
	public List<GER_UTILIZADORES> getAllSECTOR() {
		Query query = entityManager.createNativeQuery("Select a.NOME_UTILIZADOR,a.ID_UTILIZADOR,a.EMAIL,c.DES_SECTOR from GER_UTILIZADORES a "
				+ "left join RH_FUNCIONARIOS b ON b.COD_FUNC_ORIGEM = a.COD_UTZ left join RH_SECTORES c on b.COD_SECTOR = c.COD_SECTOR where a.INATIVO != 1 ");
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}

	public List<GER_UTILIZADORES> getbyid(Integer id) {
		Query query = entityManager
				.createQuery("Select a from GER_UTILIZADORES a where a.ID_UTILIZADOR = :id and  a.INATIVO != 1");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}
	
	public List<GER_UTILIZADORES> getDadosUtilizador(Integer id) {
		Query query = entityManager
				.createNativeQuery("Select top 1 c.DES_SECTOR,d.ID_DEPARTAMENTO from GER_UTILIZADORES a "
						+ "left join RH_FUNCIONARIOS b on CASE WHEN a.COD_UTZ = '9889' THEN 0 ELSE a.COD_UTZ END = b.COD_FUNC_ORIGEM "
						+ "left join RH_SECTORES c on b.COD_SECTOR = c.COD_SECTOR "
						+ "left join GER_DEPARTAMENTOS_SECTORES d on c.COD_SECTOR = d.COD_SECTOR "
						+ "where a.ID_UTILIZADOR = :id and  a.INATIVO != 1");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;
	}
	

	public List<GER_UTILIZADORES> getDadosUtilizadorAll() {
		Query query = entityManager
				.createNativeQuery("Select a.NOME_UTILIZADOR,c.DES_SECTOR,d.ID_DEPARTAMENTO,a.ID_UTILIZADOR,a.EMAIL from GER_UTILIZADORES a "
						+ "left join RH_FUNCIONARIOS b on CASE WHEN a.COD_UTZ = '9889' THEN 0 ELSE a.COD_UTZ END = b.COD_FUNC_ORIGEM "
						+ "left join RH_SECTORES c on b.COD_SECTOR = c.COD_SECTOR "
						+ "left join GER_DEPARTAMENTOS_SECTORES d on c.COD_SECTOR = d.COD_SECTOR "
						+ "where a.INATIVO != 1 order by NOME_UTILIZADOR");
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}
	
	
	public List<GER_UTILIZADORES> verifica_login(Integer id, String login) {
		Query query = entityManager.createQuery(
				"Select a from GER_UTILIZADORES a where a.LOGIN = :login and a.ID_UTILIZADOR != :id and  a.INATIVO != 1 and  a.BLOQUEADO != 1 ");
		query.setParameter("id", id);
		query.setParameter("login", login);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}

	public List<GER_UTILIZADORES> verifica_code(Integer id, String code) {
		Query query = entityManager.createQuery(
				"Select a from GER_UTILIZADORES a where a.COD_UTZ = :code and a.ID_UTILIZADOR != :id and  a.INATIVO != 1 and  a.BLOQUEADO != 1 ");
		query.setParameter("id", id);
		query.setParameter("code", code);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}
	
	public List<GER_UTILIZADORES> verifica_LDAP(Integer id, String code) {
		Query query = entityManager.createQuery(
				"Select a from GER_UTILIZADORES a where a.USER_WINDOWS = :code and a.ID_UTILIZADOR != :id and  a.INATIVO != 1 and  a.BLOQUEADO != 1 ");
		query.setParameter("id", id);
		query.setParameter("code", code);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}
}
