package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_BANHO;
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

	public List<AB_MOV_ANALISE_LINHA> getbyid_analise(Integer id, Integer id_analise) {

		Query query = entityManager
				.createQuery("Select a,b, "
						+ " (select c.LIMITE_AMARELO_INF from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_analise and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_AMARELO_INF, "
						+ "(select c.LIMITE_AMARELO_SUP from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_analise and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_AMARELO_SUP, "
						+ "(select c.LIMITE_VERDE_INF from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_analise and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_VERDE_INF, "
						+ " (select c.LIMITE_VERDE_SUP from AB_DIC_BANHO_COMPONENTE c where c.ID_BANHO = :id_analise and c.ID_COMPONENTE=a.ID_COMPONENTE and (c.DATA_FIM >= GETDATE() or c.DATA_FIM is null)  and c.DATA_INICIO <= GETDATE()) as LIMITE_VERDE_SUP "
						+ "from AB_MOV_ANALISE_LINHA a,AB_DIC_COMPONENTE b "
						+ "where a.ID_COMPONENTE = b.ID_COMPONENTE and "
						+ "a.ID_ANALISE = :id and a.ID_COMPONENTE = b.ID_COMPONENTE  "
						+ "order by b.DATA_CRIA,b.ID_COMPONENTE");
		query.setParameter("id", id);
		query.setParameter("id_analise", id_analise);
		List<AB_MOV_ANALISE_LINHA> data = query.getResultList();
		return data;

	}

}
