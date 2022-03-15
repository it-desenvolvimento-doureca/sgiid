package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PE_MOV_CAB;
import pt.example.entity.PE_MOV_CAB;

public class PE_MOV_CABDao extends GenericDaoJpaImpl<PE_MOV_CAB, Integer> implements GenericDao<PE_MOV_CAB, Integer> {
	public PE_MOV_CABDao() {
		super(PE_MOV_CAB.class);
	}

	public List<PE_MOV_CAB> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a,(select NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) "
				+ ",(select d.DES_SECTOR from GER_UTILIZADORES xb, RH_FUNCIONARIOS c,RH_SECTORES d where  xb.ID_UTILIZADOR = a.UTZ_CRIA and c.COD_FUNC_ORIGEM = xb.COD_UTZ and c.COD_SECTOR = d.COD_SECTOR) "
				+ "from PE_MOV_CAB a where a.ID = :id ");
		query.setParameter("id", id);
		List<PE_MOV_CAB> data = query.getResultList();
		return data;

	}


	public List<PE_MOV_CAB> getall() {

		Query query = entityManager.createQuery("Select a "
				+ ",(select c.DESCRICAO from GER_DEPARTAMENTO c WHERE c.ID  = a.DEPARTAMENTO) as DEPARTAMENTO "
				+ ",(select d.DES_SECTOR from GER_UTILIZADORES xb, RH_FUNCIONARIOS c,RH_SECTORES d where  xb.ID_UTILIZADOR = a.UTZ_CRIA and c.COD_FUNC_ORIGEM = xb.COD_UTZ and c.COD_SECTOR = d.COD_SECTOR) " 
				+ " from PE_MOV_CAB a where a.ATIVO  = 1 order by DATA_CRIA desc,a.ID desc");
		List<PE_MOV_CAB> data = query.getResultList();
		return data;

	} 
	
	
	public List<PE_MOV_CAB> getallbyTIPO(String tipo, String emAtraso) {
		String query_where = "";
		if (!tipo.equals("T")) {
			query_where = "and xa.TIPO = '" + tipo + "' ";
		}
		 
		if (emAtraso.equals("true")) {
			emAtraso = "1";
		} else {
			emAtraso = "0";
		}

		Query query = entityManager.createNativeQuery(
				" DECLARE @emAtraso bit = " + emAtraso + " " + "select "
						+ "a.ID_PLANO_CAB,a.DATA_CRIA,a.DATA_OBJETIVO,a.AMBITO,a.ORIGEM,(select NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_CRIA) as UTZ_CRIA,a.DESCRICAO,a.ESTADO "
						+ ",b.DATA_ACCAO,(select NOME_UTILIZADOR from GER_UTILIZADORES y where y.ID_UTILIZADOR = b.RESPONSAVEL) as RESPONSAVEL, c.DESCRICAO_PT,b.DESCRICAO as descricao_acao,d.DESCRICAO as DESCRICAO_PRIORIDADE "
						+ ",b.ESTADO as ESTADO_ACAO,b.fastresponse ,(select sd.DESCRICAO from PA_DIC_AMBITOS sd where sd.ID_AMBITO = a.AMBITO) as AMBITO_DESC, f.DESCRICAO as TIPO_ACAO_DESC,g.ID_TAREFA,xe.DESCRICAO NOME_DEPARTAMENTO,xa.ID,xa.ANO_PLANO "
						+ ",(select NOME_UTILIZADOR from GER_UTILIZADORES y where y.ID_UTILIZADOR = xa.RESPONSAVEL) as RESPONSAVELXA,  xa.DATA_CRIA as DATA_CRIAXA,xa.ESTADO ESTADOXA "
						+ ",ISNULL(g.PERCENTAGEM_CONCLUSAO,0) conclusaoacao ,AVG(ISNULL(g.PERCENTAGEM_CONCLUSAO,0)) OVER(PARTITION BY a.ID_PLANO_CAB) AS conclusaoplano, CASE WHEN (select COUNT(DISTINCT t1.ID_PLANO_CAB)  from PA_MOV_CAB t1 where t1.ID_PLANO_ESTRATEGICO = xa.ID  ) = 0 THEN 0 WHEN (select COUNT(DISTINCT t1.ID_PLANO_CAB)  from PA_MOV_CAB t1 where t1.ID_PLANO_ESTRATEGICO = xa.ID  ) = 1 THEN "
						+ "AVG(ISNULL(g.PERCENTAGEM_CONCLUSAO,0)) OVER(PARTITION BY xa.ID) ELSE "
						+ "SUM(ISNULL(g.PERCENTAGEM_CONCLUSAO,0)) OVER(PARTITION BY xa.ID) / (select COUNT(DISTINCT t1.ID_PLANO_CAB)  from PA_MOV_CAB t1 where t1.ID_PLANO_ESTRATEGICO = xa.ID  ) END AS conclusaototal, b.OBJETIVO,b.SEGUIR_LINHA,b.ID_PLANO_LINHA,b.DATA_CRIA DATA_CRIA_LINHA "
						+ "from PE_MOV_CAB xa "
						+ "left join PA_MOV_CAB a on xa.ID = a.ID_PLANO_ESTRATEGICO  "
						+ "left join GER_DEPARTAMENTO xe on xa.DEPARTAMENTO = xe.ID" + " left join PA_MOV_LINHA b on a.ID_PLANO_CAB = b.ID_PLANO_CAB "
						+ "left join GT_DIC_TAREFAS c on b.ID_ACCAO = c.ID "
						+ "left join RC_DIC_GRAU_IMPORTANCIA d on b.PRIORIDADE = d.ID "
						+ "left join GER_DEPARTAMENTO e on a.DEPARTAMENTO_ORIGEM = e.ID "
						+ " left join GT_DIC_TIPO_ACAO f on b.TIPO_ACAO = f.ID_TIPO_ACAO  "
						+ "left join GT_MOV_TAREFAS g on g.ID_MODULO = 13 and g.SUB_MODULO = 'PA' and b.ID_PLANO_LINHA = g.ID_CAMPO "
						+ " where  "
						+ " ((not @emAtraso != 0) or (b.DATA_ACCAO < GETDATE() and g.ESTADO in ('P','L','E') )) "
						+ query_where + " order by a.DATA_OBJETIVO asc,a.ID_PLANO_CAB asc,b.DATA_ACCAO asc ");
		List<PE_MOV_CAB> data = query.getResultList();
		return data;

	}

	


}
