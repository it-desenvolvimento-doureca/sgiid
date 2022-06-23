package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_MANUTENCAO_OPERARIOS;

public class MAN_MOV_MANUTENCAO_OPERARIOSDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_OPERARIOS, Integer>
		implements GenericDao<MAN_MOV_MANUTENCAO_OPERARIOS, Integer> {
	public MAN_MOV_MANUTENCAO_OPERARIOSDao() {
		super(MAN_MOV_MANUTENCAO_OPERARIOS.class);
	}

	public List<MAN_MOV_MANUTENCAO_OPERARIOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_OPERARIOS a ");

		List<MAN_MOV_MANUTENCAO_OPERARIOS> utz = query.getResultList();
		return utz;

	}

	public List<MAN_MOV_MANUTENCAO_OPERARIOS> getbyid(Integer id) {
		Query query = entityManager.createNativeQuery(
				"select ID_MANUTENCAO_OPERARIOS,ID_MANUTENCAO_CAB,ID_OPERARIO,DATA_INICIO,DATA_FIM,TEMP_EXEC,TEMP_PAUSA,TEMP_TOTAL,ESTADO,UTZ_CRIA,a.DATA_CRIA,UTZ_ULT_MODIF,DATA_ULT_MODIF,b.NOME_UTILIZADOR "
						+ "from  MAN_MOV_MANUTENCAO_OPERARIOS a left join  GER_UTILIZADORES b on a.ID_OPERARIO = b.ID_UTILIZADOR where ID_MANUTENCAO_CAB = :id /*and a.ESTADO  not in ('D')*/ order by DATA_INICIO");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_OPERARIOS> data = query.getResultList();
		return data;

	}
}
