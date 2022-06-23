package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_MANUTENCAO_NOTAS;

public class MAN_MOV_MANUTENCAO_NOTASDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_NOTAS, Integer>
		implements GenericDao<MAN_MOV_MANUTENCAO_NOTAS, Integer> {
	public MAN_MOV_MANUTENCAO_NOTASDao() {
		super(MAN_MOV_MANUTENCAO_NOTAS.class);
	}

	public List<MAN_MOV_MANUTENCAO_NOTAS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_NOTAS a ");

		List<MAN_MOV_MANUTENCAO_NOTAS> utz = query.getResultList();
		return utz;

	}

	public List<MAN_MOV_MANUTENCAO_NOTAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a,(select x.NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_CRIA ) as NOME_UTILIZADOR from MAN_MOV_MANUTENCAO_NOTAS a where a.ID_MANUTENCAO_CAB = :id");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_NOTAS> utz = query.getResultList();
		return utz;

	}
}
