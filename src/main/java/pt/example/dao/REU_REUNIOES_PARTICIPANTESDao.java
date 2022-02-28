package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.REU_REUNIOES_PARTICIPANTES;

public class REU_REUNIOES_PARTICIPANTESDao extends GenericDaoJpaImpl<REU_REUNIOES_PARTICIPANTES, Integer>
		implements GenericDao<REU_REUNIOES_PARTICIPANTES, Integer> {
	public REU_REUNIOES_PARTICIPANTESDao() {
		super(REU_REUNIOES_PARTICIPANTES.class);
	}

	public List<REU_REUNIOES_PARTICIPANTES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from REU_REUNIOES_PARTICIPANTES a where a.ID_REUNIAO = :id ");
		query.setParameter("id", id);
		List<REU_REUNIOES_PARTICIPANTES> data = query.getResultList();
		return data;

	}

	public List<REU_REUNIOES_PARTICIPANTES> getall() {

		Query query = entityManager.createQuery("Select a from REU_REUNIOES_PARTICIPANTES a ");
		List<REU_REUNIOES_PARTICIPANTES> data = query.getResultList();
		return data;

	}

}
