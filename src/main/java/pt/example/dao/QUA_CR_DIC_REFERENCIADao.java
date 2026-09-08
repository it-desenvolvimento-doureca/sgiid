package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_CR_DIC_REFERENCIA;

public class QUA_CR_DIC_REFERENCIADao extends GenericDaoJpaImpl<QUA_CR_DIC_REFERENCIA, Integer>
		implements GenericDao<QUA_CR_DIC_REFERENCIA, Integer> {
	public QUA_CR_DIC_REFERENCIADao() {
		super(QUA_CR_DIC_REFERENCIA.class);
	}

	public List<QUA_CR_DIC_REFERENCIA> getall() {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_REFERENCIA a where a.ATIVO = true order by a.REFERENCIA");
		return query.getResultList();
	}

	public List<QUA_CR_DIC_REFERENCIA> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_REFERENCIA a where a.ID_REFERENCIA = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	// Dropdown do cabecalho do ensaio: so o que e preciso para escolher a
	// referencia e para pre-encher a tab de espessuras (NUM_AMOSTRAS propoe
	// quantas linhas de amostra criar; as espessuras exigidas e as pautas
	// aparecem em read-only).
	public List<Object[]> getdropdown() {
		Query query = entityManager.createQuery(
			"Select a.ID_REFERENCIA, a.REFERENCIA, a.DESIGNACAO, a.CLIENTE, a.NUM_AMOSTRAS "
			+ "from QUA_CR_DIC_REFERENCIA a where a.ATIVO = true order by a.REFERENCIA");
		return query.getResultList();
	}

	// Listagem do ecra. TEM_FOTO evita ter de pedir os anexos referencia a
	// referencia so para mostrar um icone na lista.
	//
	// C0=ID  C1=REFERENCIA  C2=REF_PECA_PLASTICA  C3=DESIGNACAO  C4=CLIENTE
	// C5=MATERIAL  C6=TIPO_PECA  C7=TIPO_SUPERFICIE  C8=ASPECTO
	// C9=ESPESSURA_CU  C10=ESPESSURA_NI  C11=ESPESSURA_CR
	// C12=PAUTA_ENSAIO  C13=NUM_AMOSTRAS  C14=PROREF_SILVER
	// C15=N_TESTES  C16=TEM_FOTO
	public List<Object[]> getlista() {
		String sql =
			"SELECT a.ID_REFERENCIA AS C0, a.REFERENCIA AS C1, a.REF_PECA_PLASTICA AS C2, "
			+ "       a.DESIGNACAO AS C3, a.CLIENTE AS C4, a.MATERIAL AS C5, "
			+ "       tp.DESCRICAO AS C6, ts.DESCRICAO AS C7, asp.DESCRICAO AS C8, "
			+ "       a.ESPESSURA_CU AS C9, a.ESPESSURA_NI AS C10, a.ESPESSURA_CR AS C11, "
			+ "       a.PAUTA_ENSAIO AS C12, a.NUM_AMOSTRAS AS C13, a.PROREF_SILVER AS C14, "
			+ "       ( SELECT COUNT(*) FROM QUA_CR_DIC_REF_TESTE t "
			+ "         WHERE t.ID_REFERENCIA = a.ID_REFERENCIA AND t.ATIVO = 1 ) AS C15, "
			+ "       CASE WHEN EXISTS ( SELECT 1 FROM QUA_CR_DIC_REFERENCIA_FICHEIROS f "
			+ "                          WHERE f.ID_REFERENCIA = a.ID_REFERENCIA "
			+ "                            AND f.CATEGORIA = 'FOTOGRAFIA' AND f.ATIVO = 1 ) "
			+ "            THEN 1 ELSE 0 END AS C16 "
			+ "FROM QUA_CR_DIC_REFERENCIA a "
			+ "LEFT JOIN QUA_CR_DIC_TIPO_PECA tp       ON tp.ID_TIPO_PECA = a.ID_TIPO_PECA "
			+ "LEFT JOIN QUA_CR_DIC_TIPO_SUPERFICIE ts ON ts.ID_TIPO_SUPERFICIE = a.ID_TIPO_SUPERFICIE "
			+ "LEFT JOIN QUA_CR_DIC_ASPECTO asp        ON asp.ID_ASPECTO = a.ID_ASPECTO "
			+ "WHERE a.ATIVO = 1 "
			+ "ORDER BY a.REFERENCIA";
		return entityManager.createNativeQuery(sql).getResultList();
	}

	// Usada na migracao e no ecra, para nao criar referencias duplicadas.
	// No Access havia 13 referencias repetidas e 2 vazias.
	public List<QUA_CR_DIC_REFERENCIA> getbyReferencia(String referencia) {
		Query query = entityManager.createQuery(
			"Select a from QUA_CR_DIC_REFERENCIA a where a.REFERENCIA = :ref order by a.ID_REFERENCIA");
		query.setParameter("ref", referencia);
		return query.getResultList();
	}
}
