package pt.example.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_CR_DIC_REFERENCIA")
public class QUA_CR_DIC_REFERENCIA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_REFERENCIA")
	private Integer ID_REFERENCIA;
	@JsonProperty("REFERENCIA")
	private String REFERENCIA;
	@JsonProperty("PROREF_SILVER")
	private String PROREF_SILVER;
	@JsonProperty("PROREF_SILVER_DESC")
	private String PROREF_SILVER_DESC;
	@JsonProperty("REF_PECA_PLASTICA")
	private String REF_PECA_PLASTICA;
	@JsonProperty("PROREF_PLASTICA_SILVER")
	private String PROREF_PLASTICA_SILVER;
	@JsonProperty("PROREF_PLASTICA_DESC")
	private String PROREF_PLASTICA_DESC;
	@JsonProperty("CLIENTE")
	private String CLIENTE;
	@JsonProperty("CLICOD_SILVER")
	private String CLICOD_SILVER;
	@JsonProperty("DESIGNACAO")
	private String DESIGNACAO;
	@JsonProperty("MATERIAL")
	private String MATERIAL;
	@JsonProperty("CAVIDADES")
	private String CAVIDADES;
	@JsonProperty("FICHA_TECNICA")
	private String FICHA_TECNICA;
	@JsonProperty("CARTELA")
	private String CARTELA;
	@JsonProperty("NUM_AMOSTRAS")
	private Integer NUM_AMOSTRAS;
	@JsonProperty("ID_TIPO_PECA")
	private Integer ID_TIPO_PECA;
	@JsonProperty("NORMA_CLIENTE")
	private String NORMA_CLIENTE;
	@JsonProperty("ID_TIPO_SUPERFICIE")
	private Integer ID_TIPO_SUPERFICIE;
	@JsonProperty("ID_ASPECTO")
	private Integer ID_ASPECTO;
	@JsonProperty("ESPESSURA_CU")
	private String ESPESSURA_CU;
	@JsonProperty("ESPESSURA_NI")
	private String ESPESSURA_NI;
	@JsonProperty("ESPESSURA_CR")
	private String ESPESSURA_CR;
	@JsonProperty("MICROPOROSO")
	private Boolean MICROPOROSO;
	@JsonProperty("MICROFISSURADO")
	private Boolean MICROFISSURADO;
	@JsonProperty("N_EXIG_POROS")
	private String N_EXIG_POROS;
	@JsonProperty("N_EXIG_FISSURAS")
	private String N_EXIG_FISSURAS;
	@JsonProperty("PAUTA_ENSAIO")
	private String PAUTA_ENSAIO;
	@JsonProperty("PAUTA_ESPESSURAS")
	private String PAUTA_ESPESSURAS;
	@JsonProperty("PAUTA_POROS")
	private String PAUTA_POROS;
	@JsonProperty("PAUTA_STEP")
	private String PAUTA_STEP;
	@JsonProperty("PAUTA_CACL2")
	private String PAUTA_CACL2;
	@JsonProperty("PAUTA_NSS")
	private String PAUTA_NSS;
	@JsonProperty("TESTE_CACL2")
	private String TESTE_CACL2;
	@JsonProperty("PROC_CACL2")
	private String PROC_CACL2;
	@JsonProperty("TESTE_NSS")
	private String TESTE_NSS;
	@JsonProperty("PROC_NSS")
	private String PROC_NSS;
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

	public Integer getID_REFERENCIA() { return ID_REFERENCIA; }
	public void setID_REFERENCIA(Integer v) { ID_REFERENCIA = v; }
	public String getREFERENCIA() { return REFERENCIA; }
	public void setREFERENCIA(String v) { REFERENCIA = v; }
	public String getPROREF_SILVER() { return PROREF_SILVER; }
	public void setPROREF_SILVER(String v) { PROREF_SILVER = v; }
	public String getPROREF_SILVER_DESC() { return PROREF_SILVER_DESC; }
	public void setPROREF_SILVER_DESC(String v) { PROREF_SILVER_DESC = v; }
	public String getREF_PECA_PLASTICA() { return REF_PECA_PLASTICA; }
	public void setREF_PECA_PLASTICA(String v) { REF_PECA_PLASTICA = v; }
	public String getPROREF_PLASTICA_SILVER() { return PROREF_PLASTICA_SILVER; }
	public void setPROREF_PLASTICA_SILVER(String v) { PROREF_PLASTICA_SILVER = v; }
	public String getPROREF_PLASTICA_DESC() { return PROREF_PLASTICA_DESC; }
	public void setPROREF_PLASTICA_DESC(String v) { PROREF_PLASTICA_DESC = v; }
	public String getCLIENTE() { return CLIENTE; }
	public void setCLIENTE(String v) { CLIENTE = v; }
	public String getCLICOD_SILVER() { return CLICOD_SILVER; }
	public void setCLICOD_SILVER(String v) { CLICOD_SILVER = v; }
	public String getDESIGNACAO() { return DESIGNACAO; }
	public void setDESIGNACAO(String v) { DESIGNACAO = v; }
	public String getMATERIAL() { return MATERIAL; }
	public void setMATERIAL(String v) { MATERIAL = v; }
	public String getCAVIDADES() { return CAVIDADES; }
	public void setCAVIDADES(String v) { CAVIDADES = v; }
	public String getFICHA_TECNICA() { return FICHA_TECNICA; }
	public void setFICHA_TECNICA(String v) { FICHA_TECNICA = v; }
	public String getCARTELA() { return CARTELA; }
	public void setCARTELA(String v) { CARTELA = v; }
	public Integer getNUM_AMOSTRAS() { return NUM_AMOSTRAS; }
	public void setNUM_AMOSTRAS(Integer v) { NUM_AMOSTRAS = v; }
	public Integer getID_TIPO_PECA() { return ID_TIPO_PECA; }
	public void setID_TIPO_PECA(Integer v) { ID_TIPO_PECA = v; }
	public String getNORMA_CLIENTE() { return NORMA_CLIENTE; }
	public void setNORMA_CLIENTE(String v) { NORMA_CLIENTE = v; }
	public Integer getID_TIPO_SUPERFICIE() { return ID_TIPO_SUPERFICIE; }
	public void setID_TIPO_SUPERFICIE(Integer v) { ID_TIPO_SUPERFICIE = v; }
	public Integer getID_ASPECTO() { return ID_ASPECTO; }
	public void setID_ASPECTO(Integer v) { ID_ASPECTO = v; }
	public String getESPESSURA_CU() { return ESPESSURA_CU; }
	public void setESPESSURA_CU(String v) { ESPESSURA_CU = v; }
	public String getESPESSURA_NI() { return ESPESSURA_NI; }
	public void setESPESSURA_NI(String v) { ESPESSURA_NI = v; }
	public String getESPESSURA_CR() { return ESPESSURA_CR; }
	public void setESPESSURA_CR(String v) { ESPESSURA_CR = v; }
	public Boolean getMICROPOROSO() { return MICROPOROSO; }
	public void setMICROPOROSO(Boolean v) { MICROPOROSO = v; }
	public Boolean getMICROFISSURADO() { return MICROFISSURADO; }
	public void setMICROFISSURADO(Boolean v) { MICROFISSURADO = v; }
	public String getN_EXIG_POROS() { return N_EXIG_POROS; }
	public void setN_EXIG_POROS(String v) { N_EXIG_POROS = v; }
	public String getN_EXIG_FISSURAS() { return N_EXIG_FISSURAS; }
	public void setN_EXIG_FISSURAS(String v) { N_EXIG_FISSURAS = v; }
	public String getPAUTA_ENSAIO() { return PAUTA_ENSAIO; }
	public void setPAUTA_ENSAIO(String v) { PAUTA_ENSAIO = v; }
	public String getPAUTA_ESPESSURAS() { return PAUTA_ESPESSURAS; }
	public void setPAUTA_ESPESSURAS(String v) { PAUTA_ESPESSURAS = v; }
	public String getPAUTA_POROS() { return PAUTA_POROS; }
	public void setPAUTA_POROS(String v) { PAUTA_POROS = v; }
	public String getPAUTA_STEP() { return PAUTA_STEP; }
	public void setPAUTA_STEP(String v) { PAUTA_STEP = v; }
	public String getPAUTA_CACL2() { return PAUTA_CACL2; }
	public void setPAUTA_CACL2(String v) { PAUTA_CACL2 = v; }
	public String getPAUTA_NSS() { return PAUTA_NSS; }
	public void setPAUTA_NSS(String v) { PAUTA_NSS = v; }
	public String getTESTE_CACL2() { return TESTE_CACL2; }
	public void setTESTE_CACL2(String v) { TESTE_CACL2 = v; }
	public String getPROC_CACL2() { return PROC_CACL2; }
	public void setPROC_CACL2(String v) { PROC_CACL2 = v; }
	public String getTESTE_NSS() { return TESTE_NSS; }
	public void setTESTE_NSS(String v) { TESTE_NSS = v; }
	public String getPROC_NSS() { return PROC_NSS; }
	public void setPROC_NSS(String v) { PROC_NSS = v; }
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