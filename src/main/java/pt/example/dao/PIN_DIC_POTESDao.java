package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_POTES;

public class PIN_DIC_POTESDao extends GenericDaoJpaImpl<PIN_DIC_POTES,Integer> implements GenericDao<PIN_DIC_POTES,Integer> {
	public PIN_DIC_POTESDao() {
		super(PIN_DIC_POTES.class);
	}
	
	public List<PIN_DIC_POTES> getbyid(Integer id,Integer linha) {

		Query query = entityManager.createQuery("Select a,b from PIN_DIC_POTES a, AB_DIC_LINHA b where  a.ID_LINHA = b.ID_LINHA and a.ID = :id and ((not :linha != 0) or (a.ID_LINHA = :linha)) ");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<PIN_DIC_POTES> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_POTES> getall(Integer linha) {
		Query query = entityManager.createQuery("Select a,b,c, "
				+ "CASE WHEN a.ID_TIPO_ACABAMENTO IS NULL THEN '' ELSE (select d.NOME from PIN_DIC_TIPO_ACABAMENTO d where a.ID_TIPO_ACABAMENTO = d.ID )END as nome_tipo "
				+ "from PIN_DIC_POTES a,AB_DIC_LINHA b,PIN_DIC_CABINES c where a.ID_LINHA = b.ID_LINHA and a.ID_CABINE = c.ID and a.INATIVO !=1 and ((not :linha != 0) or (a.ID_LINHA = :linha)) order by a.ID");
		query.setParameter("linha", linha);
		List<PIN_DIC_POTES> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_POTES> getall2() {
		Query query = entityManager.createQuery("Select a,c, "
				+ "CASE WHEN a.ID_TIPO_ACABAMENTO IS NULL THEN '' ELSE (select d.NOME from PIN_DIC_TIPO_ACABAMENTO d where a.ID_TIPO_ACABAMENTO = d.ID )END as nome_tipo "
				+ "from PIN_DIC_POTES a,PIN_DIC_CABINES c where a.ID_CABINE = c.ID and a.INATIVO !=1  order by ISNULL(ORDEM,999)");

		List<PIN_DIC_POTES> data = query.getResultList();
		return data;

	}
	
	public Integer updateordem(Integer ordem, Integer id) {

		Query query = entityManager
				.createNativeQuery("UPDATE PIN_DIC_POTES set ORDEM = :ordem  where ID = :id ");
		query.setParameter("ordem", ordem);
		query.setParameter("id", id);

		Integer data = query.executeUpdate();
		return data;

	}

}
