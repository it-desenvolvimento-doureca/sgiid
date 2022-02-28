package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_ACORDOS_AMORTIZACOES;

public class COM_ACORDOS_AMORTIZACOESDao extends GenericDaoJpaImpl<COM_ACORDOS_AMORTIZACOES, Integer>
		implements GenericDao<COM_ACORDOS_AMORTIZACOES, Integer> {
	public COM_ACORDOS_AMORTIZACOESDao() {
		super(COM_ACORDOS_AMORTIZACOES.class);
	}

	public List<COM_ACORDOS_AMORTIZACOES> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_AMORTIZACOES a where a.ID_ACORDO = :id and a.VERSAO = :versao ");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_ACORDOS_AMORTIZACOES> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS_AMORTIZACOES> getall() {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_AMORTIZACOES a ");
		List<COM_ACORDOS_AMORTIZACOES> data = query.getResultList();
		return data;

	}

}
