package pt.example.entity;

import java.sql.Date;
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
@Table(name = "COMU_FICHEIROS")
public class COMU_FICHEIROS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("NOME_FICHEIRO")
	private String NOME_FICHEIRO;
	@JsonProperty("ORIGINAL_NAME")
	private String ORIGINAL_NAME;
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
	private String ID_FICHEIRO;
	@JsonProperty("ID_PASTA")
	private String ID_PASTA;
	@JsonProperty("TIPO_FICHEIRO")
	private String TIPO_FICHEIRO;
	@JsonProperty("DATA_INICIO")
	private Date DATA_INICIO;
	@JsonProperty("DATA_FIM")
	private Date DATA_FIM;
	@JsonProperty("TEMPO")
	private Integer TEMPO;

	public Integer getID() {
		return ID;
	}

	public String getNOME_FICHEIRO() {
		return NOME_FICHEIRO;
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

	public String getID_FICHEIRO() {
		return ID_FICHEIRO;
	}

	public String getID_PASTA() {
		return ID_PASTA;
	}

	public String getTIPO_FICHEIRO() {
		return TIPO_FICHEIRO;
	}

	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	public Date getDATA_FIM() {
		return DATA_FIM;
	}

	public Integer getTEMPO() {
		return TEMPO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setNOME_FICHEIRO(String nOME_FICHEIRO) {
		NOME_FICHEIRO = nOME_FICHEIRO;
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

	public void setID_FICHEIRO(String iD_FICHEIRO) {
		ID_FICHEIRO = iD_FICHEIRO;
	}

	public void setID_PASTA(String iD_PASTA) {
		ID_PASTA = iD_PASTA;
	}

	public void setTIPO_FICHEIRO(String tIPO_FICHEIRO) {
		TIPO_FICHEIRO = tIPO_FICHEIRO;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Date dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public void setTEMPO(Integer tEMPO) {
		TEMPO = tEMPO;
	}

	public String getORIGINAL_NAME() {
		return ORIGINAL_NAME;
	}

	public void setORIGINAL_NAME(String oRIGINAL_NAME) {
		ORIGINAL_NAME = oRIGINAL_NAME;
	}

}