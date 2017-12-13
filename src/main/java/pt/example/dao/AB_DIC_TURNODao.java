package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_TURNO;

public class AB_DIC_TURNODao extends GenericDaoJpaImpl<AB_DIC_TURNO, Integer>
		implements GenericDao<AB_DIC_TURNO, Integer> {
	public AB_DIC_TURNODao() {
		super(AB_DIC_TURNO.class);
	}

	public List<AB_DIC_TURNO> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_TURNO a where a.INATIVO != 1 ");
		List<AB_DIC_TURNO> data = query.getResultList();
		return data;

	}

}
