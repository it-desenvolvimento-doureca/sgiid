package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_MOV_CALIB_EQUIP;

public class QUA_MC_MOV_CALIB_EQUIPDao extends GenericDaoJpaImpl<QUA_MC_MOV_CALIB_EQUIP, Integer>
		implements GenericDao<QUA_MC_MOV_CALIB_EQUIP, Integer> {
	public QUA_MC_MOV_CALIB_EQUIPDao() {
		super(QUA_MC_MOV_CALIB_EQUIP.class);
	}

	public List<QUA_MC_MOV_CALIB_EQUIP> getbyEquipamento(Integer idEquipamento) {
		Query query = entityManager.createQuery("Select a from QUA_MC_MOV_CALIB_EQUIP a where a.ID_EQUIPAMENTO = :id");
		query.setParameter("id", idEquipamento);
		return query.getResultList();
	}

	public List<QUA_MC_MOV_CALIB_EQUIP> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_MOV_CALIB_EQUIP a where a.ID_CALIB_EQUIP = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
