package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.INJ_DIC_TIPO_PARAGEM;

public class INJ_DIC_TIPO_PARAGEMDao extends GenericDaoJpaImpl<INJ_DIC_TIPO_PARAGEM, Integer>
		implements GenericDao<INJ_DIC_TIPO_PARAGEM, Integer> {

	public INJ_DIC_TIPO_PARAGEMDao() {
		super(INJ_DIC_TIPO_PARAGEM.class);
	}

	/**
	 * Todos, ativos primeiro e por ordem de apresentacao.
	 *
	 * A ordem do ecra e a mesma que o tablet usa: assim quem configura ve a
	 * grelha como o operario a vai ver.
	 */
	public List<INJ_DIC_TIPO_PARAGEM> getall() {
		Query query = entityManager.createQuery(
				"Select a from INJ_DIC_TIPO_PARAGEM a "
				+ "order by a.ATIVO desc, a.ORDEM, a.CODIGO ");
		@SuppressWarnings("unchecked")
		List<INJ_DIC_TIPO_PARAGEM> data = query.getResultList();
		return data;
	}

	public List<INJ_DIC_TIPO_PARAGEM> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from INJ_DIC_TIPO_PARAGEM a where a.ID = :id ");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<INJ_DIC_TIPO_PARAGEM> data = query.getResultList();
		return data;
	}
}
