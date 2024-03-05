package pt.example.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@IdClass(PK_PIN_MOV_RECEITAS.class)
@Table(name = "PIN_MOV_RECEITAS")
public class PIN_MOV_RECEITAS implements Serializable{
	@Id 
	@JsonProperty("ID")
	private Integer ID;
	@Id
	@JsonProperty("VERSAO")
    private Integer VERSAO;
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
	@JsonProperty("LIMPEZA_IPA")
	private Boolean LIMPEZA_IPA;
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
	@JsonProperty("FLAMEADO")
	private Boolean FLAMEADO;
	@JsonProperty("REFERENCIA_PINTURA")
	private String REFERENCIA_PINTURA;
	@JsonProperty("NOME_REFERENCIA_PINTURA")
	private String NOME_REFERENCIA_PINTURA;
	@JsonProperty("ASPETO")
	private String ASPETO;
	@JsonProperty("ACABAMENTOS")
	private String ACABAMENTOS;
	@JsonProperty("PRIMARIO")
	private String PRIMARIO;
	@JsonProperty("BASE")
	private String BASE;
	@JsonProperty("VERNIZ")
	private String VERNIZ;
	@JsonProperty("CARTELA")
	private String CARTELA;
	@JsonProperty("RACK")
	private String RACK;
	@JsonProperty("NOME_COR")
	private String NOME_COR;
	@JsonProperty("COD_ASPETO")
	private String COD_ASPETO;
	@JsonProperty("QUANTIDADES_RACK")
	private Integer QUANTIDADES_RACK;
	@JsonProperty("PECAS_RACK")
	private Integer PECAS_RACK;
	@JsonProperty("VERSAO_ATIVA")
	private Boolean VERSAO_ATIVA;
	@JsonProperty("RECEITA_INATIVA")
	private Boolean RECEITA_INATIVA;
	@JsonProperty("ID_COR")
	private Integer ID_COR;
	@JsonProperty("TAXA_REJEICAO")
	private Float TAXA_REJEICAO;
	

	public Float getTAXA_REJEICAO() {
		return TAXA_REJEICAO;
	}

	public void setTAXA_REJEICAO(Float tAXA_REJEICAO) {
		TAXA_REJEICAO = tAXA_REJEICAO;
	}

	public Boolean getVERSAO_ATIVA() {
		return VERSAO_ATIVA;
	}

	public void setVERSAO_ATIVA(Boolean vERSAO_ATIVA) {
		VERSAO_ATIVA = vERSAO_ATIVA;
	}

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

	public Boolean getLIMPEZA_IPA() {
		return LIMPEZA_IPA;
	}

	public void setLIMPEZA_IPA(Boolean lIMPEZA_IPA) {
		LIMPEZA_IPA = lIMPEZA_IPA;
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

	public Boolean getFLAMEADO() {
		return FLAMEADO;
	}

	public void setFLAMEADO(Boolean fLAMEADO) {
		FLAMEADO = fLAMEADO;
	}

	public String getREFERENCIA_PINTURA() {
		return REFERENCIA_PINTURA;
	}

	public void setREFERENCIA_PINTURA(String rEFERENCIA_PINTURA) {
		REFERENCIA_PINTURA = rEFERENCIA_PINTURA;
	}

	public String getNOME_REFERENCIA_PINTURA() {
		return NOME_REFERENCIA_PINTURA;
	}

	public void setNOME_REFERENCIA_PINTURA(String nOME_REFERENCIA_PINTURA) {
		NOME_REFERENCIA_PINTURA = nOME_REFERENCIA_PINTURA;
	}

	public String getASPETO() {
		return ASPETO;
	}

	public void setASPETO(String aSPETO) {
		ASPETO = aSPETO;
	}

	public String getACABAMENTOS() {
		return ACABAMENTOS;
	}

	public void setACABAMENTOS(String aCABAMENTOS) {
		ACABAMENTOS = aCABAMENTOS;
	}

	public String getPRIMARIO() {
		return PRIMARIO;
	}

	public void setPRIMARIO(String pRIMARIO) {
		PRIMARIO = pRIMARIO;
	}

	public String getBASE() {
		return BASE;
	}

	public void setBASE(String bASE) {
		BASE = bASE;
	}

	public String getVERNIZ() {
		return VERNIZ;
	}

	public void setVERNIZ(String vERNIZ) {
		VERNIZ = vERNIZ;
	}

	public String getCARTELA() {
		return CARTELA;
	}

	public void setCARTELA(String cARTELA) {
		CARTELA = cARTELA;
	}

	public String getRACK() {
		return RACK;
	}

	public void setRACK(String rACK) {
		RACK = rACK;
	}

	public String getNOME_COR() {
		return NOME_COR;
	}

	public void setNOME_COR(String nOME_COR) {
		NOME_COR = nOME_COR;
	}

	public String getCOD_ASPETO() {
		return COD_ASPETO;
	}

	public void setCOD_ASPETO(String cOD_ASPETO) {
		COD_ASPETO = cOD_ASPETO;
	}

	public Integer getQUANTIDADES_RACK() {
		return QUANTIDADES_RACK;
	}

	public void setQUANTIDADES_RACK(Integer qUANTIDADES_RACK) {
		QUANTIDADES_RACK = qUANTIDADES_RACK;
	}

	public Integer getPECAS_RACK() {
		return PECAS_RACK;
	}

	public void setPECAS_RACK(Integer pECAS_RACK) {
		PECAS_RACK = pECAS_RACK;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

	public Boolean getRECEITA_INATIVA() {
		return RECEITA_INATIVA;
	}

	public void setRECEITA_INATIVA(Boolean rECEITA_INATIVA) {
		RECEITA_INATIVA = rECEITA_INATIVA;
	}

	public Integer getID_COR() {
		return ID_COR;
	}

	public void setID_COR(Integer iD_COR) {
		ID_COR = iD_COR;
	}

}