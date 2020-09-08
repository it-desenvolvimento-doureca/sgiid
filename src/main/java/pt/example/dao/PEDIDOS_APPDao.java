package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PEDIDOS_APP;

public class PEDIDOS_APPDao extends GenericDaoJpaImpl<PEDIDOS_APP, Integer> implements GenericDao<PEDIDOS_APP, Integer> {
	public PEDIDOS_APPDao() {
		super(PEDIDOS_APP.class);
	}

	public List<PEDIDOS_APP> getbyid(String id) {

		Query query = entityManager.createQuery(
				"Select a,(select NOME_UTILIZADOR from GER_UTILIZADORES where ID_UTILIZADOR = a.UTZ_PEDIDO) as NOME from PEDIDOS_APP a where a.ID_PEDIDO  = :id ");
		query.setParameter("id", id);
		List<PEDIDOS_APP> data = query.getResultList();
		return data;

	}

	public List<PEDIDOS_APP> getall() {

		Query query = entityManager.createQuery("Select a from PEDIDOS_APP a ");
		List<PEDIDOS_APP> data = query.getResultList();
		return data;

	}

}
