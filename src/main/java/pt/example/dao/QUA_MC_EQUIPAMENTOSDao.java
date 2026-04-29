package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_EQUIPAMENTOS;

public class QUA_MC_EQUIPAMENTOSDao extends GenericDaoJpaImpl<QUA_MC_EQUIPAMENTOS, Integer>
		implements GenericDao<QUA_MC_EQUIPAMENTOS, Integer> {
	public QUA_MC_EQUIPAMENTOSDao() {
		super(QUA_MC_EQUIPAMENTOS.class);
	}

	public List<QUA_MC_EQUIPAMENTOS> getall() {
		Query query = entityManager.createQuery(
			"Select a, " +
			"(select b.LOCAL_SECCAO from QUA_MC_DIC_SECCOES b where b.ID_SECCAO = a.ID_SECCAO) as NOME_SECCAO, " +
			"(select c.RESP_VALIDACAO from QUA_MC_DIC_RESP_VALIDACAO c where c.ID_RESP_VALIDACAO = a.ID_RESP_ENTRADA) as NOME_RESP " +
			"from QUA_MC_EQUIPAMENTOS a order by a.DESIGNACAO");
		return query.getResultList();
	}

	public List<QUA_MC_EQUIPAMENTOS> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_EQUIPAMENTOS a where a.ID_EQUIPAMENTO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
