package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_TIPO_ACABAMENTO;

public class PIN_DIC_TIPO_ACABAMENTODao extends GenericDaoJpaImpl<PIN_DIC_TIPO_ACABAMENTO, Integer>
		implements GenericDao<PIN_DIC_TIPO_ACABAMENTO, Integer> {
	public PIN_DIC_TIPO_ACABAMENTODao() {
		super(PIN_DIC_TIPO_ACABAMENTO.class);
	}

	public List<PIN_DIC_TIPO_ACABAMENTO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_TIPO_ACABAMENTO a where a.ID = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_TIPO_ACABAMENTO> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_TIPO_ACABAMENTO> getall() {

		Query query = entityManager.createQuery("Select a from PIN_DIC_TIPO_ACABAMENTO a where a.ATIVO = 1 ");
		List<PIN_DIC_TIPO_ACABAMENTO> data = query.getResultList();
		return data;

	}

}
