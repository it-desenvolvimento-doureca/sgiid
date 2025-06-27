package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_PECAS_CRITICAS")
public class PR_PECAS_CRITICAS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Integer ID;

    @JsonProperty("PROREF")
    private String PROREF;

    @JsonProperty("DESC_PROREF")
    private String DESC_PROREF;

    @ManyToOne
    @JoinColumn(name = "ID_SECTOR_PECAS_CRITICAS", referencedColumnName = "ID")
    @JsonProperty("ID_SECTOR_PECAS_CRITICAS")
    private PR_DIC_SECTORES_PECAS_CRITICAS ID_SECTOR_PECAS_CRITICAS;

    @ManyToOne
    @JoinColumn(name = "ID_MOTIVO", referencedColumnName = "ID")
    @JsonProperty("ID_MOTIVO")
    private PR_DIC_MOTIVO_CRITICIDADE ID_MOTIVO;

    @JsonProperty("FAST_RESPONSE")
    private Boolean FAST_RESPONSE;

    @JsonProperty("DATA_CRIA")
    private Timestamp DATA_CRIA;

    @JsonProperty("UTZ_CRIA")
    private Integer UTZ_CRIA;

    @JsonProperty("DATA_MODIF")
    private Timestamp DATA_MODIF;

    @JsonProperty("UTZ_MODIF")
    private Integer UTZ_MODIF;

    @JsonProperty("DATA_ANULA")
    private Timestamp DATA_ANULA;

    @JsonProperty("UTZ_ANULA")
    private Integer UTZ_ANULA;

    @JsonProperty("ATIVO")
    private Boolean ATIVO;

	public Integer getID() {
		return ID;
	}

	public String getPROREF() {
		return PROREF;
	}

	public String getDESC_PROREF() {
		return DESC_PROREF;
	}

	public PR_DIC_SECTORES_PECAS_CRITICAS getID_SECTOR_PECAS_CRITICAS() {
		return ID_SECTOR_PECAS_CRITICAS;
	}

	public PR_DIC_MOTIVO_CRITICIDADE getID_MOTIVO() {
		return ID_MOTIVO;
	}

	public Boolean getFAST_RESPONSE() {
		return FAST_RESPONSE;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Timestamp getDATA_ANULA() {
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

	public void setPROREF(String pROREF) {
		PROREF = pROREF;
	}

	public void setDESC_PROREF(String dESC_PROREF) {
		DESC_PROREF = dESC_PROREF;
	}

	public void setID_SECTOR_PECAS_CRITICAS(PR_DIC_SECTORES_PECAS_CRITICAS iD_SECTOR_PECAS_CRITICAS) {
		ID_SECTOR_PECAS_CRITICAS = iD_SECTOR_PECAS_CRITICAS;
	}

	public void setID_MOTIVO(PR_DIC_MOTIVO_CRITICIDADE iD_MOTIVO) {
		ID_MOTIVO = iD_MOTIVO;
	}

	public void setFAST_RESPONSE(Boolean fAST_RESPONSE) {
		FAST_RESPONSE = fAST_RESPONSE;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}
 
    
    
}
