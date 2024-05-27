package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_RECEITAS_REFERENCIAS;

public class PIN_MOV_RECEITAS_REFERENCIASDao extends GenericDaoJpaImpl<PIN_MOV_RECEITAS_REFERENCIAS, Integer>
		implements GenericDao<PIN_MOV_RECEITAS_REFERENCIAS, Integer> {
	public PIN_MOV_RECEITAS_REFERENCIASDao() {
		super(PIN_MOV_RECEITAS_REFERENCIAS.class);
	}

	public List<PIN_MOV_RECEITAS_REFERENCIAS> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_RECEITAS_REFERENCIAS a where a.ID_RECEITA = :id and a.VERSAO =:versao");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<PIN_MOV_RECEITAS_REFERENCIAS> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_RECEITAS_REFERENCIAS> getall() {
		Query query = entityManager.createQuery("Select a from PIN_MOV_RECEITAS_REFERENCIAS a ");
		List<PIN_MOV_RECEITAS_REFERENCIAS> data = query.getResultList();
		return data;

	}

}
