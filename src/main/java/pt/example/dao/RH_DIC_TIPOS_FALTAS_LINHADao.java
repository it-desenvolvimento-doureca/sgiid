package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_TIPOS_FALTAS_LINHA;

public class RH_DIC_TIPOS_FALTAS_LINHADao extends GenericDaoJpaImpl<RH_DIC_TIPOS_FALTAS_LINHA, Integer>
		implements GenericDao<RH_DIC_TIPOS_FALTAS_LINHA, Integer> {

	public RH_DIC_TIPOS_FALTAS_LINHADao() {
		super(RH_DIC_TIPOS_FALTAS_LINHA.class);
	}

	public List<RH_DIC_TIPOS_FALTAS_LINHA> getAll() {
		Query query = entityManager.createQuery("SELECT l FROM RH_DIC_TIPOS_FALTAS_LINHA l");
		return query.getResultList();
	}

	public List<Object[]> getFaltasAll() {
		Query query = entityManager.createNativeQuery(
				"select A.CodigoFalta AS COD_FALTA,  A.Descricao AS DESC_FALTA  from Cronos.dbo.Faltas A "
						+ " WHERE NOT EXISTS ( SELECT 1 FROM RH_DIC_TIPOS_FALTAS_LINHA B  WHERE B.COD_FALTA = A.CodigoFalta ) And Absentismo = 1");
		return query.getResultList();
	}

}