package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.REU_REUNIOES_PLANOS_ACCOES;

public class REU_REUNIOES_PLANOS_ACCOESDao extends GenericDaoJpaImpl<REU_REUNIOES_PLANOS_ACCOES, Integer>
		implements GenericDao<REU_REUNIOES_PLANOS_ACCOES, Integer> {
	public REU_REUNIOES_PLANOS_ACCOESDao() {
		super(REU_REUNIOES_PLANOS_ACCOES.class);
	}

	public List<REU_REUNIOES_PLANOS_ACCOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from REU_REUNIOES_PLANOS_ACCOES a where a.ID_REUNIAO = :id order by ordem,id ");
		query.setParameter("id", id);
		List<REU_REUNIOES_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}

	public List<REU_REUNIOES_PLANOS_ACCOES> getbyidambito(Integer id) {

		Query query = entityManager.createQuery("Select a from REU_REUNIOES_PLANOS_ACCOES a,REU_REUNIOES b where  a.ID_REUNIAO = b.ID_REUNIAO "
				+ "and (b.ID_AMBITO in (select c.ID_AMBITO from REU_REUNIOES c where c.ID_AMBITO = b.ID_AMBITO and c.ID_REUNIAO = :id)) order by ordem,id  ");
		query.setParameter("id", id);
		List<REU_REUNIOES_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}
	
	public List<REU_REUNIOES_PLANOS_ACCOES> getbyidplano(Integer id) {

		Query query = entityManager.createQuery("Select a from REU_REUNIOES_PLANOS_ACCOES a where a.ID = :id ");
		query.setParameter("id", id);
		List<REU_REUNIOES_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}
	
	public List<REU_REUNIOES_PLANOS_ACCOES> getall() {

		Query query = entityManager.createQuery("Select a from REU_REUNIOES_PLANOS_ACCOES a ");
		List<REU_REUNIOES_PLANOS_ACCOES> data = query.getResultList();
		return data;

	}

}
