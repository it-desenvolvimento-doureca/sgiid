package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.QUA_DERROGACOES_PLANOS_ACCOES;

public class QUA_DERROGACOES_PLANOS_ACCOESDao extends GenericDaoJpaImpl<QUA_DERROGACOES_PLANOS_ACCOES, Integer>
		implements GenericDao<QUA_DERROGACOES_PLANOS_ACCOES, Integer> {
	public QUA_DERROGACOES_PLANOS_ACCOESDao() {
		super(QUA_DERROGACOES_PLANOS_ACCOES.class);
	}

	public List<QUA_DERROGACOES_PLANOS_ACCOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from QUA_DERROGACOES_PLANOS_ACCOES a where a.ID_DERROGACAO = :id order by ordem,id ");
		query.setParameter("id", id);
		List<QUA_DERROGACOES_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}

	public List<QUA_DERROGACOES_PLANOS_ACCOES> getbyidplano(Integer id) {

		Query query = entityManager.createQuery("Select a from QUA_DERROGACOES_PLANOS_ACCOES a where a.ID = :id ");
		query.setParameter("id", id);
		List<QUA_DERROGACOES_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}
	
	public List<QUA_DERROGACOES_PLANOS_ACCOES> getall() {

		Query query = entityManager.createQuery("Select a from QUA_DERROGACOES_PLANOS_ACCOES a ");
		List<QUA_DERROGACOES_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}

}
