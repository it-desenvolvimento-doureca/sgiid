package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_DIC_CLASSIFICACAO;

public class RC_DIC_CLASSIFICACAODao extends GenericDaoJpaImpl<RC_DIC_CLASSIFICACAO, Integer>
		implements GenericDao<RC_DIC_CLASSIFICACAO, Integer> {
	public RC_DIC_CLASSIFICACAODao() {
		super(RC_DIC_CLASSIFICACAO.class);
	}

	public List<RC_DIC_CLASSIFICACAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_DIC_CLASSIFICACAO a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_DIC_CLASSIFICACAO> data = query.getResultList();
		return data;

	}

	public List<RC_DIC_CLASSIFICACAO> getall() {

		Query query = entityManager.createQuery("Select a from RC_DIC_CLASSIFICACAO a ");
		List<RC_DIC_CLASSIFICACAO> data = query.getResultList();
		return data;

	}

}
