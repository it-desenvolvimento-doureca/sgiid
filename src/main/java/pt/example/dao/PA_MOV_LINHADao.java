package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import pt.example.entity.PA_MOV_LINHA;

public class PA_MOV_LINHADao extends GenericDaoJpaImpl<PA_MOV_LINHA, Integer> implements GenericDao<PA_MOV_LINHA, Integer> {
	public PA_MOV_LINHADao() {
		super(PA_MOV_LINHA.class);
	}

	public List<PA_MOV_LINHA> getbyid(Integer id, Integer user) {
		// Native SQL with WITH (NOLOCK) to prevent deadlocks with concurrent UPDATEs
		// on PA_MOV_LINHA / GT_MOV_TAREFAS (getPA_MOV_LINHAAtualizaESTADOS).
		// addEntity("a", ...) maps {a.*} columns back to the PA_MOV_LINHA entity,
		// preserving the same Object[] tuple structure [entity, nome, id_tarefa, segui, subtarefas]
		// that the calling code expects.
		String sql = "SELECT {a.*},"
				+ " (SELECT c.NOME_UTILIZADOR FROM GER_UTILIZADORES c WITH (NOLOCK)"
				+ "  WHERE c.ID_UTILIZADOR = ("
				+ "    SELECT b.UTZ_ENCAMINHADO FROM GT_MOV_TAREFAS b WITH (NOLOCK)"
				+ "    WHERE b.ID_MODULO = 13 AND b.SUB_MODULO = 'PA'"
				+ "    AND b.ID_CAMPO = a.ID_PLANO_LINHA AND b.ID_TAREFA_PAI IS NULL AND b.INATIVO = 0"
				+ "  )) AS utz_encaminhado_nome,"
				+ " (SELECT b.ID_TAREFA FROM GT_MOV_TAREFAS b WITH (NOLOCK)"
				+ "  WHERE b.ID_MODULO = 13 AND b.SUB_MODULO = 'PA'"
				+ "  AND b.ID_CAMPO = a.ID_PLANO_LINHA AND b.ID_TAREFA_PAI IS NULL AND b.INATIVO = 0) AS id_tarefa,"
				+ " (SELECT COUNT(ID) FROM PA_MOV_SEGUIR_LINHA b WITH (NOLOCK)"
				+ "  WHERE b.ID_PLANO_LINHA = a.ID_PLANO_LINHA AND b.UTILIZADOR = :user) AS segui_linha,"
				+ " (SELECT COUNT(x.ID_TAREFA) FROM GT_MOV_TAREFAS x WITH (NOLOCK)"
				+ "  WHERE x.ID_TAREFA_PAI = ("
				+ "    SELECT b.ID_TAREFA FROM GT_MOV_TAREFAS b WITH (NOLOCK)"
				+ "    WHERE b.ID_MODULO = 13 AND b.SUB_MODULO = 'PA'"
				+ "    AND b.ID_CAMPO = a.ID_PLANO_LINHA AND b.ID_TAREFA_PAI IS NULL AND b.INATIVO = 0"
				+ "  )) AS subtarefas"
				+ " FROM PA_MOV_LINHA a WITH (NOLOCK)"
				+ " WHERE a.ID_PLANO_CAB = :id"
				+ " ORDER BY a.ORDENACAO";

		Session session = entityManager.unwrap(Session.class);
		NativeQuery<?> nq = session.createNativeQuery(sql);
		nq.addEntity("a", PA_MOV_LINHA.class);
		nq.addScalar("utz_encaminhado_nome");
		nq.addScalar("id_tarefa");
		nq.addScalar("segui_linha");
		nq.addScalar("subtarefas");
		nq.setParameter("id", id);
		nq.setParameter("user", user);
		return (List<PA_MOV_LINHA>) nq.getResultList();
	}


	public List<PA_MOV_LINHA> getall() {

		Query query = entityManager.createQuery("Select a from PA_MOV_LINHA a");
		List<PA_MOV_LINHA> data = query.getResultList();
		return data;

	}

}
