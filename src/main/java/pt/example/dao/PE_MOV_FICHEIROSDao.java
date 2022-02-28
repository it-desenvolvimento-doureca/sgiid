package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PE_MOV_FICHEIROS;

public class PE_MOV_FICHEIROSDao extends GenericDaoJpaImpl<PE_MOV_FICHEIROS, Integer>
		implements GenericDao<PE_MOV_FICHEIROS, Integer> {
	public PE_MOV_FICHEIROSDao() {
		super(PE_MOV_FICHEIROS.class);
	}

	public List<PE_MOV_FICHEIROS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a,b from PE_MOV_FICHEIROS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_PLANO_ESTRATEGICO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<PE_MOV_FICHEIROS> data = query.getResultList();
		return data;

	}
	
	public List<PE_MOV_FICHEIROS> getbyid2(Integer id) {

		Query query = entityManager.createQuery("Select a from PE_MOV_FICHEIROS a where a.ID_PLANO_CAB = :id ");
		query.setParameter("id", id);
		List<PE_MOV_FICHEIROS> data = query.getResultList();
		return data;

	}
		

	public List<PE_MOV_FICHEIROS> getall() {

		Query query = entityManager.createQuery("Select a from PE_MOV_FICHEIROS a ");
		List<PE_MOV_FICHEIROS> data = query.getResultList();
		return data;

	}

}
