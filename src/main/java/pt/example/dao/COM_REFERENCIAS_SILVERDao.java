package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_REFERENCIAS_SILVER;

public class COM_REFERENCIAS_SILVERDao extends GenericDaoJpaImpl<COM_REFERENCIAS_SILVER, Integer>
		implements GenericDao<COM_REFERENCIAS_SILVER, Integer> {
	public COM_REFERENCIAS_SILVERDao() {
		super(COM_REFERENCIAS_SILVER.class);
	}

	public List<COM_REFERENCIAS_SILVER> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from COM_REFERENCIAS_SILVER a where a.ID_REFERENCIA = :id ");
		query.setParameter("id", id);
		List<COM_REFERENCIAS_SILVER> data = query.getResultList();
		return data;

	}

	public List<COM_REFERENCIAS_SILVER> getall() {

		Query query = entityManager.createQuery("Select a from COM_REFERENCIAS_SILVER a  ");
		List<COM_REFERENCIAS_SILVER> data = query.getResultList();
		return data;

	}

}
