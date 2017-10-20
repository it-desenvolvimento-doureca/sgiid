package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_BANHO;
import pt.example.entity.AB_DIC_BANHO_ADITIVO;
import pt.example.entity.GER_FORNECEDOR;
import pt.example.entity.GER_MODULO;

public class GER_MODULODao extends GenericDaoJpaImpl<GER_MODULO,Integer> implements GenericDao<GER_MODULO,Integer> {
	public GER_MODULODao() {
		super(GER_MODULO.class);
	}
	
	public List<GER_MODULO> getall() {
		Query query = entityManager.createQuery("select a"
				+ " from GER_MODULO a where a.INATIVO != 1");
		List<GER_MODULO> data = query.getResultList();
		return data;

	}

}
