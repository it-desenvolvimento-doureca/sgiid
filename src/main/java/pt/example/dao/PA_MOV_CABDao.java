package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PA_MOV_CAB;

public class PA_MOV_CABDao extends GenericDaoJpaImpl<PA_MOV_CAB, Integer> implements GenericDao<PA_MOV_CAB, Integer> {
	public PA_MOV_CABDao() {
		super(PA_MOV_CAB.class);
	}

	public List<PA_MOV_CAB> getbyid(Integer id) {

		Query query = entityManager.createQuery(
				"Select a,(select NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) "
						+ ",(select d.DES_SECTOR from GER_UTILIZADORES xb, RH_FUNCIONARIOS c,RH_SECTORES d where  xb.ID_UTILIZADOR = a.UTZ_CRIA and c.COD_FUNC_ORIGEM = xb.COD_UTZ and c.COD_SECTOR = d.COD_SECTOR) "
						+ "from PA_MOV_CAB a where a.ID_PLANO_CAB = :id ");
		query.setParameter("id", id);
		List<PA_MOV_CAB> data = query.getResultList();
		return data;

	}

	public List<PA_MOV_CAB> getbyidPlanoEstrategico(String tipo,Integer id) {
		String query2 = "ID_PLANO_ESTRATEGICO";
		if(tipo.equals("ID_PLANO_CAB")){
			query2 = "a.ID_PLANO_CAB";
		}
		Query query = entityManager.createNativeQuery(
				"select a.ID_PLANO_CAB,a.DATA_CRIA,a.DATA_OBJETIVO,a.AMBITO,a.ORIGEM,(select NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_CRIA) as UTZ_CRIA,a.DESCRICAO,a.ESTADO "
						+ ",b.DATA_ACCAO,(select NOME_UTILIZADOR from GER_UTILIZADORES y where y.ID_UTILIZADOR = b.RESPONSAVEL) as RESPONSAVEL, c.DESCRICAO_PT,b.DESCRICAO as descricao_acao,d.DESCRICAO as DESCRICAO_PRIORIDADE "
						+ ",b.ESTADO as ESTADO_ACAO,b.fastresponse ,(select sd.DESCRICAO from PA_DIC_AMBITOS sd where sd.ID_AMBITO = a.AMBITO) as AMBITO_DESC, f.DESCRICAO as TIPO_ACAO_DESC,g.ID_TAREFA,a.ID_PLANO_ESTRATEGICO,b.INVESTIMENTOS,a.OBJETIVO,ISNULL(g.PERCENTAGEM_CONCLUSAO,0)  PERCENTAGEM_CONCLUSAO,b.EFICACIA_CUMPRIMENTO_OBJETIVO "
						+ "from PA_MOV_CAB a "
						+ "left join PA_MOV_LINHA b on a.ID_PLANO_CAB = b.ID_PLANO_CAB left join GT_DIC_TAREFAS c on b.ID_ACCAO = c.ID "
						+ "left join RC_DIC_GRAU_IMPORTANCIA d on b.PRIORIDADE = d.ID left join GER_DEPARTAMENTO e on a.DEPARTAMENTO_ORIGEM = e.ID  left join GT_DIC_TIPO_ACAO f on b.TIPO_ACAO = f.ID_TIPO_ACAO "
						+ "  left join GT_MOV_TAREFAS g on g.ID_MODULO = 13 and g.SUB_MODULO = 'PA' and b.ID_PLANO_LINHA = g.ID_CAMPO  where  "+query2+" = :id order by a.DATA_OBJETIVO desc ");
		query.setParameter("id", id);
		List<PA_MOV_CAB> data = query.getResultList();
		return data;

	}

	public List<PA_MOV_CAB> getall() {

		Query query = entityManager
				.createQuery("Select a,(select COR from AB_DIC_LINHA WHERE ID_LINHA  = a.ID_LINHA) as LINHA"
						+ ",(select c.DESCRICAO from GER_DEPARTAMENTO c WHERE c.ID  = a.DEPARTAMENTO_ORIGEM) as DEPARTAMENTO "
						+ ",(select d.DES_SECTOR from GER_UTILIZADORES xb, RH_FUNCIONARIOS c,RH_SECTORES d where  xb.ID_UTILIZADOR = a.UTZ_CRIA and c.COD_FUNC_ORIGEM = xb.COD_UTZ and c.COD_SECTOR = d.COD_SECTOR) "
						+ " from PA_MOV_CAB a where a.ATIVO  = 1 order by DATA_CRIA desc,ID_PLANO_CAB desc");
		List<PA_MOV_CAB> data = query.getResultList();
		return data;

	}

	public List<PA_MOV_CAB> getallbyTIPO(String tipo, String fastresponse, String emAtraso) {
		String query_where = "";
		if (!tipo.equals("T")) {
			query_where = "and e.MODULO = '" + tipo + "' ";
		}
		if (fastresponse.equals("true")) {
			fastresponse = "1";
		} else {
			fastresponse = "0";
		}
		if (emAtraso.equals("true")) {
			emAtraso = "1";
		} else {
			emAtraso = "0";
		}

		Query query = entityManager.createNativeQuery(
				"DECLARE @fastresponse bit = " + fastresponse + " DECLARE @emAtraso bit = " + emAtraso + " " + "select "
						+ "a.ID_PLANO_CAB,a.DATA_CRIA,a.DATA_OBJETIVO,a.AMBITO,a.ORIGEM,(select NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_CRIA) as UTZ_CRIA,a.DESCRICAO,a.ESTADO "
						+ ",b.DATA_ACCAO,(select NOME_UTILIZADOR from GER_UTILIZADORES y where y.ID_UTILIZADOR = b.RESPONSAVEL) as RESPONSAVEL, c.DESCRICAO_PT,b.DESCRICAO as descricao_acao,d.DESCRICAO as DESCRICAO_PRIORIDADE "
						+ ",b.ESTADO as ESTADO_ACAO,b.fastresponse ,(select sd.DESCRICAO from PA_DIC_AMBITOS sd where sd.ID_AMBITO = a.AMBITO) as AMBITO_DESC, f.DESCRICAO as TIPO_ACAO_DESC,g.ID_TAREFA,a.ID_PLANO_ESTRATEGICO,ISNULL(g.PERCENTAGEM_CONCLUSAO,0)  PERCENTAGEM_CONCLUSAO "
						+ "from PA_MOV_CAB a " + "left join PA_MOV_LINHA b on a.ID_PLANO_CAB = b.ID_PLANO_CAB "
						+ "left join GT_DIC_TAREFAS c on b.ID_ACCAO = c.ID "
						+ "left join RC_DIC_GRAU_IMPORTANCIA d on b.PRIORIDADE = d.ID "
						+ "left join GER_DEPARTAMENTO e on a.DEPARTAMENTO_ORIGEM = e.ID "
						+ " left join GT_DIC_TIPO_ACAO f on b.TIPO_ACAO = f.ID_TIPO_ACAO  "
						+ "left join GT_MOV_TAREFAS g on g.ID_MODULO = 13 and g.SUB_MODULO = 'PA' and b.ID_PLANO_LINHA = g.ID_CAMPO "
						+ " where ((not @fastresponse != 0) or (b.FASTRESPONSE = @fastresponse )) and "
						+ " ((not @emAtraso != 0) or (b.DATA_ACCAO < GETDATE() and g.ESTADO in ('P','L','E') )) "
						+ query_where + " order by a.DATA_CRIA desc,a.ID_PLANO_CAB desc ");
		List<PA_MOV_CAB> data = query.getResultList();
		return data;

	}

	public List<PA_MOV_CAB> getallbyTIPOaccoes(String tipo, String fastresponse, String emAtraso) {
		String query_where = "";
		if (!tipo.equals("T")) {
			query_where = "and e.MODULO = '" + tipo + "' ";
		}
		if (fastresponse.equals("true")) {
			fastresponse = "1";
		} else {
			fastresponse = "0";
		}
		if (emAtraso.equals("true")) {
			emAtraso = "1";
		} else {
			emAtraso = "0";
		}

		Query query = entityManager.createNativeQuery(
				"DECLARE @fastresponse bit = " + fastresponse + " DECLARE @emAtraso bit = " + emAtraso + " " + "select "
						+ "a.ID_PLANO_CAB,a.DATA_CRIA,a.DATA_OBJETIVO,a.AMBITO,a.ORIGEM,(select NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_CRIA) as UTZ_CRIA,a.DESCRICAO,a.ESTADO "
						+ ",b.DATA_ACCAO,(select NOME_UTILIZADOR from GER_UTILIZADORES y where y.ID_UTILIZADOR = b.RESPONSAVEL) as RESPONSAVEL, c.DESCRICAO_PT,b.DESCRICAO as descricao_acao,d.DESCRICAO as DESCRICAO_PRIORIDADE "
						+ ",b.ESTADO as ESTADO_ACAO,b.fastresponse ,(select sd.DESCRICAO from PA_DIC_AMBITOS sd where sd.ID_AMBITO = a.AMBITO) as AMBITO_DESC, f.DESCRICAO as TIPO_ACAO_DESC,g.ID_TAREFA,g.ESTADO ESTADO_TAREFA,(select i.DESCRICAO from GER_UTILIZADORES t inner join RH_FUNCIONARIOS y on t.COD_UTZ = y.COD_FUNC_ORIGEM inner join GER_DEPARTAMENTOS_SECTORES u on y.COD_SECTOR = u.COD_SECTOR inner join  "
						+ "GER_DEPARTAMENTO i on u.ID_DEPARTAMENTO = i.ID where t.ID_UTILIZADOR = b.RESPONSAVEL),(select x.NOME_LINHA from AB_DIC_LINHA x where a.ID_LINHA = x.ID_LINHA) as LINHA "
						+ ",CASE WHEN a.UNIDADE = 1 THEN 'Formariz' WHEN   a.UNIDADE = 1 THEN 'São Bento' ELSE '' END as UNIDADES "
						+ ",CASE WHEN b.REFERENCIA is not null then b.REFERENCIA + ' - '+ b.DESIGN_REFERENCIA ELSE null END REFERENCIA "
						+ ",b.ITEM,b.CAUSA,g.DATA_CONCLUSAO " + "from PA_MOV_CAB a "
						+ "inner join PA_MOV_LINHA b on a.ID_PLANO_CAB = b.ID_PLANO_CAB "
						+ "left join GT_DIC_TAREFAS c on b.ID_ACCAO = c.ID "
						+ "left join RC_DIC_GRAU_IMPORTANCIA d on b.PRIORIDADE = d.ID "
						+ "inner join GER_DEPARTAMENTO e on a.DEPARTAMENTO_ORIGEM = e.ID "
						+ " left join GT_DIC_TIPO_ACAO f on b.TIPO_ACAO = f.ID_TIPO_ACAO  "
						+ "left join GT_MOV_TAREFAS g on g.ID_MODULO = 13 and g.SUB_MODULO = 'PA' and b.ID_PLANO_LINHA = g.ID_CAMPO "
						+ " where ((not @fastresponse != 0) or (b.FASTRESPONSE = @fastresponse )) and "
						+ " ((not @emAtraso != 0) or (b.DATA_ACCAO < GETDATE() and g.ESTADO in ('P','L','E') )) "
						+ query_where + " order by a.DATA_CRIA desc,a.ID_PLANO_CAB desc ");
		List<PA_MOV_CAB> data = query.getResultList();
		return data;

	}

}
