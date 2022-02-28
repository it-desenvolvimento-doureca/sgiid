package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.QUA_DERROGACOES_EQUIPA;

public class QUA_DERROGACOES_EQUIPADao extends GenericDaoJpaImpl<QUA_DERROGACOES_EQUIPA, Integer>
		implements GenericDao<QUA_DERROGACOES_EQUIPA, Integer> {
	public QUA_DERROGACOES_EQUIPADao() {
		super(QUA_DERROGACOES_EQUIPA.class);
	}

	public List<QUA_DERROGACOES_EQUIPA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from QUA_DERROGACOES_EQUIPA a where a.ID_DERROGACAO = :id ");
		query.setParameter("id", id);
		List<QUA_DERROGACOES_EQUIPA> data = query.getResultList();
		return data;

	}

	public List<QUA_DERROGACOES_EQUIPA> getall() {

		Query query = entityManager.createQuery("Select a from QUA_DERROGACOES_EQUIPA a ");
		List<QUA_DERROGACOES_EQUIPA> data = query.getResultList();
		return data;

	}

}
