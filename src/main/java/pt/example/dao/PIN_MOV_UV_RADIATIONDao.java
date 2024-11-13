package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_UV_RADIATION;

public class PIN_MOV_UV_RADIATIONDao extends GenericDaoJpaImpl<PIN_MOV_UV_RADIATION, Integer>
		implements GenericDao<PIN_MOV_UV_RADIATION, Integer> {
	public PIN_MOV_UV_RADIATIONDao() {
		super(PIN_MOV_UV_RADIATION.class);
	}

	public List<PIN_MOV_UV_RADIATION> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_UV_RADIATION a where a.ID_RECEITA = :id and a.VERSAO =:versao order by UV_RADIATION");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<PIN_MOV_UV_RADIATION> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_UV_RADIATION> getall() {
		Query query = entityManager.createQuery("Select a from PIN_MOV_UV_RADIATION a ");
		List<PIN_MOV_UV_RADIATION> data = query.getResultList();
		return data;

	}

}
