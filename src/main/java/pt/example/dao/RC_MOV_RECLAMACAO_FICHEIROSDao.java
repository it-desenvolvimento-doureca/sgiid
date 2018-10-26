package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_FICHEIROS;

public class RC_MOV_RECLAMACAO_FICHEIROSDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_FICHEIROS, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_FICHEIROS, Integer> {
	public RC_MOV_RECLAMACAO_FICHEIROSDao() {
		super(RC_MOV_RECLAMACAO_FICHEIROS.class);
	}

	public List<RC_MOV_RECLAMACAO_FICHEIROS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a,b from RC_MOV_RECLAMACAO_FICHEIROS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_RECLAMACAO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_FICHEIROS> data = query.getResultList();
		return data;

	}
	
	public List<RC_MOV_RECLAMACAO_FICHEIROS> getbyid2(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_FICHEIROS a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_FICHEIROS> data = query.getResultList();
		return data;

	}
	
	
	public List<RC_MOV_RECLAMACAO_FICHEIROS> getbyidtarefa(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_FICHEIROS a where a.ID_TAREFA = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_FICHEIROS> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_FICHEIROS> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_FICHEIROS a ");
		List<RC_MOV_RECLAMACAO_FICHEIROS> data = query.getResultList();
		return data;

	}

}
