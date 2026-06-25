package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_MOV_CALIB_EQUIP_FICHEIROS;

public class QUA_MC_MOV_CALIB_EQUIP_FICHEIROSDao extends GenericDaoJpaImpl<QUA_MC_MOV_CALIB_EQUIP_FICHEIROS, Integer>
		implements GenericDao<QUA_MC_MOV_CALIB_EQUIP_FICHEIROS, Integer> {
	public QUA_MC_MOV_CALIB_EQUIP_FICHEIROSDao() {
		super(QUA_MC_MOV_CALIB_EQUIP_FICHEIROS.class);
	}

	public List<QUA_MC_MOV_CALIB_EQUIP_FICHEIROS> getbyCalibDet(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_MC_MOV_CALIB_EQUIP_FICHEIROS a where a.ID_CALIB_EQUIP_DET = :id and a.ATIVO = true order by a.DATA_CRIA");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
