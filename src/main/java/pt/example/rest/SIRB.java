package pt.example.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import com.lowagie.text.pdf.codec.Base64;
import com.sun.jersey.core.header.FormDataContentDisposition;

import net.sf.jasperreports.engine.JRException;
import pt.example.bootstrap.ConnectProgress;
import pt.example.bootstrap.ReportGenerator;
import pt.example.bootstrap.SendEmail;
import pt.example.dao.AB_DIC_BANHODao;
import pt.example.dao.AB_DIC_BANHO_ADITIVODao;
import pt.example.dao.AB_DIC_BANHO_COMPONENTEDao;
import pt.example.dao.AB_DIC_COMPONENTEDao;
import pt.example.dao.AB_DIC_LINHADao;
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
import pt.example.dao.AB_MOV_MANUTENCAO_LINHADao;
import pt.example.dao.AB_MOV_REG_PARAM_OPERACAODao;
import pt.example.dao.GER_ANALISESDao;
import pt.example.dao.GER_ARMAZEMDao;
import pt.example.dao.GER_EVENTOS_CONFDao;
import pt.example.dao.GER_FORNECEDORDao;
import pt.example.dao.GER_LOG_EVENTOSDao;
import pt.example.dao.GER_MODULODao;
import pt.example.dao.GER_PARAMETROSDao;
import pt.example.dao.GER_PERFIL_CABDao;
import pt.example.dao.GER_PERFIL_LINDao;
import pt.example.dao.GER_UTILIZADORESDao;
import pt.example.dao.GER_UTZ_PERFILDao;
import pt.example.entity.AB_DIC_BANHO;
import pt.example.entity.AB_DIC_BANHO_ADITIVO;
import pt.example.entity.AB_DIC_BANHO_COMPONENTE;
import pt.example.entity.AB_DIC_COMPONENTE;
import pt.example.entity.AB_DIC_LINHA;
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
import pt.example.entity.AB_MOV_MANUTENCAO_LINHA;
import pt.example.entity.AB_MOV_REG_PARAM_OPERACAO;
import pt.example.entity.EMAIL;
import pt.example.entity.GER_ANALISES;
import pt.example.entity.GER_ARMAZEM;
import pt.example.entity.GER_EVENTOS_CONF;
import pt.example.entity.GER_FORNECEDOR;
import pt.example.entity.GER_LOG_EVENTOS;
import pt.example.entity.GER_MODULO;
import pt.example.entity.GER_PARAMETROS;
import pt.example.entity.GER_PERFIL_CAB;
import pt.example.entity.GER_PERFIL_LIN;
import pt.example.entity.GER_UTILIZADORES;
import pt.example.entity.GER_UTZ_PERFIL;
import pt.example.entity.conf;

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
	@Path("/getAB_MOV_MANUTENCAO/{linha}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getAB_MOV_MANUTENCAO(@PathParam("linha") Integer linha,
			final ArrayList<String> query) {
		return dao8.getall(linha, query);
	}

	@POST
	@Path("/getAB_MOV_MANUTENCAOsorid/{linha}")
	@Consumes("*/*")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO> getAB_MOV_MANUTENCAOsorid(@PathParam("linha") Integer linha,
			final ArrayList<String> query) {
		return dao8.getallsortid(linha, query);
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
	@Path("/getAB_MOV_MANUTENCAO_CABbyid_banho/{idbanho}/{inicio}/{fim}/{id_man}")
	@Produces("application/json")
	public List<AB_MOV_MANUTENCAO_CAB> getAB_MOV_MANUTENCAO_CABbyid_banho(@PathParam("idbanho") Integer idbanho,
			@PathParam("inicio") Integer inicio, @PathParam("fim") Integer fim, @PathParam("id_man") Integer id_man) {
		return dao15.getbyidbanho(idbanho, inicio, fim, id_man);
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

	@GET
	@Path("/getAB_DIC_TIPO_ADICAO")
	@Produces("application/json")
	public List<AB_DIC_TIPO_ADICAO> getAB_DIC_TIPO_ADICAO() {
		return dao16.getall();
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

	@GET
	@Path("/getAB_DIC_TIPO_MANUTENCAO")
	@Produces("application/json")
	public List<AB_DIC_TIPO_MANUTENCAO> getAB_DIC_TIPO_MANUTENCAO() {
		return dao17.getall();
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

	@GET
	@Path("/getAB_DIC_TIPO_OPERACAO")
	@Produces("application/json")
	public List<AB_DIC_TIPO_OPERACAO> getAB_DIC_TIPO_OPERACAO() {
		return dao18.getall();
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

		List<HashMap<String, String>> dados = connectionProgress.getArmazem();
		return dados;
	}

	@GET
	@Path("/getFornecedores")
	@Produces("application/json")
	public List<HashMap<String, String>> getFornecedores() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getFornecedores();
		return dados;
	}

	@GET
	@Path("/getStock/{proref}/{liecod}")
	@Produces("application/json")
	public List<HashMap<String, String>> getStock(@PathParam("proref") String proref,
			@PathParam("liecod") String liecod) throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getStock(proref, liecod);
		return dados;
	}

	@GET
	@Path("/getComponentes")
	@Produces("application/json")
	public List<HashMap<String, String>> getComponentes() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getComponentes();
		return dados;
	}

	@GET
	@Path("/getusers")
	@Produces("application/json")
	public List<HashMap<String, String>> getUsers() throws SQLException, ClassNotFoundException {

		ConnectProgress connectionProgress = new ConnectProgress();

		List<HashMap<String, String>> dados = connectionProgress.getUsers();
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
	public GER_PARAMETROS updateAB_MOV_ANALISE_LINHA(final GER_PARAMETROS GER_PARAMETROS) {
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
		ReportGenerator report = new ReportGenerator();
		try {
			fileName = report.relatorio(format, Name, ID, relatorio);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fileName != null) {
			conf pasta = new conf();
			File file = new File("/" + pasta.nomepasta + "/relatorios/" + fileName);
			ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition", "attachment; filename=report." + format + "");
			return response.build();
		} else {
			return null;
		}

	}

	/* Email ************************************/
	@POST
	@Path("/sendemail")
	@Consumes("*/*")
	@Produces("application/json")
	public EMAIL sendemail(final EMAIL data) {
		System.out.println(data);
		SendEmail send = new SendEmail();
		send.enviarEmail(data.getDE(), data.getPARA(), data.getASSUNTO(), data.getMENSAGEM(), data.getNOME_FICHEIRO());
		return data;

	}
}