package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_WINROBOT_RACKS; 

public class PR_WINROBOT_RACKSDao extends GenericDaoJpaImpl<PR_WINROBOT_RACKS,Integer> implements GenericDao<PR_WINROBOT_RACKS,Integer> {
	public PR_WINROBOT_RACKSDao() {
		super(PR_WINROBOT_RACKS.class);
	}

	public List<PR_WINROBOT_RACKS> getall() {

		Query query = entityManager.createQuery("Select a from PR_WINROBOT_RACKS a ");

		List<PR_WINROBOT_RACKS> utz = query.getResultList();
		return utz;

	}

	public List<PR_WINROBOT_RACKS> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from PR_WINROBOT_RACKS a where a.ID_CAB = :id ");
		query.setParameter("id", id);
		List<PR_WINROBOT_RACKS> data = query.getResultList();
		return data;

	}


}
