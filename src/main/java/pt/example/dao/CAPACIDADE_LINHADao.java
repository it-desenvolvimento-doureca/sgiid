package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.CAPACIDADE_LINHA;

public class CAPACIDADE_LINHADao extends GenericDaoJpaImpl<CAPACIDADE_LINHA,Integer> implements GenericDao<CAPACIDADE_LINHA,Integer> {
	public CAPACIDADE_LINHADao() {
		super(CAPACIDADE_LINHA.class);
	}

	
	public List<CAPACIDADE_LINHA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from CAPACIDADE_LINHA a where a.ID_CAPACIDADE = :id ");
		query.setParameter("id", id);
		List<CAPACIDADE_LINHA> data = query.getResultList();
		return data;

	}
	
	
	public List<CAPACIDADE_LINHA> getbyidlinha(Integer id) {

		Query query = entityManager.createQuery("Select a from CAPACIDADE_LINHA a where a.LINHA = :id ");
		query.setParameter("id", id);
		List<CAPACIDADE_LINHA> data = query.getResultList();
		return data;

	}
	
	public List<CAPACIDADE_LINHA> getall() {

		Query query = entityManager.createQuery("Select a from CAPACIDADE_LINHA a ");
		List<CAPACIDADE_LINHA> data = query.getResultList();
		return data;

	}

}
