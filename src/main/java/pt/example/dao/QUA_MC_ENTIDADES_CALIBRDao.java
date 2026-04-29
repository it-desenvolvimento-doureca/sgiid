package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_ENTIDADES_CALIBRACAO;

public class QUA_MC_ENTIDADES_CALIBRDao extends GenericDaoJpaImpl<QUA_MC_ENTIDADES_CALIBRACAO, Integer>
		implements GenericDao<QUA_MC_ENTIDADES_CALIBRACAO, Integer> {
	public QUA_MC_ENTIDADES_CALIBRDao() {
		super(QUA_MC_ENTIDADES_CALIBRACAO.class);
	}

	public List<QUA_MC_ENTIDADES_CALIBRACAO> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_ENTIDADES_CALIBRACAO a order by a.NOME_ENTIDADE");
		return query.getResultList();
	}

	public List<QUA_MC_ENTIDADES_CALIBRACAO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_ENTIDADES_CALIBRACAO a where a.ID_ENTIDADE_CALIBRACAO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
