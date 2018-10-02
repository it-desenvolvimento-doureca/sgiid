package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_STOCK;

public class RC_MOV_RECLAMACAO_STOCKDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_STOCK, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_STOCK, Integer> {
	public RC_MOV_RECLAMACAO_STOCKDao() {
		super(RC_MOV_RECLAMACAO_STOCK.class);
	}

	public List<RC_MOV_RECLAMACAO_STOCK> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_STOCK a where a.ID_RECLAMACAO = :id order by a.LIECOD");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_STOCK> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_STOCK> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_STOCK a ");
		List<RC_MOV_RECLAMACAO_STOCK> data = query.getResultList();
		return data;

	}

}
