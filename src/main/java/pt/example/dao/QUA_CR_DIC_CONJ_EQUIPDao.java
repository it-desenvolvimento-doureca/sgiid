package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_CONJ_EQUIP;

public class QUA_CR_DIC_CONJ_EQUIPDao extends GenericDaoJpaImpl<QUA_CR_DIC_CONJ_EQUIP, Integer>
		implements GenericDao<QUA_CR_DIC_CONJ_EQUIP, Integer> {
	public QUA_CR_DIC_CONJ_EQUIPDao() {
		super(QUA_CR_DIC_CONJ_EQUIP.class);
	}

	public List<QUA_CR_DIC_CONJ_EQUIP> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_CONJ_EQUIP a where a.ATIVO = true order by a.DESIGNACAO");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_CONJ_EQUIP> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_DIC_CONJ_EQUIP a where a.ID_CONJ_EQUIP = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}