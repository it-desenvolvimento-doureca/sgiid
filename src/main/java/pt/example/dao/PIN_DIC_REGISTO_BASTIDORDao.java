package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_REGISTO_BASTIDOR;

public class PIN_DIC_REGISTO_BASTIDORDao extends GenericDaoJpaImpl<PIN_DIC_REGISTO_BASTIDOR, Integer>
		implements GenericDao<PIN_DIC_REGISTO_BASTIDOR, Integer> {
	public PIN_DIC_REGISTO_BASTIDORDao() {
		super(PIN_DIC_REGISTO_BASTIDOR.class);
	}

	public List<PIN_DIC_REGISTO_BASTIDOR> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from PIN_DIC_REGISTO_BASTIDOR a where a.ID = :id and a.ATIVO = 1");
		query.setParameter("id", id);
		List<PIN_DIC_REGISTO_BASTIDOR> data = query.getResultList();
		return data;
	}

	public List<PIN_DIC_REGISTO_BASTIDOR> getall() {
		Query query = entityManager.createQuery("Select a,(select b.NOME_PROJETO from PIN_MOV_RECEITAS b where b.ID = a.ID_RECEITA AND b.VERSAO = a.VERSAO) as NOME_PROJETO, "
				+ "(select c.NOME_CABINE from PIN_DIC_CABINES c where c.ID = a.ID_CABINE) as NOME_CABINE,"
				+ "(select c.NOME_CABINE from PIN_DIC_CABINES c where c.ID = a.ID_CABINE_2) as NOME_CABINE2, "
				+ " (select c.NOME_CABINE from PIN_DIC_CABINES c where c.ID = a.ID_CABINE_3) as NOME_CABINE3 "
				+ "from PIN_DIC_REGISTO_BASTIDOR a where a.ATIVO = 1 order by ID desc ");
		List<PIN_DIC_REGISTO_BASTIDOR> data = query.getResultList();
		return data;
	}

}
