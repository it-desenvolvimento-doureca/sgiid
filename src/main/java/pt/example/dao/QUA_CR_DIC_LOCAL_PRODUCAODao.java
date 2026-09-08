package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_LOCAL_PRODUCAO;

public class QUA_CR_DIC_LOCAL_PRODUCAODao extends GenericDaoJpaImpl<QUA_CR_DIC_LOCAL_PRODUCAO, Integer>
		implements GenericDao<QUA_CR_DIC_LOCAL_PRODUCAO, Integer> {
	public QUA_CR_DIC_LOCAL_PRODUCAODao() {
		super(QUA_CR_DIC_LOCAL_PRODUCAO.class);
	}

	public List<QUA_CR_DIC_LOCAL_PRODUCAO> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_LOCAL_PRODUCAO a where a.ATIVO = true order by a.ORDEM");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_LOCAL_PRODUCAO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_DIC_LOCAL_PRODUCAO a where a.ID_LOCAL_PRODUCAO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}