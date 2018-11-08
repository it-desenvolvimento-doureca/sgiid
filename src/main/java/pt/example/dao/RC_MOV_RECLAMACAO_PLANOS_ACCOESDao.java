package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_PLANOS_ACCOES;

public class RC_MOV_RECLAMACAO_PLANOS_ACCOESDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_PLANOS_ACCOES, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_PLANOS_ACCOES, Integer> {
	public RC_MOV_RECLAMACAO_PLANOS_ACCOESDao() {
		super(RC_MOV_RECLAMACAO_PLANOS_ACCOES.class);
	}

	public List<RC_MOV_RECLAMACAO_PLANOS_ACCOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_PLANOS_ACCOES a where a.ID_RECLAMACAO = :id order by ordem,id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_PLANOS_ACCOES> getbyidplano(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_PLANOS_ACCOES a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}
	
	public List<RC_MOV_RECLAMACAO_PLANOS_ACCOES> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_PLANOS_ACCOES a ");
		List<RC_MOV_RECLAMACAO_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}

}
