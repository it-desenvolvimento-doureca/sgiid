package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
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
@Table(name = "PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS")
public class PIN_DIC_REGISTO_SALAS_MISTURA_REFERENCIAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_REGISTO_SALAS_MISTURA")
	private Integer ID_REGISTO_SALAS_MISTURA;
	@JsonProperty("ID_REFERENCIA")
	private Integer ID_REFERENCIA;
	@JsonProperty("REFERENCIA")
	private String REFERENCIA;
	@JsonProperty("VALOR")
	private BigDecimal VALOR;
	@JsonProperty("REFERENCIA_DESC")
	private String REFERENCIA_DESC;
	@JsonProperty("LOTE")
	private String LOTE;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getID_REGISTO_SALAS_MISTURA() {
		return ID_REGISTO_SALAS_MISTURA;
	}

	public void setID_REGISTO_SALAS_MISTURA(Integer iD_REGISTO_SALAS_MISTURA) {
		ID_REGISTO_SALAS_MISTURA = iD_REGISTO_SALAS_MISTURA;
	}

	public Integer getID_REFERENCIA() {
		return ID_REFERENCIA;
	}

	public void setID_REFERENCIA(Integer iD_REFERENCIA) {
		ID_REFERENCIA = iD_REFERENCIA;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public BigDecimal getVALOR() {
		return VALOR;
	}

	public void setVALOR(BigDecimal vALOR) {
		VALOR = vALOR;
	}

	public String getREFERENCIA_DESC() {
		return REFERENCIA_DESC;
	}

	public void setREFERENCIA_DESC(String rEFERENCIA_DESC) {
		REFERENCIA_DESC = rEFERENCIA_DESC;
	}

	public String getLOTE() {
		return LOTE;
	}

	public void setLOTE(String lOTE) {
		LOTE = lOTE;
	}

}
