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

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "COM_CONTRATOS")
public class COM_CONTRATOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("N_CONTRATO")
	private String N_CONTRATO;
	@JsonProperty("DATA_CONTRATO")
	private Date DATA_CONTRATO;
	@JsonProperty("N_CLIENTE")
	private Integer N_CLIENTE;
	@JsonProperty("NOME_CLIENTE")
	private String NOME_CLIENTE;
	@JsonProperty("MORADA_CLIENTE")
	private String MORADA_CLIENTE;
	@JsonProperty("COD_MORADA")
	private String COD_MORADA;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("DATA_ANULACAO")
	private Timestamp DATA_ANULACAO;
	@JsonProperty("UTZ_ANULACAO")
	private Integer UTZ_ANULACAO;
	@JsonProperty("INATIVO")
	private Boolean INATIVO;
	@JsonProperty("CONTRATO_SILVER")
	private String CONTRATO_SILVER;
	@JsonProperty("DESC_CONTRATO_SILVER")
	private String DESC_CONTRATO_SILVER;
	@JsonProperty("CONTRIBUINTE")
	private String CONTRIBUINTE;

	public String getCONTRIBUINTE() {
		return CONTRIBUINTE;
	}

	public void setCONTRIBUINTE(String cONTRIBUINTE) {
		CONTRIBUINTE = cONTRIBUINTE;
	}

	public Integer getID() {
		return ID;
	}

	public String getN_CONTRATO() {
		return N_CONTRATO;
	}

	public Date getDATA_CONTRATO() {
		return DATA_CONTRATO;
	}

	public Integer getN_CLIENTE() {
		return N_CLIENTE;
	}

	public String getNOME_CLIENTE() {
		return NOME_CLIENTE;
	}

	public String getMORADA_CLIENTE() {
		return MORADA_CLIENTE;
	}

	public String getCOD_MORADA() {
		return COD_MORADA;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public String getCONTRATO_SILVER() {
		return CONTRATO_SILVER;
	}

	public String getDESC_CONTRATO_SILVER() {
		return DESC_CONTRATO_SILVER;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setN_CONTRATO(String n_CONTRATO) {
		N_CONTRATO = n_CONTRATO;
	}

	public void setDATA_CONTRATO(Date dATA_CONTRATO) {
		DATA_CONTRATO = dATA_CONTRATO;
	}

	public void setN_CLIENTE(Integer n_CLIENTE) {
		N_CLIENTE = n_CLIENTE;
	}

	public void setNOME_CLIENTE(String nOME_CLIENTE) {
		NOME_CLIENTE = nOME_CLIENTE;
	}

	public void setMORADA_CLIENTE(String mORADA_CLIENTE) {
		MORADA_CLIENTE = mORADA_CLIENTE;
	}

	public void setCOD_MORADA(String cOD_MORADA) {
		COD_MORADA = cOD_MORADA;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public void setCONTRATO_SILVER(String cONTRATO_SILVER) {
		CONTRATO_SILVER = cONTRATO_SILVER;
	}

	public void setDESC_CONTRATO_SILVER(String dESC_CONTRATO_SILVER) {
		DESC_CONTRATO_SILVER = dESC_CONTRATO_SILVER;
	}

}