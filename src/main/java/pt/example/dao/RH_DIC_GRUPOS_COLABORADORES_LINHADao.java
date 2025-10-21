package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_GRUPOS_COLABORADORES_LINHA;

public class RH_DIC_GRUPOS_COLABORADORES_LINHADao extends GenericDaoJpaImpl<RH_DIC_GRUPOS_COLABORADORES_LINHA, Integer>
		implements GenericDao<RH_DIC_GRUPOS_COLABORADORES_LINHA, Integer> {

	public RH_DIC_GRUPOS_COLABORADORES_LINHADao() {
		super(RH_DIC_GRUPOS_COLABORADORES_LINHA.class);
	}

	public List<RH_DIC_GRUPOS_COLABORADORES_LINHA> getByGrupoId(Integer grupoId) {
		Query query = entityManager.createQuery(
				"SELECT l FROM RH_DIC_GRUPOS_COLABORADORES_LINHA l WHERE l.grupoColaboradores.ID = :grupoId");
		query.setParameter("grupoId", grupoId);
		return query.getResultList();
	}

	public List<RH_DIC_GRUPOS_COLABORADORES_LINHA> getAll() {
		Query query = entityManager.createQuery("SELECT l FROM RH_DIC_GRUPOS_COLABORADORES_LINHA l");
		return query.getResultList();
	}
	
	
	public List<Object[]> getColaboradoresAll(Integer idGrupo) {
	    Query query = entityManager.createNativeQuery(
	        "DECLARE @ID INT = " + idGrupo + " " +
	        "SELECT RIGHT(CONCAT('00000', COD_FUNC_ORIGEM), 5) AS COD_FUNC, * " +
	        "FROM RH_FUNCIONARIOS f " +
	        "WHERE f.COD_FUNC_ORIGEM NOT IN ( " +
	        "    SELECT l.COD_FUNC FROM RH_DIC_GRUPOS_COLABORADORES_LINHA l WHERE l.ID_GRUPO_COLABORADORES = @ID " +
	        ") " +
	        "ORDER BY f.NOME"
	    );
	    return query.getResultList();
	}

	public List<Object[]> getColaboradoresGrupos(Integer idGrupo) {
	    Query query = entityManager.createNativeQuery(
	        "DECLARE @ID INT = " + idGrupo + " " +
	        "SELECT RIGHT(CONCAT('00000', f.COD_FUNC_ORIGEM), 5) AS COD_FUNC, f.* " +
	        "FROM RH_DIC_GRUPOS_COLABORADORES_LINHA l " +
	        "INNER JOIN RH_FUNCIONARIOS f ON l.COD_FUNC = RIGHT(CONCAT('00000', f.COD_FUNC_ORIGEM), 5) " +
	        "WHERE l.ID_GRUPO_COLABORADORES = @ID " +
	        "ORDER BY f.NOME"
	    );
	    return query.getResultList();
	}

	
}