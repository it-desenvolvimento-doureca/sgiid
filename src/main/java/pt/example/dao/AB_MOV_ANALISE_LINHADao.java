package pt.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_MOV_ANALISE_LINHA;

public class AB_MOV_ANALISE_LINHADao extends GenericDaoJpaImpl<AB_MOV_ANALISE_LINHA, Integer>
		implements GenericDao<AB_MOV_ANALISE_LINHA, Integer> {
	public AB_MOV_ANALISE_LINHADao() {
		super(AB_MOV_ANALISE_LINHA.class);
	}

	public List<AB_MOV_ANALISE_LINHA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_MOV_ANALISE_LINHA a where a.ID_ANALISE_LIN = :id ");
		query.setParameter("id", id);
		List<AB_MOV_ANALISE_LINHA> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_ANALISE_LINHA> getbyid_analise_comp(Integer id, Integer id_banho, ArrayList<Integer> data2) {

		Query query = entityManager.createQuery("Select a "
				/*
				 * +
				 * ", (select c.LIMITE_AMARELO_INF from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_banho and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_AMARELO_INF, "
				 * +
				 * "(select c.LIMITE_AMARELO_SUP from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_banho and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_AMARELO_SUP, "
				 * +
				 * "(select c.LIMITE_VERDE_INF from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_banho and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_VERDE_INF, "
				 * +
				 * " (select c.LIMITE_VERDE_SUP from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_banho and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_VERDE_SUP "
				 */
				+ " from AB_MOV_ANALISE_LINHA a where a.ID_ANALISE = :id and a.ID_COMPONENTE in (:data2)");
		query.setParameter("id", id);
		query.setParameter("data2", data2);
		// query.setParameter("id_banho", id_banho);
		List<AB_MOV_ANALISE_LINHA> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_ANALISE_LINHA> getbyid_analise_comp2(Integer id, Integer id_banho, ArrayList<Integer> data2) {

		Query query = entityManager.createQuery("Select a "
				+ ", (select c.LIMITE_AMARELO_INF from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = b.ID_BANHO and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= b.DATA_ANALISE or c.DATA_FIM is null)  and c.DATA_INICIO <= b.DATA_ANALISE) as LIMITE_AMARELO_INF, "
				+ "(select c.LIMITE_AMARELO_SUP from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = b.ID_BANHO and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= b.DATA_ANALISE or c.DATA_FIM is null)  and c.DATA_INICIO <= b.DATA_ANALISE) as LIMITE_AMARELO_SUP, "
				+ "(select c.LIMITE_VERDE_INF from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = b.ID_BANHO and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= b.DATA_ANALISE or c.DATA_FIM is null)  and c.DATA_INICIO <= b.DATA_ANALISE) as LIMITE_VERDE_INF, "
				+ " (select c.LIMITE_VERDE_SUP from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = b.ID_BANHO and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= b.DATA_ANALISE or c.DATA_FIM is null)  and c.DATA_INICIO <= b.DATA_ANALISE) as LIMITE_VERDE_SUP, "
				+ "(select c.ID_BANHO_COMP from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = b.ID_BANHO and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= b.DATA_ANALISE or c.DATA_FIM is null)  and c.DATA_INICIO <= b.DATA_ANALISE) as ATIVO_ID "
				+ " from AB_MOV_ANALISE_LINHA a, AB_MOV_ANALISE b where a.ID_ANALISE = :id and a.ID_COMPONENTE in (:data2) and a.ID_ANALISE = b.ID_ANALISE");
		query.setParameter("id", id);
		query.setParameter("data2", data2);
		// query.setParameter("id_banho", id_banho);
		List<AB_MOV_ANALISE_LINHA> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_ANALISE_LINHA> getbyid_analise(Integer id, Integer id_banho) {

		Query query = entityManager.createQuery("Select a,b, "
				/*
				 * +
				 * " (select c.LIMITE_AMARELO_INF from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_banho and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_AMARELO_INF, "
				 * +
				 * "(select c.LIMITE_AMARELO_SUP from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_banho and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_AMARELO_SUP, "
				 * +
				 * "(select c.LIMITE_VERDE_INF from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_banho and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_VERDE_INF, "
				 * +
				 * " (select c.LIMITE_VERDE_SUP from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_banho and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_VERDE_SUP, "
				 */
				+ " (select d.MEDIDA from AB_DIC_UNIDADE_MEDIDA d, AB_DIC_BANHO_COMPONENTE f where d.ID_MEDIDA = f.ID_UNIDADE_COMPONENTE and a.ID_COMPONENTE = f.ID_COMPONENTE and  f.ID_BANHO = :id_banho and f.INATIVO != 1 and (f.DATA_FIM >= GETDATE() or f.DATA_FIM is null)  and f.DATA_INICIO <= GETDATE()) as Medida "
				+ "from AB_MOV_ANALISE_LINHA a,AB_DIC_COMPONENTE b " + "where a.ID_COMPONENTE = b.ID_COMPONENTE and "
				+ "a.ID_ANALISE = :id and a.ID_COMPONENTE = b.ID_COMPONENTE  "
				+ "order by b.DATA_CRIA,b.ID_COMPONENTE");
		query.setParameter("id", id);
		query.setParameter("id_banho", id_banho);
		List<AB_MOV_ANALISE_LINHA> data = query.getResultList();
		return data;

	}

}
