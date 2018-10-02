package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVAS;

public class RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVASDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVAS, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVAS, Integer> {
	public RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVASDao() {
		super(RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVAS.class);
	}

	public List<RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVAS a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVAS> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVAS> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVAS a ");
		List<RC_MOV_RECLAMACAO_PLANO_ACCOES_PREVENTIVAS> data = query.getResultList();
		return data;

	}

}
