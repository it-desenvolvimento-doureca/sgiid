package pt.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PR_DIC_SECTORES_PECAS_CRITICAS")
public class PR_DIC_SECTORES_PECAS_CRITICAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("SECTOR")
	private String SECTOR;

	@JsonProperty("DESC_SECTOR")
	private String DESC_SECTOR;

	@JsonProperty("LOCAL")
	private Integer LOCAL;

	@JsonProperty("COR")
	private String COR;

	@JsonProperty("DATA_CRIA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date DATA_CRIA;

	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;

	@JsonProperty("DATA_MODIF")
	@Temporal(TemporalType.TIMESTAMP)
	private Date DATA_MODIF;

	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	@JsonProperty("DATA_ANULA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date DATA_ANULA;

	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;

	@JsonProperty("ATIVO")
	private Boolean ATIVO;
	
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;

	public Integer getID() {
		return ID;
	}

	public String getSECTOR() {
		return SECTOR;
	}

	public String getDESC_SECTOR() {
		return DESC_SECTOR;
	}

	public Integer getLOCAL() {
		return LOCAL;
	}

	public String getCOR() {
		return COR;
	}

	public Date getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Date getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Date getDATA_ANULA() {
		return DATA_ANULA;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setSECTOR(String sECTOR) {
		SECTOR = sECTOR;
	}

	public void setDESC_SECTOR(String dESC_SECTOR) {
		DESC_SECTOR = dESC_SECTOR;
	}

	public void setLOCAL(Integer lOCAL) {
		LOCAL = lOCAL;
	}

	public void setCOR(String cOR) {
		COR = cOR;
	}

	public void setDATA_CRIA(Date dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_MODIF(Date dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_ANULA(Date dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

}
