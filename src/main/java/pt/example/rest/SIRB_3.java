package pt.example.rest;

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

import pt.example.dao.COM_BUDGETSDao;
import pt.example.dao.COM_BUDGETS_ANALISESDao;
import pt.example.dao.COM_BUDGETS_LINHASDao;
import pt.example.dao.COM_CUSTOMER_GROUPSDao;
import pt.example.dao.GER_CONF_CONSUMOS_SILVERDao;
import pt.example.dao.GER_CONF_CONSUMOS_SILVER_OFDao;
import pt.example.dao.GER_INFO_PAGINASDao;
import pt.example.dao.MAN_DIC_ARTIGOS_TIPOLOGIADao;
import pt.example.entity.COM_BUDGETS;
import pt.example.entity.COM_BUDGETS_ANALISES;
import pt.example.entity.COM_BUDGETS_LINHAS;
import pt.example.entity.COM_CUSTOMER_GROUPS;
import pt.example.entity.GER_CONF_CONSUMOS_SILVER;
import pt.example.entity.GER_CONF_CONSUMOS_SILVER_OF;
import pt.example.entity.GER_INFO_PAGINAS;
import pt.example.entity.MAN_DIC_ARTIGOS_TIPOLOGIA; 

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
		return dao6.create(data);
	}

	@GET
	@Path("/getCOM_BUDGETS")
	@Produces("application/json")
	public List<COM_BUDGETS> getCOM_BUDGETS() {
		return dao6.getall();
	}

	@GET
	@Path("/getCOM_BUDGETSbyid/{id}")
	@Produces("application/json")
	public List<COM_BUDGETS> getCOM_BUDGETSbyid(@PathParam("id") Integer id) {
		return dao6.getbyid(id);
	}

	@PUT
	@Path("/updateCOM_BUDGETS")
	@Consumes("*/*")
	@Produces("application/json")
	public COM_BUDGETS updateCOM_BUDGETS(final COM_BUDGETS COM_BUDGETS) {
		COM_BUDGETS.setID(COM_BUDGETS.getID());
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
	@Path("/getCOM_BUDGETSbyverificaseexiste/{id}/{ano}")
	@Produces("application/json")
	public List<COM_BUDGETS> getCOM_BUDGETSbyverificaseexiste(@PathParam("id") Integer id, @PathParam("ano") Integer ano) {
		return dao6.verificaseexiste(id, ano);
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
	public List<COM_BUDGETS_LINHAS> getCOM_BUDGETS_LINHASbyid(@PathParam("id") Integer id) {
		return dao8.getbyid(id);
	}

	@GET
	@Path("/getCOM_BUDGETS_LINHASbyid2/{id}")
	@Produces("application/json")
	public List<COM_BUDGETS_LINHAS> getCOM_BUDGETS_LINHASbyid2(@PathParam("id") Integer id) {
		return dao8.getbyid2(id);
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
		Query query_folder = entityManager.createNativeQuery("EXEC [COM_GET_REFERENCE_CUSTOMER] '"+CLICOD+"','"+ETSNUM+"','"+PROREF+"'");

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
		
		Query query_folder = entityManager.createNativeQuery("EXEC [COM_GET_BUDGET_ANALISE] "+ANO+","+ID_CUSTOMER+","+CUSTOMER_GROUP+","+REFERENCE_INTERNAL+"");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}
}
