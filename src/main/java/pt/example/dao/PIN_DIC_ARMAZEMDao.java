package pt.example.dao;

import pt.example.entity.PIN_DIC_ARMAZEM;

public class PIN_DIC_ARMAZEMDao extends GenericDaoJpaImpl<PIN_DIC_ARMAZEM, Integer>
		implements GenericDao<PIN_DIC_ARMAZEM, Integer> {
	public PIN_DIC_ARMAZEMDao() {
		super(PIN_DIC_ARMAZEM.class);
	}
}
