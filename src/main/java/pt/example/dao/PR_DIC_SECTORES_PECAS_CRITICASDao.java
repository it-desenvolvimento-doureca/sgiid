package pt.example.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pt.example.entity.PR_DIC_SECTORES_PECAS_CRITICAS;
import pt.example.entity.PR_PECAS_CRITICAS;

public class PR_DIC_SECTORES_PECAS_CRITICASDao extends GenericDaoJpaImpl<PR_DIC_SECTORES_PECAS_CRITICAS, Integer>
		implements GenericDao<PR_DIC_SECTORES_PECAS_CRITICAS, Integer> {
	public PR_DIC_SECTORES_PECAS_CRITICASDao() {
		super(PR_DIC_SECTORES_PECAS_CRITICAS.class);
	}

	public List<PR_DIC_SECTORES_PECAS_CRITICAS> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from PR_DIC_SECTORES_PECAS_CRITICAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_DIC_SECTORES_PECAS_CRITICAS> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_SECTORES_PECAS_CRITICAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_SECTORES_PECAS_CRITICAS a where a.ATIVO = 1");
		List<PR_DIC_SECTORES_PECAS_CRITICAS> data = query.getResultList();
		return data;

	}	
	

	public List<PR_DIC_SECTORES_PECAS_CRITICAS> getall2(Integer userId) {
		
		 // Primeiro: verificar se o usuário é admin (em SQL nativo ou outro método)
	    boolean isAdmin = isAdminUser(userId); 

	    String jpql = "SELECT p FROM PR_DIC_SECTORES_PECAS_CRITICAS p " +
	                  "WHERE p.ATIVO = 1 " +
	                  (isAdmin ? "" : 
	                   "AND p.ID IN (" +
	                   "  SELECT u.ID_SECTORES_PECAS_CRITICAS " +
	                   "  FROM PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES u " +
	                   "  WHERE u.ID_UTILIZADOR = :userId" +
	                   ")");

	    TypedQuery<PR_DIC_SECTORES_PECAS_CRITICAS> query = entityManager.createQuery(jpql, PR_DIC_SECTORES_PECAS_CRITICAS.class);
	    
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
