package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.FIN_DIVIDAS_ATIVIDADE;

public class FIN_DIVIDAS_ATIVIDADEDao extends GenericDaoJpaImpl<FIN_DIVIDAS_ATIVIDADE, Integer>
		implements GenericDao<FIN_DIVIDAS_ATIVIDADE, Integer> {
	public FIN_DIVIDAS_ATIVIDADEDao() {
		super(FIN_DIVIDAS_ATIVIDADE.class);
	}

	public List<FIN_DIVIDAS_ATIVIDADE> getbyid(String id) {
		Query query = entityManager.createQuery("Select a,(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) as NOME_UTILIZADOR from FIN_DIVIDAS_ATIVIDADE a where a.ID_CLIENTE = :id");
		query.setParameter("id", id);
		List<FIN_DIVIDAS_ATIVIDADE> data = query.getResultList();
		return data;
	}

	public List<FIN_DIVIDAS_ATIVIDADE> getall() {
		Query query = entityManager.createQuery("Select a from FIN_DIVIDAS_ATIVIDADE a ");
		List<FIN_DIVIDAS_ATIVIDADE> data = query.getResultList();
		return data;
	}



}
