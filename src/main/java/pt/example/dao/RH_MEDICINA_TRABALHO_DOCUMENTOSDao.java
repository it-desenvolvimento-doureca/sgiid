package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_MEDICINA_TRABALHO_DOCUMENTOS;
import pt.example.entity.PA_MOV_FICHEIROS;

public class RH_MEDICINA_TRABALHO_DOCUMENTOSDao extends GenericDaoJpaImpl<RH_MEDICINA_TRABALHO_DOCUMENTOS, Integer>
		implements GenericDao<RH_MEDICINA_TRABALHO_DOCUMENTOS, Integer> {
	public RH_MEDICINA_TRABALHO_DOCUMENTOSDao() {
		super(RH_MEDICINA_TRABALHO_DOCUMENTOS.class);
	}

	public List<RH_MEDICINA_TRABALHO_DOCUMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from RH_MEDICINA_TRABALHO_DOCUMENTOS a ");

		List<RH_MEDICINA_TRABALHO_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}

	public List<RH_MEDICINA_TRABALHO_DOCUMENTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_MEDICINA_TRABALHO_DOCUMENTOS a where a.ID = :id");
		query.setParameter("id", id);
		List<RH_MEDICINA_TRABALHO_DOCUMENTOS> utz = query.getResultList();
		return utz;

	}
	
	public List<RH_MEDICINA_TRABALHO_DOCUMENTOS> getbyid2(Integer id) {

		Query query = entityManager.createQuery("select a,b from RH_MEDICINA_TRABALHO_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_MEDICINA_TRABALHO = :id order by a.DATA_CRIA");
		query.setParameter("id", id);
		List<RH_MEDICINA_TRABALHO_DOCUMENTOS> data = query.getResultList();
		return data;

	}
	
	public List<RH_MEDICINA_TRABALHO_DOCUMENTOS> getbyid3(Integer id,Integer userId) {

		Query query = entityManager.createQuery("select a.ID,a.TAMANHO,a.NOME,a.CAMINHO,a.DESCRICAO,a.TIPO,a.ID_MEDICINA_TRABALHO,a.UTZ_CRIA,a.DATA_CRIA,a.UTZ_ULT_MODIF,a.DATA_ULT_MODIF,a.DATATYPE,a.ORDEM,a.TIPO_DOCUMENTO,b from RH_MEDICINA_TRABALHO_DOCUMENTOS a, GER_UTILIZADORES b "
				+ "where a.UTZ_CRIA = b.ID_UTILIZADOR and a.ID_MEDICINA_TRABALHO = :id "
				+ "and a.TIPO_DOCUMENTO in (Select distinct a.ID from RH_DIC_TIPOS_DOCUMENTO a inner join RH_DIC_TIPOS_DOCUMENTO_UTZ b on a.ID = b.ID_TIPO_DOCUMENTO "
				+ "where a.INATIVO != 1 and (b.ID_UTZ = :userId or (select ADMIN from GER_UTILIZADORES c where c.ID_UTILIZADOR = :userId) = 1)) "
				+ "order by a.DATA_CRIA");
		query.setParameter("id", id);
		query.setParameter("userId", userId);
		List<RH_MEDICINA_TRABALHO_DOCUMENTOS> data = query.getResultList();
		return data;

	}
}
