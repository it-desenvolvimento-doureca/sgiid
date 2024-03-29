package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_DIC_NIVEIS_CRITICIDADE;

public class MAN_DIC_NIVEIS_CRITICIDADEDao extends GenericDaoJpaImpl<MAN_DIC_NIVEIS_CRITICIDADE, Integer> implements GenericDao<MAN_DIC_NIVEIS_CRITICIDADE, Integer> {
	public MAN_DIC_NIVEIS_CRITICIDADEDao() {
		super(MAN_DIC_NIVEIS_CRITICIDADE.class);
	}

	public List<MAN_DIC_NIVEIS_CRITICIDADE> getall() {

		Query query = entityManager.createQuery("Select a from MAN_DIC_NIVEIS_CRITICIDADE a where a.ATIVO = 1 ");

		List<MAN_DIC_NIVEIS_CRITICIDADE> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_DIC_NIVEIS_CRITICIDADE> getbynivel(Integer nivel) {

		Query query = entityManager.createQuery("Select a from MAN_DIC_NIVEIS_CRITICIDADE a where a.NIVEL  = :nivel AND a.ATIVO = 1 ");
		query.setParameter("nivel", nivel);
		List<MAN_DIC_NIVEIS_CRITICIDADE> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_DIC_NIVEIS_CRITICIDADE> getbydepartamento(Integer departamento) {

		Query query = entityManager.createNativeQuery("Select b.ID,b.DESCRICAO,b.NIVEL from  MAN_DIC_NIVEIS_CRITICIDADE b inner join MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA a on a.NIVEL = b.NIVEL "
				+ "where a.ID_DEPARTAMENTO  = :departamento AND b.ATIVO = 1 GROUP BY b.ID,b.DESCRICAO,b.NIVEL ");
		query.setParameter("departamento", departamento);
		List<MAN_DIC_NIVEIS_CRITICIDADE> utz = query.getResultList();
		return utz;

	}
}
