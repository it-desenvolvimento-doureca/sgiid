package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PA_MOV_FICHEIROS;

public class PA_MOV_FICHEIROSDao extends GenericDaoJpaImpl<PA_MOV_FICHEIROS, Integer>
		implements GenericDao<PA_MOV_FICHEIROS, Integer> {
	public PA_MOV_FICHEIROSDao() {
		super(PA_MOV_FICHEIROS.class);
	}

	public List<PA_MOV_FICHEIROS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a,b from PA_MOV_FICHEIROS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_PLANO_CAB = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<PA_MOV_FICHEIROS> data = query.getResultList();
		return data;

	}
	
	public List<PA_MOV_FICHEIROS> getbyid2(Integer id) {

		Query query = entityManager.createQuery("Select a from PA_MOV_FICHEIROS a where a.ID_PLANO_CAB = :id ");
		query.setParameter("id", id);
		List<PA_MOV_FICHEIROS> data = query.getResultList();
		return data;

	}
		

	public List<PA_MOV_FICHEIROS> getall() {

		Query query = entityManager.createQuery("Select a from PA_MOV_FICHEIROS a ");
		List<PA_MOV_FICHEIROS> data = query.getResultList();
		return data;

	}

}
