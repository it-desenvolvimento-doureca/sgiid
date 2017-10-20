package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_MOV_REG_PARAM_OPERACAO;

public class AB_MOV_REG_PARAM_OPERACAODao extends GenericDaoJpaImpl<AB_MOV_REG_PARAM_OPERACAO,Integer> implements GenericDao<AB_MOV_REG_PARAM_OPERACAO,Integer> {
	public AB_MOV_REG_PARAM_OPERACAODao() {
		super(AB_MOV_REG_PARAM_OPERACAO.class);
	}

	
	public List<AB_MOV_REG_PARAM_OPERACAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a,"
				+ "CASE WHEN a.UTZ_CRIA IS NULL THEN '' ELSE (select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA)END as nome ,"
				+ "CASE WHEN a.UTZ_VALIDA IS NULL THEN '' ELSE (select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_VALIDA)END as nome2 "
				+ " from AB_MOV_REG_PARAM_OPERACAO a where a.ID_REG_PARAM_OPERA = :id ");
		query.setParameter("id", id);
		List<AB_MOV_REG_PARAM_OPERACAO> data = query.getResultList();
		return data;

	}
	
	public List<AB_MOV_REG_PARAM_OPERACAO> getall() {
		Query query = entityManager.createQuery("select a, "
				+ "CASE WHEN a.DATA_VALIDA IS NULL THEN 1 ELSE '2'END as B "
				+ " from AB_MOV_REG_PARAM_OPERACAO a where a.INATIVO != 1 order by B,a.DATA_CRIA asc");
		List<AB_MOV_REG_PARAM_OPERACAO> data = query.getResultList();
		return data;

	}
	

}
