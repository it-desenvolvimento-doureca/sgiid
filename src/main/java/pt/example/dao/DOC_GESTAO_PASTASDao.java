package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.DOC_GESTAO_PASTAS;

public class DOC_GESTAO_PASTASDao extends GenericDaoJpaImpl<DOC_GESTAO_PASTAS, Integer>
		implements GenericDao<DOC_GESTAO_PASTAS, Integer> {
	public DOC_GESTAO_PASTASDao() {
		super(DOC_GESTAO_PASTAS.class);
	}

	public List<DOC_GESTAO_PASTAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from DOC_GESTAO_PASTAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<DOC_GESTAO_PASTAS> data = query.getResultList();
		return data;

	}

	public List<DOC_GESTAO_PASTAS> getall() {

		Query query = entityManager.createQuery("Select a from DOC_GESTAO_PASTAS a ");
		List<DOC_GESTAO_PASTAS> data = query.getResultList();
		return data;

	}
	
	public List<DOC_GESTAO_PASTAS> verificacaminho(Integer id, String caminho) {

		Query query = entityManager.createQuery("Select a from DOC_GESTAO_PASTAS a where a.ID != :id and a.CAMINHO = :caminho");
		query.setParameter("id", id);
		query.setParameter("caminho", caminho);
		List<DOC_GESTAO_PASTAS> data = query.getResultList();
		return data;

	}

	
	

}
