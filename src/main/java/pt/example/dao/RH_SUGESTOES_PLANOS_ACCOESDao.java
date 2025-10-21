package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_SUGESTOES_PLANOS_ACCOES;

public class RH_SUGESTOES_PLANOS_ACCOESDao extends GenericDaoJpaImpl<RH_SUGESTOES_PLANOS_ACCOES, Integer>
		implements GenericDao<RH_SUGESTOES_PLANOS_ACCOES, Integer> {
	public RH_SUGESTOES_PLANOS_ACCOESDao() {
		super(RH_SUGESTOES_PLANOS_ACCOES.class);
	}

	public List<RH_SUGESTOES_PLANOS_ACCOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_SUGESTOES_PLANOS_ACCOES a where a.ID_SUGESTAO = :id order by ordem,id ");
		query.setParameter("id", id);
		List<RH_SUGESTOES_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}

	public List<RH_SUGESTOES_PLANOS_ACCOES> getbyidplano(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_SUGESTOES_PLANOS_ACCOES a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_SUGESTOES_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}
	
	public List<RH_SUGESTOES_PLANOS_ACCOES> getall() {

		Query query = entityManager.createQuery("Select a from RH_SUGESTOES_PLANOS_ACCOES a ");
		List<RH_SUGESTOES_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}

}
