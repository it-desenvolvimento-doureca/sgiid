package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DIC_PECAS_CROMADAS;

public class QUA_MC_DIC_PECAS_CROMDao extends GenericDaoJpaImpl<QUA_MC_DIC_PECAS_CROMADAS, Integer>
		implements GenericDao<QUA_MC_DIC_PECAS_CROMADAS, Integer> {
	public QUA_MC_DIC_PECAS_CROMDao() {
		super(QUA_MC_DIC_PECAS_CROMADAS.class);
	}

	public List<QUA_MC_DIC_PECAS_CROMADAS> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_PECAS_CROMADAS a order by a.REFERENCIA");
		return query.getResultList();
	}

	public List<QUA_MC_DIC_PECAS_CROMADAS> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_PECAS_CROMADAS a where a.ID_PECA_CROMADA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
