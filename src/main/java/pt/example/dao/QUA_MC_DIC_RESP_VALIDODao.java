package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DIC_RESP_VALIDACAO;

public class QUA_MC_DIC_RESP_VALIDODao extends GenericDaoJpaImpl<QUA_MC_DIC_RESP_VALIDACAO, Integer>
		implements GenericDao<QUA_MC_DIC_RESP_VALIDACAO, Integer> {
	public QUA_MC_DIC_RESP_VALIDODao() {
		super(QUA_MC_DIC_RESP_VALIDACAO.class);
	}

	public List<QUA_MC_DIC_RESP_VALIDACAO> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_RESP_VALIDACAO a order by a.RESP_VALIDACAO");
		return query.getResultList();
	}

	public List<QUA_MC_DIC_RESP_VALIDACAO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_RESP_VALIDACAO a where a.ID_RESP_VALIDACAO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
