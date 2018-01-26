package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_PERFIL_CAB;

public class GER_PERFIL_CABDao extends GenericDaoJpaImpl<GER_PERFIL_CAB, Integer>
		implements GenericDao<GER_PERFIL_CAB, Integer> {
	public GER_PERFIL_CABDao() {
		super(GER_PERFIL_CAB.class);
	}

	public List<GER_PERFIL_CAB> getall() {
		Query query = entityManager.createQuery("select a" + " from GER_PERFIL_CAB a where a.INATIVO != 1");
		List<GER_PERFIL_CAB> data = query.getResultList();
		return data;

	}

	public List<GER_PERFIL_CAB> getallperfil(Integer id,Integer modulo) {
		Query query = entityManager.createQuery("select a,b from GER_PERFIL_CAB a,GER_MODULO b "
				+ "where a.ID_MODULO = b.ID_MODULO and a.ID_PERFIL_CAB not in (select b.ID_PERFIL from GER_UTZ_PERFIL b where b.ID_UTZ = :id) "
				+ "and a.INATIVO != 1 and ((not :modulo != 0) or (a.ID_MODULO = :modulo))");
		query.setParameter("id", id);
		query.setParameter("modulo", modulo);
		List<GER_PERFIL_CAB> data = query.getResultList();
		return data;

	}
	
	public List<GER_PERFIL_CAB> getallperfilmodulo(Integer modulo) {
		Query query = entityManager.createQuery("select a,b from GER_PERFIL_CAB a,GER_MODULO b "
				+ "where a.ID_MODULO = b.ID_MODULO "
				+ "and a.INATIVO != 1 and ((not :modulo != 0) or (a.ID_MODULO = :modulo))");
		query.setParameter("modulo", modulo);
		List<GER_PERFIL_CAB> data = query.getResultList();
		return data;

	}
}
