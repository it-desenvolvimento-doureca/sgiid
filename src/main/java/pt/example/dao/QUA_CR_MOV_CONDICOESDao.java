package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_MOV_CONDICOES;

public class QUA_CR_MOV_CONDICOESDao extends GenericDaoJpaImpl<QUA_CR_MOV_CONDICOES, Integer>
		implements GenericDao<QUA_CR_MOV_CONDICOES, Integer> {
	public QUA_CR_MOV_CONDICOESDao() {
		super(QUA_CR_MOV_CONDICOES.class);
	}

	public List<QUA_CR_MOV_CONDICOES> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_CONDICOES a where a.ATIVO = true order by a.ORDEM");
		return query.getResultList();
	}

	public List<QUA_CR_MOV_CONDICOES> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_MOV_CONDICOES a where a.ID_CONDICAO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_MOV_CONDICOES> getbyCondicoesCab(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_CONDICOES a where a.ID_CONDICOES_CAB = :id and a.ATIVO = true order by a.ORDEM");
		query.setParameter("id", id);
		return query.getResultList();
	}
}