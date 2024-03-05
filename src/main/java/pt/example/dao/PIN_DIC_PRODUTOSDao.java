package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_PRODUTOS;

public class PIN_DIC_PRODUTOSDao extends GenericDaoJpaImpl<PIN_DIC_PRODUTOS, Integer>
		implements GenericDao<PIN_DIC_PRODUTOS, Integer> {
	public PIN_DIC_PRODUTOSDao() {
		super(PIN_DIC_PRODUTOS.class);
	}

	public List<PIN_DIC_PRODUTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_PRODUTOS a where a.ID = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_PRODUTOS> getAll() {

		Query query = entityManager.createQuery(
				"Select a,(select b.NOME from PIN_DIC_TIPO_ACABAMENTO b where b.ID = a.ID_TIPO_ACABAMENTO ) from PIN_DIC_PRODUTOS a where a.INATIVO != 1 ");

		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_PRODUTOS> getAll2() {

		Query query = entityManager.createQuery(
				"Select a,(select b.NOME from PIN_DIC_TIPO_ACABAMENTO b where b.ID = a.ID_TIPO_ACABAMENTO ) from PIN_DIC_PRODUTOS a");

		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_PRODUTOS> getbyTipoAcabamento(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_PRODUTOS a where a.ID_TIPO_ACABAMENTO = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_PRODUTOS> getbyReceita(Integer id,Integer versao) {

		Query query = entityManager.createNativeQuery("select * from ( "
				+ "select cod_REF ,cisterna,nome_REF_SUBSTITUTA,cod_REF_SUBSTITUTA,b.ID,ID_POTE,nome_REF,id_UNIDADE,factor_CONVERSAO,0 relacionado,0 PERC_DILUICAO,b.ID id_pai,a.UNIDADE,a.VALVULA_POTE,a.ID id_linha,a.ID_POTE_CATALISADOR,a.VALVULA_CATALISADOR,CASE WHEN  a.REFERENCIA_A = a.REFERENCIA_B THEN 1 ELSE 0 END ref_catalizador"
				+ " ,( case when x.NOME like '%primário%' then 1 when x.NOME like '%base%' then 2 when x.NOME like '%verniz%' then 3 else 4 end  ) order_ "
				+ "from PIN_MOV_RECEITAS_LINHAS a INNER JOIN PIN_MOV_RECEITAS a1 on a.ID_RECEITA = a1.ID  and a.VERSAO = a1.VERSAO " + "INNER JOIN PIN_DIC_PRODUTOS b on a.ID_REFERENCIA_A = b.ID "
				+ "LEFT JOIN PIN_DIC_TIPO_ACABAMENTO x on x.ID = a.ID_TIPO_ACABAMENTO WHERE a.ID_RECEITA = :id /*AND b.INATIVO = 0*/   and a1.VERSAO = :versao " + "union "
				+ "select d.cod_REF ,d.cisterna,d.nome_REF_SUBSTITUTA,d.cod_REF_SUBSTITUTA,d.ID,ID_POTE,d.nome_REF,d.id_UNIDADE,d.factor_CONVERSAO,1 relacionado,c.PERC_DILUICAO,b.ID id_pai,a.UNIDADE,a.VALVULA_POTE,a.ID id_linha,a.ID_POTE_CATALISADOR,a.VALVULA_CATALISADOR,CASE WHEN  a.REFERENCIA_A = a.REFERENCIA_B THEN 1 ELSE 0 END ref_catalizador  "
				+ " ,( case when x.NOME like '%primário%' then 1 when x.NOME like '%base%' then 2 when x.NOME like '%verniz%' then 3 else 4 end  ) order_ "
				+ "from PIN_MOV_RECEITAS_LINHAS a  INNER JOIN PIN_MOV_RECEITAS a1 on a.ID_RECEITA = a1.ID  and a.VERSAO = a1.VERSAO " + "INNER JOIN PIN_DIC_PRODUTOS b on a.ID_REFERENCIA_A = b.ID "
				+ "INNER JOIN PIN_DIC_PRODUTOS_RELACIONADOS c on c.ID_PRODUTO = b.ID "
				+ "INNER JOIN PIN_DIC_PRODUTOS d on c.ID_REF = d.ID LEFT JOIN PIN_DIC_TIPO_ACABAMENTO x on x.ID = a.ID_TIPO_ACABAMENTO "
				+ "WHERE a.ID_RECEITA = :id AND /*b.INATIVO = 0  AND*/ d.INATIVO = 0  and a1.VERSAO = :versao"
				+ ") tab order by order_,id_linha,id_pai,relacionado ");
		query.setParameter("id", id).setParameter("versao", versao);
		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_PRODUTOS> getbyIdProduto(Integer id) {

		Query query = entityManager.createNativeQuery("select * from ( "
				+ "select cod_REF ,cisterna,nome_REF_SUBSTITUTA,cod_REF_SUBSTITUTA,b.ID,null ID_POTE,nome_REF,id_UNIDADE,factor_CONVERSAO,0 relacionado,0 PERC_DILUICAO,b.ID id_pai "
				+ ",null UNIDADE,null VALVULA_POTE,null id_linha,null ID_POTE_CATALISADOR,null VALVULA_CATALISADOR,0 ref_catalizador "
				+ "from PIN_DIC_PRODUTOS b "
				+ "WHERE b.INATIVO = 0 AND b.ID = :id "
				+ "union "
				+ "select d.cod_REF ,d.cisterna,d.nome_REF_SUBSTITUTA,d.cod_REF_SUBSTITUTA,d.ID,null ID_POTE,d.nome_REF,d.id_UNIDADE,d.factor_CONVERSAO,1 relacionado "
				+ ",c.PERC_DILUICAO,b.ID id_pai,null UNIDADE,null VALVULA_POTE,null id_linha,null ID_POTE_CATALISADOR,null VALVULA_CATALISADOR,0 ref_catalizador  "
				+ "from  PIN_DIC_PRODUTOS b "
				+ "INNER JOIN PIN_DIC_PRODUTOS_RELACIONADOS c on c.ID_PRODUTO = b.ID "
				+ "INNER JOIN PIN_DIC_PRODUTOS d on c.ID_REF = d.ID "
				+ "WHERE b.INATIVO = 0  AND d.INATIVO = 0  AND b.ID = :id "
				+ ") tab order by id_linha,id_pai,relacionado");
		query.setParameter("id", id);
		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}
}
