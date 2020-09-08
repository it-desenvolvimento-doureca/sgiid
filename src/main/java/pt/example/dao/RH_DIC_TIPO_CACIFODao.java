package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_TIPO_CACIFO;

public class RH_DIC_TIPO_CACIFODao extends GenericDaoJpaImpl<RH_DIC_TIPO_CACIFO, Integer>
		implements GenericDao<RH_DIC_TIPO_CACIFO, Integer> {
	public RH_DIC_TIPO_CACIFODao() {
		super(RH_DIC_TIPO_CACIFO.class);
	}

	public List<RH_DIC_TIPO_CACIFO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_TIPO_CACIFO a where a.COD_TIPO = :id ");
		query.setParameter("id", id);
		List<RH_DIC_TIPO_CACIFO> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_TIPO_CACIFO> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_TIPO_CACIFO a ");
		List<RH_DIC_TIPO_CACIFO> data = query.getResultList();
		return data;

	}

}
