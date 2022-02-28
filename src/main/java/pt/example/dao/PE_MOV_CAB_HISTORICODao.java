package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PE_MOV_CAB_HISTORICO;

public class PE_MOV_CAB_HISTORICODao extends GenericDaoJpaImpl<PE_MOV_CAB_HISTORICO, Integer>
		implements GenericDao<PE_MOV_CAB_HISTORICO, Integer> {
	public PE_MOV_CAB_HISTORICODao() {
		super(PE_MOV_CAB_HISTORICO.class);
	}

	public List<PE_MOV_CAB_HISTORICO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PE_MOV_CAB_HISTORICO a where a.ID_PLANO_ESTRATEGICO = :id ");
		query.setParameter("id", id);
		List<PE_MOV_CAB_HISTORICO> data = query.getResultList();
		return data;

	}

	public List<PE_MOV_CAB_HISTORICO> getall() {

		Query query = entityManager.createQuery("Select a from PE_MOV_CAB_HISTORICO a");
		List<PE_MOV_CAB_HISTORICO> data = query.getResultList();
		return data;

	}

}
