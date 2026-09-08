package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_MOV_ESPESSURA;

public class QUA_CR_MOV_ESPESSURADao extends GenericDaoJpaImpl<QUA_CR_MOV_ESPESSURA, Integer>
		implements GenericDao<QUA_CR_MOV_ESPESSURA, Integer> {
	public QUA_CR_MOV_ESPESSURADao() {
		super(QUA_CR_MOV_ESPESSURA.class);
	}

	public List<QUA_CR_MOV_ESPESSURA> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_ESPESSURA a where a.ATIVO = true order by a.NUM_AMOSTRA");
		return query.getResultList();
	}

	public List<QUA_CR_MOV_ESPESSURA> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_MOV_ESPESSURA a where a.ID_ESPESSURA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_MOV_ESPESSURA> getbyEspessuraCab(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_ESPESSURA a where a.ID_ESPESSURA_CAB = :id and a.ATIVO = true order by a.NUM_AMOSTRA");
		query.setParameter("id", id);
		return query.getResultList();
	}
}