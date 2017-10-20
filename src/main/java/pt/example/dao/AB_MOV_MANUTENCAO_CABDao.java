package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_MOV_MANUTENCAO_CAB;

public class AB_MOV_MANUTENCAO_CABDao extends GenericDaoJpaImpl<AB_MOV_MANUTENCAO_CAB, Integer>
		implements GenericDao<AB_MOV_MANUTENCAO_CAB, Integer> {
	public AB_MOV_MANUTENCAO_CABDao() {
		super(AB_MOV_MANUTENCAO_CAB.class);
	}

	public List<AB_MOV_MANUTENCAO_CAB> getbyidmanutencao(Integer id) {
		Query query = entityManager.createQuery(
				"Select DISTINCT a ,CASE WHEN a.ID_ANALISE IS NULL THEN '' ELSE (select c.NOME_BANHO from AB_MOV_ANALISE b, AB_DIC_BANHO c where b.ID_BANHO = c.ID_BANHO and b.ID_ANALISE = a.ID_ANALISE)END as nome,"
						+ "CASE WHEN a.ID_TINA IS NULL THEN '' ELSE (select b.CAPACIDADE from AB_DIC_TINA b where b.ID_TINA = a.ID_TINA)END as capacidade, "
						+ "CASE WHEN d.ID195 IS NULL THEN '' ELSE (select e.ID_REG_PARAM_OPERA from AB_MOV_REG_PARAM_OPERACAO e where e.ID_MANUTENCAO_CAB = a.ID_MANUTENCAO_CAB and e.INATIVO != 1 )END as operacao,  "
						+ "CASE WHEN a.UTZ_EXECUCAO IS NULL THEN '' ELSE (select g.NOME_UTILIZADOR from GER_UTILIZADORES g where g.ID_UTILIZADOR = a.UTZ_EXECUCAO)END as nomeutz, "
						+ "CASE WHEN a.UTZ_PREPARACAO IS NULL THEN '' ELSE (select f.NOME_UTILIZADOR from GER_UTILIZADORES f where f.ID_UTILIZADOR = a.UTZ_PREPARACAO)END as nomeutz2, "
						+ "CASE WHEN a.ID_TINA IS NULL THEN '' ELSE (select b.COD_TINA from AB_DIC_TINA b where b.ID_TINA = a.ID_TINA)END as cod_tina, "
						+ "CASE WHEN d.ID195 IS NULL THEN '' ELSE (select m.DATA_VALIDA from AB_MOV_REG_PARAM_OPERACAO m where m.ID_MANUTENCAO_CAB = a.ID_MANUTENCAO_CAB and m.INATIVO != 1 )END as dataoperacao "
						+ "from AB_MOV_MANUTENCAO_CAB a,AB_DIC_TIPO_OPERACAO d  where  (( a.ID_TIPO_OPERACAO is null) or (d.ID_TIPO_OPERACAO = a.ID_TIPO_OPERACAO)) and a.ID_MANUTENCAO = :id and a.INATIVO != 1 order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<AB_MOV_MANUTENCAO_CAB> data = query.getResultList();
		return data;
	}

	public List<AB_MOV_MANUTENCAO_CAB> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select DISTINCT a ,CASE WHEN a.ID_ANALISE IS NULL THEN '' ELSE (select c.NOME_BANHO from AB_MOV_ANALISE b, AB_DIC_BANHO c where b.ID_BANHO = c.ID_BANHO and b.ID_ANALISE = a.ID_ANALISE)END as nome, "
						+ "CASE WHEN a.ID_TINA IS NULL THEN '' ELSE (select b.CAPACIDADE from AB_DIC_TINA b where b.ID_TINA = a.ID_TINA)END as capacidade, "
						+ "CASE WHEN d.ID195 IS NULL THEN '' ELSE (select e.ID_REG_PARAM_OPERA from AB_MOV_REG_PARAM_OPERACAO e where e.ID_MANUTENCAO_CAB = a.ID_MANUTENCAO_CAB and e.INATIVO != 1)END as operacao, "
						+ "CASE WHEN a.ID_BANHO IS NULL THEN '' ELSE (select f.NOME_BANHO from AB_DIC_BANHO f where f.ID_BANHO = a.ID_BANHO)END as banho, "
						+ "CASE WHEN a.ID_TINA IS NULL THEN '' ELSE (select b.COD_TINA from AB_DIC_TINA b where b.ID_TINA = a.ID_TINA)END as cod, "
						+ "CASE WHEN a.UTZ_EXECUCAO IS NULL THEN '' ELSE (select g.NOME_UTILIZADOR from GER_UTILIZADORES g where g.ID_UTILIZADOR = a.UTZ_EXECUCAO)END as nomeutz, h, "
						+ "CASE WHEN a.UTZ_PREPARACAO IS NULL THEN '' ELSE (select f.NOME_UTILIZADOR from GER_UTILIZADORES f where f.ID_UTILIZADOR = a.UTZ_PREPARACAO)END as nomeutz2, "
						+ "CASE WHEN d.ID195 IS NULL THEN '' ELSE (select m.DATA_VALIDA from AB_MOV_REG_PARAM_OPERACAO m where m.ID_MANUTENCAO_CAB = a.ID_MANUTENCAO_CAB and m.INATIVO != 1 )END as dataoperacao  "
						+ "from AB_MOV_MANUTENCAO_CAB a,AB_DIC_TIPO_OPERACAO d,AB_DIC_LINHA h, AB_MOV_MANUTENCAO i where i.ID_MANUTENCAO = a.ID_MANUTENCAO and i.ID_LINHA=h.ID_LINHA and "
						+ "(( a.ID_TIPO_OPERACAO is null) or (d.ID_TIPO_OPERACAO = a.ID_TIPO_OPERACAO)) and  a.ID_MANUTENCAO_CAB = :id and a.INATIVO != 1 ");
		query.setParameter("id", id);
		List<AB_MOV_MANUTENCAO_CAB> data = query.getResultList();
		return data;

	}

}
