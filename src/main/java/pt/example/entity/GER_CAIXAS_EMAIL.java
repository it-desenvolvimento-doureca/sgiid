package pt.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "GER_CAIXAS_EMAIL")
public class GER_CAIXAS_EMAIL {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("EMAIL")
	private String EMAIL;
	@JsonProperty("UTILIZADOR")
	private String UTILIZADOR;
	@JsonProperty("PALAVRA_CHAVE")
	private String PALAVRA_CHAVE;
	@JsonProperty("ATIVA")
	private Boolean ATIVA;
	@JsonProperty("PROCESSA_INBOX_INICIAL")
	private Boolean PROCESSA_INBOX_INICIAL;
	@JsonProperty("MOVE_PARA_PASTA_LIDAS")
	private Boolean MOVE_PARA_PASTA_LIDAS;
	@JsonProperty("NOME_PASTA_LIDAS")
	private String NOME_PASTA_LIDAS;

	public Integer getID() {
		return ID;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public String getUTILIZADOR() {
		return UTILIZADOR;
	}

	public String getPALAVRA_CHAVE() {
		return PALAVRA_CHAVE;
	}

	public Boolean getATIVA() {
		return ATIVA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public void setUTILIZADOR(String uTILIZADOR) {
		UTILIZADOR = uTILIZADOR;
	}

	public void setPALAVRA_CHAVE(String pALAVRA_CHAVE) {
		PALAVRA_CHAVE = pALAVRA_CHAVE;
	}

	public void setATIVA(Boolean aTIVA) {
		ATIVA = aTIVA;
	}

	public Boolean getPROCESSA_INBOX_INICIAL() {
		return PROCESSA_INBOX_INICIAL;
	}

	public void setPROCESSA_INBOX_INICIAL(Boolean pROCESSA_INBOX_INICIAL) {
		PROCESSA_INBOX_INICIAL = pROCESSA_INBOX_INICIAL;
	}

	public Boolean getMOVE_PARA_PASTA_LIDAS() {
		return MOVE_PARA_PASTA_LIDAS;
	}

	public String getNOME_PASTA_LIDAS() {
		return NOME_PASTA_LIDAS;
	}

	public void setMOVE_PARA_PASTA_LIDAS(Boolean mOVE_PARA_PASTA_LIDAS) {
		MOVE_PARA_PASTA_LIDAS = mOVE_PARA_PASTA_LIDAS;
	}

	public void setNOME_PASTA_LIDAS(String nOME_PASTA_LIDAS) {
		NOME_PASTA_LIDAS = nOME_PASTA_LIDAS;
	}

}