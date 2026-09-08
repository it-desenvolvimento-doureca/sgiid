package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_CONJ_EQUIP_LIN;

public class QUA_CR_DIC_CONJ_EQUIP_LINDao extends GenericDaoJpaImpl<QUA_CR_DIC_CONJ_EQUIP_LIN, Integer>
		implements GenericDao<QUA_CR_DIC_CONJ_EQUIP_LIN, Integer> {
	public QUA_CR_DIC_CONJ_EQUIP_LINDao() {
		super(QUA_CR_DIC_CONJ_EQUIP_LIN.class);
	}

	public List<QUA_CR_DIC_CONJ_EQUIP_LIN> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_CONJ_EQUIP_LIN a where a.ATIVO = true order by a.ORDEM");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_CONJ_EQUIP_LIN> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_DIC_CONJ_EQUIP_LIN a where a.ID_CONJ_EQUIP_LIN = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_DIC_CONJ_EQUIP_LIN> getbyConjEquip(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_CONJ_EQUIP_LIN a where a.ID_CONJ_EQUIP = :id and a.ATIVO = true order by a.ORDEM");
		query.setParameter("id", id);
		return query.getResultList();
	}
}