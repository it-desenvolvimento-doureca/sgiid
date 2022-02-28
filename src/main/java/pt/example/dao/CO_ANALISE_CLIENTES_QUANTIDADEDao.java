package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.CO_ANALISE_CLIENTES_QUANTIDADE;

public class CO_ANALISE_CLIENTES_QUANTIDADEDao extends GenericDaoJpaImpl<CO_ANALISE_CLIENTES_QUANTIDADE, Integer>
		implements GenericDao<CO_ANALISE_CLIENTES_QUANTIDADE, Integer> {
	public CO_ANALISE_CLIENTES_QUANTIDADEDao() {
		super(CO_ANALISE_CLIENTES_QUANTIDADE.class);
	}

	public List<CO_ANALISE_CLIENTES_QUANTIDADE> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from CO_ANALISE_CLIENTES_QUANTIDADE a where a.ID = :id");
		query.setParameter("id", id);
		List<CO_ANALISE_CLIENTES_QUANTIDADE> data = query.getResultList();
		return data;
	}

	public List<CO_ANALISE_CLIENTES_QUANTIDADE> getall() {
		Query query = entityManager.createQuery("Select a from CO_ANALISE_CLIENTES_QUANTIDADE a ");
		List<CO_ANALISE_CLIENTES_QUANTIDADE> data = query.getResultList();
		return data;
	}

}
