package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_AVALIACAO_DESEMPENHO;

public class RH_AVALIACAO_DESEMPENHODao extends GenericDaoJpaImpl<RH_AVALIACAO_DESEMPENHO, Integer>
		implements GenericDao<RH_AVALIACAO_DESEMPENHO, Integer> {

	public RH_AVALIACAO_DESEMPENHODao() {
		super(RH_AVALIACAO_DESEMPENHO.class);
	}

	public List<RH_AVALIACAO_DESEMPENHO> getByid(Integer id) {
		Query query = entityManager.createQuery("SELECT p FROM RH_AVALIACAO_DESEMPENHO p WHERE p.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<RH_AVALIACAO_DESEMPENHO> getAll() {
		Query query = entityManager.createQuery("SELECT p FROM RH_AVALIACAO_DESEMPENHO p where p.ATIVO = 1");
		return query.getResultList();
	}
	
	public boolean existsByAno(Integer ano, Integer idExclusao) {
	    Query query = entityManager.createQuery(
	        "SELECT COUNT(p) FROM RH_AVALIACAO_DESEMPENHO p WHERE  p.ANO = :ano and p.ID <> :idExclusao and p.ATIVO = 1" 
	    ); 
	    
	    query.setParameter("ano", ano);
	    query.setParameter("idExclusao", idExclusao);
	    Long count = (Long) query.getSingleResult();
	    return count > 0;
	}
}