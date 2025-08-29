package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_TIPOS_ACABAMENTO;

public class PIN_DIC_TIPOS_ACABAMENTODao extends GenericDaoJpaImpl<PIN_DIC_TIPOS_ACABAMENTO, Integer>
		implements GenericDao<PIN_DIC_TIPOS_ACABAMENTO, Integer> {
	public PIN_DIC_TIPOS_ACABAMENTODao() {
		super(PIN_DIC_TIPOS_ACABAMENTO.class);
	}

	public List<PIN_DIC_TIPOS_ACABAMENTO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_TIPOS_ACABAMENTO a where a.ID = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_TIPOS_ACABAMENTO> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_TIPOS_ACABAMENTO> getbytipo(String tipo) {

		Query query = entityManager.createQuery(" select a.ID,a.NOME,a.GAMA,c.TIPO from PIN_DIC_TIPOS_ACABAMENTO a  "
				+ " left join PIN_DIC_POTES b on a.ID_POTE = b.ID "
				+ " left join PIN_DIC_CABINES c on b.ID_CABINE = c.ID "
				+ " where c.TIPO = :tipo and a.ATIVO = 1 ");
		query.setParameter("tipo", tipo);
		List<PIN_DIC_TIPOS_ACABAMENTO> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_TIPOS_ACABAMENTO> getbytipoAll(){

		Query query = entityManager.createQuery(" select a.ID,a.NOME,a.GAMA,c.TIPO,b.NOME as NOME_POTE,c.NOME_CABINE from PIN_DIC_TIPOS_ACABAMENTO a  "
				+ " inner join PIN_DIC_POTES b on a.ID_POTE = b.ID  inner join PIN_DIC_CABINES c on b.ID_CABINE = c.ID "); 
		List<PIN_DIC_TIPOS_ACABAMENTO> data = query.getResultList();
		return data;

	}
	
	
	public List<PIN_DIC_TIPOS_ACABAMENTO> getbytipoAll2(){

		Query query = entityManager.createNativeQuery("   select  a.ID,a.NOME,a.GAMA,c.TIPO , "
				+ "	STRING_AGG(concat(b.NOME,' - ', c.NOME_CABINE),' | ') NOME_POTE "
				+ "  from PIN_DIC_TIPOS_ACABAMENTO a  "
				+ "  left join PIN_DIC_POTES b on  b.ID  in (select value from string_split(a.ID_POTE,',')) "
				+ "  left join PIN_DIC_CABINES c on b.ID_CABINE = c.ID "
				+ "  where  a.ATIVO = 1 "
				+ "  GROUP BY  a.ID,a.NOME,a.GAMA,c.TIPO "
				+ "  order by TIPO,GAMA "); 
		List<PIN_DIC_TIPOS_ACABAMENTO> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_TIPOS_ACABAMENTO> getall() {

		Query query = entityManager.createQuery("Select a from PIN_DIC_TIPOS_ACABAMENTO a where a.ATIVO = 1 ");
		List<PIN_DIC_TIPOS_ACABAMENTO> data = query.getResultList();
		return data;

	}

	
	public List<PIN_DIC_TIPOS_ACABAMENTO> getall2() {

		Query query = entityManager.createNativeQuery("select a.ID,a.GAMA,a.NOME,a.ID_POTE,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.UTZ_ANULA,a.DATA_ANULA,a.ATIVO, "
				+ "	STRING_AGG(concat(b.NOME,' - ', c.NOME_CABINE),' | ') NOME_POTE "
				+ "  from PIN_DIC_TIPOS_ACABAMENTO a   "
				+ "  left join PIN_DIC_POTES b on  b.ID  in (select value from string_split(a.ID_POTE,',')) "
				+ "  left join PIN_DIC_CABINES c on b.ID_CABINE = c.ID  "
				+ "  where  a.ATIVO = 1  "
				+ "  GROUP BY a.ID,a.GAMA,a.NOME,a.ID_POTE,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.UTZ_ANULA,a.DATA_ANULA,a.ATIVO ");
		List<PIN_DIC_TIPOS_ACABAMENTO> data = query.getResultList();
		return data;

	}
	
}
