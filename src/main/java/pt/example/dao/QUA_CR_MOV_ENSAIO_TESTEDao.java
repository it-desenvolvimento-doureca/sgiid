package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_MOV_ENSAIO_TESTE;

public class QUA_CR_MOV_ENSAIO_TESTEDao extends GenericDaoJpaImpl<QUA_CR_MOV_ENSAIO_TESTE, Integer>
		implements GenericDao<QUA_CR_MOV_ENSAIO_TESTE, Integer> {
	public QUA_CR_MOV_ENSAIO_TESTEDao() {
		super(QUA_CR_MOV_ENSAIO_TESTE.class);
	}

	public List<QUA_CR_MOV_ENSAIO_TESTE> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_ENSAIO_TESTE a where a.ATIVO = true order by a.NUM_TESTE");
		return query.getResultList();
	}

	public List<QUA_CR_MOV_ENSAIO_TESTE> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_MOV_ENSAIO_TESTE a where a.ID_ENSAIO_TESTE = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_MOV_ENSAIO_TESTE> getbyEnsaioCab(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_ENSAIO_TESTE a where a.ID_ENSAIO_CAB = :id and a.ATIVO = true order by a.NUM_TESTE");
		query.setParameter("id", id);
		return query.getResultList();
	}
}