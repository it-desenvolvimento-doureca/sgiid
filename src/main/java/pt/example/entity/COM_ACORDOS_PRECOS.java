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
@Table(name = "COM_ACORDOS_PRECOS")
public class COM_ACORDOS_PRECOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_ACORDO")
	private Integer ID_ACORDO;
	@JsonProperty("DATA_INICIO")
	private Date DATA_INICIO;
	@JsonProperty("DATA_FIM")
	private Date DATA_FIM;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("OBSERVACAO")
	private String OBSERVACAO;
	@JsonProperty("PRECO")
	private BigDecimal PRECO;
	@JsonProperty("VERSAO")
	private Integer VERSAO;
	
	public Integer getID() {
		return ID;
	}

	public Integer getID_ACORDO() {
		return ID_ACORDO;
	}

	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	public Date getDATA_FIM() {
		return DATA_FIM;
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

	public String getOBSERVACAO() {
		return OBSERVACAO;
	}

	public BigDecimal getPRECO() {
		return PRECO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_ACORDO(Integer iD_ACORDO) {
		ID_ACORDO = iD_ACORDO;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Date dATA_FIM) {
		DATA_FIM = dATA_FIM;
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

	public void setOBSERVACAO(String oBSERVACAO) {
		OBSERVACAO = oBSERVACAO;
	}

	public void setPRECO(BigDecimal pRECO) {
		PRECO = pRECO;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

}
