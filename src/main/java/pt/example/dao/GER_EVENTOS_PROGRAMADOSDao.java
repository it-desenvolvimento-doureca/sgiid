package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_EVENTOS_PROGRAMADOS;

public class GER_EVENTOS_PROGRAMADOSDao extends GenericDaoJpaImpl<GER_EVENTOS_PROGRAMADOS, Integer>
		implements GenericDao<GER_EVENTOS_PROGRAMADOS, Integer> {
	public GER_EVENTOS_PROGRAMADOSDao() {
		super(GER_EVENTOS_PROGRAMADOS.class);
	}

	public List<GER_EVENTOS_PROGRAMADOS> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from GER_EVENTOS_PROGRAMADOS a where a.ID = :id ");
		query.setParameter("id", id);
		List<GER_EVENTOS_PROGRAMADOS> data = query.getResultList();
		return data;

	}

	public List<GER_EVENTOS_PROGRAMADOS> getAll() {
		Query query = entityManager.createQuery("Select a,b from GER_EVENTOS_PROGRAMADOS a , GER_MODULO b where  a.MODULO = b.ID_MODULO and (a.INATIVO = null or a.INATIVO != 1)");
		List<GER_EVENTOS_PROGRAMADOS> data = query.getResultList();
		return data;

	}

}
