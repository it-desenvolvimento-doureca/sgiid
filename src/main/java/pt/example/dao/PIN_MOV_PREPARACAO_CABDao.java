package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_PREPARACAO_CAB;

public class PIN_MOV_PREPARACAO_CABDao extends GenericDaoJpaImpl<PIN_MOV_PREPARACAO_CAB, Integer>
		implements GenericDao<PIN_MOV_PREPARACAO_CAB, Integer> {
	public PIN_MOV_PREPARACAO_CABDao() {
		super(PIN_MOV_PREPARACAO_CAB.class);
	}

	public List<PIN_MOV_PREPARACAO_CAB> getbyidPREPARACAO(Integer id) {
		Query query = entityManager.createQuery(
				"Select DISTINCT a ,CASE WHEN a.ID_ANALISE IS NULL THEN '' ELSE (select c.NOME_BANHO from AB_MOV_ANALISE b, AB_DIC_BANHO c where b.ID_BANHO = c.ID_BANHO and b.ID_ANALISE = a.ID_ANALISE)END as nome,"
						+ "CASE WHEN a.ID_POTE IS NULL THEN '' ELSE (select b.CAPACIDADE from PIN_DIC_POTES b where b.ID = a.ID_POTE)END as capacidade, "
						+ "CASE WHEN d.ID195 IS NULL THEN '' ELSE (select e.ID_REG_PARAM_OPERA from AB_MOV_REG_PARAM_OPERACAO e where e.ID_PREPARACAO_CAB = a.ID_PREPARACAO_CAB and e.INATIVO != 1 )END as operacao,  "
						+ "CASE WHEN a.UTZ_EXECUCAO IS NULL THEN '' ELSE (select g.NOME_UTILIZADOR from GER_UTILIZADORES g where g.ID_UTILIZADOR = a.UTZ_EXECUCAO)END as nomeutz, "
						+ "CASE WHEN a.UTZ_PREPARACAO IS NULL THEN '' ELSE (select f.NOME_UTILIZADOR from GER_UTILIZADORES f where f.ID_UTILIZADOR = a.UTZ_PREPARACAO)END as nomeutz2, "
						+ "CASE WHEN a.ID_POTE IS NULL THEN '' ELSE (select b.COD_TINA from PIN_DIC_POTES b where b.ID = a.ID_POTE)END as cod_tina, "
						+ "CASE WHEN d.ID195 IS NULL THEN '' ELSE (select m.DATA_VALIDA from AB_MOV_REG_PARAM_OPERACAO m where m.ID_PREPARACAO_CAB = a.ID_PREPARACAO_CAB and m.INATIVO != 1 )END as dataoperacao "
						+ ", CASE WHEN a.DATA_PREPARACAO IS NULL THEN '1' WHEN a.DATA_EXECUCAO is null THEN '2' WHEN a.DATA_EXECUCAO is not null THEN '4' WHEN a.DATA_PREPARACAO is not null THEN '3' END AS B "
						+ "from PIN_MOV_PREPARACAO_CAB a where  a.ID_PREPARACAO = :id and a.INATIVO != 1 order by B,DATA_PREVISTA,HORA_PREVISTA,DATA_CRIA");
		query.setParameter("id", id);
		List<PIN_MOV_PREPARACAO_CAB> data = query.getResultList();
		return data;
	}

	public List<PIN_MOV_PREPARACAO_CAB> getbyidpote(Integer idpote, Integer inicio, Integer fim, Integer id_man,String classif) {
		Query query = entityManager.createNativeQuery(
				"SELECT a.ID_PREPARACAO_CAB,DATA_PLANEAMENTO,HORA_PLANEAMENTO, ( SELECT COUNT(*) AS totalPayments FROM PIN_MOV_PREPARACAO_CAB a,PIN_MOV_PREPARACAO b where a.ID_PREPARACAO=b.ID_PREPARACAO and b.CLASSIF= :classif and a.INATIVO != 1 and ID_POTE = :idpote and ID_PREPARACAO_CAB not in (:id_man) ) "
						+ "FROM ( SELECT a.*, ROW_NUMBER() OVER (ORDER BY ID_PREPARACAO_CAB desc) as row FROM PIN_MOV_PREPARACAO_CAB a,PIN_MOV_PREPARACAO b where a.ID_PREPARACAO=b.ID_PREPARACAO and b.CLASSIF= :classif and a.INATIVO != 1 and ID_POTE = :idpote and ID_PREPARACAO_CAB not in (:id_man) ) a "
						+ "inner join PIN_MOV_PREPARACAO b on a.ID_PREPARACAO = b.ID_PREPARACAO "
						+ "WHERE row > :inicio and row <= :fim and b.CLASSIF = :classif order by b.DATA_PLANEAMENTO desc");
		query.setParameter("idpote", idpote);
		query.setParameter("inicio", inicio);
		query.setParameter("fim", fim);
		query.setParameter("id_man", id_man);
		query.setParameter("classif", classif);
		List<PIN_MOV_PREPARACAO_CAB> data = query.getResultList();
		return data;

	}
	
	
	public List<PIN_MOV_PREPARACAO_CAB> getbyidpoteall(Integer idpote) {
		Query query = entityManager.createQuery(
				"SELECT a FROM PIN_MOV_PREPARACAO_CAB a where a.ID_POTE = :idpote");
		query.setParameter("idpote", idpote);
		List<PIN_MOV_PREPARACAO_CAB> data = query.getResultList();
		return data;

	}
	public List<PIN_MOV_PREPARACAO_CAB> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select DISTINCT a ,null as nome,/*CASE WHEN a.ID_ANALISE IS NULL THEN '' ELSE (select c.NOME_BANHO from AB_MOV_ANALISE b, AB_DIC_BANHO c where b.ID_BANHO = c.ID_BANHO and b.ID_ANALISE = a.ID_ANALISE)END as nome,*/ "
						+ "CASE WHEN a.ID_POTE IS NULL THEN '' ELSE (select b.CAPACIDADE from PIN_DIC_POTES b where b.ID = a.ID_POTE)END as capacidade, "
						+ "null as operacao,-- CASE WHEN d.ID195 IS NULL THEN '' ELSE (select e.ID_REG_PARAM_OPERA from AB_MOV_REG_PARAM_OPERACAO e where e.ID_PREPARACAO_CAB = a.ID_PREPARACAO_CAB and e.INATIVO != 1)END as operacao, "
						+ "CASE WHEN a.ID_POTE IS NULL THEN '' ELSE (select fc.NOME_CABINE from PIN_DIC_POTES f,PIN_DIC_CABINES fc where f.ID = a.ID_POTE and fc.ID = f.ID_CABINE)END as cabine, "
						+ "CASE WHEN a.ID_POTE IS NULL THEN '' ELSE (select b.NOME from PIN_DIC_POTES b where b.ID = a.ID_POTE)END as cod, "
						+ "CASE WHEN a.UTZ_EXECUCAO IS NULL THEN '' ELSE (select g.NOME_UTILIZADOR from GER_UTILIZADORES g where g.ID_UTILIZADOR = a.UTZ_EXECUCAO)END as nomeutz, h, "
						+ "CASE WHEN a.UTZ_PREPARACAO IS NULL THEN '' ELSE (select f.NOME_UTILIZADOR from GER_UTILIZADORES f where f.ID_UTILIZADOR = a.UTZ_PREPARACAO)END as nomeutz2, "
						+ "null as dataoperacao,--CASE WHEN d.ID195 IS NULL THEN '' ELSE (select m.DATA_VALIDA from AB_MOV_REG_PARAM_OPERACAO m where m.ID_PREPARACAO_CAB = a.ID_PREPARACAO_CAB and m.INATIVO != 1 )END as dataoperacao, "
						+ "i.DATA_PLANEAMENTO,i.HORA_PLANEAMENTO "
						+ "from PIN_MOV_PREPARACAO_CAB a,AB_DIC_LINHA h, PIN_MOV_PREPARACAO i where i.ID_PREPARACAO = a.ID_PREPARACAO and i.ID_LINHA=h.ID_LINHA and "
						+ " a.ID_PREPARACAO_CAB = :id and a.INATIVO != 1 ");
		query.setParameter("id", id);
		List<PIN_MOV_PREPARACAO_CAB> data = query.getResultList();
		return data;

	}

}
