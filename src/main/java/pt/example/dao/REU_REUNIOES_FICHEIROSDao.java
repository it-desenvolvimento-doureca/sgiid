package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.REU_REUNIOES_FICHEIROS;

public class REU_REUNIOES_FICHEIROSDao extends GenericDaoJpaImpl<REU_REUNIOES_FICHEIROS, Integer>
		implements GenericDao<REU_REUNIOES_FICHEIROS, Integer> {
	public REU_REUNIOES_FICHEIROSDao() {
		super(REU_REUNIOES_FICHEIROS.class);
	}

	public List<REU_REUNIOES_FICHEIROS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a,b from REU_REUNIOES_FICHEIROS a, GER_UTILIZADORES b where  a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_REUNIAO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<REU_REUNIOES_FICHEIROS> data = query.getResultList();
		return data;

	}

	public List<REU_REUNIOES_FICHEIROS> getall() {

		Query query = entityManager.createQuery("Select a from REU_REUNIOES_FICHEIROS a ");
		List<REU_REUNIOES_FICHEIROS> data = query.getResultList();
		return data;

	}

}
