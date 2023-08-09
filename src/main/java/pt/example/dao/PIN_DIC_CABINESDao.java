package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_CABINES;

public class PIN_DIC_CABINESDao extends GenericDaoJpaImpl<PIN_DIC_CABINES, Integer>
		implements GenericDao<PIN_DIC_CABINES, Integer> {
	public PIN_DIC_CABINESDao() {
		super(PIN_DIC_CABINES.class);
	}

	public List<PIN_DIC_CABINES> getbyid(Integer id, Integer linha) {

		Query query = entityManager.createQuery(
				"Select a,b from PIN_DIC_CABINES a, AB_DIC_LINHA b where a.ID = :id and a.ID_LINHA = b.ID_LINHA and a.INATIVO != 1 and ((not :linha != 0) or (a.ID_LINHA = :linha))");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<PIN_DIC_CABINES> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_CABINES> getall(Integer linha) {
		Query query = entityManager.createQuery(
				"Select a,b from PIN_DIC_CABINES a,AB_DIC_LINHA b where  a.ID_LINHA = b.ID_LINHA and a.INATIVO != 1 and ((not :linha != 0) or (a.ID_LINHA = :linha))");
		query.setParameter("linha", linha);
		List<PIN_DIC_CABINES> data = query.getResultList();
		return data;

	}

	
	/*public List<PIN_DIC_CABINES> getAllLINHAbylinha_tipo(Integer linha, Integer id, String tipo) {
		String quer = "";
		if (tipo.equals("N")) {
			quer = " and MANUTENCAONAOPROGRAMADA = 1";
		} else if (tipo.equals("R")) {
			quer = " and MANUTENCAOREPOSICAO = 1";
		}
		Query query = entityManager.createQuery("Select a,b,c from PIN_DIC_CABINES a,AB_DIC_LINHA b, AB_DIC_TINA c "
				+ "where  a.ID_LINHA = b.ID_LINHA and a.ID_TINA = c.ID_TINA  and a.INATIVO != 1  and a.ESTADO = 1 "
				+ "and ((not :linha != 0) or (a.ID_LINHA = :linha))" + quer);
		// query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<PIN_DIC_CABINES> data = query.getResultList();
		return data;

	}*/

}
