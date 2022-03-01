package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_ACORDOS_PRECOS;

public class COM_ACORDOS_PRECOSDao extends GenericDaoJpaImpl<COM_ACORDOS_PRECOS, Integer>
		implements GenericDao<COM_ACORDOS_PRECOS, Integer> {
	public COM_ACORDOS_PRECOSDao() {
		super(COM_ACORDOS_PRECOS.class);
	}

	public List<COM_ACORDOS_PRECOS> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_PRECOS a where a.ID_ACORDO = :id and a.VERSAO = :versao order by a.DATA_INICIO");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_ACORDOS_PRECOS> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS_PRECOS> getall() {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_PRECOS a  ");
		List<COM_ACORDOS_PRECOS> data = query.getResultList();
		return data;

	}

}
