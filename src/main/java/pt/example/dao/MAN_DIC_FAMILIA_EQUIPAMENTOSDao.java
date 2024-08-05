package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_DIC_FAMILIA_EQUIPAMENTOS;

public class MAN_DIC_FAMILIA_EQUIPAMENTOSDao extends GenericDaoJpaImpl<MAN_DIC_FAMILIA_EQUIPAMENTOS, Integer> implements GenericDao<MAN_DIC_FAMILIA_EQUIPAMENTOS, Integer> {
	public MAN_DIC_FAMILIA_EQUIPAMENTOSDao() {
		super(MAN_DIC_FAMILIA_EQUIPAMENTOS.class);
	}

	public List<MAN_DIC_FAMILIA_EQUIPAMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_DIC_FAMILIA_EQUIPAMENTOS a ");

		List<MAN_DIC_FAMILIA_EQUIPAMENTOS> utz = query.getResultList();
		return utz;

	}
	 
}
