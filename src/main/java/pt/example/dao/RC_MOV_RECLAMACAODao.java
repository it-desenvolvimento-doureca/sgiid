package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO;

public class RC_MOV_RECLAMACAODao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO, Integer> {
	public RC_MOV_RECLAMACAODao() {
		super(RC_MOV_RECLAMACAO.class);
	}

	public List<RC_MOV_RECLAMACAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO a where a.INATIVO != 1 order by a.DATA_RECLAMACAO desc, a.ID_RECLAMACAO desc  ");
		List<RC_MOV_RECLAMACAO> data = query.getResultList();
		return data;

	}
	
	public List<RC_MOV_RECLAMACAO> getbyidtotaltarefas(Integer id) {

		Query query = entityManager.createNativeQuery("select count(*) from RC_MOV_RECLAMACAO_PLANOS_ACCOES where ID_RECLAMACAO = :id and ESTADO not in ('A','C','R') ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO> data = query.getResultList();
		return data;

	}

}
