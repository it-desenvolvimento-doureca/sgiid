package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_REF_TESTE;

public class QUA_CR_DIC_REF_TESTEDao extends GenericDaoJpaImpl<QUA_CR_DIC_REF_TESTE, Integer>
		implements GenericDao<QUA_CR_DIC_REF_TESTE, Integer> {
	public QUA_CR_DIC_REF_TESTEDao() {
		super(QUA_CR_DIC_REF_TESTE.class);
	}

	public List<QUA_CR_DIC_REF_TESTE> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_REF_TESTE a where a.ATIVO = true order by a.ORDEM");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_REF_TESTE> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_DIC_REF_TESTE a where a.ID_REF_TESTE = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_DIC_REF_TESTE> getbyReferencia(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_REF_TESTE a where a.ID_REFERENCIA = :id and a.ATIVO = true order by a.ORDEM");
		query.setParameter("id", id);
		return query.getResultList();
	}
}