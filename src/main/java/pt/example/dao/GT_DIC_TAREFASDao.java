package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GT_DIC_TAREFAS;

public class GT_DIC_TAREFASDao extends GenericDaoJpaImpl<GT_DIC_TAREFAS, Integer>
		implements GenericDao<GT_DIC_TAREFAS, Integer> {
	public GT_DIC_TAREFASDao() {
		super(GT_DIC_TAREFAS.class);
	}

	public List<GT_DIC_TAREFAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GT_DIC_TAREFAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<GT_DIC_TAREFAS> data = query.getResultList();
		return data;

	}

	public List<GT_DIC_TAREFAS> getall() {

		Query query = entityManager.createQuery("Select a from GT_DIC_TAREFAS a ");
		List<GT_DIC_TAREFAS> data = query.getResultList();
		return data;

	}

}
