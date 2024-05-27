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
@Table(name = "PIN_MOV_RECEITAS_REFERENCIAS")
public class PIN_MOV_RECEITAS_REFERENCIAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("VERSAO")
	private Integer VERSAO;
	@JsonProperty("ID_RECEITA")
	private Integer ID_RECEITA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("REFERENCIA_PINTURA")
	private String REFERENCIA_PINTURA;
	@JsonProperty("NOME_REFERENCIA_PINTURA")
	private String NOME_REFERENCIA_PINTURA;
	@JsonProperty("RACK")
	private String RACK;
	@JsonProperty("QUANTIDADES_RACK")
	private Integer QUANTIDADES_RACK;
	@JsonProperty("PECAS_RACK")
	private Integer PECAS_RACK;
	@JsonProperty("TAXA_REJEICAO")
	private BigDecimal TAXA_REJEICAO;

	public Integer getID() {
		return ID;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public Integer getID_RECEITA() {
		return ID_RECEITA;
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

	public String getREFERENCIA_PINTURA() {
		return REFERENCIA_PINTURA;
	}

	public String getNOME_REFERENCIA_PINTURA() {
		return NOME_REFERENCIA_PINTURA;
	}

	public String getRACK() {
		return RACK;
	}

	public Integer getQUANTIDADES_RACK() {
		return QUANTIDADES_RACK;
	}

	public Integer getPECAS_RACK() {
		return PECAS_RACK;
	}

	public BigDecimal getTAXA_REJEICAO() {
		return TAXA_REJEICAO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

	public void setID_RECEITA(Integer iD_RECEITA) {
		ID_RECEITA = iD_RECEITA;
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

	public void setREFERENCIA_PINTURA(String rEFERENCIA_PINTURA) {
		REFERENCIA_PINTURA = rEFERENCIA_PINTURA;
	}

	public void setNOME_REFERENCIA_PINTURA(String nOME_REFERENCIA_PINTURA) {
		NOME_REFERENCIA_PINTURA = nOME_REFERENCIA_PINTURA;
	}

	public void setRACK(String rACK) {
		RACK = rACK;
	}

	public void setQUANTIDADES_RACK(Integer qUANTIDADES_RACK) {
		QUANTIDADES_RACK = qUANTIDADES_RACK;
	}

	public void setPECAS_RACK(Integer pECAS_RACK) {
		PECAS_RACK = pECAS_RACK;
	}

	public void setTAXA_REJEICAO(BigDecimal tAXA_REJEICAO) {
		TAXA_REJEICAO = tAXA_REJEICAO;
	}

}