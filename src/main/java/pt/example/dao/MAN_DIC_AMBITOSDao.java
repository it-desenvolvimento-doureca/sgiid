package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_DIC_AMBITOS;

public class MAN_DIC_AMBITOSDao extends GenericDaoJpaImpl<MAN_DIC_AMBITOS, Integer> implements GenericDao<MAN_DIC_AMBITOS, Integer> {
	public MAN_DIC_AMBITOSDao() {
		super(MAN_DIC_AMBITOS.class);
	}

	public List<MAN_DIC_AMBITOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_DIC_AMBITOS a ");

		List<MAN_DIC_AMBITOS> utz = query.getResultList();
		return utz;

	}
}
