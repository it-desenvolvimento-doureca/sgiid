package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO;

public class MAN_DIC_TIPO_CLASSIFICACAO_PEDIDODao
		extends GenericDaoJpaImpl<MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO, Integer>
		implements GenericDao<MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO, Integer> {

	public MAN_DIC_TIPO_CLASSIFICACAO_PEDIDODao() {
		super(MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO.class);
	}

	public List<MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO> getall() {
		Query query = entityManager.createQuery(
				"Select a from MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO a where a.ATIVO = true order by a.ID");
		return query.getResultList();
	}

	public List<MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"select a from MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}
}
