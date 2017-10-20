package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_BANHO_COMPONENTE;
import pt.example.entity.AB_DIC_COMPONENTE;

public class AB_DIC_COMPONENTEDao extends GenericDaoJpaImpl<AB_DIC_COMPONENTE, Integer>
		implements GenericDao<AB_DIC_COMPONENTE, Integer> {
	public AB_DIC_COMPONENTEDao() {
		super(AB_DIC_COMPONENTE.class);
	}

	public List<AB_DIC_COMPONENTE> getbyid_componente(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_COMPONENTE a where a.ID_COMPONENTE = :id ");
		query.setParameter("id", id);
		List<AB_DIC_COMPONENTE> data = query.getResultList();
		return data;

	}

	public List<AB_DIC_COMPONENTE> gelAll(String tipo) {
		Query query = null;
		if (tipo.equals("T")) {
			query = entityManager.createQuery("Select a from AB_DIC_COMPONENTE a where a.INATIVO != 1 ");
		} else {
			query = entityManager
					.createQuery("Select a from AB_DIC_COMPONENTE a where a.INATIVO != 1 and a.TIPO IN (:tipo,'T')");
			query.setParameter("tipo", tipo);
		}

		List<AB_DIC_COMPONENTE> data = query.getResultList();
		return data;

	}

}
