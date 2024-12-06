package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_CRITERIOS_AVALIACAO;

public class RH_DIC_CRITERIOS_AVALIACAODao extends GenericDaoJpaImpl<RH_DIC_CRITERIOS_AVALIACAO, Integer>
		implements GenericDao<RH_DIC_CRITERIOS_AVALIACAO, Integer> {
	public RH_DIC_CRITERIOS_AVALIACAODao() {
		super(RH_DIC_CRITERIOS_AVALIACAO.class);
	}

	public List<RH_DIC_CRITERIOS_AVALIACAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_CRITERIOS_AVALIACAO a where a.COD_TURNO = :id ");
		query.setParameter("id", id);
		List<RH_DIC_CRITERIOS_AVALIACAO> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_CRITERIOS_AVALIACAO> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_CRITERIOS_AVALIACAO a ");
		List<RH_DIC_CRITERIOS_AVALIACAO> data = query.getResultList();
		return data;

	}

}
