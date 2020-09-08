package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_DIC_VEICULO;

public class GER_DIC_VEICULODao extends GenericDaoJpaImpl<GER_DIC_VEICULO, Integer>
		implements GenericDao<GER_DIC_VEICULO, Integer> {
	public GER_DIC_VEICULODao() {
		super(GER_DIC_VEICULO.class);
	}

	public List<GER_DIC_VEICULO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GER_DIC_VEICULO a where a.ID_VEICULO = :id ");
		query.setParameter("id", id);
		List<GER_DIC_VEICULO> data = query.getResultList();
		return data;

	}

	public List<GER_DIC_VEICULO> getall() {

		Query query = entityManager.createQuery("Select a,(select b.NOME from GER_DIC_OEM b where a.ID_OEM = b.ID_OEM)  from GER_DIC_VEICULO a");
		List<GER_DIC_VEICULO> data = query.getResultList();
		return data;

	}

}
