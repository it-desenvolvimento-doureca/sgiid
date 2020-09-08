package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PA_DIC_AMBITOS;

public class PA_DIC_AMBITOSDao extends GenericDaoJpaImpl<PA_DIC_AMBITOS, Integer> implements GenericDao<PA_DIC_AMBITOS, Integer> {
	public PA_DIC_AMBITOSDao() {
		super(PA_DIC_AMBITOS.class);
	}

	public List<PA_DIC_AMBITOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PA_DIC_AMBITOS a where a.ID_AMBITO = :id ");
		query.setParameter("id", id);
		List<PA_DIC_AMBITOS> data = query.getResultList();
		return data;

	}


	public List<PA_DIC_AMBITOS> getall() {

		Query query = entityManager.createQuery("Select a from PA_DIC_AMBITOS a");
		List<PA_DIC_AMBITOS> data = query.getResultList();
		return data;

	}

}
