package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB;

public class PR_PLANEAMENTO_PRODUCAO_SECCOES_CABDao extends GenericDaoJpaImpl<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB, Integer>
		implements GenericDao<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB, Integer> {
	public PR_PLANEAMENTO_PRODUCAO_SECCOES_CABDao() {
		super(PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB.class);
	}

	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a,(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) from PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB a where a.ID_PLANEAMENTO_PRODUCAO_CAB = :id ");
		query.setParameter("id", id);
		List<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB> data = query.getResultList();
		return data;

	}

	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB> getall() {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB a");
		List<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB> data = query.getResultList();
		return data;

	}

	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB> getall2() {

		Query query = entityManager.createNativeQuery(
				"Select a.ID_PLANEAMENTO_PRODUCAO_CAB,a.DATA_CRIA, (select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) UTILIZADOR, a.N_MRP,SECCAO,null COR,a.ESTADO, DATEPART(iso_week, dbo.[GetPrimeiraSemanaPlaneamentoSeccoes](DATA_MRP,NUMERO_SEMANAS,0,a.ID_PLANEAMENTO_PRODUCAO_CAB) ) PRIMEIRA_SEMANA,a.DATA_MRP from PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB a where a.ATIVO = 1 order by a.ID_PLANEAMENTO_PRODUCAO_CAB DESC");
		List<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB> data = query.getResultList();
		return data;

	}

}
