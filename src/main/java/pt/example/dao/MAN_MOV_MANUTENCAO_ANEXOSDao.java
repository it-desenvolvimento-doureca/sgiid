package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_MANUTENCAO_ANEXOS; 
import pt.example.entity.PA_MOV_FICHEIROS;

public class MAN_MOV_MANUTENCAO_ANEXOSDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_ANEXOS, Integer>
		implements GenericDao<MAN_MOV_MANUTENCAO_ANEXOS, Integer> {
	public MAN_MOV_MANUTENCAO_ANEXOSDao() {
		super(MAN_MOV_MANUTENCAO_ANEXOS.class);
	}

	public List<MAN_MOV_MANUTENCAO_ANEXOS> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_ANEXOS a ");

		List<MAN_MOV_MANUTENCAO_ANEXOS> utz = query.getResultList();
		return utz;

	}

	public List<MAN_MOV_MANUTENCAO_ANEXOS> getbyidanexo(Integer id) {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_ANEXOS a where a.ID = :id");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_ANEXOS> utz = query.getResultList();
		return utz;

	}
	
		
	public List<MAN_MOV_MANUTENCAO_ANEXOS> getbyid(Integer id,String separador) {

		Query query = entityManager.createQuery("select a,b from MAN_MOV_MANUTENCAO_ANEXOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_LINHA_SEPARADOR = :id and a.SEPARADOR = :separador order by a.DATA_CRIA");
		query.setParameter("id", id);
		query.setParameter("separador", separador);
		List<MAN_MOV_MANUTENCAO_ANEXOS> data = query.getResultList();
		return data;

	}
	
	
	public List<MAN_MOV_MANUTENCAO_ANEXOS> getbyid2(Integer id,String separador) {

		Query query = entityManager.createQuery("select a.ID,a.TAMANHO,a.NOME,a.CAMINHO,a.DESCRICAO,a.TIPO,a.ID_MANUTENCAO,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.DATATYPE,b from MAN_MOV_MANUTENCAO_ANEXOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_LINHA_SEPARADOR = :id and a.SEPARADOR = :separador order by a.DATA_CRIA");
		query.setParameter("id", id);
		query.setParameter("separador", separador);
		List<MAN_MOV_MANUTENCAO_ANEXOS> data = query.getResultList();
		return data;

	}
	
	public List<MAN_MOV_MANUTENCAO_ANEXOS> getbyid3(Integer id,String separador) {

		Query query = entityManager.createQuery("select a.ID,a.TAMANHO,a.NOME,a.CAMINHO,a.DESCRICAO,a.TIPO,a.ID_MANUTENCAO,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.DATATYPE,b from MAN_MOV_MANUTENCAO_ANEXOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_MANUTENCAO = :id and a.SEPARADOR = :separador order by a.DATA_CRIA");
		query.setParameter("id", id);
		query.setParameter("separador", separador);
		List<MAN_MOV_MANUTENCAO_ANEXOS> data = query.getResultList();
		return data;

	}
	
}
