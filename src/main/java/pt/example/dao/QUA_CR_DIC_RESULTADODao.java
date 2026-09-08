package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_RESULTADO;

public class QUA_CR_DIC_RESULTADODao extends GenericDaoJpaImpl<QUA_CR_DIC_RESULTADO, Integer>
		implements GenericDao<QUA_CR_DIC_RESULTADO, Integer> {
	public QUA_CR_DIC_RESULTADODao() {
		super(QUA_CR_DIC_RESULTADO.class);
	}

	public List<QUA_CR_DIC_RESULTADO> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_RESULTADO a where a.ATIVO = true order by a.ORDEM");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_RESULTADO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_DIC_RESULTADO a where a.ID_RESULTADO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}