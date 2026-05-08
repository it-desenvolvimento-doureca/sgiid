package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DIC_SECCOES;

public class QUA_MC_DIC_SECCOESDao extends GenericDaoJpaImpl<QUA_MC_DIC_SECCOES, Integer>
		implements GenericDao<QUA_MC_DIC_SECCOES, Integer> {
	public QUA_MC_DIC_SECCOESDao() {
		super(QUA_MC_DIC_SECCOES.class);
	}

	public List<QUA_MC_DIC_SECCOES> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_SECCOES a order by a.LOCAL_SECCAO");
		return query.getResultList();
	}

	public List<QUA_MC_DIC_SECCOES> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_SECCOES a where a.ID_SECCAO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
