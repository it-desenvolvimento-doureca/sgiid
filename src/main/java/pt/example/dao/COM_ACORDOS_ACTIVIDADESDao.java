package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.COM_ACORDOS_ACTIVIDADES;

public class COM_ACORDOS_ACTIVIDADESDao extends GenericDaoJpaImpl<COM_ACORDOS_ACTIVIDADES, Integer>
		implements GenericDao<COM_ACORDOS_ACTIVIDADES, Integer> {
	public COM_ACORDOS_ACTIVIDADESDao() {
		super(COM_ACORDOS_ACTIVIDADES.class);
	}

	public List<COM_ACORDOS_ACTIVIDADES> getbyid(Integer id, Integer versao) {

		Query query = entityManager.createQuery(
				"Select a.ID,a.ID_ACORDO,a.DATA_ATIVIDADE,a. UTILIZADOR,a.DESCRICAO,a.OBSERVACAO,a.PRECO,a.DATA_CRIA,a.UTZ_CRIA,a.DATA_ULT_MODIF,a.UTZ_ULT_MODIF,a.TAMANHO_FICHEIRO,a.NOME_FICHEIRO,a. DATATYPE_FICHEIRO,a.TYPE_FICHEIRO,a.GERAR_ALERTA,a.ALERTA_GERADO  "
						+ " ,(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTILIZADOR) from COM_ACORDOS_ACTIVIDADES a where a.ID_ACORDO = :id and a.VERSAO = :versao order by DATA_ATIVIDADE desc ");
		query.setParameter("id", id);
		query.setParameter("versao", versao);
		List<COM_ACORDOS_ACTIVIDADES> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS_ACTIVIDADES> getall() {

		Query query = entityManager.createQuery("Select a from COM_ACORDOS_ACTIVIDADES a ");
		List<COM_ACORDOS_ACTIVIDADES> data = query.getResultList();
		return data;

	}

	public List<COM_ACORDOS_ACTIVIDADES> getbyidFicheiro(String id) {
		Query query = entityManager
				.createNativeQuery("Select a.FICHEIRO,a.FICHEIRO_2 from COM_ACORDOS_ACTIVIDADES a where a.ID = :id ");
		query.setParameter("id", id); 
		List<COM_ACORDOS_ACTIVIDADES> data = query.getResultList();
		return data;
	}

}
