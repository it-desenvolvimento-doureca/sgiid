package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_EPI_MOV_PEDIDO_HIST;

public class QUA_EPI_MOV_PEDIDO_HISTDao extends GenericDaoJpaImpl<QUA_EPI_MOV_PEDIDO_HIST, Integer>
		implements GenericDao<QUA_EPI_MOV_PEDIDO_HIST, Integer> {
	public QUA_EPI_MOV_PEDIDO_HISTDao() {
		super(QUA_EPI_MOV_PEDIDO_HIST.class);
	}

	public List<QUA_EPI_MOV_PEDIDO_HIST> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_PEDIDO_HIST a where a.ATIVO = 1");
		return query.getResultList();
	}

	public List<QUA_EPI_MOV_PEDIDO_HIST> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_PEDIDO_HIST a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * Histórico de um pedido, com o nome do utilizador resolvido.
	 * C0 = data/hora, C1 = nome, C2 = estado anterior, C3 = estado novo, C4 = observações
	 */
	public List<Object[]> getbypedido(Integer idPedido) {
		String sql =
			"SELECT a.DATA_HORA AS C0, u.NOME_UTILIZADOR AS C1, a.ESTADO_ANTERIOR AS C2, " +
			" a.ESTADO_NOVO AS C3, a.OBSERVACOES AS C4 " +
			"FROM QUA_EPI_MOV_PEDIDO_HIST a " +
			"LEFT JOIN GER_UTILIZADORES u ON u.ID_UTILIZADOR = a.ID_UTILIZADOR " +
			"WHERE a.ATIVO = 1 AND a.ID_PEDIDO = :id ORDER BY a.DATA_HORA DESC";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", idPedido);
		return query.getResultList();
	}
}
