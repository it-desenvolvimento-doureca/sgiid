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

		Query query = entityManager.createQuery("Select a,(select b.NOME from PIN_DIC_TIPO_ACABAMENTO b where b.ID = a.ID_TIPO_ACABAMENTO ) from PIN_DIC_PRODUTOS a where a.INATIVO != 1 ");

		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}

	
	public List<PIN_DIC_PRODUTOS> getbyTipoAcabamento(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_PRODUTOS a where a.ID_TIPO_ACABAMENTO = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_PRODUTOS> getbyReceita(Integer id) {

		Query query = entityManager.createNativeQuery("select * from ( "
				+ "select cod_REF ,cisterna,nome_REF_SUBSTITUTA,cod_REF_SUBSTITUTA,b.ID,ID_POTE,nome_REF,id_UNIDADE,factor_CONVERSAO,0 relacionado,0 PERC_DILUICAO,b.ID id_pai,a.UNIDADE,a.VALVULA_POTE,a.ID id_linha,a.ID_POTE_CATALISADOR,a.VALVULA_CATALISADOR,CASE WHEN  a.REFERENCIA_A = a.REFERENCIA_B THEN 1 ELSE 0 END ref_catalizador "
				+ "from PIN_MOV_RECEITAS_LINHAS a  "
				+ "INNER JOIN PIN_DIC_PRODUTOS b on a.ID_REFERENCIA_A = b.ID "
				+ "WHERE a.ID_RECEITA = :id AND b.INATIVO = 0 "
				+ "union "
				+ "select d.cod_REF ,d.cisterna,d.nome_REF_SUBSTITUTA,d.cod_REF_SUBSTITUTA,d.ID,ID_POTE,d.nome_REF,d.id_UNIDADE,d.factor_CONVERSAO,1 relacionado,c.PERC_DILUICAO,b.ID id_pai,a.UNIDADE,a.VALVULA_POTE,a.ID id_linha,a.ID_POTE_CATALISADOR,a.VALVULA_CATALISADOR,CASE WHEN  a.REFERENCIA_A = a.REFERENCIA_B THEN 1 ELSE 0 END ref_catalizador  "
				+ "from PIN_MOV_RECEITAS_LINHAS a  "
				+ "INNER JOIN PIN_DIC_PRODUTOS b on a.ID_REFERENCIA_A = b.ID "
				+ "INNER JOIN PIN_DIC_PRODUTOS_RELACIONADOS c on c.ID_PRODUTO = b.ID "
				+ "INNER JOIN PIN_DIC_PRODUTOS d on c.ID_REF = d.ID "
				+ "WHERE a.ID_RECEITA = :id AND b.INATIVO = 0  AND d.INATIVO = 0 "
				+ ") tab order by id_linha,id_pai,relacionado ");
		query.setParameter("id", id);
		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}
}
