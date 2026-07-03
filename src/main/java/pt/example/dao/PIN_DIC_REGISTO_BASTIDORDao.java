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

	public List<PIN_DIC_REGISTO_BASTIDOR> getanalise(Integer id_produto, String ini, String fim) {

		String select_base = "select a.ID, "
				+ "(select b.NOME_PROJETO from PIN_MOV_RECEITAS b where b.ID = a.ID_RECEITA AND b.VERSAO = a.VERSAO) as NOME_PROJETO, "
				+ "a.DATA, a.HORA, "
				+ "(select c.NOME_CABINE from PIN_DIC_CABINES c where c.ID = %s) as NOME_CABINE, "
				+ "%s as TEMPERATURA, %s as HUMIDADE, "
				+ "r.TEMPERATURA_MIN, r.TEMPERATURA_MAX, r.HUMIDADE_MIN, r.HUMIDADE_MAX, %s as ID_CABINE "
				+ "from PIN_DIC_REGISTO_BASTIDOR a "
				+ "left join PIN_MOV_RECEITAS r on r.ID = a.ID_RECEITA and r.VERSAO = a.VERSAO "
				+ "where a.ATIVO = 1 and %s is not null "
				+ "and (:id_produto = 0 or exists (select 1 from PIN_MOV_RECEITAS_LINHAS l "
				+ "     where l.ID_RECEITA = a.ID_RECEITA and l.VERSAO = a.VERSAO and l.ID_REFERENCIA_A = :id_produto)) "
				+ "and a.DATA >= CAST(:ini as date) and a.DATA <= CAST(:fim as date) ";

		String sql = "select * from ( "
				+ String.format(select_base, "a.ID_CABINE", "a.TEMPERATURA", "a.HUMIDADE", "a.ID_CABINE", "a.ID_CABINE")
				+ " union all "
				+ String.format(select_base, "a.ID_CABINE_2", "a.TEMPERATURA_2", "a.HUMIDADE_2", "a.ID_CABINE_2", "a.ID_CABINE_2")
				+ " union all "
				+ String.format(select_base, "a.ID_CABINE_3", "a.TEMPERATURA_3", "a.HUMIDADE_3", "a.ID_CABINE_3", "a.ID_CABINE_3")
				+ ") x order by x.ID_CABINE, x.DATA, x.HORA";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id_produto", id_produto);
		query.setParameter("ini", ini);
		query.setParameter("fim", fim);
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
