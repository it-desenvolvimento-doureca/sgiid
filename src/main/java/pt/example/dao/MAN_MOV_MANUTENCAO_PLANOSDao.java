package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_MANUTENCAO_EQUIPAMENTOS;
import pt.example.entity.MAN_MOV_MANUTENCAO_PLANOS;

public class MAN_MOV_MANUTENCAO_PLANOSDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_PLANOS, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_PLANOS, Integer> {
	public MAN_MOV_MANUTENCAO_PLANOSDao() {
		super(MAN_MOV_MANUTENCAO_PLANOS.class);
	}

	public List<MAN_MOV_MANUTENCAO_PLANOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_PLANOS a ");

		List<MAN_MOV_MANUTENCAO_PLANOS> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_PLANOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a from MAN_MOV_MANUTENCAO_PLANOS a "
				+ "where  a.ID_MANUTENCAO = :id ");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_PLANOS> data = query.getResultList();
		return data;

	}
}
