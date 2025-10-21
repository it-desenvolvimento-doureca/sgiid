package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_ANALISE_PRODUTIVIDADE_REFERENCIAS")
public class PR_ANALISE_PRODUTIVIDADE_REFERENCIAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;

	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	@JsonProperty("PROREF")
	private String PROREF;

	// Getters e Setters
	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer UTZ_CRIA) {
		this.UTZ_CRIA = UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp DATA_CRIA) {
		this.DATA_CRIA = DATA_CRIA;
	}

	public String getPROREF() {
		return PROREF;
	}

	public void setPROREF(String PROREF) {
		this.PROREF = PROREF;
	}
}