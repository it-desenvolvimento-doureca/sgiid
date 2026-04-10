package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_DIC_NIVEL_URGENCIA_INCIDENTE;

public class MAN_DIC_NIVEL_URGENCIA_INCIDENTEDao
		extends GenericDaoJpaImpl<MAN_DIC_NIVEL_URGENCIA_INCIDENTE, Integer>
		implements GenericDao<MAN_DIC_NIVEL_URGENCIA_INCIDENTE, Integer> {

	public MAN_DIC_NIVEL_URGENCIA_INCIDENTEDao() {
		super(MAN_DIC_NIVEL_URGENCIA_INCIDENTE.class);
	}

	public List<MAN_DIC_NIVEL_URGENCIA_INCIDENTE> getall() {
		Query query = entityManager.createQuery(
				"Select a from MAN_DIC_NIVEL_URGENCIA_INCIDENTE a where a.ATIVO = true order by a.NIVEL");
		return query.getResultList();
	}

	public List<MAN_DIC_NIVEL_URGENCIA_INCIDENTE> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"select a from MAN_DIC_NIVEL_URGENCIA_INCIDENTE a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
