package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_MOV_VERIF_MAQUINA;

public class QUA_MC_MOV_VERIF_MAQUINADao extends GenericDaoJpaImpl<QUA_MC_MOV_VERIF_MAQUINA, Integer>
		implements GenericDao<QUA_MC_MOV_VERIF_MAQUINA, Integer> {
	public QUA_MC_MOV_VERIF_MAQUINADao() {
		super(QUA_MC_MOV_VERIF_MAQUINA.class);
	}

	public List<QUA_MC_MOV_VERIF_MAQUINA> getbyMaquina(Integer idMaquina) {
		Query query = entityManager.createQuery(
			"Select a from QUA_MC_MOV_VERIF_MAQUINA a where a.ID_MAQUINA = :id order by a.DATA_VERIFICACAO desc");
		query.setParameter("id", idMaquina);
		return query.getResultList();
	}

	public List<QUA_MC_MOV_VERIF_MAQUINA> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_MOV_VERIF_MAQUINA a where a.ID_VERIF_MAQUINA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
