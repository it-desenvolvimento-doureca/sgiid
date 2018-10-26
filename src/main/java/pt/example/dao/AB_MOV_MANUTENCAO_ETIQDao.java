package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_MOV_MANUTENCAO_ETIQ;

public class AB_MOV_MANUTENCAO_ETIQDao extends GenericDaoJpaImpl<AB_MOV_MANUTENCAO_ETIQ, Integer>
		implements GenericDao<AB_MOV_MANUTENCAO_ETIQ, Integer> {
	public AB_MOV_MANUTENCAO_ETIQDao() {
		super(AB_MOV_MANUTENCAO_ETIQ.class);
	}

	public List<AB_MOV_MANUTENCAO_ETIQ> getbyid_manu(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from AB_MOV_MANUTENCAO_ETIQ a where a.ID_MANUTENCAO_LIN = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<AB_MOV_MANUTENCAO_ETIQ> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO_ETIQ> getbyid(Integer id) {

		Query query = entityManager
				.createQuery("Select a from AB_MOV_MANUTENCAO_ETIQ a where a.ID_MOV_MANU_ETIQUETA = :id");
		query.setParameter("id", id);
		List<AB_MOV_MANUTENCAO_ETIQ> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO_ETIQ> getbyRef(Integer id, String ref) {

		Query query = entityManager.createQuery("Select a, ("
				+ "select SUM(c.CONSUMIR) from AB_MOV_MANUTENCAO_ETIQ c where c.ID_MANUTENCAO_LIN in "
				+ "(select d.ID_MANUTENCAO_LIN from AB_MOV_MANUTENCAO_LINHA d where d.ID_MANUTENCAO_CAB = :id) and c.PROREF = :ref "
				+ ") as c from AB_MOV_MANUTENCAO_ETIQ a where a.ID_MANUTENCAO_LIN in "
				+ "(select b.ID_MANUTENCAO_LIN from AB_MOV_MANUTENCAO_LINHA b where b.ID_MANUTENCAO_CAB = :id) and a.PROREF = :ref order by a.DATA_CRIA");
		query.setParameter("id", id);
		query.setParameter("ref", ref);
		List<AB_MOV_MANUTENCAO_ETIQ> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO_ETIQ> getbyRef2(Integer id) {

		Query query = entityManager.createQuery("Select a, ("
				+ "select SUM(c.CONSUMIR) from AB_MOV_MANUTENCAO_ETIQ c where c.ID_MANUTENCAO_LIN = :id "
				+ ") as c from AB_MOV_MANUTENCAO_ETIQ a where a.ID_MANUTENCAO_LIN = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<AB_MOV_MANUTENCAO_ETIQ> data = query.getResultList();
		return data;

	}

}
