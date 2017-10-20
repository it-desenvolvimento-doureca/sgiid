package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_UNIDADE_MEDIDA;
import pt.example.entity.AB_DIC_ZONA;
import pt.example.entity.GER_FORNECEDOR;

public class AB_DIC_ZONADao extends GenericDaoJpaImpl<AB_DIC_ZONA, Integer>
		implements GenericDao<AB_DIC_ZONA, Integer> {
	public AB_DIC_ZONADao() {
		super(AB_DIC_ZONA.class);
	}

	public List<AB_DIC_ZONA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_ZONA a where a.ID_ZONA = :id ");
		query.setParameter("id", id);
		List<AB_DIC_ZONA> data = query.getResultList();
		return data;

	}

	public List<AB_DIC_ZONA> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_ZONA a where a.INATIVO != 1 ");
		List<AB_DIC_ZONA> data = query.getResultList();
		return data;

	}

}
