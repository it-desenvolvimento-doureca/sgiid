package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_DIC_SECTORES_AGREGADORES_LINHA;

public class PR_DIC_SECTORES_AGREGADORES_LINHADao extends GenericDaoJpaImpl<PR_DIC_SECTORES_AGREGADORES_LINHA, Integer>
		implements GenericDao<PR_DIC_SECTORES_AGREGADORES_LINHA, Integer> {
	public PR_DIC_SECTORES_AGREGADORES_LINHADao() {
		super(PR_DIC_SECTORES_AGREGADORES_LINHA.class);
	}

	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a from PR_DIC_SECTORES_AGREGADORES_LINHA a where a.ID_SECTOR_AGREGADOR_LINHA = :id ");
		query.setParameter("id", id);
		List<PR_DIC_SECTORES_AGREGADORES_LINHA> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getall() {

		Query query = entityManager.createQuery("Select a from PR_DIC_SECTORES_AGREGADORES_LINHA a");
		List<PR_DIC_SECTORES_AGREGADORES_LINHA> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getSectoresAll(Integer id, Integer id_linha) {

		Query query = entityManager.createNativeQuery("DECLARE @LINHA int = " + id_linha
				+ "DECLARE @ID int = " + id
				+ " select COD_SECTOR,DES_SECTOR from RH_SECTORES a where a.COD_SECTOR not in (select b.COD_SECTOR from PR_DIC_SECTORES_AGREGADORES_LINHA b where b.LINHA = @LINHA and b.ID_SECTOR_AGREGADOR = @ID) order by DES_SECTOR");

		List<PR_DIC_SECTORES_AGREGADORES_LINHA> data = query.getResultList();
		return data;

	}

	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getSectoresAgregadores(Integer id, Integer id_linha) {

		Query query = entityManager.createNativeQuery("DECLARE @LINHA int = " + id_linha
				+ "DECLARE @ID int = "+ id
				+ " select a.ID_SECTOR_AGREGADOR_LINHA,b.COD_SECTOR,DES_SECTOR from PR_DIC_SECTORES_AGREGADORES_LINHA a "
				+ "inner join RH_SECTORES b  on a.COD_SECTOR = b.COD_SECTOR "
				+ "where  a.LINHA = @LINHA and a.ID_SECTOR_AGREGADOR = @ID order by DES_SECTOR");

		List<PR_DIC_SECTORES_AGREGADORES_LINHA> data = query.getResultList();
		return data;

	}

}
