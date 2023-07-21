package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.COM_CUSTOMERS_GROUPS;

public class COM_CUSTOMERS_GROUPSDao extends GenericDaoJpaImpl<COM_CUSTOMERS_GROUPS, Integer> implements GenericDao<COM_CUSTOMERS_GROUPS, Integer> {
	public COM_CUSTOMERS_GROUPSDao() {
		super(COM_CUSTOMERS_GROUPS.class);
	}

	public List<COM_CUSTOMERS_GROUPS> getall() {

		Query query = entityManager.createQuery("Select a,b from COM_CUSTOMERS_GROUPS a,COM_CUSTOMER_GROUPS b where a.ID_CUSTOMER_GROUP = b.ID ");

		List<COM_CUSTOMERS_GROUPS> utz = query.getResultList();
		return utz;

	}
	
	 
}
