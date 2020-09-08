package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_FORNECEDOR;

public class RC_MOV_RECLAMACAO_FORNECEDORDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_FORNECEDOR, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_FORNECEDOR, Integer> {
	public RC_MOV_RECLAMACAO_FORNECEDORDao() {
		super(RC_MOV_RECLAMACAO_FORNECEDOR.class);
	}

	public List<RC_MOV_RECLAMACAO_FORNECEDOR> getbyid(Integer id) {

		Query query = entityManager
				.createQuery("Select a from RC_MOV_RECLAMACAO_FORNECEDOR a where a.ID_RECLAMACAO = :id ");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_FORNECEDOR> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_FORNECEDOR> getall() {

		Query query = entityManager.createQuery(
				"Select a from RC_MOV_RECLAMACAO_FORNECEDOR a where a.INATIVO != 1 order by a.DATA_RECLAMACAO desc, a.ID_RECLAMACAO desc  ");
		List<RC_MOV_RECLAMACAO_FORNECEDOR> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_FORNECEDOR> getall2() {

		Query query = entityManager.createQuery(
				"Select a,(select b.DESCRICAO from RC_DIC_CLASSIFICACAO b where b.ID = a.CLASSIFICACAO) as CLASSIFICACAO_DESC "
				+ "from RC_MOV_RECLAMACAO_FORNECEDOR a where a.INATIVO != 1 order by a.DATA_RECLAMACAO desc, a.ID_RECLAMACAO desc  ");
		List<RC_MOV_RECLAMACAO_FORNECEDOR> data = query.getResultList();
		return data;

	}

}
