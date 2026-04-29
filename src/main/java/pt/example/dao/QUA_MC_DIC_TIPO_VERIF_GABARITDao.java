package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DIC_TIPO_VERIF_GABARIT;

public class QUA_MC_DIC_TIPO_VERIF_GABARITDao extends GenericDaoJpaImpl<QUA_MC_DIC_TIPO_VERIF_GABARIT, Integer>
		implements GenericDao<QUA_MC_DIC_TIPO_VERIF_GABARIT, Integer> {
	public QUA_MC_DIC_TIPO_VERIF_GABARITDao() {
		super(QUA_MC_DIC_TIPO_VERIF_GABARIT.class);
	}

	public List<QUA_MC_DIC_TIPO_VERIF_GABARIT> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_TIPO_VERIF_GABARIT a order by a.TIPO_VERIF_GABARIT");
		return query.getResultList();
	}

	public List<QUA_MC_DIC_TIPO_VERIF_GABARIT> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_TIPO_VERIF_GABARIT a where a.ID_TIPO_VERIF_GABARIT = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
