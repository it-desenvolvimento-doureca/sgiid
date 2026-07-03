package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_MOV_RECEITAS_PRESSOES")
public class PIN_MOV_RECEITAS_PRESSOES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("VERSAO")
	private Integer VERSAO;
	@JsonProperty("ID_RECEITA")
	private Integer ID_RECEITA;
	@JsonProperty("PROGRAMA")
	private String PROGRAMA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("PRIMARIO_TINTA_ENTRADA_MIN")
	private BigDecimal PRIMARIO_TINTA_ENTRADA_MIN;
	@JsonProperty("PRIMARIO_TINTA_ENTRADA_MAX")
	private BigDecimal PRIMARIO_TINTA_ENTRADA_MAX;
	@JsonProperty("PRIMARIO_TINTA_SAIDA_MIN")
	private BigDecimal PRIMARIO_TINTA_SAIDA_MIN;
	@JsonProperty("PRIMARIO_TINTA_SAIDA_MAX")
	private BigDecimal PRIMARIO_TINTA_SAIDA_MAX;
	@JsonProperty("PRIMARIO_CATALISADOR_ENTRADA_MIN")
	private BigDecimal PRIMARIO_CATALISADOR_ENTRADA_MIN;
	@JsonProperty("PRIMARIO_CATALISADOR_ENTRADA_MAX")
	private BigDecimal PRIMARIO_CATALISADOR_ENTRADA_MAX;
	@JsonProperty("PRIMARIO_CATALISADOR_SAIDA_MIN")
	private BigDecimal PRIMARIO_CATALISADOR_SAIDA_MIN;
	@JsonProperty("PRIMARIO_CATALISADOR_SAIDA_MAX")
	private BigDecimal PRIMARIO_CATALISADOR_SAIDA_MAX;
	@JsonProperty("BASE_TINTA_ENTRADA_MIN")
	private BigDecimal BASE_TINTA_ENTRADA_MIN;
	@JsonProperty("BASE_TINTA_ENTRADA_MAX")
	private BigDecimal BASE_TINTA_ENTRADA_MAX;
	@JsonProperty("BASE_TINTA_SAIDA_MIN")
	private BigDecimal BASE_TINTA_SAIDA_MIN;
	@JsonProperty("BASE_TINTA_SAIDA_MAX")
	private BigDecimal BASE_TINTA_SAIDA_MAX;
	@JsonProperty("BASE_CATALISADOR_ENTRADA_MIN")
	private BigDecimal BASE_CATALISADOR_ENTRADA_MIN;
	@JsonProperty("BASE_CATALISADOR_ENTRADA_MAX")
	private BigDecimal BASE_CATALISADOR_ENTRADA_MAX;
	@JsonProperty("BASE_CATALISADOR_SAIDA_MIN")
	private BigDecimal BASE_CATALISADOR_SAIDA_MIN;
	@JsonProperty("BASE_CATALISADOR_SAIDA_MAX")
	private BigDecimal BASE_CATALISADOR_SAIDA_MAX;
	@JsonProperty("VERNIZ_TINTA_ENTRADA_MIN")
	private BigDecimal VERNIZ_TINTA_ENTRADA_MIN;
	@JsonProperty("VERNIZ_TINTA_ENTRADA_MAX")
	private BigDecimal VERNIZ_TINTA_ENTRADA_MAX;
	@JsonProperty("VERNIZ_TINTA_SAIDA_MIN")
	private BigDecimal VERNIZ_TINTA_SAIDA_MIN;
	@JsonProperty("VERNIZ_TINTA_SAIDA_MAX")
	private BigDecimal VERNIZ_TINTA_SAIDA_MAX;
	@JsonProperty("VERNIZ_CATALISADOR_ENTRADA_MIN")
	private BigDecimal VERNIZ_CATALISADOR_ENTRADA_MIN;
	@JsonProperty("VERNIZ_CATALISADOR_ENTRADA_MAX")
	private BigDecimal VERNIZ_CATALISADOR_ENTRADA_MAX;
	@JsonProperty("VERNIZ_CATALISADOR_SAIDA_MIN")
	private BigDecimal VERNIZ_CATALISADOR_SAIDA_MIN;
	@JsonProperty("VERNIZ_CATALISADOR_SAIDA_MAX")
	private BigDecimal VERNIZ_CATALISADOR_SAIDA_MAX;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

	public Integer getID_RECEITA() {
		return ID_RECEITA;
	}

	public void setID_RECEITA(Integer iD_RECEITA) {
		ID_RECEITA = iD_RECEITA;
	}

	public String getPROGRAMA() {
		return PROGRAMA;
	}

	public void setPROGRAMA(String pROGRAMA) {
		PROGRAMA = pROGRAMA;
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

	public BigDecimal getPRIMARIO_TINTA_ENTRADA_MIN() {
		return PRIMARIO_TINTA_ENTRADA_MIN;
	}

	public void setPRIMARIO_TINTA_ENTRADA_MIN(BigDecimal v) {
		PRIMARIO_TINTA_ENTRADA_MIN = v;
	}

	public BigDecimal getPRIMARIO_TINTA_ENTRADA_MAX() {
		return PRIMARIO_TINTA_ENTRADA_MAX;
	}

	public void setPRIMARIO_TINTA_ENTRADA_MAX(BigDecimal v) {
		PRIMARIO_TINTA_ENTRADA_MAX = v;
	}

	public BigDecimal getPRIMARIO_TINTA_SAIDA_MIN() {
		return PRIMARIO_TINTA_SAIDA_MIN;
	}

	public void setPRIMARIO_TINTA_SAIDA_MIN(BigDecimal v) {
		PRIMARIO_TINTA_SAIDA_MIN = v;
	}

	public BigDecimal getPRIMARIO_TINTA_SAIDA_MAX() {
		return PRIMARIO_TINTA_SAIDA_MAX;
	}

	public void setPRIMARIO_TINTA_SAIDA_MAX(BigDecimal v) {
		PRIMARIO_TINTA_SAIDA_MAX = v;
	}

	public BigDecimal getPRIMARIO_CATALISADOR_ENTRADA_MIN() {
		return PRIMARIO_CATALISADOR_ENTRADA_MIN;
	}

	public void setPRIMARIO_CATALISADOR_ENTRADA_MIN(BigDecimal v) {
		PRIMARIO_CATALISADOR_ENTRADA_MIN = v;
	}

	public BigDecimal getPRIMARIO_CATALISADOR_ENTRADA_MAX() {
		return PRIMARIO_CATALISADOR_ENTRADA_MAX;
	}

	public void setPRIMARIO_CATALISADOR_ENTRADA_MAX(BigDecimal v) {
		PRIMARIO_CATALISADOR_ENTRADA_MAX = v;
	}

	public BigDecimal getPRIMARIO_CATALISADOR_SAIDA_MIN() {
		return PRIMARIO_CATALISADOR_SAIDA_MIN;
	}

	public void setPRIMARIO_CATALISADOR_SAIDA_MIN(BigDecimal v) {
		PRIMARIO_CATALISADOR_SAIDA_MIN = v;
	}

	public BigDecimal getPRIMARIO_CATALISADOR_SAIDA_MAX() {
		return PRIMARIO_CATALISADOR_SAIDA_MAX;
	}

	public void setPRIMARIO_CATALISADOR_SAIDA_MAX(BigDecimal v) {
		PRIMARIO_CATALISADOR_SAIDA_MAX = v;
	}

	public BigDecimal getBASE_TINTA_ENTRADA_MIN() {
		return BASE_TINTA_ENTRADA_MIN;
	}

	public void setBASE_TINTA_ENTRADA_MIN(BigDecimal v) {
		BASE_TINTA_ENTRADA_MIN = v;
	}

	public BigDecimal getBASE_TINTA_ENTRADA_MAX() {
		return BASE_TINTA_ENTRADA_MAX;
	}

	public void setBASE_TINTA_ENTRADA_MAX(BigDecimal v) {
		BASE_TINTA_ENTRADA_MAX = v;
	}

	public BigDecimal getBASE_TINTA_SAIDA_MIN() {
		return BASE_TINTA_SAIDA_MIN;
	}

	public void setBASE_TINTA_SAIDA_MIN(BigDecimal v) {
		BASE_TINTA_SAIDA_MIN = v;
	}

	public BigDecimal getBASE_TINTA_SAIDA_MAX() {
		return BASE_TINTA_SAIDA_MAX;
	}

	public void setBASE_TINTA_SAIDA_MAX(BigDecimal v) {
		BASE_TINTA_SAIDA_MAX = v;
	}

	public BigDecimal getBASE_CATALISADOR_ENTRADA_MIN() {
		return BASE_CATALISADOR_ENTRADA_MIN;
	}

	public void setBASE_CATALISADOR_ENTRADA_MIN(BigDecimal v) {
		BASE_CATALISADOR_ENTRADA_MIN = v;
	}

	public BigDecimal getBASE_CATALISADOR_ENTRADA_MAX() {
		return BASE_CATALISADOR_ENTRADA_MAX;
	}

	public void setBASE_CATALISADOR_ENTRADA_MAX(BigDecimal v) {
		BASE_CATALISADOR_ENTRADA_MAX = v;
	}

	public BigDecimal getBASE_CATALISADOR_SAIDA_MIN() {
		return BASE_CATALISADOR_SAIDA_MIN;
	}

	public void setBASE_CATALISADOR_SAIDA_MIN(BigDecimal v) {
		BASE_CATALISADOR_SAIDA_MIN = v;
	}

	public BigDecimal getBASE_CATALISADOR_SAIDA_MAX() {
		return BASE_CATALISADOR_SAIDA_MAX;
	}

	public void setBASE_CATALISADOR_SAIDA_MAX(BigDecimal v) {
		BASE_CATALISADOR_SAIDA_MAX = v;
	}

	public BigDecimal getVERNIZ_TINTA_ENTRADA_MIN() {
		return VERNIZ_TINTA_ENTRADA_MIN;
	}

	public void setVERNIZ_TINTA_ENTRADA_MIN(BigDecimal v) {
		VERNIZ_TINTA_ENTRADA_MIN = v;
	}

	public BigDecimal getVERNIZ_TINTA_ENTRADA_MAX() {
		return VERNIZ_TINTA_ENTRADA_MAX;
	}

	public void setVERNIZ_TINTA_ENTRADA_MAX(BigDecimal v) {
		VERNIZ_TINTA_ENTRADA_MAX = v;
	}

	public BigDecimal getVERNIZ_TINTA_SAIDA_MIN() {
		return VERNIZ_TINTA_SAIDA_MIN;
	}

	public void setVERNIZ_TINTA_SAIDA_MIN(BigDecimal v) {
		VERNIZ_TINTA_SAIDA_MIN = v;
	}

	public BigDecimal getVERNIZ_TINTA_SAIDA_MAX() {
		return VERNIZ_TINTA_SAIDA_MAX;
	}

	public void setVERNIZ_TINTA_SAIDA_MAX(BigDecimal v) {
		VERNIZ_TINTA_SAIDA_MAX = v;
	}

	public BigDecimal getVERNIZ_CATALISADOR_ENTRADA_MIN() {
		return VERNIZ_CATALISADOR_ENTRADA_MIN;
	}

	public void setVERNIZ_CATALISADOR_ENTRADA_MIN(BigDecimal v) {
		VERNIZ_CATALISADOR_ENTRADA_MIN = v;
	}

	public BigDecimal getVERNIZ_CATALISADOR_ENTRADA_MAX() {
		return VERNIZ_CATALISADOR_ENTRADA_MAX;
	}

	public void setVERNIZ_CATALISADOR_ENTRADA_MAX(BigDecimal v) {
		VERNIZ_CATALISADOR_ENTRADA_MAX = v;
	}

	public BigDecimal getVERNIZ_CATALISADOR_SAIDA_MIN() {
		return VERNIZ_CATALISADOR_SAIDA_MIN;
	}

	public void setVERNIZ_CATALISADOR_SAIDA_MIN(BigDecimal v) {
		VERNIZ_CATALISADOR_SAIDA_MIN = v;
	}

	public BigDecimal getVERNIZ_CATALISADOR_SAIDA_MAX() {
		return VERNIZ_CATALISADOR_SAIDA_MAX;
	}

	public void setVERNIZ_CATALISADOR_SAIDA_MAX(BigDecimal v) {
		VERNIZ_CATALISADOR_SAIDA_MAX = v;
	}

}
