package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_AVALIACAO_DESEMPENHO_LINHAS;

public class RH_AVALIACAO_DESEMPENHO_LINHASDao extends GenericDaoJpaImpl<RH_AVALIACAO_DESEMPENHO_LINHAS, Integer>
		implements GenericDao<RH_AVALIACAO_DESEMPENHO_LINHAS, Integer> {

	public RH_AVALIACAO_DESEMPENHO_LINHASDao() {
		super(RH_AVALIACAO_DESEMPENHO_LINHAS.class);
	}

	public List<RH_AVALIACAO_DESEMPENHO_LINHAS> getById(Integer id) {
		Query query = entityManager.createQuery("SELECT p FROM RH_AVALIACAO_DESEMPENHO_LINHAS p WHERE p.ID_AVALIACAO_DESEMPENHO = :id ORDER BY p.GRUPO_COLABORADORES,p.SECCAO,p.NOME");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<RH_AVALIACAO_DESEMPENHO_LINHAS> getAll() {
		Query query = entityManager.createQuery("SELECT p FROM RH_AVALIACAO_DESEMPENHO_LINHAS p where p.ATIVO = 1");
		return query.getResultList();
	}
	 
}