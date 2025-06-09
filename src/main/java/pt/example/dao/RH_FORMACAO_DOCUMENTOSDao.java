package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_FORMACAO_DOCUMENTOS;
import pt.example.entity.PA_MOV_FICHEIROS;

public class RH_FORMACAO_DOCUMENTOSDao extends GenericDaoJpaImpl<RH_FORMACAO_DOCUMENTOS, Integer>
		implements GenericDao<RH_FORMACAO_DOCUMENTOS, Integer> {
	public RH_FORMACAO_DOCUMENTOSDao() {
		super(RH_FORMACAO_DOCUMENTOS.class);
	}

	public List<RH_FORMACAO_DOCUMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from RH_FORMACAO_DOCUMENTOS a ");

		List<RH_FORMACAO_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}

	public List<RH_FORMACAO_DOCUMENTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_FORMACAO_DOCUMENTOS a where a.ID = :id");
		query.setParameter("id", id);
		List<RH_FORMACAO_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}
	
	public List<RH_FORMACAO_DOCUMENTOS> getbyid2(Integer id) {

		Query query = entityManager.createQuery("select a,b from RH_FORMACAO_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_FORMACAO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<RH_FORMACAO_DOCUMENTOS> data = query.getResultList();
		return data;

	}
	
	public List<RH_FORMACAO_DOCUMENTOS> getbyid3(Integer id) {

		Query query = entityManager.createQuery("select a.ID,a.TAMANHO,a.NOME,a.CAMINHO,a.DESCRICAO,a.TIPO,a.ID_FORMACAO,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.DATATYPE,a.ORDEM,b from RH_FORMACAO_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_FORMACAO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<RH_FORMACAO_DOCUMENTOS> data = query.getResultList();
		return data;

	}
}
