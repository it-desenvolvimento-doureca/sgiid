package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_DIC_TIPO_ACEITACAO;

public class QUA_MC_DIC_TIPO_ACEITDao extends GenericDaoJpaImpl<QUA_MC_DIC_TIPO_ACEITACAO, Integer>
		implements GenericDao<QUA_MC_DIC_TIPO_ACEITACAO, Integer> {
	public QUA_MC_DIC_TIPO_ACEITDao() {
		super(QUA_MC_DIC_TIPO_ACEITACAO.class);
	}

	public List<QUA_MC_DIC_TIPO_ACEITACAO> getall() {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_TIPO_ACEITACAO a order by a.TIPO_ACEIT_CALIB_EQUIP");
		return query.getResultList();
	}

	public List<QUA_MC_DIC_TIPO_ACEITACAO> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_DIC_TIPO_ACEITACAO a where a.ID_TIPO_ACEITACAO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
