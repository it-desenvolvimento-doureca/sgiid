package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_MOV_CONSUMO_TINTAS")
public class PIN_MOV_CONSUMO_TINTAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_CONSUMO_TINTAS")
	private Integer ID_CONSUMO_TINTAS;

	@JsonProperty("ANO")
	private Integer ANO;

	@JsonProperty("SEMANA")
	private Integer SEMANA;

	@JsonProperty("DIA")
	private Integer DIA;

	@JsonProperty("DATA")
	private Timestamp DATA;

	@JsonProperty("ID_PLANO_DIARIO_PINTURA")
	private Integer ID_PLANO_DIARIO_PINTURA;

	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;

	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;

	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;

	@JsonProperty("DATA_ANULA")
	private Timestamp DATA_ANULA;

	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	public Integer getID_CONSUMO_TINTAS() { return ID_CONSUMO_TINTAS; }
	public void setID_CONSUMO_TINTAS(Integer id) { this.ID_CONSUMO_TINTAS = id; }

	public Integer getANO() { return ANO; }
	public void setANO(Integer ano) { this.ANO = ano; }

	public Integer getSEMANA() { return SEMANA; }
	public void setSEMANA(Integer semana) { this.SEMANA = semana; }

	public Integer getDIA() { return DIA; }
	public void setDIA(Integer dia) { this.DIA = dia; }

	public Timestamp getDATA() { return DATA; }
	public void setDATA(Timestamp data) { this.DATA = data; }

	public Integer getID_PLANO_DIARIO_PINTURA() { return ID_PLANO_DIARIO_PINTURA; }
	public void setID_PLANO_DIARIO_PINTURA(Integer id) { this.ID_PLANO_DIARIO_PINTURA = id; }

	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer utz) { this.UTZ_CRIA = utz; }

	public Timestamp getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Timestamp data) { this.DATA_CRIA = data; }

	public Integer getUTZ_MODIF() { return UTZ_MODIF; }
	public void setUTZ_MODIF(Integer utz) { this.UTZ_MODIF = utz; }

	public Timestamp getDATA_MODIF() { return DATA_MODIF; }
	public void setDATA_MODIF(Timestamp data) { this.DATA_MODIF = data; }

	public Integer getUTZ_ANULA() { return UTZ_ANULA; }
	public void setUTZ_ANULA(Integer utz) { this.UTZ_ANULA = utz; }

	public Timestamp getDATA_ANULA() { return DATA_ANULA; }
	public void setDATA_ANULA(Timestamp data) { this.DATA_ANULA = data; }

	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean ativo) { this.ATIVO = ativo; }
}
