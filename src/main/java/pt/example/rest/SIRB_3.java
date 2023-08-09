package pt.example.rest;

import java.sql.SQLException;
import java.util.ArrayList;
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
import pt.example.dao.COM_BUDGETSDao;
import pt.example.dao.COM_BUDGETS_ANALISESDao;
import pt.example.dao.COM_BUDGETS_LINHASDao;
import pt.example.dao.COM_CUSTOMERS_GROUPSDao;
import pt.example.dao.COM_CUSTOMER_GROUPSDao;
import pt.example.dao.GER_CONF_CONSUMOS_SILVERDao;
import pt.example.dao.GER_CONF_CONSUMOS_SILVER_OFDao;
import pt.example.dao.GER_INFO_PAGINASDao;
import pt.example.dao.MAN_DIC_ARTIGOS_TIPOLOGIADao;
import pt.example.dao.PIN_DIC_ARMAZEMDao;
import pt.example.dao.PIN_DIC_CABINESDao;
import pt.example.dao.PIN_DIC_POTESDao;
import pt.example.dao.PIN_DIC_PRODUTOSDao;
import pt.example.dao.PIN_DIC_PRODUTOS_RELACIONADOSDao;
import pt.example.dao.PIN_DIC_TIPO_ACABAMENTODao;
import pt.example.dao.PIN_MOV_PREPARACAODao;
import pt.example.dao.PIN_MOV_PREPARACAO_CABDao;
import pt.example.dao.PIN_MOV_PREPARACAO_ETIQDao;
import pt.example.dao.PIN_MOV_PREPARACAO_LINHADao;
import pt.example.entity.COM_BUDGETS;
import pt.example.entity.COM_BUDGETS_ANALISES;
import pt.example.entity.COM_BUDGETS_LINHAS;
import pt.example.entity.COM_CUSTOMERS_GROUPS;
import pt.example.entity.COM_CUSTOMER_GROUPS;
import pt.example.entity.GER_CONF_CONSUMOS_SILVER;
import pt.example.entity.GER_CONF_CONSUMOS_SILVER_OF;
import pt.example.entity.GER_INFO_PAGINAS;
import pt.example.entity.MAN_DIC_ARTIGOS_TIPOLOGIA;
import pt.example.entity.PIN_DIC_ARMAZEM;
import pt.example.entity.PIN_DIC_CABINES;
import pt.example.entity.PIN_DIC_POTES;
import pt.example.entity.PIN_DIC_PRODUTOS;
import pt.example.entity.PIN_DIC_PRODUTOS_RELACIONADOS;
import pt.example.entity.PIN_DIC_TIPO_ACABAMENTO;
import pt.example.entity.PIN_MOV_PREPARACAO;
import pt.example.entity.PIN_MOV_PREPARACAO_CAB;
import pt.example.entity.PIN_MOV_PREPARACAO_ETIQ;
import pt.example.entity.PIN_MOV_PREPARACAO_LINHA;

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
	@Path("/getPIN_DIC_POTESbyid/{id}")
	@Produces("application/json")
	public List<PIN_DIC_POTES> getPIN_DIC_POTESvbyid_tina(@PathParam("id") Integer id) {
		return dao13.getbyid(id, 0);
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
			@PathParam("classif") String classif, final ArrayList<String> query,
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
			@PathParam("classif") String classif, final ArrayList<String> query,
			@PathParam("idpote") Integer idpote) {
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

	@GET
	@Path("/getPIN_MOV_PREPARACAO_CABbyid_banho/{idpote}/{inicio}/{fim}/{id_man}/{classif}")
	@Produces("application/json")
	public List<PIN_MOV_PREPARACAO_CAB> getPIN_MOV_PREPARACAO_CABbyid_banho(@PathParam("idpote") Integer idpote,
			@PathParam("inicio") Integer inicio, @PathParam("fim") Integer fim, @PathParam("id_man") Integer id_man,
			@PathParam("classif") String classif) {
		return dao17.getbyidpote(idpote, inicio, fim, id_man, classif);
	}

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
	public List<PIN_MOV_PREPARACAO_LINHA> getPIN_MOV_PREPARACAO_LINHAbyidPREPARACAOcabtotal(@PathParam("id") Integer id) {
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
			Query query = entityManager.createNativeQuery("select ID_PREPARACAO_LIN,ID_MOV_MANU_ETIQUETA "
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
	public PIN_MOV_PREPARACAO_ETIQ updatePIN_MOV_PREPARACAO_ETIQ(final PIN_MOV_PREPARACAO_ETIQ PIN_MOV_PREPARACAO_ETIQ) {
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
}
