package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_DIC_ARTIGOS_TIPOLOGIA;

public class MAN_DIC_ARTIGOS_TIPOLOGIADao extends GenericDaoJpaImpl<MAN_DIC_ARTIGOS_TIPOLOGIA, Integer> implements GenericDao<MAN_DIC_ARTIGOS_TIPOLOGIA, Integer> {
	public MAN_DIC_ARTIGOS_TIPOLOGIADao() {
		super(MAN_DIC_ARTIGOS_TIPOLOGIA.class);
	}

	public List<MAN_DIC_ARTIGOS_TIPOLOGIA> getall() {

		Query query = entityManager.createQuery("Select a from MAN_DIC_ARTIGOS_TIPOLOGIA a");

		List<MAN_DIC_ARTIGOS_TIPOLOGIA> utz = query.getResultList();
		return utz;

	}
	

}
