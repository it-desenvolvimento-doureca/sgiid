package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_REFERENCIAS;

public class COM_REFERENCIASDao extends GenericDaoJpaImpl<COM_REFERENCIAS, Integer>
		implements GenericDao<COM_REFERENCIAS, Integer> {
	public COM_REFERENCIASDao() {
		super(COM_REFERENCIAS.class);
	}

	public List<COM_REFERENCIAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a,(select NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) as NOME_UTILIZADOR from COM_REFERENCIAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<COM_REFERENCIAS> data = query.getResultList();
		return data;

	}

	public List<COM_REFERENCIAS> getall() {

		Query query = entityManager.createQuery("Select a from COM_REFERENCIAS a where a.INATIVO != 1 ");
		List<COM_REFERENCIAS> data = query.getResultList();
		return data;

	}

}
