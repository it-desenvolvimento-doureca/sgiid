package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_MOV_MANUTENCAO_OPERARIOS;

public class MAN_MOV_MANUTENCAO_OPERARIOSDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_OPERARIOS, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_OPERARIOS, Integer> {
	public MAN_MOV_MANUTENCAO_OPERARIOSDao() {
		super(MAN_MOV_MANUTENCAO_OPERARIOS.class);
	}

	public List<MAN_MOV_MANUTENCAO_OPERARIOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_OPERARIOS a ");

		List<MAN_MOV_MANUTENCAO_OPERARIOS> utz = query.getResultList();
		return utz;

	}
}
