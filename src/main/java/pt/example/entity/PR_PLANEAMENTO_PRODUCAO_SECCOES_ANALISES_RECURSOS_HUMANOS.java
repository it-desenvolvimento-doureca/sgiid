package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES_RECURSOS_HUMANOS")
public class PR_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES_RECURSOS_HUMANOS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES")
	private Integer ID_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES;
	@JsonProperty("ID_SECTOR")
	private Integer ID_SECTOR;
	@JsonProperty("NOME")
	private String NOME;
	@JsonProperty("COD_SECCAO")
	private Integer COD_SECCAO;
	@JsonProperty("DES_SECCAO")
	private String DES_SECCAO;
	@JsonProperty("ANO")
	private Integer ANO;
	@JsonProperty("SEMANA")
	private Integer SEMANA;
	@JsonProperty("OPERARIOS_DISPONIVEIS")
	private Integer OPERARIOS_DISPONIVEIS;
	@JsonProperty("OPERARIOS_NECESSARIOS")
	private Integer OPERARIOS_NECESSARIOS;
	@JsonProperty("OPERARIOS_NECESSARIOS_VALOR")
	private BigDecimal OPERARIOS_NECESSARIOS_VALOR;

	public Integer getID() {
		return ID;
	}

	public Integer getID_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES() {
		return ID_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES;
	}

	public Integer getID_SECTOR() {
		return ID_SECTOR;
	}

	public String getNOME() {
		return NOME;
	}

	public Integer getCOD_SECCAO() {
		return COD_SECCAO;
	}

	public String getDES_SECCAO() {
		return DES_SECCAO;
	}

	public Integer getANO() {
		return ANO;
	}

	public Integer getSEMANA() {
		return SEMANA;
	}

	public Integer getOPERARIOS_DISPONIVEIS() {
		return OPERARIOS_DISPONIVEIS;
	}

	public Integer getOPERARIOS_NECESSARIOS() {
		return OPERARIOS_NECESSARIOS;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES(Integer iD_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES) {
		ID_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES = iD_PLANEAMENTO_PRODUCAO_SECCOES_ANALISES;
	}

	public void setID_SECTOR(Integer iD_SECTOR) {
		ID_SECTOR = iD_SECTOR;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setCOD_SECCAO(Integer cOD_SECCAO) {
		COD_SECCAO = cOD_SECCAO;
	}

	public void setDES_SECCAO(String dES_SECCAO) {
		DES_SECCAO = dES_SECCAO;
	}

	public void setANO(Integer aNO) {
		ANO = aNO;
	}

	public void setSEMANA(Integer sEMANA) {
		SEMANA = sEMANA;
	}

	public void setOPERARIOS_DISPONIVEIS(Integer oPERARIOS_DISPONIVEIS) {
		OPERARIOS_DISPONIVEIS = oPERARIOS_DISPONIVEIS;
	}

	public void setOPERARIOS_NECESSARIOS(Integer oPERARIOS_NECESSARIOS) {
		OPERARIOS_NECESSARIOS = oPERARIOS_NECESSARIOS;
	}

	public BigDecimal getOPERARIOS_NECESSARIOS_VALOR() {
		return OPERARIOS_NECESSARIOS_VALOR;
	}

	public void setOPERARIOS_NECESSARIOS_VALOR(BigDecimal oPERARIOS_NECESSARIOS_VALOR) {
		OPERARIOS_NECESSARIOS_VALOR = oPERARIOS_NECESSARIOS_VALOR;
	}

	
}