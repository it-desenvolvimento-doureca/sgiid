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
	@JsonProperty("COD_DOCUMENTO")
	private String COD_DOCUMENTO;
	@JsonProperty("NOME_DOCUMENTO")
	private String NOME_DOCUMENTO;
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
	private String ID_FICHEIRO;
	@JsonProperty("ID_PASTA")
	private String ID_PASTA;
	@JsonProperty("CAMINHO")
	private String CAMINHO;
	@JsonProperty("NOME_FICHEIRO")
	private String NOME_FICHEIRO;
	@JsonProperty("TIPO_FICHEIRO")
	private String TIPO_FICHEIRO;
	@JsonProperty("ID_CAMINHO")
	private Integer ID_CAMINHO;
	

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

	public String getCOD_DOCUMENTO() {
		return COD_DOCUMENTO;
	}

	public String getNOME_DOCUMENTO() {
		return NOME_DOCUMENTO;
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

	public void setCOD_DOCUMENTO(String cOD_DOCUMENTO) {
		COD_DOCUMENTO = cOD_DOCUMENTO;
	}

	public void setNOME_DOCUMENTO(String nOME_DOCUMENTO) {
		NOME_DOCUMENTO = nOME_DOCUMENTO;
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

	public String getID_FICHEIRO() {
		return ID_FICHEIRO;
	}

	public String getID_PASTA() {
		return ID_PASTA;
	}

	public String getCAMINHO() {
		return CAMINHO;
	}

	public String getNOME_FICHEIRO() {
		return NOME_FICHEIRO;
	}

	public void setID_FICHEIRO(String iD_FICHEIRO) {
		ID_FICHEIRO = iD_FICHEIRO;
	}

	public void setID_PASTA(String iD_PASTA) {
		ID_PASTA = iD_PASTA;
	}

	public void setCAMINHO(String cAMINHO) {
		CAMINHO = cAMINHO;
	}

	public void setNOME_FICHEIRO(String nOME_FICHEIRO) {
		NOME_FICHEIRO = nOME_FICHEIRO;
	}

	public String getTIPO_FICHEIRO() {
		return TIPO_FICHEIRO;
	}

	public void setTIPO_FICHEIRO(String tIPO_FICHEIRO) {
		TIPO_FICHEIRO = tIPO_FICHEIRO;
	}

	public Integer getID_CAMINHO() {
		return ID_CAMINHO;
	}

	public void setID_CAMINHO(Integer iD_CAMINHO) {
		ID_CAMINHO = iD_CAMINHO;
	}

}
