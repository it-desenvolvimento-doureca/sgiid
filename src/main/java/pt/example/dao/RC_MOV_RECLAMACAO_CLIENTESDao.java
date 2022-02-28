package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_CLIENTES;

public class RC_MOV_RECLAMACAO_CLIENTESDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_CLIENTES, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_CLIENTES, Integer> {
	public RC_MOV_RECLAMACAO_CLIENTESDao() {
		super(RC_MOV_RECLAMACAO_CLIENTES.class);
	}

	public List<RC_MOV_RECLAMACAO_CLIENTES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_CLIENTES a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_CLIENTES> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_CLIENTES> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_CLIENTES a ");
		List<RC_MOV_RECLAMACAO_CLIENTES> data = query.getResultList();
		return data;

	}

}
