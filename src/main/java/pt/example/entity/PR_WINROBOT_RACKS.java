package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_WINROBOT_RACKS")
public class PR_WINROBOT_RACKS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("RACK_CODE")
	private String RACK_CODE;
	@JsonProperty("RACK_TYPE")
	private String RACK_TYPE;
	@JsonProperty("ID_CAB")
	private Integer ID_CAB;
	@JsonProperty("UTZ_CRIA")
	private String UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	public Integer getID() {
		return ID;
	}

	public String getRACK_CODE() {
		return RACK_CODE;
	}

	public String getRACK_TYPE() {
		return RACK_TYPE;
	}

	public Integer getID_CAB() {
		return ID_CAB;
	}

	public String getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setRACK_CODE(String rACK_CODE) {
		RACK_CODE = rACK_CODE;
	}

	public void setRACK_TYPE(String rACK_TYPE) {
		RACK_TYPE = rACK_TYPE;
	}

	public void setID_CAB(Integer iD_CAB) {
		ID_CAB = iD_CAB;
	}

	public void setUTZ_CRIA(String uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

}