package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_DIC_LIMITES_ENCOMENDA;

public class GER_DIC_LIMITES_ENCOMENDADao extends GenericDaoJpaImpl<GER_DIC_LIMITES_ENCOMENDA, Integer>
		implements GenericDao<GER_DIC_LIMITES_ENCOMENDA, Integer> {
	public GER_DIC_LIMITES_ENCOMENDADao() {
		super(GER_DIC_LIMITES_ENCOMENDA.class);
	}

	public List<GER_DIC_LIMITES_ENCOMENDA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a  from GER_DIC_LIMITES_ENCOMENDA a where a.ID_LIMITE = :id ");
		query.setParameter("id", id);
		List<GER_DIC_LIMITES_ENCOMENDA> data = query.getResultList();
		return data;

	}

	public List<GER_DIC_LIMITES_ENCOMENDA> getall() {

		Query query = entityManager.createQuery("Select a from GER_DIC_LIMITES_ENCOMENDA a");
		List<GER_DIC_LIMITES_ENCOMENDA> data = query.getResultList();
		return data;

	}

}
