package pt.example.dao;

import pt.example.entity.GER_LOG_EVENTOS;

public class GER_LOG_EVENTOSDao extends GenericDaoJpaImpl<GER_LOG_EVENTOS, Integer>
		implements GenericDao<GER_LOG_EVENTOS, Integer> {
	public GER_LOG_EVENTOSDao() {
		super(GER_LOG_EVENTOS.class);
	}

}
