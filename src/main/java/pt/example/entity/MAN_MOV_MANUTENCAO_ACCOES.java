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
@Table(name = "MAN_MOV_MANUTENCAO_ACCOES")
public class MAN_MOV_MANUTENCAO_ACCOES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_MANUTENCAO_ACOES")
	private Integer ID_MANUTENCAO_ACOES;
	@JsonProperty("ID_MANUTENCAO_CAB")
	private Integer ID_MANUTENCAO_CAB;
	@JsonProperty("ID_ACAO")
	private Integer ID_ACAO;
	@JsonProperty("REALIZADA")
	private Boolean REALIZADA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("TEMPO")
	private Time TEMPO;
	@JsonProperty("TEMPO_ESTIMADO")
	private Time TEMPO_ESTIMADO;

	public Integer getID_MANUTENCAO_ACOES() {
		return ID_MANUTENCAO_ACOES;
	}

	public Integer getID_MANUTENCAO_CAB() {
		return ID_MANUTENCAO_CAB;
	}

	public Integer getID_ACAO() {
		return ID_ACAO;
	}

	public Boolean getREALIZADA() {
		return REALIZADA;
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

	public void setID_MANUTENCAO_ACOES(Integer iD_MANUTENCAO_ACOES) {
		ID_MANUTENCAO_ACOES = iD_MANUTENCAO_ACOES;
	}

	public void setID_MANUTENCAO_CAB(Integer iD_MANUTENCAO_CAB) {
		ID_MANUTENCAO_CAB = iD_MANUTENCAO_CAB;
	}

	public void setID_ACAO(Integer iD_ACAO) {
		ID_ACAO = iD_ACAO;
	}

	public void setREALIZADA(Boolean rEALIZADA) {
		REALIZADA = rEALIZADA;
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

	public Time getTEMPO() {
		return TEMPO;
	}

	public Time getTEMPO_ESTIMADO() {
		return TEMPO_ESTIMADO;
	}

	public void setTEMPO(Time tEMPO) {
		TEMPO = tEMPO;
	}

	public void setTEMPO_ESTIMADO(Time tEMPO_ESTIMADO) {
		TEMPO_ESTIMADO = tEMPO_ESTIMADO;
	}

}
