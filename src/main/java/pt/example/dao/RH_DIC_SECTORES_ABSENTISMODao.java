package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_SECTORES_ABSENTISMO;

public class RH_DIC_SECTORES_ABSENTISMODao extends GenericDaoJpaImpl<RH_DIC_SECTORES_ABSENTISMO, Integer>
		implements GenericDao<RH_DIC_SECTORES_ABSENTISMO, Integer> {
	public RH_DIC_SECTORES_ABSENTISMODao() {
		super(RH_DIC_SECTORES_ABSENTISMO.class);
	}

	public List<RH_DIC_SECTORES_ABSENTISMO> getbyid(Integer id) {

		Query query = entityManager
				.createQuery("Select a from RH_DIC_SECTORES_ABSENTISMO a where a.ID_SECTOR_ABSENTISMO = :id ");
		query.setParameter("id", id);
		List<RH_DIC_SECTORES_ABSENTISMO> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_SECTORES_ABSENTISMO> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_SECTORES_ABSENTISMO a where a.ATIVO = 1");
		List<RH_DIC_SECTORES_ABSENTISMO> data = query.getResultList();
		return data;

	}

}
