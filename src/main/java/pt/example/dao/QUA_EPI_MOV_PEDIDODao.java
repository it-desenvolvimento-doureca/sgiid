package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_EPI_MOV_PEDIDO;

public class QUA_EPI_MOV_PEDIDODao extends GenericDaoJpaImpl<QUA_EPI_MOV_PEDIDO, Integer>
		implements GenericDao<QUA_EPI_MOV_PEDIDO, Integer> {
	public QUA_EPI_MOV_PEDIDODao() {
		super(QUA_EPI_MOV_PEDIDO.class);
	}

	public List<QUA_EPI_MOV_PEDIDO> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_PEDIDO a where a.ATIVO = 1 order by a.ID_PEDIDO desc");
		return query.getResultList();
	}

	public List<QUA_EPI_MOV_PEDIDO> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_PEDIDO a where a.ID_PEDIDO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * Lista de pedidos visíveis para um utilizador.
	 *
	 * Regra de visibilidade (aplicada aqui, nunca no frontend):
	 *  - os pedidos que o próprio criou;
	 *  - os de locais onde é responsável de EPI;
	 *  - os que ainda não têm local atribuído, se for responsável de algum
	 *    local. O local só é definido na aceitação, por isso sem esta terceira
	 *    condição um pedido acabado de submeter não seria visto por ninguém e
	 *    nunca poderia ser aceite.
	 *
	 * C0 = id, C1 = data, C2 = estado, C3 = nome requerente,
	 * C4 = nome destinatário, C5 = sector, C6 = local de entrega,
	 * C7 = data agendada, C8 = hora agendada, C9 = nº de linhas
	 */
	public List<Object[]> getlista(Integer idUtilizador) {
		String sql =
			"SELECT p.ID_PEDIDO AS C0, p.DATA_PEDIDO AS C1, p.ESTADO AS C2, " +
			// Codigo antes do nome, para desambiguar homonimos. O do funcionario
			// e inteiro na BD; os zeros a esquerda sao convencao de apresentacao.
			" ISNULL(ur.COD_UTZ + ' - ', '') + ur.NOME_UTILIZADOR AS C3, " +
			" RIGHT('000' + CAST(fd.COD_FUNCIONARIO AS VARCHAR(10)), " +
			"       CASE WHEN LEN(CAST(fd.COD_FUNCIONARIO AS VARCHAR(10))) > 3 " +
			"            THEN LEN(CAST(fd.COD_FUNCIONARIO AS VARCHAR(10))) ELSE 3 END) " +
			"   + ' - ' + fd.NOME AS C4, " +
			" s.DES_SECTOR AS C5, l.DESCRICAO AS C6, " +
			" p.DATA_ENTREGA_AGENDADA AS C7, p.HORA_ENTREGA_AGENDADA AS C8, " +
			" (SELECT COUNT(*) FROM QUA_EPI_MOV_PEDIDO_LIN pl " +
			"   WHERE pl.ID_PEDIDO = p.ID_PEDIDO AND pl.ATIVO = 1) AS C9 " +
			"FROM QUA_EPI_MOV_PEDIDO p " +
			"LEFT JOIN GER_UTILIZADORES ur ON ur.ID_UTILIZADOR = p.ID_REQUERENTE " +
			"LEFT JOIN RH_FUNCIONARIOS fd ON fd.COD_FUNCIONARIO = p.ID_DESTINATARIO " +
			"LEFT JOIN RH_SECTORES s ON s.COD_SECTOR = p.COD_SECTOR " +
			// ID_LOCAL_ENTREGA -> QUA_EPI_LOCAL.ID -> GER_LOCAIS.ID
			"LEFT JOIN QUA_EPI_LOCAL el ON el.ID = p.ID_LOCAL_ENTREGA " +
			"LEFT JOIN GER_LOCAIS l ON l.ID = el.ID_LOCAL " +
			"WHERE p.ATIVO = 1 " +
			// Administradores veem tudo. Verificado na base de dados e não pelo
			// frontend, que poderia ser contornado.
			"  AND ( EXISTS ( SELECT 1 FROM GER_UTILIZADORES ua " +
			"                 WHERE ua.ID_UTILIZADOR = :utz AND ua.ADMIN = 1 ) " +
			"     OR p.ID_REQUERENTE = :utz " +
			// responsável do local do pedido
			"     OR EXISTS ( SELECT 1 FROM QUA_EPI_LOCAL_RESP r " +
			"                 WHERE r.ID_UTZ = :utz AND r.ATIVO = 1 " +
			"                   AND r.ID_LOCAL = p.ID_LOCAL_ENTREGA ) " +
			// Rede de segurança: pedidos sem local (sector sem local EPI
			// definido) continuam visíveis a qualquer responsável, senão
			// ficariam sem ninguém que os pudesse aceitar.
			"     OR ( p.ID_LOCAL_ENTREGA IS NULL " +
			"          AND EXISTS ( SELECT 1 FROM QUA_EPI_LOCAL_RESP r " +
			"                       WHERE r.ID_UTZ = :utz AND r.ATIVO = 1 ) ) ) " +
			"ORDER BY p.ID_PEDIDO DESC";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("utz", idUtilizador);
		return query.getResultList();
	}

	/**
	 * Sector e turno do utilizador autenticado, para pré-preencher o pedido.
	 * O join utilizador->funcionário não é FK: passa por
	 * GER_UTILIZADORES.COD_UTZ = RH_FUNCIONARIOS.COD_FUNC_ORIGEM, com o caso
	 * especial do COD_UTZ '9889' replicado dos restantes DAOs do projeto.
	 *
	 * C0 = cod sector, C1 = descrição sector, C2 = cod turno, C3 = cod funcionário
	 */
	public List<Object[]> getsectordoutilizador(Integer idUtilizador) {
		String sql =
			"SELECT TOP 1 f.COD_SECTOR AS C0, s.DES_SECTOR AS C1, s.COD_TURNO AS C2, f.COD_FUNCIONARIO AS C3 " +
			"FROM GER_UTILIZADORES u " +
			"INNER JOIN RH_FUNCIONARIOS f " +
			"  ON CASE WHEN u.COD_UTZ = '9889' THEN 0 ELSE u.COD_UTZ END = f.COD_FUNC_ORIGEM " +
			"LEFT JOIN RH_SECTORES s ON s.COD_SECTOR = f.COD_SECTOR " +
			"WHERE u.ID_UTILIZADOR = :utz";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("utz", idUtilizador);
		return query.getResultList();
	}

	/**
	 * Sector do funcionario destinatario, para o pedido feito a partir da
	 * ficha dele. Mesmas colunas do sector do utilizador, para o ecra tratar
	 * os dois casos da mesma maneira.
	 *
	 * C0 = cod sector, C1 = descricao sector, C2 = cod turno, C3 = cod funcionario
	 */
	public List<Object[]> getsectordofuncionario(Integer codFuncionario) {
		String sql =
			"SELECT TOP 1 f.COD_SECTOR AS C0, s.DES_SECTOR AS C1, s.COD_TURNO AS C2, f.COD_FUNCIONARIO AS C3 " +
			"FROM RH_FUNCIONARIOS f " +
			"LEFT JOIN RH_SECTORES s ON s.COD_SECTOR = f.COD_SECTOR " +
			"WHERE f.COD_FUNCIONARIO = :func";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("func", codFuncionario);
		return query.getResultList();
	}

	/**
	 * Dados para o email de notificação do requerente.
	 * Alimenta os campos {CAMPO} do template em GER_EVENTOS_CONF.
	 *
	 * A lista de EPIs é concatenada aqui (STUFF + FOR XML PATH, que funciona
	 * em qualquer versão do SQL Server) para não obrigar a uma segunda chamada.
	 *
	 * C0 = nome requerente, C1 = email requerente, C2 = nome destinatário,
	 * C3 = sector, C4 = local de entrega, C5 = data agendada, C6 = hora, C7 = EPIs
	 */
	public List<Object[]> getdadosnotificacao(Integer idPedido) {
		String sql =
			"SELECT ur.NOME_UTILIZADOR AS C0, ur.EMAIL AS C1, fd.NOME AS C2, s.DES_SECTOR AS C3, " +
			" l.DESCRICAO AS C4, p.DATA_ENTREGA_AGENDADA AS C5, p.HORA_ENTREGA_AGENDADA AS C6, " +
			" STUFF(( SELECT ', ' + e.DESCRICAO + ' (x' + CAST(pl.QTD_PEDIDA AS NVARCHAR(10)) + ')' " +
			"         FROM QUA_EPI_MOV_PEDIDO_LIN pl " +
			"         LEFT JOIN QUA_EPI_DIC_EPI e ON e.ID_EPI = pl.ID_EPI " +
			"         WHERE pl.ID_PEDIDO = p.ID_PEDIDO AND pl.ATIVO = 1 " +
			"         FOR XML PATH('') ), 1, 2, '') AS C7 " +
			"FROM QUA_EPI_MOV_PEDIDO p " +
			"LEFT JOIN GER_UTILIZADORES ur ON ur.ID_UTILIZADOR = p.ID_REQUERENTE " +
			"LEFT JOIN RH_FUNCIONARIOS fd ON fd.COD_FUNCIONARIO = p.ID_DESTINATARIO " +
			"LEFT JOIN RH_SECTORES s ON s.COD_SECTOR = p.COD_SECTOR " +
			"LEFT JOIN QUA_EPI_LOCAL el ON el.ID = p.ID_LOCAL_ENTREGA " +
			"LEFT JOIN GER_LOCAIS l ON l.ID = el.ID_LOCAL " +
			"WHERE p.ID_PEDIDO = :id";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", idPedido);
		return query.getResultList();
	}

	/**
	 * Local EPI provável para um sector.
	 *
	 * RH_SECTORES.local aponta para GER_LOCAIS; se esse local estiver entre
	 * os locais EPI, devolve o QUA_EPI_LOCAL.ID correspondente. Serve para o
	 * pedido nascer já encaminhado para os responsáveis certos, em vez de
	 * ficar visível a todos até ser aceite.
	 *
	 * Devolve vazio quando o sector não tem local, ou quando o local do
	 * sector não é um local de EPI.
	 */
	public List<Object[]> getlocalepidosector(Integer codSector) {
		String sql =
			"SELECT TOP 1 el.ID AS C0, l.DESCRICAO AS C1 " +
			"FROM RH_SECTORES s " +
			"INNER JOIN GER_LOCAIS l ON CAST(l.ID AS NVARCHAR(20)) = CAST(s.LOCAL AS NVARCHAR(20)) " +
			"INNER JOIN QUA_EPI_LOCAL el ON el.ID_LOCAL = l.ID AND el.ATIVO = 1 " +
			"WHERE s.COD_SECTOR = :sector";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("sector", codSector);
		return query.getResultList();
	}

	/**
	 * Funcionários ativos de um sector - dropdown de destinatário.
	 * C0 = cod funcionário, C1 = nome
	 */
	public List<Object[]> getfuncionariosdosector(Integer codSector) {
		// ISNULL: há funcionários com ATIVO por preencher, e sem isto ficavam
		// de fora do dropdown - incluindo o próprio requerente.
		String sql =
			"SELECT f.COD_FUNCIONARIO AS C0, f.NOME AS C1 " +
			"FROM RH_FUNCIONARIOS f " +
			"WHERE f.COD_SECTOR = :sector AND ISNULL(f.ATIVO, 1) = 1 ORDER BY f.NOME";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("sector", codSector);
		return query.getResultList();
	}
}
