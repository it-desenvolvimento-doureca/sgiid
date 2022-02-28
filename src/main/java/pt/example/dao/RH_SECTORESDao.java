package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_SECTORES;

public class RH_SECTORESDao extends GenericDaoJpaImpl<RH_SECTORES, Integer>
		implements GenericDao<RH_SECTORES, Integer> {
	public RH_SECTORESDao() {
		super(RH_SECTORES.class);
	}

	public List<RH_SECTORES> getbyid(Integer id) {

		Query query = entityManager
				.createQuery("Select a,(select b.NOME from RH_FUNCIONARIOS b where b.COD_FUNCIONARIO = a.CHEFE1 ) ,"
						+ "(select b.NOME from RH_FUNCIONARIOS b where b.COD_FUNCIONARIO = a.CHEFE2 ),(select t.DES_TURNO from RH_TURNOS t where t.COD_TURNO = a.COD_TURNO )"
						+ " from RH_SECTORES a where a.COD_SECTOR = :id ");
		query.setParameter("id", id);
		List<RH_SECTORES> data = query.getResultList();
		return data;

	}

	public List<RH_SECTORES> getall() {

		Query query = entityManager.createQuery("Select a,(select b.NOME from RH_FUNCIONARIOS b where b.COD_FUNCIONARIO = a.CHEFE1 ),"
				+ "(select b.NOME from RH_FUNCIONARIOS b where b.COD_FUNCIONARIO = a.CHEFE2 ),"
				+ "(select t.DES_TURNO from RH_TURNOS t where t.COD_TURNO = a.COD_TURNO ),(select t.DESCRICAO from GER_LOCAIS t where t.ID = a.LOCAL )  from RH_SECTORES a order by DES_SECTOR");
		List<RH_SECTORES> data = query.getResultList();
		return data;

	}

}
