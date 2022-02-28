package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_SECTORES_ABSENTISMO_LINHA;

public class RH_DIC_SECTORES_ABSENTISMO_LINHADao extends GenericDaoJpaImpl<RH_DIC_SECTORES_ABSENTISMO_LINHA, Integer>
		implements GenericDao<RH_DIC_SECTORES_ABSENTISMO_LINHA, Integer> {
	public RH_DIC_SECTORES_ABSENTISMO_LINHADao() {
		super(RH_DIC_SECTORES_ABSENTISMO_LINHA.class);
	}

	public List<RH_DIC_SECTORES_ABSENTISMO_LINHA> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from RH_DIC_SECTORES_ABSENTISMO_LINHA a where a.ID_SECTOR_ABSENTISMO_LINHA = :id ");
		query.setParameter("id", id);
		List<RH_DIC_SECTORES_ABSENTISMO_LINHA> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_SECTORES_ABSENTISMO_LINHA> getall() {

		Query query = entityManager.createQuery("Select a from RH_DIC_SECTORES_ABSENTISMO_LINHA a");
		List<RH_DIC_SECTORES_ABSENTISMO_LINHA> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_SECTORES_ABSENTISMO_LINHA> getSectoresAll(Integer id ) {

		Query query = entityManager.createNativeQuery(" DECLARE @ID int = " + id
				+ " select COD_SECTOR,DES_SECTOR from RH_SECTORES a where a.COD_SECTOR not in (select b.COD_SECTOR from RH_DIC_SECTORES_ABSENTISMO_LINHA b where b.ID_SECTOR_ABSENTISMO = @ID) order by DES_SECTOR");

		List<RH_DIC_SECTORES_ABSENTISMO_LINHA> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_SECTORES_ABSENTISMO_LINHA> getSectoresAbsentismo(Integer id ) {

		Query query = entityManager.createNativeQuery("DECLARE @ID int = "+ id
				+ " select a.ID_SECTOR_ABSENTISMO_LINHA,b.COD_SECTOR,DES_SECTOR from RH_DIC_SECTORES_ABSENTISMO_LINHA a "
				+ "inner join RH_SECTORES b  on a.COD_SECTOR = b.COD_SECTOR "
				+ "where a.ID_SECTOR_ABSENTISMO = @ID order by DES_SECTOR");

		List<RH_DIC_SECTORES_ABSENTISMO_LINHA> data = query.getResultList();
		return data;

	}

}
