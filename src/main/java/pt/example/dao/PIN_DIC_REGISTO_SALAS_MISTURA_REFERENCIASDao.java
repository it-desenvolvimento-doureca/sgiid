package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS;

public class PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIASDao extends GenericDaoJpaImpl<PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS, Integer>
		implements GenericDao<PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS, Integer> {
	public PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIASDao() {
		super(PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS.class);
	}

	public List<PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS a where a.ID_REGISTO_SALAS_MISTURA = :id ");
		query.setParameter("id", id); 
		List<PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS> getall() {

		Query query = entityManager.createQuery("Select a from PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS a ");
		List<PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS> data = query.getResultList();
		return data;

	}

}
