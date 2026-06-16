package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_RECEITAS;

public class PIN_MOV_RECEITASDao extends GenericDaoJpaImpl<PIN_MOV_RECEITAS, Integer>
		implements GenericDao<PIN_MOV_RECEITAS, Integer> {
	public PIN_MOV_RECEITASDao() {
		super(PIN_MOV_RECEITAS.class);
	}

	public List<PIN_MOV_RECEITAS> getbyid(Integer id, Integer linha, Integer versao) {

		Query query = entityManager.createQuery(
				"Select a,b,(select max(c.VERSAO) from PIN_MOV_RECEITAS c where c.ID = a.ID ) as MAX_VERSAO "
						+ ",(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) as NOME_UTILIZADOR "
						+ "from PIN_MOV_RECEITAS a, AB_DIC_LINHA b where a.ID = :id and a.LINHA = b.ID_LINHA and a.VERSAO =:versao  and ((not :linha != 0) or (a.LINHA = :linha))");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		query.setParameter("versao", versao);
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_RECEITAS> getall() {
		Query query = entityManager.createQuery(
				"Select a,b from PIN_MOV_RECEITAS a,AB_DIC_LINHA b where  a.LINHA = b.ID_LINHA and a.INATIVO != 1 and a.VERSAO_ATIVA = 1 ");
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}
	
	public List<PIN_MOV_RECEITAS> getall2() {
		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_RECEITAS a where a.VERSAO_ATIVA = 1 ");
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_RECEITAS> getallFiltro(String referencia) {
		String query_ = "";

		if (!referencia.equals("_empty_")) {
			query_ = " inner join  (select c.ID_RECEITA,c.VERSAO from PIN_MOV_RECEITAS_REFERENCIAS c where c.REFERENCIA_PINTURA = '"+referencia+"') c on a.ID = c.ID_RECEITA and a.VERSAO = c.VERSAO ";
		}

		Query query = entityManager
				.createNativeQuery(" Select distinct ID,a.VERSAO,NOME_PROJETO,nome_LINHA,b.COR,id_LINHA,RECEITA_INATIVA ,a.ACABAMENTOS,a.ASPETO,a.COD_ASPETO,a.CLASSIFICACAO "
						+ " from PIN_MOV_RECEITAS a " + query_
						+ " left join AB_DIC_LINHA b on a.LINHA = b.ID_LINHA and a.INATIVO != 1"
						+ " where a.VERSAO_ATIVA = 1 and a.INATIVO != 1  ");
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_RECEITAS> chechExistRef(String referencia, Integer id) {
		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_RECEITAS a where  a.INATIVO != 1 and a.VERSAO_ATIVA = 1 and RECEITA_INATIVA != 1 and  a.REFERENCIA_PINTURA = :referencia AND a.ID != :id ");
		query.setParameter("referencia", referencia);
		query.setParameter("id", id);
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_RECEITAS> chechExistRefs(String referencias, Integer id) {
		Query query = entityManager.createNativeQuery(
				" Select b.* from PIN_MOV_RECEITAS_REFERENCIAS a "
				+ " inner join PIN_MOV_RECEITAS b on a.ID_RECEITA = b.ID and a.VERSAO = b.VERSAO "
				+ " where  b.INATIVO != 1 and b.VERSAO_ATIVA = 1 and RECEITA_INATIVA != 1  "
				+ " and  a.REFERENCIA_PINTURA in (select value from string_split(:referencias,',')) "
				+ " AND b.ID != :id ",PIN_MOV_RECEITAS.class);
		query.setParameter("id", id);
		query.setParameter("referencias", referencias);
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}
	
	public List<PIN_MOV_RECEITAS> getversoes(Integer id, Integer versao) {

		Query query = entityManager
				.createNativeQuery("Select a.ID,a.VERSAO from PIN_MOV_RECEITAS a where a.ID = :id  order by a.VERSAO ");
		query.setParameter("id", id);
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}

	@SuppressWarnings("rawtypes")
	public List getReceitasPlano() {
		Query query = entityManager.createNativeQuery(
			"SELECT rr.REFERENCIA_PINTURA, rr.NOME_REFERENCIA_PINTURA, r.JOB, " +
			"f1.CODIGO AS PRIMARIO, f2.CODIGO AS BASE, f3.CODIGO AS VERNIZ, " +
			"CASE WHEN r.CICLO_PRIMARIO >= r.CICLO_BASE AND r.CICLO_PRIMARIO >= r.CICLO_VERNIZ THEN r.CICLO_PRIMARIO " +
			"WHEN r.CICLO_BASE >= r.CICLO_PRIMARIO AND r.CICLO_BASE >= r.CICLO_VERNIZ THEN r.CICLO_BASE " +
			"ELSE r.CICLO_VERNIZ END AS TEMPO_CICLO, z.COR " +
			"FROM PIN_MOV_RECEITAS r " +
			"INNER JOIN (SELECT DISTINCT ID_RECEITA, VERSAO, REFERENCIA_PINTURA, NOME_REFERENCIA_PINTURA FROM PIN_MOV_RECEITAS_REFERENCIAS) rr ON r.ID = rr.ID_RECEITA AND r.VERSAO = rr.VERSAO " +
			"LEFT JOIN PIN_DIC_CORES_ACABAMENTOS f1 ON f1.ID = r.PRIMARIO AND f1.ATIVO = 1 " +
			"LEFT JOIN PIN_DIC_CORES_ACABAMENTOS f2 ON f2.ID = r.BASE AND f2.ATIVO = 1 " +
			"LEFT JOIN PIN_DIC_CORES_ACABAMENTOS f3 ON f3.ID = r.VERNIZ AND f3.ATIVO = 1 " +
			"LEFT JOIN SILVER_BI.dbo.SPAZPL z ON z.zpavallib = r.ASPETO " +
			"WHERE r.VERSAO_ATIVA = 1 AND r.INATIVO = 0 and RECEITA_INATIVA = 0"
		);
		return query.getResultList();
	}

}
