package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_DIC_PROGRAMAS_REFERENCIAS")
public class PIN_DIC_PROGRAMAS_REFERENCIAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("REFERENCIA")
	private String REFERENCIA;
	@JsonProperty("DESC_REFERENCIA")
	private String DESC_REFERENCIA;
	@JsonProperty("QUANTIDADE")
	private BigDecimal QUANTIDADE;
	@JsonProperty("ID_PROGRAMA")
	private Integer ID_PROGRAMA;
	@JsonProperty("REFERENCIA_INJETADA")
	private String REFERENCIA_INJETADA;
	@JsonProperty("DESC_REFERENCIA_INJETADA")
	private String DESC_REFERENCIA_INJETADA;

	public Integer getID() {
		return ID;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public String getDESC_REFERENCIA() {
		return DESC_REFERENCIA;
	}

	public BigDecimal getQUANTIDADE() {
		return QUANTIDADE;
	}

	public Integer getID_PROGRAMA() {
		return ID_PROGRAMA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public void setDESC_REFERENCIA(String dESC_REFERENCIA) {
		DESC_REFERENCIA = dESC_REFERENCIA;
	}

	public void setQUANTIDADE(BigDecimal qUANTIDADE) {
		QUANTIDADE = qUANTIDADE;
	}

	public void setID_PROGRAMA(Integer iD_PROGRAMA) {
		ID_PROGRAMA = iD_PROGRAMA;
	}

	public String getREFERENCIA_INJETADA() {
		return REFERENCIA_INJETADA;
	}

	public String getDESC_REFERENCIA_INJETADA() {
		return DESC_REFERENCIA_INJETADA;
	}

	public void setREFERENCIA_INJETADA(String rEFERENCIA_INJETADA) {
		REFERENCIA_INJETADA = rEFERENCIA_INJETADA;
	}

	public void setDESC_REFERENCIA_INJETADA(String dESC_REFERENCIA_INJETADA) {
		DESC_REFERENCIA_INJETADA = dESC_REFERENCIA_INJETADA;
	}

}
