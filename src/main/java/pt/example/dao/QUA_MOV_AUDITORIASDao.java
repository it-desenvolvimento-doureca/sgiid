package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.QUA_MOV_AUDITORIAS;

public class QUA_MOV_AUDITORIASDao extends GenericDaoJpaImpl<QUA_MOV_AUDITORIAS,Integer> implements GenericDao<QUA_MOV_AUDITORIAS,Integer> {
	public QUA_MOV_AUDITORIASDao() {
		super(QUA_MOV_AUDITORIAS.class);
	}

	
	public List<QUA_MOV_AUDITORIAS> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MOV_AUDITORIAS a where a.ID_AUDITORIA = :id ");
		query.setParameter("id", id);
		List<QUA_MOV_AUDITORIAS> data = query.getResultList();
		return data;

	}
	
	public List<QUA_MOV_AUDITORIAS> getbyano(Integer ano) {

		Query query = entityManager.createNativeQuery("EXEC GET_QUA_MOV_AUDITORIAS " + ano);
		List<QUA_MOV_AUDITORIAS> data = query.getResultList();
		return data;

	}
	
	public List<QUA_MOV_AUDITORIAS> getall() {

		Query query = entityManager.createQuery("Select a from QUA_MOV_AUDITORIAS a ");
		List<QUA_MOV_AUDITORIAS> data = query.getResultList();
		return data;

	}

}
