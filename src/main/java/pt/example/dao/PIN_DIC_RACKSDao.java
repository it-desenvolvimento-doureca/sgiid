package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_RACKS;

public class PIN_DIC_RACKSDao extends GenericDaoJpaImpl<PIN_DIC_RACKS, Integer>
		implements GenericDao<PIN_DIC_RACKS, Integer> {
	public PIN_DIC_RACKSDao() {
		super(PIN_DIC_RACKS.class);
	}

	public List<PIN_DIC_RACKS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a  from PIN_DIC_RACKS a,  where  a.ID = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_RACKS> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_RACKS> getall() {

		Query query = entityManager.createQuery("Select a from PIN_DIC_RACKS a");
		List<PIN_DIC_RACKS> data = query.getResultList();
		return data;

	}
}
