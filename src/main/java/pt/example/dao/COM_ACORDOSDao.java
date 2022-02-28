package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_ACORDOS; 

public class COM_ACORDOSDao extends GenericDaoJpaImpl<COM_ACORDOS, Integer>
		implements GenericDao<COM_ACORDOS, Integer> {
	public COM_ACORDOSDao() {
		super(COM_ACORDOS.class);
	}

	public List<COM_ACORDOS> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS a where a.ID = :id and a.VERSAO = :versao ");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_ACORDOS> data = query.getResultList();
		return data;
	}

	public List<COM_ACORDOS> getbyid2(Integer id, Integer versao) {

		Query query = entityManager.createQuery(
				"Select a,(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) as NOME_UTILIZADOR,(select max(c.VERSAO) from COM_ACORDOS c where c.ID = a.ID ) as MAX_VERSAO "
				+ ",(select c.NOME_UTILIZADOR from GER_UTILIZADORES c where c.ID_UTILIZADOR = a.UTZ_FECHO_LTA) as NOME_UTZ_FECHO_LTA "
				+ ",(select d.NOME_UTILIZADOR from GER_UTILIZADORES d where d.ID_UTILIZADOR = a.UTZ_FECHO_AMORTIZACOES) as NOME_UTZ_FECHO_AMORTIZACOES "
				+ ",(select e.NOME_UTILIZADOR from GER_UTILIZADORES e where e.ID_UTILIZADOR = a.UTZ_FECHO) as NOME_UTZ_FECHO "
				+ "from COM_ACORDOS a where a.ID = :id and a.VERSAO = :versao ");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_ACORDOS> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS> getversoes(Integer id, Integer versao) {

		Query query = entityManager.createNativeQuery(
				"Select a.ID,a.VERSAO from COM_ACORDOS a where a.ID = :id  order by a.VERSAO ");
		query.setParameter("id", id);
		//query.setParameter("versao", versao);
		List<COM_ACORDOS> data = query.getResultList();
		return data;

	}
	
	public List<COM_ACORDOS> getall() {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS a where a.INATIVO != 1 ");
		List<COM_ACORDOS> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS> getall2() {

		Query query = entityManager.createNativeQuery(
				"Select a.ID,a.ID_CONTRATO,a.ID_REFERENCIA,b.COD_REFERENCIA,b.DESCRICAO,c.N_CONTRATO /*,MAX(a.VERSAO) */,VERSAO,ESTADO from COM_ACORDOS a left join COM_REFERENCIAS b on a.ID_REFERENCIA = b.ID left join COM_CONTRATOS c on a.ID_CONTRATO = c.ID where a.INATIVO != 1   AND VERSAO = (select MAX(b.VERSAO) FROM COM_ACORDOS b WHERE b.ID = a.ID) ");
		List<COM_ACORDOS> data = query.getResultList();
		return data;

	}

}
