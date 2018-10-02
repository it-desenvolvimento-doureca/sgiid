package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS;

public class RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATASDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS, Integer> {
	public RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATASDao() {
		super(RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS.class);
	}

	public List<RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS a ");
		List<RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS> data = query.getResultList();
		return data;

	}

}
