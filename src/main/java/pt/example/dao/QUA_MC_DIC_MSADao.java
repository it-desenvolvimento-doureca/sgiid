package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DIC_MSA;

public class QUA_MC_DIC_MSADao extends GenericDaoJpaImpl<QUA_MC_DIC_MSA, Integer>
		implements GenericDao<QUA_MC_DIC_MSA, Integer> {
	public QUA_MC_DIC_MSADao() {
		super(QUA_MC_DIC_MSA.class);
	}

	public List<QUA_MC_DIC_MSA> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_MSA a order by a.DESIGNACAO");
		return query.getResultList();
	}

	public List<QUA_MC_DIC_MSA> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_MSA a where a.ID_MSA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
