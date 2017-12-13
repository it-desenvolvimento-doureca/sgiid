package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_PERFIL_LIN;

public class GER_PERFIL_LINDao extends GenericDaoJpaImpl<GER_PERFIL_LIN, Integer>
		implements GenericDao<GER_PERFIL_LIN, Integer> {
	public GER_PERFIL_LINDao() {
		super(GER_PERFIL_LIN.class);
	}

	public List<GER_PERFIL_LIN> getbyId(Integer id) {
		Query query = entityManager.createQuery("select a" + " from GER_PERFIL_LIN a where a.ID_PERFIL_CAB = :id");
		query.setParameter("id", id);
		List<GER_PERFIL_LIN> data = query.getResultList();
		return data;

	}

	public List<GER_PERFIL_LIN> getbyId_Node(Integer id, String node) {
		Query query = entityManager.createQuery("select a from GER_PERFIL_LIN a, GER_UTZ_PERFIL b "
				+ "where b.ID_UTZ = :id and a.ID_PERFIL_CAB = b.ID_PERFIL and"
				+ " ((not :node != 'null') or (a.ID_CAMPO  = :node))");
		query.setParameter("id", id);
		query.setParameter("node", node);
		List<GER_PERFIL_LIN> data = query.getResultList();
		return data;

	}

	public void delete(Integer id) {
		Query query = entityManager.createQuery("Delete from GER_PERFIL_LIN a where a.ID_PERFIL_CAB = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
