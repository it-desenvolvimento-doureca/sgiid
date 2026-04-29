package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_ENTIDADES_CALIBRACAO_CERTIF;

public class QUA_MC_ENTIDADES_CERTIFDao extends GenericDaoJpaImpl<QUA_MC_ENTIDADES_CALIBRACAO_CERTIF, Integer>
		implements GenericDao<QUA_MC_ENTIDADES_CALIBRACAO_CERTIF, Integer> {
	public QUA_MC_ENTIDADES_CERTIFDao() {
		super(QUA_MC_ENTIDADES_CALIBRACAO_CERTIF.class);
	}

	public List<QUA_MC_ENTIDADES_CALIBRACAO_CERTIF> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_ENTIDADES_CALIBRACAO_CERTIF a order by a.ID_CERTIF");
		return query.getResultList();
	}

	public List<QUA_MC_ENTIDADES_CALIBRACAO_CERTIF> getbyEntidade(Integer idEntidade) {
		Query query = entityManager.createQuery("Select a from QUA_MC_ENTIDADES_CALIBRACAO_CERTIF a where a.ID_ENTIDADE_CALIBRACAO = :id order by a.NUM");
		query.setParameter("id", idEntidade);
		return query.getResultList();
	}

	public List<QUA_MC_ENTIDADES_CALIBRACAO_CERTIF> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_ENTIDADES_CALIBRACAO_CERTIF a where a.ID_CERTIF = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
