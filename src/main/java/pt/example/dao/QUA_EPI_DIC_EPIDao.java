package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_EPI_DIC_EPI;

public class QUA_EPI_DIC_EPIDao extends GenericDaoJpaImpl<QUA_EPI_DIC_EPI, Integer>
		implements GenericDao<QUA_EPI_DIC_EPI, Integer> {
	public QUA_EPI_DIC_EPIDao() {
		super(QUA_EPI_DIC_EPI.class);
	}

	public List<QUA_EPI_DIC_EPI> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_DIC_EPI a where a.ATIVO = 1 order by a.DESCRICAO");
		return query.getResultList();
	}

	public List<QUA_EPI_DIC_EPI> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_DIC_EPI a where a.ID_EPI = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	// EPIs de uma família - dropdown do separador da ficha do funcionário
	public List<QUA_EPI_DIC_EPI> getbyfamilia(Integer idFamilia) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_DIC_EPI a where a.ATIVO = 1 and a.ID_FAMILIA = :id order by a.DESCRICAO");
		query.setParameter("id", idFamilia);
		return query.getResultList();
	}

	/**
	 * Lista para a grelha, com a família resolvida e o stock atual.
	 * O stock vem do SILVER na própria query (SILVER.dbo.STODET), como já se
	 * faz no PIN_MOV_PREPARACAO_LINHADao - evita uma segunda chamada.
	 *
	 * C0 = id, C1 = descrição, C2 = id família, C3 = família,
	 * C4 = obriga devolução, C5 = artigo Silver, C6 = descrição do artigo,
	 * C7 = stock atual (null quando não há PROREF associado)
	 */
	public List<Object[]> getlista() {
	    String sql =
	        "SELECT a.ID_EPI AS C0, a.DESCRICAO AS C1, a.ID_FAMILIA AS C2, f.DESCRICAO AS C3, " +
	        " a.OBRIGA_DEVOLUCAO AS C4, a.PROREF AS C5, a.PROREF_DESCRICAO AS C6, s.QTD_TOTAL AS C7 " +
	        "FROM QUA_EPI_DIC_EPI a " +
	        "LEFT JOIN QUA_EPI_DIC_FAMILIA f ON f.ID_FAMILIA = a.ID_FAMILIA " +
	        "LEFT JOIN ( " +
	        "   SELECT PROREF, SUM(LOTQTE) AS QTD_TOTAL " +
	        "   FROM SILVER.dbo.STODET " +
	        "   WHERE PROREF IS NOT NULL AND PROREF <> '' " +
	        "   GROUP BY PROREF " +
	        ") s ON s.PROREF = CAST(a.PROREF AS VARCHAR(34)) " +
	        "WHERE a.ATIVO = 1 " +
	        "ORDER BY f.DESCRICAO, a.DESCRICAO";
	    return entityManager.createNativeQuery(sql).getResultList();
	}

	/**
	 * Stock de um EPI por armazém e lote, também direto ao SILVER.
	 * LIECOD = armazém; EMPCOD = localização dentro do armazém.
	 *
	 * C0 = armazém, C1 = localização, C2 = lote, C3 = qtd, C4 = qtd afeta,
	 * C5 = validade, C6 = data de criação do lote
	 */
	public List<Object[]> getstockdetalhe(Integer idEpi) {
		// STOLOT tem PROREF, por isso a junção por referência + lote é mais
		// segura do que por INDNUMENR (que na documentação aparece duplicado).
		// Sem lote atribuído em STODET não há linha em STOLOT: a validade
		// vem nula, o que é o correto para artigos não geridos por lote.
		String sql =
			"SELECT s.LIECOD AS C0, s.EMPCOD AS C1, s.LOTREF AS C2, s.LOTQTE AS C3, " +
			" s.LOTQTEAFF AS C4, l.LOTDATVLF AS C5, l.LOTDATCRE AS C6 " +
			"FROM QUA_EPI_DIC_EPI a " +
			"INNER JOIN SILVER.dbo.STODET s ON s.PROREF = a.PROREF " +
			"LEFT JOIN SILVER.dbo.STOLOT l " +
			"  ON l.PROREF = s.PROREF AND l.LOTREF = s.LOTREF " +
			" AND NULLIF(LTRIM(RTRIM(s.LOTREF)), '') IS NOT NULL " +
			"WHERE a.ID_EPI = :id AND s.LOTQTE > 0 " +
			"ORDER BY l.LOTDATVLF, s.LIECOD";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", idEpi);
		return query.getResultList();
	}
}
