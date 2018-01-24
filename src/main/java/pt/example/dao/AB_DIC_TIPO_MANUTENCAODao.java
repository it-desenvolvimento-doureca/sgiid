package pt.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_TIPO_MANUTENCAO;

public class AB_DIC_TIPO_MANUTENCAODao extends GenericDaoJpaImpl<AB_DIC_TIPO_MANUTENCAO, Integer>
		implements GenericDao<AB_DIC_TIPO_MANUTENCAO, Integer> {
	public AB_DIC_TIPO_MANUTENCAODao() {
		super(AB_DIC_TIPO_MANUTENCAO.class);
	}

	public List<AB_DIC_TIPO_MANUTENCAO> getall(final ArrayList<String> classif) {

		Query query = entityManager.createQuery("Select a from AB_DIC_TIPO_MANUTENCAO a where a.INATIVO != 1 and a.CLASSIF in (:classif)");
		query.setParameter("classif", classif);
		List<AB_DIC_TIPO_MANUTENCAO> data = query.getResultList();
		return data;

	}

}
