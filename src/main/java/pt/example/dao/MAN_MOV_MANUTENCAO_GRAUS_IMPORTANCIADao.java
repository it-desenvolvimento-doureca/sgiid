package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_MANUTENCAO_EQUIPAMENTOS;
import pt.example.entity.MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA;

public class MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIADao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA, Integer> {
	public MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIADao() {
		super(MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA.class);
	}

	public List<MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA a ");

		List<MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a from MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA a "
				+ "where  a.ID_MANUTENCAO = :id ");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA> data = query.getResultList();
		return data;

	}
}
