package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_TIPO_PECA;

public class QUA_CR_DIC_TIPO_PECADao extends GenericDaoJpaImpl<QUA_CR_DIC_TIPO_PECA, Integer>
		implements GenericDao<QUA_CR_DIC_TIPO_PECA, Integer> {
	public QUA_CR_DIC_TIPO_PECADao() {
		super(QUA_CR_DIC_TIPO_PECA.class);
	}

	public List<QUA_CR_DIC_TIPO_PECA> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_TIPO_PECA a where a.ATIVO = true order by a.ORDEM");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_TIPO_PECA> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_DIC_TIPO_PECA a where a.ID_TIPO_PECA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}