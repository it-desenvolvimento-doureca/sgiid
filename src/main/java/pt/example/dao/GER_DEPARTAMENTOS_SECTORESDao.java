package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_DEPARTAMENTOS_SECTORES;

public class GER_DEPARTAMENTOS_SECTORESDao extends GenericDaoJpaImpl<GER_DEPARTAMENTOS_SECTORES, Integer>
		implements GenericDao<GER_DEPARTAMENTOS_SECTORES, Integer> {
	public GER_DEPARTAMENTOS_SECTORESDao() {
		super(GER_DEPARTAMENTOS_SECTORES.class);
	}

	public List<GER_DEPARTAMENTOS_SECTORES> getbyId(Integer id) {
		Query query = entityManager.createQuery("select a,b from GER_DEPARTAMENTOS_SECTORES a,RH_SECTORES b "
				+ " where  a.COD_SECTOR = b.COD_SECTOR and a.ID_DEPARTAMENTOS_SECTORES = :id");
		query.setParameter("id", id);
		List<GER_DEPARTAMENTOS_SECTORES> data = query.getResultList();
		return data;

	}

	public List<GER_DEPARTAMENTOS_SECTORES> getbyIduser(Integer ID_DEPARTAMENTO) {
		Query query = entityManager.createNativeQuery("select a.ID_DEPARTAMENTOS_SECTORES,b.COD_SECTOR,b.DES_SECTOR from  GER_DEPARTAMENTOS_SECTORES a "
				+ " inner join RH_SECTORES b on a.COD_SECTOR = b.COD_SECTOR where a.ID_DEPARTAMENTO =:id");
		query.setParameter("id", ID_DEPARTAMENTO);
		List<GER_DEPARTAMENTOS_SECTORES> data = query.getResultList();
		return data;

	}

	public List<GER_DEPARTAMENTOS_SECTORES> getbyIdnotuser(Integer ID_DEPARTAMENTO) {
		Query query = entityManager.createNativeQuery("select COD_SECTOR,DES_SECTOR from RH_SECTORES "
				+ "where COD_SECTOR not in (select COD_SECTOR from GER_DEPARTAMENTOS_SECTORES a where  a.ID_DEPARTAMENTO = :id  ) "
				+ "and COD_SECTOR not in (select COD_SECTOR from GER_DEPARTAMENTOS_SECTORES a) ");
		query.setParameter("id", ID_DEPARTAMENTO);
		List<GER_DEPARTAMENTOS_SECTORES> data = query.getResultList();
		return data;

	}

}
