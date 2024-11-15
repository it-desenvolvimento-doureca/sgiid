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
@Table(name = "MAN_MOV_MANUTENCAO_PAUSAS")
public class MAN_MOV_MANUTENCAO_PAUSAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty( "ID_MANUTENCAO_PAUSA")
	private Integer ID_MANUTENCAO_PAUSA;
	@JsonProperty( "ID_MANUTENCAO_OPERARIO")
	private Integer ID_MANUTENCAO_OPERARIO;
	@JsonProperty( "ID_OPERARIO")
	private Integer ID_OPERARIO;
	@JsonProperty( "DATA_INICIO")
	private Timestamp DATA_INICIO;
	@JsonProperty( "DATA_FIM")
	private Timestamp DATA_FIM;
	@JsonProperty( "TIPO_PARAGEM")
	private String TIPO_PARAGEM;
	@JsonProperty( "DES_PARAGEM")
	private String DES_PARAGEM;
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

	public Integer getID_MANUTENCAO_PAUSA() {
		return ID_MANUTENCAO_PAUSA;
	}

	public Integer getID_MANUTENCAO_OPERARIO() {
		return ID_MANUTENCAO_OPERARIO;
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

	public String getTIPO_PARAGEM() {
		return TIPO_PARAGEM;
	}

	public String getDES_PARAGEM() {
		return DES_PARAGEM;
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

	public void setID_MANUTENCAO_PAUSA(Integer iD_MANUTENCAO_PAUSA) {
		ID_MANUTENCAO_PAUSA = iD_MANUTENCAO_PAUSA;
	}

	public void setID_MANUTENCAO_OPERARIO(Integer iD_MANUTENCAO_OPERARIO) {
		ID_MANUTENCAO_OPERARIO = iD_MANUTENCAO_OPERARIO;
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

	public void setTIPO_PARAGEM(String tIPO_PARAGEM) {
		TIPO_PARAGEM = tIPO_PARAGEM;
	}

	public void setDES_PARAGEM(String dES_PARAGEM) {
		DES_PARAGEM = dES_PARAGEM;
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
