package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_DIC_ENTIDADE_MEDICA_LOCAIS")
public class RH_DIC_ENTIDADE_MEDICA_LOCAIS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("NOME")
	private String NOME;
	@JsonProperty("MORADA")
	private String MORADA;
	@JsonProperty("LOCALIDADE")
	private String LOCALIDADE;
	@JsonProperty("CODIGO_POSTAL")
	private String CODIGO_POSTAL;
	@JsonProperty("ID_ENTIDADE_MEDIA")
	private Integer ID_ENTIDADE_MEDIA;

	public Integer getID() {
		return ID;
	}

	public String getNOME() {
		return NOME;
	}

	public String getMORADA() {
		return MORADA;
	}

	public String getLOCALIDADE() {
		return LOCALIDADE;
	}

	public String getCODIGO_POSTAL() {
		return CODIGO_POSTAL;
	}

	public Integer getID_ENTIDADE_MEDIA() {
		return ID_ENTIDADE_MEDIA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setMORADA(String mORADA) {
		MORADA = mORADA;
	}

	public void setLOCALIDADE(String lOCALIDADE) {
		LOCALIDADE = lOCALIDADE;
	}

	public void setCODIGO_POSTAL(String cODIGO_POSTAL) {
		CODIGO_POSTAL = cODIGO_POSTAL;
	}

	public void setID_ENTIDADE_MEDIA(Integer iD_ENTIDADE_MEDIA) {
		ID_ENTIDADE_MEDIA = iD_ENTIDADE_MEDIA;
	}

}
