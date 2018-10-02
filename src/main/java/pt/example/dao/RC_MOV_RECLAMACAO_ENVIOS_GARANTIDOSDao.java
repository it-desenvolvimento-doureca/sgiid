package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS;

public class RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOSDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS, Integer> {
	public RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOSDao() {
		super(RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS.class);
	}

	public List<RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS a ");
		List<RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS> data = query.getResultList();
		return data;

	}

}
