package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_EQUIPA;

public class RC_MOV_RECLAMACAO_EQUIPADao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_EQUIPA, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_EQUIPA, Integer> {
	public RC_MOV_RECLAMACAO_EQUIPADao() {
		super(RC_MOV_RECLAMACAO_EQUIPA.class);
	}

	public List<RC_MOV_RECLAMACAO_EQUIPA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_EQUIPA a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_EQUIPA> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_EQUIPA> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_EQUIPA a ");
		List<RC_MOV_RECLAMACAO_EQUIPA> data = query.getResultList();
		return data;

	}

}
