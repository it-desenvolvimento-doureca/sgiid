package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_RECEITAS_LINHAS;

public class PIN_MOV_RECEITAS_LINHASDao extends GenericDaoJpaImpl<PIN_MOV_RECEITAS_LINHAS, Integer>
		implements GenericDao<PIN_MOV_RECEITAS_LINHAS, Integer> {
	public PIN_MOV_RECEITAS_LINHASDao() {
		super(PIN_MOV_RECEITAS_LINHAS.class);
	}

	public List<PIN_MOV_RECEITAS_LINHAS> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_RECEITAS_LINHAS a where a.ID_RECEITA = :id ");
		query.setParameter("id", id);
		List<PIN_MOV_RECEITAS_LINHAS> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_RECEITAS_LINHAS> getall() {
		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_RECEITAS_LINHAS a ");
		List<PIN_MOV_RECEITAS_LINHAS> data = query.getResultList();
		return data;

	}

	

}
