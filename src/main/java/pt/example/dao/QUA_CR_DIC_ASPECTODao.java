package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_ASPECTO;

public class QUA_CR_DIC_ASPECTODao extends GenericDaoJpaImpl<QUA_CR_DIC_ASPECTO, Integer>
		implements GenericDao<QUA_CR_DIC_ASPECTO, Integer> {
	public QUA_CR_DIC_ASPECTODao() {
		super(QUA_CR_DIC_ASPECTO.class);
	}

	public List<QUA_CR_DIC_ASPECTO> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_ASPECTO a where a.ATIVO = true order by a.ORDEM");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_ASPECTO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_DIC_ASPECTO a where a.ID_ASPECTO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}