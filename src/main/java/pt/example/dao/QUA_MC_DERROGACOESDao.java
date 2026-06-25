package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DERROGACOES;

public class QUA_MC_DERROGACOESDao extends GenericDaoJpaImpl<QUA_MC_DERROGACOES, Integer>
		implements GenericDao<QUA_MC_DERROGACOES, Integer> {
	public QUA_MC_DERROGACOESDao() {
		super(QUA_MC_DERROGACOES.class);
	}

	public List<QUA_MC_DERROGACOES> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_MC_DERROGACOES a where a.ATIVO = true order by a.DATA desc, a.ID_DERROGACAO desc");
		return query.getResultList();
	}

	public List<QUA_MC_DERROGACOES> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DERROGACOES a where a.ID_DERROGACAO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
