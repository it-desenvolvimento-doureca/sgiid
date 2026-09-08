package pt.example.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_CR_DIC_CONJ_EQUIP_LIN")
public class QUA_CR_DIC_CONJ_EQUIP_LIN {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_CONJ_EQUIP_LIN")
	private Integer ID_CONJ_EQUIP_LIN;
	@JsonProperty("ID_CONJ_EQUIP")
	private Integer ID_CONJ_EQUIP;
	@JsonProperty("ID_EQUIPAMENTO")
	private Integer ID_EQUIPAMENTO;
	@JsonProperty("COD_INTERNO")
	private String COD_INTERNO;
	@JsonProperty("ORDEM")
	private Integer ORDEM;
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

	public Integer getID_CONJ_EQUIP_LIN() { return ID_CONJ_EQUIP_LIN; }
	public void setID_CONJ_EQUIP_LIN(Integer v) { ID_CONJ_EQUIP_LIN = v; }
	public Integer getID_CONJ_EQUIP() { return ID_CONJ_EQUIP; }
	public void setID_CONJ_EQUIP(Integer v) { ID_CONJ_EQUIP = v; }
	public Integer getID_EQUIPAMENTO() { return ID_EQUIPAMENTO; }
	public void setID_EQUIPAMENTO(Integer v) { ID_EQUIPAMENTO = v; }
	public String getCOD_INTERNO() { return COD_INTERNO; }
	public void setCOD_INTERNO(String v) { COD_INTERNO = v; }
	public Integer getORDEM() { return ORDEM; }
	public void setORDEM(Integer v) { ORDEM = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Timestamp getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Timestamp v) { DATA_CRIA = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; }
	public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
	public Timestamp getDATA_MODIF() { return DATA_MODIF; }
	public void setDATA_MODIF(Timestamp v) { DATA_MODIF = v; }
	public Integer getUTZ_ANULA() { return UTZ_ANULA; }
	public void setUTZ_ANULA(Integer v) { UTZ_ANULA = v; }
	public Timestamp getDATA_ANULA() { return DATA_ANULA; }
	public void setDATA_ANULA(Timestamp v) { DATA_ANULA = v; }
	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }
}