package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.FIN_DIC_CLIENTES;

public class FIN_DIC_CLIENTESDao extends GenericDaoJpaImpl<FIN_DIC_CLIENTES, Integer>
		implements GenericDao<FIN_DIC_CLIENTES, Integer> {
	public FIN_DIC_CLIENTESDao() {
		super(FIN_DIC_CLIENTES.class);
	}

	public List<FIN_DIC_CLIENTES> getbyid(String id) {
		Query query = entityManager.createQuery("Select a from FIN_DIC_CLIENTES a where a.ID_CLIENTE = :id");
		query.setParameter("id", id);
		List<FIN_DIC_CLIENTES> data = query.getResultList();
		return data;
	}

	public List<FIN_DIC_CLIENTES> getall() {
		Query query = entityManager.createQuery("Select a from FIN_DIC_CLIENTES a ");
		List<FIN_DIC_CLIENTES> data = query.getResultList();
		return data;
	}

	

}
