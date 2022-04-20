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
	
	public List<MAN_MOV_PEDIDOS> getall2(String classificacao) {

		Query query = entityManager.createNativeQuery("Select a.ID_PEDIDO,a.DATA_HORA_PEDIDO,(select NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.ID_RESPONSAVEL) RESPONSAVEL,c.NOME,d.REFERENCIA,d.DESC_REFERENCIA, a.ESTADO,e.DESCRICAO as localizacao_desc "
				+ ",a.DESCRICAO_PEDIDO ,CASE WHEN a.TIPO_RESPONSAVEL = 'U'  AND a.UTILIZADOR is not null THEN (select xy.NOME_UTILIZADOR from GER_UTILIZADORES xy where xy.ID_UTILIZADOR = a.UTILIZADOR)  ELSE  f.NOME_EQUIPA END equipa_utilizador "
				+ "from MAN_MOV_PEDIDOS a left join MAN_MOV_MANUTENCAO_EQUIPAMENTOS c on a.EQUIPAMENTO = c.ID_MANUTENCAO left join MAN_MOV_MANUTENCAO_COMPONENTES d on D.ID_MANUTENCAO = c.ID_MANUTENCAO AND d.ID = a.COMPONENTE "
				+ "left join (select CONCAT('E',ID)  as ID,DESCRICAO from MAN_DIC_EDIFICIOS 	union all "
				+ "	select CONCAT('P',a.ID) as ID,b.DESCRICAO + '/' + a.DESCRICAO  from MAN_DIC_PISOS a inner join MAN_DIC_EDIFICIOS b on a.ID_EDIFICIO = b.ID "
				+ "union all 	select CONCAT('D' , a.ID)  as ID,c.DESCRICAO + '/' + b.DESCRICAO+ '/' + a.DESCRICAO from MAN_DIC_DIVISOES a "
				+ "inner join MAN_DIC_PISOS b on a.ID_PISO = b.ID inner join MAN_DIC_EDIFICIOS c on b.ID_EDIFICIO = c.ID ) e on e.ID = CONCAT(ISNULL(a.TIPO_LOCALIZACAO,'E'), a.LOCALIZACAO) "
				+ "left join MAN_DIC_EQUIPAS f on f.ID = CASE WHEN a.TIPO_RESPONSAVEL = 'E' THEN a.UTILIZADOR  ELSE 0 END "
				+ " WHERE a.CLASSIFICACAO = '"+classificacao+"'");

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
