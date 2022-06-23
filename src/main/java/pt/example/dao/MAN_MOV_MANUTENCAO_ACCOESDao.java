package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_MOV_MANUTENCAO_ACCOES;

public class MAN_MOV_MANUTENCAO_ACCOESDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_ACCOES, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_ACCOES, Integer> {
	public MAN_MOV_MANUTENCAO_ACCOESDao() {
		super(MAN_MOV_MANUTENCAO_ACCOES.class);
	}

	public List<MAN_MOV_MANUTENCAO_ACCOES> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_ACCOES a ");

		List<MAN_MOV_MANUTENCAO_ACCOES> utz = query.getResultList();
		return utz;

	}
	
	
	public List<MAN_MOV_MANUTENCAO_ACCOES> getbyId(Integer id) {
		Query query = entityManager.createNativeQuery(
				"select  ID_MANUTENCAO_ACOES,ID_MANUTENCAO_CAB,ID_ACAO,REALIZADA,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF, b.DESCRICAO_PT,a.TEMPO "
						+ "from MAN_MOV_MANUTENCAO_ACCOES a inner join GT_DIC_TAREFAS b on a.ID_ACAO = b.ID where a.ID_MANUTENCAO_CAB = :id");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_ACCOES> data = query.getResultList();
		return data;

	}
}
