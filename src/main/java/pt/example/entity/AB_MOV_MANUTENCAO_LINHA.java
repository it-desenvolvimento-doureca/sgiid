package pt.example.entity;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_MOV_MANUTENCAO_LINHA")
public class AB_MOV_MANUTENCAO_LINHA {

	private Integer ID_MANUTENCAO_LIN;
	private Integer ID_MANUTENCAO_CAB;
	private Integer ID_ADITIVO;
	private Integer ID_UNIDADE1;
	private String COD_REF;
	private Integer ID_UNIDADE2;
	private String VALOR1;
	private String VALOR2;
	private String HORA_PREVISTA;
	private String OBS_PLANEAMENTO;
	private Float STOCK;
	private Float VALOR_AGUA;
	private String STKUNIT;

	@Id
	@Column(name = "ID_MANUTENCAO_LIN")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_MANUTENCAO_LIN() {
		return ID_MANUTENCAO_LIN;
	}

	@Column(name = "ID_ADITIVO")
	public Integer getID_ADITIVO() {
		return ID_ADITIVO;
	}

	@Column(name = "ID_MANUTENCAO_CAB")
	public Integer getID_MANUTENCAO_CAB() {
		return ID_MANUTENCAO_CAB;
	}

	@Column(name = "ID_UNIDADE1")
	public Integer getID_UNIDADE1() {
		return ID_UNIDADE1;
	}

	@Column(name = "ID_UNIDADE2")
	public Integer getID_UNIDADE2() {
		return ID_UNIDADE2;
	}

	@Column(name = "VALOR1")
	public String getVALOR1() {
		return VALOR1;
	}

	@Column(name = "VALOR2")
	public String getVALOR2() {
		return VALOR2;
	}

	@Column(name = "HORA_PREVISTA")
	public String getHORA_PREVISTA() {
		return HORA_PREVISTA;
	}

	@Column(name = "OBS_PLANEAMENTO")
	public String getOBS_PLANEAMENTO() {
		return OBS_PLANEAMENTO;
	}

	@Column(name = "STOCK")
	public Float getSTOCK() {
		return STOCK;
	}

	@Column(name = "COD_REF")
	public String getCOD_REF() {
		return COD_REF;
	}

	@Column(name = "STKUNIT")
	public String getSTKUNIT() {
		return STKUNIT;
	}
	
	@Column(name = "VALOR_AGUA")
	public Float getVALOR_AGUA() {
		return VALOR_AGUA;
	}

	public void setVALOR_AGUA(Float vALOR_AGUA) {
		VALOR_AGUA = vALOR_AGUA;
	}

	public void setSTKUNIT(String sTKUNIT) {
		STKUNIT = sTKUNIT;
	}

	public void setCOD_REF(String cOD_REF) {
		COD_REF = cOD_REF;
	}

	public void setSTOCK(Float sTOCK) {
		STOCK = sTOCK;
	}

	public void setID_MANUTENCAO_CAB(Integer iD_MANUTENCAO_CAB) {
		ID_MANUTENCAO_CAB = iD_MANUTENCAO_CAB;
	}

	public void setID_UNIDADE1(Integer iD_UNIDADE1) {
		ID_UNIDADE1 = iD_UNIDADE1;
	}

	public void setID_UNIDADE2(Integer iD_UNIDADE2) {
		ID_UNIDADE2 = iD_UNIDADE2;
	}

	public void setVALOR1(String vALOR1) {
		VALOR1 = vALOR1;
	}

	public void setVALOR2(String vALOR2) {
		VALOR2 = vALOR2;
	}

	public void setHORA_PREVISTA(String hORA_PREVISTA) {
		HORA_PREVISTA = hORA_PREVISTA;
	}

	public void setOBS_PLANEAMENTO(String oBS_PLANEAMENTO) {
		OBS_PLANEAMENTO = oBS_PLANEAMENTO;
	}

	public void setID_MANUTENCAO_LIN(Integer iD_MANUTENCAO_LIN) {
		ID_MANUTENCAO_LIN = iD_MANUTENCAO_LIN;
	}

	public void setID_ADITIVO(Integer iD_ADITIVO) {
		ID_ADITIVO = iD_ADITIVO;
	}

}
