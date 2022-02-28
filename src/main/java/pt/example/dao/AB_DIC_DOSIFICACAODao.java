package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_DOSIFICACAO;

public class AB_DIC_DOSIFICACAODao extends GenericDaoJpaImpl<AB_DIC_DOSIFICACAO,Integer> implements GenericDao<AB_DIC_DOSIFICACAO,Integer> {
	public AB_DIC_DOSIFICACAODao() {
		super(AB_DIC_DOSIFICACAO.class);
	}

	
	public List<AB_DIC_DOSIFICACAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_DOSIFICACAO a where a.ID = :id ");
		query.setParameter("id", id);
		List<AB_DIC_DOSIFICACAO> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_DOSIFICACAO> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_DOSIFICACAO a ");
		List<AB_DIC_DOSIFICACAO> data = query.getResultList();
		return data;

	}

}
