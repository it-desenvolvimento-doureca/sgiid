package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_COMPONENTE_DOCUMENTOS;

public class AB_DIC_COMPONENTE_DOCUMENTOSDao extends GenericDaoJpaImpl<AB_DIC_COMPONENTE_DOCUMENTOS, Integer>
		implements GenericDao<AB_DIC_COMPONENTE_DOCUMENTOS, Integer> {
	public AB_DIC_COMPONENTE_DOCUMENTOSDao() {
		super(AB_DIC_COMPONENTE_DOCUMENTOS.class);
	}

	public List<AB_DIC_COMPONENTE_DOCUMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from AB_DIC_COMPONENTE_DOCUMENTOS a ");

		List<AB_DIC_COMPONENTE_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}

	public List<AB_DIC_COMPONENTE_DOCUMENTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_COMPONENTE_DOCUMENTOS a where a.ID = :id");
		query.setParameter("id", id);
		List<AB_DIC_COMPONENTE_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}
	
	public List<AB_DIC_COMPONENTE_DOCUMENTOS> getbyid2(Integer id) {

		Query query = entityManager.createQuery("select a,b from AB_DIC_COMPONENTE_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_COMPONENTE = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<AB_DIC_COMPONENTE_DOCUMENTOS> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_COMPONENTE_DOCUMENTOS> getbyid3(Integer id) {

		Query query = entityManager.createQuery("select a.ID,a.TAMANHO,a.NOME,a.CAMINHO,a.DESCRICAO,a.TIPO,a.ID_COMPONENTE,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.DATATYPE,a.ORDEM,b from AB_DIC_COMPONENTE_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_COMPONENTE = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<AB_DIC_COMPONENTE_DOCUMENTOS> data = query.getResultList();
		return data;

	}
}
