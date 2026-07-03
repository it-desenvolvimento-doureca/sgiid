package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_RECEITAS_PRESSOES;

public class PIN_MOV_RECEITAS_PRESSOESDao extends GenericDaoJpaImpl<PIN_MOV_RECEITAS_PRESSOES, Integer>
		implements GenericDao<PIN_MOV_RECEITAS_PRESSOES, Integer> {
	public PIN_MOV_RECEITAS_PRESSOESDao() {
		super(PIN_MOV_RECEITAS_PRESSOES.class);
	}

	public List<PIN_MOV_RECEITAS_PRESSOES> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_RECEITAS_PRESSOES a where a.ID_RECEITA = :id and a.VERSAO = :versao order by a.ID");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<PIN_MOV_RECEITAS_PRESSOES> data = query.getResultList();
		return data;

	}

}
