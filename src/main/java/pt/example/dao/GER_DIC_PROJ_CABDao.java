package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_DIC_PROJ_CAB;

public class GER_DIC_PROJ_CABDao extends GenericDaoJpaImpl<GER_DIC_PROJ_CAB, Integer>
		implements GenericDao<GER_DIC_PROJ_CAB, Integer> {
	public GER_DIC_PROJ_CABDao() {
		super(GER_DIC_PROJ_CAB.class);
	}

	public List<GER_DIC_PROJ_CAB> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_DIC_PROJ_CAB a where a.ID_PROJ_CAB = :id ");
		query.setParameter("id", id);
		List<GER_DIC_PROJ_CAB> data = query.getResultList();
		return data;

	}

	public List<GER_DIC_PROJ_CAB> getall() {

		Query query = entityManager.createQuery("Select a,(select b.NOME from GER_DIC_PROGRAMA b where a.ID_PROGRAMA = b.ID_PROGRAMA) from GER_DIC_PROJ_CAB a");
		List<GER_DIC_PROJ_CAB> data = query.getResultList();
		return data;

	}

	
	public List<GER_DIC_PROJ_CAB> getall2() {

		Query query = entityManager.createNativeQuery("Select a.ID_PROJ_CAB,b.NOME PROGRAMA,c.NOME as VEICULO,d.NOME as OEM  from GER_DIC_PROJ_CAB a "
				+ "inner join GER_DIC_PROGRAMA b on a.ID_PROGRAMA = b.ID_PROGRAMA "
				+ "inner join GER_DIC_VEICULO c on b.ID_VEICULO = c.ID_VEICULO "
				+ "inner join GER_DIC_OEM d on c.ID_OEM = d.ID_OEM");
		
		List<GER_DIC_PROJ_CAB> data = query.getResultList();
		return data;

	}
	

	public List<GER_DIC_PROJ_CAB> getbyid2(Integer id) {

		Query query = entityManager.createNativeQuery("Select a.ID_PROJ_CAB,a.ID_PROGRAMA,c.NOME as VEICULO,d.NOME as OEM  from GER_DIC_PROJ_CAB a "
				+ "inner join GER_DIC_PROGRAMA b on a.ID_PROGRAMA = b.ID_PROGRAMA "
				+ "inner join GER_DIC_VEICULO c on b.ID_VEICULO = c.ID_VEICULO "
				+ "inner join GER_DIC_OEM d on c.ID_OEM = d.ID_OEM "
				+ "where a.ID_PROJ_CAB = :id ");
		query.setParameter("id", id);
		List<GER_DIC_PROJ_CAB> data = query.getResultList();
		return data;

	}
}
