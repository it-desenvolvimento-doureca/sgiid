package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_MOV_MANUTENCAO_COMPONENTES;
import pt.example.entity.MAN_MOV_MANUTENCAO_EQUIPAMENTOS;

public class MAN_MOV_MANUTENCAO_COMPONENTESDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_COMPONENTES, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_COMPONENTES, Integer> {
	public MAN_MOV_MANUTENCAO_COMPONENTESDao() {
		super(MAN_MOV_MANUTENCAO_COMPONENTES.class);
	}

	public List<MAN_MOV_MANUTENCAO_COMPONENTES> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_COMPONENTES a ");

		List<MAN_MOV_MANUTENCAO_COMPONENTES> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_COMPONENTES> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a from MAN_MOV_MANUTENCAO_COMPONENTES a "
				+ "where  a.ID_MANUTENCAO = :id ");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_COMPONENTES> data = query.getResultList();
		return data;

	}
	
	public List<MAN_MOV_MANUTENCAO_COMPONENTES> getbyid2(Integer id) {

		Query query = entityManager.createNativeQuery("select a.ID,ID_MANUTENCAO,a.REFERENCIA,a.DESC_REFERENCIA,a.QUANTIDADE,a.ANEXO,b.STOCK_TOTAL "
				+ " from MAN_MOV_MANUTENCAO_COMPONENTES a left join (SELECT PROREF, sum(lotqte) STOCK_TOTAL FROM  SILVER.dbo.stodet GROUP BY proref ) b on b.proref = a.REFERENCIA where  a.ID_MANUTENCAO = :id ");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_COMPONENTES> data = query.getResultList();
		return data;

	}
}
