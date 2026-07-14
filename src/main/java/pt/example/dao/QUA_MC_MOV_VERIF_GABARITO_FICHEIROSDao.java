package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_MOV_VERIF_GABARITO_FICHEIROS;

public class QUA_MC_MOV_VERIF_GABARITO_FICHEIROSDao extends GenericDaoJpaImpl<QUA_MC_MOV_VERIF_GABARITO_FICHEIROS, Integer>
		implements GenericDao<QUA_MC_MOV_VERIF_GABARITO_FICHEIROS, Integer> {
	public QUA_MC_MOV_VERIF_GABARITO_FICHEIROSDao() {
		super(QUA_MC_MOV_VERIF_GABARITO_FICHEIROS.class);
	}

	public List<QUA_MC_MOV_VERIF_GABARITO_FICHEIROS> getbyVerifGabarito(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_MC_MOV_VERIF_GABARITO_FICHEIROS a where a.ID_VERIF_GABARITO = :id and a.ATIVO = true order by a.DATA_CRIA");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
