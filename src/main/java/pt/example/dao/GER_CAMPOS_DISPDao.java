package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_ANALISES;
import pt.example.entity.GER_CAMPOS_DISP;

public class GER_CAMPOS_DISPDao extends GenericDaoJpaImpl<GER_CAMPOS_DISP, Integer>
		implements GenericDao<GER_CAMPOS_DISP, Integer> {
	public GER_CAMPOS_DISPDao() {
		super(GER_CAMPOS_DISP.class);
	}

	public List<GER_CAMPOS_DISP> getbyId(Integer id) {
		Query query = entityManager.createQuery("select a from GER_CAMPOS_DISP a where a.ID_EVENTO_CONF = :id order by a.DESCRICAO_CAMPO");
		query.setParameter("id", id);
		List<GER_CAMPOS_DISP> data = query.getResultList();
		return data;

	}

}
