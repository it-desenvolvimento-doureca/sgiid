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

	private static final String SQL_ESTADO_DESC = "CASE %s"
			+ " WHEN 'E' THEN N'Em Elaboração'"
			+ " WHEN 'P' THEN N'Planeado'"
			+ " WHEN 'I' THEN N'Desenvolvido/ Realizado'"
			+ " WHEN 'C' THEN N'Controlado/ Verificado'"
			+ " WHEN 'V' THEN N'Aprovado/ Finalizado'"
			+ " WHEN 'R' THEN N'Rejeitado'"
			+ " WHEN 'D' THEN N'Cancelado'"
			+ " ELSE %s END";

	public List<PA_MOV_CAB_HISTORICO> getbyPlano(Integer id) {
		String estadoAnterior = String.format(SQL_ESTADO_DESC, "h.VALOR_ANTERIOR", "h.VALOR_ANTERIOR");
		String estadoNovo = String.format(SQL_ESTADO_DESC, "h.VALOR_NOVO", "h.VALOR_NOVO");
		Query query = entityManager.createNativeQuery(
				"SELECT h.ID, h.ID_PLANO_CAB, h.TIPO_ALTERACAO,"
				+ " CASE WHEN h.TIPO_ALTERACAO = 'ESTADO' THEN " + estadoAnterior + " ELSE h.VALOR_ANTERIOR END as VALOR_ANTERIOR,"
				+ " CASE WHEN h.TIPO_ALTERACAO = 'ESTADO' THEN " + estadoNovo + " ELSE h.VALOR_NOVO END as VALOR_NOVO,"
				+ " h.JUSTIFICACAO, h.ESTADO_PE, h.DATA_CRIA,"
				+ " (SELECT NOME_UTILIZADOR FROM GER_UTILIZADORES WHERE ID_UTILIZADOR = h.UTZ_CRIA) as UTILIZADOR"
				+ " FROM PA_MOV_CAB_HISTORICO h WHERE h.ID_PLANO_CAB = :id"
				+ " AND NOT (h.TIPO_ALTERACAO = 'ESTADO' AND h.VALOR_ANTERIOR IS NULL)"
				+ " ORDER BY h.DATA_CRIA DESC");
		query.setParameter("id", id);
		List<PA_MOV_CAB_HISTORICO> data = query.getResultList();
		return data;
	}
}
