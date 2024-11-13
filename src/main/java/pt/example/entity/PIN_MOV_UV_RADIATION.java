package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_MOV_UV_RADIATION")
public class PIN_MOV_UV_RADIATION {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("VERSAO")
	private Integer VERSAO;
	@JsonProperty("ID_RECEITA")
	private Integer ID_RECEITA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("UV_RADIATION")
	private String UV_RADIATION;
	@JsonProperty("RECOMMENDED_VALUE")
	private String RECOMMENDED_VALUE;

	public Integer getID() {
		return ID;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public Integer getID_RECEITA() {
		return ID_RECEITA;
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

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

	public void setID_RECEITA(Integer iD_RECEITA) {
		ID_RECEITA = iD_RECEITA;
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

	public String getUV_RADIATION() {
		return UV_RADIATION;
	}

	public String getRECOMMENDED_VALUE() {
		return RECOMMENDED_VALUE;
	}

	public void setUV_RADIATION(String uV_RADIATION) {
		UV_RADIATION = uV_RADIATION;
	}

	public void setRECOMMENDED_VALUE(String rECOMMENDED_VALUE) {
		RECOMMENDED_VALUE = rECOMMENDED_VALUE;
	}

}