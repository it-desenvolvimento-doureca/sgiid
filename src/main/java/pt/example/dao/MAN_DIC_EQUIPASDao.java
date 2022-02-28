package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_DIC_EQUIPAS;

public class MAN_DIC_EQUIPASDao extends GenericDaoJpaImpl<MAN_DIC_EQUIPAS, Integer> implements GenericDao<MAN_DIC_EQUIPAS, Integer> {
	public MAN_DIC_EQUIPASDao() {
		super(MAN_DIC_EQUIPAS.class);
	}

	public List<MAN_DIC_EQUIPAS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_DIC_EQUIPAS a ");

		List<MAN_DIC_EQUIPAS> utz = query.getResultList();
		return utz;

	}
}
