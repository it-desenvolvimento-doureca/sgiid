package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_PRODUTOS_RELACIONADOS;

public class PIN_DIC_PRODUTOS_RELACIONADOSDao extends GenericDaoJpaImpl<PIN_DIC_PRODUTOS_RELACIONADOS, Integer>
		implements GenericDao<PIN_DIC_PRODUTOS_RELACIONADOS, Integer> {
	public PIN_DIC_PRODUTOS_RELACIONADOSDao() {
		super(PIN_DIC_PRODUTOS_RELACIONADOS.class);
	}

	public List<PIN_DIC_PRODUTOS_RELACIONADOS> getbyid_componente(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_PRODUTOS_RELACIONADOS a where a.ID_PRODUTO = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_PRODUTOS_RELACIONADOS> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_PRODUTOS_RELACIONADOS> gelAll() {

		Query query = entityManager.createQuery("Select a from PIN_DIC_PRODUTOS_RELACIONADOS a ");

		List<PIN_DIC_PRODUTOS_RELACIONADOS> data = query.getResultList();
		return data;

	}

}
