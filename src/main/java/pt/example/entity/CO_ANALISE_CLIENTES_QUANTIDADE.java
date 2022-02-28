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
@Table(name = "CO_ANALISE_CLIENTES_QUANTIDADE")
public class CO_ANALISE_CLIENTES_QUANTIDADE {
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
	@Column(name = "QUANTIDADE")
	private BigDecimal QUANTIDADE;
	@Column(name = "ANO")
	private Integer ANO;
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

	public Integer getANO() {
		return ANO;
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

	public void setANO(Integer aNO) {
		ANO = aNO;
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

	public BigDecimal getQUANTIDADE() {
		return QUANTIDADE;
	}

	public void setQUANTIDADE(BigDecimal qUANTIDADE) {
		QUANTIDADE = qUANTIDADE;
	}

}
