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
@Table(name = "AB_MOV_ANALISE")
public class AB_MOV_ANALISE {

	private Integer ID_ANALISE;
	private Integer ID_BANHO;
	private Integer ID_LINHA;
	private Date DATA_ANALISE;
	private String HORA_ANALISE;
	private String ANALISE_INT_EXT;
	private Boolean CELULAHULL;
	private String OBS;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private String ESTADO;
	private Timestamp DATA_VALIDA;
	private Integer UTZ_VALIDA;
	private String COR_LIMITES;

	@Column(name = "DATA_ANULACAO")
	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	@Column(name = "UTZ_ANULACAO")
	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	@Column(name = "INATIVO")
	public Boolean getINATIVO() {
		return INATIVO;
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

	@Id
	@Column(name = "ID_ANALISE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_ANALISE() {
		return ID_ANALISE;
	}

	@Column(name = "ID_LINHA")
	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	@Column(name = "ID_BANHO")
	public Integer getID_BANHO() {
		return ID_BANHO;
	}

	@Column(name = "DATA_ANALISE")
	public Date getDATA_ANALISE() {
		return DATA_ANALISE;
	}

	@Column(name = "HORA_ANALISE")
	public String getHORA_ANALISE() {
		return HORA_ANALISE;
	}

	@Column(name = "ANALISE_INT_EXT")
	public String getANALISE_INT_EXT() {
		return ANALISE_INT_EXT;
	}

	@Column(name = "CELULAHULL")
	public Boolean getCELULAHULL() {
		return CELULAHULL;
	}

	@Column(name = "OBS")
	public String getOBS() {
		return OBS;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_ULT_MODIF")
	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	@Column(name = "UTZ_ULT_MODIF")
	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	@Column(name = "ESTADO")
	public String getESTADO() {
		return ESTADO;
	}

	@Column(name = "UTZ_VALIDA")
	public Integer getUTZ_VALIDA() {
		return UTZ_VALIDA;
	}

	@Column(name = "DATA_VALIDA")
	public Timestamp getDATA_VALIDA() {
		return DATA_VALIDA;
	}

	@Column(name = "COR_LIMITES")
	public String getCOR_LIMITES() {
		return COR_LIMITES;
	}

	public void setCOR_LIMITES(String cOR_LIMITES) {
		COR_LIMITES = cOR_LIMITES;
	}

	public void setDATA_VALIDA(Timestamp dATA_VALIDA) {
		DATA_VALIDA = dATA_VALIDA;
	}

	public void setUTZ_VALIDA(Integer uTZ_VALIDA) {
		UTZ_VALIDA = uTZ_VALIDA;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setID_ANALISE(Integer iD_ANALISE) {
		ID_ANALISE = iD_ANALISE;
	}

	public void setID_BANHO(Integer iD_BANHO) {
		ID_BANHO = iD_BANHO;
	}

	public void setDATA_ANALISE(Date dATA_ANALISE) {
		DATA_ANALISE = dATA_ANALISE;
	}

	public void setHORA_ANALISE(String hORA_ANALISE) {
		HORA_ANALISE = hORA_ANALISE;
	}

	public void setANALISE_INT_EXT(String aNALISE_INT_EXT) {
		ANALISE_INT_EXT = aNALISE_INT_EXT;
	}

	public void setCELULAHULL(Boolean cELULAHULL) {
		CELULAHULL = cELULAHULL;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

}
