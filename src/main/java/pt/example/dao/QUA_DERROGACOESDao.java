package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.QUA_DERROGACOES;

public class QUA_DERROGACOESDao extends GenericDaoJpaImpl<QUA_DERROGACOES, Integer>
		implements GenericDao<QUA_DERROGACOES, Integer> {
	public QUA_DERROGACOESDao() {
		super(QUA_DERROGACOES.class);
	}

	public List<QUA_DERROGACOES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from QUA_DERROGACOES a where a.ID_DERROGACAO = :id ");
		query.setParameter("id", id);
		List<QUA_DERROGACOES> data = query.getResultList();
		return data;

	}
	

	public List<QUA_DERROGACOES> getall() {

		Query query = entityManager.createQuery("Select a ,(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.EMISSOR) as NOME_EMISSOR "
				+ ",(select c.DESCRICAO from GER_SECCAO c where c.ID = a.SETOR) as NOME_SETOR from QUA_DERROGACOES a order by a.ID_DERROGACAO DESC");
		List<QUA_DERROGACOES> data = query.getResultList();
		return data;

	}

	public List<QUA_DERROGACOES> getall_dash() {

		Query query = entityManager.createQuery("Select a ,(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.EMISSOR) as NOME_EMISSOR "
				+ ",(select c.DESCRICAO from GER_SECCAO c where c.ID = a.SETOR) as NOME_SETOR from QUA_DERROGACOES a order by a.DATA_FIM asc");
		List<QUA_DERROGACOES> data = query.getResultList();
		return data;

	}
}
