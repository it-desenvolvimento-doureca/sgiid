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
@Table(name = "RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO")
public class RH_DIC_CRITERIOS_AVALIACAO_DESEMPENHO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("CRITERIO")
	private String CRITERIO;

	@JsonProperty("MIN_VALOR")
	private Integer MIN_VALOR;

	@JsonProperty("MAX_VALOR")
	private Integer MAX_VALOR;

	@JsonProperty("LABEL")
	private String LABEL;

	@JsonProperty("PONTOS")
	private Integer PONTOS;

	@JsonProperty("DATA_MODIF")
	private Date DATA_MODIF;

	public Integer getID() {
		return ID;
	}

	public String getCRITERIO() {
		return CRITERIO;
	}

	public Integer getMIN_VALOR() {
		return MIN_VALOR;
	}

	public Integer getMAX_VALOR() {
		return MAX_VALOR;
	}

	public String getLABEL() {
		return LABEL;
	}

	public Integer getPONTOS() {
		return PONTOS;
	}

	@JsonProperty("ID_UTZ")
	private Integer ID_UTZ;

	public Date getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setCRITERIO(String cRITERIO) {
		CRITERIO = cRITERIO;
	}

	public void setMIN_VALOR(Integer mIN_VALOR) {
		MIN_VALOR = mIN_VALOR;
	}

	public void setMAX_VALOR(Integer mAX_VALOR) {
		MAX_VALOR = mAX_VALOR;
	}

	public void setLABEL(String lABEL) {
		LABEL = lABEL;
	}

	public void setPONTOS(Integer pONTOS) {
		PONTOS = pONTOS;
	}

	public void setDATA_MODIF(Date dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public Integer getID_UTZ() {
		return ID_UTZ;
	}

	public void setID_UTZ(Integer iD_UTZ) {
		ID_UTZ = iD_UTZ;
	}

}