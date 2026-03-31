package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PA_MOV_CAB_HISTORICO;

public class PA_MOV_CAB_HISTORICODao extends GenericDaoJpaImpl<PA_MOV_CAB_HISTORICO, Integer>
		implements GenericDao<PA_MOV_CAB_HISTORICO, Integer> {

	public PA_MOV_CAB_HISTORICODao() {
		super(PA_MOV_CAB_HISTORICO.class);
	}

	public List<PA_MOV_CAB_HISTORICO> getall() {
		Query query = entityManager.createQuery("Select a from PA_MOV_CAB_HISTORICO a order by a.DATA_CRIA desc");
		List<PA_MOV_CAB_HISTORICO> data = query.getResultList();
		return data;
	}

	public List<PA_MOV_CAB_HISTORICO> getbyPlano(Integer id) {
		Query query = entityManager.createNativeQuery(
				"SELECT h.ID, h.ID_PLANO_CAB, h.TIPO_ALTERACAO, h.VALOR_ANTERIOR, h.VALOR_NOVO,"
				+ " h.JUSTIFICACAO, h.ESTADO_PE, h.DATA_CRIA,"
				+ " (SELECT NOME_UTILIZADOR FROM GER_UTILIZADORES WHERE ID_UTILIZADOR = h.UTZ_CRIA) as UTILIZADOR"
				+ " FROM PA_MOV_CAB_HISTORICO h WHERE h.ID_PLANO_CAB = :id ORDER BY h.DATA_CRIA DESC");
		query.setParameter("id", id);
		List<PA_MOV_CAB_HISTORICO> data = query.getResultList();
		return data;
	}
}
