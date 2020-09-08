package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GT_MOV_FICHEIROS;

public class GT_MOV_FICHEIROSDao extends GenericDaoJpaImpl<GT_MOV_FICHEIROS, Integer>
		implements GenericDao<GT_MOV_FICHEIROS, Integer> {
	public GT_MOV_FICHEIROSDao() {
		super(GT_MOV_FICHEIROS.class);
	}

	public List<GT_MOV_FICHEIROS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a,b from GT_MOV_FICHEIROS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_TAREFA = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<GT_MOV_FICHEIROS> data = query.getResultList();
		return data;

	}
	
	public List<GT_MOV_FICHEIROS> getbyid2(Integer id) {

		Query query = entityManager.createQuery("Select a from GT_MOV_FICHEIROS a where a.ID_TAREFA = :id ");
		query.setParameter("id", id);
		List<GT_MOV_FICHEIROS> data = query.getResultList();
		return data;

	}
		

	public List<GT_MOV_FICHEIROS> getall() {

		Query query = entityManager.createQuery("Select a from GT_MOV_FICHEIROS a ");
		List<GT_MOV_FICHEIROS> data = query.getResultList();
		return data;

	}

}
