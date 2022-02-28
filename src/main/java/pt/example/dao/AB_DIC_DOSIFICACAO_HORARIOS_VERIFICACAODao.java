package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO;

public class AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAODao extends GenericDaoJpaImpl<AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO,Integer> implements GenericDao<AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO,Integer> {
	public AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAODao() {
		super(AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO.class);
	}

	
	public List<AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO a where a.ID = :id ");
		query.setParameter("id", id);
		List<AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO a ");
		List<AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO> data = query.getResultList();
		return data;

	}

}
