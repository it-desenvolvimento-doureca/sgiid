package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.REU_REUNIOES;

public class REU_REUNIOESDao extends GenericDaoJpaImpl<REU_REUNIOES, Integer>
		implements GenericDao<REU_REUNIOES, Integer> {
	public REU_REUNIOESDao() {
		super(REU_REUNIOES.class);
	}

	public List<REU_REUNIOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from REU_REUNIOES a where a.ID_REUNIAO = :id ");
		query.setParameter("id", id);
		List<REU_REUNIOES> data = query.getResultList();
		return data;

	}

	public List<REU_REUNIOES> getall() {

		Query query = entityManager.createQuery("Select a,b.DESCRICAO from REU_REUNIOES a ,REU_AMBITOS_REUNIOES b where a.ID_AMBITO = b.ID_AMBITO order by a.DATA_CRIA DESC");
		List<REU_REUNIOES> data = query.getResultList();
		return data;

	}

}
