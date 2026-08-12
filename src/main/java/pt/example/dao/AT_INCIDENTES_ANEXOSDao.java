package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_INCIDENTES_ANEXOS;

public class AT_INCIDENTES_ANEXOSDao extends GenericDaoJpaImpl<AT_INCIDENTES_ANEXOS,Integer> implements GenericDao<AT_INCIDENTES_ANEXOS,Integer> {
	public AT_INCIDENTES_ANEXOSDao() {
		super(AT_INCIDENTES_ANEXOS.class);
	}

	/*
	 * Apenas metadados - o conteudo base64 so e lido no preview/download.
	 * Indices: 0 ID | 1 NOME | 2 DESCRICAO | 3 TIPO | 4 TAMANHO | 5 DATATYPE
	 *          6 CATEGORIA | 7 DATA_CRIA | 8 utilizador
	 */
	public List<Object[]> getbyIncidente(Integer id) {
		Query query = entityManager.createQuery(
			"Select a.ID, a.NOME, a.DESCRICAO, a.TIPO, a.TAMANHO, a.DATATYPE, a.CATEGORIA, a.DATA_CRIA, "
			+ "(select u.NOME_UTILIZADOR from GER_UTILIZADORES u where u.ID_UTILIZADOR = a.UTZ_CRIA) "
			+ "from AT_INCIDENTES_ANEXOS a where a.ID_INCIDENTE = :id "
			+ "and (a.INATIVO is null or a.INATIVO = false) order by a.DATA_CRIA");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public String getConteudo(Integer id) {
		Query query = entityManager.createNativeQuery(
			"select CONCAT(FICHEIRO_1, FICHEIRO_2) from AT_INCIDENTES_ANEXOS where ID = :id");
		query.setParameter("id", id);
		List<?> r = query.getResultList();
		return (r.isEmpty() || r.get(0) == null) ? "" : r.get(0).toString();
	}

	public List<AT_INCIDENTES_ANEXOS> getall() {

		Query query = entityManager.createQuery("Select a from AT_INCIDENTES_ANEXOS a ");
		List<AT_INCIDENTES_ANEXOS> data = query.getResultList();
		return data;

	}

}
