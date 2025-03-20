package pt.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_FORMACAO_PARTICIPANTES")
public class RH_FORMACAO_PARTICIPANTES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_FORMACAO")
	private Integer ID_FORMACAO;
	@JsonProperty("ID_FUNCIONARIO")
	private String ID_FUNCIONARIO;
	@JsonProperty("NOME_FUNCIONARIO")
	private String NOME_FUNCIONARIO;
	@JsonProperty("AVALIACAO_FINAL")
	private String AVALIACAO_FINAL;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("EMAIL")
	private String EMAIL;

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public Integer getID() {
		return ID;
	}

	public Integer getID_FORMACAO() {
		return ID_FORMACAO;
	}

	public String getID_FUNCIONARIO() {
		return ID_FUNCIONARIO;
	}

	public String getNOME_FUNCIONARIO() {
		return NOME_FUNCIONARIO;
	}

	public String getAVALIACAO_FINAL() {
		return AVALIACAO_FINAL;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_FORMACAO(Integer iD_FORMACAO) {
		ID_FORMACAO = iD_FORMACAO;
	}

	public void setID_FUNCIONARIO(String iD_FUNCIONARIO) {
		ID_FUNCIONARIO = iD_FUNCIONARIO;
	}

	public void setNOME_FUNCIONARIO(String nOME_FUNCIONARIO) {
		NOME_FUNCIONARIO = nOME_FUNCIONARIO;
	}

	public void setAVALIACAO_FINAL(String aVALIACAO_FINAL) {
		AVALIACAO_FINAL = aVALIACAO_FINAL;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

}