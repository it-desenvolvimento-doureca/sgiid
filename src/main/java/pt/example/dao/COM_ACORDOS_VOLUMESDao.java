package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_ACORDOS_VOLUMES;

public class COM_ACORDOS_VOLUMESDao extends GenericDaoJpaImpl<COM_ACORDOS_VOLUMES, Integer>
		implements GenericDao<COM_ACORDOS_VOLUMES, Integer> {
	public COM_ACORDOS_VOLUMESDao() {
		super(COM_ACORDOS_VOLUMES.class);
	}

	public List<COM_ACORDOS_VOLUMES> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_VOLUMES a where a.ID_ACORDO = :id and a.VERSAO = :versao order by ANO");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_ACORDOS_VOLUMES> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS_VOLUMES> getall() {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_VOLUMES a ");
		List<COM_ACORDOS_VOLUMES> data = query.getResultList();
		return data;

	}

}
