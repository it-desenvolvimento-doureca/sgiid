package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DIC_TIPO_CALIBRACAO;

public class QUA_MC_DIC_TIPO_CALIBRDao extends GenericDaoJpaImpl<QUA_MC_DIC_TIPO_CALIBRACAO, Integer>
		implements GenericDao<QUA_MC_DIC_TIPO_CALIBRACAO, Integer> {
	public QUA_MC_DIC_TIPO_CALIBRDao() {
		super(QUA_MC_DIC_TIPO_CALIBRACAO.class);
	}

	public List<QUA_MC_DIC_TIPO_CALIBRACAO> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_TIPO_CALIBRACAO a order by a.TIPO_CALIB_EQUP");
		return query.getResultList();
	}

	public List<QUA_MC_DIC_TIPO_CALIBRACAO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_TIPO_CALIBRACAO a where a.ID_TIPO_CALIBRACAO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
