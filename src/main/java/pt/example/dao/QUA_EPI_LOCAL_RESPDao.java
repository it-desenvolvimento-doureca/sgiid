package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_EPI_LOCAL_RESP;

public class QUA_EPI_LOCAL_RESPDao extends GenericDaoJpaImpl<QUA_EPI_LOCAL_RESP, Integer>
		implements GenericDao<QUA_EPI_LOCAL_RESP, Integer> {
	public QUA_EPI_LOCAL_RESPDao() {
		super(QUA_EPI_LOCAL_RESP.class);
	}

	public List<QUA_EPI_LOCAL_RESP> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_LOCAL_RESP a where a.ATIVO = 1");
		return query.getResultList();
	}

	public List<QUA_EPI_LOCAL_RESP> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_LOCAL_RESP a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	// Responsáveis de um local EPI (QUA_EPI_LOCAL.ID), com o nome resolvido.
	// C0 = id, C1 = id utilizador, C2 = nome, C3 = email
	public List<Object[]> getbylocal(Integer idLocal) {
		String sql =
			"SELECT a.ID AS C0, a.ID_UTZ AS C1, u.NOME_UTILIZADOR AS C2, u.EMAIL AS C3 " +
			"FROM QUA_EPI_LOCAL_RESP a " +
			"LEFT JOIN GER_UTILIZADORES u ON u.ID_UTILIZADOR = a.ID_UTZ " +
			"WHERE a.ATIVO = 1 AND a.ID_LOCAL = :id ORDER BY u.NOME_UTILIZADOR";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", idLocal);
		return query.getResultList();
	}

	// Locais EPI (QUA_EPI_LOCAL.ID) onde um utilizador é responsável.
	// Base da regra de visibilidade de pedidos e do filtro do levantamento.
	public List<Integer> getlocaisbyutz(Integer idUtz) {
		Query query = entityManager.createQuery(
			"Select a.ID_LOCAL from QUA_EPI_LOCAL_RESP a where a.ATIVO = 1 and a.ID_UTZ = :id");
		query.setParameter("id", idUtz);
		return query.getResultList();
	}

}
