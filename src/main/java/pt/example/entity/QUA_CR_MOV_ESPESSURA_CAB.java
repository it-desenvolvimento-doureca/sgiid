package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_CR_MOV_ESPESSURA_CAB")
public class QUA_CR_MOV_ESPESSURA_CAB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_ESPESSURA_CAB")
	private Integer ID_ESPESSURA_CAB;
	@JsonProperty("ID_RELATORIO")
	private Integer ID_RELATORIO;
	@JsonProperty("DATA_MEDICAO")
	private Date DATA_MEDICAO;
	@JsonProperty("ID_OPERADOR")
	private Integer ID_OPERADOR;
	@JsonProperty("POROS")
	private BigDecimal POROS;
	@JsonProperty("RESULTADO_POROS")
	private String RESULTADO_POROS;
	@JsonProperty("FISSURAS")
	private String FISSURAS;
	@JsonProperty("RESULTADO_FISSURAS")
	private String RESULTADO_FISSURAS;
	@JsonProperty("TEM_STEP")
	private Boolean TEM_STEP;
	@JsonProperty("STEP_ESP_1")
	private String STEP_ESP_1;
	@JsonProperty("STEP_ESP_2_1")
	private String STEP_ESP_2_1;
	@JsonProperty("STEP_ESP_3_2")
	private String STEP_ESP_3_2;
	@JsonProperty("STEP_POT_1")
	private String STEP_POT_1;
	@JsonProperty("STEP_POT_2_1")
	private String STEP_POT_2_1;
	@JsonProperty("STEP_POT_3_2")
	private String STEP_POT_3_2;
	@JsonProperty("STEP_POT_4_3")
	private String STEP_POT_4_3;
	@JsonProperty("ID_CONJ_EQUIP_ESP")
	private Integer ID_CONJ_EQUIP_ESP;
	@JsonProperty("ID_CONJ_EQUIP_POROS")
	private Integer ID_CONJ_EQUIP_POROS;
	@JsonProperty("ID_CONJ_EQUIP_STEP")
	private Integer ID_CONJ_EQUIP_STEP;
	@JsonProperty("RESULTADO_TOTAL")
	private String RESULTADO_TOTAL;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("ID_ACCESS_LEGADO")
	private Integer ID_ACCESS_LEGADO;
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

	public Integer getID_ESPESSURA_CAB() { return ID_ESPESSURA_CAB; }
	public void setID_ESPESSURA_CAB(Integer v) { ID_ESPESSURA_CAB = v; }
	public Integer getID_RELATORIO() { return ID_RELATORIO; }
	public void setID_RELATORIO(Integer v) { ID_RELATORIO = v; }
	public Date getDATA_MEDICAO() { return DATA_MEDICAO; }
	public void setDATA_MEDICAO(Date v) { DATA_MEDICAO = v; }
	public Integer getID_OPERADOR() { return ID_OPERADOR; }
	public void setID_OPERADOR(Integer v) { ID_OPERADOR = v; }
	public BigDecimal getPOROS() { return POROS; }
	public void setPOROS(BigDecimal v) { POROS = v; }
	public String getRESULTADO_POROS() { return RESULTADO_POROS; }
	public void setRESULTADO_POROS(String v) { RESULTADO_POROS = v; }
	public String getFISSURAS() { return FISSURAS; }
	public void setFISSURAS(String v) { FISSURAS = v; }
	public String getRESULTADO_FISSURAS() { return RESULTADO_FISSURAS; }
	public void setRESULTADO_FISSURAS(String v) { RESULTADO_FISSURAS = v; }
	public Boolean getTEM_STEP() { return TEM_STEP; }
	public void setTEM_STEP(Boolean v) { TEM_STEP = v; }
	public String getSTEP_ESP_1() { return STEP_ESP_1; }
	public void setSTEP_ESP_1(String v) { STEP_ESP_1 = v; }
	public String getSTEP_ESP_2_1() { return STEP_ESP_2_1; }
	public void setSTEP_ESP_2_1(String v) { STEP_ESP_2_1 = v; }
	public String getSTEP_ESP_3_2() { return STEP_ESP_3_2; }
	public void setSTEP_ESP_3_2(String v) { STEP_ESP_3_2 = v; }
	public String getSTEP_POT_1() { return STEP_POT_1; }
	public void setSTEP_POT_1(String v) { STEP_POT_1 = v; }
	public String getSTEP_POT_2_1() { return STEP_POT_2_1; }
	public void setSTEP_POT_2_1(String v) { STEP_POT_2_1 = v; }
	public String getSTEP_POT_3_2() { return STEP_POT_3_2; }
	public void setSTEP_POT_3_2(String v) { STEP_POT_3_2 = v; }
	public String getSTEP_POT_4_3() { return STEP_POT_4_3; }
	public void setSTEP_POT_4_3(String v) { STEP_POT_4_3 = v; }
	public Integer getID_CONJ_EQUIP_ESP() { return ID_CONJ_EQUIP_ESP; }
	public void setID_CONJ_EQUIP_ESP(Integer v) { ID_CONJ_EQUIP_ESP = v; }
	public Integer getID_CONJ_EQUIP_POROS() { return ID_CONJ_EQUIP_POROS; }
	public void setID_CONJ_EQUIP_POROS(Integer v) { ID_CONJ_EQUIP_POROS = v; }
	public Integer getID_CONJ_EQUIP_STEP() { return ID_CONJ_EQUIP_STEP; }
	public void setID_CONJ_EQUIP_STEP(Integer v) { ID_CONJ_EQUIP_STEP = v; }
	public String getRESULTADO_TOTAL() { return RESULTADO_TOTAL; }
	public void setRESULTADO_TOTAL(String v) { RESULTADO_TOTAL = v; }
	public String getOBSERVACOES() { return OBSERVACOES; }
	public void setOBSERVACOES(String v) { OBSERVACOES = v; }
	public Integer getID_ACCESS_LEGADO() { return ID_ACCESS_LEGADO; }
	public void setID_ACCESS_LEGADO(Integer v) { ID_ACCESS_LEGADO = v; }
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