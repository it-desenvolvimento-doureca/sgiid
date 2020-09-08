package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PLANEAMENTO_LINHAS;

public class PLANEAMENTO_LINHASDao extends GenericDaoJpaImpl<PLANEAMENTO_LINHAS,Integer> implements GenericDao<PLANEAMENTO_LINHAS,Integer> {
	public PLANEAMENTO_LINHASDao() {
		super(PLANEAMENTO_LINHAS.class);
	}

	
	public List<PLANEAMENTO_LINHAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PLANEAMENTO_LINHAS a where a.ID_PLANEAMENTO = :id order by DATA");
		query.setParameter("id", id);
		List<PLANEAMENTO_LINHAS> data = query.getResultList();
		return data;

	}
	
	public List<PLANEAMENTO_LINHAS> getall() {

		Query query = entityManager.createQuery("Select a from PLANEAMENTO_LINHAS a ");
		List<PLANEAMENTO_LINHAS> data = query.getResultList();
		return data;

	}

}
