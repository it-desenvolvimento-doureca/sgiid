package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_EPI_MOV_ENTREGA;

public class QUA_EPI_MOV_ENTREGADao extends GenericDaoJpaImpl<QUA_EPI_MOV_ENTREGA, Integer>
		implements GenericDao<QUA_EPI_MOV_ENTREGA, Integer> {
	public QUA_EPI_MOV_ENTREGADao() {
		super(QUA_EPI_MOV_ENTREGA.class);
	}

	public List<QUA_EPI_MOV_ENTREGA> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_ENTREGA a where a.ATIVO = 1");
		return query.getResultList();
	}

	public List<QUA_EPI_MOV_ENTREGA> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_ENTREGA a where a.ID_ENTREGA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	/** Entrega ja concluida de um pedido, para consulta. */
	public List<QUA_EPI_MOV_ENTREGA> getconcluida(Integer idPedido) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_ENTREGA a where a.ID_PEDIDO = :id " +
			"and a.ATIVO = 1 and (a.ESTADO = 'CONCLUIDA' or a.ESTADO is null)");
		query.setParameter("id", idPedido);
		return query.getResultList();
	}

	/** Rascunho de entrega de um pedido, se existir. */
	public List<QUA_EPI_MOV_ENTREGA> getrascunho(Integer idPedido) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_ENTREGA a where a.ID_PEDIDO = :id " +
			"and a.ATIVO = 1 and a.ESTADO = 'RASCUNHO'");
		query.setParameter("id", idPedido);
		return query.getResultList();
	}

	/**
	 * Etiquetas já gravadas numa entrega, para repor o ecrã do levantamento.
	 * C0 = etiqueta, C1 = artigo, C2 = armazém, C3 = localização, C4 = lote,
	 * C5 = nº lote, C6 = unidade, C7 = qtd na etiqueta, C8 = qtd a entregar,
	 * C9 = id linha pedido, C10 = id EPI, C11 = obriga devolução,
	 * C12 = devolvido, C13 = descrição do artigo
	 */
	public List<Object[]> getetiquetasdaentrega(Integer idEntrega) {
		String sql =
			"SELECT ee.ETQNUM AS C0, ee.PROREF AS C1, ee.LIECOD AS C2, ee.EMPCOD AS C3, " +
			" ee.ETQORILOT1 AS C4, ee.LOTNUMENR AS C5, ee.UNISTO AS C6, ee.QUANT AS C7, " +
			" ee.CONSUMIR AS C8, ee.ID_PEDIDO_LIN AS C9, ee.ID_EPI AS C10, " +
			" ee.OBRIGA_DEVOLUCAO AS C11, ee.DEVOLVIDO AS C12, e.DESCRICAO AS C13 " +
			"FROM QUA_EPI_MOV_ENTREGA_ETIQ ee " +
			"LEFT JOIN QUA_EPI_DIC_EPI e ON e.ID_EPI = ee.ID_EPI " +
			"WHERE ee.ID_ENTREGA = :id AND ee.ATIVO = 1 ORDER BY ee.ID_LINHA";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", idEntrega);
		return query.getResultList();
	}

	/** Apaga as etiquetas de um rascunho, antes de regravar. */
	public void apagaretiquetas(Integer idEntrega) {
		entityManager.createNativeQuery(
			"DELETE FROM QUA_EPI_MOV_ENTREGA_ETIQ WHERE ID_ENTREGA = :id")
			.setParameter("id", idEntrega).executeUpdate();
	}

	/**
	 * Pedidos de um local, para o ecrã de levantamento.
	 *
	 * Devolve todos os estados; o filtro é feito no ecrã. A elegibilidade
	 * para levantar vem em C7: ACEITE, agendado até hoje (as notas pedem
	 * "pedidos aceites anteriores àquela data") e ainda sem entrega.
	 *
	 * idLocal = 0 traz todos os locais a que o utilizador tem acesso.
	 * A restrição por responsabilidade é feita aqui e não no ecrã: passar 0
	 * não pode servir para ver locais alheios. Administradores veem tudo.
	 *
	 * C0 = id pedido, C1 = destinatário, C2 = sector, C3 = data agendada,
	 * C4 = hora agendada, C5 = nº de linhas, C6 = requerente,
	 * C7 = 1 se pode ser levantado, C8 = estado, C9 = 1 se já foi entregue,
	 * C10 = local de entrega
	 */
	public List<Object[]> getparalevantamento(Integer idLocal, Integer idUtz) {
		String sql =
			"SELECT p.ID_PEDIDO AS C0, " +
			// Codigo antes do nome, para desambiguar homonimos
			" RIGHT('000' + CAST(fd.COD_FUNCIONARIO AS VARCHAR(10)), " +
			"       CASE WHEN LEN(CAST(fd.COD_FUNCIONARIO AS VARCHAR(10))) > 3 " +
			"            THEN LEN(CAST(fd.COD_FUNCIONARIO AS VARCHAR(10))) ELSE 3 END) " +
			"   + ' - ' + fd.NOME AS C1, s.DES_SECTOR AS C2, " +
			" p.DATA_ENTREGA_AGENDADA AS C3, p.HORA_ENTREGA_AGENDADA AS C4, " +
			" (SELECT COUNT(*) FROM QUA_EPI_MOV_PEDIDO_LIN pl " +
			"   WHERE pl.ID_PEDIDO = p.ID_PEDIDO AND pl.ATIVO = 1) AS C5, " +
			" ISNULL(ur.COD_UTZ + ' - ', '') + ur.NOME_UTILIZADOR AS C6, " +
			" CASE WHEN p.ESTADO = 'ACEITE' " +
			"       AND (p.DATA_ENTREGA_AGENDADA IS NULL " +
			"            OR p.DATA_ENTREGA_AGENDADA <= CAST(GETDATE() AS DATE)) " +
			"       AND NOT EXISTS ( SELECT 1 FROM QUA_EPI_MOV_ENTREGA en " +
			"                        WHERE en.ID_PEDIDO = p.ID_PEDIDO AND en.ATIVO = 1 AND (en.ESTADO = 'CONCLUIDA' OR en.ESTADO IS NULL) ) " +
			"      THEN 1 ELSE 0 END AS C7, " +
			" p.ESTADO AS C8, " +
			" CASE WHEN EXISTS ( SELECT 1 FROM QUA_EPI_MOV_ENTREGA en " +
			"                    WHERE en.ID_PEDIDO = p.ID_PEDIDO AND en.ATIVO = 1 AND (en.ESTADO = 'CONCLUIDA' OR en.ESTADO IS NULL) ) " +
			"      THEN 1 ELSE 0 END AS C9, " +
			" gl.DESCRICAO AS C10, " +
			// Data efetiva da entrega, para a coluna Entregue
			" (SELECT MAX(en.DATA_HORA_ENTREGA) FROM QUA_EPI_MOV_ENTREGA en " +
			"   WHERE en.ID_PEDIDO = p.ID_PEDIDO AND en.ATIVO = 1 " +
			"     AND (en.ESTADO = 'CONCLUIDA' OR en.ESTADO IS NULL)) AS C11 " +
			"FROM QUA_EPI_MOV_PEDIDO p " +
			"LEFT JOIN RH_FUNCIONARIOS fd ON fd.COD_FUNCIONARIO = p.ID_DESTINATARIO " +
			"LEFT JOIN RH_SECTORES s ON s.COD_SECTOR = p.COD_SECTOR " +
			"LEFT JOIN GER_UTILIZADORES ur ON ur.ID_UTILIZADOR = p.ID_REQUERENTE " +
			"LEFT JOIN QUA_EPI_LOCAL el ON el.ID = p.ID_LOCAL_ENTREGA " +
			"LEFT JOIN GER_LOCAIS gl ON gl.ID = el.ID_LOCAL " +
			"WHERE p.ATIVO = 1 AND p.ID_LOCAL_ENTREGA IS NOT NULL " +
			"  AND (:local = 0 OR p.ID_LOCAL_ENTREGA = :local) " +
			// Só locais a que o utilizador tem acesso; admin vê todos
			"  AND ( EXISTS ( SELECT 1 FROM GER_UTILIZADORES ua " +
			"                 WHERE ua.ID_UTILIZADOR = :utz AND ua.ADMIN = 1 ) " +
			"     OR EXISTS ( SELECT 1 FROM QUA_EPI_LOCAL_RESP r " +
			"                 WHERE r.ID_UTZ = :utz AND r.ATIVO = 1 " +
			"                   AND r.ID_LOCAL = p.ID_LOCAL_ENTREGA ) ) " +
			"ORDER BY C7 DESC, p.DATA_ENTREGA_AGENDADA, p.ID_PEDIDO";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("local", idLocal == null ? 0 : idLocal);
		query.setParameter("utz", idUtz);
		return query.getResultList();
	}

	/**
	 * Consumos entre datas.
	 *
	 * Uma linha por EPI entregue, com colaborador, sector e etiqueta. Os
	 * filtros de colaborador e de EPI são opcionais: passa-se 0 para "todos",
	 * evitando montar SQL diferente conforme os filtros escolhidos.
	 *
	 * C0 = data da entrega, C1 = nº pedido, C2 = colaborador, C3 = sector,
	 * C4 = família, C5 = EPI, C6 = etiqueta, C7 = quantidade, C8 = local,
	 * C9 = obriga devolução, C10 = devolvido
	 */
	public List<Object[]> getconsumos(String dataIni, String dataFim, Integer codFuncionario, Integer idEpi) {
		String sql =
			"SELECT en.DATA_HORA_ENTREGA AS C0, p.ID_PEDIDO AS C1, fd.NOME AS C2, s.DES_SECTOR AS C3, " +
			" f.DESCRICAO AS C4, e.DESCRICAO AS C5, ee.ETQNUM AS C6, ee.CONSUMIR AS C7, " +
			" l.DESCRICAO AS C8, ee.OBRIGA_DEVOLUCAO AS C9, ee.DEVOLVIDO AS C10 " +
			"FROM QUA_EPI_MOV_ENTREGA_ETIQ ee " +
			"INNER JOIN QUA_EPI_MOV_ENTREGA en ON en.ID_ENTREGA = ee.ID_ENTREGA AND en.ATIVO = 1 AND en.ESTADO = 'CONCLUIDA' " +
			"INNER JOIN QUA_EPI_MOV_PEDIDO p ON p.ID_PEDIDO = en.ID_PEDIDO " +
			"LEFT JOIN RH_FUNCIONARIOS fd ON fd.COD_FUNCIONARIO = p.ID_DESTINATARIO " +
			"LEFT JOIN RH_SECTORES s ON s.COD_SECTOR = p.COD_SECTOR " +
			"LEFT JOIN QUA_EPI_DIC_EPI e ON e.ID_EPI = ee.ID_EPI " +
			"LEFT JOIN QUA_EPI_DIC_FAMILIA f ON f.ID_FAMILIA = e.ID_FAMILIA " +
			"LEFT JOIN QUA_EPI_LOCAL el ON el.ID = en.ID_LOCAL " +
			"LEFT JOIN GER_LOCAIS l ON l.ID = el.ID_LOCAL " +
			"WHERE ee.ATIVO = 1 " +
			"  AND en.DATA_HORA_ENTREGA >= :dataIni AND en.DATA_HORA_ENTREGA < DATEADD(DAY, 1, :dataFim) " +
			"  AND (:func = 0 OR p.ID_DESTINATARIO = :func) " +
			"  AND (:epi = 0 OR ee.ID_EPI = :epi) " +
			"ORDER BY en.DATA_HORA_ENTREGA DESC";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("dataIni", dataIni);
		query.setParameter("dataFim", dataFim);
		query.setParameter("func", codFuncionario == null ? 0 : codFuncionario);
		query.setParameter("epi", idEpi == null ? 0 : idEpi);
		return query.getResultList();
	}

	/**
	 * Histórico de EPIs de uma pessoa: tudo o que já lhe foi entregue, com a
	 * indicação do que tem atualmente (a entrega mais recente de cada EPI).
	 *
	 * C0 = data, C1 = família, C2 = EPI, C3 = etiqueta, C4 = quantidade,
	 * C5 = 1 se é a entrega atual desse EPI, C6 = obriga devolução, C7 = devolvido
	 */
	public List<Object[]> gethistoricopessoa(Integer codFuncionario) {
		String sql =
			"SELECT en.DATA_HORA_ENTREGA AS C0, f.DESCRICAO AS C1, e.DESCRICAO AS C2, ee.ETQNUM AS C3, " +
			" ee.CONSUMIR AS C4, " +
			" CASE WHEN ROW_NUMBER() OVER (PARTITION BY ee.ID_EPI ORDER BY en.DATA_HORA_ENTREGA DESC) = 1 " +
			"      THEN 1 ELSE 0 END AS C5, " +
			" ee.OBRIGA_DEVOLUCAO AS C6, ee.DEVOLVIDO AS C7 " +
			"FROM QUA_EPI_MOV_ENTREGA_ETIQ ee " +
			"INNER JOIN QUA_EPI_MOV_ENTREGA en ON en.ID_ENTREGA = ee.ID_ENTREGA AND en.ATIVO = 1 AND en.ESTADO = 'CONCLUIDA' " +
			"INNER JOIN QUA_EPI_MOV_PEDIDO p ON p.ID_PEDIDO = en.ID_PEDIDO " +
			"LEFT JOIN QUA_EPI_DIC_EPI e ON e.ID_EPI = ee.ID_EPI " +
			"LEFT JOIN QUA_EPI_DIC_FAMILIA f ON f.ID_FAMILIA = e.ID_FAMILIA " +
			"WHERE ee.ATIVO = 1 AND p.ID_DESTINATARIO = :func " +
			"ORDER BY en.DATA_HORA_ENTREGA DESC";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("func", codFuncionario);
		return query.getResultList();
	}

	/**
	 * Stocks de todos os EPIs, lidos do SILVER na própria query.
	 * Inclui a validade mais próxima, para assinalar o que está a expirar.
	 *
	 * C0 = id EPI, C1 = EPI, C2 = família, C3 = artigo Silver, C4 = stock,
	 * C5 = validade mais próxima, C6 = nº de lotes já expirados
	 */
	public List<Object[]> getstocks() {
		String sql =
			"SELECT a.ID_EPI AS C0, a.DESCRICAO AS C1, f.DESCRICAO AS C2, a.PROREF AS C3, " +
			" ISNULL((SELECT SUM(s.LOTQTE) FROM SILVER.dbo.STODET s WHERE s.PROREF = a.PROREF), 0) AS C4, " +
			" (SELECT MIN(l.LOTDATVLF) FROM SILVER.dbo.STODET s " +
			"    LEFT JOIN SILVER.dbo.STOLOT l ON l.PROREF = s.PROREF AND l.LOTREF = s.LOTREF " +
			"  WHERE s.PROREF = a.PROREF AND s.LOTQTE > 0) AS C5, " +
			" (SELECT COUNT(*) FROM SILVER.dbo.STODET s " +
			"    LEFT JOIN SILVER.dbo.STOLOT l ON l.PROREF = s.PROREF AND l.LOTREF = s.LOTREF " +
			"  WHERE s.PROREF = a.PROREF AND s.LOTQTE > 0 AND l.LOTDATVLF < GETDATE()) AS C6 " +
			"FROM QUA_EPI_DIC_EPI a " +
			"LEFT JOIN QUA_EPI_DIC_FAMILIA f ON f.ID_FAMILIA = a.ID_FAMILIA " +
			"WHERE a.ATIVO = 1 AND a.PROREF IS NOT NULL AND a.PROREF <> '' " +
			"ORDER BY f.DESCRICAO, a.DESCRICAO";
		return entityManager.createNativeQuery(sql).getResultList();
	}
}
