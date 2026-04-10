package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_DIC_PRIORIDADE_INTERVENCAO;

public class MAN_DIC_PRIORIDADE_INTERVENCAODao
		extends GenericDaoJpaImpl<MAN_DIC_PRIORIDADE_INTERVENCAO, Integer>
		implements GenericDao<MAN_DIC_PRIORIDADE_INTERVENCAO, Integer> {

	public MAN_DIC_PRIORIDADE_INTERVENCAODao() {
		super(MAN_DIC_PRIORIDADE_INTERVENCAO.class);
	}

	public List<MAN_DIC_PRIORIDADE_INTERVENCAO> getall() {
		Query query = entityManager.createQuery(
				"Select a from MAN_DIC_PRIORIDADE_INTERVENCAO a where a.ATIVO = true order by a.NIVEL");
		return query.getResultList();
	}

	public List<MAN_DIC_PRIORIDADE_INTERVENCAO> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"select a from MAN_DIC_PRIORIDADE_INTERVENCAO a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
