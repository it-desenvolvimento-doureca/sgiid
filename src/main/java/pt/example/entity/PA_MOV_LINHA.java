package pt.example.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PA_MOV_LINHA")
public class PA_MOV_LINHA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLANO_LINHA")
	private Integer ID_PLANO_LINHA;
	@Column(name = "ID_ACCAO")
	private Integer ID_ACCAO;
	@Column(name = "ID_PLANO_CAB")
	private Integer ID_PLANO_CAB;
	@Column(name = "RESPONSAVEL")
	private Integer RESPONSAVEL;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "DEPARTAMENTO")
	private Integer DEPARTAMENTO;
	@Column(name = "DATA_ACCAO")
	private Date DATA_ACCAO;
	@Column(name = "HORA_ACCAO")
	private Time HORA_ACCAO;

	@Column(name = "FASTRESPONSE")
	private Boolean FASTRESPONSE;
	@Column(name = "PRIORIDADE")
	private Integer PRIORIDADE;
	@Column(name = "ESTADO")
	private String ESTADO;

	@Column(name = "DATA_CONTROLADO")
	private Timestamp DATA_CONTROLADO;
	@Column(name = "DATA_APROVADO")
	private Timestamp DATA_APROVADO;
	@Column(name = "DATA_CANCELADO")
	private Timestamp DATA_CANCELADO;

	@Column(name = "UTZ_CONTROLADO")
	private Integer UTZ_CONTROLADO;
	@Column(name = "UTZ_APROVADO")
	private Integer UTZ_APROVADO;
	@Column(name = "UTZ_CANCELADO")
	private Integer UTZ_CANCELADO;

	@Column(name = "TIPO_ACAO")
	private Integer TIPO_ACAO;
	@Column(name = "ITEM")
	private String ITEM;

	@Column(name = "UNIDADE")
	private Integer UNIDADE;
	@Column(name = "REFERENCIA")
	private String REFERENCIA;
	@Column(name = "DESIGN_REFERENCIA")
	private String DESIGN_REFERENCIA;
	@Column(name = "CAUSA")
	private String CAUSA;
	@Column(name = "INVESTIMENTOS")
	private String INVESTIMENTOS;
	@Column(name = "EFICACIA_CUMPRIMENTO_OBJETIVO")
	private String EFICACIA_CUMPRIMENTO_OBJETIVO;
	@Column(name = "SEGUIR_LINHA")
	private Boolean SEGUIR_LINHA;
	@Column(name = "OBJETIVO")
	private String OBJETIVO;
	
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "ORIGEM")
	private String ORIGEM;

	@Column(name = "ORDENACAO")
	private String ORDENACAO;
	
	@Column(name = "DATA_ACCAO_ORIGINAL")
	private Date DATA_ACCAO_ORIGINAL;
	@Column(name = "HORA_ACCAO_ORIGINAL")
	private Time HORA_ACCAO_ORIGINAL;
	
	@Column(name = "JUSTIFICACAO")
	private String JUSTIFICACAO;
	
	@Column(name = "DATA_INICIO_ACCAO")
	private Date DATA_INICIO_ACCAO;
	@Column(name = "HORA_INICIO_ACCAO")
	private Time HORA_INICIO_ACCAO;
	
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public Integer getID_PLANO_LINHA() {
		return ID_PLANO_LINHA;
	}

	public Integer getID_ACCAO() {
		return ID_ACCAO;
	}

	public Integer getID_PLANO_CAB() {
		return ID_PLANO_CAB;
	}

	public Integer getRESPONSAVEL() {
		return RESPONSAVEL;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public Integer getDEPARTAMENTO() {
		return DEPARTAMENTO;
	}

	public Date getDATA_ACCAO() {
		return DATA_ACCAO;
	}

	public Time getHORA_ACCAO() {
		return HORA_ACCAO;
	}

	public void setID_PLANO_LINHA(Integer iD_PLANO_LINHA) {
		ID_PLANO_LINHA = iD_PLANO_LINHA;
	}

	public void setID_ACCAO(Integer iD_ACCAO) {
		ID_ACCAO = iD_ACCAO;
	}

	public void setID_PLANO_CAB(Integer iD_PLANO_CAB) {
		ID_PLANO_CAB = iD_PLANO_CAB;
	}

	public void setRESPONSAVEL(Integer rESPONSAVEL) {
		RESPONSAVEL = rESPONSAVEL;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setDEPARTAMENTO(Integer dEPARTAMENTO) {
		DEPARTAMENTO = dEPARTAMENTO;
	}

	public void setDATA_ACCAO(Date dATA_ACCAO) {
		DATA_ACCAO = dATA_ACCAO;
	}

	public void setHORA_ACCAO(Time hORA_ACCAO) {
		HORA_ACCAO = hORA_ACCAO;
	}

	public Boolean getFASTRESPONSE() {
		return FASTRESPONSE;
	}

	public Integer getPRIORIDADE() {
		return PRIORIDADE;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setFASTRESPONSE(Boolean fASTRESPONSE) {
		FASTRESPONSE = fASTRESPONSE;
	}

	public void setPRIORIDADE(Integer pRIORIDADE) {
		PRIORIDADE = pRIORIDADE;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public Timestamp getDATA_CONTROLADO() {
		return DATA_CONTROLADO;
	}

	public Timestamp getDATA_APROVADO() {
		return DATA_APROVADO;
	}

	public Timestamp getDATA_CANCELADO() {
		return DATA_CANCELADO;
	}

	public Integer getUTZ_CONTROLADO() {
		return UTZ_CONTROLADO;
	}

	public Integer getUTZ_APROVADO() {
		return UTZ_APROVADO;
	}

	public Integer getUTZ_CANCELADO() {
		return UTZ_CANCELADO;
	}

	public void setDATA_CONTROLADO(Timestamp dATA_CONTROLADO) {
		DATA_CONTROLADO = dATA_CONTROLADO;
	}

	public void setDATA_APROVADO(Timestamp dATA_APROVADO) {
		DATA_APROVADO = dATA_APROVADO;
	}

	public void setDATA_CANCELADO(Timestamp dATA_CANCELADO) {
		DATA_CANCELADO = dATA_CANCELADO;
	}

	public void setUTZ_CONTROLADO(Integer uTZ_CONTROLADO) {
		UTZ_CONTROLADO = uTZ_CONTROLADO;
	}

	public void setUTZ_APROVADO(Integer uTZ_APROVADO) {
		UTZ_APROVADO = uTZ_APROVADO;
	}

	public void setUTZ_CANCELADO(Integer uTZ_CANCELADO) {
		UTZ_CANCELADO = uTZ_CANCELADO;
	}

	public Integer getTIPO_ACAO() {
		return TIPO_ACAO;
	}

	public String getITEM() {
		return ITEM;
	}

	public void setTIPO_ACAO(Integer tIPO_ACAO) {
		TIPO_ACAO = tIPO_ACAO;
	}

	public void setITEM(String iTEM) {
		ITEM = iTEM;
	}

	public Integer getUNIDADE() {
		return UNIDADE;
	}

	public void setUNIDADE(Integer uNIDADE) {
		UNIDADE = uNIDADE;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public String getDESIGN_REFERENCIA() {
		return DESIGN_REFERENCIA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public void setDESIGN_REFERENCIA(String dESIGN_REFERENCIA) {
		DESIGN_REFERENCIA = dESIGN_REFERENCIA;
	}

	public String getCAUSA() {
		return CAUSA;
	}

	public void setCAUSA(String cAUSA) {
		CAUSA = cAUSA;
	}

	public String getINVESTIMENTOS() {
		return INVESTIMENTOS;
	}

	public String getEFICACIA_CUMPRIMENTO_OBJETIVO() {
		return EFICACIA_CUMPRIMENTO_OBJETIVO;
	}

	public Boolean getSEGUIR_LINHA() {
		return SEGUIR_LINHA;
	}

	public void setINVESTIMENTOS(String iNVESTIMENTOS) {
		INVESTIMENTOS = iNVESTIMENTOS;
	}

	public void setEFICACIA_CUMPRIMENTO_OBJETIVO(String eFICACIA_CUMPRIMENTO_OBJETIVO) {
		EFICACIA_CUMPRIMENTO_OBJETIVO = eFICACIA_CUMPRIMENTO_OBJETIVO;
	}

	public void setSEGUIR_LINHA(Boolean sEGUIR_LINHA) {
		SEGUIR_LINHA = sEGUIR_LINHA;
	}

	public String getOBJETIVO() {
		return OBJETIVO;
	}

	public void setOBJETIVO(String oBJETIVO) {
		OBJETIVO = oBJETIVO;
	}

	public String getORIGEM() {
		return ORIGEM;
	}

	public void setORIGEM(String oRIGEM) {
		ORIGEM = oRIGEM;
	}

	public String getORDENACAO() {
		return ORDENACAO;
	}

	public void setORDENACAO(String oRDENACAO) {
		ORDENACAO = oRDENACAO;
	}

	public Date getDATA_ACCAO_ORIGINAL() {
		return DATA_ACCAO_ORIGINAL;
	}

	public Time getHORA_ACCAO_ORIGINAL() {
		return HORA_ACCAO_ORIGINAL;
	}

	public void setDATA_ACCAO_ORIGINAL(Date dATA_ACCAO_ORIGINAL) {
		DATA_ACCAO_ORIGINAL = dATA_ACCAO_ORIGINAL;
	}

	public void setHORA_ACCAO_ORIGINAL(Time hORA_ACCAO_ORIGINAL) {
		HORA_ACCAO_ORIGINAL = hORA_ACCAO_ORIGINAL;
	}

	public String getJUSTIFICACAO() {
		return JUSTIFICACAO;
	}

	public void setJUSTIFICACAO(String jUSTIFICACAO) {
		JUSTIFICACAO = jUSTIFICACAO;
	}

	public Date getDATA_INICIO_ACCAO() {
		return DATA_INICIO_ACCAO;
	}

	public void setDATA_INICIO_ACCAO(Date dATA_INICIO_ACCAO) {
		DATA_INICIO_ACCAO = dATA_INICIO_ACCAO;
	}

	public Time getHORA_INICIO_ACCAO() {
		return HORA_INICIO_ACCAO;
	}

	public void setHORA_INICIO_ACCAO(Time hORA_INICIO_ACCAO) {
		HORA_INICIO_ACCAO = hORA_INICIO_ACCAO;
	}

}
