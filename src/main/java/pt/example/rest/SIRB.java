package pt.example.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataParam;

import net.sf.jasperreports.engine.JRException;
import pt.example.bootstrap.ConnectProgress;
import pt.example.bootstrap.Printer;
import pt.example.bootstrap.ReportGenerator;
import pt.example.bootstrap.SendEmail;
import pt.example.bootstrap.SomeQuarterlyJob;
import pt.example.dao.AB_DIC_BANHODao;
import pt.example.dao.AB_DIC_BANHO_ADITIVODao;
import pt.example.dao.AB_DIC_BANHO_COMPONENTEDao;
import pt.example.dao.AB_DIC_COMPONENTEDao;
import pt.example.dao.AB_DIC_LINHADao;
import pt.example.dao.AB_DIC_LINHA_OFDao;
import pt.example.dao.AB_DIC_TINADao;
import pt.example.dao.AB_DIC_TIPO_ADICAODao;
import pt.example.dao.AB_DIC_TIPO_MANUTENCAODao;
import pt.example.dao.AB_DIC_TIPO_OPERACAODao;
import pt.example.dao.AB_DIC_TURNODao;
import pt.example.dao.AB_DIC_UNIDADE_MEDIDADao;
import pt.example.dao.AB_DIC_ZONADao;
import pt.example.dao.AB_MOV_ANALISEDao;
import pt.example.dao.AB_MOV_ANALISE_LINHADao;
import pt.example.dao.AB_MOV_MANUTENCAODao;
import pt.example.dao.AB_MOV_MANUTENCAO_CABDao;
import pt.example.dao.AB_MOV_MANUTENCAO_ETIQDao;
import pt.example.dao.AB_MOV_MANUTENCAO_LINHADao;
import pt.example.dao.AB_MOV_REG_PARAM_OPERACAODao;
import pt.example.dao.GER_ANALISESDao;
import pt.example.dao.GER_ARMAZEMDao;
import pt.example.dao.GER_CAMPOS_DISPDao;
import pt.example.dao.GER_EVENTOS_CONFDao;
import pt.example.dao.GER_EVENTOS_PROGRAMADOSDao;
import pt.example.dao.GER_FORNECEDORDao;
import pt.example.dao.GER_LOG_EVENTOSDao;
import pt.example.dao.GER_MODULODao;
import pt.example.dao.GER_PARAMETROSDao;
import pt.example.dao.GER_PERFIL_CABDao;
import pt.example.dao.GER_PERFIL_LINDao;
import pt.example.dao.GER_POSTOSDao;
import pt.example.dao.GER_UTILIZADORESDao;
import pt.example.dao.GER_UTZ_PERFILDao;
import pt.example.dao.GER_VISTASDao;
import pt.example.entity.AB_DIC_BANHO;
import pt.example.entity.AB_DIC_BANHO_ADITIVO;
import pt.example.entity.AB_DIC_BANHO_COMPONENTE;
import pt.example.entity.AB_DIC_COMPONENTE;
import pt.example.entity.AB_DIC_LINHA;
import pt.example.entity.AB_DIC_LINHA_OF;
import pt.example.entity.AB_DIC_TINA;
import pt.example.entity.AB_DIC_TIPO_ADICAO;
import pt.example.entity.AB_DIC_TIPO_MANUTENCAO;
import pt.example.entity.AB_DIC_TIPO_OPERACAO;
import pt.example.entity.AB_DIC_TURNO;
import pt.example.entity.AB_DIC_UNIDADE_MEDIDA;
import pt.example.entity.AB_DIC_ZONA;
import pt.example.entity.AB_MOV_ANALISE;
import pt.example.entity.AB_MOV_ANALISE_LINHA;
import pt.example.entity.AB_MOV_MANUTENCAO;
import pt.example.entity.AB_MOV_MANUTENCAO_CAB;
import pt.example.entity.AB_MOV_MANUTENCAO_ETIQ;
import pt.example.entity.AB_MOV_MANUTENCAO_LINHA;
import pt.example.entity.AB_MOV_REG_PARAM_OPERACAO;
import pt.example.entity.EMAIL;
import pt.example.entity.GER_ANALISES;
import pt.example.entity.GER_ARMAZEM;
import pt.example.entity.GER_CAMPOS_DISP;
import pt.example.entity.GER_EVENTOS_CONF;
import pt.example.entity.GER_EVENTOS_PROGRAMADOS;
import pt.example.entity.GER_FORNECEDOR;
import pt.example.entity.GER_LOG_EVENTOS;
import pt.example.entity.GER_MODULO;
import pt.example.entity.GER_PARAMETROS;
import pt.example.entity.GER_PERFIL_CAB;
import pt.example.entity.GER_PERFIL_LIN;
import pt.example.entity.GER_POSTOS;
import pt.example.entity.GER_UTILIZADORES;
import pt.example.entity.GER_UTZ_PERFIL;
import pt.example.entity.GER_VISTAS;

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

	@GET
	@Path("/getAB_DIC_BANHO_COMPONENTEbyid_banho/{id}")
	@Produces("application/json")
	public List<AB_DIC_BANHO_COMPONENTE> getAB_DIC_BANHO_COMPONENTEbyid_banho(@PathParam("id") Integer id) {
		return dao2.getbyid_banho(id);
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

	@GET
	@Path("/getallAB_MOV_ANALISE/{linha}")
	@Produces("application/json")
	public List<AB_MOV_ANALISE> getallAB_MOV_ANALISE(@PathParam("linha") Integer linha) {
		return dao6.getall(linha);
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
	@Path("/getAB_MOV_MANUTENCAO/{linha}/{classif}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getAB_MOV_MANUTENCAO(@PathParam("linha") Integer linha,
			@PathParam("classif") String classif, final ArrayList<String> query) {
		return dao8.getall(linha, query, classif);
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
	@Path("/getAB_MOV_MANUTENCAOsorid/{linha}/{classif}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getAB_MOV_MANUTENCAOsorid(@PathParam("linha") Integer linha,
			@PathParam("classif") String classif, final ArrayList<String> query) {
		return dao8.getallsortid(linha, query, classif);
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
	@Path("/getDadosEtiquetabyREFcisterna/{ref}")
	@Produces("application/json")
	public List<HashMap<String, String>> getDadosEtiquetabyREFcisterna(@PathParam("ref") String ref)
			throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getDadosEtiquetabyREFcisterna(getURLSILVER(), ref);
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
		return dao35.create(data);
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

		if (evento.getCRIAR_FICHEIRO()) {

			Query query_folder = entityManager.createNativeQuery(evento.getQUERY());

			List<Object[]> dados_folder = query_folder.getResultList();
			String filepath = getFILEPATH();

			for (Object[] content : dados_folder) {
				try {

					String nome = relatorio.relatorio2("pdf", content[1].toString(),
							Integer.parseInt(content[0].toString()), evento.getNOME_RELATORIO(),
							evento.getPASTA_DESTINO(), filepath, getURL());

					if (evento.getANEXA_FICHEIROS() != null && evento.getANEXA_FICHEIROS())
						ficheiros.add(nome);
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
					evento.getEMAIL_MENSAGEM(), ficheiros, evento.getPASTA_DESTINO());
		}
		return data;
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
	@Path("/upload/{nome}/{formato}")
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream, @PathParam("nome") String nome,
			@PathParam("formato") String formato) {

		String uploadedFileLocation = "c://teste/" + nome + '.' + formato;
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();

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
			uploadedInputStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/* FICHEIRO ************************************/
	@GET
	@Path("/get/{format}/{filename}/{id}/{relatorio}")
	// @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response getFile(@PathParam("format") String format, @PathParam("filename") String Name,
			@PathParam("id") Integer ID, @PathParam("relatorio") String relatorio) {
		String fileName = null;
		String filepath = getFILEPATH();
		ReportGenerator report = new ReportGenerator();
		try {
			fileName = report.relatorio(format, Name, ID, relatorio, getURL(), filepath);
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
	public EMAIL sendemail(final EMAIL data, String[] fic) {
		// System.out.println(data.getPARA());
		SendEmail send = new SendEmail();
		send.enviarEmail(data.getDE(), data.getPARA(), data.getASSUNTO(), data.getMENSAGEM(), data.getNOME_FICHEIRO(),
				fic, getFILEPATH());
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

			sendemail(email, keyValuePairs3);
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
				"select top 1  PASTA_FICHEIRO,PASTA_ETIQUETAS,NOME_IMPRESSORA,IP_IMPRESSORA,MODELO_REPORT from GER_PARAMETROS a,GER_POSTOS b where IP_POSTO ='"
						+ ip_posto + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		String nome_ficheiro = "ETIQUETA_NUM_" + ETIQUETA + ".txt";

		for (Object[] content : dados_folder) {
			path2 = content[1].toString() + nome_ficheiro;
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

		criar_ficheiro(data_etiq, path2);
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

		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat horaformate = new SimpleDateFormat("HHmmss");
		String datatual = formate.format(new java.util.Date());
		String horatual = horaformate.format(new java.util.Date());
		String sequencia = "000000000";
		String path = "";
		String path2 = "";
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
				"select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO,a.INDNUMENR,a.EMPCOD,a.PRODES,a.DATCRE"
						+ ",(select ID_MANUTENCAO from AB_MOV_MANUTENCAO_CAB where ID_MANUTENCAO_CAB = b.ID_MANUTENCAO_CAB) as id2 "
						+ ", CASE WHEN ( a.QUANT  - a.QUANT_FINAL) < 0 THEN 0 ELSE ( a.QUANT  - a.QUANT_FINAL) END as qtt , t.CISTERNA "
						+ ",( a.QUANT_FINAL / (CASE WHEN t.FACTOR_CONVERSAO IS NULL  THEN 1 WHEN t.FACTOR_CONVERSAO = 0 THEN 1 ELSE t.FACTOR_CONVERSAO END) ) as qtt2 "
						+ ",(select MEDIDA from AB_DIC_UNIDADE_MEDIDA where ID_MEDIDA = t.ID_UNIDADE_ADITIVO) as unidaditivo "
						+ "from AB_MOV_MANUTENCAO_ETIQ a "
						+ "inner join AB_MOV_MANUTENCAO_LINHA b on a.ID_MANUTENCAO_LIN = b.ID_MANUTENCAO_LIN "
						+ "inner join AB_DIC_COMPONENTE t on  t.ID_COMPONENTE = b.ID_ADITIVO "
						+ "where b.ID_MANUTENCAO_CAB  = " + id + "");

		Query query2 = entityManager.createNativeQuery(
				"select a.ETQNUM,a.QUANT,a.CONSUMIR,a.QUANT_FINAL,a.INDREF,a.VA1REF,a.VA2REF,a.PROREF,a.UNICOD,a.LIECOD,a.ETQORILOT1,a.ETQNUMENR,a.LOTNUMENR,a.UNISTO,a.INDNUMENR,a.EMPCOD,a.PRODES,a.DATCRE "
						+ ",'correcao'+CONVERT(varchar(10), ID_MOV_MANU_ETIQUETA), a.CONSUMIR as cons, '0' as id2,a.QUANT_FINAL as qtdf,a.UNISTO as unnd,a.sinal "
						+ "from AB_MOV_MANUTENCAO_ETIQ a where a.ID_MOV_MANU_ETIQUETA in (" + ids + ")");

		Query query_folder = entityManager.createNativeQuery(
				"select top 1  PASTA_FICHEIRO,PASTA_ETIQUETAS,NOME_IMPRESSORA,IP_IMPRESSORA,MODELO_REPORT from GER_PARAMETROS a,GER_POSTOS b where IP_POSTO ='"
						+ ip_posto + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		for (Object[] content : dados_folder) {
			path = content[0] + nome_ficheiro;
			path2 = content[1].toString();
			nomeimpressora = content[2].toString();
			if (content[3] != null) {
				ipimpressora = content[3].toString();
			}
			modelo_REPORT = content[4].toString();
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
			path2 = path2 + "ETIQUETA" + id;
		} else {
			path2 = path2 + "ETIQUETA_CORRECAO_IMPRE_" + data_path;
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
			String cisterna = (content[20] != null) ? content[20].toString() : "false";
			if (Float.parseFloat(content[3].toString()) != 0 && !content[3].toString().equals(content[1].toString())
					&& !cisterna.equals("true")) {
				// path2 = path2 + data_path + "_" + content[0].toString();

				// criar ficheiro que gera etiquetas
				data_etiq += criaFicheiroEtiqueta(content);
				size_etiq++;
			}
			
			try {
				connectionProgress.EXEC_SINCRO(content[0].toString(),Float.parseFloat(content[3].toString()), getURLSILVER());
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
				String result = String.format("%.4f", content[19]).replace("$", ",");
				String[] parts = result.split(",");
				String part1 = "00000000000";
				String part2 = "0000";
				if (parts.length > 0) {
					if (parts[0] != null) {
						String size = part1 + parts[0];
						part1 = (size).substring(size.length() - 11, size.length());
					}
					if (parts.length > 1) {
						String size = part2 + parts[1];
						part2 = (size).substring(size.length() - 4, size.length());
					}
				}
				data += (part1 + part2 + "  ").substring(0, 17);
			} else {
				data += "000000000000000  ";
			}

			if (id != null) {
				data += "+"; // Signe
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
			criar_ficheiro(data, path);
		}

		if (size_etiq > 0) {
			criar_ficheiro(data_etiq, path2);
		}

		/*new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				try {
					connectionProgress.EXEC_SINCRO("SETQDE", getURLSILVER());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					connectionProgress.EXEC_SINCRO("SOFC", getURLSILVER());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 1000);*/

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
		List<HashMap<String, String>> data = new ArrayList<>();
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

	public void criar_ficheiro(String data, String path) {
		File file2 = new File(path + ".txt");
		if (file2.delete())
			// if file doesnt exists, then create it

			try {
				file2.createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		BufferedWriter bw2 = null;
		FileWriter fw2 = null;
		// true = append file
		try {
			fw2 = new FileWriter(file2.getAbsoluteFile(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (fw2 != null) {
				try {
					fw2.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}

}
