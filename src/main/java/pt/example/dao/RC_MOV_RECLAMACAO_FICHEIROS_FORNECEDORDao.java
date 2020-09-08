package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR;

public class RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDORDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR, Integer> {
	public RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDORDao() {
		super(RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR.class);
	}

	public List<RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a,b from RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_RECLAMACAO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR> data = query.getResultList();
		return data;

	}
	
	public List<RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR> getbyid2(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR> data = query.getResultList();
		return data;

	}
		

	public List<RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR a ");
		List<RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR> data = query.getResultList();
		return data;

	}

}
