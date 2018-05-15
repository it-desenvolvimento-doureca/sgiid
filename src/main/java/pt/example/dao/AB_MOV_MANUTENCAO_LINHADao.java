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
		Query query = entityManager.createQuery("Select a,b,"
				+ "(select c.MEDIDA from AB_DIC_UNIDADE_MEDIDA c where a.ID_UNIDADE1 = c.ID_MEDIDA)  as Medida,  "
				+ "(select distinct d.MANUTENCAONAOPROGRAMADA from AB_DIC_BANHO_ADITIVO d,AB_MOV_MANUTENCAO_CAB x where x.ID_MANUTENCAO_CAB = a.ID_MANUTENCAO_CAB and x.ID_BANHO = d.ID_BANHO and a.ID_ADITIVO = d.ID_ADITIVO)  as NAOP,  "
				+ "(Select SUM(p.CONSUMIR) from AB_MOV_MANUTENCAO_ETIQ p where p.ID_MANUTENCAO_LIN = a.ID_MANUTENCAO_LIN ) as total_etiquetas, "
				+ "(select COUNT(h.ID_MOV_MANU_ETIQUETA) from AB_MOV_MANUTENCAO_ETIQ h where h.ID_MANUTENCAO_LIN = a.ID_MANUTENCAO_LIN and h.CONSUMIR = 0) as vazios "
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

	public int apagar_linhas(Integer id) {

		Query query = entityManager.createNativeQuery(
				"DELETE a FROM AB_MOV_MANUTENCAO_LINHA a inner join AB_MOV_MANUTENCAO_CAB b on a.ID_MANUTENCAO_CAB = b.ID_MANUTENCAO_CAB "
						+ "where (a.VALOR1 = '0' or a.VALOR1 = '' or a.VALOR1 is null ) and (a.VALOR2 = '0' or a.VALOR2 = '' or a.VALOR2 is null) and b.ID_MANUTENCAO   = "
						+ id + " ");
		int utz = query.executeUpdate();
		return utz;

	}
}
