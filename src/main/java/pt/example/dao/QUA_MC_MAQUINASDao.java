package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_MAQUINAS;

public class QUA_MC_MAQUINASDao extends GenericDaoJpaImpl<QUA_MC_MAQUINAS, Integer>
		implements GenericDao<QUA_MC_MAQUINAS, Integer> {
	public QUA_MC_MAQUINASDao() {
		super(QUA_MC_MAQUINAS.class);
	}

	public List<QUA_MC_MAQUINAS> getall() {
		Query query = entityManager.createQuery(
			"Select a, " +
			"(select b.LOCAL_SECCAO from QUA_MC_DIC_SECCOES b where b.ID_SECCAO = a.ID_SECCAO) as NOME_SECCAO " +
			"from QUA_MC_MAQUINAS a order by a.DESIGNACAO");
		return query.getResultList();
	}

	public List<QUA_MC_MAQUINAS> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_MAQUINAS a where a.ID_MAQUINA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
