package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_DIC_POTES")
public class PIN_DIC_POTES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_LINHA")
	private Integer ID_LINHA;
	@JsonProperty("ID_CABINE")
	private Integer ID_CABINE;
	@JsonProperty("NOME")
	private String NOME;
	@JsonProperty("N_VALVULA")
	private String N_VALVULA;
	@JsonProperty("CAPACIDADE")
	private BigDecimal CAPACIDADE;
	@JsonProperty("ID_TIPO_ACABAMENTO")
	private Integer ID_TIPO_ACABAMENTO;
	@JsonProperty("OBS")
	private String OBS;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("INATIVO")
	private Boolean INATIVO;
	@JsonProperty("DATA_ANULACAO")
	private Timestamp DATA_ANULACAO;
	@JsonProperty("UTZ_ANULACAO")
	private Integer UTZ_ANULACAO;
	@JsonProperty("REGISTO_TINTAS")
	private Boolean REGISTO_TINTAS;
	@JsonProperty("ORDEM")
	private Integer ORDEM;
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public Integer getID_CABINE() {
		return ID_CABINE;
	}

	public void setID_CABINE(Integer iD_CABINE) {
		ID_CABINE = iD_CABINE;
	}

	public String getNOME() {
		return NOME;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public String getN_VALVULA() {
		return N_VALVULA;
	}

	public void setN_VALVULA(String n_VALVULA) {
		N_VALVULA = n_VALVULA;
	}

	public BigDecimal getCAPACIDADE() {
		return CAPACIDADE;
	}

	public void setCAPACIDADE(BigDecimal cAPACIDADE) {
		CAPACIDADE = cAPACIDADE;
	}

	public Integer getID_TIPO_ACABAMENTO() {
		return ID_TIPO_ACABAMENTO;
	}

	public void setID_TIPO_ACABAMENTO(Integer iD_TIPO_ACABAMENTO) {
		ID_TIPO_ACABAMENTO = iD_TIPO_ACABAMENTO;
	}

	public String getOBS() {
		return OBS;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public Boolean getREGISTO_TINTAS() {
		return REGISTO_TINTAS;
	}

	public void setREGISTO_TINTAS(Boolean rEGISTO_TINTAS) {
		REGISTO_TINTAS = rEGISTO_TINTAS;
	}

	public Integer getORDEM() {
		return ORDEM;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
	}

}
