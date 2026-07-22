package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_REGISTO_SALAS_MISTURA;

public class PIN_DIC_REGISTO_SALAS_MISTURADao extends GenericDaoJpaImpl<PIN_DIC_REGISTO_SALAS_MISTURA, Integer>
		implements GenericDao<PIN_DIC_REGISTO_SALAS_MISTURA, Integer> {
	public PIN_DIC_REGISTO_SALAS_MISTURADao() {
		super(PIN_DIC_REGISTO_SALAS_MISTURA.class);
	}

	public List<PIN_DIC_REGISTO_SALAS_MISTURA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_REGISTO_SALAS_MISTURA a where a.ID = :id and a.ATIVO = 1 ");
		query.setParameter("id", id); 
		List<PIN_DIC_REGISTO_SALAS_MISTURA> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_REGISTO_SALAS_MISTURA> getall() {

		Query query = entityManager.createQuery("Select a,(select b.NOME_PROJETO from PIN_MOV_RECEITAS b where b.ID = a.ID_RECEITA AND b.VERSAO = a.VERSAO) as NOME_PROJETO, "
				+ "(select c.NOME_CABINE from PIN_DIC_CABINES c where c.ID = a.ID_CABINE) as NOME_CABINE from PIN_DIC_REGISTO_SALAS_MISTURA a where a.ATIVO = 1");
		List<PIN_DIC_REGISTO_SALAS_MISTURA> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_REGISTO_SALAS_MISTURA> getanalise(Integer id_referencia, String ini, String fim) {

		Query query = entityManager.createNativeQuery("select a.ID, "
				+ "(select b.NOME_PROJETO from PIN_MOV_RECEITAS b where b.ID = a.ID_RECEITA AND b.VERSAO = a.VERSAO) as NOME_PROJETO, "
				+ "(select c.NOME_CABINE from PIN_DIC_CABINES c where c.ID = a.ID_CABINE) as NOME_CABINE, "
				+ "a.DATA, a.HORA, a.TEMPERATURA, a.VISCOSIDADE, "
				+ "CONCAT(a.REFERENCIA_COR,' - ',a.REFERENCIA_COR_DESC) as REF_COR, "
				+ "CONCAT_WS(', ', CASE WHEN a.ID_REFERENCIA_DILUENTE IS NOT NULL THEN CONCAT(a.REFERENCIA_DILUENTE,' - ',a.REFERENCIA_DILUENTE_DESC) END, (select STRING_AGG(CONCAT(rr.REFERENCIA,' - ',rr.REFERENCIA_DESC),', ') from PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS rr where rr.ID_REGISTO_SALAS_MISTURA = a.ID)) as REF_DILUENTE, "
				+ "p.LIMITE_INF_VISCOSIDADE, p.LIMITE_SUP_VISCOSIDADE, "
				+ "a.ID_REFERENCIA_COR, a.ID_CABINE, a.ID_RECEITA, "
				+ "(select b.TEMPERATURA_MIN from PIN_MOV_RECEITAS b where b.ID = a.ID_RECEITA AND b.VERSAO = a.VERSAO) as TEMPERATURA_MIN, "
				+ "(select b.TEMPERATURA_MAX from PIN_MOV_RECEITAS b where b.ID = a.ID_RECEITA AND b.VERSAO = a.VERSAO) as TEMPERATURA_MAX "
				+ "from PIN_DIC_REGISTO_SALAS_MISTURA a "
				+ "left join PIN_DIC_PRODUTOS p on p.ID = a.ID_REFERENCIA_COR "
				+ "where a.ATIVO = 1 "
				+ "and (:id_referencia = 0 or a.ID_REFERENCIA_COR = :id_referencia) "
				+ "and a.DATA >= CAST(:ini as date) and a.DATA <= CAST(:fim as date) "
				+ "order by a.ID_REFERENCIA_COR, a.DATA, a.HORA");
		query.setParameter("id_referencia", id_referencia);
		query.setParameter("ini", ini);
		query.setParameter("fim", fim);
		List<PIN_DIC_REGISTO_SALAS_MISTURA> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_REGISTO_SALAS_MISTURA> getall2() {

		Query query = entityManager.createNativeQuery("Select a.ID,a.ID_RECEITA,a.ID_CABINE,a.DATA,a.HORA,a.TEMPERATURA,a.VISCOSIDADE,a.ID_REFERENCIA_COR,a.REFERENCIA_COR,a.VALOR_COR,a.REFERENCIA_COR_DESC,a.LOTE_COR,a.ID_REFERENCIA_DILUENTE,a.REFERENCIA_DILUENTE,a.VALOR_DILUENTE,a.REFERENCIA_DILUENTE_DESC,a.LOTE_DILUENTE,a.ID_REFERENCIA_CATALIZADOR,a.REFERENCIA_CATALIZADOR,a.VALOR_CATALIZADOR,a.REFERENCIA_CATALIZADOR_DESC,a.LOTE_CATALIZADOR,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.UTZ_ANULA,a.DATA_ANULA,a.ATIVO,a.OBSERVACOES,a.VERSAO,(select b.NOME_PROJETO from PIN_MOV_RECEITAS b where b.ID = a.ID_RECEITA AND b.VERSAO = a.VERSAO) as NOME_PROJETO, "
				+ "(select c.NOME_CABINE from PIN_DIC_CABINES c where c.ID = a.ID_CABINE) as NOME_CABINE "
				+ ",(select STRING_AGG(CONCAT(rr.REFERENCIA,' - ',rr.REFERENCIA_DESC),',') from PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS rr where rr.ID_REGISTO_SALAS_MISTURA = a.ID) as REFS_DILUENTE "
				+ "from PIN_DIC_REGISTO_SALAS_MISTURA a where a.ATIVO = 1");
		List<PIN_DIC_REGISTO_SALAS_MISTURA> data = query.getResultList();
		return data;

	}

}
