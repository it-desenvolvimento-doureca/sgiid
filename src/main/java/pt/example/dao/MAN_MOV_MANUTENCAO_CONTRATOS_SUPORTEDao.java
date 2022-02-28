package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE;
import pt.example.entity.MAN_MOV_MANUTENCAO_EQUIPAMENTOS;

public class MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTEDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE, Integer> {
	public MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTEDao() {
		super(MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE.class);
	}

	public List<MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE a ");

		List<MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a from MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE a "
				+ "where  a.ID_MANUTENCAO = :id ");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE> data = query.getResultList();
		return data;

	}
}
