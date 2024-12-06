package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_FORMACAO_PARTICIPANTES;

public class RH_FORMACAO_PARTICIPANTESDao extends GenericDaoJpaImpl<RH_FORMACAO_PARTICIPANTES, Integer>
		implements GenericDao<RH_FORMACAO_PARTICIPANTES, Integer> {
	public RH_FORMACAO_PARTICIPANTESDao() {
		super(RH_FORMACAO_PARTICIPANTES.class);
	}

	public List<RH_FORMACAO_PARTICIPANTES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_FORMACAO_PARTICIPANTES a where a.ID_FORMACAO = :id ");
		query.setParameter("id", id);
		List<RH_FORMACAO_PARTICIPANTES> data = query.getResultList();
		return data;

	}

	public List<RH_FORMACAO_PARTICIPANTES> getall() {

		Query query = entityManager.createQuery("Select a from RH_FORMACAO_PARTICIPANTES a ");
		List<RH_FORMACAO_PARTICIPANTES> data = query.getResultList();
		return data;

	}

}
