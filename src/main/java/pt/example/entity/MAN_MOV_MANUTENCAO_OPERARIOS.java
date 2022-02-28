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
@Table(name = "VERSAO_APP")
public class MAN_MOV_MANUTENCAO_OPERARIOS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty( "ID_MANUTENCAO_OPERARIOS")
	private Integer ID_MANUTENCAO_OPERARIOS;
	@JsonProperty( "ID_MANUTENCAO_CAB")
	private Integer ID_MANUTENCAO_CAB;
	@JsonProperty( "ID_OPERARIO")
	private Integer ID_OPERARIO;
	@JsonProperty( "DATA_INICIO")
	private Timestamp DATA_INICIO;
	@JsonProperty( "DATA_FIM")
	private Timestamp DATA_FIM;
	@JsonProperty( "TEMP_EXEC")
	private String TEMP_EXEC;
	@JsonProperty( "TEMP_PAUSA")
	private String TEMP_PAUSA;
	@JsonProperty( "TEMP_TOTAL")
	private String TEMP_TOTAL;
	@JsonProperty( "ESTADO")
	private String ESTADO;
	@JsonProperty( "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty( "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty( "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty( "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;

	public Integer getID_MANUTENCAO_OPERARIOS() {
		return ID_MANUTENCAO_OPERARIOS;
	}

	public Integer getID_MANUTENCAO_CAB() {
		return ID_MANUTENCAO_CAB;
	}

	public Integer getID_OPERARIO() {
		return ID_OPERARIO;
	}

	public Timestamp getDATA_INICIO() {
		return DATA_INICIO;
	}

	public Timestamp getDATA_FIM() {
		return DATA_FIM;
	}

	public String getTEMP_EXEC() {
		return TEMP_EXEC;
	}

	public String getTEMP_PAUSA() {
		return TEMP_PAUSA;
	}

	public String getTEMP_TOTAL() {
		return TEMP_TOTAL;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setID_MANUTENCAO_OPERARIOS(Integer iD_MANUTENCAO_OPERARIOS) {
		ID_MANUTENCAO_OPERARIOS = iD_MANUTENCAO_OPERARIOS;
	}

	public void setID_MANUTENCAO_CAB(Integer iD_MANUTENCAO_CAB) {
		ID_MANUTENCAO_CAB = iD_MANUTENCAO_CAB;
	}

	public void setID_OPERARIO(Integer iD_OPERARIO) {
		ID_OPERARIO = iD_OPERARIO;
	}

	public void setDATA_INICIO(Timestamp dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Timestamp dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public void setTEMP_EXEC(String tEMP_EXEC) {
		TEMP_EXEC = tEMP_EXEC;
	}

	public void setTEMP_PAUSA(String tEMP_PAUSA) {
		TEMP_PAUSA = tEMP_PAUSA;
	}

	public void setTEMP_TOTAL(String tEMP_TOTAL) {
		TEMP_TOTAL = tEMP_TOTAL;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

}
