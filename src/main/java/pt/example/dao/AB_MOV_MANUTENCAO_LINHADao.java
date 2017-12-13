package pt.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_MOV_MANUTENCAO_LINHA;

public class AB_MOV_MANUTENCAO_LINHADao extends GenericDaoJpaImpl<AB_MOV_MANUTENCAO_LINHA, Integer>
		implements GenericDao<AB_MOV_MANUTENCAO_LINHA, Integer> {
	public AB_MOV_MANUTENCAO_LINHADao() {
		super(AB_MOV_MANUTENCAO_LINHA.class);
	}

	public List<AB_MOV_MANUTENCAO_LINHA> getbyidmanutencaocab(Integer id) {
		Query query = entityManager.createQuery(
				"Select a,b,"
				+ "(select c.MEDIDA from AB_DIC_UNIDADE_MEDIDA c where a.ID_UNIDADE1 = c.ID_MEDIDA)  as Medida  "
				+ "from AB_MOV_MANUTENCAO_LINHA a, AB_DIC_COMPONENTE b where a.ID_MANUTENCAO_CAB = :id and a.ID_ADITIVO = b.ID_COMPONENTE order by a.ID_ADITIVO");
		query.setParameter("id", id);
		List<AB_MOV_MANUTENCAO_LINHA> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO_LINHA> getbyid(Integer id) {
		Query query = entityManager
				.createQuery("Select a from AB_MOV_MANUTENCAO_LINHA a where a.ID_MANUTENCAO_LIN = :id ");
		query.setParameter("id", id);
		List<AB_MOV_MANUTENCAO_LINHA> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_MANUTENCAO_LINHA> getbyid_manut_comp(Integer id, ArrayList<Integer> data2) {

		Query query = entityManager.createQuery("Select a "
				+ " from AB_MOV_MANUTENCAO_LINHA a where a.ID_MANUTENCAO_CAB = :id and a.ID_ADITIVO in (:data2)");
		query.setParameter("id", id);
		query.setParameter("data2", data2);
		List<AB_MOV_MANUTENCAO_LINHA> data = query.getResultList();
		return data;

	}
}
