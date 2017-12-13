package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_TIPO_ADICAO;

public class AB_DIC_TIPO_ADICAODao extends GenericDaoJpaImpl<AB_DIC_TIPO_ADICAO, Integer>
		implements GenericDao<AB_DIC_TIPO_ADICAO, Integer> {
	public AB_DIC_TIPO_ADICAODao() {
		super(AB_DIC_TIPO_ADICAO.class);
	}

	public List<AB_DIC_TIPO_ADICAO> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_TIPO_ADICAO a where a.INATIVO != 1 ");
		List<AB_DIC_TIPO_ADICAO> data = query.getResultList();
		return data;

	}
}
