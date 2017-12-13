package pt.example.dao;

import pt.example.entity.GER_ARMAZEM;

public class GER_ARMAZEMDao extends GenericDaoJpaImpl<GER_ARMAZEM, Integer>
		implements GenericDao<GER_ARMAZEM, Integer> {
	public GER_ARMAZEMDao() {
		super(GER_ARMAZEM.class);
	}
}
