package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_GABARITOS;

public class QUA_MC_GABARITOSDao extends GenericDaoJpaImpl<QUA_MC_GABARITOS, Integer>
		implements GenericDao<QUA_MC_GABARITOS, Integer> {
	public QUA_MC_GABARITOSDao() {
		super(QUA_MC_GABARITOS.class);
	}

	// Versão leve (entidade + secção) - usada por dropdowns
	public List<QUA_MC_GABARITOS> getall() {
		Query query = entityManager.createQuery(
			"Select a, " +
			"(select b.LOCAL_SECCAO from QUA_MC_DIC_SECCOES b where b.ID_SECCAO = a.ID_SECCAO) as NOME_SECCAO " +
			"from QUA_MC_GABARITOS a order by a.NOME_GABARITO");
		return query.getResultList();
	}

	// Lista (nativa, performante): Estado Metrológico/MSA da verificação mais recente via OUTER APPLY (lv).
	// C10 = última verificação (qualquer tipo); C11 = próxima verificação calculada SÓ a partir da
	// verificação interna ou estudo 3D mais recente (lvp) -- exclui R+R/externa (pedido cliente).
	// C12 = data em que passou a obsoleto; C13 = data entrada; C14 = verificação em atraso.
	public List<Object[]> getlista() {
		String sql =
			"SELECT a.ID_GABARITO AS C0, a.NOME_GABARITO AS C1, a.CODIGO_GABARITO AS C2, a.EM_UTILIZACAO AS C3, a.OBSOLETO AS C4, " +
			" s.LOCAL_SECCAO AS C5, em.DESIGNACAO AS C6, em.COR AS C7, msa.DESIGNACAO AS C8, msa.COR AS C9, lv.DATA_VERIFICACAO AS C10, " +
			" CASE WHEN lvp.DATA_VERIFICACAO IS NULL OR a.FREQ_VERIFICACAO_MESES IS NULL THEN NULL " +
			"      ELSE DATEADD(MONTH, a.FREQ_VERIFICACAO_MESES, lvp.DATA_VERIFICACAO) END AS C11, a.DATA_OBSOLETO AS C12, a.DATA_ENTRADA AS C13, " +
			" CASE WHEN a.OBSOLETO = 0 AND (lvp.DATA_VERIFICACAO IS NULL OR DATEADD(MONTH, a.FREQ_VERIFICACAO_MESES, lvp.DATA_VERIFICACAO) < GETDATE()) THEN 1 ELSE 0 END AS C14 " +
			"FROM QUA_MC_GABARITOS a " +
			"LEFT JOIN QUA_MC_DIC_SECCOES s ON s.ID_SECCAO = a.ID_SECCAO " +
			"OUTER APPLY ( SELECT TOP 1 v.ID_ESTADO_METROLOGICO, v.ID_MSA, v.DATA_VERIFICACAO " +
			"  FROM QUA_MC_MOV_VERIF_GABARITO v WHERE v.ID_GABARITO = a.ID_GABARITO " +
			"  ORDER BY v.DATA_VERIFICACAO DESC, v.ID_VERIF_GABARITO DESC ) lv " +
			"OUTER APPLY ( SELECT TOP 1 v.DATA_VERIFICACAO " +
			"  FROM QUA_MC_MOV_VERIF_GABARITO v " +
			"  LEFT JOIN QUA_MC_DIC_TIPO_VERIF_GABARIT tv ON tv.ID_TIPO_VERIF_GABARIT = v.ID_TIPO_VERIF_GABARIT " +
			"  WHERE v.ID_GABARITO = a.ID_GABARITO " +
			"    AND ( UPPER(tv.TIPO_VERIF_GABARIT) LIKE '%INTERN%' OR UPPER(tv.TIPO_VERIF_GABARIT) LIKE '%3D%' ) " +
			"  ORDER BY v.DATA_VERIFICACAO DESC, v.ID_VERIF_GABARITO DESC ) lvp " +
			"LEFT JOIN QUA_MC_DIC_ESTADO_METROLOGICO em ON em.ID_ESTADO_METROLOGICO = lv.ID_ESTADO_METROLOGICO " +
			"LEFT JOIN QUA_MC_DIC_MSA msa ON msa.ID_MSA = lv.ID_MSA " +
			"ORDER BY a.NOME_GABARITO";
		return entityManager.createNativeQuery(sql).getResultList();
	}

	public List<QUA_MC_GABARITOS> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from QUA_MC_GABARITOS a where a.ID_GABARITO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
