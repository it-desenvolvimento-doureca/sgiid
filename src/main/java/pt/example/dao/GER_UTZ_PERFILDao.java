package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_UTZ_PERFIL;

public class GER_UTZ_PERFILDao extends GenericDaoJpaImpl<GER_UTZ_PERFIL,Integer> implements GenericDao<GER_UTZ_PERFIL,Integer> {
	public GER_UTZ_PERFILDao() {
		super(GER_UTZ_PERFIL.class);
	}
	
	public List<GER_UTZ_PERFIL> getbyId(Integer id) {
		Query query = entityManager.createQuery("select a,b,c from GER_UTZ_PERFIL a,GER_MODULO b,GER_PERFIL_CAB c "
				+ "where  a.ID_PERFIL = c.ID_PERFIL_CAB and b.ID_MODULO = c.ID_MODULO and a.ID_UTZ = :id");
		query.setParameter("id", id);
		List<GER_UTZ_PERFIL> data = query.getResultList();
		return data;

	}
	
}
