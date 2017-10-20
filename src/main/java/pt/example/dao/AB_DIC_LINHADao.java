package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_LINHA;
import pt.example.entity.AB_DIC_ZONA;
import pt.example.entity.GER_FORNECEDOR;

public class AB_DIC_LINHADao extends GenericDaoJpaImpl<AB_DIC_LINHA,Integer> implements GenericDao<AB_DIC_LINHA,Integer> {
	public AB_DIC_LINHADao() {
		super(AB_DIC_LINHA.class);
	}

	
	public List<AB_DIC_LINHA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_LINHA a where a.ID_LINHA = :id ");
		query.setParameter("id", id);
		List<AB_DIC_LINHA> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_LINHA> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_LINHA a where a.INATIVO != 1 ");
		List<AB_DIC_LINHA> data = query.getResultList();
		return data;

	}

}
