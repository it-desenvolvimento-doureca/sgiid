package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_WINROBOT_ETIQUETAS; 

public class PR_WINROBOT_ETIQUETASDao extends GenericDaoJpaImpl<PR_WINROBOT_ETIQUETAS,Integer> implements GenericDao<PR_WINROBOT_ETIQUETAS,Integer> {
	public PR_WINROBOT_ETIQUETASDao() {
		super(PR_WINROBOT_ETIQUETAS.class);
	}

	public List<PR_WINROBOT_ETIQUETAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_WINROBOT_ETIQUETAS a ");

		List<PR_WINROBOT_ETIQUETAS> utz = query.getResultList();
		return utz;

	}

	public List<PR_WINROBOT_ETIQUETAS> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from PR_WINROBOT_ETIQUETAS a where a.ID_ARTIGO = :id ");
		query.setParameter("id", id);
		List<PR_WINROBOT_ETIQUETAS> data = query.getResultList();
		return data;

	}


}
