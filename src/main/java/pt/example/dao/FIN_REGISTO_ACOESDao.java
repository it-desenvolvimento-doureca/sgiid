package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.FIN_REGISTO_ACOES;

public class FIN_REGISTO_ACOESDao extends GenericDaoJpaImpl<FIN_REGISTO_ACOES, Integer>
		implements GenericDao<FIN_REGISTO_ACOES, Integer> {
	public FIN_REGISTO_ACOESDao() {
		super(FIN_REGISTO_ACOES.class);
	}

	public List<FIN_REGISTO_ACOES> getbyid(String id) {
		Query query = entityManager.createQuery("Select a.ID_ACAO,a.ID_CLIENTE,a.DATA_CRIA,a.UTZ_CRIA,a.CONTACTO,a.GERAR_ALERTA,a.ALERTA_GERADO,a.ORIGEM,a.DESCRICAO,a.TAMANHO_FICHEIRO,a.NOME_FICHEIRO,a.DATATYPE_FICHEIRO,a.TYPE_FICHEIRO "
				+ ",(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) from FIN_REGISTO_ACOES a where a.ID_CLIENTE = :id order by DATA_CRIA desc");
		query.setParameter("id", id);
		List<FIN_REGISTO_ACOES> data = query.getResultList();
		return data;
	}

	
	public List<FIN_REGISTO_ACOES> getbyidFicheiro(String id) {
		Query query = entityManager.createNativeQuery("Select a.FICHEIRO,a.FICHEIRO_2 from FIN_REGISTO_ACOES a where a.ID_ACAO = :id ");
		query.setParameter("id", id);
		List<FIN_REGISTO_ACOES> data = query.getResultList();
		return data;
	}
	
	public List<FIN_REGISTO_ACOES> getall() {
		Query query = entityManager.createQuery("Select a from FIN_REGISTO_ACOES a");
		List<FIN_REGISTO_ACOES> data = query.getResultList();
		return data;
	}

}
