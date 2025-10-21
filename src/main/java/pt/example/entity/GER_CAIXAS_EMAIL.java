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

}