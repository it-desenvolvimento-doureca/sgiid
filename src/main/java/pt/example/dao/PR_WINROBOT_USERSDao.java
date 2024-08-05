package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_WINROBOT_USERS; 

public class PR_WINROBOT_USERSDao extends GenericDaoJpaImpl<PR_WINROBOT_USERS,Integer> implements GenericDao<PR_WINROBOT_USERS,Integer> {
	public PR_WINROBOT_USERSDao() {
		super(PR_WINROBOT_USERS.class);
	}

	public List<PR_WINROBOT_USERS> getall() {

		Query query = entityManager.createQuery("Select a from PR_WINROBOT_USERS a ");

		List<PR_WINROBOT_USERS> utz = query.getResultList();
		return utz;

	}

	public List<PR_WINROBOT_USERS> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from PR_WINROBOT_USERS a where a.ID_CAB = :id ");
		query.setParameter("id", id);
		List<PR_WINROBOT_USERS> data = query.getResultList();
		return data;

	}


}
