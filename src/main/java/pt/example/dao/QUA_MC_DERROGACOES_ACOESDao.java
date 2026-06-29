package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DERROGACOES_ACOES;

public class QUA_MC_DERROGACOES_ACOESDao extends GenericDaoJpaImpl<QUA_MC_DERROGACOES_ACOES, Integer>
		implements GenericDao<QUA_MC_DERROGACOES_ACOES, Integer> {
	public QUA_MC_DERROGACOES_ACOESDao() {
		super(QUA_MC_DERROGACOES_ACOES.class);
	}

	public List<Object[]> getbyDerrogacao(Integer id) {
		// Devolve a ação + o estado VIVO da tarefa associada (sub-módulo 'DMC'), se existir.
		Query query = entityManager.createQuery(
			"Select a, (select t.ESTADO from GT_MOV_TAREFAS t where t.ID_CAMPO = a.ID and t.ID_MODULO = 5 and t.SUB_MODULO = 'DMC') as TAREFA_ESTADO "
			+ "from QUA_MC_DERROGACOES_ACOES a where a.ID_DERROGACAO = :id and a.ATIVO = true order by a.NUM");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
