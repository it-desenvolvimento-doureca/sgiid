package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_MAQUINAS_PARADAS;
import pt.example.entity.PA_MOV_FICHEIROS;

public class MAN_MOV_MAQUINAS_PARADASDao extends GenericDaoJpaImpl<MAN_MOV_MAQUINAS_PARADAS, Integer>
		implements GenericDao<MAN_MOV_MAQUINAS_PARADAS, Integer> {
	public MAN_MOV_MAQUINAS_PARADASDao() {
		super(MAN_MOV_MAQUINAS_PARADAS.class);
	}

	public List<MAN_MOV_MAQUINAS_PARADAS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MAQUINAS_PARADAS a ");

		List<MAN_MOV_MAQUINAS_PARADAS> utz = query.getResultList();
		return utz;

	}


	public List<MAN_MOV_MAQUINAS_PARADAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a  from MAN_MOV_MAQUINAS_PARADAS a  a.ID = :id");
		query.setParameter("id", id);
		List<MAN_MOV_MAQUINAS_PARADAS> data = query.getResultList();
		return data;

	}
	
}
