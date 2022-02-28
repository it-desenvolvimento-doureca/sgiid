package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_MOV_MANUTENCAO_PAUSAS;

public class MAN_MOV_MANUTENCAO_PAUSASDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_PAUSAS, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_PAUSAS, Integer> {
	public MAN_MOV_MANUTENCAO_PAUSASDao() {
		super(MAN_MOV_MANUTENCAO_PAUSAS.class);
	}

	public List<MAN_MOV_MANUTENCAO_PAUSAS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_PAUSAS a ");

		List<MAN_MOV_MANUTENCAO_PAUSAS> utz = query.getResultList();
		return utz;

	}
}
