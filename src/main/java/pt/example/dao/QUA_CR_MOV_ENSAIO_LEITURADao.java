package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_MOV_ENSAIO_LEITURA;

public class QUA_CR_MOV_ENSAIO_LEITURADao extends GenericDaoJpaImpl<QUA_CR_MOV_ENSAIO_LEITURA, Integer>
		implements GenericDao<QUA_CR_MOV_ENSAIO_LEITURA, Integer> {
	public QUA_CR_MOV_ENSAIO_LEITURADao() {
		super(QUA_CR_MOV_ENSAIO_LEITURA.class);
	}

	public List<QUA_CR_MOV_ENSAIO_LEITURA> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_ENSAIO_LEITURA a where a.ATIVO = true order by a.NUM_LEITURA");
		return query.getResultList();
	}

	public List<QUA_CR_MOV_ENSAIO_LEITURA> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_CR_MOV_ENSAIO_LEITURA a where a.ID_ENSAIO_LEITURA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<QUA_CR_MOV_ENSAIO_LEITURA> getbyEnsaioTeste(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_ENSAIO_LEITURA a where a.ID_ENSAIO_TESTE = :id and a.ATIVO = true order by a.NUM_LEITURA");
		query.setParameter("id", id);
		return query.getResultList();
	}
}