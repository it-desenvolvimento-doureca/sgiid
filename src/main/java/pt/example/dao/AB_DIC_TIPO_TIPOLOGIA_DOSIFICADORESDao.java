package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES;

public class AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORESDao extends GenericDaoJpaImpl<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES,Integer> implements GenericDao<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES,Integer> {
	public AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORESDao() {
		super(AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES.class);
	}

	
	public List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES a where a.ID = :id ");
		query.setParameter("id", id);
		List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES a where a.ATIVO = 1 ");
		List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES> data = query.getResultList();
		return data;

	}

}
