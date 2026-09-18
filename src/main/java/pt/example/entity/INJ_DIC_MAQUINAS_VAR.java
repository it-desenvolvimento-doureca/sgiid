package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mapa de variaveis do coletor de injecao: chave canonica -> endereco nativo.
 *
 * AS VARIAVEIS SAO POR TIPO, NAO POR MAQUINA. ID_MAQUINA a NULL significa
 * "vale para todas as maquinas desta familia" - as 8 Haitian respondem aos
 * mesmos caminhos APPL.* e as 3 ENGEL aos mesmos nodeIds. Uma linha com
 * ID_MAQUINA preenchido e a EXCECAO de uma maquina, e ganha a linha do tipo.
 *
 * Mexer aqui cala o coletor se ficar errado: um ENDERECO trocado deixa de ler
 * a variavel, e o tablet deixa de contar pecas sem dizer porque.
 */
@Entity
@Table(name = "INJ_DIC_MAQUINAS_VAR")
public class INJ_DIC_MAQUINAS_VAR {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	/** OPCUA | DEVADMIN | SIGMATEK */
	@JsonProperty("TIPO_MAQUINA")
	private String TIPO_MAQUINA;
	/** NULL = vale para a familia toda. Preenchido = excecao desta maquina. */
	@JsonProperty("ID_MAQUINA")
	private Integer ID_MAQUINA;
	/** Chave canonica, igual em todas as familias: cycleCounter, moldTemp... */
	@JsonProperty("CHAVE")
	private String CHAVE;
	@JsonProperty("ENDERECO")
	private String ENDERECO;
	/** SUBSCRICAO | FOLHA | CONTENTOR | METODO */
	@JsonProperty("MODO_LEITURA")
	private String MODO_LEITURA;
	/** No pai, obrigatorio quando MODO_LEITURA = CONTENTOR e so nesse caso. */
	@JsonProperty("CONTENTOR")
	private String CONTENTOR;
	/** EVENT | ANALOG | DERIVED */
	@JsonProperty("CLASSE")
	private String CLASSE;
	@JsonProperty("DEADBAND")
	private BigDecimal DEADBAND;
	@JsonProperty("ESCALA")
	private BigDecimal ESCALA;
	@JsonProperty("UNIDADE")
	private String UNIDADE;
	/** RAPIDO | LENTO | ARRANQUE */
	@JsonProperty("CICLO")
	private String CICLO;
	@JsonProperty("NOTA")
	private String NOTA;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getTIPO_MAQUINA() {
		return TIPO_MAQUINA;
	}

	public void setTIPO_MAQUINA(String tIPO_MAQUINA) {
		TIPO_MAQUINA = tIPO_MAQUINA;
	}

	public Integer getID_MAQUINA() {
		return ID_MAQUINA;
	}

	public void setID_MAQUINA(Integer iD_MAQUINA) {
		ID_MAQUINA = iD_MAQUINA;
	}

	public String getCHAVE() {
		return CHAVE;
	}

	public void setCHAVE(String cHAVE) {
		CHAVE = cHAVE;
	}

	public String getENDERECO() {
		return ENDERECO;
	}

	public void setENDERECO(String eNDERECO) {
		ENDERECO = eNDERECO;
	}

	public String getMODO_LEITURA() {
		return MODO_LEITURA;
	}

	public void setMODO_LEITURA(String mODO_LEITURA) {
		MODO_LEITURA = mODO_LEITURA;
	}

	public String getCONTENTOR() {
		return CONTENTOR;
	}

	public void setCONTENTOR(String cONTENTOR) {
		CONTENTOR = cONTENTOR;
	}

	public String getCLASSE() {
		return CLASSE;
	}

	public void setCLASSE(String cLASSE) {
		CLASSE = cLASSE;
	}

	public BigDecimal getDEADBAND() {
		return DEADBAND;
	}

	public void setDEADBAND(BigDecimal dEADBAND) {
		DEADBAND = dEADBAND;
	}

	public BigDecimal getESCALA() {
		return ESCALA;
	}

	public void setESCALA(BigDecimal eSCALA) {
		ESCALA = eSCALA;
	}

	public String getUNIDADE() {
		return UNIDADE;
	}

	public void setUNIDADE(String uNIDADE) {
		UNIDADE = uNIDADE;
	}

	public String getCICLO() {
		return CICLO;
	}

	public void setCICLO(String cICLO) {
		CICLO = cICLO;
	}

	public String getNOTA() {
		return NOTA;
	}

	public void setNOTA(String nOTA) {
		NOTA = nOTA;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}
}
