package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_PLANO_DIARIO_PINTURA;

public class PIN_PLANO_DIARIO_PINTURADao extends GenericDaoJpaImpl<PIN_PLANO_DIARIO_PINTURA, Integer>
		implements GenericDao<PIN_PLANO_DIARIO_PINTURA, Integer> {
	public PIN_PLANO_DIARIO_PINTURADao() {
		super(PIN_PLANO_DIARIO_PINTURA.class);
	}

	public List<PIN_PLANO_DIARIO_PINTURA> getbyid(Integer id) {
		Query query = entityManager
				.createQuery("Select a from PIN_PLANO_DIARIO_PINTURA a where a.ID_PLANO_DIARIO_PINTURA =:id");
		query.setParameter("id", id);
		List<PIN_PLANO_DIARIO_PINTURA> data = query.getResultList();
		return data;
	}

	public List<PIN_PLANO_DIARIO_PINTURA> getall() {
		Query query = entityManager.createQuery(
				"Select a from PIN_PLANO_DIARIO_PINTURA a where a.ATIVO = true ORDER BY a.ANO desc, a.SEMANA desc");
		List<PIN_PLANO_DIARIO_PINTURA> data = query.getResultList();
		return data;
	}
}
