package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_DIC_ACOES_PECAS_CRITICAS")
public class PR_DIC_ACOES_PECAS_CRITICAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("DESCRICAO")
	private String DESCRICAO;

	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;

	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;

	public Integer getID() {
		return ID;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

}
