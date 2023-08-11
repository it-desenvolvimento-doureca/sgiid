package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_MOV_RECEITAS")
public class PIN_MOV_RECEITAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
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
	@JsonProperty("NOME_PROJETO")
	private String NOME_PROJETO;
	@JsonProperty("MASTER")
	private String MASTER;
	@JsonProperty("LIMPEZA_PIA")
	private Boolean LIMPEZA_PIA;
	@JsonProperty("CO2")
	private Boolean CO2;
	@JsonProperty("CURA")
	private Integer CURA;
	@JsonProperty("JOB")
	private String JOB;
	@JsonProperty("COR")
	private String COR;
	@JsonProperty("LINHA")
	private Integer LINHA;
	@JsonProperty("COD_FORNECEDOR")
	private String COD_FORNECEDOR;
	@JsonProperty("NOME_FORNECEDOR")
	private String NOME_FORNECEDOR;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
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

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public String getNOME_PROJETO() {
		return NOME_PROJETO;
	}

	public void setNOME_PROJETO(String nOME_PROJETO) {
		NOME_PROJETO = nOME_PROJETO;
	}

	public String getMASTER() {
		return MASTER;
	}

	public void setMASTER(String mASTER) {
		MASTER = mASTER;
	}

	public Boolean getLIMPEZA_PIA() {
		return LIMPEZA_PIA;
	}

	public void setLIMPEZA_PIA(Boolean lIMPEZA_PIA) {
		LIMPEZA_PIA = lIMPEZA_PIA;
	}

	public Boolean getCO2() {
		return CO2;
	}

	public void setCO2(Boolean cO2) {
		CO2 = cO2;
	}

	public Integer getCURA() {
		return CURA;
	}

	public void setCURA(Integer cURA) {
		CURA = cURA;
	}

	public String getJOB() {
		return JOB;
	}

	public void setJOB(String jOB) {
		JOB = jOB;
	}

	public String getCOR() {
		return COR;
	}

	public void setCOR(String cOR) {
		COR = cOR;
	}

	public Integer getLINHA() {
		return LINHA;
	}

	public void setLINHA(Integer lINHA) {
		LINHA = lINHA;
	}

	public String getCOD_FORNECEDOR() {
		return COD_FORNECEDOR;
	}

	public void setCOD_FORNECEDOR(String cOD_FORNECEDOR) {
		COD_FORNECEDOR = cOD_FORNECEDOR;
	}

	public String getNOME_FORNECEDOR() {
		return NOME_FORNECEDOR;
	}

	public void setNOME_FORNECEDOR(String nOME_FORNECEDOR) {
		NOME_FORNECEDOR = nOME_FORNECEDOR;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

}