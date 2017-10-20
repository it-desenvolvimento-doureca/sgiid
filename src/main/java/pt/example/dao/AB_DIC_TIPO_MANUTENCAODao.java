package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_TIPO_ADICAO;
import pt.example.entity.AB_DIC_TIPO_MANUTENCAO;
import pt.example.entity.AB_DIC_ZONA;
import pt.example.entity.GER_FORNECEDOR;

public class AB_DIC_TIPO_MANUTENCAODao extends GenericDaoJpaImpl<AB_DIC_TIPO_MANUTENCAO, Integer>
		implements GenericDao<AB_DIC_TIPO_MANUTENCAO, Integer> {
	public AB_DIC_TIPO_MANUTENCAODao() {
		super(AB_DIC_TIPO_MANUTENCAO.class);
	}

	public List<AB_DIC_TIPO_MANUTENCAO> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_TIPO_MANUTENCAO a where a.INATIVO != 1 ");
		List<AB_DIC_TIPO_MANUTENCAO> data = query.getResultList();
		return data;

	}

}
