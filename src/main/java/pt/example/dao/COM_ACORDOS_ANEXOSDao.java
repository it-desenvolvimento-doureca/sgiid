package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_ACORDOS_ANEXOS;
import pt.example.entity.COM_ACORDOS_ANEXOS;

public class COM_ACORDOS_ANEXOSDao extends GenericDaoJpaImpl<COM_ACORDOS_ANEXOS, Integer>
		implements GenericDao<COM_ACORDOS_ANEXOS, Integer> {
	public COM_ACORDOS_ANEXOSDao() {
		super(COM_ACORDOS_ANEXOS.class);
	}

	public List<COM_ACORDOS_ANEXOS> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery("Select a.ID,a.ID_ACORDO,a.DATA_CRIA,a.UTZ_CRIA,a.DESCRICAO,a.TAMANHO_FICHEIRO,a.NOME_FICHEIRO,a.DATATYPE_FICHEIRO,a.TYPE_FICHEIRO "
				+ ",(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) from COM_ACORDOS_ANEXOS a "
				+ "where a.ID_ACORDO = :id and a.VERSAO = :versao order by DATA_CRIA desc ");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_ACORDOS_ANEXOS> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS_ANEXOS> getall() {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_ANEXOS a  ");
		List<COM_ACORDOS_ANEXOS> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS_ANEXOS> getbyidFicheiro(String id) {
		Query query = entityManager.createNativeQuery("Select a.FICHEIRO,a.FICHEIRO_2 from COM_ACORDOS_ANEXOS a where a.ID = :id ");
		query.setParameter("id", id); 
		List<COM_ACORDOS_ANEXOS> data = query.getResultList();
		return data;
	}
	
}
