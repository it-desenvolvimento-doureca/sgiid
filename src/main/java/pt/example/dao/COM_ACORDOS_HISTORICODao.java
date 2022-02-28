package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_ACORDOS_HISTORICO;

public class COM_ACORDOS_HISTORICODao extends GenericDaoJpaImpl<COM_ACORDOS_HISTORICO, Integer>
		implements GenericDao<COM_ACORDOS_HISTORICO, Integer> {
	public COM_ACORDOS_HISTORICODao() {
		super(COM_ACORDOS_HISTORICO.class);
	}

	public List<COM_ACORDOS_HISTORICO> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_HISTORICO a where a.ID_ACORDO = :id and a.VERSAO = :versao ");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_ACORDOS_HISTORICO> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS_HISTORICO> getall() {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_HISTORICO a");
		List<COM_ACORDOS_HISTORICO> data = query.getResultList();
		return data;

	}

}
