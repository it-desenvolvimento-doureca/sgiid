package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DERROGACOES_FICHEIROS;

public class QUA_MC_DERROGACOES_FICHEIROSDao_MC extends GenericDaoJpaImpl<QUA_MC_DERROGACOES_FICHEIROS, Integer>
		implements GenericDao<QUA_MC_DERROGACOES_FICHEIROS, Integer> {
	public QUA_MC_DERROGACOES_FICHEIROSDao_MC() {
		super(QUA_MC_DERROGACOES_FICHEIROS.class);
	}

	// Apenas metadados (NÃO carrega FICHEIRO_1/FICHEIRO_2 - lazy load no preview/download)
	public List<Object[]> getbyDerrogacao(Integer id) {
		Query query = entityManager.createQuery(
			"Select a.ID, a.NOME, a.DESCRICAO, a.TIPO, a.TAMANHO, a.DATATYPE, a.DATA_CRIA, "
			+ "(select u.NOME_UTILIZADOR from GER_UTILIZADORES u where u.ID_UTILIZADOR = a.UTZ_CRIA) "
			+ "from QUA_MC_DERROGACOES_FICHEIROS a where a.ID_DERROGACAO = :id and a.ATIVO = true order by a.DATA_CRIA");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public String getConteudo(Integer id) {
		Query query = entityManager.createNativeQuery(
			"select CONCAT(FICHEIRO_1, FICHEIRO_2) from QUA_MC_DERROGACOES_FICHEIROS where ID = " + id);
		List<?> r = query.getResultList();
		return (r.isEmpty() || r.get(0) == null) ? "" : r.get(0).toString();
	}
}
