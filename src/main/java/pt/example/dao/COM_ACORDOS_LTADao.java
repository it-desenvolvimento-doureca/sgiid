package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_ACORDOS_LTA;

public class COM_ACORDOS_LTADao extends GenericDaoJpaImpl<COM_ACORDOS_LTA, Integer>
		implements GenericDao<COM_ACORDOS_LTA, Integer> {
	public COM_ACORDOS_LTADao() {
		super(COM_ACORDOS_LTA.class);
	}

	public List<COM_ACORDOS_LTA> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_LTA a where a.ID_ACORDO = :id and a.VERSAO = :versao ");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_ACORDOS_LTA> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS_LTA> getall() {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_LTA a ");
		List<COM_ACORDOS_LTA> data = query.getResultList();
		return data;

	}

}
