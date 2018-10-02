package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVAS;

public class RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVASDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVAS, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVAS, Integer> {
	public RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVASDao() {
		super(RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVAS.class);
	}

	public List<RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVAS a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVAS> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVAS> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVAS a ");
		List<RC_MOV_RECLAMACAO_PLANO_ACCOES_CORRETIVAS> data = query.getResultList();
		return data;

	}

}
