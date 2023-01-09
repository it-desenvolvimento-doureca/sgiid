package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_DIC_TIPOLOGIA_AVARIA;

public class MAN_DIC_TIPOLOGIA_AVARIADao extends GenericDaoJpaImpl<MAN_DIC_TIPOLOGIA_AVARIA, Integer> implements GenericDao<MAN_DIC_TIPOLOGIA_AVARIA, Integer> {
	public MAN_DIC_TIPOLOGIA_AVARIADao() {
		super(MAN_DIC_TIPOLOGIA_AVARIA.class);
	}

	public List<MAN_DIC_TIPOLOGIA_AVARIA> getall() {

		Query query = entityManager.createQuery("Select a from MAN_DIC_TIPOLOGIA_AVARIA a where a.ATIVO = 1 ");

		List<MAN_DIC_TIPOLOGIA_AVARIA> utz = query.getResultList();
		return utz;

	}
	

}
