package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_UTZ_PERFIL;

public class GER_UTZ_PERFILDao extends GenericDaoJpaImpl<GER_UTZ_PERFIL, Integer>
		implements GenericDao<GER_UTZ_PERFIL, Integer> {
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

	public List<GER_UTZ_PERFIL> getbyIduser(Integer ID_PERFIL, Integer ID_MODULO) {
		Query query = entityManager.createNativeQuery("select ID_UTILIZADOR,NOME_UTILIZADOR,(select ID_PERFIL_UTZ from GER_UTZ_PERFIL a,GER_MODULO b  "
				+ "where  a.ID_PERFIL = :id and b.ID_MODULO = :id2 and a.ID_UTZ= ID_UTILIZADOR ) as id_perfil from GER_UTILIZADORES "
				+ "where ID_UTILIZADOR in (select ID_UTZ from GER_UTZ_PERFIL a,GER_MODULO b "
				+ "where  a.ID_PERFIL = :id and b.ID_MODULO = :id2 )");
		query.setParameter("id", ID_PERFIL);
		query.setParameter("id2", ID_MODULO);
		List<GER_UTZ_PERFIL> data = query.getResultList();
		return data;

	}

	public List<GER_UTZ_PERFIL> getbyIdnotuser(Integer ID_PERFIL, Integer ID_MODULO) {
		Query query = entityManager.createNativeQuery("select ID_UTILIZADOR,NOME_UTILIZADOR "
				+ "from GER_UTILIZADORES "
				+ "where ID_UTILIZADOR not in (select ID_UTZ from GER_UTZ_PERFIL a,GER_MODULO b "
				+ "where  a.ID_PERFIL = :id and b.ID_MODULO = :id2 )");
		query.setParameter("id", ID_PERFIL);
		query.setParameter("id2", ID_MODULO);
		List<GER_UTZ_PERFIL> data = query.getResultList();
		return data;

	}

}
