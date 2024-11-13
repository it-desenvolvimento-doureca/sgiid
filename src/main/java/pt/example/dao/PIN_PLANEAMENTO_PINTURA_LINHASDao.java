package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_PLANEAMENTO_PINTURA_LINHAS;

public class PIN_PLANEAMENTO_PINTURA_LINHASDao extends GenericDaoJpaImpl<PIN_PLANEAMENTO_PINTURA_LINHAS,Integer> implements GenericDao<PIN_PLANEAMENTO_PINTURA_LINHAS,Integer> {
	public PIN_PLANEAMENTO_PINTURA_LINHASDao() {
		super(PIN_PLANEAMENTO_PINTURA_LINHAS.class);
	}
	
	public List<PIN_PLANEAMENTO_PINTURA_LINHAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a  from PIN_PLANEAMENTO_PINTURA_LINHAS a where a.ID_CAB = :id order by a.ORDEM");
		query.setParameter("id", id); 
		List<PIN_PLANEAMENTO_PINTURA_LINHAS> data = query.getResultList();
		return data;

	}
	
	public List<PIN_PLANEAMENTO_PINTURA_LINHAS> getall() {
		Query query = entityManager.createQuery("Select a,b,c " 
				+ "from PIN_PLANEAMENTO_PINTURA_LINHAS a,PIN_DIC_POTES b,PIN_DIC_CORES_ACABAMENTOS c "
				+ "where a.ID_POTE = b.ID and a.ID_COR_ACABAMENTO = c.ID order by a.ORDEM");
		
		List<PIN_PLANEAMENTO_PINTURA_LINHAS> data = query.getResultList();
		return data;

	}


}
