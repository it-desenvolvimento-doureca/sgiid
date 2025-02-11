package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "GER_EVENTOS_DESTINATARIOS")
public class GER_EVENTOS_DESTINATARIOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_EVENTO")
	private Integer ID_EVENTO;
	@JsonProperty("EMAIL_PARA")
	private String EMAIL_PARA;
	@JsonProperty("TYPOF")
	private String TYPOF;

	public Integer getID() {
		return ID;
	}

	public Integer getID_EVENTO() {
		return ID_EVENTO;
	}

	public String getEMAIL_PARA() {
		return EMAIL_PARA;
	}

	public String getTYPOF() {
		return TYPOF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_EVENTO(Integer iD_EVENTO) {
		ID_EVENTO = iD_EVENTO;
	}

	public void setEMAIL_PARA(String eMAIL_PARA) {
		EMAIL_PARA = eMAIL_PARA;
	}

	public void setTYPOF(String tYPOF) {
		TYPOF = tYPOF;
	}

}
