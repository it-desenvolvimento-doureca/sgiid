package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_AVALIACAO_CATEGORIA;

public class RH_DIC_AVALIACAO_CATEGORIADao extends GenericDaoJpaImpl<RH_DIC_AVALIACAO_CATEGORIA, Integer>
		implements GenericDao<RH_DIC_AVALIACAO_CATEGORIA, Integer> {

	public RH_DIC_AVALIACAO_CATEGORIADao() {
		super(RH_DIC_AVALIACAO_CATEGORIA.class);
	}

	public List<RH_DIC_AVALIACAO_CATEGORIA> getById(Integer grupoId) {
		Query query = entityManager.createQuery("SELECT p FROM RH_DIC_AVALIACAO_CATEGORIA p WHERE p.grupo.ID = :grupoId");
		query.setParameter("grupoId", grupoId);
		return query.getResultList();
	}

	public List<RH_DIC_AVALIACAO_CATEGORIA> getAll() {
		Query query = entityManager.createQuery("SELECT p FROM RH_DIC_AVALIACAO_CATEGORIA p where p.ATIVO = 1");
		return query.getResultList();
	}
	
	public boolean existsByGrupoAndAno(Integer idGrupo, Integer ano, Integer idExclusao) {
	    Query query = entityManager.createQuery(
	        "SELECT COUNT(p) FROM RH_DIC_AVALIACAO_CATEGORIA p WHERE p.grupo.ID = :idGrupo AND p.ANO = :ano and p.ID <> :idExclusao" 
	    );
	    query.setParameter("idGrupo", idGrupo);
	    query.setParameter("ano", ano);
	    query.setParameter("idExclusao", idExclusao);
	    Long count = (Long) query.getSingleResult();
	    return count > 0;
	}
}