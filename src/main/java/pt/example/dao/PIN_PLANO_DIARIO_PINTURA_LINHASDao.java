package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_PLANO_DIARIO_PINTURA_LINHAS;

public class PIN_PLANO_DIARIO_PINTURA_LINHASDao
		extends GenericDaoJpaImpl<PIN_PLANO_DIARIO_PINTURA_LINHAS, Integer>
		implements GenericDao<PIN_PLANO_DIARIO_PINTURA_LINHAS, Integer> {
	public PIN_PLANO_DIARIO_PINTURA_LINHASDao() {
		super(PIN_PLANO_DIARIO_PINTURA_LINHAS.class);
	}

	public List<PIN_PLANO_DIARIO_PINTURA_LINHAS> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from PIN_PLANO_DIARIO_PINTURA_LINHAS a where a.ID_PLANO_DIARIO_PINTURA_LINHA =:id");
		query.setParameter("id", id);
		List<PIN_PLANO_DIARIO_PINTURA_LINHAS> data = query.getResultList();
		return data;
	}

	public List<PIN_PLANO_DIARIO_PINTURA_LINHAS> getall() {
		Query query = entityManager
				.createQuery("Select a from PIN_PLANO_DIARIO_PINTURA_LINHAS a ORDER BY a.DIA_SEMANA, a.ORDEM");
		List<PIN_PLANO_DIARIO_PINTURA_LINHAS> data = query.getResultList();
		return data;
	}

	public List<PIN_PLANO_DIARIO_PINTURA_LINHAS> getByPlanoDiario(Integer idPlanoDiario) {
		Query query = entityManager.createQuery(
				"Select a from PIN_PLANO_DIARIO_PINTURA_LINHAS a where a.ID_PLANO_DIARIO_PINTURA =:id ORDER BY a.DIA_SEMANA, a.ORDEM");
		query.setParameter("id", idPlanoDiario);
		List<PIN_PLANO_DIARIO_PINTURA_LINHAS> data = query.getResultList();
		return data;
	}
}
