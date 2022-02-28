package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CO_ANALISE_CLIENTES_ACCOES")
public class CO_ANALISE_CLIENTES_ACCOES {
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
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "QUEM")
	private String QUEM;
	@Column(name = "DATA")
	private Date DATA;
	@Column(name = "ESTADO")
	private String ESTADO;
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

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getQUEM() {
		return QUEM;
	}

	public Date getDATA() {
		return DATA;
	}

	public String getESTADO() {
		return ESTADO;
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

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setQUEM(String qUEM) {
		QUEM = qUEM;
	}

	public void setDATA(Date dATA) {
		DATA = dATA;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
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

	public String getCLICOD() {
		return CLICOD;
	}

	public String getETSNUM() {
		return ETSNUM;
	}

	public String getPROREF() {
		return PROREF;
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

}
