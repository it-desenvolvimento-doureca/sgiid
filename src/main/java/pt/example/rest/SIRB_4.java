package pt.example.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pt.example.dao.QUA_MC_DIC_SECCOESDao;
import pt.example.dao.QUA_MC_DIC_RESP_VALIDODao;
import pt.example.dao.QUA_MC_DIC_TIPO_CALIBRDao;
import pt.example.dao.QUA_MC_DIC_TIPO_ACEITDao;
import pt.example.dao.QUA_MC_DIC_TIPO_VERIF_GABARITDao;
import pt.example.dao.QUA_MC_DIC_RESULT_VALIDDao;
import pt.example.dao.QUA_MC_DIC_PECAS_CROMDao;
import pt.example.dao.QUA_MC_ENTIDADES_CALIBRDao;
import pt.example.dao.QUA_MC_ENTIDADES_CERTIFDao;
import pt.example.dao.QUA_MC_EQUIPAMENTOSDao;
import pt.example.dao.QUA_MC_MOV_CALIB_EQUIPDao;
import pt.example.dao.QUA_MC_MOV_CALIB_EQUIP_DETDao;
import pt.example.dao.QUA_MC_GABARITOSDao;
import pt.example.dao.QUA_MC_MOV_VERIF_GABARITODao;
import pt.example.dao.QUA_MC_MOV_VERIF_INT_GABARITODao;
import pt.example.dao.QUA_MC_MAQUINASDao;
import pt.example.dao.QUA_MC_MOV_VERIF_MAQUINADao;
import pt.example.dao.QUA_MC_SALASDao;
import pt.example.dao.QUA_MC_MOV_VERIF_SALADao;
import pt.example.dao.QUA_MC_DIC_ESTADO_METROLOGICODao;
import pt.example.dao.QUA_MC_DIC_MSADao;
import pt.example.dao.QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROSDao;
import pt.example.dao.QUA_MC_EQUIPAMENTOS_FICHEIROSDao;
import pt.example.dao.QUA_MC_GABARITOS_FICHEIROSDao;
import pt.example.dao.QUA_MC_MOV_CALIB_EQUIP_FICHEIROSDao;
import pt.example.dao.QUA_MC_DERROGACOESDao;
import pt.example.dao.QUA_MC_DERROGACOES_ACOESDao;
import pt.example.dao.QUA_MC_DERROGACOES_FICHEIROSDao_MC;
import pt.example.dao.QUA_MC_DECLARACOES_NCDao;

import pt.example.entity.QUA_MC_DIC_SECCOES;
import pt.example.entity.QUA_MC_DIC_RESP_VALIDACAO;
import pt.example.entity.QUA_MC_DIC_TIPO_CALIBRACAO;
import pt.example.entity.QUA_MC_DIC_TIPO_ACEITACAO;
import pt.example.entity.QUA_MC_DIC_TIPO_VERIF_GABARIT;
import pt.example.entity.QUA_MC_DIC_RESULTADO_VALIDACAO;
import pt.example.entity.QUA_MC_DIC_PECAS_CROMADAS;
import pt.example.entity.QUA_MC_ENTIDADES_CALIBRACAO;
import pt.example.entity.QUA_MC_ENTIDADES_CALIBRACAO_CERTIF;
import pt.example.entity.QUA_MC_EQUIPAMENTOS;
import pt.example.entity.QUA_MC_MOV_CALIB_EQUIP;
import pt.example.entity.QUA_MC_MOV_CALIB_EQUIP_DET;
import pt.example.entity.QUA_MC_GABARITOS;
import pt.example.entity.QUA_MC_MOV_VERIF_GABARITO;
import pt.example.entity.QUA_MC_MOV_VERIF_INT_GABARITO;
import pt.example.entity.QUA_MC_MAQUINAS;
import pt.example.entity.QUA_MC_MOV_VERIF_MAQUINA;
import pt.example.entity.QUA_MC_SALAS;
import pt.example.entity.QUA_MC_MOV_VERIF_SALA;
import pt.example.entity.QUA_MC_DIC_ESTADO_METROLOGICO;
import pt.example.entity.QUA_MC_DIC_MSA;
import pt.example.entity.QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS;
import pt.example.entity.QUA_MC_EQUIPAMENTOS_FICHEIROS;
import pt.example.entity.QUA_MC_GABARITOS_FICHEIROS;
import pt.example.entity.QUA_MC_MOV_CALIB_EQUIP_FICHEIROS;
import pt.example.entity.QUA_MC_DERROGACOES;
import pt.example.entity.QUA_MC_DERROGACOES_ACOES;
import pt.example.entity.QUA_MC_DERROGACOES_FICHEIROS;
import pt.example.entity.QUA_MC_DECLARACOES_NC;

@Stateless
@Path("/sirb")
public class SIRB_4 {

	@Inject private QUA_MC_DIC_SECCOESDao dao1;
	@Inject private QUA_MC_DIC_RESP_VALIDODao dao2;
	@Inject private QUA_MC_DIC_TIPO_CALIBRDao dao3;
	@Inject private QUA_MC_DIC_TIPO_ACEITDao dao4;
	@Inject private QUA_MC_DIC_TIPO_VERIF_GABARITDao dao5;
	@Inject private QUA_MC_DIC_RESULT_VALIDDao dao6;
	@Inject private QUA_MC_DIC_PECAS_CROMDao dao7;
	@Inject private QUA_MC_ENTIDADES_CALIBRDao dao8;
	@Inject private QUA_MC_ENTIDADES_CERTIFDao dao9;
	@Inject private QUA_MC_EQUIPAMENTOSDao dao10;
	@Inject private QUA_MC_MOV_CALIB_EQUIPDao dao11;
	@Inject private QUA_MC_MOV_CALIB_EQUIP_DETDao dao12;
	@Inject private QUA_MC_GABARITOSDao dao13;
	@Inject private QUA_MC_MOV_VERIF_GABARITODao dao14;
	@Inject private QUA_MC_MOV_VERIF_INT_GABARITODao dao15;
	@Inject private QUA_MC_MAQUINASDao dao16;
	@Inject private QUA_MC_MOV_VERIF_MAQUINADao dao17;
	@Inject private QUA_MC_SALASDao dao18;
	@Inject private QUA_MC_MOV_VERIF_SALADao dao19;
	@Inject private QUA_MC_DIC_ESTADO_METROLOGICODao dao20;
	@Inject private QUA_MC_DIC_MSADao dao21;
	@Inject private QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROSDao dao22;
	@Inject private QUA_MC_EQUIPAMENTOS_FICHEIROSDao dao23;
	@Inject private QUA_MC_MOV_CALIB_EQUIP_FICHEIROSDao dao24;
	@Inject private QUA_MC_DERROGACOESDao dao25;
	@Inject private QUA_MC_DERROGACOES_ACOESDao dao26;
	@Inject private QUA_MC_DERROGACOES_FICHEIROSDao_MC dao27;
	@Inject private QUA_MC_DECLARACOES_NCDao dao28;
	@Inject private QUA_MC_GABARITOS_FICHEIROSDao dao29;

	/************************************* QUA_MC_DIC_ESTADO_METROLOGICO */
	@POST
	@Path("/createQUA_MC_DIC_ESTADO_METROLOGICO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_ESTADO_METROLOGICO insertQUA_MC_DIC_ESTADO_METROLOGICO(final QUA_MC_DIC_ESTADO_METROLOGICO data) {
		return dao20.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_ESTADO_METROLOGICO")
	@Produces("application/json")
	public List<QUA_MC_DIC_ESTADO_METROLOGICO> getQUA_MC_DIC_ESTADO_METROLOGICO() {
		return dao20.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_ESTADO_METROLOGICO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_ESTADO_METROLOGICO updateQUA_MC_DIC_ESTADO_METROLOGICO(final QUA_MC_DIC_ESTADO_METROLOGICO data) {
		return dao20.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_ESTADO_METROLOGICO/{id}")
	public void deleteQUA_MC_DIC_ESTADO_METROLOGICO(@PathParam("id") Integer id) {
		QUA_MC_DIC_ESTADO_METROLOGICO e = new QUA_MC_DIC_ESTADO_METROLOGICO();
		e.setID_ESTADO_METROLOGICO(id);
		dao20.delete(e);
	}

	/************************************* QUA_MC_DIC_MSA */
	@POST
	@Path("/createQUA_MC_DIC_MSA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_MSA insertQUA_MC_DIC_MSA(final QUA_MC_DIC_MSA data) {
		return dao21.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_MSA")
	@Produces("application/json")
	public List<QUA_MC_DIC_MSA> getQUA_MC_DIC_MSA() {
		return dao21.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_MSA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_MSA updateQUA_MC_DIC_MSA(final QUA_MC_DIC_MSA data) {
		return dao21.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_MSA/{id}")
	public void deleteQUA_MC_DIC_MSA(@PathParam("id") Integer id) {
		QUA_MC_DIC_MSA e = new QUA_MC_DIC_MSA();
		e.setID_MSA(id);
		dao21.delete(e);
	}

	/************************************* QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS */
	@POST
	@Path("/createQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS insertQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS(final QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS data) {
		return dao22.create(data);
	}

	@GET
	@Path("/getQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROSbyentidade/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROSbyentidade(@PathParam("id") Integer id) {
		return dao22.getbyEntidade(id);
	}

	@GET
	@Path("/getQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROScontent/{id}")
	@Produces("text/plain")
	public String getQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROScontent(@PathParam("id") Integer id) {
		return dao22.getConteudo(id);
	}

	@PUT
	@Path("/updateQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS updateQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS(final QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS data) {
		return dao22.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS/{id}")
	public void deleteQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS(@PathParam("id") Integer id) {
		QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS e = new QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS();
		e.setID(id);
		dao22.delete(e);
	}

	/************************************* QUA_MC_EQUIPAMENTOS_FICHEIROS */
	@POST
	@Path("/createQUA_MC_EQUIPAMENTOS_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_EQUIPAMENTOS_FICHEIROS insertQUA_MC_EQUIPAMENTOS_FICHEIROS(final QUA_MC_EQUIPAMENTOS_FICHEIROS data) {
		return dao23.create(data);
	}

	@GET
	@Path("/getQUA_MC_EQUIPAMENTOS_FICHEIROSbyequipamento/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_EQUIPAMENTOS_FICHEIROSbyequipamento(@PathParam("id") Integer id) {
		return dao23.getbyEquipamento(id);
	}

	@GET
	@Path("/getQUA_MC_EQUIPAMENTOS_FICHEIROScontent/{id}")
	@Produces("text/plain")
	public String getQUA_MC_EQUIPAMENTOS_FICHEIROScontent(@PathParam("id") Integer id) {
		return dao23.getConteudo(id);
	}

	@PUT
	@Path("/updateQUA_MC_EQUIPAMENTOS_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_EQUIPAMENTOS_FICHEIROS updateQUA_MC_EQUIPAMENTOS_FICHEIROS(final QUA_MC_EQUIPAMENTOS_FICHEIROS data) {
		return dao23.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_EQUIPAMENTOS_FICHEIROS/{id}")
	public void deleteQUA_MC_EQUIPAMENTOS_FICHEIROS(@PathParam("id") Integer id) {
		QUA_MC_EQUIPAMENTOS_FICHEIROS e = new QUA_MC_EQUIPAMENTOS_FICHEIROS();
		e.setID(id);
		dao23.delete(e);
	}

	/************************************* QUA_MC_GABARITOS_FICHEIROS */
	@POST
	@Path("/createQUA_MC_GABARITOS_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_GABARITOS_FICHEIROS insertQUA_MC_GABARITOS_FICHEIROS(final QUA_MC_GABARITOS_FICHEIROS data) {
		return dao29.create(data);
	}

	@GET
	@Path("/getQUA_MC_GABARITOS_FICHEIROSbygabarito/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_GABARITOS_FICHEIROSbygabarito(@PathParam("id") Integer id) {
		return dao29.getbyGabarito(id);
	}

	@GET
	@Path("/getQUA_MC_GABARITOS_FICHEIROScontent/{id}")
	@Produces("text/plain")
	public String getQUA_MC_GABARITOS_FICHEIROScontent(@PathParam("id") Integer id) {
		return dao29.getConteudo(id);
	}

	@PUT
	@Path("/updateQUA_MC_GABARITOS_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_GABARITOS_FICHEIROS updateQUA_MC_GABARITOS_FICHEIROS(final QUA_MC_GABARITOS_FICHEIROS data) {
		return dao29.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_GABARITOS_FICHEIROS/{id}")
	public void deleteQUA_MC_GABARITOS_FICHEIROS(@PathParam("id") Integer id) {
		QUA_MC_GABARITOS_FICHEIROS e = new QUA_MC_GABARITOS_FICHEIROS();
		e.setID(id);
		dao29.delete(e);
	}

	/************************************* QUA_MC_MOV_CALIB_EQUIP_FICHEIROS */
	@POST
	@Path("/createQUA_MC_MOV_CALIB_EQUIP_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP_FICHEIROS insertQUA_MC_MOV_CALIB_EQUIP_FICHEIROS(final QUA_MC_MOV_CALIB_EQUIP_FICHEIROS data) {
		return dao24.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_CALIB_EQUIP_FICHEIROSbycalibdet/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_CALIB_EQUIP_FICHEIROS> getQUA_MC_MOV_CALIB_EQUIP_FICHEIROSbycalibdet(@PathParam("id") Integer id) {
		return dao24.getbyCalibDet(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_CALIB_EQUIP_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP_FICHEIROS updateQUA_MC_MOV_CALIB_EQUIP_FICHEIROS(final QUA_MC_MOV_CALIB_EQUIP_FICHEIROS data) {
		return dao24.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_CALIB_EQUIP_FICHEIROS/{id}")
	public void deleteQUA_MC_MOV_CALIB_EQUIP_FICHEIROS(@PathParam("id") Integer id) {
		QUA_MC_MOV_CALIB_EQUIP_FICHEIROS e = new QUA_MC_MOV_CALIB_EQUIP_FICHEIROS();
		e.setID(id);
		dao24.delete(e);
	}

	/************************************* QUA_MC_DERROGACOES */
	@POST
	@Path("/createQUA_MC_DERROGACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES insertQUA_MC_DERROGACOES(final QUA_MC_DERROGACOES data) {
		return dao25.create(data);
	}

	@GET
	@Path("/getQUA_MC_DERROGACOES")
	@Produces("application/json")
	public List<QUA_MC_DERROGACOES> getQUA_MC_DERROGACOES() {
		return dao25.getall();
	}

	@GET
	@Path("/getQUA_MC_DERROGACOESbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_DERROGACOES> getQUA_MC_DERROGACOESbyid(@PathParam("id") Integer id) {
		return dao25.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_DERROGACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES updateQUA_MC_DERROGACOES(final QUA_MC_DERROGACOES data) {
		return dao25.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DERROGACOES/{id}")
	public void deleteQUA_MC_DERROGACOES(@PathParam("id") Integer id) {
		QUA_MC_DERROGACOES e = new QUA_MC_DERROGACOES();
		e.setID_DERROGACAO(id);
		dao25.delete(e);
	}

	/************************************* QUA_MC_DERROGACOES_ACOES */
	@POST
	@Path("/createQUA_MC_DERROGACOES_ACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES_ACOES insertQUA_MC_DERROGACOES_ACOES(final QUA_MC_DERROGACOES_ACOES data) {
		return dao26.create(data);
	}

	@GET
	@Path("/getQUA_MC_DERROGACOES_ACOESbyderrogacao/{id}")
	@Produces("application/json")
	public List<QUA_MC_DERROGACOES_ACOES> getQUA_MC_DERROGACOES_ACOESbyderrogacao(@PathParam("id") Integer id) {
		return dao26.getbyDerrogacao(id);
	}

	@PUT
	@Path("/updateQUA_MC_DERROGACOES_ACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES_ACOES updateQUA_MC_DERROGACOES_ACOES(final QUA_MC_DERROGACOES_ACOES data) {
		return dao26.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DERROGACOES_ACOES/{id}")
	public void deleteQUA_MC_DERROGACOES_ACOES(@PathParam("id") Integer id) {
		QUA_MC_DERROGACOES_ACOES e = new QUA_MC_DERROGACOES_ACOES();
		e.setID(id);
		dao26.delete(e);
	}

	/************************************* QUA_MC_DERROGACOES_FICHEIROS */
	@POST
	@Path("/createQUA_MC_DERROGACOES_FICHEIROS_MC")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES_FICHEIROS insertQUA_MC_DERROGACOES_FICHEIROS_MC(final QUA_MC_DERROGACOES_FICHEIROS data) {
		return dao27.create(data);
	}

	@GET
	@Path("/getQUA_MC_DERROGACOES_FICHEIROSbyderrogacao/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_DERROGACOES_FICHEIROSbyderrogacao(@PathParam("id") Integer id) {
		return dao27.getbyDerrogacao(id);
	}

	@GET
	@Path("/getQUA_MC_DERROGACOES_FICHEIROScontent/{id}")
	@Produces("text/plain")
	public String getQUA_MC_DERROGACOES_FICHEIROScontent(@PathParam("id") Integer id) {
		return dao27.getConteudo(id);
	}

	@PUT
	@Path("/updateQUA_MC_DERROGACOES_FICHEIROS_MC")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES_FICHEIROS updateQUA_MC_DERROGACOES_FICHEIROS_MC(final QUA_MC_DERROGACOES_FICHEIROS data) {
		return dao27.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DERROGACOES_FICHEIROS_MC/{id}")
	public void deleteQUA_MC_DERROGACOES_FICHEIROS_MC(@PathParam("id") Integer id) {
		QUA_MC_DERROGACOES_FICHEIROS e = new QUA_MC_DERROGACOES_FICHEIROS();
		e.setID(id);
		dao27.delete(e);
	}

	/************************************* QUA_MC_DECLARACOES_NC */
	@POST
	@Path("/createQUA_MC_DECLARACOES_NC")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DECLARACOES_NC insertQUA_MC_DECLARACOES_NC(final QUA_MC_DECLARACOES_NC data) {
		return dao28.create(data);
	}

	@GET
	@Path("/getQUA_MC_DECLARACOES_NC")
	@Produces("application/json")
	public List<QUA_MC_DECLARACOES_NC> getQUA_MC_DECLARACOES_NC() {
		return dao28.getall();
	}

	@GET
	@Path("/getQUA_MC_DECLARACOES_NCbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_DECLARACOES_NC> getQUA_MC_DECLARACOES_NCbyid(@PathParam("id") Integer id) {
		return dao28.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_DECLARACOES_NC")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DECLARACOES_NC updateQUA_MC_DECLARACOES_NC(final QUA_MC_DECLARACOES_NC data) {
		return dao28.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DECLARACOES_NC/{id}")
	public void deleteQUA_MC_DECLARACOES_NC(@PathParam("id") Integer id) {
		QUA_MC_DECLARACOES_NC e = new QUA_MC_DECLARACOES_NC();
		e.setID_DECLARACAO(id);
		dao28.delete(e);
	}

	/************************************* QUA_MC_DIC_SECCOES */
	@POST
	@Path("/createQUA_MC_DIC_SECCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_SECCOES insertQUA_MC_DIC_SECCOES(final QUA_MC_DIC_SECCOES data) {
		return dao1.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_SECCOES")
	@Produces("application/json")
	public List<QUA_MC_DIC_SECCOES> getQUA_MC_DIC_SECCOES() {
		return dao1.getall();
	}

	@GET
	@Path("/getQUA_MC_DIC_SECCOESbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_DIC_SECCOES> getQUA_MC_DIC_SECCOESbyid(@PathParam("id") Integer id) {
		return dao1.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_DIC_SECCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_SECCOES updateQUA_MC_DIC_SECCOES(final QUA_MC_DIC_SECCOES data) {
		return dao1.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_SECCOES/{id}")
	public void deleteQUA_MC_DIC_SECCOES(@PathParam("id") Integer id) {
		QUA_MC_DIC_SECCOES e = new QUA_MC_DIC_SECCOES();
		e.setID_SECCAO(id);
		dao1.delete(e);
	}

	/************************************* QUA_MC_DIC_RESP_VALIDACAO */
	@POST
	@Path("/createQUA_MC_DIC_RESP_VALIDACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_RESP_VALIDACAO insertQUA_MC_DIC_RESP_VALIDACAO(final QUA_MC_DIC_RESP_VALIDACAO data) {
		return dao2.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_RESP_VALIDACAO")
	@Produces("application/json")
	public List<QUA_MC_DIC_RESP_VALIDACAO> getQUA_MC_DIC_RESP_VALIDACAO() {
		return dao2.getall();
	}

	@GET
	@Path("/getQUA_MC_DIC_RESP_VALIDObyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_DIC_RESP_VALIDACAO> getQUA_MC_DIC_RESP_VALIDObyid(@PathParam("id") Integer id) {
		return dao2.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_DIC_RESP_VALIDACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_RESP_VALIDACAO updateQUA_MC_DIC_RESP_VALIDACAO(final QUA_MC_DIC_RESP_VALIDACAO data) {
		return dao2.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_RESP_VALIDACAO/{id}")
	public void deleteQUA_MC_DIC_RESP_VALIDACAO(@PathParam("id") Integer id) {
		QUA_MC_DIC_RESP_VALIDACAO e = new QUA_MC_DIC_RESP_VALIDACAO();
		e.setID_RESP_VALIDACAO(id);
		dao2.delete(e);
	}

	/************************************* QUA_MC_DIC_TIPO_CALIBRACAO */
	@POST
	@Path("/createQUA_MC_DIC_TIPO_CALIBRACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_CALIBRACAO insertQUA_MC_DIC_TIPO_CALIBRACAO(final QUA_MC_DIC_TIPO_CALIBRACAO data) {
		return dao3.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_TIPO_CALIBRACAO")
	@Produces("application/json")
	public List<QUA_MC_DIC_TIPO_CALIBRACAO> getQUA_MC_DIC_TIPO_CALIBRACAO() {
		return dao3.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_TIPO_CALIBRACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_CALIBRACAO updateQUA_MC_DIC_TIPO_CALIBRACAO(final QUA_MC_DIC_TIPO_CALIBRACAO data) {
		return dao3.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_TIPO_CALIBRACAO/{id}")
	public void deleteQUA_MC_DIC_TIPO_CALIBRACAO(@PathParam("id") Integer id) {
		QUA_MC_DIC_TIPO_CALIBRACAO e = new QUA_MC_DIC_TIPO_CALIBRACAO();
		e.setID_TIPO_CALIBRACAO(id);
		dao3.delete(e);
	}

	/************************************* QUA_MC_DIC_TIPO_ACEITACAO */
	@POST
	@Path("/createQUA_MC_DIC_TIPO_ACEITACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_ACEITACAO insertQUA_MC_DIC_TIPO_ACEITACAO(final QUA_MC_DIC_TIPO_ACEITACAO data) {
		return dao4.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_TIPO_ACEITACAO")
	@Produces("application/json")
	public List<QUA_MC_DIC_TIPO_ACEITACAO> getQUA_MC_DIC_TIPO_ACEITACAO() {
		return dao4.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_TIPO_ACEITACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_ACEITACAO updateQUA_MC_DIC_TIPO_ACEITACAO(final QUA_MC_DIC_TIPO_ACEITACAO data) {
		return dao4.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_TIPO_ACEITACAO/{id}")
	public void deleteQUA_MC_DIC_TIPO_ACEITACAO(@PathParam("id") Integer id) {
		QUA_MC_DIC_TIPO_ACEITACAO e = new QUA_MC_DIC_TIPO_ACEITACAO();
		e.setID_TIPO_ACEITACAO(id);
		dao4.delete(e);
	}

	/************************************* QUA_MC_DIC_TIPO_VERIF_GABARIT */
	@POST
	@Path("/createQUA_MC_DIC_TIPO_VERIF_GABARIT")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_VERIF_GABARIT insertQUA_MC_DIC_TIPO_VERIF_GABARIT(final QUA_MC_DIC_TIPO_VERIF_GABARIT data) {
		return dao5.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_TIPO_VERIF_GABARIT")
	@Produces("application/json")
	public List<QUA_MC_DIC_TIPO_VERIF_GABARIT> getQUA_MC_DIC_TIPO_VERIF_GABARIT() {
		return dao5.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_TIPO_VERIF_GABARIT")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_VERIF_GABARIT updateQUA_MC_DIC_TIPO_VERIF_GABARIT(final QUA_MC_DIC_TIPO_VERIF_GABARIT data) {
		return dao5.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_TIPO_VERIF_GABARIT/{id}")
	public void deleteQUA_MC_DIC_TIPO_VERIF_GABARIT(@PathParam("id") Integer id) {
		QUA_MC_DIC_TIPO_VERIF_GABARIT e = new QUA_MC_DIC_TIPO_VERIF_GABARIT();
		e.setID_TIPO_VERIF_GABARIT(id);
		dao5.delete(e);
	}

	/************************************* QUA_MC_DIC_RESULTADO_VALIDACAO */
	@POST
	@Path("/createQUA_MC_DIC_RESULTADO_VALIDACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_RESULTADO_VALIDACAO insertQUA_MC_DIC_RESULTADO_VALIDACAO(final QUA_MC_DIC_RESULTADO_VALIDACAO data) {
		return dao6.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_RESULTADO_VALIDACAO")
	@Produces("application/json")
	public List<QUA_MC_DIC_RESULTADO_VALIDACAO> getQUA_MC_DIC_RESULTADO_VALIDACAO() {
		return dao6.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_RESULTADO_VALIDACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_RESULTADO_VALIDACAO updateQUA_MC_DIC_RESULTADO_VALIDACAO(final QUA_MC_DIC_RESULTADO_VALIDACAO data) {
		return dao6.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_RESULTADO_VALIDACAO/{id}")
	public void deleteQUA_MC_DIC_RESULTADO_VALIDACAO(@PathParam("id") Integer id) {
		QUA_MC_DIC_RESULTADO_VALIDACAO e = new QUA_MC_DIC_RESULTADO_VALIDACAO();
		e.setID_RESULTADO_VALIDACAO(id);
		dao6.delete(e);
	}

	/************************************* QUA_MC_DIC_PECAS_CROMADAS */
	@POST
	@Path("/createQUA_MC_DIC_PECAS_CROMADAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_PECAS_CROMADAS insertQUA_MC_DIC_PECAS_CROMADAS(final QUA_MC_DIC_PECAS_CROMADAS data) {
		return dao7.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_PECAS_CROMADAS")
	@Produces("application/json")
	public List<QUA_MC_DIC_PECAS_CROMADAS> getQUA_MC_DIC_PECAS_CROMADAS() {
		return dao7.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_PECAS_CROMADAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_PECAS_CROMADAS updateQUA_MC_DIC_PECAS_CROMADAS(final QUA_MC_DIC_PECAS_CROMADAS data) {
		return dao7.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_PECAS_CROMADAS/{id}")
	public void deleteQUA_MC_DIC_PECAS_CROMADAS(@PathParam("id") Integer id) {
		QUA_MC_DIC_PECAS_CROMADAS e = new QUA_MC_DIC_PECAS_CROMADAS();
		e.setID_PECA_CROMADA(id);
		dao7.delete(e);
	}

	/************************************* QUA_MC_ENTIDADES_CALIBRACAO */
	@POST
	@Path("/createQUA_MC_ENTIDADES_CALIBRACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO insertQUA_MC_ENTIDADES_CALIBRACAO(final QUA_MC_ENTIDADES_CALIBRACAO data) {
		return dao8.create(data);
	}

	@GET
	@Path("/getQUA_MC_ENTIDADES_CALIBRACAO")
	@Produces("application/json")
	public List<QUA_MC_ENTIDADES_CALIBRACAO> getQUA_MC_ENTIDADES_CALIBRACAO() {
		return dao8.getall();
	}

	@GET
	@Path("/getQUA_MC_ENTIDADES_CALIBRbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_ENTIDADES_CALIBRACAO> getQUA_MC_ENTIDADES_CALIBRbyid(@PathParam("id") Integer id) {
		return dao8.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_ENTIDADES_CALIBRACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO updateQUA_MC_ENTIDADES_CALIBRACAO(final QUA_MC_ENTIDADES_CALIBRACAO data) {
		return dao8.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_ENTIDADES_CALIBRACAO/{id}")
	public void deleteQUA_MC_ENTIDADES_CALIBRACAO(@PathParam("id") Integer id) {
		QUA_MC_ENTIDADES_CALIBRACAO e = new QUA_MC_ENTIDADES_CALIBRACAO();
		e.setID_ENTIDADE_CALIBRACAO(id);
		dao8.delete(e);
	}

	/************************************* QUA_MC_ENTIDADES_CALIBRACAO_CERTIF */
	@POST
	@Path("/createQUA_MC_ENTIDADES_CALIBRACAO_CERTIF")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO_CERTIF insertQUA_MC_ENTIDADES_CALIBRACAO_CERTIF(final QUA_MC_ENTIDADES_CALIBRACAO_CERTIF data) {
		return dao9.create(data);
	}

	@GET
	@Path("/getQUA_MC_ENTIDADES_CALIBRACAO_CERTIFbyEntidade/{id}")
	@Produces("application/json")
	public List<QUA_MC_ENTIDADES_CALIBRACAO_CERTIF> getQUA_MC_ENTIDADES_CALIBRACAO_CERTIFbyEntidade(@PathParam("id") Integer id) {
		return dao9.getbyEntidade(id);
	}

	@PUT
	@Path("/updateQUA_MC_ENTIDADES_CALIBRACAO_CERTIF")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO_CERTIF updateQUA_MC_ENTIDADES_CALIBRACAO_CERTIF(final QUA_MC_ENTIDADES_CALIBRACAO_CERTIF data) {
		return dao9.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_ENTIDADES_CALIBRACAO_CERTIF/{id}")
	public void deleteQUA_MC_ENTIDADES_CALIBRACAO_CERTIF(@PathParam("id") Integer id) {
		QUA_MC_ENTIDADES_CALIBRACAO_CERTIF e = new QUA_MC_ENTIDADES_CALIBRACAO_CERTIF();
		e.setID_CERTIF(id);
		dao9.delete(e);
	}

	/************************************* QUA_MC_EQUIPAMENTOS */
	@POST
	@Path("/createQUA_MC_EQUIPAMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_EQUIPAMENTOS insertQUA_MC_EQUIPAMENTOS(final QUA_MC_EQUIPAMENTOS data) {
		return dao10.create(data);
	}

	@GET
	@Path("/getQUA_MC_EQUIPAMENTOS")
	@Produces("application/json")
	public List<QUA_MC_EQUIPAMENTOS> getQUA_MC_EQUIPAMENTOS() {
		return dao10.getall();
	}

	@GET
	@Path("/getQUA_MC_EQUIPAMENTOS_LISTA")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_EQUIPAMENTOS_LISTA() {
		return dao10.getlista();
	}

	@GET
	@Path("/getQUA_MC_EQUIPAMENTOSbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_EQUIPAMENTOS> getQUA_MC_EQUIPAMENTOSbyid(@PathParam("id") Integer id) {
		return dao10.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_EQUIPAMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_EQUIPAMENTOS updateQUA_MC_EQUIPAMENTOS(final QUA_MC_EQUIPAMENTOS data) {
		return dao10.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_EQUIPAMENTOS/{id}")
	public void deleteQUA_MC_EQUIPAMENTOS(@PathParam("id") Integer id) {
		QUA_MC_EQUIPAMENTOS e = new QUA_MC_EQUIPAMENTOS();
		e.setID_EQUIPAMENTO(id);
		dao10.delete(e);
	}

	/************************************* QUA_MC_MOV_CALIB_EQUIP */
	@POST
	@Path("/createQUA_MC_MOV_CALIB_EQUIP")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP insertQUA_MC_MOV_CALIB_EQUIP(final QUA_MC_MOV_CALIB_EQUIP data) {
		return dao11.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_CALIB_EQUIPbyEquipamento/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_CALIB_EQUIP> getQUA_MC_MOV_CALIB_EQUIPbyEquipamento(@PathParam("id") Integer id) {
		return dao11.getbyEquipamento(id);
	}

	@GET
	@Path("/getQUA_MC_MOV_CALIB_EQUIPbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_CALIB_EQUIP> getQUA_MC_MOV_CALIB_EQUIPbyid(@PathParam("id") Integer id) {
		return dao11.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_CALIB_EQUIP")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP updateQUA_MC_MOV_CALIB_EQUIP(final QUA_MC_MOV_CALIB_EQUIP data) {
		return dao11.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_CALIB_EQUIP/{id}")
	public void deleteQUA_MC_MOV_CALIB_EQUIP(@PathParam("id") Integer id) {
		QUA_MC_MOV_CALIB_EQUIP e = new QUA_MC_MOV_CALIB_EQUIP();
		e.setID_CALIB_EQUIP(id);
		dao11.delete(e);
	}

	/************************************* QUA_MC_MOV_CALIB_EQUIP_DET */
	@POST
	@Path("/createQUA_MC_MOV_CALIB_EQUIP_DET")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP_DET insertQUA_MC_MOV_CALIB_EQUIP_DET(final QUA_MC_MOV_CALIB_EQUIP_DET data) {
		return dao12.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_CALIB_EQUIP_DETbyCalibEquip/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_CALIB_EQUIP_DET> getQUA_MC_MOV_CALIB_EQUIP_DETbyCalibEquip(@PathParam("id") Integer id) {
		return dao12.getbyCalibEquip(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_CALIB_EQUIP_DET")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP_DET updateQUA_MC_MOV_CALIB_EQUIP_DET(final QUA_MC_MOV_CALIB_EQUIP_DET data) {
		return dao12.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_CALIB_EQUIP_DET/{id}")
	public void deleteQUA_MC_MOV_CALIB_EQUIP_DET(@PathParam("id") Integer id) {
		QUA_MC_MOV_CALIB_EQUIP_DET e = new QUA_MC_MOV_CALIB_EQUIP_DET();
		e.setID_CALIB_EQUIP_DET(id);
		dao12.delete(e);
	}

	/************************************* QUA_MC_GABARITOS */
	@POST
	@Path("/createQUA_MC_GABARITOS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_GABARITOS insertQUA_MC_GABARITOS(final QUA_MC_GABARITOS data) {
		return dao13.create(data);
	}

	@GET
	@Path("/getQUA_MC_GABARITOS")
	@Produces("application/json")
	public List<QUA_MC_GABARITOS> getQUA_MC_GABARITOS() {
		return dao13.getall();
	}

	@GET
	@Path("/getQUA_MC_GABARITOS_LISTA")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_GABARITOS_LISTA() {
		return dao13.getlista();
	}

	@GET
	@Path("/getQUA_MC_GABARITOSbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_GABARITOS> getQUA_MC_GABARITOSbyid(@PathParam("id") Integer id) {
		return dao13.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_GABARITOS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_GABARITOS updateQUA_MC_GABARITOS(final QUA_MC_GABARITOS data) {
		return dao13.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_GABARITOS/{id}")
	public void deleteQUA_MC_GABARITOS(@PathParam("id") Integer id) {
		QUA_MC_GABARITOS e = new QUA_MC_GABARITOS();
		e.setID_GABARITO(id);
		dao13.delete(e);
	}

	/************************************* QUA_MC_MOV_VERIF_GABARITO */
	@POST
	@Path("/createQUA_MC_MOV_VERIF_GABARITO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_GABARITO insertQUA_MC_MOV_VERIF_GABARITO(final QUA_MC_MOV_VERIF_GABARITO data) {
		return dao14.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_VERIF_GABARITObyGabarito/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_VERIF_GABARITO> getQUA_MC_MOV_VERIF_GABARITObyGabarito(@PathParam("id") Integer id) {
		return dao14.getbyGabarito(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_VERIF_GABARITO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_GABARITO updateQUA_MC_MOV_VERIF_GABARITO(final QUA_MC_MOV_VERIF_GABARITO data) {
		return dao14.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_VERIF_GABARITO/{id}")
	public void deleteQUA_MC_MOV_VERIF_GABARITO(@PathParam("id") Integer id) {
		QUA_MC_MOV_VERIF_GABARITO e = new QUA_MC_MOV_VERIF_GABARITO();
		e.setID_VERIF_GABARITO(id);
		dao14.delete(e);
	}

	/************************************* QUA_MC_MOV_VERIF_INT_GABARITO */
	@POST
	@Path("/createQUA_MC_MOV_VERIF_INT_GABARITO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_INT_GABARITO insertQUA_MC_MOV_VERIF_INT_GABARITO(final QUA_MC_MOV_VERIF_INT_GABARITO data) {
		return dao15.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_VERIF_INT_GABARITObyVerif/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_VERIF_INT_GABARITO> getQUA_MC_MOV_VERIF_INT_GABARITObyVerif(@PathParam("id") Integer id) {
		return dao15.getbyVerifGabarito(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_VERIF_INT_GABARITO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_INT_GABARITO updateQUA_MC_MOV_VERIF_INT_GABARITO(final QUA_MC_MOV_VERIF_INT_GABARITO data) {
		return dao15.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_VERIF_INT_GABARITO/{id}")
	public void deleteQUA_MC_MOV_VERIF_INT_GABARITO(@PathParam("id") Integer id) {
		QUA_MC_MOV_VERIF_INT_GABARITO e = new QUA_MC_MOV_VERIF_INT_GABARITO();
		e.setID_VERIF_INT_GABARITO(id);
		dao15.delete(e);
	}

	/************************************* QUA_MC_MAQUINAS */
	@POST
	@Path("/createQUA_MC_MAQUINAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MAQUINAS insertQUA_MC_MAQUINAS(final QUA_MC_MAQUINAS data) {
		return dao16.create(data);
	}

	@GET
	@Path("/getQUA_MC_MAQUINAS")
	@Produces("application/json")
	public List<QUA_MC_MAQUINAS> getQUA_MC_MAQUINAS() {
		return dao16.getall();
	}

	@GET
	@Path("/getQUA_MC_MAQUINASbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_MAQUINAS> getQUA_MC_MAQUINASbyid(@PathParam("id") Integer id) {
		return dao16.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_MAQUINAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MAQUINAS updateQUA_MC_MAQUINAS(final QUA_MC_MAQUINAS data) {
		return dao16.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MAQUINAS/{id}")
	public void deleteQUA_MC_MAQUINAS(@PathParam("id") Integer id) {
		QUA_MC_MAQUINAS e = new QUA_MC_MAQUINAS();
		e.setID_MAQUINA(id);
		dao16.delete(e);
	}

	/************************************* QUA_MC_MOV_VERIF_MAQUINA */
	@POST
	@Path("/createQUA_MC_MOV_VERIF_MAQUINA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_MAQUINA insertQUA_MC_MOV_VERIF_MAQUINA(final QUA_MC_MOV_VERIF_MAQUINA data) {
		return dao17.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_VERIF_MAQUINAbyMaquina/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_VERIF_MAQUINA> getQUA_MC_MOV_VERIF_MAQUINAbyMaquina(@PathParam("id") Integer id) {
		return dao17.getbyMaquina(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_VERIF_MAQUINA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_MAQUINA updateQUA_MC_MOV_VERIF_MAQUINA(final QUA_MC_MOV_VERIF_MAQUINA data) {
		return dao17.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_VERIF_MAQUINA/{id}")
	public void deleteQUA_MC_MOV_VERIF_MAQUINA(@PathParam("id") Integer id) {
		QUA_MC_MOV_VERIF_MAQUINA e = new QUA_MC_MOV_VERIF_MAQUINA();
		e.setID_VERIF_MAQUINA(id);
		dao17.delete(e);
	}

	/************************************* QUA_MC_SALAS */
	@POST
	@Path("/createQUA_MC_SALAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_SALAS insertQUA_MC_SALAS(final QUA_MC_SALAS data) {
		return dao18.create(data);
	}

	@GET
	@Path("/getQUA_MC_SALAS")
	@Produces("application/json")
	public List<QUA_MC_SALAS> getQUA_MC_SALAS() {
		return dao18.getall();
	}

	@GET
	@Path("/getQUA_MC_SALASbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_SALAS> getQUA_MC_SALASbyid(@PathParam("id") Integer id) {
		return dao18.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_SALAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_SALAS updateQUA_MC_SALAS(final QUA_MC_SALAS data) {
		return dao18.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_SALAS/{id}")
	public void deleteQUA_MC_SALAS(@PathParam("id") Integer id) {
		QUA_MC_SALAS e = new QUA_MC_SALAS();
		e.setID_SALA(id);
		dao18.delete(e);
	}

	/************************************* QUA_MC_MOV_VERIF_SALA */
	@POST
	@Path("/createQUA_MC_MOV_VERIF_SALA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_SALA insertQUA_MC_MOV_VERIF_SALA(final QUA_MC_MOV_VERIF_SALA data) {
		return dao19.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_VERIF_SALAbySala/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_VERIF_SALA> getQUA_MC_MOV_VERIF_SALAbySala(@PathParam("id") Integer id) {
		return dao19.getBySala(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_VERIF_SALA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_SALA updateQUA_MC_MOV_VERIF_SALA(final QUA_MC_MOV_VERIF_SALA data) {
		return dao19.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_VERIF_SALA/{id}")
	public void deleteQUA_MC_MOV_VERIF_SALA(@PathParam("id") Integer id) {
		QUA_MC_MOV_VERIF_SALA e = new QUA_MC_MOV_VERIF_SALA();
		e.setID_VERIF_SALA(id);
		dao19.delete(e);
	}
}
