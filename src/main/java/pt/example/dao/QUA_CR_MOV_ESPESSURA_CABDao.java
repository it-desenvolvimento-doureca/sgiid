package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_MOV_ESPESSURA_CAB;

public class QUA_CR_MOV_ESPESSURA_CABDao extends GenericDaoJpaImpl<QUA_CR_MOV_ESPESSURA_CAB, Integer>
		implements GenericDao<QUA_CR_MOV_ESPESSURA_CAB, Integer> {
	public QUA_CR_MOV_ESPESSURA_CABDao() {
		super(QUA_CR_MOV_ESPESSURA_CAB.class);
	}

	public List<QUA_CR_MOV_ESPESSURA_CAB> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_ESPESSURA_CAB a where a.ATIVO = true order by a.ID_ESPESSURA_CAB");
		return query.getResultList();
	}

	public List<QUA_CR_MOV_ESPESSURA_CAB> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_MOV_ESPESSURA_CAB a where a.ID_ESPESSURA_CAB = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_MOV_ESPESSURA_CAB> getbyRelatorio(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_ESPESSURA_CAB a where a.ID_RELATORIO = :id and a.ATIVO = true order by a.ID_ESPESSURA_CAB");
		query.setParameter("id", id);
		return query.getResultList();
	}
}