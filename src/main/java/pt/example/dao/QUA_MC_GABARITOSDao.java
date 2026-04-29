package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_GABARITOS;

public class QUA_MC_GABARITOSDao extends GenericDaoJpaImpl<QUA_MC_GABARITOS, Integer>
		implements GenericDao<QUA_MC_GABARITOS, Integer> {
	public QUA_MC_GABARITOSDao() {
		super(QUA_MC_GABARITOS.class);
	}

	public List<QUA_MC_GABARITOS> getall() {
		Query query = entityManager.createQuery(
			"Select a, " +
			"(select b.LOCAL_SECCAO from QUA_MC_DIC_SECCOES b where b.ID_SECCAO = a.ID_SECCAO) as NOME_SECCAO " +
			"from QUA_MC_GABARITOS a order by a.NOME_GABARITO");
		return query.getResultList();
	}

	public List<QUA_MC_GABARITOS> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_GABARITOS a where a.ID_GABARITO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
