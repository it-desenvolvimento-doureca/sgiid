package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_MOV_PREPARACAO_LINHA")
public class PIN_MOV_PREPARACAO_LINHA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_PREPARACAO_LIN")
	private Integer ID_PREPARACAO_LIN;
	@JsonProperty("ID_PREPARACAO_CAB")
	private Integer ID_PREPARACAO_CAB;
	@JsonProperty("ID_PRODUTO")
	private Integer ID_PRODUTO;
	@JsonProperty("COD_REF")
	private String COD_REF;
	@JsonProperty("STOCK")
	private BigDecimal STOCK;
	@JsonProperty("VALOR")
	private String VALOR;
	@JsonProperty("ID_UNIDADE")
	private Integer ID_UNIDADE;
	@JsonProperty("HORA_PREVISTA")
	private Time HORA_PREVISTA;
	@JsonProperty("OBS_PLANEAMENTO")
	private String OBS_PLANEAMENTO;
	@JsonProperty("STKUNIT")
	private String STKUNIT;
	@JsonProperty("NOME_REF")
	private String NOME_REF;
	@JsonProperty("LIECOD")
	private String LIECOD;

	public Integer getID_PREPARACAO_LIN() {
		return ID_PREPARACAO_LIN;
	}

	public void setID_PREPARACAO_LIN(Integer iD_PREPARACAO_LIN) {
		ID_PREPARACAO_LIN = iD_PREPARACAO_LIN;
	}

	public Integer getID_PREPARACAO_CAB() {
		return ID_PREPARACAO_CAB;
	}

	public void setID_PREPARACAO_CAB(Integer iD_PREPARACAO_CAB) {
		ID_PREPARACAO_CAB = iD_PREPARACAO_CAB;
	}

	public Integer getID_PRODUTO() {
		return ID_PRODUTO;
	}

	public void setID_PRODUTO(Integer iD_PRODUTO) {
		ID_PRODUTO = iD_PRODUTO;
	}

	public String getCOD_REF() {
		return COD_REF;
	}

	public void setCOD_REF(String cOD_REF) {
		COD_REF = cOD_REF;
	}

	public BigDecimal getSTOCK() {
		return STOCK;
	}

	public void setSTOCK(BigDecimal sTOCK) {
		STOCK = sTOCK;
	}

	public String getVALOR() {
		return VALOR;
	}

	public void setVALOR(String vALOR) {
		VALOR = vALOR;
	}

	public Integer getID_UNIDADE() {
		return ID_UNIDADE;
	}

	public void setID_UNIDADE(Integer iD_UNIDADE) {
		ID_UNIDADE = iD_UNIDADE;
	}

	public Time getHORA_PREVISTA() {
		return HORA_PREVISTA;
	}

	public void setHORA_PREVISTA(Time hORA_PREVISTA) {
		HORA_PREVISTA = hORA_PREVISTA;
	}

	public String getOBS_PLANEAMENTO() {
		return OBS_PLANEAMENTO;
	}

	public void setOBS_PLANEAMENTO(String oBS_PLANEAMENTO) {
		OBS_PLANEAMENTO = oBS_PLANEAMENTO;
	}

	public String getSTKUNIT() {
		return STKUNIT;
	}

	public void setSTKUNIT(String sTKUNIT) {
		STKUNIT = sTKUNIT;
	}

	public String getNOME_REF() {
		return NOME_REF;
	}

	public void setNOME_REF(String nOME_REF) {
		NOME_REF = nOME_REF;
	}

	public String getLIECOD() {
		return LIECOD;
	}

	public void setLIECOD(String lIECOD) {
		LIECOD = lIECOD;
	}

}
