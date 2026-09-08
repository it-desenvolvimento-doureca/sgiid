package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_TIPO_ENSAIO;

public class QUA_CR_DIC_TIPO_ENSAIODao extends GenericDaoJpaImpl<QUA_CR_DIC_TIPO_ENSAIO, Integer>
		implements GenericDao<QUA_CR_DIC_TIPO_ENSAIO, Integer> {
	public QUA_CR_DIC_TIPO_ENSAIODao() {
		super(QUA_CR_DIC_TIPO_ENSAIO.class);
	}

	public List<QUA_CR_DIC_TIPO_ENSAIO> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_TIPO_ENSAIO a where a.ATIVO = true order by a.ORDEM");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_TIPO_ENSAIO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_DIC_TIPO_ENSAIO a where a.ID_TIPO_ENSAIO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}