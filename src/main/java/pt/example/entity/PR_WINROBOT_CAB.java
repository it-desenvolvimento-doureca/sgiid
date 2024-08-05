package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_WINROBOT_CAB")
public class PR_WINROBOT_CAB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_POSTO")
	private Integer ID_POSTO;
	@JsonProperty("NUMERO_IDENTICACAO_CARGA")
	private String NUMERO_IDENTICACAO_CARGA;
	@JsonProperty("TIPO_RACK")
	private String TIPO_RACK;
	@JsonProperty("ORDEM")
	private String ORDEM;
	@JsonProperty("ID_PROGRAMA")
	private String ID_PROGRAMA;
	@JsonProperty("DESIGN_PROGRAMA")
	private String DESIGN_PROGRAMA;
	@JsonProperty("DATA_HORA_INICIO")
	private Timestamp DATA_HORA_INICIO;
	@JsonProperty("DATA_HORA_FIM")
	private Timestamp DATA_HORA_FIM;
	@JsonProperty("UTZ_PRINCIPAL")
	private String UTZ_PRINCIPAL;
	@JsonProperty("UTZ_CRIA")
	private String UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("ESTADO")
	private String ESTADO;
	@JsonProperty("NUM_BARRA")
	private Integer NUM_BARRA;
	@JsonProperty("UTZ_MODIF")
	private String UTZ_MODIF;
	@JsonProperty("NOME_POSTO")
	private String NOME_POSTO;
	@JsonProperty("RECEITA")
	private String RECEITA;
	@JsonProperty("DESC_RECEITA")
	private String DESC_RECEITA;
	@JsonProperty("INFORMACAO_DESCARGA")
	private String INFORMACAO_DESCARGA;
	@JsonProperty("ALERTA_DESCARGA")
	private Integer ALERTA_DESCARGA;
	@JsonProperty("TIPO_POSTO")
	private String TIPO_POSTO;
	@JsonProperty("MAINTENANCE")
	private Integer MAINTENANCE;
	@JsonProperty("LINHA")
	private Integer LINHA;
	@JsonProperty("ESTADO_POLL")
	private String ESTADO_POLL;
	@JsonProperty("ORDEM_POLL")
	private Integer ORDEM_POLL;
	@JsonProperty("NUM_CARRO")
	private String NUM_CARRO;

	@Transient
	private String referencias;

	@Transient
	private String racks;

	public Integer getID() {
		return ID;
	}

	public Integer getID_POSTO() {
		return ID_POSTO;
	}

	public String getNUMERO_IDENTICACAO_CARGA() {
		return NUMERO_IDENTICACAO_CARGA;
	}

	public String getTIPO_RACK() {
		return TIPO_RACK;
	}

	public String getORDEM() {
		return ORDEM;
	}

	public String getID_PROGRAMA() {
		return ID_PROGRAMA;
	}

	public String getDESIGN_PROGRAMA() {
		return DESIGN_PROGRAMA;
	}

	public Timestamp getDATA_HORA_INICIO() {
		return DATA_HORA_INICIO;
	}

	public Timestamp getDATA_HORA_FIM() {
		return DATA_HORA_FIM;
	}

	public String getUTZ_PRINCIPAL() {
		return UTZ_PRINCIPAL;
	}

	public String getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Integer getNUM_BARRA() {
		return NUM_BARRA;
	}

	public String getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public String getNOME_POSTO() {
		return NOME_POSTO;
	}

	public String getRECEITA() {
		return RECEITA;
	}

	public String getDESC_RECEITA() {
		return DESC_RECEITA;
	}

	public String getINFORMACAO_DESCARGA() {
		return INFORMACAO_DESCARGA;
	}

	public Integer getALERTA_DESCARGA() {
		return ALERTA_DESCARGA;
	}

	public String getTIPO_POSTO() {
		return TIPO_POSTO;
	}

	public Integer getMAINTENANCE() {
		return MAINTENANCE;
	}

	public Integer getLINHA() {
		return LINHA;
	}

	public String getESTADO_POLL() {
		return ESTADO_POLL;
	}

	public Integer getORDEM_POLL() {
		return ORDEM_POLL;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_POSTO(Integer iD_POSTO) {
		ID_POSTO = iD_POSTO;
	}

	public void setNUMERO_IDENTICACAO_CARGA(String nUMERO_IDENTICACAO_CARGA) {
		NUMERO_IDENTICACAO_CARGA = nUMERO_IDENTICACAO_CARGA;
	}

	public void setTIPO_RACK(String tIPO_RACK) {
		TIPO_RACK = tIPO_RACK;
	}

	public void setORDEM(String oRDEM) {
		ORDEM = oRDEM;
	}

	public void setID_PROGRAMA(String iD_PROGRAMA) {
		ID_PROGRAMA = iD_PROGRAMA;
	}

	public void setDESIGN_PROGRAMA(String dESIGN_PROGRAMA) {
		DESIGN_PROGRAMA = dESIGN_PROGRAMA;
	}

	public void setDATA_HORA_INICIO(Timestamp dATA_HORA_INICIO) {
		DATA_HORA_INICIO = dATA_HORA_INICIO;
	}

	public void setDATA_HORA_FIM(Timestamp dATA_HORA_FIM) {
		DATA_HORA_FIM = dATA_HORA_FIM;
	}

	public void setUTZ_PRINCIPAL(String uTZ_PRINCIPAL) {
		UTZ_PRINCIPAL = uTZ_PRINCIPAL;
	}

	public void setUTZ_CRIA(String uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setNUM_BARRA(Integer nUM_BARRA) {
		NUM_BARRA = nUM_BARRA;
	}

	public void setUTZ_MODIF(String uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setNOME_POSTO(String nOME_POSTO) {
		NOME_POSTO = nOME_POSTO;
	}

	public void setRECEITA(String rECEITA) {
		RECEITA = rECEITA;
	}

	public void setDESC_RECEITA(String dESC_RECEITA) {
		DESC_RECEITA = dESC_RECEITA;
	}

	public void setINFORMACAO_DESCARGA(String iNFORMACAO_DESCARGA) {
		INFORMACAO_DESCARGA = iNFORMACAO_DESCARGA;
	}

	public void setALERTA_DESCARGA(Integer aLERTA_DESCARGA) {
		ALERTA_DESCARGA = aLERTA_DESCARGA;
	}

	public void setTIPO_POSTO(String tIPO_POSTO) {
		TIPO_POSTO = tIPO_POSTO;
	}

	public void setMAINTENANCE(Integer mAINTENANCE) {
		MAINTENANCE = mAINTENANCE;
	}

	public void setLINHA(Integer lINHA) {
		LINHA = lINHA;
	}

	public void setESTADO_POLL(String eSTADO_POLL) {
		ESTADO_POLL = eSTADO_POLL;
	}

	public void setORDEM_POLL(Integer oRDEM_POLL) {
		ORDEM_POLL = oRDEM_POLL;
	}

	public String getReferencias() {
		return referencias;
	}

	public String getRacks() {
		return racks;
	}

	public void setRacks(String racks) {
		this.racks = racks;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	public String getNUM_CARRO() {
		return NUM_CARRO;
	}

	public void setNUM_CARRO(String nUM_CARRO) {
		NUM_CARRO = nUM_CARRO;
	}

}