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
@Table(name = "MAN_MOV_MANUTENCAO_NOTAS")
public class MAN_MOV_MANUTENCAO_NOTAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("DESCRICAO2")
	private String DESCRICAO2;
	@JsonProperty("ESTADO")
	private String ESTADO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("ID_MANUTENCAO_CAB")
	private Integer ID_MANUTENCAO_CAB;

	public Integer getID() {
		return ID;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getDESCRICAO2() {
		return DESCRICAO2;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Integer getID_MANUTENCAO_CAB() {
		return ID_MANUTENCAO_CAB;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setDESCRICAO2(String dESCRICAO2) {
		DESCRICAO2 = dESCRICAO2;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setID_MANUTENCAO_CAB(Integer iD_MANUTENCAO_CAB) {
		ID_MANUTENCAO_CAB = iD_MANUTENCAO_CAB;
	}
}
