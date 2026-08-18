package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_EPI_MOV_ENTREGA_ETIQ;

public class QUA_EPI_MOV_ENTREGA_ETIQDao extends GenericDaoJpaImpl<QUA_EPI_MOV_ENTREGA_ETIQ, Integer>
		implements GenericDao<QUA_EPI_MOV_ENTREGA_ETIQ, Integer> {
	public QUA_EPI_MOV_ENTREGA_ETIQDao() {
		super(QUA_EPI_MOV_ENTREGA_ETIQ.class);
	}

	public List<QUA_EPI_MOV_ENTREGA_ETIQ> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_ENTREGA_ETIQ a where a.ATIVO = 1");
		return query.getResultList();
	}

	public List<QUA_EPI_MOV_ENTREGA_ETIQ> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_ENTREGA_ETIQ a where a.ID_LINHA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	// A mesma etiqueta não pode ser registada duas vezes na mesma entrega
	public List<QUA_EPI_MOV_ENTREGA_ETIQ> getbyentregaetiqueta(Integer idEntrega, String etqnum) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_MOV_ENTREGA_ETIQ a where a.ID_ENTREGA = :id and a.ETQNUM = :etq");
		query.setParameter("id", idEntrega);
		query.setParameter("etq", etqnum);
		return query.getResultList();
	}
}
