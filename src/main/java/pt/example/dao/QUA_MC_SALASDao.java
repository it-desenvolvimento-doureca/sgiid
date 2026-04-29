package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_SALAS;

public class QUA_MC_SALASDao extends GenericDaoJpaImpl<QUA_MC_SALAS, Integer>
		implements GenericDao<QUA_MC_SALAS, Integer> {
	public QUA_MC_SALASDao() {
		super(QUA_MC_SALAS.class);
	}

	public List<QUA_MC_SALAS> getall() {
		Query query = entityManager.createQuery(
			"Select a, " +
			"(select b.LOCAL_SECCAO from QUA_MC_DIC_SECCOES b where b.ID_SECCAO = a.ID_SECCAO) as NOME_SECCAO " +
			"from QUA_MC_SALAS a order by a.DESIGNACAO_ESPACO");
		return query.getResultList();
	}

	public List<QUA_MC_SALAS> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_SALAS a where a.ID_SALA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
