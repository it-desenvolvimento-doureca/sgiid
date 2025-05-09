package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_CORES_ACABAMENTOS;

public class PIN_DIC_CORES_ACABAMENTOSDao extends GenericDaoJpaImpl<PIN_DIC_CORES_ACABAMENTOS, Integer>
		implements GenericDao<PIN_DIC_CORES_ACABAMENTOS, Integer> {
	public PIN_DIC_CORES_ACABAMENTOSDao() {
		super(PIN_DIC_CORES_ACABAMENTOS.class);
	}

	public List<PIN_DIC_CORES_ACABAMENTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_CORES_ACABAMENTOS a where a.ID = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_CORES_ACABAMENTOS> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_CORES_ACABAMENTOS> getbytipo(String tipo) {

		Query query = entityManager.createQuery(" select a.ID,a.NOME,a.CODIGO,c.TIPO from PIN_DIC_CORES_ACABAMENTOS a  "
				+ " left join PIN_DIC_POTES b on a.ID_POTE = b.ID "
				+ " left join PIN_DIC_CABINES c on b.ID_CABINE = c.ID "
				+ " where c.TIPO = :tipo and a.ATIVO = 1 ");
		query.setParameter("tipo", tipo);
		List<PIN_DIC_CORES_ACABAMENTOS> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_CORES_ACABAMENTOS> getbytipoAll(){

		Query query = entityManager.createQuery(" select a.ID,a.NOME,a.CODIGO,c.TIPO,b.NOME as NOME_POTE,c.NOME_CABINE from PIN_DIC_CORES_ACABAMENTOS a  "
				+ " inner join PIN_DIC_POTES b on a.ID_POTE = b.ID  inner join PIN_DIC_CABINES c on b.ID_CABINE = c.ID "); 
		List<PIN_DIC_CORES_ACABAMENTOS> data = query.getResultList();
		return data;

	}
	
	
	public List<PIN_DIC_CORES_ACABAMENTOS> getbytipoAll2(){

		Query query = entityManager.createNativeQuery("   select  a.ID,a.NOME,a.CODIGO,c.TIPO , "
				+ "	STRING_AGG(concat(b.NOME,' - ', c.NOME_CABINE),' | ') NOME_POTE "
				+ "  from PIN_DIC_CORES_ACABAMENTOS a  "
				+ "  left join PIN_DIC_POTES b on  b.ID  in (select value from string_split(a.ID_POTE,',')) "
				+ "  left join PIN_DIC_CABINES c on b.ID_CABINE = c.ID "
				+ "  where  a.ATIVO = 1 "
				+ "  GROUP BY  a.ID,a.NOME,a.CODIGO,c.TIPO "
				+ "  order by TIPO,CODIGO "); 
		List<PIN_DIC_CORES_ACABAMENTOS> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_CORES_ACABAMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from PIN_DIC_CORES_ACABAMENTOS a where a.ATIVO = 1 ");
		List<PIN_DIC_CORES_ACABAMENTOS> data = query.getResultList();
		return data;

	}

	
	public List<PIN_DIC_CORES_ACABAMENTOS> getall2() {

		Query query = entityManager.createNativeQuery("select a.ID,a.CODIGO,a.NOME,a.ID_POTE,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.UTZ_ANULA,a.DATA_ANULA,a.ATIVO, "
				+ "	STRING_AGG(concat(b.NOME,' - ', c.NOME_CABINE),' | ') NOME_POTE "
				+ "  from PIN_DIC_CORES_ACABAMENTOS a   "
				+ "  left join PIN_DIC_POTES b on  b.ID  in (select value from string_split(a.ID_POTE,',')) "
				+ "  left join PIN_DIC_CABINES c on b.ID_CABINE = c.ID  "
				+ "  where  a.ATIVO = 1  "
				+ "  GROUP BY a.ID,a.CODIGO,a.NOME,a.ID_POTE,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.UTZ_ANULA,a.DATA_ANULA,a.ATIVO ");
		List<PIN_DIC_CORES_ACABAMENTOS> data = query.getResultList();
		return data;

	}
	
}
