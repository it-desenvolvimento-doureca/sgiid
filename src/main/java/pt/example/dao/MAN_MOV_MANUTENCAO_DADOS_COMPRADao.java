package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_MOV_MANUTENCAO_DADOS_COMPRA;
import pt.example.entity.MAN_MOV_MANUTENCAO_EQUIPAMENTOS;

public class MAN_MOV_MANUTENCAO_DADOS_COMPRADao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_DADOS_COMPRA, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_DADOS_COMPRA, Integer> {
	public MAN_MOV_MANUTENCAO_DADOS_COMPRADao() {
		super(MAN_MOV_MANUTENCAO_DADOS_COMPRA.class);
	}

	public List<MAN_MOV_MANUTENCAO_DADOS_COMPRA> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_DADOS_COMPRA a ");

		List<MAN_MOV_MANUTENCAO_DADOS_COMPRA> utz = query.getResultList();
		return utz;

	}
	
	
	public List<MAN_MOV_MANUTENCAO_DADOS_COMPRA> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a from MAN_MOV_MANUTENCAO_DADOS_COMPRA a "
				+ "where  a.ID_MANUTENCAO = :id ");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_DADOS_COMPRA> data = query.getResultList();
		return data;

	}
}
