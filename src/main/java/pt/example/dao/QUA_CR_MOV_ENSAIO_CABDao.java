package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_MOV_ENSAIO_CAB;

public class QUA_CR_MOV_ENSAIO_CABDao extends GenericDaoJpaImpl<QUA_CR_MOV_ENSAIO_CAB, Integer>
		implements GenericDao<QUA_CR_MOV_ENSAIO_CAB, Integer> {
	public QUA_CR_MOV_ENSAIO_CABDao() {
		super(QUA_CR_MOV_ENSAIO_CAB.class);
	}

	public List<QUA_CR_MOV_ENSAIO_CAB> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_ENSAIO_CAB a where a.ATIVO = true order by a.ID_ENSAIO_CAB");
		return query.getResultList();
	}

	public List<QUA_CR_MOV_ENSAIO_CAB> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_MOV_ENSAIO_CAB a where a.ID_ENSAIO_CAB = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_MOV_ENSAIO_CAB> getbyRelatorio(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_ENSAIO_CAB a where a.ID_RELATORIO = :id and a.ATIVO = true order by a.ID_ENSAIO_CAB");
		query.setParameter("id", id);
		return query.getResultList();
	}
}