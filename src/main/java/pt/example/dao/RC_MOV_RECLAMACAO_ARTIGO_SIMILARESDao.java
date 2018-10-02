package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_ARTIGO_SIMILARES;

public class RC_MOV_RECLAMACAO_ARTIGO_SIMILARESDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_ARTIGO_SIMILARES, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_ARTIGO_SIMILARES, Integer> {
	public RC_MOV_RECLAMACAO_ARTIGO_SIMILARESDao() {
		super(RC_MOV_RECLAMACAO_ARTIGO_SIMILARES.class);
	}

	public List<RC_MOV_RECLAMACAO_ARTIGO_SIMILARES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_ARTIGO_SIMILARES a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_ARTIGO_SIMILARES> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_ARTIGO_SIMILARES> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_ARTIGO_SIMILARES a ");
		List<RC_MOV_RECLAMACAO_ARTIGO_SIMILARES> data = query.getResultList();
		return data;

	}

}
