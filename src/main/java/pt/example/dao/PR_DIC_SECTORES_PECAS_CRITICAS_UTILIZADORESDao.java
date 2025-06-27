package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_UTILIZADORES;
import pt.example.entity.PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES;

public class PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORESDao extends GenericDaoJpaImpl<PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES, Integer>
		implements GenericDao<PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES, Integer> {
	public PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORESDao() {
		super(PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES.class);
	}

	public List<PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES a where a.ID = :id ");
		query.setParameter("id", id);
		List<PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES a where a.ATIVO = 1");
		List<PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES> data = query.getResultList();
		return data;

	}	
	
	public List<GER_UTILIZADORES> getPR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES_ALLUSERS(Integer id) {
		Query query = entityManager.createQuery("select a from GER_UTILIZADORES a "
				+ "where  a.ID_UTILIZADOR not in (select b.ID_UTILIZADOR from PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES b where b.ID_SECTORES_PECAS_CRITICAS = :id) "
				+ "and a.INATIVO != 1 ");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}

	public List<GER_UTILIZADORES> getPR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES_ID(Integer id) {
		Query query = entityManager.createQuery("select a,b.NOME_UTILIZADOR,b.ID_UTILIZADOR from PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES a ,GER_UTILIZADORES b "
				+ "where  a.ID_UTILIZADOR = b.ID_UTILIZADOR and a.ID_SECTORES_PECAS_CRITICAS = :id ");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}
	

}
