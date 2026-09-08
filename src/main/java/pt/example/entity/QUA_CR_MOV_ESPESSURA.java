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
@Table(name = "QUA_CR_MOV_ESPESSURA")
public class QUA_CR_MOV_ESPESSURA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_ESPESSURA")
	private Integer ID_ESPESSURA;
	@JsonProperty("ID_ESPESSURA_CAB")
	private Integer ID_ESPESSURA_CAB;
	@JsonProperty("NUM_AMOSTRA")
	private Integer NUM_AMOSTRA;
	@JsonProperty("IDENT_AMOSTRA")
	private String IDENT_AMOSTRA;
	@JsonProperty("CAVIDADE")
	private String CAVIDADE;
	@JsonProperty("PESO")
	private BigDecimal PESO;
	@JsonProperty("ESP_CU")
	private BigDecimal ESP_CU;
	@JsonProperty("ESP_NI")
	private BigDecimal ESP_NI;
	@JsonProperty("ESP_CR")
	private BigDecimal ESP_CR;
	@JsonProperty("RESULTADO")
	private String RESULTADO;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
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

	public Integer getID_ESPESSURA() { return ID_ESPESSURA; }
	public void setID_ESPESSURA(Integer v) { ID_ESPESSURA = v; }
	public Integer getID_ESPESSURA_CAB() { return ID_ESPESSURA_CAB; }
	public void setID_ESPESSURA_CAB(Integer v) { ID_ESPESSURA_CAB = v; }
	public Integer getNUM_AMOSTRA() { return NUM_AMOSTRA; }
	public void setNUM_AMOSTRA(Integer v) { NUM_AMOSTRA = v; }
	public String getIDENT_AMOSTRA() { return IDENT_AMOSTRA; }
	public void setIDENT_AMOSTRA(String v) { IDENT_AMOSTRA = v; }
	public String getCAVIDADE() { return CAVIDADE; }
	public void setCAVIDADE(String v) { CAVIDADE = v; }
	public BigDecimal getPESO() { return PESO; }
	public void setPESO(BigDecimal v) { PESO = v; }
	public BigDecimal getESP_CU() { return ESP_CU; }
	public void setESP_CU(BigDecimal v) { ESP_CU = v; }
	public BigDecimal getESP_NI() { return ESP_NI; }
	public void setESP_NI(BigDecimal v) { ESP_NI = v; }
	public BigDecimal getESP_CR() { return ESP_CR; }
	public void setESP_CR(BigDecimal v) { ESP_CR = v; }
	public String getRESULTADO() { return RESULTADO; }
	public void setRESULTADO(String v) { RESULTADO = v; }
	public String getOBSERVACOES() { return OBSERVACOES; }
	public void setOBSERVACOES(String v) { OBSERVACOES = v; }
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