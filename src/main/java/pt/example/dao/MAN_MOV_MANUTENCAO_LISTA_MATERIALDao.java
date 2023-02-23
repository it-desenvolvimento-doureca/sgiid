package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_MOV_MANUTENCAO_LISTA_MATERIAL;

public class MAN_MOV_MANUTENCAO_LISTA_MATERIALDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_LISTA_MATERIAL, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_LISTA_MATERIAL, Integer> {
	public MAN_MOV_MANUTENCAO_LISTA_MATERIALDao() {
		super(MAN_MOV_MANUTENCAO_LISTA_MATERIAL.class);
	}

	public List<MAN_MOV_MANUTENCAO_LISTA_MATERIAL> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_LISTA_MATERIAL a ");

		List<MAN_MOV_MANUTENCAO_LISTA_MATERIAL> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_LISTA_MATERIAL> getbyId(Integer id) {
		Query query = entityManager.createQuery(
				"select a from MAN_MOV_MANUTENCAO_LISTA_MATERIAL a where a.ID_MANUTENCAO_CAB = :id");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_LISTA_MATERIAL> data = query.getResultList();
		return data;

	}
	
}
