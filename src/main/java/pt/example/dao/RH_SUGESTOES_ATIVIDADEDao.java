package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_SUGESTOES_ATIVIDADE;

public class RH_SUGESTOES_ATIVIDADEDao extends GenericDaoJpaImpl<RH_SUGESTOES_ATIVIDADE, Integer>
		implements GenericDao<RH_SUGESTOES_ATIVIDADE, Integer> {
	public RH_SUGESTOES_ATIVIDADEDao() {
		super(RH_SUGESTOES_ATIVIDADE.class);
	}

	public List<RH_SUGESTOES_ATIVIDADE> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_SUGESTOES_ATIVIDADE a where a.ID_SUGESTAO = :id ");
		query.setParameter("id", id);
		List<RH_SUGESTOES_ATIVIDADE> data = query.getResultList();
		return data;

	}

	public List<RH_SUGESTOES_ATIVIDADE> getall() {

		Query query = entityManager.createQuery("Select a from RH_SUGESTOES_ATIVIDADE a  ");
		List<RH_SUGESTOES_ATIVIDADE> data = query.getResultList();
		return data;

	}
 
}
