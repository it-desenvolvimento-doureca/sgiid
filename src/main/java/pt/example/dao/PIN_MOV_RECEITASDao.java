package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_RECEITAS;

public class PIN_MOV_RECEITASDao extends GenericDaoJpaImpl<PIN_MOV_RECEITAS, Integer>
		implements GenericDao<PIN_MOV_RECEITAS, Integer> {
	public PIN_MOV_RECEITASDao() {
		super(PIN_MOV_RECEITAS.class);
	}

	public List<PIN_MOV_RECEITAS> getbyid(Integer id, Integer linha) {

		Query query = entityManager.createQuery(
				"Select a,b from PIN_MOV_RECEITAS a, AB_DIC_LINHA b where a.ID = :id and a.LINHA = b.ID_LINHA and a.INATIVO != 1 and ((not :linha != 0) or (a.LINHA = :linha))");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_RECEITAS> getall(Integer linha) {
		Query query = entityManager.createQuery(
				"Select a,b from PIN_MOV_RECEITAS a,AB_DIC_LINHA b where  a.LINHA = b.ID_LINHA and a.INATIVO != 1 and ((not :linha != 0) or (a.LINHA = :linha))");
		query.setParameter("linha", linha);
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}



}
