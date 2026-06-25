package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DERROGACOES_ACOES;

public class QUA_MC_DERROGACOES_ACOESDao extends GenericDaoJpaImpl<QUA_MC_DERROGACOES_ACOES, Integer>
		implements GenericDao<QUA_MC_DERROGACOES_ACOES, Integer> {
	public QUA_MC_DERROGACOES_ACOESDao() {
		super(QUA_MC_DERROGACOES_ACOES.class);
	}

	public List<QUA_MC_DERROGACOES_ACOES> getbyDerrogacao(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_MC_DERROGACOES_ACOES a where a.ID_DERROGACAO = :id and a.ATIVO = true order by a.NUM");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
