package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_EVENTOS_DESTINATARIOS;

public class GER_EVENTOS_DESTINATARIOSDao extends GenericDaoJpaImpl<GER_EVENTOS_DESTINATARIOS, Integer>
		implements GenericDao<GER_EVENTOS_DESTINATARIOS, Integer> {
	public GER_EVENTOS_DESTINATARIOSDao() {
		super(GER_EVENTOS_DESTINATARIOS.class);
	}

	public List<GER_EVENTOS_DESTINATARIOS> getbyId(Integer id) {
		Query query = entityManager.createQuery("select a from GER_EVENTOS_DESTINATARIOS a where a.ID_EVENTO = :id ");
		query.setParameter("id", id);
		List<GER_EVENTOS_DESTINATARIOS> data = query.getResultList();
		return data;

	}

}
