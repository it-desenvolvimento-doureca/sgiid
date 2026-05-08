package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DIC_RESULTADO_VALIDACAO;

public class QUA_MC_DIC_RESULT_VALIDDao extends GenericDaoJpaImpl<QUA_MC_DIC_RESULTADO_VALIDACAO, Integer>
		implements GenericDao<QUA_MC_DIC_RESULTADO_VALIDACAO, Integer> {
	public QUA_MC_DIC_RESULT_VALIDDao() {
		super(QUA_MC_DIC_RESULTADO_VALIDACAO.class);
	}

	public List<QUA_MC_DIC_RESULTADO_VALIDACAO> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_RESULTADO_VALIDACAO a order by a.RESULTADO_VALIDACAO");
		return query.getResultList();
	}

	public List<QUA_MC_DIC_RESULTADO_VALIDACAO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_RESULTADO_VALIDACAO a where a.ID_RESULTADO_VALIDACAO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
