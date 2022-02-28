package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.QUA_DIC_TIPOS_AUDITORIA;

public class QUA_DIC_TIPOS_AUDITORIADao extends GenericDaoJpaImpl<QUA_DIC_TIPOS_AUDITORIA, Integer>
		implements GenericDao<QUA_DIC_TIPOS_AUDITORIA, Integer> {
	public QUA_DIC_TIPOS_AUDITORIADao() {
		super(QUA_DIC_TIPOS_AUDITORIA.class);
	}

	public List<QUA_DIC_TIPOS_AUDITORIA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from QUA_DIC_TIPOS_AUDITORIA a where a.ID_TIPO_AUDITORIA = :id ");
		query.setParameter("id", id);
		List<QUA_DIC_TIPOS_AUDITORIA> data = query.getResultList();
		return data;

	}

	public List<QUA_DIC_TIPOS_AUDITORIA> getall() {

		Query query = entityManager.createQuery("Select a from QUA_DIC_TIPOS_AUDITORIA a");
		List<QUA_DIC_TIPOS_AUDITORIA> data = query.getResultList();
		return data;

	}

}
