package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_EPI_DIC_FAMILIA;

public class QUA_EPI_DIC_FAMILIADao extends GenericDaoJpaImpl<QUA_EPI_DIC_FAMILIA, Integer>
		implements GenericDao<QUA_EPI_DIC_FAMILIA, Integer> {
	public QUA_EPI_DIC_FAMILIADao() {
		super(QUA_EPI_DIC_FAMILIA.class);
	}

	public List<QUA_EPI_DIC_FAMILIA> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_DIC_FAMILIA a where a.ATIVO = 1 order by a.DESCRICAO");
		return query.getResultList();
	}

	public List<QUA_EPI_DIC_FAMILIA> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_EPI_DIC_FAMILIA a where a.ID_FAMILIA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
