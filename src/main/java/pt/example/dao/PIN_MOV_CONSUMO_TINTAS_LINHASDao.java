package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_CONSUMO_TINTAS_LINHAS;

public class PIN_MOV_CONSUMO_TINTAS_LINHASDao extends GenericDaoJpaImpl<PIN_MOV_CONSUMO_TINTAS_LINHAS, Integer>
		implements GenericDao<PIN_MOV_CONSUMO_TINTAS_LINHAS, Integer> {

	public PIN_MOV_CONSUMO_TINTAS_LINHASDao() {
		super(PIN_MOV_CONSUMO_TINTAS_LINHAS.class);
	}

	public List<PIN_MOV_CONSUMO_TINTAS_LINHAS> getbyidcab(Integer idConsumoTintas) {
		Query query = entityManager.createQuery(
				"Select l from PIN_MOV_CONSUMO_TINTAS_LINHAS l "
				+ "where l.ID_CONSUMO_TINTAS = :id ORDER BY l.ORDEM");
		query.setParameter("id", idConsumoTintas);
		return query.getResultList();
	}

	public void gravarLinhas(List<PIN_MOV_CONSUMO_TINTAS_LINHAS> linhas) {
		for (PIN_MOV_CONSUMO_TINTAS_LINHAS linha : linhas) {
			if (linha.getID_CONSUMO_TINTAS_LINHA() == null || linha.getID_CONSUMO_TINTAS_LINHA() == 0) {
				entityManager.persist(linha);
			} else {
				entityManager.merge(linha);
			}
		}
	}

	public void populateLinhas(Integer idConsumoTintas, Integer idPlanoDiario, Integer dia) {
		Query query = entityManager.createNativeQuery(
				"EXEC SP_CREATE_CONSUMO_TINTAS_LINHAS :idConsumoTintas, :idPlanoDiario, :dia");
		query.setParameter("idConsumoTintas", idConsumoTintas);
		query.setParameter("idPlanoDiario", idPlanoDiario);
		query.setParameter("dia", dia);
		query.executeUpdate();
	}
}
