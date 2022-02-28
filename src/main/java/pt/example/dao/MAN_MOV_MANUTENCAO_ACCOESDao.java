package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_MOV_MANUTENCAO_ACCOES;

public class MAN_MOV_MANUTENCAO_ACCOESDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_ACCOES, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_ACCOES, Integer> {
	public MAN_MOV_MANUTENCAO_ACCOESDao() {
		super(MAN_MOV_MANUTENCAO_ACCOES.class);
	}

	public List<MAN_MOV_MANUTENCAO_ACCOES> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_ACCOES a ");

		List<MAN_MOV_MANUTENCAO_ACCOES> utz = query.getResultList();
		return utz;

	}
}
