package pt.example.entity;

import java.math.BigDecimal;
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
@Table(name = "MAN_MOV_MANUTENCAO_LISTA_MATERIAL")
public class MAN_MOV_MANUTENCAO_LISTA_MATERIAL {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty( "ID_MANUTENCAO_LISTA_MATERIAL")
	private Integer ID_MANUTENCAO_LISTA_MATERIAL;
	@JsonProperty( "ID_MANUTENCAO_CAB")
	private Integer ID_MANUTENCAO_CAB;
	@JsonProperty( "NUMERO_ETIQUETA")
	private String NUMERO_ETIQUETA;
	@JsonProperty( "COD_REFERENCIA")
	private String COD_REFERENCIA;
	@JsonProperty( "DESC_REFERENCIA")
	private String DESC_REFERENCIA;
	@JsonProperty( "QUANTIDADE")
	private Integer QUANTIDADE;
	@JsonProperty( "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty( "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty( "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty( "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty( "PRECO_MATERIAL")
	private BigDecimal PRECO_MATERIAL;
	@JsonProperty( "UNIDADE")
	private String UNIDADE;

	public Integer getID_MANUTENCAO_LISTA_MATERIAL() {
		return ID_MANUTENCAO_LISTA_MATERIAL;
	}

	public Integer getID_MANUTENCAO_CAB() {
		return ID_MANUTENCAO_CAB;
	}

	public String getNUMERO_ETIQUETA() {
		return NUMERO_ETIQUETA;
	}

	public String getCOD_REFERENCIA() {
		return COD_REFERENCIA;
	}

	public String getDESC_REFERENCIA() {
		return DESC_REFERENCIA;
	}

	public Integer getQUANTIDADE() {
		return QUANTIDADE;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setID_MANUTENCAO_LISTA_MATERIAL(Integer iD_MANUTENCAO_LISTA_MATERIAL) {
		ID_MANUTENCAO_LISTA_MATERIAL = iD_MANUTENCAO_LISTA_MATERIAL;
	}

	public void setID_MANUTENCAO_CAB(Integer iD_MANUTENCAO_CAB) {
		ID_MANUTENCAO_CAB = iD_MANUTENCAO_CAB;
	}

	public void setNUMERO_ETIQUETA(String nUMERO_ETIQUETA) {
		NUMERO_ETIQUETA = nUMERO_ETIQUETA;
	}

	public void setCOD_REFERENCIA(String cOD_REFERENCIA) {
		COD_REFERENCIA = cOD_REFERENCIA;
	}

	public void setDESC_REFERENCIA(String dESC_REFERENCIA) {
		DESC_REFERENCIA = dESC_REFERENCIA;
	}

	public void setQUANTIDADE(Integer qUANTIDADE) {
		QUANTIDADE = qUANTIDADE;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public BigDecimal getPRECO_MATERIAL() {
		return PRECO_MATERIAL;
	}

	public void setPRECO_MATERIAL(BigDecimal pRECO_MATERIAL) {
		PRECO_MATERIAL = pRECO_MATERIAL;
	}

	public String getUNIDADE() {
		return UNIDADE;
	}

	public void setUNIDADE(String uNIDADE) {
		UNIDADE = uNIDADE;
	}

}
