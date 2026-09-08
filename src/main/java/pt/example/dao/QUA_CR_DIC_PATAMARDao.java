package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_PATAMAR;

public class QUA_CR_DIC_PATAMARDao extends GenericDaoJpaImpl<QUA_CR_DIC_PATAMAR, Integer>
		implements GenericDao<QUA_CR_DIC_PATAMAR, Integer> {
	public QUA_CR_DIC_PATAMARDao() {
		super(QUA_CR_DIC_PATAMAR.class);
	}

	public List<QUA_CR_DIC_PATAMAR> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_PATAMAR a where a.ATIVO = true order by a.TIPO, a.ORDEM");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_PATAMAR> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_DIC_PATAMAR a where a.ID_PATAMAR = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}