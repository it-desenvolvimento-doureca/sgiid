package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_BANHO;

public class AB_DIC_BANHODao extends GenericDaoJpaImpl<AB_DIC_BANHO, Integer>
		implements GenericDao<AB_DIC_BANHO, Integer> {
	public AB_DIC_BANHODao() {
		super(AB_DIC_BANHO.class);
	}

	public List<AB_DIC_BANHO> getbyid(Integer id, Integer linha) {

		Query query = entityManager.createQuery("Select a,b from AB_DIC_BANHO a, AB_DIC_LINHA b where a.ID_BANHO = :id and a.ID_LINHA = b.ID_LINHA and a.INATIVO != 1 and ((not :linha != 0) or (a.ID_LINHA = :linha))" );
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<AB_DIC_BANHO> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_BANHO> getall( Integer linha) {
		Query query = entityManager.createQuery("Select a,b,c from AB_DIC_BANHO a,AB_DIC_LINHA b, AB_DIC_TINA c where  a.ID_LINHA = b.ID_LINHA and a.ID_TINA = c.ID_TINA and a.INATIVO != 1  and ((not :linha != 0) or (a.ID_LINHA = :linha))");
		query.setParameter("linha", linha);
		List<AB_DIC_BANHO> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_BANHO> getAllLINHAbylinha(Integer linha, Integer id) {
		Query query = entityManager.createQuery("Select a,b,c from AB_DIC_BANHO a,AB_DIC_LINHA b, AB_DIC_TINA c where  a.ID_LINHA = b.ID_LINHA and a.ID_TINA = c.ID_TINA  and a.INATIVO != 1  and a.ESTADO = 1 and ((not :linha != 0) or (a.ID_LINHA = :linha))");
		//query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<AB_DIC_BANHO> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_BANHO> getAllLINHAbylinha_tipo(Integer linha, Integer id,String tipo) {
		String quer = "";
		if(tipo.equals("N")){
			quer = " and MANUTENCAONAOPROGRAMADA = 1";
		}else if(tipo.equals("R")){
			quer = " and MANUTENCAOREPOSICAO = 1";
		}
		Query query = entityManager.createQuery("Select a,b,c from AB_DIC_BANHO a,AB_DIC_LINHA b, AB_DIC_TINA c "
				+ "where  a.ID_LINHA = b.ID_LINHA and a.ID_TINA = c.ID_TINA  and a.INATIVO != 1  and a.ESTADO = 1 "
				+ "and ((not :linha != 0) or (a.ID_LINHA = :linha))"+ quer);
		//query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<AB_DIC_BANHO> data = query.getResultList();
		return data;

	}
	
}
