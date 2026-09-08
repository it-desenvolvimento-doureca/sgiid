package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_CR_MOV_CONDICOES")
public class QUA_CR_MOV_CONDICOES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_CONDICAO")
	private Integer ID_CONDICAO;
	@JsonProperty("ID_CONDICOES_CAB")
	private Integer ID_CONDICOES_CAB;
	@JsonProperty("BANHO")
	private String BANHO;
	@JsonProperty("ORDEM")
	private Integer ORDEM;
	@JsonProperty("CORRENTE")
	private BigDecimal CORRENTE;
	@JsonProperty("TEMPO")
	private Time TEMPO;
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

	public Integer getID_CONDICAO() { return ID_CONDICAO; }
	public void setID_CONDICAO(Integer v) { ID_CONDICAO = v; }
	public Integer getID_CONDICOES_CAB() { return ID_CONDICOES_CAB; }
	public void setID_CONDICOES_CAB(Integer v) { ID_CONDICOES_CAB = v; }
	public String getBANHO() { return BANHO; }
	public void setBANHO(String v) { BANHO = v; }
	public Integer getORDEM() { return ORDEM; }
	public void setORDEM(Integer v) { ORDEM = v; }
	public BigDecimal getCORRENTE() { return CORRENTE; }
	public void setCORRENTE(BigDecimal v) { CORRENTE = v; }
	public Time getTEMPO() { return TEMPO; }
	public void setTEMPO(Time v) { TEMPO = v; }
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