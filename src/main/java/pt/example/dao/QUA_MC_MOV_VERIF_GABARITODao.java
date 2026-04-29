package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_MOV_VERIF_GABARITO;

public class QUA_MC_MOV_VERIF_GABARITODao extends GenericDaoJpaImpl<QUA_MC_MOV_VERIF_GABARITO, Integer>
		implements GenericDao<QUA_MC_MOV_VERIF_GABARITO, Integer> {
	public QUA_MC_MOV_VERIF_GABARITODao() {
		super(QUA_MC_MOV_VERIF_GABARITO.class);
	}

	public List<QUA_MC_MOV_VERIF_GABARITO> getbyGabarito(Integer idGabarito) {
		Query query = entityManager.createQuery(
			"Select a from QUA_MC_MOV_VERIF_GABARITO a where a.ID_GABARITO = :id order by a.DATA_VERIFICACAO desc");
		query.setParameter("id", idGabarito);
		return query.getResultList();
	}

	public List<QUA_MC_MOV_VERIF_GABARITO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_MOV_VERIF_GABARITO a where a.ID_VERIF_GABARITO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
