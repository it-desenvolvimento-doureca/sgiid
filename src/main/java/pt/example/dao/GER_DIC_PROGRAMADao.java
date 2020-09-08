package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_DIC_PROGRAMA;

public class GER_DIC_PROGRAMADao extends GenericDaoJpaImpl<GER_DIC_PROGRAMA, Integer>
		implements GenericDao<GER_DIC_PROGRAMA, Integer> {
	public GER_DIC_PROGRAMADao() {
		super(GER_DIC_PROGRAMA.class);
	}

	public List<GER_DIC_PROGRAMA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a  from GER_DIC_PROGRAMA a where a.ID_PROGRAMA = :id ");
		query.setParameter("id", id);
		List<GER_DIC_PROGRAMA> data = query.getResultList();
		return data;

	}

	public List<GER_DIC_PROGRAMA> getall() {

		Query query = entityManager.createQuery("Select a,(select b.NOME from GER_DIC_VEICULO b where a.ID_VEICULO = b.ID_VEICULO)  as VEICULO , "
				+ "(select c.NOME from GER_DIC_OEM c,GER_DIC_VEICULO cb where c.ID_OEM = cb.ID_OEM and cb.ID_VEICULO = a.ID_VEICULO)  as OEM  from GER_DIC_PROGRAMA a");
		List<GER_DIC_PROGRAMA> data = query.getResultList();
		return data;

	}

}
