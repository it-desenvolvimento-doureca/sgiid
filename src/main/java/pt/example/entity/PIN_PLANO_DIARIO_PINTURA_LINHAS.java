package pt.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_PLANO_DIARIO_PINTURA_LINHAS")
public class PIN_PLANO_DIARIO_PINTURA_LINHAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_PLANO_DIARIO_PINTURA_LINHA")
	private Integer ID_PLANO_DIARIO_PINTURA_LINHA;
	@JsonProperty("ID_PLANO_DIARIO_PINTURA")
	private Integer ID_PLANO_DIARIO_PINTURA;
	@JsonProperty("DIA_SEMANA")
	private Integer DIA_SEMANA;
	@JsonProperty("ORDEM")
	private Integer ORDEM;
	@JsonProperty("REFERENCIA")
	private String REFERENCIA;
	@JsonProperty("JOB")
	private String JOB;
	@JsonProperty("PRIMARIO")
	private String PRIMARIO;
	@JsonProperty("BASE")
	private String BASE;
	@JsonProperty("VERNIZ")
	private String VERNIZ;
	@JsonProperty("NUM_RACKS")
	private Integer NUM_RACKS;
	@JsonProperty("TEMPO_CICLO")
	private Integer TEMPO_CICLO;
	@JsonProperty("TEMPO_TRANSITO")
	private Integer TEMPO_TRANSITO;
	@JsonProperty("TEMPO_TOTAL")
	private Integer TEMPO_TOTAL;
	@JsonProperty("MANUAL")
	private Boolean MANUAL;
	@JsonProperty("COD_REFERENCIA")
	private String COD_REFERENCIA;

	public Integer getID_PLANO_DIARIO_PINTURA_LINHA() {
		return ID_PLANO_DIARIO_PINTURA_LINHA;
	}

	public void setID_PLANO_DIARIO_PINTURA_LINHA(Integer iD_PLANO_DIARIO_PINTURA_LINHA) {
		ID_PLANO_DIARIO_PINTURA_LINHA = iD_PLANO_DIARIO_PINTURA_LINHA;
	}

	public Integer getID_PLANO_DIARIO_PINTURA() {
		return ID_PLANO_DIARIO_PINTURA;
	}

	public void setID_PLANO_DIARIO_PINTURA(Integer iD_PLANO_DIARIO_PINTURA) {
		ID_PLANO_DIARIO_PINTURA = iD_PLANO_DIARIO_PINTURA;
	}

	public Integer getDIA_SEMANA() {
		return DIA_SEMANA;
	}

	public void setDIA_SEMANA(Integer dIA_SEMANA) {
		DIA_SEMANA = dIA_SEMANA;
	}

	public Integer getORDEM() {
		return ORDEM;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public String getJOB() {
		return JOB;
	}

	public void setJOB(String jOB) {
		JOB = jOB;
	}

	public String getPRIMARIO() {
		return PRIMARIO;
	}

	public void setPRIMARIO(String pRIMARIO) {
		PRIMARIO = pRIMARIO;
	}

	public String getBASE() {
		return BASE;
	}

	public void setBASE(String bASE) {
		BASE = bASE;
	}

	public String getVERNIZ() {
		return VERNIZ;
	}

	public void setVERNIZ(String vERNIZ) {
		VERNIZ = vERNIZ;
	}

	public Integer getNUM_RACKS() {
		return NUM_RACKS;
	}

	public void setNUM_RACKS(Integer nUM_RACKS) {
		NUM_RACKS = nUM_RACKS;
	}

	public Integer getTEMPO_CICLO() {
		return TEMPO_CICLO;
	}

	public void setTEMPO_CICLO(Integer tEMPO_CICLO) {
		TEMPO_CICLO = tEMPO_CICLO;
	}

	public Integer getTEMPO_TRANSITO() {
		return TEMPO_TRANSITO;
	}

	public void setTEMPO_TRANSITO(Integer tEMPO_TRANSITO) {
		TEMPO_TRANSITO = tEMPO_TRANSITO;
	}

	public Integer getTEMPO_TOTAL() {
		return TEMPO_TOTAL;
	}

	public void setTEMPO_TOTAL(Integer tEMPO_TOTAL) {
		TEMPO_TOTAL = tEMPO_TOTAL;
	}

	public Boolean getMANUAL() {
		return MANUAL;
	}

	public void setMANUAL(Boolean mANUAL) {
		MANUAL = mANUAL;
	}

	public String getCOD_REFERENCIA() {
		return COD_REFERENCIA;
	}
	
	public void setCOD_REFERENCIA(String cOD_REFERENCIA) {
		COD_REFERENCIA = cOD_REFERENCIA;
	}
}
