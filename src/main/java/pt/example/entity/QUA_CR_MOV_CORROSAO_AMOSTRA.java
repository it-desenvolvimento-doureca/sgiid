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
@Table(name = "QUA_CR_MOV_CORROSAO_AMOSTRA")
public class QUA_CR_MOV_CORROSAO_AMOSTRA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_CORROSAO_AMOSTRA")
	private Integer ID_CORROSAO_AMOSTRA;
	@JsonProperty("ID_CORROSAO_CAB")
	private Integer ID_CORROSAO_CAB;
	@JsonProperty("TIPO")
	private String TIPO;
	@JsonProperty("NUM_AMOSTRA")
	private Integer NUM_AMOSTRA;
	@JsonProperty("CAVIDADE")
	private String CAVIDADE;
	@JsonProperty("PESO")
	private BigDecimal PESO;
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

	public Integer getID_CORROSAO_AMOSTRA() { return ID_CORROSAO_AMOSTRA; }
	public void setID_CORROSAO_AMOSTRA(Integer v) { ID_CORROSAO_AMOSTRA = v; }
	public Integer getID_CORROSAO_CAB() { return ID_CORROSAO_CAB; }
	public void setID_CORROSAO_CAB(Integer v) { ID_CORROSAO_CAB = v; }
	public String getTIPO() { return TIPO; }
	public void setTIPO(String v) { TIPO = v; }
	public Integer getNUM_AMOSTRA() { return NUM_AMOSTRA; }
	public void setNUM_AMOSTRA(Integer v) { NUM_AMOSTRA = v; }
	public String getCAVIDADE() { return CAVIDADE; }
	public void setCAVIDADE(String v) { CAVIDADE = v; }
	public BigDecimal getPESO() { return PESO; }
	public void setPESO(BigDecimal v) { PESO = v; }
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