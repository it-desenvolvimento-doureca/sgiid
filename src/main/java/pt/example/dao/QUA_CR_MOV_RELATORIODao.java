package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_MOV_RELATORIO;

public class QUA_CR_MOV_RELATORIODao extends GenericDaoJpaImpl<QUA_CR_MOV_RELATORIO, Integer>
		implements GenericDao<QUA_CR_MOV_RELATORIO, Integer> {
	public QUA_CR_MOV_RELATORIODao() {
		super(QUA_CR_MOV_RELATORIO.class);
	}

	public List<QUA_CR_MOV_RELATORIO> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_RELATORIO a where a.ATIVO = true order by a.ANO desc, a.NUM_SEQ desc");
		return query.getResultList();
	}

	public List<QUA_CR_MOV_RELATORIO> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_MOV_RELATORIO a where a.ID_RELATORIO = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	// Listagem do ecra, denormalizada. Devolve Object[] com aliases posicionais
	// (o padrao dos ecras de lista do SGIID - ver QUA_MC_EQUIPAMENTOSDao).
	//
	// C0=ID  C1=N_RELAT_CR  C2=REFERENCIA  C3=DESIGNACAO  C4=CLIENTE
	// C5=TIPO_ENSAIO  C6=LINHA  C7=LOTE  C8=DATA_HORA_PRODUCAO
	// C9=DATA_ENTRADA_LAB  C10=LOCAL_PRODUCAO
	// C11=FAZ_ESPESSURAS  C12=FAZ_PAUTAS  C13=FAZ_CORROSAO
	// C14=RESULTADO_ESPESSURAS  C15=RESULTADO_PAUTAS  C16=RESULTADO_CACL2
	// C17=RESULTADO_NSS
	//
	// Os C14..C17 vem por OUTER APPLY dos cabecalhos de cada bloco: um
	// relatorio pode ter mais de uma medicao do mesmo tipo (6 relatorios de
	// condicoes, 2 de espessuras e 2 de pautas no Access tinham repeticoes), e
	// a lista mostra a mais recente.
	public List<Object[]> getlista(Integer ano) {
		String sql =
			"SELECT a.ID_RELATORIO AS C0, a.N_RELAT_CR AS C1, r.REFERENCIA AS C2, r.DESIGNACAO AS C3, "
			+ "       r.CLIENTE AS C4, te.CODIGO AS C5, l.NOME_LINHA AS C6, a.LOTE AS C7, "
			+ "       a.DATA_HORA_PRODUCAO AS C8, a.DATA_ENTRADA_LAB AS C9, lp.DESIGNACAO AS C10, "
			+ "       a.FAZ_ESPESSURAS AS C11, a.FAZ_PAUTAS AS C12, a.FAZ_CORROSAO AS C13, "
			+ "       esp.RESULTADO_TOTAL AS C14, ens.RESULTADO_TOTAL AS C15, "
			+ "       cor.RESULTADO_TOTAL_CACL2 AS C16, cor.RESULTADO_TOTAL_NSS AS C17 "
			+ "FROM QUA_CR_MOV_RELATORIO a "
			+ "LEFT JOIN QUA_CR_DIC_REFERENCIA r      ON r.ID_REFERENCIA = a.ID_REFERENCIA "
			+ "LEFT JOIN QUA_CR_DIC_TIPO_ENSAIO te    ON te.ID_TIPO_ENSAIO = a.ID_TIPO_ENSAIO "
			+ "LEFT JOIN QUA_CR_DIC_LOCAL_PRODUCAO lp ON lp.ID_LOCAL_PRODUCAO = a.ID_LOCAL_PRODUCAO "
			+ "LEFT JOIN AB_DIC_LINHA l               ON l.ID_LINHA = a.ID_LINHA "
			+ "OUTER APPLY ( SELECT TOP 1 x.RESULTADO_TOTAL FROM QUA_CR_MOV_ESPESSURA_CAB x "
			+ "              WHERE x.ID_RELATORIO = a.ID_RELATORIO AND x.ATIVO = 1 "
			+ "              ORDER BY x.ID_ESPESSURA_CAB DESC ) esp "
			+ "OUTER APPLY ( SELECT TOP 1 x.RESULTADO_TOTAL FROM QUA_CR_MOV_ENSAIO_CAB x "
			+ "              WHERE x.ID_RELATORIO = a.ID_RELATORIO AND x.ATIVO = 1 "
			+ "              ORDER BY x.ID_ENSAIO_CAB DESC ) ens "
			+ "OUTER APPLY ( SELECT TOP 1 x.RESULTADO_TOTAL_CACL2, x.RESULTADO_TOTAL_NSS "
			+ "              FROM QUA_CR_MOV_CORROSAO_CAB x "
			+ "              WHERE x.ID_RELATORIO = a.ID_RELATORIO AND x.ATIVO = 1 "
			+ "              ORDER BY x.ID_CORROSAO_CAB DESC ) cor "
			+ "WHERE a.ATIVO = 1 ";
		if (ano != null && ano > 0) {
			sql += "AND a.ANO = " + ano + " ";
		}
		sql += "ORDER BY a.ANO DESC, a.NUM_SEQ DESC";
		return entityManager.createNativeQuery(sql).getResultList();
	}

	// Seguimento por referencia: alimenta o ecra que substitui os 4 relatorios
	// "SEG PCS CROMADAS" do Access (que eram o mesmo relatorio com 1/2-3/4/5
	// blocos de teste visiveis).
	public List<Object[]> getlistabyReferencia(Integer idReferencia) {
		Query query = entityManager.createNativeQuery(
			// Os aliases C0..C7 sao obrigatorios: os tres cabecalhos tem todos uma
			// coluna RESULTADO_TOTAL, e sem alias distinto o Hibernate rejeita a
			// query nativa com NonUniqueDiscoveredSqlAliasException.
			"SELECT a.ID_RELATORIO AS C0, a.N_RELAT_CR AS C1, a.DATA_HORA_PRODUCAO AS C2, "
			+ "       te.CODIGO AS C3, a.LOTE AS C4, esp.RESULTADO_TOTAL AS C5, "
			+ "       ens.RESULTADO_TOTAL AS C6, cor.RESULTADO_TOTAL_CACL2 AS C7 "
			+ "FROM QUA_CR_MOV_RELATORIO a "
			+ "LEFT JOIN QUA_CR_DIC_TIPO_ENSAIO te ON te.ID_TIPO_ENSAIO = a.ID_TIPO_ENSAIO "
			+ "OUTER APPLY ( SELECT TOP 1 x.RESULTADO_TOTAL FROM QUA_CR_MOV_ESPESSURA_CAB x "
			+ "              WHERE x.ID_RELATORIO = a.ID_RELATORIO AND x.ATIVO = 1 "
			+ "              ORDER BY x.ID_ESPESSURA_CAB DESC ) esp "
			+ "OUTER APPLY ( SELECT TOP 1 x.RESULTADO_TOTAL FROM QUA_CR_MOV_ENSAIO_CAB x "
			+ "              WHERE x.ID_RELATORIO = a.ID_RELATORIO AND x.ATIVO = 1 "
			+ "              ORDER BY x.ID_ENSAIO_CAB DESC ) ens "
			+ "OUTER APPLY ( SELECT TOP 1 x.RESULTADO_TOTAL_CACL2 FROM QUA_CR_MOV_CORROSAO_CAB x "
			+ "              WHERE x.ID_RELATORIO = a.ID_RELATORIO AND x.ATIVO = 1 "
			+ "              ORDER BY x.ID_CORROSAO_CAB DESC ) cor "
			+ "WHERE a.ATIVO = 1 AND a.ID_REFERENCIA = :id "
			+ "ORDER BY a.DATA_HORA_PRODUCAO DESC");
		query.setParameter("id", idReferencia);
		return query.getResultList();
	}

	// Evolucao de resultados por mes. Substitui o relatorio Access "EVOLUCAO
	// RESULTADOS" (Consulta_EVOLUCAO_RESULTADOS), que contava OK / N OK RES /
	// N OK por bloco com cadeias de IIf() sobre os trios de booleanos. Aqui o
	// RESULTADO esta numa coluna, portanto e um SUM(CASE) direto.
	//
	// idLocal = 0 nao filtra. Com o local da Doureca, da o mesmo que o
	// relatorio "EVOLUCAO DE ENSAIOS - DOURECA".
	//
	// C0=ANO C1=MES C2=TOTAL
	// C3..C6   = espessuras OK / OK_COND / NOK / sem resultado
	// C7..C10  = pautas
	// C11..C14 = CaCl2
	// C15..C18 = NSS
	public List<Object[]> getanaliseevolucao(String dataIni, String dataFim, Integer idLocal) {
		String sql =
			"SELECT YEAR(r.DATA_HORA_PRODUCAO) AS C0, MONTH(r.DATA_HORA_PRODUCAO) AS C1, "
			+ "COUNT(*) AS C2, "
			+ blocoContagens("esp.RESULTADO_TOTAL",       "C3",  "C4",  "C5",  "C6")
			+ blocoContagens("ens.RESULTADO_TOTAL",       "C7",  "C8",  "C9",  "C10")
			+ blocoContagens("cor.RESULTADO_TOTAL_CACL2", "C11", "C12", "C13", "C14")
			+ blocoContagens("cor.RESULTADO_TOTAL_NSS",   "C15", "C16", "C17", "C18")
			+ "0 AS FIM "
			+ "FROM QUA_CR_MOV_RELATORIO r "
			+ aplicaBlocos()
			+ "WHERE r.ATIVO = 1 AND r.DATA_HORA_PRODUCAO IS NOT NULL "
			+ "  AND r.DATA_HORA_PRODUCAO >= :ini AND r.DATA_HORA_PRODUCAO < DATEADD(DAY, 1, CAST(:fim AS DATE)) "
			+ "  AND (:local = 0 OR r.ID_LOCAL_PRODUCAO = :local) "
			+ "GROUP BY YEAR(r.DATA_HORA_PRODUCAO), MONTH(r.DATA_HORA_PRODUCAO) "
			+ "ORDER BY YEAR(r.DATA_HORA_PRODUCAO), MONTH(r.DATA_HORA_PRODUCAO)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("ini", dataIni);
		query.setParameter("fim", dataFim);
		query.setParameter("local", idLocal == null ? 0 : idLocal);
		return query.getResultList();
	}

	// A mesma contagem, agrupada por referencia em vez de por mes. Substitui
	// "EVOLUCAO ENSAIOS POR REF" (Consulta_ID_EVOLUCAO ENSAIOS).
	//
	// C0=ID_REFERENCIA C1=REFERENCIA C2=DESIGNACAO C3=CLIENTE C4=TOTAL
	// C5..C8 espessuras, C9..C12 pautas, C13..C16 CaCl2, C17..C20 NSS
	public List<Object[]> getanaliseporreferencia(String dataIni, String dataFim, Integer idLocal) {
		String sql =
			"SELECT ref.ID_REFERENCIA AS C0, ref.REFERENCIA AS C1, ref.DESIGNACAO AS C2, "
			+ "ref.CLIENTE AS C3, COUNT(*) AS C4, "
			+ blocoContagens("esp.RESULTADO_TOTAL",       "C5",  "C6",  "C7",  "C8")
			+ blocoContagens("ens.RESULTADO_TOTAL",       "C9",  "C10", "C11", "C12")
			+ blocoContagens("cor.RESULTADO_TOTAL_CACL2", "C13", "C14", "C15", "C16")
			+ blocoContagens("cor.RESULTADO_TOTAL_NSS",   "C17", "C18", "C19", "C20")
			+ "0 AS FIM "
			+ "FROM QUA_CR_MOV_RELATORIO r "
			+ "INNER JOIN QUA_CR_DIC_REFERENCIA ref ON ref.ID_REFERENCIA = r.ID_REFERENCIA "
			+ aplicaBlocos()
			+ "WHERE r.ATIVO = 1 AND r.DATA_HORA_PRODUCAO IS NOT NULL "
			+ "  AND r.DATA_HORA_PRODUCAO >= :ini AND r.DATA_HORA_PRODUCAO < DATEADD(DAY, 1, CAST(:fim AS DATE)) "
			+ "  AND (:local = 0 OR r.ID_LOCAL_PRODUCAO = :local) "
			+ "GROUP BY ref.ID_REFERENCIA, ref.REFERENCIA, ref.DESIGNACAO, ref.CLIENTE "
			+ "ORDER BY ref.REFERENCIA";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("ini", dataIni);
		query.setParameter("fim", dataFim);
		query.setParameter("local", idLocal == null ? 0 : idLocal);
		return query.getResultList();
	}

	// As quatro contagens de um bloco. NULL conta como "sem resultado" e nao
	// se soma aos outros tres: e um estado real (o ensaio ainda nao foi
	// avaliado), nao um zero.
	private String blocoContagens(String col, String a, String b, String c, String d) {
		return "SUM(CASE WHEN " + col + " = 'OK' THEN 1 ELSE 0 END) AS " + a + ", "
		     + "SUM(CASE WHEN " + col + " = 'OK_COND' THEN 1 ELSE 0 END) AS " + b + ", "
		     + "SUM(CASE WHEN " + col + " = 'NOK' THEN 1 ELSE 0 END) AS " + c + ", "
		     + "SUM(CASE WHEN " + col + " IS NULL THEN 1 ELSE 0 END) AS " + d + ", ";
	}

	// Um relatorio pode ter mais de uma medicao do mesmo bloco (havia-as no
	// Access); a analise conta a mais recente, que e a que vale.
	private String aplicaBlocos() {
		return "OUTER APPLY ( SELECT TOP 1 x.RESULTADO_TOTAL FROM QUA_CR_MOV_ESPESSURA_CAB x "
		     + "              WHERE x.ID_RELATORIO = r.ID_RELATORIO AND x.ATIVO = 1 "
		     + "              ORDER BY x.ID_ESPESSURA_CAB DESC ) esp "
		     + "OUTER APPLY ( SELECT TOP 1 x.RESULTADO_TOTAL FROM QUA_CR_MOV_ENSAIO_CAB x "
		     + "              WHERE x.ID_RELATORIO = r.ID_RELATORIO AND x.ATIVO = 1 "
		     + "              ORDER BY x.ID_ENSAIO_CAB DESC ) ens "
		     + "OUTER APPLY ( SELECT TOP 1 x.RESULTADO_TOTAL_CACL2, x.RESULTADO_TOTAL_NSS "
		     + "              FROM QUA_CR_MOV_CORROSAO_CAB x "
		     + "              WHERE x.ID_RELATORIO = r.ID_RELATORIO AND x.ATIVO = 1 "
		     + "              ORDER BY x.ID_CORROSAO_CAB DESC ) cor ";
	}

	// Numeracao automatica. Conta sobre TODOS os registos do ano, incluindo os
	// anulados, para nunca reutilizar um numero que ja foi impresso num
	// boletim. Os 1086 relatorios migrados de 2026 mantem o numero do Access,
	// portanto a sequencia arranca a partir do maximo que ja existe.
	public Integer proximoNumero(Integer ano) {
		Query query = entityManager.createQuery(
			"Select coalesce(max(a.NUM_SEQ), 0) from QUA_CR_MOV_RELATORIO a where a.ANO = :ano");
		query.setParameter("ano", ano);
		Object r = query.getSingleResult();
		return (r == null ? 1 : ((Number) r).intValue() + 1);
	}
}
