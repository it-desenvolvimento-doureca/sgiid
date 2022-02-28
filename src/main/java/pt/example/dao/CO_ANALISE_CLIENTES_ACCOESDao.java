package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.CO_ANALISE_CLIENTES_ACCOES;

public class CO_ANALISE_CLIENTES_ACCOESDao extends GenericDaoJpaImpl<CO_ANALISE_CLIENTES_ACCOES, Integer>
		implements GenericDao<CO_ANALISE_CLIENTES_ACCOES, Integer> {
	public CO_ANALISE_CLIENTES_ACCOESDao() {
		super(CO_ANALISE_CLIENTES_ACCOES.class);
	}

	public List<CO_ANALISE_CLIENTES_ACCOES> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from CO_ANALISE_CLIENTES_ACCOES a where a.ID = :id");
		query.setParameter("id", id);
		List<CO_ANALISE_CLIENTES_ACCOES> data = query.getResultList();
		return data;
	}

	public List<CO_ANALISE_CLIENTES_ACCOES> getall() {
		Query query = entityManager.createQuery("Select a from CO_ANALISE_CLIENTES_ACCOES a ");
		List<CO_ANALISE_CLIENTES_ACCOES> data = query.getResultList();
		return data;
	}

}
