package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_PLANEAMENTO_PRODUCAO_LINHAS")
public class PR_PLANEAMENTO_PRODUCAO_LINHAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLANEAMENTO_PRODUCAO_LINHA")
	private Integer ID_PLANEAMENTO_PRODUCAO_LINHA;
	@Column(name = "ID_PLANEAMENTO_PRODUCAO_CAB")
	private Integer ID_PLANEAMENTO_PRODUCAO_CAB;
	@Column(name = "SEMANA")
	private Integer SEMANA;
	@Column(name = "ANO")
	private Integer ANO;
	@Column(name = "FASE")
	private String FASE;
	@Column(name = "COD_REF")
	private String COD_REF;
	@Column(name = "DESIGN_REF")
	private String DESIGN_REF;
	@Column(name = "QUANT_PECAS_BARRA")
	private Float QUANT_PECAS_BARRA;
	@Column(name = "RACK")
	private String RACK;
	@Column(name = "NUM_BARRAS")
	private Integer NUM_BARRAS;
	@Column(name = "ACABAMENTO")
	private String ACABAMENTO;
	@Column(name = "ANTECEDENCIA")
	private Float ANTECEDENCIA;
	@Column(name = "QUANT_MRP")
	private Float QUANT_MRP;
	@Column(name = "NUM_BARRAS_MRP")
	private Float NUM_BARRAS_MRP;
	@Column(name = "QUANT_PLANO")
	private Float QUANT_PLANO;
	@Column(name = "NUM_BARRAS_PLANO")
	private Float NUM_BARRAS_PLANO;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;

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

	public Float getQUANT_PECAS_BARRA() {
		return QUANT_PECAS_BARRA;
	}

	public String getRACK() {
		return RACK;
	}

	public Integer getNUM_BARRAS() {
		return NUM_BARRAS;
	}

	public String getACABAMENTO() {
		return ACABAMENTO;
	}

	public Float getANTECEDENCIA() {
		return ANTECEDENCIA;
	}

	public Float getQUANT_MRP() {
		return QUANT_MRP;
	}

	public Float getNUM_BARRAS_MRP() {
		return NUM_BARRAS_MRP;
	}

	public Float getQUANT_PLANO() {
		return QUANT_PLANO;
	}

	public Float getNUM_BARRAS_PLANO() {
		return NUM_BARRAS_PLANO;
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

	public void setQUANT_PECAS_BARRA(Float qUANT_PECAS_BARRA) {
		QUANT_PECAS_BARRA = qUANT_PECAS_BARRA;
	}

	public void setRACK(String rACK) {
		RACK = rACK;
	}

	public void setNUM_BARRAS(Integer nUM_BARRAS) {
		NUM_BARRAS = nUM_BARRAS;
	}

	public void setACABAMENTO(String aCABAMENTO) {
		ACABAMENTO = aCABAMENTO;
	}

	public void setANTECEDENCIA(Float aNTECEDENCIA) {
		ANTECEDENCIA = aNTECEDENCIA;
	}

	public void setQUANT_MRP(Float qUANT_MRP) {
		QUANT_MRP = qUANT_MRP;
	}

	public void setNUM_BARRAS_MRP(Float nUM_BARRAS_MRP) {
		NUM_BARRAS_MRP = nUM_BARRAS_MRP;
	}

	public void setQUANT_PLANO(Float qUANT_PLANO) {
		QUANT_PLANO = qUANT_PLANO;
	}

	public void setNUM_BARRAS_PLANO(Float nUM_BARRAS_PLANO) {
		NUM_BARRAS_PLANO = nUM_BARRAS_PLANO;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

}