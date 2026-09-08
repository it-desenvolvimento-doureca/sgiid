package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_REFERENCIA_FICHEIROS;

public class QUA_CR_DIC_REFERENCIA_FICHEIROSDao extends GenericDaoJpaImpl<QUA_CR_DIC_REFERENCIA_FICHEIROS, Integer>
		implements GenericDao<QUA_CR_DIC_REFERENCIA_FICHEIROS, Integer> {
	public QUA_CR_DIC_REFERENCIA_FICHEIROSDao() {
		super(QUA_CR_DIC_REFERENCIA_FICHEIROS.class);
	}

	// Um ficheiro, com conteudo. E o unico getby que devolve a entidade
	// inteira, e de proposito: usa-se para um anexo especifico.
	//
	// Nao existe getall(): "todos os ficheiros de todas as referencias" seriam
	// ~250 MB de fotografias numa resposta. Quem precisa de uma lista usa
	// getbyReferencia (so metadados) e depois getConteudo por ficheiro.
	public List<QUA_CR_DIC_REFERENCIA_FICHEIROS> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_REFERENCIA_FICHEIROS a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	// Apenas metadados (NAO carrega FICHEIRO_1/FICHEIRO_2 - lazy load no
	// preview/download, via getConteudo). As fotografias das referencias sao
	// ~250 MB no total, portanto nunca podem vir numa listagem.
	public List<Object[]> getbyReferencia(Integer id) {
		Query query = entityManager.createQuery(
			"Select a.ID, a.NOME, a.DESCRICAO, a.TIPO, a.TAMANHO, a.DATATYPE, a.CATEGORIA, a.DATA_CRIA, "
			+ "(select u.NOME_UTILIZADOR from GER_UTILIZADORES u where u.ID_UTILIZADOR = a.UTZ_CRIA) "
			+ "from QUA_CR_DIC_REFERENCIA_FICHEIROS a "
			+ "where a.ID_REFERENCIA = :id and a.ATIVO = true order by a.CATEGORIA, a.DATA_CRIA");
		query.setParameter("id", id);
		return query.getResultList();
	}

	// A fotografia aparece no cabecalho do ecra de ensaio, nao so na ficha da
	// referencia, e por isso vale a pena poder pedir so essa.
	public List<Object[]> getbyReferenciaCategoria(Integer id, String categoria) {
		Query query = entityManager.createQuery(
			"Select a.ID, a.NOME, a.DESCRICAO, a.TIPO, a.TAMANHO, a.DATATYPE, a.CATEGORIA, a.DATA_CRIA "
			+ "from QUA_CR_DIC_REFERENCIA_FICHEIROS a "
			+ "where a.ID_REFERENCIA = :id and a.CATEGORIA = :categoria and a.ATIVO = true "
			+ "order by a.DATA_CRIA");
		query.setParameter("id", id);
		query.setParameter("categoria", categoria);
		return query.getResultList();
	}

	public String getConteudo(Integer id) {
		Query query = entityManager.createNativeQuery(
			"select CONCAT(FICHEIRO_1, FICHEIRO_2) from QUA_CR_DIC_REFERENCIA_FICHEIROS where ID = " + id);
		List<?> r = query.getResultList();
		return (r.isEmpty() || r.get(0) == null) ? "" : r.get(0).toString();
	}
}
