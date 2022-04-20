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
@Table(name = "MAN_MOV_MAQUINAS_PARADAS")
public class MAN_MOV_MAQUINAS_PARADAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_PEDIDO")
	private Integer ID_PEDIDO;
	@JsonProperty("DATA_INICIO")
	private Timestamp DATA_INICIO;
	@JsonProperty("DATA_FIM")
	private Timestamp DATA_FIM;
	@JsonProperty("ESTADO")
	private String ESTADO;
	@JsonProperty("ID_EQUIPAMENTO")
	private Integer ID_EQUIPAMENTO;
	@JsonProperty( "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty( "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty( "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty( "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	
	public Integer getID() {
		return ID;
	}

	public Integer getID_PEDIDO() {
		return ID_PEDIDO;
	}

	public Timestamp getDATA_INICIO() {
		return DATA_INICIO;
	}

	public Timestamp getDATA_FIM() {
		return DATA_FIM;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Integer getID_EQUIPAMENTO() {
		return ID_EQUIPAMENTO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_PEDIDO(Integer iD_PEDIDO) {
		ID_PEDIDO = iD_PEDIDO;
	}

	public void setDATA_INICIO(Timestamp dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Timestamp dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setID_EQUIPAMENTO(Integer iD_EQUIPAMENTO) {
		ID_EQUIPAMENTO = iD_EQUIPAMENTO;
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

}
