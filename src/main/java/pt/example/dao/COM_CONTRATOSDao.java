package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_CONTRATOS;

public class COM_CONTRATOSDao extends GenericDaoJpaImpl<COM_CONTRATOS, Integer>
		implements GenericDao<COM_CONTRATOS, Integer> {
	public COM_CONTRATOSDao() {
		super(COM_CONTRATOS.class);
	}

	public List<COM_CONTRATOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a,(select NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) as NOME_UTILIZADOR from COM_CONTRATOS a where a.ID = :id ");
		query.setParameter("id", id);
		List<COM_CONTRATOS> data = query.getResultList();
		return data;

	}

	public List<COM_CONTRATOS> getall() {

		Query query = entityManager.createQuery("Select a from COM_CONTRATOS a where a.INATIVO != 1 ");
		List<COM_CONTRATOS> data = query.getResultList();
		return data;

	}

}
