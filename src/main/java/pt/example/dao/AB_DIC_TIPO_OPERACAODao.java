package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.AB_DIC_TIPO_ADICAO;
import pt.example.entity.AB_DIC_TIPO_OPERACAO;
import pt.example.entity.AB_DIC_ZONA;
import pt.example.entity.GER_FORNECEDOR;

public class AB_DIC_TIPO_OPERACAODao extends GenericDaoJpaImpl<AB_DIC_TIPO_OPERACAO, Integer>
		implements GenericDao<AB_DIC_TIPO_OPERACAO, Integer> {
	public AB_DIC_TIPO_OPERACAODao() {
		super(AB_DIC_TIPO_OPERACAO.class);
	}

	public List<AB_DIC_TIPO_OPERACAO> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_TIPO_OPERACAO a where a.INATIVO != 1 ");
		List<AB_DIC_TIPO_OPERACAO> data = query.getResultList();
		return data;

	}
}
