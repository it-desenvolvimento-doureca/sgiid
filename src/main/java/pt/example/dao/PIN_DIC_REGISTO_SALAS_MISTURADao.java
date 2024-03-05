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
	
	public List<PIN_DIC_REGISTO_SALAS_MISTURA> getall2() {

		Query query = entityManager.createNativeQuery("Select a.ID,a.ID_RECEITA,a.ID_CABINE,a.DATA,a.HORA,a.TEMPERATURA,a.VISCOSIDADE,a.ID_REFERENCIA_COR,a.REFERENCIA_COR,a.VALOR_COR,a.REFERENCIA_COR_DESC,a.LOTE_COR,a.ID_REFERENCIA_DILUENTE,a.REFERENCIA_DILUENTE,a.VALOR_DILUENTE,a.REFERENCIA_DILUENTE_DESC,a.LOTE_DILUENTE,a.ID_REFERENCIA_CATALIZADOR,a.REFERENCIA_CATALIZADOR,a.VALOR_CATALIZADOR,a.REFERENCIA_CATALIZADOR_DESC,a.LOTE_CATALIZADOR,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.UTZ_ANULA,a.DATA_ANULA,a.ATIVO,a.OBSERVACOES,a.VERSAO,(select b.NOME_PROJETO from PIN_MOV_RECEITAS b where b.ID = a.ID_RECEITA AND b.VERSAO = a.VERSAO) as NOME_PROJETO, "
				+ "(select c.NOME_CABINE from PIN_DIC_CABINES c where c.ID = a.ID_CABINE) as NOME_CABINE "
				+ ",(select STRING_AGG(CONCAT(rr.REFERENCIA,' - ',rr.REFERENCIA_DESC),',') from PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS rr where rr.ID_REGISTO_SALAS_MISTURA = a.ID) as REFS_DILUENTE "
				+ "from PIN_DIC_REGISTO_SALAS_MISTURA a where a.ATIVO = 1");
		List<PIN_DIC_REGISTO_SALAS_MISTURA> data = query.getResultList();
		return data;

	}

}
