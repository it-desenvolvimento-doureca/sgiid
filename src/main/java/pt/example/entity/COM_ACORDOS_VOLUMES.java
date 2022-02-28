package pt.example.entity;

import java.math.BigDecimal;
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
@Table(name = "COM_ACORDOS_VOLUMES")
public class COM_ACORDOS_VOLUMES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
    private Integer ID;
	@JsonProperty("ANO")
    private String ANO;
	@JsonProperty("VALOR")
    private BigDecimal VALOR;
	@JsonProperty("ID_ACORDO")
    private Integer ID_ACORDO;
	@JsonProperty("VERSAO")
	private Integer VERSAO;
	
	public Integer getID() {
		return ID;
	}
	public String getANO() {
		return ANO;
	}
	public BigDecimal getVALOR() {
		return VALOR;
	}
	public Integer getID_ACORDO() {
		return ID_ACORDO;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public void setANO(String aNO) {
		ANO = aNO;
	}
	public void setVALOR(BigDecimal vALOR) {
		VALOR = vALOR;
	}
	public void setID_ACORDO(Integer iD_ACORDO) {
		ID_ACORDO = iD_ACORDO;
	}
	public Integer getVERSAO() {
		return VERSAO;
	}
	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}
	
	
}
