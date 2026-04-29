package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_PARAM_PLANO_PINTURA;

public class PIN_DIC_PARAM_PLANO_PINTURADao extends GenericDaoJpaImpl<PIN_DIC_PARAM_PLANO_PINTURA, Integer>
		implements GenericDao<PIN_DIC_PARAM_PLANO_PINTURA, Integer> {

	public PIN_DIC_PARAM_PLANO_PINTURADao() {
		super(PIN_DIC_PARAM_PLANO_PINTURA.class);
	}

	public List<PIN_DIC_PARAM_PLANO_PINTURA> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from PIN_DIC_PARAM_PLANO_PINTURA a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<PIN_DIC_PARAM_PLANO_PINTURA> getall() {
		Query query = entityManager.createQuery("Select a from PIN_DIC_PARAM_PLANO_PINTURA a where a.ATIVO = true");
		return query.getResultList();
	}
}
