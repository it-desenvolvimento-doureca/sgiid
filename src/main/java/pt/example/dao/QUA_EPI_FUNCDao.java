package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_EPI_FUNC;

public class QUA_EPI_FUNCDao extends GenericDaoJpaImpl<QUA_EPI_FUNC, Integer>
		implements GenericDao<QUA_EPI_FUNC, Integer> {
	public QUA_EPI_FUNCDao() {
		super(QUA_EPI_FUNC.class);
	}

	public List<QUA_EPI_FUNC> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_FUNC a where a.ATIVO = 1");
		return query.getResultList();
	}

	public List<QUA_EPI_FUNC> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_FUNC a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * Linhas do separador EPI's da ficha do funcionário.
	 *
	 * Parte dos EPIs das famílias configuradas no sector do funcionário (LEFT
	 * JOIN às atribuições) para que os EPIs ainda por atribuir apareçam na
	 * mesma: a tabela é derivada do sector, não de entrada livre. Uma família
	 * com dois EPIs no dicionário dá duas linhas - todos os EPIs da família são
	 * listados, cada um atribuível em separado.
	 *
	 * Famílias do sector ainda sem EPIs no dicionário aparecem como uma linha
	 * com o EPI a null.
	 *
	 * C5 = 1 quando existe atribuição de uma família que já não pertence ao
	 * sector (funcionário mudou de sector) - a linha continua visível mas fica
	 * assinalada em vez de desaparecer.
	 *
	 * C0 = id atribuição (null se por preencher), C1 = id família, C2 = descrição família,
	 * C3 = id EPI, C4 = descrição EPI, C5 = fora do sector, C6 = tamanho,
	 * C7 = duração de uso (dias), C8 = obriga devolução
	 */
	public List<Object[]> getbyfuncionario(Integer codFuncionario) {
		String sql =
			"SELECT x.C0, x.C1, x.C2, x.C3, x.C4, x.C5, x.C6, x.C7, x.C8 FROM ( " +
			// atribuições ativas: uma linha por EPI atribuído
			"  SELECT ef.ID AS C0, f.ID_FAMILIA AS C1, f.DESCRICAO AS C2, ef.ID_EPI AS C3, " +
			"         e.DESCRICAO AS C4, " +
			"         CASE WHEN EXISTS ( SELECT 1 FROM RH_SECTORES_EPI_FAMILIA sf " +
			"                            WHERE sf.COD_SECTOR = fu.COD_SECTOR " +
			"                              AND sf.ID_FAMILIA = ef.ID_FAMILIA AND sf.ATIVO = 1 ) " +
			"              THEN 0 ELSE 1 END AS C5, " +
			"         ef.TAMANHO AS C6, f.DURACAO_USO_DIAS AS C7, e.OBRIGA_DEVOLUCAO AS C8 " +
			"  FROM QUA_EPI_FUNC ef " +
			"  INNER JOIN RH_FUNCIONARIOS fu ON fu.COD_FUNCIONARIO = ef.COD_FUNCIONARIO " +
			"  INNER JOIN QUA_EPI_DIC_FAMILIA f ON f.ID_FAMILIA = ef.ID_FAMILIA " +
			"  LEFT JOIN QUA_EPI_DIC_EPI e ON e.ID_EPI = ef.ID_EPI " +
			"  WHERE ef.COD_FUNCIONARIO = :id AND ef.ATIVO = 1 " +
			"  UNION ALL " +
			// famílias do sector ainda sem atribuição: uma linha por família,
			// para se escolher o EPI - não uma linha por cada EPI da família
			"  SELECT NULL AS C0, f.ID_FAMILIA AS C1, f.DESCRICAO AS C2, NULL AS C3, " +
			"         NULL AS C4, 0 AS C5, NULL AS C6, f.DURACAO_USO_DIAS AS C7, NULL AS C8 " +
			"  FROM RH_FUNCIONARIOS fu " +
			"  INNER JOIN RH_SECTORES_EPI_FAMILIA sf ON sf.COD_SECTOR = fu.COD_SECTOR AND sf.ATIVO = 1 " +
			"  INNER JOIN QUA_EPI_DIC_FAMILIA f ON f.ID_FAMILIA = sf.ID_FAMILIA AND f.ATIVO = 1 " +
			"  WHERE fu.COD_FUNCIONARIO = :id " +
			"    AND NOT EXISTS ( SELECT 1 FROM QUA_EPI_FUNC ef " +
			"                     WHERE ef.COD_FUNCIONARIO = fu.COD_FUNCIONARIO " +
			"                       AND ef.ID_FAMILIA = f.ID_FAMILIA AND ef.ATIVO = 1 ) " +
			") x ORDER BY x.C5, x.C2, x.C4";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", codFuncionario);
		return query.getResultList();
	}
}
