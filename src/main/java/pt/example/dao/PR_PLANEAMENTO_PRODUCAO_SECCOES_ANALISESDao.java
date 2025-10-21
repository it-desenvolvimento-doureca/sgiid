package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_ANALISES;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES;

public class PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISESDao extends GenericDaoJpaImpl<PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES, Integer>
		implements GenericDao<PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES, Integer> {
	public PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISESDao() {
		super(PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES.class);
	}

	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a,(select x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_CRIA) as NOME_UTILIZADOR from PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES a where a.ID_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES = :id ");
		query.setParameter("id", id);
		List<PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES> data = query.getResultList();
		return data;

	}

	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES> getall() {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES a ");
		List<PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES> data = query.getResultList();
		return data;

	}	
	
	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES> getall2() {

		Query query = entityManager.createNativeQuery("Select a.ID_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES,a.DATA_CRIA,a.SEMANA,a.ANO,a.N_SEMANAS, "
				+ " ( "
				+ " select STRING_AGG(CONCAT(x.ID_PLANEAMENTO_PRODUCAO_CAB, ' | ', CAST(x.DATA_CRIA as DATE), ' | ', CAST(x.DATA_MRP as DATE), ' | ', x.N_MRP, ' | ',x.SECCAO), ', ') "
				+ " from PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB x "
				+ " where x.ID_PLANEAMENTO_PRODUCAO_CAB in (select value from string_split(ID_PLANOS,',')) "
				+ " ) as PLANOS,b.NOME_UTILIZADOR "
				+ " from PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES a  LEFT JOIN GER_UTILIZADORES b on a.UTZ_CRIA = b.ID_UTILIZADOR where a.ATIVO = 1 order by a.ID_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES DESC");
		List<PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES> data = query.getResultList();
		return data;

	}
	

}
