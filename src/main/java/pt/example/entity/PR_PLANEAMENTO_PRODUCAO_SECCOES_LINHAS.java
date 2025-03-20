package pt.example.entity;

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
@Table(name = "PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS")
public class PR_PLANEAMENTO_PRODUCAO_SECCOES_LINHAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_PLANEAMENTO_PRODUCAO_LINHA")
	private Integer ID_PLANEAMENTO_PRODUCAO_LINHA;
	@JsonProperty("ID_PLANEAMENTO_PRODUCAO_CAB")
	private Integer ID_PLANEAMENTO_PRODUCAO_CAB;
	@JsonProperty("SEMANA")
	private Integer SEMANA;
	@JsonProperty("ANO")
	private Integer ANO;
	@JsonProperty("FASE")
	private String FASE;
	@JsonProperty("COD_REF")
	private String COD_REF;
	@JsonProperty("DESIGN_REF")
	private String DESIGN_REF;
	@JsonProperty("QUANT_PECAS")
	private Integer QUANT_PECAS;
	@JsonProperty("NUM_CAIXAS")
	private Integer NUM_CAIXAS;
	@JsonProperty("ANTECEDENCIA")
	private Integer ANTECEDENCIA;
	@JsonProperty("QUANT_MRP")
	private Integer QUANT_MRP;
	@JsonProperty("QUANT_OF")
	private Integer QUANT_OF;
	@JsonProperty("NUM_CAIXAS_MRP")
	private Integer NUM_CAIXAS_MRP;
	@JsonProperty("QUANT_PLANO")
	private Integer QUANT_PLANO;
	@JsonProperty("HORAS_PLANO")
	private Integer HORAS_PLANO;
	@JsonProperty("NUM_CAIXAS_PLANO")
	private Integer NUM_CAIXAS_PLANO;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("SECCAO")
	private String SECCAO;
	@JsonProperty("NOME_SECCAO")
	private String NOME_SECCAO;
	@JsonProperty("PROTYPCOD")
	private String PROTYPCOD;


	public Integer getID_PLANEAMENTO_PRODUCAO_LINHA() {
		return ID_PLANEAMENTO_PRODUCAO_LINHA;
	}

	public Integer getID_PLANEAMENTO_PRODUCAO_CAB() {
		return ID_PLANEAMENTO_PRODUCAO_CAB;
	}

	public Integer getSEMANA() {
		return SEMANA;
	}

	public Integer getANO() {
		return ANO;
	}

	public String getFASE() {
		return FASE;
	}

	public String getCOD_REF() {
		return COD_REF;
	}

	public String getDESIGN_REF() {
		return DESIGN_REF;
	}

	public Integer getQUANT_PECAS() {
		return QUANT_PECAS;
	}


	public Integer getNUM_CAIXAS() {
		return NUM_CAIXAS;
	}



	public Integer getANTECEDENCIA() {
		return ANTECEDENCIA;
	}

	public Integer getQUANT_MRP() {
		return QUANT_MRP;
	}

	public Integer getNUM_CAIXAS_MRP() {
		return NUM_CAIXAS_MRP;
	}

	public Integer getQUANT_PLANO() {
		return QUANT_PLANO;
	}

	public Integer getHORAS_PLANO() {
		return HORAS_PLANO;
	}

	public Integer getNUM_CAIXAS_PLANO() {
		return NUM_CAIXAS_PLANO;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public String getSECCAO() {
		return SECCAO;
	}

	public String getNOME_SECCAO() {
		return NOME_SECCAO;
	}


	public void setID_PLANEAMENTO_PRODUCAO_LINHA(Integer iD_PLANEAMENTO_PRODUCAO_LINHA) {
		ID_PLANEAMENTO_PRODUCAO_LINHA = iD_PLANEAMENTO_PRODUCAO_LINHA;
	}

	public void setID_PLANEAMENTO_PRODUCAO_CAB(Integer iD_PLANEAMENTO_PRODUCAO_CAB) {
		ID_PLANEAMENTO_PRODUCAO_CAB = iD_PLANEAMENTO_PRODUCAO_CAB;
	}

	public void setSEMANA(Integer sEMANA) {
		SEMANA = sEMANA;
	}

	public void setANO(Integer aNO) {
		ANO = aNO;
	}

	
	public void setFASE(String fASE) {
		FASE = fASE;
	}

	public void setCOD_REF(String cOD_REF) {
		COD_REF = cOD_REF;
	}

	public void setDESIGN_REF(String dESIGN_REF) {
		DESIGN_REF = dESIGN_REF;
	}

	public void setQUANT_PECAS(Integer qUANT_PECAS) {
		QUANT_PECAS = qUANT_PECAS;
	}

	

	public void setNUM_CAIXAS(Integer nUM_CAIXAS) {
		NUM_CAIXAS = nUM_CAIXAS;
	}

	
	public void setANTECEDENCIA(Integer aNTECEDENCIA) {
		ANTECEDENCIA = aNTECEDENCIA;
	}

	public void setQUANT_MRP(Integer qUANT_MRP) {
		QUANT_MRP = qUANT_MRP;
	}

	public void setNUM_CAIXAS_MRP(Integer nUM_CAIXAS_MRP) {
		NUM_CAIXAS_MRP = nUM_CAIXAS_MRP;
	}

	public void setQUANT_PLANO(Integer qUANT_PLANO) {
		QUANT_PLANO = qUANT_PLANO;
	}

	public void setHORAS_PLANO(Integer hORAS_PLANO) {
		HORAS_PLANO = hORAS_PLANO;
	}

	public void setNUM_CAIXAS_PLANO(Integer nUM_CAIXAS_PLANO) {
		NUM_CAIXAS_PLANO = nUM_CAIXAS_PLANO;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setSECCAO(String sECCAO) {
		SECCAO = sECCAO;
	}

	public void setNOME_SECCAO(String nOME_SECCAO) {
		NOME_SECCAO = nOME_SECCAO;
	}

	public Integer getQUANT_OF() {
		return QUANT_OF;
	}

	public String getPROTYPCOD() {
		return PROTYPCOD;
	}

	public void setQUANT_OF(Integer qUANT_OF) {
		QUANT_OF = qUANT_OF;
	}

	public void setPROTYPCOD(String pROTYPCOD) {
		PROTYPCOD = pROTYPCOD;
	}

 

}
