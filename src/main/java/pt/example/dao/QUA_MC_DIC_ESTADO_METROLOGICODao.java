package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DIC_ESTADO_METROLOGICO;

public class QUA_MC_DIC_ESTADO_METROLOGICODao extends GenericDaoJpaImpl<QUA_MC_DIC_ESTADO_METROLOGICO, Integer>
		implements GenericDao<QUA_MC_DIC_ESTADO_METROLOGICO, Integer> {
	public QUA_MC_DIC_ESTADO_METROLOGICODao() {
		super(QUA_MC_DIC_ESTADO_METROLOGICO.class);
	}

	public List<QUA_MC_DIC_ESTADO_METROLOGICO> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_ESTADO_METROLOGICO a order by a.DESIGNACAO");
		return query.getResultList();
	}

	public List<QUA_MC_DIC_ESTADO_METROLOGICO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_ESTADO_METROLOGICO a where a.ID_ESTADO_METROLOGICO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
