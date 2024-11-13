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
@Table(name = "PR_REGISTO_PINTURA")
public class PR_REGISTO_PINTURA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_POTE")
	private Integer ID_POTE;
	@JsonProperty("ID_COR")
	private Integer ID_COR;
	@JsonProperty("VALOR")
	private String VALOR;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private String UTZ_CRIA;
	@JsonProperty("NOME_POTE")
	private String NOME_POTE;
	@JsonProperty("NOME_VALVULA")
	private String NOME_VALVULA;
	@JsonProperty("NOME_CABINE")
	private String NOME_CABINE;
	@JsonProperty("ID_CABINE")
	private Integer ID_CABINE;

	public Integer getID() {
		return ID;
	}

	public String getVALOR() {
		return VALOR;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public String getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public String getNOME_POTE() {
		return NOME_POTE;
	}

	public String getNOME_VALVULA() {
		return NOME_VALVULA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setVALOR(String vALOR) {
		VALOR = vALOR;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(String uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setNOME_POTE(String nOME_POTE) {
		NOME_POTE = nOME_POTE;
	}

	public void setNOME_VALVULA(String nOME_VALVULA) {
		NOME_VALVULA = nOME_VALVULA;
	}

	public String getNOME_CABINE() {
		return NOME_CABINE;
	}

	public void setNOME_CABINE(String nOME_CABINE) {
		NOME_CABINE = nOME_CABINE;
	}

	public Integer getID_COR() {
		return ID_COR;
	}

	public void setID_COR(Integer iD_COR) {
		ID_COR = iD_COR;
	}

	public Integer getID_POTE() {
		return ID_POTE;
	}

	public Integer getID_CABINE() {
		return ID_CABINE;
	}

	public void setID_POTE(Integer iD_POTE) {
		ID_POTE = iD_POTE;
	}

	public void setID_CABINE(Integer iD_CABINE) {
		ID_CABINE = iD_CABINE;
	}

}
