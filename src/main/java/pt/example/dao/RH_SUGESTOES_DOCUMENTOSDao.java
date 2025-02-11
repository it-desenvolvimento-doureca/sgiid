package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_SUGESTOES_DOCUMENTOS;
import pt.example.entity.PA_MOV_FICHEIROS;

public class RH_SUGESTOES_DOCUMENTOSDao extends GenericDaoJpaImpl<RH_SUGESTOES_DOCUMENTOS, Integer>
		implements GenericDao<RH_SUGESTOES_DOCUMENTOS, Integer> {
	public RH_SUGESTOES_DOCUMENTOSDao() {
		super(RH_SUGESTOES_DOCUMENTOS.class);
	}

	public List<RH_SUGESTOES_DOCUMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from RH_SUGESTOES_DOCUMENTOS a ");

		List<RH_SUGESTOES_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}

	public List<RH_SUGESTOES_DOCUMENTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_SUGESTOES_DOCUMENTOS a where a.ID = :id");
		query.setParameter("id", id);
		List<RH_SUGESTOES_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}
	
	public List<RH_SUGESTOES_DOCUMENTOS> getbyidFicheiro(Integer id) {

		Query query = entityManager.createQuery("select a,b from RH_SUGESTOES_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_SUGESTAO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<RH_SUGESTOES_DOCUMENTOS> data = query.getResultList();
		return data;

	}
	
	public List<RH_SUGESTOES_DOCUMENTOS> getbyidFicheiro2(Integer id) {

		Query query = entityManager.createQuery("select a.ID,a.TAMANHO,a.NOME,a.CAMINHO,a.DESCRICAO,a.TIPO,a.ID_SUGESTAO,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.DATATYPE,a.ORDEM,b from RH_SUGESTOES_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_SUGESTAO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<RH_SUGESTOES_DOCUMENTOS> data = query.getResultList();
		return data;

	}
}
