package pt.example.entity;

import java.math.BigDecimal;
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
@Table(name = "COM_ACORDOS")
public class COM_ACORDOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("VERSAO")
	private Integer VERSAO;
	@JsonProperty("ID_REFERENCIA")
	private Integer ID_REFERENCIA;
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
	@JsonProperty("ID_CONTRATO")
	private Integer ID_CONTRATO;
	@JsonProperty("SOP")
	private Date SOP;
	@JsonProperty("EOP")
	private Date EOP;
	@JsonProperty("PRECO_BASE")
	private BigDecimal PRECO_BASE;
	@JsonProperty("DATA_FECHO")
	private Timestamp DATA_FECHO;
	@JsonProperty("UTZ_FECHO")
	private Integer UTZ_FECHO;
	@JsonProperty("ESTADO")
	private String ESTADO;

	@JsonProperty("DATA_FECHO_LTA")
	private Timestamp DATA_FECHO_LTA;
	@JsonProperty("UTZ_FECHO_LTA")
	private Integer UTZ_FECHO_LTA;
	@JsonProperty("DATA_FECHO_AMORTIZACOES")
	private Timestamp DATA_FECHO_AMORTIZACOES;
	@JsonProperty("UTZ_FECHO_AMORTIZACOES")
	private Integer UTZ_FECHO_AMORTIZACOES;
	
	public Integer getID() {
		return ID;
	}

	public Integer getID_REFERENCIA() {
		return ID_REFERENCIA;
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

	public Integer getID_CONTRATO() {
		return ID_CONTRATO;
	}

	public Date getSOP() {
		return SOP;
	}

	public Date getEOP() {
		return EOP;
	}

	public BigDecimal getPRECO_BASE() {
		return PRECO_BASE;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_REFERENCIA(Integer iD_REFERENCIA) {
		ID_REFERENCIA = iD_REFERENCIA;
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

	public void setID_CONTRATO(Integer iD_CONTRATO) {
		ID_CONTRATO = iD_CONTRATO;
	}

	public void setSOP(Date sOP) {
		SOP = sOP;
	}

	public void setEOP(Date eOP) {
		EOP = eOP;
	}

	public void setPRECO_BASE(BigDecimal pRECO_BASE) {
		PRECO_BASE = pRECO_BASE;
	}

	public Timestamp getDATA_FECHO() {
		return DATA_FECHO;
	}

	public Integer getUTZ_FECHO() {
		return UTZ_FECHO;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setDATA_FECHO(Timestamp dATA_FECHO) {
		DATA_FECHO = dATA_FECHO;
	}

	public void setUTZ_FECHO(Integer uTZ_FECHO) {
		UTZ_FECHO = uTZ_FECHO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

	public Timestamp getDATA_FECHO_LTA() {
		return DATA_FECHO_LTA;
	}

	public Integer getUTZ_FECHO_LTA() {
		return UTZ_FECHO_LTA;
	}

	public Timestamp getDATA_FECHO_AMORTIZACOES() {
		return DATA_FECHO_AMORTIZACOES;
	}

	public Integer getUTZ_FECHO_AMORTIZACOES() {
		return UTZ_FECHO_AMORTIZACOES;
	}

	public void setDATA_FECHO_LTA(Timestamp dATA_FECHO_LTA) {
		DATA_FECHO_LTA = dATA_FECHO_LTA;
	}

	public void setUTZ_FECHO_LTA(Integer uTZ_FECHO_LTA) {
		UTZ_FECHO_LTA = uTZ_FECHO_LTA;
	}

	public void setDATA_FECHO_AMORTIZACOES(Timestamp dATA_FECHO_AMORTIZACOES) {
		DATA_FECHO_AMORTIZACOES = dATA_FECHO_AMORTIZACOES;
	}

	public void setUTZ_FECHO_AMORTIZACOES(Integer uTZ_FECHO_AMORTIZACOES) {
		UTZ_FECHO_AMORTIZACOES = uTZ_FECHO_AMORTIZACOES;
	}

}
