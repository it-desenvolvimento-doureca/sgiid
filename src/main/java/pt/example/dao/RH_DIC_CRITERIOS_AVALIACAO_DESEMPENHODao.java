package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO;

public class RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHODao extends GenericDaoJpaImpl<RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO, Integer>
		implements GenericDao<RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO, Integer> {
	public RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHODao() {
		super(RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO.class);
	}

	public List<RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO a ");
		List<RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO> data = query.getResultList();
		return data;

	}

}
