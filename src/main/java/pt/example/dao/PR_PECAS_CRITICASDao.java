package pt.example.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pt.example.entity.PR_PECAS_CRITICAS;

public class PR_PECAS_CRITICASDao extends GenericDaoJpaImpl<PR_PECAS_CRITICAS, Integer>
		implements GenericDao<PR_PECAS_CRITICAS, Integer> {
	public PR_PECAS_CRITICASDao() {
		super(PR_PECAS_CRITICAS.class);
	}

	public List<PR_PECAS_CRITICAS> getbyid(Integer id, Integer userId) {
	    boolean isAdmin = isAdminUser(userId); // método auxiliar abaixo

	    String jpql = "SELECT p FROM PR_PECAS_CRITICAS p " +
	                  "WHERE p.ATIVO = 1 AND p.ID = :id " +
	                  (isAdmin ? "" : 
	                   "AND p.ID_SECTOR_PECAS_CRITICAS.ID IN (" +
	                   "  SELECT u.ID_SECTORES_PECAS_CRITICAS " +
	                   "  FROM PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES u " +
	                   "  WHERE u.ID_UTILIZADOR = :userId" +
	                   ")");

	    TypedQuery<PR_PECAS_CRITICAS> query = entityManager.createQuery(jpql, PR_PECAS_CRITICAS.class);
	    query.setParameter("id", id);
	    if (!isAdmin) {
	        query.setParameter("userId", userId);
	    }

	    return query.getResultList();
	}

	public List<PR_PECAS_CRITICAS> getall(Integer userId) {
	    // Primeiro: verificar se o usuário é admin (em SQL nativo ou outro método)
	    boolean isAdmin = isAdminUser(userId); // Crie esse método com nativeQuery ou repository

	    String jpql = "SELECT p FROM PR_PECAS_CRITICAS p " +
	                  "WHERE p.ATIVO = 1 " +
	                  (isAdmin ? "" : 
	                   "AND p.ID_SECTOR_PECAS_CRITICAS.ID IN (" +
	                   "  SELECT u.ID_SECTORES_PECAS_CRITICAS " +
	                   "  FROM PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES u " +
	                   "  WHERE u.ID_UTILIZADOR = :userId" +
	                   ")");

	    TypedQuery<PR_PECAS_CRITICAS> query = entityManager.createQuery(jpql, PR_PECAS_CRITICAS.class);
	    
	    if (!isAdmin) {
	        query.setParameter("userId", userId);
	    }

	    return query.getResultList();
	}

	
	public boolean isAdminUser(Integer userId) {
	    String sql = "SELECT ADMIN FROM SGIID.dbo.GER_UTILIZADORES WHERE ID_UTILIZADOR = :userId";
	    Object result = entityManager.createNativeQuery(sql)
	        .setParameter("userId", userId)
	        .getSingleResult();
	    return result != null && Boolean.TRUE.equals(result);
	}
}
