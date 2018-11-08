package pt.example.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GT_MOV_TAREFAS;

public class GT_MOV_TAREFASDao extends GenericDaoJpaImpl<GT_MOV_TAREFAS, Integer>
		implements GenericDao<GT_MOV_TAREFAS, Integer> {
	public GT_MOV_TAREFASDao() {
		super(GT_MOV_TAREFAS.class);
	}

	public List<GT_MOV_TAREFAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GT_MOV_TAREFAS a where a.ID_TAREFA = :id ");
		query.setParameter("id", id);
		List<GT_MOV_TAREFAS> data = query.getResultList();
		return data;

	}

	public List<GT_MOV_TAREFAS> getbyidUser2(Integer id) {

		Query query = entityManager.createNativeQuery(
				"select a.*,b.DESCRICAO , (select DESCRICAO_PT from GT_DIC_TAREFAS where id =  ID_ACCAO )  from GT_MOV_TAREFAS a "
						+ "left join GER_GRUPO b on a.UTZ_ID = b.ID  where UTZ_TIPO = 'g' and UTZ_ID in "
						+ "(Select ID_GRUPO from  GER_GRUPO_UTZ where ID_UTZ = :id) Union "
						+ "select *,'', (select DESCRICAO_PT from GT_DIC_TAREFAS where id =  ID_ACCAO ) from GT_MOV_TAREFAS  where UTZ_TIPO = 'u' and UTZ_ID = :id");
		query.setParameter("id", id);
		List<GT_MOV_TAREFAS> data = query.getResultList();
		return data;

	}

	public List<GT_MOV_TAREFAS> getbyidUser(Integer id,List<HashMap<String, String>> data2) {
		HashMap<String, String> firstMap = data2.get(0);

		String utilizador = firstMap.get("utilizador");
		String utilizador_grupo = firstMap.get("utilizador_grupo");
		String tipo_utilizador = firstMap.get("tipo_utilizador");
		
		String query_utilizador = "";
		if (tipo_utilizador != null && utilizador_grupo != null) {
			query_utilizador = "and (UTZ_TIPO = 'u' and UTZ_ID = " + utilizador + " or ( UTZ_TIPO = 'g' and UTZ_ID in (Select ID_GRUPO from  GER_GRUPO_UTZ where ID_UTZ = 1) ))";
		} else if (tipo_utilizador != null) {
			query_utilizador = "and UTZ_TIPO = 'u' and UTZ_ID = " + utilizador;
		} else if (utilizador_grupo != null) {
			query_utilizador = "and ( UTZ_TIPO = 'g' and UTZ_ID in (Select ID_GRUPO from  GER_GRUPO_UTZ where ID_UTZ = 1) )";
		}

		
		Query query = entityManager.createNativeQuery("Select  a.ID_ACCAO  "
				+ ",(select DESCRICAO_PT from GT_DIC_TAREFAS where ID = a.ID_ACCAO) as accao"
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'C'  "+query_utilizador+" and ESTADO ='P' and ID_ACCAO = a.ID_ACCAO ) as TAREFAS_NAO_LIDAS "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'C' "+query_utilizador+" and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'C' "+query_utilizador+" and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO AND UTZ_ENCAMINHADO is not null) as TAREFAS_ENCAMINHADAS "
				+ "from GT_MOV_TAREFAS a where ESTADO in ('P','L','E') AND INATIVO != 1 "+query_utilizador+" GROUP BY a.ID_ACCAO ORDER BY accao");
		//query.setParameter("id", id);
		List<GT_MOV_TAREFAS> data = query.getResultList();
		return data;

	}

	public List<GT_MOV_TAREFAS> getbyidFiltros(List<HashMap<String, String>> data2) {
		HashMap<String, String> firstMap = data2.get(0);

		String utilizador = firstMap.get("utilizador");
		String utilizador_grupo = firstMap.get("utilizador_grupo");
		String tipo_utilizador = firstMap.get("tipo_utilizador");
		Integer varquery = 1;
		Integer varquery1 = 1;
		Integer varquery2 = 1;
		Integer varquery3 = 1;
		Integer varquery4 = 1;
		Integer varquery5 = 1;
		Integer varquery6 = 1;
		String estado = firstMap.get("estado");

		String datacria1 = firstMap.get("datacria1");
		String datacria2 = firstMap.get("datacria2");

		String datafim1 = firstMap.get("datafim1");
		String datafim2 = firstMap.get("datafim2");
		String id = firstMap.get("id");

		String accao = firstMap.get("accao");

		String query_utilizador = "";
		if (tipo_utilizador != null && utilizador_grupo != null) {
			query_utilizador = "and  (UTZ_TIPO = 'u' and UTZ_ID = " + utilizador + " or ( UTZ_TIPO = 'g' and UTZ_ID in (Select ID_GRUPO from  GER_GRUPO_UTZ where ID_UTZ = 1) ))";
		} else if (tipo_utilizador != null) {
			query_utilizador = "and UTZ_TIPO = 'u' and UTZ_ID = " + utilizador;
		} else if (utilizador_grupo != null) {
			query_utilizador = "and ( UTZ_TIPO = 'g' and UTZ_ID in (Select ID_GRUPO from  GER_GRUPO_UTZ where ID_UTZ = 1) )";
		}

		if (estado == null) {
			varquery = 0;
		}

		if (datacria1 == null)
			varquery1 = 0;
		if (datacria2 == null)
			varquery2 = 0;
		if (datafim1 == null)
			varquery3 = 0;
		if (datafim2 == null)
			varquery4 = 0;
		if (accao == null)
			varquery5 = 0;
		if (id == null)
			varquery6 = 0;

		Query query = entityManager.createNativeQuery(
				"select (select DESCRICAO_PT from GT_DIC_TAREFAS where ID = a.ID_ACCAO) as NOME_TAREFA, "
						+ "(select NOME_UTILIZADOR from GER_UTILIZADORES where ID_UTILIZADOR = b.RESPONSAVEL) as UTILIZADOR_ORIGEM, a.DATA_CRIA as DATA_ATRIBUIÇÃO, "
						+ "(select NOME_UTILIZADOR from GER_UTILIZADORES where ID_UTILIZADOR = a.UTZ_ID) as ATRIBUIDO, (select NOME_UTILIZADOR from GER_UTILIZADORES where ID_UTILIZADOR = a.UTZ_ENCAMINHADO) as ENCAMINHADO,"
						+ "a.DATA_ENCAMINHADO, a.DATA_FIM,  (select c.DESCRICAO from RC_DIC_GRAU_IMPORTANCIA c where c.ID = a.PRIORIDADE) as PRIORIDADE, a.ESTADO, b.CLIENTE, b.REF, "
						+ "b.NOME_REF, a.DATA_CONCLUSAO, (select NOME_UTILIZADOR from GER_UTILIZADORES where ID_UTILIZADOR = a.UTZ_CONCLUSAO) as UTILIZADOR_CONCLUIU,a.ID_TAREFA "
						+ ",b.ID_RECLAMACAO ,CASE WHEN a.UTZ_TIPO = 'g' THEN (select g.DESCRICAO from GER_GRUPO g where g.ID= a.UTZ_ID)   ELSE null   END,a.PERCENTAGEM_CONCLUSAO,a.DESCRICAO,a.TEMPO_GASTO, "
						+ "a.UTZ_ENCAMINHADO,a.OBSERVACOES,a.ID_CAMPO,a.UTZ_ID,b.RESPONSAVEL,b.OBRIGA_EVIDENCIAS, "
						+ "(select EMAIL from GER_UTILIZADORES where ID_UTILIZADOR = b.RESPONSAVEL) as UTILIZADOR_ORIGEM_EMAIL,b.TIPO "
						+ "from  GT_MOV_TAREFAS a  inner join "
						+ "( select e.ID_RECLAMACAO,e.NOME_CLIENTE as CLIENTE,e.REFERENCIA as REF,e.DESIGNACAO_REF as NOME_REF,d.ID,e.UTZ_RESPONSAVEL as RESPONSAVEL,OBRIGA_EVIDENCIAS,d.TIPO from RC_MOV_RECLAMACAO_PLANOS_ACCOES d inner join RC_MOV_RECLAMACAO e on d.ID_RECLAMACAO = e.ID_RECLAMACAO) b "
						+ "on a.ID_CAMPO = b.ID where ((not " + varquery + " != 0) or (a.ESTADO in ("+estado+")))  "
						+ query_utilizador + "  AND INATIVO != 1 " + "and ((not " + varquery2
						+ " != 0) or (a.DATA_CRIA <= ('" + datacria2 + " 23:59:59'))) and ((not " + varquery1
						+ " != 0) or (a.DATA_CRIA >= ('" + datacria1 + "'))) " + "and ((not " + varquery4
						+ " != 0) or (a.DATA_FIM <= ('" + datafim2 + " 23:59:59'))) and ((not " + varquery3
						+ " != 0) or (a.DATA_FIM >= ('" + datafim1 + "'))) " + "and ((not " + varquery5
						+ " != 0) or (a.ID_ACCAO in (" + accao + "))) and ((not " + varquery6
						+ " != 0) or (a.ID_TAREFA = " + id + "))");

		//query.setParameter("query2", estado);
		List<GT_MOV_TAREFAS> data = query.getResultList();
		return data;

	}

	public List<GT_MOV_TAREFAS> getall() {

		Query query = entityManager.createQuery("Select a from GT_MOV_TAREFAS a ");
		List<GT_MOV_TAREFAS> data = query.getResultList();
		return data;

	}

}
