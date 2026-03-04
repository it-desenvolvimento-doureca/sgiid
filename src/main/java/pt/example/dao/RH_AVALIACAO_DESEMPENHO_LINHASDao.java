package pt.example.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_AVALIACAO_DESEMPENHO_LINHAS;

public class RH_AVALIACAO_DESEMPENHO_LINHASDao extends GenericDaoJpaImpl<RH_AVALIACAO_DESEMPENHO_LINHAS, Integer>
		implements GenericDao<RH_AVALIACAO_DESEMPENHO_LINHAS, Integer> {

	public RH_AVALIACAO_DESEMPENHO_LINHASDao() {
		super(RH_AVALIACAO_DESEMPENHO_LINHAS.class);
	}

	public List<RH_AVALIACAO_DESEMPENHO_LINHAS> getById(Integer id) {
		Query query = entityManager.createQuery(
				"SELECT p FROM RH_AVALIACAO_DESEMPENHO_LINHAS p WHERE p.ID_AVALIACAO_DESEMPENHO = :id ORDER BY ISNULL(p.GRUPO_COLABORADORES,'ZZ'),p.SECCAO,p.NOME");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<RH_AVALIACAO_DESEMPENHO_LINHAS> getAll() {
		Query query = entityManager.createQuery("SELECT p FROM RH_AVALIACAO_DESEMPENHO_LINHAS p where p.ATIVO = 1");
		return query.getResultList();
	}

	public List<RH_AVALIACAO_DESEMPENHO_LINHAS> getByidGrupo(Integer id, List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String idGrupo = firstMap.get("GRUPO");
		String NUMERO_FUNCIONARIO = firstMap.get("NUMERO_FUNCIONARIO");
		String NOME = firstMap.get("NOME");
		String SECCAO = firstMap.get("SECCAO");
		String CHEFIA_DIRETA = firstMap.get("CHEFIA_DIRETA");
		String LOCAL = firstMap.get("LOCAL");
		String CATEGORIA_PROFISSIONAL = firstMap.get("CATEGORIA_PROFISSIONAL");

		String query_ = "";
		
		if (idGrupo != null && !idGrupo.isEmpty()) {
			query_ = " AND p.ID_GRUPO_COLABORADORES = :idGrupo";
		}
		if (NUMERO_FUNCIONARIO != null && !NUMERO_FUNCIONARIO.isEmpty()) {
			query_ += " AND p.NUMERO_FUNCIONARIO LIKE '%" + NUMERO_FUNCIONARIO + "%'";
		}
		if (NOME != null && !NOME.isEmpty()) {
			query_ += " AND p.NOME LIKE '%" + NOME + "%'";
		}
		if (SECCAO != null && !SECCAO.isEmpty()) {
			query_ += " AND p.SECCAO LIKE '%" + SECCAO + "%'";
		}
		if (CHEFIA_DIRETA != null && !CHEFIA_DIRETA.isEmpty()) {
			query_ += " AND p.CHEFIA_DIRETA LIKE '%" + CHEFIA_DIRETA + "%'";
		}
		if (LOCAL != null && !LOCAL.isEmpty()) {
			query_ += " AND p.LOCAL LIKE '%" + LOCAL + "%'";
		}
		if (CATEGORIA_PROFISSIONAL != null && !CATEGORIA_PROFISSIONAL.isEmpty()) {
			query_ += " AND p.CATEGORIA_PROFISSIONAL LIKE '%" + CATEGORIA_PROFISSIONAL + "%'";
		}
		 

		Query query = entityManager
				.createQuery("SELECT p FROM RH_AVALIACAO_DESEMPENHO_LINHAS p WHERE p.ID_AVALIACAO_DESEMPENHO = :id " + query_ + "  ORDER BY ISNULL(p.GRUPO_COLABORADORES,'ZZ'),p.SECCAO,p.NOME");
		query.setParameter("id", id);
		if (idGrupo != null  && !idGrupo.isEmpty()) {
			query.setParameter("idGrupo", Integer.parseInt(idGrupo));
		}
				
		return query.getResultList();
	}
}