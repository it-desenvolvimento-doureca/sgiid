package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.DOC_FICHA_DOCUMENTOS;

public class DOC_FICHA_DOCUMENTOSDao extends GenericDaoJpaImpl<DOC_FICHA_DOCUMENTOS, Integer>
		implements GenericDao<DOC_FICHA_DOCUMENTOS, Integer> {
	public DOC_FICHA_DOCUMENTOSDao() {
		super(DOC_FICHA_DOCUMENTOS.class);
	}

	public List<DOC_FICHA_DOCUMENTOS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from DOC_FICHA_DOCUMENTOS a where a.ID = :id ");
		query.setParameter("id", id);
		List<DOC_FICHA_DOCUMENTOS> data = query.getResultList();
		return data;

	}
	
	public List<DOC_FICHA_DOCUMENTOS> getbylocalizacao(Integer id) {

		Query query = entityManager.createQuery("Select a from DOC_FICHA_DOCUMENTOS a where a.ID_CAMINHO = :id ");
		query.setParameter("id", id);
		List<DOC_FICHA_DOCUMENTOS> data = query.getResultList();
		return data;

	}

	public List<DOC_FICHA_DOCUMENTOS> getall() {

		Query query = entityManager.createQuery("Select a from DOC_FICHA_DOCUMENTOS a where a.INATIVO != 1 ");
		List<DOC_FICHA_DOCUMENTOS> data = query.getResultList();
		return data;

	}
	
	public List<DOC_FICHA_DOCUMENTOS> getall2() {

		Query query = entityManager.createNativeQuery("Select  a.ID,a.DATA_CRIA,a.COD_DOCUMENTO,a.REFERENCIA,a.DESC_REFERENCIA,a.COD_MAQUINA,a.DESC_MAQUINA,a.NOME_DOCUMENTO,(select STRING_AGG(x.DES_SECTOR,' | ') from RH_SECTORES x where x.COD_SECTOR in (select value from string_split(a.SECTOR,',')))  as desc_sector from DOC_FICHA_DOCUMENTOS a where a.INATIVO != 1 ");
		List<DOC_FICHA_DOCUMENTOS> data = query.getResultList();
		return data;

	}

}
