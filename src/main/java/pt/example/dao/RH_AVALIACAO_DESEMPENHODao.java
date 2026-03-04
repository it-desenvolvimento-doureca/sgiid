package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_AVALIACAO_DESEMPENHO;

public class RH_AVALIACAO_DESEMPENHODao extends GenericDaoJpaImpl<RH_AVALIACAO_DESEMPENHO, Integer>
		implements GenericDao<RH_AVALIACAO_DESEMPENHO, Integer> {

	public RH_AVALIACAO_DESEMPENHODao() {
		super(RH_AVALIACAO_DESEMPENHO.class);
	}

	public List<RH_AVALIACAO_DESEMPENHO> getByid(Integer id,String tipo) {
		Query query = entityManager.createQuery("SELECT p FROM RH_AVALIACAO_DESEMPENHO p WHERE p.ID = :id and p.TIPO = :tipo");
		query.setParameter("id", id);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}

	public List<RH_AVALIACAO_DESEMPENHO> getAll(String tipo) {
		Query query = entityManager.createQuery("SELECT p FROM RH_AVALIACAO_DESEMPENHO p where p.ATIVO = 1 and p.TIPO = :tipo");
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}
	
	public boolean existsByAno(Integer ano, Integer idExclusao,String tipo) {
	    Query query = entityManager.createQuery(
	        "SELECT COUNT(p) FROM RH_AVALIACAO_DESEMPENHO p WHERE  p.ANO = :ano and p.ID <> :idExclusao and p.ATIVO = 1 and p.TIPO = :tipo" 
	    ); 
	    
	    query.setParameter("ano", ano);
	    query.setParameter("idExclusao", idExclusao);
	    query.setParameter("tipo", tipo);
	    Long count = (Long) query.getSingleResult();
	    return count > 0;
	}
}