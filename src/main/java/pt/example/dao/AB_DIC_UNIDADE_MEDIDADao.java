package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_UNIDADE_MEDIDA;

public class AB_DIC_UNIDADE_MEDIDADao extends GenericDaoJpaImpl<AB_DIC_UNIDADE_MEDIDA,Integer> implements GenericDao<AB_DIC_UNIDADE_MEDIDA,Integer> {
	public AB_DIC_UNIDADE_MEDIDADao() {
		super(AB_DIC_UNIDADE_MEDIDA.class);
	}
	
	public List<AB_DIC_UNIDADE_MEDIDA> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_UNIDADE_MEDIDA a where a.INATIVO != 1 ");
		List<AB_DIC_UNIDADE_MEDIDA> data = query.getResultList();
		return data;

	}

}
