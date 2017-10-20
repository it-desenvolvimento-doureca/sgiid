package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_FORNECEDOR;

public class GER_FORNECEDORDao extends GenericDaoJpaImpl<GER_FORNECEDOR, Integer>
		implements GenericDao<GER_FORNECEDOR, Integer> {
	public GER_FORNECEDORDao() {
		super(GER_FORNECEDOR.class);
	}

	public List<GER_FORNECEDOR> getbyid(Integer id) {
		Query query = entityManager
				.createQuery("Select a from GER_FORNECEDOR a where a.ID_FORNECEDOR = :id and a.INATIVO != 1 ");
		query.setParameter("id", id);
		List<GER_FORNECEDOR> data = query.getResultList();
		return data;

	}

	public List<GER_FORNECEDOR> getAll() {
		Query query = entityManager.createQuery("Select a from GER_FORNECEDOR a where a.INATIVO != 1 ");
		List<GER_FORNECEDOR> data = query.getResultList();
		return data;

	}

	public List<GER_FORNECEDOR> verifica_num_fornece(Integer id, Integer num) {
		Query query = entityManager.createQuery(
				"Select a from GER_FORNECEDOR a where a.NUM_FORNECEDOR = :num and a.ID_FORNECEDOR != :id  and a.INATIVO != 1 ");
		query.setParameter("id", id);
		query.setParameter("num", num);
		List<GER_FORNECEDOR> data = query.getResultList();
		return data;

	}

	public List<GER_FORNECEDOR> verifica_num(Integer num) {
		Query query = entityManager.createQuery(
				"Select a from GER_FORNECEDOR a where a.NUM_FORNECEDOR = :num and a.INATIVO != 1 ");
		query.setParameter("num", num);
		List<GER_FORNECEDOR> data = query.getResultList();
		return data;

	}

	public List<Integer> getMaxID() {
		Query query = entityManager.createQuery("Select max(a.NUM_FORNECEDOR) from GER_FORNECEDOR a");
		List<Integer> utz = query.getResultList();
		return utz;

	}

}
