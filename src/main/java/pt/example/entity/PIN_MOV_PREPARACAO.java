package pt.example.entity;

import java.sql.Time;
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
@Table(name = "PIN_MOV_PREPARACAO")
public class PIN_MOV_PREPARACAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_PREPARACAO")
	private Integer ID_PREPARACAO;
	@JsonProperty("ID_LINHA")
	private Integer ID_LINHA;
	@JsonProperty("ID_TURNO")
	private Integer ID_TURNO;
	@JsonProperty("DATA_PLANEAMENTO")
	private Date DATA_PLANEAMENTO;
	@JsonProperty("HORA_PLANEAMENTO")
	private String HORA_PLANEAMENTO;
	@JsonProperty("UTZ_PLANEAMENTO")
	private Integer UTZ_PLANEAMENTO;
	@JsonProperty("ESTADO")
	private String ESTADO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("INATIVO")
	private Boolean INATIVO;
	@JsonProperty("DATA_ANULACAO")
	private Timestamp DATA_ANULACAO;
	@JsonProperty("UTZ_ANULACAO")
	private Integer UTZ_ANULACAO;
	@JsonProperty("IMPRESSO")
	private Boolean IMPRESSO;
	@JsonProperty("DATA_ULT_IMPRES")
	private Timestamp DATA_ULT_IMPRES;
	@JsonProperty("UTZ_ULT_IMPRES")
	private Integer UTZ_ULT_IMPRES;
	@JsonProperty("CLASSIF")
	private String CLASSIF;

	public Integer getID_PREPARACAO() {
		return ID_PREPARACAO;
	}

	public void setID_PREPARACAO(Integer iD_PREPARACAO) {
		ID_PREPARACAO = iD_PREPARACAO;
	}

	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public Integer getID_TURNO() {
		return ID_TURNO;
	}

	public void setID_TURNO(Integer iD_TURNO) {
		ID_TURNO = iD_TURNO;
	}

	public Date getDATA_PLANEAMENTO() {
		return DATA_PLANEAMENTO;
	}

	public void setDATA_PLANEAMENTO(Date dATA_PLANEAMENTO) {
		DATA_PLANEAMENTO = dATA_PLANEAMENTO;
	}

	public String getHORA_PLANEAMENTO() {
		return HORA_PLANEAMENTO;
	}

	public void setHORA_PLANEAMENTO(String hORA_PLANEAMENTO) {
		HORA_PLANEAMENTO = hORA_PLANEAMENTO;
	}

	public Integer getUTZ_PLANEAMENTO() {
		return UTZ_PLANEAMENTO;
	}

	public void setUTZ_PLANEAMENTO(Integer uTZ_PLANEAMENTO) {
		UTZ_PLANEAMENTO = uTZ_PLANEAMENTO;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public Boolean getIMPRESSO() {
		return IMPRESSO;
	}

	public void setIMPRESSO(Boolean iMPRESSO) {
		IMPRESSO = iMPRESSO;
	}

	public Timestamp getDATA_ULT_IMPRES() {
		return DATA_ULT_IMPRES;
	}

	public void setDATA_ULT_IMPRES(Timestamp dATA_ULT_IMPRES) {
		DATA_ULT_IMPRES = dATA_ULT_IMPRES;
	}

	public Integer getUTZ_ULT_IMPRES() {
		return UTZ_ULT_IMPRES;
	}

	public void setUTZ_ULT_IMPRES(Integer uTZ_ULT_IMPRES) {
		UTZ_ULT_IMPRES = uTZ_ULT_IMPRES;
	}

	public String getCLASSIF() {
		return CLASSIF;
	}

	public void setCLASSIF(String cLASSIF) {
		CLASSIF = cLASSIF;
	}

}
