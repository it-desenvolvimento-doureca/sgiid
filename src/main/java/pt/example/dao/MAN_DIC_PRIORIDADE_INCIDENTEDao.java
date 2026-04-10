package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_DIC_PRIORIDADE_INCIDENTE;

public class MAN_DIC_PRIORIDADE_INCIDENTEDao
		extends GenericDaoJpaImpl<MAN_DIC_PRIORIDADE_INCIDENTE, Integer>
		implements GenericDao<MAN_DIC_PRIORIDADE_INCIDENTE, Integer> {

	public MAN_DIC_PRIORIDADE_INCIDENTEDao() {
		super(MAN_DIC_PRIORIDADE_INCIDENTE.class);
	}

	public List<MAN_DIC_PRIORIDADE_INCIDENTE> getall() {
		Query query = entityManager.createQuery(
				"Select a from MAN_DIC_PRIORIDADE_INCIDENTE a where a.ATIVO = true order by a.PRIORIDADE");
		return query.getResultList();
	}

	public List<MAN_DIC_PRIORIDADE_INCIDENTE> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"select a from MAN_DIC_PRIORIDADE_INCIDENTE a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<MAN_DIC_PRIORIDADE_INCIDENTE> getbyprioridade(Integer prioridade) {
		Query query = entityManager.createQuery(
				"select a from MAN_DIC_PRIORIDADE_INCIDENTE a where a.PRIORIDADE = :p");
		query.setParameter("p", prioridade);
		return query.getResultList();
	}
}
