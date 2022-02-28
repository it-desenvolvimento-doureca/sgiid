package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA;

public class QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTADao extends GenericDaoJpaImpl<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA, Integer>
		implements GenericDao<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA, Integer> {
	public QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTADao() {
		super(QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA.class);
	}

	public List<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA a where a.ID = :id ");
		query.setParameter("id", id);
		List<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA> data = query.getResultList();
		return data;

	}
	
	public List<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA> getbyid_ano(Integer id,Integer ano) {

		Query query = entityManager.createQuery("Select a from QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA a where a.ID_TIPO_AUDITORIA = :id and a.ANO = :ano");
		query.setParameter("id", id);
		query.setParameter("ano", ano);
		List<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA> data = query.getResultList();
		return data;

	}

	public List<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA> getall() {

		Query query = entityManager.createQuery("Select a from QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA a");
		List<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA> data = query.getResultList();
		return data;

	}

}
