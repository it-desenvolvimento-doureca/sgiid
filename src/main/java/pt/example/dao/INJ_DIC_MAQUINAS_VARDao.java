package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.INJ_DIC_MAQUINAS_VAR;

/**
 * Mapa de variaveis do coletor de injecao.
 *
 * A ordem da listagem e a que o ecra precisa: primeiro por tipo de maquina,
 * depois as linhas da familia (ID_MAQUINA nulo) e so a seguir as excecoes de
 * cada maquina. Assim a linha que vale por omissao aparece antes da que a
 * substitui, que e como se le a regra.
 */
public class INJ_DIC_MAQUINAS_VARDao extends GenericDaoJpaImpl<INJ_DIC_MAQUINAS_VAR, Integer>
		implements GenericDao<INJ_DIC_MAQUINAS_VAR, Integer> {

	public INJ_DIC_MAQUINAS_VARDao() {
		super(INJ_DIC_MAQUINAS_VAR.class);
	}

	public List<INJ_DIC_MAQUINAS_VAR> getall() {
		Query query = entityManager.createQuery(
				"Select a from INJ_DIC_MAQUINAS_VAR a "
				+ "order by a.TIPO_MAQUINA, a.ID_MAQUINA, a.CHAVE ");
		@SuppressWarnings("unchecked")
		List<INJ_DIC_MAQUINAS_VAR> data = query.getResultList();
		return data;
	}

	public List<INJ_DIC_MAQUINAS_VAR> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from INJ_DIC_MAQUINAS_VAR a where a.ID = :id ");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<INJ_DIC_MAQUINAS_VAR> data = query.getResultList();
		return data;
	}

	/** As variaveis de um tipo de maquina: as da familia mais as excecoes. */
	public List<INJ_DIC_MAQUINAS_VAR> getbytipo(String tipo) {
		Query query = entityManager.createQuery(
				"Select a from INJ_DIC_MAQUINAS_VAR a where a.TIPO_MAQUINA = :tipo "
				+ "order by a.ID_MAQUINA, a.CHAVE ");
		query.setParameter("tipo", tipo);
		@SuppressWarnings("unchecked")
		List<INJ_DIC_MAQUINAS_VAR> data = query.getResultList();
		return data;
	}
}
