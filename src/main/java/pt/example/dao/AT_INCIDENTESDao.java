package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_INCIDENTES;

public class AT_INCIDENTESDao extends GenericDaoJpaImpl<AT_INCIDENTES,Integer> implements GenericDao<AT_INCIDENTES,Integer> {
	public AT_INCIDENTESDao() {
		super(AT_INCIDENTES.class);
	}

	public List<AT_INCIDENTES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AT_INCIDENTES a where a.ID_INCIDENTE = :id ");
		query.setParameter("id", id);
		List<AT_INCIDENTES> data = query.getResultList();
		return data;

	}

	public List<AT_INCIDENTES> getall() {

		Query query = entityManager.createQuery(
			"Select a from AT_INCIDENTES a where (a.INATIVO is null or a.INATIVO = false) order by a.DATA_CRIA DESC ");
		List<AT_INCIDENTES> data = query.getResultList();
		return data;

	}

	/**
	 * Proximo numero de ficha para o ano indicado. A numeracao e sequencial por
	 * ano e reinicia em cada ano, como no formulario em papel ("Ficha n.o __ / ano").
	 */
	public Integer proximoNumero(Integer ano) {

		Query query = entityManager.createQuery(
			"Select coalesce(max(a.NUMERO), 0) from AT_INCIDENTES a where a.ANO = :ano ");
		query.setParameter("ano", ano);
		Object r = query.getSingleResult();
		return (r == null) ? 1 : ((Number) r).intValue() + 1;

	}

}
