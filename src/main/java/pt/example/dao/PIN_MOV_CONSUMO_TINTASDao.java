package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_CONSUMO_TINTAS;

public class PIN_MOV_CONSUMO_TINTASDao extends GenericDaoJpaImpl<PIN_MOV_CONSUMO_TINTAS, Integer>
		implements GenericDao<PIN_MOV_CONSUMO_TINTAS, Integer> {

	public PIN_MOV_CONSUMO_TINTASDao() {
		super(PIN_MOV_CONSUMO_TINTAS.class);
	}

	public List<PIN_MOV_CONSUMO_TINTAS> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_CONSUMO_TINTAS a where a.ID_CONSUMO_TINTAS = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<Object[]> getbyano(Integer ano) {
		Query query = entityManager.createNativeQuery(
				"SELECT c.ID_CONSUMO_TINTAS, c.ANO, c.SEMANA, c.DIA, c.DATA, c.ID_PLANO_DIARIO_PINTURA, c.DATA_CRIA, u.NOME_UTILIZADOR "
				+ "FROM PIN_MOV_CONSUMO_TINTAS c "
				+ "LEFT JOIN GER_UTILIZADORES u ON c.UTZ_CRIA = u.ID_UTILIZADOR "
				+ "WHERE c.ANO = :ano AND c.ATIVO = 1 "
				+ "ORDER BY c.SEMANA, c.DIA");
		query.setParameter("ano", ano);
		return query.getResultList();
	}

	public List<Object[]> getall() {
		Query query = entityManager.createNativeQuery(
				"SELECT c.ID_CONSUMO_TINTAS, c.ANO, c.SEMANA, c.DIA, c.DATA, c.ID_PLANO_DIARIO_PINTURA, c.DATA_CRIA, u.NOME_UTILIZADOR "
				+ "FROM PIN_MOV_CONSUMO_TINTAS c "
				+ "LEFT JOIN GER_UTILIZADORES u ON c.UTZ_CRIA = u.ID_UTILIZADOR "
				+ "WHERE c.ATIVO = 1 "
				+ "ORDER BY c.ANO DESC, c.SEMANA DESC, c.DIA DESC");
		return query.getResultList();
	}
}
