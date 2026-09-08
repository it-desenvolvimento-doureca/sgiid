package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_MOV_CORROSAO_LEITURA;

public class QUA_CR_MOV_CORROSAO_LEITURADao extends GenericDaoJpaImpl<QUA_CR_MOV_CORROSAO_LEITURA, Integer>
		implements GenericDao<QUA_CR_MOV_CORROSAO_LEITURA, Integer> {
	public QUA_CR_MOV_CORROSAO_LEITURADao() {
		super(QUA_CR_MOV_CORROSAO_LEITURA.class);
	}

	public List<QUA_CR_MOV_CORROSAO_LEITURA> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_CORROSAO_LEITURA a where a.ATIVO = true order by a.ID_PATAMAR");
		return query.getResultList();
	}

	public List<QUA_CR_MOV_CORROSAO_LEITURA> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_MOV_CORROSAO_LEITURA a where a.ID_CORROSAO_LEITURA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_MOV_CORROSAO_LEITURA> getbyCorrosaoAmostra(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_CORROSAO_LEITURA a where a.ID_CORROSAO_AMOSTRA = :id and a.ATIVO = true order by a.ID_PATAMAR");
		query.setParameter("id", id);
		return query.getResultList();
	}
}