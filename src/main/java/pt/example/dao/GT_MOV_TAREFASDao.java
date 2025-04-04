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

	public List<GT_MOV_TAREFAS> getbyids(Integer id, Integer modulo, String submodulo) {

		Query query = entityManager.createQuery(
				"Select a from GT_MOV_TAREFAS a where a.ID_CAMPO = :id and a.ID_MODULO= :modulo and a.SUB_MODULO= :submodulo");
		query.setParameter("id", id);
		query.setParameter("modulo", modulo);
		query.setParameter("submodulo", submodulo);
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

	public List<GT_MOV_TAREFAS> getbyidUser(Integer id, List<HashMap<String, String>> data2) {
		HashMap<String, String> firstMap = data2.get(0);

		String utilizador = firstMap.get("utilizador");
		String utilizador_grupo = firstMap.get("utilizador_grupo");
		String utilizador_sector = firstMap.get("utilizador_sector");
		String tipo_utilizador = firstMap.get("tipo_utilizador");

		String query_utilizador = "";
		/*if (tipo_utilizador != null && utilizador_grupo != null && utilizador_sector!= null) {
			query_utilizador = "and (UTZ_TIPO = 'u' and UTZ_ID = " + utilizador
					+ " or ( UTZ_TIPO = 'g' and UTZ_ID in (Select ID_GRUPO from  GER_GRUPO_UTZ where ID_UTZ = "+utilizador+") ))";
		} else*/
		if (tipo_utilizador != null) {
			query_utilizador = " ( UTZ_TIPO = 'u' and (UTZ_ID = " + utilizador + " OR UTZ_ENCAMINHADO  ="+ utilizador + " and 1 = CASE WHEN UTZ_ENCAMINHADO is not null and UTZ_ENCAMINHADO  != "+ utilizador + " THEN 0 ELSE 1 END ))";
		} 
		
		if (utilizador_grupo != null) {
			if(!query_utilizador.isEmpty()){ 
				query_utilizador += " or ";
			}
			query_utilizador += " ( UTZ_TIPO = 'g' and UTZ_ID in (Select ID_GRUPO from  GER_GRUPO_UTZ where ID_UTZ = "+utilizador+") )";
		}
		
		if (utilizador_sector != null) {
			if(!query_utilizador.isEmpty()){ 
				query_utilizador += " or ";
			}
			query_utilizador += " ( UTZ_TIPO = 's' and UTZ_ID in ( select COD_SECTOR from RH_FUNCIONARIOS where COD_FUNCIONARIO = (select COD_UTZ from GER_UTILIZADORES where ID_UTILIZADOR = "+utilizador+")) )";
		}

		if(!query_utilizador.isEmpty()){ 
			query_utilizador = " and (" + query_utilizador + ") ";
		}
		
		Query query = entityManager.createNativeQuery("Select  a.ID_ACCAO  "
				+ ",(select DESCRICAO_PT from GT_DIC_TAREFAS where ID = a.ID_ACCAO) as accao"
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'C'  " + query_utilizador+ " and ESTADO ='P' and ID_ACCAO = a.ID_ACCAO ) as TAREFAS_NAO_LIDAS "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'C' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'C' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO AND UTZ_ENCAMINHADO is not null) as TAREFAS_ENCAMINHADAS "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 10 and SUB_MODULO = 'A'  " + query_utilizador+ " and ESTADO ='P' and ID_ACCAO = a.ID_ACCAO ) as TAREFAS_NAO_LIDAS_AMOSTRAS "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 10 and SUB_MODULO = 'A' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_AMOSTRAS "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 10 and SUB_MODULO = 'A' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO AND UTZ_ENCAMINHADO is not null) as TAREFAS_ENCAMINHADAS_AMOSTRAS "
				
				+ ",ISNULL((select count(xa.ID_TAREFA) from GT_MOV_TAREFAS xa inner join PA_MOV_LINHA b on xa.ID_CAMPO = b.ID_PLANO_LINHA inner join PA_MOV_CAB c on b.ID_PLANO_CAB = c.ID_PLANO_CAB  where  (select count(*) from PE_PLANOS_ASSOCIADOS x where x.ID_PLANO_CAB = c.ID_PLANO_CAB) = 0 AND ID_MODULO= 13 and SUB_MODULO = 'PA'  " + query_utilizador+ " and xa.ESTADO ='P' and xa.ID_ACCAO = a.ID_ACCAO ),0) as TAREFAS_NAO_LIDAS_PLANOS "
				+ ",ISNULL((select count(xa.ID_TAREFA) from GT_MOV_TAREFAS xa inner join PA_MOV_LINHA b on xa.ID_CAMPO = b.ID_PLANO_LINHA inner join PA_MOV_CAB c on b.ID_PLANO_CAB = c.ID_PLANO_CAB  where  (select count(*) from PE_PLANOS_ASSOCIADOS x where x.ID_PLANO_CAB = c.ID_PLANO_CAB) = 0 AND ID_MODULO= 13 and SUB_MODULO = 'PA' " + query_utilizador+ " and xa.ESTADO in ('P','L','E') and xa.ID_ACCAO = a.ID_ACCAO ),0) as TOTAL_TAREFAS_PLANOS "
				+ ",ISNULL((select count(xa.ID_TAREFA) from GT_MOV_TAREFAS xa inner join PA_MOV_LINHA b on xa.ID_CAMPO = b.ID_PLANO_LINHA inner join PA_MOV_CAB c on b.ID_PLANO_CAB = c.ID_PLANO_CAB  where  (select count(*) from PE_PLANOS_ASSOCIADOS x where x.ID_PLANO_CAB = c.ID_PLANO_CAB) = 0 AND ID_MODULO= 13 and SUB_MODULO = 'PA' " + query_utilizador+ " and xa.ESTADO in ('P','L','E') and xa.ID_ACCAO = a.ID_ACCAO AND UTZ_ENCAMINHADO is not null),0) as TAREFAS_ENCAMINHADAS_PLANOS "
				
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'D'  " + query_utilizador+ " and ESTADO ='P' and ID_ACCAO = a.ID_ACCAO ) as TAREFAS_NAO_LIDAS_DERROGACAOES "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'D' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_DERROGACAOES "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'D' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO AND UTZ_ENCAMINHADO is not null) as TAREFAS_ENCAMINHADAS_DERROGACAOES "
				
				+ ",ISNULL((select count(xa.ID_TAREFA) from GT_MOV_TAREFAS xa inner join PA_MOV_LINHA b on xa.ID_CAMPO = b.ID_PLANO_LINHA inner join PA_MOV_CAB c on b.ID_PLANO_CAB = c.ID_PLANO_CAB  where  (select count(*) from PE_PLANOS_ASSOCIADOS x where x.ID_PLANO_CAB = c.ID_PLANO_CAB) > 0 AND  SUB_MODULO = 'PA'  " + query_utilizador+ " and xa.ESTADO ='P' and xa.ID_ACCAO = a.ID_ACCAO /*GROUP BY xa.ID_TAREFA*/),0) as TAREFAS_NAO_LIDAS_PLANOS_ESTRATEGICOS"
				+ ",ISNULL((select count(xa.ID_TAREFA) from GT_MOV_TAREFAS xa inner join PA_MOV_LINHA b on xa.ID_CAMPO = b.ID_PLANO_LINHA inner join PA_MOV_CAB c on b.ID_PLANO_CAB = c.ID_PLANO_CAB  where  (select count(*) from PE_PLANOS_ASSOCIADOS x where x.ID_PLANO_CAB = c.ID_PLANO_CAB) > 0 AND ID_MODULO= 13 and SUB_MODULO = 'PA' " + query_utilizador+ " and xa.ESTADO in ('P','L','E') and xa.ID_ACCAO = a.ID_ACCAO /*GROUP BY xa.ID_TAREFA*/),0) as TOTAL_TAREFAS_PLANOS_ESTRATEGICOS"
				+ ",ISNULL((select count(xa.ID_TAREFA) from GT_MOV_TAREFAS xa inner join PA_MOV_LINHA b on xa.ID_CAMPO = b.ID_PLANO_LINHA inner join PA_MOV_CAB c on b.ID_PLANO_CAB = c.ID_PLANO_CAB  where  (select count(*) from PE_PLANOS_ASSOCIADOS x where x.ID_PLANO_CAB = c.ID_PLANO_CAB) > 0 AND ID_MODULO= 13 and SUB_MODULO = 'PA' " + query_utilizador+ " and xa.ESTADO in ('P','L','E') and xa.ID_ACCAO = a.ID_ACCAO AND UTZ_ENCAMINHADO is not null /*GROUP BY xa.ID_TAREFA*/),0) as TAREFAS_ENCAMINHADAS_PLANOS_ESTRATEGICOS"
				
				+ ",(select count(*) from GT_MOV_TAREFAS where DATA_FIM<GETDATE() AND ID_MODULO= 5 and SUB_MODULO = 'C' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_ATRASO"
				+ ",(select count(*) from GT_MOV_TAREFAS where DATA_FIM<GETDATE() AND ID_MODULO= 10 and SUB_MODULO = 'A' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_ATRASO_AMOSTRAS"
				+ ",ISNULL((select count(xa.ID_TAREFA) from GT_MOV_TAREFAS xa inner join PA_MOV_LINHA b on xa.ID_CAMPO = b.ID_PLANO_LINHA inner join PA_MOV_CAB c on b.ID_PLANO_CAB = c.ID_PLANO_CAB  where  (select count(*) from PE_PLANOS_ASSOCIADOS x where x.ID_PLANO_CAB = c.ID_PLANO_CAB) = 0 AND  DATA_FIM<GETDATE() AND ID_MODULO= 13 and SUB_MODULO = 'PA' " + query_utilizador+ " and xa.ESTADO in ('P','L','E') and xa.ID_ACCAO = a.ID_ACCAO /*GROUP BY xa.ID_TAREFA*/),0) as TOTAL_TAREFAS_ATRASO_PLANOS"
				+ ",(select count(*) from GT_MOV_TAREFAS where DATA_FIM<GETDATE() AND ID_MODULO= 5 and SUB_MODULO = 'D' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_ATRASO_DERROGACAOES"
				+ ",ISNULL((select count(xa.ID_TAREFA) from GT_MOV_TAREFAS xa inner join PA_MOV_LINHA b on xa.ID_CAMPO = b.ID_PLANO_LINHA inner join PA_MOV_CAB c on b.ID_PLANO_CAB = c.ID_PLANO_CAB  where  (select count(*) from PE_PLANOS_ASSOCIADOS x where x.ID_PLANO_CAB = c.ID_PLANO_CAB) > 0 AND DATA_FIM<GETDATE() AND ID_MODULO= 13 and SUB_MODULO = 'PA' " + query_utilizador+ " and xa.ESTADO in ('P','L','E') and xa.ID_ACCAO = a.ID_ACCAO /*GROUP BY xa.ID_TAREFA*/),0) as TOTAL_TAREFAS_ATRASO_PLANOS_ESTRATEGICOS "
				
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'F'  " + query_utilizador+ " and ESTADO ='P' and ID_ACCAO = a.ID_ACCAO ) as TAREFAS_NAO_LIDAS_FORNECEDOR "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'F' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_FORNECEDOR "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 5 and SUB_MODULO = 'F' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO AND UTZ_ENCAMINHADO is not null) as TAREFAS_ENCAMINHADAS_FORNECEDOR "
				+ ",(select count(*) from GT_MOV_TAREFAS where DATA_FIM<GETDATE() AND ID_MODULO= 5 and SUB_MODULO = 'F' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_ATRASO_FORNECEDOR "
				
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 19 and SUB_MODULO = 'R'  " + query_utilizador+ " and ESTADO ='P' and ID_ACCAO = a.ID_ACCAO ) as TAREFAS_NAO_LIDAS_REUNIAO "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 19 and SUB_MODULO = 'R' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_REUNIAO "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO= 19 and SUB_MODULO = 'R' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO AND UTZ_ENCAMINHADO is not null) as TAREFAS_ENCAMINHADAS_REUNIAO "
				+ ",(select count(*) from GT_MOV_TAREFAS where DATA_FIM<GETDATE() AND ID_MODULO= 19 and SUB_MODULO = 'R' " + query_utilizador+ " and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_ATRASO_REUNIAO "
						
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO is null and SUB_MODULO is null   and ( ( UTZ_TIPO = 'u' and (UTZ_ID = 1 OR UTZ_ENCAMINHADO  =1 and 1 = CASE WHEN UTZ_ENCAMINHADO is not null and UTZ_ENCAMINHADO  != 1 THEN 0 ELSE 1 END )))  and ESTADO ='P' and ID_ACCAO = a.ID_ACCAO ) as TAREFAS_NAO_LIDAS_DIVERSOS "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO is null and SUB_MODULO is null  and ( ( UTZ_TIPO = 'u' and (UTZ_ID = 1 OR UTZ_ENCAMINHADO  =1 and 1 = CASE WHEN UTZ_ENCAMINHADO is not null and UTZ_ENCAMINHADO  != 1 THEN 0 ELSE 1 END )))  and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_DIVERSOS "
				+ ",(select count(*) from GT_MOV_TAREFAS where ID_MODULO is null and SUB_MODULO is null  and ( ( UTZ_TIPO = 'u' and (UTZ_ID = 1 OR UTZ_ENCAMINHADO  =1 and 1 = CASE WHEN UTZ_ENCAMINHADO is not null and UTZ_ENCAMINHADO  != 1 THEN 0 ELSE 1 END )))  and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO AND UTZ_ENCAMINHADO is not null) as TAREFAS_ENCAMINHADAS_DIVERSOS "
				+ ",(select count(*) from GT_MOV_TAREFAS where DATA_FIM<GETDATE() AND ID_MODULO  is null and SUB_MODULO  is null  and ( ( UTZ_TIPO = 'u' and (UTZ_ID = 1 OR UTZ_ENCAMINHADO  =1 and 1 = CASE WHEN UTZ_ENCAMINHADO is not null and UTZ_ENCAMINHADO  != 1 THEN 0 ELSE 1 END )))  and ESTADO in ('P','L','E') and ID_ACCAO = a.ID_ACCAO ) as TOTAL_TAREFAS_ATRASO_DIVERSOS "
				
				+ "from GT_MOV_TAREFAS a where ESTADO in ('P','L','E') AND INATIVO != 1 " + query_utilizador
				+ " GROUP BY a.ID_ACCAO ORDER BY accao");
		// query.setParameter("id", id);
		List<GT_MOV_TAREFAS> data = query.getResultList();
		return data;

	}

	public List<GT_MOV_TAREFAS> getbyidFiltros(List<HashMap<String, String>> data2) {
		HashMap<String, String> firstMap = data2.get(0);

		String utilizador = firstMap.get("utilizador");
		String utilizador_grupo = firstMap.get("utilizador_grupo");
		String tipo_utilizador = firstMap.get("tipo_utilizador");
		String utilizador_sector = firstMap.get("utilizador_sector");
		Integer varquery = 1;
		Integer varquery1 = 1;
		Integer varquery2 = 1;
		Integer varquery3 = 1;
		Integer varquery4 = 1;
		Integer varquery5 = 1;
		Integer varquery6 = 1;
		Integer varquery7 = 1;
		Integer varquery8 = 1;
		Integer varquery9 = 1;
		Integer varquery10 = 1;
		Integer varquery11 = 1;
		String estado = firstMap.get("estado");

		String datacria1 = firstMap.get("datacria1");
		String datacria2 = firstMap.get("datacria2");

		String datafim1 = firstMap.get("datafim1");
		String datafim2 = firstMap.get("datafim2");
		String id = firstMap.get("id");
		String idsubtarefa = firstMap.get("idsubtarefa");

		String accao = firstMap.get("accao");
		String ambitos = firstMap.get("ambitos");
		
		String modulo = firstMap.get("modulo");
		String submodulo = firstMap.get("submodulo");

		String planosestrategicos = firstMap.get("planosestrategicos");
		varquery10 = 1;
		String query_utilizador = "";
		/*if (tipo_utilizador != null && utilizador_grupo != null) {
			query_utilizador = "and  (UTZ_TIPO = 'u' and UTZ_ID = " + utilizador
					+ " or ( UTZ_TIPO = 'g' and UTZ_ID in (Select ID_GRUPO from  GER_GRUPO_UTZ where ID_UTZ = 1) ))";
		} else if (tipo_utilizador != null) {
			query_utilizador = "and UTZ_TIPO = 'u' and UTZ_ID = " + utilizador;
		} else if (utilizador_grupo != null) {
			query_utilizador = "and ( UTZ_TIPO = 'g' and UTZ_ID in (Select ID_GRUPO from  GER_GRUPO_UTZ where ID_UTZ = 1) )";
		}*/
		
		if (tipo_utilizador != null) {
			query_utilizador = " ( UTZ_TIPO = 'u' and (UTZ_ID = " + utilizador + " OR UTZ_ENCAMINHADO  ="+ utilizador + " and 1 = CASE WHEN UTZ_ENCAMINHADO is not null and UTZ_ENCAMINHADO  != "+ utilizador + " THEN 0 ELSE 1 END ))";
		} 
		
		if (utilizador_grupo != null) {
			if(!query_utilizador.isEmpty()){ 
				query_utilizador += " or ";
			}
			query_utilizador += " ( UTZ_TIPO = 'g' and UTZ_ID in (Select ID_GRUPO from  GER_GRUPO_UTZ where ID_UTZ = "+utilizador+") )";
		}
		
		if (utilizador_sector != null) {
			if(!query_utilizador.isEmpty()){ 
				query_utilizador += " or ";
			}
			query_utilizador += " ( UTZ_TIPO = 's' and UTZ_ID in ( select COD_SECTOR from RH_FUNCIONARIOS where COD_FUNCIONARIO = (select COD_UTZ from GER_UTILIZADORES where ID_UTILIZADOR = "+utilizador+")) )";
		}

		if(!query_utilizador.isEmpty()){ 
			query_utilizador = " and (" + query_utilizador + ") ";
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
		if (modulo == null)
			varquery7 = 0;
		if (submodulo == null)
			varquery8 = 0;
		if (idsubtarefa == null)
			varquery9 = 0;

		if (planosestrategicos == null){
			varquery10 = 0;
		}else{
			varquery10 = Integer.parseInt(planosestrategicos);
		}
		
		if (ambitos == null)
			varquery11 = 0;
		
		Query query = entityManager.createNativeQuery(
				"DECLARE @TABELA_TEMP TABLE(ID_RECLAMACAO int,CLIENTE varchar(max),REF varchar(max),NOME_REF varchar(max),ID varchar(max),RESPONSAVEL varchar(max),OBRIGA_EVIDENCIAS bit,TIPO varchar(max),MODULO int,DEPARTAMENTO int,SUB_MODULO varchar(2),ID_AMBITO int,ESTADO varchar(5)) "
						+ "INSERT @TABELA_TEMP "
						+ "select e.ID_RECLAMACAO,e.NOME_CLIENTE as CLIENTE,e.REFERENCIA as REF,e.DESIGNACAO_REF as NOME_REF,d.ID,e.UTZ_RESPONSAVEL as RESPONSAVEL,OBRIGA_EVIDENCIAS,d.TIPO,5,null,'C' SUB_MODULO ,null ID_AMBITO,e.ESTADO from RC_MOV_RECLAMACAO_PLANOS_ACCOES d inner join RC_MOV_RECLAMACAO e on d.ID_RECLAMACAO = e.ID_RECLAMACAO "
						+ " union select e.ID_RECLAMACAO,e.NOME_FORNECEDOR as CLIENTE,e.REFERENCIA as REF,e.DESIGNACAO_REF as NOME_REF,d.ID,e.UTZ_RESPONSAVEL as RESPONSAVEL,OBRIGA_EVIDENCIAS,d.TIPO,5,null,'F' SUB_MODULO ,null ID_AMBITO,e.ESTADO from RC_MOV_RECLAMACAO_FORNECEDOR_PLANOS_ACCOES d inner join RC_MOV_RECLAMACAO_FORNECEDOR e on d.ID_RECLAMACAO = e.ID_RECLAMACAO "
						
						+ "union select c.ID_AMOSTRA,null,c.REFERENCIA,c.DESCRICAO,b.ID_AMOSTRA_ACCAO,a.UTZ_CRIA,null,null,10,null,'A' SUB_MODULO ,null ID_AMBITO,c.ESTADO from GT_MOV_TAREFAS a left join PR_AMOSTRAS_ACCOES b on a.ID_CAMPO = b.ID_AMOSTRA_ACCAO "
						+ "left join PR_AMOSTRAS_CAB c on b.ID_AMOSTRA = c.ID_AMOSTRA where ((not " + varquery6+ " != 0) or (ID_TAREFA = " + id + ")) and a.ID_MODULO = 10   AND ((not " + varquery6+ " = 0) or ( a.ID_TAREFA_PAI is null)) "
						
						+ "union select c.ID_PLANO_CAB,null,b.REFERENCIA,b.DESIGN_REFERENCIA,b.ID_PLANO_LINHA,a.UTZ_CRIA,null,null,13,c.DEPARTAMENTO_ORIGEM,'PA' SUB_MODULO ,c.AMBITO ID_AMBITO ,c.ESTADO from GT_MOV_TAREFAS a  "
						+ "left join PA_MOV_LINHA b on a.ID_CAMPO = b.ID_PLANO_LINHA left join PA_MOV_CAB c on b.ID_PLANO_CAB = c.ID_PLANO_CAB  where ((not " + varquery6+ " != 0) or (ID_TAREFA = " + id + ")) and a.ID_MODULO = 13  AND ((not " + varquery6+ " = 0) or ( a.ID_TAREFA_PAI is null))  "
						
						+ " union select e.ID_DERROGACAO,e.NOME_CLIENTE as CLIENTE,e.REFERENCIA as REF,e.DESIGNACAO_REF as NOME_REF,d.ID,e.UTZ_CRIA as RESPONSAVEL,OBRIGA_EVIDENCIAS,d.TIPO,5,null,'D' SUB_MODULO ,null ID_AMBITO,e.ESTADO	"
						+ "from QUA_DERROGACOES_PLANOS_ACCOES d inner join QUA_DERROGACOES e on d.ID_DERROGACAO = e.ID_DERROGACAO "
						
						+ " union select e.ID_REUNIAO,null as CLIENTE,null as REF,null as NOME_REF,d.ID,e.UTZ_CRIA as RESPONSAVEL,OBRIGA_EVIDENCIAS,d.TIPO,19,null,'R' SUB_MODULO ,e.ID_AMBITO ID_AMBITO,null ESTADO	"
						+ "from REU_REUNIOES_PLANOS_ACCOES d inner join REU_REUNIOES e on d.ID_REUNIAO = e.ID_REUNIAO "
						
						+ "select (select DESCRICAO_PT from GT_DIC_TAREFAS where ID = a.ID_ACCAO) as NOME_TAREFA, "
						+ "(select NOME_UTILIZADOR from GER_UTILIZADORES where ID_UTILIZADOR = CASE WHEN a.ID_MODULO is null THEN  a.UTZ_CRIA ELSE b.RESPONSAVEL END) as UTILIZADOR_ORIGEM, a.DATA_CRIA as DATA_ATRIBUICAO, "
						+ "(select NOME_UTILIZADOR from GER_UTILIZADORES where ID_UTILIZADOR = a.UTZ_ID) as ATRIBUIDO, (select NOME_UTILIZADOR from GER_UTILIZADORES where ID_UTILIZADOR = a.UTZ_ENCAMINHADO) as ENCAMINHADO,"
						+ "a.DATA_ENCAMINHADO, a.DATA_FIM,  (select c.DESCRICAO from RC_DIC_GRAU_IMPORTANCIA c where c.ID = a.PRIORIDADE) as PRIORIDADE, a.ESTADO, b.CLIENTE, b.REF, "
						+ "b.NOME_REF, a.DATA_CONCLUSAO, (select NOME_UTILIZADOR from GER_UTILIZADORES where ID_UTILIZADOR = a.UTZ_CONCLUSAO) as UTILIZADOR_CONCLUIU,a.ID_TAREFA "
						+ ",b.ID_RECLAMACAO ,CASE WHEN a.UTZ_TIPO = 'g' THEN (select g.DESCRICAO from GER_GRUPO g where g.ID= a.UTZ_ID) WHEN  a.UTZ_TIPO = 's' THEN (select s.DES_SECTOR from RH_SECTORES s where s.COD_SECTOR= a.UTZ_ID)  ELSE null   END,a.PERCENTAGEM_CONCLUSAO,a.DESCRICAO,a.TEMPO_GASTO, "
						+ "a.UTZ_ENCAMINHADO,a.OBSERVACOES,a.ID_CAMPO,a.UTZ_ID,b.RESPONSAVEL,b.OBRIGA_EVIDENCIAS, "
						+ "(select EMAIL from GER_UTILIZADORES where ID_UTILIZADOR = CASE WHEN a.ID_MODULO is null THEN  a.UTZ_CRIA ELSE b.RESPONSAVEL END) as UTILIZADOR_ORIGEM_EMAIL,b.TIPO,a.ID_MODULO,a.SUB_MODULO,(select DESCRICAO from GER_DEPARTAMENTO where ID = CASE WHEN a.ID_MODULO is null THEN (Select top 1  d.ID_DEPARTAMENTO from GER_UTILIZADORES xa "
						+ "left join RH_FUNCIONARIOS b on CASE WHEN xa.COD_UTZ = '9889' THEN 0 ELSE xa.COD_UTZ END = b.COD_FUNC_ORIGEM left join RH_SECTORES c on b.COD_SECTOR = c.COD_SECTOR left join GER_DEPARTAMENTOS_SECTORES d on c.COD_SECTOR = d.COD_SECTOR "
						+ "where xa.ID_UTILIZADOR = a.UTZ_CRIA) ELSE b.DEPARTAMENTO END) NOME_DEPARTAMENTO, a.MOTIVO_REJEICAO,a.JUSTIFICACAO_ALTERACAO_ESTADO,a.ID_TAREFA_PAI   "
						+ " ,(select count(*) from GT_MOV_TAREFAS x where x.ID_TAREFA_PAI = a.ID_TAREFA ) subtarefas , case when a.ID_MODULO = 19 then d.DESCRICAO else c.DESCRICAO end as AMBITO,a.UTZ_CRIA,a.ID_ACCAO,b.ESTADO ESTADO_ORIGEM "
						+ "from  GT_MOV_TAREFAS a  left join " + " @TABELA_TEMP b "
						+ "on a.ID_CAMPO = b.ID and b.MODULO = a.ID_MODULO and b.SUB_MODULO = a.SUB_MODULO "
						+ "left join PA_DIC_AMBITOS c on b.ID_AMBITO = c.ID_AMBITO and a.ID_MODULO = 13 "
						+ "left join REU_AMBITOS_REUNIOES d on b.ID_AMBITO = d.ID_AMBITO and a.ID_MODULO = 19 "
						+ "LEFT JOIN  (SELECT Count(*) total,ID_PLANO_CAB FROM pe_planos_associados x group by x.ID_PLANO_CAB) x on x.ID_PLANO_CAB = b.id_reclamacao " 
						+ "where ((not " + varquery + " != 0) or (a.ESTADO in (" + estado + ")))  "
						+ query_utilizador + "  AND a.INATIVO != 1 " + "and ((not " + varquery2
						+ " != 0) or (a.DATA_CRIA <= ('" + datacria2 + " 23:59:59'))) and ((not " + varquery1
						+ " != 0) or (a.DATA_CRIA >= ('" + datacria1 + "'))) " + "and ((not " + varquery4
						+ " != 0) or (a.DATA_FIM <= ('" + datafim2 + " 23:59:59'))) and ((not " + varquery3
						+ " != 0) or (a.DATA_FIM >= ('" + datafim1 + "'))) " 
						+ "and ((not " + varquery5+ " != 0) or (a.ID_ACCAO in (" + accao + "))) "
						+ "and ((not " + varquery11+ " != 0) or (b.ID_AMBITO in (" + ambitos + "))) "
						+ "and ((not " + varquery6+ " != 0) or (a.ID_TAREFA = " + id + "))  and ((not " + varquery9
						+ " != 0) or (a.ID_TAREFA_PAI = " + idsubtarefa + ")) "
						+ " and ((not " + varquery7 + " != 0) or (a.ID_MODULO = "+modulo+")) "
						+ " and ((not " + varquery8 + " != 0) or (a.SUB_MODULO = '"+submodulo+"'))"
						+ " and (( (" + varquery10 + " != 1 )) or (a.ID_MODULO = 13 AND isnull(x.total,0)   > 0 ))"
						+ " and (( (" + varquery10 + " != 2 )) or (a.ID_MODULO = 13 AND isnull(x.total,0) = 0 ))");

		// query.setParameter("query2", estado);
		List<GT_MOV_TAREFAS> data = query.getResultList();
		return data;

	}

	public List<GT_MOV_TAREFAS> getall() {

		Query query = entityManager.createQuery("Select a from GT_MOV_TAREFAS a ");
		List<GT_MOV_TAREFAS> data = query.getResultList();
		return data;

	}

}
