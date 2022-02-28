package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.CO_ANALISE_CLIENTES_OBSERVACOES;

public class CO_ANALISE_CLIENTES_OBSERVACOESDao extends GenericDaoJpaImpl<CO_ANALISE_CLIENTES_OBSERVACOES, Integer>
		implements GenericDao<CO_ANALISE_CLIENTES_OBSERVACOES, Integer> {
	public CO_ANALISE_CLIENTES_OBSERVACOESDao() {
		super(CO_ANALISE_CLIENTES_OBSERVACOES.class);
	}

	public List<CO_ANALISE_CLIENTES_OBSERVACOES> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from CO_ANALISE_CLIENTES_OBSERVACOES a where a.ID = :id");
		query.setParameter("id", id);
		List<CO_ANALISE_CLIENTES_OBSERVACOES> data = query.getResultList();
		return data;
	}

	public List<CO_ANALISE_CLIENTES_OBSERVACOES> getall() {
		Query query = entityManager.createQuery("Select a from CO_ANALISE_CLIENTES_OBSERVACOES a ");
		List<CO_ANALISE_CLIENTES_OBSERVACOES> data = query.getResultList();
		return data;
	}

}
