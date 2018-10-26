package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RC_MOV_RECLAMACAO_ENCOMENDAS;
import pt.example.entity.RC_MOV_RECLAMACAO_STOCK;

public class RC_MOV_RECLAMACAO_ENCOMENDASDao extends GenericDaoJpaImpl<RC_MOV_RECLAMACAO_ENCOMENDAS, Integer>
		implements GenericDao<RC_MOV_RECLAMACAO_ENCOMENDAS, Integer> {
	public RC_MOV_RECLAMACAO_ENCOMENDASDao() {
		super(RC_MOV_RECLAMACAO_ENCOMENDAS.class);
	}

	public List<RC_MOV_RECLAMACAO_ENCOMENDAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_ENCOMENDAS a where a.ID_RECLAMACAO = :id and  a.ID_LINHA_ARTIGO_SIMILAR = null order by a.CDDDATBES");
		query.setParameter("id", id);
		List<RC_MOV_RECLAMACAO_ENCOMENDAS> data = query.getResultList();
		return data;

	}
	

	public List<RC_MOV_RECLAMACAO_STOCK> getbyidLINHA(Integer id, Integer id_linha) {

		Query query = entityManager.createQuery(
				"Select a from RC_MOV_RECLAMACAO_ENCOMENDAS a where a.ID_RECLAMACAO = :id and a.ID_LINHA_ARTIGO_SIMILAR =:id_linha order by a.CDDDATBES");
		query.setParameter("id", id);
		query.setParameter("id_linha", id_linha);
		List<RC_MOV_RECLAMACAO_STOCK> data = query.getResultList();
		return data;

	}

	public List<RC_MOV_RECLAMACAO_ENCOMENDAS> getall() {

		Query query = entityManager.createQuery("Select a from RC_MOV_RECLAMACAO_ENCOMENDAS a ");
		List<RC_MOV_RECLAMACAO_ENCOMENDAS> data = query.getResultList();
		return data;

	}

}
