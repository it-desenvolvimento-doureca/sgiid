package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_WINROBOT_PAUSAS; 

public class PR_WINROBOT_PAUSASDao extends GenericDaoJpaImpl<PR_WINROBOT_PAUSAS,Integer> implements GenericDao<PR_WINROBOT_PAUSAS,Integer> {
	public PR_WINROBOT_PAUSASDao() {
		super(PR_WINROBOT_PAUSAS.class);
	}

	public List<PR_WINROBOT_PAUSAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_WINROBOT_PAUSAS a ");

		List<PR_WINROBOT_PAUSAS> utz = query.getResultList();
		return utz;

	}

	public List<PR_WINROBOT_PAUSAS> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from PR_WINROBOT_PAUSAS a where a.ID_CAB_OPERARIO = :id ");
		query.setParameter("id", id);
		List<PR_WINROBOT_PAUSAS> data = query.getResultList();
		return data;

	}


}
