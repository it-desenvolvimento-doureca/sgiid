package pt.example.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_BANHO_COMPONENTE;
import pt.example.entity.FIN_DOC_ACORDO;

public class FIN_DOC_ACORDODao extends GenericDaoJpaImpl<FIN_DOC_ACORDO, Integer>
		implements GenericDao<FIN_DOC_ACORDO, Integer> {
	public FIN_DOC_ACORDODao() {
		super(FIN_DOC_ACORDO.class);
	}

	public List<FIN_DOC_ACORDO> getbyid(String id) {
		Query query = entityManager.createQuery("Select a from FIN_DOC_ACORDO a where a.ID_CLIENTE = :id");
		query.setParameter("id", id);
		List<FIN_DOC_ACORDO> data = query.getResultList();
		return data;
	}

	public List<FIN_DOC_ACORDO> getall() {
		Query query = entityManager.createQuery("Select a from FIN_DOC_ACORDO a ");
		List<FIN_DOC_ACORDO> data = query.getResultList();
		return data;
	}



}
