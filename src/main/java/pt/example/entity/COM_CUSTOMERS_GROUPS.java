package pt.example.entity;

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
@Table(name = "COM_CUSTOMERS_GROUPS")
public class COM_CUSTOMERS_GROUPS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_CLIENTE")
	private String ID_CLIENTE;
	@JsonProperty("NOME_CLIENTE")
	private String NOME_CLIENTE;
	@JsonProperty("ID_CUSTOMER_GROUP")
	private String ID_CUSTOMER_GROUP;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	public Integer getID() {
		return ID;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public String getID_CLIENTE() {
		return ID_CLIENTE;
	}

	public void setID_CLIENTE(String iD_CLIENTE) {
		ID_CLIENTE = iD_CLIENTE;
	}

	public String getID_CUSTOMER_GROUP() {
		return ID_CUSTOMER_GROUP;
	}

	public void setID_CUSTOMER_GROUP(String iD_CUSTOMER_GROUP) {
		ID_CUSTOMER_GROUP = iD_CUSTOMER_GROUP;
	}

	public String getNOME_CLIENTE() {
		return NOME_CLIENTE;
	}

	public void setNOME_CLIENTE(String nOME_CLIENTE) {
		NOME_CLIENTE = nOME_CLIENTE;
	}

}
