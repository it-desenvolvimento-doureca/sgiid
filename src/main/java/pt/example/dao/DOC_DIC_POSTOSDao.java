package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.DOC_DIC_POSTOS;

public class DOC_DIC_POSTOSDao extends GenericDaoJpaImpl<DOC_DIC_POSTOS, Integer>
		implements GenericDao<DOC_DIC_POSTOS, Integer> {
	public DOC_DIC_POSTOSDao() {
		super(DOC_DIC_POSTOS.class);
	}

	public List<DOC_DIC_POSTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from DOC_DIC_POSTOS a where a.ID = :id ");
		query.setParameter("id", id);
		List<DOC_DIC_POSTOS> data = query.getResultList();
		return data;

	}

	public List<DOC_DIC_POSTOS> getbyip(String ip) {

		Query query = entityManager.createQuery("Select a from DOC_DIC_POSTOS a where a.IP_POSTO = :ip ");
		query.setParameter("ip", ip);
		List<DOC_DIC_POSTOS> data = query.getResultList();
		return data;

	}
	
	public List<DOC_DIC_POSTOS> getall() {

		Query query = entityManager.createQuery("Select a from DOC_DIC_POSTOS a ");
		List<DOC_DIC_POSTOS> data = query.getResultList();
		return data;

	}

}
