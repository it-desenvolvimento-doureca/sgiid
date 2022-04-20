package pt.example.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

import pt.example.bootstrap.ConnectionSQL;
import pt.example.dao.AB_DIC_DOSIFICACAODao;
import pt.example.dao.AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAODao;
import pt.example.dao.AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORESDao;
import pt.example.dao.AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOSDao;
import pt.example.dao.AB_MOV_MANUTENCAO_DOSIFICADORESDao;
import pt.example.dao.AT_DIC_CAUSAS_ACIDENTEDao;
import pt.example.dao.COM_ACORDOSDao;
import pt.example.dao.COM_ACORDOS_ACTIVIDADESDao;
import pt.example.dao.COM_ACORDOS_AMORTIZACOESDao;
import pt.example.dao.COM_ACORDOS_ANEXOSDao;
import pt.example.dao.COM_ACORDOS_HISTORICODao;
import pt.example.dao.COM_ACORDOS_LTADao;
import pt.example.dao.COM_ACORDOS_PRECOSDao;
import pt.example.dao.COM_ACORDOS_VOLUMESDao;
import pt.example.dao.COM_CONTRATOSDao;
import pt.example.dao.COM_REFERENCIASDao;
import pt.example.dao.COM_REFERENCIAS_SILVERDao;
import pt.example.dao.CO_ANALISE_CLIENTESDao;
import pt.example.dao.CO_ANALISE_CLIENTES_ACCOESDao;
import pt.example.dao.CO_ANALISE_CLIENTES_OBSERVACOESDao;
import pt.example.dao.CO_ANALISE_CLIENTES_QUANTIDADEDao;
import pt.example.dao.FIN_DIC_PARAMETROS_SEGUIMENTODao;
import pt.example.dao.GER_FAVORITOSDao;
import pt.example.dao.GER_REFERENCIAS_FASTRESPONSE_REJEICOESDao;
import pt.example.dao.LG_ANALISE_ENVIOSDao;
import pt.example.dao.MAN_DIC_AMBITOSDao;
import pt.example.dao.MAN_DIC_AMBITO_UTILIZADORESDao;
import pt.example.dao.MAN_DIC_DIVISOESDao;
import pt.example.dao.MAN_DIC_EDIFICIOSDao;
import pt.example.dao.MAN_DIC_EQUIPASDao;
import pt.example.dao.MAN_DIC_EQUIPAS_UTILIZADORESDao;
import pt.example.dao.MAN_DIC_NIVEIS_CRITICIDADEDao;
import pt.example.dao.MAN_DIC_PISOSDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_ACCOESDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_ANEXOSDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_CABDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_COMPONENTESDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTEDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_DADOS_COMPRADao;
import pt.example.dao.MAN_MOV_MANUTENCAO_DOCUMENTOSDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_EQUIPAMENTOSDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIADao;
import pt.example.dao.MAN_MOV_MANUTENCAO_LISTA_MATERIALDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_OPERARIOSDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_PAUSASDao;
import pt.example.dao.MAN_MOV_MANUTENCAO_PLANOSDao;
import pt.example.dao.MAN_MOV_MAQUINAS_PARADASDao;
import pt.example.dao.MAN_MOV_PEDIDOSDao;
import pt.example.dao.MAN_MOV_PEDIDOS_DOCUMENTOSDao;
import pt.example.dao.PE_MOV_CABDao;
import pt.example.dao.PE_MOV_CAB_HISTORICODao;
import pt.example.dao.PE_MOV_FICHEIROSDao;
import pt.example.dao.PR_DIC_CAPACIDADE_ACABAMENTODao;
import pt.example.dao.PR_DIC_CAPACIDADE_RACKSDao;
import pt.example.dao.PR_DIC_PRODUCAO_SEMANADao;
import pt.example.dao.PR_DIC_SECTORES_AGREGADORESDao;
import pt.example.dao.PR_DIC_SECTORES_AGREGADORES_LINHADao;
import pt.example.dao.PR_DIC_SEMANAS_ANALISEDao;
import pt.example.dao.PR_DIC_VALIDACAO_BASTIDORDao;
import pt.example.dao.PR_GESTAO_BARRASDao;
import pt.example.dao.PR_GESTAO_BARRAS_REFERENCIASDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_ANALISESDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOSDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_CABDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_LINHASDao;
import pt.example.dao.QUA_DERROGACOESDao;
import pt.example.dao.QUA_DERROGACOES_EQUIPADao;
import pt.example.dao.QUA_DERROGACOES_FICHEIROSDao;
import pt.example.dao.QUA_DERROGACOES_PLANOS_ACCOESDao;
import pt.example.dao.QUA_DIC_TIPOS_AUDITORIADao;
import pt.example.dao.QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTADao;
import pt.example.dao.QUA_MOV_AUDITORIASDao;
import pt.example.dao.RC_MOV_RECLAMACAO_CLIENTESDao;
import pt.example.dao.REU_AMBITOS_REUNIOESDao;
import pt.example.dao.REU_AMBITOS_REUNIOES_PARTICIPANTESDao;
import pt.example.dao.REU_REUNIOESDao;
import pt.example.dao.REU_REUNIOES_FICHEIROSDao;
import pt.example.dao.REU_REUNIOES_PARTICIPANTESDao;
import pt.example.dao.RH_DIC_EPIDao;
import pt.example.dao.RH_DIC_SECTORES_ABSENTISMODao;
import pt.example.dao.RH_DIC_SECTORES_ABSENTISMO_LINHADao;
import pt.example.entity.AB_DIC_DOSIFICACAO;
import pt.example.entity.AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO;
import pt.example.entity.AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES;
import pt.example.entity.AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS;
import pt.example.entity.AB_MOV_MANUTENCAO_DOSIFICADORES;
import pt.example.entity.AT_DIC_CAUSAS_ACIDENTE;
import pt.example.entity.COM_ACORDOS;
import pt.example.entity.COM_ACORDOS_ACTIVIDADES;
import pt.example.entity.COM_ACORDOS_AMORTIZACOES;
import pt.example.entity.COM_ACORDOS_ANEXOS;
import pt.example.entity.COM_ACORDOS_HISTORICO;
import pt.example.entity.COM_ACORDOS_LTA;
import pt.example.entity.COM_ACORDOS_PRECOS;
import pt.example.entity.COM_ACORDOS_VOLUMES;
import pt.example.entity.COM_CONTRATOS;
import pt.example.entity.COM_REFERENCIAS;
import pt.example.entity.COM_REFERENCIAS_SILVER;
import pt.example.entity.CO_ANALISE_CLIENTES;
import pt.example.entity.CO_ANALISE_CLIENTES_ACCOES;
import pt.example.entity.CO_ANALISE_CLIENTES_OBSERVACOES;
import pt.example.entity.CO_ANALISE_CLIENTES_QUANTIDADE;
import pt.example.entity.FIN_DIC_PARAMETROS_SEGUIMENTO;
import pt.example.entity.FIN_REGISTO_ACOES;
import pt.example.entity.GER_FAVORITOS;
import pt.example.entity.GER_REFERENCIAS_FASTRESPONSE_REJEICOES;
import pt.example.entity.GER_UTILIZADORES;
import pt.example.entity.LG_ANALISE_ENVIOS;
import pt.example.entity.MAN_DIC_AMBITOS;
import pt.example.entity.MAN_DIC_AMBITO_UTILIZADORES;
import pt.example.entity.MAN_DIC_DIVISOES;
import pt.example.entity.MAN_DIC_EDIFICIOS;
import pt.example.entity.MAN_DIC_EQUIPAS;
import pt.example.entity.MAN_DIC_EQUIPAS_UTILIZADORES;
import pt.example.entity.MAN_DIC_NIVEIS_CRITICIDADE;
import pt.example.entity.MAN_DIC_PISOS;
import pt.example.entity.MAN_MOV_MANUTENCAO_ACCOES;
import pt.example.entity.MAN_MOV_MANUTENCAO_ANEXOS;
import pt.example.entity.MAN_MOV_MANUTENCAO_CAB;
import pt.example.entity.MAN_MOV_MANUTENCAO_COMPONENTES;
import pt.example.entity.MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE;
import pt.example.entity.MAN_MOV_MANUTENCAO_DADOS_COMPRA;
import pt.example.entity.MAN_MOV_MANUTENCAO_DOCUMENTOS;
import pt.example.entity.MAN_MOV_MANUTENCAO_EQUIPAMENTOS;
import pt.example.entity.MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA;
import pt.example.entity.MAN_MOV_MANUTENCAO_LISTA_MATERIAL;
import pt.example.entity.MAN_MOV_MANUTENCAO_OPERARIOS;
import pt.example.entity.MAN_MOV_MANUTENCAO_PAUSAS;
import pt.example.entity.MAN_MOV_MANUTENCAO_PLANOS;
import pt.example.entity.MAN_MOV_MAQUINAS_PARADAS;
import pt.example.entity.MAN_MOV_PEDIDOS;
import pt.example.entity.MAN_MOV_PEDIDOS_DOCUMENTOS;
import pt.example.entity.PA_MOV_CAB;
import pt.example.entity.PA_MOV_FICHEIROS;
import pt.example.entity.PE_MOV_CAB;
import pt.example.entity.PE_MOV_CAB_HISTORICO;
import pt.example.entity.PE_MOV_FICHEIROS;
import pt.example.entity.PR_DIC_CAPACIDADE_ACABAMENTO;
import pt.example.entity.PR_DIC_CAPACIDADE_RACKS;
import pt.example.entity.PR_DIC_PRODUCAO_SEMANA;
import pt.example.entity.PR_DIC_SECTORES_AGREGADORES;
import pt.example.entity.PR_DIC_SECTORES_AGREGADORES_LINHA;
import pt.example.entity.PR_DIC_SEMANAS_ANALISE;
import pt.example.entity.PR_DIC_VALIDACAO_BASTIDOR;
import pt.example.entity.PR_GESTAO_BARRAS;
import pt.example.entity.PR_GESTAO_BARRAS_REFERENCIAS;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_ANALISES;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_CAB;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_LINHAS;
import pt.example.entity.QUA_DERROGACOES;
import pt.example.entity.QUA_DERROGACOES_EQUIPA;
import pt.example.entity.QUA_DERROGACOES_FICHEIROS;
import pt.example.entity.QUA_DERROGACOES_PLANOS_ACCOES;
import pt.example.entity.QUA_DIC_TIPOS_AUDITORIA;
import pt.example.entity.QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA;
import pt.example.entity.QUA_MOV_AUDITORIAS;
import pt.example.entity.RC_MOV_RECLAMACAO_CLIENTES;
import pt.example.entity.REU_AMBITOS_REUNIOES;
import pt.example.entity.REU_AMBITOS_REUNIOES_PARTICIPANTES;
import pt.example.entity.REU_REUNIOES;
import pt.example.entity.REU_REUNIOES_FICHEIROS;
import pt.example.entity.REU_REUNIOES_PARTICIPANTES;
import pt.example.entity.RH_DIC_EPI;
import pt.example.entity.RH_DIC_SECTORES_ABSENTISMO;
import pt.example.entity.RH_DIC_SECTORES_ABSENTISMO_LINHA;

@Stateless
@Path("/sirb")
public class SIRB_2 {

	@Inject
	private PR_DIC_PRODUCAO_SEMANADao dao1;
	@Inject
	private PR_DIC_CAPACIDADE_ACABAMENTODao dao2;
	@Inject
	private PR_DIC_CAPACIDADE_RACKSDao dao3;
	@Inject
	private PR_DIC_SEMANAS_ANALISEDao dao4;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_CABDao dao5;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_LINHASDao dao6;
	@Inject
	private PR_DIC_SECTORES_AGREGADORES_LINHADao dao7;
	@Inject
	private PR_DIC_SECTORES_AGREGADORESDao dao8;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_ANALISESDao dao9;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOSDao dao10;
	@Inject
	private PR_GESTAO_BARRASDao dao11;
	@Inject
	private PR_GESTAO_BARRAS_REFERENCIASDao dao12;
	@Inject
	private PR_DIC_VALIDACAO_BASTIDORDao dao13;
	@Inject
	private GER_FAVORITOSDao dao14;
	@Inject
	private FIN_DIC_PARAMETROS_SEGUIMENTODao dao15;
	@Inject
	private AT_DIC_CAUSAS_ACIDENTEDao dao16;
	@Inject
	private RH_DIC_EPIDao dao17;
	@Inject
	private RC_MOV_RECLAMACAO_CLIENTESDao dao18;
	@Inject
	private QUA_DIC_TIPOS_AUDITORIADao dao19;
	@Inject
	private QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTADao dao20;
	@Inject
	private QUA_MOV_AUDITORIASDao dao21;
	@Inject
	private QUA_DERROGACOESDao dao22;
	@Inject
	private GER_REFERENCIAS_FASTRESPONSE_REJEICOESDao dao23;
	@Inject
	private RH_DIC_SECTORES_ABSENTISMODao dao24;
	@Inject
	private RH_DIC_SECTORES_ABSENTISMO_LINHADao dao25;
	@Inject
	private CO_ANALISE_CLIENTESDao dao26;
	@Inject
	private CO_ANALISE_CLIENTES_QUANTIDADEDao dao27;
	@Inject
	private CO_ANALISE_CLIENTES_OBSERVACOESDao dao28;
	@Inject
	private CO_ANALISE_CLIENTES_ACCOESDao dao29;
	@Inject
	private QUA_DERROGACOES_FICHEIROSDao dao30;
	@Inject
	private QUA_DERROGACOES_PLANOS_ACCOESDao dao31;
	@Inject
	private QUA_DERROGACOES_EQUIPADao dao32;

	@Inject
	private REU_AMBITOS_REUNIOESDao dao33;
	@Inject
	private REU_AMBITOS_REUNIOES_PARTICIPANTESDao dao34;
	@Inject
	private REU_REUNIOESDao dao35;
	@Inject
	private REU_REUNIOES_PARTICIPANTESDao dao36;
	@Inject
	private REU_REUNIOES_FICHEIROSDao dao37;
	@Inject
	private LG_ANALISE_ENVIOSDao dao38;

	@Inject
	private MAN_MOV_MANUTENCAO_CABDao dao39;
	@Inject
	private MAN_MOV_MANUTENCAO_OPERARIOSDao dao40;
	@Inject
	private MAN_MOV_MANUTENCAO_PAUSASDao dao41;
	@Inject
	private MAN_MOV_MANUTENCAO_ACCOESDao dao42;
	@Inject
	private MAN_MOV_MANUTENCAO_LISTA_MATERIALDao dao43;
	@Inject
	private MAN_DIC_DIVISOESDao dao44;
	@Inject
	private MAN_DIC_EDIFICIOSDao dao45;
	@Inject
	private MAN_DIC_PISOSDao dao46;
	@Inject
	private MAN_MOV_MANUTENCAO_COMPONENTESDao dao47;
	@Inject
	private MAN_MOV_MANUTENCAO_DOCUMENTOSDao dao48;
	@Inject
	private MAN_MOV_MANUTENCAO_PLANOSDao dao49;
	@Inject
	private MAN_DIC_EQUIPAS_UTILIZADORESDao dao50;
	@Inject
	private MAN_MOV_MANUTENCAO_EQUIPAMENTOSDao dao51;
	@Inject
	private MAN_MOV_MANUTENCAO_DADOS_COMPRADao dao52;
	@Inject
	private MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTEDao dao53;
	@Inject
	private MAN_DIC_EQUIPASDao dao54;
	@Inject
	private MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIADao dao55;
	@Inject
	private MAN_MOV_PEDIDOSDao dao56;
	@Inject
	private MAN_MOV_PEDIDOS_DOCUMENTOSDao dao57;
	@Inject
	private MAN_MOV_MANUTENCAO_ANEXOSDao dao58;
	@Inject
	private COM_ACORDOSDao dao59;
	@Inject
	private COM_ACORDOS_ACTIVIDADESDao dao60;
	@Inject
	private COM_ACORDOS_AMORTIZACOESDao dao61;
	@Inject
	private COM_ACORDOS_ANEXOSDao dao62;
	@Inject
	private COM_ACORDOS_HISTORICODao dao63;
	@Inject
	private COM_ACORDOS_LTADao dao64;
	@Inject
	private COM_ACORDOS_PRECOSDao dao65;
	@Inject
	private COM_ACORDOS_VOLUMESDao dao66;
	@Inject
	private COM_CONTRATOSDao dao67;
	@Inject
	private COM_REFERENCIASDao dao68;
	@Inject
	private COM_REFERENCIAS_SILVERDao dao69;
	@Inject
	private AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORESDao dao70;
	@Inject
	private AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOSDao dao71;
	@Inject
	private AB_DIC_DOSIFICACAODao dao72;
	@Inject
	private AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAODao dao73;
	@Inject
	private AB_MOV_MANUTENCAO_DOSIFICADORESDao dao74;
	@Inject
	private PE_MOV_CABDao dao75;
	@Inject
	private PE_MOV_FICHEIROSDao dao76;
	@Inject
	private PE_MOV_CAB_HISTORICODao dao77;
	@Inject
	private MAN_MOV_MAQUINAS_PARADASDao dao78;
	@Inject
	private MAN_DIC_NIVEIS_CRITICIDADEDao dao79;
	@Inject
	private MAN_DIC_AMBITO_UTILIZADORESDao dao80;
	@Inject
	private MAN_DIC_AMBITOSDao dao81;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	/************************************* PR_DIC_PRODUCAO_SEMANA */

	@POST
	@Path("/createPR_DIC_PRODUCAO_SEMANA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_PRODUCAO_SEMANA insertPR_DIC_PRODUCAO_SEMANAA(final PR_DIC_PRODUCAO_SEMANA data) {
		return dao1.create(data);
	}

	@GET
	@Path("/getPR_DIC_PRODUCAO_SEMANA")
	@Produces("application/json")
	public List<PR_DIC_PRODUCAO_SEMANA> getPR_DIC_PRODUCAO_SEMANA() {
		return dao1.getall();
	}

	@GET
	@Path("/getPR_DIC_PRODUCAO_SEMANAbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_PRODUCAO_SEMANA> getPR_DIC_PRODUCAO_SEMANAbyip(@PathParam("id") Integer id) {
		return dao1.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_PRODUCAO_SEMANA/{id}")
	public void deletePR_DIC_PRODUCAO_SEMANA(@PathParam("id") Integer id) {
		PR_DIC_PRODUCAO_SEMANA PR_DIC_PRODUCAO_SEMANA = new PR_DIC_PRODUCAO_SEMANA();
		PR_DIC_PRODUCAO_SEMANA.setID_PRODUCAO_SEMANA(id);
		dao1.delete(PR_DIC_PRODUCAO_SEMANA);
	}

	@PUT
	@Path("/updatePR_DIC_PRODUCAO_SEMANA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_PRODUCAO_SEMANA updatePR_DIC_PRODUCAO_SEMANA(final PR_DIC_PRODUCAO_SEMANA PR_DIC_PRODUCAO_SEMANA) {
		PR_DIC_PRODUCAO_SEMANA.setID_PRODUCAO_SEMANA(PR_DIC_PRODUCAO_SEMANA.getID_PRODUCAO_SEMANA());
		return dao1.update(PR_DIC_PRODUCAO_SEMANA);
	}

	/************************************* PR_DIC_CAPACIDADE_ACABAMENTO */

	@POST
	@Path("/createPR_DIC_CAPACIDADE_ACABAMENTO")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_CAPACIDADE_ACABAMENTO insertPR_DIC_CAPACIDADE_ACABAMENTOA(final PR_DIC_CAPACIDADE_ACABAMENTO data) {
		return dao2.create(data);
	}

	@GET
	@Path("/getPR_DIC_CAPACIDADE_ACABAMENTO")
	@Produces("application/json")
	public List<PR_DIC_CAPACIDADE_ACABAMENTO> getPR_DIC_CAPACIDADE_ACABAMENTO() {
		return dao2.getall();
	}

	@GET
	@Path("/getPR_DIC_CAPACIDADE_ACABAMENTObyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_CAPACIDADE_ACABAMENTO> getPR_DIC_CAPACIDADE_ACABAMENTObyip(@PathParam("id") Integer id) {
		return dao2.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_CAPACIDADE_ACABAMENTO/{id}")
	public void deletePR_DIC_CAPACIDADE_ACABAMENTO(@PathParam("id") Integer id) {
		PR_DIC_CAPACIDADE_ACABAMENTO PR_DIC_CAPACIDADE_ACABAMENTO = new PR_DIC_CAPACIDADE_ACABAMENTO();
		PR_DIC_CAPACIDADE_ACABAMENTO.setID_CAPACIDADE_ACABAMENTO(id);
		dao2.delete(PR_DIC_CAPACIDADE_ACABAMENTO);
	}

	@PUT
	@Path("/updatePR_DIC_CAPACIDADE_ACABAMENTO")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_CAPACIDADE_ACABAMENTO updatePR_DIC_CAPACIDADE_ACABAMENTO(
			final PR_DIC_CAPACIDADE_ACABAMENTO PR_DIC_CAPACIDADE_ACABAMENTO) {
		PR_DIC_CAPACIDADE_ACABAMENTO
				.setID_CAPACIDADE_ACABAMENTO(PR_DIC_CAPACIDADE_ACABAMENTO.getID_CAPACIDADE_ACABAMENTO());
		return dao2.update(PR_DIC_CAPACIDADE_ACABAMENTO);
	}

	/************************************* PR_DIC_CAPACIDADE_RACKS */

	@POST
	@Path("/createPR_DIC_CAPACIDADE_RACKS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_CAPACIDADE_RACKS insertPR_DIC_CAPACIDADE_RACKSA(final PR_DIC_CAPACIDADE_RACKS data) {
		return dao3.create(data);
	}

	@GET
	@Path("/getPR_DIC_CAPACIDADE_RACKS")
	@Produces("application/json")
	public List<PR_DIC_CAPACIDADE_RACKS> getPR_DIC_CAPACIDADE_RACKS() {
		return dao3.getall();
	}

	@GET
	@Path("/getPR_DIC_CAPACIDADE_RACKSbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_CAPACIDADE_RACKS> getPR_DIC_CAPACIDADE_RACKSbyip(@PathParam("id") Integer id) {
		return dao3.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_CAPACIDADE_RACKS/{id}")
	public void deletePR_DIC_CAPACIDADE_RACKS(@PathParam("id") Integer id) {
		PR_DIC_CAPACIDADE_RACKS PR_DIC_CAPACIDADE_RACKS = new PR_DIC_CAPACIDADE_RACKS();
		PR_DIC_CAPACIDADE_RACKS.setID_CAPACIDADE_RACKS(id);
		dao3.delete(PR_DIC_CAPACIDADE_RACKS);
	}

	@PUT
	@Path("/updatePR_DIC_CAPACIDADE_RACKS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_CAPACIDADE_RACKS updatePR_DIC_CAPACIDADE_RACKS(
			final PR_DIC_CAPACIDADE_RACKS PR_DIC_CAPACIDADE_RACKS) {
		PR_DIC_CAPACIDADE_RACKS.setID_CAPACIDADE_RACKS(PR_DIC_CAPACIDADE_RACKS.getID_CAPACIDADE_RACKS());
		return dao3.update(PR_DIC_CAPACIDADE_RACKS);
	}

	/************************************* PR_DIC_SEMANAS_ANALISE */

	@POST
	@Path("/createPR_DIC_SEMANAS_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SEMANAS_ANALISE insertPR_DIC_SEMANAS_ANALISEA(final PR_DIC_SEMANAS_ANALISE data) {
		return dao4.create(data);
	}

	@GET
	@Path("/getPR_DIC_SEMANAS_ANALISE")
	@Produces("application/json")
	public List<PR_DIC_SEMANAS_ANALISE> getPR_DIC_SEMANAS_ANALISE() {
		return dao4.getall();
	}

	@GET
	@Path("/getPR_DIC_SEMANAS_ANALISEbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_SEMANAS_ANALISE> getPR_DIC_SEMANAS_ANALISEbyip(@PathParam("id") Integer id) {
		return dao4.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_SEMANAS_ANALISE/{id}")
	public void deletePR_DIC_SEMANAS_ANALISE(@PathParam("id") Integer id) {
		PR_DIC_SEMANAS_ANALISE PR_DIC_SEMANAS_ANALISE = new PR_DIC_SEMANAS_ANALISE();
		PR_DIC_SEMANAS_ANALISE.setID_SEMANAS_ANALISE(id);
		dao4.delete(PR_DIC_SEMANAS_ANALISE);
	}

	@PUT
	@Path("/updatePR_DIC_SEMANAS_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SEMANAS_ANALISE updatePR_DIC_SEMANAS_ANALISE(final PR_DIC_SEMANAS_ANALISE PR_DIC_SEMANAS_ANALISE) {
		PR_DIC_SEMANAS_ANALISE.setID_SEMANAS_ANALISE(PR_DIC_SEMANAS_ANALISE.getID_SEMANAS_ANALISE());
		return dao4.update(PR_DIC_SEMANAS_ANALISE);
	}

	@GET
	@Path("/GET_QUANT_RACKS_BARRAS")
	@Produces("application/json")
	public List<Object[]> GET_QUANT_RACKS_BARRAS() {

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_QUANT_RACKS_BARRAS]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/GET_TIPO_ACABAMENTO")
	@Produces("application/json")
	public List<Object[]> GET_TIPO_ACABAMENTO() {

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_TIPO_ACABAMENTO]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_LISTA_MRPS")
	@Produces("application/json")
	public List<Object[]> GET_LISTA_MRPS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_LISTA_MRPS] '" + DATA + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_LISTA_MRPS_PARA_PRODUCAO")
	@Produces("application/json")
	public List<Object[]> GET_LISTA_MRPS_PARA_PRODUCAO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA = firstMap.get("DATA");
		String PRIMEIRA_SEMANA = firstMap.get("PRIMEIRA_SEMANA");
		String N_MRP = firstMap.get("N_MRP");
		String LINHA = firstMap.get("LINHA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_LISTA_MRPS_PARA_PRODUCAO] '" + DATA + "',"
				+ PRIMEIRA_SEMANA + "," + N_MRP + "," + LINHA + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_CAB */

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_CAB insertPR_PLANEAMENTO_PRODUCAO_CABA(final PR_PLANEAMENTO_PRODUCAO_CAB data) {
		return dao5.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_CAB")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_CAB> getPR_PLANEAMENTO_PRODUCAO_CAB() {
		return dao5.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_CAB2")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_CAB> getPR_PLANEAMENTO_PRODUCAO_CAB2() {
		return dao5.getall2();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_CABbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_CAB> getPR_PLANEAMENTO_PRODUCAO_CABbyip(@PathParam("id") Integer id) {
		return dao5.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_CAB/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_CAB(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_CAB PR_PLANEAMENTO_PRODUCAO_CAB = new PR_PLANEAMENTO_PRODUCAO_CAB();
		PR_PLANEAMENTO_PRODUCAO_CAB.setID_PLANEAMENTO_PRODUCAO_CAB(id);
		dao5.delete(PR_PLANEAMENTO_PRODUCAO_CAB);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_CAB updatePR_PLANEAMENTO_PRODUCAO_CAB(
			final PR_PLANEAMENTO_PRODUCAO_CAB PR_PLANEAMENTO_PRODUCAO_CAB) {
		PR_PLANEAMENTO_PRODUCAO_CAB
				.setID_PLANEAMENTO_PRODUCAO_CAB(PR_PLANEAMENTO_PRODUCAO_CAB.getID_PLANEAMENTO_PRODUCAO_CAB());
		return dao5.update(PR_PLANEAMENTO_PRODUCAO_CAB);
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_LINHAS */

	@POST
	@Path("/getPR_PLANEAMENTO_PRODUCAO_LINHAS_FILTRO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> getPR_PLANEAMENTO_PRODUCAO_LINHAS_FILTRO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID_PLANEAMENTO_PRODUCAO_CAB = firstMap.get("ID_PLANEAMENTO_PRODUCAO_CAB");
		String FASE = firstMap.get("FASE");
		String COD_REF = firstMap.get("COD_REF");
		String ACABAMENTO = firstMap.get("ACABAMENTO");
		String RACK = firstMap.get("RACK");
		String ANTECEDENCIA = firstMap.get("ANTECEDENCIA");
		String BARRAS_CAPACIDADE = firstMap.get("BARRAS_CAPACIDADE");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_PR_PLANEAMENTO_PRODUCAO_LINHAS_FILTRO] "
				+ ID_PLANEAMENTO_PRODUCAO_CAB + ",'" + COD_REF + "','" + FASE + "','" + ACABAMENTO + "','" + RACK + "',"
				+ ANTECEDENCIA + "," + BARRAS_CAPACIDADE + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_LINHAS insertPR_PLANEAMENTO_PRODUCAO_LINHASA(
			final PR_PLANEAMENTO_PRODUCAO_LINHAS data) {
		return dao6.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_LINHAS")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_LINHAS> getPR_PLANEAMENTO_PRODUCAO_LINHAS() {
		return dao6.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_LINHASbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_LINHAS> getPR_PLANEAMENTO_PRODUCAO_LINHASbyip(@PathParam("id") Integer id) {
		return dao6.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_LINHAS/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_LINHAS(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_LINHAS PR_PLANEAMENTO_PRODUCAO_LINHAS = new PR_PLANEAMENTO_PRODUCAO_LINHAS();
		PR_PLANEAMENTO_PRODUCAO_LINHAS.setID_PLANEAMENTO_PRODUCAO_LINHA(id);
		dao6.delete(PR_PLANEAMENTO_PRODUCAO_LINHAS);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_LINHAS updatePR_PLANEAMENTO_PRODUCAO_LINHAS(
			final PR_PLANEAMENTO_PRODUCAO_LINHAS PR_PLANEAMENTO_PRODUCAO_LINHAS) {
		PR_PLANEAMENTO_PRODUCAO_LINHAS
				.setID_PLANEAMENTO_PRODUCAO_LINHA(PR_PLANEAMENTO_PRODUCAO_LINHAS.getID_PLANEAMENTO_PRODUCAO_LINHA());
		return dao6.update(PR_PLANEAMENTO_PRODUCAO_LINHAS);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_LINHAS_CAMPOS")
	@Consumes("*/*")
	@Produces("application/json")
	public boolean updatePR_PLANEAMENTO_PRODUCAO_LINHAS_CAMPOS(
			final PR_PLANEAMENTO_PRODUCAO_LINHAS PR_PLANEAMENTO_PRODUCAO_LINHAS) {

		entityManager.createNativeQuery("UPDATE PR_PLANEAMENTO_PRODUCAO_LINHAS SET UTZ_MODIF= "
				+ PR_PLANEAMENTO_PRODUCAO_LINHAS.getUTZ_MODIF() + ", DATA_MODIF = GETDATE(),NUM_BARRAS_PLANO = "
				+ PR_PLANEAMENTO_PRODUCAO_LINHAS.getNUM_BARRAS_PLANO() + " WHERE ID_PLANEAMENTO_PRODUCAO_LINHA = "
				+ PR_PLANEAMENTO_PRODUCAO_LINHAS.getID_PLANEAMENTO_PRODUCAO_LINHA() + "").executeUpdate();
		return true;
	}

	/************************************* PR_DIC_SECTORES_AGREGADORES */

	@POST
	@Path("/createPR_DIC_SECTORES_AGREGADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SECTORES_AGREGADORES insertPR_DIC_SECTORES_AGREGADORESA(final PR_DIC_SECTORES_AGREGADORES data) {
		return dao8.create(data);
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORES")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES> getPR_DIC_SECTORES_AGREGADORES() {
		return dao8.getall();
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORESbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES> getPR_DIC_SECTORES_AGREGADORESbyip(@PathParam("id") Integer id) {
		return dao8.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_SECTORES_AGREGADORES/{id}")
	public void deletePR_DIC_SECTORES_AGREGADORES(@PathParam("id") Integer id) {
		PR_DIC_SECTORES_AGREGADORES PR_DIC_SECTORES_AGREGADORES = new PR_DIC_SECTORES_AGREGADORES();
		PR_DIC_SECTORES_AGREGADORES.setID_SECTOR_AGREGADOR(id);
		dao8.delete(PR_DIC_SECTORES_AGREGADORES);
	}

	@PUT
	@Path("/updatePR_DIC_SECTORES_AGREGADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SECTORES_AGREGADORES updatePR_DIC_SECTORES_AGREGADORES(
			final PR_DIC_SECTORES_AGREGADORES PR_DIC_SECTORES_AGREGADORES) {
		PR_DIC_SECTORES_AGREGADORES.setID_SECTOR_AGREGADOR(PR_DIC_SECTORES_AGREGADORES.getID_SECTOR_AGREGADOR());
		return dao8.update(PR_DIC_SECTORES_AGREGADORES);
	}

	/************************************* PR_DIC_SECTORES_AGREGADORES_LINHA */

	@POST
	@Path("/createPR_DIC_SECTORES_AGREGADORES_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SECTORES_AGREGADORES_LINHA insertPR_DIC_SECTORES_AGREGADORES_LINHAA(
			final PR_DIC_SECTORES_AGREGADORES_LINHA data) {
		return dao7.create(data);
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORES_LINHA")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getPR_DIC_SECTORES_AGREGADORES_LINHA() {
		return dao7.getall();
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORES_LINHAbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getPR_DIC_SECTORES_AGREGADORES_LINHAbyip(
			@PathParam("id") Integer id) {
		return dao7.getbyid(id);
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORES_LINHAgetSectoresAll/{id}/{linha}")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getSectoresAll(@PathParam("id") Integer id,
			@PathParam("linha") Integer linha) {
		return dao7.getSectoresAll(id, linha);
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORES_LINHAgetSectoresAgregadores/{id}/{linha}")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getSectoresAgregadores(@PathParam("id") Integer id,
			@PathParam("linha") Integer linha) {
		return dao7.getSectoresAgregadores(id, linha);
	}

	@DELETE
	@Path("/deletePR_DIC_SECTORES_AGREGADORES_LINHA/{id}")
	public void deletePR_DIC_SECTORES_AGREGADORES_LINHA(@PathParam("id") Integer id) {
		PR_DIC_SECTORES_AGREGADORES_LINHA PR_DIC_SECTORES_AGREGADORES_LINHA = new PR_DIC_SECTORES_AGREGADORES_LINHA();
		PR_DIC_SECTORES_AGREGADORES_LINHA.setID_SECTOR_AGREGADOR_LINHA(id);
		dao7.delete(PR_DIC_SECTORES_AGREGADORES_LINHA);
	}

	@PUT
	@Path("/updatePR_DIC_SECTORES_AGREGADORES_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SECTORES_AGREGADORES_LINHA updatePR_DIC_SECTORES_AGREGADORES_LINHA(
			final PR_DIC_SECTORES_AGREGADORES_LINHA PR_DIC_SECTORES_AGREGADORES_LINHA) {
		PR_DIC_SECTORES_AGREGADORES_LINHA
				.setID_SECTOR_AGREGADOR_LINHA(PR_DIC_SECTORES_AGREGADORES_LINHA.getID_SECTOR_AGREGADOR_LINHA());
		return dao7.update(PR_DIC_SECTORES_AGREGADORES_LINHA);
	}

	/************************************* PR_GESTAO_BARRAS */

	@POST
	@Path("/createPR_GESTAO_BARRAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_GESTAO_BARRAS insertPR_GESTAO_BARRASA(final PR_GESTAO_BARRAS data) {
		return dao11.create(data);
	}

	@GET
	@Path("/getPR_GESTAO_BARRAS")
	@Produces("application/json")
	public List<PR_GESTAO_BARRAS> getPR_GESTAO_BARRAS() {
		return dao11.getall();
	}

	@GET
	@Path("/getPR_GESTAO_BARRASbyid/{id}")
	@Produces("application/json")
	public List<PR_GESTAO_BARRAS> getPR_GESTAO_BARRASbyip(@PathParam("id") Integer id) {
		return dao11.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_GESTAO_BARRAS/{id}")
	public void deletePR_GESTAO_BARRAS(@PathParam("id") Integer id) {
		PR_GESTAO_BARRAS PR_GESTAO_BARRAS = new PR_GESTAO_BARRAS();
		PR_GESTAO_BARRAS.setID_GESTAO_BARRAS(id);
		dao11.delete(PR_GESTAO_BARRAS);
	}

	@PUT
	@Path("/updatePR_GESTAO_BARRAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_GESTAO_BARRAS updatePR_GESTAO_BARRAS(final PR_GESTAO_BARRAS PR_GESTAO_BARRAS) {
		PR_GESTAO_BARRAS.setID_GESTAO_BARRAS(PR_GESTAO_BARRAS.getID_GESTAO_BARRAS());
		return dao11.update(PR_GESTAO_BARRAS);
	}

	/************************************* PR_GESTAO_BARRAS_REFERENCIAS */

	@POST
	@Path("/createPR_GESTAO_BARRAS_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_GESTAO_BARRAS_REFERENCIAS insertPR_GESTAO_BARRAS_REFERENCIASA(final PR_GESTAO_BARRAS_REFERENCIAS data) {
		return dao12.create(data);
	}

	@GET
	@Path("/getPR_GESTAO_BARRAS_REFERENCIAS")
	@Produces("application/json")
	public List<PR_GESTAO_BARRAS_REFERENCIAS> getPR_GESTAO_BARRAS_REFERENCIAS() {
		return dao12.getall();
	}

	@GET
	@Path("/getPR_GESTAO_BARRAS_REFERENCIASbyid/{id}")
	@Produces("application/json")
	public List<PR_GESTAO_BARRAS_REFERENCIAS> getPR_GESTAO_BARRAS_REFERENCIASbyip(@PathParam("id") Integer id) {
		return dao12.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_GESTAO_BARRAS_REFERENCIAS/{id}")
	public void deletePR_GESTAO_BARRAS_REFERENCIAS(@PathParam("id") Integer id) {
		PR_GESTAO_BARRAS_REFERENCIAS PR_GESTAO_BARRAS_REFERENCIAS = new PR_GESTAO_BARRAS_REFERENCIAS();
		PR_GESTAO_BARRAS_REFERENCIAS.setID_GESTAO_BARRAS_REFERENCIAS(id);
		dao12.delete(PR_GESTAO_BARRAS_REFERENCIAS);
	}

	@PUT
	@Path("/updatePR_GESTAO_BARRAS_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_GESTAO_BARRAS_REFERENCIAS updatePR_GESTAO_BARRAS_REFERENCIAS(
			final PR_GESTAO_BARRAS_REFERENCIAS PR_GESTAO_BARRAS_REFERENCIAS) {
		PR_GESTAO_BARRAS_REFERENCIAS
				.setID_GESTAO_BARRAS_REFERENCIAS(PR_GESTAO_BARRAS_REFERENCIAS.getID_GESTAO_BARRAS_REFERENCIAS());
		return dao12.update(PR_GESTAO_BARRAS_REFERENCIAS);
	}

	/************************************* PR_DIC_VALIDACAO_BASTIDOR */

	@POST
	@Path("/createPR_DIC_VALIDACAO_BASTIDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_VALIDACAO_BASTIDOR insertPR_DIC_VALIDACAO_BASTIDORA(final PR_DIC_VALIDACAO_BASTIDOR data) {
		return dao13.create(data);
	}

	@GET
	@Path("/getPR_DIC_VALIDACAO_BASTIDOR")
	@Produces("application/json")
	public List<PR_DIC_VALIDACAO_BASTIDOR> getPR_DIC_VALIDACAO_BASTIDOR() {
		return dao13.getall();
	}

	@GET
	@Path("/getPR_DIC_VALIDACAO_BASTIDORbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_VALIDACAO_BASTIDOR> getPR_DIC_VALIDACAO_BASTIDORbyip(@PathParam("id") Integer id) {
		return dao13.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_VALIDACAO_BASTIDOR/{id}")
	public void deletePR_DIC_VALIDACAO_BASTIDOR(@PathParam("id") Integer id) {
		PR_DIC_VALIDACAO_BASTIDOR PR_DIC_VALIDACAO_BASTIDOR = new PR_DIC_VALIDACAO_BASTIDOR();
		PR_DIC_VALIDACAO_BASTIDOR.setID_VALIDACAO_BASTIDOR(id);
		dao13.delete(PR_DIC_VALIDACAO_BASTIDOR);
	}

	@PUT
	@Path("/updatePR_DIC_VALIDACAO_BASTIDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_VALIDACAO_BASTIDOR updatePR_DIC_VALIDACAO_BASTIDOR(
			final PR_DIC_VALIDACAO_BASTIDOR PR_DIC_VALIDACAO_BASTIDOR) {
		PR_DIC_VALIDACAO_BASTIDOR.setID_VALIDACAO_BASTIDOR(PR_DIC_VALIDACAO_BASTIDOR.getID_VALIDACAO_BASTIDOR());
		return dao13.update(PR_DIC_VALIDACAO_BASTIDOR);
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_ANALISES */

	@POST
	@Path("/GET_PLANOS")
	@Produces("application/json")
	public List<Object[]> GET_PLANOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String SEMANA = firstMap.get("SEMANA");

		Query query_folder = entityManager
				.createNativeQuery("DECLARE @SEMANA int = " + SEMANA + "; " + " DECLARE @ANO int = " + ANO + "; "
						+ "select a.ID_PLANEAMENTO_PRODUCAO_CAB,CAST(a.DATA_CRIA as date) DATA_CRIA ,a.DATA_MRP,a.N_MRP,a.ID_LINHA,a.ESTADO,a.NUMERO_SEMANAS "
						+ "from PR_PLANEAMENTO_PRODUCAO_CAB a "
						+ "where DATEPART(iso_week,a.DATA_CRIA) = @SEMANA and YEAR(a.DATA_CRIA) = @ANO and ativo = 1");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_BARRAS_PRODUZIR")
	@Produces("application/json")
	public List<Object[]> GET_BARRAS_PRODUZIR(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_BARRAS_PRODUZIR] '" + IDS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_TIPO_ACABAMENTO_ANALISE")
	@Produces("application/json")
	public List<Object[]> GET_TIPO_ACABAMENTO_ANALISE(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_TIPO_ACABAMENTO_ANALISE] '" + IDS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_RACKS_ANALISES")
	@Produces("application/json")
	public List<Object[]> GET_RACKS_ANALISES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");
		String FILTRO = firstMap.get("FILTRO");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_RACKS_ANALISES] '" + IDS + "'," + FILTRO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_RACKS_REFERENCIAS_ANALISES")
	@Produces("application/json")
	public List<Object[]> GET_RACKS_REFERENCIAS_ANALISES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");
		String RACK = firstMap.get("RACK");

		Query query_folder = entityManager
				.createNativeQuery("EXEC [GET_RACKS_REFERENCIAS_ANALISES] '" + IDS + "','" + RACK + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_RACKS_MONTADOS_ANALISES")
	@Produces("application/json")
	public List<Object[]> GET_RACKS_MONTADOS_ANALISES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");
		String FILTRO = firstMap.get("FILTRO");

		Query query_folder = entityManager
				.createNativeQuery("EXEC [GET_RACKS_MONTADOS_ANALISES] '" + IDS + "'," + FILTRO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_RACKS_MONTADOS_REFERENCIAS_ANALISES")
	@Produces("application/json")
	public List<Object[]> GET_RACKS_MONTADOS_REFERENCIAS_ANALISES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");
		String RACK = firstMap.get("RACK");

		Query query_folder = entityManager
				.createNativeQuery("EXEC [GET_RACKS_MONTADOS_REFERENCIAS_ANALISES] '" + IDS + "','" + RACK + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/ATUALIZAR_RECURSOS_HUMANOS")
	@Produces("application/json")
	public List<Object[]> ATUALIZAR_RECURSOS_HUMANOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");
		String ID_ANALISE = firstMap.get("ID_ANALISE");
		String N_SEMANAS = firstMap.get("N_SEMANAS");
		String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC [ATUALIZAR_RECURSOS_HUMANOS] '" + IDS + "'," + ID_ANALISE + "," + N_SEMANAS + ",'" + DATA + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_GESTAO_BARRAS")
	@Produces("application/json")
	public List<Object[]> GET_GESTAO_BARRAS(final List<HashMap<String, String>> dados) {
		/*
		 * HashMap<String, String> firstMap = dados.get(0); String IDS =
		 * firstMap.get("IDS"); String ID_ANALISE = firstMap.get("ID_ANALISE");
		 * String N_SEMANAS = firstMap.get("N_SEMANAS"); String DATA =
		 * firstMap.get("DATA");
		 */

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_GESTAO_BARRAS] ");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/DUPLICAR_GESTAO_BARRAS")
	@Produces("application/json")
	public List<Object[]> DUPLICAR_GESTAO_BARRAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ID = firstMap.get("ID");
		String ID_USER = firstMap.get("ID_USER");
		/*
		 * String ID_ANALISE = firstMap.get("ID");
		 * 
		 * String N_SEMANAS = firstMap.get("N_SEMANAS"); String DATA =
		 * firstMap.get("DATA");
		 */

		Query query_folder = entityManager.createNativeQuery("EXEC [DUPLICAR_GESTAO_BARRAS] " + ID + "," + ID_USER);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_ANALISES")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_ANALISES insertPR_PLANEAMENTO_PRODUCAO_ANALISESA(
			final PR_PLANEAMENTO_PRODUCAO_ANALISES data) {
		return dao9.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_ANALISES")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES> getPR_PLANEAMENTO_PRODUCAO_ANALISES() {
		return dao9.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_ANALISES2")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES> getPR_PLANEAMENTO_PRODUCAO_ANALISES2() {
		return dao9.getall2();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_ANALISESbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES> getPR_PLANEAMENTO_PRODUCAO_ANALISESbyip(@PathParam("id") Integer id) {
		return dao9.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_ANALISES/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_ANALISES(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_ANALISES PR_PLANEAMENTO_PRODUCAO_ANALISES = new PR_PLANEAMENTO_PRODUCAO_ANALISES();
		PR_PLANEAMENTO_PRODUCAO_ANALISES.setID_PLANEAMENTO_PRODUCAO_ANALISES(id);
		dao9.delete(PR_PLANEAMENTO_PRODUCAO_ANALISES);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_ANALISES")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_ANALISES updatePR_PLANEAMENTO_PRODUCAO_ANALISES(
			final PR_PLANEAMENTO_PRODUCAO_ANALISES PR_PLANEAMENTO_PRODUCAO_ANALISES) {
		PR_PLANEAMENTO_PRODUCAO_ANALISES.setID_PLANEAMENTO_PRODUCAO_ANALISES(
				PR_PLANEAMENTO_PRODUCAO_ANALISES.getID_PLANEAMENTO_PRODUCAO_ANALISES());
		return dao9.update(PR_PLANEAMENTO_PRODUCAO_ANALISES);
	}

	@POST
	@Path("/GET_DIVIDAS_LISTA_RESUMO_KAM")
	@Produces("application/json")
	public List<Object[]> GET_DIVIDAS_LISTA_RESUMO_KAM(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_DIVIDAS_LISTA_RESUMO_KAM]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_DIVIDAS_LISTA_TOP_15")
	@Produces("application/json")
	public List<Object[]> GET_DIVIDAS_LISTA_TOP_15(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_DIVIDAS_LISTA_TOP_15]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_DIVIDAS_LISTA_TOP_15_DIVIDAS")
	@Produces("application/json")
	public List<Object[]> GET_DIVIDAS_LISTA_TOP_15_DIVIDAS(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_DIVIDAS_LISTA_TOP_15_DIVIDAS]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/INSERT_PR_PLANEAMENTO_PRODUCAO_LINHAS")
	@Produces("application/json")
	public List<Object[]> INSERT_PR_PLANEAMENTO_PRODUCAO_LINHAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID = firstMap.get("ID");

		Query query_folder = entityManager.createNativeQuery("EXEC [INSERT_PR_PLANEAMENTO_PRODUCAO_LINHAS] " + ID);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS */

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS insertPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOSA(
			final PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS data) {
		return dao10.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS> getPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS() {
		return dao10.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOSbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS> getPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOSbyip(
			@PathParam("id") Integer id) {
		return dao10.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS2/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS2(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS = new PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS();
		PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS.setID(id);
		dao10.delete(PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS(@PathParam("id") Integer id) {
		Query query = entityManager.createNativeQuery(
				"DELETE PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS where ID_PLANEAMENTO_PRODUCAO_ANALISES = "
						+ id);

		query.executeUpdate();

	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS updatePR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS(
			final PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS) {
		PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS
				.setID(PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS.getID());
		return dao10.update(PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS);
	}

	@POST
	@Path("/GET_SEMANAS_PLANEAMENTO")
	@Produces("application/json")
	public List<Object[]> GET_SEMANAS_PLANEAMENTO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA = firstMap.get("DATA");
		String NUMERO_SEMANAS = firstMap.get("NUMERO_SEMANAS");
		String NOVO = firstMap.get("NOVO");
		String ID = firstMap.get("ID");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC [GET_SEMANAS_PLANEAMENTO] '" + DATA + "'," + NUMERO_SEMANAS + "," + NOVO + "," + ID);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_FASES")
	@Produces("application/json")
	public List<Object[]> GET_FASES(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_FASES]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_RACKS")
	@Produces("application/json")
	public List<Object[]> GET_RACKS(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_RACKS]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getEvolucaoDefeitoRef")
	@Produces("application/json")
	public List<Object[]> getEvolucaoDefeitoRef(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String REF = firstMap.get("REF");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String DEFEITO = firstMap.get("DEFEITO");

		/*
		 * String HORA_INI = firstMap.get("HORA_INI"); String HORA_FIM =
		 * firstMap.get("HORA_FIM");
		 */

		Query query_folder = entityManager.createNativeQuery("EXEC SILVER_BI.dbo.QUERY_EVOLUCAO_DEFEITO_REF '" + REF
				+ "','" + DATA_INI + "','" + DATA_FIM + "','" + DEFEITO + "' ");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* GER_FAVORITOS */

	@POST
	@Path("/createGER_FAVORITOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_FAVORITOS insertGER_FAVORITOSA(final GER_FAVORITOS data) {
		return dao14.create(data);
	}

	@GET
	@Path("/getGER_FAVORITOSbyid/{id}")
	@Produces("application/json")
	public List<GER_FAVORITOS> getGER_FAVORITOSbyip(@PathParam("id") Integer id) {
		return dao14.getbyId(id);
	}

	@DELETE
	@Path("/deleteGER_FAVORITOS/{id}")
	public void deleteGER_FAVORITOS(@PathParam("id") Integer id) {
		GER_FAVORITOS GER_FAVORITOS = new GER_FAVORITOS();
		GER_FAVORITOS.setID_FAVORITO(id);
		dao14.delete(GER_FAVORITOS);
	}

	@PUT
	@Path("/updateGER_FAVORITOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_FAVORITOS updateGER_FAVORITOS(final GER_FAVORITOS GER_FAVORITOS) {
		GER_FAVORITOS.setID_FAVORITO(GER_FAVORITOS.getID_FAVORITO());
		return dao14.update(GER_FAVORITOS);
	}

	@GET
	@Path("/getUPDATETIPO_LISTA/{id}/{tipo_lista}")
	@Produces("application/json")
	public boolean getUPDATETIPO_LISTA(@PathParam("id") Integer id, @PathParam("tipo_lista") String tipo_lista) {
		entityManager.createNativeQuery("UPDATE GER_UTILIZADORES SET TIPO_LISTA_FAVORITOS = '" + tipo_lista
				+ "' WHERE ID_UTILIZADOR = " + id + "").executeUpdate();
		return true;
	}

	/************************************* FIN_DIC_PARAMETROS_SEGUIMENTO */

	@POST
	@Path("/createFIN_DIC_PARAMETROS_SEGUIMENTO")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_PARAMETROS_SEGUIMENTO insertFIN_DIC_PARAMETROS_SEGUIMENTOA(
			final FIN_DIC_PARAMETROS_SEGUIMENTO data) {
		return dao15.create(data);
	}

	@GET
	@Path("/getFIN_DIC_PARAMETROS_SEGUIMENTO")
	@Produces("application/json")
	public List<FIN_DIC_PARAMETROS_SEGUIMENTO> getFIN_DIC_PARAMETROS_SEGUIMENTO() {
		return dao15.getall();
	}

	@GET
	@Path("/getFIN_DIC_PARAMETROS_SEGUIMENTObyid/{id}")
	@Produces("application/json")
	public List<FIN_DIC_PARAMETROS_SEGUIMENTO> getFIN_DIC_PARAMETROS_SEGUIMENTObyip(@PathParam("id") Integer id) {
		return dao15.getbyid(id);
	}

	@DELETE
	@Path("/deleteFIN_DIC_PARAMETROS_SEGUIMENTO/{id}")
	public void deleteFIN_DIC_PARAMETROS_SEGUIMENTO(@PathParam("id") Integer id) {
		FIN_DIC_PARAMETROS_SEGUIMENTO FIN_DIC_PARAMETROS_SEGUIMENTO = new FIN_DIC_PARAMETROS_SEGUIMENTO();
		FIN_DIC_PARAMETROS_SEGUIMENTO.setID_ANALISE(id);
		dao15.delete(FIN_DIC_PARAMETROS_SEGUIMENTO);
	}

	@PUT
	@Path("/updateFIN_DIC_PARAMETROS_SEGUIMENTO")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_PARAMETROS_SEGUIMENTO updateFIN_DIC_PARAMETROS_SEGUIMENTO(
			final FIN_DIC_PARAMETROS_SEGUIMENTO FIN_DIC_PARAMETROS_SEGUIMENTO) {
		FIN_DIC_PARAMETROS_SEGUIMENTO.setID_ANALISE(FIN_DIC_PARAMETROS_SEGUIMENTO.getID_ANALISE());
		return dao15.update(FIN_DIC_PARAMETROS_SEGUIMENTO);
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_BUDGET")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_BUDGET(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_BUDGET " + ANO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_ANOS")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_ANOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_ANOS " + ANO + ",'"
				+ CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_ANOS_CLIENTES")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_ANOS_CLIENTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_ANOS_CLIENTES " + ANO + ",'"
				+ CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_ANOS_OEM_VEICULO_PROJETO")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_ANOS_OEM_VEICULO_PROJETO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_ANOS_OEM_VEICULO_PROJETO "
				+ ANO + ",'" + CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_ANOS_REFERENCIAS")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_ANOS_REFERENCIAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_ANOS_REFERENCIAS " + ANO
				+ ",'" + CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_DESCONTOS")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_DESCONTOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_DESCONTOS " + ANO + ",'"
				+ CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_DESCONTOS_CLIENTES")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_DESCONTOS_CLIENTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_DESCONTOS_CLIENTES " + ANO
				+ ",'" + CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_DESCONTOS_OEM_VEICULO_PROJETO")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_DESCONTOS_OEM_VEICULO_PROJETO(
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_DESCONTOS_OEM_VEICULO_PROJETO " + ANO + ",'"
						+ CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_DESCONTOS_REFERENCIAS")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_DESCONTOS_REFERENCIAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_DESCONTOS_REFERENCIAS "
				+ ANO + ",'" + CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_DEVOLUCOES")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_DEVOLUCOES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		// String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_DEVOLUCOES " + ANO + ",'"
				+ PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_DEVOLUCOES_OEM_VEICULO_PROJETO")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_DEVOLUCOES_OEM_VEICULO_PROJETO(
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		// String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_DEVOLUCOES_OEM_VEICULO_PROJETO " + ANO + ",'" + PROREF
						+ "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_DEVOLUCOES_REFERENCIAS")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_DEVOLUCOES_REFERENCIAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		// String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_DEVOLUCOES_REFERENCIAS "
				+ ANO + ",'" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_GENERICO")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_GENERICO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String ID_ANALISE = firstMap.get("ID_ANALISE");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_GENERICO " + ANO + ",'" + CLIENTES + "','" + PROREF
						+ "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "', " + ID_ANALISE + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_GENERICO_2")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_GENERICO_2(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String ID_ANALISE = firstMap.get("ID_ANALISE");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_GENERICO_2 " + ANO + ",'" + CLIENTES + "','" + PROREF
						+ "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'," + ID_ANALISE + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_GENERICO_2_CLIENTES")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_GENERICO_2_CLIENTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String ID_ANALISE = firstMap.get("ID_ANALISE");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_GENERICO_2_CLIENTES " + ANO + ",'" + CLIENTES + "','"
						+ PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'," + ID_ANALISE + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_GENERICO_2_OEM_VEICULO_PROJETO")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_GENERICO_2_OEM_VEICULO_PROJETO(
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String ID_ANALISE = firstMap.get("ID_ANALISE");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC GET_SEGUIMENTO_FATURACAO_GENERICO_2_OEM_VEICULO_PROJETO " + ANO + ",'" + CLIENTES + "','" + PROREF
						+ "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'," + ID_ANALISE + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_GENERICO_2_REFERENCIAS")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_GENERICO_2_REFERENCIAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String ID_ANALISE = firstMap.get("ID_ANALISE");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_GENERICO_2_REFERENCIAS " + ANO + ",'" + CLIENTES
						+ "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'," + ID_ANALISE + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_GENERICO_CLIENTES")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_GENERICO_CLIENTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String ID_ANALISE = firstMap.get("ID_ANALISE");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_GENERICO_CLIENTES " + ANO + ",'" + CLIENTES + "','"
						+ PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'," + ID_ANALISE + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_GENERICO_OEM_VEICULO_PROJETO")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_GENERICO_OEM_VEICULO_PROJETO(
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String ID_ANALISE = firstMap.get("ID_ANALISE");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_GENERICO_OEM_VEICULO_PROJETO " + ANO + ",'" + CLIENTES
						+ "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'," + ID_ANALISE + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_GENERICO_REFERENCIAS")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_GENERICO_REFERENCIAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String ID_ANALISE = firstMap.get("ID_ANALISE");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_GENERICO_REFERENCIAS " + ANO + ",'" + CLIENTES + "','"
						+ PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'," + ID_ANALISE + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO " + ANO
				+ ",'" + CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO_CLIENTES")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO_CLIENTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO_CLIENTES "
				+ ANO + ",'" + CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO_OEM_VEICULO_PROJETO")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO_OEM_VEICULO_PROJETO(
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO_OEM_VEICULO_PROJETO " + ANO + ",'"
						+ CLIENTES + "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO_REFERENCIAS")
	@Produces("application/json")
	public List<Object[]> GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO_REFERENCIAS(
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROJETOS = firstMap.get("PROJETOS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");

		Query query_folder = entityManager
				.createNativeQuery("EXEC GET_SEGUIMENTO_FATURACAO_TOTAL_REALIZADO_REFERENCIAS " + ANO + ",'" + CLIENTES
						+ "','" + PROREF + "','" + OEM + "','" + VEICULOS + "','" + PROJETOS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* AT_DIC_CAUSAS_ACIDENTE */

	@POST
	@Path("/createAT_DIC_CAUSAS_ACIDENTE")
	@Consumes("*/*")
	@Produces("application/json")
	public AT_DIC_CAUSAS_ACIDENTE insertAT_DIC_CAUSAS_ACIDENTEA(final AT_DIC_CAUSAS_ACIDENTE data) {
		return dao16.create(data);
	}

	@GET
	@Path("/getAT_DIC_CAUSAS_ACIDENTE")
	@Produces("application/json")
	public List<AT_DIC_CAUSAS_ACIDENTE> getAT_DIC_CAUSAS_ACIDENTE() {
		return dao16.getall();
	}

	@GET
	@Path("/getAT_DIC_CAUSAS_ACIDENTEbyid/{id}")
	@Produces("application/json")
	public List<AT_DIC_CAUSAS_ACIDENTE> getAT_DIC_CAUSAS_ACIDENTEbyip(@PathParam("id") Integer id) {
		return dao16.getbyid(id);
	}

	@DELETE
	@Path("/deleteAT_DIC_CAUSAS_ACIDENTE/{id}")
	public void deleteAT_DIC_CAUSAS_ACIDENTE(@PathParam("id") Integer id) {
		AT_DIC_CAUSAS_ACIDENTE AT_DIC_CAUSAS_ACIDENTE = new AT_DIC_CAUSAS_ACIDENTE();
		AT_DIC_CAUSAS_ACIDENTE.setID_CAUSAS_ACIDENTE(id);
		dao16.delete(AT_DIC_CAUSAS_ACIDENTE);
	}

	@PUT
	@Path("/updateAT_DIC_CAUSAS_ACIDENTE")
	@Consumes("*/*")
	@Produces("application/json")
	public AT_DIC_CAUSAS_ACIDENTE updateAT_DIC_CAUSAS_ACIDENTE(final AT_DIC_CAUSAS_ACIDENTE AT_DIC_CAUSAS_ACIDENTE) {
		AT_DIC_CAUSAS_ACIDENTE.setID_CAUSAS_ACIDENTE(AT_DIC_CAUSAS_ACIDENTE.getID_CAUSAS_ACIDENTE());
		return dao16.update(AT_DIC_CAUSAS_ACIDENTE);
	}

	@GET
	@Path("/getAT_OCORRENCIAS_CAUSAS_ACIDENTE/{id}")
	@Produces("application/json")
	public List<Object[]> getAT_OCORRENCIAS_CAUSAS_ACIDENTE(@PathParam("id") Integer id) {
		Query query_folder = entityManager.createNativeQuery(
				"SELECT a.ID_CAUSAS_ACIDENTE,(SELECT b.ID_OCORRENCIA FROM  AT_OCORRENCIAS_CAUSAS_ACIDENTE b where a.ID_CAUSAS_ACIDENTE = b.ID_CAUSAS_ACIDENTE and b.ID_OCORRENCIA = "
						+ id + ")  ID_OCORRENCIA,a.DESCRICAO FROM AT_DIC_CAUSAS_ACIDENTE a  order by a.DESCRICAO");
		List<Object[]> dados_folder = query_folder.getResultList();
		return dados_folder;
	}

	@GET
	@Path("/insertAT_OCORRENCIAS_CAUSAS_ACIDENTE/{id}/{id_causa}")
	@Produces("application/json")
	public void insertAT_OCORRENCIAS_CAUSAS_ACIDENTE(@PathParam("id") Integer id,
			@PathParam("id_causa") Integer id_causa) {
		Query query = entityManager.createNativeQuery(
				"INSERT INTO AT_OCORRENCIAS_CAUSAS_ACIDENTE (ID_OCORRENCIA,ID_CAUSAS_ACIDENTE) VALUES(" + id + ","
						+ id_causa + ")");
		query.executeUpdate();
	}

	@DELETE
	@Path("/deleteAT_OCORRENCIAS_CAUSAS_ACIDENTE/{id}")
	public void deleteAT_OCORRENCIAS_CAUSAS_ACIDENTE(@PathParam("id") Integer id) {
		Query query = entityManager
				.createNativeQuery("DELETE AT_OCORRENCIAS_CAUSAS_ACIDENTE where ID_OCORRENCIA = " + id);
		query.executeUpdate();
	}

	/************************************* RH_DIC_EPI */

	@POST
	@Path("/createRH_DIC_EPI")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_EPI insertRH_DIC_EPIA(final RH_DIC_EPI data) {
		return dao17.create(data);
	}

	@GET
	@Path("/getRH_DIC_EPI")
	@Produces("application/json")
	public List<RH_DIC_EPI> getRH_DIC_EPI() {
		return dao17.getall();
	}

	@GET
	@Path("/getAT_OCORRENCIAS_EPI/{id}")
	@Produces("application/json")
	public List<Object[]> getAT_OCORRENCIAS_EPI(@PathParam("id") Integer id) {
		Query query_folder = entityManager.createNativeQuery(
				"SELECT a.ID_EPI,(SELECT b.ID_OCORRENCIA FROM  AT_OCORRENCIAS_EPI b where a.ID_EPI = b.ID_EPI and b.ID_OCORRENCIA = "
						+ id + ")  ID_OCORRENCIA,a.DESCRICAO FROM RH_DIC_EPI a " + " order by a.DESCRICAO");
		List<Object[]> dados_folder = query_folder.getResultList();
		return dados_folder;
	}

	@GET
	@Path("/insertAT_OCORRENCIAS_EPI/{id}/{id_epi}")
	@Produces("application/json")
	public void insertAT_OCORRENCIAS_EPI(@PathParam("id") Integer id, @PathParam("id_epi") Integer id_epi) {
		Query query = entityManager.createNativeQuery(
				"INSERT INTO AT_OCORRENCIAS_EPI (ID_OCORRENCIA,ID_EPI) VALUES(" + id + "," + id_epi + ")");
		query.executeUpdate();
	}

	@DELETE
	@Path("/deleteAT_OCORRENCIAS_EPI/{id}")
	public void deleteAT_OCORRENCIAS_EPI(@PathParam("id") Integer id) {
		Query query = entityManager.createNativeQuery("DELETE AT_OCORRENCIAS_EPI where ID_OCORRENCIA = " + id);
		query.executeUpdate();
	}

	@GET
	@Path("/getRH_DIC_EPIbyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_EPI> getRH_DIC_EPIbyip(@PathParam("id") Integer id) {
		return dao17.getbyid(id);
	}

	@DELETE
	@Path("/deleteRH_DIC_EPI/{id}")
	public void deleteRH_DIC_EPI(@PathParam("id") Integer id) {
		RH_DIC_EPI RH_DIC_EPI = new RH_DIC_EPI();
		RH_DIC_EPI.setID_EPI(id);
		dao17.delete(RH_DIC_EPI);
	}

	@PUT
	@Path("/updateRH_DIC_EPI")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_EPI updateRH_DIC_EPI(final RH_DIC_EPI RH_DIC_EPI) {
		RH_DIC_EPI.setID_EPI(RH_DIC_EPI.getID_EPI());
		return dao17.update(RH_DIC_EPI);
	}

	/************************************* RC_MOV_RECLAMACAO_CLIENTES */
	@POST
	@Path("/createRC_MOV_RECLAMACAO_CLIENTES")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_CLIENTES insertRC_MOV_RECLAMACAO_CLIENTES(final RC_MOV_RECLAMACAO_CLIENTES data) {
		return dao18.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_CLIENTES")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_CLIENTES> getRC_MOV_RECLAMACAO_CLIENTES() {
		return dao18.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_CLIENTESbyid_reclamacao/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_CLIENTES> getRC_MOV_RECLAMACAO_CLIENTESbyid_reclamacao(@PathParam("id") Integer id) {
		return dao18.getbyid(id);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO_CLIENTES/{id}")
	public void deleteRC_MOV_RECLAMACAO_CLIENTES(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO_CLIENTES RC_MOV_RECLAMACAO_CLIENTES = new RC_MOV_RECLAMACAO_CLIENTES();
		RC_MOV_RECLAMACAO_CLIENTES.setID(id);
		dao18.delete(RC_MOV_RECLAMACAO_CLIENTES);
	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO_CLIENTES")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_CLIENTES updateRC_MOV_RECLAMACAO_CLIENTES(
			final RC_MOV_RECLAMACAO_CLIENTES RC_MOV_RECLAMACAO_CLIENTES) {
		RC_MOV_RECLAMACAO_CLIENTES.setID(RC_MOV_RECLAMACAO_CLIENTES.getID());
		return dao18.update(RC_MOV_RECLAMACAO_CLIENTES);
	}

	/************************************* QUA_DIC_TIPOS_AUDITORIA */
	@POST
	@Path("/createQUA_DIC_TIPOS_AUDITORIA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DIC_TIPOS_AUDITORIA insertQUA_DIC_TIPOS_AUDITORIA(final QUA_DIC_TIPOS_AUDITORIA data) {
		return dao19.create(data);
	}

	@GET
	@Path("/getQUA_DIC_TIPOS_AUDITORIA")
	@Produces("application/json")
	public List<QUA_DIC_TIPOS_AUDITORIA> getQUA_DIC_TIPOS_AUDITORIA() {
		return dao19.getall();
	}

	@GET
	@Path("/getQUA_DIC_TIPOS_AUDITORIAbyid/{id}")
	@Produces("application/json")
	public List<QUA_DIC_TIPOS_AUDITORIA> getQUA_DIC_TIPOS_AUDITORIAbyid(@PathParam("id") Integer id) {
		return dao19.getbyid(id);
	}

	@DELETE
	@Path("/deleteQUA_DIC_TIPOS_AUDITORIA/{id}")
	public void deleteQUA_DIC_TIPOS_AUDITORIA(@PathParam("id") Integer id) {
		QUA_DIC_TIPOS_AUDITORIA QUA_DIC_TIPOS_AUDITORIA = new QUA_DIC_TIPOS_AUDITORIA();
		QUA_DIC_TIPOS_AUDITORIA.setID_TIPO_AUDITORIA(id);
		dao19.delete(QUA_DIC_TIPOS_AUDITORIA);
	}

	@PUT
	@Path("/updateQUA_DIC_TIPOS_AUDITORIA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DIC_TIPOS_AUDITORIA updateQUA_DIC_TIPOS_AUDITORIA(
			final QUA_DIC_TIPOS_AUDITORIA QUA_DIC_TIPOS_AUDITORIA) {
		QUA_DIC_TIPOS_AUDITORIA.setID_TIPO_AUDITORIA(QUA_DIC_TIPOS_AUDITORIA.getID_TIPO_AUDITORIA());
		return dao19.update(QUA_DIC_TIPOS_AUDITORIA);
	}

	/************************************* QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA */
	@POST
	@Path("/createQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA insertQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA(
			final QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA data) {
		return dao20.create(data);
	}

	@GET
	@Path("/getQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA")
	@Produces("application/json")
	public List<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA> getQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA() {
		return dao20.getall();
	}

	@GET
	@Path("/getQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTAbyid/{id}")
	@Produces("application/json")
	public List<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA> getQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTAbyid_reclamacao(
			@PathParam("id") Integer id) {
		return dao20.getbyid(id);
	}

	@GET
	@Path("/getQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTAbyid_ano/{id}/{ano}")
	@Produces("application/json")
	public List<QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA> getQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTAbyid_ano(
			@PathParam("id") Integer id, @PathParam("ano") Integer ano) {
		return dao20.getbyid_ano(id, ano);
	}

	@DELETE
	@Path("/deleteQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA/{id}")
	public void deleteQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA(@PathParam("id") Integer id) {
		QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA = new QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA();
		QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA.setID(id);
		dao20.delete(QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA);
	}

	@PUT
	@Path("/updateQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA updateQUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA(
			final QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA) {
		QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA.setID(QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA.getID());
		return dao20.update(QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA);
	}

	/************************************* QUA_MOV_AUDITORIAS */
	@POST
	@Path("/createQUA_MOV_AUDITORIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MOV_AUDITORIAS insertQUA_MOV_AUDITORIAS(final QUA_MOV_AUDITORIAS data) {
		return dao21.create(data);
	}

	@GET
	@Path("/getQUA_MOV_AUDITORIAS")
	@Produces("application/json")
	public List<QUA_MOV_AUDITORIAS> getQUA_MOV_AUDITORIAS() {
		return dao21.getall();
	}

	@GET
	@Path("/getQUA_MOV_AUDITORIASbyid/{id}")
	@Produces("application/json")
	public List<QUA_MOV_AUDITORIAS> getQUA_MOV_AUDITORIASbyid(@PathParam("id") Integer id) {
		return dao21.getbyid(id);
	}

	@GET
	@Path("/getQUA_MOV_AUDITORIASbyano/{ano}")
	@Produces("application/json")
	public List<QUA_MOV_AUDITORIAS> getQUA_MOV_AUDITORIASbyano(@PathParam("ano") Integer ano) {
		return dao21.getbyano(ano);
	}

	@DELETE
	@Path("/deleteQUA_MOV_AUDITORIAS/{id}")
	public void deleteQUA_MOV_AUDITORIAS(@PathParam("id") Integer id) {
		QUA_MOV_AUDITORIAS QUA_MOV_AUDITORIAS = new QUA_MOV_AUDITORIAS();
		QUA_MOV_AUDITORIAS.setID_AUDITORIA(id);
		dao21.delete(QUA_MOV_AUDITORIAS);
	}

	@PUT
	@Path("/updateQUA_MOV_AUDITORIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MOV_AUDITORIAS updateQUA_MOV_AUDITORIAS(final QUA_MOV_AUDITORIAS QUA_MOV_AUDITORIAS) {
		QUA_MOV_AUDITORIAS.setID_AUDITORIA(QUA_MOV_AUDITORIAS.getID_AUDITORIA());
		return dao21.update(QUA_MOV_AUDITORIAS);
	}

	/************************************* QUA_DERROGACOES */
	@POST
	@Path("/createQUA_DERROGACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DERROGACOES insertQUA_DERROGACOES(final QUA_DERROGACOES data) {
		return dao22.create(data);
	}

	@GET
	@Path("/getQUA_DERROGACOES")
	@Produces("application/json")
	public List<QUA_DERROGACOES> getQUA_DERROGACOES() {
		return dao22.getall();
	}

	@GET
	@Path("/getQUA_DERROGACOES_DASHBOARD")
	@Produces("application/json")
	public List<QUA_DERROGACOES> getQUA_DERROGACOES_DASHBOARD() {
		return dao22.getall_dash();
	}

	@GET
	@Path("/getQUA_DERROGACOESbyid/{id}")
	@Produces("application/json")
	public List<QUA_DERROGACOES> getQUA_DERROGACOESbyid_reclamacao(@PathParam("id") Integer id) {
		return dao22.getbyid(id);
	}

	@DELETE
	@Path("/deleteQUA_DERROGACOES/{id}")
	public void deleteQUA_DERROGACOES(@PathParam("id") Integer id) {
		QUA_DERROGACOES QUA_DERROGACOES = new QUA_DERROGACOES();
		QUA_DERROGACOES.setID_DERROGACAO(id);
		dao22.delete(QUA_DERROGACOES);
	}

	@GET
	@Path("/deleteQUA_DERROGACOESUPDATEESTADOS/{id}/{modulo}")
	@Produces("application/json")
	public void deleteQUA_DERROGACOESUPDATEESTADOS(@PathParam("id") Integer id, @PathParam("modulo") Integer modulo) {

		Query query = entityManager
				.createNativeQuery("UPDATE QUA_DERROGACOES_PLANOS_ACCOES SET ESTADO = 'A' where ID_DERROGACAO = " + id
						+ " and ESTADO != 'C' "
						+ "UPDATE GT_MOV_TAREFAS SET ESTADO = 'A' where ID_CAMPO in (select ID from QUA_DERROGACOES_PLANOS_ACCOES where ID_DERROGACAO = '"
						+ id + "') and ID_MODULO='" + modulo + "' and SUB_MODULO = 'D' and ESTADO != 'C'");

		query.executeUpdate();

	}

	@PUT
	@Path("/updateQUA_DERROGACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DERROGACOES updateQUA_DERROGACOES(final QUA_DERROGACOES QUA_DERROGACOES) {
		QUA_DERROGACOES.setID_DERROGACAO(QUA_DERROGACOES.getID_DERROGACAO());
		return dao22.update(QUA_DERROGACOES);
	}

	/************************************* QUA_DERROGACOES_FICHEIROS */
	@POST
	@Path("/createQUA_DERROGACOES_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DERROGACOES_FICHEIROS insertQUA_DERROGACOES_FICHEIROSA(final QUA_DERROGACOES_FICHEIROS data) {
		return dao30.create(data);
	}

	@GET
	@Path("/getQUA_DERROGACOES_FICHEIROS")
	@Produces("application/json")
	public List<QUA_DERROGACOES_FICHEIROS> getQUA_DERROGACOES_FICHEIROS() {
		return dao30.getall();
	}

	@GET
	@Path("/getQUA_DERROGACOES_FICHEIROSbyidDERROGACAO/{id}")
	@Produces("application/json")
	public List<QUA_DERROGACOES_FICHEIROS> getQUA_DERROGACOES_FICHEIROSbyidDERROGACAO(@PathParam("id") Integer id) {
		return dao30.getbyid(id);
	}

	@DELETE
	@Path("/deleteQUA_DERROGACOES_FICHEIROS/{id}")
	public void deleteQUA_DERROGACOES_FICHEIROS(@PathParam("id") Integer id) {
		QUA_DERROGACOES_FICHEIROS QUA_DERROGACOES_FICHEIROS = new QUA_DERROGACOES_FICHEIROS();
		QUA_DERROGACOES_FICHEIROS.setID(id);
		dao30.delete(QUA_DERROGACOES_FICHEIROS);
	}

	@PUT
	@Path("/updateQUA_DERROGACOES_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DERROGACOES_FICHEIROS updateQUA_DERROGACOES_FICHEIROS(
			final QUA_DERROGACOES_FICHEIROS QUA_DERROGACOES_FICHEIROS) {
		QUA_DERROGACOES_FICHEIROS.setID(QUA_DERROGACOES_FICHEIROS.getID());
		return dao30.update(QUA_DERROGACOES_FICHEIROS);
	}

	/************************************* QUA_DERROGACOES_PLANOS_ACCOES */
	@POST
	@Path("/createQUA_DERROGACOES_PLANOS_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DERROGACOES_PLANOS_ACCOES insertQUA_DERROGACOES_PLANOS_ACCOESA(
			final QUA_DERROGACOES_PLANOS_ACCOES data) {
		return dao31.create(data);
	}

	@GET
	@Path("/getQUA_DERROGACOES_PLANOS_ACCOES")
	@Produces("application/json")
	public List<QUA_DERROGACOES_PLANOS_ACCOES> getQUA_DERROGACOES_PLANOS_ACCOES() {
		return dao31.getall();
	}

	@GET
	@Path("/getQUA_DERROGACOES_PLANOS_ACCOESbyid_derrogacao/{id}")
	@Produces("application/json")
	public List<QUA_DERROGACOES_PLANOS_ACCOES> getQUA_DERROGACOES_PLANOS_ACCOESbyid_derrogacao(
			@PathParam("id") Integer id) {
		return dao31.getbyid(id);
	}

	@GET
	@Path("/getQUA_DERROGACOES_PLANOS_ACCOESbyid/{id}")
	@Produces("application/json")
	public List<QUA_DERROGACOES_PLANOS_ACCOES> getQUA_DERROGACOES_PLANOS_ACCOESbyid(@PathParam("id") Integer id) {
		return dao31.getbyidplano(id);
	}

	@DELETE
	@Path("/deleteQUA_DERROGACOES_PLANOS_ACCOES/{id}")
	public void deleteQUA_DERROGACOES_PLANOS_ACCOES(@PathParam("id") Integer id) {
		QUA_DERROGACOES_PLANOS_ACCOES QUA_DERROGACOES_PLANOS_ACCOES = new QUA_DERROGACOES_PLANOS_ACCOES();
		QUA_DERROGACOES_PLANOS_ACCOES.setID(id);
		dao31.delete(QUA_DERROGACOES_PLANOS_ACCOES);
	}

	@PUT
	@Path("/updateQUA_DERROGACOES_PLANOS_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DERROGACOES_PLANOS_ACCOES updateQUA_DERROGACOES_PLANOS_ACCOES(
			final QUA_DERROGACOES_PLANOS_ACCOES QUA_DERROGACOES_PLANOS_ACCOES) {
		QUA_DERROGACOES_PLANOS_ACCOES.setID(QUA_DERROGACOES_PLANOS_ACCOES.getID());
		return dao31.update(QUA_DERROGACOES_PLANOS_ACCOES);
	}

	/************************************* QUA_DERROGACOES_EQUIPA */
	@POST
	@Path("/createQUA_DERROGACOES_EQUIPA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DERROGACOES_EQUIPA insertQUA_DERROGACOES_EQUIPAA(final QUA_DERROGACOES_EQUIPA data) {
		return dao32.create(data);
	}

	@GET
	@Path("/getQUA_DERROGACOES_EQUIPA")
	@Produces("application/json")
	public List<QUA_DERROGACOES_EQUIPA> getQUA_DERROGACOES_EQUIPA() {
		return dao32.getall();
	}

	@GET
	@Path("/getQUA_DERROGACOES_EQUIPAbyid_derrogacao/{id}")
	@Produces("application/json")
	public List<QUA_DERROGACOES_EQUIPA> getQUA_DERROGACOES_EQUIPAbyid_derrogacao(@PathParam("id") Integer id) {
		return dao32.getbyid(id);
	}

	@DELETE
	@Path("/deleteQUA_DERROGACOES_EQUIPA/{id}")
	public void deleteQUA_DERROGACOES_EQUIPA(@PathParam("id") Integer id) {
		QUA_DERROGACOES_EQUIPA QUA_DERROGACOES_EQUIPA = new QUA_DERROGACOES_EQUIPA();
		QUA_DERROGACOES_EQUIPA.setID(id);
		dao32.delete(QUA_DERROGACOES_EQUIPA);
	}

	@PUT
	@Path("/updateQUA_DERROGACOES_EQUIPA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_DERROGACOES_EQUIPA updateQUA_DERROGACOES_EQUIPA(final QUA_DERROGACOES_EQUIPA QUA_DERROGACOES_EQUIPA) {
		QUA_DERROGACOES_EQUIPA.setID(QUA_DERROGACOES_EQUIPA.getID());
		return dao32.update(QUA_DERROGACOES_EQUIPA);
	}

	/************************************* GER_REFERENCIAS_FASTRESPONSE_REJEICOES */
	@POST
	@Path("/createGER_REFERENCIAS_FASTRESPONSE_REJEICOES")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_REFERENCIAS_FASTRESPONSE_REJEICOES insertGER_REFERENCIAS_FASTRESPONSE_REJEICOES(
			final GER_REFERENCIAS_FASTRESPONSE_REJEICOES data) {
		return dao23.create(data);
	}

	@GET
	@Path("/getGER_REFERENCIAS_FASTRESPONSE_REJEICOES")
	@Produces("application/json")
	public List<GER_REFERENCIAS_FASTRESPONSE_REJEICOES> getGER_REFERENCIAS_FASTRESPONSE_REJEICOES() {
		return dao23.getall();
	}

	@GET
	@Path("/getGER_REFERENCIAS_FASTRESPONSE_REJEICOESbyid/{id}")
	@Produces("application/json")
	public List<GER_REFERENCIAS_FASTRESPONSE_REJEICOES> getGER_REFERENCIAS_FASTRESPONSE_REJEICOESbyid_reclamacao(
			@PathParam("id") Integer id) {
		return dao23.getbyid(id);
	}

	@POST
	@Path("/getGER_REFERENCIAS_FASTRESPONSE_REJEICOESbydata")
	@Consumes("*/*")
	@Produces("application/json")
	public List<GER_REFERENCIAS_FASTRESPONSE_REJEICOES> getGER_REFERENCIAS_FASTRESPONSE_REJEICOESbydata(
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA = firstMap.get("DATA");
		return dao23.getByData(DATA);
	}

	@DELETE
	@Path("/deleteGER_REFERENCIAS_FASTRESPONSE_REJEICOES/{id}")
	public void deleteGER_REFERENCIAS_FASTRESPONSE_REJEICOES(@PathParam("id") Integer id) {
		GER_REFERENCIAS_FASTRESPONSE_REJEICOES GER_REFERENCIAS_FASTRESPONSE_REJEICOES = new GER_REFERENCIAS_FASTRESPONSE_REJEICOES();
		GER_REFERENCIAS_FASTRESPONSE_REJEICOES.setID(id);
		dao23.delete(GER_REFERENCIAS_FASTRESPONSE_REJEICOES);
	}

	@PUT
	@Path("/updateGER_REFERENCIAS_FASTRESPONSE_REJEICOES")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_REFERENCIAS_FASTRESPONSE_REJEICOES updateGER_REFERENCIAS_FASTRESPONSE_REJEICOES(
			final GER_REFERENCIAS_FASTRESPONSE_REJEICOES GER_REFERENCIAS_FASTRESPONSE_REJEICOES) {
		GER_REFERENCIAS_FASTRESPONSE_REJEICOES.setID(GER_REFERENCIAS_FASTRESPONSE_REJEICOES.getID());
		return dao23.update(GER_REFERENCIAS_FASTRESPONSE_REJEICOES);
	}

	/************************************* RH_DIC_SECTORES_ABSENTISMO */

	@POST
	@Path("/createRH_DIC_SECTORES_ABSENTISMO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_SECTORES_ABSENTISMO insertRH_DIC_SECTORES_ABSENTISMOA(final RH_DIC_SECTORES_ABSENTISMO data) {
		return dao24.create(data);
	}

	@GET
	@Path("/getRH_DIC_SECTORES_ABSENTISMO")
	@Produces("application/json")
	public List<RH_DIC_SECTORES_ABSENTISMO> getRH_DIC_SECTORES_ABSENTISMO() {
		return dao24.getall();
	}

	@GET
	@Path("/getRH_DIC_SECTORES_ABSENTISMObyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_SECTORES_ABSENTISMO> getRH_DIC_SECTORES_ABSENTISMObyip(@PathParam("id") Integer id) {
		return dao24.getbyid(id);
	}

	@DELETE
	@Path("/deleteRH_DIC_SECTORES_ABSENTISMO/{id}")
	public void deleteRH_DIC_SECTORES_ABSENTISMO(@PathParam("id") Integer id) {
		RH_DIC_SECTORES_ABSENTISMO RH_DIC_SECTORES_ABSENTISMO = new RH_DIC_SECTORES_ABSENTISMO();
		RH_DIC_SECTORES_ABSENTISMO.setID_SECTOR_ABSENTISMO(id);
		dao24.delete(RH_DIC_SECTORES_ABSENTISMO);
	}

	@PUT
	@Path("/updateRH_DIC_SECTORES_ABSENTISMO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_SECTORES_ABSENTISMO updateRH_DIC_SECTORES_ABSENTISMO(
			final RH_DIC_SECTORES_ABSENTISMO RH_DIC_SECTORES_ABSENTISMO) {
		RH_DIC_SECTORES_ABSENTISMO.setID_SECTOR_ABSENTISMO(RH_DIC_SECTORES_ABSENTISMO.getID_SECTOR_ABSENTISMO());
		return dao24.update(RH_DIC_SECTORES_ABSENTISMO);
	}

	/************************************* RH_DIC_SECTORES_ABSENTISMO_LINHA */

	@POST
	@Path("/createRH_DIC_SECTORES_ABSENTISMO_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_SECTORES_ABSENTISMO_LINHA insertRH_DIC_SECTORES_ABSENTISMO_LINHAA(
			final RH_DIC_SECTORES_ABSENTISMO_LINHA data) {
		return dao25.create(data);
	}

	@GET
	@Path("/getRH_DIC_SECTORES_ABSENTISMO_LINHA")
	@Produces("application/json")
	public List<RH_DIC_SECTORES_ABSENTISMO_LINHA> getRH_DIC_SECTORES_ABSENTISMO_LINHA() {
		return dao25.getall();
	}

	@GET
	@Path("/getRH_DIC_SECTORES_ABSENTISMO_LINHAbyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_SECTORES_ABSENTISMO_LINHA> getRH_DIC_SECTORES_ABSENTISMO_LINHAbyip(@PathParam("id") Integer id) {
		return dao25.getbyid(id);
	}

	@GET
	@Path("/getRH_DIC_SECTORES_ABSENTISMO_LINHAgetSectoresAll/{id}")
	@Produces("application/json")
	public List<RH_DIC_SECTORES_ABSENTISMO_LINHA> getSectoresAll(@PathParam("id") Integer id) {
		return dao25.getSectoresAll(id);
	}

	@GET
	@Path("/getRH_DIC_SECTORES_ABSENTISMO_LINHAgetSectoresAbsentismo/{id}")
	@Produces("application/json")
	public List<RH_DIC_SECTORES_ABSENTISMO_LINHA> getSectoresAbsentismo(@PathParam("id") Integer id) {
		return dao25.getSectoresAbsentismo(id);
	}

	@DELETE
	@Path("/deleteRH_DIC_SECTORES_ABSENTISMO_LINHA/{id}")
	public void deleteRH_DIC_SECTORES_ABSENTISMO_LINHA(@PathParam("id") Integer id) {
		RH_DIC_SECTORES_ABSENTISMO_LINHA RH_DIC_SECTORES_ABSENTISMO_LINHA = new RH_DIC_SECTORES_ABSENTISMO_LINHA();
		RH_DIC_SECTORES_ABSENTISMO_LINHA.setID_SECTOR_ABSENTISMO_LINHA(id);
		dao25.delete(RH_DIC_SECTORES_ABSENTISMO_LINHA);
	}

	@PUT
	@Path("/updateRH_DIC_SECTORES_ABSENTISMO_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_SECTORES_ABSENTISMO_LINHA updateRH_DIC_SECTORES_ABSENTISMO_LINHA(
			final RH_DIC_SECTORES_ABSENTISMO_LINHA RH_DIC_SECTORES_ABSENTISMO_LINHA) {
		RH_DIC_SECTORES_ABSENTISMO_LINHA
				.setID_SECTOR_ABSENTISMO_LINHA(RH_DIC_SECTORES_ABSENTISMO_LINHA.getID_SECTOR_ABSENTISMO_LINHA());
		return dao25.update(RH_DIC_SECTORES_ABSENTISMO_LINHA);
	}

	/************************************* CO_ANALISE_CLIENTES */

	@POST
	@Path("/createCO_ANALISE_CLIENTES")
	@Consumes("*/*")
	@Produces("application/json")
	public CO_ANALISE_CLIENTES insertCO_ANALISE_CLIENTESA(final CO_ANALISE_CLIENTES data) {
		return dao26.create(data);
	}

	@GET
	@Path("/getCO_ANALISE_CLIENTES")
	@Produces("application/json")
	public List<CO_ANALISE_CLIENTES> getCO_ANALISE_CLIENTES() {
		return dao26.getall();
	}

	@GET
	@Path("/getCO_ANALISE_CLIENTESbyid/{id}")
	@Produces("application/json")
	public List<CO_ANALISE_CLIENTES> getCO_ANALISE_CLIENTESbyip(@PathParam("id") Integer id) {
		return dao26.getbyid(id);
	}

	@DELETE
	@Path("/deleteCO_ANALISE_CLIENTES/{id}")
	public void deleteCO_ANALISE_CLIENTES(@PathParam("id") Integer id) {
		CO_ANALISE_CLIENTES CO_ANALISE_CLIENTES = new CO_ANALISE_CLIENTES();
		CO_ANALISE_CLIENTES.setID(id);
		dao26.delete(CO_ANALISE_CLIENTES);
	}

	@PUT
	@Path("/updateCO_ANALISE_CLIENTES")
	@Consumes("*/*")
	@Produces("application/json")
	public CO_ANALISE_CLIENTES updateCO_ANALISE_CLIENTES(final CO_ANALISE_CLIENTES CO_ANALISE_CLIENTES) {
		CO_ANALISE_CLIENTES.setID(CO_ANALISE_CLIENTES.getID());
		return dao26.update(CO_ANALISE_CLIENTES);
	}

	@POST
	@Path("/GET_ANALISE_CLIENTES_CLIENTES")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_CLIENTES_CLIENTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String SEMANA = firstMap.get("SEMANA");
		String ANO = firstMap.get("ANO");
		String COD_CLIENTE = firstMap.get("COD_CLIENTE");
		String NOME_CLIENTE = firstMap.get("NOME_CLIENTE");
		String PROREF = firstMap.get("PROREF");

		String PROGRAMAS = firstMap.get("PROGRAMAS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String FABRICAS = firstMap.get("FABRICAS");
		String LINHA = firstMap.get("LINHA");
		String TODOS = firstMap.get("TODOS");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_ANALISE_CLIENTES_CLIENTES] " + SEMANA + ","
				+ ANO + ",'" + COD_CLIENTE + "','" + NOME_CLIENTE + "','" + PROREF + "','" + PROGRAMAS + "','"
				+ VEICULOS + "','" + OEM + "','" + FABRICAS + "'," + LINHA + "," + TODOS);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_CLIENTES_REFERENCIAS")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_CLIENTES_REFERENCIAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String SEMANA = firstMap.get("SEMANA");
		String ANO = firstMap.get("ANO");
		String COD_CLIENTE = firstMap.get("COD_CLIENTE");
		String NOME_CLIENTE = firstMap.get("NOME_CLIENTE");
		String PROREF = firstMap.get("PROREF");

		String PROGRAMAS = firstMap.get("PROGRAMAS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String FABRICAS = firstMap.get("FABRICAS");
		String LINHA = firstMap.get("LINHA");
		String ETSNUM = firstMap.get("ETSNUM");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_ANALISE_CLIENTES_REFERENCIAS] " + SEMANA + ","
				+ ANO + ",'" + COD_CLIENTE + "','" + NOME_CLIENTE + "','" + ETSNUM + "','" + PROREF + "','" + PROGRAMAS
				+ "','" + VEICULOS + "','" + OEM + "','" + FABRICAS + "'," + LINHA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* CO_ANALISE_CLIENTES_QUANTIDADE */

	@POST
	@Path("/createCO_ANALISE_CLIENTES_QUANTIDADE")
	@Consumes("*/*")
	@Produces("application/json")
	public CO_ANALISE_CLIENTES_QUANTIDADE insertCO_ANALISE_CLIENTES_QUANTIDADEA(
			final CO_ANALISE_CLIENTES_QUANTIDADE data) {
		return dao27.create(data);
	}

	@GET
	@Path("/getCO_ANALISE_CLIENTES_QUANTIDADE")
	@Produces("application/json")
	public List<CO_ANALISE_CLIENTES_QUANTIDADE> getCO_ANALISE_CLIENTES_QUANTIDADE() {
		return dao27.getall();
	}

	@GET
	@Path("/getCO_ANALISE_CLIENTES_QUANTIDADEbyid/{id}")
	@Produces("application/json")
	public List<CO_ANALISE_CLIENTES_QUANTIDADE> getCO_ANALISE_CLIENTES_QUANTIDADEbyip(@PathParam("id") Integer id) {
		return dao27.getbyid(id);
	}

	@DELETE
	@Path("/deleteCO_ANALISE_CLIENTES_QUANTIDADE/{id}")
	public void deleteCO_ANALISE_CLIENTES_QUANTIDADE(@PathParam("id") Integer id) {
		CO_ANALISE_CLIENTES_QUANTIDADE CO_ANALISE_CLIENTES_QUANTIDADE = new CO_ANALISE_CLIENTES_QUANTIDADE();
		CO_ANALISE_CLIENTES_QUANTIDADE.setID(id);
		dao27.delete(CO_ANALISE_CLIENTES_QUANTIDADE);
	}

	@PUT
	@Path("/updateCO_ANALISE_CLIENTES_QUANTIDADE")
	@Consumes("*/*")
	@Produces("application/json")
	public CO_ANALISE_CLIENTES_QUANTIDADE updateCO_ANALISE_CLIENTES_QUANTIDADE(
			final CO_ANALISE_CLIENTES_QUANTIDADE CO_ANALISE_CLIENTES_QUANTIDADE) {
		CO_ANALISE_CLIENTES_QUANTIDADE.setID(CO_ANALISE_CLIENTES_QUANTIDADE.getID());
		return dao27.update(CO_ANALISE_CLIENTES_QUANTIDADE);
	}

	/************************************* CO_ANALISE_CLIENTES_OBSERVACOES */

	@POST
	@Path("/createCO_ANALISE_CLIENTES_OBSERVACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public CO_ANALISE_CLIENTES_OBSERVACOES insertCO_ANALISE_CLIENTES_OBSERVACOESA(
			final CO_ANALISE_CLIENTES_OBSERVACOES data) {
		return dao28.create(data);
	}

	@GET
	@Path("/getCO_ANALISE_CLIENTES_OBSERVACOES")
	@Produces("application/json")
	public List<CO_ANALISE_CLIENTES_OBSERVACOES> getCO_ANALISE_CLIENTES_OBSERVACOES() {
		return dao28.getall();
	}

	@GET
	@Path("/getCO_ANALISE_CLIENTES_OBSERVACOESbyid/{id}")
	@Produces("application/json")
	public List<CO_ANALISE_CLIENTES_OBSERVACOES> getCO_ANALISE_CLIENTES_OBSERVACOESbyip(@PathParam("id") Integer id) {
		return dao28.getbyid(id);
	}

	@DELETE
	@Path("/deleteCO_ANALISE_CLIENTES_OBSERVACOES/{id}")
	public void deleteCO_ANALISE_CLIENTES_OBSERVACOES(@PathParam("id") Integer id) {
		CO_ANALISE_CLIENTES_OBSERVACOES CO_ANALISE_CLIENTES_OBSERVACOES = new CO_ANALISE_CLIENTES_OBSERVACOES();
		CO_ANALISE_CLIENTES_OBSERVACOES.setID(id);
		dao28.delete(CO_ANALISE_CLIENTES_OBSERVACOES);
	}

	@PUT
	@Path("/updateCO_ANALISE_CLIENTES_OBSERVACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public CO_ANALISE_CLIENTES_OBSERVACOES updateCO_ANALISE_CLIENTES_OBSERVACOES(
			final CO_ANALISE_CLIENTES_OBSERVACOES CO_ANALISE_CLIENTES_OBSERVACOES) {
		CO_ANALISE_CLIENTES_OBSERVACOES.setID(CO_ANALISE_CLIENTES_OBSERVACOES.getID());
		return dao28.update(CO_ANALISE_CLIENTES_OBSERVACOES);
	}

	@POST
	@Path("/GET_ANALISE_CLIENTES_OBSERVACOES")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_CLIENTES_OBSERVACOES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		// String SEMANA = firstMap.get("SEMANA");
		// String ANO = firstMap.get("ANO");
		String COD_CLIENTE = firstMap.get("COD_CLIENTE");
		String PROREF = firstMap.get("PROREF");
		String ETSNUM = firstMap.get("ETSNUM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC [GET_ANALISE_CLIENTES_OBSERVACOES] '" + COD_CLIENTE + "','" + ETSNUM + "','" + PROREF + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_CLIENTES_ACCOES")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_CLIENTES_ACCOES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		// String SEMANA = firstMap.get("SEMANA");
		// String ANO = firstMap.get("ANO");
		String COD_CLIENTE = firstMap.get("COD_CLIENTE");
		String PROREF = firstMap.get("PROREF");
		String ETSNUM = firstMap.get("ETSNUM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC [GET_ANALISE_CLIENTES_ACCOES] '" + COD_CLIENTE + "','" + ETSNUM + "','" + PROREF + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* CO_ANALISE_CLIENTES_ACCOES */

	@POST
	@Path("/createCO_ANALISE_CLIENTES_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public CO_ANALISE_CLIENTES_ACCOES insertCO_ANALISE_CLIENTES_ACCOESA(final CO_ANALISE_CLIENTES_ACCOES data) {
		return dao29.create(data);
	}

	@GET
	@Path("/getCO_ANALISE_CLIENTES_ACCOES")
	@Produces("application/json")
	public List<CO_ANALISE_CLIENTES_ACCOES> getCO_ANALISE_CLIENTES_ACCOES() {
		return dao29.getall();
	}

	@GET
	@Path("/getCO_ANALISE_CLIENTES_ACCOESbyid/{id}")
	@Produces("application/json")
	public List<CO_ANALISE_CLIENTES_ACCOES> getCO_ANALISE_CLIENTES_ACCOESbyip(@PathParam("id") Integer id) {
		return dao29.getbyid(id);
	}

	@DELETE
	@Path("/deleteCO_ANALISE_CLIENTES_ACCOES/{id}")
	public void deleteCO_ANALISE_CLIENTES_ACCOES(@PathParam("id") Integer id) {
		CO_ANALISE_CLIENTES_ACCOES CO_ANALISE_CLIENTES_ACCOES = new CO_ANALISE_CLIENTES_ACCOES();
		CO_ANALISE_CLIENTES_ACCOES.setID(id);
		dao29.delete(CO_ANALISE_CLIENTES_ACCOES);
	}

	@PUT
	@Path("/updateCO_ANALISE_CLIENTES_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public CO_ANALISE_CLIENTES_ACCOES updateCO_ANALISE_CLIENTES_ACCOES(
			final CO_ANALISE_CLIENTES_ACCOES CO_ANALISE_CLIENTES_ACCOES) {
		CO_ANALISE_CLIENTES_ACCOES.setID(CO_ANALISE_CLIENTES_ACCOES.getID());
		return dao29.update(CO_ANALISE_CLIENTES_ACCOES);
	}

	/*****************************/
	@POST
	@Path("/GET_ANALISE_LOTE_FORNECEDOR_LOTES")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_LOTE_FORNECEDOR_LOTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String LOTE = firstMap.get("LOTE");

		String lote_q = "'" + LOTE + "'";
		if (LOTE == null)
			lote_q = "null";

		if (DATA_INI != null)
			DATA_INI = "'" + DATA_INI + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC SILVER_BI.dbo.GET_ANALISE_LOTE_FORNECEDOR_LOTES "
				+ DATA_INI + "," + DATA_FIM + "," + lote_q + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_LOTE_FORNECEDOR_REFERENCIAS")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_LOTE_FORNECEDOR_REFERENCIAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String LOTE = firstMap.get("LOTE");
		String OFS = firstMap.get("OFS");

		String lote_q = "'" + LOTE + "'";
		if (LOTE == null)
			lote_q = "null";

		if (DATA_INI != null)
			DATA_INI = "'" + DATA_INI + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC SILVER_BI.dbo.GET_ANALISE_LOTE_FORNECEDOR_REFERENCIAS " + DATA_INI + ","
						+ DATA_FIM + "," + lote_q + ",'" + OFS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_LOTE_FORNECEDOR_FAM")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_LOTE_FORNECEDOR_FAM(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String LOTE = firstMap.get("LOTE");
		String OFS = firstMap.get("OFS");
		String REF = firstMap.get("REF");

		String lote_q = "'" + LOTE + "'";
		if (LOTE == null)
			lote_q = "null";

		if (DATA_INI != null)
			DATA_INI = "'" + DATA_INI + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC SILVER_BI.dbo.GET_ANALISE_LOTE_FORNECEDOR_FAM "
				+ DATA_INI + "," + DATA_FIM + "," + lote_q + ",'" + OFS + "','" + REF + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_LOTE_FORNECEDOR_DEFEITOS")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_LOTE_FORNECEDOR_DEFEITOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String LOTE = firstMap.get("LOTE");
		String OFS = firstMap.get("OFS");
		String REF = firstMap.get("REF");
		String FAM = firstMap.get("FAM");

		String lote_q = "'" + LOTE + "'";
		if (LOTE == null)
			lote_q = "null";

		if (DATA_INI != null)
			DATA_INI = "'" + DATA_INI + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC SILVER_BI.dbo.GET_ANALISE_LOTE_FORNECEDOR_DEFEITOS "
				+ DATA_INI + "," + DATA_FIM + "," + lote_q + ",'" + OFS + "','" + REF + "', '" + FAM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_LOTE_FORNECEDOR_OFREF")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_LOTE_FORNECEDOR_OFREF(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String LOTE = firstMap.get("LOTE");
		String OFS = firstMap.get("OFS");
		String REF = firstMap.get("REF");
		String FAM = firstMap.get("FAM");
		String DEFEITO = firstMap.get("DEFEITO");

		String lote_q = "'" + LOTE + "'";
		if (LOTE == null)
			lote_q = "null";

		if (DATA_INI != null)
			DATA_INI = "'" + DATA_INI + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC SILVER_BI.dbo.GET_ANALISE_LOTE_FORNECEDOR_OFREF " + DATA_INI + "," + DATA_FIM
						+ "," + lote_q + ",'" + OFS + "','" + REF + "', '" + FAM + "', '" + DEFEITO + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* REU_AMBITOS_REUNIOES */
	@POST
	@Path("/createREU_AMBITOS_REUNIOES")
	@Consumes("*/*")
	@Produces("application/json")
	public REU_AMBITOS_REUNIOES insertREU_AMBITOS_REUNIOESA(final REU_AMBITOS_REUNIOES data) {

		REU_AMBITOS_REUNIOES result = dao33.create(data);

		Query query_folder = entityManager
				.createNativeQuery("EXEC ATUALIZAR_REU_AMBITOS_REUNIOES " + result.getID_AMBITO());
		query_folder.getResultList();
		return result;
	}

	@GET
	@Path("/getREU_AMBITOS_REUNIOES")
	@Produces("application/json")
	public List<REU_AMBITOS_REUNIOES> getREU_AMBITOS_REUNIOES() {
		return dao33.getall();
	}

	@GET
	@Path("/getREU_AMBITOS_REUNIOESbyid/{id}")
	@Produces("application/json")
	public List<REU_AMBITOS_REUNIOES> getREU_AMBITOS_REUNIOES(@PathParam("id") Integer id) {
		return dao33.getbyid(id);
	}

	@DELETE
	@Path("/deleteREU_AMBITOS_REUNIOES/{id}")
	public void deleteREU_AMBITOS_REUNIOES(@PathParam("id") Integer id) {
		REU_AMBITOS_REUNIOES REU_AMBITOS_REUNIOES = new REU_AMBITOS_REUNIOES();
		REU_AMBITOS_REUNIOES.setID_AMBITO(id);
		dao33.delete(REU_AMBITOS_REUNIOES);
	}

	@PUT
	@Path("/updateREU_AMBITOS_REUNIOES")
	@Consumes("*/*")
	@Produces("application/json")
	public REU_AMBITOS_REUNIOES updateREU_AMBITOS_REUNIOES(final REU_AMBITOS_REUNIOES REU_AMBITOS_REUNIOES) {
		REU_AMBITOS_REUNIOES.setID_AMBITO(REU_AMBITOS_REUNIOES.getID_AMBITO());
		REU_AMBITOS_REUNIOES result = dao33.update(REU_AMBITOS_REUNIOES);

		Query query_folder = entityManager
				.createNativeQuery("EXEC ATUALIZAR_REU_AMBITOS_REUNIOES " + result.getID_AMBITO());
		query_folder.getResultList();

		return result;

	}

	/************************************* REU_AMBITOS_REUNIOES_PARTICIPANTES */
	@POST
	@Path("/createREU_AMBITOS_REUNIOES_PARTICIPANTES")
	@Consumes("*/*")
	@Produces("application/json")
	public REU_AMBITOS_REUNIOES_PARTICIPANTES insertREU_AMBITOS_REUNIOES_PARTICIPANTESA(
			final REU_AMBITOS_REUNIOES_PARTICIPANTES data) {
		return dao34.create(data);
	}

	@GET
	@Path("/getREU_AMBITOS_REUNIOES_PARTICIPANTES")
	@Produces("application/json")
	public List<REU_AMBITOS_REUNIOES_PARTICIPANTES> getREU_AMBITOS_REUNIOES_PARTICIPANTES() {
		return dao34.getall();
	}

	@GET
	@Path("/getREU_AMBITOS_REUNIOES_PARTICIPANTESbyid/{id}")
	@Produces("application/json")
	public List<REU_AMBITOS_REUNIOES_PARTICIPANTES> getREU_AMBITOS_REUNIOES_PARTICIPANTESbyid(
			@PathParam("id") Integer id) {
		return dao34.getbyid(id);
	}

	@DELETE
	@Path("/deleteREU_AMBITOS_REUNIOES_PARTICIPANTES/{id}")
	public void deleteREU_AMBITOS_REUNIOES_PARTICIPANTES(@PathParam("id") Integer id) {
		REU_AMBITOS_REUNIOES_PARTICIPANTES REU_AMBITOS_REUNIOES_PARTICIPANTES = new REU_AMBITOS_REUNIOES_PARTICIPANTES();
		REU_AMBITOS_REUNIOES_PARTICIPANTES.setID(id);
		dao34.delete(REU_AMBITOS_REUNIOES_PARTICIPANTES);
	}

	@PUT
	@Path("/updateREU_AMBITOS_REUNIOES_PARTICIPANTES")
	@Consumes("*/*")
	@Produces("application/json")
	public REU_AMBITOS_REUNIOES_PARTICIPANTES updateREU_AMBITOS_REUNIOES_PARTICIPANTES(
			final REU_AMBITOS_REUNIOES_PARTICIPANTES REU_AMBITOS_REUNIOES_PARTICIPANTES) {
		REU_AMBITOS_REUNIOES_PARTICIPANTES.setID(REU_AMBITOS_REUNIOES_PARTICIPANTES.getID());
		return dao34.update(REU_AMBITOS_REUNIOES_PARTICIPANTES);
	}

	/************************************* REU_REUNIOES */
	@POST
	@Path("/createREU_REUNIOES")
	@Consumes("*/*")
	@Produces("application/json")
	public REU_REUNIOES insertREU_REUNIOESA(final REU_REUNIOES data) {
		return dao35.create(data);
	}

	@GET
	@Path("/getREU_REUNIOES")
	@Produces("application/json")
	public List<REU_REUNIOES> getREU_REUNIOES() {
		return dao35.getall();
	}

	@GET
	@Path("/getREU_REUNIOESbyid/{id}")
	@Produces("application/json")
	public List<REU_REUNIOES> getREU_REUNIOESbyid(@PathParam("id") Integer id) {
		return dao35.getbyid(id);
	}

	@DELETE
	@Path("/deleteREU_REUNIOES/{id}")
	public void deleteREU_REUNIOES(@PathParam("id") Integer id) {
		REU_REUNIOES REU_REUNIOES = new REU_REUNIOES();
		REU_REUNIOES.setID_REUNIAO(id);
		dao35.delete(REU_REUNIOES);
	}

	@PUT
	@Path("/updateREU_REUNIOES")
	@Consumes("*/*")
	@Produces("application/json")
	public REU_REUNIOES updateREU_REUNIOES(final REU_REUNIOES REU_REUNIOES) {
		REU_REUNIOES.setID_REUNIAO(REU_REUNIOES.getID_REUNIAO());
		return dao35.update(REU_REUNIOES);
	}

	/************************************* REU_REUNIOES_PARTICIPANTES */
	@POST
	@Path("/createREU_REUNIOES_PARTICIPANTES")
	@Consumes("*/*")
	@Produces("application/json")
	public REU_REUNIOES_PARTICIPANTES insertREU_REUNIOES_PARTICIPANTESA(final REU_REUNIOES_PARTICIPANTES data) {
		return dao36.create(data);
	}

	@GET
	@Path("/getREU_REUNIOES_PARTICIPANTES")
	@Produces("application/json")
	public List<REU_REUNIOES_PARTICIPANTES> getREU_REUNIOES_PARTICIPANTES() {
		return dao36.getall();
	}

	@GET
	@Path("/getREU_REUNIOES_PARTICIPANTESbyid/{id}")
	@Produces("application/json")
	public List<REU_REUNIOES_PARTICIPANTES> getREU_REUNIOES_PARTICIPANTESbyid(@PathParam("id") Integer id) {
		return dao36.getbyid(id);
	}

	@DELETE
	@Path("/deleteREU_REUNIOES_PARTICIPANTES/{id}")
	public void deleteREU_REUNIOES_PARTICIPANTES(@PathParam("id") Integer id) {
		REU_REUNIOES_PARTICIPANTES REU_REUNIOES_PARTICIPANTES = new REU_REUNIOES_PARTICIPANTES();
		REU_REUNIOES_PARTICIPANTES.setID(id);
		dao36.delete(REU_REUNIOES_PARTICIPANTES);
	}

	@PUT
	@Path("/updateREU_REUNIOES_PARTICIPANTES")
	@Consumes("*/*")
	@Produces("application/json")
	public REU_REUNIOES_PARTICIPANTES updateREU_REUNIOES_PARTICIPANTES(
			final REU_REUNIOES_PARTICIPANTES REU_REUNIOES_PARTICIPANTES) {
		REU_REUNIOES_PARTICIPANTES.setID(REU_REUNIOES_PARTICIPANTES.getID());
		return dao36.update(REU_REUNIOES_PARTICIPANTES);
	}

	/************************************* REU_REUNIOES_FICHEIROS */
	@POST
	@Path("/createREU_REUNIOES_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public REU_REUNIOES_FICHEIROS insertREU_REUNIOES_FICHEIROSA(final REU_REUNIOES_FICHEIROS data) {
		return dao37.create(data);
	}

	@GET
	@Path("/getREU_REUNIOES_FICHEIROS")
	@Produces("application/json")
	public List<REU_REUNIOES_FICHEIROS> getREU_REUNIOES_FICHEIROS() {
		return dao37.getall();
	}

	@GET
	@Path("/getREU_REUNIOES_FICHEIROSbyid/{id}")
	@Produces("application/json")
	public List<REU_REUNIOES_FICHEIROS> getREU_REUNIOES_FICHEIROSbyid(@PathParam("id") Integer id) {
		return dao37.getbyid(id);
	}

	@DELETE
	@Path("/deleteREU_REUNIOES_FICHEIROS/{id}")
	public void deleteREU_REUNIOES_FICHEIROS(@PathParam("id") Integer id) {
		REU_REUNIOES_FICHEIROS REU_REUNIOES_FICHEIROS = new REU_REUNIOES_FICHEIROS();
		REU_REUNIOES_FICHEIROS.setID(id);
		dao37.delete(REU_REUNIOES_FICHEIROS);
	}

	@PUT
	@Path("/updateREU_REUNIOES_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public REU_REUNIOES_FICHEIROS updateREU_REUNIOES_FICHEIROS(final REU_REUNIOES_FICHEIROS REU_REUNIOES_FICHEIROS) {
		REU_REUNIOES_FICHEIROS.setID(REU_REUNIOES_FICHEIROS.getID());
		return dao37.update(REU_REUNIOES_FICHEIROS);
	}

	/************************************* LG_ANALISE_ENVIOS */
	@POST
	@Path("/createLG_ANALISE_ENVIOS")
	@Consumes("*/*")
	@Produces("application/json")
	public LG_ANALISE_ENVIOS insertLG_ANALISE_ENVIOSA(final LG_ANALISE_ENVIOS data) {
		return dao38.create(data);
	}

	@GET
	@Path("/getLG_ANALISE_ENVIOS")
	@Produces("application/json")
	public List<LG_ANALISE_ENVIOS> getLG_ANALISE_ENVIOS() {
		return dao38.getall();
	}

	@GET
	@Path("/getLG_ANALISE_ENVIOSbyid/{id}")
	@Produces("application/json")
	public List<LG_ANALISE_ENVIOS> getLG_ANALISE_ENVIOSbyid(@PathParam("id") Integer id) {
		return dao38.getbyid(id);
	}

	@DELETE
	@Path("/deleteLG_ANALISE_ENVIOS/{id}")
	public void deleteLG_ANALISE_ENVIOS(@PathParam("id") Integer id) {
		LG_ANALISE_ENVIOS LG_ANALISE_ENVIOS = new LG_ANALISE_ENVIOS();
		LG_ANALISE_ENVIOS.setID(id);
		dao38.delete(LG_ANALISE_ENVIOS);
	}

	@PUT
	@Path("/updateLG_ANALISE_ENVIOS")
	@Consumes("*/*")
	@Produces("application/json")
	public LG_ANALISE_ENVIOS updateLG_ANALISE_ENVIOS(final LG_ANALISE_ENVIOS LG_ANALISE_ENVIOS) {
		LG_ANALISE_ENVIOS.setID(LG_ANALISE_ENVIOS.getID());
		return dao38.update(LG_ANALISE_ENVIOS);
	}

	@POST
	@Consumes("*/*")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/importFicheirosTNT/{user}")
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream, @PathParam("user") String user) {

		// Boolean trace = readFile.file(uploadedInputStream, user,app);

		String str = "";
		StringBuffer buf = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(uploadedInputStream));

			try {
				while ((str = reader.readLine()) != null) {
					buf.append(str + "\n");
				}
			} catch (IOException e) {
			}

		} finally {

		}

		String output = "File imported!" + buf;
		return Response.ok().entity(output).build();
	}

	/************************************ MAN_MOV_MANUTENCAO_CAB */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_CAB insertMAN_MOV_MANUTENCAO_CAB(final MAN_MOV_MANUTENCAO_CAB data) {
		return dao39.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_CAB")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_CAB> getMAN_MOV_MANUTENCAO_CAB() {
		return dao39.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_CABbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_CAB> getMAN_MOV_MANUTENCAO_CABbyid(@PathParam("id") Integer id) {
		// return .getbyid(id);
		return null;
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_CAB_HISTORICO/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_CAB> getMAN_MOV_MANUTENCAO_CAB_HISTORICO(@PathParam("id") Integer id) {
		return dao39.getHistorico(id);
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_CAB/{id}")
	public void deleteMAN_MOV_MANUTENCAO_CAB(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_CAB MAN_MOV_MANUTENCAO_CAB = new MAN_MOV_MANUTENCAO_CAB();
		MAN_MOV_MANUTENCAO_CAB.setID_MANUTENCAO_CAB(id);
		dao39.delete(MAN_MOV_MANUTENCAO_CAB);
	}

	/************************************* MAN_MOV_MANUTENCAO_OPERARIOS */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_OPERARIOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_OPERARIOS insertMAN_MOV_MANUTENCAO_OPERARIOS(final MAN_MOV_MANUTENCAO_OPERARIOS data) {
		return dao40.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_OPERARIOS")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_OPERARIOS> getMAN_MOV_MANUTENCAO_OPERARIOS() {
		return dao40.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_OPERARIOSbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_OPERARIOS> getMAN_MOV_MANUTENCAO_OPERARIOSbyid(@PathParam("id") Integer id) {
		// return dao40.getbyid(id);
		return null;
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_OPERARIOS/{id}")
	public void deleteMAN_MOV_MANUTENCAO_OPERARIOS(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_OPERARIOS MAN_MOV_MANUTENCAO_OPERARIOS = new MAN_MOV_MANUTENCAO_OPERARIOS();
		MAN_MOV_MANUTENCAO_OPERARIOS.setID_MANUTENCAO_OPERARIOS(id);
		dao40.delete(MAN_MOV_MANUTENCAO_OPERARIOS);
	}

	/************************************* MAN_MOV_MANUTENCAO_PAUSAS */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_PAUSAS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_PAUSAS insertMAN_MOV_MANUTENCAO_PAUSAS(final MAN_MOV_MANUTENCAO_PAUSAS data) {
		return dao41.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_PAUSAS")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_PAUSAS> getMAN_MOV_MANUTENCAO_PAUSAS() {
		return dao41.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_PAUSASbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_PAUSAS> getMAN_MOV_MANUTENCAO_PAUSASbyid(@PathParam("id") Integer id) {
		// return dao41.getbyid(id);
		return null;
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_PAUSAS/{id}")
	public void deleteMAN_MOV_MANUTENCAO_PAUSAS(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_PAUSAS MAN_MOV_MANUTENCAO_PAUSAS = new MAN_MOV_MANUTENCAO_PAUSAS();
		MAN_MOV_MANUTENCAO_PAUSAS.setID_MANUTENCAO_PAUSA(id);
		dao41.delete(MAN_MOV_MANUTENCAO_PAUSAS);
	}

	/************************************* MAN_MOV_MANUTENCAO_ACCOES */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_ACCOES insertMAN_MOV_MANUTENCAO_ACCOES(final MAN_MOV_MANUTENCAO_ACCOES data) {
		return dao42.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_ACCOES")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_ACCOES> getMAN_MOV_MANUTENCAO_ACCOES() {
		return dao42.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_ACCOESbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_ACCOES> getMAN_MOV_MANUTENCAO_ACCOESbyid(@PathParam("id") Integer id) {
		// return dao42.getbyid(id);
		return null;
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_ACCOES/{id}")
	public void deleteMAN_MOV_MANUTENCAO_ACCOES(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_ACCOES MAN_MOV_MANUTENCAO_ACCOES = new MAN_MOV_MANUTENCAO_ACCOES();
		MAN_MOV_MANUTENCAO_ACCOES.setID_MANUTENCAO_ACOES(id);
		dao42.delete(MAN_MOV_MANUTENCAO_ACCOES);
	}

	/************************************* MAN_MOV_MANUTENCAO_LISTA_MATERIAL */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_LISTA_MATERIAL")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_LISTA_MATERIAL insertMAN_MOV_MANUTENCAO_LISTA_MATERIAL(
			final MAN_MOV_MANUTENCAO_LISTA_MATERIAL data) {
		return dao43.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_LISTA_MATERIAL")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_LISTA_MATERIAL> getMAN_MOV_MANUTENCAO_LISTA_MATERIAL() {
		return dao43.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_LISTA_MATERIALbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_LISTA_MATERIAL> getMAN_MOV_MANUTENCAO_LISTA_MATERIALbyid(
			@PathParam("id") Integer id) {
		// return dao43.getbyid(id);
		return null;
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_LISTA_MATERIAL/{id}")
	public void deleteMAN_MOV_MANUTENCAO_LISTA_MATERIAL(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_LISTA_MATERIAL MAN_MOV_MANUTENCAO_LISTA_MATERIAL = new MAN_MOV_MANUTENCAO_LISTA_MATERIAL();
		MAN_MOV_MANUTENCAO_LISTA_MATERIAL.setID_MANUTENCAO_LISTA_MATERIAL(id);
		dao43.delete(MAN_MOV_MANUTENCAO_LISTA_MATERIAL);
	}

	/************************************ MAN_DIC_DIVISOES */

	@POST
	@Path("/createMAN_DIC_DIVISOES")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_DIVISOES insertMAN_DIC_DIVISOES(final MAN_DIC_DIVISOES data) {
		return dao44.create(data);
	}

	@GET
	@Path("/getMAN_DIC_DIVISOES")
	@Produces("application/json")
	public List<MAN_DIC_DIVISOES> getMAN_DIC_DIVISOES() {
		return dao44.getall();
	}

	@GET
	@Path("/getMAN_DIC_DIVISOESbyid/{id}")
	@Produces("application/json")
	public List<MAN_DIC_DIVISOES> getMAN_DIC_DIVISOESbyid(@PathParam("id") Integer id) {
		// return dao44.getbyid(id);
		return null;
	}

	@PUT
	@Path("/updateMAN_DIC_DIVISOES")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_DIVISOES updateMAN_DIC_DIVISOES(final MAN_DIC_DIVISOES MAN_DIC_DIVISOES) {
		MAN_DIC_DIVISOES.setID(MAN_DIC_DIVISOES.getID());
		return dao44.update(MAN_DIC_DIVISOES);
	}

	@DELETE
	@Path("/deleteMAN_DIC_DIVISOES/{id}")
	public void deleteMAN_DIC_DIVISOES(@PathParam("id") Integer id) {
		MAN_DIC_DIVISOES MAN_DIC_DIVISOES = new MAN_DIC_DIVISOES();
		MAN_DIC_DIVISOES.setID(id);
		dao44.delete(MAN_DIC_DIVISOES);
	}

	/************************************ MAN_DIC_EDIFICIOS */

	@POST
	@Path("/createMAN_DIC_EDIFICIOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_EDIFICIOS insertMAN_DIC_EDIFICIOS(final MAN_DIC_EDIFICIOS data) {
		return dao45.create(data);
	}

	@GET
	@Path("/getMAN_DIC_EDIFICIOS")
	@Produces("application/json")
	public List<MAN_DIC_EDIFICIOS> getMAN_DIC_EDIFICIOS() {
		return dao45.getall();
	}

	@GET
	@Path("/getMAN_DIC_EDIFICIOSbyid/{id}")
	@Produces("application/json")
	public List<MAN_DIC_EDIFICIOS> getMAN_DIC_EDIFICIOSbyid(@PathParam("id") Integer id) {
		// return dao45.getbyid(id);
		return null;
	}

	@PUT
	@Path("/updateMAN_DIC_EDIFICIOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_EDIFICIOS updateMAN_DIC_EDIFICIOS(final MAN_DIC_EDIFICIOS MAN_DIC_EDIFICIOS) {
		MAN_DIC_EDIFICIOS.setID(MAN_DIC_EDIFICIOS.getID());
		return dao45.update(MAN_DIC_EDIFICIOS);
	}

	@DELETE
	@Path("/deleteMAN_DIC_EDIFICIOS/{id}")
	public void deleteMAN_DIC_EDIFICIOS(@PathParam("id") Integer id) {
		MAN_DIC_EDIFICIOS MAN_DIC_EDIFICIOS = new MAN_DIC_EDIFICIOS();
		MAN_DIC_EDIFICIOS.setID(id);
		dao45.delete(MAN_DIC_EDIFICIOS);
	}

	/************************************ MAN_DIC_PISOS */

	@POST
	@Path("/createMAN_DIC_PISOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_PISOS insertMAN_DIC_PISOS(final MAN_DIC_PISOS data) {
		return dao46.create(data);
	}

	@GET
	@Path("/getMAN_DIC_PISOS")
	@Produces("application/json")
	public List<MAN_DIC_PISOS> getMAN_DIC_PISOS() {
		return dao46.getall();
	}

	@GET
	@Path("/getMAN_DIC_PISOSbyid/{id}")
	@Produces("application/json")
	public List<MAN_DIC_PISOS> getMAN_DIC_PISOSbyid(@PathParam("id") Integer id) {
		// return dao46.getbyid(id);
		return null;
	}

	@PUT
	@Path("/updateMAN_DIC_PISOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_PISOS updateMAN_DIC_PISOS(final MAN_DIC_PISOS MAN_DIC_PISOS) {
		MAN_DIC_PISOS.setID(MAN_DIC_PISOS.getID());
		return dao46.update(MAN_DIC_PISOS);
	}

	@GET
	@Path("/getALLLOCALLIZACOES")
	@Produces("application/json")
	public List<MAN_DIC_PISOS> getALLLOCALLIZACOES() {
		return dao46.getALLLOCALLIZACOES();
	}

	@DELETE
	@Path("/deleteMAN_DIC_PISOS/{id}")
	public void deleteMAN_DIC_PISOS(@PathParam("id") Integer id) {
		MAN_DIC_PISOS MAN_DIC_PISOS = new MAN_DIC_PISOS();
		MAN_DIC_PISOS.setID(id);
		dao46.delete(MAN_DIC_PISOS);
	}

	/************************************ MAN_MOV_MANUTENCAO_COMPONENTES */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_COMPONENTES")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_COMPONENTES insertMAN_MOV_MANUTENCAO_COMPONENTES(
			final MAN_MOV_MANUTENCAO_COMPONENTES data) {
		return dao47.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_COMPONENTES")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_COMPONENTES> getMAN_MOV_MANUTENCAO_COMPONENTES() {
		return dao47.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_COMPONENTESbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_COMPONENTES> getMAN_MOV_MANUTENCAO_COMPONENTESbyid(@PathParam("id") Integer id) {
		return dao47.getbyid(id);

	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_COMPONENTESbyid2/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_COMPONENTES> getMAN_MOV_MANUTENCAO_COMPONENTESbyid2(@PathParam("id") Integer id) {
		return dao47.getbyid2(id);

	}

	@PUT
	@Path("/updateMAN_MOV_MANUTENCAO_COMPONENTES")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_COMPONENTES updateMAN_MOV_MANUTENCAO_COMPONENTES(
			final MAN_MOV_MANUTENCAO_COMPONENTES MAN_MOV_MANUTENCAO_COMPONENTES) {
		MAN_MOV_MANUTENCAO_COMPONENTES.setID(MAN_MOV_MANUTENCAO_COMPONENTES.getID());
		return dao47.update(MAN_MOV_MANUTENCAO_COMPONENTES);
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_COMPONENTES/{id}")
	public void deleteMAN_MOV_MANUTENCAO_COMPONENTES(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_COMPONENTES MAN_MOV_MANUTENCAO_COMPONENTES = new MAN_MOV_MANUTENCAO_COMPONENTES();
		MAN_MOV_MANUTENCAO_COMPONENTES.setID(id);
		dao47.delete(MAN_MOV_MANUTENCAO_COMPONENTES);
	}

	/************************************ MAN_MOV_MANUTENCAO_DOCUMENTOS */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_DOCUMENTOS insertMAN_MOV_MANUTENCAO_DOCUMENTOS(final MAN_MOV_MANUTENCAO_DOCUMENTOS data) {
		return dao48.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_DOCUMENTOS")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_DOCUMENTOS> getMAN_MOV_MANUTENCAO_DOCUMENTOS() {
		return dao48.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_DOCUMENTOSbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_DOCUMENTOS> getMAN_MOV_MANUTENCAO_DOCUMENTOSbyid(@PathParam("id") Integer id) {
		return dao48.getbyid(id);
	}

	@PUT
	@Path("/updateMAN_MOV_MANUTENCAO_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_DOCUMENTOS updateMAN_MOV_MANUTENCAO_DOCUMENTOS(
			final MAN_MOV_MANUTENCAO_DOCUMENTOS MAN_MOV_MANUTENCAO_DOCUMENTOS) {
		MAN_MOV_MANUTENCAO_DOCUMENTOS.setID(MAN_MOV_MANUTENCAO_DOCUMENTOS.getID());
		return dao48.update(MAN_MOV_MANUTENCAO_DOCUMENTOS);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_DOCUMENTOSbyidEquipamento/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_DOCUMENTOS> getMAN_MOV_MANUTENCAO_DOCUMENTOSbyidEquipamento(
			@PathParam("id") Integer id) {
		return dao48.getbyidEquipamento(id);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_DOCUMENTOSbyidEquipamento2/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_DOCUMENTOS> getMAN_MOV_MANUTENCAO_DOCUMENTOSbyidEquipamento2(
			@PathParam("id") Integer id) {
		return dao48.getbyidEquipamento2(id);
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_DOCUMENTOS/{id}")
	public void deleteMAN_MOV_MANUTENCAO_DOCUMENTOS(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_DOCUMENTOS MAN_MOV_MANUTENCAO_DOCUMENTOS = new MAN_MOV_MANUTENCAO_DOCUMENTOS();
		MAN_MOV_MANUTENCAO_DOCUMENTOS.setID(id);
		dao48.delete(MAN_MOV_MANUTENCAO_DOCUMENTOS);
	}

	/************************************ MAN_MOV_MANUTENCAO_PLANOS */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_PLANOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_PLANOS insertMAN_MOV_MANUTENCAO_PLANOS(final MAN_MOV_MANUTENCAO_PLANOS data) {
		return dao49.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_PLANOS")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_PLANOS> getMAN_MOV_MANUTENCAO_PLANOS() {
		return dao49.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_PLANOSbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_PLANOS> getMAN_MOV_MANUTENCAO_PLANOSbyid(@PathParam("id") Integer id) {
		return dao49.getbyid(id);

	}

	@PUT
	@Path("/updateMAN_MOV_MANUTENCAO_PLANOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_PLANOS updateMAN_MOV_MANUTENCAO_PLANOS(
			final MAN_MOV_MANUTENCAO_PLANOS MAN_MOV_MANUTENCAO_PLANOS) {
		MAN_MOV_MANUTENCAO_PLANOS.setID(MAN_MOV_MANUTENCAO_PLANOS.getID());
		return dao49.update(MAN_MOV_MANUTENCAO_PLANOS);
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_PLANOS/{id}")
	public void deleteMAN_MOV_MANUTENCAO_PLANOS(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_PLANOS MAN_MOV_MANUTENCAO_PLANOS = new MAN_MOV_MANUTENCAO_PLANOS();
		MAN_MOV_MANUTENCAO_PLANOS.setID(id);
		dao49.delete(MAN_MOV_MANUTENCAO_PLANOS);
	}

	/************************************ MAN_DIC_EQUIPAS_UTILIZADORES */

	@POST
	@Path("/createMAN_DIC_EQUIPAS_UTILIZADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_EQUIPAS_UTILIZADORES insertMAN_DIC_EQUIPAS_UTILIZADORES(final MAN_DIC_EQUIPAS_UTILIZADORES data) {
		return dao50.create(data);
	}

	@GET
	@Path("/getMAN_DIC_EQUIPAS_UTILIZADORES")
	@Produces("application/json")
	public List<MAN_DIC_EQUIPAS_UTILIZADORES> getMAN_DIC_EQUIPAS_UTILIZADORES() {
		return dao50.getall();
	}

	@GET
	@Path("/getMAN_DIC_EQUIPAS_UTILIZADORESbyid/{id}")
	@Produces("application/json")
	public List<MAN_DIC_EQUIPAS_UTILIZADORES> getMAN_DIC_EQUIPAS_UTILIZADORESbyid(@PathParam("id") Integer id) {
		// return dao50.getbyid(id);
		return null;
	}

	@PUT
	@Path("/updateMAN_DIC_EQUIPAS_UTILIZADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_EQUIPAS_UTILIZADORES updateMAN_DIC_EQUIPAS_UTILIZADORES(
			final MAN_DIC_EQUIPAS_UTILIZADORES MAN_DIC_EQUIPAS_UTILIZADORES) {
		MAN_DIC_EQUIPAS_UTILIZADORES.setID(MAN_DIC_EQUIPAS_UTILIZADORES.getID());
		return dao50.update(MAN_DIC_EQUIPAS_UTILIZADORES);
	}

	@GET
	@Path("/getMAN_DIC_EQUIPAS_UTILIZADORES_ALLUSERS/{id}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getMAN_DIC_EQUIPAS_UTILIZADORES_ALLUSERS(@PathParam("id") Integer id) {
		return dao50.getMAN_DIC_EQUIPAS_UTILIZADORES_ALLUSERS(id);
	}

	@GET
	@Path("/getMAN_DIC_EQUIPAS_UTILIZADORES_EQUIPA/{id}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getMAN_DIC_EQUIPAS_UTILIZADORES_EQUIPA(@PathParam("id") Integer id) {
		return dao50.getMAN_DIC_EQUIPAS_UTILIZADORES_EQUIPA(id);
	}

	@DELETE
	@Path("/deleteMAN_DIC_EQUIPAS_UTILIZADORES/{id}")
	public void deleteMAN_DIC_EQUIPAS_UTILIZADORES(@PathParam("id") Integer id) {
		MAN_DIC_EQUIPAS_UTILIZADORES MAN_DIC_EQUIPAS_UTILIZADORES = new MAN_DIC_EQUIPAS_UTILIZADORES();
		MAN_DIC_EQUIPAS_UTILIZADORES.setID(id);
		dao50.delete(MAN_DIC_EQUIPAS_UTILIZADORES);
	}

	/************************************ MAN_MOV_MANUTENCAO_EQUIPAMENTOS */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_EQUIPAMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_EQUIPAMENTOS insertMAN_MOV_MANUTENCAO_EQUIPAMENTOS(
			final MAN_MOV_MANUTENCAO_EQUIPAMENTOS data) {
		return dao51.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_EQUIPAMENTOS")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> getMAN_MOV_MANUTENCAO_EQUIPAMENTOS() {
		return dao51.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_EQUIPAMENTOS2")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> getMAN_MOV_MANUTENCAO_EQUIPAMENTOS2() {
		return dao51.getall2();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_EQUIPAMENTOSbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> getMAN_MOV_MANUTENCAO_EQUIPAMENTOSbyid(@PathParam("id") Integer id) {
		return dao51.getbyid(id);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_EQUIPAMENTOSgetAllLocalizacao/{tipo}/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_EQUIPAMENTOS> getMAN_MOV_MANUTENCAO_EQUIPAMENTOSgetAllLocalizacao(
			@PathParam("tipo") String tipo, @PathParam("id") Integer id) {
		return dao51.getbyidlocalizacao(tipo, id);
	}

	@PUT
	@Path("/updateMAN_MOV_MANUTENCAO_EQUIPAMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_EQUIPAMENTOS updateMAN_MOV_MANUTENCAO_EQUIPAMENTOS(
			final MAN_MOV_MANUTENCAO_EQUIPAMENTOS MAN_MOV_MANUTENCAO_EQUIPAMENTOS) {
		MAN_MOV_MANUTENCAO_EQUIPAMENTOS.setID_MANUTENCAO(MAN_MOV_MANUTENCAO_EQUIPAMENTOS.getID_MANUTENCAO());
		return dao51.update(MAN_MOV_MANUTENCAO_EQUIPAMENTOS);
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_EQUIPAMENTOS/{id}")
	public void deleteMAN_MOV_MANUTENCAO_EQUIPAMENTOS(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_EQUIPAMENTOS MAN_MOV_MANUTENCAO_EQUIPAMENTOS = new MAN_MOV_MANUTENCAO_EQUIPAMENTOS();
		MAN_MOV_MANUTENCAO_EQUIPAMENTOS.setID_MANUTENCAO(id);
		dao51.delete(MAN_MOV_MANUTENCAO_EQUIPAMENTOS);
	}

	@POST
	@Path("/DUPLICA_MAN_MOV_MANUTENCAO_EQUIPAMENTOS")
	@Produces("application/json")
	public List<Object[]> DUPLICA_MAN_MOV_MANUTENCAO_EQUIPAMENTOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID_MANUTENCAO = firstMap.get("ID_MANUTENCAO");
		String NOME = firstMap.get("NOME");
		String UTZ_CRIA = firstMap.get("UTZ_CRIA");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC DUPLICA_MAN_MOV_MANUTENCAO_EQUIPAMENTOS " + ID_MANUTENCAO + ", '" + NOME + "', " + UTZ_CRIA + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************ MAN_MOV_MANUTENCAO_DADOS_COMPRA */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_DADOS_COMPRA")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_DADOS_COMPRA insertMAN_MOV_MANUTENCAO_DADOS_COMPRA(
			final MAN_MOV_MANUTENCAO_DADOS_COMPRA data) {
		return dao52.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_DADOS_COMPRA")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_DADOS_COMPRA> getMAN_MOV_MANUTENCAO_DADOS_COMPRA() {
		return dao52.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_DADOS_COMPRAbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_DADOS_COMPRA> getMAN_MOV_MANUTENCAO_DADOS_COMPRAbyid(@PathParam("id") Integer id) {
		return dao52.getbyid(id);
	}

	@PUT
	@Path("/updateMAN_MOV_MANUTENCAO_DADOS_COMPRA")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_DADOS_COMPRA updateMAN_MOV_MANUTENCAO_DADOS_COMPRA(
			final MAN_MOV_MANUTENCAO_DADOS_COMPRA MAN_MOV_MANUTENCAO_DADOS_COMPRA) {
		MAN_MOV_MANUTENCAO_DADOS_COMPRA.setID(MAN_MOV_MANUTENCAO_DADOS_COMPRA.getID());
		return dao52.update(MAN_MOV_MANUTENCAO_DADOS_COMPRA);
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_DADOS_COMPRA/{id}")
	public void deleteMAN_MOV_MANUTENCAO_DADOS_COMPRA(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_DADOS_COMPRA MAN_MOV_MANUTENCAO_DADOS_COMPRA = new MAN_MOV_MANUTENCAO_DADOS_COMPRA();
		MAN_MOV_MANUTENCAO_DADOS_COMPRA.setID(id);
		dao52.delete(MAN_MOV_MANUTENCAO_DADOS_COMPRA);
	}

	/************************************ MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE insertMAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE(
			final MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE data) {
		return dao53.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE> getMAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE() {
		return dao53.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_CONTRATOS_SUPORTEbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE> getMAN_MOV_MANUTENCAO_CONTRATOS_SUPORTEbyid(
			@PathParam("id") Integer id) {
		return dao53.getbyid(id);

	}

	@PUT
	@Path("/updateMAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE updateMAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE(
			final MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE) {
		MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE.setID(MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE.getID());
		return dao53.update(MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE);
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE/{id}")
	public void deleteMAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE = new MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE();
		MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE.setID(id);
		dao53.delete(MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE);
	}

	/************************************ MAN_DIC_EQUIPAS */

	@POST
	@Path("/createMAN_DIC_EQUIPAS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_EQUIPAS insertMAN_DIC_EQUIPAS(final MAN_DIC_EQUIPAS data) {
		return dao54.create(data);
	}

	@GET
	@Path("/getMAN_DIC_EQUIPAS")
	@Produces("application/json")
	public List<MAN_DIC_EQUIPAS> getMAN_DIC_EQUIPAS() {
		return dao54.getall();
	}

	@GET
	@Path("/getMAN_DIC_EQUIPASbyid/{id}")
	@Produces("application/json")
	public List<MAN_DIC_EQUIPAS> getMAN_DIC_EQUIPASbyid(@PathParam("id") Integer id) {
		// return dao50.getbyid(id);
		return null;
	}

	@PUT
	@Path("/updateMAN_DIC_EQUIPAS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_EQUIPAS updateMAN_DIC_EQUIPAS(final MAN_DIC_EQUIPAS MAN_DIC_EQUIPAS) {
		MAN_DIC_EQUIPAS.setID(MAN_DIC_EQUIPAS.getID());
		return dao54.update(MAN_DIC_EQUIPAS);
	}

	@DELETE
	@Path("/deleteMAN_DIC_EQUIPAS/{id}")
	public void deleteMAN_DIC_EQUIPAS(@PathParam("id") Integer id) {
		MAN_DIC_EQUIPAS MAN_DIC_EQUIPAS = new MAN_DIC_EQUIPAS();
		MAN_DIC_EQUIPAS.setID(id);
		dao54.delete(MAN_DIC_EQUIPAS);
	}

	/************************************ MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA insertMAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA(
			final MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA data) {
		return dao55.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA> getMAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA() {
		return dao55.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIAbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA> getMAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIAbyid(
			@PathParam("id") Integer id) {
		return dao55.getbyid(id);
	}

	@PUT
	@Path("/updateMAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA updateMAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA(
			final MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA) {
		MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA.setID(MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA.getID());
		return dao55.update(MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA);
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA/{id}")
	public void deleteMAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA = new MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA();
		MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA.setID(id);
		dao55.delete(MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA);
	}

	/************************************ MAN_MOV_PEDIDOS */

	@POST
	@Path("/createMAN_MOV_PEDIDOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_PEDIDOS insertMAN_MOV_PEDIDOS(final MAN_MOV_PEDIDOS data) {
		return dao56.create(data);
	}

	@GET
	@Path("/getMAN_MOV_PEDIDOS")
	@Produces("application/json")
	public List<MAN_MOV_PEDIDOS> getMAN_MOV_PEDIDOS() {
		return dao56.getall();
	}

	@GET
	@Path("/getMAN_MOV_PEDIDOS2/{classificacao}")
	@Produces("application/json")
	public List<MAN_MOV_PEDIDOS> getMAN_MOV_PEDIDOS2(@PathParam("classificacao") String classificacao) {
		return dao56.getall2(classificacao);
	}

	@GET
	@Path("/getMAN_MOV_PEDIDOSbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_PEDIDOS> getMAN_MOV_PEDIDOSbyid(@PathParam("id") Integer id) {
		return dao56.getbyid(id);
	}

	@PUT
	@Path("/updateMAN_MOV_PEDIDOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_PEDIDOS updateMAN_MOV_PEDIDOS(final MAN_MOV_PEDIDOS MAN_MOV_PEDIDOS) {
		MAN_MOV_PEDIDOS.setID_PEDIDO(MAN_MOV_PEDIDOS.getID_PEDIDO());
		return dao56.update(MAN_MOV_PEDIDOS);
	}

	@DELETE
	@Path("/deleteMAN_MOV_PEDIDOS/{id}")
	public void deleteMAN_MOV_PEDIDOS(@PathParam("id") Integer id) {
		MAN_MOV_PEDIDOS MAN_MOV_PEDIDOS = new MAN_MOV_PEDIDOS();
		MAN_MOV_PEDIDOS.setID_PEDIDO(id);
		dao56.delete(MAN_MOV_PEDIDOS);
	}

	@POST
	@Path("/MAN_CRIAR_MANUTENCOES_CORRETIVAS")
	@Produces("application/json")
	public List<Object[]> MAN_CRIAR_MANUTENCOES_CORRETIVAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID_PEDIDO = firstMap.get("ID_PEDIDO");
		String POSICAO = firstMap.get("POSICAO");

		Query query_folder = entityManager
				.createNativeQuery("EXEC MAN_CRIAR_MANUTENCOES_CORRETIVAS " + ID_PEDIDO + "," + POSICAO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************ MAN_MOV_PEDIDOS_DOCUMENTOS */

	@POST
	@Path("/createMAN_MOV_PEDIDOS_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_PEDIDOS_DOCUMENTOS insertMAN_MOV_PEDIDOS_DOCUMENTOS(final MAN_MOV_PEDIDOS_DOCUMENTOS data) {
		return dao57.create(data);
	}

	@GET
	@Path("/getMAN_MOV_PEDIDOS_DOCUMENTOS")
	@Produces("application/json")
	public List<MAN_MOV_PEDIDOS_DOCUMENTOS> getMAN_MOV_PEDIDOS_DOCUMENTOS() {
		return dao57.getall();
	}

	@GET
	@Path("/getMAN_MOV_PEDIDOS_DOCUMENTOSbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_PEDIDOS_DOCUMENTOS> getMAN_MOV_PEDIDOS_DOCUMENTOSbyid(@PathParam("id") Integer id) {
		return dao57.getbyid(id);
	}

	@PUT
	@Path("/updateMAN_MOV_PEDIDOS_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_PEDIDOS_DOCUMENTOS updateMAN_MOV_PEDIDOS_DOCUMENTOS(
			final MAN_MOV_PEDIDOS_DOCUMENTOS MAN_MOV_PEDIDOS_DOCUMENTOS) {
		MAN_MOV_PEDIDOS_DOCUMENTOS.setID(MAN_MOV_PEDIDOS_DOCUMENTOS.getID());
		return dao57.update(MAN_MOV_PEDIDOS_DOCUMENTOS);
	}

	@DELETE
	@Path("/deleteMAN_MOV_PEDIDOS_DOCUMENTOS/{id}")
	public void deleteMAN_MOV_PEDIDOS_DOCUMENTOS(@PathParam("id") Integer id) {
		MAN_MOV_PEDIDOS_DOCUMENTOS MAN_MOV_PEDIDOS_DOCUMENTOS = new MAN_MOV_PEDIDOS_DOCUMENTOS();
		MAN_MOV_PEDIDOS_DOCUMENTOS.setID(id);
		dao57.delete(MAN_MOV_PEDIDOS_DOCUMENTOS);
	}

	/************************************ MAN_MOV_MANUTENCAO_ANEXOS */

	@POST
	@Path("/createMAN_MOV_MANUTENCAO_ANEXOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_ANEXOS insertMAN_MOV_MANUTENCAO_ANEXOS(final MAN_MOV_MANUTENCAO_ANEXOS data) {
		return dao58.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_ANEXOS")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_ANEXOS> getMAN_MOV_MANUTENCAO_ANEXOS() {
		return dao58.getall();
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_ANEXOSbyid/{id}/{separador}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_ANEXOS> getMAN_MOV_MANUTENCAO_ANEXOSbyid(@PathParam("id") Integer id,
			@PathParam("separador") String separador) {
		return dao58.getbyid(id, separador);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_ANEXOSbyid2/{id}/{separador}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_ANEXOS> getMAN_MOV_MANUTENCAO_ANEXOSbyid2(@PathParam("id") Integer id,
			@PathParam("separador") String separador) {
		return dao58.getbyid2(id, separador);
	}

	@GET
	@Path("/getMAN_MOV_MANUTENCAO_ANEXOSbyidanexo/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MANUTENCAO_ANEXOS> getMAN_MOV_MANUTENCAO_ANEXOSbyidanexo(@PathParam("id") Integer id) {
		return dao58.getbyidanexo(id);
	}

	@PUT
	@Path("/updateMAN_MOV_MANUTENCAO_ANEXOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MANUTENCAO_ANEXOS updateMAN_MOV_MANUTENCAO_ANEXOS(
			final MAN_MOV_MANUTENCAO_ANEXOS MAN_MOV_MANUTENCAO_ANEXOS) {
		MAN_MOV_MANUTENCAO_ANEXOS.setID(MAN_MOV_MANUTENCAO_ANEXOS.getID());
		return dao58.update(MAN_MOV_MANUTENCAO_ANEXOS);
	}

	@DELETE
	@Path("/deleteMAN_MOV_MANUTENCAO_ANEXOS/{id}")
	public void deleteMAN_MOV_MANUTENCAO_ANEXOS(@PathParam("id") Integer id) {
		MAN_MOV_MANUTENCAO_ANEXOS MAN_MOV_MANUTENCAO_ANEXOS = new MAN_MOV_MANUTENCAO_ANEXOS();
		MAN_MOV_MANUTENCAO_ANEXOS.setID(id);
		dao58.delete(MAN_MOV_MANUTENCAO_ANEXOS);
	}

	@POST
	@Path("/FIN_EVOLUCAO_CLIENTES")
	@Produces("application/json")
	public List<Object[]> FIN_EVOLUCAO_CLIENTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String CLIENTE = firstMap.get("CLIENTE");
		String KAM = firstMap.get("KAM");

		if (CLIENTE != null)
			CLIENTE = "'" + CLIENTE + "'";
		if (KAM != null)
			KAM = "'" + KAM + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC FIN_EVOLUCAO_CLIENTES " + CLIENTE + "," + KAM + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/FIN_EVOLUCAO_KAM")
	@Produces("application/json")
	public List<Object[]> FIN_EVOLUCAO_KAM(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String KAM = firstMap.get("KAM");

		if (KAM != null)
			KAM = "'" + KAM + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC FIN_EVOLUCAO_KAM " + KAM + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/FIN_EVOLUCAO_DOCUMENTOS")
	@Produces("application/json")
	public List<Object[]> FIN_EVOLUCAO_DOCUMENTOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String CLIENTE = firstMap.get("CLIENTE");
		String ANO = firstMap.get("ANO");
		String MES = firstMap.get("MES");

		if (CLIENTE != null)
			CLIENTE = "'" + CLIENTE + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC FIN_EVOLUCAO_DOCUMENTOS " + CLIENTE + ", " + ANO + "," + MES + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/FIN_EVOLUCAO_LINHAS_DOCUMENTOS")
	@Produces("application/json")
	public List<Object[]> FIN_EVOLUCAO_LINHAS_DOCUMENTOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String N_DOCUMENTO = firstMap.get("N_DOCUMENTO");

		if (N_DOCUMENTO != null)
			N_DOCUMENTO = "'" + N_DOCUMENTO + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC FIN_EVOLUCAO_LINHAS_DOCUMENTOS " + N_DOCUMENTO + " ");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/FIN_EVOLUCAO_GRAFICOS_1")
	@Produces("application/json")
	public List<Object[]> FIN_EVOLUCAO_GRAFICOS_1() {
		Query query_folder = entityManager.createNativeQuery("EXEC FIN_EVOLUCAO_GRAFICOS_1 ");
		List<Object[]> dados_folder = query_folder.getResultList();
		return dados_folder;
	}

	@GET
	@Path("/FIN_EVOLUCAO_GRAFICOS_2")
	@Produces("application/json")
	public List<Object[]> FIN_EVOLUCAO_GRAFICOS_2() {
		Query query_folder = entityManager.createNativeQuery("EXEC FIN_EVOLUCAO_GRAFICOS_2 ");
		List<Object[]> dados_folder = query_folder.getResultList();
		return dados_folder;
	}

	/************************************ COM_ACORDOS */

	@POST
	@Path("/createCOM_ACORDOS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS insertCOM_ACORDOS(final COM_ACORDOS data) {
		return dao59.create(data);
	}

	@GET
	@Path("/getCOM_ACORDOS")
	@Produces("application/json")
	public List<COM_ACORDOS> getCOM_ACORDOS() {
		return dao59.getall();
	}

	@GET
	@Path("/getCOM_ACORDOS2")
	@Produces("application/json")
	public List<COM_ACORDOS> getCOM_ACORDOS2() {
		return dao59.getall2();
	}

	@GET
	@Path("/getCOM_ACORDOSbyid/{id}/{versao}")
	@Produces("application/json")
	public List<COM_ACORDOS> getCOM_ACORDOSbyid(@PathParam("id") Integer id, @PathParam("versao") Integer versao) {
		return dao59.getbyid(id, versao);
	}

	@GET
	@Path("/getCOM_ACORDOSbyid2/{id}/{versao}")
	@Produces("application/json")
	public List<COM_ACORDOS> getCOM_ACORDOSbyid2(@PathParam("id") Integer id, @PathParam("versao") Integer versao) {
		return dao59.getbyid2(id, versao);
	}

	@GET
	@Path("/getCOM_ACORDOS_VERSOES/{id}/{versao}")
	@Produces("application/json")
	public List<COM_ACORDOS> getCOM_ACORDOS_VERSOES(@PathParam("id") Integer id, @PathParam("versao") Integer versao) {
		return dao59.getversoes(id, versao);
	}

	@GET
	@Path("/getCOM_ACORDOS_VALIDA_ACORDO/{id_contrato}/{id_referencia}/{id_acordo}")
	@Produces("application/json")
	public List<Object[]> getCOM_ACORDOS_VALIDA_ACORDO(@PathParam("id_contrato") Integer id_contrato,
			@PathParam("id_referencia") Integer id_referencia, @PathParam("id_acordo") Integer id_acordo) {
		Query query = entityManager.createNativeQuery("DECLARE @ID_REFERENCIA int = " + id_referencia
				+ "; DECLARE @ID_CONTRATO int = " + id_contrato + ";DECLARE @ID_ACORDO int = " + id_acordo + "; "
				+ "select Count(*) as total, 'total' as txt from COM_ACORDOS a where ID_CONTRATO = @ID_CONTRATO AND ID_CONTRATO = @ID_CONTRATO AND INATIVO != 1 AND VERSAO = (select MAX(b.VERSAO) FROM COM_ACORDOS b WHERE b.ID = a.ID) AND a.ID != @ID_ACORDO");

		List<Object[]> dados = query.getResultList();
		return dados;
	}

	@PUT
	@Path("/updateCOM_ACORDOS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS updateCOM_ACORDOS(final COM_ACORDOS COM_ACORDOS) {
		COM_ACORDOS.setID(COM_ACORDOS.getID());
		COM_ACORDOS.setVERSAO(COM_ACORDOS.getVERSAO());
		return dao59.update(COM_ACORDOS);
	}

	@POST
	@Path("/updateCOM_ACORDOS_ESTADO/{tipo}/{tipo2}")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS updateCOM_ACORDOS_ESTADO(final COM_ACORDOS COM_ACORDOS, @PathParam("tipo") String tipo,
			@PathParam("tipo2") String tipo2) {
		Integer VERSAO = COM_ACORDOS.getVERSAO();
		Integer ID_ACORDO = COM_ACORDOS.getID();
		Integer UTILIZADOR = COM_ACORDOS.getUTZ_ULT_MODIF();

		if (tipo.equals("A")) {
			// APAGAR
			Query query = entityManager.createNativeQuery("UPDATE COM_ACORDOS SET ESTADO='A',UTZ_ANULACAO = "
					+ UTILIZADOR + ",DATA_ANULACAO = GETDATE(), INATIVO = 1 where ID = " + ID_ACORDO + " AND VERSAO = "
					+ VERSAO);
			query.executeUpdate();
		}

		if (tipo.equals("F")) {
			// FECHO
			Query query = entityManager.createNativeQuery("UPDATE COM_ACORDOS SET ESTADO='F',UTZ_FECHO = " + UTILIZADOR
					+ ",DATA_FECHO = GETDATE() where ID = " + ID_ACORDO + " AND VERSAO = " + VERSAO);
			query.executeUpdate();
		}

		if (tipo.equals("F_LTA") || tipo2.equals("F_ALL1") || tipo2.equals("F_ALLT")) {
			// FECHO LTA
			Query query = entityManager.createNativeQuery("UPDATE COM_ACORDOS SET  UTZ_FECHO_LTA = " + UTILIZADOR
					+ ",DATA_FECHO_LTA = GETDATE() where ID = " + ID_ACORDO + " AND VERSAO = " + VERSAO);
			query.executeUpdate();
		}

		if (tipo.equals("F_AMOR") || tipo2.equals("F_ALL2") || tipo2.equals("F_ALLT")) {
			// FECHO AMORTIZACOES
			Query query = entityManager.createNativeQuery("UPDATE COM_ACORDOS SET UTZ_FECHO_AMORTIZACOES = "
					+ UTILIZADOR + ",DATA_FECHO_AMORTIZACOES = GETDATE() where ID = " + ID_ACORDO + " AND VERSAO = "
					+ VERSAO);
			query.executeUpdate();
		}

		return null;
	}

	@PUT
	@Path("/updateCOM_ACORDOS_NOVA_VERSAO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> updateCOM_ACORDOS_NOVA_VERSAO(final COM_ACORDOS COM_ACORDOS) {
		Integer VERSAO = COM_ACORDOS.getVERSAO();
		Integer ID_ACORDO = COM_ACORDOS.getID();
		Integer UTILIZADOR = COM_ACORDOS.getUTZ_ULT_MODIF();
		Integer ID_REFERENCIA = COM_ACORDOS.getID_REFERENCIA();
		String OBSERVACOES = COM_ACORDOS.getOBSERVACOES();
		Integer ID_CONTRATO = COM_ACORDOS.getID_CONTRATO();
		BigDecimal PRECO_BASE = COM_ACORDOS.getPRECO_BASE();
		Date SOP = COM_ACORDOS.getSOP();
		Date EOP = COM_ACORDOS.getEOP();

		Query query = entityManager.createNativeQuery("EXEC COM_ACORDOS_CRIAR_NOVA_VERSAO " + VERSAO + "," + ID_ACORDO
				+ "," + UTILIZADOR + ",:ID_REFERENCIA,:OBSERVACOES,:ID_CONTRATO,:PRECO_BASE,:SOP,:EOP");
		query.setParameter("ID_REFERENCIA", ID_REFERENCIA);
		query.setParameter("OBSERVACOES", OBSERVACOES);
		query.setParameter("ID_CONTRATO", ID_CONTRATO);
		query.setParameter("PRECO_BASE", PRECO_BASE);
		query.setParameter("SOP", SOP);
		query.setParameter("EOP", EOP);
		List<Object[]> dados_folder = query.getResultList();
		return dados_folder;
	}

	@POST
	@Path("/COM_ACORDOS_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_ACORDOS_ANALISE(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String CONTRATO = firstMap.get("CONTRATO");
		String ID_REFERENCIA = firstMap.get("ID_REFERENCIA");
		String ID_ACORDO = firstMap.get("ID_ACORDO");
		String VERSAO = firstMap.get("VERSAO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (CONTRATO != null)
			CONTRATO = "'" + CONTRATO + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC COM_ACORDOS_ANALISE " + DATA_INICIO + ", " + DATA_FIM
				+ ", " + CONTRATO + "," + ID_REFERENCIA + "," + ID_ACORDO + "," + VERSAO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/COM_ACORDOS_ANALISE_1")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_ACORDOS_ANALISE_1(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String CONTRATO = firstMap.get("CONTRATO");
		String ID_REFERENCIA = firstMap.get("ID_REFERENCIA");
		String ID_ACORDO = firstMap.get("ID_ACORDO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (CONTRATO != null)
			CONTRATO = "'" + CONTRATO + "'";
		if (ID_REFERENCIA != null)
			ID_REFERENCIA = "'" + ID_REFERENCIA + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC COM_ACORDOS_ANALISE_1 " + DATA_INICIO + ", "
				+ DATA_FIM + ", " + CONTRATO + "," + ID_REFERENCIA + "," + ID_ACORDO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/COM_ACORDOS_ANALISE_2")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_ACORDOS_ANALISE_2(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String CONTRATO = firstMap.get("CONTRATO");
		String ID_REFERENCIA = firstMap.get("ID_REFERENCIA");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (CONTRATO != null)
			CONTRATO = "'" + CONTRATO + "'";
		if (ID_REFERENCIA != null)
			ID_REFERENCIA = "'" + ID_REFERENCIA + "'";

		Query query_folder = entityManager.createNativeQuery(
				"EXEC COM_ACORDOS_ANALISE_2 " + DATA_INICIO + ", " + DATA_FIM + ", " + CONTRATO + "," + ID_REFERENCIA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/COM_ACORDOS_ANALISE_3")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_ACORDOS_ANALISE_3(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String CONTRATO = firstMap.get("CONTRATO");
		String ID_REFERENCIA = firstMap.get("ID_REFERENCIA");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (CONTRATO != null)
			CONTRATO = "'" + CONTRATO + "'";
		if (ID_REFERENCIA != null)
			ID_REFERENCIA = "'" + ID_REFERENCIA + "'";

		Query query_folder = entityManager.createNativeQuery(
				"EXEC COM_ACORDOS_ANALISE_3 " + DATA_INICIO + ", " + DATA_FIM + ", " + CONTRATO + "," + ID_REFERENCIA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/COM_ACORDOS_ANALISE_CLIENTES")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_ACORDOS_ANALISE_CLIENTES(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);

		Query query_folder = entityManager.createNativeQuery(
				"select B.NOME_CLIENTE,B.N_CLIENTE , STRING_AGG (C.ID, ',') as REFERENCIAS_SILVER,  STRING_AGG (B.N_CONTRATO, ',') as CONTRATOS, MIN(A.SOP) as SOP,MAX(A.EOP) EOP "
						+ "FROM COM_ACORDOS A " + "LEFT JOIN COM_CONTRATOS B ON A.ID_CONTRATO = B.ID "
						+ "LEFT JOIN COM_REFERENCIAS C ON  A.ID_REFERENCIA = C.ID "
						+ "WHERE A.VERSAO = (select MAX(X.VERSAO) from COM_ACORDOS X WHERE A.ID = X.ID) AND A.INATIVO = 0 "
						+ "GROUP BY B.NOME_CLIENTE,B.N_CLIENTE");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/COM_ACORDOS_ANALISE_ACORDOS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_ACORDOS_ANALISE_ACORDOS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ID_CLIENTE = firstMap.get("ID_CLIENTE");

		if (ID_CLIENTE != null)
			ID_CLIENTE = "'" + ID_CLIENTE + "'";

		Query query_folder = entityManager.createNativeQuery("DECLARE @N_CLIENTE varchar(200) =  " + ID_CLIENTE
				+ "; select A.ID,A.VERSAO,B.N_CONTRATO,A.ID_REFERENCIA,C.COD_REFERENCIA,c.DESCRICAO,A.SOP,A.EOP,B.N_CLIENTE,b.NOME_CLIENTE from COM_ACORDOS A "
				+ " LEFT JOIN COM_CONTRATOS B ON A.ID_CONTRATO = B.ID "
				+ " LEFT JOIN COM_REFERENCIAS C ON  A.ID_REFERENCIA = C.ID "
				+ " WHERE A.VERSAO = (select MAX(X.VERSAO) from COM_ACORDOS X WHERE A.ID = X.ID) AND A.INATIVO = 0 AND ((@N_CLIENTE is null )OR(b.N_CLIENTE = @N_CLIENTE))");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@DELETE
	@Path("/deleteCOM_ACORDOS/{id}/{versao}")
	public void deleteCOM_ACORDOS(@PathParam("id") Integer id, @PathParam("versao") Integer versao) {
		COM_ACORDOS COM_ACORDOS = new COM_ACORDOS();
		COM_ACORDOS.setID(id);
		COM_ACORDOS.setVERSAO(versao);
		dao59.delete(COM_ACORDOS);
	}

	/************************************ COM_ACORDOS_ACTIVIDADES */

	@POST
	@Path("/createCOM_ACORDOS_ACTIVIDADES")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_ACTIVIDADES insertCOM_ACORDOS_ACTIVIDADES(final COM_ACORDOS_ACTIVIDADES data) {
		return dao60.create(data);
	}

	@GET
	@Path("/getCOM_ACORDOS_ACTIVIDADES")
	@Produces("application/json")
	public List<COM_ACORDOS_ACTIVIDADES> getCOM_ACORDOS_ACTIVIDADES() {
		return dao60.getall();
	}

	@GET
	@Path("/getCOM_ACORDOS_ACTIVIDADESbyid/{id}/{versao}")
	@Produces("application/json")
	public List<COM_ACORDOS_ACTIVIDADES> getCOM_ACORDOS_ACTIVIDADESbyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao60.getbyid(id, versao);
	}

	@PUT
	@Path("/updateCOM_ACORDOS_ACTIVIDADES")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_ACTIVIDADES updateCOM_ACORDOS_ACTIVIDADES(
			final COM_ACORDOS_ACTIVIDADES COM_ACORDOS_ACTIVIDADES) {
		COM_ACORDOS_ACTIVIDADES.setID(COM_ACORDOS_ACTIVIDADES.getID());
		return dao60.update(COM_ACORDOS_ACTIVIDADES);
	}

	@DELETE
	@Path("/deleteCOM_ACORDOS_ACTIVIDADES/{id}")
	public void deleteCOM_ACORDOS_ACTIVIDADES(@PathParam("id") Integer id) {
		COM_ACORDOS_ACTIVIDADES COM_ACORDOS_ACTIVIDADES = new COM_ACORDOS_ACTIVIDADES();
		COM_ACORDOS_ACTIVIDADES.setID(id);
		dao60.delete(COM_ACORDOS_ACTIVIDADES);
	}

	@GET
	@Path("/getCOM_ACORDOS_ACTIVIDADESbyidFICHEIRO/{id}")
	@Produces("application/json")
	public List<COM_ACORDOS_ACTIVIDADES> getCOM_ACORDOS_ACTIVIDADESbyidFICHEIRO(@PathParam("id") String id) {
		return dao60.getbyidFicheiro(id);
	}

	/************************************ COM_ACORDOS_AMORTIZACOES */

	@POST
	@Path("/createCOM_ACORDOS_AMORTIZACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_AMORTIZACOES insertCOM_ACORDOS_AMORTIZACOES(final COM_ACORDOS_AMORTIZACOES data) {
		return dao61.create(data);
	}

	@GET
	@Path("/getCOM_ACORDOS_AMORTIZACOES")
	@Produces("application/json")
	public List<COM_ACORDOS_AMORTIZACOES> getCOM_ACORDOS_AMORTIZACOES() {
		return dao61.getall();
	}

	@GET
	@Path("/getCOM_ACORDOS_AMORTIZACOESbyid/{id}/{versao}")
	@Produces("application/json")
	public List<COM_ACORDOS_AMORTIZACOES> getCOM_ACORDOS_AMORTIZACOESbyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao61.getbyid(id, versao);
	}

	@PUT
	@Path("/updateCOM_ACORDOS_AMORTIZACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_AMORTIZACOES updateCOM_ACORDOS_AMORTIZACOES(
			final COM_ACORDOS_AMORTIZACOES COM_ACORDOS_AMORTIZACOES) {
		COM_ACORDOS_AMORTIZACOES.setID(COM_ACORDOS_AMORTIZACOES.getID());
		return dao61.update(COM_ACORDOS_AMORTIZACOES);
	}

	@DELETE
	@Path("/deleteCOM_ACORDOS_AMORTIZACOES/{id}")
	public void deleteCOM_ACORDOS_AMORTIZACOES(@PathParam("id") Integer id) {
		COM_ACORDOS_AMORTIZACOES COM_ACORDOS_AMORTIZACOES = new COM_ACORDOS_AMORTIZACOES();
		COM_ACORDOS_AMORTIZACOES.setID(id);
		dao61.delete(COM_ACORDOS_AMORTIZACOES);
	}

	/************************************ COM_ACORDOS_ANEXOS */

	@POST
	@Path("/createCOM_ACORDOS_ANEXOS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_ANEXOS insertCOM_ACORDOS_ANEXOS(final COM_ACORDOS_ANEXOS data) {
		return dao62.create(data);
	}

	@GET
	@Path("/getCOM_ACORDOS_ANEXOS")
	@Produces("application/json")
	public List<COM_ACORDOS_ANEXOS> getCOM_ACORDOS_ANEXOS() {
		return dao62.getall();
	}

	@GET
	@Path("/getCOM_ACORDOS_ANEXOSbyid/{id}/{versao}")
	@Produces("application/json")
	public List<COM_ACORDOS_ANEXOS> getCOM_ACORDOS_ANEXOSbyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao62.getbyid(id, versao);
	}

	@PUT
	@Path("/updateCOM_ACORDOS_ANEXOS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_ANEXOS updateCOM_ACORDOS_ANEXOS(final COM_ACORDOS_ANEXOS COM_ACORDOS_ANEXOS) {
		COM_ACORDOS_ANEXOS.setID(COM_ACORDOS_ANEXOS.getID());
		return dao62.update(COM_ACORDOS_ANEXOS);
	}

	@GET
	@Path("/getCOM_ACORDOS_ANEXOSbyidFICHEIRO/{id}")
	@Produces("application/json")
	public List<COM_ACORDOS_ANEXOS> getCOM_ACORDOS_ANEXOSbyidFICHEIRO(@PathParam("id") String id) {
		return dao62.getbyidFicheiro(id);
	}

	@DELETE
	@Path("/deleteCOM_ACORDOS_ANEXOS/{id}")
	public void deleteCOM_ACORDOS_ANEXOS(@PathParam("id") Integer id) {
		COM_ACORDOS_ANEXOS COM_ACORDOS_ANEXOS = new COM_ACORDOS_ANEXOS();
		COM_ACORDOS_ANEXOS.setID(id);
		dao62.delete(COM_ACORDOS_ANEXOS);
	}

	/************************************ COM_ACORDOS_HISTORICO */

	@POST
	@Path("/createCOM_ACORDOS_HISTORICO")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_HISTORICO insertCOM_ACORDOS_HISTORICO(final COM_ACORDOS_HISTORICO data) {
		return dao63.create(data);
	}

	@GET
	@Path("/getCOM_ACORDOS_HISTORICO")
	@Produces("application/json")
	public List<COM_ACORDOS_HISTORICO> getCOM_ACORDOS_HISTORICO() {
		return dao63.getall();
	}

	@GET
	@Path("/getCOM_ACORDOS_HISTORICObyid/{id}/{versao}")
	@Produces("application/json")
	public List<COM_ACORDOS_HISTORICO> getCOM_ACORDOS_HISTORICObyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao63.getbyid(id, versao);
	}

	@PUT
	@Path("/updateCOM_ACORDOS_HISTORICO")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_HISTORICO updateCOM_ACORDOS_HISTORICO(final COM_ACORDOS_HISTORICO COM_ACORDOS_HISTORICO) {
		COM_ACORDOS_HISTORICO.setID(COM_ACORDOS_HISTORICO.getID());
		return dao63.update(COM_ACORDOS_HISTORICO);
	}

	@DELETE
	@Path("/deleteCOM_ACORDOS_HISTORICO/{id}")
	public void deleteCOM_ACORDOS_HISTORICO(@PathParam("id") Integer id) {
		COM_ACORDOS_HISTORICO COM_ACORDOS_HISTORICO = new COM_ACORDOS_HISTORICO();
		COM_ACORDOS_HISTORICO.setID(id);
		dao63.delete(COM_ACORDOS_HISTORICO);
	}

	/************************************ COM_ACORDOS_LTA */

	@POST
	@Path("/createCOM_ACORDOS_LTA")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_LTA insertCOM_ACORDOS_LTA(final COM_ACORDOS_LTA data) {
		return dao64.create(data);
	}

	@GET
	@Path("/getCOM_ACORDOS_LTA")
	@Produces("application/json")
	public List<COM_ACORDOS_LTA> getCOM_ACORDOS_LTA() {
		return dao64.getall();
	}

	@GET
	@Path("/getCOM_ACORDOS_LTAbyid/{id}/{versao}")
	@Produces("application/json")
	public List<COM_ACORDOS_LTA> getCOM_ACORDOS_LTAbyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao64.getbyid(id, versao);
	}

	@PUT
	@Path("/updateCOM_ACORDOS_LTA")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_LTA updateCOM_ACORDOS_LTA(final COM_ACORDOS_LTA COM_ACORDOS_LTA) {
		COM_ACORDOS_LTA.setID(COM_ACORDOS_LTA.getID());
		return dao64.update(COM_ACORDOS_LTA);
	}

	@DELETE
	@Path("/deleteCOM_ACORDOS_LTA/{id}")
	public void deleteCOM_ACORDOS_LTA(@PathParam("id") Integer id) {
		COM_ACORDOS_LTA COM_ACORDOS_LTA = new COM_ACORDOS_LTA();
		COM_ACORDOS_LTA.setID(id);
		dao64.delete(COM_ACORDOS_LTA);
	}

	/************************************ COM_ACORDOS_PRECOS */

	@POST
	@Path("/createCOM_ACORDOS_PRECOS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_PRECOS insertCOM_ACORDOS_PRECOS(final COM_ACORDOS_PRECOS data) {
		return dao65.create(data);
	}

	@GET
	@Path("/getCOM_ACORDOS_PRECOS")
	@Produces("application/json")
	public List<COM_ACORDOS_PRECOS> getCOM_ACORDOS_PRECOS() {
		return dao65.getall();
	}

	@GET
	@Path("/getCOM_ACORDOS_PRECOSbyid/{id}/{versao}")
	@Produces("application/json")
	public List<COM_ACORDOS_PRECOS> getCOM_ACORDOS_PRECOSbyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao65.getbyid(id, versao);
	}

	@PUT
	@Path("/updateCOM_ACORDOS_PRECOS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_PRECOS updateCOM_ACORDOS_PRECOS(final COM_ACORDOS_PRECOS COM_ACORDOS_PRECOS) {
		COM_ACORDOS_PRECOS.setID(COM_ACORDOS_PRECOS.getID());
		return dao65.update(COM_ACORDOS_PRECOS);
	}

	@DELETE
	@Path("/deleteCOM_ACORDOS_PRECOS/{id}")
	public void deleteCOM_ACORDOS_PRECOS(@PathParam("id") Integer id) {
		COM_ACORDOS_PRECOS COM_ACORDOS_PRECOS = new COM_ACORDOS_PRECOS();
		COM_ACORDOS_PRECOS.setID(id);
		dao65.delete(COM_ACORDOS_PRECOS);
	}

	/************************************ COM_ACORDOS_VOLUMES */

	@POST
	@Path("/createCOM_ACORDOS_VOLUMES")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_VOLUMES insertCOM_ACORDOS_VOLUMES(final COM_ACORDOS_VOLUMES data) {
		return dao66.create(data);
	}

	@GET
	@Path("/getCOM_ACORDOS_VOLUMES")
	@Produces("application/json")
	public List<COM_ACORDOS_VOLUMES> getCOM_ACORDOS_VOLUMES() {
		return dao66.getall();
	}

	@GET
	@Path("/getCOM_ACORDOS_VOLUMESbyid/{id}/{versao}")
	@Produces("application/json")
	public List<COM_ACORDOS_VOLUMES> getCOM_ACORDOS_VOLUMESbyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao66.getbyid(id, versao);
	}

	@PUT
	@Path("/updateCOM_ACORDOS_VOLUMES")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_ACORDOS_VOLUMES updateCOM_ACORDOS_VOLUMES(final COM_ACORDOS_VOLUMES COM_ACORDOS_VOLUMES) {
		COM_ACORDOS_VOLUMES.setID(COM_ACORDOS_VOLUMES.getID());
		return dao66.update(COM_ACORDOS_VOLUMES);
	}

	@DELETE
	@Path("/deleteCOM_ACORDOS_VOLUMES/{id}")
	public void deleteCOM_ACORDOS_VOLUMES(@PathParam("id") Integer id) {
		COM_ACORDOS_VOLUMES COM_ACORDOS_VOLUMES = new COM_ACORDOS_VOLUMES();
		COM_ACORDOS_VOLUMES.setID(id);
		dao66.delete(COM_ACORDOS_VOLUMES);
	}

	@DELETE
	@Path("/deleteCOM_ACORDOS_VOLUMES_TODOS/{id}/{versao}")
	public void deleteCOM_ACORDOS_VOLUMES_TODOS(@PathParam("id") Integer id, @PathParam("versao") Integer versao) {
		Query query = entityManager
				.createNativeQuery("DELETE COM_ACORDOS_VOLUMES where ID_ACORDO = " + id + " AND VERSAO = " + versao);
		query.executeUpdate();
	}

	/************************************ COM_CONTRATOS */

	@POST
	@Path("/createCOM_CONTRATOS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_CONTRATOS insertCOM_CONTRATOS(final COM_CONTRATOS data) {
		return dao67.create(data);
	}

	@GET
	@Path("/getCOM_CONTRATOS")
	@Produces("application/json")
	public List<COM_CONTRATOS> getCOM_CONTRATOS() {
		return dao67.getall();
	}

	@GET
	@Path("/getCOM_CONTRATOSbyid/{id}")
	@Produces("application/json")
	public List<COM_CONTRATOS> getCOM_CONTRATOSbyid(@PathParam("id") Integer id) {
		return dao67.getbyid(id);
	}

	@PUT
	@Path("/updateCOM_CONTRATOS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_CONTRATOS updateCOM_CONTRATOS(final COM_CONTRATOS COM_CONTRATOS) {
		COM_CONTRATOS.setID(COM_CONTRATOS.getID());
		return dao67.update(COM_CONTRATOS);
	}

	@DELETE
	@Path("/deleteCOM_CONTRATOS/{id}")
	public void deleteCOM_CONTRATOS(@PathParam("id") Integer id) {
		COM_CONTRATOS COM_CONTRATOS = new COM_CONTRATOS();
		COM_CONTRATOS.setID(id);
		dao67.delete(COM_CONTRATOS);
	}

	/************************************ COM_REFERENCIAS */

	@POST
	@Path("/createCOM_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_REFERENCIAS insertCOM_REFERENCIAS(final COM_REFERENCIAS data) {
		return dao68.create(data);
	}

	@GET
	@Path("/getCOM_REFERENCIAS")
	@Produces("application/json")
	public List<COM_REFERENCIAS> getCOM_REFERENCIAS() {
		return dao68.getall();
	}

	@GET
	@Path("/getCOM_REFERENCIASbyid/{id}")
	@Produces("application/json")
	public List<COM_REFERENCIAS> getCOM_REFERENCIASbyid(@PathParam("id") Integer id) {
		return dao68.getbyid(id);
	}

	@PUT
	@Path("/updateCOM_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_REFERENCIAS updateCOM_REFERENCIAS(final COM_REFERENCIAS COM_REFERENCIAS) {
		COM_REFERENCIAS.setID(COM_REFERENCIAS.getID());
		return dao68.update(COM_REFERENCIAS);
	}

	@DELETE
	@Path("/deleteCOM_REFERENCIAS/{id}")
	public void deleteCOM_REFERENCIAS(@PathParam("id") Integer id) {
		COM_REFERENCIAS COM_REFERENCIAS = new COM_REFERENCIAS();
		COM_REFERENCIAS.setID(id);
		dao68.delete(COM_REFERENCIAS);
	}

	/************************************ COM_REFERENCIAS_SILVER */

	@POST
	@Path("/createCOM_REFERENCIAS_SILVER")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_REFERENCIAS_SILVER insertCOM_REFERENCIAS_SILVER(final COM_REFERENCIAS_SILVER data) {
		return dao69.create(data);
	}

	@GET
	@Path("/getCOM_REFERENCIAS_SILVER")
	@Produces("application/json")
	public List<COM_REFERENCIAS_SILVER> getCOM_REFERENCIAS_SILVER() {
		return dao69.getall();
	}

	@GET
	@Path("/getCOM_REFERENCIAS_SILVERbyid/{id}")
	@Produces("application/json")
	public List<COM_REFERENCIAS_SILVER> getCOM_REFERENCIAS_SILVERbyid(@PathParam("id") Integer id) {
		return dao69.getbyid(id);
	}

	@PUT
	@Path("/updateCOM_REFERENCIAS_SILVER")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_REFERENCIAS_SILVER updateCOM_REFERENCIAS_SILVER(final COM_REFERENCIAS_SILVER COM_REFERENCIAS_SILVER) {
		COM_REFERENCIAS_SILVER.setID(COM_REFERENCIAS_SILVER.getID());
		return dao69.update(COM_REFERENCIAS_SILVER);
	}

	@DELETE
	@Path("/deleteCOM_REFERENCIAS_SILVER/{id}")
	public void deleteCOM_REFERENCIAS_SILVER(@PathParam("id") Integer id) {
		COM_REFERENCIAS_SILVER COM_REFERENCIAS_SILVER = new COM_REFERENCIAS_SILVER();
		COM_REFERENCIAS_SILVER.setID(id);
		dao69.delete(COM_REFERENCIAS_SILVER);
	}

	/************************************* AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES */
	@POST
	@Path("/createAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES insertAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES(
			final AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES data) {
		return dao70.create(data);
	}

	@GET
	@Path("/getAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORESbyid/{id}")
	@Produces("application/json")
	public List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES> getAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORESbyid_linha(
			@PathParam("id") Integer id) {
		return dao70.getbyid(id);
	}

	@GET
	@Path("/getAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES")
	@Produces("application/json")
	public List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES> getAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES() {
		return dao70.getall();
	}

	@DELETE
	@Path("/deleteAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES/{id}")
	public void deleteAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES(@PathParam("id") Integer id) {
		AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES = new AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES();
		AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES.setID(id);
		dao70.delete(AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES);
	}

	@PUT
	@Path("/updateAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES updateAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES(
			final AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES) {
		AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES.setID(AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES.getID());
		return dao70.update(AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES);
	}

	/************************************* AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS */
	@POST
	@Path("/createAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS insertAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS(
			final AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS data) {
		return dao71.create(data);
	}

	@GET
	@Path("/getAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOSbyid/{id}")
	@Produces("application/json")
	public List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS> getAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOSbyid_linha(
			@PathParam("id") Integer id) {
		return dao71.getbyid(id);
	}

	@GET
	@Path("/getAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS")
	@Produces("application/json")
	public List<AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS> getAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS() {
		return dao71.getall();
	}

	@DELETE
	@Path("/deleteAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS/{id}")
	public void deleteAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS(@PathParam("id") Integer id) {
		AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS = new AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS();
		AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS.setID(id);
		dao71.delete(AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS);
	}

	@PUT
	@Path("/updateAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS updateAB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS(
			final AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS) {
		AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS.setID(AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS.getID());
		return dao71.update(AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS);
	}

	/************************************* AB_DIC_DOSIFICACAO */
	@POST
	@Path("/createAB_DIC_DOSIFICACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_DOSIFICACAO insertAB_DIC_DOSIFICACAO(final AB_DIC_DOSIFICACAO data) {
		return dao72.create(data);
	}

	@GET
	@Path("/getAB_DIC_DOSIFICACAObyid/{id}")
	@Produces("application/json")
	public List<AB_DIC_DOSIFICACAO> getAB_DIC_DOSIFICACAObyid_linha(@PathParam("id") Integer id) {
		return dao72.getbyid(id);
	}

	@GET
	@Path("/getAB_DIC_DOSIFICACAO")
	@Produces("application/json")
	public List<AB_DIC_DOSIFICACAO> getAB_DIC_DOSIFICACAO() {
		return dao72.getall();
	}

	@DELETE
	@Path("/deleteAB_DIC_DOSIFICACAO/{id}")
	public void deleteAB_DIC_DOSIFICACAO(@PathParam("id") Integer id) {
		AB_DIC_DOSIFICACAO AB_DIC_DOSIFICACAO = new AB_DIC_DOSIFICACAO();
		AB_DIC_DOSIFICACAO.setID(id);
		dao72.delete(AB_DIC_DOSIFICACAO);
	}

	@PUT
	@Path("/updateAB_DIC_DOSIFICACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_DOSIFICACAO updateAB_DIC_DOSIFICACAO(final AB_DIC_DOSIFICACAO AB_DIC_DOSIFICACAO) {
		AB_DIC_DOSIFICACAO.setID(AB_DIC_DOSIFICACAO.getID());
		return dao72.update(AB_DIC_DOSIFICACAO);
	}

	/************************************* AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO */
	@POST
	@Path("/createAB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO insertAB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO(
			final AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO data) {
		return dao73.create(data);
	}

	@GET
	@Path("/getAB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAObyid/{id}")
	@Produces("application/json")
	public List<AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO> getAB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAObyid_linha(
			@PathParam("id") Integer id) {
		return dao73.getbyid(id);
	}

	@GET
	@Path("/getAB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO")
	@Produces("application/json")
	public List<AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO> getAB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO() {
		return dao73.getall();
	}

	@DELETE
	@Path("/deleteAB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO/{id}")
	public void deleteAB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO(@PathParam("id") Integer id) {
		AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO = new AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO();
		AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO.setID(id);
		dao73.delete(AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO);
	}

	@PUT
	@Path("/updateAB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO updateAB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO(
			final AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO) {
		AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO.setID(AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO.getID());
		return dao73.update(AB_DIC_DOSIFICACAO_HORARIOS_VERIFICACAO);
	}

	/************************************* AB_MOV_MANUTENCAO_DOSIFICADORES */
	@POST
	@Path("/createAB_MOV_MANUTENCAO_DOSIFICADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_MANUTENCAO_DOSIFICADORES insertAB_MOV_MANUTENCAO_DOSIFICADORES(
			final AB_MOV_MANUTENCAO_DOSIFICADORES data) {
		return dao74.create(data);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_DOSIFICADORESbyid/{id}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_DOSIFICADORES> getAB_MOV_MANUTENCAO_DOSIFICADORESbyid_linha(
			@PathParam("id") Integer id) {
		return dao74.getbyid(id);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_DOSIFICADORES")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_DOSIFICADORES> getAB_MOV_MANUTENCAO_DOSIFICADORES() {
		return dao74.getall();
	}

	@DELETE
	@Path("/deleteAB_MOV_MANUTENCAO_DOSIFICADORES/{id}")
	public void deleteAB_MOV_MANUTENCAO_DOSIFICADORES(@PathParam("id") Integer id) {
		AB_MOV_MANUTENCAO_DOSIFICADORES AB_MOV_MANUTENCAO_DOSIFICADORES = new AB_MOV_MANUTENCAO_DOSIFICADORES();
		AB_MOV_MANUTENCAO_DOSIFICADORES.setID(id);
		dao74.delete(AB_MOV_MANUTENCAO_DOSIFICADORES);
	}

	@PUT
	@Path("/updateAB_MOV_MANUTENCAO_DOSIFICADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_MANUTENCAO_DOSIFICADORES updateAB_MOV_MANUTENCAO_DOSIFICADORES(
			final AB_MOV_MANUTENCAO_DOSIFICADORES AB_MOV_MANUTENCAO_DOSIFICADORES) {
		AB_MOV_MANUTENCAO_DOSIFICADORES.setID(AB_MOV_MANUTENCAO_DOSIFICADORES.getID());
		return dao74.update(AB_MOV_MANUTENCAO_DOSIFICADORES);
	}

	@POST
	@Path("/ANALISE_DOSIFICADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> ANALISE_DOSIFICADORES(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String SEMANA = firstMap.get("SEMANA");
		String TINA = firstMap.get("TINA");

		Query query_folder = entityManager
				.createNativeQuery("EXEC ANALISE_DOSIFICADORES " + SEMANA + ", " + ANO + ", " + TINA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* PE_MOV_CAB */

	@POST
	@Path("/createPE_MOV_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PE_MOV_CAB insertPE_MOV_CABA(final PE_MOV_CAB data) {
		return dao75.create(data);
	}

	@GET
	@Path("/getPE_MOV_CAB")
	@Produces("application/json")
	public List<PE_MOV_CAB> getPE_MOV_CAB() {
		return dao75.getall();
	}

	@GET
	@Path("/getPE_MOV_CABbyid/{id}")
	@Produces("application/json")
	public List<PE_MOV_CAB> getPE_MOV_CABbyid(@PathParam("id") Integer id) {
		return dao75.getbyid(id);
	}

	@DELETE
	@Path("/deletePE_MOV_CAB/{id}")
	public void deletePE_MOV_CAB(@PathParam("id") Integer id) {
		PE_MOV_CAB PE_MOV_CAB = new PE_MOV_CAB();
		PE_MOV_CAB.setID(id);
		dao75.delete(PE_MOV_CAB);
	}

	@PUT
	@Path("/updatePE_MOV_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PE_MOV_CAB updatePE_MOV_CAB(final PE_MOV_CAB PE_MOV_CAB) {
		PE_MOV_CAB.setID(PE_MOV_CAB.getID());
		return dao75.update(PE_MOV_CAB);
	}

	@POST
	@Path("/getPE_MOV_CABbyTIPO/{tipo}")
	@Produces("application/json")
	public List<PE_MOV_CAB> getPE_MOV_CABbyTIPO(@PathParam("tipo") String tipo,
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String emAtraso = firstMap.get("EM_ATRASO");
		return dao75.getallbyTIPO(tipo, emAtraso);
	}

	/************************************* PE_MOV_FICHEIROS */
	@POST
	@Path("/createPE_MOV_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public PE_MOV_FICHEIROS insertPE_MOV_FICHEIROSA(final PE_MOV_FICHEIROS data) {
		return dao76.create(data);
	}

	@GET
	@Path("/getPE_MOV_FICHEIROS")
	@Produces("application/json")
	public List<PE_MOV_FICHEIROS> getPE_MOV_FICHEIROS() {
		return dao76.getall();
	}

	@GET
	@Path("/getPE_MOV_FICHEIROSbyidPLANO/{id}")
	@Produces("application/json")
	public List<PE_MOV_FICHEIROS> getPE_MOV_FICHEIROSbyidPLANO(@PathParam("id") Integer id) {
		return dao76.getbyid(id);
	}

	@DELETE
	@Path("/deletePE_MOV_FICHEIROS/{id}")
	public void deletePE_MOV_FICHEIROS(@PathParam("id") Integer id) {
		PE_MOV_FICHEIROS PE_MOV_FICHEIROS = new PE_MOV_FICHEIROS();
		PE_MOV_FICHEIROS.setID(id);
		dao76.delete(PE_MOV_FICHEIROS);
	}

	@PUT
	@Path("/updatePE_MOV_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public PE_MOV_FICHEIROS updatePE_MOV_FICHEIROS(final PE_MOV_FICHEIROS PE_MOV_FICHEIROS) {
		PE_MOV_FICHEIROS.setID(PE_MOV_FICHEIROS.getID());
		return dao76.update(PE_MOV_FICHEIROS);
	}

	/************************************ PE_MOV_CAB_HISTORICO */

	@POST
	@Path("/createPE_MOV_CAB_HISTORICO")
	@Consumes("*/*")
	@Produces("application/json")
	public PE_MOV_CAB_HISTORICO insertPE_MOV_CAB_HISTORICO(final PE_MOV_CAB_HISTORICO data) {
		return dao77.create(data);
	}

	@GET
	@Path("/getPE_MOV_CAB_HISTORICO")
	@Produces("application/json")
	public List<PE_MOV_CAB_HISTORICO> getPE_MOV_CAB_HISTORICO() {
		return dao77.getall();
	}

	@GET
	@Path("/getPE_MOV_CAB_HISTORICObyid/{id}")
	@Produces("application/json")
	public List<PE_MOV_CAB_HISTORICO> getPE_MOV_CAB_HISTORICObyid(@PathParam("id") Integer id) {
		return dao77.getbyid(id);
	}

	@PUT
	@Path("/updatePE_MOV_CAB_HISTORICO")
	@Consumes("*/*")
	@Produces("application/json")
	public PE_MOV_CAB_HISTORICO updatePE_MOV_CAB_HISTORICO(final PE_MOV_CAB_HISTORICO PE_MOV_CAB_HISTORICO) {
		PE_MOV_CAB_HISTORICO.setID(PE_MOV_CAB_HISTORICO.getID());
		return dao77.update(PE_MOV_CAB_HISTORICO);
	}

	@DELETE
	@Path("/deletePE_MOV_CAB_HISTORICO/{id}")
	public void deletePE_MOV_CAB_HISTORICO(@PathParam("id") Integer id) {
		PE_MOV_CAB_HISTORICO PE_MOV_CAB_HISTORICO = new PE_MOV_CAB_HISTORICO();
		PE_MOV_CAB_HISTORICO.setID(id);
		dao77.delete(PE_MOV_CAB_HISTORICO);
	}

	@POST
	@Path("/PE_GET_ACOES_EM_ATRASO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> PE_GET_ACOES_EM_ATRASO(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PAGINA = firstMap.get("PAGINA");

		Query query_folder = entityManager.createNativeQuery("EXEC PE_GET_ACOES_EM_ATRASO " + ANO + ", " + PAGINA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/PE_GET_ULTIMAS_ACOES_CONCLUIDAS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> PE_GET_ULTIMAS_ACOES_CONCLUIDAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String PAGINA = firstMap.get("PAGINA");

		Query query_folder = entityManager
				.createNativeQuery("EXEC PE_GET_ULTIMAS_ACOES_CONCLUIDAS " + ANO + ", " + PAGINA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/PE_GET_ANALISE_CONTADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> PE_GET_ANALISE_CONTADORES(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");

		Query query_folder = entityManager.createNativeQuery("EXEC PE_GET_ANALISE_CONTADORES " + ANO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/PE_GET_ANALISE_GRAFICO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> PE_GET_ANALISE_GRAFICO(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");

		Query query_folder = entityManager.createNativeQuery("EXEC PE_GET_ANALISE_GRAFICO " + ANO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getDASHBOARD_PLANEAMENTO_2")
	@Produces("application/json")
	public List<Object[]> getDASHBOARD_PLANEAMENTO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String SEMANA = firstMap.get("SEMANA");

		Query query_folder = entityManager
				.createNativeQuery("EXEC [DASHBOARD_PLANEAMENTO_2] " + ANO + "," + SEMANA + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getDASHBOARD_PLANEAMENTO_GRAFICOS")
	@Produces("application/json")
	public List<Object[]> getDASHBOARD_PLANEAMENTO_GRAFICOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String SEMANA = firstMap.get("SEMANA");

		Query query_folder = entityManager
				.createNativeQuery("EXEC [DASHBOARD_PLANEAMENTO_GRAFICOS] " + ANO + "," + SEMANA + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getDASHBOARD_RECURSOS_HUMANOS")
	@Produces("application/json")
	public List<Object[]> getDASHBOARD_RECURSOS_HUMANOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String SEMANA = firstMap.get("SEMANA");

		Query query_folder = entityManager.createNativeQuery("EXEC [DASHBOARD_RECURSOS_HUMANOS]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getDASHBOARD_CUMPRIMENTO_OBJETIVO_VENDAS")
	@Produces("application/json")
	public List<Object[]> getDASHBOARD_CUMPRIMENTO_OBJETIVO_VENDAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String SEMANA = firstMap.get("SEMANA");

		Query query_folder = entityManager.createNativeQuery("EXEC [DASHBOARD_CUMPRIMENTO_OBJETIVO_VENDAS] " + ANO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getDASHBOARD_VARIACAO_STOCK")
	@Produces("application/json")
	public List<Object[]> getDASHBOARD_VARIACAO_STOCK(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String SEMANA = firstMap.get("SEMANA");

		Query query_folder = entityManager.createNativeQuery("EXEC [DASHBOARD_VARIACAO_STOCK] " + ANO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getPA_GET_TOTAIS")
	@Produces("application/json")
	public List<Object[]> getPA_GET_TOTAIS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String SEMANA = firstMap.get("SEMANA");
		String UTILIZADOR = firstMap.get("UTILIZADOR");

		Query query_folder = entityManager.createNativeQuery("EXEC [PA_GET_TOTAIS] " + UTILIZADOR);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************ MAN_MOV_MAQUINAS_PARADAS */

	@POST
	@Path("/createMAN_MOV_MAQUINAS_PARADAS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MAQUINAS_PARADAS insertMAN_MOV_MAQUINAS_PARADAS(final MAN_MOV_MAQUINAS_PARADAS data) {
		return dao78.create(data);
	}

	@GET
	@Path("/getMAN_MOV_MAQUINAS_PARADAS")
	@Produces("application/json")
	public List<MAN_MOV_MAQUINAS_PARADAS> getMAN_MOV_MAQUINAS_PARADAS() {
		return dao78.getall();
	}

	@GET
	@Path("/getMAN_MOV_MAQUINAS_PARADASbyid/{id}")
	@Produces("application/json")
	public List<MAN_MOV_MAQUINAS_PARADAS> getMAN_MOV_MAQUINAS_PARADASbyid(@PathParam("id") Integer id) {
		return dao78.getbyid(id);
	}

	@PUT
	@Path("/updateMAN_MOV_MAQUINAS_PARADAS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_MOV_MAQUINAS_PARADAS updateMAN_MOV_MAQUINAS_PARADAS(
			final MAN_MOV_MAQUINAS_PARADAS MAN_MOV_MAQUINAS_PARADAS) {
		MAN_MOV_MAQUINAS_PARADAS.setID_PEDIDO(MAN_MOV_MAQUINAS_PARADAS.getID_PEDIDO());
		return dao78.update(MAN_MOV_MAQUINAS_PARADAS);
	}

	@DELETE
	@Path("/deleteMAN_MOV_MAQUINAS_PARADAS/{id}")
	public void deleteMAN_MOV_MAQUINAS_PARADAS(@PathParam("id") Integer id) {
		MAN_MOV_MAQUINAS_PARADAS MAN_MOV_MAQUINAS_PARADAS = new MAN_MOV_MAQUINAS_PARADAS();
		MAN_MOV_MAQUINAS_PARADAS.setID_PEDIDO(id);
		dao78.delete(MAN_MOV_MAQUINAS_PARADAS);
	}
 
	/************************************ MAN_DIC_NIVEIS_CRITICIDADE */

	@POST
	@Path("/createMAN_DIC_NIVEIS_CRITICIDADE")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_NIVEIS_CRITICIDADE insertMAN_DIC_NIVEIS_CRITICIDADE(final MAN_DIC_NIVEIS_CRITICIDADE data) {
		return dao79.create(data);
	}

	@GET
	@Path("/getMAN_DIC_NIVEIS_CRITICIDADE")
	@Produces("application/json")
	public List<MAN_DIC_NIVEIS_CRITICIDADE> getMAN_DIC_NIVEIS_CRITICIDADE() {
		return dao79.getall();
	}

	@GET
	@Path("/getMAN_DIC_NIVEIS_CRITICIDADEbyid/{id}")
	@Produces("application/json")
	public List<MAN_DIC_NIVEIS_CRITICIDADE> getMAN_DIC_NIVEIS_CRITICIDADEbyid(@PathParam("id") Integer id) {
		// return dao79.getbyid(id);
		return null;
	}

	@GET
	@Path("/getMAN_DIC_NIVEIS_CRITICIDADEbyNIVEL/{nivel}")
	@Produces("application/json")
	public List<MAN_DIC_NIVEIS_CRITICIDADE> getMAN_DIC_NIVEIS_CRITICIDADEbyNIVEL(@PathParam("nivel") Integer nivel) {
		return dao79.getbynivel(nivel);		
	}
	
	@PUT
	@Path("/updateMAN_DIC_NIVEIS_CRITICIDADE")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_NIVEIS_CRITICIDADE updateMAN_DIC_NIVEIS_CRITICIDADE(final MAN_DIC_NIVEIS_CRITICIDADE MAN_DIC_NIVEIS_CRITICIDADE) {
		MAN_DIC_NIVEIS_CRITICIDADE.setID(MAN_DIC_NIVEIS_CRITICIDADE.getID());
		return dao79.update(MAN_DIC_NIVEIS_CRITICIDADE);
	}

	@DELETE
	@Path("/deleteMAN_DIC_NIVEIS_CRITICIDADE/{id}")
	public void deleteMAN_DIC_NIVEIS_CRITICIDADE(@PathParam("id") Integer id) {
		MAN_DIC_NIVEIS_CRITICIDADE MAN_DIC_NIVEIS_CRITICIDADE = new MAN_DIC_NIVEIS_CRITICIDADE();
		MAN_DIC_NIVEIS_CRITICIDADE.setID(id);
		dao79.delete(MAN_DIC_NIVEIS_CRITICIDADE);
	}

	/************************************ MAN_DIC_AMBITO_UTILIZADORES */

	@POST
	@Path("/createMAN_DIC_AMBITO_UTILIZADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_AMBITO_UTILIZADORES insertMAN_DIC_AMBITO_UTILIZADORES(final MAN_DIC_AMBITO_UTILIZADORES data) {
		return dao80.create(data);
	}

	@GET
	@Path("/getMAN_DIC_AMBITO_UTILIZADORES")
	@Produces("application/json")
	public List<MAN_DIC_AMBITO_UTILIZADORES> getMAN_DIC_AMBITO_UTILIZADORES() {
		return dao80.getall();
	}

	@GET
	@Path("/getMAN_DIC_AMBITO_UTILIZADORESbyid/{id}")
	@Produces("application/json")
	public List<MAN_DIC_AMBITO_UTILIZADORES> getMAN_DIC_AMBITO_UTILIZADORESbyid(@PathParam("id") Integer id) {
		// return dao80.getbyid(id);
		return null;
	}

	@PUT
	@Path("/updateMAN_DIC_AMBITO_UTILIZADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_AMBITO_UTILIZADORES updateMAN_DIC_AMBITO_UTILIZADORES(
			final MAN_DIC_AMBITO_UTILIZADORES MAN_DIC_AMBITO_UTILIZADORES) {
		MAN_DIC_AMBITO_UTILIZADORES.setID(MAN_DIC_AMBITO_UTILIZADORES.getID());
		return dao80.update(MAN_DIC_AMBITO_UTILIZADORES);
	}

	@GET
	@Path("/getMAN_DIC_AMBITO_UTILIZADORES_ALLUSERS/{id}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getMAN_DIC_AMBITO_UTILIZADORES_ALLUSERS(@PathParam("id") Integer id) {
		return dao80.getMAN_DIC_AMBITO_UTILIZADORES_ALLUSERS(id);
	}

	@GET
	@Path("/getMAN_DIC_AMBITO_UTILIZADORES_EQUIPA/{id}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getMAN_DIC_AMBITO_UTILIZADORES_EQUIPA(@PathParam("id") Integer id) {
		return dao80.getMAN_DIC_AMBITO_UTILIZADORES_EQUIPA(id);
	}

	@DELETE
	@Path("/deleteMAN_DIC_AMBITO_UTILIZADORES/{id}")
	public void deleteMAN_DIC_AMBITO_UTILIZADORES(@PathParam("id") Integer id) {
		MAN_DIC_AMBITO_UTILIZADORES MAN_DIC_AMBITO_UTILIZADORES = new MAN_DIC_AMBITO_UTILIZADORES();
		MAN_DIC_AMBITO_UTILIZADORES.setID(id);
		dao80.delete(MAN_DIC_AMBITO_UTILIZADORES);
	}
	
	
	/************************************ MAN_DIC_AMBITOS */

	@POST
	@Path("/createMAN_DIC_AMBITOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_AMBITOS insertMAN_DIC_AMBITOS(final MAN_DIC_AMBITOS data) {
		return dao81.create(data);
	}

	@GET
	@Path("/getMAN_DIC_AMBITOS")
	@Produces("application/json")
	public List<MAN_DIC_AMBITOS> getMAN_DIC_AMBITOS() {
		return dao81.getall();
	}

	@GET
	@Path("/getMAN_DIC_AMBITOSbyid/{id}")
	@Produces("application/json")
	public List<MAN_DIC_AMBITOS> getMAN_DIC_AMBITOSbyid(@PathParam("id") Integer id) {
		// return dao50.getbyid(id);
		return null;
	}

	@PUT
	@Path("/updateMAN_DIC_AMBITOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_AMBITOS updateMAN_DIC_AMBITOS(final MAN_DIC_AMBITOS MAN_DIC_AMBITOS) {
		MAN_DIC_AMBITOS.setID(MAN_DIC_AMBITOS.getID());
		return dao81.update(MAN_DIC_AMBITOS);
	}

	@DELETE
	@Path("/deleteMAN_DIC_AMBITOS/{id}")
	public void deleteMAN_DIC_AMBITOS(@PathParam("id") Integer id) {
		MAN_DIC_AMBITOS MAN_DIC_AMBITOS = new MAN_DIC_AMBITOS();
		MAN_DIC_AMBITOS.setID(id);
		dao81.delete(MAN_DIC_AMBITOS);
	}

}
