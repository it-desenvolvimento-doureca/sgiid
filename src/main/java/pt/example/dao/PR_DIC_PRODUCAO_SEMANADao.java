package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_PRODUCAO_SEMANA;

public class PR_DIC_PRODUCAO_SEMANADao extends GenericDaoJpaImpl<PR_DIC_PRODUCAO_SEMANA, Integer> implements GenericDao<PR_DIC_PRODUCAO_SEMANA, Integer> {
	public PR_DIC_PRODUCAO_SEMANADao() {
		super(PR_DIC_PRODUCAO_SEMANA.class);
	}

	public List<PR_DIC_PRODUCAO_SEMANA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_DIC_PRODUCAO_SEMANA a where a.ID_TIPOLOGIA_ENSAIO = :id ");
		query.setParameter("id", id);
		List<PR_DIC_PRODUCAO_SEMANA> data = query.getResultList();
		return data;

	}


	public List<PR_DIC_PRODUCAO_SEMANA> getall() {

		Query query = entityManager.createQuery("Select a,(select x.NOME_LINHA from AB_DIC_LINHA x where x.ID_LINHA = a.ID_LINHA ) as NOME_LINHA from PR_DIC_PRODUCAO_SEMANA a where a.ATIVO = 1");
		List<PR_DIC_PRODUCAO_SEMANA> data = query.getResultList();
		return data;

	}

}
