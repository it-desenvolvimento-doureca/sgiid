package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "FIN_DIC_FILTROS")
public class FIN_DIC_FILTROS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("TIPO_ANALISE")
	private Integer TIPO_ANALISE;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("PROTYPCOD")
	private String PROTYPCOD;
	@JsonProperty("ACHFAMCOD")
	private String ACHFAMCOD;

	public Integer getID() {
		return ID;
	}

	public Integer getTIPO_ANALISE() {
		return TIPO_ANALISE;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getPROTYPCOD() {
		return PROTYPCOD;
	}

	public String getACHFAMCOD() {
		return ACHFAMCOD;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setTIPO_ANALISE(Integer tIPO_ANALISE) {
		TIPO_ANALISE = tIPO_ANALISE;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setPROTYPCOD(String pROTYPCOD) {
		PROTYPCOD = pROTYPCOD;
	}

	public void setACHFAMCOD(String aCHFAMCOD) {
		ACHFAMCOD = aCHFAMCOD;
	}

}
