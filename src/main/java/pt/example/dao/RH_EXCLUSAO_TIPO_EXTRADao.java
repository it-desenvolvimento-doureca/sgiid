package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_EXCLUSAO_TIPO_EXTRA;

public class RH_EXCLUSAO_TIPO_EXTRADao extends GenericDaoJpaImpl<RH_EXCLUSAO_TIPO_EXTRA, Integer>
		implements GenericDao<RH_EXCLUSAO_TIPO_EXTRA, Integer> {
	public RH_EXCLUSAO_TIPO_EXTRADao() {
		super(RH_EXCLUSAO_TIPO_EXTRA.class);
	}

	public List<RH_EXCLUSAO_TIPO_EXTRA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_EXCLUSAO_TIPO_EXTRA a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_EXCLUSAO_TIPO_EXTRA> data = query.getResultList();
		return data;

	}

	public List<RH_EXCLUSAO_TIPO_EXTRA> getall() {

		Query query = entityManager.createQuery("Select a from RH_EXCLUSAO_TIPO_EXTRA a ");
		List<RH_EXCLUSAO_TIPO_EXTRA> data = query.getResultList();
		return data;

	}
	
	public List<RH_EXCLUSAO_TIPO_EXTRA> getTiposExtra() {

		Query query = entityManager.createNativeQuery("Select Escalao,Descricao from [Cronos].[dbo].[EspecialidadesEscaloes] where Escalao >=0");
		List<RH_EXCLUSAO_TIPO_EXTRA> data = query.getResultList();
		return data;

	}

}
