package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_DIC_PISOS;

public class MAN_DIC_PISOSDao extends GenericDaoJpaImpl<MAN_DIC_PISOS, Integer> implements GenericDao<MAN_DIC_PISOS, Integer> {
	public MAN_DIC_PISOSDao() {
		super(MAN_DIC_PISOS.class);
	}

	public List<MAN_DIC_PISOS> getall() {

		Query query = entityManager.createQuery("Select a,b.DESCRICAO from MAN_DIC_PISOS a,MAN_DIC_EDIFICIOS b where a.ID_EDIFICIO = b.ID ");

		List<MAN_DIC_PISOS> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_DIC_PISOS> getALLLOCALLIZACOES() {

		Query query = entityManager.createNativeQuery("select ID,DESCRICAO,'E' as tipo,null as EMAIL_PARA from MAN_DIC_EDIFICIOS union "
				+ "select a.ID,b.DESCRICAO + '/' + a.DESCRICAO ,'P' as tipo,null as EMAIL_PARA from MAN_DIC_PISOS a inner join MAN_DIC_EDIFICIOS b on a.ID_EDIFICIO = b.ID "
				+ "union "
				+ "select a.ID,c.DESCRICAO + '/' + b.DESCRICAO+ '/' + a.DESCRICAO,'D' as tipo,EMAIL_PARA from MAN_DIC_DIVISOES a inner join MAN_DIC_PISOS b on a.ID_PISO = b.ID inner join MAN_DIC_EDIFICIOS c on b.ID_EDIFICIO = c.ID "
				+ "order by DESCRICAO");

		List<MAN_DIC_PISOS> utz = query.getResultList();
		return utz;

	}
}
