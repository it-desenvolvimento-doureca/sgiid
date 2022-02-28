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
@Table(name = "COM_REFERENCIAS_SILVER")
public class COM_REFERENCIAS_SILVER {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_REFERENCIA")
	private Integer ID_REFERENCIA;
	@JsonProperty("COD_REFERENCIA_SILVER")
	private String COD_REFERENCIA_SILVER;
	@JsonProperty("DESC_REFERENCIA_SILVER")
	private String DESC_REFERENCIA_SILVER;

	public Integer getID() {
		return ID;
	}

	public Integer getID_REFERENCIA() {
		return ID_REFERENCIA;
	}

	public String getCOD_REFERENCIA_SILVER() {
		return COD_REFERENCIA_SILVER;
	}

	public String getDESC_REFERENCIA_SILVER() {
		return DESC_REFERENCIA_SILVER;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_REFERENCIA(Integer iD_REFERENCIA) {
		ID_REFERENCIA = iD_REFERENCIA;
	}

	public void setCOD_REFERENCIA_SILVER(String cOD_REFERENCIA_SILVER) {
		COD_REFERENCIA_SILVER = cOD_REFERENCIA_SILVER;
	}

	public void setDESC_REFERENCIA_SILVER(String dESC_REFERENCIA_SILVER) {
		DESC_REFERENCIA_SILVER = dESC_REFERENCIA_SILVER;
	}

}
