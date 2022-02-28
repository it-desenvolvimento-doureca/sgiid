package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CO_ANALISE_CLIENTES")
public class CO_ANALISE_CLIENTES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "CLICOD")
	private String CLICOD;
	@Column(name = "ETSNUM")
	private String ETSNUM;
	@Column(name = "PROREF")
	private String PROREF;
	@Column(name = "ID_LINHA")
	private Integer ID_LINHA;
	@Column(name = "STOCK_CLIENTE")
	private BigDecimal STOCK_CLIENTE;
	@Column(name = "DATA_STOCK_CLIENTE")
	private Date DATA_STOCK_CLIENTE;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;

	public Integer getID() {
		return ID;
	}

	public String getCLICOD() {
		return CLICOD;
	}

	public String getETSNUM() {
		return ETSNUM;
	}

	public String getPROREF() {
		return PROREF;
	}

	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public BigDecimal getSTOCK_CLIENTE() {
		return STOCK_CLIENTE;
	}

	public Date getDATA_STOCK_CLIENTE() {
		return DATA_STOCK_CLIENTE;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setCLICOD(String cLICOD) {
		CLICOD = cLICOD;
	}

	public void setETSNUM(String eTSNUM) {
		ETSNUM = eTSNUM;
	}

	public void setPROREF(String pROREF) {
		PROREF = pROREF;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public void setSTOCK_CLIENTE(BigDecimal sTOCK_CLIENTE) {
		STOCK_CLIENTE = sTOCK_CLIENTE;
	}

	public void setDATA_STOCK_CLIENTE(Date dATA_STOCK_CLIENTE) {
		DATA_STOCK_CLIENTE = dATA_STOCK_CLIENTE;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

}