package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PLANEAMENTO_CAB;

public class PLANEAMENTO_CABDao extends GenericDaoJpaImpl<PLANEAMENTO_CAB, Integer>
		implements GenericDao<PLANEAMENTO_CAB, Integer> {
	public PLANEAMENTO_CABDao() {
		super(PLANEAMENTO_CAB.class);
	}

	public List<PLANEAMENTO_CAB> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PLANEAMENTO_CAB a where a.ID_PLANEAMENTO = :id ");
		query.setParameter("id", id);
		List<PLANEAMENTO_CAB> data = query.getResultList();
		return data;

	}

	public List<PLANEAMENTO_CAB> getall() {

		Query query = entityManager.createQuery(
				"Select a,(select COR from AB_DIC_LINHA WHERE ID_LINHA  = a.LINHA),(select NOME_LINHA from AB_DIC_LINHA WHERE ID_LINHA  = a.LINHA) as NOME_LINHA from PLANEAMENTO_CAB a order by ano desc,semana desc ");
		List<PLANEAMENTO_CAB> data = query.getResultList();
		return data;

	}

	public List<PLANEAMENTO_CAB> verificaseexiste(Integer id, Integer ano, Integer semana, Integer linha) {
		Query query = entityManager.createNativeQuery(
				"select ID_PLANEAMENTO from PLANEAMENTO_CAB where ANO = " + ano + " AND SEMANA = " + semana
						+ " AND LINHA = " + linha + " AND INATIVO != 1 AND ID_PLANEAMENTO not in (" + id + ")");
		List<PLANEAMENTO_CAB> data = query.getResultList();
		return data;

	}

}
