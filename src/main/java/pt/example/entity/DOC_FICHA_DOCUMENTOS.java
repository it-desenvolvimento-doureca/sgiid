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
@Table(name = "DOC_FICHA_DOCUMENTOS")
public class DOC_FICHA_DOCUMENTOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("SECTOR")
	private String SECTOR;
	@JsonProperty("DESC_MAQUINA")
	private String DESC_MAQUINA;
	@JsonProperty("COD_MAQUINA")
	private String COD_MAQUINA;
	@JsonProperty("REFERENCIA")
	private String REFERENCIA;
	@JsonProperty("DESC_REFERENCIA")
	private String DESC_REFERENCIA;
	@JsonProperty("NOME_ABA")
	private String NOME_ABA;
	@JsonProperty("TIPO_DOCUMENTO")
	private Integer TIPO_DOCUMENTO;
	@JsonProperty("ORDEM")
	private Integer ORDEM;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("DATA_ANULA")
	private Timestamp DATA_ANULA;
	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;
	@JsonProperty("INATIVO")
	private Boolean INATIVO;
	@JsonProperty("ID_FICHEIRO")
	private Integer ID_FICHEIRO;

	public Integer getID() {
		return ID;
	}

	public String getSECTOR() {
		return SECTOR;
	}

	public String getDESC_MAQUINA() {
		return DESC_MAQUINA;
	}

	public String getCOD_MAQUINA() {
		return COD_MAQUINA;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public String getDESC_REFERENCIA() {
		return DESC_REFERENCIA;
	}

	public String getNOME_ABA() {
		return NOME_ABA;
	}

	public Integer getTIPO_DOCUMENTO() {
		return TIPO_DOCUMENTO;
	}

	public Integer getORDEM() {
		return ORDEM;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setSECTOR(String sECTOR) {
		SECTOR = sECTOR;
	}

	public void setDESC_MAQUINA(String dESC_MAQUINA) {
		DESC_MAQUINA = dESC_MAQUINA;
	}

	public void setCOD_MAQUINA(String cOD_MAQUINA) {
		COD_MAQUINA = cOD_MAQUINA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public void setDESC_REFERENCIA(String dESC_REFERENCIA) {
		DESC_REFERENCIA = dESC_REFERENCIA;
	}

	public void setNOME_ABA(String nOME_ABA) {
		NOME_ABA = nOME_ABA;
	}

	public void setTIPO_DOCUMENTO(Integer tIPO_DOCUMENTO) {
		TIPO_DOCUMENTO = tIPO_DOCUMENTO;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public Integer getID_FICHEIRO() {
		return ID_FICHEIRO;
	}

	public void setID_FICHEIRO(Integer iD_FICHEIRO) {
		ID_FICHEIRO = iD_FICHEIRO;
	}

}
