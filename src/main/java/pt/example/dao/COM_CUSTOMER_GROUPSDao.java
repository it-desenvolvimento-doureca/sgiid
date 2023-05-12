package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.COM_CUSTOMER_GROUPS;

public class COM_CUSTOMER_GROUPSDao extends GenericDaoJpaImpl<COM_CUSTOMER_GROUPS, Integer> implements GenericDao<COM_CUSTOMER_GROUPS, Integer> {
	public COM_CUSTOMER_GROUPSDao() {
		super(COM_CUSTOMER_GROUPS.class);
	}

	public List<COM_CUSTOMER_GROUPS> getall() {

		Query query = entityManager.createQuery("Select a from COM_CUSTOMER_GROUPS a ");

		List<COM_CUSTOMER_GROUPS> utz = query.getResultList();
		return utz;

	}
	
	 
}
