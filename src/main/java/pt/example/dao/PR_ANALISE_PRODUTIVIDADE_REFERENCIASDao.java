package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_ANALISE_PRODUTIVIDADE_REFERENCIAS;

public class PR_ANALISE_PRODUTIVIDADE_REFERENCIASDao extends GenericDaoJpaImpl<PR_ANALISE_PRODUTIVIDADE_REFERENCIAS, Integer>
		implements GenericDao<PR_ANALISE_PRODUTIVIDADE_REFERENCIAS, Integer> {
	public PR_ANALISE_PRODUTIVIDADE_REFERENCIASDao() {
		super(PR_ANALISE_PRODUTIVIDADE_REFERENCIAS.class);
	}

	public List<PR_ANALISE_PRODUTIVIDADE_REFERENCIAS> getall() {

		Query query = entityManager.createQuery("Select a from PR_ANALISE_PRODUTIVIDADE_REFERENCIAS a ");
		List<PR_ANALISE_PRODUTIVIDADE_REFERENCIAS> data = query.getResultList();
		return data;

	}
	
	public void deleteByProRef(PR_ANALISE_PRODUTIVIDADE_REFERENCIAS obj) {
	    Query q = entityManager.createQuery("DELETE FROM PR_ANALISE_PRODUTIVIDADE_REFERENCIAS r WHERE r.PROREF = :proRef");
	    q.setParameter("proRef", obj.getPROREF());
	    q.executeUpdate();
	}

	 

}
