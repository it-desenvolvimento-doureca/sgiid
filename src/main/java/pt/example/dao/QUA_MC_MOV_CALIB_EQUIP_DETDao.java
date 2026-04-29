package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_MOV_CALIB_EQUIP_DET;

public class QUA_MC_MOV_CALIB_EQUIP_DETDao extends GenericDaoJpaImpl<QUA_MC_MOV_CALIB_EQUIP_DET, Integer>
		implements GenericDao<QUA_MC_MOV_CALIB_EQUIP_DET, Integer> {
	public QUA_MC_MOV_CALIB_EQUIP_DETDao() {
		super(QUA_MC_MOV_CALIB_EQUIP_DET.class);
	}

	public List<QUA_MC_MOV_CALIB_EQUIP_DET> getbyCalibEquip(Integer idCalibEquip) {
		Query query = entityManager.createQuery(
			"Select a from QUA_MC_MOV_CALIB_EQUIP_DET a where a.ID_CALIB_EQUIP = :id order by a.NUM");
		query.setParameter("id", idCalibEquip);
		return query.getResultList();
	}

	public List<QUA_MC_MOV_CALIB_EQUIP_DET> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_MOV_CALIB_EQUIP_DET a where a.ID_CALIB_EQUIP_DET = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
