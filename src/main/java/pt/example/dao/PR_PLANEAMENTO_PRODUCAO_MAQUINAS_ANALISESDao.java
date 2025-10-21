package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_ANALISES;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES;

public class PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISESDao extends GenericDaoJpaImpl<PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES, Integer>
		implements GenericDao<PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES, Integer> {
	public PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISESDao() {
		super(PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES.class);
	}

	public List<PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a,(select x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_CRIA) as NOME_UTILIZADOR from PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES a where a.ID_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES = :id ");
		query.setParameter("id", id);
		List<PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES> data = query.getResultList();
		return data;

	}

	public List<PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES> getall() {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES a ");
		List<PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES> data = query.getResultList();
		return data;

	}	
	
	public List<PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES> getall2() {

		Query query = entityManager.createNativeQuery("Select a.ID_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES,a.DATA_CRIA,a.SEMANA,a.ANO,a.N_SEMANAS, "
				+ " (select CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(x.ID_PLANEAMENTO_PRODUCAO_CAB,' | '), CAST (x.DATA_CRIA as DATE )) +' | ' ,CAST (x.DATA_MRP as date)) ,' | '), x.N_MRP)  "
				+ " from PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB x where x.ID_PLANEAMENTO_PRODUCAO_CAB in(select value from string_split(ID_PLANOS,',')) ) as PLANOS,b.NOME_UTILIZADOR "
				+ " from PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES a  LEFT JOIN GER_UTILIZADORES b on a.UTZ_CRIA = b.ID_UTILIZADOR where a.ATIVO = 1 order by a.ID_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES DESC");
		List<PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES> data = query.getResultList();
		return data;

	}
	

}
