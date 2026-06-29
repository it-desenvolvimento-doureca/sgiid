package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DECLARACOES_NC;

public class QUA_MC_DECLARACOES_NCDao extends GenericDaoJpaImpl<QUA_MC_DECLARACOES_NC, Integer>
		implements GenericDao<QUA_MC_DECLARACOES_NC, Integer> {
	public QUA_MC_DECLARACOES_NCDao() {
		super(QUA_MC_DECLARACOES_NC.class);
	}

	public List<QUA_MC_DECLARACOES_NC> getall() {
		Query query = entityManager.createQuery(
			"Select a, " +
			"(select e.DESIGNACAO from QUA_MC_EQUIPAMENTOS e where e.ID_EQUIPAMENTO = a.ID_EQUIPAMENTO) as EQUIPAMENTO_DES, " +
			"(select u.NOME_UTILIZADOR from GER_UTILIZADORES u where u.ID_UTILIZADOR = a.UTZ_CRIA) as EMITIDA_POR_NOME " +
			"from QUA_MC_DECLARACOES_NC a where a.ATIVO = true order by a.DATA_EMISSAO desc, a.ID_DECLARACAO desc");
		return query.getResultList();
	}

	public List<QUA_MC_DECLARACOES_NC> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DECLARACOES_NC a where a.ID_DECLARACAO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
