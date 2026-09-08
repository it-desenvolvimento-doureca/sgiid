package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_MOV_CORROSAO_CAB;

public class QUA_CR_MOV_CORROSAO_CABDao extends GenericDaoJpaImpl<QUA_CR_MOV_CORROSAO_CAB, Integer>
		implements GenericDao<QUA_CR_MOV_CORROSAO_CAB, Integer> {
	public QUA_CR_MOV_CORROSAO_CABDao() {
		super(QUA_CR_MOV_CORROSAO_CAB.class);
	}

	public List<QUA_CR_MOV_CORROSAO_CAB> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_CORROSAO_CAB a where a.ATIVO = true order by a.ID_CORROSAO_CAB");
		return query.getResultList();
	}

	public List<QUA_CR_MOV_CORROSAO_CAB> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_MOV_CORROSAO_CAB a where a.ID_CORROSAO_CAB = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_MOV_CORROSAO_CAB> getbyRelatorio(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_CORROSAO_CAB a where a.ID_RELATORIO = :id and a.ATIVO = true order by a.ID_CORROSAO_CAB");
		query.setParameter("id", id);
		return query.getResultList();
	}
}