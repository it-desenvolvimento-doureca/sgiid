package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_FERIADOS;

public class GER_FERIADOSDao extends GenericDaoJpaImpl<GER_FERIADOS, Integer>
		implements GenericDao<GER_FERIADOS, Integer> {
	public GER_FERIADOSDao() {
		super(GER_FERIADOS.class);
	}

	public List<GER_FERIADOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_FERIADOS a where a.ID_FERIADO = :id");
		query.setParameter("id", id);
		List<GER_FERIADOS> data = query.getResultList();
		return data;

	}

	public List<GER_FERIADOS> getall() {

		Query query = entityManager.createQuery("Select a from GER_FERIADOS a order by DATA DESC");
		List<GER_FERIADOS> data = query.getResultList();
		return data;

	}
	
	public List<GER_FERIADOS> getbyAno(Integer ano) {

		Query query = entityManager.createQuery("Select a from GER_FERIADOS a where a.ANO = :ano");
		query.setParameter("ano", ano);
		List<GER_FERIADOS> data = query.getResultList();
		return data;

	}
}
