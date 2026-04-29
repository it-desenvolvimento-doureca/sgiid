package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_MOV_VERIF_INT_GABARITO;

public class QUA_MC_MOV_VERIF_INT_GABARITODao extends GenericDaoJpaImpl<QUA_MC_MOV_VERIF_INT_GABARITO, Integer>
		implements GenericDao<QUA_MC_MOV_VERIF_INT_GABARITO, Integer> {
	public QUA_MC_MOV_VERIF_INT_GABARITODao() {
		super(QUA_MC_MOV_VERIF_INT_GABARITO.class);
	}

	public List<QUA_MC_MOV_VERIF_INT_GABARITO> getbyVerifGabarito(Integer idVerifGabarito) {
		Query query = entityManager.createQuery(
			"Select a from QUA_MC_MOV_VERIF_INT_GABARITO a where a.ID_VERIF_GABARITO = :id order by a.NUM_MEDIDA");
		query.setParameter("id", idVerifGabarito);
		return query.getResultList();
	}

	public List<QUA_MC_MOV_VERIF_INT_GABARITO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_MOV_VERIF_INT_GABARITO a where a.ID_VERIF_INT_GABARITO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
