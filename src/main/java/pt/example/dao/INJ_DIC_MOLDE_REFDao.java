package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.INJ_DIC_MOLDE_REF;

/**
 * A configuracao de cavidades: que referencias cada molde produz.
 *
 * O molde e identificado pela REFERENCIA dele no Silver (REF_MOLDE), nao por
 * um ID de uma tabela nossa — nao ha tabela de moldes, leem-se do Silver.
 */
public class INJ_DIC_MOLDE_REFDao extends GenericDaoJpaImpl<INJ_DIC_MOLDE_REF, Integer>
		implements GenericDao<INJ_DIC_MOLDE_REF, Integer> {

	public INJ_DIC_MOLDE_REFDao() {
		super(INJ_DIC_MOLDE_REF.class);
	}

	public List<INJ_DIC_MOLDE_REF> getall() {
		Query query = entityManager.createQuery(
				"Select a from INJ_DIC_MOLDE_REF a order by a.REF_MOLDE, a.REF_NUM ");
		@SuppressWarnings("unchecked")
		List<INJ_DIC_MOLDE_REF> data = query.getResultList();
		return data;
	}

	public List<INJ_DIC_MOLDE_REF> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from INJ_DIC_MOLDE_REF a where a.ID = :id ");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<INJ_DIC_MOLDE_REF> data = query.getResultList();
		return data;
	}

	/** As referencias configuradas de um molde. */
	public List<INJ_DIC_MOLDE_REF> getbymolde(String refMolde) {
		Query query = entityManager.createQuery(
				"Select a from INJ_DIC_MOLDE_REF a where a.REF_MOLDE = :m order by a.REF_NUM ");
		query.setParameter("m", refMolde);
		@SuppressWarnings("unchecked")
		List<INJ_DIC_MOLDE_REF> data = query.getResultList();
		return data;
	}

	/**
	 * Os moldes que ja tem configuracao.
	 *
	 * E por aqui que o ecra comeca: sao ~2800 moldes no Silver e quase todos
	 * nunca serao usados na injecao. Mostrar primeiro os configurados poupa uma
	 * pesquisa a quem so quer corrigir o que ja la esta.
	 */
	public List<String> getmoldesconfigurados() {
		Query query = entityManager.createQuery(
				"Select distinct a.REF_MOLDE from INJ_DIC_MOLDE_REF a order by a.REF_MOLDE ");
		@SuppressWarnings("unchecked")
		List<String> data = query.getResultList();
		return data;
	}
}
