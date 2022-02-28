package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_MANUTENCAO_CAB; 

public class MAN_MOV_MANUTENCAO_CABDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_CAB, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_CAB, Integer> {
	public MAN_MOV_MANUTENCAO_CABDao() {
		super(MAN_MOV_MANUTENCAO_CAB.class);
	}

	public List<MAN_MOV_MANUTENCAO_CAB> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_CAB a ");

		List<MAN_MOV_MANUTENCAO_CAB> utz = query.getResultList();
		return utz;

	}
}
