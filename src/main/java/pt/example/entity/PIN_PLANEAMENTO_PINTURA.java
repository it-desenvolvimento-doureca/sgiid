package pt.example.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_PLANEAMENTO_PINTURA")
public class PIN_PLANEAMENTO_PINTURA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("DATA")
	private Date DATA;
	@JsonProperty("HORA")
	private Time HORA;
	@JsonProperty("ESTADO")
	private String ESTADO;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("UTZ_ANULACAO")
	private Integer UTZ_ANULACAO;
	@JsonProperty("DATA_ANULACAO")
	private Timestamp DATA_ANULACAO;
	@JsonProperty("INATIVO")
	private Boolean INATIVO;
	@JsonProperty("DATA_CONCLUSAO")
	private Timestamp DATA_CONCLUSAO;

	public Integer getID() {
		return ID;
	}

	public Date getDATA() {
		return DATA;
	}

	public Time getHORA() {
		return HORA;
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

	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setDATA(Date dATA) {
		DATA = dATA;
	}

	public void setHORA(Time hORA) {
		HORA = hORA;
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

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public Timestamp getDATA_CONCLUSAO() {
		return DATA_CONCLUSAO;
	}

	public void setDATA_CONCLUSAO(Timestamp dATA_CONCLUSAO) {
		DATA_CONCLUSAO = dATA_CONCLUSAO;
	}

}