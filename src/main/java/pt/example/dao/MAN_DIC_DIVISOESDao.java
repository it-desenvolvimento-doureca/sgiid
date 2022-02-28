package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_DIC_DIVISOES;

public class MAN_DIC_DIVISOESDao extends GenericDaoJpaImpl<MAN_DIC_DIVISOES, Integer> implements GenericDao<MAN_DIC_DIVISOES, Integer> {
	public MAN_DIC_DIVISOESDao() {
		super(MAN_DIC_DIVISOES.class);
	}

	public List<MAN_DIC_DIVISOES> getall() {

		Query query = entityManager.createQuery("Select a,b.DESCRICAO ,c.DESCRICAO  as DESCRICAO1 from MAN_DIC_DIVISOES a ,MAN_DIC_PISOS b,MAN_DIC_EDIFICIOS c where a.ID_PISO = b.ID AND b.ID_EDIFICIO = c.ID ");

		List<MAN_DIC_DIVISOES> utz = query.getResultList();
		return utz;

	}
}
