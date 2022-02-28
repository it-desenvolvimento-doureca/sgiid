package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_MANUTENCAO_DOCUMENTOS;
import pt.example.entity.PA_MOV_FICHEIROS;

public class MAN_MOV_MANUTENCAO_DOCUMENTOSDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_DOCUMENTOS, Integer>
		implements GenericDao<MAN_MOV_MANUTENCAO_DOCUMENTOS, Integer> {
	public MAN_MOV_MANUTENCAO_DOCUMENTOSDao() {
		super(MAN_MOV_MANUTENCAO_DOCUMENTOS.class);
	}

	public List<MAN_MOV_MANUTENCAO_DOCUMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_DOCUMENTOS a ");

		List<MAN_MOV_MANUTENCAO_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}

	public List<MAN_MOV_MANUTENCAO_DOCUMENTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_DOCUMENTOS a where a.ID = :id");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_DOCUMENTOS> getbyidEquipamento(Integer id) {

		Query query = entityManager.createQuery("select a,b from MAN_MOV_MANUTENCAO_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_MANUTENCAO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_DOCUMENTOS> data = query.getResultList();
		return data;

	}
	
	public List<MAN_MOV_MANUTENCAO_DOCUMENTOS> getbyidEquipamento2(Integer id) {

		Query query = entityManager.createQuery("select a.ID,a.TAMANHO,a.NOME,a.CAMINHO,a.DESCRICAO,a.TIPO,a.ID_MANUTENCAO,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.DATATYPE,a.ORDEM,b from MAN_MOV_MANUTENCAO_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_MANUTENCAO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_DOCUMENTOS> data = query.getResultList();
		return data;

	}
}
