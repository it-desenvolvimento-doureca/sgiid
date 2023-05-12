package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_MOV_MANUTENCAO_EQUIPAMENTOS;
import pt.example.entity.PA_MOV_FICHEIROS;

public class MAN_MOV_MANUTENCAO_EQUIPAMENTOSDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_EQUIPAMENTOS, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_EQUIPAMENTOS, Integer> {
	public MAN_MOV_MANUTENCAO_EQUIPAMENTOSDao() {
		super(MAN_MOV_MANUTENCAO_EQUIPAMENTOS.class);
	}

	public List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_EQUIPAMENTOS a ");

		List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> getall2() {

		Query query = entityManager.createNativeQuery("Select a.ID_MANUTENCAO,a.NOME,a.LOCALIZACAO,e.DESCRICAO,a.COD_EQUIPAMENTO_PRINCIPAL,c.NOME  NOME_EQUIPAMENTO_PRINCIPAL,a.ATIVO,a.OBSOLETO,a.FABRICANTE ,a.MODELO ,a.ANO ,a.N_SERIE from MAN_MOV_MANUTENCAO_EQUIPAMENTOS a "
				+ " left join (select CONCAT('E',ID)  as ID,DESCRICAO from MAN_DIC_EDIFICIOS 	union all"
				+ "	select CONCAT('P',a.ID) as ID,b.DESCRICAO + '/' + a.DESCRICAO  from MAN_DIC_PISOS a inner join MAN_DIC_EDIFICIOS b on a.ID_EDIFICIO = b.ID "
				+ "	union all 	select CONCAT('D' , a.ID)  as ID,c.DESCRICAO + '/' + b.DESCRICAO+ '/' + a.DESCRICAO from MAN_DIC_DIVISOES a "
				+ " inner join MAN_DIC_PISOS b on a.ID_PISO = b.ID inner join MAN_DIC_EDIFICIOS c on b.ID_EDIFICIO = c.ID  ) e on e.ID = CONCAT(ISNULL(a.TIPO_LOCALIZACAO,'E'), a.LOCALIZACAO)"
				+ " left join MAN_MOV_MANUTENCAO_EQUIPAMENTOS c on a.COD_EQUIPAMENTO_PRINCIPAL = c.ID_MANUTENCAO");

		List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a from MAN_MOV_MANUTENCAO_EQUIPAMENTOS a "
				+ "where  a.ID_MANUTENCAO = :id ");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> data = query.getResultList();
		return data;

	}
	
	public List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> getbyidlocalizacao(String tipo,Integer id) {

		Query query = entityManager.createQuery("select a from MAN_MOV_MANUTENCAO_EQUIPAMENTOS a "
				+ "where  a.TIPO_LOCALIZACAO = :tipo and a.LOCALIZACAO = :id");
		query.setParameter("id", id);
		query.setParameter("tipo", tipo);
		List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> data = query.getResultList();
		return data;

	}
	
}
