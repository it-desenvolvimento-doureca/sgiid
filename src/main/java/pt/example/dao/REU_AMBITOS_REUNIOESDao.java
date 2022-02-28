package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.REU_AMBITOS_REUNIOES;

public class REU_AMBITOS_REUNIOESDao extends GenericDaoJpaImpl<REU_AMBITOS_REUNIOES, Integer>
		implements GenericDao<REU_AMBITOS_REUNIOES, Integer> {
	public REU_AMBITOS_REUNIOESDao() {
		super(REU_AMBITOS_REUNIOES.class);
	}

	public List<REU_AMBITOS_REUNIOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from REU_AMBITOS_REUNIOES a where a.ID_AMBITO = :id ");
		query.setParameter("id", id);
		List<REU_AMBITOS_REUNIOES> data = query.getResultList();
		return data;

	}

	public List<REU_AMBITOS_REUNIOES> getall() {

		Query query = entityManager.createQuery("Select a from REU_AMBITOS_REUNIOES a ");
		List<REU_AMBITOS_REUNIOES> data = query.getResultList();
		return data;

	}

}
