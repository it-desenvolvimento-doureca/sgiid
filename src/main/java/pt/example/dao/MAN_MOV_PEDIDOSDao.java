package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_MANUTENCAO_PLANOS;
import pt.example.entity.MAN_MOV_PEDIDOS;

public class MAN_MOV_PEDIDOSDao extends GenericDaoJpaImpl<MAN_MOV_PEDIDOS, Integer> implements GenericDao<MAN_MOV_PEDIDOS, Integer> {
	public MAN_MOV_PEDIDOSDao() {
		super(MAN_MOV_PEDIDOS.class);
	}

	public List<MAN_MOV_PEDIDOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_PEDIDOS a ");

		List<MAN_MOV_PEDIDOS> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_PEDIDOS> getall2() {

		Query query = entityManager.createNativeQuery("Select a.ID_PEDIDO,a.DATA_HORA_PEDIDO,(select NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.ID_RESPONSAVEL) RESPONSAVEL,c.NOME,d.REFERENCIA,d.DESC_REFERENCIA, a.ESTADO "
				+ "from MAN_MOV_PEDIDOS a left join MAN_MOV_MANUTENCAO_EQUIPAMENTOS c on a.EQUIPAMENTO = c.ID_MANUTENCAO left join MAN_MOV_MANUTENCAO_COMPONENTES d on D.ID_MANUTENCAO = c.ID_MANUTENCAO AND d.ID = a.COMPONENTE");

		List<MAN_MOV_PEDIDOS> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_PEDIDOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a from MAN_MOV_PEDIDOS a "
				+ "where  a.ID_PEDIDO = :id ");
		query.setParameter("id", id);
		List<MAN_MOV_PEDIDOS> data = query.getResultList();
		return data;

	}
}
