package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.FIN_DIC_TIPO_MOVIMENTO_STOCK;
 

public class FIN_DIC_TIPO_MOVIMENTO_STOCKDao extends GenericDaoJpaImpl<FIN_DIC_TIPO_MOVIMENTO_STOCK, Integer> implements GenericDao<FIN_DIC_TIPO_MOVIMENTO_STOCK, Integer> {
	public FIN_DIC_TIPO_MOVIMENTO_STOCKDao() {
		super(FIN_DIC_TIPO_MOVIMENTO_STOCK.class);
	}

	public List<FIN_DIC_TIPO_MOVIMENTO_STOCK> getall() {

		Query query = entityManager.createQuery("Select a from FIN_DIC_TIPO_MOVIMENTO_STOCK a ");

		List<FIN_DIC_TIPO_MOVIMENTO_STOCK> utz = query.getResultList();
		return utz;

	}
	 	
	public List<FIN_DIC_TIPO_MOVIMENTO_STOCK> getbyId(Integer id) {
		Query query = entityManager.createQuery(
				"select a from FIN_DIC_TIPO_MOVIMENTO_STOCK a where a.ID = :id");
		query.setParameter("id", id);

		List<FIN_DIC_TIPO_MOVIMENTO_STOCK> data = query.getResultList();
		return data;

	}
}
