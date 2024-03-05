package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_CAB;

public class PR_PLANEAMENTO_PRODUCAO_CABDao extends GenericDaoJpaImpl<PR_PLANEAMENTO_PRODUCAO_CAB, Integer>
		implements GenericDao<PR_PLANEAMENTO_PRODUCAO_CAB, Integer> {
	public PR_PLANEAMENTO_PRODUCAO_CABDao() {
		super(PR_PLANEAMENTO_PRODUCAO_CAB.class);
	}

	public List<PR_PLANEAMENTO_PRODUCAO_CAB> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a,(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) from PR_PLANEAMENTO_PRODUCAO_CAB a where a.ID_PLANEAMENTO_PRODUCAO_CAB = :id ");
		query.setParameter("id", id);
		List<PR_PLANEAMENTO_PRODUCAO_CAB> data = query.getResultList();
		return data;

	}

	public List<PR_PLANEAMENTO_PRODUCAO_CAB> getall() {

		Query query = entityManager.createQuery("Select a from PR_PLANEAMENTO_PRODUCAO_CAB a");
		List<PR_PLANEAMENTO_PRODUCAO_CAB> data = query.getResultList();
		return data;

	}

	public List<PR_PLANEAMENTO_PRODUCAO_CAB> getall2() {

		Query query = entityManager.createNativeQuery(
				"Select a.ID_PLANEAMENTO_PRODUCAO_CAB,a.DATA_CRIA, (select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) UTILIZADOR, a.N_MRP,c.nome_linha,c.COR,a.ESTADO, DATEPART(iso_week, dbo.[GetPrimeiraSemanaPlaneamento](DATA_MRP,NUMERO_SEMANAS,0,a.ID_PLANEAMENTO_PRODUCAO_CAB) ) PRIMEIRA_SEMANA,a.DATA_MRP from PR_PLANEAMENTO_PRODUCAO_CAB a inner join AB_DIC_LINHA c on c.id_linha = a.id_linha where a.ATIVO = 1 order by a.ID_PLANEAMENTO_PRODUCAO_CAB DESC");
		List<PR_PLANEAMENTO_PRODUCAO_CAB> data = query.getResultList();
		return data;

	}

}
