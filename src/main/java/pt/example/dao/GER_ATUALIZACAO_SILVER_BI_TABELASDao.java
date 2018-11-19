package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_ATUALIZACAO_SILVER_BI_TABELAS;

public class GER_ATUALIZACAO_SILVER_BI_TABELASDao extends GenericDaoJpaImpl<GER_ATUALIZACAO_SILVER_BI_TABELAS, Integer>
		implements GenericDao<GER_ATUALIZACAO_SILVER_BI_TABELAS, Integer> {
	public GER_ATUALIZACAO_SILVER_BI_TABELASDao() {
		super(GER_ATUALIZACAO_SILVER_BI_TABELAS.class);
	}

	public List<GER_ATUALIZACAO_SILVER_BI_TABELAS> getall() {
		Query query = entityManager.createQuery("select a from GER_ATUALIZACAO_SILVER_BI_TABELAS a");
		List<GER_ATUALIZACAO_SILVER_BI_TABELAS> data = query.getResultList();
		return data;

	}

}
