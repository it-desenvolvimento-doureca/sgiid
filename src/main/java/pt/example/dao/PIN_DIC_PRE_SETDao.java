package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_PRE_SET;

public class PIN_DIC_PRE_SETDao extends GenericDaoJpaImpl<PIN_DIC_PRE_SET, Integer>
		implements GenericDao<PIN_DIC_PRE_SET, Integer> {
	public PIN_DIC_PRE_SETDao() {
		super(PIN_DIC_PRE_SET.class);
	}

	public List<PIN_DIC_PRE_SET> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_PRE_SET a where a.ID = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_PRE_SET> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_PRE_SET> getall() {

		Query query = entityManager.createQuery("Select a,(select b.NOME from PIN_DIC_TIPO_ACABAMENTO b where b.ID = a.ID_TIPO_ACABAMENTO) as TIPO_ACABAMENTO from PIN_DIC_PRE_SET a where a.ATIVO = 1 ");
		List<PIN_DIC_PRE_SET> data = query.getResultList();
		return data;

	}

}
