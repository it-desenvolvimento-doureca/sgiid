package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_DIC_ACCOES_RECLAMACAO;

public class RC_DIC_ACCOES_RECLAMACAODao extends GenericDaoJpaImpl<RC_DIC_ACCOES_RECLAMACAO, Integer>
		implements GenericDao<RC_DIC_ACCOES_RECLAMACAO, Integer> {
	public RC_DIC_ACCOES_RECLAMACAODao() {
		super(RC_DIC_ACCOES_RECLAMACAO.class);
	}

	public List<RC_DIC_ACCOES_RECLAMACAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_DIC_ACCOES_RECLAMACAO a where a.ID = :id ");
		query.setParameter("id", id);
		List<RC_DIC_ACCOES_RECLAMACAO> data = query.getResultList();
		return data;

	}

	public List<RC_DIC_ACCOES_RECLAMACAO> getall() {

		Query query = entityManager.createQuery("Select a from RC_DIC_ACCOES_RECLAMACAO a ");
		List<RC_DIC_ACCOES_RECLAMACAO> data = query.getResultList();
		return data;

	}

}
