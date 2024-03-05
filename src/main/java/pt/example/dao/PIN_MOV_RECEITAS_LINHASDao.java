package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_RECEITAS_LINHAS;

public class PIN_MOV_RECEITAS_LINHASDao extends GenericDaoJpaImpl<PIN_MOV_RECEITAS_LINHAS, Integer>
		implements GenericDao<PIN_MOV_RECEITAS_LINHAS, Integer> {
	public PIN_MOV_RECEITAS_LINHASDao() {
		super(PIN_MOV_RECEITAS_LINHAS.class);
	}

	public List<PIN_MOV_RECEITAS_LINHAS> getbyid(Integer id,Integer versao) {

		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_RECEITAS_LINHAS a,PIN_DIC_TIPO_ACABAMENTO x where a.ID_RECEITA = :id and a.VERSAO =:versao and  x.ID = a.ID_TIPO_ACABAMENTO "
				+ "order by ( case when x.NOME like '%primário%' then 1 when x.NOME like '%base%' then 2 when x.NOME like '%verniz%' then 3 else 4 end  )");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<PIN_MOV_RECEITAS_LINHAS> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_RECEITAS_LINHAS> getall() {
		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_RECEITAS_LINHAS a ");
		List<PIN_MOV_RECEITAS_LINHAS> data = query.getResultList();
		return data;

	}

	

}
