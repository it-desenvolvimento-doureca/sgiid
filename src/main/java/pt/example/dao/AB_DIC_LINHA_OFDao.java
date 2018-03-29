package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_LINHA_OF;

public class AB_DIC_LINHA_OFDao extends GenericDaoJpaImpl<AB_DIC_LINHA_OF,Integer> implements GenericDao<AB_DIC_LINHA_OF,Integer> {
	public AB_DIC_LINHA_OFDao() {
		super(AB_DIC_LINHA_OF.class);
	}

	
	public List<AB_DIC_LINHA_OF> getbyidlinha(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_LINHA_OF a where a.ID_LINHA = :id ");
		query.setParameter("id", id);
		List<AB_DIC_LINHA_OF> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_LINHA_OF> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_LINHA_OF a");
		List<AB_DIC_LINHA_OF> data = query.getResultList();
		return data;

	}

}
