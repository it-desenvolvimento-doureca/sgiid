package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_CACIFOS;

public class RH_DIC_CACIFOSDao extends GenericDaoJpaImpl<RH_DIC_CACIFOS, Integer>
		implements GenericDao<RH_DIC_CACIFOS, Integer> {
	public RH_DIC_CACIFOSDao() {
		super(RH_DIC_CACIFOS.class);
	}

	public List<RH_DIC_CACIFOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_DIC_CACIFOS a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_DIC_CACIFOS> data = query.getResultList();
		return data;

	}

	public List<RH_DIC_CACIFOS> getall() {

		Query query = entityManager.createQuery("Select a,(select NOME from RH_FUNCIONARIOS b  where  a.UTILIZADOR = b.COD_FUNCIONARIO ) "
				+ ",(select DESCRICAO from  GER_LOCAIS c  where a.LOCAL = c.ID ),(select DESIGNACAO from RH_DIC_TIPO_CACIFO x  where  x.COD_TIPO = a.TIPO_CACIFO )  from RH_DIC_CACIFOS a where a.INATIVO != 1");
		List<RH_DIC_CACIFOS> data = query.getResultList();
		return data;

	}
	
	public List<RH_DIC_CACIFOS> getall_livre() {

		Query query = entityManager.createQuery("Select a,(select NOME from RH_FUNCIONARIOS b  where  a.UTILIZADOR = b.COD_FUNCIONARIO )  "
				+ ",(select DESCRICAO from  GER_LOCAIS c  where a.LOCAL = c.ID ),(select DESIGNACAO from RH_DIC_TIPO_CACIFO x  where  x.COD_TIPO = a.TIPO_CACIFO ) from RH_DIC_CACIFOS a where a.INATIVO != 1 and a.UTILIZADOR = null");
		List<RH_DIC_CACIFOS> data = query.getResultList();
		return data;

	}
}
