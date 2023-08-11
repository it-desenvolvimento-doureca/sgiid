package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_PREPARACAO_ETIQ;

public class PIN_MOV_PREPARACAO_ETIQDao extends GenericDaoJpaImpl<PIN_MOV_PREPARACAO_ETIQ, Integer>
		implements GenericDao<PIN_MOV_PREPARACAO_ETIQ, Integer> {
	public PIN_MOV_PREPARACAO_ETIQDao() {
		super(PIN_MOV_PREPARACAO_ETIQ.class);
	}

	public List<PIN_MOV_PREPARACAO_ETIQ> getbyid_manu(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_PREPARACAO_ETIQ a where a.ID_PREPARACAO_LIN = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<PIN_MOV_PREPARACAO_ETIQ> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO_ETIQ> getbyid(Integer id) {

		Query query = entityManager
				.createQuery("Select a from PIN_MOV_PREPARACAO_ETIQ a where a.ID_MOV_PREP_ETIQUETA = :id");
		query.setParameter("id", id);
		List<PIN_MOV_PREPARACAO_ETIQ> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO_ETIQ> getbyRef(Integer id, String ref) {

		Query query = entityManager.createQuery("Select a, ("
				+ "select SUM(c.CONSUMIR) from PIN_MOV_PREPARACAO_ETIQ c where c.ID_PREPARACAO_LIN in "
				+ "(select d.ID_PREPARACAO_LIN from PIN_MOV_PREPARACAO_LINHA d where d.ID_PREPARACAO_CAB = :id) and c.PROREF = :ref "
				+ ") as c from PIN_MOV_PREPARACAO_ETIQ a where a.ID_PREPARACAO_LIN in "
				+ "(select b.ID_PREPARACAO_LIN from PIN_MOV_PREPARACAO_LINHA b where b.ID_PREPARACAO_CAB = :id) and a.PROREF = :ref order by a.DATA_CRIA");
		query.setParameter("id", id);
		query.setParameter("ref", ref);
		List<PIN_MOV_PREPARACAO_ETIQ> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO_ETIQ> getbyRef2(Integer id) {

		Query query = entityManager.createQuery("Select a, ("
				+ "select SUM(c.CONSUMIR) from PIN_MOV_PREPARACAO_ETIQ c where c.ID_PREPARACAO_LIN = :id "
				+ ") as c from PIN_MOV_PREPARACAO_ETIQ a where a.ID_PREPARACAO_LIN = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<PIN_MOV_PREPARACAO_ETIQ> data = query.getResultList();
		return data;

	}

}
