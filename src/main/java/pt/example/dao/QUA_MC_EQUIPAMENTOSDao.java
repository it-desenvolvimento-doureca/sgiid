package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_EQUIPAMENTOS;

public class QUA_MC_EQUIPAMENTOSDao extends GenericDaoJpaImpl<QUA_MC_EQUIPAMENTOS, Integer>
		implements GenericDao<QUA_MC_EQUIPAMENTOS, Integer> {
	public QUA_MC_EQUIPAMENTOSDao() {
		super(QUA_MC_EQUIPAMENTOS.class);
	}

	// Versão leve (entidade + secção + responsável) - usada por dropdowns
	public List<QUA_MC_EQUIPAMENTOS> getall() {
		Query query = entityManager.createQuery(
			"Select a, " +
			"(select b.LOCAL_SECCAO from QUA_MC_DIC_SECCOES b where b.ID_SECCAO = a.ID_SECCAO) as NOME_SECCAO, " +
			"(select c.RESP_VALIDACAO from QUA_MC_DIC_RESP_VALIDACAO c where c.ID_RESP_VALIDACAO = a.ID_RESP_ENTRADA) as NOME_RESP " +
			"from QUA_MC_EQUIPAMENTOS a order by a.DESIGNACAO");
		return query.getResultList();
	}

	// Lista (nativa, performante): Estado Metrológico/MSA da calibração mais recente via OUTER APPLY
	public List<Object[]> getlista() {
		String sql =
			"SELECT a.ID_EQUIPAMENTO AS C0, a.DESIGNACAO AS C1, a.COD_INTERNO AS C2, a.EM_UTILIZACAO AS C3, a.OBSOLETO AS C4, " +
			" s.LOCAL_SECCAO AS C5, em.DESIGNACAO AS C6, em.COR AS C7, msa.DESIGNACAO AS C8, msa.COR AS C9, lc.DATA_CALIBRACAO AS C10 " +
			"FROM QUA_MC_EQUIPAMENTOS a " +
			"LEFT JOIN QUA_MC_DIC_SECCOES s ON s.ID_SECCAO = a.ID_SECCAO " +
			"OUTER APPLY ( SELECT TOP 1 d.ID_ESTADO_METROLOGICO, d.ID_MSA, d.DATA_CALIBRACAO " +
			"  FROM QUA_MC_MOV_CALIB_EQUIP_DET d INNER JOIN QUA_MC_MOV_CALIB_EQUIP h ON d.ID_CALIB_EQUIP = h.ID_CALIB_EQUIP " +
			"  WHERE h.ID_EQUIPAMENTO = a.ID_EQUIPAMENTO ORDER BY d.DATA_CALIBRACAO DESC, d.ID_CALIB_EQUIP_DET DESC ) lc " +
			"LEFT JOIN QUA_MC_DIC_ESTADO_METROLOGICO em ON em.ID_ESTADO_METROLOGICO = lc.ID_ESTADO_METROLOGICO " +
			"LEFT JOIN QUA_MC_DIC_MSA msa ON msa.ID_MSA = lc.ID_MSA " +
			"ORDER BY a.DESIGNACAO";
		return entityManager.createNativeQuery(sql).getResultList();
	}

	public List<QUA_MC_EQUIPAMENTOS> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_EQUIPAMENTOS a where a.ID_EQUIPAMENTO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
