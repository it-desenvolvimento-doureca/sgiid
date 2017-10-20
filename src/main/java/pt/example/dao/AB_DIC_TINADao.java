package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_BANHO;
import pt.example.entity.AB_DIC_TINA;
import pt.example.entity.GER_FORNECEDOR;

public class AB_DIC_TINADao extends GenericDaoJpaImpl<AB_DIC_TINA,Integer> implements GenericDao<AB_DIC_TINA,Integer> {
	public AB_DIC_TINADao() {
		super(AB_DIC_TINA.class);
	}
	
	public List<AB_DIC_TINA> getbyid(Integer id,Integer linha) {

		Query query = entityManager.createQuery("Select a,b from AB_DIC_TINA a, AB_DIC_LINHA b where  a.ID_LINHA = b.ID_LINHA  and a.ID_TINA = :id and ((not :linha != 0) or (a.ID_LINHA = :linha)) ");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<AB_DIC_TINA> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_TINA> getall(Integer linha) {
		Query query = entityManager.createQuery("Select a,b, "
				+ "CASE WHEN a.ID_ZONA IS NULL THEN '' ELSE (select c.NOME_ZONA from AB_DIC_ZONA c where a.ID_ZONA = c.ID_ZONA )END as nome_zona "
				+ "from AB_DIC_TINA a,AB_DIC_LINHA b where a.ID_LINHA = b.ID_LINHA and a.INATIVO !=1 and ((not :linha != 0) or (a.ID_LINHA = :linha))");
		query.setParameter("linha", linha);
		List<AB_DIC_TINA> data = query.getResultList();
		return data;

	}


}
