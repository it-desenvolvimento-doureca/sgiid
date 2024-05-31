package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.FIN_DIC_FILTROS;
 

public class FIN_DIC_FILTROSDao extends GenericDaoJpaImpl<FIN_DIC_FILTROS, Integer> implements GenericDao<FIN_DIC_FILTROS, Integer> {
	public FIN_DIC_FILTROSDao() {
		super(FIN_DIC_FILTROS.class);
	}

	public List<FIN_DIC_FILTROS> getall() {

		Query query = entityManager.createQuery("Select a from FIN_DIC_FILTROS a ");

		List<FIN_DIC_FILTROS> utz = query.getResultList();
		return utz;

	}
	 	
	public List<FIN_DIC_FILTROS> getbyId(Integer id) {
		Query query = entityManager.createQuery(
				"select a from FIN_DIC_FILTROS a where a.ID = :id");
		query.setParameter("id", id);

		List<FIN_DIC_FILTROS> data = query.getResultList();
		return data;

	}
	
	public List<FIN_DIC_FILTROS> getbyTipo(Integer tipo) {
		Query query = entityManager.createQuery(
				"select a from FIN_DIC_FILTROS a where a.TIPO_ANALISE = :tipo");
		query.setParameter("tipo", tipo);

		List<FIN_DIC_FILTROS> data = query.getResultList();
		return data;

	}
}
