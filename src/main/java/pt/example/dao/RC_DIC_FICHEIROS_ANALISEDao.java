package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_DIC_FICHEIROS_ANALISE;

public class RC_DIC_FICHEIROS_ANALISEDao extends GenericDaoJpaImpl<RC_DIC_FICHEIROS_ANALISE, Integer>
		implements GenericDao<RC_DIC_FICHEIROS_ANALISE, Integer> {
	public RC_DIC_FICHEIROS_ANALISEDao() {
		super(RC_DIC_FICHEIROS_ANALISE.class);
	}

	public List<RC_DIC_FICHEIROS_ANALISE> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_DIC_FICHEIROS_ANALISE a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_DIC_FICHEIROS_ANALISE> data = query.getResultList();
		return data;

	}

	public List<RC_DIC_FICHEIROS_ANALISE> getall() {

		Query query = entityManager.createQuery("Select a from RC_DIC_FICHEIROS_ANALISE a ");
		List<RC_DIC_FICHEIROS_ANALISE> data = query.getResultList();
		return data;

	}

}
