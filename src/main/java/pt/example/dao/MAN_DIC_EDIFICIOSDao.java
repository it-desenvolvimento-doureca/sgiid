package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_DIC_EDIFICIOS;

public class MAN_DIC_EDIFICIOSDao extends GenericDaoJpaImpl<MAN_DIC_EDIFICIOS, Integer> implements GenericDao<MAN_DIC_EDIFICIOS, Integer> {
	public MAN_DIC_EDIFICIOSDao() {
		super(MAN_DIC_EDIFICIOS.class);
	}

	public List<MAN_DIC_EDIFICIOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_DIC_EDIFICIOS a ");

		List<MAN_DIC_EDIFICIOS> utz = query.getResultList();
		return utz;

	}
}
