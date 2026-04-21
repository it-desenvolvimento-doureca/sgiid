package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PA_MOV_LINHA_HISTORICO;

public class PA_MOV_LINHA_HISTORICODao extends GenericDaoJpaImpl<PA_MOV_LINHA_HISTORICO, Integer>
		implements GenericDao<PA_MOV_LINHA_HISTORICO, Integer> {

	public PA_MOV_LINHA_HISTORICODao() {
		super(PA_MOV_LINHA_HISTORICO.class);
	}

	public List<PA_MOV_LINHA_HISTORICO> getall() {
		Query query = entityManager.createQuery("Select a from PA_MOV_LINHA_HISTORICO a order by a.DATA_CRIA desc");
		List<PA_MOV_LINHA_HISTORICO> data = query.getResultList();
		return data;
	}

	public List<PA_MOV_LINHA_HISTORICO> getbyPlano(Integer id) {
		Query query = entityManager.createNativeQuery(
				"SELECT h.ID, h.ID_PLANO_CAB, h.ID_PLANO_LINHA, h.TIPO_ALTERACAO, h.DESCRICAO,"
				+ " h.ESTADO_PLANO_MOMENTO, h.DATA_CRIA,"
				+ " (SELECT NOME_UTILIZADOR FROM GER_UTILIZADORES WHERE ID_UTILIZADOR = h.UTZ_CRIA) as UTILIZADOR,"
				+ " h.VALOR_ANTERIOR, h.VALOR_NOVO"
				+ " FROM PA_MOV_LINHA_HISTORICO h WHERE h.ID_PLANO_CAB = :id ORDER BY h.DATA_CRIA DESC");
		query.setParameter("id", id);
		List<PA_MOV_LINHA_HISTORICO> data = query.getResultList();
		return data;
	}
}
