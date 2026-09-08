package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_TIPO_SUPERFICIE;

public class QUA_CR_DIC_TIPO_SUPERFICIEDao extends GenericDaoJpaImpl<QUA_CR_DIC_TIPO_SUPERFICIE, Integer>
		implements GenericDao<QUA_CR_DIC_TIPO_SUPERFICIE, Integer> {
	public QUA_CR_DIC_TIPO_SUPERFICIEDao() {
		super(QUA_CR_DIC_TIPO_SUPERFICIE.class);
	}

	public List<QUA_CR_DIC_TIPO_SUPERFICIE> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_TIPO_SUPERFICIE a where a.ATIVO = true order by a.ORDEM");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_TIPO_SUPERFICIE> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_DIC_TIPO_SUPERFICIE a where a.ID_TIPO_SUPERFICIE = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}