package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_ANALISES;

public class PR_PLANEAMENTO_PRODUCAO_ANALISESDao extends GenericDaoJpaImpl<PR_PLANEAMENTO_PRODUCAO_ANALISES, Integer>
		implements GenericDao<PR_PLANEAMENTO_PRODUCAO_ANALISES, Integer> {
	public PR_PLANEAMENTO_PRODUCAO_ANALISESDao() {
		super(PR_PLANEAMENTO_PRODUCAO_ANALISES.class);
	}

	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a,(select x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_CRIA) as NOME_UTILIZADOR from PR_PLANEAMENTO_PRODUCAO_ANALISES a where a.ID_PLANEAMENTO_PRODUCAO_ANALISES = :id ");
		query.setParameter("id", id);
		List<PR_PLANEAMENTO_PRODUCAO_ANALISES> data = query.getResultList();
		return data;

	}

	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES> getall() {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_ANALISES a where a.ATIVO = 1");
		List<PR_PLANEAMENTO_PRODUCAO_ANALISES> data = query.getResultList();
		return data;

	}
	
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES> getall2() {

		Query query = entityManager.createNativeQuery("Select a.ID_PLANEAMENTO_PRODUCAO_ANALISES,a.DATA_CRIA,a.SEMANA,a.ANO,a.N_SEMANAS, "
				+ " (select CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(x.ID_PLANEAMENTO_PRODUCAO_CAB,' | '), CAST (x.DATA_CRIA as DATE )) +' | ' ,x.DATA_MRP) ,' | '), x.N_MRP)  from PR_PLANEAMENTO_PRODUCAO_CAB x where x.ID_PLANEAMENTO_PRODUCAO_CAB = a.ID_PLANO_LINHA_1) as PLANO_LINHA_1, "
				+ " (select CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(x.ID_PLANEAMENTO_PRODUCAO_CAB,' | '), CAST (x.DATA_CRIA as DATE )) +' | ' ,x.DATA_MRP) ,' | '), x.N_MRP)  from PR_PLANEAMENTO_PRODUCAO_CAB x where x.ID_PLANEAMENTO_PRODUCAO_CAB = a.ID_PLANO_LINHA_2) as PLANO_LINHA_2 "
				+ " from PR_PLANEAMENTO_PRODUCAO_ANALISES a where a.ATIVO = 1");
		List<PR_PLANEAMENTO_PRODUCAO_ANALISES> data = query.getResultList();
		return data;

	}

}
