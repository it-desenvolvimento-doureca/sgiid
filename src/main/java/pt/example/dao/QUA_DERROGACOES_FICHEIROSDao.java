package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.QUA_DERROGACOES_FICHEIROS;

public class QUA_DERROGACOES_FICHEIROSDao extends GenericDaoJpaImpl<QUA_DERROGACOES_FICHEIROS, Integer>
		implements GenericDao<QUA_DERROGACOES_FICHEIROS, Integer> {
	public QUA_DERROGACOES_FICHEIROSDao() {
		super(QUA_DERROGACOES_FICHEIROS.class);
	}

	public List<QUA_DERROGACOES_FICHEIROS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a,b from QUA_DERROGACOES_FICHEIROS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_DERROGACAO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<QUA_DERROGACOES_FICHEIROS> data = query.getResultList();
		return data;

	}
	
	public List<QUA_DERROGACOES_FICHEIROS> getbyid2(Integer id) {

		Query query = entityManager.createQuery("Select a from QUA_DERROGACOES_FICHEIROS a where a.ID_DERROGACAO = :id ");
		query.setParameter("id", id);
		List<QUA_DERROGACOES_FICHEIROS> data = query.getResultList();
		return data;

	}
		

	public List<QUA_DERROGACOES_FICHEIROS> getall() {

		Query query = entityManager.createQuery("Select a from QUA_DERROGACOES_FICHEIROS a ");
		List<QUA_DERROGACOES_FICHEIROS> data = query.getResultList();
		return data;

	}

}
