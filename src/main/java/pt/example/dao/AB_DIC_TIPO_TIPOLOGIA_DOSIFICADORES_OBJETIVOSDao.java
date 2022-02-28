package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS;

public class AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOSDao extends GenericDaoJpaImpl<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS,Integer> implements GenericDao<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS,Integer> {
	public AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOSDao() {
		super(AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS.class);
	}

	
	public List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS a where a.ID_TIPO_TIPOLOGIA_DOSIFICADORES = :id ");
		query.setParameter("id", id);
		List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS a ");
		List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS> data = query.getResultList();
		return data;

	}

}
