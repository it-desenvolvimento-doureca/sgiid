package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_PRODUTOS;

public class PIN_DIC_PRODUTOSDao extends GenericDaoJpaImpl<PIN_DIC_PRODUTOS, Integer>
		implements GenericDao<PIN_DIC_PRODUTOS, Integer> {
	public PIN_DIC_PRODUTOSDao() {
		super(PIN_DIC_PRODUTOS.class);
	}

	public List<PIN_DIC_PRODUTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_PRODUTOS a where a.ID = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_PRODUTOS> getAll() {

		Query query = entityManager.createQuery("Select a,(select b.NOME from PIN_DIC_TIPO_ACABAMENTO b where b.ID = a.ID_TIPO_ACABAMENTO ) from PIN_DIC_PRODUTOS a where a.INATIVO != 1 ");

		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}

	
	public List<PIN_DIC_PRODUTOS> getbyTipoAcabamento(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_PRODUTOS a where a.ID_TIPO_ACABAMENTO = :id ");
		query.setParameter("id", id);
		List<PIN_DIC_PRODUTOS> data = query.getResultList();
		return data;

	}
}
