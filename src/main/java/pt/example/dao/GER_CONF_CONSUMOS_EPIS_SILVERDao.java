package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.GER_CONF_CONSUMOS_EPIS_SILVER;

public class GER_CONF_CONSUMOS_EPIS_SILVERDao
		extends GenericDaoJpaImpl<GER_CONF_CONSUMOS_EPIS_SILVER, Integer>
		implements GenericDao<GER_CONF_CONSUMOS_EPIS_SILVER, Integer> {

	public GER_CONF_CONSUMOS_EPIS_SILVERDao() {
		super(GER_CONF_CONSUMOS_EPIS_SILVER.class);
	}

	public List<GER_CONF_CONSUMOS_EPIS_SILVER> getall() {
		Query query = entityManager.createQuery(
			"Select a from GER_CONF_CONSUMOS_EPIS_SILVER a where a.ATIVO = 1");
		return query.getResultList();
	}

	/** A linha de configuração em uso, ou null se ainda não houver. */
	public GER_CONF_CONSUMOS_EPIS_SILVER getconf() {
		List<GER_CONF_CONSUMOS_EPIS_SILVER> l = getall();
		return l.isEmpty() ? null : l.get(0);
	}

	public GER_CONF_CONSUMOS_EPIS_SILVER update(GER_CONF_CONSUMOS_EPIS_SILVER e) {
		return entityManager.merge(e);
	}
}
