package pt.example.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import pt.example.bootstrap.ConnectProgress;
import pt.example.dao.COMU_DIC_CATEGORIASDao;
import pt.example.dao.COMU_FICHEIROSDao;
import pt.example.dao.COMU_TICKERSDao;
import pt.example.dao.COM_BUDGETSDao;
import pt.example.dao.COM_BUDGETS_ANALISESDao;
import pt.example.dao.COM_BUDGETS_LINHASDao;
import pt.example.dao.COM_CUSTOMERS_GROUPSDao;
import pt.example.dao.COM_CUSTOMER_GROUPSDao;
import pt.example.dao.DOC_DIC_TIPOS_DOCUMENTO_UTZDao;
import pt.example.dao.DOC_DOCUMENTOSDao;
import pt.example.dao.DOC_DOCUMENTOS_VERSOESDao;
import pt.example.dao.FIN_DIC_FILTROSDao;
import pt.example.dao.FIN_DIC_TIPO_MOVIMENTO_STOCKDao;
import pt.example.dao.GER_CONF_CONSUMOS_SILVERDao;
import pt.example.dao.GER_CONF_CONSUMOS_SILVER_OFDao;
import pt.example.dao.GER_EVENTOS_DESTINATARIOSDao;
import pt.example.dao.GER_INFO_PAGINASDao;
import pt.example.dao.MAN_DIC_ARTIGOS_TIPOLOGIADao;
import pt.example.dao.MAN_DIC_FAMILIA_EQUIPAMENTOSDao;
import pt.example.dao.PIN_DIC_ARMAZEMDao;
import pt.example.dao.PIN_DIC_CABINESDao;
import pt.example.dao.PIN_DIC_CORESDao;
import pt.example.dao.PIN_DIC_CORES_ACABAMENTOSDao;
import pt.example.dao.PIN_DIC_POTESDao;
import pt.example.dao.PIN_DIC_PRE_SETDao;
import pt.example.dao.PIN_DIC_PRODUTOSDao;
import pt.example.dao.PIN_DIC_PRODUTOS_RELACIONADOSDao;
import pt.example.dao.PIN_DIC_PROGRAMASDao;
import pt.example.dao.PIN_DIC_PROGRAMAS_REFERENCIASDao;
import pt.example.dao.PIN_DIC_RACKSDao;
import pt.example.dao.PIN_DIC_REGISTO_BASTIDORDao;
import pt.example.dao.PIN_DIC_REGISTO_SALAS_MISTURADao;
import pt.example.dao.PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIASDao;
import pt.example.dao.PIN_DIC_TIPO_ACABAMENTODao;
import pt.example.dao.PIN_MOV_PREPARACAODao;
import pt.example.dao.PIN_MOV_PREPARACAO_CABDao;
import pt.example.dao.PIN_MOV_PREPARACAO_ETIQDao;
import pt.example.dao.PIN_MOV_PREPARACAO_LINHADao;
import pt.example.dao.PIN_MOV_RECEITASDao;
import pt.example.dao.PIN_MOV_RECEITAS_LINHASDao;
import pt.example.dao.PIN_MOV_RECEITAS_REFERENCIASDao;
import pt.example.dao.PIN_MOV_UV_RADIATIONDao;
import pt.example.dao.PIN_PLANEAMENTO_PINTURADao;
import pt.example.dao.PIN_PLANEAMENTO_PINTURA_LINHASDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_OPERACOES_CABDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHASDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_SECCOES_CABDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHASDao;
import pt.example.dao.PR_REGISTO_PINTURADao;
import pt.example.dao.PR_WINROBOT_USERSDao;
import pt.example.dao.RH_CANDIDATURASDao;
import pt.example.dao.RH_DADOS_FUNCIONARIODao;
import pt.example.dao.RH_DIC_AREA_FORMACAODao;
import pt.example.dao.RH_DIC_CRITERIOS_AVALIACAODao;
import pt.example.dao.RH_DIC_ENTIDADE_FORMADORADao;
import pt.example.dao.RH_DIC_ENTIDADE_MEDICADao;
import pt.example.dao.RH_DIC_ENTIDADE_MEDICA_LOCAISDao;
import pt.example.dao.RH_DIC_EXAMESDao;
import pt.example.dao.RH_DIC_PERIOCIDADE_ALERTASDao;
import pt.example.dao.RH_FORMACAODao;
import pt.example.dao.RH_FORMACAO_DOCUMENTOSDao;
import pt.example.dao.RH_FORMACAO_PARTICIPANTESDao;
import pt.example.dao.RH_MEDICINA_TRABALHODao;
import pt.example.dao.RH_MEDICINA_TRABALHO_DOCUMENTOSDao;
import pt.example.dao.RH_REGISTO_ACOESDao;
import pt.example.dao.RH_SUGESTOESDao;
import pt.example.dao.RH_SUGESTOES_ATIVIDADEDao;
import pt.example.dao.RH_SUGESTOES_DOCUMENTOSDao;
import pt.example.entity.COMU_DIC_CATEGORIAS;
import pt.example.entity.COMU_FICHEIROS;
import pt.example.entity.COMU_TICKERS;
import pt.example.entity.COM_ACORDOS;
import pt.example.entity.COM_BUDGETS;
import pt.example.entity.COM_BUDGETS_ANALISES;
import pt.example.entity.COM_BUDGETS_LINHAS;
import pt.example.entity.COM_CUSTOMERS_GROUPS;
import pt.example.entity.COM_CUSTOMER_GROUPS;
import pt.example.entity.DOC_DIC_TIPOS_DOCUMENTO_UTZ;
import pt.example.entity.DOC_DOCUMENTOS;
import pt.example.entity.DOC_DOCUMENTOS_VERSOES;
import pt.example.entity.FIN_DIC_FILTROS;
import pt.example.entity.FIN_DIC_TIPO_MOVIMENTO_STOCK;
import pt.example.entity.GER_CONF_CONSUMOS_SILVER;
import pt.example.entity.GER_CONF_CONSUMOS_SILVER_OF;
import pt.example.entity.GER_EVENTOS_DESTINATARIOS;
import pt.example.entity.GER_INFO_PAGINAS;
import pt.example.entity.GER_UTILIZADORES;
import pt.example.entity.MAN_DIC_ARTIGOS_TIPOLOGIA;
import pt.example.entity.MAN_DIC_FAMILIA_EQUIPAMENTOS;
import pt.example.entity.MAN_MOV_MANUTENCAO_OPERARIOS;
import pt.example.entity.PIN_DIC_ARMAZEM;
import pt.example.entity.PIN_DIC_CABINES;
import pt.example.entity.PIN_DIC_CORES;
import pt.example.entity.PIN_DIC_CORES_ACABAMENTOS;
import pt.example.entity.PIN_DIC_POTES;
import pt.example.entity.PIN_DIC_PRE_SET;
import pt.example.entity.PIN_DIC_PRODUTOS;
import pt.example.entity.PIN_DIC_PRODUTOS_RELACIONADOS;
import pt.example.entity.PIN_DIC_PROGRAMAS;
import pt.example.entity.PIN_DIC_PROGRAMAS_REFERENCIAS;
import pt.example.entity.PIN_DIC_RACKS;
import pt.example.entity.PIN_DIC_REGISTO_BASTIDOR;
import pt.example.entity.PIN_DIC_REGISTO_SALAS_MISTURA;
import pt.example.entity.PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS;
import pt.example.entity.PIN_DIC_TIPO_ACABAMENTO;
import pt.example.entity.PIN_MOV_PREPARACAO;
import pt.example.entity.PIN_MOV_PREPARACAO_CAB;
import pt.example.entity.PIN_MOV_PREPARACAO_ETIQ;
import pt.example.entity.PIN_MOV_PREPARACAO_LINHA;
import pt.example.entity.PIN_MOV_RECEITAS;
import pt.example.entity.PIN_MOV_RECEITAS_LINHAS;
import pt.example.entity.PIN_MOV_RECEITAS_REFERENCIAS;
import pt.example.entity.PIN_MOV_UV_RADIATION;
import pt.example.entity.PIN_PLANEAMENTO_PINTURA;
import pt.example.entity.PIN_PLANEAMENTO_PINTURA_LINHAS;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS;
import pt.example.entity.PR_REGISTO_PINTURA;
import pt.example.entity.PR_WINROBOT_CAB;
import pt.example.entity.PR_WINROBOT_USERS;
import pt.example.entity.RH_CANDIDATURAS;
import pt.example.entity.RH_DADOS_FUNCIONARIO;
import pt.example.entity.RH_DIC_AREA_FORMACAO;
import pt.example.entity.RH_DIC_CRITERIOS_AVALIACAO;
import pt.example.entity.RH_DIC_ENTIDADE_FORMADORA;
import pt.example.entity.RH_DIC_ENTIDADE_MEDICA;
import pt.example.entity.RH_DIC_ENTIDADE_MEDICA_LOCAIS;
import pt.example.entity.RH_DIC_EXAMES;
import pt.example.entity.RH_DIC_PERIOCIDADE_ALERTAS;
import pt.example.entity.RH_FORMACAO;
import pt.example.entity.RH_FORMACAO_DOCUMENTOS;
import pt.example.entity.RH_FORMACAO_PARTICIPANTES;
import pt.example.entity.RH_MEDICINA_TRABALHO;
import pt.example.entity.RH_MEDICINA_TRABALHO_DOCUMENTOS;
import pt.example.entity.RH_REGISTO_ACOES;
import pt.example.entity.RH_SUGESTOES;
import pt.example.entity.RH_SUGESTOES_ATIVIDADE;
import pt.example.entity.RH_SUGESTOES_DOCUMENTOS;

@Stateless
@Path("/sirb")
public class SIRB_3 {

	@Inject
	private GER_CONF_CONSUMOS_SILVERDao dao1;
	@Inject
	private GER_CONF_CONSUMOS_SILVER_OFDao dao2;
	@Inject
	private MAN_DIC_ARTIGOS_TIPOLOGIADao dao3;
	@Inject
	private GER_INFO_PAGINASDao dao4;
	@Inject
	private COM_CUSTOMER_GROUPSDao dao5;
	@Inject
	private COM_BUDGETSDao dao6;
	@Inject
	private COM_BUDGETS_ANALISESDao dao7;
	@Inject
	private COM_BUDGETS_LINHASDao dao8;
	@Inject
	private COM_CUSTOMERS_GROUPSDao dao9;
	@Inject
	private PIN_DIC_TIPO_ACABAMENTODao dao10;
	@Inject
	private PIN_DIC_ARMAZEMDao dao11;
	@Inject
	private PIN_DIC_CABINESDao dao12;
	@Inject
	private PIN_DIC_POTESDao dao13;
	@Inject
	private PIN_DIC_PRODUTOSDao dao14;
	@Inject
	private PIN_DIC_PRODUTOS_RELACIONADOSDao dao15;
	@Inject
	private PIN_MOV_PREPARACAODao dao16;
	@Inject
	private PIN_MOV_PREPARACAO_CABDao dao17;
	@Inject
	private PIN_MOV_PREPARACAO_LINHADao dao18;
	@Inject
	private PIN_MOV_PREPARACAO_ETIQDao dao19;
	@Inject
	private PIN_MOV_RECEITASDao dao20;
	@Inject
	private PIN_MOV_RECEITAS_LINHASDao dao21;
	@Inject
	private PIN_DIC_PRE_SETDao dao22;
	@Inject
	private PIN_DIC_REGISTO_BASTIDORDao dao23;
	@Inject
	private PIN_DIC_REGISTO_SALAS_MISTURADao dao24;
	@Inject
	private PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIASDao dao25;
	@Inject
	private PIN_DIC_CORESDao dao26;
	@Inject
	private PIN_DIC_PROGRAMASDao dao27;
	@Inject
	private PIN_DIC_PROGRAMAS_REFERENCIASDao dao28;
	@Inject
	private PIN_DIC_RACKSDao dao29;
	@Inject
	private FIN_DIC_TIPO_MOVIMENTO_STOCKDao dao30;
	@Inject
	private PIN_MOV_RECEITAS_REFERENCIASDao dao31;
	@Inject
	private FIN_DIC_FILTROSDao dao32;
	@Inject
	private RH_CANDIDATURASDao dao33;
	@Inject
	private MAN_DIC_FAMILIA_EQUIPAMENTOSDao dao34;
	@Inject
	private PR_WINROBOT_USERSDao dao35;
	@Inject
	private PIN_DIC_CORES_ACABAMENTOSDao dao36;
	@Inject
	private PR_REGISTO_PINTURADao dao37;
	@Inject
	private PIN_MOV_UV_RADIATIONDao dao38;
	@Inject
	private PIN_PLANEAMENTO_PINTURADao dao39;
	@Inject
	private PIN_PLANEAMENTO_PINTURA_LINHASDao dao40;
	@Inject
	private DOC_DOCUMENTOSDao dao41;
	@Inject
	private DOC_DIC_TIPOS_DOCUMENTO_UTZDao dao42;
	@Inject
	private DOC_DOCUMENTOS_VERSOESDao dao43;
	@Inject
	private RH_REGISTO_ACOESDao dao44;
	@Inject
	private RH_DIC_AREA_FORMACAODao dao45;
	@Inject
	private RH_DIC_CRITERIOS_AVALIACAODao dao46;
	@Inject
	private RH_DIC_ENTIDADE_FORMADORADao dao47;
	@Inject
	private RH_FORMACAO_DOCUMENTOSDao dao48;
	@Inject
	private RH_FORMACAODao dao49;
	@Inject
	private RH_FORMACAO_PARTICIPANTESDao dao50;
	@Inject
	private RH_MEDICINA_TRABALHO_DOCUMENTOSDao dao51;
	@Inject
	private RH_MEDICINA_TRABALHODao dao52;
	@Inject
	private RH_DIC_EXAMESDao dao53;
	@Inject
	private RH_DIC_ENTIDADE_MEDICADao dao54;
	@Inject
	private RH_DIC_ENTIDADE_MEDICA_LOCAISDao dao55;
	@Inject
	private RH_DIC_PERIOCIDADE_ALERTASDao dao56;
	@Inject
	private GER_EVENTOS_DESTINATARIOSDao dao57;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_SECCOES_CABDao dao58;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHASDao dao59;
	@Inject
	private RH_SUGESTOESDao dao60;
	@Inject
	private RH_SUGESTOES_ATIVIDADEDao dao61;
	@Inject
	private RH_DADOS_FUNCIONARIODao dao62;
	@Inject
	private RH_SUGESTOES_DOCUMENTOSDao dao63;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_OPERACOES_CABDao dao64;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHASDao dao65;
	@Inject
	private COMU_FICHEIROSDao dao66;
	@Inject
	private COMU_TICKERSDao dao67;
	@Inject
	private COMU_DIC_CATEGORIASDao dao68;

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	/************************************* GER_CONF_CONSUMOS_SILVER */
	@POST
	@Path("/createGER_CONF_CONSUMOS_SILVER")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_CONF_CONSUMOS_SILVER insertGER_CONF_CONSUMOS_SILVERA(final GER_CONF_CONSUMOS_SILVER data) {
		return dao1.create(data);
	}

	@GET
	@Path("/getGER_CONF_CONSUMOS_SILVER")
	@Produces("application/json")
	public List<GER_CONF_CONSUMOS_SILVER> getGER_CONF_CONSUMOS_SILVER() {
		return dao1.getall();
	}

	@GET
	@Path("/getGER_CONF_CONSUMOS_SILVERbyid/{id}")
	@Produces("application/json")
	public List<GER_CONF_CONSUMOS_SILVER> getGER_CONF_CONSUMOS_SILVERbyip(@PathParam("id") Integer id) {
		return dao1.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_CONF_CONSUMOS_SILVER/{id}")
	public void deleteGER_CONF_CONSUMOS_SILVER(@PathParam("id") Integer id) {
		GER_CONF_CONSUMOS_SILVER GER_CONF_CONSUMOS_SILVER = new GER_CONF_CONSUMOS_SILVER();
		GER_CONF_CONSUMOS_SILVER.setID_CONF(id);
		dao1.delete(GER_CONF_CONSUMOS_SILVER);
	}

	@PUT
	@Path("/updateGER_CONF_CONSUMOS_SILVER")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_CONF_CONSUMOS_SILVER updateGER_CONF_CONSUMOS_SILVER(
			final GER_CONF_CONSUMOS_SILVER GER_CONF_CONSUMOS_SILVER) {
		GER_CONF_CONSUMOS_SILVER.setID_CONF(GER_CONF_CONSUMOS_SILVER.getID_CONF());
		return dao1.update(GER_CONF_CONSUMOS_SILVER);
	}

	/************************************* GER_CONF_CONSUMOS_SILVER_OF */
	@POST
	@Path("/createGER_CONF_CONSUMOS_SILVER_OF")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_CONF_CONSUMOS_SILVER_OF insertGER_CONF_CONSUMOS_SILVER_OFA(final GER_CONF_CONSUMOS_SILVER_OF data) {
		return dao2.create(data);
	}

	@GET
	@Path("/getGER_CONF_CONSUMOS_SILVER_OF")
	@Produces("application/json")
	public List<GER_CONF_CONSUMOS_SILVER_OF> getGER_CONF_CONSUMOS_SILVER_OF() {
		return dao2.getall();
	}

	@GET
	@Path("/getGER_CONF_CONSUMOS_SILVER_OFbyidconf/{id}")
	@Produces("application/json")
	public List<GER_CONF_CONSUMOS_SILVER_OF> getGER_CONF_CONSUMOS_SILVER_OFbyip(@PathParam("id") Integer id) {
		return dao2.getbyidlinha(id);
	}

	@DELETE
	@Path("/deleteGER_CONF_CONSUMOS_SILVER_OF/{id}")
	public void deleteGER_CONF_CONSUMOS_SILVER_OF(@PathParam("id") Integer id) {
		GER_CONF_CONSUMOS_SILVER_OF GER_CONF_CONSUMOS_SILVER_OF = new GER_CONF_CONSUMOS_SILVER_OF();
		GER_CONF_CONSUMOS_SILVER_OF.setID_CONF_OF(id);
		dao2.delete(GER_CONF_CONSUMOS_SILVER_OF);
	}

	@PUT
	@Path("/updateGER_CONF_CONSUMOS_SILVER_OF")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_CONF_CONSUMOS_SILVER_OF updateGER_CONF_CONSUMOS_SILVER_OF(
			final GER_CONF_CONSUMOS_SILVER_OF GER_CONF_CONSUMOS_SILVER_OF) {
		GER_CONF_CONSUMOS_SILVER_OF.setID_CONF_OF(GER_CONF_CONSUMOS_SILVER_OF.getID_CONF_OF());
		return dao2.update(GER_CONF_CONSUMOS_SILVER_OF);
	}

	/************************************* MAN_DIC_ARTIGOS_TIPOLOGIA */
	@POST
	@Path("/createMAN_DIC_ARTIGOS_TIPOLOGIA")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_ARTIGOS_TIPOLOGIA insertMAN_DIC_ARTIGOS_TIPOLOGIAA(final MAN_DIC_ARTIGOS_TIPOLOGIA data) {
		return dao3.create(data);
	}

	@GET
	@Path("/getMAN_DIC_ARTIGOS_TIPOLOGIA")
	@Produces("application/json")
	public List<MAN_DIC_ARTIGOS_TIPOLOGIA> getMAN_DIC_ARTIGOS_TIPOLOGIA() {
		return dao3.getall();
	}

	@DELETE
	@Path("/deleteMAN_DIC_ARTIGOS_TIPOLOGIA/{id}")
	public void deleteMAN_DIC_ARTIGOS_TIPOLOGIA(@PathParam("id") Integer id) {
		MAN_DIC_ARTIGOS_TIPOLOGIA MAN_DIC_ARTIGOS_TIPOLOGIA = new MAN_DIC_ARTIGOS_TIPOLOGIA();
		MAN_DIC_ARTIGOS_TIPOLOGIA.setID(id);
		dao3.delete(MAN_DIC_ARTIGOS_TIPOLOGIA);
	}

	@PUT
	@Path("/updateMAN_DIC_ARTIGOS_TIPOLOGIA")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_ARTIGOS_TIPOLOGIA updateMAN_DIC_ARTIGOS_TIPOLOGIA(
			final MAN_DIC_ARTIGOS_TIPOLOGIA MAN_DIC_ARTIGOS_TIPOLOGIA) {
		MAN_DIC_ARTIGOS_TIPOLOGIA.setID(MAN_DIC_ARTIGOS_TIPOLOGIA.getID());
		return dao3.update(MAN_DIC_ARTIGOS_TIPOLOGIA);

	}

	@POST
	@Path("/MAN_ANALISE_TEMPOS_PREVISTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> MAN_ANALISE_TEMPOS_PREVISTOS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);

		Query query_folder = entityManager.createNativeQuery("EXEC MAN_ANALISE_TEMPOS_PREVISTOS");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/MAN_ANALISE_TRABALHOS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> MAN_ANALISE_TRABALHOS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String OPERARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC MAN_ANALISE_TRABALHOS " + DATA_INICIO + ", " + DATA_FIM + ", " + OPERARIO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/MAN_PRODUTIVIDADE")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> MAN_PRODUTIVIDADE(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String OPERARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC MAN_PRODUTIVIDADE " + DATA_INICIO + ", " + DATA_FIM + ", " + OPERARIO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/MAN_RANKING_MANUTENCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> MAN_RANKING_MANUTENCOES(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String AMBITO = firstMap.get("AMBITO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC MAN_RANKING_MANUTENCOES " + DATA_INICIO + ", " + DATA_FIM + ", " + AMBITO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/MAN_GET_MANUTENCOES_EM_ATRASO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> MAN_GET_MANUTENCOES_EM_ATRASO(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String AMBITO = firstMap.get("AMBITO");
		String EQUIPA = firstMap.get("EQUIPA");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager.createNativeQuery(
				"EXEC MAN_GET_MANUTENCOES_EM_ATRASO " + DATA_INICIO + ", " + DATA_FIM + ", " + AMBITO + ", " + EQUIPA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/MAN_GET_MANUTENCOES_ENTRE_DATAS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> MAN_GET_MANUTENCOES_ENTRE_DATAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String AMBITO = firstMap.get("AMBITO");
		String EQUIPA = firstMap.get("EQUIPA");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC MAN_GET_MANUTENCOES_ENTRE_DATAS " + DATA_INICIO
				+ ", " + DATA_FIM + ", " + AMBITO + ", " + EQUIPA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/MAN_GET_MANUTENCOES_PENDENTES")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> MAN_GET_MANUTENCOES_PENDENTES(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String AMBITO = firstMap.get("AMBITO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		String EQUIPA = firstMap.get("EQUIPA");

		Query query_folder = entityManager.createNativeQuery("EXEC MAN_GET_MANUTENCOES_PENDENTES_2 " + DATA_INICIO
				+ ", " + DATA_FIM + ", " + AMBITO + ", " + EQUIPA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/MAN_GET_MANUTENCOES_GET_UTILIZADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> MAN_GET_MANUTENCOES_GET_UTILIZADORES(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String EQUIPA = firstMap.get("EQUIPA");
		String AMBITO = firstMap.get("AMBITO");

		Query query_folder = entityManager
				.createNativeQuery("EXEC MAN_GET_MANUTENCOES_GET_UTILIZADORES " + EQUIPA + ", " + AMBITO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/MAN_UPDATE_MANUTENCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public int MAN_UPDATE_MANUTENCOES(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String DATA_FIM = firstMap.get("DATA_FIM");
		String UTILIZADOR = firstMap.get("UTILIZADOR");
		String TIPO_UTILIZADOR = firstMap.get("TIPO_UTILIZADOR");
		String ID = firstMap.get("ID");

		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";

		Query query_folder = entityManager.createNativeQuery(
				"UPDATE MAN_MOV_MANUTENCAO_CAB SET TIPO_RESPONSAVEL = '" + TIPO_UTILIZADOR + "',UTILIZADOR = "
						+ UTILIZADOR + ", DATA_HORA_PEDIDO = case when DATA_HORA_PEDIDO is null then null else "
						+ DATA_FIM + " end, " + "DATA_REALIZACAO = case when DATA_HORA_PEDIDO is null then " + DATA_FIM
						+ " else DATA_REALIZACAO end WHERE ID_MANUTENCAO_CAB = " + ID);

		int dados_folder = query_folder.executeUpdate();

		return dados_folder;
	}

	@POST
	@Path("/PAINEL_FUNC_FALTAR")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> PAINEL_FUNC_FALTAR(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);

		Query query_folder = entityManager
				.createNativeQuery("DECLARE @DATA date = GETDATE(); EXEC PAINEL_FUNC_FALTAR @DATA,null");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/PAINEL_GERAL_CARDS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> PAINEL_GERAL_CARDS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);

		Query query_folder = entityManager
				.createNativeQuery("DECLARE @DATA date = GETDATE(); EXEC PAINEL_GERAL_CARDS @DATA,null");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* GER_INFO_PAGINAS */

	@POST
	@Path("/createGER_INFO_PAGINAS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_INFO_PAGINAS insertGER_INFO_PAGINASA(final GER_INFO_PAGINAS data) {
		return dao4.create(data);
	}

	@GET
	@Path("/getGER_INFO_PAGINASbyid/{urlencod}")
	@Produces("application/json")
	public List<GER_INFO_PAGINAS> getGER_INFO_PAGINASbyip(@PathParam("urlencod") String urlencod) {
		String url = java.net.URLDecoder.decode(urlencod);
		return dao4.getbyId(url);
	}

	@DELETE
	@Path("/deleteGER_INFO_PAGINAS/{id}")
	public void deleteGER_INFO_PAGINAS(@PathParam("id") Integer id) {
		GER_INFO_PAGINAS GER_INFO_PAGINAS = new GER_INFO_PAGINAS();
		GER_INFO_PAGINAS.setID(id);
		dao4.delete(GER_INFO_PAGINAS);
	}

	@PUT
	@Path("/updateGER_INFO_PAGINAS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_INFO_PAGINAS updateGER_INFO_PAGINAS(final GER_INFO_PAGINAS GER_INFO_PAGINAS) {
		GER_INFO_PAGINAS.setID(GER_INFO_PAGINAS.getID());
		return dao4.update(GER_INFO_PAGINAS);
	}

	@GET
	@Path("/getUPDATETIPO_LISTA/{id}/{tipo_lista}")
	@Produces("application/json")
	public boolean getUPDATETIPO_LISTA(@PathParam("id") Integer id, @PathParam("tipo_lista") String tipo_lista) {
		entityManager.createNativeQuery("UPDATE GER_UTILIZADORES SET TIPO_LISTA_FAVORITOS = '" + tipo_lista
				+ "' WHERE ID_UTILIZADOR = " + id + "").executeUpdate();
		return true;
	}

	/************************************ COM_CUSTOMER_GROUPS */

	@POST
	@Path("/createCOM_CUSTOMER_GROUPS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_CUSTOMER_GROUPS insertCOM_CUSTOMER_GROUPS(final COM_CUSTOMER_GROUPS data) {
		return dao5.create(data);
	}

	@GET
	@Path("/getCOM_CUSTOMER_GROUPS")
	@Produces("application/json")
	public List<COM_CUSTOMER_GROUPS> getCOM_CUSTOMER_GROUPS() {
		return dao5.getall();
	}

	@GET
	@Path("/getCOM_CUSTOMER_GROUPSbyid/{id}")
	@Produces("application/json")
	public List<COM_CUSTOMER_GROUPS> getCOM_CUSTOMER_GROUPSbyid(@PathParam("id") Integer id) {
		// return dao5.getbyid(id);
		return null;
	}

	@PUT
	@Path("/updateCOM_CUSTOMER_GROUPS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_CUSTOMER_GROUPS updateCOM_CUSTOMER_GROUPS(final COM_CUSTOMER_GROUPS COM_CUSTOMER_GROUPS) {
		COM_CUSTOMER_GROUPS.setID(COM_CUSTOMER_GROUPS.getID());
		return dao5.update(COM_CUSTOMER_GROUPS);
	}

	@DELETE
	@Path("/deleteCOM_CUSTOMER_GROUPS/{id}")
	public void deleteCOM_CUSTOMER_GROUPS(@PathParam("id") Integer id) {
		COM_CUSTOMER_GROUPS COM_CUSTOMER_GROUPS = new COM_CUSTOMER_GROUPS();
		COM_CUSTOMER_GROUPS.setID(id);
		dao5.delete(COM_CUSTOMER_GROUPS);
	}

	/************************************ COM_BUDGETS */

	@POST
	@Path("/createCOM_BUDGETS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_BUDGETS insertCOM_BUDGETS(final COM_BUDGETS data) {

		List<Object[]> dados = entityManager.createNativeQuery("select ISNULL(MAX(ID),0)+1,0 as teste FROM COM_BUDGETS")
				.getResultList();
		data.setID(Integer.parseInt(dados.get(0)[0].toString()));

		return dao6.create(data);
	}

	@GET
	@Path("/getCOM_BUDGETS")
	@Produces("application/json")
	public List<COM_BUDGETS> getCOM_BUDGETS() {
		return dao6.getall();
	}

	@GET
	@Path("/getCOM_BUDGETSbyid/{id}/{versao}")
	@Produces("application/json")
	public List<COM_BUDGETS> getCOM_BUDGETSbyid(@PathParam("id") Integer id, @PathParam("versao") Integer versao) {
		return dao6.getbyid(id, versao);
	}

	@PUT
	@Path("/updateCOM_BUDGETS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_BUDGETS updateCOM_BUDGETS(final COM_BUDGETS COM_BUDGETS) {
		COM_BUDGETS.setID(COM_BUDGETS.getID());
		COM_BUDGETS.setVERSAO(COM_BUDGETS.getVERSAO());
		return dao6.update(COM_BUDGETS);
	}

	@DELETE
	@Path("/deleteCOM_BUDGETS/{id}")
	public void deleteCOM_BUDGETS(@PathParam("id") Integer id) {
		COM_BUDGETS COM_BUDGETS = new COM_BUDGETS();
		COM_BUDGETS.setID(id);
		dao6.delete(COM_BUDGETS);
	}

	@GET
	@Path("/getCOM_BUDGETSbyverificaseexiste/{id}/{ano}/{versao}")
	@Produces("application/json")
	public List<COM_BUDGETS> getCOM_BUDGETSbyverificaseexiste(@PathParam("id") Integer id,
			@PathParam("ano") Integer ano, @PathParam("versao") Integer versao) {
		return dao6.verificaseexiste(id, ano, versao);
	}

	/************************************ COM_BUDGETS_LINHAS */

	@POST
	@Path("/createCOM_BUDGETS_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_BUDGETS_LINHAS insertCOM_BUDGETS_LINHAS(final COM_BUDGETS_LINHAS data) {
		return dao8.create(data);
	}

	@GET
	@Path("/getCOM_BUDGETS_LINHAS")
	@Produces("application/json")
	public List<COM_BUDGETS_LINHAS> getCOM_BUDGETS_LINHAS() {
		return dao8.getall();
	}

	@GET
	@Path("/getCOM_BUDGETS_LINHASbyid/{id}")
	@Produces("application/json")
	public List<COM_BUDGETS_LINHAS> getCOM_BUDGETS_LINHASbyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao8.getbyid(id, versao);
	}

	@GET
	@Path("/getCOM_BUDGETS_LINHASbyid2/{id}/{versao}")
	@Produces("application/json")
	public List<COM_BUDGETS_LINHAS> getCOM_BUDGETS_LINHASbyid2(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao8.getbyid2(id, versao);
	}

	@PUT
	@Path("/updateCOM_BUDGETS_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_BUDGETS_LINHAS updateCOM_BUDGETS_LINHAS(final COM_BUDGETS_LINHAS COM_BUDGETS_LINHAS) {
		COM_BUDGETS_LINHAS.setID(COM_BUDGETS_LINHAS.getID());
		return dao8.update(COM_BUDGETS_LINHAS);
	}

	@DELETE
	@Path("/deleteCOM_BUDGETS_LINHAS/{id}")
	public void deleteCOM_BUDGETS_LINHAS(@PathParam("id") Integer id) {
		COM_BUDGETS_LINHAS COM_BUDGETS_LINHAS = new COM_BUDGETS_LINHAS();
		COM_BUDGETS_LINHAS.setID(id);
		dao8.delete(COM_BUDGETS_LINHAS);
	}

	/************************************ COM_CUSTOMERS_GROUPS */

	@POST
	@Path("/createCOM_CUSTOMERS_GROUPS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_CUSTOMERS_GROUPS insertCOM_CUSTOMERS_GROUPS(final COM_CUSTOMERS_GROUPS data) {
		return dao9.create(data);
	}

	@GET
	@Path("/getCOM_CUSTOMERS_GROUPS")
	@Produces("application/json")
	public List<COM_CUSTOMERS_GROUPS> getCOM_CUSTOMERS_GROUPS() {
		return dao9.getall();
	}

	@GET
	@Path("/getCOM_CUSTOMERS_GROUPSbyid/{id}")
	@Produces("application/json")
	public List<COM_CUSTOMERS_GROUPS> getCOM_CUSTOMERS_GROUPSbyid(@PathParam("id") Integer id) {
		// return dao9.getbyid(id);
		return null;
	}

	@PUT
	@Path("/updateCOM_CUSTOMERS_GROUPS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_CUSTOMERS_GROUPS updateCOM_CUSTOMERS_GROUPS(final COM_CUSTOMERS_GROUPS COM_CUSTOMERS_GROUPS) {
		COM_CUSTOMERS_GROUPS.setID(COM_CUSTOMERS_GROUPS.getID());
		return dao9.update(COM_CUSTOMERS_GROUPS);
	}

	@DELETE
	@Path("/deleteCOM_CUSTOMERS_GROUPS/{id}")
	public void deleteCOM_CUSTOMERS_GROUPS(@PathParam("id") Integer id) {
		COM_CUSTOMERS_GROUPS COM_CUSTOMERS_GROUPS = new COM_CUSTOMERS_GROUPS();
		COM_CUSTOMERS_GROUPS.setID(id);
		dao9.delete(COM_CUSTOMERS_GROUPS);
	}

	/************************************ COM_BUDGETS_ANALISES */

	@POST
	@Path("/createCOM_BUDGETS_ANALISES")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_BUDGETS_ANALISES insertCOM_BUDGETS_ANALISES(final COM_BUDGETS_ANALISES data) {
		return dao7.create(data);
	}

	@GET
	@Path("/getCOM_BUDGETS_ANALISES")
	@Produces("application/json")
	public List<COM_BUDGETS_ANALISES> getCOM_BUDGETS_ANALISES() {
		return dao7.getall();
	}

	@GET
	@Path("/getCOM_BUDGETS_ANALISESbyid/{id}")
	@Produces("application/json")
	public List<COM_BUDGETS_ANALISES> getCOM_BUDGETS_ANALISESbyid(@PathParam("id") Integer id) {
		return dao7.getbyid(id);
	}

	@PUT
	@Path("/updateCOM_BUDGETS_ANALISES")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_BUDGETS_ANALISES updateCOM_BUDGETS_ANALISES(final COM_BUDGETS_ANALISES COM_BUDGETS_ANALISES) {
		COM_BUDGETS_ANALISES.setID(COM_BUDGETS_ANALISES.getID());
		return dao7.update(COM_BUDGETS_ANALISES);
	}

	@DELETE
	@Path("/deleteCOM_BUDGETS_ANALISES/{id}")
	public void deleteCOM_BUDGETS_ANALISES(@PathParam("id") Integer id) {
		COM_BUDGETS_ANALISES COM_BUDGETS_ANALISES = new COM_BUDGETS_ANALISES();
		COM_BUDGETS_ANALISES.setID(id);
		dao7.delete(COM_BUDGETS_ANALISES);
	}

	@POST
	@Path("/COM_GET_CUSTOMERS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_GET_CUSTOMERS(final List<HashMap<String, String>> dados) {

		Query query_folder = entityManager.createNativeQuery("EXEC [COM_GET_CUSTOMERS]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/COM_GET_REFERENCE_CUSTOMER")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_GET_REFERENCE_CUSTOMER(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String CLICOD = firstMap.get("CLICOD");
		String ETSNUM = firstMap.get("ETSNUM");
		String PROREF = firstMap.get("PROREF");
		Query query_folder = entityManager.createNativeQuery(
				"EXEC [COM_GET_REFERENCE_CUSTOMER] '" + CLICOD + "','" + ETSNUM + "','" + PROREF + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/COM_GET_BUDGET_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_GET_BUDGET_ANALISE(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String ID_CUSTOMER = firstMap.get("ID_CUSTOMER");
		String CUSTOMER_GROUP = firstMap.get("CUSTOMER_GROUP");
		String REFERENCE_INTERNAL = firstMap.get("REFERENCE_INTERNAL");

		if (ID_CUSTOMER != null)
			ID_CUSTOMER = "'" + ID_CUSTOMER + "'";

		if (REFERENCE_INTERNAL != null)
			REFERENCE_INTERNAL = "'" + REFERENCE_INTERNAL + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC [COM_GET_BUDGET_ANALISE] " + ANO + "," + ID_CUSTOMER
				+ "," + CUSTOMER_GROUP + "," + REFERENCE_INTERNAL + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/COM_GET_BUDGET_ANALISE_TOOLS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_GET_BUDGET_ANALISE_TOOLS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String MES = firstMap.get("MES");

		Query query_folder = entityManager.createNativeQuery("EXEC [COM_GET_BUDGET_ANALISE_TOOLS] " + ANO + "," + MES);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/COM_GET_BUDGET_RESUMO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> COM_GET_BUDGET_RESUMO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String MES = firstMap.get("MES");

		Query query_folder = entityManager.createNativeQuery("EXEC [COM_GET_BUDGET_RESUMO] " + ANO + "," + MES);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* PIN_DIC_TIPO_ACABAMENTO */
	@POST
	@Path("/createPIN_DIC_TIPO_ACABAMENTO")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_TIPO_ACABAMENTO insertPIN_DIC_TIPO_ACABAMENTO(final PIN_DIC_TIPO_ACABAMENTO data) {
		return dao10.create(data);
	}

	@GET
	@Path("/getPIN_DIC_TIPO_ACABAMENTObyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_TIPO_ACABAMENTO> getPIN_DIC_TIPO_ACABAMENTObyid_linha(@PathParam("id") Integer id) {
		return dao10.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_TIPO_ACABAMENTO")
	@Produces("application/json")
	public List<PIN_DIC_TIPO_ACABAMENTO> getPIN_DIC_TIPO_ACABAMENTO() {
		return dao10.getall();
	}

	@DELETE
	@Path("/deletePIN_DIC_TIPO_ACABAMENTO/{id}")
	public void deletePIN_DIC_TIPO_ACABAMENTO(@PathParam("id") Integer id) {
		PIN_DIC_TIPO_ACABAMENTO PIN_DIC_TIPO_ACABAMENTO = new PIN_DIC_TIPO_ACABAMENTO();
		PIN_DIC_TIPO_ACABAMENTO.setID(id);
		dao10.delete(PIN_DIC_TIPO_ACABAMENTO);
	}

	@PUT
	@Path("/updatePIN_DIC_TIPO_ACABAMENTO")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_TIPO_ACABAMENTO updatePIN_DIC_TIPO_ACABAMENTO(
			final PIN_DIC_TIPO_ACABAMENTO PIN_DIC_TIPO_ACABAMENTO) {
		PIN_DIC_TIPO_ACABAMENTO.setID(PIN_DIC_TIPO_ACABAMENTO.getID());
		return dao10.update(PIN_DIC_TIPO_ACABAMENTO);
	}

	/************************************* PIN_DIC_ARMAZEM */
	@POST
	@Path("/createPIN_DIC_ARMAZEM")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_ARMAZEM insertPIN_DIC_ARMAZEM(final PIN_DIC_ARMAZEM data) {
		return dao11.create(data);
	}

	@GET
	@Path("/getPIN_DIC_ARMAZEM")
	@Produces("application/json")
	public List<PIN_DIC_ARMAZEM> getPIN_DIC_ARMAZEM() {
		return dao11.allEntries();
	}

	@DELETE
	@Path("/deletePIN_DIC_ARMAZEM/{id}")
	public void deletePIN_DIC_ARMAZEM(@PathParam("id") Integer id) {
		PIN_DIC_ARMAZEM PIN_DIC_ARMAZEM = new PIN_DIC_ARMAZEM();
		PIN_DIC_ARMAZEM.setID_ARMAZEM(id);
		dao11.delete(PIN_DIC_ARMAZEM);
	}

	@PUT
	@Path("/updatePIN_DIC_ARMAZEM")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_ARMAZEM updatePIN_DIC_ARMAZEM(final PIN_DIC_ARMAZEM PIN_DIC_ARMAZEM) {
		PIN_DIC_ARMAZEM.setID_ARMAZEM(PIN_DIC_ARMAZEM.getID_ARMAZEM());
		return dao11.update(PIN_DIC_ARMAZEM);
	}

	/************************************* PIN_DIC_CABINES */
	@POST
	@Path("/createPIN_DIC_CABINES")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_CABINES insertAB_DIC_ADITIVO(final PIN_DIC_CABINES data) {
		return dao12.create(data);
	}

	@GET
	@Path("/getPIN_DIC_CABINESyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_CABINES> getPIN_DIC_CABINESyid_banho(@PathParam("id") Integer id) {
		return dao12.getbyid(id, 0);
	}

	@GET
	@Path("/getPIN_DIC_CABINES")
	@Produces("application/json")
	public List<PIN_DIC_CABINES> getPIN_DIC_CABINES() {
		return dao12.allEntries();
	}

	@GET
	@Path("/getPIN_DIC_CABINES2")
	@Produces("application/json")
	public List<PIN_DIC_CABINES> getPIN_DIC_CABINESLINHA() {
		return dao12.getall(0);
	}

	/*
	 * @GET
	 * 
	 * @Path("/getAllLINHAbylinha_tipo/{id}/{linha}/{tipo}")
	 * 
	 * @Produces("application/json") public List<PIN_DIC_CABINES>
	 * getAllLINHAbylinha_tipo(@PathParam("id") Integer id, @PathParam("linha")
	 * Integer linha,
	 * 
	 * @PathParam("tipo") String tipo) { return dao12.getAllLINHAbylinha_tipo(id,
	 * linha, tipo); }
	 */

	@DELETE
	@Path("/deletePIN_DIC_CABINES/{id}")
	public void deletePIN_DIC_CABINES(@PathParam("id") Integer id) {
		PIN_DIC_CABINES PIN_DIC_CABINES = new PIN_DIC_CABINES();
		PIN_DIC_CABINES.setID(id);
		dao12.delete(PIN_DIC_CABINES);
	}

	@PUT
	@Path("/updatePIN_DIC_CABINES")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_CABINES updatePIN_DIC_CABINES(final PIN_DIC_CABINES PIN_DIC_CABINES) {
		PIN_DIC_CABINES.setID(PIN_DIC_CABINES.getID());
		return dao12.update(PIN_DIC_CABINES);
	}

	/************************************* PIN_DIC_POTES */
	@POST
	@Path("/createPIN_DIC_POTES")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_POTES insertPIN_DIC_POTES(final PIN_DIC_POTES data) {
		return dao13.create(data);
	}

	@GET
	@Path("/getPIN_DIC_POTES")
	@Produces("application/json")
	public List<PIN_DIC_POTES> getPIN_DIC_POTES() {
		return dao13.allEntries();
	}

	@GET
	@Path("/getallPIN_DIC_POTES/{linha}")
	@Produces("application/json")
	public List<PIN_DIC_POTES> getallPIN_DIC_POTES(@PathParam("linha") Integer linha) {
		return dao13.getall(linha);
	}

	@GET
	@Path("/getallPIN_DIC_POTES2")
	@Produces("application/json")
	public List<PIN_DIC_POTES> getallPIN_DIC_POTES() {
		return dao13.getall2();
	}

	@GET
	@Path("/getPIN_DIC_POTESbyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_POTES> getPIN_DIC_POTESvbyid_tina(@PathParam("id") Integer id) {
		return dao13.getbyid(id, 0);
	}

	@GET
	@Path("/updateOrdemPIN_DIC_POTES/{id}/{ordem}")
	@Produces("application/json")
	public Integer updateOrdemPIN_DIC_POTES(@PathParam("ordem") Integer ordem, @PathParam("id") Integer id) {
		return dao13.updateordem(ordem, id);
	}

	@DELETE
	@Path("/deletePIN_DIC_POTES/{id}")
	public void deletePIN_DIC_POTES(@PathParam("id") Integer id) {
		PIN_DIC_POTES PIN_DIC_POTES = new PIN_DIC_POTES();
		PIN_DIC_POTES.setID(id);
		dao13.delete(PIN_DIC_POTES);
	}

	@PUT
	@Path("/updatePIN_DIC_POTES")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_POTES updatePIN_DIC_POTES(final PIN_DIC_POTES PIN_DIC_POTES) {
		PIN_DIC_POTES.setID(PIN_DIC_POTES.getID());
		return dao13.update(PIN_DIC_POTES);
	}

	/************************************* PIN_DIC_PRODUTOS */
	@POST
	@Path("/createPIN_DIC_PRODUTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_PRODUTOS insertPIN_DIC_PRODUTOS(final PIN_DIC_PRODUTOS data) {
		return dao14.create(data);
	}

	@GET
	@Path("/getPIN_DIC_PRODUTOS")
	@Produces("application/json")
	public List<PIN_DIC_PRODUTOS> getPIN_DIC_PRODUTOS() {
		return dao14.getAll();
	}

	@GET
	@Path("/getPIN_DIC_PRODUTOS2")
	@Produces("application/json")
	public List<PIN_DIC_PRODUTOS> getPIN_DIC_PRODUTOS2() {
		return dao14.getAll2();
	}

	@GET
	@Path("/getPIN_DIC_PRODUTOSbyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_PRODUTOS> getPIN_DIC_PRODUTOSbyid(@PathParam("id") Integer id) {
		return dao14.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_PRODUTOSbyTipoAcabamento/{id}")
	@Produces("application/json")
	public List<PIN_DIC_PRODUTOS> getPIN_DIC_PRODUTOSbyTipoAcabamento(@PathParam("id") Integer id) {
		return dao14.getbyTipoAcabamento(id);
	}

	@GET
	@Path("/getPIN_DIC_PRODUTOSbyReceita/{id}/{versao}")
	@Produces("application/json")
	public List<PIN_DIC_PRODUTOS> getPIN_DIC_PRODUTOSbyReceita(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao14.getbyReceita(id, versao);
	}

	@GET
	@Path("/getPIN_DIC_PRODUTOSbyIdProduto/{id}")
	@Produces("application/json")
	public List<PIN_DIC_PRODUTOS> getPIN_DIC_PRODUTOSbyIdProduto(@PathParam("id") Integer id) {
		return dao14.getbyIdProduto(id);
	}

	@DELETE
	@Path("/deletePIN_DIC_PRODUTOS/{id}")
	public void deletePIN_DIC_PRODUTOS(@PathParam("id") Integer id) {
		PIN_DIC_PRODUTOS PIN_DIC_PRODUTOS = new PIN_DIC_PRODUTOS();
		PIN_DIC_PRODUTOS.setID(id);
		dao14.delete(PIN_DIC_PRODUTOS);
	}

	@PUT
	@Path("/updatePIN_DIC_PRODUTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_PRODUTOS updatePIN_DIC_PRODUTOS(final PIN_DIC_PRODUTOS PIN_DIC_PRODUTOS) {
		PIN_DIC_PRODUTOS.setID(PIN_DIC_PRODUTOS.getID());
		return dao14.update(PIN_DIC_PRODUTOS);
	}

	/************************************* PIN_DIC_PRODUTOS_RELACIONADOS */
	@POST
	@Path("/createPIN_DIC_PRODUTOS_RELACIONADOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_PRODUTOS_RELACIONADOS insertPIN_DIC_PRODUTOS_RELACIONADOS(final PIN_DIC_PRODUTOS_RELACIONADOS data) {
		return dao15.create(data);
	}

	@GET
	@Path("/getPIN_DIC_PRODUTOS_RELACIONADOS")
	@Produces("application/json")
	public List<PIN_DIC_PRODUTOS_RELACIONADOS> getPIN_DIC_PRODUTOS_RELACIONADOS() {
		return dao15.gelAll();
	}

	@GET
	@Path("/getPIN_DIC_PRODUTOS_RELACIONADOSbyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_PRODUTOS_RELACIONADOS> getPIN_DIC_PRODUTOS_RELACIONADOSbyid_componente(
			@PathParam("id") Integer id) {
		return dao15.getbyid_componente(id);
	}

	@DELETE
	@Path("/deletePIN_DIC_PRODUTOS_RELACIONADOS/{id}")
	public void deletePIN_DIC_PRODUTOS_RELACIONADOS(@PathParam("id") Integer id) {
		PIN_DIC_PRODUTOS_RELACIONADOS PIN_DIC_PRODUTOS_RELACIONADOS = new PIN_DIC_PRODUTOS_RELACIONADOS();
		PIN_DIC_PRODUTOS_RELACIONADOS.setID(id);
		dao15.delete(PIN_DIC_PRODUTOS_RELACIONADOS);
	}

	@PUT
	@Path("/updatePIN_DIC_PRODUTOS_RELACIONADOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_PRODUTOS_RELACIONADOS updatePIN_DIC_PRODUTOS_RELACIONADOS(
			final PIN_DIC_PRODUTOS_RELACIONADOS PIN_DIC_PRODUTOS_RELACIONADOS) {
		PIN_DIC_PRODUTOS_RELACIONADOS.setID(PIN_DIC_PRODUTOS_RELACIONADOS.getID());
		return dao15.update(PIN_DIC_PRODUTOS_RELACIONADOS);
	}

	@GET
	@Path("/PIN_UPDATE_PIN_DIC_PRODUTOS_PERC_DILUICAO/{id}")
	@Produces("application/json")
	public int PIN_UPDATE_PIN_DIC_PRODUTOS_PERC_DILUICAO(@PathParam("id") Integer id) {

		int query_folder = entityManager.createNativeQuery("EXEC PIN_UPDATE_PIN_DIC_PRODUTOS_PERC_DILUICAO :id")
				.setParameter("id", id).executeUpdate();

		return query_folder;

	}

	@GET
	@Path("/getPIN_DIC_PRODUTOS_RELACIONADOSPERC_DILUICAO")
	@Produces("application/json")
	public List<PIN_DIC_PRODUTOS_RELACIONADOS> getPIN_DIC_PRODUTOS_RELACIONADOSPERC_DILUICAO() {
		return dao15.getbyPERC_DILUICAO();
	}

	/************************************* PIN_MOV_PREPARACAO */
	@POST
	@Path("/createPIN_MOV_PREPARACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_PREPARACAO insertPIN_MOV_PREPARACAOA(final PIN_MOV_PREPARACAO data) {
		return dao16.create(data);
	}

	@POST
	@Path("/getPIN_MOV_PREPARACAO/{linha}/{classif}/{classif2}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO> getPIN_MOV_PREPARACAO(@PathParam("linha") Integer linha,
			@PathParam("classif") String classif, List<HashMap<String, String>> query,
			@PathParam("classif2") String classif2) {

		return dao16.getall(linha, query, classif, classif2);
	}

	@POST
	@Path("/getPIN_MOV_PREPARACAOall/{linha}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO> getPIN_MOV_PREPARACAOall(@PathParam("linha") Integer linha,
			final List<HashMap<String, String>> query) {
		return dao16.getallmanu(linha, query);
	}

	@POST
	@Path("/getPIN_MOV_PREPARACAOidpote/{linha}/{classif}/{idpote}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO> getPIN_MOV_PREPARACAOidpote(@PathParam("linha") Integer linha,
			@PathParam("classif") String classif, final ArrayList<String> query, @PathParam("idpote") Integer idpote) {
		return dao16.getallidabanho(linha, query, classif, idpote);
	}

	@POST
	@Path("/getPIN_MOV_PREPARACAOsorid/{linha}/{classif}/{classif2}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO> getPIN_MOV_PREPARACAOsorid(@PathParam("linha") Integer linha,
			@PathParam("classif") String classif, @PathParam("classif2") String classif2,
			final ArrayList<String> query) {
		return dao16.getallsortid(linha, query, classif, classif2);
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAObyid/{id}/{linha}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO> getPIN_MOV_PREPARACAObyid(@PathParam("id") Integer id,
			@PathParam("linha") Integer linha) {
		return dao16.getbyid(id, linha);
	}

	@DELETE
	@Path("/deletePIN_MOV_PREPARACAO/{id}")
	public void deletePIN_MOV_PREPARACAO(@PathParam("id") Integer id) {
		PIN_MOV_PREPARACAO PIN_MOV_PREPARACAO = new PIN_MOV_PREPARACAO();
		PIN_MOV_PREPARACAO.setID_PREPARACAO(id);
		dao16.delete(PIN_MOV_PREPARACAO);
	}

	@PUT
	@Path("/updatePIN_MOV_PREPARACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_PREPARACAO updateAB_MOV_ANALISE_LINHA(final PIN_MOV_PREPARACAO PIN_MOV_PREPARACAO) {
		PIN_MOV_PREPARACAO.setID_PREPARACAO(PIN_MOV_PREPARACAO.getID_PREPARACAO());
		return dao16.update(PIN_MOV_PREPARACAO);
	}

	/************************************ PIN_MOV_PREPARACAO_CAB */
	@POST
	@Path("/createPIN_MOV_PREPARACAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_PREPARACAO_CAB insertPIN_MOV_PREPARACAO_CAB(final PIN_MOV_PREPARACAO_CAB data) {
		return dao17.create(data);
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_CAB")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_CAB> getPIN_MOV_PREPARACAO_CAB() {
		return dao17.allEntries();
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_CABbyidPREPARACAO/{id}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_CAB> getPIN_MOV_PREPARACAO_CABbyidPREPARACAO(@PathParam("id") Integer id) {
		return dao17.getbyidPREPARACAO(id);
	}

	/*
	 * @GET
	 * 
	 * @Path(
	 * "/getPIN_MOV_PREPARACAO_CABbyid_banho/{idpote}/{inicio}/{fim}/{id_man}/{classif}")
	 * 
	 * @Produces("application/json") public List<PIN_MOV_PREPARACAO_CAB>
	 * getPIN_MOV_PREPARACAO_CABbyid_banho(@PathParam("idpote") Integer idpote,
	 * 
	 * @PathParam("inicio") Integer inicio, @PathParam("fim") Integer
	 * fim, @PathParam("id_man") Integer id_man,
	 * 
	 * @PathParam("classif") String classif) { return dao17.getbyidpote(idpote,
	 * inicio, fim, id_man, classif); }
	 */

	@GET
	@Path("/getPIN_MOV_PREPARACAO_CABbyid_banhoall/{idpote}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_CAB> getPIN_MOV_PREPARACAO_CABbyid_banhoall(@PathParam("idpote") Integer idpote) {
		return dao17.getbyidpoteall(idpote);
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_CABbyid/{id}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_CAB> getPIN_MOV_PREPARACAO_CABbyid(@PathParam("id") Integer id) {
		return dao17.getbyid(id);
	}

	@DELETE
	@Path("/deletePIN_MOV_PREPARACAO_CAB/{id}")
	public void deletePIN_MOV_PREPARACAO_CABA(@PathParam("id") Integer id) {
		PIN_MOV_PREPARACAO_CAB PIN_MOV_PREPARACAO_CAB = new PIN_MOV_PREPARACAO_CAB();
		PIN_MOV_PREPARACAO_CAB.setID_PREPARACAO_CAB(id);
		dao17.delete(PIN_MOV_PREPARACAO_CAB);
	}

	@PUT
	@Path("/updatePIN_MOV_PREPARACAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_PREPARACAO_CAB updatePIN_MOV_PREPARACAO_CAB(final PIN_MOV_PREPARACAO_CAB PIN_MOV_PREPARACAO_CAB) {
		PIN_MOV_PREPARACAO_CAB.setID_PREPARACAO_CAB(PIN_MOV_PREPARACAO_CAB.getID_PREPARACAO_CAB());
		return dao17.update(PIN_MOV_PREPARACAO_CAB);
	}

	/************************************* PIN_MOV_PREPARACAO_LINHA */
	@POST
	@Path("/createPIN_MOV_PREPARACAO_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_PREPARACAO_LINHA insertAPIN_MOV_PREPARACAO_LINHA(final PIN_MOV_PREPARACAO_LINHA data) {
		return dao18.create(data);
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_LINHA")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_LINHA> getPIN_MOV_PREPARACAO_LINHA() {
		return dao18.allEntries();
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_LINHAbyidPREPARACAOcab/{id}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_LINHA> getPIN_MOV_PREPARACAO_LINHAbyidPREPARACAOcab(@PathParam("id") Integer id) {
		return dao18.getbyidPREPARACAOcab(id);
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_LINHAbyidPREPARACAOcabtotal/{id}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_LINHA> getPIN_MOV_PREPARACAO_LINHAbyidPREPARACAOcabtotal(
			@PathParam("id") Integer id) {
		return dao18.getbyidPREPARACAOcabtotal(id);
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_LINHAbyidPREPARACAOcabtotal2/{id}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_LINHA> getPIN_MOV_PREPARACAO_LINHAbyidPREPARACAOcabtotal2(
			@PathParam("id") Integer id) {
		return dao18.getbyidPREPARACAOcabtotal2(id);
	}

	@DELETE
	@Path("/apagar_linhasPintura/{id}")
	public void apagar_linhas(@PathParam("id") Integer id) {
		dao18.apagar_linhas(id);
	}

	@POST
	@Path("/getPIN_MOV_PREPARACAO_LINHAbyid_analise_comp/{id}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_LINHA> getbyid_analise_comp(@PathParam("id") Integer id,
			final ArrayList<Integer> data) {
		return dao18.getbyid_manut_comp(id, data);
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_LINHAbyid/{id}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_LINHA> getPIN_MOV_PREPARACAO_LINHAbyid(@PathParam("id") Integer id) {
		return dao18.getbyid(id);
	}

	@DELETE
	@Path("/deletePIN_MOV_PREPARACAO_LINHA/{id}")
	public void deletePIN_MOV_PREPARACAO_LINHA(@PathParam("id") Integer id) {
		PIN_MOV_PREPARACAO_LINHA PIN_MOV_PREPARACAO_LINHA = new PIN_MOV_PREPARACAO_LINHA();
		PIN_MOV_PREPARACAO_LINHA.setID_PREPARACAO_LIN(id);
		dao18.delete(PIN_MOV_PREPARACAO_LINHA);
	}

	@PUT
	@Path("/updatePIN_MOV_PREPARACAO_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_PREPARACAO_LINHA updatePIN_MOV_PREPARACAO_LINHA(
			final PIN_MOV_PREPARACAO_LINHA PIN_MOV_PREPARACAO_LINHA) {
		PIN_MOV_PREPARACAO_LINHA.setID_PREPARACAO_LIN(PIN_MOV_PREPARACAO_LINHA.getID_PREPARACAO_LIN());
		return dao18.update(PIN_MOV_PREPARACAO_LINHA);
	}

	/************************************* PIN_MOV_PREPARACAO_ETIQ */
	@POST
	@Path("/createPIN_MOV_PREPARACAO_ETIQ")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_PREPARACAO_ETIQ insertPIN_MOV_PREPARACAO_ETIQA(final PIN_MOV_PREPARACAO_ETIQ data) {
		if (data.getID_PREPARACAO_LIN() == 0 || data.getID_PREPARACAO_LIN() == null) {
			return dao19.create(data);
		} else {
			Query query = entityManager.createNativeQuery("select ID_PREPARACAO_LIN,ID_MOV_PREP_ETIQUETA "
					+ "from PIN_MOV_PREPARACAO_ETIQ where ID_PREPARACAO_LIN = " + data.getID_PREPARACAO_LIN()
					+ " and ETQNUM = '" + data.getETQNUM() + "' ");

			List<Object[]> dados = query.getResultList();
			String id = null;
			for (Object[] content : dados) {
				id = content[0].toString();
			}

			if (id == null) {
				return dao19.create(data);
			}
		}
		return null;
	}

	@GET
	@Path("/ATUALIZAQUANTAOAPAGARPINTURA/{id}")
	@Consumes("*/*")
	@Produces("application/json")
	public void ATUALIZAQUANTAOAPAGAR(@PathParam("id") Integer id) {

		final ConnectProgress connectionProgress = new ConnectProgress();
		String url = getURLSILVER();
		Query query = entityManager.createNativeQuery(
				"select ETQNUM,(QUANT - QUANT_FINAL) from PIN_MOV_PREPARACAO_ETIQ where ID_PREPARACAO_LIN in "
						+ "(select ID_PREPARACAO_LIN from PIN_MOV_PREPARACAO_LINHA where ID_PREPARACAO_CAB = " + id
						+ ")");

		List<Object[]> dados = query.getResultList();

		for (Object[] content : dados) {
			try {
				connectionProgress.EXEC_SINCRO_AO_APAGAR(content[0].toString(), Float.parseFloat(content[1].toString()),
						url);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@POST
	@Path("/ATUALIZAQUANTAPAGARPINTURA")
	@Consumes("*/*")
	@Produces("application/json")
	public void ATUALIZAQUANTAPAGARPINTURA(final List<HashMap<String, String>> datas) {

		HashMap<String, String> firstMap = datas.get(0);

		String etiqueta = firstMap.get("etiqueta");
		Float qtd = Float.parseFloat(firstMap.get("qtd"));

		final ConnectProgress connectionProgress = new ConnectProgress();
		try {
			connectionProgress.EXEC_SINCRO2(etiqueta, qtd, getURLSILVER());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_ETIQbyidmanu/{id}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_ETIQ> getPIN_MOV_PREPARACAO_ETIQbyip(@PathParam("id") Integer id) {
		return dao19.getbyid_manu(id);
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_ETIQbyid/{id}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_ETIQ> getPIN_MOV_PREPARACAO_ETIQbyid(@PathParam("id") Integer id) {
		return dao19.getbyid(id);
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_ETIQbyref/{id}/{ref}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_ETIQ> getPIN_MOV_PREPARACAO_ETIQbyref(@PathParam("id") Integer id,
			@PathParam("ref") String ref) {
		return dao19.getbyRef(id, ref);
	}

	@GET
	@Path("/getPIN_MOV_PREPARACAO_ETIQbyref2/{id}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_ETIQ> getPIN_MOV_PREPARACAO_ETIQbyref2(@PathParam("id") Integer id) {
		return dao19.getbyRef2(id);
	}

	@DELETE
	@Path("/deletePIN_MOV_PREPARACAO_ETIQ/{id}")
	public void deletePIN_MOV_PREPARACAO_ETIQ(@PathParam("id") Integer id) {
		PIN_MOV_PREPARACAO_ETIQ PIN_MOV_PREPARACAO_ETIQ = new PIN_MOV_PREPARACAO_ETIQ();
		PIN_MOV_PREPARACAO_ETIQ.setID_MOV_PREP_ETIQUETA(id);
		dao19.delete(PIN_MOV_PREPARACAO_ETIQ);
	}

	@PUT
	@Path("/updatePIN_MOV_PREPARACAO_ETIQ")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_PREPARACAO_ETIQ updatePIN_MOV_PREPARACAO_ETIQ(
			final PIN_MOV_PREPARACAO_ETIQ PIN_MOV_PREPARACAO_ETIQ) {
		PIN_MOV_PREPARACAO_ETIQ.setID_MOV_PREP_ETIQUETA(PIN_MOV_PREPARACAO_ETIQ.getID_MOV_PREP_ETIQUETA());
		return dao19.update(PIN_MOV_PREPARACAO_ETIQ);
	}

	public String getURLSILVER() {
		String url = "";
		Query query_folder = entityManager.createNativeQuery("select top 1 * from GER_PARAMETROS a");

		List<Object[]> dados_folder = query_folder.getResultList();

		for (Object[] content : dados_folder) {
			url = content[2].toString();
		}

		return url;
	}

	// CRIAR
	// FICHEIRO****************************************************************

	@POST
	@Path("/ficheirocorrecaoPintura")
	@Consumes("*/*")
	@Produces("application/json")
	public void ficheirocorrecaoPintura(final List<HashMap<String, String>> data) {

		HashMap<String, String> firstMap = data.get(0);
		String id = firstMap.get("id");
		String linha = firstMap.get("linha");
		String nome_ficheiro = "";
		String ip_posto = firstMap.get("ip_posto");
		String ids = "";
		List<String> listStrings = new ArrayList<String>(Arrays.asList(id.split(",")));
		Boolean varbol = false;
		for (String current : listStrings) {
			if (varbol) {
				ids += "," + current;
			} else {
				ids += current;
				varbol = true;
			}
		}

		Query query = entityManager.createNativeQuery(
				"Select (select top 1 OF_NUM from AB_DIC_LINHA_OF e where ID_LINHA = a.id_linha and DATA <= GETDATE() order by e.DATA desc) as ofnum,a.SECCAO,a.SUBSECCAO,a.REF_COMPOSTO "
						+ "from AB_DIC_LINHA a where a.ID_LINHA = " + linha + "");

		List<Object[]> dados = query.getResultList();

		String data_path = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		for (Object[] content : dados) {
			// nome_ficheiro = data + "_ETIQUETA_" + content[0].toString() +
			// ".txt";
			nome_ficheiro = "ETIQUETA_CORRECAO_PINTURA" + data_path + ".txt";

			criarFicheiro(null, nome_ficheiro, content[0].toString(), content[1].toString(), content[2].toString(),
					content[3].toString(), null, ip_posto, ids);

		}
	}

	@POST
	@Path("/ficheiroimprimiretiquetaPintura")
	@Consumes("*/*")
	@Produces("application/json")
	public void ficheiroimprimiretiquetaPintura(final List<HashMap<String, String>> data) {
		SIRB SIRB = new SIRB();
		HashMap<String, String> firstMap = data.get(0);
		String ip_posto = firstMap.get("ip_posto");
		String modelo_REPORT = "";
		String nomeimpressora = "";
		String ipimpressora = "";
		String data_etiq = "";
		String path2 = "";
		String path_error = "";

		/*** CAMPOS PARA O FICHEIRO ***/

		String DATCRE = firstMap.get("DATCRE");
		String QUANT = firstMap.get("QUANT");
		String UNIDADE = firstMap.get("UNIDADE");
		String ETIQUETA = firstMap.get("ETIQUETA");
		String PROREF = firstMap.get("PROREF");
		String ETQORILOT1 = firstMap.get("ETQORILOT1");
		String PRODES = firstMap.get("PRODES");

		/******************************/

		Query query_folder = entityManager.createNativeQuery(
				"select top 1  PASTA_FICHEIRO,PASTA_ETIQUETAS,NOME_IMPRESSORA,IP_IMPRESSORA,MODELO_REPORT,PASTA_DESTINO_ERRO from GER_PARAMETROS a,GER_POSTOS b where IP_POSTO ='"
						+ ip_posto + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		String nome_ficheiro = "ETIQUETA_PINTURA_NUM_" + ETIQUETA + ".txt";

		for (Object[] content : dados_folder) {
			path2 = content[1].toString() + nome_ficheiro;
			path_error = content[5].toString() + nome_ficheiro;
			nomeimpressora = content[2].toString();
			if (content[3] != null) {
				ipimpressora = content[3].toString();
			}
			modelo_REPORT = content[4].toString();
		}

		data_etiq += "LAB_NAME=" + modelo_REPORT + "\r\n";
		if (!ipimpressora.isEmpty() && ipimpressora != null) {
			ipimpressora = ",->" + ipimpressora;
		}
		data_etiq += "THT_NAME=" + nomeimpressora + ipimpressora + "\r\n";
		data_etiq += "AF100;AF101;AF1;AF2;A2;AF3;A3;AF4;A4;AF5;A5;AF6;AF7;A7;AF8;AF9;AF10;AF11;AF24;AF12;AF16;A16;AF17;AF18;AF19;AF20;A20;AF21;A21;AF22;AF23;AF25;AF26;AF27;AF28;AF29;AF30;AF31;AF32;AF33;AF34;AF35;AF36;AF37;AF38;AF39;AF40;AF41;AF42;AF43;AF44;END;\r\n";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = null;
		try {
			date1 = formatter.parse(DATCRE);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		formatter = new SimpleDateFormat("yyMMdd");
		String datas = formatter.format(date1);

		String quant = String.format("%.3f", Float.parseFloat(QUANT)).replace("$", ",");
		data_etiq += "DOURECA - PT- 4940;DOURECA - PT- 4940;;EW93 404D52 DA;P;";
		data_etiq += quant + " " + ((UNIDADE != null) ? UNIDADE : "") + ";" // AF3
				+ ";" // A3
				+ ";" // AF4
				+ ";" // A4
				+ ETIQUETA + ";" // AF5
				+ "S;" // A5
				+ PRODES + ";" // AF6
				+ PROREF + ";" // AF7
				+ ";" // A7
				+ ";" // AF8
				+ ";" // AF9
				+ ";" // AF10
				+ ";" // AF11
				+ ";" // AF24
				+ ";" // AF12
				+ "D" + datas + ";" // AF16
				+ ";" // A16
				+ ";" // AF17
				+ ";" // AF18
				+ ";" // AF19
				+ ";" // AF20
				+ ";" // A20
				+ ETQORILOT1 + ";" // AF21
				+ ";" // A21
				+ ";" // AF22
				+ ";" // AF23
				+ ";" // AF25
				+ ";" // AF26
				+ ";" // AF27
				+ ";" // AF28
				+ ";" // AF29
				+ ";" // AF30
				+ ";" // AF31
				+ ";" // AF32
				+ ";" // AF33
				+ ";" // AF34
				+ ";" // AF35
				+ ";" // AF36
				+ ";" // AF37
				+ ";" // AF38
				+ ";" // AF39
				+ ";" // AF40
				+ ";" // AF41
				+ ";" // AF42
				+ ";" // AF43
				+ ";" // AF44
				+ ";\r\n";

		SIRB.criar_ficheiro(data_etiq, path2, path_error, false, "");
	}

	@POST
	@Path("/ficheiroPintura")
	@Produces("application/json")
	public void getFicheiroPintura(final List<HashMap<String, String>> data2) throws IOException, ParseException {
		HashMap<String, String> firstMap = data2.get(0);
		String ip_posto = firstMap.get("ip_posto");
		String id = firstMap.get("id");
		try {
			// Thread.sleep(3000);
			Integer comp_num = 1;

			String data = new SimpleDateFormat("yyyyMMddHHmmss_").format(new java.util.Date());
			String nome_ficheiro = "";

			Query query = entityManager.createNativeQuery(
					"Select a.ID_PREPARACAO_CAB,(select top 1 OF_NUM from AB_DIC_LINHA_OF e where ID_LINHA = c.id_linha and DATA <= GETDATE() order by e.DATA desc) as ofnum,c.SECCAO,c.SUBSECCAO,c.REF_COMPOSTO,a.ID_PREPARACAO "
							+ "from PIN_MOV_PREPARACAO_CAB a "
							+ "inner join PIN_MOV_PREPARACAO b on  a.ID_PREPARACAO = b.ID_PREPARACAO "
							+ "inner join AB_DIC_LINHA c on  b.ID_LINHA = c.ID_LINHA " + "where a.ID_PREPARACAO_CAB = "
							+ id);

			List<Object[]> dados = query.getResultList();

			for (Object[] content : dados) {
				nome_ficheiro = "ETIQUETA_PINTURA_ID" + content[0].toString() + ".txt";

				criarFicheiro(content[0].toString(), nome_ficheiro, content[1].toString(), content[2].toString(),
						content[3].toString(), content[4].toString(), content[5].toString(), ip_posto, null);

			}

			Query query2 = entityManager.createNativeQuery(
					"select ID_PREPARACAO_LIN,ID_PREPARACAO_CAB from PIN_MOV_PREPARACAO_LINHA where ID_PREPARACAO_CAB = "
							+ id);

			List<Object[]> dados2 = query2.getResultList();

			for (Object[] content2 : dados2) {
				alerta_etiquetas(Integer.parseInt(content2[0].toString()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void criarFicheiro(String id, String nome_ficheiro, String of, String SECCAO, String SUBSECCAO,
			String REF_COMPOSTO, String num_manutencao, String ip_posto, String ids) {
		SIRB SIRB = new SIRB();
		java.util.Date datacria = new java.util.Date();
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat horaformate = new SimpleDateFormat("HHmmss");
		String datatual = formate.format(datacria);
		String horatual = horaformate.format(datacria);
		String sequencia = "000000000";
		String path = "";
		String path2 = "";
		String path_error = "";
		String path_error2 = "";
		String modelo_REPORT = "";
		String nomeimpressora = "";
		String ipimpressora = "";
		String data_etiq = "";
		/*
		 * Query query = entityManager.createNativeQuery(
		 * "select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO from PIN_MOV_PREPARACAO_ETIQ a where ID_PREPARACAO_LIN = "
		 * + id + "");
		 */

		Query query = entityManager.createNativeQuery(
				"select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO,a.INDNUMENR,a.EMPCOD,a.PRODES,a.DATCRE "
						+ ",(select ID_PREPARACAO from PIN_MOV_PREPARACAO_CAB where ID_PREPARACAO_CAB = b.ID_PREPARACAO_CAB) as id2 "
						+ ", CASE WHEN ( a.QUANT  - a.QUANT_FINAL) < 0 THEN (( a.QUANT  - a.QUANT_FINAL) * -1) ELSE ( a.QUANT  - a.QUANT_FINAL) END as qtt , t.CISTERNA "
						+ ",( a.QUANT_FINAL / (CASE WHEN t.FACTOR_CONVERSAO IS NULL  THEN 1 WHEN t.FACTOR_CONVERSAO = 0 THEN 1 ELSE t.FACTOR_CONVERSAO END) ) as qtt2 "
						+ ",(select MEDIDA from AB_DIC_UNIDADE_MEDIDA where ID_MEDIDA = t.ID_UNIDADE) as unidaditivo "
						+ ", CASE WHEN ( a.QUANT  - a.QUANT_FINAL) < 0 THEN '-' ELSE '+' END as sinal,a.ID_MOV_PREP_ETIQUETA "
						+ "from PIN_MOV_PREPARACAO_ETIQ a "
						+ "inner join PIN_MOV_PREPARACAO_LINHA b on a.ID_PREPARACAO_LIN = b.ID_PREPARACAO_LIN "
						+ "inner join PIN_DIC_PRODUTOS t on  t.ID = b.ID_PRODUTO " + "where b.ID_PREPARACAO_CAB  = "
						+ id + "");

		Query query2 = entityManager.createNativeQuery(
				"select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO,a.INDNUMENR,a.EMPCOD,a.PRODES,a.DATCRE "
						+ ",'correcao'+CONVERT(varchar(10), ID_MOV_PREP_ETIQUETA), a.CONSUMIR as cons, '0' as id2,a.QUANT_FINAL as qtdf,a.UNISTO as unnd,a.sinal,a.ID_MOV_PREP_ETIQUETA "
						+ "from PIN_MOV_PREPARACAO_ETIQ a where a.ID_MOV_PREP_ETIQUETA in (" + ids + ")");

		Query query_folder = entityManager.createNativeQuery(
				"select top 1  PASTA_FICHEIRO,PASTA_ETIQUETAS,MODELO_REPORT,PASTA_DESTINO_ERRO from GER_PARAMETROS a");

		Query query_impressora = entityManager.createNativeQuery(
				"select top 1  NOME_IMPRESSORA,IP_IMPRESSORA from GER_POSTOS b where IP_POSTO ='" + ip_posto + "'");

		List<Object[]> dados_folder = query_folder.getResultList();
		List<Object[]> dados_impressora = query_impressora.getResultList();
		Boolean imprime = false;

		for (Object[] content : dados_folder) {
			path = content[0] + nome_ficheiro;
			path2 = content[1].toString();
			path_error = content[3] + nome_ficheiro;
			path_error2 = content[3].toString();
			modelo_REPORT = content[2].toString();
		}

		for (Object[] content2 : dados_impressora) {
			nomeimpressora = content2[0].toString();
			if (content2[1] != null) {
				ipimpressora = content2[1].toString();
			}
			imprime = true;
		}

		sequencia = sequencia();

		List<Object[]> dados = null;
		if (id != null) {
			dados = query.getResultList();
		} else {
			dados = query2.getResultList();
		}

		final ConnectProgress connectionProgress = new ConnectProgress();
		List<HashMap<String, String>> lista = null;
		List<HashMap<String, String>> lista2 = null;
		Boolean Orig_Composant = false;
		ArrayList<String> values_etiqueta = new ArrayList<String>();
		Integer count = 0;
		String data = "";
		String INDNUMCSE = "";
		String NCLRANG = "";
		Integer size_etiq = 0;
		data_etiq += "LAB_NAME=" + modelo_REPORT + "\r\n";

		if (!ipimpressora.isEmpty() && ipimpressora != null) {
			ipimpressora = ",->" + ipimpressora;
		}

		String data_path = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		if (id != null) {
			path2 = path2 + "ETIQUETA_PINTURA" + id + ".txt";
			path_error2 = path_error2 + "ETIQUETA_PINTURA" + id + ".txt";
		} else {
			path2 = path2 + "ETIQUETA_PINTURA_CORRECAO_IMPRE_" + data_path + ".txt";
			path_error2 = path_error2 + "ETIQUETA_PINTURA_CORRECAO_IMPRE_" + data_path + ".txt";
		}
		data_etiq += "THT_NAME=" + nomeimpressora + ipimpressora + "\r\n";
		data_etiq += "AF100;AF101;AF1;AF2;A2;AF3;A3;AF4;A4;AF5;A5;AF6;AF7;A7;AF8;AF9;AF10;AF11;AF24;AF12;AF16;A16;AF17;AF18;AF19;AF20;A20;AF21;A21;AF22;AF23;AF25;AF26;AF27;AF28;AF29;AF30;AF31;AF32;AF33;AF34;AF35;AF36;AF37;AF38;AF39;AF40;AF41;AF42;AF43;AF44;END;\r\n";

		try {
			lista2 = connectionProgress.getOrigineComposant2(getURLSILVER(), REF_COMPOSTO, of);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (lista2.size() > 0) {
			INDNUMCSE = lista2.get(0).get("INDNUMENR");
		}

		for (Object[] content : dados) {
			count = 0;
			NCLRANG = "";
			lista = null;

			String cisterna = (content[20] != null) ? content[20].toString() : "false";
			if (Float.parseFloat(content[3].toString()) != 0 && !content[3].toString().equals(content[1].toString())
					&& !cisterna.equals("true")) {
				// path2 = path2 + data_path + "_" + content[0].toString();

				// criar ficheiro que gera etiquetas
				data_etiq += SIRB.criaFicheiroEtiqueta(content);
				size_etiq++;
			}

			try {
				connectionProgress.EXEC_SINCRO(content[0].toString(), Float.parseFloat(content[3].toString()),
						getURLSILVER());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				lista = connectionProgress.getOrigineComposant(getURLSILVER(), content[7].toString(), of);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (lista.size() > 0) {
				count = Integer.parseInt(lista.get(0).get("total"));
				NCLRANG = lista.get(0).get("NCLRANG");
			}

			// System.out.println(content[0]);
			data += "01        ";// Soci�t�
			data += datatual; // Date suivi
			data += sequencia; // N� s�quence

			data += "    ";// + Ligne de production

			data += "1";// Type N� OF
			data += (of + "          ").substring(0, 10); // N� OF

			data += "1";// Type op�ration

			// OP_NUM
			data += ("0010").substring(0, 4);// N� Op�ration

			data += "1";// Position ( S12 )

			// Code section
			data += (SECCAO + "          ").substring(0, 10);

			// Code sous-section
			data += (SUBSECCAO + "          ").substring(0, 10);

			data += "  "; // N� d'�quipe

			// Type de ressource
			data += ("    ").substring(0, 4);

			// Code ressource
			data += ("          ").substring(0, 10);

			data += "   C"; // N� �tablissement + Type d'�l�ment C

			// Date d�but
			data += datatual;
			// Heure d�but
			data += horatual.substring(0, 6);
			// Date fin
			data += datatual;
			// Heure fin
			data += horatual.substring(0, 6);

			// Origine composant
			if (count > 0 && NCLRANG != null) {
				Orig_Composant = true;
				data += "0";
			} else {
				boolean ans = values_etiqueta.contains(content[7].toString());
				if (ans) {
					Orig_Composant = true;
					data += "0";
				} else {
					Orig_Composant = false;
					data += "1";
					values_etiqueta.add(content[7].toString());
				}
			}

			// R�f�rence compos�
			data += (REF_COMPOSTO + "                 ").substring(0, 17);

			// Variante compos� (1)
			data += ("          ").substring(0, 10);

			// Variante compos� (2)
			data += ("          ").substring(0, 10);

			// Indice du compos�
			data += ("          ").substring(0, 10);

			// N� enregistrement Cs�
			String enregistrementcse = "000000000";
			String sizecse = enregistrementcse + INDNUMCSE;
			enregistrementcse = (sizecse).substring(sizecse.length() - 9, sizecse.length());
			data += enregistrementcse;

			// N� de rang
			/*
			 * String rang = "00000"; if (Orig_Composant) { String size = rang + NCLRANG;
			 * rang = (size).substring(size.length() - 5, size.length()); data += rang; }
			 * else { data += rang; }
			 */

			if (Orig_Composant) {
				data += (NCLRANG + "     ").substring(0, 5);
			} else {
				data += ("     ").substring(0, 5);
			}

			// R�f�rence composant
			data += (content[7] + "                 ").substring(0, 17);

			// Variante composant (1)
			if (content[5] != null) {
				data += (content[5] + "          ").substring(0, 10);
			} else {
				data += "          ";
			}

			// Variante composant (2)
			if (content[6] != null) {
				data += (content[6] + "          ").substring(0, 10);
			} else {
				data += "          ";
			}

			// Indice du composant
			if (content[4] != null) {
				data += (content[4] + "          ").substring(0, 10);
			} else {
				data += "          ";
			}

			// N� enregistrement Cst

			String enregistrement = "000000000";
			if (content[14] != null) {
				String size = enregistrement + content[14];
				enregistrement = (size).substring(size.length() - 9, size.length());
				data += enregistrement;
			} else {
				data += enregistrement;
			}

			/*
			 * if (content[14] != null) { data += (content[14] + "         ").substring(0,
			 * 9); } else { data += ("         ").substring(0, 9); }
			 */

			// Type quantit�
			data += "1";

			// Quantit�
			if (content[19] != null) {
				String result = String.format("%.3f", content[19]).replace("$", ",");
				String[] parts = result.split(",");
				String part1 = "00000000000";
				String part2 = "0000";
				if (parts.length > 0) {
					if (parts[0] != null) {
						String size = part1 + parts[0];
						part1 = (size).substring(size.length() - 11, size.length());
					}
					if (parts.length > 1) {
						String size = parts[1] + part2;
						part2 = (size).substring(0, 4);
					}
				}
				data += (part1 + part2 + "  ").substring(0, 17);
			} else {
				data += "000000000000000  ";
			}

			if (id != null) {
				data += content[23].toString(); // Signe
			} else {
				data += content[23]; // Signe
			}
			// Unit�
			if (content[8] != null) {
				data += (content[8] + "    ").substring(0, 4);
			} else {
				data += "    ";
			}

			// Quantit� (US2)
			data += "               ";

			// Lieu origine
			if (content[9] != null) {
				data += (content[9] + "          ").substring(0, 10);
			} else {
				data += "          ";
			}

			// Emplacement origine
			if (content[15] != null) {
				data += (content[15] + "          ").substring(0, 10);
			} else {
				data += "          ";
			}

			// R�f�rence du lot
			if (content[10] != null) {
				data += (content[10] + "                                   ").substring(0, 35);
			} else {
				data += "                                   ";
			}

			// N� de lot interne
			String lotinterne = "000000000";
			if (content[12] != null) {
				String size = lotinterne + content[12];
				lotinterne = (size).substring(size.length() - 9, size.length());
				data += lotinterne;
			} else {
				data += lotinterne;
			}

			// N� d'�tiquette
			if (content[0] != null) {
				data += (content[0] + "          ").substring(0, 10);
			} else {
				data += "          ";
			}

			// N� enreg. �tiquette
			String etiquette = "000000000";
			if (content[1] != null) {
				String size = etiquette + content[11];
				etiquette = (size).substring(size.length() - 9, size.length());
				data += etiquette;
			} else {
				data += etiquette;
			}

			// Texte libre
			if (id != null) {
				data += (num_manutencao + "                                        ").substring(0, 40);
			} else {
				data += (content[18] + "                                        ").substring(0, 40);
			}
			data += "\r\n";

			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datacria);
			entityManager.createNativeQuery("UPDATE PIN_MOV_PREPARACAO_ETIQ SET DATA_PREP_EXEC = '" + timeStamp
					+ "' where ID_MOV_PREP_ETIQUETA = " + content[24] + "").executeUpdate();
		}

		if (data.length() > 0) {
			SIRB.criar_ficheiro(data, path, path_error, false, "");
		}

		if (size_etiq > 0 && imprime) {
			SIRB.criar_ficheiro(data_etiq, path2, path_error2, false, "");
		}

	}

	public String sequencia() {
		String sequencia = "000000000";
		Query query_seq = entityManager.createNativeQuery(
				"select top 1 NUMERO_SEQUENCIA,DATA_SEQUENCIA from GER_SEQUENCIA_FICHEIRO where DATA_SEQUENCIA = CONVERT (date, GETDATE())");

		List<Object[]> dados_seq = query_seq.getResultList();
		if (dados_seq.size() > 0) {
			Integer val = 1;
			for (Object[] contentseq : dados_seq) {
				val = Integer.parseInt(contentseq[0].toString()) + 1;
				sequencia = ("000000000" + val).substring(("000000000" + val).length() - 9,
						("000000000" + val).length());
			}
			entityManager.createNativeQuery("UPDATE GER_SEQUENCIA_FICHEIRO SET NUMERO_SEQUENCIA = " + val
					+ " where DATA_SEQUENCIA = CONVERT (date, GETDATE())").executeUpdate();
		} else {
			sequencia = "000000001";
			entityManager
					.createNativeQuery(
							"INSERT INTO GER_SEQUENCIA_FICHEIRO (DATA_SEQUENCIA,NUMERO_SEQUENCIA) VALUES (GETDATE(),1)")
					.executeUpdate();
		}
		return sequencia;
	}

	public void alerta_etiquetas(Integer id) {
		SIRB SIRB = new SIRB();
		DecimalFormat decimalFormat = new DecimalFormat("#0.000");

		String email_para = "", numero_manutencao = "", data_manutencao = "", nome_pote = "", cabine = "",
				utilizador = "", total_consumido = "", valor_aditivo = "", linha = "", tipo_manutencao = "",
				ref_aditivo = "", nome_aditivo = "";

		String etiquetas = "<table  border='1'><tr><th><b>Nº Etiqueta</b></th><th><b>Qtd.</b></th><th><b>Consumido</b></th><th><b>Qtd. Final</b></th></tr>";
		String valor = null, total = null;
		Query query = entityManager.createNativeQuery(
				"select d.ID_PREPARACAO,c.ETQNUM,c.CONSUMIR,b.VALOR,d.DATA_PLANEAMENTO,d.HORA_PLANEAMENTO, "
						+ "(select SUM(v.CONSUMIR) from PIN_MOV_PREPARACAO_ETIQ v where v.ID_PREPARACAO_LIN = b.ID_PREPARACAO_LIN) as total, "
						+ "(select MEDIDA from AB_DIC_UNIDADE_MEDIDA where ID_MEDIDA = b.ID_UNIDADE) as unidade, "
						+ "(select f.NOME from PIN_DIC_POTES f where f.ID = b.ID_POTE) as banho, b.ID_POTE,  "
						+ "/*(select f.EMAIL_PARA from AB_DIC_BANHO f where f.ID_BANHO = a.ID_BANHO)*/ null as email_banho, "
						+ "(select g.NOME_CABINE from PIN_DIC_POTES f left join PIN_DIC_CABINES g on f.ID_CABINE = g.ID where f.ID = b.ID_POTE) as cabine, "
						+ "/*(select h.NOME_TIPO_MANUTENCAO from AB_DIC_TIPO_MANUTENCAO h where h.ID_TIPO_MANUTENCAO = d.ID_TIPO_MANUTENCAO)*/ '' as tipo, "
						+ "(select NOME_UTILIZADOR from GER_UTILIZADORES h where ID_UTILIZADOR = d.UTZ_ULT_MODIF) as utilizador, "
						+ "(select NOME_LINHA from AB_DIC_LINHA h where ID_LINHA = d.ID_LINHA) as linha, "
						+ "CASE WHEN ( c.QUANT  - c.QUANT_FINAL) < 0 THEN 0 ELSE ( c.QUANT  - c.QUANT_FINAL) END as qtt , c.UNICOD,c.QUANT, t.FACTOR_CONVERSAO,t.NOME_REF,t.COD_REF,c.QUANT_FINAL, "
						+ "a.DATA_PREPARACAO,a.HORA_PREPARACAO,a.DATA_EXECUCAO,a.HORA_EXECUCAO,(select f.NOME_PROJETO from PIN_MOV_RECEITAS f  where f.ID = a.ID_RECEITA and f.VERSAO = a.VERSAO) as receita "
						+ "from PIN_MOV_PREPARACAO_CAB a "
						+ "inner join PIN_MOV_PREPARACAO_LINHA b on a.ID_PREPARACAO_CAB = b.ID_PREPARACAO_CAB "
						+ "left join PIN_MOV_PREPARACAO_ETIQ c on b.ID_PREPARACAO_LIN = c.ID_PREPARACAO_LIN "
						+ "inner join PIN_MOV_PREPARACAO d on a.ID_PREPARACAO = d.ID_PREPARACAO "
						+ "inner join PIN_DIC_PRODUTOS t on b.ID_PRODUTO =  t.ID " + "where b.ID_PREPARACAO_LIN ="
						+ id);
		// ( c.CONSUMIR / (CASE WHEN t.FACTOR_CONVERSAO IS NULL THEN 1 WHEN
		// t.FACTOR_CONVERSAO = 0 THEN 1 ELSE t.FACTOR_CONVERSAO END) )
		List<Object[]> dados = query.getResultList();

		String datahoraexecucao = "";
		String datahorapreparacao = "";

		for (Object[] content : dados) {

			if (content[24] != null) {
				datahoraexecucao = content[24].toString() + " " + content[25].toString().substring(0, 8);
			}

			if (content[22] != null) {
				datahorapreparacao = content[22].toString() + " " + content[23].toString().substring(0, 8);
			}

			String vv = (content[3] != null) ? content[3].toString() : "0";
			String tt = (content[6] != null) ? content[6].toString() : "0";
			Double valor2 = Double.valueOf(vv.replace(",", "."));
			Double total2 = Double.valueOf(tt.replace(",", "."));
			valor = decimalFormat.format(valor2);
			total = decimalFormat.format(total2);

			numero_manutencao = content[0].toString();
			valor_aditivo = valor + " " + content[7].toString();
			data_manutencao = content[4].toString() + " " + content[5].toString().substring(0, 8);
			total_consumido = total + " " + content[7].toString();
			nome_pote = content[9].toString() + "/" + content[8].toString();
			email_para = (content[10] != null) ? content[10].toString() : "";
			cabine = content[11].toString();
			tipo_manutencao = content[12].toString();
			utilizador = content[13].toString();
			linha = content[14].toString();
			nome_aditivo = (content[19] != null) ? content[19].toString() : "";
			ref_aditivo = (content[20] != null) ? content[20].toString() : "";

			String cc = (content[2] != null) ? content[2].toString() : "0";
			Double total3 = Double.valueOf(cc.replace(",", "."));
			String qq = (content[15] != null) ? content[15].toString() : "0";
			Double total4 = Double.valueOf(qq.replace(",", "."));
			String dd = (content[17] != null) ? content[17].toString() : "0";
			Double total5 = Double.valueOf(dd.replace(",", "."));
			Double factor = Double.valueOf(((content[18] != null) ? content[18].toString() : "0").replace(",", "."));
			String qf = (content[21] != null) ? content[21].toString() : "0";
			Double qtdfinal = Double.valueOf(qf.replace(",", "."));

			factor = (factor == 0 || factor == null) ? 1 : factor;

			String yy = (content[16] != null) ? content[16].toString() : "--";
			String qtd = decimalFormat.format(total5).replace("$", ",") + " " + yy + "/"
					+ decimalFormat.format(total5 / factor).replace("$", ",") + " " + content[7].toString();

			String etn = (content[1] != null) ? content[1].toString() : "Sem Etiqueta";
			String etn1 = (content[7] != null) ? content[7].toString() : "0";
			etiquetas += "<tr><td style='padding: 0px 5px 0px 5px;'>" + etn
					+ "</td><td style='padding: 0px 5px 0px 5px;'>" + qtd
					+ "</td><td style='padding: 0px 5px 0px 5px;'>"
					+ decimalFormat.format(total3 * factor).replace("$", ",") + " " + yy + "/"
					+ decimalFormat.format(total3).replace("$", ",") + " " + etn1
					+ "</td><td style='padding: 0px 5px 0px 5px;'>" + decimalFormat.format(qtdfinal).replace("$", ",")
					+ " " + yy + "</td></tr>";
		}

		etiquetas += "</table>";
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> n = new HashMap<String, String>();
		n.put("MODULO", "1");
		n.put("MOMENTO", "Ao Finalizar Preparação");
		n.put("PAGINA", "Preparações Pintura");
		n.put("ESTADO", "1");
		n.put("EMAIL_PARA", email_para);

		n.put("DADOS",
				"{numero_preparacao::" + numero_manutencao + "\n/datahorapreparacao::" + datahorapreparacao
						+ "\n/datahoraexecucao::" + datahoraexecucao + "\n/data_preparacao::" + data_manutencao + ""
						+ "\n/nome_pote::" + nome_pote + "" + "\n/cabine::" + cabine + "\n/utilizador::" + utilizador
						+ "" + "\n/dados_etiquetas::" + etiquetas + "" + "" + "\n/total_consumido::"
						+ total_consumido.replace("$", ",") + "\n/valor_aditivo::" + valor_aditivo.replace("$", ",")
						+ "" + "\n/linha::" + linha + "" + "\n/tipo_manutencao::" + tipo_manutencao
						+ "\n/nome_aditivo::" + nome_aditivo + "\n/ref_aditivo::" + ref_aditivo + "}");
		data.add(n);

		if (valor != null && !valor.equals(total))
			SIRB.verficaEventos(data);
	}

	/************************************* PIN_MOV_RECEITAS */
	@POST
	@Path("/createPIN_MOV_RECEITAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_RECEITAS insertPIN_MOV_RECEITAS(final PIN_MOV_RECEITAS data) {
		List<Object[]> dados = entityManager
				.createNativeQuery("select ISNULL(MAX(ID),0)+1,0 as teste FROM PIN_MOV_RECEITAS").getResultList();
		data.setID(Integer.parseInt(dados.get(0)[0].toString()));
		data.setVERSAO(1);
		return dao20.create(data);
	}

	@GET
	@Path("/getPIN_MOV_RECEITASyid/{id}/{versao}")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS> getPIN_MOV_RECEITASyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao20.getbyid(id, 0, versao);
	}

	@GET
	@Path("/getPIN_MOV_RECEITAS_VERSOES/{id}/{versao}")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS> getPIN_MOV_RECEITAS_VERSOES(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao20.getversoes(id, versao);
	}

	@GET
	@Path("/getPIN_MOV_RECEITASchechExistRef/{referencia}/{id}")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS> getPIN_MOV_RECEITASchechExistRef(@PathParam("id") Integer id,
			@PathParam("referencia") String referencia) {
		String proref = java.net.URLDecoder.decode(referencia);
		return dao20.chechExistRef(proref, id);
	}

	@POST
	@Path("/getPIN_MOV_RECEITASchechExistRefs/{id}")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS> getPIN_MOV_RECEITASchechExistRefs(@PathParam("id") Integer id,
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String REFERENCIAS = firstMap.get("REFERENCIAS");

		return dao20.chechExistRefs(REFERENCIAS, id);
	}

	@GET
	@Path("/getPIN_MOV_RECEITAS")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS> getPIN_MOV_RECEITAS() {
		return dao20.allEntries();
	}

	@GET
	@Path("/getPIN_MOV_RECEITASFILTRO/{referencia}")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS> getPIN_MOV_RECEITASFILTRO(@PathParam("referencia") String referencia) {
		return dao20.getallFiltro(referencia);
	}

	@GET
	@Path("/getPIN_MOV_RECEITAS2")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS> getPIN_MOV_RECEITASLINHA() {
		return dao20.getall();
	}

	@GET
	@Path("/getPIN_MOV_RECEITAS3")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS> getPIN_MOV_RECEITASLINHA3() {
		return dao20.getall2();
	}

	@DELETE
	@Path("/deletePIN_MOV_RECEITAS/{id}/{versao}")
	public void deletePIN_MOV_RECEITAS(@PathParam("id") Integer id, @PathParam("versao") Integer versao) {
		PIN_MOV_RECEITAS PIN_MOV_RECEITAS = new PIN_MOV_RECEITAS();
		PIN_MOV_RECEITAS.setID(id);
		dao20.delete(PIN_MOV_RECEITAS);
	}

	@PUT
	@Path("/updatePIN_MOV_RECEITAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_RECEITAS updatePIN_MOV_RECEITAS(final PIN_MOV_RECEITAS PIN_MOV_RECEITAS) {
		PIN_MOV_RECEITAS.setID(PIN_MOV_RECEITAS.getID());
		PIN_MOV_RECEITAS.setVERSAO(PIN_MOV_RECEITAS.getVERSAO());
		return dao20.update(PIN_MOV_RECEITAS);
	}

	/************************************* PIN_MOV_RECEITAS_LINHAS */
	@POST
	@Path("/createPIN_MOV_RECEITAS_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_RECEITAS_LINHAS insertAB_DIC_ADITIVO(final PIN_MOV_RECEITAS_LINHAS data) {
		return dao21.create(data);
	}

	@GET
	@Path("/getPIN_MOV_RECEITAS_LINHASyid/{id}/{versao}")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS_LINHAS> getPIN_MOV_RECEITAS_LINHASyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao21.getbyid(id, versao);
	}

	@GET
	@Path("/getPIN_MOV_RECEITAS_LINHAS")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS_LINHAS> getPIN_MOV_RECEITAS_LINHAS() {
		return dao21.allEntries();
	}

	@GET
	@Path("/getPIN_MOV_RECEITAS_LINHAS2")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS_LINHAS> getPIN_MOV_RECEITAS_LINHASLINHA() {
		return dao21.getall();
	}

	@DELETE
	@Path("/deletePIN_MOV_RECEITAS_LINHAS/{id}")
	public void deletePIN_MOV_RECEITAS_LINHAS(@PathParam("id") Integer id) {
		PIN_MOV_RECEITAS_LINHAS PIN_MOV_RECEITAS_LINHAS = new PIN_MOV_RECEITAS_LINHAS();
		PIN_MOV_RECEITAS_LINHAS.setID(id);
		dao21.delete(PIN_MOV_RECEITAS_LINHAS);
	}

	@PUT
	@Path("/updatePIN_MOV_RECEITAS_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_RECEITAS_LINHAS updatePIN_MOV_RECEITAS_LINHAS(
			final PIN_MOV_RECEITAS_LINHAS PIN_MOV_RECEITAS_LINHAS) {
		PIN_MOV_RECEITAS_LINHAS.setID(PIN_MOV_RECEITAS_LINHAS.getID());
		return dao21.update(PIN_MOV_RECEITAS_LINHAS);
	}

	/************************************* PIN_MOV_RECEITAS_REFERENCIAS */
	@POST
	@Path("/createPIN_MOV_RECEITAS_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_RECEITAS_REFERENCIAS insertAB_DIC_ADITIVO(final PIN_MOV_RECEITAS_REFERENCIAS data) {
		return dao31.create(data);
	}

	@GET
	@Path("/getPIN_MOV_RECEITAS_REFERENCIASyid/{id}/{versao}")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS_REFERENCIAS> getPIN_MOV_RECEITAS_REFERENCIASyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao31.getbyid(id, versao);
	}

	@GET
	@Path("/getPIN_MOV_RECEITAS_REFERENCIAS")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS_REFERENCIAS> getPIN_MOV_RECEITAS_REFERENCIAS() {
		return dao31.allEntries();
	}

	@GET
	@Path("/getPIN_MOV_RECEITAS_REFERENCIAS2")
	@Produces("application/json")
	public List<PIN_MOV_RECEITAS_REFERENCIAS> getPIN_MOV_RECEITAS_REFERENCIASLINHA() {
		return dao31.getall();
	}

	@DELETE
	@Path("/deletePIN_MOV_RECEITAS_REFERENCIAS/{id}")
	public void deletePIN_MOV_RECEITAS_REFERENCIAS(@PathParam("id") Integer id) {
		PIN_MOV_RECEITAS_REFERENCIAS PIN_MOV_RECEITAS_REFERENCIAS = new PIN_MOV_RECEITAS_REFERENCIAS();
		PIN_MOV_RECEITAS_REFERENCIAS.setID(id);
		dao31.delete(PIN_MOV_RECEITAS_REFERENCIAS);
	}

	@PUT
	@Path("/updatePIN_MOV_RECEITAS_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_RECEITAS_REFERENCIAS updatePIN_MOV_RECEITAS_REFERENCIAS(
			final PIN_MOV_RECEITAS_REFERENCIAS PIN_MOV_RECEITAS_REFERENCIAS) {
		PIN_MOV_RECEITAS_REFERENCIAS.setID(PIN_MOV_RECEITAS_REFERENCIAS.getID());
		return dao31.update(PIN_MOV_RECEITAS_REFERENCIAS);
	}

	/************************************* PIN_DIC_PRE_SET */
	@POST
	@Path("/createPIN_DIC_PRE_SET")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_PRE_SET insertAB_DIC_ADITIVO(final PIN_DIC_PRE_SET data) {
		return dao22.create(data);
	}

	@GET
	@Path("/getPIN_DIC_PRE_SETyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_PRE_SET> getPIN_DIC_PRE_SETyid(@PathParam("id") Integer id) {
		return dao22.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_PRE_SET")
	@Produces("application/json")
	public List<PIN_DIC_PRE_SET> getPIN_DIC_PRE_SETLINHA() {
		return dao22.getall();
	}

	@DELETE
	@Path("/deletePIN_DIC_PRE_SET/{id}")
	public void deletePIN_DIC_PRE_SET(@PathParam("id") Integer id) {
		PIN_DIC_PRE_SET PIN_DIC_PRE_SET = new PIN_DIC_PRE_SET();
		PIN_DIC_PRE_SET.setID(id);
		dao22.delete(PIN_DIC_PRE_SET);
	}

	@PUT
	@Path("/updatePIN_DIC_PRE_SET")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_PRE_SET updatePIN_DIC_PRE_SET(final PIN_DIC_PRE_SET PIN_DIC_PRE_SET) {
		PIN_DIC_PRE_SET.setID(PIN_DIC_PRE_SET.getID());

		PIN_DIC_PRE_SET OLD_PIN_DIC_PRE_SET = dao22.getbyid(PIN_DIC_PRE_SET.getID()).get(0);
		Boolean atualiza = false;

		if ((OLD_PIN_DIC_PRE_SET.getCAUDAL() != null ? OLD_PIN_DIC_PRE_SET.getCAUDAL() : "null")
				.equals(PIN_DIC_PRE_SET.getCAUDAL())) {
			atualiza = true;
		}
		if ((OLD_PIN_DIC_PRE_SET.getPRESSAO_ATOMIZACAO() != null ? OLD_PIN_DIC_PRE_SET.getPRESSAO_ATOMIZACAO() : "null")
				.equals(PIN_DIC_PRE_SET.getPRESSAO_ATOMIZACAO())) {
			atualiza = true;
		}
		if ((OLD_PIN_DIC_PRE_SET.getPRESSAO_LEQUE() != null ? OLD_PIN_DIC_PRE_SET.getPRESSAO_LEQUE() : "null")
				.equals(PIN_DIC_PRE_SET.getPRESSAO_LEQUE())) {
			atualiza = true;
		}
		if ((OLD_PIN_DIC_PRE_SET.getTAMANHO_BICO() != null ? OLD_PIN_DIC_PRE_SET.getTAMANHO_BICO() : "null")
				.equals(PIN_DIC_PRE_SET.getTAMANHO_BICO())) {
			atualiza = true;
		}
		if ((OLD_PIN_DIC_PRE_SET.getVOLTAS_PISTOLA() != null ? OLD_PIN_DIC_PRE_SET.getVOLTAS_PISTOLA() : "null")
				.equals(PIN_DIC_PRE_SET.getVOLTAS_PISTOLA())) {
			atualiza = true;
		}

		PIN_DIC_PRE_SET _PIN_DIC_PRE_SET = dao22.update(PIN_DIC_PRE_SET);

		if (atualiza) {
			int query_folder = entityManager.createNativeQuery("EXEC PIN_UPDATE_PIN_DIC_PRE_SET :id")
					.setParameter("id", PIN_DIC_PRE_SET.getID()).executeUpdate();
		}

		return _PIN_DIC_PRE_SET;
	}

	/************************************* PIN_DIC_REGISTO_BASTIDOR */
	@POST
	@Path("/createPIN_DIC_REGISTO_BASTIDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_REGISTO_BASTIDOR insertAB_DIC_ADITIVO(final PIN_DIC_REGISTO_BASTIDOR data) {
		return dao23.create(data);
	}

	@GET
	@Path("/getPIN_DIC_REGISTO_BASTIDORyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_REGISTO_BASTIDOR> getPIN_DIC_REGISTO_BASTIDORyid(@PathParam("id") Integer id) {
		return dao23.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_REGISTO_BASTIDOR")
	@Produces("application/json")
	public List<PIN_DIC_REGISTO_BASTIDOR> getPIN_DIC_REGISTO_BASTIDORLINHA() {
		return dao23.getall();
	}

	@DELETE
	@Path("/deletePIN_DIC_REGISTO_BASTIDOR/{id}")
	public void deletePIN_DIC_REGISTO_BASTIDOR(@PathParam("id") Integer id) {
		PIN_DIC_REGISTO_BASTIDOR PIN_DIC_REGISTO_BASTIDOR = new PIN_DIC_REGISTO_BASTIDOR();
		PIN_DIC_REGISTO_BASTIDOR.setID(id);
		dao23.delete(PIN_DIC_REGISTO_BASTIDOR);
	}

	@PUT
	@Path("/updatePIN_DIC_REGISTO_BASTIDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_REGISTO_BASTIDOR updatePIN_DIC_REGISTO_BASTIDOR(
			final PIN_DIC_REGISTO_BASTIDOR PIN_DIC_REGISTO_BASTIDOR) {
		PIN_DIC_REGISTO_BASTIDOR.setID(PIN_DIC_REGISTO_BASTIDOR.getID());
		return dao23.update(PIN_DIC_REGISTO_BASTIDOR);
	}

	/************************************* PIN_DIC_REGISTO_SALAS_MISTURA */
	@POST
	@Path("/createPIN_DIC_REGISTO_SALAS_MISTURA")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_REGISTO_SALAS_MISTURA insertAB_DIC_ADITIVO(final PIN_DIC_REGISTO_SALAS_MISTURA data) {
		return dao24.create(data);
	}

	@GET
	@Path("/getPIN_DIC_REGISTO_SALAS_MISTURAyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_REGISTO_SALAS_MISTURA> getPIN_DIC_REGISTO_SALAS_MISTURAyid(@PathParam("id") Integer id) {
		return dao24.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_REGISTO_SALAS_MISTURA")
	@Produces("application/json")
	public List<PIN_DIC_REGISTO_SALAS_MISTURA> getPIN_DIC_REGISTO_SALAS_MISTURALINHA() {
		return dao24.getall();
	}

	@GET
	@Path("/getPIN_DIC_REGISTO_SALAS_MISTURA2")
	@Produces("application/json")
	public List<PIN_DIC_REGISTO_SALAS_MISTURA> getPIN_DIC_REGISTO_SALAS_MISTURALINHA2() {
		return dao24.getall2();
	}

	@DELETE
	@Path("/deletePIN_DIC_REGISTO_SALAS_MISTURA/{id}")
	public void deletePIN_DIC_REGISTO_SALAS_MISTURA(@PathParam("id") Integer id) {
		PIN_DIC_REGISTO_SALAS_MISTURA PIN_DIC_REGISTO_SALAS_MISTURA = new PIN_DIC_REGISTO_SALAS_MISTURA();
		PIN_DIC_REGISTO_SALAS_MISTURA.setID(id);
		dao24.delete(PIN_DIC_REGISTO_SALAS_MISTURA);
	}

	@PUT
	@Path("/updatePIN_DIC_REGISTO_SALAS_MISTURA")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_REGISTO_SALAS_MISTURA updatePIN_DIC_REGISTO_SALAS_MISTURA(
			final PIN_DIC_REGISTO_SALAS_MISTURA PIN_DIC_REGISTO_SALAS_MISTURA) {
		PIN_DIC_REGISTO_SALAS_MISTURA.setID(PIN_DIC_REGISTO_SALAS_MISTURA.getID());
		return dao24.update(PIN_DIC_REGISTO_SALAS_MISTURA);
	}

	/*************************************
	 * PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS
	 */
	@POST
	@Path("/createPIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS insertAB_DIC_ADITIVO(
			final PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS data) {
		return dao25.create(data);
	}

	@GET
	@Path("/getPIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIASbyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS> getPIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIASyid(
			@PathParam("id") Integer id) {
		return dao25.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS")
	@Produces("application/json")
	public List<PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS> getPIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIASLINHA() {
		return dao25.getall();
	}

	@DELETE
	@Path("/deletePIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS/{id}")
	public void deletePIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS(@PathParam("id") Integer id) {
		PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS = new PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS();
		PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS.setID(id);
		dao25.delete(PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS);
	}

	@PUT
	@Path("/updatePIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS updatePIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS(
			final PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS) {
		PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS.setID(PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS.getID());
		return dao25.update(PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS);
	}

	/************************************* PIN_DIC_CORES */
	@POST
	@Path("/createPIN_DIC_CORES")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_CORES insertPIN_DIC_CORES(final PIN_DIC_CORES data) {
		return dao26.create(data);
	}

	@GET
	@Path("/getPIN_DIC_CORESbyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_CORES> getPIN_DIC_CORESbyid_linha(@PathParam("id") Integer id) {
		return dao26.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_CORES")
	@Produces("application/json")
	public List<PIN_DIC_CORES> getPIN_DIC_CORES() {
		return dao26.getall();
	}

	@DELETE
	@Path("/deletePIN_DIC_CORES/{id}")
	public void deletePIN_DIC_CORES(@PathParam("id") Integer id) {
		PIN_DIC_CORES PIN_DIC_CORES = new PIN_DIC_CORES();
		PIN_DIC_CORES.setID(id);
		dao26.delete(PIN_DIC_CORES);
	}

	@PUT
	@Path("/updatePIN_DIC_CORES")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_CORES updatePIN_DIC_CORES(final PIN_DIC_CORES PIN_DIC_CORES) {
		PIN_DIC_CORES.setID(PIN_DIC_CORES.getID());
		return dao26.update(PIN_DIC_CORES);
	}

	/************************************* PIN_DIC_PROGRAMAS */
	@POST
	@Path("/createPIN_DIC_PROGRAMAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_PROGRAMAS insertPIN_DIC_PROGRAMAS(final PIN_DIC_PROGRAMAS data) {
		return dao27.create(data);
	}

	@GET
	@Path("/getPIN_DIC_PROGRAMASbyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_PROGRAMAS> getPIN_DIC_PROGRAMASbyid_linha(@PathParam("id") Integer id) {
		return dao27.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_PROGRAMASbyid2/{id}")
	@Produces("application/json")
	public List<PIN_DIC_PROGRAMAS> getPIN_DIC_PROGRAMASbyid_linha2(@PathParam("id") Integer id) {
		return dao27.getbyid2(id);
	}

	@GET
	@Path("/getPIN_DIC_PROGRAMASbyid3/{id}")
	@Produces("application/json")
	public List<PIN_DIC_PROGRAMAS> getPIN_DIC_PROGRAMASbyid_linha3(@PathParam("id") Integer id) {
		return dao27.getbyid3(id);
	}

	@POST
	@Path("/getPIN_DIC_PROGRAMASvalidaseexisterepetidos")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> getPIN_DIC_PROGRAMASvalidaseexisterepetidos(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ID = firstMap.get("ID");
		String REFERENCIAS = firstMap.get("REFERENCIAS");

		Query query_folder = entityManager.createNativeQuery("select distinct a.ID,a.CODIGO from PIN_DIC_PROGRAMAS a "
				+ "LEFT JOIN PIN_DIC_PROGRAMAS_REFERENCIAS b on a.ID = b.ID_PROGRAMA " + "where a.ID not in (" + ID
				+ ") and b.REFERENCIA in (select value from string_split( '" + REFERENCIAS
				+ "',',')) AND ATIVO = 1 AND ISNULL(ANULADO,0) = 0 ");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;

	}

	@POST
	@Path("/atualizaESTADO_PROGRAMAS")
	@Consumes("*/*")
	@Produces("application/json")
	public Integer atualizaESTADO_PROGRAMAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");
		String ESTADO = firstMap.get("ESTADO");
		String USER = firstMap.get("USER");

		Query query_folder = entityManager.createNativeQuery("UPDATE PIN_DIC_PROGRAMAS SET UTZ_MODIF = " + USER
				+ ",DATA_MODIF=GETDATE(), " + "UTZ_ANULA= CASE WHEN " + ESTADO + " = 1 THEN null ELSE " + USER
				+ " END,DATA_ANULA = CASE WHEN " + ESTADO + " = 1 THEN null ELSE GETDATE() END, " + "ATIVO = " + ESTADO
				+ " where ID in (select value from string_split( '" + IDS + "',',')) ");

		Integer dados_folder = query_folder.executeUpdate();

		return dados_folder;
	}

	@GET
	@Path("/getPIN_DIC_PROGRAMAS")
	@Produces("application/json")
	public List<PIN_DIC_PROGRAMAS> getPIN_DIC_PROGRAMAS() {
		return dao27.getAll();
	}

	@GET
	@Path("/getbyTempNumber")
	@Produces("application/json")
	public List<PIN_DIC_PROGRAMAS> getbyTempNumber() {
		return dao27.getbyTempNumber();
	}

	@DELETE
	@Path("/deletePIN_DIC_PROGRAMAS/{id}")
	public void deletePIN_DIC_PROGRAMAS(@PathParam("id") Integer id) {
		PIN_DIC_PROGRAMAS PIN_DIC_PROGRAMAS = new PIN_DIC_PROGRAMAS();
		PIN_DIC_PROGRAMAS.setID(id);
		dao27.delete(PIN_DIC_PROGRAMAS);
	}

	@PUT
	@Path("/updatePIN_DIC_PROGRAMAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_PROGRAMAS updatePIN_DIC_PROGRAMAS(final PIN_DIC_PROGRAMAS PIN_DIC_PROGRAMAS) {
		PIN_DIC_PROGRAMAS.setID(PIN_DIC_PROGRAMAS.getID());
		return dao27.update(PIN_DIC_PROGRAMAS);
	}

	/************************************* PIN_DIC_PROGRAMAS_REFERENCIAS */
	@POST
	@Path("/createPIN_DIC_PROGRAMAS_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_PROGRAMAS_REFERENCIAS insertPIN_DIC_PROGRAMAS_REFERENCIAS(final PIN_DIC_PROGRAMAS_REFERENCIAS data) {
		return dao28.create(data);
	}

	@GET
	@Path("/getPIN_DIC_PROGRAMAS_REFERENCIASbyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_PROGRAMAS_REFERENCIAS> getPIN_DIC_PROGRAMAS_REFERENCIASbyId(@PathParam("id") Integer id) {
		return dao28.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_PROGRAMAS_REFERENCIAS")
	@Produces("application/json")
	public List<PIN_DIC_PROGRAMAS_REFERENCIAS> getPIN_DIC_PROGRAMAS_REFERENCIAS() {
		return dao28.getall();
	}

	@DELETE
	@Path("/deletePIN_DIC_PROGRAMAS_REFERENCIAS/{id}")
	public void deletePIN_DIC_PROGRAMAS_REFERENCIAS(@PathParam("id") Integer id) {
		PIN_DIC_PROGRAMAS_REFERENCIAS PIN_DIC_PROGRAMAS_REFERENCIAS = new PIN_DIC_PROGRAMAS_REFERENCIAS();
		PIN_DIC_PROGRAMAS_REFERENCIAS.setID(id);
		dao28.delete(PIN_DIC_PROGRAMAS_REFERENCIAS);
	}

	@PUT
	@Path("/updatePIN_DIC_PROGRAMAS_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_PROGRAMAS_REFERENCIAS updatePIN_DIC_PROGRAMAS_REFERENCIAS(
			final PIN_DIC_PROGRAMAS_REFERENCIAS PIN_DIC_PROGRAMAS_REFERENCIAS) {
		PIN_DIC_PROGRAMAS_REFERENCIAS.setID(PIN_DIC_PROGRAMAS_REFERENCIAS.getID());
		return dao28.update(PIN_DIC_PROGRAMAS_REFERENCIAS);
	}

	/************************************* PIN_DIC_RACKS */
	@POST
	@Path("/createPIN_DIC_RACKS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_RACKS insertPIN_DIC_RACKS(final PIN_DIC_RACKS data) {
		return dao29.create(data);
	}

	@GET
	@Path("/getPIN_DIC_RACKSbyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_RACKS> getPIN_DIC_RACKSbyId(@PathParam("id") Integer id) {
		return dao29.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_RACKS")
	@Produces("application/json")
	public List<PIN_DIC_RACKS> getPIN_DIC_RACKS() {
		return dao29.getall();
	}

	@DELETE
	@Path("/deletePIN_DIC_RACKS/{id}")
	public void deletePIN_DIC_RACKS(@PathParam("id") Integer id) {
		PIN_DIC_RACKS PIN_DIC_RACKS = new PIN_DIC_RACKS();
		PIN_DIC_RACKS.setID(id);
		dao29.delete(PIN_DIC_RACKS);
	}

	@PUT
	@Path("/updatePIN_DIC_RACKS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_RACKS updatePIN_DIC_RACKS(final PIN_DIC_RACKS PIN_DIC_RACKS) {
		PIN_DIC_RACKS.setID(PIN_DIC_RACKS.getID());
		return dao29.update(PIN_DIC_RACKS);
	}

	/************************************* FIN_DIC_TIPO_MOVIMENTO_STOCK */
	@POST
	@Path("/createFIN_DIC_TIPO_MOVIMENTO_STOCK")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_TIPO_MOVIMENTO_STOCK insertFIN_DIC_TIPO_MOVIMENTO_STOCK(final FIN_DIC_TIPO_MOVIMENTO_STOCK data) {
		return dao30.create(data);
	}

	@GET
	@Path("/getFIN_DIC_TIPO_MOVIMENTO_STOCKbyid/{id}")
	@Produces("application/json")
	public List<FIN_DIC_TIPO_MOVIMENTO_STOCK> getFIN_DIC_TIPO_MOVIMENTO_STOCKbyId(@PathParam("id") Integer id) {
		return dao30.getbyId(id);
	}

	@GET
	@Path("/getFIN_DIC_TIPO_MOVIMENTO_STOCK")
	@Produces("application/json")
	public List<FIN_DIC_TIPO_MOVIMENTO_STOCK> getFIN_DIC_TIPO_MOVIMENTO_STOCK() {
		return dao30.getall();
	}

	@DELETE
	@Path("/deleteFIN_DIC_TIPO_MOVIMENTO_STOCK/{id}")
	public void deleteFIN_DIC_TIPO_MOVIMENTO_STOCK(@PathParam("id") Integer id) {
		FIN_DIC_TIPO_MOVIMENTO_STOCK FIN_DIC_TIPO_MOVIMENTO_STOCK = new FIN_DIC_TIPO_MOVIMENTO_STOCK();
		FIN_DIC_TIPO_MOVIMENTO_STOCK.setID(id);
		dao30.delete(FIN_DIC_TIPO_MOVIMENTO_STOCK);
	}

	@PUT
	@Path("/updateFIN_DIC_TIPO_MOVIMENTO_STOCK")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_TIPO_MOVIMENTO_STOCK updateFIN_DIC_TIPO_MOVIMENTO_STOCK(
			final FIN_DIC_TIPO_MOVIMENTO_STOCK FIN_DIC_TIPO_MOVIMENTO_STOCK) {
		FIN_DIC_TIPO_MOVIMENTO_STOCK.setID(FIN_DIC_TIPO_MOVIMENTO_STOCK.getID());
		return dao30.update(FIN_DIC_TIPO_MOVIMENTO_STOCK);
	}

	@GET
	@Path("/getTIPOS_MOVIMENTO")
	@Produces("application/json")
	public List<Object[]> getTIPOS_MOVIMENTO() {
		Query query_folder = entityManager.createNativeQuery("select mvscod,mvslib from SILVER_BI.dbo.SPAMVS");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/getProtypcod")
	@Produces("application/json")
	public List<Object[]> getProtypcod() {
		Query query_folder = entityManager.createNativeQuery("select protypcod,protyplib from SILVER_BI.dbo.SPAPRT");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/getAchfamcod")
	@Produces("application/json")
	public List<Object[]> getAchfamcod() {
		Query query_folder = entityManager.createNativeQuery("select famcod,famlib from SILVER_BI.dbo.SPAFAM");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_STOCK")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_STOCK(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String MES = firstMap.get("MES");
		String protypcod = firstMap.get("protypcod");
		String achfamcod = firstMap.get("achfamcod");

		if (protypcod != null)
			protypcod = "'" + protypcod + "'";

		if (achfamcod != null)
			achfamcod = "'" + achfamcod + "'";

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.GET_ANALISE_STOCK " + ANO + "," + MES + "," + protypcod + "," + achfamcod);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_CONSUMOS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_CONSUMOS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String MES = firstMap.get("MES");
		String protypcod = firstMap.get("protypcod");
		String achfamcod = firstMap.get("achfamcod");

		if (protypcod != null)
			protypcod = "'" + protypcod + "'";

		if (achfamcod != null)
			achfamcod = "'" + achfamcod + "'";

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.GET_ANALISE_CONSUMOS " + ANO + "," + MES + "," + protypcod + "," + achfamcod);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* FIN_DIC_FILTROS */
	@POST
	@Path("/createFIN_DIC_FILTROS")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_FILTROS insertFIN_DIC_FILTROS(final FIN_DIC_FILTROS data) {
		return dao32.create(data);
	}

	@GET
	@Path("/getFIN_DIC_FILTROSbyid/{id}")
	@Produces("application/json")
	public List<FIN_DIC_FILTROS> getFIN_DIC_FILTROSbyId(@PathParam("id") Integer id) {
		return dao32.getbyId(id);
	}

	@GET
	@Path("/getFIN_DIC_FILTROSbyTipo/{tipo}")
	@Produces("application/json")
	public List<FIN_DIC_FILTROS> getFIN_DIC_FILTROSbyTipo(@PathParam("tipo") Integer tipo) {
		return dao32.getbyTipo(tipo);
	}

	@GET
	@Path("/getFIN_DIC_FILTROS")
	@Produces("application/json")
	public List<FIN_DIC_FILTROS> getFIN_DIC_FILTROS() {
		return dao32.getall();
	}

	@DELETE
	@Path("/deleteFIN_DIC_FILTROS/{id}")
	public void deleteFIN_DIC_FILTROS(@PathParam("id") Integer id) {
		FIN_DIC_FILTROS FIN_DIC_FILTROS = new FIN_DIC_FILTROS();
		FIN_DIC_FILTROS.setID(id);
		dao32.delete(FIN_DIC_FILTROS);
	}

	@PUT
	@Path("/updateFIN_DIC_FILTROS")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_FILTROS updateFIN_DIC_FILTROS(final FIN_DIC_FILTROS FIN_DIC_FILTROS) {
		FIN_DIC_FILTROS.setID(FIN_DIC_FILTROS.getID());
		return dao32.update(FIN_DIC_FILTROS);
	}

	@POST
	@Path("/GET_PAINEL_CONTROLO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> GET_PAINEL_CONTROLO(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String SEC_NUM = firstMap.get("SEC_NUM");
		String DATE1 = firstMap.get("DATE1");
		String DATE2 = firstMap.get("DATE2");
		String DATE3 = firstMap.get("DATE3");
		String DATE4 = firstMap.get("DATE4");
		String TIME1 = firstMap.get("TIME1");
		String TIME2 = firstMap.get("TIME2");
		String TIME3 = firstMap.get("TIME3");
		String TIME4 = firstMap.get("TIME4");
		String ESTADO = firstMap.get("ESTADO");
		String TEMPO_PROD_MENOR = firstMap.get("TEMPO_PROD_MENOR");
		String TEMPO_PROD_MAIOR = firstMap.get("TEMPO_PROD_MAIOR");
		String FUNC = firstMap.get("FUNC");
		String NUMERO_OF = firstMap.get("NUMERO_OF");
		String OPERACAO = firstMap.get("OPERACAO");
		String MAQUINA = firstMap.get("MAQUINA");
		String DESIGN_REF = firstMap.get("DESIGN_REF");
		String REF = firstMap.get("REF");
		String ETIQUETA = firstMap.get("ETIQUETA");
		String QTT = firstMap.get("QTT");
		String QTTMENOR = firstMap.get("QTTMENOR");
		String START = firstMap.get("START");
		String RACKS = firstMap.get("RACKS");
		String LOTE = firstMap.get("LOTE");
		String TIPO = firstMap.get("TIPO");
		String CARRO = firstMap.get("CARRO");
		String LINHA = firstMap.get("LINHA");

		/*
		 * if (SEC_NUM != null) SEC_NUM = "'" + SEC_NUM + "'"; if (DATE1 != null) DATE1
		 * = "'" + DATE1 + "'"; if (DATE2 != null) DATE2 = "'" + DATE2 + "'"; if (DATE3
		 * != null) DATE3 = "'" + DATE3 + "'"; if (DATE4 != null) DATE4 = "'" + DATE4 +
		 * "'"; if (TIME1 != null) TIME1 = "'" + TIME1 + "'"; if (TIME2 != null) TIME2 =
		 * "'" + TIME2 + "'"; if (TIME3 != null) TIME3 = "'" + TIME3 + "'"; if (TIME4 !=
		 * null) TIME4 = "'" + TIME4 + "'"; if (ESTADO != null) ESTADO = "'" + ESTADO +
		 * "'"; if (TEMPO_PROD_MENOR != null) TEMPO_PROD_MENOR = "'" + TEMPO_PROD_MENOR
		 * + "'"; if (TEMPO_PROD_MAIOR != null) TEMPO_PROD_MAIOR = "'" +
		 * TEMPO_PROD_MAIOR + "'"; if (FUNC != null) FUNC = "'" + FUNC + "'"; if
		 * (NUMERO_OF != null) NUMERO_OF = "'" + NUMERO_OF + "'"; if (OPERACAO != null)
		 * OPERACAO = "'" + OPERACAO + "'"; if (MAQUINA != null) MAQUINA = "'" + MAQUINA
		 * + "'"; if (DESIGN_REF != null) DESIGN_REF = "'" + DESIGN_REF + "'"; if (REF
		 * != null) REF = "'" + REF + "'"; if (ETIQUETA != null) ETIQUETA = "'" +
		 * ETIQUETA + "'"; if (QTT != null) QTT = "'" + QTT + "'"; if (QTTMENOR != null)
		 * QTTMENOR = "'" + QTTMENOR + "'"; if (START != null) START = "'" + START +
		 * "'"; if (RACKS != null) RACKS = "'" + RACKS + "'"; if (LOTE != null) LOTE =
		 * "'" + LOTE + "'"; if (TIPO != null) TIPO = "'" + TIPO + "'";
		 */

		Query query_folder = entityManager.createNativeQuery(
				"EXEC GET_PAINEL_CONTROLO :SEC_NUM ,:DATE1,:DATE2,:DATE3,:DATE4,:TIME1 ,:TIME2 ,:TIME3 ,:TIME4 ,:ESTADO ,:TEMPO_PROD_MENOR ,:TEMPO_PROD_MAIOR ,:FUNC ,:NUMERO_OF ,:OPERACAO ,:MAQUINA ,:DESIGN_REF ,:REF ,:ETIQUETA ,:QTT ,:QTTMENOR ,:START,:RACKS ,:LOTE,:TIPO,:CARRO,:LINHA ")
				.setParameter("SEC_NUM", SEC_NUM).setParameter("DATE1", DATE1).setParameter("DATE2", DATE2)
				.setParameter("DATE3", DATE3).setParameter("DATE4", DATE4).setParameter("TIME1", TIME1)
				.setParameter("TIME2", TIME2).setParameter("TIME3", TIME3).setParameter("TIME4", TIME4)
				.setParameter("ESTADO", ESTADO).setParameter("TEMPO_PROD_MENOR", TEMPO_PROD_MENOR)
				.setParameter("TEMPO_PROD_MAIOR", TEMPO_PROD_MAIOR).setParameter("FUNC", FUNC)
				.setParameter("NUMERO_OF", NUMERO_OF).setParameter("OPERACAO", OPERACAO)
				.setParameter("MAQUINA", MAQUINA).setParameter("DESIGN_REF", DESIGN_REF).setParameter("REF", REF)
				.setParameter("ETIQUETA", ETIQUETA).setParameter("QTT", QTT).setParameter("QTTMENOR", QTTMENOR)
				.setParameter("START", START).setParameter("RACKS", RACKS).setParameter("LOTE", LOTE)
				.setParameter("TIPO", TIPO).setParameter("CARRO", CARRO).setParameter("LINHA", LINHA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* RH_CANDIDATURAS */
	@POST
	@Path("/createRH_CANDIDATURAS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_CANDIDATURAS insertRH_CANDIDATURASA(final RH_CANDIDATURAS data) {
		RH_CANDIDATURAS result = dao33.create(data);
		return result;
	}

	@GET
	@Path("/getRH_CANDIDATURASSINCRO")
	@Produces("application/json")
	public int getRH_CANDIDATURASSINCRO() {
		Query query_folder = entityManager.createNativeQuery("EXEC ATUALIZAR_RH_CANDIDATURAS ");
		return query_folder.executeUpdate();
	}

	@GET
	@Path("/getRH_CANDIDATURAS")
	@Produces("application/json")
	public List<RH_CANDIDATURAS> getRH_CANDIDATURAS() {
		return dao33.getall();
	}

	@GET
	@Path("/getRH_CANDIDATURAS2")
	@Produces("application/json")
	public List<RH_CANDIDATURAS> getRH_CANDIDATURAS2() {
		return dao33.getall2();
	}

	@GET
	@Path("/getRH_CANDIDATURASbyid/{id}")
	@Produces("application/json")
	public List<RH_CANDIDATURAS> getRH_CANDIDATURAS(@PathParam("id") Integer id) {
		return dao33.getbyid(id);
	}

	@DELETE
	@Path("/deleteRH_CANDIDATURAS/{id}")
	public void deleteRH_CANDIDATURAS(@PathParam("id") Integer id) {
		RH_CANDIDATURAS RH_CANDIDATURAS = new RH_CANDIDATURAS();
		RH_CANDIDATURAS.setID(id);
		dao33.delete(RH_CANDIDATURAS);
	}

	@PUT
	@Path("/updateRH_CANDIDATURAS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_CANDIDATURAS updateRH_CANDIDATURAS(final RH_CANDIDATURAS RH_CANDIDATURAS) {
		RH_CANDIDATURAS.setID(RH_CANDIDATURAS.getID());
		return dao33.update(RH_CANDIDATURAS);
	}

	/************************************* MAN_DIC_FAMILIA_EQUIPAMENTOS */
	@POST
	@Path("/createMAN_DIC_FAMILIA_EQUIPAMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_FAMILIA_EQUIPAMENTOS insertMAN_DIC_FAMILIA_EQUIPAMENTOSA(final MAN_DIC_FAMILIA_EQUIPAMENTOS data) {
		return dao34.create(data);
	}

	@GET
	@Path("/getMAN_DIC_FAMILIA_EQUIPAMENTOS")
	@Produces("application/json")
	public List<MAN_DIC_FAMILIA_EQUIPAMENTOS> getMAN_DIC_FAMILIA_EQUIPAMENTOS() {
		return dao34.getall();
	}

	@DELETE
	@Path("/deleteMAN_DIC_FAMILIA_EQUIPAMENTOS/{id}")
	public void deleteMAN_DIC_FAMILIA_EQUIPAMENTOS(@PathParam("id") Integer id) {
		MAN_DIC_FAMILIA_EQUIPAMENTOS MAN_DIC_FAMILIA_EQUIPAMENTOS = new MAN_DIC_FAMILIA_EQUIPAMENTOS();
		MAN_DIC_FAMILIA_EQUIPAMENTOS.setID(id);
		dao34.delete(MAN_DIC_FAMILIA_EQUIPAMENTOS);
	}

	@PUT
	@Path("/updateMAN_DIC_FAMILIA_EQUIPAMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public MAN_DIC_FAMILIA_EQUIPAMENTOS updateMAN_DIC_FAMILIA_EQUIPAMENTOS(
			final MAN_DIC_FAMILIA_EQUIPAMENTOS MAN_DIC_FAMILIA_EQUIPAMENTOS) {
		MAN_DIC_FAMILIA_EQUIPAMENTOS.setID(MAN_DIC_FAMILIA_EQUIPAMENTOS.getID());
		return dao34.update(MAN_DIC_FAMILIA_EQUIPAMENTOS);

	}

	@POST
	@Path("/GET_DADOS_CARTELAS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> GET_DADOS_CARTELAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String LOTE = firstMap.get("LOTE");
		String ID = firstMap.get("ID");

		if (LOTE != null)
			LOTE = "'" + LOTE + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC PR_GET_DADOS_CARTELAS " + LOTE + "," + ID);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* PR_WINROBOT_USERS */
	@POST
	@Path("/createPR_WINROBOT_USERS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_WINROBOT_USERS insertPR_WINROBOT_USERSA(final PR_WINROBOT_USERS data) {
		return dao35.create(data);
	}

	@GET
	@Path("/getPR_WINROBOT_USERS")
	@Produces("application/json")
	public List<PR_WINROBOT_USERS> getPR_WINROBOT_USERS() {
		return dao35.getall();
	}

	@DELETE
	@Path("/deletePR_WINROBOT_USERS/{id}")
	public void deletePR_WINROBOT_USERS(@PathParam("id") Integer id) {
		PR_WINROBOT_USERS PR_WINROBOT_USERS = new PR_WINROBOT_USERS();
		PR_WINROBOT_USERS.setID(id);
		dao35.delete(PR_WINROBOT_USERS);
	}

	@PUT
	@Path("/updatePR_WINROBOT_USERS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_WINROBOT_USERS updatePR_WINROBOT_USERS(final PR_WINROBOT_USERS PR_WINROBOT_USERS) {
		PR_WINROBOT_USERS.setID(PR_WINROBOT_USERS.getID());
		return dao35.update(PR_WINROBOT_USERS);

	}

	@PUT
	@Path("/updateDADOSPR_WINROBOT_USERS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_WINROBOT_USERS updateDADOSPR_WINROBOT_USERS(final PR_WINROBOT_USERS PR_WINROBOT_USERS) {

		PR_WINROBOT_USERS result = PR_WINROBOT_USERS;

		String DATA_HORA_INICIO = (result.getDATA_HORA_INICIO() == null) ? null
				: "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getDATA_HORA_INICIO()) + "'";
		String DATA_HORA_FIM = (result.getDATA_HORA_FIM() == null) ? null
				: "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getDATA_HORA_FIM()) + "'";
		String DATA_INICIO_PREP = (result.getDATA_INICIO_PREP() == null) ? null
				: "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getDATA_INICIO_PREP()) + "'";
		String DATA_FIM_PREP = (result.getDATA_FIM_PREP() == null) ? null
				: "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getDATA_FIM_PREP()) + "'";
		String DATA_INICIO_EXEC = (result.getDATA_INICIO_EXEC() == null) ? null
				: "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getDATA_INICIO_EXEC()) + "'";
		String DATA_FIM_EXEC = (result.getDATA_FIM_EXEC() == null) ? null
				: "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getDATA_FIM_EXEC()) + "'";

		String NOME_UTZ = (result.getNOME_UTZ() == null) ? null : "'" + result.getNOME_UTZ() + "'";
		String ID_UTZ = (result.getID_UTZ() == null) ? null : "'" + result.getID_UTZ() + "'";
		String ID_CAB = (result.getID_CAB() == null) ? null : "'" + result.getID_CAB() + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC PR_ATUALIZA_USER " + result.getID() + "," + DATA_HORA_INICIO + ","
						+ DATA_HORA_FIM + "," + DATA_INICIO_PREP + "," + DATA_FIM_PREP + "," + DATA_INICIO_EXEC + ","
						+ DATA_FIM_EXEC + "," + result.getUTZ_MODIF() + "," + NOME_UTZ + "," + ID_UTZ + "," + ID_CAB);
		query_folder.executeUpdate();

		return result;
	}

	@POST
	@Path("/INSERT_PIN_WINROBOT_PROGRAM")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> INSERT_PIN_WINROBOT_PROGRAM(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String SUP_PROGRAM = firstMap.get("SUP_PROGRAM");
		String ID = firstMap.get("ID");

		if (SUP_PROGRAM != null)
			SUP_PROGRAM = "'" + SUP_PROGRAM + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC INSERT_PIN_WINROBOT_PROGRAM " + SUP_PROGRAM + "," + ID);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/REMOVER_PIN_WINROBOT_PROGRAM")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> REMOVER_PIN_WINROBOT_PROGRAM(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String SUP_PROGRAM = firstMap.get("SUP_PROGRAM");
		String ID = firstMap.get("ID");

		if (SUP_PROGRAM != null)
			SUP_PROGRAM = "'" + SUP_PROGRAM + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC REMOVER_PIN_WINROBOT_PROGRAM " + SUP_PROGRAM + "," + ID);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/INSERT_PIN_WINROBOT_RACKS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> INSERT_PIN_WINROBOT_RACKS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String RACK_CODE = firstMap.get("RACK_CODE");
		String ID = firstMap.get("ID");
		String USER = firstMap.get("USER");

		if (RACK_CODE != null)
			RACK_CODE = "'" + RACK_CODE + "'";

		if (USER != null)
			USER = "'" + USER + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC INSERT_PIN_WINROBOT_RACKS " + RACK_CODE + "," + ID + "," + USER);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/PR_WINROBOT_ATUALIZA_ETIQUETAS_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public int PR_WINROBOT_ATUALIZA_ETIQUETAS_LINHA(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID = firstMap.get("ID");

		Query query_folder = entityManager.createNativeQuery("EXEC PR_WINROBOT_ATUALIZA_ETIQUETAS_LINHA " + ID);

		int dados_folder = query_folder.executeUpdate();

		return dados_folder;
	}

	@POST
	@Path("/REMOVE_PIN_WINROBOT_RACKS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> REMOVE_PIN_WINROBOT_RACKS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String RACK_CODE = firstMap.get("RACK_CODE");
		String ID = firstMap.get("ID");
		String USER = firstMap.get("USER");

		if (RACK_CODE != null)
			RACK_CODE = "'" + RACK_CODE + "'";

		if (USER != null)
			USER = "'" + USER + "'";

		Query query_folder = entityManager
				.createNativeQuery("EXEC REMOVE_PIN_WINROBOT_RACKS  " + RACK_CODE + "," + ID + "," + USER);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/REMOVE_PIN_WINROBOT_ETIQUETA")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> REMOVE_PIN_WINROBOT_ETIQUETA(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ID = firstMap.get("ID");
		String ETIQUETA = firstMap.get("ETIQUETA");
		String INDEX_ARTICLE = firstMap.get("INDEX_ARTICLE");
		String INDEX_ETIQUETA = firstMap.get("INDEX_ETIQUETA");
		if (ETIQUETA != null)
			ETIQUETA = "'" + ETIQUETA + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC REMOVE_PIN_WINROBOT_ETIQUETA  " + ID + ","
				+ INDEX_ARTICLE + "," + INDEX_ETIQUETA + "," + ETIQUETA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/INSERT_PIN_WINROBOT_ETIQUETA")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> INSERT_PIN_WINROBOT_ETIQUETA(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ETIQUETA = firstMap.get("ETIQUETA");
		String ID = firstMap.get("ID");
		String INDEX_ARTICLE = firstMap.get("INDEX_ARTICLE");
		String INDEX_ETIQUETA = firstMap.get("INDEX_ETIQUETA");

		if (ETIQUETA != null)
			ETIQUETA = "'" + ETIQUETA + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC INSERT_PIN_WINROBOT_ETIQUETA " + ID + ","
				+ INDEX_ARTICLE + "," + INDEX_ETIQUETA + "," + ETIQUETA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* PIN_DIC_CORES_ACABAMENTOS */
	@POST
	@Path("/createPIN_DIC_CORES_ACABAMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_CORES_ACABAMENTOS insertPIN_DIC_CORES_ACABAMENTOS(final PIN_DIC_CORES_ACABAMENTOS data) {
		return dao36.create(data);
	}

	@GET
	@Path("/getPIN_DIC_CORES_ACABAMENTOSbyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_CORES_ACABAMENTOS> getPIN_DIC_CORES_ACABAMENTOSbyid(@PathParam("id") Integer id) {
		return dao36.getbyid(id);
	}

	@GET
	@Path("/getPIN_DIC_CORES_ACABAMENTOSbytipo/{tipo}")
	@Produces("application/json")
	public List<PIN_DIC_CORES_ACABAMENTOS> getPIN_DIC_CORES_ACABAMENTOSbytipo(@PathParam("id") String tipo) {
		return dao36.getbytipo(tipo);
	}

	@GET
	@Path("/getPIN_DIC_CORES_ACABAMENTOSbytipoAll")
	@Produces("application/json")
	public List<PIN_DIC_CORES_ACABAMENTOS> getPIN_DIC_CORES_ACABAMENTOSbytipoAll() {
		return dao36.getbytipoAll();
	}

	@GET
	@Path("/getPIN_DIC_CORES_ACABAMENTOSbytipoAll2")
	@Produces("application/json")
	public List<PIN_DIC_CORES_ACABAMENTOS> getPIN_DIC_CORES_ACABAMENTOSbytipoAll2() {
		return dao36.getbytipoAll2();
	}

	@GET
	@Path("/getPIN_DIC_CORES_ACABAMENTOS")
	@Produces("application/json")
	public List<PIN_DIC_CORES_ACABAMENTOS> getPIN_DIC_CORES_ACABAMENTOS() {
		return dao36.getall();
	}

	@GET
	@Path("/getPIN_DIC_CORES_ACABAMENTOS2")
	@Produces("application/json")
	public List<PIN_DIC_CORES_ACABAMENTOS> getPIN_DIC_CORES_ACABAMENTOS2() {
		return dao36.getall2();
	}

	@DELETE
	@Path("/deletePIN_DIC_CORES_ACABAMENTOS/{id}")
	public void deletePIN_DIC_CORES_ACABAMENTOS(@PathParam("id") Integer id) {
		PIN_DIC_CORES_ACABAMENTOS PIN_DIC_CORES_ACABAMENTOS = new PIN_DIC_CORES_ACABAMENTOS();
		PIN_DIC_CORES_ACABAMENTOS.setID(id);
		dao36.delete(PIN_DIC_CORES_ACABAMENTOS);
	}

	@PUT
	@Path("/updatePIN_DIC_CORES_ACABAMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_DIC_CORES_ACABAMENTOS updatePIN_DIC_CORES_ACABAMENTOS(
			final PIN_DIC_CORES_ACABAMENTOS PIN_DIC_CORES_ACABAMENTOS) {
		PIN_DIC_CORES_ACABAMENTOS.setID(PIN_DIC_CORES_ACABAMENTOS.getID());

		PIN_DIC_CORES_ACABAMENTOS _PIN_DIC_CORES_ACABAMENTOS = dao36.update(PIN_DIC_CORES_ACABAMENTOS);

		int query_folder = entityManager.createNativeQuery("EXEC PIN_ATUALIZA_RECEITAS_CORES_ACABAMENTOS :id")
				.setParameter("id", PIN_DIC_CORES_ACABAMENTOS.getID()).executeUpdate();

		return _PIN_DIC_CORES_ACABAMENTOS;
	}

	/************************************* PR_REGISTO_PINTURA */
	@POST
	@Path("/createPR_REGISTO_PINTURA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_REGISTO_PINTURA insertPR_REGISTO_PINTURA(final PR_REGISTO_PINTURA data) {
		return dao37.create(data);
	}

	@GET
	@Path("/getPR_REGISTO_PINTURAbyid/{id}")
	@Produces("application/json")
	public List<PR_REGISTO_PINTURA> getPR_REGISTO_PINTURAbyid(@PathParam("id") Integer id) {
		return dao37.getbyid(id);
	}

	@GET
	@Path("/getPR_REGISTO_PINTURA")
	@Produces("application/json")
	public List<PR_REGISTO_PINTURA> getPR_REGISTO_PINTURA() {
		return dao37.getall();
	}

	@DELETE
	@Path("/deletePR_REGISTO_PINTURA/{id}")
	public void deletePR_REGISTO_PINTURA(@PathParam("id") Integer id) {
		PR_REGISTO_PINTURA PR_REGISTO_PINTURA = new PR_REGISTO_PINTURA();
		PR_REGISTO_PINTURA.setID(id);
		dao37.delete(PR_REGISTO_PINTURA);
	}

	@PUT
	@Path("/updatePR_REGISTO_PINTURA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_REGISTO_PINTURA updatePR_REGISTO_PINTURA(final PR_REGISTO_PINTURA PR_REGISTO_PINTURA) {
		PR_REGISTO_PINTURA.setID(PR_REGISTO_PINTURA.getID());
		return dao37.update(PR_REGISTO_PINTURA);
	}

	@GET
	@Path("/getPIN_UPDATE_PLANEAMENTO/{id}")
	@Produces("application/json")
	public int getPIN_UPDATE_PLANEAMENTO(@PathParam("id") Integer id) {

		Query query_folder = entityManager
				.createNativeQuery(
						"UPDATE PIN_PLANEAMENTO_PINTURA set ESTADO = 'C',DATA_CONCLUSAO = GETDATE() where ID = :id")
				.setParameter("id", id);

		int dados_folder = query_folder.executeUpdate();

		return dados_folder;
	}

	@GET
	@Path("/getPIN_GET_PLANEAMENTO")
	@Produces("application/json")
	public List<Object[]> getPIN_GET_PLANEAMENTO() {

		Query query_folder = entityManager.createNativeQuery("EXEC PIN_GET_PLANEAMENTO");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/getPIN_GET_VALVULAS")
	@Produces("application/json")
	public List<Object[]> getPIN_GET_VALVULAS() {

		Query query_folder = entityManager.createNativeQuery("EXEC PIN_GET_VALVULAS");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getPIN_GET_VALVULAS_POTE")
	@Produces("application/json")
	public List<Object[]> getPIN_GET_VALVULAS_POTE(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String QUERY = firstMap.get("QUERY");
		String ID_POTE = firstMap.get("ID_POTE");

		Query query_folder = entityManager.createNativeQuery(
				" select  a.CODIGO,a.NOME,b.NOME as NOME_POTEa,a.ID from PIN_DIC_CORES_ACABAMENTOS a "
						+ " left join PIN_DIC_POTES b on  b.ID in (select value from string_split(a.ID_POTE,','))  "
						+ " where a.ATIVO = 1 and CODIGO like :codigo and b.ID = :id")
				.setParameter("codigo", "%" + QUERY + "%").setParameter("id", ID_POTE);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* PIN_MOV_UV_RADIATION */
	@POST
	@Path("/createPIN_MOV_UV_RADIATION")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_UV_RADIATION insertAB_DIC_ADITIVO(final PIN_MOV_UV_RADIATION data) {
		return dao38.create(data);
	}

	@GET
	@Path("/getPIN_MOV_UV_RADIATIONyid/{id}/{versao}")
	@Produces("application/json")
	public List<PIN_MOV_UV_RADIATION> getPIN_MOV_UV_RADIATIONyid(@PathParam("id") Integer id,
			@PathParam("versao") Integer versao) {
		return dao38.getbyid(id, versao);
	}

	@GET
	@Path("/getPIN_MOV_UV_RADIATION")
	@Produces("application/json")
	public List<PIN_MOV_UV_RADIATION> getPIN_MOV_UV_RADIATION() {
		return dao38.allEntries();
	}

	@GET
	@Path("/getPIN_MOV_UV_RADIATION2")
	@Produces("application/json")
	public List<PIN_MOV_UV_RADIATION> getPIN_MOV_UV_RADIATIONLINHA() {
		return dao38.getall();
	}

	@DELETE
	@Path("/deletePIN_MOV_UV_RADIATION/{id}")
	public void deletePIN_MOV_UV_RADIATION(@PathParam("id") Integer id) {
		PIN_MOV_UV_RADIATION PIN_MOV_UV_RADIATION = new PIN_MOV_UV_RADIATION();
		PIN_MOV_UV_RADIATION.setID(id);
		dao38.delete(PIN_MOV_UV_RADIATION);
	}

	@PUT
	@Path("/updatePIN_MOV_UV_RADIATION")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_MOV_UV_RADIATION updatePIN_MOV_UV_RADIATION(final PIN_MOV_UV_RADIATION PIN_MOV_UV_RADIATION) {
		PIN_MOV_UV_RADIATION.setID(PIN_MOV_UV_RADIATION.getID());
		return dao38.update(PIN_MOV_UV_RADIATION);
	}

	/************************************* PIN_PLANEAMENTO_PINTURA */
	@POST
	@Path("/createPIN_PLANEAMENTO_PINTURA")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_PLANEAMENTO_PINTURA insertAB_DIC_ADITIVO(final PIN_PLANEAMENTO_PINTURA data) {
		return dao39.create(data);
	}

	@GET
	@Path("/getPIN_PLANEAMENTO_PINTURAyid/{id}")
	@Produces("application/json")
	public List<PIN_PLANEAMENTO_PINTURA> getPIN_PLANEAMENTO_PINTURAyid(@PathParam("id") Integer id) {
		return dao39.getbyid(id);
	}

	/*
	 * @GET
	 * 
	 * @Path("/getPIN_PLANEAMENTO_PINTURA")
	 * 
	 * @Produces("application/json") public List<PIN_PLANEAMENTO_PINTURA>
	 * getPIN_PLANEAMENTO_PINTURA() { return dao39.allEntries(); }
	 */

	@GET
	@Path("/getPIN_PLANEAMENTO_PINTURA")
	@Produces("application/json")
	public List<PIN_PLANEAMENTO_PINTURA> getPIN_PLANEAMENTO_PINTURA() {
		return dao39.getall();
	}

	@DELETE
	@Path("/deletePIN_PLANEAMENTO_PINTURA/{id}")
	public void deletePIN_PLANEAMENTO_PINTURA(@PathParam("id") Integer id) {
		PIN_PLANEAMENTO_PINTURA PIN_PLANEAMENTO_PINTURA = new PIN_PLANEAMENTO_PINTURA();
		PIN_PLANEAMENTO_PINTURA.setID(id);
		dao39.delete(PIN_PLANEAMENTO_PINTURA);
	}

	@PUT
	@Path("/updatePIN_PLANEAMENTO_PINTURA")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_PLANEAMENTO_PINTURA updatePIN_PLANEAMENTO_PINTURA(
			final PIN_PLANEAMENTO_PINTURA PIN_PLANEAMENTO_PINTURA) {
		PIN_PLANEAMENTO_PINTURA.setID(PIN_PLANEAMENTO_PINTURA.getID());
		return dao39.update(PIN_PLANEAMENTO_PINTURA);
	}

	/************************************* PIN_PLANEAMENTO_PINTURA_LINHAS */
	@POST
	@Path("/createPIN_PLANEAMENTO_PINTURA_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_PLANEAMENTO_PINTURA_LINHAS insertAB_DIC_ADITIVO(final PIN_PLANEAMENTO_PINTURA_LINHAS data) {
		return dao40.create(data);
	}

	@GET
	@Path("/getPIN_PLANEAMENTO_PINTURA_LINHASyid/{id}")
	@Produces("application/json")
	public List<PIN_PLANEAMENTO_PINTURA_LINHAS> getPIN_PLANEAMENTO_PINTURA_LINHASyid(@PathParam("id") Integer id) {
		return dao40.getbyid(id);
	}

	@GET
	@Path("/getPIN_PLANEAMENTO_PINTURA_LINHAS")
	@Produces("application/json")
	public List<PIN_PLANEAMENTO_PINTURA_LINHAS> getPIN_PLANEAMENTO_PINTURA_LINHAS() {
		return dao40.allEntries();
	}

	@GET
	@Path("/getPIN_PLANEAMENTO_PINTURA_LINHAS2")
	@Produces("application/json")
	public List<PIN_PLANEAMENTO_PINTURA_LINHAS> getPIN_PLANEAMENTO_PINTURA_LINHASLINHA() {
		return dao40.getall();
	}

	@DELETE
	@Path("/deletePIN_PLANEAMENTO_PINTURA_LINHAS/{id}")
	public void deletePIN_PLANEAMENTO_PINTURA_LINHAS(@PathParam("id") Integer id) {
		PIN_PLANEAMENTO_PINTURA_LINHAS PIN_PLANEAMENTO_PINTURA_LINHAS = new PIN_PLANEAMENTO_PINTURA_LINHAS();
		PIN_PLANEAMENTO_PINTURA_LINHAS.setID(id);
		dao40.delete(PIN_PLANEAMENTO_PINTURA_LINHAS);
	}

	@PUT
	@Path("/updatePIN_PLANEAMENTO_PINTURA_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PIN_PLANEAMENTO_PINTURA_LINHAS updatePIN_PLANEAMENTO_PINTURA_LINHAS(
			final PIN_PLANEAMENTO_PINTURA_LINHAS PIN_PLANEAMENTO_PINTURA_LINHAS) {
		PIN_PLANEAMENTO_PINTURA_LINHAS.setID(PIN_PLANEAMENTO_PINTURA_LINHAS.getID());
		return dao40.update(PIN_PLANEAMENTO_PINTURA_LINHAS);
	}

	/************************************ DOC_DOCUMENTOS */

	@POST
	@Path("/createDOC_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public DOC_DOCUMENTOS insertDOC_DOCUMENTOS(final DOC_DOCUMENTOS data) {
		return dao41.create(data);
	}

	@GET
	@Path("/getDOC_DOCUMENTOS")
	@Produces("application/json")
	public List<DOC_DOCUMENTOS> getDOC_DOCUMENTOS() {
		return dao41.getall();
	}

	@GET
	@Path("/getDOC_DOCUMENTOSTipo/{id}")
	@Produces("application/json")
	public List<DOC_DOCUMENTOS> getDOC_DOCUMENTOSTipo(@PathParam("id") Integer id) {
		return dao41.getallTipo(id);
	}

	@GET
	@Path("/getDOC_DOCUMENTOSbyid/{id}")
	@Produces("application/json")
	public List<DOC_DOCUMENTOS> getDOC_DOCUMENTOSbyid(@PathParam("id") Integer id) {
		return dao41.getbyid(id);
	}

	@GET
	@Path("/getDOC_DOCUMENTOSbyLocalizacao/{id}")
	@Produces("application/json")
	public List<DOC_DOCUMENTOS> getDOC_DOCUMENTOSbyLocalizacao(@PathParam("id") Integer id) {
		return dao41.getbylocalizacao(id);
	}

	@PUT
	@Path("/updateDOC_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public DOC_DOCUMENTOS updateDOC_DOCUMENTOS(final DOC_DOCUMENTOS DOC_DOCUMENTOS) {
		DOC_DOCUMENTOS.setID(DOC_DOCUMENTOS.getID());
		return dao41.update(DOC_DOCUMENTOS);
	}

	@DELETE
	@Path("/deleteDOC_DOCUMENTOS/{id}")
	public void deleteDOC_DOCUMENTOS(@PathParam("id") Integer id) {
		DOC_DOCUMENTOS DOC_DOCUMENTOS = new DOC_DOCUMENTOS();
		DOC_DOCUMENTOS.setID(id);
		dao41.delete(DOC_DOCUMENTOS);
	}

	/************************************* DOC_DIC_TIPOS_DOCUMENTO_UTZ */
	@POST
	@Path("/createDOC_DIC_TIPOS_DOCUMENTO_UTZ")
	@Consumes("*/*")
	@Produces("application/json")
	public DOC_DIC_TIPOS_DOCUMENTO_UTZ insertDOC_DIC_TIPOS_DOCUMENTO_UTZA(final DOC_DIC_TIPOS_DOCUMENTO_UTZ data) {
		return dao42.create(data);
	}

	@GET
	@Path("/getDOC_DIC_TIPOS_DOCUMENTO_UTZ")
	@Produces("application/json")
	public List<DOC_DIC_TIPOS_DOCUMENTO_UTZ> getDOC_DIC_TIPOS_DOCUMENTO_UTZ() {
		return dao42.getall();
	}

	@GET
	@Path("/getDOC_DIC_TIPOS_DOCUMENTO_UTZbyUtilizadores/{id}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getDOC_DIC_TIPOS_DOCUMENTO_UTZbyUtilizadores(@PathParam("id") Integer id) {
		return dao42.getUtilizadores(id);
	}

	@GET
	@Path("/getDOC_DIC_TIPOS_DOCUMENTO_UTZbyidgrupo/{id}")
	@Produces("application/json")
	public List<DOC_DIC_TIPOS_DOCUMENTO_UTZ> getDOC_DIC_TIPOS_DOCUMENTO_UTZbyidgrupo(@PathParam("id") Integer id) {
		return dao42.getbyidgrupo(id);
	}

	@GET
	@Path("/getDOC_DIC_TIPOS_DOCUMENTO_UTZbyid/{id}")
	@Produces("application/json")
	public List<DOC_DIC_TIPOS_DOCUMENTO_UTZ> getDOC_DIC_TIPOS_DOCUMENTO_UTZbyid(@PathParam("id") Integer id) {
		return dao42.getbyid(id);
	}

	@DELETE
	@Path("/deleteDOC_DIC_TIPOS_DOCUMENTO_UTZ/{id}")
	public void deleteDOC_DIC_TIPOS_DOCUMENTO_UTZ(@PathParam("id") Integer id) {
		DOC_DIC_TIPOS_DOCUMENTO_UTZ DOC_DIC_TIPOS_DOCUMENTO_UTZ = new DOC_DIC_TIPOS_DOCUMENTO_UTZ();
		DOC_DIC_TIPOS_DOCUMENTO_UTZ.setID(id);
		dao42.delete(DOC_DIC_TIPOS_DOCUMENTO_UTZ);
	}

	@PUT
	@Path("/updateDOC_DIC_TIPOS_DOCUMENTO_UTZ")
	@Consumes("*/*")
	@Produces("application/json")
	public DOC_DIC_TIPOS_DOCUMENTO_UTZ updateDOC_DIC_TIPOS_DOCUMENTO_UTZ(
			final DOC_DIC_TIPOS_DOCUMENTO_UTZ DOC_DIC_TIPOS_DOCUMENTO_UTZ) {
		DOC_DIC_TIPOS_DOCUMENTO_UTZ.setID(DOC_DIC_TIPOS_DOCUMENTO_UTZ.getID());
		return dao42.update(DOC_DIC_TIPOS_DOCUMENTO_UTZ);
	}

	/************************************ DOC_DOCUMENTOS_VERSOES */

	@POST
	@Path("/createDOC_DOCUMENTOS_VERSOES")
	@Consumes("*/*")
	@Produces("application/json")
	public DOC_DOCUMENTOS_VERSOES insertDOC_DOCUMENTOS_VERSOES(final DOC_DOCUMENTOS_VERSOES data) {
		return dao43.create(data);
	}

	@GET
	@Path("/getDOC_DOCUMENTOS_VERSOES")
	@Produces("application/json")
	public List<DOC_DOCUMENTOS_VERSOES> getDOC_DOCUMENTOS_VERSOES() {
		return dao43.getall();
	}

	@GET
	@Path("/getDOC_DOCUMENTOS_VERSOESbyid/{id}")
	@Produces("application/json")
	public List<DOC_DOCUMENTOS_VERSOES> getDOC_DOCUMENTOS_VERSOESbyid(@PathParam("id") Integer id) {
		return dao43.getbyid(id);
	}

	@PUT
	@Path("/updateDOC_DOCUMENTOS_VERSOES")
	@Consumes("*/*")
	@Produces("application/json")
	public DOC_DOCUMENTOS_VERSOES updateDOC_DOCUMENTOS_VERSOES(final DOC_DOCUMENTOS_VERSOES DOC_DOCUMENTOS_VERSOES) {
		DOC_DOCUMENTOS_VERSOES.setID(DOC_DOCUMENTOS_VERSOES.getID());
		return dao43.update(DOC_DOCUMENTOS_VERSOES);
	}

	@DELETE
	@Path("/deleteDOC_DOCUMENTOS_VERSOES/{id}")
	public void deleteDOC_DOCUMENTOS_VERSOES(@PathParam("id") Integer id) {
		DOC_DOCUMENTOS_VERSOES DOC_DOCUMENTOS_VERSOES = new DOC_DOCUMENTOS_VERSOES();
		DOC_DOCUMENTOS_VERSOES.setID(id);
		dao43.delete(DOC_DOCUMENTOS_VERSOES);
	}

	@POST
	@Path("/RH_DADOS_CADASTRO_FUNCIONARIO")
	@Produces("application/json")
	public List<Object[]> RH_DADOS_CADASTRO_FUNCIONARIO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID_FUNCIONARIO = firstMap.get("ID_FUNCIONARIO");

		if (ID_FUNCIONARIO != null)
			ID_FUNCIONARIO = "'" + ID_FUNCIONARIO + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC RH_DADOS_CADASTRO_FUNCIONARIO " + ID_FUNCIONARIO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* RH_REGISTO_ACOES */

	@POST
	@Path("/createRH_REGISTO_ACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_REGISTO_ACOES insertRH_REGISTO_ACOESA(final RH_REGISTO_ACOES data) {
		return dao44.create(data);
	}

	@GET
	@Path("/getRH_REGISTO_ACOES")
	@Produces("application/json")
	public List<RH_REGISTO_ACOES> getRH_REGISTO_ACOES() {
		return dao44.getall();
	}

	@GET
	@Path("/getRH_REGISTO_ACOESbyid/{id}")
	@Produces("application/json")
	public List<RH_REGISTO_ACOES> getRH_REGISTO_ACOESbyip(@PathParam("id") String id) {
		return dao44.getbyid(id);
	}

	@GET
	@Path("/getRH_REGISTO_ACOESbyidFICHEIRO/{id}")
	@Produces("application/json")
	public List<RH_REGISTO_ACOES> getRH_REGISTO_ACOESbyidFICHEIRO(@PathParam("id") String id) {
		return dao44.getbyidFicheiro(id);
	}

	@DELETE
	@Path("/deleteRH_REGISTO_ACOES/{id}")
	public void deleteRH_REGISTO_ACOES(@PathParam("id") Integer id) {
		RH_REGISTO_ACOES RH_REGISTO_ACOES = new RH_REGISTO_ACOES();
		RH_REGISTO_ACOES.setID_ACAO(id);
		dao44.delete(RH_REGISTO_ACOES);
	}

	@PUT
	@Path("/updateRH_REGISTO_ACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_REGISTO_ACOES updateRH_REGISTO_ACOES(final RH_REGISTO_ACOES RH_REGISTO_ACOES) {
		RH_REGISTO_ACOES.setID_ACAO(RH_REGISTO_ACOES.getID_ACAO());
		return dao44.update(RH_REGISTO_ACOES);
	}

	/*******************************************
	 * RH_DIC_AREA_FORMACAO
	 *******************/
	@POST
	@Path("/createRH_DIC_AREA_FORMACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_AREA_FORMACAO insertARH_DIC_AREA_FORMACAO(final RH_DIC_AREA_FORMACAO data) {
		return dao45.create(data);
	}

	@GET
	@Path("/getRH_DIC_AREA_FORMACAObyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_AREA_FORMACAO> getRH_DIC_AREA_FORMACAObyid(@PathParam("id") Integer id) {
		return dao45.getbyid(id);
	}

	@GET
	@Path("/getRH_DIC_AREA_FORMACAO")
	@Produces("application/json")
	public List<RH_DIC_AREA_FORMACAO> getRH_DIC_AREA_FORMACAO() {
		return dao45.getall();
	}

	@DELETE
	@Path("/deleteRH_DIC_AREA_FORMACAO/{id}")
	public void deleteRH_DIC_AREA_FORMACAO(@PathParam("id") Integer id) {
		RH_DIC_AREA_FORMACAO RH_DIC_AREA_FORMACAO = new RH_DIC_AREA_FORMACAO();
		RH_DIC_AREA_FORMACAO.setID(id);
		dao45.delete(RH_DIC_AREA_FORMACAO);
	}

	@PUT
	@Path("/updateRH_DIC_AREA_FORMACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_AREA_FORMACAO updateRH_DIC_AREA_FORMACAO(final RH_DIC_AREA_FORMACAO RH_DIC_AREA_FORMACAO) {
		RH_DIC_AREA_FORMACAO.setID(RH_DIC_AREA_FORMACAO.getID());
		return dao45.update(RH_DIC_AREA_FORMACAO);
	}

	/*******************************************
	 * RH_DIC_CRITERIOS_AVALIACAO
	 *******************/
	@POST
	@Path("/createRH_DIC_CRITERIOS_AVALIACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_CRITERIOS_AVALIACAO insertARH_DIC_CRITERIOS_AVALIACAO(final RH_DIC_CRITERIOS_AVALIACAO data) {
		return dao46.create(data);
	}

	@GET
	@Path("/getRH_DIC_CRITERIOS_AVALIACAObyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_CRITERIOS_AVALIACAO> getRH_DIC_CRITERIOS_AVALIACAObyid(@PathParam("id") Integer id) {
		return dao46.getbyid(id);
	}

	@GET
	@Path("/getRH_DIC_CRITERIOS_AVALIACAO")
	@Produces("application/json")
	public List<RH_DIC_CRITERIOS_AVALIACAO> getRH_DIC_CRITERIOS_AVALIACAO() {
		return dao46.getall();
	}

	@DELETE
	@Path("/deleteRH_DIC_CRITERIOS_AVALIACAO/{id}")
	public void deleteRH_DIC_CRITERIOS_AVALIACAO(@PathParam("id") Integer id) {
		RH_DIC_CRITERIOS_AVALIACAO RH_DIC_CRITERIOS_AVALIACAO = new RH_DIC_CRITERIOS_AVALIACAO();
		RH_DIC_CRITERIOS_AVALIACAO.setID(id);
		dao46.delete(RH_DIC_CRITERIOS_AVALIACAO);
	}

	@PUT
	@Path("/updateRH_DIC_CRITERIOS_AVALIACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_CRITERIOS_AVALIACAO updateRH_DIC_CRITERIOS_AVALIACAO(
			final RH_DIC_CRITERIOS_AVALIACAO RH_DIC_CRITERIOS_AVALIACAO) {
		RH_DIC_CRITERIOS_AVALIACAO.setID(RH_DIC_CRITERIOS_AVALIACAO.getID());
		return dao46.update(RH_DIC_CRITERIOS_AVALIACAO);
	}

	/*******************************************
	 * RH_DIC_ENTIDADE_FORMADORA
	 *******************/
	@POST
	@Path("/createRH_DIC_ENTIDADE_FORMADORA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_ENTIDADE_FORMADORA insertARH_DIC_ENTIDADE_FORMADORA(final RH_DIC_ENTIDADE_FORMADORA data) {
		return dao47.create(data);
	}

	@GET
	@Path("/getRH_DIC_ENTIDADE_FORMADORAbyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_ENTIDADE_FORMADORA> getRH_DIC_ENTIDADE_FORMADORAbyid(@PathParam("id") Integer id) {
		return dao47.getbyid(id);
	}

	@GET
	@Path("/getRH_DIC_ENTIDADE_FORMADORA")
	@Produces("application/json")
	public List<RH_DIC_ENTIDADE_FORMADORA> getRH_DIC_ENTIDADE_FORMADORA() {
		return dao47.getall();
	}

	@DELETE
	@Path("/deleteRH_DIC_ENTIDADE_FORMADORA/{id}")
	public void deleteRH_DIC_ENTIDADE_FORMADORA(@PathParam("id") Integer id) {
		RH_DIC_ENTIDADE_FORMADORA RH_DIC_ENTIDADE_FORMADORA = new RH_DIC_ENTIDADE_FORMADORA();
		RH_DIC_ENTIDADE_FORMADORA.setID(id);
		dao47.delete(RH_DIC_ENTIDADE_FORMADORA);
	}

	@PUT
	@Path("/updateRH_DIC_ENTIDADE_FORMADORA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_ENTIDADE_FORMADORA updateRH_DIC_ENTIDADE_FORMADORA(
			final RH_DIC_ENTIDADE_FORMADORA RH_DIC_ENTIDADE_FORMADORA) {
		RH_DIC_ENTIDADE_FORMADORA.setID(RH_DIC_ENTIDADE_FORMADORA.getID());
		return dao47.update(RH_DIC_ENTIDADE_FORMADORA);
	}

	/************************************ RH_FORMACAO_DOCUMENTOS */

	@POST
	@Path("/createRH_FORMACAO_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_FORMACAO_DOCUMENTOS insertRH_FORMACAO_DOCUMENTOS(final RH_FORMACAO_DOCUMENTOS data) {
		return dao48.create(data);
	}

	@GET
	@Path("/getRH_FORMACAO_DOCUMENTOS")
	@Produces("application/json")
	public List<RH_FORMACAO_DOCUMENTOS> getRH_FORMACAO_DOCUMENTOS() {
		return dao48.getall();
	}

	@GET
	@Path("/getRH_FORMACAO_DOCUMENTOSbyid/{id}")
	@Produces("application/json")
	public List<RH_FORMACAO_DOCUMENTOS> getRH_FORMACAO_DOCUMENTOSbyid(@PathParam("id") Integer id) {
		return dao48.getbyid(id);
	}

	@PUT
	@Path("/updateRH_FORMACAO_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_FORMACAO_DOCUMENTOS updateRH_FORMACAO_DOCUMENTOS(final RH_FORMACAO_DOCUMENTOS RH_FORMACAO_DOCUMENTOS) {
		RH_FORMACAO_DOCUMENTOS.setID(RH_FORMACAO_DOCUMENTOS.getID());
		return dao48.update(RH_FORMACAO_DOCUMENTOS);
	}

	@GET
	@Path("/getRH_FORMACAO_DOCUMENTOSbyidEquipamento/{id}")
	@Produces("application/json")
	public List<RH_FORMACAO_DOCUMENTOS> getRH_FORMACAO_DOCUMENTOSbyidEquipamento(@PathParam("id") Integer id) {
		return dao48.getbyidEquipamento(id);
	}

	@GET
	@Path("/getRH_FORMACAO_DOCUMENTOSbyidEquipamento2/{id}")
	@Produces("application/json")
	public List<RH_FORMACAO_DOCUMENTOS> getRH_FORMACAO_DOCUMENTOSbyidEquipamento2(@PathParam("id") Integer id) {
		return dao48.getbyidEquipamento2(id);
	}

	@DELETE
	@Path("/deleteRH_FORMACAO_DOCUMENTOS/{id}")
	public void deleteRH_FORMACAO_DOCUMENTOS(@PathParam("id") Integer id) {
		RH_FORMACAO_DOCUMENTOS RH_FORMACAO_DOCUMENTOS = new RH_FORMACAO_DOCUMENTOS();
		RH_FORMACAO_DOCUMENTOS.setID(id);
		dao48.delete(RH_FORMACAO_DOCUMENTOS);
	}

	/******************************************* RH_FORMACAO *******************/
	@POST
	@Path("/createRH_FORMACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_FORMACAO insertARH_FORMACAO(final RH_FORMACAO data) {
		return dao49.create(data);
	}

	@GET
	@Path("/getRH_FORMACAObyid/{id}")
	@Produces("application/json")
	public List<RH_FORMACAO> getRH_FORMACAObyid(@PathParam("id") Integer id) {
		return dao49.getbyid(id);
	}

	@GET
	@Path("/getRH_FORMACAO")
	@Produces("application/json")
	public List<RH_FORMACAO> getRH_FORMACAO() {
		return dao49.getall();
	}

	@GET
	@Path("/getRH_FORMACAO2/{id}")
	@Produces("application/json")
	public List<RH_FORMACAO> getRH_FORMACAO2(@PathParam("id") String id) {
		return dao49.getall2(id);
	}

	@DELETE
	@Path("/deleteRH_FORMACAO/{id}")
	public void deleteRH_FORMACAO(@PathParam("id") Integer id) {
		RH_FORMACAO RH_FORMACAO = new RH_FORMACAO();
		RH_FORMACAO.setID(id);
		dao49.delete(RH_FORMACAO);
	}

	@PUT
	@Path("/updateRH_FORMACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_FORMACAO updateRH_FORMACAO(final RH_FORMACAO RH_FORMACAO) {
		RH_FORMACAO.setID(RH_FORMACAO.getID());
		return dao49.update(RH_FORMACAO);
	}

	@POST
	@Path("/RH_GET_FORMACOES_TOTAIS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_FORMACOES_TOTAIS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao49.RH_GET_FORMACOES_TOTAIS(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}

	@POST
	@Path("/RH_GET_FORMACOES_TERMINADAS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_FORMACOES_TERMINADAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao49.RH_GET_FORMACOES_TERMINADAS(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}

	@POST
	@Path("/RH_GET_FORMACOES_EM_PROGRESSO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_FORMACOES_EM_PROGRESSO(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao49.RH_GET_FORMACOES_EM_PROGRESSO(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}

	@POST
	@Path("/RH_GET_FORMACOES_POR_INICIAR")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_FORMACOES_POR_INICIAR(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao49.RH_GET_FORMACOES_POR_INICIAR(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}

	@POST
	@Path("/RH_GET_PROXIMAS_FORMACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_PROXIMAS_FORMACOES(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao49.RH_GET_PROXIMAS_FORMACOES(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}

	@POST
	@Path("/RH_GET_AVALIACAO_EFICACIA")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_AVALIACAO_EFICACIA(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao49.RH_GET_AVALIACAO_EFICACIA(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}
	
	@POST
	@Path("/RH_GET_MAIS_35_HORAS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_MAIS_35_HORAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao49.RH_GET_MAIS_35_HORAS(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}	

	@POST
	@Path("/RH_GET_FORMACOES_EXPIRADAS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_FORMACOES_EXPIRADAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao49.RH_GET_FORMACOES_EXPIRADAS(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}

	/*******************************************
	 * RH_FORMACAO_PARTICIPANTES
	 *******************/
	@POST
	@Path("/createRH_FORMACAO_PARTICIPANTES")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_FORMACAO_PARTICIPANTES insertARH_FORMACAO_PARTICIPANTES(final RH_FORMACAO_PARTICIPANTES data) {
		return dao50.create(data);
	}

	@GET
	@Path("/getRH_FORMACAO_PARTICIPANTESbyid/{id}")
	@Produces("application/json")
	public List<RH_FORMACAO_PARTICIPANTES> getRH_FORMACAO_PARTICIPANTESbyid(@PathParam("id") Integer id) {
		return dao50.getbyid(id);
	}

	@GET
	@Path("/getRH_FORMACAO_PARTICIPANTES")
	@Produces("application/json")
	public List<RH_FORMACAO_PARTICIPANTES> getRH_FORMACAO_PARTICIPANTES() {
		return dao50.getall();
	}

	@DELETE
	@Path("/deleteRH_FORMACAO_PARTICIPANTES/{id}")
	public void deleteRH_FORMACAO_PARTICIPANTES(@PathParam("id") Integer id) {
		RH_FORMACAO_PARTICIPANTES RH_FORMACAO_PARTICIPANTES = new RH_FORMACAO_PARTICIPANTES();
		RH_FORMACAO_PARTICIPANTES.setID(id);
		dao50.delete(RH_FORMACAO_PARTICIPANTES);
	}

	@PUT
	@Path("/updateRH_FORMACAO_PARTICIPANTES")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_FORMACAO_PARTICIPANTES updateRH_FORMACAO_PARTICIPANTES(
			final RH_FORMACAO_PARTICIPANTES RH_FORMACAO_PARTICIPANTES) {
		RH_FORMACAO_PARTICIPANTES.setID(RH_FORMACAO_PARTICIPANTES.getID());
		return dao50.update(RH_FORMACAO_PARTICIPANTES);
	}

	/************************************ RH_MEDICINA_TRABALHO_DOCUMENTOS */

	@POST
	@Path("/createRH_MEDICINA_TRABALHO_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_MEDICINA_TRABALHO_DOCUMENTOS insertRH_MEDICINA_TRABALHO_DOCUMENTOS(
			final RH_MEDICINA_TRABALHO_DOCUMENTOS data) {
		return dao51.create(data);
	}

	@GET
	@Path("/getRH_MEDICINA_TRABALHO_DOCUMENTOS")
	@Produces("application/json")
	public List<RH_MEDICINA_TRABALHO_DOCUMENTOS> getRH_MEDICINA_TRABALHO_DOCUMENTOS() {
		return dao51.getall();
	}

	@GET
	@Path("/getRH_MEDICINA_TRABALHO_DOCUMENTOSbyid/{id}")
	@Produces("application/json")
	public List<RH_MEDICINA_TRABALHO_DOCUMENTOS> getRH_MEDICINA_TRABALHO_DOCUMENTOSbyid(@PathParam("id") Integer id) {
		return dao51.getbyid(id);
	}

	@PUT
	@Path("/updateRH_MEDICINA_TRABALHO_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_MEDICINA_TRABALHO_DOCUMENTOS updateRH_MEDICINA_TRABALHO_DOCUMENTOS(
			final RH_MEDICINA_TRABALHO_DOCUMENTOS RH_MEDICINA_TRABALHO_DOCUMENTOS) {
		RH_MEDICINA_TRABALHO_DOCUMENTOS.setID(RH_MEDICINA_TRABALHO_DOCUMENTOS.getID());
		return dao51.update(RH_MEDICINA_TRABALHO_DOCUMENTOS);
	}

	@GET
	@Path("/getRH_MEDICINA_TRABALHO_DOCUMENTOSbyidEquipamento/{id}")
	@Produces("application/json")
	public List<RH_MEDICINA_TRABALHO_DOCUMENTOS> getRH_MEDICINA_TRABALHO_DOCUMENTOSbyidEquipamento(
			@PathParam("id") Integer id) {
		return dao51.getbyidEquipamento(id);
	}

	@GET
	@Path("/getRH_MEDICINA_TRABALHO_DOCUMENTOSbyidEquipamento2/{id}")
	@Produces("application/json")
	public List<RH_MEDICINA_TRABALHO_DOCUMENTOS> getRH_MEDICINA_TRABALHO_DOCUMENTOSbyidEquipamento2(
			@PathParam("id") Integer id) {
		return dao51.getbyidEquipamento2(id);
	}

	@DELETE
	@Path("/deleteRH_MEDICINA_TRABALHO_DOCUMENTOS/{id}")
	public void deleteRH_MEDICINA_TRABALHO_DOCUMENTOS(@PathParam("id") Integer id) {
		RH_MEDICINA_TRABALHO_DOCUMENTOS RH_MEDICINA_TRABALHO_DOCUMENTOS = new RH_MEDICINA_TRABALHO_DOCUMENTOS();
		RH_MEDICINA_TRABALHO_DOCUMENTOS.setID(id);
		dao51.delete(RH_MEDICINA_TRABALHO_DOCUMENTOS);
	}

	/******************************************** RH_MEDICINA_TRABALHO **/
	@POST
	@Path("/createRH_MEDICINA_TRABALHO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_MEDICINA_TRABALHO insertARH_MEDICINA_TRABALHO(final RH_MEDICINA_TRABALHO data) {
		return dao52.create(data);
	}

	@GET
	@Path("/getRH_MEDICINA_TRABALHObyid/{id}")
	@Produces("application/json")
	public List<RH_MEDICINA_TRABALHO> getRH_MEDICINA_TRABALHObyid(@PathParam("id") Integer id) {
		return dao52.getbyid(id);
	}
	

	@GET
	@Path("/getRH_FUNCIONARIO_DT_NASCIMENTO/{id}")
	@Produces("application/json")
	public List<Object> getRH_FUNCIONARIO_DT_NASCIMENTO(@PathParam("id") String id) {
		return dao52.getDataNascimento(id);
	}

	@GET
	@Path("/getRH_MEDICINA_TRABALHO")
	@Produces("application/json")
	public List<RH_MEDICINA_TRABALHO> getRH_MEDICINA_TRABALHO() {
		return dao52.getall();
	}

	@GET
	@Path("/getRH_MEDICINA_TRABALHO2/{id}")
	@Produces("application/json")
	public List<RH_MEDICINA_TRABALHO> getRH_MEDICINA_TRABALHO2(@PathParam("id") String id) {
		return dao52.getall2(id);
	}

	@DELETE
	@Path("/deleteRH_MEDICINA_TRABALHO/{id}")
	public void deleteRH_MEDICINA_TRABALHO(@PathParam("id") Integer id) {
		RH_MEDICINA_TRABALHO RH_MEDICINA_TRABALHO = new RH_MEDICINA_TRABALHO();
		RH_MEDICINA_TRABALHO.setID(id);
		dao52.delete(RH_MEDICINA_TRABALHO);
	}

	@PUT
	@Path("/updateRH_MEDICINA_TRABALHO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_MEDICINA_TRABALHO updateRH_MEDICINA_TRABALHO(final RH_MEDICINA_TRABALHO RH_MEDICINA_TRABALHO) {
		RH_MEDICINA_TRABALHO.setID(RH_MEDICINA_TRABALHO.getID());
		return dao52.update(RH_MEDICINA_TRABALHO);
	}

	@POST
	@Path("/RH_GET_MEDICINA_TOTAIS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_MEDICINA_TOTAIS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao52.RH_GET_MEDICINA_TOTAIS(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}

	@POST
	@Path("/RH_GET_MEDICINA_PROXIMAS_CONSULTAS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_MEDICINA_PROXIMAS_CONSULTAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao52.RH_GET_MEDICINA_PROXIMAS_CONSULTAS(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}

	@POST
	@Path("/RH_GET_MEDICINA_REALIZADAS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_MEDICINA_REALIZADAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao52.RH_GET_MEDICINA_REALIZADAS(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}

	@POST
	@Path("/RH_GET_MEDICINA_EXPIRADAS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> RH_GET_MEDICINA_EXPIRADAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String FUNCIONARIO = firstMap.get("FUNCIONARIO");

		if (DATA_INICIO != null)
			DATA_INICIO = "'" + DATA_INICIO + "'";
		if (DATA_FIM != null)
			DATA_FIM = "'" + DATA_FIM + "'";
		if (FUNCIONARIO != null)
			FUNCIONARIO = "'" + FUNCIONARIO + "'";

		List<Object[]> dados_folder = dao52.RH_GET_MEDICINA_EXPIRADAS(ANO, FUNCIONARIO, DATA_INICIO, DATA_FIM);

		return dados_folder;
	}

	/******************************************** RH_DIC_EXAMES **/
	@POST
	@Path("/createRH_DIC_EXAMES")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_EXAMES insertARH_DIC_EXAMES(final RH_DIC_EXAMES data) {
		return dao53.create(data);
	}

	@GET
	@Path("/getRH_DIC_EXAMESbyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_EXAMES> getRH_DIC_EXAMESbyid(@PathParam("id") Integer id) {
		return dao53.getbyid(id);
	}

	@GET
	@Path("/getRH_DIC_EXAMES")
	@Produces("application/json")
	public List<RH_DIC_EXAMES> getRH_DIC_EXAMES() {
		return dao53.getall();
	}

	@DELETE
	@Path("/deleteRH_DIC_EXAMES/{id}")
	public void deleteRH_DIC_EXAMES(@PathParam("id") Integer id) {
		RH_DIC_EXAMES RH_DIC_EXAMES = new RH_DIC_EXAMES();
		RH_DIC_EXAMES.setID(id);
		dao53.delete(RH_DIC_EXAMES);
	}

	@PUT
	@Path("/updateRH_DIC_EXAMES")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_EXAMES updateRH_DIC_EXAMES(final RH_DIC_EXAMES RH_DIC_EXAMES) {
		RH_DIC_EXAMES.setID(RH_DIC_EXAMES.getID());
		return dao53.update(RH_DIC_EXAMES);
	}

	/******************************************** RH_DIC_ENTIDADE_MEDICA **/
	@POST
	@Path("/createRH_DIC_ENTIDADE_MEDICA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_ENTIDADE_MEDICA insertARH_DIC_ENTIDADE_MEDICA(final RH_DIC_ENTIDADE_MEDICA data) {
		return dao54.create(data);
	}

	@GET
	@Path("/getRH_DIC_ENTIDADE_MEDICAbyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_ENTIDADE_MEDICA> getRH_DIC_ENTIDADE_MEDICAbyid(@PathParam("id") Integer id) {
		return dao54.getbyid(id);
	}

	@GET
	@Path("/getRH_DIC_ENTIDADE_MEDICA")
	@Produces("application/json")
	public List<RH_DIC_ENTIDADE_MEDICA> getRH_DIC_ENTIDADE_MEDICA() {
		return dao54.getall();
	}

	@DELETE
	@Path("/deleteRH_DIC_ENTIDADE_MEDICA/{id}")
	public void deleteRH_DIC_ENTIDADE_MEDICA(@PathParam("id") Integer id) {
		RH_DIC_ENTIDADE_MEDICA RH_DIC_ENTIDADE_MEDICA = new RH_DIC_ENTIDADE_MEDICA();
		RH_DIC_ENTIDADE_MEDICA.setID(id);
		dao54.delete(RH_DIC_ENTIDADE_MEDICA);
	}

	@PUT
	@Path("/updateRH_DIC_ENTIDADE_MEDICA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_ENTIDADE_MEDICA updateRH_DIC_ENTIDADE_MEDICA(final RH_DIC_ENTIDADE_MEDICA RH_DIC_ENTIDADE_MEDICA) {
		RH_DIC_ENTIDADE_MEDICA.setID(RH_DIC_ENTIDADE_MEDICA.getID());
		return dao54.update(RH_DIC_ENTIDADE_MEDICA);
	}

	/******************************************** RH_DIC_ENTIDADE_MEDICA_LOCAIS **/
	@POST
	@Path("/createRH_DIC_ENTIDADE_MEDICA_LOCAIS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_ENTIDADE_MEDICA_LOCAIS insertARH_DIC_ENTIDADE_MEDICA_LOCAIS(
			final RH_DIC_ENTIDADE_MEDICA_LOCAIS data) {
		return dao55.create(data);
	}

	@GET
	@Path("/getRH_DIC_ENTIDADE_MEDICA_LOCAISbyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_ENTIDADE_MEDICA_LOCAIS> getRH_DIC_ENTIDADE_MEDICA_LOCAISbyid(@PathParam("id") Integer id) {
		return dao55.getbyid(id);
	}

	@GET
	@Path("/getRH_DIC_ENTIDADE_MEDICA_LOCAIS")
	@Produces("application/json")
	public List<RH_DIC_ENTIDADE_MEDICA_LOCAIS> getRH_DIC_ENTIDADE_MEDICA_LOCAIS() {
		return dao55.getall();
	}

	@DELETE
	@Path("/deleteRH_DIC_ENTIDADE_MEDICA_LOCAIS/{id}")
	public void deleteRH_DIC_ENTIDADE_MEDICA_LOCAIS(@PathParam("id") Integer id) {
		RH_DIC_ENTIDADE_MEDICA_LOCAIS RH_DIC_ENTIDADE_MEDICA_LOCAIS = new RH_DIC_ENTIDADE_MEDICA_LOCAIS();
		RH_DIC_ENTIDADE_MEDICA_LOCAIS.setID(id);
		dao55.delete(RH_DIC_ENTIDADE_MEDICA_LOCAIS);
	}

	@PUT
	@Path("/updateRH_DIC_ENTIDADE_MEDICA_LOCAIS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_ENTIDADE_MEDICA_LOCAIS updateRH_DIC_ENTIDADE_MEDICA_LOCAIS(
			final RH_DIC_ENTIDADE_MEDICA_LOCAIS RH_DIC_ENTIDADE_MEDICA_LOCAIS) {
		RH_DIC_ENTIDADE_MEDICA_LOCAIS.setID(RH_DIC_ENTIDADE_MEDICA_LOCAIS.getID());
		return dao55.update(RH_DIC_ENTIDADE_MEDICA_LOCAIS);
	}

	/******************************************** RH_DIC_PERIOCIDADE_ALERTAS **/
	@POST
	@Path("/createRH_DIC_PERIOCIDADE_ALERTAS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_PERIOCIDADE_ALERTAS insertARH_DIC_PERIOCIDADE_ALERTAS(final RH_DIC_PERIOCIDADE_ALERTAS data) {
		return dao56.create(data);
	}

	@GET
	@Path("/getRH_DIC_PERIOCIDADE_ALERTASbyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_PERIOCIDADE_ALERTAS> getRH_DIC_PERIOCIDADE_ALERTASbyid(@PathParam("id") Integer id) {
		return dao56.getbyid(id);
	}

	@GET
	@Path("/getRH_DIC_PERIOCIDADE_ALERTAS")
	@Produces("application/json")
	public List<RH_DIC_PERIOCIDADE_ALERTAS> getRH_DIC_PERIOCIDADE_ALERTAS() {
		return dao56.getall();
	}

	@DELETE
	@Path("/deleteRH_DIC_PERIOCIDADE_ALERTAS/{id}")
	public void deleteRH_DIC_PERIOCIDADE_ALERTAS(@PathParam("id") Integer id) {
		RH_DIC_PERIOCIDADE_ALERTAS RH_DIC_PERIOCIDADE_ALERTAS = new RH_DIC_PERIOCIDADE_ALERTAS();
		RH_DIC_PERIOCIDADE_ALERTAS.setID(id);
		dao56.delete(RH_DIC_PERIOCIDADE_ALERTAS);
	}

	@PUT
	@Path("/updateRH_DIC_PERIOCIDADE_ALERTAS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_PERIOCIDADE_ALERTAS updateRH_DIC_PERIOCIDADE_ALERTAS(
			final RH_DIC_PERIOCIDADE_ALERTAS RH_DIC_PERIOCIDADE_ALERTAS) {
		RH_DIC_PERIOCIDADE_ALERTAS.setID(RH_DIC_PERIOCIDADE_ALERTAS.getID());
		return dao56.update(RH_DIC_PERIOCIDADE_ALERTAS);
	}

	/******************************************** GER_EVENTOS_DESTINATARIOS **/
	@POST
	@Path("/createGER_EVENTOS_DESTINATARIOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_EVENTOS_DESTINATARIOS insertAGER_EVENTOS_DESTINATARIOS(final GER_EVENTOS_DESTINATARIOS data) {
		return dao57.create(data);
	}

	@GET
	@Path("/getGER_EVENTOS_DESTINATARIOSbyid/{id}")
	@Produces("application/json")
	public List<GER_EVENTOS_DESTINATARIOS> getGER_EVENTOS_DESTINATARIOSbyid(@PathParam("id") Integer id) {
		return dao57.getbyId(id);
	}

	@DELETE
	@Path("/deleteGER_EVENTOS_DESTINATARIOS/{id}")
	public void deleteGER_EVENTOS_DESTINATARIOS(@PathParam("id") Integer id) {
		GER_EVENTOS_DESTINATARIOS GER_EVENTOS_DESTINATARIOS = new GER_EVENTOS_DESTINATARIOS();
		GER_EVENTOS_DESTINATARIOS.setID(id);
		dao57.delete(GER_EVENTOS_DESTINATARIOS);
	}

	@PUT
	@Path("/updateGER_EVENTOS_DESTINATARIOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_EVENTOS_DESTINATARIOS updateGER_EVENTOS_DESTINATARIOS(
			final GER_EVENTOS_DESTINATARIOS GER_EVENTOS_DESTINATARIOS) {
		GER_EVENTOS_DESTINATARIOS.setID(GER_EVENTOS_DESTINATARIOS.getID());
		return dao57.update(GER_EVENTOS_DESTINATARIOS);
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB */

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_SECCOES_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB insertPR_PLANEAMENTO_PRODUCAO_SECCOES_CABA(
			final PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB data) {
		return dao58.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_SECCOES_CAB")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB> getPR_PLANEAMENTO_PRODUCAO_SECCOES_CAB() {
		return dao58.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_SECCOES_CAB2")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB> getPR_PLANEAMENTO_PRODUCAO_SECCOES_CAB2() {
		return dao58.getall2();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_SECCOES_CABbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB> getPR_PLANEAMENTO_PRODUCAO_SECCOES_CABbyip(
			@PathParam("id") Integer id) {
		return dao58.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_SECCOES_CAB/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_SECCOES_CAB(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB = new PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB();
		PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB.setID_PLANEAMENTO_PRODUCAO_CAB(id);
		dao58.delete(PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_SECCOES_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB updatePR_PLANEAMENTO_PRODUCAO_SECCOES_CAB(
			final PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB) {
		PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB
				.setID_PLANEAMENTO_PRODUCAO_CAB(PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB.getID_PLANEAMENTO_PRODUCAO_CAB());
		return dao58.update(PR_PLANEAMENTO_PRODUCAO_SECCOES_CAB);
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS */

	@POST
	@Path("/getPR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS_FILTRO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> getPR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS_FILTRO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID_PLANEAMENTO_PRODUCAO_CAB = firstMap.get("ID_PLANEAMENTO_PRODUCAO_CAB");
		String FASE = firstMap.get("FASE");
		String COD_REF = firstMap.get("COD_REF");
		String ANTECEDENCIA = firstMap.get("ANTECEDENCIA");
		String CAIXA_CAPACIDADE = firstMap.get("CAIXA_CAPACIDADE");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS_FILTRO] "
				+ ID_PLANEAMENTO_PRODUCAO_CAB + ",'" + COD_REF + "','" + FASE + "'," + ANTECEDENCIA + "," + CAIXA_CAPACIDADE);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS_BASE_COR")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> getPR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS_BASE_COR() {

		Query query_folder = entityManager.createNativeQuery(
				"select distinct DESC_BASE_COR,'' as txt from PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS where  DESC_BASE_COR is not null ");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS insertPR_PLANEAMENTO_PRODUCAO_SECCOES_LINHASA(
			final PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS data) {
		return dao59.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS> getPR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS() {
		return dao59.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_SECCOES_LINHASbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS> getPR_PLANEAMENTO_PRODUCAO_SECCOES_LINHASbyip(
			@PathParam("id") Integer id) {
		return dao59.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS = new PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS();
		PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS.setID_PLANEAMENTO_PRODUCAO_LINHA(id);
		dao59.delete(PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS updatePR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS(
			final PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS) {
		PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS.setID_PLANEAMENTO_PRODUCAO_LINHA(
				PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS.getID_PLANEAMENTO_PRODUCAO_LINHA());
		return dao59.update(PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS_CAMPOS")
	@Consumes("*/*")
	@Produces("application/json")
	public boolean updatePR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS_CAMPOS(
			final PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS) {

		entityManager.createNativeQuery("UPDATE PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS SET UTZ_MODIF= "
				+ PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS.getUTZ_MODIF() + ", DATA_MODIF = GETDATE(),NUM_CAIXAS_PLANO = "
				+ PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS.getNUM_CAIXAS_PLANO()
				+ " WHERE ID_PLANEAMENTO_PRODUCAO_LINHA = "
				+ PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS.getID_PLANEAMENTO_PRODUCAO_LINHA() + "").executeUpdate();
		return true;
	}

	@POST
	@Path("/INSERT_PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS")
	@Produces("application/json")
	public List<Object[]> INSERT_PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID = firstMap.get("ID");

		Query query_folder = entityManager
				.createNativeQuery("EXEC [INSERT_PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS] " + ID);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/******************************************** RH_SUGESTOES **/
	@POST
	@Path("/createRH_SUGESTOES")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_SUGESTOES insertARH_SUGESTOES(final RH_SUGESTOES data) {
		return dao60.create(data);
	}

	@GET
	@Path("/getRH_SUGESTOESbyid/{id}")
	@Produces("application/json")
	public List<RH_SUGESTOES> getRH_SUGESTOESbyid(@PathParam("id") Integer id) {
		return dao60.getbyid(id);
	}

	@GET
	@Path("/getRH_SUGESTOES")
	@Produces("application/json")
	public List<RH_SUGESTOES> getRH_SUGESTOES() {
		return dao60.getall();
	}

	@DELETE
	@Path("/deleteRH_SUGESTOES/{id}")
	public void deleteRH_SUGESTOES(@PathParam("id") Integer id) {
		RH_SUGESTOES RH_SUGESTOES = new RH_SUGESTOES();
		RH_SUGESTOES.setID(id);
		dao60.delete(RH_SUGESTOES);
	}

	@PUT
	@Path("/updateRH_SUGESTOES")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_SUGESTOES updateRH_SUGESTOES(final RH_SUGESTOES RH_SUGESTOES) {
		RH_SUGESTOES.setID(RH_SUGESTOES.getID());
		return dao60.update(RH_SUGESTOES);
	}

	@POST
	@Path("/RH_SUGESTOES_DASHBOARD")
	@Produces("application/json")
	public List<Object[]> RH_SUGESTOES_DASHBOARD(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String MES = firstMap.get("MES");

		Query query_folder = entityManager.createNativeQuery("EXEC [RH_SUGESTOES_DASHBOARD] " + ANO + "," + MES);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/******************************************** RH_SUGESTOES_ATIVIDADE **/
	@POST
	@Path("/createRH_SUGESTOES_ATIVIDADE")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_SUGESTOES_ATIVIDADE insertARH_SUGESTOES_ATIVIDADE(final RH_SUGESTOES_ATIVIDADE data) {
		return dao61.create(data);
	}

	@GET
	@Path("/getRH_SUGESTOES_ATIVIDADEbyid/{id}")
	@Produces("application/json")
	public List<RH_SUGESTOES_ATIVIDADE> getRH_SUGESTOES_ATIVIDADEbyid(@PathParam("id") Integer id) {
		return dao61.getbyid(id);
	}

	@DELETE
	@Path("/deleteRH_SUGESTOES_ATIVIDADE/{id}")
	public void deleteRH_SUGESTOES_ATIVIDADE(@PathParam("id") Integer id) {
		RH_SUGESTOES_ATIVIDADE RH_SUGESTOES_ATIVIDADE = new RH_SUGESTOES_ATIVIDADE();
		RH_SUGESTOES_ATIVIDADE.setID(id);
		dao61.delete(RH_SUGESTOES_ATIVIDADE);
	}

	@PUT
	@Path("/updateRH_SUGESTOES_ATIVIDADE")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_SUGESTOES_ATIVIDADE updateRH_SUGESTOES_ATIVIDADE(final RH_SUGESTOES_ATIVIDADE RH_SUGESTOES_ATIVIDADE) {
		RH_SUGESTOES_ATIVIDADE.setID(RH_SUGESTOES_ATIVIDADE.getID());
		return dao61.update(RH_SUGESTOES_ATIVIDADE);
	}

	/******************************************** RH_DADOS_FUNCIONARIO **/
	@POST
	@Path("/createRH_DADOS_FUNCIONARIO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DADOS_FUNCIONARIO insertARH_DADOS_FUNCIONARIO(final RH_DADOS_FUNCIONARIO data) {
		return dao62.create(data);
	}

	@GET
	@Path("/getRH_DADOS_FUNCIONARIObyid/{id}")
	@Produces("application/json")
	public List<RH_DADOS_FUNCIONARIO> getRH_DADOS_FUNCIONARIObyid(@PathParam("id") String id) {
		return dao62.getbyid(id);
	}

	@GET
	@Path("/getRH_DADOS_FUNCIONARIOProximasConsultas/{id}")
	@Produces("application/json")
	public List<RH_DADOS_FUNCIONARIO> getRH_DADOS_FUNCIONARIOProximasConsultas(@PathParam("id") Integer id) {
		return dao62.getProximasConsultas(id);
	}

	@GET
	@Path("/getRH_DADOS_FUNCIONARIO")
	@Produces("application/json")
	public List<RH_DADOS_FUNCIONARIO> getRH_DADOS_FUNCIONARIO() {
		return dao62.getall();
	}

	@DELETE
	@Path("/deleteRH_DADOS_FUNCIONARIO/{id}")
	public void deleteRH_DADOS_FUNCIONARIO(@PathParam("id") Integer id) {
		RH_DADOS_FUNCIONARIO RH_DADOS_FUNCIONARIO = new RH_DADOS_FUNCIONARIO();
		RH_DADOS_FUNCIONARIO.setID(id);
		dao62.delete(RH_DADOS_FUNCIONARIO);
	}

	@PUT
	@Path("/updateRH_DADOS_FUNCIONARIO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DADOS_FUNCIONARIO updateRH_DADOS_FUNCIONARIO(final RH_DADOS_FUNCIONARIO RH_DADOS_FUNCIONARIO) {
		RH_DADOS_FUNCIONARIO.setID(RH_DADOS_FUNCIONARIO.getID());
		return dao62.update(RH_DADOS_FUNCIONARIO);
	}

	@POST
	@Path("/RH_GET_DADOS_ESIGEMP")
	@Produces("application/json")
	public List<Object[]> RH_GET_DADOS_ESIGEMP(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String TABLE = firstMap.get("TABLE");
		String SEARCH = firstMap.get("SEARCH");

		if (SEARCH != null)
			SEARCH = "'" + SEARCH + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC [RH_GET_DADOS_ESIGEMP] '" + TABLE + "'," + SEARCH);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************ RH_SUGESTOES_DOCUMENTOS */

	@POST
	@Path("/createRH_SUGESTOES_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_SUGESTOES_DOCUMENTOS insertRH_SUGESTOES_DOCUMENTOS(final RH_SUGESTOES_DOCUMENTOS data) {
		return dao63.create(data);
	}

	@GET
	@Path("/getRH_SUGESTOES_DOCUMENTOS")
	@Produces("application/json")
	public List<RH_SUGESTOES_DOCUMENTOS> getRH_SUGESTOES_DOCUMENTOS() {
		return dao63.getall();
	}

	@GET
	@Path("/getRH_SUGESTOES_DOCUMENTOSbyid/{id}")
	@Produces("application/json")
	public List<RH_SUGESTOES_DOCUMENTOS> getRH_SUGESTOES_DOCUMENTOSbyid(@PathParam("id") Integer id) {
		return dao63.getbyid(id);
	}

	@PUT
	@Path("/updateRH_SUGESTOES_DOCUMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_SUGESTOES_DOCUMENTOS updateRH_SUGESTOES_DOCUMENTOS(
			final RH_SUGESTOES_DOCUMENTOS RH_SUGESTOES_DOCUMENTOS) {
		RH_SUGESTOES_DOCUMENTOS.setID(RH_SUGESTOES_DOCUMENTOS.getID());
		return dao63.update(RH_SUGESTOES_DOCUMENTOS);
	}

	@GET
	@Path("/getRH_SUGESTOES_DOCUMENTOSbyidFicheiro/{id}")
	@Produces("application/json")
	public List<RH_SUGESTOES_DOCUMENTOS> getRH_SUGESTOES_DOCUMENTOSbyidFicheiro(@PathParam("id") Integer id) {
		return dao63.getbyidFicheiro(id);
	}

	@GET
	@Path("/getRH_SUGESTOES_DOCUMENTOSbyidFicheiro2/{id}")
	@Produces("application/json")
	public List<RH_SUGESTOES_DOCUMENTOS> getRH_SUGESTOES_DOCUMENTOSbyidFicheiro2(@PathParam("id") Integer id) {
		return dao63.getbyidFicheiro2(id);
	}

	@DELETE
	@Path("/deleteRH_SUGESTOES_DOCUMENTOS/{id}")
	public void deleteRH_SUGESTOES_DOCUMENTOS(@PathParam("id") Integer id) {
		RH_SUGESTOES_DOCUMENTOS RH_SUGESTOES_DOCUMENTOS = new RH_SUGESTOES_DOCUMENTOS();
		RH_SUGESTOES_DOCUMENTOS.setID(id);
		dao63.delete(RH_SUGESTOES_DOCUMENTOS);
	}
	
	
	
	@POST
	@Path("/COMU_GET_DOCUMENTOS")
	@Produces("application/json")
	public List<Object[]> COMU_GET_DOCUMENTOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IP = firstMap.get("IP_POSTO"); 

		if (IP != null)
			IP = "'" + IP + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC [COMU_GET_DOCUMENTOS]  " + IP);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}
	
	@POST
	@Path("/COMU_GET_TICKERS")
	@Produces("application/json")
	public List<Object[]> COMU_GET_TICKERS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IP = firstMap.get("IP_POSTO"); 

		if (IP != null)
			IP = "'" + IP + "'";

		Query query_folder = entityManager.createNativeQuery("EXEC [COMU_GET_TICKERS]  " + IP);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}
	/************************************* PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB */

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB insertPR_PLANEAMENTO_PRODUCAO_OPERACOES_CABA(
			final PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB data) {
		return dao64.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB> getPR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB() {
		return dao64.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB2")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB> getPR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB2() {
		return dao64.getall2();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_OPERACOES_CABbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB> getPR_PLANEAMENTO_PRODUCAO_OPERACOES_CABbyip(
			@PathParam("id") Integer id) {
		return dao64.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB = new PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB();
		PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB.setID_PLANEAMENTO_PRODUCAO_CAB(id);
		dao64.delete(PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB updatePR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB(
			final PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB) {
		PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB
				.setID_PLANEAMENTO_PRODUCAO_CAB(PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB.getID_PLANEAMENTO_PRODUCAO_CAB());
		return dao64.update(PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB);
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS */

	@POST
	@Path("/getPR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS_FILTRO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> getPR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS_FILTRO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID_PLANEAMENTO_PRODUCAO_CAB = firstMap.get("ID_PLANEAMENTO_PRODUCAO_CAB");
		String FASE = firstMap.get("FASE");
		String COD_REF = firstMap.get("COD_REF");
		String MOLDE = firstMap.get("MOLDE");
		String MAQUINA = firstMap.get("MAQUINA");
		String ANTECEDENCIA = firstMap.get("ANTECEDENCIA");
		String CAIXA_CAPACIDADE = firstMap.get("CAIXA_CAPACIDADE");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS_FILTRO] "
				+ ID_PLANEAMENTO_PRODUCAO_CAB + ",'" + COD_REF + "','" + FASE + "','" + MOLDE + "','" + MAQUINA + "',"
				+ ANTECEDENCIA + "," + CAIXA_CAPACIDADE);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS_BASE_COR")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> getPR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS_BASE_COR() {

		Query query_folder = entityManager.createNativeQuery(
				"select distinct DESC_BASE_COR,'' as txt from PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS where  DESC_BASE_COR is not null ");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS insertPR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHASA(
			final PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS data) {
		return dao65.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS> getPR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS() {
		return dao65.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHASbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS> getPR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHASbyip(
			@PathParam("id") Integer id) {
		return dao65.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS = new PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS();
		PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS.setID_PLANEAMENTO_PRODUCAO_LINHA(id);
		dao65.delete(PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS updatePR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS(
			final PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS) {
		PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS.setID_PLANEAMENTO_PRODUCAO_LINHA(
				PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS.getID_PLANEAMENTO_PRODUCAO_LINHA());
		return dao65.update(PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS_CAMPOS")
	@Consumes("*/*")
	@Produces("application/json")
	public boolean updatePR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS_CAMPOS(
			final PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS) {

		entityManager.createNativeQuery("UPDATE PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS SET UTZ_MODIF= "
				+ PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS.getUTZ_MODIF() + ", DATA_MODIF = GETDATE(),NUM_CAIXAS_PLANO = "
				+ PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS.getNUM_CAIXAS_PLANO()
				+ " WHERE ID_PLANEAMENTO_PRODUCAO_LINHA = "
				+ PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS.getID_PLANEAMENTO_PRODUCAO_LINHA() + "").executeUpdate();
		return true;
	}

	@POST
	@Path("/INSERT_PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS")
	@Produces("application/json")
	public List<Object[]> INSERT_PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID = firstMap.get("ID");

		Query query_folder = entityManager
				.createNativeQuery("EXEC [INSERT_PR_PLANEAMENTO_PRODUCAO_OPERACOES_LINHAS] " + ID);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}
	
	
	/************************************* COMU_FICHEIROS */

	@POST
	@Path("/createCOMU_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public COMU_FICHEIROS insertCOMU_FICHEIROSA(
			final COMU_FICHEIROS data) {
		return dao66.create(data);
	}

	@GET
	@Path("/getCOMU_FICHEIROS")
	@Produces("application/json")
	public List<COMU_FICHEIROS> getCOMU_FICHEIROS() {
		return dao66.getall();
	}
 
	@GET
	@Path("/getCOMU_FICHEIROSbyid/{id}")
	@Produces("application/json")
	public List<COMU_FICHEIROS> getCOMU_FICHEIROSbyip(
			@PathParam("id") Integer id) {
		return dao66.getbyid(id);
	}

	@DELETE
	@Path("/deleteCOMU_FICHEIROS/{id}")
	public void deleteCOMU_FICHEIROS(@PathParam("id") Integer id) {
		COMU_FICHEIROS COMU_FICHEIROS = new COMU_FICHEIROS();
		COMU_FICHEIROS.setID(id);
		dao66.delete(COMU_FICHEIROS);
	}

	@PUT
	@Path("/updateCOMU_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public COMU_FICHEIROS updateCOMU_FICHEIROS(
			final COMU_FICHEIROS COMU_FICHEIROS) {
		COMU_FICHEIROS
				.setID(COMU_FICHEIROS.getID());
		return dao66.update(COMU_FICHEIROS);
	}
	
	/************************************* COMU_TICKERS */

	@POST
	@Path("/createCOMU_TICKERS")
	@Consumes("*/*")
	@Produces("application/json")
	public COMU_TICKERS insertCOMU_TICKERSA(
			final COMU_TICKERS data) {
		return dao67.create(data);
	}

	@GET
	@Path("/getCOMU_TICKERS")
	@Produces("application/json")
	public List<COMU_TICKERS> getCOMU_TICKERS() {
		return dao67.getall();
	}
 
	@GET
	@Path("/getCOMU_TICKERSbyid/{id}")
	@Produces("application/json")
	public List<COMU_TICKERS> getCOMU_TICKERSbyip(
			@PathParam("id") Integer id) {
		return dao67.getbyid(id);
	}

	@DELETE
	@Path("/deleteCOMU_TICKERS/{id}")
	public void deleteCOMU_TICKERS(@PathParam("id") Integer id) {
		COMU_TICKERS COMU_TICKERS = new COMU_TICKERS();
		COMU_TICKERS.setID(id);
		dao67.delete(COMU_TICKERS);
	}

	@PUT
	@Path("/updateCOMU_TICKERS")
	@Consumes("*/*")
	@Produces("application/json")
	public COMU_TICKERS updateCOMU_TICKERS(
			final COMU_TICKERS COMU_TICKERS) {
		COMU_TICKERS
				.setID(COMU_TICKERS.getID());
		return dao67.update(COMU_TICKERS);
	}
	
	/************************************* COMU_DIC_CATEGORIAS */

	@POST
	@Path("/createCOMU_DIC_CATEGORIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public COMU_DIC_CATEGORIAS insertCOMU_DIC_CATEGORIASA(
			final COMU_DIC_CATEGORIAS data) {
		return dao68.create(data);
	}

	@GET
	@Path("/getCOMU_DIC_CATEGORIAS")
	@Produces("application/json")
	public List<COMU_DIC_CATEGORIAS> getCOMU_DIC_CATEGORIAS() {
		return dao68.getall();
	}
 
	@GET
	@Path("/getCOMU_DIC_CATEGORIASbyid/{id}")
	@Produces("application/json")
	public List<COMU_DIC_CATEGORIAS> getCOMU_DIC_CATEGORIASbyip(
			@PathParam("id") Integer id) {
		return dao68.getbyid(id);
	}

	@DELETE
	@Path("/deleteCOMU_DIC_CATEGORIAS/{id}")
	public void deleteCOMU_DIC_CATEGORIAS(@PathParam("id") Integer id) {
		COMU_DIC_CATEGORIAS COMU_DIC_CATEGORIAS = new COMU_DIC_CATEGORIAS();
		COMU_DIC_CATEGORIAS.setID(id);
		dao68.delete(COMU_DIC_CATEGORIAS);
	}

	@PUT
	@Path("/updateCOMU_DIC_CATEGORIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public COMU_DIC_CATEGORIAS updateCOMU_DIC_CATEGORIAS(
			final COMU_DIC_CATEGORIAS COMU_DIC_CATEGORIAS) {
		COMU_DIC_CATEGORIAS
				.setID(COMU_DIC_CATEGORIAS.getID());
		return dao68.update(COMU_DIC_CATEGORIAS);
	}
}
