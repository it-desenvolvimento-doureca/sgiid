package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_EPI_MOV_PEDIDO_LIN;

public class QUA_EPI_MOV_PEDIDO_LINDao extends GenericDaoJpaImpl<QUA_EPI_MOV_PEDIDO_LIN, Integer>
		implements GenericDao<QUA_EPI_MOV_PEDIDO_LIN, Integer> {
	public QUA_EPI_MOV_PEDIDO_LINDao() {
		super(QUA_EPI_MOV_PEDIDO_LIN.class);
	}

	public List<QUA_EPI_MOV_PEDIDO_LIN> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_PEDIDO_LIN a where a.ATIVO = 1");
		return query.getResultList();
	}

	public List<QUA_EPI_MOV_PEDIDO_LIN> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_PEDIDO_LIN a where a.ID_LINHA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * Linhas de um pedido, com EPI e família resolvidos.
	 * C0 = id linha, C1 = id EPI, C2 = descrição EPI, C3 = família,
	 * C4 = tamanho, C5 = qtd, C6 = motivo de atraso, C7 = obriga devolução,
	 * C8 = artigo Silver (usado no levantamento para validar a etiqueta lida)
	 */
	public List<Object[]> getbypedido(Integer idPedido) {
		String sql =
			"SELECT a.ID_LINHA AS C0, a.ID_EPI AS C1, e.DESCRICAO AS C2, f.DESCRICAO AS C3, " +
			" a.TAMANHO AS C4, a.QTD_PEDIDA AS C5, a.MOTIVO_ATRASO AS C6, e.OBRIGA_DEVOLUCAO AS C7, " +
			" e.PROREF AS C8 " +
			"FROM QUA_EPI_MOV_PEDIDO_LIN a " +
			"LEFT JOIN QUA_EPI_DIC_EPI e ON e.ID_EPI = a.ID_EPI " +
			"LEFT JOIN QUA_EPI_DIC_FAMILIA f ON f.ID_FAMILIA = e.ID_FAMILIA " +
			"WHERE a.ATIVO = 1 AND a.ID_PEDIDO = :id ORDER BY f.DESCRICAO, e.DESCRICAO";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", idPedido);
		return query.getResultList();
	}

	/**
	 * Avisos de uma linha do pedido, para o funcionario e o EPI escolhidos.
	 *
	 * C0 = data da ultima entrega, C1 = duracao de uso da familia,
	 * C2 = tem EPI por devolver, C3 = duracao ultrapassada, C4 = dias desde a entrega
	 *
	 * Sobre o C2: o DEVOLVIDO gravado numa entrega diz que o EPI *anterior*
	 * foi devolvido no momento daquela entrega - nao que o entregue nessa
	 * linha ja voltou. Testar DEVOLVIDO = 0 nunca dava aviso nenhum, porque
	 * o levantamento so fecha com a devolucao confirmada. O que interessa e
	 * se a ultima entrega obriga a devolver: se obriga, a pessoa tem esse EPI
	 * em maos e tem de o entregar ao levantar o novo.
	 */
	public List<Object[]> validalinha(Integer codFuncionario, Integer idEpi) {
		String sql =
			"SELECT ult.DATA_HORA_ENTREGA AS C0, " +
			" ISNULL(f.DURACAO_USO_DIAS, 0) AS C1, " +
			" CASE WHEN ISNULL(ult.OBRIGA_DEVOLUCAO, 0) = 1 THEN 1 ELSE 0 END AS C2, " +
			" CASE WHEN ISNULL(f.DURACAO_USO_DIAS, 0) > 0 AND ult.DATA_HORA_ENTREGA IS NOT NULL " +
			"       AND DATEDIFF(DAY, ult.DATA_HORA_ENTREGA, GETDATE()) > f.DURACAO_USO_DIAS " +
			"      THEN 1 ELSE 0 END AS C3, " +
			" CASE WHEN ult.DATA_HORA_ENTREGA IS NULL THEN NULL " +
			"      ELSE DATEDIFF(DAY, ult.DATA_HORA_ENTREGA, GETDATE()) END AS C4 " +
			"FROM QUA_EPI_DIC_EPI e " +
			"LEFT JOIN QUA_EPI_DIC_FAMILIA f ON f.ID_FAMILIA = e.ID_FAMILIA " +
			"OUTER APPLY ( " +
			"  SELECT TOP 1 en.DATA_HORA_ENTREGA, ee.OBRIGA_DEVOLUCAO " +
			"  FROM QUA_EPI_MOV_ENTREGA_ETIQ ee " +
			"  INNER JOIN QUA_EPI_MOV_ENTREGA en ON en.ID_ENTREGA = ee.ID_ENTREGA AND en.ATIVO = 1 AND en.ESTADO = 'CONCLUIDA' " +
			"  INNER JOIN QUA_EPI_MOV_PEDIDO pp ON pp.ID_PEDIDO = en.ID_PEDIDO " +
			"  WHERE ee.ATIVO = 1 AND ee.ID_EPI = :epi AND pp.ID_DESTINATARIO = :func " +
			"  ORDER BY en.DATA_HORA_ENTREGA DESC " +
			") ult " +
			"WHERE e.ID_EPI = :epi";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("epi", idEpi);
		query.setParameter("func", codFuncionario);
		return query.getResultList();
	}
}
