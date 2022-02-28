package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.CO_ANALISE_CLIENTES;

public class CO_ANALISE_CLIENTESDao extends GenericDaoJpaImpl<CO_ANALISE_CLIENTES, Integer>
		implements GenericDao<CO_ANALISE_CLIENTES, Integer> {
	public CO_ANALISE_CLIENTESDao() {
		super(CO_ANALISE_CLIENTES.class);
	}

	public List<CO_ANALISE_CLIENTES> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from CO_ANALISE_CLIENTES a where a.ID = :id");
		query.setParameter("id", id);
		List<CO_ANALISE_CLIENTES> data = query.getResultList();
		return data;
	}

	public List<CO_ANALISE_CLIENTES> getall() {
		Query query = entityManager.createQuery("Select a from CO_ANALISE_CLIENTES a ");
		List<CO_ANALISE_CLIENTES> data = query.getResultList();
		return data;
	}

}
