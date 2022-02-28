package pt.example.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
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
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.auxilii.msgparser.Message;
import com.auxilii.msgparser.MsgParser;
import com.auxilii.msgparser.attachment.Attachment;
import com.auxilii.msgparser.attachment.FileAttachment;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import net.sf.jasperreports.engine.JRException;
import pt.example.bootstrap.ConnectProgress;
import pt.example.bootstrap.Printer;
import pt.example.bootstrap.ReportGenerator;
import pt.example.bootstrap.SendEmail;
import pt.example.bootstrap.SomeQuarterlyJob;
import pt.example.dao.*;
import pt.example.entity.*;

@Stateless
@Path("/sirb")
public class SIRB {

	@Inject
	private AB_DIC_BANHODao dao1;
	@Inject
	private AB_DIC_BANHO_COMPONENTEDao dao2;
	@Inject
	private AB_DIC_COMPONENTEDao dao3;
	@Inject
	private AB_DIC_TINADao dao5;
	@Inject
	private AB_MOV_ANALISEDao dao6;
	@Inject
	private AB_MOV_ANALISE_LINHADao dao7;
	@Inject
	private AB_MOV_MANUTENCAODao dao8;
	@Inject
	private AB_MOV_MANUTENCAO_LINHADao dao9;
	@Inject
	private GER_FORNECEDORDao dao10;
	@Inject
	private GER_UTILIZADORESDao dao11;
	@Inject
	private AB_DIC_LINHADao dao12;
	@Inject
	private AB_DIC_ZONADao dao13;
	@Inject
	private AB_DIC_UNIDADE_MEDIDADao dao14;
	@Inject
	private AB_MOV_MANUTENCAO_CABDao dao15;
	@Inject
	private AB_DIC_TIPO_ADICAODao dao16;
	@Inject
	private AB_DIC_TIPO_MANUTENCAODao dao17;
	@Inject
	private AB_DIC_TIPO_OPERACAODao dao18;
	@Inject
	private AB_DIC_TURNODao dao19;
	@Inject
	private AB_DIC_BANHO_ADITIVODao dao20;
	@Inject
	private AB_MOV_REG_PARAM_OPERACAODao dao21;
	@Inject
	private GER_MODULODao dao22;
	@Inject
	private GER_PERFIL_CABDao dao23;
	@Inject
	private GER_PERFIL_LINDao dao24;
	@Inject
	private GER_UTZ_PERFILDao dao25;
	@Inject
	private GER_ARMAZEMDao dao26;
	@Inject
	private GER_LOG_EVENTOSDao dao27;
	@Inject
	private GER_EVENTOS_CONFDao dao28;
	@Inject
	private GER_ANALISESDao dao29;
	@Inject
	private GER_PARAMETROSDao dao30;
	@Inject
	private GER_VISTASDao dao31;
	@Inject
	private GER_CAMPOS_DISPDao dao32;
	@Inject
	private GER_POSTOSDao dao33;
	@Inject
	private AB_DIC_LINHA_OFDao dao34;
	@Inject
	private AB_MOV_MANUTENCAO_ETIQDao dao35;
	@Inject
	private GER_EVENTOS_PROGRAMADOSDao dao36;
	@Inject
	private RC_DIC_FICHEIROS_ANALISEDao dao37;
	@Inject
	private RC_DIC_GRAU_IMPORTANCIADao dao38;
	@Inject
	private RC_DIC_REJEICAODao dao39;
	@Inject
	private RC_DIC_TIPO_DEFEITODao dao40;
	@Inject
	private RC_DIC_TIPO_RECLAMACAODao dao41;
	@Inject
	private RC_MOV_RECLAMACAODao dao42;
	@Inject
	private RC_MOV_RECLAMACAO_ARTIGO_SIMILARESDao dao43;
	@Inject
	private RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOSDao dao44;
	@Inject
	private RC_MOV_RECLAMACAO_EQUIPADao dao45;
	@Inject
	private RC_MOV_RECLAMACAO_FICHEIROSDao dao46;
	@Inject
	private RC_MOV_RECLAMACAO_PLANOS_ACCOESDao dao47;
	@Inject
	private RC_DIC_TEMPO_RESPOSTADao dao50;
	@Inject
	private GER_GRUPO_UTZDao dao51;
	@Inject
	private GER_GRUPODao dao52;
	@Inject
	private GER_SECCAO_CHEFESDao dao53;
	@Inject
	private GER_SECCAO_UTZDao dao54;
	@Inject
	private GER_SECCAODao dao55;
	@Inject
	private GER_DEPARTAMENTODao dao56;
	@Inject
	private GT_DIC_TAREFASDao dao57;
	@Inject
	private RC_MOV_RECLAMACAO_STOCKDao dao58;
	@Inject
	private GT_MOV_TAREFASDao dao59;
	@Inject
	private GT_LOGSDao dao48;
	@Inject
	private RC_MOV_RECLAMACAO_ENCOMENDASDao dao49;
	@Inject
	private RC_DIC_TIPO_OCORRENCIADao dao60;
	@Inject
	private RC_DIC_TIPO_NAO_DETECAODao dao61;
	@Inject
	private GER_ATUALIZACAO_SILVER_BI_TABELASDao dao62;
	@Inject
	private RH_FUNCIONARIOSDao dao63;
	@Inject
	private RH_ESTADOS_FUNCDao dao64;
	@Inject
	private RH_SECTORESDao dao65;
	@Inject
	private RH_TURNOSDao dao66;
	@Inject
	private RH_EXCLUSAO_TIPO_EXTRADao dao67;
	@Inject
	private GER_LOCAISDao dao68;
	@Inject
	private RH_DIC_CACIFOSDao dao69;
	@Inject
	private RH_DIC_TIPO_CACIFODao dao70;
	@Inject
	private RH_TIPOS_PAUSADao dao71;
	@Inject
	private RH_PAUSASDao dao72;
	@Inject
	private AT_ACCOESDao dao73;
	@Inject
	private AT_ENTREVISTASDao dao74;
	@Inject
	private AT_OCORRENCIASDao dao75;
	@Inject
	private AT_TESTEMUNHASDao dao76;
	@Inject
	private PLANEAMENTO_LINHASDao dao77;
	@Inject
	private PLANEAMENTO_CABDao dao78;
	@Inject
	private TIPOS_OCORRENCIADao dao79;
	@Inject
	private CAPACIDADE_LINHADao dao80;
	@Inject
	private GER_FERIADOSDao dao81;
	@Inject
	private PR_DIC_OBJETIVOS_PLANOSDao dao82;
	@Inject
	private PR_DIC_SECTORES_ANALISEDao dao83;
	@Inject
	private PR_DIC_TIPOLOGIA_ENSAIODao dao84;
	@Inject
	private PR_AMOSTRAS_CABDao dao85;
	@Inject
	private PR_AMOSTRAS_ACCOESDao dao86;
	@Inject
	private RC_DIC_CLASSIFICACAODao dao87;
	@Inject
	private RC_DIC_TIPOLOGIADao dao88;
	@Inject
	private RC_MOV_RECLAMACAO_FORNECEDORDao dao89;
	@Inject
	private RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDORDao dao90;
	@Inject
	private LG_DIC_OBJETIVOSDao dao91;
	@Inject
	private PR_BARRAS_ALERTADao dao92;
	@Inject
	private PR_REVISOES_PRIORITARIASDao dao93;
	@Inject
	private PR_PRODUCOES_PRIORITARIASDao dao94;
	@Inject
	private PA_MOV_CABDao dao95;
	@Inject
	private PA_MOV_LINHADao dao96;
	@Inject
	private GER_DEPARTAMENTOS_SECTORESDao dao97;
	@Inject
	private PA_MOV_FICHEIROSDao dao98;
	@Inject
	private GT_MOV_FICHEIROSDao dao99;
	@Inject
	private PA_DIC_AMBITOSDao dao100;
	@Inject
	private PEDIDOS_APPDao dao101;
	@Inject
	private FICHEIROS_PAGINASDao dao102;
	@Inject
	private GT_DIC_TIPO_ACAODao dao103;
	@Inject
	private GER_DIC_OEMDao dao104;
	@Inject
	private GER_DIC_FABRICADao dao105;
	@Inject
	private GER_DIC_PROGRAMADao dao106;
	@Inject
	private GER_DIC_VEICULODao dao107;
	@Inject
	private GER_DIC_PROJ_CABDao dao108;
	@Inject
	private GER_DIC_PROJ_REFDao dao109;
	@Inject
	private GER_DIC_PROJ_FABDao dao110;
	@Inject
	private GER_DIC_LIMITES_ENCOMENDADao dao111;
	@Inject
	private FIN_DIC_OBJETIVOSDao dao112;
	@Inject
	private FIN_DIC_CLIENTESDao dao113;
	@Inject
	private FIN_DIC_TIPO_DOCDao dao114;
	@Inject
	private FIN_DOC_ACORDODao dao115;
	@Inject
	private FIN_DIVIDAS_ATIVIDADEDao dao116;
	@Inject
	private FIN_REGISTO_ACOESDao dao117;

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	/************************************* AB_DIC_BANHO */
	@POST
	@Path("/createAB_DIC_BANHO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_BANHO insertAB_DIC_ADITIVO(final AB_DIC_BANHO data) {
		return dao1.create(data);
	}

	@GET
	@Path("/getAB_DIC_BANHOyid_banho/{id}/{linha}")
	@Produces("application/json")
	public List<AB_DIC_BANHO> getAB_DIC_BANHOyid_banho(@PathParam("id") Integer id, @PathParam("linha") Integer linha) {
		return dao1.getbyid(id, linha);
	}

	@GET
	@Path("/getAB_DIC_BANHO")
	@Produces("application/json")
	public List<AB_DIC_BANHO> getAB_DIC_BANHO() {
		return dao1.allEntries();
	}

	@GET
	@Path("/getAB_DIC_BANHOLINHA/{linha}")
	@Produces("application/json")
	public List<AB_DIC_BANHO> getAB_DIC_BANHOLINHA(@PathParam("linha") Integer linha) {
		return dao1.getall(linha);
	}

	@GET
	@Path("/getAllLINHAbylinha/{id}/{linha}")
	@Produces("application/json")
	public List<AB_DIC_BANHO> getAllLINHAbylinha(@PathParam("id") Integer id, @PathParam("linha") Integer linha) {
		return dao1.getAllLINHAbylinha(id, linha);
	}

	@GET
	@Path("/getAllLINHAbylinhatodos/{id}/{linha}")
	@Produces("application/json")
	public List<AB_DIC_BANHO> getAllLINHAbylinhatodos(@PathParam("id") Integer id, @PathParam("linha") Integer linha) {
		return dao1.getAllLINHAbylinha2(id, linha);
	}

	@GET
	@Path("/getAllLINHAbylinha_tipo/{id}/{linha}/{tipo}")
	@Produces("application/json")
	public List<AB_DIC_BANHO> getAllLINHAbylinha_tipo(@PathParam("id") Integer id, @PathParam("linha") Integer linha,
			@PathParam("tipo") String tipo) {
		return dao1.getAllLINHAbylinha_tipo(id, linha, tipo);
	}

	@DELETE
	@Path("/deleteAB_DIC_BANHO/{id}")
	public void deleteAB_DIC_BANHO(@PathParam("id") Integer id) {
		AB_DIC_BANHO AB_DIC_BANHO = new AB_DIC_BANHO();
		AB_DIC_BANHO.setID_BANHO(id);
		dao1.delete(AB_DIC_BANHO);
	}

	@PUT
	@Path("/updateAB_DIC_BANHO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_BANHO updateAB_DIC_BANHO(final AB_DIC_BANHO AB_DIC_BANHO) {
		AB_DIC_BANHO.setID_BANHO(AB_DIC_BANHO.getID_BANHO());
		return dao1.update(AB_DIC_BANHO);
	}

	/************************************* AB_DIC_BANHO_COMPONENTE */
	@POST
	@Path("/createAB_DIC_BANHO_COMPONENTE")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_BANHO_COMPONENTE insertAB_DIC_BANHO_COMPONENTE(final AB_DIC_BANHO_COMPONENTE data) {
		return dao2.create(data);
	}

	@GET
	@Path("/getAB_DIC_BANHO_COMPONENTE")
	@Produces("application/json")
	public List<AB_DIC_BANHO_COMPONENTE> getAB_DIC_BANHO_COMPONENTE() {
		return dao2.allEntries();
	}

	@POST
	@Path("/getAB_DIC_BANHO_COMPONENTEbyid_banho/{id}")
	@Produces("application/json")
	public List<AB_DIC_BANHO_COMPONENTE> getAB_DIC_BANHO_COMPONENTEbyid_banho(@PathParam("id") Integer id,
			final String data) {
		return dao2.getbyid_banho(id, data);
	}

	@GET
	@Path("/getAB_DIC_BANHO_COMPONENTEbyid_banhoall/{id}")
	@Produces("application/json")
	public List<AB_DIC_BANHO_COMPONENTE> getAB_DIC_BANHO_COMPONENTEbyid_banhoall(@PathParam("id") Integer id) {
		return dao2.getbyid_banhoall(id);
	}

	@GET
	@Path("/getAB_DIC_BANHO_COMPONENTEbyid_banho_comp/{id}")
	@Produces("application/json")
	public List<AB_DIC_BANHO_COMPONENTE> getAB_DIC_BANHO_COMPONENTEbyid_banho_comp(@PathParam("id") Integer id) {
		return dao2.getbyid_banho_comp(id);
	}

	@DELETE
	@Path("/deleteAB_DIC_BANHO_COMPONENTE/{id}")
	public void deleteAB_DIC_BANHO_COMPONENTE(@PathParam("id") Integer id) {
		AB_DIC_BANHO_COMPONENTE AB_DIC_BANHO_COMPONENTE = new AB_DIC_BANHO_COMPONENTE();
		AB_DIC_BANHO_COMPONENTE.setID_BANHO_COMP(id);
		dao2.delete(AB_DIC_BANHO_COMPONENTE);
	}

	@PUT
	@Path("/updateAB_DIC_BANHO_COMPONENTE")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_BANHO_COMPONENTE updateAB_DIC_BANHO_COMPONENTE(
			final AB_DIC_BANHO_COMPONENTE AB_DIC_BANHO_COMPONENTE) {
		AB_DIC_BANHO_COMPONENTE.setID_BANHO_COMP(AB_DIC_BANHO_COMPONENTE.getID_BANHO_COMP());
		return dao2.update(AB_DIC_BANHO_COMPONENTE);
	}

	/************************************* AB_DIC_COMPONENTE */
	@POST
	@Path("/createAB_DIC_COMPONENTE")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_COMPONENTE insertAB_DIC_COMPONENTE(final AB_DIC_COMPONENTE data) {
		return dao3.create(data);
	}

	@GET
	@Path("/getAB_DIC_COMPONENTE/{tipo}")
	@Produces("application/json")
	public List<AB_DIC_COMPONENTE> getAB_DIC_COMPONENTE(@PathParam("tipo") String tipo) {
		return dao3.gelAll(tipo);
	}

	@GET
	@Path("/getAB_DIC_COMPONENTEbyid_componente/{id}")
	@Produces("application/json")
	public List<AB_DIC_COMPONENTE> getAB_DIC_COMPONENTEbyid_componente(@PathParam("id") Integer id) {
		return dao3.getbyid_componente(id);
	}

	@DELETE
	@Path("/deleteAB_DIC_COMPONENTE/{id}")
	public void deleteAB_DIC_COMPONENTE(@PathParam("id") Integer id) {
		AB_DIC_COMPONENTE AB_DIC_COMPONENTE = new AB_DIC_COMPONENTE();
		AB_DIC_COMPONENTE.setID_COMPONENTE(id);
		dao3.delete(AB_DIC_COMPONENTE);
	}

	@PUT
	@Path("/updateAB_DIC_COMPONENTE")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_COMPONENTE updateAB_DIC_COMPONENTE(final AB_DIC_COMPONENTE AB_DIC_COMPONENTE) {
		AB_DIC_COMPONENTE.setID_COMPONENTE(AB_DIC_COMPONENTE.getID_COMPONENTE());
		return dao3.update(AB_DIC_COMPONENTE);
	}

	/************************************* AB_DIC_TINA */
	@POST
	@Path("/createAB_DIC_TINA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TINA insertAB_DIC_TINA(final AB_DIC_TINA data) {
		return dao5.create(data);
	}

	@GET
	@Path("/getAB_DIC_TINA")
	@Produces("application/json")
	public List<AB_DIC_TINA> getAB_DIC_TINA() {
		return dao5.allEntries();
	}

	@GET
	@Path("/getallAB_DIC_TINA/{linha}")
	@Produces("application/json")
	public List<AB_DIC_TINA> getallAB_DIC_TINA(@PathParam("linha") Integer linha) {
		return dao5.getall(linha);
	}

	@GET
	@Path("/getAB_DIC_TINAvbyid_tina/{id}/{linha}")
	@Produces("application/json")
	public List<AB_DIC_TINA> getAB_DIC_TINAvbyid_tina(@PathParam("id") Integer id, @PathParam("linha") Integer linha) {
		return dao5.getbyid(id, linha);
	}

	@DELETE
	@Path("/deleteAB_DIC_TINA/{id}")
	public void deleteAB_DIC_TINA(@PathParam("id") Integer id) {
		AB_DIC_TINA AB_DIC_TINA = new AB_DIC_TINA();
		AB_DIC_TINA.setID_TINA(id);
		dao5.delete(AB_DIC_TINA);
	}

	@PUT
	@Path("/updateAB_DIC_TINA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TINA updateAB_DIC_TINA(final AB_DIC_TINA AB_DIC_TINA) {
		AB_DIC_TINA.setID_TINA(AB_DIC_TINA.getID_TINA());
		return dao5.update(AB_DIC_TINA);
	}

	/************************************* AB_MOV_ANALISE */
	@POST
	@Path("/createAB_MOV_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_ANALISE insertAB_MOV_ANALISE(final AB_MOV_ANALISE data) {
		return dao6.create(data);
	}

	@GET
	@Path("/getAB_MOV_ANALISE")
	@Produces("application/json")
	public List<AB_MOV_ANALISE> getAB_MOV_ANALISE() {
		return dao6.allEntries();
	}

	@POST
	@Path("/getallAB_MOV_ANALISE/{linha}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_ANALISE> getallAB_MOV_ANALISE(@PathParam("linha") Integer linha,
			final List<HashMap<String, String>> datas) {

		HashMap<String, String> firstMap = datas.get(0);

		String data = firstMap.get("DATA");
		String datafim = firstMap.get("DATA_FIM");
		return dao6.getall(linha, data, datafim);
	}

	@POST
	@Path("/getallAB_MOV_ANALISEmanu/{linha}/{idbanho}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_ANALISE> getallAB_MOV_ANALISEmanu(@PathParam("linha") Integer linha,
			@PathParam("idbanho") Integer idbanho, final Date data) {
		return dao6.getallmanu(linha, idbanho, data);
	}

	@GET
	@Path("/getallAB_MOV_ANALISEidbanho/{idbanho}/{inicio}/{fim}/{id_analise}")
	@Produces("application/json")
	public List<AB_MOV_ANALISE> getallbyidbanho(@PathParam("idbanho") Integer idbanho,
			@PathParam("inicio") Integer inicio, @PathParam("fim") Integer fim,
			@PathParam("id_analise") Integer id_analise) {
		return dao6.getallbyidbanho(idbanho, inicio, fim, id_analise);
	}

	@POST
	@Path("/getallAB_MOV_ANALISEidbanho_comp/{idbanho}/{inicio}/{fim}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_ANALISE> getallbyidbanho_comp(@PathParam("idbanho") Integer idbanho,
			@PathParam("inicio") Integer inicio, @PathParam("fim") Integer fim,
			final List<HashMap<String, String>> data) throws ParseException {
		return dao6.getallbyidbanho_manut(idbanho, inicio, fim, data);
	}

	@GET
	@Path("/getAB_MOV_ANALISEbyid/{id}/{linha}")
	@Produces("application/json")
	public List<AB_MOV_ANALISE> getAB_MOV_ANALISEbyid_tina(@PathParam("id") Integer id,
			@PathParam("linha") Integer linha) {
		return dao6.getbyid(id, linha);
	}

	@DELETE
	@Path("/deleteAB_MOV_ANALISE/{id}")
	public void deleteAB_MOV_ANALISE(@PathParam("id") Integer id) {
		AB_MOV_ANALISE AB_MOV_ANALISE = new AB_MOV_ANALISE();
		AB_MOV_ANALISE.setID_ANALISE(id);
		dao6.delete(AB_MOV_ANALISE);
	}

	@PUT
	@Path("/updateAB_MOV_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_ANALISE updateAB_MOV_ANALISE(final AB_MOV_ANALISE AB_MOV_ANALISE) {
		AB_MOV_ANALISE.setID_ANALISE(AB_MOV_ANALISE.getID_ANALISE());
		return dao6.update(AB_MOV_ANALISE);
	}

	/************************************* AB_MOV_ANALISE_LINHA */
	@POST
	@Path("/createAB_MOV_ANALISE_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_ANALISE_LINHA insertAB_MOV_ANALISE_LINHA(final AB_MOV_ANALISE_LINHA data) {
		return dao7.create(data);
	}

	@GET
	@Path("/getAB_MOV_ANALISE_LINHA")
	@Produces("application/json")
	public List<AB_MOV_ANALISE_LINHA> getAB_MOV_ANALISE_LINHA() {
		return dao7.allEntries();
	}

	@GET
	@Path("/getAB_MOV_ANALISE_LINHAbyid/{id}")
	@Produces("application/json")
	public List<AB_MOV_ANALISE_LINHA> getAB_MOV_ANALISE_LINHAbyid(@PathParam("id") Integer id) {
		return dao7.getbyid(id);
	}

	@POST
	@Path("/getAB_MOV_ANALISE_LINHAbyid_analise_comp/{id}/{id_banho}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_ANALISE_LINHA> getbyid_analise_comp(@PathParam("id") Integer id,
			@PathParam("id_banho") Integer id_banho, final ArrayList<Integer> data) {
		return dao7.getbyid_analise_comp(id, id_banho, data);
	}

	@POST
	@Path("/getAB_MOV_ANALISE_LINHAbyid_analise_comp2/{id}/{id_banho}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_ANALISE_LINHA> getbyid_analise_comp2(@PathParam("id") Integer id,
			@PathParam("id_banho") Integer id_banho, final ArrayList<Integer> data) {
		return dao7.getbyid_analise_comp2(id, id_banho, data);
	}

	@GET
	@Path("/getAB_MOV_ANALISE_LINHAbyid_analise/{id}/{id_banho}")
	@Produces("application/json")
	public List<AB_MOV_ANALISE_LINHA> getAB_MOV_ANALISE_LINHAbyid_analise(@PathParam("id") Integer id,
			@PathParam("id_banho") Integer id_banho) {
		return dao7.getbyid_analise(id, id_banho);
	}

	@DELETE
	@Path("/deleteAB_MOV_ANALISE_LINHA/{id}")
	public void deleteAB_MOV_ANALISE_LINHA(@PathParam("id") Integer id) {
		AB_MOV_ANALISE_LINHA AB_MOV_ANALISE_LINHA = new AB_MOV_ANALISE_LINHA();
		AB_MOV_ANALISE_LINHA.setID_ANALISE_LIN(id);
		dao7.delete(AB_MOV_ANALISE_LINHA);
	}

	@PUT
	@Path("/updateAB_MOV_ANALISE_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_ANALISE_LINHA updateAB_MOV_ANALISE_LINHA(final AB_MOV_ANALISE_LINHA AB_MOV_ANALISE_LINHA) {
		AB_MOV_ANALISE_LINHA.setID_ANALISE_LIN(AB_MOV_ANALISE_LINHA.getID_ANALISE_LIN());
		return dao7.update(AB_MOV_ANALISE_LINHA);
	}

	/************************************* AB_MOV_MANUTENCAO */
	@POST
	@Path("/createAB_MOV_MANUTENCAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_MANUTENCAO insertAB_MOV_MANUTENCAOA(final AB_MOV_MANUTENCAO data) {
		return dao8.create(data);
	}

	@POST
	@Path("/getAB_MOV_MANUTENCAO/{linha}/{classif}/{classif2}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getAB_MOV_MANUTENCAO(@PathParam("linha") Integer linha,
			@PathParam("classif") String classif, final ArrayList<String> query,
			@PathParam("classif2") String classif2) {
		return dao8.getall(linha, query, classif, classif2);
	}

	@POST
	@Path("/getAB_MOV_MANUTENCAOall/{linha}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getAB_MOV_MANUTENCAOall(@PathParam("linha") Integer linha,
			final List<HashMap<String, String>> query) {
		return dao8.getallmanu(linha, query);
	}

	@POST
	@Path("/getallAnaliseConsumos")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getallAnaliseConsumos(final List<HashMap<String, String>> data) {
		return dao8.getallAnaliseConsumos(data);
	}

	@POST
	@Path("/getallAnaliseConsumos2")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getallAnaliseConsumos2(final List<HashMap<String, String>> data) {
		return dao8.getallAnaliseConsumos2(data);
	}

	@POST
	@Path("/getAB_MOV_MANUTENCAOidbanho/{linha}/{classif}/{idbanho}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getAB_MOV_MANUTENCAOidbanho(@PathParam("linha") Integer linha,
			@PathParam("classif") String classif, final ArrayList<String> query,
			@PathParam("idbanho") Integer idbanho) {
		return dao8.getallidabanho(linha, query, classif, idbanho);
	}

	@POST
	@Path("/getAB_MOV_MANUTENCAOsorid/{linha}/{classif}/{classif2}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getAB_MOV_MANUTENCAOsorid(@PathParam("linha") Integer linha,
			@PathParam("classif") String classif, @PathParam("classif2") String classif2,
			final ArrayList<String> query) {
		return dao8.getallsortid(linha, query, classif, classif2);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAObyid/{id}/{linha}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getAB_MOV_MANUTENCAObyid(@PathParam("id") Integer id,
			@PathParam("linha") Integer linha) {
		return dao8.getbyid(id, linha);
	}

	@DELETE
	@Path("/deleteAB_MOV_MANUTENCAO/{id}")
	public void deleteAB_MOV_MANUTENCAO(@PathParam("id") Integer id) {
		AB_MOV_MANUTENCAO AB_MOV_MANUTENCAO = new AB_MOV_MANUTENCAO();
		AB_MOV_MANUTENCAO.setID_MANUTENCAO(id);
		dao8.delete(AB_MOV_MANUTENCAO);
	}

	@PUT
	@Path("/updateAB_MOV_MANUTENCAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_MANUTENCAO updateAB_MOV_ANALISE_LINHA(final AB_MOV_MANUTENCAO AB_MOV_MANUTENCAO) {
		AB_MOV_MANUTENCAO.setID_MANUTENCAO(AB_MOV_MANUTENCAO.getID_MANUTENCAO());
		return dao8.update(AB_MOV_MANUTENCAO);
	}

	/************************************ AB_MOV_MANUTENCAO_CAB */
	@POST
	@Path("/createAB_MOV_MANUTENCAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_MANUTENCAO_CAB insertAB_MOV_MANUTENCAO_CAB(final AB_MOV_MANUTENCAO_CAB data) {
		return dao15.create(data);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_CAB")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_CAB> getAB_MOV_MANUTENCAO_CAB() {
		return dao15.allEntries();
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_CABbyidmanutencao/{id}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_CAB> getAB_MOV_MANUTENCAO_CABbyidmanutencao(@PathParam("id") Integer id) {
		return dao15.getbyidmanutencao(id);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_CABbyid_banho/{idbanho}/{inicio}/{fim}/{id_man}/{classif}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_CAB> getAB_MOV_MANUTENCAO_CABbyid_banho(@PathParam("idbanho") Integer idbanho,
			@PathParam("inicio") Integer inicio, @PathParam("fim") Integer fim, @PathParam("id_man") Integer id_man,
			@PathParam("classif") String classif) {
		return dao15.getbyidbanho(idbanho, inicio, fim, id_man, classif);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_CABbyid_banhoall/{idbanho}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_CAB> getAB_MOV_MANUTENCAO_CABbyid_banhoall(@PathParam("idbanho") Integer idbanho) {
		return dao15.getbyidbanhoall(idbanho);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_CABbyid/{id}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_CAB> getAB_MOV_MANUTENCAO_CABbyid(@PathParam("id") Integer id) {
		return dao15.getbyid(id);
	}

	@DELETE
	@Path("/deleteAB_MOV_MANUTENCAO_CAB/{id}")
	public void deleteAB_MOV_MANUTENCAO_CABA(@PathParam("id") Integer id) {
		AB_MOV_MANUTENCAO_CAB AB_MOV_MANUTENCAO_CAB = new AB_MOV_MANUTENCAO_CAB();
		AB_MOV_MANUTENCAO_CAB.setID_MANUTENCAO_CAB(id);
		dao15.delete(AB_MOV_MANUTENCAO_CAB);
	}

	@PUT
	@Path("/updateAB_MOV_MANUTENCAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_MANUTENCAO_CAB updateAB_MOV_MANUTENCAO_CAB(final AB_MOV_MANUTENCAO_CAB AB_MOV_MANUTENCAO_CAB) {
		AB_MOV_MANUTENCAO_CAB.setID_MANUTENCAO_CAB(AB_MOV_MANUTENCAO_CAB.getID_MANUTENCAO_CAB());
		return dao15.update(AB_MOV_MANUTENCAO_CAB);
	}

	/************************************* AB_MOV_MANUTENCAO_LINHA */
	@POST
	@Path("/createAB_MOV_MANUTENCAO_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_MANUTENCAO_LINHA insertAAB_MOV_MANUTENCAO_LINHA(final AB_MOV_MANUTENCAO_LINHA data) {
		return dao9.create(data);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_LINHA")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_LINHA> getAB_MOV_MANUTENCAO_LINHA() {
		return dao9.allEntries();
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_LINHAbyidmanutencaocab/{id}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_LINHA> getAB_MOV_MANUTENCAO_LINHAbyidmanutencaocab(@PathParam("id") Integer id) {
		return dao9.getbyidmanutencaocab(id);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_LINHAbyidmanutencaocabtotal/{id}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_LINHA> getAB_MOV_MANUTENCAO_LINHAbyidmanutencaocabtotal(@PathParam("id") Integer id) {
		return dao9.getbyidmanutencaocabtotal(id);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_LINHAbyidmanutencaocabtotal2/{id}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_LINHA> getAB_MOV_MANUTENCAO_LINHAbyidmanutencaocabtotal2(
			@PathParam("id") Integer id) {
		return dao9.getbyidmanutencaocabtotal2(id);
	}

	@DELETE
	@Path("/apagar_linhas/{id}")
	public void apagar_linhas(@PathParam("id") Integer id) {
		dao9.apagar_linhas(id);
	}

	@POST
	@Path("/getAB_MOV_MANUTENCAO_LINHAbyid_analise_comp/{id}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_LINHA> getbyid_analise_comp(@PathParam("id") Integer id,
			final ArrayList<Integer> data) {
		return dao9.getbyid_manut_comp(id, data);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_LINHAbyid/{id}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_LINHA> getAB_MOV_MANUTENCAO_LINHAbyid(@PathParam("id") Integer id) {
		return dao9.getbyid(id);
	}

	@DELETE
	@Path("/deleteAB_MOV_MANUTENCAO_LINHA/{id}")
	public void deleteAB_MOV_MANUTENCAO_LINHA(@PathParam("id") Integer id) {
		AB_MOV_MANUTENCAO_LINHA AB_MOV_MANUTENCAO_LINHA = new AB_MOV_MANUTENCAO_LINHA();
		AB_MOV_MANUTENCAO_LINHA.setID_MANUTENCAO_LIN(id);
		dao9.delete(AB_MOV_MANUTENCAO_LINHA);
	}

	@PUT
	@Path("/updateAB_MOV_MANUTENCAO_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_MANUTENCAO_LINHA updateAB_MOV_MANUTENCAO_LINHA(
			final AB_MOV_MANUTENCAO_LINHA AB_MOV_MANUTENCAO_LINHA) {
		AB_MOV_MANUTENCAO_LINHA.setID_MANUTENCAO_LIN(AB_MOV_MANUTENCAO_LINHA.getID_MANUTENCAO_LIN());
		return dao9.update(AB_MOV_MANUTENCAO_LINHA);
	}

	/************************************* GER_FORNECEDOR */
	@POST
	@Path("/createGER_FORNECEDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_FORNECEDOR insertGER_FORNECEDOR(final GER_FORNECEDOR data) {
		return dao10.create(data);
	}

	@GET
	@Path("/getGER_FORNECEDOR")
	@Produces("application/json")
	public List<GER_FORNECEDOR> getGER_FORNECEDOR() {
		return dao10.getAll();
	}

	@GET
	@Path("/getGER_FORNECEDORbyid_fornecedor/{id}")
	@Produces("application/json")
	public List<GER_FORNECEDOR> getGER_UTILIZADORESbyid_fornecedor(@PathParam("id") Integer id) {
		return dao10.getbyid(id);
	}

	@GET
	@Path("/verifica_num_fornece/{id}/{num}")
	@Produces("application/json")
	public List<GER_FORNECEDOR> verifica_num_fornece(@PathParam("id") Integer id, @PathParam("num") Integer num) {
		return dao10.verifica_num_fornece(id, num);
	}

	@GET
	@Path("/verifica_num/{num}")
	@Produces("application/json")
	public List<GER_FORNECEDOR> verifica_num(@PathParam("num") Integer num) {
		return dao10.verifica_num(num);
	}

	@GET
	@Path("/getGER_FORNECEDORMaxID")
	@Produces("application/json")
	public List<Integer> getGER_UTILIZADORESMaxID() {
		return dao10.getMaxID();
	}

	@DELETE
	@Path("/deleteGER_FORNECEDOR/{id}")
	public void deleteGER_FORNECEDOR(@PathParam("id") Integer id) {
		GER_FORNECEDOR GER_FORNECEDOR = new GER_FORNECEDOR();
		GER_FORNECEDOR.setID_FORNECEDOR(id);
		dao10.delete(GER_FORNECEDOR);
	}

	@PUT
	@Path("/updateGER_FORNECEDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_FORNECEDOR updateGER_FORNECEDOR(final GER_FORNECEDOR GER_FORNECEDOR) {
		GER_FORNECEDOR.setID_FORNECEDOR(GER_FORNECEDOR.getID_FORNECEDOR());
		return dao10.update(GER_FORNECEDOR);
	}

	/************************************* GER_UTILIZADORES */
	@POST
	@Path("/createGER_UTILIZADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_UTILIZADORES insertGER_UTILIZADORES(final GER_UTILIZADORES data) {
		return dao11.create(data);
	}

	@GET
	@Path("/getGER_UTILIZADORES")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_UTILIZADORES() {
		return dao11.getAll();
	}

	@GET
	@Path("/getGER_UTILIZADORES_SECTOR")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_UTILIZADORES_SECTOR() {
		return dao11.getAllSECTOR();
	}

	@GET
	@Path("/getDadosUtilizador/{id}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getDadosUtilizador(@PathParam("id") Integer id) {
		return dao11.getDadosUtilizador(id);
	}

	@GET
	@Path("/getDadosUtilizadorAll")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getDadosUtilizadorAll() {
		return dao11.getDadosUtilizadorAll();
	}

	@GET
	@Path("/getGER_UTILIZADORESbyid_utilizador/{id}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_UTILIZADORESbyid_utilizador(@PathParam("id") Integer id) {
		return dao11.getbyid(id);
	}

	@GET
	@Path("/getGER_UTILIZADORESbylogin/{login}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_UTILIZADORESbylogin(@PathParam("login") String login) {
		return dao11.getbylogin(login);
	}

	@GET
	@Path("/getGER_UTILIZADORESbycode_user/{code}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_UTILIZADORESbycode_user(@PathParam("code") String code) {
		return dao11.getbycode(code);
	}

	@GET
	@Path("/getGER_UTILIZADORESbyLDAP/{code}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_UTILIZADORESbyLDAP(@PathParam("code") String code) {
		return dao11.getbyLoginLDAP(code);
	}

	@GET
	@Path("/getGER_UTILIZADORESverifica_login/{id}/{login}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_UTILIZADORESverifica_login(@PathParam("login") String login,
			@PathParam("id") Integer id) {
		return dao11.verifica_login(id, login);
	}

	@GET
	@Path("/getGER_UTILIZADORESverifica_code/{id}/{code}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_UTILIZADORESverifica_code(@PathParam("code") String code,
			@PathParam("id") Integer id) {
		return dao11.verifica_code(id, code);
	}

	@GET
	@Path("/getGER_UTILIZADORESverifica_LDAP/{id}/{code}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_UTILIZADORESverifica_LDAP(@PathParam("code") String code,
			@PathParam("id") Integer id) {
		return dao11.verifica_LDAP(id, code);
	}

	@DELETE
	@Path("/deleteGER_UTILIZADORES/{id}")
	public void deleteGER_UTILIZADORES(@PathParam("id") Integer id) {
		GER_UTILIZADORES GER_UTILIZADORES = new GER_UTILIZADORES();
		GER_UTILIZADORES.setID_UTILIZADOR(id);
		dao11.delete(GER_UTILIZADORES);
	}

	@PUT
	@Path("/updateGER_UTILIZADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_UTILIZADORES updateGER_UTILIZADORES(final GER_UTILIZADORES GER_UTILIZADORES) {
		GER_UTILIZADORES.setID_UTILIZADOR(GER_UTILIZADORES.getID_UTILIZADOR());
		return dao11.update(GER_UTILIZADORES);
	}

	@POST
	@Path("/createGER_UTILIZADOREStesteLDAP")
	@Consumes("*/*")
	@Produces("application/json")
	public boolean createGER_UTILIZADOREStesteLDAP(final List<HashMap<String, String>> data) {
		HashMap<String, String> firstMap = data.get(0);

		String user = firstMap.get("USER");
		String password = firstMap.get("PASSWORD");

		String ldapSearchBase = "";
		String DOMAIN = "";
		String IP_DOMAIN = "";

		Query query_folder = entityManager.createNativeQuery(
				"select top 1 NOME_USER,ID_USER,PASSWORD_USER,ldapSearchBase,DOMAIN,IP_DOMAIN from GER_LDAP_CONF a");

		List<Object[]> dados_folder = query_folder.getResultList();

		for (Object[] content : dados_folder) {
			ldapSearchBase = content[3].toString();
			DOMAIN = content[4].toString();
			IP_DOMAIN = content[5].toString();
		}

		try {
			String senha2 = new String(Base64.getDecoder().decode(password));
			return testLDAP(user.replaceAll("\\s+", ""), senha2, DOMAIN, ldapSearchBase, IP_DOMAIN);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@GET
	@Path("/getGER_UTILIZADORESLDAP")
	@Produces("application/json")
	public List<HashMap<String, String>> getGER_UTILIZADORESLDAP() {

		String NOME_USER = "";
		String ID_USER = "";
		String PASSWORD_USER = "";
		String ldapSearchBase = "";
		String DOMAIN = "";
		String IP_DOMAIN = "";

		Query query_folder = entityManager.createNativeQuery(
				"select top 1 NOME_USER,ID_USER,PASSWORD_USER,ldapSearchBase,DOMAIN,IP_DOMAIN from GER_LDAP_CONF a");

		List<Object[]> dados_folder = query_folder.getResultList();

		for (Object[] content : dados_folder) {
			NOME_USER = content[0].toString();
			ID_USER = content[1].toString();
			PASSWORD_USER = content[2].toString();
			ldapSearchBase = content[3].toString();
			DOMAIN = content[4].toString();
			IP_DOMAIN = content[5].toString();
		}

		try {
			return dadosLDAP(DOMAIN, ID_USER, NOME_USER, PASSWORD_USER, ldapSearchBase, IP_DOMAIN);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<HashMap<String, String>> dadosLDAP(String dominio, String user, String nome, String pass,
			String ldapSearchBase, String ip_dominio) throws NamingException {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		properties.put(Context.PROVIDER_URL,
				"ldap://" + ip_dominio/* + dominio */);
		properties.put(Context.SECURITY_AUTHENTICATION, "simple");
		properties.put(Context.SECURITY_PRINCIPAL, user + "@" + dominio);
		properties.put(Context.SECURITY_CREDENTIALS, pass);

		try {
			DirContext context = new InitialDirContext(properties);
			SearchControls searchCtrls = new SearchControls();
			searchCtrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String filter = "(&(objectCategory=person)(samaccountname=*))";
			NamingEnumeration values = context.search(ldapSearchBase, filter, searchCtrls);
			HashMap<String, String> x2 = new HashMap<String, String>();
			x2.put("NOME", nome);
			x2.put("ID", user);
			list.add(x2);
			while (values.hasMoreElements()) {
				SearchResult result = (SearchResult) values.nextElement();
				Attributes attrs = result.getAttributes();

				// Attributes attrs = values.getAttributes();

				HashMap<String, String> x = new HashMap<String, String>();

				/*
				 * System.out.println(attrs.get("distinguishedName") + " " +
				 * attrs.get("givenname") + " " + attrs.get("sn") + " " +
				 * attrs.get("mail") + " " + attrs.get("telephonenumber") + " "
				 * + attrs.get("samaccountname") + " " +
				 * attrs.get("userPrincipalName") + " " + attrs.get("memberOf")
				 * + " " + attrs.get("cn") + " " + attrs.get("name") + " " +
				 * attrs.get("uid") + " " + attrs.get("department") + " " +
				 * attrs.get("title") + " " + attrs.get("manager"));
				 */

				/*
				 * if (attrs.get("memberOf") != null &&
				 * attrs.get("memberOf").get().toString().toLowerCase().contains
				 * ("utilizador") && !attrs
				 * .get("memberOf").get().toString().toLowerCase().
				 * contains("utilizadores equipamentos")) {
				 */

				String nomes = ((attrs.get("givenname") != null) ? attrs.get("givenname").get().toString() : " ") + " "
						+ ((attrs.get("sn") != null) ? attrs.get("sn").get().toString()
								: attrs.get("samaccountname").get().toString());

				x.put("NOME", nomes);
				x.put("ID", attrs.get("samaccountname").get().toString());
				list.add(x);
				/* } */

			}

			context.close();

		} catch (NamingException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static boolean testLDAP(String user, String password, String ldapAdServer2, String ldapSearchBase,
			String ip_dominio) throws NamingException {

		final String ldapAdServer = "ldap://" + ip_dominio; // + ldapAdServer2;
		// final String ldapSearchBase = "dc=doureca,dc=local";

		final String ldapUsername = user + "@" + ldapAdServer2;
		final String ldapPassword = password;

		Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		if (ldapUsername != null) {
			env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
		}
		if (ldapPassword != null) {
			env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
		}
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapAdServer);

		try {
			// Connect with ldap
			new InitialLdapContext(env, null);

			// Connection succeeded
			// System.out.println("Connection succeeded!");
			return true;
		} catch (AuthenticationException e) {

			// Connection failed
			// System.out.println("Connection failed!");
			// e.printStackTrace();
			return false;
		}
	}

	@GET
	@Path("/getEMAILS/{id_reclamacao}")
	@Produces("application/json")
	public List<Object[]> getEMAILS(@PathParam("id_reclamacao") Integer id_reclamacao) {

		Query query_folder = entityManager.createNativeQuery(
				"select distinct e.EMAIL, 'campo' from ( " + "select b.EMAIL from RC_MOV_RECLAMACAO_PLANOS_ACCOES a "
						+ "inner join GER_UTILIZADORES b on a.RESPONSAVEL = b.ID_UTILIZADOR where a.TIPO_RESPONSAVEL = 'u' and a.ID_RECLAMACAO = "
						+ id_reclamacao + " " + "union all "
						+ "select c.EMAIL from RC_MOV_RECLAMACAO_PLANOS_ACCOES a inner join GER_GRUPO_UTZ b on a.RESPONSAVEL = b.ID_GRUPO "
						+ "inner join GER_UTILIZADORES c on a.RESPONSAVEL = c.ID_UTILIZADOR where a.TIPO_RESPONSAVEL = 'g' and a.ID_RECLAMACAO = "
						+ id_reclamacao + ") e");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getPedidosProducao")
	@Produces("application/json")
	public List<Object[]> getPedidosProducao(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LIECOD = firstMap.get("ARMAZENS");

		Query query_folder = entityManager.createNativeQuery(
				"SELECT e.proref CodRef, a.prodes1 DesRef,e.etqorilot1 Lote, e.etqnum NumEtiq, e.etqembqte QuantEtiq, e.liecod Armazem, e.empcod Localiz,"
						+ "b.UTZ_CRIA,c.NOME 'OPERARIO',d.COD_SECTOR,d.DES_SECTOR 'SECTOR', b.DATA_CRIA,CONCAT(b.COD_SECTOR_OF, + ' - ' + b.DES_SECTOR_OF) 'SECTOR_OF',p.DESCRICAO 'POSTO' "
						+ "FROM (select * from ST_PEDIDOS where ID_PEDIDO in (select MAX(ID_PEDIDO) from ST_PEDIDOS GROUP BY ETQNUM)) b INNER JOIN SILVER.dbo.SETQDE e on b.ETQNUM = e.ETQNUM "
						+ "LEFT JOIN SILVER.dbo.SDTPRA a on a.proref=e.proref "
						+ "LEFT JOIN RH_FUNCIONARIOS c on b.UTZ_CRIA = c.COD_FUNCIONARIO "
						+ "LEFT JOIN RH_SECTORES d on c.COD_SECTOR = d.COD_SECTOR "
						+ "LEFT JOIN GER_POSTOS p on p.IP_IMPRESSORA = b.IP_POSTO "
						+ "WHERE e.etqsitsto=2 AND e.etqetat=1 AND ((not " + LIECOD + " is not null) or (e.LIECOD  in ("
						+ LIECOD + ") )) ORDER BY b.DATA_CRIA,e.etqnum");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesRefe")
	@Produces("application/json")
	public List<Object[]> getRejeicoesRefe(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LINHA = firstMap.get("LINHA");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String REF = firstMap.get("REF");
		String FAM = firstMap.get("FAM");
		String LOTE = firstMap.get("LOTE");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		String objetivos_gerais = firstMap.get("objetivos_gerais");

		Query query_folder = entityManager.createNativeQuery("EXEC SILVER_BI.dbo.QUERY_REJEICOES_REF " + LINHA + ",'"
				+ DATA_INI + "','" + DATA_FIM + "','" + REF + "'," + objetivos_gerais + "," + FAM + "," + LOTE + ","
				+ AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getanalise_LOTES_LOTE")
	@Produces("application/json")
	public List<Object[]> getanalise_LOTES_LOTE(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LINHA = firstMap.get("LINHA");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String REF = firstMap.get("REF");
		String LOTE = firstMap.get("LOTE");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager
				.createNativeQuery("EXEC SILVER_BI.dbo.QUERY_LOTES_LOTE " + LINHA + ",'" + DATA_INI + "','" + DATA_FIM
						+ "','" + REF + "'," + LOTE + "," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getanalise_LOTES_DEFEITOS")
	@Produces("application/json")
	public List<Object[]> getanalise_LOTES_DEFEITOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LINHA = firstMap.get("LINHA");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String REF = firstMap.get("REF");
		String LOTE = firstMap.get("LOTE");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.QUERY_LOTES_DEFEITOS " + LINHA + ",'" + DATA_INI + "','" + DATA_FIM + "','" + REF
						+ "'," + LOTE + "," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getanalise_rejeicoes")
	@Produces("application/json")
	public List<Object[]> getanalise_rejeicoes(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LINHA = firstMap.get("LINHA");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String REF = firstMap.get("REF");
		String objetivos_gerais = firstMap.get("objetivos_gerais");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.QUERY_ANALISE_DEFEITOS " + LINHA + ",'" + DATA_INI + "','" + DATA_FIM + "','" + REF
						+ "'," + objetivos_gerais + "," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesFam_defeitos")
	@Produces("application/json")
	public List<Object[]> getRejeicoesFam_defeitos(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LINHA = firstMap.get("LINHA");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String PROREF = firstMap.get("PROREF");
		String FAM = firstMap.get("FAM");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.QUERY_REJEICOES_FAM_DEFEITOS " + LINHA + ",'" + DATA_INI + "','" + DATA_FIM + "','"
						+ PROREF + "'," + FAM + "," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesLotelista")
	@Produces("application/json")
	public List<Object[]> getRejeicoesLotelista(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LINHA = firstMap.get("LINHA");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String PROREF = firstMap.get("PROREF");
		String DEFEITO = firstMap.get("DEFEITO");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery("EXEC SILVER_BI.dbo.QUERY_REJEICOES_FAM_DEFEITOS_LOTE "
				+ LINHA + ",'" + DATA_INI + "','" + DATA_FIM + "','" + PROREF + "'," + DEFEITO + "," + AREA_PECA + " ,'"
				+ HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesdefeitos")
	@Produces("application/json")
	public List<Object[]> getRejeicoesdefeitos(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LINHA = firstMap.get("LINHA");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String PROREF = firstMap.get("PROREF");
		String FAM = firstMap.get("FAM");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.QUERY_REJEICOES_DEFEITOS " + LINHA + ",'" + DATA_INI + "','" + DATA_FIM + "','"
						+ PROREF + "'," + FAM + "," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesLinha")
	@Produces("application/json")
	public List<Object[]> getRejeicoesLinha(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LINHA = firstMap.get("LINHA");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery("EXEC SILVER_BI.dbo.QUERY_REJEICOES " + LINHA + ",'"
				+ DATA_INI + "','" + DATA_FIM + "'," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesReferencia")
	@Produces("application/json")
	public List<Object[]> getRejeicoesReferencia(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LINHA = firstMap.get("LINHA");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String DEFEITO = firstMap.get("DEFEITO");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager
				.createNativeQuery("EXEC SILVER_BI.dbo.QUERY_REJEICOES_REFERENCIA " + LINHA + ",'" + DATA_INI + "','"
						+ DATA_FIM + "','" + DEFEITO + "'," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesRefeFUNC")
	@Produces("application/json")
	public List<Object[]> getRejeicoesRefeFUNC(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String FUNC = firstMap.get("FUNC");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String REF = firstMap.get("REF");
		String AREA_PECA = firstMap.get("AREA_PECA");
		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.QUERY_REJEICOES_FUNCIONARIO_REF '" + FUNC + "','" + DATA_INI + "','" + DATA_FIM
						+ "','" + REF + "'," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesFUNCFAM")
	@Produces("application/json")
	public List<Object[]> getRejeicoesFUNCFAM(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String FUNC = firstMap.get("FUNC");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String REF = firstMap.get("REF");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.QUERY_REJEICOES_FUNCIONARIO_FAM '" + FUNC + "','" + DATA_INI + "','" + DATA_FIM
						+ "','" + REF + "'," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesFUNC")
	@Produces("application/json")
	public List<Object[]> getRejeicoesFUNC(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String FUNC = firstMap.get("FUNC");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String REF = firstMap.get("REF");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.QUERY_REJEICOES_FUNCIONARIO_FUNC '" + FUNC + "','" + DATA_INI + "','" + DATA_FIM
						+ "','" + REF + "'," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesFUNCDEF")
	@Produces("application/json")
	public List<Object[]> getRejeicoesFUNCDEF(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String FUNC = firstMap.get("FUNC");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String REF = firstMap.get("REF");
		String FAM = firstMap.get("FAM");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.QUERY_REJEICOES_FUNCIONARIO_DEF '" + FUNC + "','" + DATA_INI + "','" + DATA_FIM
						+ "','" + REF + "'," + FAM + "," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getRejeicoesLote")
	@Produces("application/json")
	public List<Object[]> getRejeicoesLote(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String LINHA = firstMap.get("LINHA");
		String REF = firstMap.get("REF");
		String DEFEITO = firstMap.get("DEFEITO");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String AREA_PECA = firstMap.get("AREA_PECA");

		String HORA_INI = firstMap.get("HORA_INI");
		String HORA_FIM = firstMap.get("HORA_FIM");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER_BI.dbo.QUERY_REJEICOES_LOTE " + LINHA + ",'" + DATA_INI + "','" + DATA_FIM + "','"
						+ DEFEITO + "','" + REF + "'," + AREA_PECA + " ,'" + HORA_INI + "','" + HORA_FIM + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/getBDS/{bd}")
	@Produces("application/json")
	public List<Object[]> getBDS(@PathParam("bd") String bd) {

		Query query_folder = entityManager
				.createNativeQuery(" SELECT name,'' as campo FROM " + bd + ".sys.Tables where lob_data_space_id != 1");

		List<Object[]> bases_de_dados = query_folder.getResultList();

		return bases_de_dados;
	}

	/************************************* AB_DIC_LINHA */
	@POST
	@Path("/createAB_DIC_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_LINHA insertAB_DIC_LINHA(final AB_DIC_LINHA data) {
		return dao12.create(data);
	}

	@GET
	@Path("/getAB_DIC_LINHAbyid_linha/{id}")
	@Produces("application/json")
	public List<AB_DIC_LINHA> getAB_DIC_LINHAbyid_linha(@PathParam("id") Integer id) {
		return dao12.getbyid(id);
	}

	@GET
	@Path("/getAB_DIC_LINHA")
	@Produces("application/json")
	public List<AB_DIC_LINHA> getAB_DIC_LINHA() {
		return dao12.getall();
	}

	@DELETE
	@Path("/deleteAB_DIC_LINHA/{id}")
	public void deleteAB_DIC_LINHA(@PathParam("id") Integer id) {
		AB_DIC_LINHA AB_DIC_LINHA = new AB_DIC_LINHA();
		AB_DIC_LINHA.setID_LINHA(id);
		dao12.delete(AB_DIC_LINHA);
	}

	@PUT
	@Path("/updateAB_DIC_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_LINHA updateAB_DIC_LINHA(final AB_DIC_LINHA AB_DIC_LINHA) {
		AB_DIC_LINHA.setID_LINHA(AB_DIC_LINHA.getID_LINHA());
		return dao12.update(AB_DIC_LINHA);
	}

	/************************************* AB_DIC_ZONA */
	@POST
	@Path("/createAB_DIC_ZONA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_ZONA insertAB_DIC_ZONA(final AB_DIC_ZONA data) {
		return dao13.create(data);
	}

	@GET
	@Path("/getAB_DIC_ZONAbyid_zona/{id}")
	@Produces("application/json")
	public List<AB_DIC_ZONA> getAB_DIC_ZONAbyid_zona(@PathParam("id") Integer id) {
		return dao13.getbyid(id);
	}

	@GET
	@Path("/getAB_DIC_ZONA")
	@Produces("application/json")
	public List<AB_DIC_ZONA> getAB_DIC_ZONA() {
		return dao13.getall();
	}

	@DELETE
	@Path("/deleteAB_DIC_ZONA/{id}")
	public void deleteAB_DIC_ZONA(@PathParam("id") Integer id) {
		AB_DIC_ZONA AB_DIC_ZONA = new AB_DIC_ZONA();
		AB_DIC_ZONA.setID_ZONA(id);
		dao13.delete(AB_DIC_ZONA);
	}

	@PUT
	@Path("/updateAB_DIC_ZONA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_ZONA updateAB_DIC_ZONA(final AB_DIC_ZONA AB_DIC_ZONA) {
		AB_DIC_ZONA.setID_ZONA(AB_DIC_ZONA.getID_ZONA());
		return dao13.update(AB_DIC_ZONA);
	}

	/******************************************* AB_DIC_UNIDADE_MEDIDA **/

	@POST
	@Path("/createAB_DIC_UNIDADE_MEDIDA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_UNIDADE_MEDIDA insertAB_DIC_UNIDADE_MEDIDA(final AB_DIC_UNIDADE_MEDIDA data) {
		return dao14.create(data);
	}

	@GET
	@Path("/getAB_DIC_UNIDADE_MEDIDA")
	@Produces("application/json")
	public List<AB_DIC_UNIDADE_MEDIDA> getAB_DIC_UNIDADE_MEDIDA() {
		return dao14.getall();
	}

	@DELETE
	@Path("/deleteAB_DIC_UNIDADE_MEDIDA/{id}")
	public void deleteAB_DIC_UNIDADE_MEDIDA(@PathParam("id") Integer id) {
		AB_DIC_UNIDADE_MEDIDA AB_DIC_UNIDADE_MEDIDA = new AB_DIC_UNIDADE_MEDIDA();
		AB_DIC_UNIDADE_MEDIDA.setID_MEDIDA(id);
		dao14.delete(AB_DIC_UNIDADE_MEDIDA);
	}

	@PUT
	@Path("/updateAB_DIC_UNIDADE_MEDIDA")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_UNIDADE_MEDIDA updateAB_DIC_UNIDADE_MEDIDA(final AB_DIC_UNIDADE_MEDIDA AB_DIC_UNIDADE_MEDIDA) {
		AB_DIC_UNIDADE_MEDIDA.setID_MEDIDA(AB_DIC_UNIDADE_MEDIDA.getID_MEDIDA());
		return dao14.update(AB_DIC_UNIDADE_MEDIDA);
	}

	/******************************************* AB_DIC_TIPO_ADICAO **/

	@POST
	@Path("/createAB_DIC_TIPO_ADICAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TIPO_ADICAO insertAB_DIC_TIPO_ADICAO(final AB_DIC_TIPO_ADICAO data) {
		return dao16.create(data);
	}

	@POST
	@Path("/getAB_DIC_TIPO_ADICAO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_DIC_TIPO_ADICAO> getAB_DIC_TIPO_ADICAO(final ArrayList<String> classif) {
		return dao16.getall(classif);
	}

	@DELETE
	@Path("/deleteAB_DIC_TIPO_ADICAO/{id}")
	public void deleteAB_DIC_TIPO_ADICAO(@PathParam("id") Integer id) {
		AB_DIC_TIPO_ADICAO AB_DIC_TIPO_ADICAO = new AB_DIC_TIPO_ADICAO();
		AB_DIC_TIPO_ADICAO.setID_TIPO_ADICAO(id);
		dao16.delete(AB_DIC_TIPO_ADICAO);
	}

	@PUT
	@Path("/updateAB_DIC_TIPO_ADICAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TIPO_ADICAO updateAB_DIC_TIPO_ADICAO(final AB_DIC_TIPO_ADICAO AB_DIC_TIPO_ADICAO) {
		AB_DIC_TIPO_ADICAO.setID_TIPO_ADICAO(AB_DIC_TIPO_ADICAO.getID_TIPO_ADICAO());
		return dao16.update(AB_DIC_TIPO_ADICAO);
	}

	/******************************************* AB_DIC_TIPO_MANUTENCAO **/

	@POST
	@Path("/createAB_DIC_TIPO_MANUTENCAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TIPO_MANUTENCAO insertAB_DIC_TIPO_MANUTENCAO(final AB_DIC_TIPO_MANUTENCAO data) {
		return dao17.create(data);
	}

	@POST
	@Path("/getAB_DIC_TIPO_MANUTENCAO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_DIC_TIPO_MANUTENCAO> getAB_DIC_TIPO_MANUTENCAO(final ArrayList<String> classif) {
		return dao17.getall(classif);
	}

	@POST
	@Path("/getAB_DIC_TIPO_MANUTENCAO2")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_DIC_TIPO_MANUTENCAO> getAB_DIC_TIPO_MANUTENCAO2(final ArrayList<String> classif) {
		return dao17.getall2(classif);
	}

	@DELETE
	@Path("/deleteAB_DIC_TIPO_MANUTENCAO/{id}")
	public void deleteAB_DIC_TIPO_MANUTENCAO(@PathParam("id") Integer id) {
		AB_DIC_TIPO_MANUTENCAO AB_DIC_TIPO_MANUTENCAO = new AB_DIC_TIPO_MANUTENCAO();
		AB_DIC_TIPO_MANUTENCAO.setID_TIPO_MANUTENCAO(id);
		dao17.delete(AB_DIC_TIPO_MANUTENCAO);
	}

	@PUT
	@Path("/updateAB_DIC_TIPO_MANUTENCAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TIPO_MANUTENCAO updateAB_DIC_TIPO_MANUTENCAO(final AB_DIC_TIPO_MANUTENCAO AB_DIC_TIPO_MANUTENCAO) {
		AB_DIC_TIPO_MANUTENCAO.setID_TIPO_MANUTENCAO(AB_DIC_TIPO_MANUTENCAO.getID_TIPO_MANUTENCAO());
		return dao17.update(AB_DIC_TIPO_MANUTENCAO);
	}

	/******************************************* AB_DIC_TIPO_OPERACAO **/

	@POST
	@Path("/createAB_DIC_TIPO_OPERACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TIPO_OPERACAO insertAB_DIC_TIPO_OPERACAO(final AB_DIC_TIPO_OPERACAO data) {
		return dao18.create(data);
	}

	@POST
	@Path("/getAB_DIC_TIPO_OPERACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_DIC_TIPO_OPERACAO> getAB_DIC_TIPO_OPERACAO(final ArrayList<String> classif) {
		return dao18.getall(classif);
	}

	@DELETE
	@Path("/deleteAB_DIC_TIPO_OPERACAO/{id}")
	public void deleteAB_DIC_TIPO_OPERACAO(@PathParam("id") Integer id) {
		AB_DIC_TIPO_OPERACAO AB_DIC_TIPO_OPERACAO = new AB_DIC_TIPO_OPERACAO();
		AB_DIC_TIPO_OPERACAO.setID_TIPO_OPERACAO(id);
		dao18.delete(AB_DIC_TIPO_OPERACAO);
	}

	@PUT
	@Path("/updateAB_DIC_TIPO_OPERACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TIPO_OPERACAO updateAB_DIC_TIPO_OPERACAO(final AB_DIC_TIPO_OPERACAO AB_DIC_TIPO_OPERACAO) {
		AB_DIC_TIPO_OPERACAO.setID_TIPO_OPERACAO(AB_DIC_TIPO_OPERACAO.getID_TIPO_OPERACAO());
		return dao18.update(AB_DIC_TIPO_OPERACAO);
	}

	/******************************************* AB_DIC_TURNO **/

	@POST
	@Path("/createAB_DIC_TURNO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TURNO insertAB_DIC_TURNO(final AB_DIC_TURNO data) {
		return dao19.create(data);
	}

	@GET
	@Path("/getAB_DIC_TURNO")
	@Produces("application/json")
	public List<AB_DIC_TURNO> getAB_DIC_TURNO() {
		return dao19.getall();
	}

	@DELETE
	@Path("/deleteAB_DIC_TURNO/{id}")
	public void deleteAB_DIC_TURNO(@PathParam("id") Integer id) {
		AB_DIC_TURNO AB_DIC_TURNO = new AB_DIC_TURNO();
		AB_DIC_TURNO.setID_TURNO(id);
		dao19.delete(AB_DIC_TURNO);
	}

	@PUT
	@Path("/updateAB_DIC_TURNO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_TURNO updateAB_DIC_TURNO(final AB_DIC_TURNO AB_DIC_TURNO) {
		AB_DIC_TURNO.setID_TURNO(AB_DIC_TURNO.getID_TURNO());
		return dao19.update(AB_DIC_TURNO);
	}

	/***************************************** AB_DIC_BANHO_ADITIVO *************/

	@POST
	@Path("/createAB_DIC_BANHO_ADITIVO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_BANHO_ADITIVO insertAAB_DIC_BANHO_ADITIVO(final AB_DIC_BANHO_ADITIVO data) {
		return dao20.create(data);
	}

	@GET
	@Path("/getAB_DIC_BANHO_ADITIVObyid_banho/{id}")
	@Produces("application/json")
	public List<AB_DIC_BANHO_ADITIVO> getAB_DIC_BANHO_ADITIVObyid_banho(@PathParam("id") Integer id) {
		return dao20.getbyid(id);
	}

	@GET
	@Path("/getAB_DIC_BANHO_ADITIVObyid_aditivo/{id}")
	@Produces("application/json")
	public List<AB_DIC_BANHO_ADITIVO> getAB_DIC_BANHO_ADITIVObyid_aditivo(@PathParam("id") Integer id) {
		return dao20.getbyid_aditivo(id);
	}

	@GET
	@Path("/getAB_DIC_BANHO_ADITIVO")
	@Produces("application/json")
	public List<AB_DIC_BANHO_ADITIVO> getAB_DIC_BANHO_ADITIVO() {
		return dao20.allEntries();
	}

	@DELETE
	@Path("/deleteAB_DIC_BANHO_ADITIVO/{id}")
	public void deleteAB_DIC_BANHO_ADITIVO(@PathParam("id") Integer id) {
		AB_DIC_BANHO_ADITIVO AB_DIC_BANHO_ADITIVO = new AB_DIC_BANHO_ADITIVO();
		AB_DIC_BANHO_ADITIVO.setID_BANHO_ADITIVO(id);
		dao20.delete(AB_DIC_BANHO_ADITIVO);
	}

	@PUT
	@Path("/updateAB_DIC_BANHO_ADITIVO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_BANHO_ADITIVO updateAB_DIC_BANHO_ADITIVO(final AB_DIC_BANHO_ADITIVO AB_DIC_BANHO_ADITIVO) {
		AB_DIC_BANHO_ADITIVO.setID_BANHO_ADITIVO(AB_DIC_BANHO_ADITIVO.getID_BANHO_ADITIVO());
		return dao20.update(AB_DIC_BANHO_ADITIVO);
	}

	/******************************************* AB_MOV_REG_PARAM_OPERACAO *******************/
	@POST
	@Path("/createAB_MOV_REG_PARAM_OPERACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_REG_PARAM_OPERACAO insertAAB_MOV_REG_PARAM_OPERACAO(final AB_MOV_REG_PARAM_OPERACAO data) {
		return dao21.create(data);
	}

	@GET
	@Path("/getAB_MOV_REG_PARAM_OPERACAObyid/{id}")
	@Produces("application/json")
	public List<AB_MOV_REG_PARAM_OPERACAO> getAB_MOV_REG_PARAM_OPERACAObyid(@PathParam("id") Integer id) {
		return dao21.getbyid(id);
	}

	@GET
	@Path("/getAB_MOV_REG_PARAM_OPERACAO")
	@Produces("application/json")
	public List<AB_MOV_REG_PARAM_OPERACAO> getAB_MOV_REG_PARAM_OPERACAO() {
		return dao21.getall();
	}

	@DELETE
	@Path("/deleteAB_MOV_REG_PARAM_OPERACAO/{id}")
	public void deleteAB_MOV_REG_PARAM_OPERACAO(@PathParam("id") Integer id) {
		AB_MOV_REG_PARAM_OPERACAO AB_MOV_REG_PARAM_OPERACAO = new AB_MOV_REG_PARAM_OPERACAO();
		AB_MOV_REG_PARAM_OPERACAO.setID_MANUTENCAO_CAB(id);
		dao21.delete(AB_MOV_REG_PARAM_OPERACAO);
	}

	@PUT
	@Path("/updateAB_MOV_REG_PARAM_OPERACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_REG_PARAM_OPERACAO updateAB_MOV_REG_PARAM_OPERACAO(
			final AB_MOV_REG_PARAM_OPERACAO AB_MOV_REG_PARAM_OPERACAO) {
		AB_MOV_REG_PARAM_OPERACAO.setID_MANUTENCAO_CAB(AB_MOV_REG_PARAM_OPERACAO.getID_MANUTENCAO_CAB());
		return dao21.update(AB_MOV_REG_PARAM_OPERACAO);
	}

	/************************************* RH_DIC_CACIFOS */
	@POST
	@Path("/createRH_DIC_CACIFOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_CACIFOS insertRH_DIC_CACIFOS(final RH_DIC_CACIFOS data) {
		return dao69.create(data);
	}

	@GET
	@Path("/getRH_DIC_CACIFOS")
	@Produces("application/json")
	public List<RH_DIC_CACIFOS> getRH_DIC_CACIFOS() {
		return dao69.getall();
	}

	@GET
	@Path("/getRH_DIC_CACIFOS2")
	@Produces("application/json")
	public List<RH_DIC_CACIFOS> getRH_DIC_CACIFOS2() {
		return dao69.getall_livre();
	}

	@GET
	@Path("/getRH_DIC_CACIFOSbyid_reclamacao/{id}")
	@Produces("application/json")
	public List<RH_DIC_CACIFOS> getRH_DIC_CACIFOSbyid(@PathParam("id") Integer id) {
		return dao69.getbyid(id);
	}

	@DELETE
	@Path("/deleteRH_DIC_CACIFOS/{id}")
	public void deleteRH_DIC_CACIFOS(@PathParam("id") Integer id) {
		RH_DIC_CACIFOS RH_DIC_CACIFOS = new RH_DIC_CACIFOS();
		RH_DIC_CACIFOS.setID(id);
		dao69.delete(RH_DIC_CACIFOS);
	}

	@PUT
	@Path("/updateRH_DIC_CACIFOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_CACIFOS updateRH_DIC_CACIFOS(final RH_DIC_CACIFOS RH_DIC_CACIFOS) {
		RH_DIC_CACIFOS.setID(RH_DIC_CACIFOS.getID());
		return dao69.update(RH_DIC_CACIFOS);
	}

	@GET
	@Path("/atualizacaCacifoUtilizador/{id}/{utilizador}")
	@Produces("application/json")
	public int atualizacaCacifoUtilizador(@PathParam("id") Integer id, @PathParam("utilizador") Integer utilizador) {
		return entityManager
				.createNativeQuery(
						"UPDATE RH_FUNCIONARIOS SET NUM_CACIFO = " + id + " WHERE COD_FUNCIONARIO =" + utilizador + "")
				.executeUpdate();
	}

	@GET
	@Path("/atualizacaCacifoUtilizador/{id}/null")
	@Produces("application/json")
	public int atualizacaCacifoUtilizador(@PathParam("id") Integer id) {
		return entityManager
				.createNativeQuery("UPDATE RH_FUNCIONARIOS SET NUM_CACIFO = null WHERE COD_FUNCIONARIO =" + id + "")
				.executeUpdate();
	}

	@GET
	@Path("/atualizacaCacifo/{id}/{utilizador}")
	@Produces("application/json")
	public int atualizacaCacifo(@PathParam("id") Integer id, @PathParam("utilizador") Integer utilizador) {
		return entityManager
				.createNativeQuery("UPDATE RH_DIC_CACIFOS SET UTILIZADOR = " + utilizador + " WHERE ID =" + id + "")
				.executeUpdate();
	}

	@GET
	@Path("/atualizacaCacifo/{id}/null")
	@Produces("application/json")
	public int atualizacaCacifo(@PathParam("id") Integer id) {
		return entityManager.createNativeQuery("UPDATE RH_DIC_CACIFOS SET UTILIZADOR = null WHERE ID =" + id + "")
				.executeUpdate();
	}

	/******************************************* RH_DIC_TIPO_CACIFO *******************/
	@POST
	@Path("/createRH_DIC_TIPO_CACIFO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_TIPO_CACIFO insertARH_DIC_TIPO_CACIFO(final RH_DIC_TIPO_CACIFO data) {
		return dao70.create(data);
	}

	@GET
	@Path("/getRH_DIC_TIPO_CACIFObyid/{id}")
	@Produces("application/json")
	public List<RH_DIC_TIPO_CACIFO> getRH_DIC_TIPO_CACIFObyid(@PathParam("id") Integer id) {
		return dao70.getbyid(id);
	}

	@GET
	@Path("/getRH_DIC_TIPO_CACIFO")
	@Produces("application/json")
	public List<RH_DIC_TIPO_CACIFO> getRH_DIC_TIPO_CACIFO() {
		return dao70.getall();
	}

	@DELETE
	@Path("/deleteRH_DIC_TIPO_CACIFO/{id}")
	public void deleteRH_DIC_TIPO_CACIFO(@PathParam("id") Integer id) {
		RH_DIC_TIPO_CACIFO RH_DIC_TIPO_CACIFO = new RH_DIC_TIPO_CACIFO();
		RH_DIC_TIPO_CACIFO.setCOD_TIPO(id);
		dao70.delete(RH_DIC_TIPO_CACIFO);
	}

	@PUT
	@Path("/updateRH_DIC_TIPO_CACIFO")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_DIC_TIPO_CACIFO updateRH_DIC_TIPO_CACIFO(final RH_DIC_TIPO_CACIFO RH_DIC_TIPO_CACIFO) {
		RH_DIC_TIPO_CACIFO.setCOD_TIPO(RH_DIC_TIPO_CACIFO.getCOD_TIPO());
		return dao70.update(RH_DIC_TIPO_CACIFO);
	}

	/******************************************* RH_TIPOS_PAUSA *******************/
	@POST
	@Path("/createRH_TIPOS_PAUSA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_TIPOS_PAUSA insertARH_TIPOS_PAUSA(final RH_TIPOS_PAUSA data) {
		return dao71.create(data);
	}

	@GET
	@Path("/getRH_TIPOS_PAUSAbyid/{id}")
	@Produces("application/json")
	public List<RH_TIPOS_PAUSA> getRH_TIPOS_PAUSAbyid(@PathParam("id") Integer id) {
		return dao71.getbyid(id);
	}

	@GET
	@Path("/getAllPAUSASSILVER")
	@Produces("application/json")
	public List<HashMap<String, String>> getAllPAUSASSILVER() throws SQLException {
		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getTipoFalta(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getRH_TIPOS_PAUSA")
	@Produces("application/json")
	public List<RH_TIPOS_PAUSA> getRH_TIPOS_PAUSA() {
		return dao71.getall();
	}

	@DELETE
	@Path("/deleteRH_TIPOS_PAUSA/{id}")
	public void deleteRH_TIPOS_PAUSA(@PathParam("id") Integer id) {
		RH_TIPOS_PAUSA RH_TIPOS_PAUSA = new RH_TIPOS_PAUSA();
		RH_TIPOS_PAUSA.setID(id);
		dao71.delete(RH_TIPOS_PAUSA);
	}

	@PUT
	@Path("/updateRH_TIPOS_PAUSA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_TIPOS_PAUSA updateRH_TIPOS_PAUSA(final RH_TIPOS_PAUSA RH_TIPOS_PAUSA) {
		RH_TIPOS_PAUSA.setID(RH_TIPOS_PAUSA.getID());
		return dao71.update(RH_TIPOS_PAUSA);
	}

	/******************************************* RH_PAUSAS *******************/
	@POST
	@Path("/createRH_PAUSAS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_PAUSAS insertARH_PAUSAS(final RH_PAUSAS data) {
		return dao72.create(data);
	}

	@GET
	@Path("/getRH_PAUSASbyid/{id}")
	@Produces("application/json")
	public List<RH_PAUSAS> getRH_PAUSASbyid(@PathParam("id") Integer id) {
		return dao72.getbyid(id);
	}

	@GET
	@Path("/getRH_PAUSAS")
	@Produces("application/json")
	public List<RH_PAUSAS> getRH_PAUSAS() {
		return dao72.getall();
	}

	@DELETE
	@Path("/deleteRH_PAUSAS/{id}")
	public void deleteRH_PAUSAS(@PathParam("id") Integer id) {
		RH_PAUSAS RH_PAUSAS = new RH_PAUSAS();
		RH_PAUSAS.setID(id);
		dao72.delete(RH_PAUSAS);
	}

	@PUT
	@Path("/updateRH_PAUSAS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_PAUSAS updateRH_PAUSAS(final RH_PAUSAS RH_PAUSAS) {
		RH_PAUSAS.setID(RH_PAUSAS.getID());
		return dao72.update(RH_PAUSAS);
	}

	/************************************* AT_ACCOES */
	@POST
	@Path("/createAT_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public AT_ACCOES insertAT_ACCOES(final AT_ACCOES data) {
		return dao73.create(data);
	}

	@GET
	@Path("/getAT_ACCOESbyid/{id}")
	@Produces("application/json")
	public List<AT_ACCOES> getAT_ACCOESbyid(@PathParam("id") Integer id) {
		return dao73.getbyid(id);
	}

	@GET
	@Path("/getAT_ACCOES")
	@Produces("application/json")
	public List<AT_ACCOES> getAT_ACCOES() {
		return dao73.getall();
	}

	@DELETE
	@Path("/deleteAT_ACCOES/{id}")
	public void deleteAT_ACCOES(@PathParam("id") Integer id) {
		AT_ACCOES AT_ACCOES = new AT_ACCOES();
		AT_ACCOES.setID_ACCAO(id);
		dao73.delete(AT_ACCOES);
	}

	@PUT
	@Path("/updateAT_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public AT_ACCOES updateAT_ACCOES(final AT_ACCOES AT_ACCOES) {
		AT_ACCOES.setID_ACCAO(AT_ACCOES.getID_ACCAO());
		return dao73.update(AT_ACCOES);
	}

	/************************************* AT_ENTREVISTAS */
	@POST
	@Path("/createAT_ENTREVISTAS")
	@Consumes("*/*")
	@Produces("application/json")
	public AT_ENTREVISTAS insertAT_ENTREVISTAS(final AT_ENTREVISTAS data) {
		return dao74.create(data);
	}

	@GET
	@Path("/getAT_ENTREVISTASbyid/{id}")
	@Produces("application/json")
	public List<AT_ENTREVISTAS> getAT_ENTREVISTASbyid(@PathParam("id") Integer id) {
		return dao74.getbyid(id);
	}

	@GET
	@Path("/getAT_ENTREVISTAS")
	@Produces("application/json")
	public List<AT_ENTREVISTAS> getAT_ENTREVISTAS() {
		return dao74.getall();
	}

	@DELETE
	@Path("/deleteAT_ENTREVISTAS/{id}")
	public void deleteAT_ENTREVISTAS(@PathParam("id") Integer id) {
		AT_ENTREVISTAS AT_ENTREVISTAS = new AT_ENTREVISTAS();
		AT_ENTREVISTAS.setID_ENTREVISTA(id);
		dao74.delete(AT_ENTREVISTAS);
	}

	@PUT
	@Path("/updateAT_ENTREVISTAS")
	@Consumes("*/*")
	@Produces("application/json")
	public AT_ENTREVISTAS updateAT_ENTREVISTAS(final AT_ENTREVISTAS AT_ENTREVISTAS) {
		AT_ENTREVISTAS.setID_ENTREVISTA(AT_ENTREVISTAS.getID_ENTREVISTA());
		return dao74.update(AT_ENTREVISTAS);
	}

	/************************************* AT_OCORRENCIAS */
	@POST
	@Path("/createAT_OCORRENCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public AT_OCORRENCIAS insertAT_OCORRENCIAS(final AT_OCORRENCIAS data) {
		return dao75.create(data);
	}

	@GET
	@Path("/getAT_OCORRENCIAbydata/{dataac}")
	@Produces("application/json")
	public List<AT_OCORRENCIAS> getAT_OCORRENCIAbydata(@PathParam("dataac") String dataac) {
		return dao75.getbydata(dataac);
	}

	@GET
	@Path("/getAT_OCORRENCIAbyid/{id}")
	@Produces("application/json")
	public List<AT_OCORRENCIAS> getAT_OCORRENCIASbyid(@PathParam("id") Integer id) {
		return dao75.getbyid(id);
	}

	@GET
	@Path("/getAT_OCORRENCIA")
	@Produces("application/json")
	public List<AT_OCORRENCIAS> getAT_OCORRENCIAS() {
		return dao75.getall();
	}

	@DELETE
	@Path("/deleteAT_OCORRENCIA/{id}")
	public void deleteAT_OCORRENCIAS(@PathParam("id") Integer id) {
		AT_OCORRENCIAS AT_OCORRENCIAS = new AT_OCORRENCIAS();
		AT_OCORRENCIAS.setID_OCORRENCIA(id);
		dao75.delete(AT_OCORRENCIAS);
	}

	@PUT
	@Path("/updateAT_OCORRENCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public AT_OCORRENCIAS updateAT_OCORRENCIAS(final AT_OCORRENCIAS AT_OCORRENCIAS) {
		AT_OCORRENCIAS.setID_OCORRENCIA(AT_OCORRENCIAS.getID_OCORRENCIA());
		return dao75.update(AT_OCORRENCIAS);
	}

	/************************************* AT_TESTEMUNHAS */
	@POST
	@Path("/createAT_TESTEMUNHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public AT_TESTEMUNHAS insertAT_TESTEMUNHAS(final AT_TESTEMUNHAS data) {
		return dao76.create(data);
	}

	@GET
	@Path("/getAT_TESTEMUNHASbyid/{id}")
	@Produces("application/json")
	public List<AT_TESTEMUNHAS> getAT_TESTEMUNHASbyid(@PathParam("id") Integer id) {
		return dao76.getbyid(id);
	}

	@GET
	@Path("/getAT_TESTEMUNHAS")
	@Produces("application/json")
	public List<AT_TESTEMUNHAS> getAT_TESTEMUNHAS() {
		return dao76.getall();
	}

	@DELETE
	@Path("/deleteAT_TESTEMUNHAS/{id}")
	public void deleteAT_TESTEMUNHAS(@PathParam("id") Integer id) {
		AT_TESTEMUNHAS AT_TESTEMUNHAS = new AT_TESTEMUNHAS();
		AT_TESTEMUNHAS.setID_TESTEMUNHA(id);
		dao76.delete(AT_TESTEMUNHAS);
	}

	@PUT
	@Path("/updateAT_TESTEMUNHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public AT_TESTEMUNHAS updateAT_TESTEMUNHAS(final AT_TESTEMUNHAS AT_TESTEMUNHAS) {
		AT_TESTEMUNHAS.setID_TESTEMUNHA(AT_TESTEMUNHAS.getID_TESTEMUNHA());
		return dao76.update(AT_TESTEMUNHAS);
	}

	/************************************* PLANEAMENTO_LINHAS */
	@POST
	@Path("/createPLANEAMENTO_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PLANEAMENTO_LINHAS insertPLANEAMENTO_LINHAS(final PLANEAMENTO_LINHAS data) {
		return dao77.create(data);
	}

	@GET
	@Path("/getPLANEAMENTO_LINHASbyid/{id}")
	@Produces("application/json")
	public List<PLANEAMENTO_LINHAS> getPLANEAMENTO_LINHASbyid(@PathParam("id") Integer id) {
		return dao77.getbyid(id);
	}

	@GET
	@Path("/getPLANEAMENTO_LINHAS")
	@Produces("application/json")
	public List<PLANEAMENTO_LINHAS> getPLANEAMENTO_LINHAS() {
		return dao77.getall();
	}

	@DELETE
	@Path("/deletePLANEAMENTO_LINHAS/{id}")
	public void deletePLANEAMENTO_LINHAS(@PathParam("id") Integer id) {
		PLANEAMENTO_LINHAS PLANEAMENTO_LINHAS = new PLANEAMENTO_LINHAS();
		PLANEAMENTO_LINHAS.setID_LINHA(id);
		dao77.delete(PLANEAMENTO_LINHAS);
	}

	@PUT
	@Path("/updatePLANEAMENTO_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PLANEAMENTO_LINHAS updatePLANEAMENTO_LINHAS(final PLANEAMENTO_LINHAS PLANEAMENTO_LINHAS) {
		PLANEAMENTO_LINHAS.setID_LINHA(PLANEAMENTO_LINHAS.getID_LINHA());
		return dao77.update(PLANEAMENTO_LINHAS);
	}

	/************************************* PLANEAMENTO_CAB */
	@POST
	@Path("/createPLANEAMENTO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PLANEAMENTO_CAB insertPLANEAMENTO_CAB(final PLANEAMENTO_CAB data) {
		return dao78.create(data);
	}

	@GET
	@Path("/getPLANEAMENTO_CABbyid/{id}")
	@Produces("application/json")
	public List<PLANEAMENTO_CAB> getPLANEAMENTO_CABbyid(@PathParam("id") Integer id) {
		return dao78.getbyid(id);
	}

	@GET
	@Path("/getPLANEAMENTO_CABbyverificaseexiste/{id}/{ano}/{semana}/{linha}")
	@Produces("application/json")
	public List<PLANEAMENTO_CAB> getPLANEAMENTO_CABbyverificaseexiste(@PathParam("id") Integer id,
			@PathParam("ano") Integer ano, @PathParam("semana") Integer semana, @PathParam("linha") Integer linha) {
		return dao78.verificaseexiste(id, ano, semana, linha);
	}

	@GET
	@Path("/getPLANEAMENTO_CAB")
	@Produces("application/json")
	public List<PLANEAMENTO_CAB> getPLANEAMENTO_CAB() {
		return dao78.getall();
	}

	@DELETE
	@Path("/deletePLANEAMENTO_CAB/{id}")
	public void deletePLANEAMENTO_CAB(@PathParam("id") Integer id) {
		PLANEAMENTO_CAB PLANEAMENTO_CAB = new PLANEAMENTO_CAB();
		PLANEAMENTO_CAB.setID_PLANEAMENTO(id);
		dao78.delete(PLANEAMENTO_CAB);
	}

	@PUT
	@Path("/updatePLANEAMENTO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PLANEAMENTO_CAB updatePLANEAMENTO_CAB(final PLANEAMENTO_CAB PLANEAMENTO_CAB) {
		PLANEAMENTO_CAB.setID_PLANEAMENTO(PLANEAMENTO_CAB.getID_PLANEAMENTO());
		return dao78.update(PLANEAMENTO_CAB);
	}

	@POST
	@Path("/ANALISE_PLANEAMENTO_BARRAS")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> ANALISE_PLANEAMENTO_BARRAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String SEMANA = firstMap.get("SEMANA");
		String LINHA = firstMap.get("LINHA");

		Query query_folder = entityManager
				.createNativeQuery("EXEC ANALISE_PLANEAMENTO_BARRAS " + SEMANA + ", " + ANO + ", " + LINHA);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* TIPOS_OCORRENCIA */
	@POST
	@Path("/createTIPOS_OCORRENCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public TIPOS_OCORRENCIA insertTIPOS_OCORRENCIA(final TIPOS_OCORRENCIA data) {
		return dao79.create(data);
	}

	@GET
	@Path("/getTIPOS_OCORRENCIAbyid/{id}")
	@Produces("application/json")
	public List<TIPOS_OCORRENCIA> getTIPOS_OCORRENCIAbyid(@PathParam("id") Integer id) {
		return dao79.getbyid(id);
	}

	@GET
	@Path("/getTIPOS_OCORRENCIA")
	@Produces("application/json")
	public List<TIPOS_OCORRENCIA> getTIPOS_OCORRENCIA() {
		return dao79.getall();
	}

	@DELETE
	@Path("/deleteTIPOS_OCORRENCIA/{id}")
	public void deleteTIPOS_OCORRENCIA(@PathParam("id") Integer id) {
		TIPOS_OCORRENCIA TIPOS_OCORRENCIA = new TIPOS_OCORRENCIA();
		TIPOS_OCORRENCIA.setID_TIPO(id);
		dao79.delete(TIPOS_OCORRENCIA);
	}

	@PUT
	@Path("/updateTIPOS_OCORRENCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public TIPOS_OCORRENCIA updateTIPOS_OCORRENCIA(final TIPOS_OCORRENCIA TIPOS_OCORRENCIA) {
		TIPOS_OCORRENCIA.setID_TIPO(TIPOS_OCORRENCIA.getID_TIPO());
		return dao79.update(TIPOS_OCORRENCIA);
	}

	/************************************* CAPACIDADE_LINHA */
	@POST
	@Path("/createCAPACIDADE_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public CAPACIDADE_LINHA insertCAPACIDADE_LINHA(final CAPACIDADE_LINHA data) {
		return dao80.create(data);
	}

	@GET
	@Path("/getCAPACIDADE_LINHAbyid_linha/{id}")
	@Produces("application/json")
	public List<CAPACIDADE_LINHA> getCAPACIDADE_LINHAbyid_linha(@PathParam("id") Integer id) {
		return dao80.getbyidlinha(id);
	}

	@GET
	@Path("/getCAPACIDADE_LINHAbyid/{id}")
	@Produces("application/json")
	public List<CAPACIDADE_LINHA> getCAPACIDADE_LINHAbyid(@PathParam("id") Integer id) {
		return dao80.getbyid(id);
	}

	@GET
	@Path("/getCAPACIDADE_LINHA")
	@Produces("application/json")
	public List<CAPACIDADE_LINHA> getCAPACIDADE_LINHA() {
		return dao80.getall();
	}

	@DELETE
	@Path("/deleteCAPACIDADE_LINHA/{id}")
	public void deleteCAPACIDADE_LINHA(@PathParam("id") Integer id) {
		CAPACIDADE_LINHA CAPACIDADE_LINHA = new CAPACIDADE_LINHA();
		CAPACIDADE_LINHA.setID_CAPACIDADE(id);
		dao80.delete(CAPACIDADE_LINHA);
	}

	@PUT
	@Path("/updateCAPACIDADE_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public CAPACIDADE_LINHA updateCAPACIDADE_LINHA(final CAPACIDADE_LINHA CAPACIDADE_LINHA) {
		CAPACIDADE_LINHA.setID_CAPACIDADE(CAPACIDADE_LINHA.getID_CAPACIDADE());
		return dao80.update(CAPACIDADE_LINHA);
	}

	/******************************************* RH_ESTADOS_FUNC *******************/
	@POST
	@Path("/createRH_ESTADOS_FUNC")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_ESTADOS_FUNC insertARH_ESTADOS_FUNC(final RH_ESTADOS_FUNC data) {
		return dao64.create(data);
	}

	@GET
	@Path("/getRH_ESTADOS_FUNCbyid/{id}")
	@Produces("application/json")
	public List<RH_ESTADOS_FUNC> getRH_ESTADOS_FUNCbyid(@PathParam("id") Integer id) {
		return dao64.getbyid(id);
	}

	@GET
	@Path("/getRH_ESTADOS_FUNC")
	@Produces("application/json")
	public List<RH_ESTADOS_FUNC> getRH_ESTADOS_FUNC() {
		return dao64.getall();
	}

	@DELETE
	@Path("/deleteRH_ESTADOS_FUNC/{id}")
	public void deleteRH_ESTADOS_FUNC(@PathParam("id") Integer id) {
		RH_ESTADOS_FUNC RH_ESTADOS_FUNC = new RH_ESTADOS_FUNC();
		RH_ESTADOS_FUNC.setCOD_ESTADO(id);
		dao64.delete(RH_ESTADOS_FUNC);
	}

	@PUT
	@Path("/updateRH_ESTADOS_FUNC")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_ESTADOS_FUNC updateRH_ESTADOS_FUNC(final RH_ESTADOS_FUNC RH_ESTADOS_FUNC) {
		RH_ESTADOS_FUNC.setCOD_ESTADO(RH_ESTADOS_FUNC.getCOD_ESTADO());
		return dao64.update(RH_ESTADOS_FUNC);
	}

	/******************************************* RH_TURNOS *******************/
	@POST
	@Path("/createRH_TURNOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_TURNOS insertARH_TURNOS(final RH_TURNOS data) {
		return dao66.create(data);
	}

	@GET
	@Path("/getRH_TURNOSbyid/{id}")
	@Produces("application/json")
	public List<RH_TURNOS> getRH_TURNOSbyid(@PathParam("id") Integer id) {
		return dao66.getbyid(id);
	}

	@GET
	@Path("/getRH_TURNOS")
	@Produces("application/json")
	public List<RH_TURNOS> getRH_TURNOS() {
		return dao66.getall();
	}

	@DELETE
	@Path("/deleteRH_TURNOS/{id}")
	public void deleteRH_TURNOS(@PathParam("id") Integer id) {
		RH_TURNOS RH_TURNOS = new RH_TURNOS();
		RH_TURNOS.setCOD_TURNO(id);
		dao66.delete(RH_TURNOS);
	}

	@PUT
	@Path("/updateRH_TURNOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_TURNOS updateRH_TURNOS(final RH_TURNOS RH_TURNOS) {
		RH_TURNOS.setCOD_TURNO(RH_TURNOS.getCOD_TURNO());
		return dao66.update(RH_TURNOS);
	}

	/******************************************* RH_SECTORES *******************/
	@POST
	@Path("/createRH_SECTORES")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_SECTORES insertARH_SECTORES(final RH_SECTORES data) {
		return dao65.create(data);
	}

	@GET
	@Path("/getRH_SECTORESbyid/{id}")
	@Produces("application/json")
	public List<RH_SECTORES> getRH_SECTORESbyid(@PathParam("id") Integer id) {
		return dao65.getbyid(id);
	}

	@GET
	@Path("/getRH_SECTORES")
	@Produces("application/json")
	public List<RH_SECTORES> getRH_SECTORES() {
		return dao65.getall();
	}

	@DELETE
	@Path("/deleteRH_SECTORES/{id}")
	public void deleteRH_SECTORES(@PathParam("id") Integer id) {
		RH_SECTORES RH_SECTORES = new RH_SECTORES();
		RH_SECTORES.setCOD_SECTOR(id);
		dao65.delete(RH_SECTORES);
	}

	@PUT
	@Path("/updateRH_SECTORES")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_SECTORES updateRH_SECTORES(final RH_SECTORES RH_SECTORES) {
		RH_SECTORES.setCOD_SECTOR(RH_SECTORES.getCOD_SECTOR());
		return dao65.update(RH_SECTORES);
	}

	/******************************************* RH_FUNCIONARIOS *******************/
	@POST
	@Path("/createRH_FUNCIONARIOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_FUNCIONARIOS insertARH_FUNCIONARIOS(final RH_FUNCIONARIOS data) {
		return dao63.create(data);
	}

	@GET
	@Path("/getRH_FUNCIONARIOSbyid/{id}")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOSbyid(@PathParam("id") Integer id) {
		return dao63.getbyid(id);
	}

	@GET
	@Path("/getRH_FUNCIONARIOSAtivos")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOSAtivos() {
		return dao63.getallativos();
	}

	@GET
	@Path("/getRH_FUNCIONARIOS")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOS() {
		return dao63.getall();
	}

	@POST
	@Path("/getRH_FUNCIONARIOS2")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOS2(final List<HashMap<String, String>> datas) {
		HashMap<String, String> firstMap = datas.get(0);

		String Ativo = firstMap.get("ATIVO");
		return dao63.getall2(Ativo);
	}

	@DELETE
	@Path("/deleteRH_FUNCIONARIOS/{id}")
	public void deleteRH_FUNCIONARIOS(@PathParam("id") Integer id) {
		RH_FUNCIONARIOS RH_FUNCIONARIOS = new RH_FUNCIONARIOS();
		RH_FUNCIONARIOS.setCOD_FUNCIONARIO(id);
		dao63.delete(RH_FUNCIONARIOS);
	}

	@PUT
	@Path("/updateRH_FUNCIONARIOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_FUNCIONARIOS updateRH_FUNCIONARIOS(final RH_FUNCIONARIOS RH_FUNCIONARIOS) {
		RH_FUNCIONARIOS.setCOD_FUNCIONARIO(RH_FUNCIONARIOS.getCOD_FUNCIONARIO());
		return dao63.update(RH_FUNCIONARIOS);
	}

	@POST
	@Path("/getRH_FUNCIONARIOSGERAL")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOSGERAL(final List<HashMap<String, String>> datas) {
		HashMap<String, String> firstMap = datas.get(0);

		String data = firstMap.get("DATA");
		String Ativo = firstMap.get("ATIVO");
		String OPERARIO = firstMap.get("OPERARIO");
		String SECTOR_ACESSO = firstMap.get("SECTOR_ACESSO");
		if (OPERARIO.isEmpty())
			OPERARIO = null;
		Boolean ADMIN = (firstMap.get("ADMIN").equals("true") ? true : false);
		return dao63.getGeral(data, Ativo, OPERARIO, SECTOR_ACESSO, ADMIN);
	}

	@POST
	@Path("/getRH_FUNCIONARIOSASSIDUIDADE")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOSASSIDUIDADE(final List<HashMap<String, String>> datas) {
		HashMap<String, String> firstMap = datas.get(0);

		String data = firstMap.get("DATA");
		String Ativo = firstMap.get("ATIVO");
		String OPERARIO = firstMap.get("OPERARIO");
		String SECTOR_ACESSO = firstMap.get("SECTOR_ACESSO");
		String Exclusao = firstMap.get("EXCLUSAO");
		if (Exclusao == null || Exclusao.isEmpty())
			Exclusao = "999999";
		if (OPERARIO.isEmpty())
			OPERARIO = null;
		Boolean ADMIN = (firstMap.get("ADMIN").equals("true") ? true : false);
		return dao63.getAssiduidade(data, Ativo, OPERARIO, SECTOR_ACESSO, ADMIN, Exclusao);
	}

	@POST
	@Path("/getRH_FUNCIONARIOSPRODUTIVIDADE")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOSPRODUTIVIDADE(final List<HashMap<String, String>> datas) {
		HashMap<String, String> firstMap = datas.get(0);

		String data1 = firstMap.get("DATA1");
		String data2 = firstMap.get("DATA2");
		String Ativo = firstMap.get("ATIVO");
		String OPERARIO = firstMap.get("OPERARIO");
		String SECTOR_ACESSO = firstMap.get("SECTOR_ACESSO");
		if (OPERARIO.isEmpty())
			OPERARIO = null;
		Boolean ADMIN = (firstMap.get("ADMIN").equals("true") ? true : false);
		return dao63.getProdutividade(data1, data2, Ativo, OPERARIO, SECTOR_ACESSO, ADMIN);
	}

	@POST
	@Path("/getRH_FUNCIONARIOSOPERACOES")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOSOPERACOES(final List<HashMap<String, String>> datas) {
		HashMap<String, String> firstMap = datas.get(0);

		String data1 = firstMap.get("DATA1");
		String data2 = firstMap.get("DATA2");
		String Ativo = firstMap.get("ATIVO");
		String OPERARIO = firstMap.get("OPERARIO");
		String SECTOR_ACESSO = firstMap.get("SECTOR_ACESSO");
		String tipo_cadencia = firstMap.get("TIPO_CADENCIA");
		String SECTOR = firstMap.get("SECTOR");
		if (OPERARIO.isEmpty())
			OPERARIO = null;
		Boolean ADMIN = (firstMap.get("ADMIN").equals("true") ? true : false);
		return dao63.getOperacoes(data1, data2, Ativo, OPERARIO, SECTOR_ACESSO, ADMIN, tipo_cadencia, SECTOR);
	}

	@POST
	@Path("/getRH_FUNCIONARIOSPAUSAS")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOSPAUSAS(final List<HashMap<String, String>> datas) {
		HashMap<String, String> firstMap = datas.get(0);

		String data1 = firstMap.get("DATA1");
		String data2 = firstMap.get("DATA2");
		String Ativo = firstMap.get("ATIVO");
		String OPERARIO = firstMap.get("OPERARIO");
		String SECTOR_ACESSO = firstMap.get("SECTOR_ACESSO");
		if (OPERARIO.isEmpty())
			OPERARIO = null;
		Boolean ADMIN = (firstMap.get("ADMIN").equals("true") ? true : false);
		return dao63.getpausas(data1, data2, Ativo, OPERARIO, SECTOR_ACESSO, ADMIN);
	}

	@POST
	@Path("/getRH_FUNCIONARIOSSECTORES")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOSSECTORES(final List<HashMap<String, String>> datas) {
		HashMap<String, String> firstMap = datas.get(0);

		String data1 = firstMap.get("DATA1");
		// String data2 = firstMap.get("DATA2");
		String Ativo = firstMap.get("ATIVO");
		String OPERARIO = firstMap.get("OPERARIO");
		String SECTOR = firstMap.get("SECTOR");
		String SECTOR_ACESSO = firstMap.get("SECTOR_ACESSO");
		String tipo_cadencia = firstMap.get("TIPO_CADENCIA");
		if (OPERARIO != null && OPERARIO.isEmpty())
			OPERARIO = null;
		if (SECTOR != null && SECTOR.isEmpty())
			SECTOR = null;
		return dao63.getSectores(data1, Ativo, OPERARIO, SECTOR, SECTOR_ACESSO, tipo_cadencia);
	}

	@POST
	@Path("/getRH_FUNCIONARIOSCADENCIA")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOSCADENCIA(final List<HashMap<String, String>> datas) {
		HashMap<String, String> firstMap = datas.get(0);

		String data1 = firstMap.get("DATA1");
		// String data2 = firstMap.get("DATA2");
		String Ativo = firstMap.get("ATIVO");
		String OPERARIO = firstMap.get("OPERARIO");
		String SECTOR = firstMap.get("SECTOR");
		String SECTOR_ACESSO = firstMap.get("SECTOR_ACESSO");
		String tipo_cadencia = firstMap.get("TIPO_CADENCIA");
		String tipo_analise = firstMap.get("TIPO_ANALISE");
		if (OPERARIO != null && OPERARIO.isEmpty())
			OPERARIO = null;
		if (SECTOR != null && SECTOR.isEmpty())
			SECTOR = null;
		return dao63.getCadencias(data1, Ativo, OPERARIO, SECTOR, SECTOR_ACESSO, tipo_cadencia, tipo_analise);
	}

	@POST
	@Path("/getRH_FUNCIONARIOSSECTORESCOMPARATIVA")
	@Produces("application/json")
	public List<RH_FUNCIONARIOS> getRH_FUNCIONARIOSSECTORESCOMPARATIVA(final List<HashMap<String, String>> datas) {
		HashMap<String, String> firstMap = datas.get(0);

		String data1 = firstMap.get("DATA1");

		String Ativo = firstMap.get("ATIVO");

		String SECTOR = firstMap.get("SECTOR");

		String tipo_cadencia = firstMap.get("TIPO_CADENCIA");
		String SECTOR_ACESSO = firstMap.get("SECTOR_ACESSO");
		if (SECTOR != null && SECTOR.isEmpty())
			SECTOR = null;
		Boolean ADMIN = (firstMap.get("ADMIN").equals("true") ? true : false);
		return dao63.getSectoresComparativa(data1, Ativo, SECTOR, SECTOR_ACESSO, ADMIN, tipo_cadencia);
	}

	/******************************************* RH_EXCLUSAO_TIPO_EXTRA *******************/
	@POST
	@Path("/createRH_EXCLUSAO_TIPO_EXTRA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_EXCLUSAO_TIPO_EXTRA insertARH_EXCLUSAO_TIPO_EXTRA(final RH_EXCLUSAO_TIPO_EXTRA data) {
		return dao67.create(data);
	}

	@GET
	@Path("/getRH_EXCLUSAO_TIPO_EXTRAbyid/{id}")
	@Produces("application/json")
	public List<RH_EXCLUSAO_TIPO_EXTRA> getRH_EXCLUSAO_TIPO_EXTRAbyid(@PathParam("id") Integer id) {
		return dao67.getbyid(id);
	}

	@GET
	@Path("/getRH_EXCLUSAO_TIPO_EXTRAgetTipos")
	@Produces("application/json")
	public List<RH_EXCLUSAO_TIPO_EXTRA> getRH_EXCLUSAO_TIPO_EXTRAgetTipos() {
		return dao67.getTiposExtra();
	}

	@GET
	@Path("/getRH_EXCLUSAO_TIPO_EXTRA")
	@Produces("application/json")
	public List<RH_EXCLUSAO_TIPO_EXTRA> getRH_EXCLUSAO_TIPO_EXTRA() {
		return dao67.getall();
	}

	@DELETE
	@Path("/deleteRH_EXCLUSAO_TIPO_EXTRA/{id}")
	public void deleteRH_EXCLUSAO_TIPO_EXTRA(@PathParam("id") Integer id) {
		RH_EXCLUSAO_TIPO_EXTRA RH_EXCLUSAO_TIPO_EXTRA = new RH_EXCLUSAO_TIPO_EXTRA();
		RH_EXCLUSAO_TIPO_EXTRA.setID(id);
		dao67.delete(RH_EXCLUSAO_TIPO_EXTRA);
	}

	@PUT
	@Path("/updateRH_EXCLUSAO_TIPO_EXTRA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_EXCLUSAO_TIPO_EXTRA updateRH_EXCLUSAO_TIPO_EXTRA(final RH_EXCLUSAO_TIPO_EXTRA RH_EXCLUSAO_TIPO_EXTRA) {
		RH_EXCLUSAO_TIPO_EXTRA.setID(RH_EXCLUSAO_TIPO_EXTRA.getID());
		return dao67.update(RH_EXCLUSAO_TIPO_EXTRA);
	}

	/******************************* SILVER ********************************************************************************************/

	@GET
	@Path("/getArmazem")
	@Produces("application/json")
	public List<HashMap<String, String>> getArmazem() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getArmazem(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getFornecedores")
	@Produces("application/json")
	public List<HashMap<String, String>> getFornecedores() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getFornecedores(getURLSILVER());
		return dados;
	}

	@POST
	@Path("/getStock")
	@Consumes("*/*")
	@Produces("application/json")
	public List<HashMap<String, String>> getStock(final List<HashMap<String, String>> data)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getStock(data, getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getDadosEtiqueta/{etiqueta}")
	@Produces("application/json")
	public List<HashMap<String, String>> getDadosEtiqueta(@PathParam("etiqueta") String etiqueta)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getDadosEtiqueta(getURLSILVER(), etiqueta);
		return dados;
	}

	@GET
	@Path("/getDadosEtiquetabyREF/{ref}")
	@Produces("application/json")
	public List<HashMap<String, String>> getDadosEtiquetabyREF(@PathParam("ref") String ref)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getDadosEtiquetabyREF(getURLSILVER(), ref);
		return dados;
	}

	@GET
	@Path("/getDadosEtiquetabyREFcisterna/{ref}/{prorefsubstituta}")
	@Produces("application/json")
	public List<HashMap<String, String>> getDadosEtiquetabyREFcisterna(@PathParam("ref") String ref,
			@PathParam("prorefsubstituta") String prorefsubstituta) throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getDadosEtiquetabyREFcisterna(getURLSILVER(), ref,
				prorefsubstituta);
		return dados;
	}

	@GET
	@Path("/getComponentes")
	@Produces("application/json")
	public List<HashMap<String, String>> getComponentes() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getComponentes(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getFamilias")
	@Produces("application/json")
	public List<HashMap<String, String>> familias() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getFamilias(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getDefeitos")
	@Produces("application/json")
	public List<HashMap<String, String>> getDefeitos() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getDefeitos(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getComponentesDOSGIID")
	@Produces("application/json")
	public List<HashMap<String, String>> getComponentesDOSGIID() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getComponentesDOSGIID(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getComponentesTodos")
	@Produces("application/json")
	public List<HashMap<String, String>> getComponentesTodos() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getComponentesTodos(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getComponentesProducao")
	@Produces("application/json")
	public List<HashMap<String, String>> getComponentesProducao() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getComponentesProducao(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getComponentesSaoBento")
	@Produces("application/json")
	public List<HashMap<String, String>> getComponentesSaoBento() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getComponentesSaoBento(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/GER_DIC_PROJ_CABgetComponentes")
	@Produces("application/json")
	public List<HashMap<String, String>> GER_DIC_PROJ_CABgetComponentes() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getComponentesProjeto(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getReferencias")
	@Produces("application/json")
	public List<HashMap<String, String>> getReferencias() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getReferencias(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getReferenciasMANU")
	@Produces("application/json")
	public List<HashMap<String, String>> getReferenciasMANU() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getReferenciasMANU(getURLSILVER());
		return dados;
	}

	@POST
	@Path("/getReferenciasSTOCK")
	@Produces("application/json")
	public List<HashMap<String, String>> getReferenciasSTOCK(final String PROREF)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getReferenciasSTOCK(getURLSILVER(), PROREF);
		return dados;
	}

	@GET
	@Path("/getClientes")
	@Produces("application/json")
	public List<HashMap<String, String>> getClientes() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getClientes(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/getMoradas/{clicod}")
	@Produces("application/json")
	public List<HashMap<String, String>> getMoradas(@PathParam("clicod") String clicod)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getMoradas(getURLSILVER(), clicod);
		return dados;
	}

	@GET
	@Path("/getMoradaClientePorReferencia/{proref}")
	@Produces("application/json")
	public List<HashMap<String, String>> getMoradaClientePorReferencia(@PathParam("proref") String proref)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getMoradaClientePorReferencia(getURLSILVER(), proref);
		return dados;
	}

	@GET
	@Path("/verificaOFNUM/{ofnum}")
	@Produces("application/json")
	public List<HashMap<String, String>> verificaOFNUM(@PathParam("ofnum") String ofnum)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.verificaOFNUM(getURLSILVER(), ofnum);
		return dados;
	}

	@POST
	@Path("/getEncomendasCliente/{proref}")
	@Produces("application/json")
	public List<HashMap<String, String>> getEncomendasCliente(@PathParam("proref") String proref,
			final List<HashMap<String, String>> datas) throws SQLException, ClassNotFoundException {

		HashMap<String, String> firstMap = datas.get(0);

		String data = firstMap.get("DATA");
		String datafim = firstMap.get("DATA_FIM");
		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getEncomendasCliente(getURLSILVER(), proref, data,
				datafim);
		return dados;
	}

	@GET
	@Path("/getPlaneado/{proref}")
	@Produces("application/json")
	public List<HashMap<String, String>> getPlaneado(@PathParam("proref") String proref)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getPlaneado(getURLSILVER(), proref);
		return dados;
	}

	@GET
	@Path("/getStockPROREF/{proref}")
	@Produces("application/json")
	public List<HashMap<String, String>> getStockPROREF(@PathParam("proref") String proref)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getStock2(getURLSILVER(), proref);
		return dados;
	}

	@POST
	@Path("/getEnviado/{proref}")
	@Produces("application/json")
	public List<HashMap<String, String>> getEnviado(@PathParam("proref") String proref,
			final List<HashMap<String, String>> datas) throws SQLException, ClassNotFoundException {

		HashMap<String, String> firstMap = datas.get(0);

		String data = firstMap.get("DATA");
		String datafim = firstMap.get("DATA_FIM");
		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getEnviado(getURLSILVER(), proref, data, datafim);
		return dados;
	}

	@POST
	@Path("/getEnviosGarantidos/{proref}")
	@Produces("application/json")
	public List<HashMap<String, String>> getEnviosGarantidos(@PathParam("proref") String proref,
			final List<HashMap<String, String>> datas) throws SQLException, ClassNotFoundException {

		HashMap<String, String> firstMap = datas.get(0);

		String data = firstMap.get("DATA");
		String datafim = firstMap.get("DATA_FIM");
		String ETSNUM = firstMap.get("ETSNUM");
		String CLICODLIV = firstMap.get("CLICODLIV");
		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getEnviosGarantidos(getURLSILVER(), proref, data,
				datafim, CLICODLIV, ETSNUM);
		return dados;
	}

	@GET
	@Path("/getComponentesdoCliente/{clicod}/{etsnum}")
	@Produces("application/json")
	public List<HashMap<String, String>> getComponentesdoCliente(@PathParam("clicod") String clicod,
			@PathParam("etsnum") String etsnum) throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getComponentesdoCliente(getURLSILVER(), clicod,
				etsnum);
		return dados;
	}

	@GET
	@Path("/validaEtiqueta/{ETIQUETA}")
	@Produces("application/json")
	public List<HashMap<String, String>> validaEtiqueta(@PathParam("ETIQUETA") String ETIQUETA)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.validaEtiqueta(getURLSILVER(), ETIQUETA);
		return dados;
	}

	@GET
	@Path("/validaGuia/{GUIA}")
	@Produces("application/json")
	public List<HashMap<String, String>> validaGuia(@PathParam("GUIA") String GUIA)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.validaGuia(getURLSILVER(), GUIA);
		return dados;
	}

	@GET
	@Path("/validalote/{LOTE}")
	@Produces("application/json")
	public List<HashMap<String, String>> validalote(@PathParam("LOTE") String LOTE)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.validalote(getURLSILVER(), LOTE);
		return dados;
	}

	@GET
	@Path("/getDadosporLote/{LOTE}")
	@Produces("application/json")
	public List<HashMap<String, String>> getDadosporLote(@PathParam("LOTE") String LOTE)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getDadosporLote(getURLSILVER(), LOTE);
		return dados;
	}

	@GET
	@Path("/getDadosporEtiqueta/{ETIQUETA}")
	@Produces("application/json")
	public List<HashMap<String, String>> getDadosporEtiqueta(@PathParam("ETIQUETA") String ETIQUETA)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getDadosporEtiqueta(getURLSILVER(), ETIQUETA);
		return dados;
	}

	@GET
	@Path("/getDadosporGuia/{GUIA}")
	@Produces("application/json")
	public List<HashMap<String, String>> getDadosporGuia(@PathParam("GUIA") String GUIA)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getDadosporGuia(getURLSILVER(), GUIA);
		return dados;
	}

	@POST
	@Path("/getEtiquetas")
	@Produces("application/json")
	public List<HashMap<String, String>> getEtiquetas(final String PROREF) throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getEtiquetas(getURLSILVER(), PROREF);
		return dados;
	}

	@GET
	@Path("/getusers")
	@Produces("application/json")
	public List<HashMap<String, String>> getUsers() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getUsers(getURLSILVER());
		return dados;
	}

	@GET
	@Path("/verificaOF/{of}")
	@Produces("application/json")
	public List<HashMap<String, String>> verificaOF(@PathParam("of") String of)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.verificaOF(getURLSILVER(), of);
		return dados;
	}

	@GET
	@Path("/verificaREF/{proref}")
	@Produces("application/json")
	public List<HashMap<String, String>> verificaREF(@PathParam("proref") String proref)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.verificaREF(getURLSILVER(), proref);
		return dados;
	}

	/************************* CARTELAS ************************/
	@POST
	@Path("/getCartelas")
	@Produces("application/json")
	public List<HashMap<String, String>> getCartelas(final List<HashMap<String, String>> data)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getCartelas(getURL(), data);
		return dados;
	}

	/************************************* GER_MODULO */
	@POST
	@Path("/createGER_MODULO")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_MODULO insertGER_MODULOA(final GER_MODULO data) {
		return dao22.create(data);
	}

	@GET
	@Path("/getGER_MODULO")
	@Produces("application/json")
	public List<GER_MODULO> getAGER_MODULO() {
		return dao22.getall();
	}

	@DELETE
	@Path("/deleteGER_MODULO/{id}")
	public void deleteGER_MODULO(@PathParam("id") Integer id) {
		GER_MODULO GER_MODULO = new GER_MODULO();
		GER_MODULO.setID_MODULO(id);
		dao22.delete(GER_MODULO);
	}

	@PUT
	@Path("/updateGER_MODULO")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_MODULO updateAB_MOV_ANALISE_LINHA(final GER_MODULO GER_MODULO) {
		GER_MODULO.setID_MODULO(GER_MODULO.getID_MODULO());
		return dao22.update(GER_MODULO);
	}

	/************************************* GER_PERFIL_CAB */
	@POST
	@Path("/createGER_PERFIL_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_PERFIL_CAB insertGER_PERFIL_CABA(final GER_PERFIL_CAB data) {
		return dao23.create(data);
	}

	@GET
	@Path("/getGER_PERFIL_CAB")
	@Produces("application/json")
	public List<GER_PERFIL_CAB> getAGER_PERFIL_CAB() {
		return dao23.getall();
	}

	@DELETE
	@Path("/deleteGER_PERFIL_CAB/{id}")
	public void deleteGER_PERFIL_CAB(@PathParam("id") Integer id) {
		GER_PERFIL_CAB GER_PERFIL_CAB = new GER_PERFIL_CAB();
		GER_PERFIL_CAB.setID_PERFIL_CAB(id);
		dao23.delete(GER_PERFIL_CAB);
	}

	@PUT
	@Path("/updateGER_PERFIL_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_PERFIL_CAB updateAB_MOV_ANALISE_LINHA(final GER_PERFIL_CAB GER_PERFIL_CAB) {
		GER_PERFIL_CAB.setID_PERFIL_CAB(GER_PERFIL_CAB.getID_PERFIL_CAB());
		return dao23.update(GER_PERFIL_CAB);
	}

	@GET
	@Path("/getGER_PERFIL_CABbyid/{id}/{modulo}")
	@Produces("application/json")
	public List<GER_PERFIL_CAB> getGER_PERFIL_CABbyid(@PathParam("id") Integer id,
			@PathParam("modulo") Integer modulo) {
		return dao23.getallperfil(id, modulo);
	}

	@GET
	@Path("/getGER_PERFIL_CABbymodulo/{modulo}")
	@Produces("application/json")
	public List<GER_PERFIL_CAB> getGER_PERFIL_CABbymodulo(@PathParam("modulo") Integer modulo) {
		return dao23.getallperfilmodulo(modulo);
	}

	/************************************* GER_ARMAZEM */
	@POST
	@Path("/createGER_ARMAZEM")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_ARMAZEM insertGER_ARMAZEM(final GER_ARMAZEM data) {
		return dao26.create(data);
	}

	@GET
	@Path("/getGER_ARMAZEM")
	@Produces("application/json")
	public List<GER_ARMAZEM> getGER_ARMAZEM() {
		return dao26.allEntries();
	}

	@DELETE
	@Path("/deleteGER_ARMAZEM/{id}")
	public void deleteGER_ARMAZEM(@PathParam("id") Integer id) {
		GER_ARMAZEM GER_ARMAZEM = new GER_ARMAZEM();
		GER_ARMAZEM.setID_ARMAZEM(id);
		dao26.delete(GER_ARMAZEM);
	}

	@PUT
	@Path("/updateGER_ARMAZEM")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_ARMAZEM updateGER_ARMAZEM(final GER_ARMAZEM GER_ARMAZEM) {
		GER_ARMAZEM.setID_ARMAZEM(GER_ARMAZEM.getID_ARMAZEM());
		return dao26.update(GER_ARMAZEM);
	}

	/************************************* GER_FERIADOS */
	@POST
	@Path("/createGER_FERIADOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_FERIADOS insertGER_FERIADOS(final GER_FERIADOS data) {
		return dao81.create(data);
	}

	@GET
	@Path("/getGER_FERIADOS")
	@Produces("application/json")
	public List<GER_FERIADOS> getGER_FERIADOS() {
		return dao81.getall();
	}

	@DELETE
	@Path("/deleteGER_FERIADOS/{id}")
	public void deleteGER_FERIADOS(@PathParam("id") Integer id) {
		GER_FERIADOS GER_FERIADOS = new GER_FERIADOS();
		GER_FERIADOS.setID_FERIADO(id);
		dao81.delete(GER_FERIADOS);
	}

	@PUT
	@Path("/updateGER_FERIADOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_FERIADOS updateGER_FERIADOS(final GER_FERIADOS GER_FERIADOS) {
		GER_FERIADOS.setID_FERIADO(GER_FERIADOS.getID_FERIADO());
		return dao81.update(GER_FERIADOS);
	}

	/************************************* GER_PERFIL_LIN */
	@POST
	@Path("/createGER_PERFIL_LIN")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_PERFIL_LIN insertGER_PERFIL_LINA(final GER_PERFIL_LIN data) {
		return dao24.create(data);
	}

	@GET
	@Path("/getGER_PERFIL_LIN")
	@Produces("application/json")
	public List<GER_PERFIL_LIN> getAGER_PERFIL_LIN() {
		return dao24.allEntries();
	}

	@DELETE
	@Path("/deleteGER_PERFIL_LIN/{id}")
	public void deleteGER_PERFIL_LIN(@PathParam("id") Integer id) {
		dao24.delete(id);
	}

	@PUT
	@Path("/updateGER_PERFIL_LIN")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_PERFIL_LIN updateAB_MOV_ANALISE_LINHA(final GER_PERFIL_LIN GER_PERFIL_LIN) {
		GER_PERFIL_LIN.setID_PERFIL_LIN(GER_PERFIL_LIN.getID_PERFIL_LIN());
		return dao24.update(GER_PERFIL_LIN);
	}

	@GET
	@Path("/getGER_PERFIL_LINbyid/{id}")
	@Produces("application/json")
	public List<GER_PERFIL_LIN> getGER_PERFIL_LINbyid(@PathParam("id") Integer id) {
		return dao24.getbyId(id);
	}

	@GET
	@Path("/getGER_PERFIL_LINbyid_node/{id}/{node}")
	@Produces("application/json")
	public List<GER_PERFIL_LIN> getGER_PERFIL_LINbyid_node(@PathParam("id") Integer id,
			@PathParam("node") String node) {
		return dao24.getbyId_Node(id, node);
	}

	/************************************* GER_UTZ_PERFIL */
	@POST
	@Path("/createGER_UTZ_PERFIL")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_UTZ_PERFIL insertGER_UTZ_PERFILA(final GER_UTZ_PERFIL data) {
		return dao25.create(data);
	}

	@GET
	@Path("/getGER_UTZ_PERFIL")
	@Produces("application/json")
	public List<GER_UTZ_PERFIL> getAGER_UTZ_PERFIL() {
		return dao25.allEntries();
	}

	@GET
	@Path("/getGER_UTZ_PERFILbyid/{id}")
	@Produces("application/json")
	public List<GER_UTZ_PERFIL> getGER_UTZ_PERFILbyid(@PathParam("id") Integer id) {
		return dao25.getbyId(id);
	}

	@GET
	@Path("/getGER_UTZ_PERFILbyidUSER/{id}/{id2}")
	@Produces("application/json")
	public List<GER_UTZ_PERFIL> getGER_UTZ_PERFILbyidUSER(@PathParam("id") Integer id, @PathParam("id2") Integer id2) {
		return dao25.getbyIduser(id, id2);
	}

	@GET
	@Path("/getGER_UTZ_PERFILbyidNOTUSER/{id}/{id2}")
	@Produces("application/json")
	public List<GER_UTZ_PERFIL> getGER_UTZ_PERFILbyidNOTUSER(@PathParam("id") Integer id,
			@PathParam("id2") Integer id2) {
		return dao25.getbyIdnotuser(id, id2);
	}

	@DELETE
	@Path("/deleteGER_UTZ_PERFIL/{id}")
	public void deleteGER_UTZ_PERFIL(@PathParam("id") Integer id) {
		GER_UTZ_PERFIL GER_UTZ_PERFIL = new GER_UTZ_PERFIL();
		GER_UTZ_PERFIL.setID_PERFIL_UTZ(id);
		dao25.delete(GER_UTZ_PERFIL);
	}

	@PUT
	@Path("/updateGER_UTZ_PERFIL")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_UTZ_PERFIL updateAB_MOV_ANALISE_LINHA(final GER_UTZ_PERFIL GER_UTZ_PERFIL) {
		GER_UTZ_PERFIL.setID_PERFIL_UTZ(GER_UTZ_PERFIL.getID_PERFIL_UTZ());
		return dao25.update(GER_UTZ_PERFIL);
	}

	/************************************* GER_DEPARTAMENTOS_SECTORES */
	@POST
	@Path("/createGER_DEPARTAMENTOS_SECTORES")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DEPARTAMENTOS_SECTORES insertGER_DEPARTAMENTOS_SECTORESA(final GER_DEPARTAMENTOS_SECTORES data) {
		return dao97.create(data);
	}

	@GET
	@Path("/getGER_DEPARTAMENTOS_SECTORES")
	@Produces("application/json")
	public List<GER_DEPARTAMENTOS_SECTORES> getAGER_DEPARTAMENTOS_SECTORES() {
		return dao97.allEntries();
	}

	@GET
	@Path("/getGER_DEPARTAMENTOS_SECTORESbyid/{id}")
	@Produces("application/json")
	public List<GER_DEPARTAMENTOS_SECTORES> getGER_DEPARTAMENTOS_SECTORESbyid(@PathParam("id") Integer id) {
		return dao97.getbyId(id);
	}

	@GET
	@Path("/getGER_DEPARTAMENTOS_SECTORESbyidDEPARTAMENTO/{id}")
	@Produces("application/json")
	public List<GER_DEPARTAMENTOS_SECTORES> getGER_DEPARTAMENTOS_SECTORESbyidUSER(@PathParam("id") Integer id) {
		return dao97.getbyIduser(id);
	}

	@GET
	@Path("/getGER_DEPARTAMENTOS_SECTORESbyidNOTDEPARTAMENTO/{id}")
	@Produces("application/json")
	public List<GER_DEPARTAMENTOS_SECTORES> getGER_DEPARTAMENTOS_SECTORESbyidNOTUSER(@PathParam("id") Integer id) {
		return dao97.getbyIdnotuser(id);
	}

	@DELETE
	@Path("/deleteGER_DEPARTAMENTOS_SECTORES/{id}")
	public void deleteGER_DEPARTAMENTOS_SECTORES(@PathParam("id") Integer id) {
		GER_DEPARTAMENTOS_SECTORES GER_DEPARTAMENTOS_SECTORES = new GER_DEPARTAMENTOS_SECTORES();
		GER_DEPARTAMENTOS_SECTORES.setID_DEPARTAMENTOS_SECTORES(id);
		dao97.delete(GER_DEPARTAMENTOS_SECTORES);
	}

	@PUT
	@Path("/updateGER_DEPARTAMENTOS_SECTORES")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DEPARTAMENTOS_SECTORES updateAB_MOV_ANALISE_LINHA(
			final GER_DEPARTAMENTOS_SECTORES GER_DEPARTAMENTOS_SECTORES) {
		GER_DEPARTAMENTOS_SECTORES
				.setID_DEPARTAMENTOS_SECTORES(GER_DEPARTAMENTOS_SECTORES.getID_DEPARTAMENTOS_SECTORES());
		return dao97.update(GER_DEPARTAMENTOS_SECTORES);
	}

	/************************************* GER_LOG_EVENTOS */
	@POST
	@Path("/createGER_LOG_EVENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_LOG_EVENTOS insertGER_LOG_EVENTOSA(final GER_LOG_EVENTOS data) {
		return dao27.create(data);
	}

	/************************************* GER_EVENTOS_CONF */
	@POST
	@Path("/createGER_EVENTOS_CONF")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_EVENTOS_CONF insertGER_EVENTOS_CONFA(final GER_EVENTOS_CONF data) {
		return dao28.create(data);
	}

	@GET
	@Path("/getGER_EVENTOS_CONF")
	@Produces("application/json")
	public List<GER_EVENTOS_CONF> getAGER_EVENTOS_CONF() {
		return dao28.getall();
	}

	@GET
	@Path("/getGER_EVENTOS_CONFbyid/{id}")
	@Produces("application/json")
	public List<GER_EVENTOS_CONF> getGER_EVENTOS_CONFbyid(@PathParam("id") Integer id) {
		return dao28.getbyId(id);
	}

	@DELETE
	@Path("/deleteGER_EVENTOS_CONF/{id}")
	public void deleteGER_EVENTOS_CONF(@PathParam("id") Integer id) {
		GER_EVENTOS_CONF GER_EVENTOS_CONF = new GER_EVENTOS_CONF();
		GER_EVENTOS_CONF.setID_EVENTO(id);
		dao28.delete(GER_EVENTOS_CONF);
	}

	@PUT
	@Path("/updateGER_EVENTOS_CONF")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_EVENTOS_CONF updateAB_MOV_ANALISE_LINHA(final GER_EVENTOS_CONF GER_EVENTOS_CONF) {
		GER_EVENTOS_CONF.setID_EVENTO(GER_EVENTOS_CONF.getID_EVENTO());
		return dao28.update(GER_EVENTOS_CONF);
	}

	/************************************* GER_ANALISES */
	@POST
	@Path("/createGER_ANALISES")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_ANALISES insertGER_ANALISESA(final GER_ANALISES data) {
		return dao29.create(data);
	}

	@GET
	@Path("/getGER_ANALISES")
	@Produces("application/json")
	public List<GER_ANALISES> getAGER_ANALISES() {
		return dao29.getall();
	}

	@GET
	@Path("/getGER_ANALISESativas")
	@Produces("application/json")
	public List<GER_ANALISES> getAGER_ANALISESativas() {
		return dao29.getallativas();
	}

	@GET
	@Path("/getGER_ANALISESbyid/{id}")
	@Produces("application/json")
	public List<GER_ANALISES> getGER_ANALISESbyid(@PathParam("id") Integer id) {
		return dao29.getbyId(id);
	}

	@GET
	@Path("/getGER_ANALISESbyidmodulo/{id_modulo}")
	@Produces("application/json")
	public List<GER_ANALISES> getGER_ANALISESbyidmodulo(@PathParam("id_modulo") Integer id_modulo) {
		return dao29.getbyId_modulo(id_modulo);
	}

	@GET
	@Path("/getGER_ANALISESbyidmoduloativas/{id_modulo}")
	@Produces("application/json")
	public List<GER_ANALISES> getGER_ANALISESbyidmoduloativas(@PathParam("id_modulo") Integer id_modulo) {
		return dao29.getbyId_moduloativas(id_modulo);
	}

	@DELETE
	@Path("/deleteGER_ANALISES/{id}")
	public void deleteGER_ANALISES(@PathParam("id") Integer id) {
		GER_ANALISES GER_ANALISES = new GER_ANALISES();
		GER_ANALISES.setID(id);
		dao29.delete(GER_ANALISES);
	}

	@PUT
	@Path("/updateGER_ANALISES")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_ANALISES updateAB_MOV_ANALISE_LINHA(final GER_ANALISES GER_ANALISES) {
		GER_ANALISES.setID(GER_ANALISES.getID());
		return dao29.update(GER_ANALISES);
	}

	/************************************* GER_VISTAS */

	@POST
	@Path("/createGER_VISTAS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_VISTAS insertGER_VISTASA(final GER_VISTAS data) {
		return dao31.create(data);
	}

	@GET
	@Path("/getGER_VISTAS")
	@Produces("application/json")
	public List<GER_VISTAS> getGER_VISTAS() {
		return dao31.getall();
	}

	@GET
	@Path("/getGER_VISTASbyid/{id}")
	@Produces("application/json")
	public List<GER_VISTAS> getGER_VISTASbyid(@PathParam("id") Integer id) {
		return dao31.getGER_VISTASbyid(id);
	}

	@GET
	@Path("/getbyid_pagina/{id}")
	@Produces("application/json")
	public List<GER_VISTAS> getbyid_pagina(@PathParam("id") Integer id) {
		return dao31.getbyid_pagina(id);
	}

	@DELETE
	@Path("/deleteGER_VISTAS/{id}")
	public void deleteGER_VISTAS(@PathParam("id") Integer id) {
		GER_VISTAS GER_VISTAS = new GER_VISTAS();
		GER_VISTAS.setID(id);
		dao31.delete(GER_VISTAS);
	}

	@PUT
	@Path("/updateGER_VISTAS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_VISTAS updateAB_MOV_ANALISE_LINHA(final GER_VISTAS GER_VISTAS) {
		GER_VISTAS.setID(GER_VISTAS.getID());
		return dao31.update(GER_VISTAS);
	}

	/************************************* GER_POSTOS */

	@POST
	@Path("/createGER_POSTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_POSTOS insertGER_POSTOSA(final GER_POSTOS data) {
		return dao33.create(data);
	}

	@GET
	@Path("/getGER_POSTOS")
	@Produces("application/json")
	public List<GER_POSTOS> getGER_POSTOS() {
		return dao33.getall();
	}

	@GET
	@Path("/getGER_POSTOSbyip/{ip}")
	@Produces("application/json")
	public List<GER_POSTOS> getGER_POSTOSbyip(@PathParam("ip") String ip) {
		return dao33.getByIp(ip);
	}

	@DELETE
	@Path("/deleteGER_POSTOS/{id}")
	public void deleteGER_POSTOS(@PathParam("id") Integer id) {
		GER_POSTOS GER_POSTOS = new GER_POSTOS();
		GER_POSTOS.setID_POSTO(id);
		dao33.delete(GER_POSTOS);
	}

	@PUT
	@Path("/updateGER_POSTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_POSTOS updateGER_POSTOS(final GER_POSTOS GER_POSTOS) {
		GER_POSTOS.setID_POSTO(GER_POSTOS.getID_POSTO());
		return dao33.update(GER_POSTOS);
	}

	/************************************* PR_DIC_OBJETIVOS_PLANOS */

	@POST
	@Path("/createPR_DIC_OBJETIVOS_PLANOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_OBJETIVOS_PLANOS insertPR_DIC_OBJETIVOS_PLANOSA(final PR_DIC_OBJETIVOS_PLANOS data) {
		return dao82.create(data);
	}

	@GET
	@Path("/getPR_DIC_OBJETIVOS_PLANOS")
	@Produces("application/json")
	public List<PR_DIC_OBJETIVOS_PLANOS> getPR_DIC_OBJETIVOS_PLANOS() {
		return dao82.getall();
	}

	@GET
	@Path("/getPR_DIC_OBJETIVOS_PLANOSbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_OBJETIVOS_PLANOS> getPR_DIC_OBJETIVOS_PLANOSbyip(@PathParam("id") Integer id) {
		return dao82.getById(id);
	}

	@DELETE
	@Path("/deletePR_DIC_OBJETIVOS_PLANOS/{id}")
	public void deletePR_DIC_OBJETIVOS_PLANOS(@PathParam("id") Integer id) {
		PR_DIC_OBJETIVOS_PLANOS PR_DIC_OBJETIVOS_PLANOS = new PR_DIC_OBJETIVOS_PLANOS();
		PR_DIC_OBJETIVOS_PLANOS.setID(id);
		dao82.delete(PR_DIC_OBJETIVOS_PLANOS);
	}

	@PUT
	@Path("/updatePR_DIC_OBJETIVOS_PLANOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_OBJETIVOS_PLANOS updatePR_DIC_OBJETIVOS_PLANOS(
			final PR_DIC_OBJETIVOS_PLANOS PR_DIC_OBJETIVOS_PLANOS) {
		PR_DIC_OBJETIVOS_PLANOS.setID(PR_DIC_OBJETIVOS_PLANOS.getID());
		return dao82.update(PR_DIC_OBJETIVOS_PLANOS);
	}

	/************************************* PR_DIC_SECTORES_ANALISE */

	@POST
	@Path("/createPR_DIC_SECTORES_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SECTORES_ANALISE insertPR_DIC_SECTORES_ANALISEA(final PR_DIC_SECTORES_ANALISE data) {
		return dao83.create(data);
	}

	@GET
	@Path("/getPR_DIC_SECTORES_ANALISE")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_ANALISE> getPR_DIC_SECTORES_ANALISE() {
		return dao83.getall();
	}

	@GET
	@Path("/getPR_DIC_SECTORES_ANALISEbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_ANALISE> getPR_DIC_SECTORES_ANALISEbyip(@PathParam("id") Integer id) {
		return dao83.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_SECTORES_ANALISE/{id}")
	public void deletePR_DIC_SECTORES_ANALISE(@PathParam("id") Integer id) {
		PR_DIC_SECTORES_ANALISE PR_DIC_SECTORES_ANALISE = new PR_DIC_SECTORES_ANALISE();
		PR_DIC_SECTORES_ANALISE.setID_SECTOR_ANALISE(id);
		dao83.delete(PR_DIC_SECTORES_ANALISE);
	}

	@PUT
	@Path("/updatePR_DIC_SECTORES_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SECTORES_ANALISE updatePR_DIC_SECTORES_ANALISE(
			final PR_DIC_SECTORES_ANALISE PR_DIC_SECTORES_ANALISE) {
		PR_DIC_SECTORES_ANALISE.setID_SECTOR_ANALISE(PR_DIC_SECTORES_ANALISE.getID_SECTOR_ANALISE());
		return dao83.update(PR_DIC_SECTORES_ANALISE);
	}

	/************************************* PR_DIC_TIPOLOGIA_ENSAIO */

	@POST
	@Path("/createPR_DIC_TIPOLOGIA_ENSAIO")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_TIPOLOGIA_ENSAIO insertPR_DIC_TIPOLOGIA_ENSAIOA(final PR_DIC_TIPOLOGIA_ENSAIO data) {
		return dao84.create(data);
	}

	@GET
	@Path("/getPR_DIC_TIPOLOGIA_ENSAIO")
	@Produces("application/json")
	public List<PR_DIC_TIPOLOGIA_ENSAIO> getPR_DIC_TIPOLOGIA_ENSAIO() {
		return dao84.getall();
	}

	@GET
	@Path("/getPR_DIC_TIPOLOGIA_ENSAIObyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_TIPOLOGIA_ENSAIO> getPR_DIC_TIPOLOGIA_ENSAIObyip(@PathParam("id") Integer id) {
		return dao84.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_TIPOLOGIA_ENSAIO/{id}")
	public void deletePR_DIC_TIPOLOGIA_ENSAIO(@PathParam("id") Integer id) {
		PR_DIC_TIPOLOGIA_ENSAIO PR_DIC_TIPOLOGIA_ENSAIO = new PR_DIC_TIPOLOGIA_ENSAIO();
		PR_DIC_TIPOLOGIA_ENSAIO.setID_TIPOLOGIA_ENSAIO(id);
		dao84.delete(PR_DIC_TIPOLOGIA_ENSAIO);
	}

	@PUT
	@Path("/updatePR_DIC_TIPOLOGIA_ENSAIO")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_TIPOLOGIA_ENSAIO updatePR_DIC_TIPOLOGIA_ENSAIO(
			final PR_DIC_TIPOLOGIA_ENSAIO PR_DIC_TIPOLOGIA_ENSAIO) {
		PR_DIC_TIPOLOGIA_ENSAIO.setID_TIPOLOGIA_ENSAIO(PR_DIC_TIPOLOGIA_ENSAIO.getID_TIPOLOGIA_ENSAIO());
		return dao84.update(PR_DIC_TIPOLOGIA_ENSAIO);
	}

	/************************************* PR_AMOSTRAS_CAB */

	@POST
	@Path("/createPR_AMOSTRAS_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_AMOSTRAS_CAB insertPR_AMOSTRAS_CABA(final PR_AMOSTRAS_CAB data) {
		return dao85.create(data);
	}

	@GET
	@Path("/getPR_AMOSTRAS_CAB")
	@Produces("application/json")
	public List<PR_AMOSTRAS_CAB> getPR_AMOSTRAS_CAB() {
		return dao85.getall();
	}

	@GET
	@Path("/getPR_AMOSTRAS_CABbyid/{id}")
	@Produces("application/json")
	public List<PR_AMOSTRAS_CAB> getPR_AMOSTRAS_CABbyip(@PathParam("id") Integer id) {
		return dao85.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_AMOSTRAS_CAB/{id}")
	public void deletePR_AMOSTRAS_CAB(@PathParam("id") Integer id) {
		PR_AMOSTRAS_CAB PR_AMOSTRAS_CAB = new PR_AMOSTRAS_CAB();
		PR_AMOSTRAS_CAB.setID_AMOSTRA(id);
		dao85.delete(PR_AMOSTRAS_CAB);
	}

	@PUT
	@Path("/updatePR_AMOSTRAS_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_AMOSTRAS_CAB updatePR_AMOSTRAS_CAB(final PR_AMOSTRAS_CAB PR_AMOSTRAS_CAB) {
		PR_AMOSTRAS_CAB.setID_AMOSTRA(PR_AMOSTRAS_CAB.getID_AMOSTRA());
		return dao85.update(PR_AMOSTRAS_CAB);
	}

	@GET
	@Path("/updatePR_AMOSTRAS_CAB_EVENTOS/{id}/{utilizador}")
	@Produces("application/json")
	public int updatePR_AMOSTRAS_CAB_EVENTOS(@PathParam("id") Integer id, @PathParam("utilizador") Integer utilizador) {
		return entityManager.createNativeQuery(
				"INSERT INTO GT_LOGS(ID_TAREFA,UTZ_CRIA,DATA_CRIA,DESCRICAO) " + "select c.ID_TAREFA," + utilizador
						+ ",GETDATE(),('Alterado Estado de '+ (CASE WHEN c.ESTADO = 'P' THEN 'Pendente'  ELSE CASE WHEN c.ESTADO = 'L' THEN 'Lida'  ELSE CASE WHEN c.ESTADO = 'E' THEN 'Em Curso'  ELSE CASE WHEN c.ESTADO = 'R' THEN 'Rejeitada'  ELSE CASE WHEN c.ESTADO = 'N' THEN 'Não Respondida' END END END END END) +' para Cancelada') descricao   from PR_AMOSTRAS_CAB a "
						+ "inner join PR_AMOSTRAS_ACCOES b on a.ID_AMOSTRA = b.ID_AMOSTRA "
						+ "inner join GT_MOV_TAREFAS c on b.ID_AMOSTRA_ACCAO = c.ID_CAMPO and ID_MODULO = 10 and SUB_MODULO = 'A' "
						+ "where a.ID_AMOSTRA = " + id + ""
						+ "UPDATE c set c.ESTADO = 'A',c.DATA_ANULACAO = GETDATE(),c.UTZ_ANULACAO = " + utilizador
						+ ",c.INATIVO = 1 from PR_AMOSTRAS_CAB a "
						+ "inner join PR_AMOSTRAS_ACCOES b on a.ID_AMOSTRA = b.ID_AMOSTRA "
						+ "inner join GT_MOV_TAREFAS c on b.ID_AMOSTRA_ACCAO = c.ID_CAMPO and ID_MODULO = 10 and SUB_MODULO = 'A' "
						+ "where a.ID_AMOSTRA = " + id + "")
				.executeUpdate();
	}

	/************************************* PR_BARRAS_ALERTA */

	@POST
	@Path("/createPR_BARRAS_ALERTA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_BARRAS_ALERTA insertPR_BARRAS_ALERTAA(final PR_BARRAS_ALERTA data) {
		return dao92.create(data);
	}

	@GET
	@Path("/getPR_BARRAS_ALERTA")
	@Produces("application/json")
	public List<PR_BARRAS_ALERTA> getPR_BARRAS_ALERTA() {
		return dao92.getall();
	}

	@GET
	@Path("/getPR_BARRAS_ALERTAbyid/{id}")
	@Produces("application/json")
	public List<PR_BARRAS_ALERTA> getPR_BARRAS_ALERTAbyip(@PathParam("id") Integer id) {
		return dao92.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_BARRAS_ALERTA/{id}")
	public void deletePR_BARRAS_ALERTA(@PathParam("id") Integer id) {
		PR_BARRAS_ALERTA PR_BARRAS_ALERTA = new PR_BARRAS_ALERTA();
		PR_BARRAS_ALERTA.setID(id);
		dao92.delete(PR_BARRAS_ALERTA);
	}

	@PUT
	@Path("/updatePR_BARRAS_ALERTA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_BARRAS_ALERTA updatePR_BARRAS_ALERTA(final PR_BARRAS_ALERTA PR_BARRAS_ALERTA) {
		PR_BARRAS_ALERTA.setID(PR_BARRAS_ALERTA.getID());
		return dao92.update(PR_BARRAS_ALERTA);
	}

	/************************************* PR_REVISOES_PRIORITARIAS */

	@POST
	@Path("/createPR_REVISOES_PRIORITARIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_REVISOES_PRIORITARIAS insertPR_REVISOES_PRIORITARIASA(final PR_REVISOES_PRIORITARIAS data) {
		return dao93.create(data);
	}

	@GET
	@Path("/getPR_REVISOES_PRIORITARIAS")
	@Produces("application/json")
	public List<PR_REVISOES_PRIORITARIAS> getPR_REVISOES_PRIORITARIAS() {
		return dao93.getall();
	}

	@GET
	@Path("/getPR_REVISOES_PRIORITARIASbyid/{id}")
	@Produces("application/json")
	public List<PR_REVISOES_PRIORITARIAS> getPR_REVISOES_PRIORITARIASbyip(@PathParam("id") Integer id) {
		return dao93.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_REVISOES_PRIORITARIAS/{id}")
	public void deletePR_REVISOES_PRIORITARIAS(@PathParam("id") Integer id) {
		PR_REVISOES_PRIORITARIAS PR_REVISOES_PRIORITARIAS = new PR_REVISOES_PRIORITARIAS();
		PR_REVISOES_PRIORITARIAS.setID(id);
		dao93.delete(PR_REVISOES_PRIORITARIAS);
	}

	@PUT
	@Path("/updatePR_REVISOES_PRIORITARIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_REVISOES_PRIORITARIAS updatePR_REVISOES_PRIORITARIAS(
			final PR_REVISOES_PRIORITARIAS PR_REVISOES_PRIORITARIAS) {
		PR_REVISOES_PRIORITARIAS.setID(PR_REVISOES_PRIORITARIAS.getID());
		return dao93.update(PR_REVISOES_PRIORITARIAS);
	}

	/************************************* PR_PRODUCOES_PRIORITARIAS */

	@POST
	@Path("/createPR_PRODUCOES_PRIORITARIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PRODUCOES_PRIORITARIAS insertPR_PRODUCOES_PRIORITARIASA(final PR_PRODUCOES_PRIORITARIAS data) {
		return dao94.create(data);
	}

	@GET
	@Path("/getPR_PRODUCOES_PRIORITARIAS")
	@Produces("application/json")
	public List<PR_PRODUCOES_PRIORITARIAS> getPR_PRODUCOES_PRIORITARIAS() {
		return dao94.getall();
	}

	@GET
	@Path("/getPR_PRODUCOES_PRIORITARIASbyid/{id}")
	@Produces("application/json")
	public List<PR_PRODUCOES_PRIORITARIAS> getPR_PRODUCOES_PRIORITARIASbyip(@PathParam("id") Integer id) {
		return dao94.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PRODUCOES_PRIORITARIAS/{id}")
	public void deletePR_PRODUCOES_PRIORITARIAS(@PathParam("id") Integer id) {
		PR_PRODUCOES_PRIORITARIAS PR_PRODUCOES_PRIORITARIAS = new PR_PRODUCOES_PRIORITARIAS();
		PR_PRODUCOES_PRIORITARIAS.setID(id);
		dao94.delete(PR_PRODUCOES_PRIORITARIAS);
	}

	@PUT
	@Path("/updatePR_PRODUCOES_PRIORITARIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PRODUCOES_PRIORITARIAS updatePR_PRODUCOES_PRIORITARIAS(
			final PR_PRODUCOES_PRIORITARIAS PR_PRODUCOES_PRIORITARIAS) {
		PR_PRODUCOES_PRIORITARIAS.setID(PR_PRODUCOES_PRIORITARIAS.getID());
		return dao94.update(PR_PRODUCOES_PRIORITARIAS);
	}

	/************************************* PA_MOV_CAB */

	@POST
	@Path("/createPA_MOV_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PA_MOV_CAB insertPA_MOV_CABA(final PA_MOV_CAB data) {
		return dao95.create(data);
	}

	@GET
	@Path("/getPA_MOV_CAB")
	@Produces("application/json")
	public List<PA_MOV_CAB> getPA_MOV_CAB() {
		return dao95.getall();
	}

	@POST
	@Path("/getPA_MOV_CABbyTIPO/{tipo}")
	@Produces("application/json")
	public List<PA_MOV_CAB> getPA_MOV_CABbyTIPO(@PathParam("tipo") String tipo,
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String fastresponse = firstMap.get("FASTRESPONSE");
		String emAtraso = firstMap.get("EM_ATRASO");
		return dao95.getallbyTIPO(tipo, fastresponse, emAtraso);
	}

	@POST
	@Path("/getPA_MOV_CABbyTIPOaccoes/{tipo}")
	@Produces("application/json")
	public List<PA_MOV_CAB> getPA_MOV_CABbyTIPOaccoes(@PathParam("tipo") String tipo,
			final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String fastresponse = firstMap.get("FASTRESPONSE");
		String emAtraso = firstMap.get("EM_ATRASO");
		return dao95.getallbyTIPOaccoes(tipo, fastresponse, emAtraso);
	}

	@GET
	@Path("/getPA_MOV_CABAssociarPlanoEstrategico/{id_plano}/{id}")
	@Produces("application/json")
	public int getPA_MOV_CABAssociarPlanoEstrategico(@PathParam("id") String id,
			@PathParam("id_plano") String id_plano) {
		return entityManager.createNativeQuery(
				" UPDATE PA_MOV_CAB set ID_PLANO_ESTRATEGICO = " + id_plano + " where  ID_PLANO_CAB = " + id + "")
				.executeUpdate();
	}

	@GET
	@Path("/getPA_MOV_CABRemoverPlanoEstrategico/{id}")
	@Produces("application/json")
	public int getPA_MOV_CABRemoverPlanoEstrategico(@PathParam("id") String id) {
		return entityManager
				.createNativeQuery(" UPDATE PA_MOV_CAB set ID_PLANO_ESTRATEGICO = null where ID_PLANO_CAB = " + id + "")
				.executeUpdate();
	}

	@GET
	@Path("/getPA_MOV_CABbyid/{id}")
	@Produces("application/json")
	public List<PA_MOV_CAB> getPA_MOV_CABbyid(@PathParam("id") Integer id) {
		return dao95.getbyid(id);
	}

	@GET
	@Path("/getPA_MOV_CABbyidPlanoEstrategico/{tipo}/{id}")
	@Produces("application/json")
	public List<PA_MOV_CAB> getPA_MOV_CABbyidPlanoEstrategico(@PathParam("tipo") String tipo,
			@PathParam("id") Integer id) {
		return dao95.getbyidPlanoEstrategico(tipo, id);
	}

	@DELETE
	@Path("/deletePA_MOV_CAB/{id}")
	public void deletePA_MOV_CAB(@PathParam("id") Integer id) {
		PA_MOV_CAB PA_MOV_CAB = new PA_MOV_CAB();
		PA_MOV_CAB.setID_PLANO_CAB(id);
		dao95.delete(PA_MOV_CAB);
	}

	@PUT
	@Path("/updatePA_MOV_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PA_MOV_CAB updatePA_MOV_CAB(final PA_MOV_CAB PA_MOV_CAB) {
		PA_MOV_CAB.setID_PLANO_CAB(PA_MOV_CAB.getID_PLANO_CAB());
		return dao95.update(PA_MOV_CAB);
	}

	@GET
	@Path("/updatePA_MOV_CAB_EVENTOS/{id}/{utilizador}")
	@Produces("application/json")
	public int updatePA_MOV_CAB_EVENTOS(@PathParam("id") Integer id, @PathParam("utilizador") Integer utilizador) {
		return entityManager.createNativeQuery("INSERT INTO GT_LOGS(ID_TAREFA,UTZ_CRIA,DATA_CRIA,DESCRICAO) "
				+ "select c.ID_TAREFA," + utilizador
				+ ",GETDATE(),('Alterado Estado de '+ (CASE WHEN c.ESTADO = 'P' THEN 'Pendente'  ELSE CASE WHEN c.ESTADO = 'L' THEN 'Lida'  ELSE CASE WHEN c.ESTADO = 'E' THEN 'Em Curso'  ELSE CASE WHEN c.ESTADO = 'R' THEN 'Rejeitada'  ELSE CASE WHEN c.ESTADO = 'N' THEN 'Não Respondida' END END END END END) +' para Cancelada') descricao   from PA_MOV_CAB a "
				+ "inner join PA_MOV_LINHA b on a.ID_PLANO_CAB = b.ID_PLANO_CAB "
				+ "inner join GT_MOV_TAREFAS c on b.ID_PLANO_LINHA = c.ID_CAMPO and ID_MODULO = 13 and SUB_MODULO = 'PA' "
				+ "where a.ID_PLANO_CAB = " + id + ""
				+ "UPDATE c set c.ESTADO = 'A',c.DATA_ANULACAO = GETDATE(),c.UTZ_ANULACAO = " + utilizador
				+ ",c.INATIVO = 1 from PA_MOV_CAB a " + "inner join PA_MOV_LINHA b on a.ID_PLANO_CAB = b.ID_PLANO_CAB "
				+ "inner join GT_MOV_TAREFAS c on b.ID_PLANO_LINHA = c.ID_CAMPO and ID_MODULO = 13 and SUB_MODULO = 'PA' "
				+ "where a.ID_PLANO_CAB = " + id + "  "
				+ "UPDATE  b set b.ESTADO = 'A'  from PA_MOV_CAB a inner join PA_MOV_LINHA b on a.ID_PLANO_CAB = b.ID_PLANO_CAB "
				+ "where a.ID_PLANO_CAB = " + id + " and a.ID_PLANO_ESTRATEGICO IS NOT NULL").executeUpdate();
	}

	/************************************* PEDIDOS_APP */
	@POST
	@Path("/createPEDIDOS_APP")
	@Consumes("*/*")
	@Produces("application/json")
	public PEDIDOS_APP insertPEDIDOS_APP(final PEDIDOS_APP data) {
		return dao101.create(data);
	}

	@GET
	@Path("/getPEDIDOS_APP")
	@Produces("application/json")
	public List<PEDIDOS_APP> getPEDIDOS_APP() {
		return dao101.getall();
	}

	@GET
	@Path("/getPEDIDOS_APPbyid/{id}")
	@Produces("application/json")
	public List<PEDIDOS_APP> getPEDIDOS_APPbyid(@PathParam("id") String id) {
		return dao101.getbyid(id);
	}

	@DELETE
	@Path("/deletePEDIDOS_APP/{id}")
	public void deletePEDIDOS_APP(@PathParam("id") Integer id) {
		PEDIDOS_APP PEDIDOS_APP = new PEDIDOS_APP();
		PEDIDOS_APP.setID_PEDIDO(id);
		dao101.delete(PEDIDOS_APP);
	}

	@PUT
	@Path("/updatePEDIDOS_APP")
	@Consumes("*/*")
	@Produces("application/json")
	public PEDIDOS_APP updatePEDIDOS_APP(final PEDIDOS_APP PEDIDOS_APP) {
		PEDIDOS_APP.setID_PEDIDO(PEDIDOS_APP.getID_PEDIDO());
		return dao101.update(PEDIDOS_APP);
	}

	/************************************* FICHEIROS_PAGINAS */
	@POST
	@Path("/createFICHEIROS_PAGINAS")
	@Consumes("*/*")
	@Produces("application/json")
	public FICHEIROS_PAGINAS insertFICHEIROS_PAGINAS(final FICHEIROS_PAGINAS data) {
		return dao102.create(data);
	}

	@GET
	@Path("/getFICHEIROS_PAGINAS")
	@Produces("application/json")
	public List<FICHEIROS_PAGINAS> getFICHEIROS_PAGINAS() {
		return dao102.getall();
	}

	@GET
	@Path("/getFICHEIROS_PAGINASbyid/{id}")
	@Produces("application/json")
	public List<FICHEIROS_PAGINAS> getFICHEIROS_PAGINASbyid(@PathParam("id") Integer id) {
		return dao102.getbyid(id);
	}

	@GET
	@Path("/getFICHEIROS_PAGINASbyPagina/{id}/{pagina}")
	@Produces("application/json")
	public List<FICHEIROS_PAGINAS> getFICHEIROS_PAGINASbyPagina(@PathParam("id") Integer id,
			@PathParam("pagina") String pagina) {
		return dao102.getbyidPagina(id, pagina);
	}

	@DELETE
	@Path("/deleteFICHEIROS_PAGINAS/{id}")
	public void deleteFICHEIROS_PAGINAS(@PathParam("id") Integer id) {
		FICHEIROS_PAGINAS FICHEIROS_PAGINAS = new FICHEIROS_PAGINAS();
		FICHEIROS_PAGINAS.setID_FICHEIRO(id);
		dao102.delete(FICHEIROS_PAGINAS);
	}

	@PUT
	@Path("/updateFICHEIROS_PAGINAS")
	@Consumes("*/*")
	@Produces("application/json")
	public FICHEIROS_PAGINAS updateFICHEIROS_PAGINAS(final FICHEIROS_PAGINAS FICHEIROS_PAGINAS) {
		FICHEIROS_PAGINAS.setID_FICHEIRO(FICHEIROS_PAGINAS.getID_FICHEIRO());
		return dao102.update(FICHEIROS_PAGINAS);
	}

	/************************************* PA_MOV_LINHA */

	@POST
	@Path("/createPA_MOV_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public PA_MOV_LINHA insertPA_MOV_LINHAA(final PA_MOV_LINHA data) {
		return dao96.create(data);
	}

	@GET
	@Path("/getPA_MOV_LINHA")
	@Produces("application/json")
	public List<PA_MOV_LINHA> getPA_MOV_LINHA() {
		return dao96.getall();
	}

	@GET
	@Path("/getPA_MOV_LINHAbyid/{id}")
	@Produces("application/json")
	public List<PA_MOV_LINHA> getPA_MOV_LINHAbyip(@PathParam("id") Integer id) {
		return dao96.getbyid(id);
	}

	@DELETE
	@Path("/deletePA_MOV_LINHA/{id}")
	public void deletePA_MOV_LINHA(@PathParam("id") Integer id) {
		PA_MOV_LINHA PA_MOV_LINHA = new PA_MOV_LINHA();
		PA_MOV_LINHA.setID_PLANO_LINHA(id);
		dao96.delete(PA_MOV_LINHA);
	}

	@PUT
	@Path("/updatePA_MOV_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public PA_MOV_LINHA updatePA_MOV_LINHA(final PA_MOV_LINHA PA_MOV_LINHA) {
		PA_MOV_LINHA.setID_PLANO_LINHA(PA_MOV_LINHA.getID_PLANO_LINHA());

		PA_MOV_LINHA dados = dao96.update(PA_MOV_LINHA);

		entityManager.createNativeQuery("UPDATE GT_MOV_TAREFAS SET OBSERVACOES = '" + PA_MOV_LINHA.getDESCRICAO() + "' "
				+ " WHERE SUB_MODULO = 'PA' AND ID_MODULO = 13 and ID_CAMPO = " + PA_MOV_LINHA.getID_PLANO_LINHA() + "")
				.executeUpdate();

		return dados;
	}

	@GET
	@Path("/getPA_MOV_LINHAAtualizaESTADOS/{id}")
	@Produces("application/json")
	public int getPA_MOV_LINHAAtualizaESTADOS(@PathParam("id") Integer id) {
		return entityManager.createNativeQuery(
				"DECLARE @TOTAL int = (SELECT COUNT(*) FROM PA_MOV_LINHA where ID_PLANO_CAB in (select ID_PLANO_CAB from PA_MOV_LINHA where ID_PLANO_CAB =  "
						+ id + ") ) "
						+ "IF (SELECT COUNT(*) FROM PA_MOV_LINHA where ID_PLANO_CAB in (select ID_PLANO_CAB from PA_MOV_LINHA where ID_PLANO_CAB =  "
						+ id + ") and ESTADO = 'C') = @TOTAL BEGIN "
						+ "	UPDATE PA_MOV_CAB set ESTADO = 'C' where ID_PLANO_CAB in (select ID_PLANO_CAB from PA_MOV_LINHA where ID_PLANO_CAB = "
						+ id + " ) END "
						+ "ELSE IF (SELECT COUNT(*) FROM PA_MOV_LINHA where ID_PLANO_CAB in (select ID_PLANO_CAB from PA_MOV_LINHA where ID_PLANO_CAB =  "
						+ id + ") and ESTADO in ('V','R','D')) = @TOTAL BEGIN "
						+ "UPDATE PA_MOV_CAB set ESTADO = 'V' where ID_PLANO_CAB in (select ID_PLANO_CAB from PA_MOV_LINHA where ID_PLANO_CAB = "
						+ id + " ) END "
						+ "ELSE IF EXISTS (select ID_PLANO_CAB from PA_MOV_LINHA where ID_PLANO_CAB =  " + id
						+ ") BEGIN " + "	UPDATE PA_MOV_CAB set ESTADO = 'P' where ID_PLANO_CAB = " + id
						+ " and ESTADO != 'E' END")
				.executeUpdate();
	}

	/************************************* PA_MOV_FICHEIROS */
	@POST
	@Path("/createPA_MOV_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public PA_MOV_FICHEIROS insertPA_MOV_FICHEIROSA(final PA_MOV_FICHEIROS data) {
		return dao98.create(data);
	}

	@GET
	@Path("/getPA_MOV_FICHEIROS")
	@Produces("application/json")
	public List<PA_MOV_FICHEIROS> getPA_MOV_FICHEIROS() {
		return dao98.getall();
	}

	@GET
	@Path("/getPA_MOV_FICHEIROSbyidPLANO/{id}")
	@Produces("application/json")
	public List<PA_MOV_FICHEIROS> getPA_MOV_FICHEIROSbyidPLANO(@PathParam("id") Integer id) {
		return dao98.getbyid(id);
	}

	@DELETE
	@Path("/deletePA_MOV_FICHEIROS/{id}")
	public void deletePA_MOV_FICHEIROS(@PathParam("id") Integer id) {
		PA_MOV_FICHEIROS PA_MOV_FICHEIROS = new PA_MOV_FICHEIROS();
		PA_MOV_FICHEIROS.setID(id);
		dao98.delete(PA_MOV_FICHEIROS);
	}

	@PUT
	@Path("/updatePA_MOV_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public PA_MOV_FICHEIROS updatePA_MOV_FICHEIROS(final PA_MOV_FICHEIROS PA_MOV_FICHEIROS) {
		PA_MOV_FICHEIROS.setID(PA_MOV_FICHEIROS.getID());
		return dao98.update(PA_MOV_FICHEIROS);
	}

	/************************************* PA_DIC_AMBITOS */
	@POST
	@Path("/createPA_DIC_AMBITOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PA_DIC_AMBITOS insertPA_DIC_AMBITOSA(final PA_DIC_AMBITOS data) {
		return dao100.create(data);
	}

	@GET
	@Path("/getPA_DIC_AMBITOS")
	@Produces("application/json")
	public List<PA_DIC_AMBITOS> getPA_DIC_AMBITOS() {
		return dao100.getall();
	}

	@GET
	@Path("/getPA_DIC_AMBITOSbyid/{id}")
	@Produces("application/json")
	public List<PA_DIC_AMBITOS> getPA_DIC_AMBITOSbyip(@PathParam("id") Integer id) {
		return dao100.getbyid(id);
	}

	@DELETE
	@Path("/deletePA_DIC_AMBITOS/{id}")
	public void deletePA_DIC_AMBITOS(@PathParam("id") Integer id) {
		PA_DIC_AMBITOS PA_DIC_AMBITOS = new PA_DIC_AMBITOS();
		PA_DIC_AMBITOS.setID_AMBITO(id);
		dao100.delete(PA_DIC_AMBITOS);
	}

	@PUT
	@Path("/updatePA_DIC_AMBITOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PA_DIC_AMBITOS updatePA_DIC_AMBITOS(final PA_DIC_AMBITOS PA_DIC_AMBITOS) {
		PA_DIC_AMBITOS.setID_AMBITO(PA_DIC_AMBITOS.getID_AMBITO());
		return dao100.update(PA_DIC_AMBITOS);
	}

	/************************************* PR_AMOSTRAS_ACCOES */

	@POST
	@Path("/createPR_AMOSTRAS_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_AMOSTRAS_ACCOES insertPR_AMOSTRAS_ACCOESA(final PR_AMOSTRAS_ACCOES data) {
		return dao86.create(data);
	}

	@GET
	@Path("/getPR_AMOSTRAS_ACCOES")
	@Produces("application/json")
	public List<PR_AMOSTRAS_ACCOES> getPR_AMOSTRAS_ACCOES() {
		return dao86.getall();
	}

	@GET
	@Path("/getPR_AMOSTRAS_ACCOESbyid/{id}")
	@Produces("application/json")
	public List<PR_AMOSTRAS_ACCOES> getPR_AMOSTRAS_ACCOESbyip(@PathParam("id") Integer id) {
		return dao86.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_AMOSTRAS_ACCOES/{id}")
	public void deletePR_AMOSTRAS_ACCOES(@PathParam("id") Integer id) {
		PR_AMOSTRAS_ACCOES PR_AMOSTRAS_ACCOES = new PR_AMOSTRAS_ACCOES();
		PR_AMOSTRAS_ACCOES.setID_AMOSTRA_ACCAO(id);
		dao86.delete(PR_AMOSTRAS_ACCOES);
	}

	@PUT
	@Path("/updatePR_AMOSTRAS_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_AMOSTRAS_ACCOES updatePR_AMOSTRAS_ACCOES(final PR_AMOSTRAS_ACCOES PR_AMOSTRAS_ACCOES) {
		PR_AMOSTRAS_ACCOES.setID_AMOSTRA_ACCAO(PR_AMOSTRAS_ACCOES.getID_AMOSTRA_ACCAO());
		return dao86.update(PR_AMOSTRAS_ACCOES);
	}

	/************************************* LG_DIC_OBJETIVOS */

	@POST
	@Path("/createLG_DIC_OBJETIVOS")
	@Consumes("*/*")
	@Produces("application/json")
	public LG_DIC_OBJETIVOS insertLG_DIC_OBJETIVOSA(final LG_DIC_OBJETIVOS data) {
		return dao91.create(data);
	}

	@GET
	@Path("/getLG_DIC_OBJETIVOS")
	@Produces("application/json")
	public List<LG_DIC_OBJETIVOS> getLG_DIC_OBJETIVOS() {
		return dao91.getall();
	}

	@GET
	@Path("/getLG_DIC_OBJETIVOSbyid/{id}")
	@Produces("application/json")
	public List<LG_DIC_OBJETIVOS> getLG_DIC_OBJETIVOSbyip(@PathParam("id") Integer id) {
		return dao91.getById(id);
	}

	@DELETE
	@Path("/deleteLG_DIC_OBJETIVOS/{id}")
	public void deleteLG_DIC_OBJETIVOS(@PathParam("id") Integer id) {
		LG_DIC_OBJETIVOS LG_DIC_OBJETIVOS = new LG_DIC_OBJETIVOS();
		LG_DIC_OBJETIVOS.setID(id);
		dao91.delete(LG_DIC_OBJETIVOS);
	}

	@PUT
	@Path("/updateLG_DIC_OBJETIVOS")
	@Consumes("*/*")
	@Produces("application/json")
	public LG_DIC_OBJETIVOS updateLG_DIC_OBJETIVOS(final LG_DIC_OBJETIVOS LG_DIC_OBJETIVOS) {
		LG_DIC_OBJETIVOS.setID(LG_DIC_OBJETIVOS.getID());
		return dao91.update(LG_DIC_OBJETIVOS);
	}

	/************************************* GER_ATUALIZACAO_SILVER_BI_TABELAS */

	@POST
	@Path("/createGER_ATUALIZACAO_SILVER_BI_TABELAS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_ATUALIZACAO_SILVER_BI_TABELAS insertGER_ATUALIZACAO_SILVER_BI_TABELASA(
			final GER_ATUALIZACAO_SILVER_BI_TABELAS data) {
		return dao62.create(data);
	}

	@GET
	@Path("/getGER_ATUALIZACAO_SILVER_BI_TABELAS")
	@Produces("application/json")
	public List<GER_ATUALIZACAO_SILVER_BI_TABELAS> getGER_ATUALIZACAO_SILVER_BI_TABELAS() {
		return dao62.getall();
	}

	@DELETE
	@Path("/deleteGER_ATUALIZACAO_SILVER_BI_TABELAS/{id}")
	public void deleteGER_ATUALIZACAO_SILVER_BI_TABELAS(@PathParam("id") Integer id) {
		GER_ATUALIZACAO_SILVER_BI_TABELAS GER_ATUALIZACAO_SILVER_BI_TABELAS = new GER_ATUALIZACAO_SILVER_BI_TABELAS();
		GER_ATUALIZACAO_SILVER_BI_TABELAS.setID(id);
		dao62.delete(GER_ATUALIZACAO_SILVER_BI_TABELAS);
	}

	@PUT
	@Path("/updateGER_ATUALIZACAO_SILVER_BI_TABELAS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_ATUALIZACAO_SILVER_BI_TABELAS updateGER_ATUALIZACAO_SILVER_BI_TABELAS(
			final GER_ATUALIZACAO_SILVER_BI_TABELAS GER_ATUALIZACAO_SILVER_BI_TABELAS) {
		GER_ATUALIZACAO_SILVER_BI_TABELAS.setID(GER_ATUALIZACAO_SILVER_BI_TABELAS.getID());
		return dao62.update(GER_ATUALIZACAO_SILVER_BI_TABELAS);
	}

	/************************************* GER_CAMPOS_DISP */

	@GET
	@Path("/getGER_CAMPOS_DISPbyid/{id}")
	@Produces("application/json")
	public List<GER_CAMPOS_DISP> getGER_CAMPOS_DISPbyidmodulo(@PathParam("id") Integer id) {
		return dao32.getbyId(id);
	}

	/************************************* IMPRESSORAS */

	@GET
	@Path("/getImpressoras")
	@Produces("application/json")
	public ArrayList<String> getimpressoras() {
		Printer impressoras = new Printer();
		return impressoras.getImpressoras();
	}

	@GET
	@Path("/imprimir/{nomeficheiro}/{impressora}")
	@Produces("application/json")
	public Response imprimir(@PathParam("nomeficheiro") String nomeficheiro,
			@PathParam("impressora") String impressora) {
		Printer impressoras = new Printer();
		return impressoras.imprimir(nomeficheiro, impressora, getFILEPATH());
	}

	/************************************* AB_DIC_LINHA_OF */
	@POST
	@Path("/createAB_DIC_LINHA_OF")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_LINHA_OF insertAB_DIC_LINHA_OFA(final AB_DIC_LINHA_OF data) {
		return dao34.create(data);
	}

	@GET
	@Path("/getAB_DIC_LINHA_OF")
	@Produces("application/json")
	public List<AB_DIC_LINHA_OF> getAB_DIC_LINHA_OF() {
		return dao34.getall();
	}

	@GET
	@Path("/getAB_DIC_LINHA_OFbyidlinha/{id}")
	@Produces("application/json")
	public List<AB_DIC_LINHA_OF> getAB_DIC_LINHA_OFbyip(@PathParam("id") Integer id) {
		return dao34.getbyidlinha(id);
	}

	@DELETE
	@Path("/deleteAB_DIC_LINHA_OF/{id}")
	public void deleteAB_DIC_LINHA_OF(@PathParam("id") Integer id) {
		AB_DIC_LINHA_OF AB_DIC_LINHA_OF = new AB_DIC_LINHA_OF();
		AB_DIC_LINHA_OF.setID_LINHA_OF(id);
		dao34.delete(AB_DIC_LINHA_OF);
	}

	@PUT
	@Path("/updateAB_DIC_LINHA_OF")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_DIC_LINHA_OF updateAB_DIC_LINHA_OF(final AB_DIC_LINHA_OF AB_DIC_LINHA_OF) {
		AB_DIC_LINHA_OF.setID_LINHA_OF(AB_DIC_LINHA_OF.getID_LINHA_OF());
		return dao34.update(AB_DIC_LINHA_OF);
	}

	/************************************* AB_MOV_MANUTENCAO_ETIQ */
	@POST
	@Path("/createAB_MOV_MANUTENCAO_ETIQ")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_MANUTENCAO_ETIQ insertAB_MOV_MANUTENCAO_ETIQA(final AB_MOV_MANUTENCAO_ETIQ data) {
		if (data.getID_MANUTENCAO_LIN() == 0 || data.getID_MANUTENCAO_LIN() == null) {
			return dao35.create(data);
		} else {
			Query query = entityManager.createNativeQuery("select ID_MANUTENCAO_LIN,ID_MOV_MANU_ETIQUETA "
					+ "from AB_MOV_MANUTENCAO_ETIQ where ID_MANUTENCAO_LIN = " + data.getID_MANUTENCAO_LIN()
					+ " and ETQNUM = '" + data.getETQNUM() + "' ");

			List<Object[]> dados = query.getResultList();
			String id = null;
			for (Object[] content : dados) {
				id = content[0].toString();
			}

			if (id == null) {
				return dao35.create(data);
			}
		}
		return null;
	}

	@POST
	@Path("/ATUALIZAQUANT")
	@Consumes("*/*")
	@Produces("application/json")
	public void ATUALIZAQUANT(final List<HashMap<String, String>> datas) {

		HashMap<String, String> firstMap = datas.get(0);

		String etiqueta = firstMap.get("etiqueta");
		Float qtd = Float.parseFloat(firstMap.get("qtd"));

		final ConnectProgress connectionProgress = new ConnectProgress();
		try {
			connectionProgress.EXEC_SINCRO(etiqueta, qtd, getURLSILVER());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@GET
	@Path("/ATUALIZAQUANTAOAPAGAR/{id}")
	@Consumes("*/*")
	@Produces("application/json")
	public void ATUALIZAQUANTAOAPAGAR(@PathParam("id") Integer id) {

		final ConnectProgress connectionProgress = new ConnectProgress();
		String url = getURLSILVER();
		Query query = entityManager.createNativeQuery(
				"select ETQNUM,(QUANT - QUANT_FINAL) from AB_MOV_MANUTENCAO_ETIQ where ID_MANUTENCAO_LIN in "
						+ "(select ID_MANUTENCAO_LIN from AB_MOV_MANUTENCAO_LINHA where ID_MANUTENCAO_CAB = " + id
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
	@Path("/ATUALIZAQUANTAPAGAR")
	@Consumes("*/*")
	@Produces("application/json")
	public void ATUALIZAQUANTAPAGAR(final List<HashMap<String, String>> datas) {

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
	@Path("/getAB_MOV_MANUTENCAO_ETIQbyidmanu/{id}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_ETIQ> getAB_MOV_MANUTENCAO_ETIQbyip(@PathParam("id") Integer id) {
		return dao35.getbyid_manu(id);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_ETIQbyid/{id}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_ETIQ> getAB_MOV_MANUTENCAO_ETIQbyid(@PathParam("id") Integer id) {
		return dao35.getbyid(id);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_ETIQbyref/{id}/{ref}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_ETIQ> getAB_MOV_MANUTENCAO_ETIQbyref(@PathParam("id") Integer id,
			@PathParam("ref") String ref) {
		return dao35.getbyRef(id, ref);
	}

	@GET
	@Path("/getAB_MOV_MANUTENCAO_ETIQbyref2/{id}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_ETIQ> getAB_MOV_MANUTENCAO_ETIQbyref2(@PathParam("id") Integer id) {
		return dao35.getbyRef2(id);
	}

	@DELETE
	@Path("/deleteAB_MOV_MANUTENCAO_ETIQ/{id}")
	public void deleteAB_MOV_MANUTENCAO_ETIQ(@PathParam("id") Integer id) {
		AB_MOV_MANUTENCAO_ETIQ AB_MOV_MANUTENCAO_ETIQ = new AB_MOV_MANUTENCAO_ETIQ();
		AB_MOV_MANUTENCAO_ETIQ.setID_MOV_MANU_ETIQUETA(id);
		dao35.delete(AB_MOV_MANUTENCAO_ETIQ);
	}

	@PUT
	@Path("/updateAB_MOV_MANUTENCAO_ETIQ")
	@Consumes("*/*")
	@Produces("application/json")
	public AB_MOV_MANUTENCAO_ETIQ updateAB_MOV_MANUTENCAO_ETIQ(final AB_MOV_MANUTENCAO_ETIQ AB_MOV_MANUTENCAO_ETIQ) {
		AB_MOV_MANUTENCAO_ETIQ.setID_MANUTENCAO_LIN(AB_MOV_MANUTENCAO_ETIQ.getID_MANUTENCAO_LIN());
		return dao35.update(AB_MOV_MANUTENCAO_ETIQ);
	}

	/************************************* GER_EVENTOS_PROGRAMADOS */
	@POST
	@Path("/createGER_EVENTOS_PROGRAMADOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_EVENTOS_PROGRAMADOS insertGER_EVENTOS_PROGRAMADOS(final GER_EVENTOS_PROGRAMADOS data) {
		return dao36.create(data);
	}

	@GET
	@Path("/getGER_EVENTOS_PROGRAMADOS")
	@Produces("application/json")
	public List<GER_EVENTOS_PROGRAMADOS> getGER_EVENTOS_PROGRAMADOS() {
		return dao36.getAll();
	}

	@GET
	@Path("/getGER_EVENTOS_PROGRAMADOSbyid/{id}")
	@Produces("application/json")
	public List<GER_EVENTOS_PROGRAMADOS> getGER_EVENTOS_PROGRAMADOSbyid(@PathParam("id") Integer id) {
		return dao36.getbyid(id);
	}

	@GET
	@Path("/getGER_EVENTOS_PROGRAMADOSTesteEvento/{id}")
	@Produces("application/json")
	public List<GER_EVENTOS_PROGRAMADOS> testeEVENTO(@PathParam("id") Integer id) {
		List<GER_EVENTOS_PROGRAMADOS> data = dao36.getbyid(id);
		GER_EVENTOS_PROGRAMADOS evento = data.get(0);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		Date ultimarepeticao = null;
		Integer repeticao;
		SendEmail email = new SendEmail();
		ReportGenerator relatorio = new ReportGenerator();
		List<String> ficheiros = new ArrayList<String>();

		String pasta_destino = "";

		String senha = "";

		Boolean credencias = false;

		if (evento.getUTILIZADOR() != null && !evento.getUTILIZADOR().isEmpty()) {
			credencias = true;
			pasta_destino = "C:\\sgiid\\programador_eventos\\temp_files\\";
		} else {
			pasta_destino = evento.getPASTA_DESTINO();
		}

		if (evento.getSENHA() != null) {
			senha = new String(Base64.getDecoder().decode(evento.getSENHA()));
		}

		if (evento.getCRIAR_FICHEIRO()) {

			Query query_folder = entityManager.createNativeQuery(evento.getQUERY());

			List<Object[]> dados_folder = query_folder.getResultList();
			String filepath = getFILEPATH();

			for (Object[] content : dados_folder) {
				try {

					String nome = relatorio.relatorio2("pdf", content[1].toString(),
							Integer.parseInt(content[0].toString()), evento.getNOME_RELATORIO(), pasta_destino,
							filepath, getURL(), null);

					if (evento.getANEXA_FICHEIROS() != null && evento.getANEXA_FICHEIROS())
						ficheiros.add(nome);

					if (credencias)
						copyfiles(evento.getPASTA_DESTINO() + content[1].toString() + ".pdf",
								pasta_destino + content[1].toString() + ".pdf", senha, evento.getDOMINIO(),
								evento.getUTILIZADOR());

				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (evento.getENVIA_EMAIL()) {
			email.enviarEmail2("alertas.it.doureca@gmail.com", evento.getEMAIL_PARA(), evento.getEMAIL_ASSUNTO(),
					evento.getEMAIL_MENSAGEM(), ficheiros, pasta_destino);
		}
		return data;
	}

	public void copyfiles(String pastadestino, String ficheiro, String senha, String dominio, String user) {
		File tempFile = null;

		tempFile = new File(ficheiro);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(tempFile);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(dominio, user, senha);
		String path = "smb://" + pastadestino;
		SmbFile sFile = null;
		try {
			sFile = new SmbFile(path, auth);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SmbFileOutputStream sfos = null;
		try {
			sfos = new SmbFileOutputStream(sFile);
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			final byte[] buf = new byte[16 * 1024 * 1024];
			int len;
			while ((len = fis.read(buf)) > 0) {
				sfos.write(buf, 0, len);
			}
		} catch (IOException e) {
			String[] keyValuePairs = { "texto_erro ::" + e.getMessage() + " " + path + "", };
			verficaEventos(keyValuePairs, "Erros ao Criar Etiquetas", "");
			e.printStackTrace();
		}
		try {
			fis.close();
			sfos.close();
		} catch (IOException e) {
			String[] keyValuePairs = { "texto_erro ::" + e.getMessage() + " " + path + "", };
			verficaEventos(keyValuePairs, "Erros ao Criar Etiquetas", "");
			e.printStackTrace();
		}

	}

	@DELETE
	@Path("/deleteGER_EVENTOS_PROGRAMADOS/{id}")
	public void deleteGER_EVENTOS_PROGRAMADOS(@PathParam("id") Integer id) {
		GER_EVENTOS_PROGRAMADOS GER_EVENTOS_PROGRAMADOS = new GER_EVENTOS_PROGRAMADOS();
		GER_EVENTOS_PROGRAMADOS.setID(id);
		dao36.delete(GER_EVENTOS_PROGRAMADOS);
	}

	@PUT
	@Path("/updateGER_EVENTOS_PROGRAMADOS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_EVENTOS_PROGRAMADOS updateGER_EVENTOS_PROGRAMADOS(
			final GER_EVENTOS_PROGRAMADOS GER_EVENTOS_PROGRAMADOS) {
		GER_EVENTOS_PROGRAMADOS.setID(GER_EVENTOS_PROGRAMADOS.getID());
		return dao36.update(GER_EVENTOS_PROGRAMADOS);
	}

	/************************************* RC_DIC_FICHEIROS_ANALISE */
	@POST
	@Path("/createRC_DIC_FICHEIROS_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_FICHEIROS_ANALISE insertRC_DIC_FICHEIROS_ANALISEA(final RC_DIC_FICHEIROS_ANALISE data) {
		return dao37.create(data);
	}

	@GET
	@Path("/getRC_DIC_FICHEIROS_ANALISE")
	@Produces("application/json")
	public List<RC_DIC_FICHEIROS_ANALISE> getRC_DIC_FICHEIROS_ANALISE() {
		return dao37.getall();
	}

	@GET
	@Path("/getRC_DIC_FICHEIROS_ANALISEbyid/{id}")
	@Produces("application/json")
	public List<RC_DIC_FICHEIROS_ANALISE> getRC_DIC_FICHEIROS_ANALISEbyid(@PathParam("id") Integer id) {
		return dao37.getbyid(id);
	}

	@DELETE
	@Path("/deleteRC_DIC_FICHEIROS_ANALISE/{id}")
	public void deleteRC_DIC_FICHEIROS_ANALISE(@PathParam("id") Integer id) {
		RC_DIC_FICHEIROS_ANALISE RC_DIC_FICHEIROS_ANALISE = new RC_DIC_FICHEIROS_ANALISE();
		RC_DIC_FICHEIROS_ANALISE.setID(id);
		dao37.delete(RC_DIC_FICHEIROS_ANALISE);
	}

	@PUT
	@Path("/updateRC_DIC_FICHEIROS_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_FICHEIROS_ANALISE updateRC_DIC_FICHEIROS_ANALISE(
			final RC_DIC_FICHEIROS_ANALISE RC_DIC_FICHEIROS_ANALISE) {
		RC_DIC_FICHEIROS_ANALISE.setID(RC_DIC_FICHEIROS_ANALISE.getID());
		return dao37.update(RC_DIC_FICHEIROS_ANALISE);
	}

	/************************************* RC_DIC_GRAU_IMPORTANCIA */
	@POST
	@Path("/createRC_DIC_GRAU_IMPORTANCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_GRAU_IMPORTANCIA insertRC_DIC_GRAU_IMPORTANCIAA(final RC_DIC_GRAU_IMPORTANCIA data) {
		return dao38.create(data);
	}

	@GET
	@Path("/getRC_DIC_GRAU_IMPORTANCIA")
	@Produces("application/json")
	public List<RC_DIC_GRAU_IMPORTANCIA> getRC_DIC_GRAU_IMPORTANCIA() {
		return dao38.getall();
	}

	@DELETE
	@Path("/deleteRC_DIC_GRAU_IMPORTANCIA/{id}")
	public void deleteRC_DIC_GRAU_IMPORTANCIA(@PathParam("id") Integer id) {
		RC_DIC_GRAU_IMPORTANCIA RC_DIC_GRAU_IMPORTANCIA = new RC_DIC_GRAU_IMPORTANCIA();
		RC_DIC_GRAU_IMPORTANCIA.setID(id);
		dao38.delete(RC_DIC_GRAU_IMPORTANCIA);
	}

	@PUT
	@Path("/updateRC_DIC_GRAU_IMPORTANCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_GRAU_IMPORTANCIA updateRC_DIC_GRAU_IMPORTANCIA(
			final RC_DIC_GRAU_IMPORTANCIA RC_DIC_GRAU_IMPORTANCIA) {
		RC_DIC_GRAU_IMPORTANCIA.setID(RC_DIC_GRAU_IMPORTANCIA.getID());
		return dao38.update(RC_DIC_GRAU_IMPORTANCIA);
	}

	/************************************* RC_DIC_CLASSIFICACAO */
	@POST
	@Path("/createRC_DIC_CLASSIFICACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_CLASSIFICACAO insertRC_DIC_CLASSIFICACAOA(final RC_DIC_CLASSIFICACAO data) {
		return dao87.create(data);
	}

	@GET
	@Path("/getRC_DIC_CLASSIFICACAO")
	@Produces("application/json")
	public List<RC_DIC_CLASSIFICACAO> getRC_DIC_CLASSIFICACAO() {
		return dao87.getall();
	}

	@DELETE
	@Path("/deleteRC_DIC_CLASSIFICACAO/{id}")
	public void deleteRC_DIC_CLASSIFICACAO(@PathParam("id") Integer id) {
		RC_DIC_CLASSIFICACAO RC_DIC_CLASSIFICACAO = new RC_DIC_CLASSIFICACAO();
		RC_DIC_CLASSIFICACAO.setID(id);
		dao87.delete(RC_DIC_CLASSIFICACAO);
	}

	@PUT
	@Path("/updateRC_DIC_CLASSIFICACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_CLASSIFICACAO updateRC_DIC_CLASSIFICACAO(final RC_DIC_CLASSIFICACAO RC_DIC_CLASSIFICACAO) {
		RC_DIC_CLASSIFICACAO.setID(RC_DIC_CLASSIFICACAO.getID());
		return dao87.update(RC_DIC_CLASSIFICACAO);
	}

	/************************************* RC_DIC_TIPOLOGIA */
	@POST
	@Path("/createRC_DIC_TIPOLOGIA")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TIPOLOGIA insertRC_DIC_TIPOLOGIAA(final RC_DIC_TIPOLOGIA data) {
		return dao88.create(data);
	}

	@GET
	@Path("/getRC_DIC_TIPOLOGIA")
	@Produces("application/json")
	public List<RC_DIC_TIPOLOGIA> getRC_DIC_TIPOLOGIA() {
		return dao88.getall();
	}

	@DELETE
	@Path("/deleteRC_DIC_TIPOLOGIA/{id}")
	public void deleteRC_DIC_TIPOLOGIA(@PathParam("id") Integer id) {
		RC_DIC_TIPOLOGIA RC_DIC_TIPOLOGIA = new RC_DIC_TIPOLOGIA();
		RC_DIC_TIPOLOGIA.setID(id);
		dao88.delete(RC_DIC_TIPOLOGIA);
	}

	@PUT
	@Path("/updateRC_DIC_TIPOLOGIA")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TIPOLOGIA updateRC_DIC_TIPOLOGIA(final RC_DIC_TIPOLOGIA RC_DIC_TIPOLOGIA) {
		RC_DIC_TIPOLOGIA.setID(RC_DIC_TIPOLOGIA.getID());
		return dao88.update(RC_DIC_TIPOLOGIA);
	}

	/************************************* RC_DIC_REJEICAO */
	@POST
	@Path("/createRC_DIC_REJEICAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_REJEICAO insertRC_DIC_REJEICAO(final RC_DIC_REJEICAO data) {
		return dao39.create(data);
	}

	@GET
	@Path("/getRC_DIC_REJEICAO")
	@Produces("application/json")
	public List<RC_DIC_REJEICAO> getRC_DIC_REJEICAO() {
		return dao39.getall();
	}

	@DELETE
	@Path("/deleteRC_DIC_REJEICAO/{id}")
	public void deleteRC_DIC_REJEICAO(@PathParam("id") Integer id) {
		RC_DIC_REJEICAO RC_DIC_REJEICAO = new RC_DIC_REJEICAO();
		RC_DIC_REJEICAO.setID(id);
		dao39.delete(RC_DIC_REJEICAO);
	}

	@PUT
	@Path("/updateRC_DIC_REJEICAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_REJEICAO updateRC_DIC_REJEICAO(final RC_DIC_REJEICAO RC_DIC_REJEICAO) {
		RC_DIC_REJEICAO.setID(RC_DIC_REJEICAO.getID());
		return dao39.update(RC_DIC_REJEICAO);
	}

	/************************************* RC_DIC_TIPO_DEFEITO */
	@POST
	@Path("/createRC_DIC_TIPO_DEFEITO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TIPO_DEFEITO insertRC_DIC_TIPO_DEFEITOA(final RC_DIC_TIPO_DEFEITO data) {
		return dao40.create(data);
	}

	@GET
	@Path("/getRC_DIC_TIPO_DEFEITO")
	@Produces("application/json")
	public List<RC_DIC_TIPO_DEFEITO> getRC_DIC_TIPO_DEFEITO() {
		return dao40.getall();
	}

	@DELETE
	@Path("/deleteRC_DIC_TIPO_DEFEITO/{id}")
	public void deleteRC_DIC_TIPO_DEFEITO(@PathParam("id") Integer id) {
		RC_DIC_TIPO_DEFEITO RC_DIC_TIPO_DEFEITO = new RC_DIC_TIPO_DEFEITO();
		RC_DIC_TIPO_DEFEITO.setID(id);
		dao40.delete(RC_DIC_TIPO_DEFEITO);
	}

	@PUT
	@Path("/updateRC_DIC_TIPO_DEFEITO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TIPO_DEFEITO updateRC_DIC_TIPO_DEFEITO(final RC_DIC_TIPO_DEFEITO RC_DIC_TIPO_DEFEITO) {
		RC_DIC_TIPO_DEFEITO.setID(RC_DIC_TIPO_DEFEITO.getID());
		return dao40.update(RC_DIC_TIPO_DEFEITO);
	}

	/************************************* GT_DIC_TAREFAS */
	@POST
	@Path("/createGT_DIC_TAREFAS")
	@Consumes("*/*")
	@Produces("application/json")
	public GT_DIC_TAREFAS insertGT_DIC_TAREFASA(final GT_DIC_TAREFAS data) {
		return dao57.create(data);
	}

	@GET
	@Path("/getGT_DIC_TAREFAS")
	@Produces("application/json")
	public List<GT_DIC_TAREFAS> getGT_DIC_TAREFAS() {
		return dao57.getall();
	}

	@GET
	@Path("/getGT_DIC_TAREFASTIPO/{tipo}")
	@Produces("application/json")
	public List<GT_DIC_TAREFAS> getGT_DIC_TAREFASTIPO(@PathParam("tipo") String tipo) {
		return dao57.getalltipo(tipo);
	}

	@POST
	@Path("/GT_DIC_TAREFAS_verificaseExiste")
	@Consumes("*/*")
	@Produces("application/json")
	public List<GT_DIC_TAREFAS> verificaseExiste(final GT_DIC_TAREFAS data) {
		return dao57.verificaseExiste(data.getDESCRICAO_PT(), data.getTIPO_TAREFA());
	}

	@DELETE
	@Path("/deleteGT_DIC_TAREFAS/{id}")
	public void deleteGT_DIC_TAREFAS(@PathParam("id") Integer id) {
		GT_DIC_TAREFAS GT_DIC_TAREFAS = new GT_DIC_TAREFAS();
		GT_DIC_TAREFAS.setID(id);
		dao57.delete(GT_DIC_TAREFAS);
	}

	@PUT
	@Path("/updateGT_DIC_TAREFAS")
	@Consumes("*/*")
	@Produces("application/json")
	public GT_DIC_TAREFAS updateGT_DIC_TAREFAS(final GT_DIC_TAREFAS GT_DIC_TAREFAS) {
		GT_DIC_TAREFAS.setID(GT_DIC_TAREFAS.getID());
		return dao57.update(GT_DIC_TAREFAS);
	}

	/************************************* GT_DIC_TIPO_ACAO */

	@POST
	@Path("/createGT_DIC_TIPO_ACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public GT_DIC_TIPO_ACAO insertGT_DIC_TIPO_ACAOA(final GT_DIC_TIPO_ACAO data) {
		return dao103.create(data);
	}

	@GET
	@Path("/getGT_DIC_TIPO_ACAO")
	@Produces("application/json")
	public List<GT_DIC_TIPO_ACAO> getGT_DIC_TIPO_ACAO() {
		return dao103.getall();
	}

	@GET
	@Path("/getGT_DIC_TIPO_ACAObyid/{id}")
	@Produces("application/json")
	public List<GT_DIC_TIPO_ACAO> getGT_DIC_TIPO_ACAObyip(@PathParam("id") Integer id) {
		return dao103.getbyid(id);
	}

	@DELETE
	@Path("/deleteGT_DIC_TIPO_ACAO/{id}")
	public void deleteGT_DIC_TIPO_ACAO(@PathParam("id") Integer id) {
		GT_DIC_TIPO_ACAO GT_DIC_TIPO_ACAO = new GT_DIC_TIPO_ACAO();
		GT_DIC_TIPO_ACAO.setID_TIPO_ACAO(id);
		dao103.delete(GT_DIC_TIPO_ACAO);
	}

	@PUT
	@Path("/updateGT_DIC_TIPO_ACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public GT_DIC_TIPO_ACAO updateGT_DIC_TIPO_ACAO(final GT_DIC_TIPO_ACAO GT_DIC_TIPO_ACAO) {
		GT_DIC_TIPO_ACAO.setID_TIPO_ACAO(GT_DIC_TIPO_ACAO.getID_TIPO_ACAO());
		return dao103.update(GT_DIC_TIPO_ACAO);
	}

	/************************************* GER_DIC_OEM */

	@POST
	@Path("/createGER_DIC_OEM")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_OEM insertGER_DIC_OEMA(final GER_DIC_OEM data) {
		return dao104.create(data);
	}

	@POST
	@Path("/verificaGER_DIC_OEM")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> verificaGER_DIC_OEM(final GER_DIC_OEM data) {
		Query query = entityManager.createNativeQuery("select * from GER_DIC_OEM where nome = '" + data.getNOME() + "' "
				+ "AND ((not " + data.getID_OEM() + " is not null) or (ID_OEM  != " + data.getID_OEM() + ")) ");

		List<Object[]> dados = query.getResultList();
		return dados;
	}

	@GET
	@Path("/getGER_DIC_OEM")
	@Produces("application/json")
	public List<GER_DIC_OEM> getGER_DIC_OEM() {
		return dao104.getall();
	}

	@GET
	@Path("/getGER_DIC_OEMbyid/{id}")
	@Produces("application/json")
	public List<GER_DIC_OEM> getGER_DIC_OEMbyip(@PathParam("id") Integer id) {
		return dao104.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_DIC_OEM/{id}")
	public void deleteGER_DIC_OEM(@PathParam("id") Integer id) {
		GER_DIC_OEM GER_DIC_OEM = new GER_DIC_OEM();
		GER_DIC_OEM.setID_OEM(id);
		dao104.delete(GER_DIC_OEM);
	}

	@PUT
	@Path("/updateGER_DIC_OEM")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_OEM updateGER_DIC_OEM(final GER_DIC_OEM GER_DIC_OEM) {
		GER_DIC_OEM.setID_OEM(GER_DIC_OEM.getID_OEM());
		return dao104.update(GER_DIC_OEM);
	}

	/************************************* GER_DIC_FABRICA */

	@POST
	@Path("/createGER_DIC_FABRICA")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_FABRICA insertGER_DIC_FABRICAA(final GER_DIC_FABRICA data) {
		return dao105.create(data);
	}

	@POST
	@Path("/verificaGER_DIC_FABRICA")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> verificaGER_DIC_FABRICA(final GER_DIC_FABRICA data) {
		Query query = entityManager
				.createNativeQuery("select * from GER_DIC_FABRICA where nome = '" + data.getNOME() + "' " + "AND ((not "
						+ data.getID_FABRICA() + " is not null) or (id_fabrica  != " + data.getID_FABRICA() + ")) ");

		List<Object[]> dados = query.getResultList();
		return dados;
	}

	@GET
	@Path("/getGER_DIC_FABRICA")
	@Produces("application/json")
	public List<GER_DIC_FABRICA> getGER_DIC_FABRICA() {
		return dao105.getall();
	}

	@GET
	@Path("/getGER_DIC_FABRICAbyid/{id}")
	@Produces("application/json")
	public List<GER_DIC_FABRICA> getGER_DIC_FABRICAbyip(@PathParam("id") Integer id) {
		return dao105.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_DIC_FABRICA/{id}")
	public void deleteGER_DIC_FABRICA(@PathParam("id") Integer id) {
		GER_DIC_FABRICA GER_DIC_FABRICA = new GER_DIC_FABRICA();
		GER_DIC_FABRICA.setID_FABRICA(id);
		dao105.delete(GER_DIC_FABRICA);
	}

	@PUT
	@Path("/updateGER_DIC_FABRICA")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_FABRICA updateGER_DIC_FABRICA(final GER_DIC_FABRICA GER_DIC_FABRICA) {
		GER_DIC_FABRICA.setID_FABRICA(GER_DIC_FABRICA.getID_FABRICA());
		return dao105.update(GER_DIC_FABRICA);
	}

	/************************************* GER_DIC_PROGRAMA */

	@POST
	@Path("/createGER_DIC_PROGRAMA")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_PROGRAMA insertGER_DIC_PROGRAMAA(final GER_DIC_PROGRAMA data) {
		return dao106.create(data);
	}

	@POST
	@Path("/verificaGER_DIC_PROGRAMA")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> verificaGER_DIC_PROGRAMA(final GER_DIC_PROGRAMA data) {
		Query query = entityManager.createNativeQuery("select * from GER_DIC_PROGRAMA where nome = '" + data.getNOME()
				+ "' " + "AND ((not " + data.getID_PROGRAMA() + " is not null) or (ID_PROGRAMA  != "
				+ data.getID_PROGRAMA() + ")) " + "AND ID_VEICULO = " + data.getID_VEICULO() + "");

		List<Object[]> dados = query.getResultList();
		return dados;
	}

	@GET
	@Path("/getGER_DIC_PROGRAMA")
	@Produces("application/json")
	public List<GER_DIC_PROGRAMA> getGER_DIC_PROGRAMA() {
		return dao106.getall();
	}

	@GET
	@Path("/getGER_DIC_PROGRAMAbyid/{id}")
	@Produces("application/json")
	public List<GER_DIC_PROGRAMA> getGER_DIC_PROGRAMAbyip(@PathParam("id") Integer id) {
		return dao106.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_DIC_PROGRAMA/{id}")
	public void deleteGER_DIC_PROGRAMA(@PathParam("id") Integer id) {
		GER_DIC_PROGRAMA GER_DIC_PROGRAMA = new GER_DIC_PROGRAMA();
		GER_DIC_PROGRAMA.setID_PROGRAMA(id);
		dao106.delete(GER_DIC_PROGRAMA);
	}

	@PUT
	@Path("/updateGER_DIC_PROGRAMA")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_PROGRAMA updateGER_DIC_PROGRAMA(final GER_DIC_PROGRAMA GER_DIC_PROGRAMA) {
		GER_DIC_PROGRAMA.setID_PROGRAMA(GER_DIC_PROGRAMA.getID_PROGRAMA());
		return dao106.update(GER_DIC_PROGRAMA);
	}

	/************************************* GER_DIC_VEICULO */

	@POST
	@Path("/createGER_DIC_VEICULO")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_VEICULO insertGER_DIC_VEICULOA(final GER_DIC_VEICULO data) {
		return dao107.create(data);
	}

	@POST
	@Path("/verificaGER_DIC_VEICULO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> verificaGER_DIC_VEICULO(final GER_DIC_VEICULO data) {
		Query query = entityManager.createNativeQuery("select * from GER_DIC_VEICULO where nome = '" + data.getNOME()
				+ "' " + "AND ((not " + data.getID_VEICULO() + " is not null) or (ID_VEICULO  != "
				+ data.getID_VEICULO() + ")) AND ID_OEM = " + data.getID_OEM() + " ");

		List<Object[]> dados = query.getResultList();
		return dados;
	}

	@GET
	@Path("/getGER_DIC_VEICULO")
	@Produces("application/json")
	public List<GER_DIC_VEICULO> getGER_DIC_VEICULO() {
		return dao107.getall();
	}

	@GET
	@Path("/getGER_DIC_VEICULObyid/{id}")
	@Produces("application/json")
	public List<GER_DIC_VEICULO> getGER_DIC_VEICULObyip(@PathParam("id") Integer id) {
		return dao107.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_DIC_VEICULO/{id}")
	public void deleteGER_DIC_VEICULO(@PathParam("id") Integer id) {
		GER_DIC_VEICULO GER_DIC_VEICULO = new GER_DIC_VEICULO();
		GER_DIC_VEICULO.setID_VEICULO(id);
		dao107.delete(GER_DIC_VEICULO);
	}

	@PUT
	@Path("/updateGER_DIC_VEICULO")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_VEICULO updateGER_DIC_VEICULO(final GER_DIC_VEICULO GER_DIC_VEICULO) {
		GER_DIC_VEICULO.setID_VEICULO(GER_DIC_VEICULO.getID_VEICULO());
		return dao107.update(GER_DIC_VEICULO);
	}

	/************************************* GER_DIC_PROJ_CAB */

	@POST
	@Path("/createGER_DIC_PROJ_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_PROJ_CAB insertGER_DIC_PROJ_CABA(final GER_DIC_PROJ_CAB data) {
		return dao108.create(data);
	}

	@POST
	@Path("/verificaGER_DIC_PROJ_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> verificaGER_DIC_PROJ_CAB(final GER_DIC_PROJ_CAB data) {
		Query query = entityManager.createNativeQuery(
				"select * from GER_DIC_PROJ_CAB where ID_PROGRAMA = " + data.getID_PROGRAMA() + " " + " AND ((not "
						+ data.getID_PROJ_CAB() + " is not null) or (ID_PROJ_CAB  != " + data.getID_PROJ_CAB() + ")) ");

		List<Object[]> dados = query.getResultList();
		return dados;
	}

	@GET
	@Path("/getGER_DIC_PROJ_CAB")
	@Produces("application/json")
	public List<GER_DIC_PROJ_CAB> getGER_DIC_PROJ_CAB() {
		return dao108.getall();
	}

	@GET
	@Path("/getGER_DIC_PROJ_CAB2")
	@Produces("application/json")
	public List<GER_DIC_PROJ_CAB> getGER_DIC_PROJ_CAB2() {
		return dao108.getall2();
	}

	@GET
	@Path("/getGER_DIC_PROJ_CABbyid/{id}")
	@Produces("application/json")
	public List<GER_DIC_PROJ_CAB> getGER_DIC_PROJ_CABbyid(@PathParam("id") Integer id) {
		return dao108.getbyid(id);
	}

	@GET
	@Path("/getGER_DIC_PROJ_CABbyid2/{id}")
	@Produces("application/json")
	public List<GER_DIC_PROJ_CAB> getGER_DIC_PROJ_CABbyid2(@PathParam("id") Integer id) {
		return dao108.getbyid2(id);
	}

	@DELETE
	@Path("/deleteGER_DIC_PROJ_CAB/{id}")
	public void deleteGER_DIC_PROJ_CAB(@PathParam("id") Integer id) {
		GER_DIC_PROJ_CAB GER_DIC_PROJ_CAB = new GER_DIC_PROJ_CAB();
		GER_DIC_PROJ_CAB.setID_PROJ_CAB(id);
		dao108.delete(GER_DIC_PROJ_CAB);
	}

	@PUT
	@Path("/updateGER_DIC_PROJ_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_PROJ_CAB updateGER_DIC_PROJ_CAB(final GER_DIC_PROJ_CAB GER_DIC_PROJ_CAB) {
		GER_DIC_PROJ_CAB.setID_PROJ_CAB(GER_DIC_PROJ_CAB.getID_PROJ_CAB());
		return dao108.update(GER_DIC_PROJ_CAB);
	}

	@POST
	@Path("/analiseencomendasREFERENCIAS")
	@Produces("application/json")
	public List<Object[]> analiseencomendasREFERENCIAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA = firstMap.get("DATA");
		String ARMAZENS = firstMap.get("ARMAZENS");
		String COD_CLIENTE = firstMap.get("COD_CLIENTE");
		String NOME_CLIENTE = firstMap.get("NOME_CLIENTE");
		String PROREF = firstMap.get("PROREF");
		String CLIENTES = firstMap.get("CLIENTES");
		String NEGATIVOS = firstMap.get("NEGATIVOS");

		String PROGRAMAS = firstMap.get("PROGRAMAS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String FABRICAS = firstMap.get("FABRICAS");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER.dbo.[GET_ANALISE_REFERENCIAS_REF] '" + DATA_INICIO + "','" + DATA + "','" + ARMAZENS + "',"
						+ COD_CLIENTE + ",'" + NOME_CLIENTE + "','" + PROREF + "','" + CLIENTES + "'," + NEGATIVOS
						+ ",'" + PROGRAMAS + "','" + VEICULOS + "','" + OEM + "','" + FABRICAS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/analiseencomendasENCOMENDAS")
	@Produces("application/json")
	public List<Object[]> analiseencomendasENCOMENDAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String PROREF = firstMap.get("PROREF");
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA = firstMap.get("DATA");
		String ARMAZENS = firstMap.get("ARMAZENS");
		String COD_CLIENTE = firstMap.get("COD_CLIENTE");
		String NOME_CLIENTE = firstMap.get("NOME_CLIENTE");
		String CLIENTES = firstMap.get("CLIENTES");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER.dbo.[GET_ANALISE_REFERENCIAS_ENCOMENDAS] '" + PROREF + "','" + DATA_INICIO + "','" + DATA
						+ "','" + ARMAZENS + "'," + COD_CLIENTE + ",'" + NOME_CLIENTE + "','" + CLIENTES + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/analiseencomendasCOMPONENTES")
	@Produces("application/json")
	public List<Object[]> analiseencomendasCOMPONENTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String PROREF = firstMap.get("PROREF");

		Query query_folder = entityManager
				.createNativeQuery("EXEC SILVER.dbo.[GET_ANALISE_REFERENCIAS_COMPONENTES] '" + PROREF + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/analiseencomendasOEM")
	@Produces("application/json")
	public List<Object[]> analiseencomendasOEM(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String PROREF = firstMap.get("PROREF");

		Query query_folder = entityManager
				.createNativeQuery("EXEC SILVER.dbo.[GET_ANALISE_REFERENCIAS_OEM] '" + PROREF + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/analiseencomendasFABRICAS")
	@Produces("application/json")
	public List<Object[]> analiseencomendasFABRICAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String PROREF = firstMap.get("PROREF");
		String FABRICAS = firstMap.get("FABRICAS");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC SILVER.dbo.[GET_ANALISE_REFERENCIAS_FABRICAS] '" + PROREF + "','" + FABRICAS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/analisePORencomendas")
	@Produces("application/json")
	public List<Object[]> analisePORencomendas(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA_INICIO = firstMap.get("DATA_INICIO");
		String DATA = firstMap.get("DATA");
		String ARMAZENS = firstMap.get("ARMAZENS");
		String COD_CLIENTE = firstMap.get("COD_CLIENTE");
		String NOME_CLIENTE = firstMap.get("NOME_CLIENTE");
		String CLIENTES = firstMap.get("CLIENTES");
		String PROREF = firstMap.get("PROREF");
		String NEGATIVOS = firstMap.get("NEGATIVOS");
		String PROGRAMAS = firstMap.get("PROGRAMAS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String FABRICAS = firstMap.get("FABRICAS");

		Query query_folder = entityManager
				.createNativeQuery("EXEC SILVER.dbo.[GET_ANALISE_POR_ENCOMENDA] '" + DATA_INICIO + "','" + DATA + "','"
						+ ARMAZENS + "'," + COD_CLIENTE + ",'" + NOME_CLIENTE + "','" + PROREF + "','" + CLIENTES + "',"
						+ NEGATIVOS + ",'" + PROGRAMAS + "','" + VEICULOS + "','" + OEM + "','" + FABRICAS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/getArmazens")
	@Produces("application/json")
	public List<Object[]> getArmazens() {

		Query query_folder = entityManager.createNativeQuery("EXEC SILVER.dbo.[GET_ARMAZENS]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/getClientesProducao")
	@Produces("application/json")
	public List<Object[]> getClientesProducao() {

		Query query_folder = entityManager.createNativeQuery("EXEC SILVER.dbo.[GET_CLIENTES]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_ENCOMENDAS_CLIENTES")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_ENCOMENDAS_CLIENTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String SEMANA = firstMap.get("SEMANA");
		String ANO = firstMap.get("ANO");
		// String ARMAZENS = firstMap.get("ARMAZENS");
		String COD_CLIENTE = firstMap.get("COD_CLIENTE");
		String NOME_CLIENTE = firstMap.get("NOME_CLIENTE");
		String PROREF = firstMap.get("PROREF");
		// String CLIENTES = firstMap.get("CLIENTES");

		String PROGRAMAS = firstMap.get("PROGRAMAS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String FABRICAS = firstMap.get("FABRICAS");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_ANALISE_ENCOMENDAS_CLIENTES] " + SEMANA + ","
				+ ANO + ",'" + COD_CLIENTE + "','" + NOME_CLIENTE + "','" + PROREF + "','" + PROGRAMAS + "','"
				+ VEICULOS + "','" + OEM + "','" + FABRICAS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_ENCOMENDAS_ATRASOS")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_ENCOMENDAS_ATRASOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String SEMANA = firstMap.get("SEMANA");
		String ANO = firstMap.get("ANO");
		// String ARMAZENS = firstMap.get("ARMAZENS");
		String COD_CLIENTE = firstMap.get("COD_CLIENTE");
		String COD_ESTABELECIMENTO = firstMap.get("COD_ESTABELECIMENTO");
		String PROREF = firstMap.get("PROREF");
		// String CLIENTES = firstMap.get("CLIENTES");

		String PROGRAMAS = firstMap.get("PROGRAMAS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String FABRICAS = firstMap.get("FABRICAS");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_ANALISE_ENCOMENDAS_ATRASOS] " + SEMANA + ","
				+ ANO + ",'" + COD_CLIENTE + "','" + COD_ESTABELECIMENTO + "','" + PROREF + "','" + PROGRAMAS + "','"
				+ VEICULOS + "','" + OEM + "','" + FABRICAS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_ANALISE_ENCOMENDAS_REFERENCIAS")
	@Produces("application/json")
	public List<Object[]> GET_ANALISE_ENCOMENDAS_REFERENCIAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String SEMANA = firstMap.get("SEMANA");
		String ANO = firstMap.get("ANO");
		// String ARMAZENS = firstMap.get("ARMAZENS");
		String COD_CLIENTE = firstMap.get("COD_CLIENTE");
		String COD_ESTABELECIMENTO = firstMap.get("COD_ESTABELECIMENTO");
		String PROREF = firstMap.get("PROREF");
		// String CLIENTES = firstMap.get("CLIENTES");

		String PROGRAMAS = firstMap.get("PROGRAMAS");
		String VEICULOS = firstMap.get("VEICULOS");
		String OEM = firstMap.get("OEM");
		String FABRICAS = firstMap.get("FABRICAS");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_ANALISE_ENCOMENDAS_REFERENCIAS] " + SEMANA + ","
				+ ANO + ",'" + COD_CLIENTE + "','" + COD_ESTABELECIMENTO + "','" + PROREF + "','" + PROGRAMAS + "','"
				+ VEICULOS + "','" + OEM + "','" + FABRICAS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* GER_DIC_PROJ_REF */

	@POST
	@Path("/createGER_DIC_PROJ_REF")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_PROJ_REF insertGER_DIC_PROJ_REFA(final GER_DIC_PROJ_REF data) {
		return dao109.create(data);
	}

	@GET
	@Path("/getGER_DIC_PROJ_REF")
	@Produces("application/json")
	public List<GER_DIC_PROJ_REF> getGER_DIC_PROJ_REF() {
		return dao109.getall();
	}

	@GET
	@Path("/getGER_DIC_PROJ_REFbyid/{id}")
	@Produces("application/json")
	public List<GER_DIC_PROJ_REF> getGER_DIC_PROJ_REFbyip(@PathParam("id") Integer id) {
		return dao109.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_DIC_PROJ_REF/{id}")
	public void deleteGER_DIC_PROJ_REF(@PathParam("id") Integer id) {
		GER_DIC_PROJ_REF GER_DIC_PROJ_REF = new GER_DIC_PROJ_REF();
		GER_DIC_PROJ_REF.setID_PROJ_REF(id);
		dao109.delete(GER_DIC_PROJ_REF);
	}

	@PUT
	@Path("/updateGER_DIC_PROJ_REF")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_PROJ_REF updateGER_DIC_PROJ_REF(final GER_DIC_PROJ_REF GER_DIC_PROJ_REF) {
		GER_DIC_PROJ_REF.setID_PROJ_REF(GER_DIC_PROJ_REF.getID_PROJ_REF());
		return dao109.update(GER_DIC_PROJ_REF);
	}

	/************************************* GER_DIC_PROJ_FAB */

	@POST
	@Path("/createGER_DIC_PROJ_FAB")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_PROJ_FAB insertGER_DIC_PROJ_FABA(final GER_DIC_PROJ_FAB data) {
		return dao110.create(data);
	}

	@GET
	@Path("/getGER_DIC_PROJ_FAB")
	@Produces("application/json")
	public List<GER_DIC_PROJ_FAB> getGER_DIC_PROJ_FAB() {
		return dao110.getall();
	}

	@GET
	@Path("/getGER_DIC_PROJ_FABbyid/{id}")
	@Produces("application/json")
	public List<GER_DIC_PROJ_FAB> getGER_DIC_PROJ_FABbyip(@PathParam("id") Integer id) {
		return dao110.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_DIC_PROJ_FAB/{id}")
	public void deleteGER_DIC_PROJ_FAB(@PathParam("id") Integer id) {
		GER_DIC_PROJ_FAB GER_DIC_PROJ_FAB = new GER_DIC_PROJ_FAB();
		GER_DIC_PROJ_FAB.setID_PROJ_FAB(id);
		dao110.delete(GER_DIC_PROJ_FAB);
	}

	@PUT
	@Path("/updateGER_DIC_PROJ_FAB")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_PROJ_FAB updateGER_DIC_PROJ_FAB(final GER_DIC_PROJ_FAB GER_DIC_PROJ_FAB) {
		GER_DIC_PROJ_FAB.setID_PROJ_FAB(GER_DIC_PROJ_FAB.getID_PROJ_FAB());
		return dao110.update(GER_DIC_PROJ_FAB);
	}

	/************************************* GER_DIC_LIMITES_ENCOMENDA */

	@POST
	@Path("/createGER_DIC_LIMITES_ENCOMENDA")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_LIMITES_ENCOMENDA insertGER_DIC_LIMITES_ENCOMENDAA(final GER_DIC_LIMITES_ENCOMENDA data) {
		return dao111.create(data);
	}

	@GET
	@Path("/getGER_DIC_LIMITES_ENCOMENDA")
	@Produces("application/json")
	public List<GER_DIC_LIMITES_ENCOMENDA> getGER_DIC_LIMITES_ENCOMENDA() {
		return dao111.getall();
	}

	@GET
	@Path("/getGER_DIC_LIMITES_ENCOMENDAbyid/{id}")
	@Produces("application/json")
	public List<GER_DIC_LIMITES_ENCOMENDA> getGER_DIC_LIMITES_ENCOMENDAbyip(@PathParam("id") Integer id) {
		return dao111.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_DIC_LIMITES_ENCOMENDA/{id}")
	public void deleteGER_DIC_LIMITES_ENCOMENDA(@PathParam("id") Integer id) {
		GER_DIC_LIMITES_ENCOMENDA GER_DIC_LIMITES_ENCOMENDA = new GER_DIC_LIMITES_ENCOMENDA();
		GER_DIC_LIMITES_ENCOMENDA.setID_LIMITE(id);
		dao111.delete(GER_DIC_LIMITES_ENCOMENDA);
	}

	@PUT
	@Path("/updateGER_DIC_LIMITES_ENCOMENDA")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DIC_LIMITES_ENCOMENDA updateGER_DIC_LIMITES_ENCOMENDA(
			final GER_DIC_LIMITES_ENCOMENDA GER_DIC_LIMITES_ENCOMENDA) {
		GER_DIC_LIMITES_ENCOMENDA.setID_LIMITE(GER_DIC_LIMITES_ENCOMENDA.getID_LIMITE());
		return dao111.update(GER_DIC_LIMITES_ENCOMENDA);
	}

	/************************************* RC_DIC_TIPO_RECLAMACAO */
	@POST
	@Path("/createRC_DIC_TIPO_RECLAMACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TIPO_RECLAMACAO insertRC_DIC_TIPO_RECLAMACAOA(final RC_DIC_TIPO_RECLAMACAO data) {
		return dao41.create(data);
	}

	@GET
	@Path("/getRC_DIC_TIPO_RECLAMACAO")
	@Produces("application/json")
	public List<RC_DIC_TIPO_RECLAMACAO> getRC_DIC_TIPO_RECLAMACAO() {
		return dao41.getall();
	}

	@DELETE
	@Path("/deleteRC_DIC_TIPO_RECLAMACAO/{id}")
	public void deleteRC_DIC_TIPO_RECLAMACAO(@PathParam("id") Integer id) {
		RC_DIC_TIPO_RECLAMACAO RC_DIC_TIPO_RECLAMACAO = new RC_DIC_TIPO_RECLAMACAO();
		RC_DIC_TIPO_RECLAMACAO.setID(id);
		dao41.delete(RC_DIC_TIPO_RECLAMACAO);
	}

	@PUT
	@Path("/updateRC_DIC_TIPO_RECLAMACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TIPO_RECLAMACAO updateRC_DIC_TIPO_RECLAMACAO(final RC_DIC_TIPO_RECLAMACAO RC_DIC_TIPO_RECLAMACAO) {
		RC_DIC_TIPO_RECLAMACAO.setID(RC_DIC_TIPO_RECLAMACAO.getID());
		return dao41.update(RC_DIC_TIPO_RECLAMACAO);
	}

	/************************************* FIN_DIC_OBJETIVOS */

	@POST
	@Path("/createFIN_DIC_OBJETIVOS")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_OBJETIVOS insertFIN_DIC_OBJETIVOSA(final FIN_DIC_OBJETIVOS data) {
		return dao112.create(data);
	}

	@GET
	@Path("/getFIN_DIC_OBJETIVOS")
	@Produces("application/json")
	public List<FIN_DIC_OBJETIVOS> getFIN_DIC_OBJETIVOS() {
		return dao112.getall();
	}

	@GET
	@Path("/getFIN_DIC_OBJETIVOS2")
	@Produces("application/json")
	public List<FIN_DIC_OBJETIVOS> getFIN_DIC_OBJETIVOS2() {
		return dao112.getall2();
	}

	@GET
	@Path("/getFIN_DIC_OBJETIVOSbyid/{id}")
	@Produces("application/json")
	public List<FIN_DIC_OBJETIVOS> getFIN_DIC_OBJETIVOSbyip(@PathParam("id") Integer id) {
		return dao112.getbyid(id);
	}

	@DELETE
	@Path("/deleteFIN_DIC_OBJETIVOS/{id}")
	public void deleteFIN_DIC_OBJETIVOS(@PathParam("id") Integer id) {
		FIN_DIC_OBJETIVOS FIN_DIC_OBJETIVOS = new FIN_DIC_OBJETIVOS();
		FIN_DIC_OBJETIVOS.setID_OBJETIVO(id);
		dao112.delete(FIN_DIC_OBJETIVOS);
	}

	@PUT
	@Path("/updateFIN_DIC_OBJETIVOS")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_OBJETIVOS updateFIN_DIC_OBJETIVOS(final FIN_DIC_OBJETIVOS FIN_DIC_OBJETIVOS) {
		FIN_DIC_OBJETIVOS.setID_OBJETIVO(FIN_DIC_OBJETIVOS.getID_OBJETIVO());
		return dao112.update(FIN_DIC_OBJETIVOS);
	}

	@POST
	@Path("/seguimentosFaturacao")
	@Produces("application/json")
	public List<Object[]> seguimentosFaturacao(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_SEGUIMENTO_FATURACAO] '" + DATA + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* FIN_DIC_CLIENTES */

	@POST
	@Path("/GET_DIVIDAS_LISTA")
	@Produces("application/json")
	public List<Object[]> GET_DIVIDAS_LISTA(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_DIVIDAS_LISTA]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_DIVIDAS_CLIENTES")
	@Produces("application/json")
	public List<Object[]> GET_DIVIDAS_CLIENTES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String CLIENTE = firstMap.get("CLIENTE");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_DIVIDAS_CLIENTES] '" + CLIENTE + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_DIVIDAS_FICHA")
	@Produces("application/json")
	public List<Object[]> GET_DIVIDAS_FICHA(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String CLIENTE = firstMap.get("CLIENTE");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_DIVIDAS_FICHA] '" + CLIENTE + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/GET_KAMS")
	@Produces("application/json")
	public List<Object[]> GET_KAMS() {

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_KAMS]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/* FICHEIRO DIVIDAS ************************************/
	@POST
	@Path("/get_file_dividias/{format}/{filename}/{relatorio}")
	// @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response get_file_dividias(@PathParam("format") String format, @PathParam("filename") String Name,
			@PathParam("relatorio") String relatorio, final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String CLIENTE = firstMap.get("CLIENTE");
		String DOCUMENTOS = firstMap.get("DOCUMENTOS");
		String IDIOMA = firstMap.get("IDIOMA");

		String fileName = null;
		String filepath = getFILEPATH();
		ReportGenerator report = new ReportGenerator();
		try {
			fileName = report.relatorio(format, Name, null, relatorio, getURL(), filepath, "financeira/", CLIENTE,
					DOCUMENTOS, IDIOMA, null);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fileName != null) {
			File file = new File("/" + filepath + "/relatorios/" + fileName);
			ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition", "attachment; filename=report." + format + "");
			return response.build();
		} else {
			return null;
		}

	}

	@POST
	@Path("/createFIN_DIC_CLIENTES")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_CLIENTES insertFIN_DIC_CLIENTESA(final FIN_DIC_CLIENTES data) {
		return dao113.create(data);
	}

	@GET
	@Path("/getFIN_DIC_CLIENTES")
	@Produces("application/json")
	public List<FIN_DIC_CLIENTES> getFIN_DIC_CLIENTES() {
		return dao113.getall();
	}

	@GET
	@Path("/getFIN_DIC_CLIENTESbyid/{id}")
	@Produces("application/json")
	public List<FIN_DIC_CLIENTES> getFIN_DIC_CLIENTESbyip(@PathParam("id") String id) {
		return dao113.getbyid(id);
	}

	@DELETE
	@Path("/deleteFIN_DIC_CLIENTES/{id}")
	public void deleteFIN_DIC_CLIENTES(@PathParam("id") Integer id) {
		FIN_DIC_CLIENTES FIN_DIC_CLIENTES = new FIN_DIC_CLIENTES();
		FIN_DIC_CLIENTES.setID(id);
		dao113.delete(FIN_DIC_CLIENTES);
	}

	@PUT
	@Path("/updateFIN_DIC_CLIENTES")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_CLIENTES updateFIN_DIC_CLIENTES(final FIN_DIC_CLIENTES FIN_DIC_CLIENTES) {
		FIN_DIC_CLIENTES.setID(FIN_DIC_CLIENTES.getID());
		return dao113.update(FIN_DIC_CLIENTES);
	}

	/************************************* FIN_DIC_TIPO_DOC */

	@POST
	@Path("/createFIN_DIC_TIPO_DOC")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_TIPO_DOC insertFIN_DIC_TIPO_DOCA(final FIN_DIC_TIPO_DOC data) {
		return dao114.create(data);
	}

	@GET
	@Path("/getFIN_DIC_TIPO_DOC")
	@Produces("application/json")
	public List<FIN_DIC_TIPO_DOC> getFIN_DIC_TIPO_DOC() {
		return dao114.getall();
	}

	@GET
	@Path("/getFIN_DIC_TIPO_DOCbyid/{id}")
	@Produces("application/json")
	public List<FIN_DIC_TIPO_DOC> getFIN_DIC_TIPO_DOCbyip(@PathParam("id") Integer id) {
		return dao114.getbyid(id);
	}

	@DELETE
	@Path("/deleteFIN_DIC_TIPO_DOC/{id}")
	public void deleteFIN_DIC_TIPO_DOC(@PathParam("id") Integer id) {
		FIN_DIC_TIPO_DOC FIN_DIC_TIPO_DOC = new FIN_DIC_TIPO_DOC();
		FIN_DIC_TIPO_DOC.setID(id);
		dao114.delete(FIN_DIC_TIPO_DOC);
	}

	@PUT
	@Path("/updateFIN_DIC_TIPO_DOC")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIC_TIPO_DOC updateFIN_DIC_TIPO_DOC(final FIN_DIC_TIPO_DOC FIN_DIC_TIPO_DOC) {
		FIN_DIC_TIPO_DOC.setID(FIN_DIC_TIPO_DOC.getID());
		return dao114.update(FIN_DIC_TIPO_DOC);
	}

	/************************************* FIN_DOC_ACORDO */

	@POST
	@Path("/createFIN_DOC_ACORDO")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DOC_ACORDO insertFIN_DOC_ACORDOA(final FIN_DOC_ACORDO data) {
		return dao115.create(data);
	}

	@GET
	@Path("/getFIN_DOC_ACORDO")
	@Produces("application/json")
	public List<FIN_DOC_ACORDO> getFIN_DOC_ACORDO() {
		return dao115.getall();
	}

	@GET
	@Path("/getFIN_DOC_ACORDObyid/{id}")
	@Produces("application/json")
	public List<FIN_DOC_ACORDO> getFIN_DOC_ACORDObyip(@PathParam("id") String id) {
		return dao115.getbyid(id);
	}

	@DELETE
	@Path("/deleteFIN_DOC_ACORDO/{id}")
	public void deleteFIN_DOC_ACORDO(@PathParam("id") Integer id) {
		FIN_DOC_ACORDO FIN_DOC_ACORDO = new FIN_DOC_ACORDO();
		FIN_DOC_ACORDO.setID(id);
		dao115.delete(FIN_DOC_ACORDO);
	}

	@PUT
	@Path("/updateFIN_DOC_ACORDO")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DOC_ACORDO updateFIN_DOC_ACORDO(final FIN_DOC_ACORDO FIN_DOC_ACORDO) {
		FIN_DOC_ACORDO.setID(FIN_DOC_ACORDO.getID());
		return dao115.update(FIN_DOC_ACORDO);
	}

	/************************************* FIN_DIVIDAS_ATIVIDADE */

	@POST
	@Path("/createFIN_DIVIDAS_ATIVIDADE")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIVIDAS_ATIVIDADE insertFIN_DIVIDAS_ATIVIDADEA(final FIN_DIVIDAS_ATIVIDADE data) {
		return dao116.create(data);
	}

	@GET
	@Path("/getFIN_DIVIDAS_ATIVIDADE")
	@Produces("application/json")
	public List<FIN_DIVIDAS_ATIVIDADE> getFIN_DIVIDAS_ATIVIDADE() {
		return dao116.getall();
	}

	@GET
	@Path("/getFIN_DIVIDAS_ATIVIDADEbyid/{id}")
	@Produces("application/json")
	public List<FIN_DIVIDAS_ATIVIDADE> getFIN_DIVIDAS_ATIVIDADEbyip(@PathParam("id") String id) {
		return dao116.getbyid(id);
	}

	@DELETE
	@Path("/deleteFIN_DIVIDAS_ATIVIDADE/{id}")
	public void deleteFIN_DIVIDAS_ATIVIDADE(@PathParam("id") Integer id) {
		FIN_DIVIDAS_ATIVIDADE FIN_DIVIDAS_ATIVIDADE = new FIN_DIVIDAS_ATIVIDADE();
		FIN_DIVIDAS_ATIVIDADE.setID(id);
		dao116.delete(FIN_DIVIDAS_ATIVIDADE);
	}

	@PUT
	@Path("/updateFIN_DIVIDAS_ATIVIDADE")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_DIVIDAS_ATIVIDADE updateFIN_DIVIDAS_ATIVIDADE(final FIN_DIVIDAS_ATIVIDADE FIN_DIVIDAS_ATIVIDADE) {
		FIN_DIVIDAS_ATIVIDADE.setID(FIN_DIVIDAS_ATIVIDADE.getID());
		return dao116.update(FIN_DIVIDAS_ATIVIDADE);
	}

	/************************************* FIN_REGISTO_ACOES */

	@POST
	@Path("/createFIN_REGISTO_ACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_REGISTO_ACOES insertFIN_REGISTO_ACOESA(final FIN_REGISTO_ACOES data) {
		return dao117.create(data);
	}

	@GET
	@Path("/getFIN_REGISTO_ACOES")
	@Produces("application/json")
	public List<FIN_REGISTO_ACOES> getFIN_REGISTO_ACOES() {
		return dao117.getall();
	}

	@GET
	@Path("/getFIN_REGISTO_ACOESbyid/{id}")
	@Produces("application/json")
	public List<FIN_REGISTO_ACOES> getFIN_REGISTO_ACOESbyip(@PathParam("id") String id) {
		return dao117.getbyid(id);
	}

	@GET
	@Path("/getFIN_REGISTO_ACOESbyidFICHEIRO/{id}")
	@Produces("application/json")
	public List<FIN_REGISTO_ACOES> getFIN_REGISTO_ACOESbyidFICHEIRO(@PathParam("id") String id) {
		return dao117.getbyidFicheiro(id);
	}

	@DELETE
	@Path("/deleteFIN_REGISTO_ACOES/{id}")
	public void deleteFIN_REGISTO_ACOES(@PathParam("id") Integer id) {
		FIN_REGISTO_ACOES FIN_REGISTO_ACOES = new FIN_REGISTO_ACOES();
		FIN_REGISTO_ACOES.setID_ACAO(id);
		dao117.delete(FIN_REGISTO_ACOES);
	}

	@PUT
	@Path("/updateFIN_REGISTO_ACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public FIN_REGISTO_ACOES updateFIN_REGISTO_ACOES(final FIN_REGISTO_ACOES FIN_REGISTO_ACOES) {
		FIN_REGISTO_ACOES.setID_ACAO(FIN_REGISTO_ACOES.getID_ACAO());
		return dao117.update(FIN_REGISTO_ACOES);
	}

	/************************************* RC_MOV_RECLAMACAO */
	@POST
	@Path("/createRC_MOV_RECLAMACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO insertRC_MOV_RECLAMACAOA(final RC_MOV_RECLAMACAO data) {
		return dao42.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO> getRC_MOV_RECLAMACAO() {
		return dao42.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAObyid/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO> getRC_MOV_RECLAMACAObyid(@PathParam("id") Integer id) {
		return dao42.getbyid(id);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAOACCOESABERTAS/{id}/{tipo}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO> getRC_MOV_RECLAMACAOACCOESABERTAS(@PathParam("id") Integer id,
			@PathParam("tipo") String tipo) {
		return dao42.getbyAccoesabertas(id, tipo);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAObyidtotaltarefas/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO> getRC_MOV_RECLAMACAObyidtotaltarefas(@PathParam("id") Integer id) {
		return dao42.getbyidtotaltarefas(id);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO/{id}")
	public void deleteRC_MOV_RECLAMACAO(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO RC_MOV_RECLAMACAO = new RC_MOV_RECLAMACAO();
		RC_MOV_RECLAMACAO.setID_RECLAMACAO(id);
		dao42.delete(RC_MOV_RECLAMACAO);
	}

	@GET
	@Path("/deleteRC_MOV_RECLAMACAUPDATEESTADOS/{id}/{modulo}")
	@Produces("application/json")
	public void deleteRC_MOV_RECLAMACAUPDATEESTADOS(@PathParam("id") Integer id, @PathParam("modulo") Integer modulo) {

		Query query = entityManager
				.createNativeQuery("UPDATE RC_MOV_RECLAMACAO_PLANOS_ACCOES SET ESTADO = 'A' where ID_RECLAMACAO = " + id
						+ " and ESTADO != 'C' "
						+ "UPDATE GT_MOV_TAREFAS SET ESTADO = 'A' where ID_CAMPO in (select ID from RC_MOV_RECLAMACAO_PLANOS_ACCOES where ID_RECLAMACAO = '"
						+ id + "') and ID_MODULO='" + modulo + "' and SUB_MODULO != 'D' and ESTADO != 'C'");

		query.executeUpdate();

	}

	@GET
	@Path("/atualizaestadosRC_MOV_RECLAMACAUPDATEESTADOS/{id}/{modulo}/{tipo}")
	@Produces("application/json")
	public void atualizaestadosRC_MOV_RECLAMACAUPDATEESTADOS(@PathParam("id") Integer id,
			@PathParam("modulo") Integer modulo, @PathParam("tipo") String tipo) {

		Query query = entityManager
				.createNativeQuery("UPDATE RC_MOV_RECLAMACAO_PLANOS_ACCOES SET ESTADO = 'N' where ID_RECLAMACAO = " + id
						+ " and tipo = '" + tipo + "' "
						+ "UPDATE GT_MOV_TAREFAS SET ESTADO = 'N' where ID_CAMPO in (select ID from RC_MOV_RECLAMACAO_PLANOS_ACCOES where ID_RECLAMACAO = "
						+ id + "and TIPO='" + tipo + "') and ID_MODULO='" + modulo + "' and SUB_MODULO != 'D' ");

		query.executeUpdate();

	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO updateRC_MOV_RECLAMACAO(final RC_MOV_RECLAMACAO RC_MOV_RECLAMACAO) {
		RC_MOV_RECLAMACAO.setID_RECLAMACAO(RC_MOV_RECLAMACAO.getID_RECLAMACAO());
		return dao42.update(RC_MOV_RECLAMACAO);
	}

	/************************************* RC_MOV_RECLAMACAO_FORNECEDOR */
	@POST
	@Path("/createRC_MOV_RECLAMACAO_FORNECEDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_FORNECEDOR insertRC_MOV_RECLAMACAO_FORNECEDORA(final RC_MOV_RECLAMACAO_FORNECEDOR data) {
		return dao89.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_FORNECEDOR")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_FORNECEDOR> getRC_MOV_RECLAMACAO_FORNECEDOR() {
		return dao89.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_FORNECEDOR2")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_FORNECEDOR> getRC_MOV_RECLAMACAO_FORNECEDOR2() {
		return dao89.getall2();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_FORNECEDORbyid/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_FORNECEDOR> getRC_MOV_RECLAMACAO_FORNECEDORbyid(@PathParam("id") Integer id) {
		return dao89.getbyid(id);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO_FORNECEDOR/{id}")
	public void deleteRC_MOV_RECLAMACAO_FORNECEDOR(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO_FORNECEDOR RC_MOV_RECLAMACAO_FORNECEDOR = new RC_MOV_RECLAMACAO_FORNECEDOR();
		RC_MOV_RECLAMACAO_FORNECEDOR.setID_RECLAMACAO(id);
		dao89.delete(RC_MOV_RECLAMACAO_FORNECEDOR);
	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO_FORNECEDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_FORNECEDOR updateRC_MOV_RECLAMACAO_FORNECEDOR(
			final RC_MOV_RECLAMACAO_FORNECEDOR RC_MOV_RECLAMACAO_FORNECEDOR) {
		RC_MOV_RECLAMACAO_FORNECEDOR.setID_RECLAMACAO(RC_MOV_RECLAMACAO_FORNECEDOR.getID_RECLAMACAO());
		return dao89.update(RC_MOV_RECLAMACAO_FORNECEDOR);
	}

	// VERSAO_APP*************************************************
	@GET
	@Path("/getVERSAO_APP")
	@Produces("application/json")
	public List<Object[]> getVERSAO_APPO() {
		Query query = entityManager.createNativeQuery("select top 1 VERSAO_SGIID,'' as d from VERSAO_APP");

		List<Object[]> dados = query.getResultList();
		return dados;
	}

	/************************************* RC_MOV_RECLAMACAO_ARTIGO_SIMILARES */
	@POST
	@Path("/createRC_MOV_RECLAMACAO_ARTIGO_SIMILARES")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_ARTIGO_SIMILARES insertRC_MOV_RECLAMACAO_ARTIGO_SIMILARES(
			final RC_MOV_RECLAMACAO_ARTIGO_SIMILARES data) {
		return dao43.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_ARTIGO_SIMILARES")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_ARTIGO_SIMILARES> getRC_MOV_RECLAMACAO_ARTIGO_SIMILARES() {
		return dao43.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_ARTIGO_SIMILARESbyid_reclamacao/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_ARTIGO_SIMILARES> getRC_MOV_RECLAMACAO_ARTIGO_SIMILARESbyid_reclamacao(
			@PathParam("id") Integer id) {
		return dao43.getbyid(id);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO_ARTIGO_SIMILARES/{id}")
	public void deleteRC_MOV_RECLAMACAO_ARTIGO_SIMILARES(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO_ARTIGO_SIMILARES RC_MOV_RECLAMACAO_ARTIGO_SIMILARES = new RC_MOV_RECLAMACAO_ARTIGO_SIMILARES();
		RC_MOV_RECLAMACAO_ARTIGO_SIMILARES.setID(id);
		dao43.delete(RC_MOV_RECLAMACAO_ARTIGO_SIMILARES);
	}

	@DELETE
	@Path("/deleteLINHASRC_MOV_RECLAMACAO_ARTIGO_SIMILARES/{id}/{id_cab}")
	public void deleteLINHASRC_MOV_RECLAMACAO_ARTIGO_SIMILARES(@PathParam("id") Integer id,
			@PathParam("id_cab") Integer id_cab) {
		Query query = entityManager.createNativeQuery(
				"DELETE  RC_MOV_RECLAMACAO_STOCK where ID_RECLAMACAO = " + id_cab + " and ID_LINHA_ARTIGO_SIMILAR = "
						+ id + " " + "DELETE from  RC_MOV_RECLAMACAO_ENCOMENDAS where ID_RECLAMACAO = " + id_cab
						+ " and ID_LINHA_ARTIGO_SIMILAR = " + id + "");

		query.executeUpdate();

	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO_ARTIGO_SIMILARES")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_ARTIGO_SIMILARES updateRC_MOV_RECLAMACAO_ARTIGO_SIMILARES(
			final RC_MOV_RECLAMACAO_ARTIGO_SIMILARES RC_MOV_RECLAMACAO_ARTIGO_SIMILARES) {
		RC_MOV_RECLAMACAO_ARTIGO_SIMILARES.setID(RC_MOV_RECLAMACAO_ARTIGO_SIMILARES.getID());
		return dao43.update(RC_MOV_RECLAMACAO_ARTIGO_SIMILARES);
	}

	/************************************* RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS */
	@POST
	@Path("/createRC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS insertRC_MOV_RECLAMACAO_ENVIOS_GARANTIDOSA(
			final RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS data) {
		return dao44.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS> getRC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS() {
		return dao44.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_ENVIOS_GARANTIDOSbyid_reclamacao/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS> getRC_MOV_RECLAMACAO_ENVIOS_GARANTIDOSbyid_reclamacao(
			@PathParam("id") Integer id) {
		return dao44.getbyid(id);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS/{id}")
	public void deleteRC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS = new RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS();
		RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS.setID(id);
		dao44.delete(RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS);
	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS updateRC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS(
			final RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS) {
		RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS.setID(RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS.getID());
		return dao44.update(RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS);
	}

	/************************************* RC_MOV_RECLAMACAO_EQUIPA */
	@POST
	@Path("/createRC_MOV_RECLAMACAO_EQUIPA")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_EQUIPA insertRC_MOV_RECLAMACAO_EQUIPAA(final RC_MOV_RECLAMACAO_EQUIPA data) {
		return dao45.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_EQUIPA")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_EQUIPA> getRC_MOV_RECLAMACAO_EQUIPA() {
		return dao45.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_EQUIPAbyid_reclamacao/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_EQUIPA> getRC_MOV_RECLAMACAO_EQUIPAbyid_reclamacao(@PathParam("id") Integer id) {
		return dao45.getbyid(id);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO_EQUIPA/{id}")
	public void deleteRC_MOV_RECLAMACAO_EQUIPA(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO_EQUIPA RC_MOV_RECLAMACAO_EQUIPA = new RC_MOV_RECLAMACAO_EQUIPA();
		RC_MOV_RECLAMACAO_EQUIPA.setID(id);
		dao45.delete(RC_MOV_RECLAMACAO_EQUIPA);
	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO_EQUIPA")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_EQUIPA updateRC_MOV_RECLAMACAO_EQUIPA(
			final RC_MOV_RECLAMACAO_EQUIPA RC_MOV_RECLAMACAO_EQUIPA) {
		RC_MOV_RECLAMACAO_EQUIPA.setID(RC_MOV_RECLAMACAO_EQUIPA.getID());
		return dao45.update(RC_MOV_RECLAMACAO_EQUIPA);
	}

	/************************************* RC_MOV_RECLAMACAO_FICHEIROS */
	@POST
	@Path("/createRC_MOV_RECLAMACAO_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_FICHEIROS insertRC_MOV_RECLAMACAO_FICHEIROSA(final RC_MOV_RECLAMACAO_FICHEIROS data) {
		return dao46.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_FICHEIROS")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_FICHEIROS> getRC_MOV_RECLAMACAO_FICHEIROS() {
		return dao46.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_FICHEIROSbyidTAREFA/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_FICHEIROS> getRC_MOV_RECLAMACAO_FICHEIROSbyidTAREFA(@PathParam("id") Integer id) {
		return dao46.getbyidtarefa(id);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_FICHEIROSbyidRECLAMACAO/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_FICHEIROS> getRC_MOV_RECLAMACAO_FICHEIROSbyidRECLAMACAO(@PathParam("id") Integer id) {
		return dao46.getbyid(id);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO_FICHEIROS/{id}")
	public void deleteRC_MOV_RECLAMACAO_FICHEIROS(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO_FICHEIROS RC_MOV_RECLAMACAO_FICHEIROS = new RC_MOV_RECLAMACAO_FICHEIROS();
		RC_MOV_RECLAMACAO_FICHEIROS.setID(id);
		dao46.delete(RC_MOV_RECLAMACAO_FICHEIROS);
	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_FICHEIROS updateRC_MOV_RECLAMACAO_FICHEIROS(
			final RC_MOV_RECLAMACAO_FICHEIROS RC_MOV_RECLAMACAO_FICHEIROS) {
		RC_MOV_RECLAMACAO_FICHEIROS.setID(RC_MOV_RECLAMACAO_FICHEIROS.getID());
		return dao46.update(RC_MOV_RECLAMACAO_FICHEIROS);
	}

	/************************************* RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR */
	@POST
	@Path("/createRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR insertRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDORA(
			final RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR data) {
		return dao90.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR> getRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR() {
		return dao90.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDORbyidRECLAMACAO/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR> getRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDORbyidRECLAMACAO(
			@PathParam("id") Integer id) {
		return dao90.getbyid(id);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR/{id}")
	public void deleteRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR = new RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR();
		RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR.setID(id);
		dao90.delete(RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR);
	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR updateRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR(
			final RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR) {
		RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR.setID(RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR.getID());
		return dao90.update(RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR);
	}

	@PUT
	@Path("/updateORDEMRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR updateORDEMRC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR(
			final RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR) {

		entityManager.createNativeQuery("UPDATE RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR SET ORDEM = "
				+ RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR.getORDEM() + " WHERE ID = "
				+ RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR.getID() + "").executeUpdate();

		return RC_MOV_RECLAMACAO_FICHEIROS_FORNECEDOR;
	}

	/************************************* RC_MOV_RECLAMACAO_PLANOS_ACCOES */
	@POST
	@Path("/createRC_MOV_RECLAMACAO_PLANOS_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_PLANOS_ACCOES insertRC_MOV_RECLAMACAO_PLANOS_ACCOESA(
			final RC_MOV_RECLAMACAO_PLANOS_ACCOES data) {
		return dao47.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_PLANOS_ACCOES")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_PLANOS_ACCOES> getRC_MOV_RECLAMACAO_PLANOS_ACCOES() {
		return dao47.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_PLANOS_ACCOESbyid_reclamacao/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_PLANOS_ACCOES> getRC_MOV_RECLAMACAO_PLANOS_ACCOESbyid_reclamacao(
			@PathParam("id") Integer id) {
		return dao47.getbyid(id);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_PLANOS_ACCOESbyid/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_PLANOS_ACCOES> getRC_MOV_RECLAMACAO_PLANOS_ACCOESbyid(@PathParam("id") Integer id) {
		return dao47.getbyidplano(id);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO_PLANOS_ACCOES/{id}")
	public void deleteRC_MOV_RECLAMACAO_PLANOS_ACCOES(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO_PLANOS_ACCOES RC_MOV_RECLAMACAO_PLANOS_ACCOES = new RC_MOV_RECLAMACAO_PLANOS_ACCOES();
		RC_MOV_RECLAMACAO_PLANOS_ACCOES.setID(id);
		dao47.delete(RC_MOV_RECLAMACAO_PLANOS_ACCOES);
	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO_PLANOS_ACCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_PLANOS_ACCOES updateRC_MOV_RECLAMACAO_PLANOS_ACCOES(
			final RC_MOV_RECLAMACAO_PLANOS_ACCOES RC_MOV_RECLAMACAO_PLANOS_ACCOES) {
		RC_MOV_RECLAMACAO_PLANOS_ACCOES.setID(RC_MOV_RECLAMACAO_PLANOS_ACCOES.getID());
		return dao47.update(RC_MOV_RECLAMACAO_PLANOS_ACCOES);
	}

	/************************************* RC_MOV_RECLAMACAO_STOCK */
	@POST
	@Path("/createRC_MOV_RECLAMACAO_STOCK")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_STOCK insertRC_MOV_RECLAMACAO_STOCKA(final RC_MOV_RECLAMACAO_STOCK data) {
		return dao58.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_STOCK")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_STOCK> getRC_MOV_RECLAMACAO_STOCK() {
		return dao58.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_STOCKbyid_reclamacao/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_STOCK> getRC_MOV_RECLAMACAO_STOCKbyid_reclamacao(@PathParam("id") Integer id) {
		return dao58.getbyid(id);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_STOCKbyid_linha/{id}/{id_linha}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_STOCK> getRC_MOV_RECLAMACAO_STOCKbyid_linha(@PathParam("id") Integer id,
			@PathParam("id_linha") Integer id_linha) {
		return dao58.getbyidLINHA(id, id_linha);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO_STOCK/{id}")
	public void deleteRC_MOV_RECLAMACAO_STOCK(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO_STOCK RC_MOV_RECLAMACAO_STOCK = new RC_MOV_RECLAMACAO_STOCK();
		RC_MOV_RECLAMACAO_STOCK.setID(id);
		dao58.delete(RC_MOV_RECLAMACAO_STOCK);
	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO_STOCK")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_STOCK updateRC_MOV_RECLAMACAO_STOCK(
			final RC_MOV_RECLAMACAO_STOCK RC_MOV_RECLAMACAO_STOCK) {
		RC_MOV_RECLAMACAO_STOCK.setID(RC_MOV_RECLAMACAO_STOCK.getID());
		return dao58.update(RC_MOV_RECLAMACAO_STOCK);
	}

	/************************************* RC_MOV_RECLAMACAO_ENCOMENDAS */
	@POST
	@Path("/createRC_MOV_RECLAMACAO_ENCOMENDAS")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_ENCOMENDAS insertRC_MOV_RECLAMACAO_ENCOMENDASA(final RC_MOV_RECLAMACAO_ENCOMENDAS data) {
		return dao49.create(data);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_ENCOMENDAS")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_ENCOMENDAS> getRC_MOV_RECLAMACAO_ENCOMENDAS() {
		return dao49.getall();
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_ENCOMENDASbyid_reclamacao/{id}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_ENCOMENDAS> getRC_MOV_RECLAMACAO_ENCOMENDASbyid_reclamacao(
			@PathParam("id") Integer id) {
		return dao49.getbyid(id);
	}

	@GET
	@Path("/getRC_MOV_RECLAMACAO_ENCOMENDASbyid_linha/{id}/{id_linha}")
	@Produces("application/json")
	public List<RC_MOV_RECLAMACAO_STOCK> getRC_MOV_RECLAMACAO_ENCOMENDASbyid_linha(@PathParam("id") Integer id,
			@PathParam("id_linha") Integer id_linha) {
		return dao49.getbyidLINHA(id, id_linha);
	}

	@DELETE
	@Path("/deleteRC_MOV_RECLAMACAO_ENCOMENDAS/{id}")
	public void deleteRC_MOV_RECLAMACAO_ENCOMENDAS(@PathParam("id") Integer id) {
		RC_MOV_RECLAMACAO_ENCOMENDAS RC_MOV_RECLAMACAO_ENCOMENDAS = new RC_MOV_RECLAMACAO_ENCOMENDAS();
		RC_MOV_RECLAMACAO_ENCOMENDAS.setID(id);
		dao49.delete(RC_MOV_RECLAMACAO_ENCOMENDAS);
	}

	@PUT
	@Path("/updateRC_MOV_RECLAMACAO_ENCOMENDAS")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_MOV_RECLAMACAO_ENCOMENDAS updateRC_MOV_RECLAMACAO_ENCOMENDAS(
			final RC_MOV_RECLAMACAO_ENCOMENDAS RC_MOV_RECLAMACAO_ENCOMENDAS) {
		RC_MOV_RECLAMACAO_ENCOMENDAS.setID(RC_MOV_RECLAMACAO_ENCOMENDAS.getID());
		return dao49.update(RC_MOV_RECLAMACAO_ENCOMENDAS);
	}

	/************************************* RC_DIC_TIPO_OCORRENCIA */
	@POST
	@Path("/createRC_DIC_TIPO_OCORRENCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TIPO_OCORRENCIA insertRC_DIC_TIPO_OCORRENCIAA(final RC_DIC_TIPO_OCORRENCIA data) {
		return dao60.create(data);
	}

	@GET
	@Path("/getRC_DIC_TIPO_OCORRENCIA")
	@Produces("application/json")
	public List<RC_DIC_TIPO_OCORRENCIA> getRC_DIC_TIPO_OCORRENCIA() {
		return dao60.getall();
	}

	@GET
	@Path("/getRC_DIC_TIPO_OCORRENCIAbyid/{id}")
	@Produces("application/json")
	public List<RC_DIC_TIPO_OCORRENCIA> getRC_DIC_TIPO_OCORRENCIAbyid(@PathParam("id") Integer id) {
		return dao60.getbyid(id);
	}

	@DELETE
	@Path("/deleteRC_DIC_TIPO_OCORRENCIA/{id}")
	public void deleteRC_DIC_TIPO_OCORRENCIA(@PathParam("id") Integer id) {
		RC_DIC_TIPO_OCORRENCIA RC_DIC_TIPO_OCORRENCIA = new RC_DIC_TIPO_OCORRENCIA();
		RC_DIC_TIPO_OCORRENCIA.setID(id);
		dao60.delete(RC_DIC_TIPO_OCORRENCIA);
	}

	@PUT
	@Path("/updateRC_DIC_TIPO_OCORRENCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TIPO_OCORRENCIA updateRC_DIC_TIPO_OCORRENCIA(final RC_DIC_TIPO_OCORRENCIA RC_DIC_TIPO_OCORRENCIA) {
		RC_DIC_TIPO_OCORRENCIA.setID(RC_DIC_TIPO_OCORRENCIA.getID());
		return dao60.update(RC_DIC_TIPO_OCORRENCIA);
	}

	/************************************* RC_DIC_TIPO_NAO_DETECAO */
	@POST
	@Path("/createRC_DIC_TIPO_NAO_DETECAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TIPO_NAO_DETECAO insertRC_DIC_TIPO_NAO_DETECAOA(final RC_DIC_TIPO_NAO_DETECAO data) {
		return dao61.create(data);
	}

	@GET
	@Path("/getRC_DIC_TIPO_NAO_DETECAO")
	@Produces("application/json")
	public List<RC_DIC_TIPO_NAO_DETECAO> getRC_DIC_TIPO_NAO_DETECAO() {
		return dao61.getall();
	}

	@GET
	@Path("/getRC_DIC_TIPO_NAO_DETECAObyid/{id}")
	@Produces("application/json")
	public List<RC_DIC_TIPO_NAO_DETECAO> getRC_DIC_TIPO_NAO_DETECAObyid(@PathParam("id") Integer id) {
		return dao61.getbyid(id);
	}

	@DELETE
	@Path("/deleteRC_DIC_TIPO_NAO_DETECAO/{id}")
	public void deleteRC_DIC_TIPO_NAO_DETECAO(@PathParam("id") Integer id) {
		RC_DIC_TIPO_NAO_DETECAO RC_DIC_TIPO_NAO_DETECAO = new RC_DIC_TIPO_NAO_DETECAO();
		RC_DIC_TIPO_NAO_DETECAO.setID(id);
		dao61.delete(RC_DIC_TIPO_NAO_DETECAO);
	}

	@PUT
	@Path("/updateRC_DIC_TIPO_NAO_DETECAO")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TIPO_NAO_DETECAO updateRC_DIC_TIPO_NAO_DETECAO(
			final RC_DIC_TIPO_NAO_DETECAO RC_DIC_TIPO_NAO_DETECAO) {
		RC_DIC_TIPO_NAO_DETECAO.setID(RC_DIC_TIPO_NAO_DETECAO.getID());
		return dao61.update(RC_DIC_TIPO_NAO_DETECAO);
	}

	/************************************* GT_MOV_TAREFAS */
	@POST
	@Path("/createGT_MOV_TAREFAS")
	@Consumes("*/*")
	@Produces("application/json")
	public GT_MOV_TAREFAS insertGT_MOV_TAREFASA(final GT_MOV_TAREFAS data) {
		return dao59.create(data);
	}

	@POST
	@Path("/atualizaTAREFA")
	@Consumes("*/*")
	@Produces("application/json")
	public int atualizaTAREFA(final GT_MOV_TAREFAS data) {
		return entityManager.createNativeQuery("UPDATE GT_MOV_TAREFAS SET DATA_FIM = '" + data.getDATA_FIM()
				+ "' where id_MODULO = " + data.getID_MODULO() + " and sub_MODULO = '" + data.getSUB_MODULO()
				+ "' and id_campo = " + data.getID_CAMPO() + "").executeUpdate();
	}

	@GET
	@Path("/getGT_MOV_TAREFAS")
	@Produces("application/json")
	public List<GT_MOV_TAREFAS> getGT_MOV_TAREFAS() {
		return dao59.getall();
	}

	@GET
	@Path("/getGT_MOV_TAREFASbyid/{id}")
	@Produces("application/json")
	public List<GT_MOV_TAREFAS> getGT_MOV_TAREFASbyid(@PathParam("id") Integer id) {
		return dao59.getbyid(id);
	}

	@GET
	@Path("/getGT_MOV_TAREFASbyids/{id}/{modulo}/{submodulo}")
	@Produces("application/json")
	public List<GT_MOV_TAREFAS> getGT_MOV_TAREFASbyids(@PathParam("id") Integer id, @PathParam("modulo") Integer modulo,
			@PathParam("submodulo") String submodulo) {
		return dao59.getbyids(id, modulo, submodulo);
	}

	@POST
	@Path("/getGT_MOV_TAREFASbyid")
	@Consumes("*/*")
	@Produces("application/json")
	public List<GT_MOV_TAREFAS> getbyidFiltros(final List<HashMap<String, String>> data) throws ParseException {
		return dao59.getbyidFiltros(data);
	}

	@POST
	@Path("/getGT_MOV_TAREFASAllbyUser/{id}")
	@Produces("application/json")
	public List<GT_MOV_TAREFAS> getGT_MOV_TAREFASAllbyUser(@PathParam("id") Integer id,
			final List<HashMap<String, String>> data) {
		return dao59.getbyidUser(id, data);
	}

	@POST
	@Path("/getGT_MOV_TAREFASAtualizaAccao/{id}/{modulo}")
	@Produces("application/json")
	public void getGT_MOV_TAREFASAtualizaAccao(@PathParam("id") Integer id, @PathParam("modulo") Integer modulo,
			final String link) {
		String url = "EXEC [GT_MOV_TAREFAS_INSERT_UPDATE] " + id + "," + modulo + "";
		/*
		 * Query query = entityManager.
		 * createNativeQuery("declare @id int = 0; declare @id_insert int = 0;"
		 * + "IF EXISTS (SELECT * FROM GT_MOV_TAREFAS a WHERE ID_MODULO = " +
		 * modulo + " and  ID_CAMPO= " + id + ")    UPDATE a  " +
		 * "SET @id = a.ID_TAREFA, a.DATA_FIM = b.DATA_PREVISTA,a.DATA_INICIO = b.DATA_CRIA, a.DATA_ULT_MODIF = a.DATA_ULT_MODIF,a.UTZ_ULT_MODIF = a.UTZ_ULT_MODIF,a.OBSERVACOES=b.OBSERVACOES, "
		 * +
		 * "a.UTZ_ID = b.RESPONSAVEL, a.UTZ_TIPO = b.TIPO_RESPONSAVEL,a.ID_ACCAO = b.ID_ACCAO, a.SUB_MODULO =  (select c.TIPO_RECLAMACAO from RC_MOV_RECLAMACAO c where c.ID_RECLAMACAO = b.ID_RECLAMACAO) "
		 * +
		 * ", a.PRIORIDADE =  (select c.GRAU_IMPORTANCIA from RC_MOV_RECLAMACAO c where c.ID_RECLAMACAO = b.ID_RECLAMACAO) "
		 * +
		 * "FROM GT_MOV_TAREFAS a inner join RC_MOV_RECLAMACAO_PLANOS_ACCOES b on a.ID_CAMPO = b.ID "
		 * + "where a.ID_CAMPO = " + id + " and a.ID_MODULO = " + modulo + " " +
		 * "ELSE INSERT INTO GT_MOV_TAREFAS(ID_MODULO,ID_CAMPO,DATA_FIM,DATA_INICIO,DATA_CRIA,UTZ_CRIA,DATA_ULT_MODIF,UTZ_ULT_MODIF,ESTADO,INATIVO,UTZ_ID,UTZ_TIPO,ID_ACCAO,SUB_MODULO,PRIORIDADE,OBSERVACOES) "
		 * + "select '" + modulo +
		 * "',ID,DATA_PREVISTA,DATA_CRIA,DATA_CRIA,UTZ_CRIA,DATA_CRIA,UTZ_CRIA,'P',0,RESPONSAVEL,TIPO_RESPONSAVEL,ID_ACCAO, (select TIPO_RECLAMACAO from RC_MOV_RECLAMACAO b where b.ID_RECLAMACAO = a.ID_RECLAMACAO), "
		 * +
		 * "(select GRAU_IMPORTANCIA from RC_MOV_RECLAMACAO b where b.ID_RECLAMACAO = a.ID_RECLAMACAO),OBSERVACOES from RC_MOV_RECLAMACAO_PLANOS_ACCOES a  where ID = "
		 * + id + " IF @id != 0 " +
		 * "UPDATE RC_MOV_RECLAMACAO_PLANOS_ACCOES SET ID_TAREFA = @id WHERE ID = "
		 * + id + " " + "ELSE " +
		 * "UPDATE RC_MOV_RECLAMACAO_PLANOS_ACCOES SET @id_insert = ID_TAREFA = (SELECT ID_TAREFA FROM GT_MOV_TAREFAS WHERE ID_TAREFA = SCOPE_IDENTITY()) WHERE ID = "
		 * + id + " " + "IF @id_insert != 0  " +
		 * "INSERT INTO GT_LOGS ([DATA_CRIA] ,[UTZ_CRIA] ,[DESCRICAO] ,[ID_TAREFA]) VALUES (GETDATE(),(SELECT UTZ_CRIA FROM GT_MOV_TAREFAS WHERE ID_TAREFA = @id_insert),'Adicionada nova Tarefa',@id_insert)"
		 * );
		 */
		Query query = entityManager.createNativeQuery(url);
		query.executeUpdate();

		enviaEventoTarefa(id, link, false);

	}

	@POST
	@Path("/getAtualizaTarefaReclamacao/{id}/{modulo}")
	@Produces("application/json")
	public void getAtualizaTarefaReclamacao(@PathParam("id") Integer id, @PathParam("modulo") Integer modulo,
			final String link) {

		Query query = entityManager.createNativeQuery("select ID,ID_RECLAMACAO from RC_MOV_RECLAMACAO_PLANOS_ACCOES "
				+ "where ID_RECLAMACAO = " + id
				+ " and ID_TAREFA is null and NOT EXISTS(select * from GT_MOV_TAREFAS where ID_MODULO = 5 and SUB_MODULO != 'D' and ID_CAMPO = ID)");

		List<Object[]> dados = query.getResultList();

		for (Object[] content : dados) {
			String url = "EXEC [GT_MOV_TAREFAS_INSERT_UPDATE] " + content[0].toString() + "," + modulo + ",null";

			Query query_tarefa = entityManager.createNativeQuery(url);
			query_tarefa.executeUpdate();

			enviaEventoTarefa(Integer.parseInt(content[0].toString()), link, false);
		}

	}

	@POST
	@Path("/getAtualizaTarefaDerrogacoes/{id}/{modulo}")
	@Produces("application/json")
	public void getAtualizaTarefaDerrogacoes(@PathParam("id") Integer id, @PathParam("modulo") Integer modulo,
			final String link) {

		Query query = entityManager.createNativeQuery("select ID,ID_DERROGACAO from QUA_DERROGACOES_PLANOS_ACCOES "
				+ "where ID = " + id
				+ " and ID_TAREFA is null and NOT EXISTS(select * from GT_MOV_TAREFAS where ID_MODULO = 5 and SUB_MODULO = 'D' and ID_CAMPO = ID)");

		List<Object[]> dados = query.getResultList();

		for (Object[] content : dados) {
			String url = "EXEC [GT_MOV_TAREFAS_INSERT_UPDATE] " + content[0].toString() + "," + modulo + ",'D'";

			Query query_tarefa = entityManager.createNativeQuery(url);
			query_tarefa.executeUpdate();

			enviaEventoTarefa(Integer.parseInt(content[0].toString()), link, true);
		}

	}

	@POST
	@Path("/getAtualizaSubtarefas/{id}/{estado}/{utilizador}")
	@Produces("application/json")
	public void getAtualizaTarefaDerrogacoes(@PathParam("id") Integer id_pai, @PathParam("estado") String estado,
			@PathParam("utilizador") Integer utilizador) {

		String sqlq = "EXEC [GT_MOV_TAREFAS_UPDATE_SUBTAREFAS] " + id_pai + "," + utilizador + ",'" + estado + "'";

		Query query_tarefa = entityManager.createNativeQuery(sqlq);
		query_tarefa.executeUpdate();

	}

	@POST
	@Path("/getValidaSubtarefas/{id}")
	@Produces("application/json")
	public List<Object[]> getValidaSubtarefas(@PathParam("id") Integer id_pai) {

		String sqlq = "EXEC [GT_MOV_TAREFAS_VALIDA_SUBTAREFAS] " + id_pai;

		Query query_tarefa = entityManager.createNativeQuery(sqlq);
		List<Object[]> dados = query_tarefa.getResultList();

		return dados;

	}

	public void enviaEventoTarefa(Integer id, String link, Boolean derrogacao) {
		String observacao = "";
		String numero_reclamacao = "";
		String cliente = "";
		String data_reclamacao = "";
		String referencia = "";
		String numero_tarefa = "";
		String accao = "";
		String tipo_responsavel = "";
		String responsavel = "";
		String email_para = "";
		String data_prevista = "";
		Query query = null;

		if (derrogacao) {
			query = entityManager.createNativeQuery(
					"select a.ID_TAREFA,a.TIPO_RESPONSAVEL,a.RESPONSAVEL,OBSERVACOES ,c.DESCRICAO_PT,b.REFERENCIA,b.DESIGNACAO_REF, "
							+ "b.NOME_CLIENTE,b.ID_DERROGACAO,a.DATA_PREVISTA,b.DATA_INICIO "
							+ "from QUA_DERROGACOES_PLANOS_ACCOES a inner join QUA_DERROGACOES b on b.ID_DERROGACAO = a.ID_DERROGACAO "
							+ "inner join GT_DIC_TAREFAS c on a.ID_ACCAO = c.ID where a.ID = " + id);
		} else {
			query = entityManager.createNativeQuery(
					"select a.ID_TAREFA,a.TIPO_RESPONSAVEL,a.RESPONSAVEL,OBSERVACOES ,c.DESCRICAO_PT,b.REFERENCIA,b.DESIGNACAO_REF, "
							+ "b.NOME_CLIENTE,b.ID_RECLAMACAO,a.DATA_PREVISTA,b.DATA_RECLAMACAO "
							+ "from RC_MOV_RECLAMACAO_PLANOS_ACCOES a inner join RC_MOV_RECLAMACAO b on b.ID_RECLAMACAO = a.ID_RECLAMACAO "
							+ "inner join GT_DIC_TAREFAS c on a.ID_ACCAO = c.ID where a.ID = " + id);
		}

		List<Object[]> dados = query.getResultList();

		for (Object[] content : dados) {
			observacao = content[3].toString();
			numero_reclamacao = content[8].toString();
			cliente = content[7].toString();
			data_reclamacao = content[10].toString();
			referencia = content[5].toString() + " - " + content[6].toString();
			numero_tarefa = (content[0] == null) ? null : content[0].toString();
			accao = content[4].toString();
			tipo_responsavel = content[1].toString();
			responsavel = content[2].toString();
			data_prevista = content[9].toString();
		}

		Query query2 = null;
		if (tipo_responsavel.equals("u") || tipo_responsavel.equals("")) {
			query2 = entityManager.createNativeQuery(
					"select EMAIL,EMAIL as emm from GER_UTILIZADORES where ID_UTILIZADOR = " + responsavel);
		} else if (tipo_responsavel.equals("g")) {
			query2 = entityManager.createNativeQuery(
					"select distinct c.EMAIL,c.EMAIL as emm from GER_GRUPO a inner join GER_GRUPO_UTZ b on a.ID = b.ID_GRUPO "
							+ "inner join GER_UTILIZADORES c on b.ID_UTZ = c.ID_UTILIZADOR where a.id = "
							+ responsavel);
		}

		if (query2 != null) {
			List<Object[]> dados2 = query2.getResultList();

			Boolean primeiro = true;
			for (Object[] content2 : dados2) {
				if (!primeiro)
					email_para += ",";
				email_para += content2[0].toString();
				primeiro = false;
			}

			List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> n = new HashMap<String, String>();
			n.put("MODULO", "5");
			n.put("MOMENTO", "Ao Criar Tarefa");
			if (derrogacao) {
				n.put("PAGINA", "Derrogações");
			} else {
				n.put("PAGINA", "Reclamações Clientes");
			}
			n.put("ESTADO", "1");
			n.put("EMAIL_PARA", email_para);

			if (derrogacao) {
				n.put("DADOS",
						"{observacao::" + observacao + "\n/link::" + link + numero_tarefa + "\n/numero_derrogacao::"
								+ numero_reclamacao + "\n/cliente::" + cliente + "\n/data_derrogacao::"
								+ data_reclamacao + "" + "\n/referencia::" + referencia + "" + "\n/numero_tarefa::"
								+ numero_tarefa + "\n/accao::" + accao + "\n/data_prevista::" + data_prevista + "}");
			} else {
				n.put("DADOS",
						"{observacao::" + observacao + "\n/link::" + link + numero_tarefa + "\n/numero_reclamacao::"
								+ numero_reclamacao + "\n/cliente::" + cliente + "\n/data_reclamacao::"
								+ data_reclamacao + "" + "\n/referencia::" + referencia + "" + "\n/numero_tarefa::"
								+ numero_tarefa + "\n/accao::" + accao + "\n/data_prevista::" + data_prevista + "}");
			}

			data.add(n);

			verficaEventos(data);
		}
	}

	@DELETE
	@Path("/deleteGT_MOV_TAREFAS/{id}")
	public void deleteGT_MOV_TAREFAS(@PathParam("id") Integer id) {
		GT_MOV_TAREFAS GT_MOV_TAREFAS = new GT_MOV_TAREFAS();
		GT_MOV_TAREFAS.setID_TAREFA(id);
		dao59.delete(GT_MOV_TAREFAS);
	}

	@PUT
	@Path("/updateGT_MOV_TAREFAS")
	@Consumes("*/*")
	@Produces("application/json")
	public GT_MOV_TAREFAS updateGT_MOV_TAREFAS(final GT_MOV_TAREFAS GT_MOV_TAREFAS) {
		GT_MOV_TAREFAS.setID_TAREFA(GT_MOV_TAREFAS.getID_TAREFA());

		GT_MOV_TAREFAS GT_MOV_TAREFA = dao59.update(GT_MOV_TAREFAS);

		if (GT_MOV_TAREFAS
				.getID_MODULO() == 10 /*
										 * && GT_MOV_TAREFAS.getSUB_MODULO(
										 * ).equals("A")
										 */) {
			entityManager.createNativeQuery(
					"IF (select COUNT(*) from PR_AMOSTRAS_CAB a	inner join PR_AMOSTRAS_ACCOES b on a.ID_AMOSTRA = b.ID_AMOSTRA "
							+ "	inner join GT_MOV_TAREFAS c on b.ID_AMOSTRA_ACCAO = c.ID_CAMPO and ID_MODULO = 10 and SUB_MODULO = 'A' "
							+ "where b.ID_AMOSTRA in (select ID_AMOSTRA from PR_AMOSTRAS_ACCOES where ID_AMOSTRA_ACCAO = "
							+ GT_MOV_TAREFAS.getID_CAMPO() + ") ) = " + "(select COUNT(*) from PR_AMOSTRAS_CAB a "
							+ "inner join PR_AMOSTRAS_ACCOES b on a.ID_AMOSTRA = b.ID_AMOSTRA "
							+ "inner join GT_MOV_TAREFAS c on b.ID_AMOSTRA_ACCAO = c.ID_CAMPO and ID_MODULO = 10 and SUB_MODULO = 'A' "
							+ "where b.ID_AMOSTRA in (select ID_AMOSTRA from PR_AMOSTRAS_ACCOES where ID_AMOSTRA_ACCAO = "
							+ GT_MOV_TAREFAS.getID_CAMPO() + ") and c.ESTADO in ('C','A','R','N'))"
							+ "	BEGIN 	UPDATE PR_AMOSTRAS_CAB set ESTADO = 'C' where ID_AMOSTRA in (select ID_AMOSTRA from PR_AMOSTRAS_ACCOES where ID_AMOSTRA_ACCAO = "
							+ GT_MOV_TAREFAS.getID_CAMPO() + ") 	END")
					.executeUpdate();
		} else if (GT_MOV_TAREFAS.getID_MODULO() == 13) {
			if (GT_MOV_TAREFAS.getESTADO().equals("C")) {
				entityManager.createNativeQuery("UPDATE PA_MOV_LINHA set ESTADO = 'I' WHERE ID_PLANO_LINHA = "
						+ GT_MOV_TAREFAS.getID_CAMPO() + "").executeUpdate();
			} else if (GT_MOV_TAREFAS.getESTADO().equals("A")) {
				entityManager.createNativeQuery("UPDATE PA_MOV_LINHA set ESTADO = 'D' WHERE ID_PLANO_LINHA = "
						+ GT_MOV_TAREFAS.getID_CAMPO() + "").executeUpdate();
			} else if (GT_MOV_TAREFAS.getESTADO().equals("R")) {
				entityManager.createNativeQuery("UPDATE PA_MOV_LINHA set ESTADO = 'R' WHERE ID_PLANO_LINHA = "
						+ GT_MOV_TAREFAS.getID_CAMPO() + "").executeUpdate();
			}

			Integer ID_PLANO_CAB = null;
			Query query = entityManager.createNativeQuery(
					"select ID_PLANO_CAB,'' from PA_MOV_LINHA where ID_PLANO_LINHA = " + GT_MOV_TAREFAS.getID_CAMPO());

			List<Object[]> dados = query.getResultList();

			for (Object[] content : dados) {
				ID_PLANO_CAB = Integer.parseInt(content[0].toString());
			}

			getPA_MOV_LINHAAtualizaESTADOS(ID_PLANO_CAB);

			/*
			 * entityManager.createNativeQuery(
			 * "IF (select COUNT(*) from PA_MOV_CAB a	inner join PA_MOV_LINHA b on a.ID_PLANO_CAB = b.ID_PLANO_CAB "
			 * +
			 * "	inner join GT_MOV_TAREFAS c on b.ID_PLANO_LINHA = c.ID_CAMPO and ID_MODULO = 13 and SUB_MODULO = 'PA' "
			 * +
			 * "where b.ID_PLANO_CAB in (select ID_PLANO_CAB from PA_MOV_LINHA where ID_PLANO_LINHA = "
			 * + GT_MOV_TAREFAS.getID_CAMPO() + ") ) = " +
			 * "(select COUNT(*) from PA_MOV_CAB a " +
			 * "inner join PA_MOV_LINHA b on a.ID_PLANO_CAB = b.ID_PLANO_CAB " +
			 * "inner join GT_MOV_TAREFAS c on b.ID_PLANO_LINHA = c.ID_CAMPO and ID_MODULO = 13 and SUB_MODULO = 'PA' "
			 * +
			 * "where b.ID_PLANO_CAB in (select ID_PLANO_CAB from PA_MOV_LINHA where ID_PLANO_LINHA = "
			 * + GT_MOV_TAREFAS.getID_CAMPO() +
			 * ") and c.ESTADO in ('C','A','R','N'))" +
			 * "	BEGIN 	UPDATE PA_MOV_CAB set ESTADO = 'C' where ID_PLANO_CAB in (select ID_PLANO_CAB from PA_MOV_LINHA where ID_PLANO_LINHA = "
			 * + GT_MOV_TAREFAS.getID_CAMPO() + ") 	END") .executeUpdate();
			 */

		}

		return GT_MOV_TAREFA;
	}

	/************************************* GT_MOV_FICHEIROS */
	@POST
	@Path("/createGT_MOV_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public GT_MOV_FICHEIROS insertGT_MOV_FICHEIROSA(final GT_MOV_FICHEIROS data) {
		return dao99.create(data);
	}

	@GET
	@Path("/getGT_MOV_FICHEIROS")
	@Produces("application/json")
	public List<GT_MOV_FICHEIROS> getGT_MOV_FICHEIROS() {
		return dao99.getall();
	}

	@GET
	@Path("/getGT_MOV_FICHEIROSbyidTAREFA/{id}")
	@Produces("application/json")
	public List<GT_MOV_FICHEIROS> getGT_MOV_FICHEIROSbyidTAREFA(@PathParam("id") Integer id) {
		return dao99.getbyid(id);
	}

	@DELETE
	@Path("/deleteGT_MOV_FICHEIROS/{id}")
	public void deleteGT_MOV_FICHEIROS(@PathParam("id") Integer id) {
		GT_MOV_FICHEIROS GT_MOV_FICHEIROS = new GT_MOV_FICHEIROS();
		GT_MOV_FICHEIROS.setID(id);
		dao99.delete(GT_MOV_FICHEIROS);
	}

	@PUT
	@Path("/updateGT_MOV_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public GT_MOV_FICHEIROS updateGT_MOV_FICHEIROS(final GT_MOV_FICHEIROS GT_MOV_FICHEIROS) {
		GT_MOV_FICHEIROS.setID(GT_MOV_FICHEIROS.getID());
		return dao99.update(GT_MOV_FICHEIROS);
	}

	/************************************* RC_DIC_TEMPO_RESPOSTA */
	@POST
	@Path("/createRC_DIC_TEMPO_RESPOSTA")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TEMPO_RESPOSTA insertRC_DIC_TEMPO_RESPOSTAA(final RC_DIC_TEMPO_RESPOSTA data) {
		return dao50.create(data);
	}

	@GET
	@Path("/getRC_DIC_TEMPO_RESPOSTA")
	@Produces("application/json")
	public List<RC_DIC_TEMPO_RESPOSTA> getRC_DIC_TEMPO_RESPOSTA() {
		return dao50.getall();
	}

	@DELETE
	@Path("/deleteRC_DIC_TEMPO_RESPOSTA/{id}")
	public void deleteRC_DIC_TEMPO_RESPOSTA(@PathParam("id") Integer id) {
		RC_DIC_TEMPO_RESPOSTA RC_DIC_TEMPO_RESPOSTA = new RC_DIC_TEMPO_RESPOSTA();
		RC_DIC_TEMPO_RESPOSTA.setID(id);
		dao50.delete(RC_DIC_TEMPO_RESPOSTA);
	}

	@PUT
	@Path("/updateRC_DIC_TEMPO_RESPOSTA")
	@Consumes("*/*")
	@Produces("application/json")
	public RC_DIC_TEMPO_RESPOSTA updateRC_DIC_TEMPO_RESPOSTA(final RC_DIC_TEMPO_RESPOSTA RC_DIC_TEMPO_RESPOSTA) {
		RC_DIC_TEMPO_RESPOSTA.setID(RC_DIC_TEMPO_RESPOSTA.getID());
		return dao50.update(RC_DIC_TEMPO_RESPOSTA);
	}

	/************************************* GER_GRUPO_UTZ */
	@POST
	@Path("/createGER_GRUPO_UTZ")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_GRUPO_UTZ insertGER_GRUPO_UTZA(final GER_GRUPO_UTZ data) {
		return dao51.create(data);
	}

	@GET
	@Path("/getGER_GRUPO_UTZ")
	@Produces("application/json")
	public List<GER_GRUPO_UTZ> getGER_GRUPO_UTZ() {
		return dao51.getall();
	}

	@GET
	@Path("/getGER_GRUPO_UTZbyid/{id}")
	@Produces("application/json")
	public List<GER_GRUPO_UTZ> getGER_GRUPO_UTZbyid(@PathParam("id") Integer id) {
		return dao51.getbyid(id);
	}

	@GET
	@Path("/getGER_GRUPO_UTZbyUtilizadores/{id}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_GRUPO_UTZbyUtilizadores(@PathParam("id") Integer id) {
		return dao51.getUtilizadores(id);
	}

	@GET
	@Path("/getGER_GRUPO_UTZbyidgrupo/{id}")
	@Produces("application/json")
	public List<GER_GRUPO_UTZ> getGER_GRUPO_UTZbyidgrupo(@PathParam("id") Integer id) {
		return dao51.getbyidgrupo(id);
	}

	@DELETE
	@Path("/deleteGER_GRUPO_UTZ/{id}")
	public void deleteGER_GRUPO_UTZ(@PathParam("id") Integer id) {
		GER_GRUPO_UTZ GER_GRUPO_UTZ = new GER_GRUPO_UTZ();
		GER_GRUPO_UTZ.setID(id);
		dao51.delete(GER_GRUPO_UTZ);
	}

	@PUT
	@Path("/updateGER_GRUPO_UTZ")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_GRUPO_UTZ updateGER_GRUPO_UTZ(final GER_GRUPO_UTZ GER_GRUPO_UTZ) {
		GER_GRUPO_UTZ.setID(GER_GRUPO_UTZ.getID());
		return dao51.update(GER_GRUPO_UTZ);
	}

	/************************************* GER_GRUPO */
	@POST
	@Path("/createGER_GRUPO")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_GRUPO insertGER_GRUPOA(final GER_GRUPO data) {
		return dao52.create(data);
	}

	@GET
	@Path("/getGER_GRUPO")
	@Produces("application/json")
	public List<GER_GRUPO> getGER_GRUPO() {
		return dao52.getall();
	}

	@GET
	@Path("/getGER_GRUPObyid/{id}")
	@Produces("application/json")
	public List<GER_GRUPO> getGER_GRUPObyid(@PathParam("id") Integer id) {
		return dao52.getbyid(id);
	}

	@GET
	@Path("/getGER_GRUPObyidUser/{id}")
	@Produces("application/json")
	public List<GER_GRUPO> getGER_GRUPObyidusres(@PathParam("id") Integer id) {
		return dao52.getbyidbyuser(id);
	}

	@DELETE
	@Path("/deleteGER_GRUPO/{id}")
	public void deleteGER_GRUPO(@PathParam("id") Integer id) {
		GER_GRUPO GER_GRUPO = new GER_GRUPO();
		GER_GRUPO.setID(id);
		dao52.delete(GER_GRUPO);
	}

	@PUT
	@Path("/updateGER_GRUPO")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_GRUPO updateGER_GRUPO(final GER_GRUPO GER_GRUPO) {
		GER_GRUPO.setID(GER_GRUPO.getID());
		return dao52.update(GER_GRUPO);
	}

	/************************************* GER_SECCAO_CHEFES */
	@POST
	@Path("/createGER_SECCAO_CHEFES")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_SECCAO_CHEFES insertGER_SECCAO_CHEFESA(final GER_SECCAO_CHEFES data) {
		return dao53.create(data);
	}

	@GET
	@Path("/getGER_SECCAO_CHEFES")
	@Produces("application/json")
	public List<GER_SECCAO_CHEFES> getGER_SECCAO_CHEFES() {
		return dao53.getall();
	}

	@GET
	@Path("/getGER_SECCAO_CHEFESbyid/{id}")
	@Produces("application/json")
	public List<GER_SECCAO_CHEFES> getGER_SECCAO_CHEFESbyid(@PathParam("id") Integer id) {
		return dao53.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_SECCAO_CHEFES/{id}")
	public void deleteGER_SECCAO_CHEFES(@PathParam("id") Integer id) {
		GER_SECCAO_CHEFES GER_SECCAO_CHEFES = new GER_SECCAO_CHEFES();
		GER_SECCAO_CHEFES.setID(id);
		dao53.delete(GER_SECCAO_CHEFES);
	}

	@GET
	@Path("/getGER_SECCAO_CHEFESbyUtilizadores/{id}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_SECCAO_CHEFESbyUtilizadores(@PathParam("id") Integer id) {
		return dao53.getUtilizadores(id);
	}

	@GET
	@Path("/getGER_SECCAO_CHEFESbyidgrupo/{id}")
	@Produces("application/json")
	public List<GER_SECCAO_CHEFES> getGER_SECCAO_CHEFESbyidgrupo(@PathParam("id") Integer id) {
		return dao53.getbyidgrupo(id);
	}

	@PUT
	@Path("/updateGER_SECCAO_CHEFES")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_SECCAO_CHEFES updateGER_SECCAO_CHEFES(final GER_SECCAO_CHEFES GER_SECCAO_CHEFES) {
		GER_SECCAO_CHEFES.setID(GER_SECCAO_CHEFES.getID());
		return dao53.update(GER_SECCAO_CHEFES);
	}

	/************************************* GER_SECCAO_UTZ */
	@POST
	@Path("/createGER_SECCAO_UTZ")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_SECCAO_UTZ insertGER_SECCAO_UTZA(final GER_SECCAO_UTZ data) {
		return dao54.create(data);
	}

	@GET
	@Path("/getGER_SECCAO_UTZ")
	@Produces("application/json")
	public List<GER_SECCAO_UTZ> getGER_SECCAO_UTZ() {
		return dao54.getall();
	}

	@GET
	@Path("/getGER_SECCAO_UTZbyUtilizadores/{id}")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getGER_SECCAO_UTZbyUtilizadores(@PathParam("id") Integer id) {
		return dao54.getUtilizadores(id);
	}

	@GET
	@Path("/getGER_SECCAO_UTZbyidgrupo/{id}")
	@Produces("application/json")
	public List<GER_SECCAO_UTZ> getGER_SECCAO_UTZbyidgrupo(@PathParam("id") Integer id) {
		return dao54.getbyidgrupo(id);
	}

	@GET
	@Path("/getGER_SECCAO_UTZbyid/{id}")
	@Produces("application/json")
	public List<GER_SECCAO_UTZ> getGER_SECCAO_UTZbyid(@PathParam("id") Integer id) {
		return dao54.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_SECCAO_UTZ/{id}")
	public void deleteGER_SECCAO_UTZ(@PathParam("id") Integer id) {
		GER_SECCAO_UTZ GER_SECCAO_UTZ = new GER_SECCAO_UTZ();
		GER_SECCAO_UTZ.setID(id);
		dao54.delete(GER_SECCAO_UTZ);
	}

	@PUT
	@Path("/updateGER_SECCAO_UTZ")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_SECCAO_UTZ updateGER_SECCAO_UTZ(final GER_SECCAO_UTZ GER_SECCAO_UTZ) {
		GER_SECCAO_UTZ.setID(GER_SECCAO_UTZ.getID());
		return dao54.update(GER_SECCAO_UTZ);
	}

	/************************************* GT_LOGS */
	@POST
	@Path("/createGT_LOGS")
	@Consumes("*/*")
	@Produces("application/json")
	public GT_LOGS insertGT_LOGSA(final GT_LOGS data) {
		return dao48.create(data);
	}

	@GET
	@Path("/getGT_LOGS")
	@Produces("application/json")
	public List<GT_LOGS> getGT_LOGS() {
		return dao48.getall();
	}

	@GET
	@Path("/getGT_LOGSbyid/{id}")
	@Produces("application/json")
	public List<GT_LOGS> getGT_LOGSbyid(@PathParam("id") Integer id) {
		return dao48.getbyid(id);
	}

	@DELETE
	@Path("/deleteGT_LOGS/{id}")
	public void deleteGT_LOGS(@PathParam("id") Integer id) {
		GT_LOGS GT_LOGS = new GT_LOGS();
		GT_LOGS.setID(id);
		dao48.delete(GT_LOGS);
	}

	@PUT
	@Path("/updateGT_LOGS")
	@Consumes("*/*")
	@Produces("application/json")
	public GT_LOGS updateGT_LOGS(final GT_LOGS GT_LOGS) {
		GT_LOGS.setID(GT_LOGS.getID());
		return dao48.update(GT_LOGS);
	}

	/************************************* GER_SECCAO */
	@POST
	@Path("/createGER_SECCAO")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_SECCAO insertGER_SECCAO(final GER_SECCAO data) {
		return dao55.create(data);
	}

	@GET
	@Path("/getGER_SECCAO")
	@Produces("application/json")
	public List<GER_SECCAO> getGER_SECCAO() {
		return dao55.getall();
	}

	@GET
	@Path("/getGER_SECCAO2")
	@Produces("application/json")
	public List<GER_SECCAO> getGER_SECCAO2() {
		return dao55.getall2();
	}

	@GET
	@Path("/getGER_SECCAObyid/{id}")
	@Produces("application/json")
	public List<GER_SECCAO> getGER_SECCAObyid(@PathParam("id") Integer id) {
		return dao55.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_SECCAO/{id}")
	public void deleteGER_SECCAO(@PathParam("id") Integer id) {
		GER_SECCAO GER_SECCAO = new GER_SECCAO();
		GER_SECCAO.setID(id);
		dao55.delete(GER_SECCAO);
	}

	@PUT
	@Path("/updateGER_SECCAO")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_SECCAO updateGER_SECCAO(final GER_SECCAO GER_SECCAO) {
		GER_SECCAO.setID(GER_SECCAO.getID());
		return dao55.update(GER_SECCAO);
	}

	/************************************* GER_DEPARTAMENTO */
	@POST
	@Path("/createGER_DEPARTAMENTO")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DEPARTAMENTO insertGER_DEPARTAMENTO(final GER_DEPARTAMENTO data) {
		return dao56.create(data);
	}

	@GET
	@Path("/getGER_DEPARTAMENTO")
	@Produces("application/json")
	public List<GER_DEPARTAMENTO> getGER_DEPARTAMENTO() {
		return dao56.getall();
	}

	@GET
	@Path("/getGER_DEPARTAMENTO2")
	@Produces("application/json")
	public List<GER_DEPARTAMENTO> getGER_DEPARTAMENTO2() {
		return dao56.getall2();
	}

	@GET
	@Path("/getGER_DEPARTAMENTObyid_reclamacao/{id}")
	@Produces("application/json")
	public List<GER_DEPARTAMENTO> getGER_DEPARTAMENTObyid(@PathParam("id") Integer id) {
		return dao56.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_DEPARTAMENTO/{id}")
	public void deleteGER_DEPARTAMENTO(@PathParam("id") Integer id) {
		GER_DEPARTAMENTO GER_DEPARTAMENTO = new GER_DEPARTAMENTO();
		GER_DEPARTAMENTO.setID(id);
		dao56.delete(GER_DEPARTAMENTO);
	}

	@PUT
	@Path("/updateGER_DEPARTAMENTO")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_DEPARTAMENTO updateGER_DEPARTAMENTO(final GER_DEPARTAMENTO GER_DEPARTAMENTO) {
		GER_DEPARTAMENTO.setID(GER_DEPARTAMENTO.getID());
		return dao56.update(GER_DEPARTAMENTO);
	}

	/************************************* GER_LOCAIS */
	@POST
	@Path("/createGER_LOCAIS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_LOCAIS insertGER_LOCAIS(final GER_LOCAIS data) {
		return dao68.create(data);
	}

	@GET
	@Path("/getGER_LOCAIS")
	@Produces("application/json")
	public List<GER_LOCAIS> getGER_LOCAIS() {
		return dao68.getall();
	}

	@GET
	@Path("/getGER_LOCAIS2")
	@Produces("application/json")
	public List<GER_LOCAIS> getGER_LOCAIS2() {
		return dao68.getall2();
	}

	@GET
	@Path("/getGER_LOCAISbyid_reclamacao/{id}")
	@Produces("application/json")
	public List<GER_LOCAIS> getGER_LOCAISbyid(@PathParam("id") Integer id) {
		return dao68.getbyid(id);
	}

	@DELETE
	@Path("/deleteGER_LOCAIS/{id}")
	public void deleteGER_LOCAIS(@PathParam("id") Integer id) {
		GER_LOCAIS GER_LOCAIS = new GER_LOCAIS();
		GER_LOCAIS.setID(id);
		dao68.delete(GER_LOCAIS);
	}

	@PUT
	@Path("/updateGER_LOCAIS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_LOCAIS updateGER_LOCAIS(final GER_LOCAIS GER_LOCAIS) {
		GER_LOCAIS.setID(GER_LOCAIS.getID());
		return dao68.update(GER_LOCAIS);
	}

	/************************************* GER_PARAMETROS */

	@GET
	@Path("/getGER_PARAMETROS")
	@Produces("application/json")
	public List<GER_PARAMETROS> getGER_PARAMETROS() {
		return dao30.getall();
	}

	@PUT
	@Path("/updateGER_PARAMETROS")
	@Consumes("*/*")
	@Produces("application/json")
	public GER_PARAMETROS updateGER_PARAMETROS(final GER_PARAMETROS GER_PARAMETROS) {
		GER_PARAMETROS.setID(GER_PARAMETROS.getID());
		return dao30.update(GER_PARAMETROS);
	}

	@POST
	@Consumes("*/*")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/uploadFilePDF/{nome}/{formato}")
	public Response uploadFilePDF(@FormDataParam("file") InputStream uploadedInputStream,
			@PathParam("nome") String nome, @PathParam("formato") String formato) {

		String uploadedFileLocation = "C:/sgiid/relatorios/" + nome + '.' + formato;
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		// sendemail(email, null, "C:/sgiid/relatorios/" + nome + '.' +
		// formato);
		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.ok().entity(output).build();

	}

	@POST
	@Consumes("*/*")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/upload/{nome}/{formato}")
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream, @PathParam("nome") String nome,
			@PathParam("formato") String formato) {

		String uploadedFileLocation = "C:/xampp/htdocs/dev/sgiid/" + nome + '.' + formato;
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.ok().entity(output).build();

	}

	@POST
	@Consumes("*/*")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/uploadRAKS")
	public Response uploadFileRAKS(@FormDataParam("file") InputStream uploadedInputStream) {

		Response output = SomeQuarterlyJob.importar(uploadedInputStream);

		return output;

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			out = null;
			System.gc();
			uploadedInputStream.close();
		} catch (IOException e) {
			String[] keyValuePairs = { "texto_erro ::" + e.getMessage() + " " + uploadedFileLocation + "", };
			verficaEventos(keyValuePairs, "Erros ao Criar Etiquetas", "");
			e.printStackTrace();
		}

	}

	public void verficaEventos(String[] keyValuePairs, String momento, String fgilepath) {

		List<String> x = new ArrayList<String>();

		Query query3 = entityManager.createQuery("Select a from GER_EVENTOS_CONF a where MODULO = 1 and MOMENTO = '"
				+ momento + "' " + "and PAGINA = 'INTERNO' and ESTADO  != 0");
		List<GER_EVENTOS_CONF> dados = query3.getResultList();

		for (GER_EVENTOS_CONF borderTypes : dados) {

			// System.out.println(borderTypes.getEMAIL_ASSUNTO());
			EMAIL email = new EMAIL();
			// email.setASSUNTO(borderTypes.getEMAIL_ASSUNTO());
			email.setDE("alertas.it.doureca@gmail.com");

			email.setPARA(borderTypes.getEMAIL_PARA());
			String mensagem = borderTypes.getEMAIL_MENSAGEM();
			String assunto = borderTypes.getEMAIL_ASSUNTO();

			for (String pair : keyValuePairs) {
				String[] entry = pair.split("::");
				mensagem = mensagem.replace("{" + entry[0].trim() + "}", entry[1].trim());
				assunto = assunto.replace("{" + entry[0].trim() + "}", (entry.length > 1) ? entry[1].trim() : "");
			}
			email.setASSUNTO(assunto);
			email.setMENSAGEM(mensagem);
			String[] keyValuePairs3 = {};

			sendemail(email, keyValuePairs3, fgilepath);

		}
	}

	@POST
	@Path("/getGER_PARAMETROSATUALIZADATA/{hora}/{minutos}/{diasemana}/{tipo}/{id}")
	@Produces("application/json")
	public int getGER_PARAMETROSATUALIZADATA(@PathParam("hora") Integer hora, @PathParam("minutos") Integer minutos,
			@PathParam("diasemana") Integer diasemana, @PathParam("tipo") Integer tipo, final String data_inicio,
			@PathParam("id") Integer id) {
		Timestamp data = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = null;
		try {
			date = df.parse(data_inicio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// se for diário
		if (tipo == 1) {
			data = new Timestamp(getTomorrowDAy(hora, minutos, diasemana).getTime());
		} else if (tipo == 2) {
			// se for semanal
			data = new Timestamp(getTomorrowSemanal(hora, minutos, diasemana, date).getTime());
		} else if (tipo == 3) {
			// se for mensal
			data = new Timestamp(getTomorrowMonth(hora, minutos, date).getTime());
		} else if (tipo == 4) {
			// se for anual
			data = new Timestamp(getTomorrowYear(hora, minutos, date).getTime());
		}

		System.out.println(tipo + "" + data);

		int exupdate = entityManager.createNativeQuery(
				"UPDATE GER_ATUALIZACAO_SILVER_BI_TABELAS SET DATA_ATUALIZACAO = '" + data + "' WHERE ID =" + id + "")
				.executeUpdate();
		return exupdate;
	}

	private static java.util.Date getTomorrowDAy(Integer fFOUR_AM, Integer fZERO_MINUTES, Integer DIA_SEMANA) {
		Calendar tomorrow = new GregorianCalendar();
		tomorrow.set(Calendar.HOUR_OF_DAY, fFOUR_AM);
		tomorrow.set(Calendar.MINUTE, fZERO_MINUTES);
		tomorrow.set(Calendar.SECOND, 0);

		tomorrow.add(Calendar.DATE, 1);

		Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR), tomorrow.get(Calendar.MONTH),
				tomorrow.get(Calendar.DATE), fFOUR_AM, fZERO_MINUTES);
		return result.getTime();
	}

	private static java.util.Date getTomorrowSemanal(Integer fFOUR_AM, Integer fZERO_MINUTES, Integer DIA_SEMANA,
			java.util.Date data) {
		Calendar tomorrow = new GregorianCalendar();
		tomorrow.setTime(data);
		tomorrow.set(Calendar.HOUR_OF_DAY, fFOUR_AM);
		tomorrow.set(Calendar.MINUTE, fZERO_MINUTES);
		tomorrow.set(Calendar.SECOND, 0);

		while (tomorrow.get(Calendar.DAY_OF_WEEK) != DIA_SEMANA
				|| tomorrow.getTime().getTime() < new java.util.Date().getTime()) {
			tomorrow.add(Calendar.DATE, 1);
		}

		Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR), tomorrow.get(Calendar.MONTH),
				tomorrow.get(Calendar.DATE), fFOUR_AM, fZERO_MINUTES);
		return result.getTime();
	}

	private static java.util.Date getTomorrowMonth(Integer fFOUR_AM, Integer fZERO_MINUTES, java.util.Date data) {
		Calendar tomorrow = new GregorianCalendar();
		tomorrow.setTime(data);
		tomorrow.set(Calendar.HOUR_OF_DAY, fFOUR_AM);
		tomorrow.set(Calendar.MINUTE, fZERO_MINUTES);
		tomorrow.set(Calendar.SECOND, 0);

		while (tomorrow.getTime().getTime() < new java.util.Date().getTime()) {
			tomorrow.add(Calendar.MONTH, 1);
		}

		Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR), tomorrow.get(Calendar.MONTH),
				tomorrow.get(Calendar.DATE), fFOUR_AM, fZERO_MINUTES);
		return result.getTime();
	}

	private static java.util.Date getTomorrowYear(Integer fFOUR_AM, Integer fZERO_MINUTES, java.util.Date data) {
		Calendar tomorrow = new GregorianCalendar();
		tomorrow.setTime(data);
		tomorrow.set(Calendar.HOUR_OF_DAY, fFOUR_AM);
		tomorrow.set(Calendar.MINUTE, fZERO_MINUTES);
		tomorrow.set(Calendar.SECOND, 0);

		while (tomorrow.getTime().getTime() < new java.util.Date().getTime()) {
			tomorrow.add(Calendar.YEAR, 1);
		}

		Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR), tomorrow.get(Calendar.MONTH),
				tomorrow.get(Calendar.DATE), fFOUR_AM, fZERO_MINUTES);
		return result.getTime();
	}

	/* FICHEIRO ************************************/
	@GET
	@Path("/get/{format}/{filename}/{id}/{relatorio}/{subPASTA}")
	// @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response getFile(@PathParam("format") String format, @PathParam("filename") String Name,
			@PathParam("id") Integer ID, @PathParam("relatorio") String relatorio,
			@PathParam("subPASTA") String subPASTA) {
		String fileName = null;
		String filepath = getFILEPATH();
		String subPASTA_path = subPASTA + "/";

		if (subPASTA.equals("nenhuma"))
			subPASTA_path = "";

		ReportGenerator report = new ReportGenerator();
		try {
			fileName = report.relatorio(format, Name, ID, relatorio, getURL(), filepath, subPASTA_path, null, null,
					null, null);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fileName != null) {
			File file = new File("/" + filepath + "/relatorios/" + fileName);
			ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition", "attachment; filename=report." + format + "");
			return response.build();
		} else {
			return null;
		}

	}

	@POST
	@Path("/getFILEPOST/{format}/{filename}/{id}/{relatorio}/{subPASTA}")
	// @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response getFile(@PathParam("format") String format, @PathParam("filename") String Name,
			@PathParam("id") Integer ID, @PathParam("relatorio") String relatorio,
			@PathParam("subPASTA") String subPASTA, final List<HashMap<String, String>> dados) {
		String fileName = null;
		String filepath = getFILEPATH();
		String subPASTA_path = subPASTA + "/";

		if (subPASTA.equals("nenhuma"))
			subPASTA_path = "";

		ReportGenerator report = new ReportGenerator();
		try {
			fileName = report.relatorio(format, Name, ID, relatorio, getURL(), filepath, subPASTA_path, null, null,
					null, dados);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fileName != null) {
			File file = new File("/" + filepath + "/relatorios/" + fileName);
			ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition", "attachment; filename=report." + format + "");
			return response.build();
		} else {
			return null;
		}

	}

	/* FICHEIRO RecursosHumanos ************************************/
	@POST
	@Consumes("*/*")
	@Path("/getFileJSON/{format}/{filename}/{relatorio}/{subPASTA}")
	// @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response getFileJSON(@PathParam("format") String format, @PathParam("filename") String Name,
			@PathParam("relatorio") String relatorio, final List<HashMap<String, String>> datas,
			@PathParam("subPASTA") String subPASTA) {
		HashMap<String, String> firstMap = datas.get(0);

		if (subPASTA == "vazia") {
			subPASTA = "";
		} else {
			subPASTA = "/" + subPASTA;
		}

		String data1 = firstMap.get("DATA");
		String data2 = firstMap.get("DATA2");
		String Ativo = firstMap.get("ATIVO");
		String OPERARIO = firstMap.get("OPERARIO");

		String SECTOR = firstMap.get("SECTOR");

		if (OPERARIO != null && OPERARIO.isEmpty())
			OPERARIO = null;
		/*
		 * if (SECTOR != null && SECTOR.isEmpty()) SECTOR = null;
		 */

		String fileName = null;
		String filepath = getFILEPATH();
		ReportGenerator report = new ReportGenerator();
		try {
			fileName = report.relatorio2(format, Name, relatorio, "", filepath, data1, data2, OPERARIO, Ativo, SECTOR,
					firstMap.get("dados"), subPASTA);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fileName != null) {
			File file = new File("/" + filepath + "/relatorios/" + fileName);
			ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition", "attachment; filename=report." + format + "");
			return response.build();
		} else {
			return null;
		}

	}

	@GET
	@Consumes("*/*")
	@Path("/downloadFile/{format}/{filename}")
	// @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response downloadFile(@PathParam("format") String format, @PathParam("filename") String Name) {

		String fileName = Name;

		String filepath = "C:/xampp/htdocs/dev/sgiid/";

		if (fileName != null) {
			File file = new File(filepath + fileName);
			ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition", "attachment; filename=" + format + "");
			return response.build();
		} else {
			return null;
		}

	}

	@GET
	@Consumes("*/*")
	@Path("/downloadFileMSG/{filename}")
	public Response downloadFileMSG(@PathParam("filename") String Name) throws IOException {
		File temp = File.createTempFile("tempfile", ".txt");
		String texto = "<div class=\"MsoNormal\"> ";
		try {

			String fileName = Name;

			String filepath = "C:/xampp/htdocs/dev/sgiid/";

			MsgParser msgp = new MsgParser();
			Message msg = msgp.parseMsg(filepath + fileName);
			String from_email = msg.getFromEmail();
			String from_name = msg.getFromName();
			String subject = msg.getSubject();
			String body = msg.getConvertedBodyHTML();
			String to_list = msg.getDisplayTo();
			String cc_list = msg.getDisplayCc();
			String bcc_list = msg.getDisplayBcc();
			List list = msg.getAttachments();
			texto += "<b>Anexos</b> - " + list.size() + "<br>";
			Iterator it_list = list.iterator();
			Attachment attachemetn = null;
			while (it_list.hasNext()) {
				attachemetn = (Attachment) it_list.next();
				texto += "<i>" + attachemetn + "</i><br>";
			}

			List<Attachment> atts = msg.getAttachments();
			for (Attachment att : atts) {
				if (att instanceof FileAttachment) {
					FileAttachment file = (FileAttachment) att;

					// you get the actual attachment with

					String encodedString = new String(Base64.getEncoder().encodeToString((file.getData())));

					body = body.replaceAll("\\\"cid:" + file.getFilename() + ".*?\\\"",
							"\"data:image/" + getFileExtension(file) + ";base64," + encodedString + "\"");
				}
			}

			texto += "------------------------------ <br>";
			texto += "<b>De</b>:  " + from_name + " <" + from_email + "><br>";
			texto += "<b>Para</b>: " + to_list + "<br>";
			texto += "<b>Cc</b>: " + cc_list + "<br>";
			texto += "<b>Bcc</b>: " + bcc_list + "<br>";
			texto += "<b>Assunto</b>: " + subject + "<br><br><b>Mensagem</b>:<br></div>";
			texto += body + "<br>";

		} catch (Exception e) {
			System.out.println(e);
		}

		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(temp), StandardCharsets.UTF_8));
		bw.write(texto);
		bw.close();

		ResponseBuilder response = Response.ok((Object) temp);
		response.header("Content-Disposition", "attachment; filename=msg.html");
		return response.build();
	}

	@POST
	@Consumes("*/*")
	@Path("/downloadFileMSGBASE64/{filename}")
	public Response downloadFileMSGBASE64(@PathParam("filename") String Name, final String bas64file2)
			throws IOException {
		File temp = File.createTempFile("tempfile", ".txt");

		String[] parts = bas64file2.split(",");
		String bas64file = parts[1];

		File file2 = new File("C:/sgiid/temp_files/" + Name);
		FileOutputStream out = new FileOutputStream(file2);
		Base64.Decoder decoder = Base64.getMimeDecoder();
		byte[] decodedBytes = decoder.decode(bas64file);
		out.write(decodedBytes);
		out.close();

		String filepath = "C:/sgiid/temp_files/" + Name;

		String texto = "<div class=\"MsoNormal\"> ";
		try {

			String fileName = Name;

			MsgParser msgp = new MsgParser();
			Message msg = msgp.parseMsg(filepath);
			String from_email = msg.getFromEmail();
			String from_name = msg.getFromName();
			String subject = msg.getSubject();
			String body = msg.getConvertedBodyHTML();
			String to_list = msg.getDisplayTo();
			String cc_list = msg.getDisplayCc();
			String bcc_list = msg.getDisplayBcc();
			List list = msg.getAttachments();
			texto += "<b>Anexos</b> - " + list.size() + "<br>";
			Iterator it_list = list.iterator();
			Attachment attachemetn = null;
			while (it_list.hasNext()) {
				attachemetn = (Attachment) it_list.next();
				texto += "<i>" + attachemetn + "</i><br>";
			}

			body = body.replaceAll("<!\\[endif]-->", "<![endif]");
			body = body.replaceAll("<!\\[endif]", "<![endif]-->");
			body = body.replaceAll("(?s)<!--.*?-->", "");

			List<Attachment> atts = msg.getAttachments();
			for (Attachment att : atts) {
				if (att instanceof FileAttachment) {
					FileAttachment file = (FileAttachment) att;

					// you get the actual attachment with

					String encodedString = new String(Base64.getEncoder().encodeToString((file.getData())));

					body = body.replaceAll("\\\"cid:" + file.getFilename() + ".*?\\\"",
							"\"data:image/" + getFileExtension(file) + ";base64," + encodedString + "\"");
				}
			}

			texto += "------------------------------ <br>";
			texto += "<b>De</b>:  " + from_name + " <" + from_email + "><br>";
			texto += "<b>Para</b>: " + to_list + "<br>";
			texto += "<b>Cc</b>: " + cc_list + "<br>";
			texto += "<b>Bcc</b>: " + bcc_list + "<br>";
			texto += "<b>Assunto</b>: " + subject + "<br><br><b>Mensagem</b>:<br></div>";
			texto += body + "<br>";

		} catch (Exception e) {
			System.out.println(e);
		}

		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(temp), StandardCharsets.UTF_8));
		bw.write(texto);
		bw.close();

		file2.delete();

		ResponseBuilder response = Response.ok((Object) temp);
		response.header("Content-Disposition", "attachment; filename=msg.html");
		return response.build();
	}

	private static String getFileExtension(FileAttachment file) {
		String fileName = file.getFilename();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	@GET
	@Consumes("*/*")
	@Path("/alterarlocalizacaoFicheiro/{format}/{filename}")
	// @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public void alterarlocalizacaoFicheiro(@PathParam("format") String format, @PathParam("filename") String Name) {

		String fileName = Name;

		String filepath = "C:/xampp/htdocs/dev/sgiid/";
		String filepath_new = "C:/xampp/htdocs/dev/sgiid/old/";

		try {

			File afile = new File(filepath + fileName);

			if (afile.renameTo(new File(filepath_new + afile.getName()))) {
				System.out.println("File is moved successful!");
			} else {
				System.out.println("File is failed to move!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getURL() {
		String url = "";
		Query query_folder = entityManager.createNativeQuery("select top 1 * from GER_PARAMETROS a");

		List<Object[]> dados_folder = query_folder.getResultList();

		for (Object[] content : dados_folder) {
			url = content[3].toString();
		}

		return url;
	}

	public String getFILEPATH() {
		String filepath = "";
		Query query_folder = entityManager
				.createNativeQuery("select top 1 PASTA_JASPERREPORT,PASTA_FICHEIRO from GER_PARAMETROS a");

		List<Object[]> dados_folder = query_folder.getResultList();

		for (Object[] content : dados_folder) {
			filepath = content[0].toString();
		}

		return filepath;
	}

	/* Email ************************************/
	@POST
	@Path("/sendemail")
	@Consumes("*/*")
	@Produces("application/json")
	public EMAIL sendemail(final EMAIL data, String[] fic, String ficheiro) {
		// System.out.println(data.getPARA());
		SendEmail send = new SendEmail();
		send.enviarEmail(data.getDE(), data.getPARA(), data.getASSUNTO(), data.getMENSAGEM(), data.getNOME_FICHEIRO(),
				fic, getFILEPATH(), ficheiro);
		return data;

	}

	@POST
	@Path("/verficaEventos")
	@Consumes("*/*")
	@Produces("application/json")
	public List<HashMap<String, String>> verficaEventos(final List<HashMap<String, String>> data) {
		HashMap<String, String> firstMap = data.get(0);

		String value = firstMap.get("DADOS");
		value = value.substring(1, value.length() - 1);
		String[] keyValuePairs = value.split("\n/");

		Query query3 = entityManager.createQuery("Select a from GER_EVENTOS_CONF a where MODULO = "
				+ firstMap.get("MODULO") + " and MOMENTO = '" + firstMap.get("MOMENTO") + "' " + "and PAGINA = '"
				+ firstMap.get("PAGINA") + "' and ESTADO = " + firstMap.get("ESTADO") + "");
		List<GER_EVENTOS_CONF> dados = query3.getResultList();

		for (GER_EVENTOS_CONF borderTypes : dados) {

			// System.out.println(borderTypes.getEMAIL_ASSUNTO());
			EMAIL email = new EMAIL();
			email.setDE("alertas.it.doureca@gmail.com");

			String email_para = (firstMap.get("EMAIL_PARA") != null && !firstMap.get("EMAIL_PARA").toString().isEmpty())
					? "," + firstMap.get("EMAIL_PARA") : "";

			email.setPARA(borderTypes.getEMAIL_PARA() + email_para);
			String mensagem = borderTypes.getEMAIL_MENSAGEM();
			String assunto = borderTypes.getEMAIL_ASSUNTO();

			// System.out.println(email.getPARA());
			for (String pair : keyValuePairs) {
				String[] entry = pair.split("::");

				mensagem = mensagem.replace("{" + entry[0].trim() + "}", (entry.length > 1) ? entry[1].trim() : "");

				assunto = assunto.replace("{" + entry[0].trim() + "}", (entry.length > 1) ? entry[1].trim() : "");
			}

			if (borderTypes.getEMAIL_ANEXO()) {
				email.setNOME_FICHEIRO(firstMap.get("FICHEIRO"));
			}
			email.setASSUNTO(assunto);
			email.setMENSAGEM(mensagem);

			String[] keyValuePairs3 = {};
			if (firstMap.get("FICHEIROS") != null) {
				keyValuePairs3 = firstMap.get("FICHEIROS").split("<:>");
			}

			sendemail(email, keyValuePairs3, null);
		}

		return data;
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
	@Path("/ficheirocorrecao")
	@Consumes("*/*")
	@Produces("application/json")
	public void ficheirocorrecao(final List<HashMap<String, String>> data) {

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
			nome_ficheiro = "ETIQUETA_CORRECAO" + data_path + ".txt";

			criarFicheiro(null, nome_ficheiro, content[0].toString(), content[1].toString(), content[2].toString(),
					content[3].toString(), null, ip_posto, ids);

		}
	}

	@POST
	@Path("/ficheiroimprimiretiqueta")
	@Consumes("*/*")
	@Produces("application/json")
	public void ficheiroimprimiretiqueta(final List<HashMap<String, String>> data) {

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

		String nome_ficheiro = "ETIQUETA_NUM_" + ETIQUETA + ".txt";

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

		criar_ficheiro(data_etiq, path2, path_error, false, "");
	}

	@POST
	@Path("/ficheiro")
	@Produces("application/json")
	public void getFicheiro(final List<HashMap<String, String>> data2) throws IOException, ParseException {
		HashMap<String, String> firstMap = data2.get(0);
		String ip_posto = firstMap.get("ip_posto");
		String id = firstMap.get("id");
		try {
			// Thread.sleep(3000);
			Integer comp_num = 1;

			String data = new SimpleDateFormat("yyyyMMddHHmmss_").format(new java.util.Date());
			String nome_ficheiro = "";

			/*
			 * Query query = entityManager.createNativeQuery(
			 * "Select f.ID_MANUTENCAO_LIN,d.OF_NUM,c.SECCAO,c.SUBSECCAO,c.REF_COMPOSTO from AB_MOV_MANUTENCAO_CAB a "
			 * +
			 * "inner join AB_MOV_MANUTENCAO_LINHA f on a.ID_MANUTENCAO_CAB = f.ID_MANUTENCAO_CAB "
			 * +
			 * "inner join AB_MOV_MANUTENCAO b on  a.ID_MANUTENCAO = b.ID_MANUTENCAO "
			 * + "inner join AB_DIC_LINHA c on  b.ID_LINHA = c.ID_LINHA " +
			 * "inner join (select top 1 * from AB_DIC_LINHA_OF e order by e.DATA) d on c.ID_LINHA = d.ID_LINHA "
			 * + "where a.ID_MANUTENCAO_CAB = " + id);
			 */
			Query query = entityManager.createNativeQuery(
					"Select a.ID_MANUTENCAO_CAB,(select top 1 OF_NUM from AB_DIC_LINHA_OF e where ID_LINHA = c.id_linha and DATA <= GETDATE() order by e.DATA desc) as ofnum,c.SECCAO,c.SUBSECCAO,c.REF_COMPOSTO,a.ID_MANUTENCAO "
							+ "from AB_MOV_MANUTENCAO_CAB a "
							+ "inner join AB_MOV_MANUTENCAO b on  a.ID_MANUTENCAO = b.ID_MANUTENCAO "
							+ "inner join AB_DIC_LINHA c on  b.ID_LINHA = c.ID_LINHA " + "where a.ID_MANUTENCAO_CAB = "
							+ id);

			List<Object[]> dados = query.getResultList();

			for (Object[] content : dados) {
				// nome_ficheiro = data + "_ETIQUETA_" + content[0].toString() +
				// ".txt";
				nome_ficheiro = "ETIQUETA_ID" + content[0].toString() + ".txt";

				criarFicheiro(content[0].toString(), nome_ficheiro, content[1].toString(), content[2].toString(),
						content[3].toString(), content[4].toString(), content[5].toString(), ip_posto, null);

			}

			Query query2 = entityManager.createNativeQuery(
					"select ID_MANUTENCAO_LIN,ID_MANUTENCAO_CAB from AB_MOV_MANUTENCAO_LINHA where ID_MANUTENCAO_CAB = "
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
		 * "select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO from AB_MOV_MANUTENCAO_ETIQ a where ID_MANUTENCAO_LIN = "
		 * + id + "");
		 */

		Query query = entityManager.createNativeQuery(
				"select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO,a.INDNUMENR,a.EMPCOD,a.PRODES,a.DATCRE "
						+ ",(select ID_MANUTENCAO from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO_CAB = b.ID_MANUTENCAO_CAB) as id2 "
						+ ", CASE WHEN ( a.QUANT  - a.QUANT_FINAL) < 0 THEN (( a.QUANT  - a.QUANT_FINAL) * -1) ELSE ( a.QUANT  - a.QUANT_FINAL) END as qtt , t.CISTERNA "
						+ ",( a.QUANT_FINAL / (CASE WHEN t.FACTOR_CONVERSAO IS NULL  THEN 1 WHEN t.FACTOR_CONVERSAO = 0 THEN 1 ELSE t.FACTOR_CONVERSAO END) ) as qtt2 "
						+ ",(select MEDIDA from AB_DIC_UNIDADE_MEDIDA where ID_MEDIDA = t.ID_UNIDADE_ADITIVO) as unidaditivo "
						+ ", CASE WHEN ( a.QUANT  - a.QUANT_FINAL) < 0 THEN '-' ELSE '+' END as sinal,a.ID_MOV_MANU_ETIQUETA "
						+ "from AB_MOV_MANUTENCAO_ETIQ a "
						+ "inner join AB_MOV_MANUTENCAO_LINHA b on a.ID_MANUTENCAO_LIN = b.ID_MANUTENCAO_LIN "
						+ "inner join AB_DIC_COMPONENTE t on  t.ID_COMPONENTE = b.ID_ADITIVO "
						+ "where b.ID_MANUTENCAO_CAB  = " + id + "");

		Query query2 = entityManager.createNativeQuery(
				"select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO,a.INDNUMENR,a.EMPCOD,a.PRODES,a.DATCRE "
						+ ",'correcao'+CONVERT(varchar(10), ID_MOV_MANU_ETIQUETA), a.CONSUMIR as cons, '0' as id2,a.QUANT_FINAL as qtdf,a.UNISTO as unnd,a.sinal,a.ID_MOV_MANU_ETIQUETA "
						+ "from AB_MOV_MANUTENCAO_ETIQ a where a.ID_MOV_MANU_ETIQUETA in (" + ids + ")");

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
			path2 = path2 + "ETIQUETA" + id + ".txt";
			path_error2 = path_error2 + "ETIQUETA" + id + ".txt";
		} else {
			path2 = path2 + "ETIQUETA_CORRECAO_IMPRE_" + data_path + ".txt";
			path_error2 = path_error2 + "ETIQUETA_CORRECAO_IMPRE_" + data_path + ".txt";
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
				data_etiq += criaFicheiroEtiqueta(content);
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
			data += "01        ";// Société
			data += datatual; // Date suivi
			data += sequencia; // N° séquence

			data += "    ";// + Ligne de production

			data += "1";// Type N° OF
			data += (of + "          ").substring(0, 10); // N° OF

			data += "1";// Type opération

			// OP_NUM
			data += ("0010").substring(0, 4);// N° Opération

			data += "1";// Position ( S12 )

			// Code section
			data += (SECCAO + "          ").substring(0, 10);

			// Code sous-section
			data += (SUBSECCAO + "          ").substring(0, 10);

			data += "  "; // N° d'équipe

			// Type de ressource
			data += ("    ").substring(0, 4);

			// Code ressource
			data += ("          ").substring(0, 10);

			data += "   C"; // N° établissement + Type d'élément C

			// Date début
			data += datatual;
			// Heure début
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

			// Référence composé
			data += (REF_COMPOSTO + "                 ").substring(0, 17);

			// Variante composé (1)
			data += ("          ").substring(0, 10);

			// Variante composé (2)
			data += ("          ").substring(0, 10);

			// Indice du composé
			data += ("          ").substring(0, 10);

			// N° enregistrement Csé
			String enregistrementcse = "000000000";
			String sizecse = enregistrementcse + INDNUMCSE;
			enregistrementcse = (sizecse).substring(sizecse.length() - 9, sizecse.length());
			data += enregistrementcse;

			// N° de rang
			/*
			 * String rang = "00000"; if (Orig_Composant) { String size = rang +
			 * NCLRANG; rang = (size).substring(size.length() - 5,
			 * size.length()); data += rang; } else { data += rang; }
			 */

			if (Orig_Composant) {
				data += (NCLRANG + "     ").substring(0, 5);
			} else {
				data += ("     ").substring(0, 5);
			}

			// Référence composant
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

			// N° enregistrement Cst

			String enregistrement = "000000000";
			if (content[14] != null) {
				String size = enregistrement + content[14];
				enregistrement = (size).substring(size.length() - 9, size.length());
				data += enregistrement;
			} else {
				data += enregistrement;
			}

			/*
			 * if (content[14] != null) { data += (content[14] +
			 * "         ").substring(0, 9); } else { data +=
			 * ("         ").substring(0, 9); }
			 */

			// Type quantité
			data += "1"; // Signe

			// Quantité
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
			// Unité
			if (content[8] != null) {
				data += (content[8] + "    ").substring(0, 4);
			} else {
				data += "    ";
			}

			// Quantité (US2)
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

			// Référence du lot
			if (content[10] != null) {
				data += (content[10] + "                                   ").substring(0, 35);
			} else {
				data += "                                   ";
			}

			// N° de lot interne
			String lotinterne = "000000000";
			if (content[12] != null) {
				String size = lotinterne + content[12];
				lotinterne = (size).substring(size.length() - 9, size.length());
				data += lotinterne;
			} else {
				data += lotinterne;
			}

			// N° d'étiquette
			if (content[0] != null) {
				data += (content[0] + "          ").substring(0, 10);
			} else {
				data += "          ";
			}

			// N° enreg. étiquette
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
			entityManager.createNativeQuery("UPDATE AB_MOV_MANUTENCAO_ETIQ SET DATA_PREP_EXEC = '" + timeStamp
					+ "' where ID_MOV_MANU_ETIQUETA = " + content[24] + "").executeUpdate();
		}

		if (data.length() > 0) {
			criar_ficheiro(data, path, path_error, false, "");
		}

		if (size_etiq > 0 && imprime) {
			criar_ficheiro(data_etiq, path2, path_error2, false, "");
		}

		/*
		 * new java.util.Timer().schedule(new java.util.TimerTask() {
		 * 
		 * @Override public void run() { try {
		 * connectionProgress.EXEC_SINCRO("SETQDE", getURLSILVER()); } catch
		 * (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } try { connectionProgress.EXEC_SINCRO("SOFC",
		 * getURLSILVER()); } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } } }, 1000);
		 */

	}

	public String criaFicheiroEtiqueta(Object[] content) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = null;
		try {
			date1 = formatter.parse(content[17].toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		formatter = new SimpleDateFormat("yyMMdd");
		String datas = formatter.format(date1);
		String data = "";
		String quant = String.format("%.3f", content[21]).replace("$", ",");
		data += "DOURECA - PT- 4940;DOURECA - PT- 4940;;EW93 404D52 DA;P;";
		data += quant + " " + ((content[22] != null) ? content[22] : "") + ";" // AF3
				+ ";" // A3
				+ ";" // AF4
				+ ";" // A4
				+ content[0] + ";" // AF5
				+ "S;" // A5
				+ content[16] + ";" // AF6
				+ content[7] + ";" // AF7
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
				+ content[10] + ";" // AF21
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
				+ content[18] + ";" // AF44
				+ ";\r\n";

		return data;
	}

	public void alerta_etiquetas(Integer id) {

		DecimalFormat decimalFormat = new DecimalFormat("#0.000");

		String email_para = "", numero_manutencao = "", data_manutencao = "", nome_banho = "", tina = "",
				utilizador = "", total_consumido = "", valor_aditivo = "", linha = "", tipo_manutencao = "",
				ref_aditivo = "", nome_aditivo = "";

		String etiquetas = "<table  border='1'><tr><th><b>Nº Etiqueta</b></th><th><b>Qtd.</b></th><th><b>Consumido</b></th><th><b>Qtd. Final</b></th></tr>";
		String valor = null, total = null;
		Query query = entityManager.createNativeQuery(
				"select d.ID_MANUTENCAO,c.ETQNUM,c.CONSUMIR,b.VALOR1,d.DATA_PLANEAMENTO,d.HORA_PLANEAMENTO, "
						+ "(select SUM(v.CONSUMIR) from AB_MOV_MANUTENCAO_ETIQ v where v.ID_MANUTENCAO_LIN = b.ID_MANUTENCAO_LIN) as total, "
						+ "(select MEDIDA from AB_DIC_UNIDADE_MEDIDA where ID_MEDIDA = b.ID_UNIDADE1) as unidade, "
						+ "(select f.NOME_BANHO from AB_DIC_BANHO f where f.ID_BANHO = a.ID_BANHO) as banho, a.ID_BANHO, "
						+ "(select f.EMAIL_PARA from AB_DIC_BANHO f where f.ID_BANHO = a.ID_BANHO) as email_banho, "
						+ "(select g.COD_TINA from AB_DIC_TINA g where g.ID_TINA = a.ID_TINA) as tina,  "
						+ "(select h.NOME_TIPO_MANUTENCAO from AB_DIC_TIPO_MANUTENCAO h where h.ID_TIPO_MANUTENCAO = d.ID_TIPO_MANUTENCAO) as tipo, "
						+ "(select NOME_UTILIZADOR from GER_UTILIZADORES h where ID_UTILIZADOR = d.UTZ_ULT_MODIF) as utilizador, "
						+ "(select NOME_LINHA from AB_DIC_LINHA h where ID_LINHA = d.ID_LINHA) as linha, "
						+ "CASE WHEN ( c.QUANT  - c.QUANT_FINAL) < 0 THEN 0 ELSE ( c.QUANT  - c.QUANT_FINAL) END as qtt , c.UNICOD,c.QUANT, t.FACTOR_CONVERSAO,t.NOME_REF,t.COD_REF,c.QUANT_FINAL, "
						+ "a.DATA_PREPARACAO,a.HORA_PREPARACAO,a.DATA_EXECUCAO,a.HORA_EXECUCAO "
						+ "from AB_MOV_MANUTENCAO_CAB a "
						+ "inner join AB_MOV_MANUTENCAO_LINHA b on a.ID_MANUTENCAO_CAB = b.ID_MANUTENCAO_CAB "
						+ "left join AB_MOV_MANUTENCAO_ETIQ c on b.ID_MANUTENCAO_LIN = c.ID_MANUTENCAO_LIN "
						+ "inner join AB_MOV_MANUTENCAO d on a.ID_MANUTENCAO = d.ID_MANUTENCAO "
						+ "inner join AB_DIC_COMPONENTE t on b.ID_ADITIVO =  t.ID_COMPONENTE "
						+ "where b.ID_MANUTENCAO_LIN =" + id);
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
			nome_banho = content[9].toString() + "/" + content[8].toString();
			email_para = (content[10] != null) ? content[10].toString() : "";
			tina = content[11].toString();
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
		n.put("PAGINA", "Manutenções");
		n.put("ESTADO", "1");
		n.put("EMAIL_PARA", email_para);

		n.put("DADOS",
				"{numero_manutencao::" + numero_manutencao + "\n/datahorapreparacao::" + datahorapreparacao
						+ "\n/datahoraexecucao::" + datahoraexecucao + "\n/data_manutencao::" + data_manutencao + ""
						+ "\n/nome_banho::" + nome_banho + "" + "\n/tina::" + tina + "\n/utilizador::" + utilizador + ""
						+ "\n/dados_etiquetas::" + etiquetas + "" + "" + "\n/total_consumido::"
						+ total_consumido.replace("$", ",") + "\n/valor_aditivo::" + valor_aditivo.replace("$", ",")
						+ "" + "\n/linha::" + linha + "" + "\n/tipo_manutencao::" + tipo_manutencao
						+ "\n/nome_aditivo::" + nome_aditivo + "\n/ref_aditivo::" + ref_aditivo + "}");
		data.add(n);

		if (valor != null && !valor.equals(total))
			verficaEventos(data);
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

	public void criar_ficheiro(String data, String path, String path_error, Boolean error, String err) {
		File file2 = new File(path);
		if (file2.delete())
			// if file doesnt exists, then create it

			try {
				file2.createNewFile();
			} catch (IOException e2) {
				String[] keyValuePairs = { "texto_erro ::" + e2.getMessage() + " " + file2.getAbsolutePath() + "", };
				verficaEventos(keyValuePairs, "Erros ao Criar Etiquetas", "");
				e2.printStackTrace();
			}
		BufferedWriter bw2 = null;
		FileWriter fw2 = null;
		// true = append file
		try {
			fw2 = new FileWriter(file2.getAbsoluteFile(), true);
		} catch (IOException e) {

			if (!error)
				criar_ficheiro(data, path_error, path_error, true, e.getMessage() + " " + file2.getAbsolutePath());
			e.printStackTrace();
		}
		if (fw2 != null) {
			bw2 = new BufferedWriter(fw2);
			try {
				bw2.write(data);
				if (bw2 != null) {
					bw2.close();
				}
				if (fw2 != null) {
					fw2.close();
				}
			} catch (IOException e) {
				if (bw2 != null) {
					try {
						bw2.close();
					} catch (IOException e1) {
						String[] keyValuePairs = {
								"texto_erro ::" + e1.getMessage() + " " + file2.getAbsolutePath() + "", };
						verficaEventos(keyValuePairs, "Erros ao Criar Etiquetas", "");
						e1.printStackTrace();
					}
				}
				if (fw2 != null) {
					try {
						fw2.close();
					} catch (IOException e1) {
						String[] keyValuePairs = {
								"texto_erro ::" + e1.getMessage() + " " + file2.getAbsolutePath() + "", };
						verficaEventos(keyValuePairs, "Erros ao Criar Etiquetas", "");
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
		}
		if (error)

		{
			String[] keyValuePairs = { "texto_erro ::" + err + "", };
			verficaEventos(keyValuePairs, "Erros ao Criar Etiquetas", path_error);
		}
	}

	@POST
	@Path("/traduzir")
	@Consumes("*/*")
	@Produces("application/json")
	public String traduzirtexto(final List<HashMap<String, String>> datas) throws IOException {

		HashMap<String, String> firstMap = datas.get(0);

		String text = firstMap.get("texto");
		String langTo = firstMap.get("langTo");

		String link = null;
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("c:\\sgiid\\conf_email.ini"));
			link = p.getProperty("link_script");
			// p.list(System.out);

		} catch (Exception e) {
		}

		String urlStr = link + "?q=" + java.net.URLEncoder.encode(text, "UTF-8") + "&target=" + langTo;

		java.net.URL url = new java.net.URL(urlStr);
		StringBuilder response = new StringBuilder();
		java.net.HttpURLConnection con = (java.net.HttpURLConnection) url.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);

		}
		in.close();

		String texto = new String(response.toString().getBytes(), "UTF-8").replace("&#39;", "'").replaceAll("&quot;",
				" ");

		return texto;
	}

	@POST
	@Path("/ficheiromanualgetEtiquetas")
	@Consumes("*/*")
	@Produces("application/json")
	public List<HashMap<String, String>> ficheiromanualgetEtiquetas(final List<HashMap<String, String>> datas)
			throws IOException, ParseException {
		final ConnectProgress connectionProgress = new ConnectProgress();
		HashMap<String, String> firstMap = datas.get(0);

		String datainicio = firstMap.get("datainicio");
		String datafim = firstMap.get("datafim");
		String ip = firstMap.get("ip");

		String data = new SimpleDateFormat("yyyyMMddHHmmss_").format(new java.util.Date());

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		try {
			// Thread.sleep(3000);

			String nome_ficheiro = "";

			Query query = entityManager.createNativeQuery(
					"select c.ID_MANUTENCAO,a.ETQNUM,a.ID_MOV_MANU_ETIQUETA,a.DATA_PREP_EXEC,a.PROREF,a.PRODES,a.LIECOD, CASE WHEN CONVERT(DECIMAL(10,3),( a.QUANT  - a.QUANT_FINAL)) < 0 THEN CONVERT(DECIMAL(10,3),(( a.QUANT  - a.QUANT_FINAL) * -1)) ELSE CONVERT(DECIMAL(10,3),( a.QUANT  - a.QUANT_FINAL)) END as qtt "
							+ ",a.UNICOD, CONVERT(char(10),a.DATA_PREP_EXEC,120) as DATA,CONVERT(char(8),a.DATA_PREP_EXEC,114) as HORA,CONVERT(DECIMAL(10,3),( a.QUANT  - a.QUANT_FINAL)) as tt "
							+ "from AB_MOV_MANUTENCAO_ETIQ a "
							+ "inner join AB_MOV_MANUTENCAO_LINHA b on a.ID_MANUTENCAO_LIN = b.ID_MANUTENCAO_LIN "
							+ "inner join AB_MOV_MANUTENCAO_CAB c on b.ID_MANUTENCAO_CAB = c.ID_MANUTENCAO_CAB "
							+ "where DATA_PREP_EXEC >= '" + datainicio + "' AND DATA_PREP_EXEC <= '" + datafim
							+ "' AND a.DATA_PREP_EXEC is not null");

			List<Object[]> dados = query.getResultList();

			for (Object[] content : dados) {

				List<HashMap<String, String>> op_num = connectionProgress.verificaEtiqueta(getURLSILVER(),
						content[1].toString(), content[11].toString(), content[6].toString(), content[9].toString(),
						content[10].toString(), content[4].toString());

				if (op_num.size() == 0) {
					HashMap<String, String> x = new HashMap<String, String>();
					x.put("DATA", content[3].toString());
					x.put("REF", content[4].toString());
					x.put("REF_DES", content[5].toString());
					x.put("ETIQUETA", content[1].toString());
					x.put("MANUTENCAO", content[0].toString());
					x.put("QUANTIDADE", content[7].toString());
					x.put("ID", content[2].toString());
					x.put("UNICOD", content[8].toString());
					x.put("ARMAZEM", content[6].toString());
					list.add(x);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@POST
	@Path("/ficheiromanual")
	@Consumes("*/*")
	@Produces("application/zip")
	public Response getFicheiroManual(final List<HashMap<String, String>> datas) throws IOException, ParseException {

		HashMap<String, String> firstMap = datas.get(0);

		String datainicio = firstMap.get("datainicio");
		String datafim = firstMap.get("datafim");
		String ip = firstMap.get("ip");

		String data = new SimpleDateFormat("yyyyMMddHHmmss_").format(new java.util.Date());

		try {
			// Thread.sleep(3000);

			String nome_ficheiro = "";

			Query query = entityManager.createNativeQuery(
					"select c.ID_MANUTENCAO_CAB, (select top 1 t.OF_NUM from AB_DIC_LINHA_OF t where t.ID_LINHA = d.id_linha and t.DATA <= GETDATE() order by t.DATA desc) as ofnum, "
							+ "e.SECCAO,e.SUBSECCAO,e.REF_COMPOSTO,c.ID_MANUTENCAO " + "from AB_MOV_MANUTENCAO_ETIQ a "
							+ "left join AB_MOV_MANUTENCAO_LINHA b on a.ID_MANUTENCAO_LIN = b.ID_MANUTENCAO_LIN "
							+ "left join AB_MOV_MANUTENCAO_CAB c on b.ID_MANUTENCAO_CAB = c.ID_MANUTENCAO_CAB "
							+ "left join AB_MOV_MANUTENCAO d on c.ID_MANUTENCAO = d.ID_MANUTENCAO "
							+ "left join AB_DIC_LINHA e on e.ID_LINHA = d.ID_LINHA " + "where a.ID_MANUTENCAO_LIN != 0 "
							+ "and a.DATA_PREP_EXEC >= '" + datainicio + "' and a.DATA_PREP_EXEC <= '" + datafim
							+ "' and c.ID_MANUTENCAO_CAB is not null "
							+ "group by c.ID_MANUTENCAO_CAB,e.SECCAO,e.SUBSECCAO,e.REF_COMPOSTO,c.ID_MANUTENCAO,d.ID_LINHA order by c.ID_MANUTENCAO_CAB");

			List<Object[]> dados = query.getResultList();

			for (Object[] content : dados) {
				// nome_ficheiro = data + "_ETIQUETA_" + content[0].toString() +
				// ".txt";
				nome_ficheiro = "ETIQUETA_ID" + content[0].toString() + ".txt";

				criarFicheiroManual(content[0].toString(), nome_ficheiro, content[1].toString(), content[2].toString(),
						content[3].toString(), content[4].toString(), content[5].toString(), null, data, ip, false);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		final File file = new File("c:/sgiid/temp_files/" + data + ".zip");
		if (file.exists() && !file.isDirectory()) {
			ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition", "attachment; filename=ficheiros.zip");
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					file.delete();
				}
			}, 5000);
			return response.build();
		} else {
			return null;
		}
	}

	@POST
	@Path("/ficheiromanual2")
	@Consumes("*/*")
	@Produces("application/zip")
	public Response getFicheiroManual2(final List<HashMap<String, String>> datas) throws IOException, ParseException {

		HashMap<String, String> firstMap = datas.get(0);

		String ids = firstMap.get("ids");
		String ip = firstMap.get("ip");

		String data = new SimpleDateFormat("yyyyMMddHHmmss_").format(new java.util.Date());

		try {
			// Thread.sleep(3000);

			String nome_ficheiro = "";

			Query query = entityManager.createNativeQuery(
					"select a.ID_MOV_MANU_ETIQUETA, (select top 1 t.OF_NUM from AB_DIC_LINHA_OF t where t.ID_LINHA = d.id_linha and t.DATA <= GETDATE() order by t.DATA desc) as ofnum, "
							+ "e.SECCAO,e.SUBSECCAO,e.REF_COMPOSTO,c.ID_MANUTENCAO from AB_MOV_MANUTENCAO_ETIQ a "
							+ "left join AB_MOV_MANUTENCAO_LINHA b on a.ID_MANUTENCAO_LIN = b.ID_MANUTENCAO_LIN "
							+ "left join AB_MOV_MANUTENCAO_CAB c on b.ID_MANUTENCAO_CAB = c.ID_MANUTENCAO_CAB left join AB_MOV_MANUTENCAO d on c.ID_MANUTENCAO = d.ID_MANUTENCAO "
							+ "left join AB_DIC_LINHA e on e.ID_LINHA = d.ID_LINHA where a.ID_MANUTENCAO_LIN != 0 AND a.ID_MOV_MANU_ETIQUETA in ("
							+ ids + ") " + "and c.ID_MANUTENCAO_CAB is not null ");

			List<Object[]> dados = query.getResultList();

			for (Object[] content : dados) {
				// nome_ficheiro = data + "_ETIQUETA_" + content[0].toString() +
				// ".txt";
				nome_ficheiro = "ETIQUETA_ID" + content[0].toString() + ".txt";

				criarFicheiroManual(content[0].toString(), nome_ficheiro, content[1].toString(), content[2].toString(),
						content[3].toString(), content[4].toString(), content[5].toString(), null, data, ip, true);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		final File file = new File("c:/sgiid/temp_files/" + data + ".zip");
		if (file.exists() && !file.isDirectory()) {
			ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition", "attachment; filename=ficheiros.zip");
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					file.delete();
				}
			}, 5000);
			return response.build();
		} else {
			return null;
		}
	}

	public void criarFicheiroManual(String id, String nome_ficheiro, String of, String SECCAO, String SUBSECCAO,
			String REF_COMPOSTO, String num_manutencao, String ids, String nomezip, String ip_posto, Boolean query2)
			throws IOException {

		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat horaformate = new SimpleDateFormat("HHmmss");
		String datatual = formate.format(new java.util.Date());
		String horatual = horaformate.format(new java.util.Date());
		String sequencia = "000000000";
		String nomeimpressora = "";
		String ipimpressora = "";
		String data_etiq = "";
		String modelo_REPORT = "";

		// String path = "";
		// String path_error = "";
		/*
		 * Query query = entityManager.createNativeQuery(
		 * "select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO from AB_MOV_MANUTENCAO_ETIQ a where ID_MANUTENCAO_LIN = "
		 * + id + "");
		 */

		Query query = entityManager.createNativeQuery(
				"select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO,a.INDNUMENR,a.EMPCOD,a.PRODES,a.DATCRE"
						+ ",(select ID_MANUTENCAO from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO_CAB = b.ID_MANUTENCAO_CAB) as id2 "
						+ ", CASE WHEN ( a.QUANT  - a.QUANT_FINAL) < 0 THEN (( a.QUANT  - a.QUANT_FINAL) * -1) ELSE ( a.QUANT  - a.QUANT_FINAL) END as qtt , t.CISTERNA "
						+ ",( a.QUANT_FINAL / (CASE WHEN t.FACTOR_CONVERSAO IS NULL  THEN 1 WHEN t.FACTOR_CONVERSAO = 0 THEN 1 ELSE t.FACTOR_CONVERSAO END) ) as qtt2 "
						+ ",(select MEDIDA from AB_DIC_UNIDADE_MEDIDA where ID_MEDIDA = t.ID_UNIDADE_ADITIVO) as unidaditivo, a.DATA_PREP_EXEC "
						+ ", CASE WHEN ( a.QUANT  - a.QUANT_FINAL) < 0 THEN '-' ELSE '+' END as sinal "
						+ "from AB_MOV_MANUTENCAO_ETIQ a "
						+ "inner join AB_MOV_MANUTENCAO_LINHA b on a.ID_MANUTENCAO_LIN = b.ID_MANUTENCAO_LIN "
						+ "inner join AB_DIC_COMPONENTE t on  t.ID_COMPONENTE = b.ID_ADITIVO "
						+ "where b.ID_MANUTENCAO_CAB  = " + id + "");

		Query query3 = entityManager.createNativeQuery(
				"select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO,a.INDNUMENR,a.EMPCOD,a.PRODES,a.DATCRE"
						+ ",(select ID_MANUTENCAO from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO_CAB = b.ID_MANUTENCAO_CAB) as id2 "
						+ ", CASE WHEN ( a.QUANT  - a.QUANT_FINAL) < 0 THEN (( a.QUANT  - a.QUANT_FINAL) * -1) ELSE ( a.QUANT  - a.QUANT_FINAL) END as qtt , t.CISTERNA "
						+ ",( a.QUANT_FINAL / (CASE WHEN t.FACTOR_CONVERSAO IS NULL  THEN 1 WHEN t.FACTOR_CONVERSAO = 0 THEN 1 ELSE t.FACTOR_CONVERSAO END) ) as qtt2 "
						+ ",(select MEDIDA from AB_DIC_UNIDADE_MEDIDA where ID_MEDIDA = t.ID_UNIDADE_ADITIVO) as unidaditivo, a.DATA_PREP_EXEC "
						+ ", CASE WHEN ( a.QUANT  - a.QUANT_FINAL) < 0 THEN '-' ELSE '+' END as sinal "
						+ "from AB_MOV_MANUTENCAO_ETIQ a "
						+ "inner join AB_MOV_MANUTENCAO_LINHA b on a.ID_MANUTENCAO_LIN = b.ID_MANUTENCAO_LIN "
						+ "inner join AB_DIC_COMPONENTE t on  t.ID_COMPONENTE = b.ID_ADITIVO "
						+ "where a.ID_MOV_MANU_ETIQUETA  = " + id + "");

		// path = "c:\\etiquegtas\\" + nome_ficheiro;
		// path_error = "c:\\etiquetas\\" + nome_ficheiro;

		Query query_impressora = entityManager.createNativeQuery(
				"select top 1  NOME_IMPRESSORA,IP_IMPRESSORA from GER_POSTOS b where IP_POSTO ='" + ip_posto + "'");

		Query query_folder = entityManager
				.createNativeQuery("select top 1  PASTA_FICHEIRO,PASTA_ETIQUETAS,MODELO_REPORT from GER_PARAMETROS a");

		List<Object[]> dados_impressora = query_impressora.getResultList();
		List<Object[]> dados_folder = query_folder.getResultList();
		Boolean imprime = false;

		for (Object[] content2 : dados_impressora) {
			nomeimpressora = content2[0].toString();
			if (content2[1] != null) {
				ipimpressora = content2[1].toString();
			}
			imprime = true;
		}

		for (Object[] content : dados_folder) {
			modelo_REPORT = content[2].toString();
		}

		sequencia = sequencia();

		List<Object[]> dados = null;

		if (query2) {
			dados = query3.getResultList();
		} else {
			dados = query.getResultList();
		}

		final ConnectProgress connectionProgress = new ConnectProgress();
		List<HashMap<String, String>> lista = null;
		List<HashMap<String, String>> lista2 = null;
		Boolean Orig_Composant = false;
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
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
			java.util.Date parsed = null;
			try {
				parsed = format.parse(content[23].toString());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			datatual = formate.format(parsed);
			horatual = horaformate.format(parsed);

			String cisterna = (content[20] != null) ? content[20].toString() : "false";
			if (content[3] != null && Float.parseFloat(content[3].toString()) != 0
					&& !content[3].toString().equals(content[1].toString()) && !cisterna.equals("true")) {
				// path2 = path2 + data_path + "_" + content[0].toString();

				// criar ficheiro que gera etiquetas
				data_etiq += criaFicheiroEtiqueta(content);
				size_etiq++;
			}
			/*
			 * try { connectionProgress.EXEC_SINCRO(content[0].toString(),
			 * Float.parseFloat(content[3].toString()), getURLSILVER()); } catch
			 * (SQLException e1) { // TODO Auto-generated catch block
			 * e1.printStackTrace(); }
			 */

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
			data += "01        ";// Société
			data += datatual; // Date suivi
			data += sequencia; // N° séquence

			data += "    ";// + Ligne de production

			data += "1";// Type N° OF
			data += (of + "          ").substring(0, 10); // N° OF

			data += "1";// Type opération

			// OP_NUM
			data += ("0010").substring(0, 4);// N° Opération

			data += "1";// Position ( S12 )

			// Code section
			data += (SECCAO + "          ").substring(0, 10);

			// Code sous-section
			data += (SUBSECCAO + "          ").substring(0, 10);

			data += "  "; // N° d'équipe

			// Type de ressource
			data += ("    ").substring(0, 4);

			// Code ressource
			data += ("          ").substring(0, 10);

			data += "   C"; // N° établissement + Type d'élément C

			// Date début
			data += datatual;
			// Heure début
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
				Orig_Composant = false;
				data += "1";
			}

			// Référence composé
			data += (REF_COMPOSTO + "                 ").substring(0, 17);

			// Variante composé (1)
			data += ("          ").substring(0, 10);

			// Variante composé (2)
			data += ("          ").substring(0, 10);

			// Indice du composé
			data += ("          ").substring(0, 10);

			// N° enregistrement Csé
			String enregistrementcse = "000000000";
			String sizecse = enregistrementcse + INDNUMCSE;
			enregistrementcse = (sizecse).substring(sizecse.length() - 9, sizecse.length());
			data += enregistrementcse;

			// N° de rang
			/*
			 * String rang = "00000"; if (Orig_Composant) { String size = rang +
			 * NCLRANG; rang = (size).substring(size.length() - 5,
			 * size.length()); data += rang; } else { data += rang; }
			 */

			if (Orig_Composant) {
				data += (NCLRANG + "     ").substring(0, 5);
			} else {
				data += ("     ").substring(0, 5);
			}

			// Référence composant
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

			// N° enregistrement Cst

			String enregistrement = "000000000";
			if (content[14] != null) {
				String size = enregistrement + content[14];
				enregistrement = (size).substring(size.length() - 9, size.length());
				data += enregistrement;
			} else {
				data += enregistrement;
			}

			/*
			 * if (content[14] != null) { data += (content[14] +
			 * "         ").substring(0, 9); } else { data +=
			 * ("         ").substring(0, 9); }
			 */

			// Type quantité
			data += "1"; // Signe

			// Quantité
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
				data += content[24].toString(); // Signe
			} else {
				data += content[23]; // Signe
			}
			// Unité
			if (content[8] != null) {
				data += (content[8] + "    ").substring(0, 4);
			} else {
				data += "    ";
			}

			// Quantité (US2)
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

			// Référence du lot
			if (content[10] != null) {
				data += (content[10] + "                                   ").substring(0, 35);
			} else {
				data += "                                   ";
			}

			// N° de lot interne
			String lotinterne = "000000000";
			if (content[12] != null) {
				String size = lotinterne + content[12];
				lotinterne = (size).substring(size.length() - 9, size.length());
				data += lotinterne;
			} else {
				data += lotinterne;
			}

			// N° d'étiquette
			if (content[0] != null) {
				data += (content[0] + "          ").substring(0, 10);
			} else {
				data += "          ";
			}

			// N° enreg. étiquette
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

		}

		if (data.length() > 0) {
			// criar_ficheiro(data, path, path_error, false, "");

			Map<String, String> env = new HashMap<String, String>();
			env.put("create", "true");
			java.nio.file.Path pathh = Paths.get("c:/sgiid/temp_files/" + nomezip + ".zip");
			URI uri = URI.create("jar:" + pathh.toUri());
			try (FileSystem fs = FileSystems.newFileSystem(uri, env)) {
				java.nio.file.Path nf = fs.getPath(nome_ficheiro);
				try (Writer writer = Files.newBufferedWriter(nf, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
					writer.write(data);
				}
			}
		}

		if (size_etiq > 0 && imprime) {
			Map<String, String> env = new HashMap<String, String>();
			env.put("create", "true");
			java.nio.file.Path pathh = Paths.get("c:/sgiid/temp_files/" + nomezip + ".zip");
			URI uri = URI.create("jar:" + pathh.toUri());
			try (FileSystem fs = FileSystems.newFileSystem(uri, env)) {
				java.nio.file.Path nf = fs.getPath("IMPRIMIR_" + nome_ficheiro);
				try (Writer writer = Files.newBufferedWriter(nf, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
					writer.write(data_etiq);
				}
			}
		}

	}

	// Atualizar Estados manutenções
	@GET
	@Path("/atualizarestados/{id}")
	@Produces("application/json")
	public int atualizarestados(@PathParam("id") Integer id) {

		Query query = entityManager
				.createNativeQuery("UPDATE AB_MOV_MANUTENCAO set ESTADO = 'Preparado' where ID_MANUTENCAO = " + id
						+ " and " + "(select count(*) from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO = " + id
						+ "  and INATIVO != 1 ) = "
						+ "(select count(*) from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO = " + id
						+ " and DATA_PREPARACAO is not null and INATIVO != 1) "
						+ "and  (select count(*) from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO = " + id
						+ " and DATA_EXECUCAO is not null and INATIVO != 1) = 0 "
						+ "and (select ESTADO from AB_MOV_MANUTENCAO where ID_MANUTENCAO = " + id
						+ "  and INATIVO != 1 ) ='Em Preparação' and (select count(*) from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO = "
						+ id + "  and INATIVO != 1 ) > 0 "
						+ "UPDATE AB_MOV_MANUTENCAO set ESTADO = 'Executado' where ID_MANUTENCAO = " + id + " and "
						+ "(select count(*) from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO = " + id
						+ "  and INATIVO != 1 ) = "
						+ "(select count(*) from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO = " + id
						+ " and DATA_EXECUCAO is not null and INATIVO != 1) and (select count(*) from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO = "
						+ id + "  and INATIVO != 1 ) > 0");

		int dados = query.executeUpdate();

		return dados;
	}

	// Atualizar Estados manutenções
	@GET
	@Path("/atualizarestadosDosificadores/{id}")
	@Produces("application/json")
	public int atualizarestadosDosificadores(@PathParam("id") Integer id) {

		Query query = entityManager.createNativeQuery("DECLARE @ID int = 69937 ; "
				+ "UPDATE AB_MOV_MANUTENCAO set ESTADO = 'Executado' where ID_MANUTENCAO = " + id
				+ " and (select count(*) from AB_MOV_MANUTENCAO_DOSIFICADORES where ID_MANUTENCAO =  @ID and INATIVO != 1 ) = "
				+ "(select count(*) from AB_MOV_MANUTENCAO_DOSIFICADORES where ID_MANUTENCAO = @ID and DATA_EXECUCAO is not null and INATIVO != 1) "
				+ "and (select count(*) from AB_MOV_MANUTENCAO_DOSIFICADORES where ID_MANUTENCAO = @ID and INATIVO != 1 ) > 0");

		int dados = query.executeUpdate();

		return dados;
	}

	@GET
	@Path("/getAlertasEncomendasPendentes")
	@Produces("application/json")
	public void getAlertasEncomendasPendentes() {

		Query query = null;
		SendEmail email = new SendEmail();
		String filepath = getFILEPATH();
		ReportGenerator relatorio = new ReportGenerator();
		String nome = null;
		String pasta_destino = "c:/" + filepath + "/relatorios/";
		String nome_relatorio = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		nome_relatorio = "AlertasEncomendasPendentes" + nome_relatorio;

		/*
		 * String url = "EXEC [MERGE_PR_ALERTA_ENCOMENDA_PENDENTE] ";
		 * 
		 * Query query_atualiza = entityManager.createNativeQuery(url);
		 * List<Object[]> dados_t =query_atualiza.getResultList();
		 */

		query = entityManager.createNativeQuery(
				"select LOWER(utinumint) utinumint,'' utinumint2  from PR_ALERTA_ENCOMENDA_PENDENTE GROUP by utinumint");

		List<Object[]> dados = query.getResultList();

		for (Object[] content : dados) {

			try {

				nome = relatorio.relatorio2("pdf", nome_relatorio, null,
						"AlertasEncomendasPendentes/AlertaEncomendaPendente", pasta_destino, filepath, getURL(),
						content[0].toString());

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String email_para = "";
			email_para += content[0].toString();
			System.out.println(content[0].toString());

			verficaEventosEncomendas("10", "Alertas de Encomendas Pendentes", "Interno", "1", email_para,
					pasta_destino + "/" + nome);

		}
	}

	public void verficaEventosEncomendas(String MODULO, String MOMENTO, String PAGINA, String ESTADO, String EMAIL_PARA,
			String ficheiro) {

		Query query3 = entityManager.createQuery("Select a from GER_EVENTOS_CONF a where MODULO = " + MODULO
				+ " and MOMENTO = '" + MOMENTO + "' " + "and PAGINA = '" + PAGINA + "' and ESTADO = " + ESTADO + "");
		List<GER_EVENTOS_CONF> dados = query3.getResultList();

		for (GER_EVENTOS_CONF borderTypes : dados) {

			// System.out.println(borderTypes.getEMAIL_ASSUNTO());
			EMAIL email = new EMAIL();
			email.setDE("alertas.it.doureca@gmail.com");

			String email_para = (EMAIL_PARA != null && !EMAIL_PARA.toString().isEmpty()) ? "," + EMAIL_PARA : "";

			email.setPARA(borderTypes.getEMAIL_PARA() + email_para);
			String mensagem = borderTypes.getEMAIL_MENSAGEM();
			String assunto = borderTypes.getEMAIL_ASSUNTO();

			email.setASSUNTO(assunto);
			email.setMENSAGEM(mensagem);

			sendemail(email, null, ficheiro);
		}
	}

	@GET
	@Path("/getAlertasDerrogacoes")
	@Produces("application/json")
	public void getAlertasDerrogacoes() {

		Query query = null;
		SendEmail email = new SendEmail();

		query = entityManager.createNativeQuery("EXEC [GET_ALERTA_DERROGACOES]");

		List<Object[]> dados = query.getResultList();

		for (Object[] content : dados) {

			String email_para = "", motivo = "", causa = "", data_inicio = "", data_fim = "", numero_derrogacao = "",
					estado = "", cliente = "", referencia = "", link = "";
			email_para = content[0].toString();
			motivo = content[1].toString();
			causa = content[2].toString();
			data_inicio = content[3].toString();
			data_fim = content[4].toString();
			numero_derrogacao = content[5].toString();
			estado = content[6].toString();
			cliente = content[7].toString();
			referencia = content[8].toString();
			link = content[9].toString();

			List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> n = new HashMap<String, String>();
			n.put("MODULO", "5");
			n.put("MOMENTO", "Alertas de Derrogações");

			n.put("PAGINA", "Derrogações");

			n.put("ESTADO", "1");
			n.put("EMAIL_PARA", email_para);

			n.put("DADOS",
					"{estado::" + estado + "\n/link::" + link + numero_derrogacao + "\n/numero_derrogacao::"
							+ numero_derrogacao + "\n/cliente::" + cliente + "\n/referencia::" + referencia
							+ "\n/motivo::" + motivo + "\n/causa::" + causa + "\n/data_inicio::" + data_inicio
							+ "\n/data_fim::" + data_fim + "}");

			data.add(n);

			verficaEventos(data);

		}

	}

}
