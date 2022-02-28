package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_PEDIDOS_DOCUMENTOS;
import pt.example.entity.PA_MOV_FICHEIROS;

public class MAN_MOV_PEDIDOS_DOCUMENTOSDao extends GenericDaoJpaImpl<MAN_MOV_PEDIDOS_DOCUMENTOS, Integer>
		implements GenericDao<MAN_MOV_PEDIDOS_DOCUMENTOS, Integer> {
	public MAN_MOV_PEDIDOS_DOCUMENTOSDao() {
		super(MAN_MOV_PEDIDOS_DOCUMENTOS.class);
	}

	public List<MAN_MOV_PEDIDOS_DOCUMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_PEDIDOS_DOCUMENTOS a ");

		List<MAN_MOV_PEDIDOS_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}


	public List<MAN_MOV_PEDIDOS_DOCUMENTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a,b from MAN_MOV_PEDIDOS_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_PEDIDO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<MAN_MOV_PEDIDOS_DOCUMENTOS> data = query.getResultList();
		return data;

	}
	
}
