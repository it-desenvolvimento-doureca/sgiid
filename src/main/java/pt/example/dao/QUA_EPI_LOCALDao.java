package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_EPI_LOCAL;

public class QUA_EPI_LOCALDao extends GenericDaoJpaImpl<QUA_EPI_LOCAL, Integer>
		implements GenericDao<QUA_EPI_LOCAL, Integer> {
	public QUA_EPI_LOCALDao() {
		super(QUA_EPI_LOCAL.class);
	}

	public List<QUA_EPI_LOCAL> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_LOCAL a where a.ATIVO = 1");
		return query.getResultList();
	}

	public List<QUA_EPI_LOCAL> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_LOCAL a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * Locais EPI escolhidos, com a descrição vinda de GER_LOCAIS e a contagem
	 * de responsáveis de cada um.
	 * C0 = id da linha, C1 = id do local (GER_LOCAIS.ID), C2 = descrição,
	 * C3 = nº de responsáveis
	 */
	public List<Object[]> getlista() {
		// Os responsáveis ligam a QUA_EPI_LOCAL.ID, não a GER_LOCAIS.ID
		String sql =
			"SELECT a.ID AS C0, a.ID_LOCAL AS C1, l.DESCRICAO AS C2, " +
			" (SELECT COUNT(*) FROM QUA_EPI_LOCAL_RESP r " +
			"   WHERE r.ID_LOCAL = a.ID AND r.ATIVO = 1) AS C3 " +
			"FROM QUA_EPI_LOCAL a " +
			"INNER JOIN GER_LOCAIS l ON l.ID = a.ID_LOCAL " +
			"WHERE a.ATIVO = 1 ORDER BY l.DESCRICAO";
		return entityManager.createNativeQuery(sql).getResultList();
	}

	/**
	 * GER_LOCAIS ainda não escolhidos como locais EPI - alimenta o dropdown
	 * de "Adicionar", para não se poder acrescentar o mesmo local duas vezes.
	 * C0 = id, C1 = descrição
	 */
	public List<Object[]> getdisponiveis() {
		String sql =
			"SELECT l.ID AS C0, l.DESCRICAO AS C1 " +
			"FROM GER_LOCAIS l " +
			"WHERE ISNULL(l.INATIVO, 0) = 0 " +
			"  AND NOT EXISTS ( SELECT 1 FROM QUA_EPI_LOCAL a " +
			"                   WHERE a.ID_LOCAL = l.ID AND a.ATIVO = 1 ) " +
			"ORDER BY l.DESCRICAO";
		return entityManager.createNativeQuery(sql).getResultList();
	}
}
