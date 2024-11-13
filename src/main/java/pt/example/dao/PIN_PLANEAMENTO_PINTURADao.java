package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_PLANEAMENTO_PINTURA;

public class PIN_PLANEAMENTO_PINTURADao extends GenericDaoJpaImpl<PIN_PLANEAMENTO_PINTURA,Integer> implements GenericDao<PIN_PLANEAMENTO_PINTURA,Integer> {
	public PIN_PLANEAMENTO_PINTURADao() {
		super(PIN_PLANEAMENTO_PINTURA.class);
	}
	
	public List<PIN_PLANEAMENTO_PINTURA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_PLANEAMENTO_PINTURA a where a.ID =:id");
		query.setParameter("id", id); 
		List<PIN_PLANEAMENTO_PINTURA> data = query.getResultList();
		return data;

	}
	
	public List<PIN_PLANEAMENTO_PINTURA> getall() {
		Query query = entityManager.createQuery("Select a from PIN_PLANEAMENTO_PINTURA a where a.INATIVO = 0 ORDER BY a.DATA desc,a.HORA desc");
		List<PIN_PLANEAMENTO_PINTURA> data = query.getResultList();
		return data;

	}


}
