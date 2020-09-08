package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_CAPACIDADE_ACABAMENTO;

public class PR_DIC_CAPACIDADE_ACABAMENTODao extends GenericDaoJpaImpl<PR_DIC_CAPACIDADE_ACABAMENTO, Integer> implements GenericDao<PR_DIC_CAPACIDADE_ACABAMENTO, Integer> {
	public PR_DIC_CAPACIDADE_ACABAMENTODao() {
		super(PR_DIC_CAPACIDADE_ACABAMENTO.class);
	}

	public List<PR_DIC_CAPACIDADE_ACABAMENTO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_DIC_CAPACIDADE_ACABAMENTO a where a.ID_TIPOLOGIA_ENSAIO = :id ");
		query.setParameter("id", id);
		List<PR_DIC_CAPACIDADE_ACABAMENTO> data = query.getResultList();
		return data;

	}


	public List<PR_DIC_CAPACIDADE_ACABAMENTO> getall() {

		Query query = entityManager.createQuery("Select a,(select b.COR from AB_DIC_LINHA b where b.ID_LINHA = a.ID_LINHA),(select b.NOME_LINHA from AB_DIC_LINHA b where b.ID_LINHA = a.ID_LINHA) from PR_DIC_CAPACIDADE_ACABAMENTO a where a.ATIVO = 1");
		List<PR_DIC_CAPACIDADE_ACABAMENTO> data = query.getResultList();
		return data;

	}

}
