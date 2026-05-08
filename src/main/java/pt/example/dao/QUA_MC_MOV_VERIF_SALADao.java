package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_MOV_VERIF_SALA;

public class QUA_MC_MOV_VERIF_SALADao extends GenericDaoJpaImpl<QUA_MC_MOV_VERIF_SALA, Integer>
		implements GenericDao<QUA_MC_MOV_VERIF_SALA, Integer> {
	public QUA_MC_MOV_VERIF_SALADao() {
		super(QUA_MC_MOV_VERIF_SALA.class);
	}

	public List<QUA_MC_MOV_VERIF_SALA> getBySala(Integer idSala) {
		Query query = entityManager.createQuery(
			"Select a from QUA_MC_MOV_VERIF_SALA a where a.ID_SALA = :id order by a.DATA_VERIFICACAO desc");
		query.setParameter("id", idSala);
		return query.getResultList();
	}

	public List<QUA_MC_MOV_VERIF_SALA> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_MOV_VERIF_SALA a where a.ID_VERIF_SALA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
