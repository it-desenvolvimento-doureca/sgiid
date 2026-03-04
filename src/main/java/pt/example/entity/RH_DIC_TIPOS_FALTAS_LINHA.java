package pt.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_DIC_TIPOS_FALTAS_LINHA")
public class RH_DIC_TIPOS_FALTAS_LINHA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("COD_FALTA")
	private String COD_FALTA;

	@JsonProperty("DESC_FALTA")
	private String DESC_FALTA;

	public Integer getID() {
		return ID;
	}

	public String getCOD_FALTA() {
		return COD_FALTA;
	}

	public String getDESC_FALTA() {
		return DESC_FALTA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setCOD_FALTA(String cOD_FALTA) {
		COD_FALTA = cOD_FALTA;
	}

	public void setDESC_FALTA(String dESC_FALTA) {
		DESC_FALTA = dESC_FALTA;
	}

}