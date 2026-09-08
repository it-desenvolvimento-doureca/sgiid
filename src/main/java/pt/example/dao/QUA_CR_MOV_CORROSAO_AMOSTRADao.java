package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_MOV_CORROSAO_AMOSTRA;

public class QUA_CR_MOV_CORROSAO_AMOSTRADao extends GenericDaoJpaImpl<QUA_CR_MOV_CORROSAO_AMOSTRA, Integer>
		implements GenericDao<QUA_CR_MOV_CORROSAO_AMOSTRA, Integer> {
	public QUA_CR_MOV_CORROSAO_AMOSTRADao() {
		super(QUA_CR_MOV_CORROSAO_AMOSTRA.class);
	}

	public List<QUA_CR_MOV_CORROSAO_AMOSTRA> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_CORROSAO_AMOSTRA a where a.ATIVO = true order by a.TIPO, a.NUM_AMOSTRA");
		return query.getResultList();
	}

	public List<QUA_CR_MOV_CORROSAO_AMOSTRA> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_MOV_CORROSAO_AMOSTRA a where a.ID_CORROSAO_AMOSTRA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_MOV_CORROSAO_AMOSTRA> getbyCorrosaoCab(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_CORROSAO_AMOSTRA a where a.ID_CORROSAO_CAB = :id and a.ATIVO = true order by a.TIPO, a.NUM_AMOSTRA");
		query.setParameter("id", id);
		return query.getResultList();
	}
}