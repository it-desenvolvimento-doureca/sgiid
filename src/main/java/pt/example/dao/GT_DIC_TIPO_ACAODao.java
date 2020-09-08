package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GT_DIC_TIPO_ACAO;

public class GT_DIC_TIPO_ACAODao extends GenericDaoJpaImpl<GT_DIC_TIPO_ACAO, Integer> implements GenericDao<GT_DIC_TIPO_ACAO, Integer> {
	public GT_DIC_TIPO_ACAODao() {
		super(GT_DIC_TIPO_ACAO.class);
	}

	public List<GT_DIC_TIPO_ACAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GT_DIC_TIPO_ACAO a where a.ID_TIPO_ACAO = :id ");
		query.setParameter("id", id);
		List<GT_DIC_TIPO_ACAO> data = query.getResultList();
		return data;

	}


	public List<GT_DIC_TIPO_ACAO> getall() {

		Query query = entityManager.createQuery("Select a from GT_DIC_TIPO_ACAO a");
		List<GT_DIC_TIPO_ACAO> data = query.getResultList();
		return data;

	}

}
