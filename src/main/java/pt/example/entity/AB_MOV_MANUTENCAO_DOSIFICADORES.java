package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "AB_MOV_MANUTENCAO_DOSIFICADORES")
public class AB_MOV_MANUTENCAO_DOSIFICADORES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_MANUTENCAO")
	private Integer ID_MANUTENCAO;
	@JsonProperty("ID_TINA")
	private Integer ID_TINA;
	@JsonProperty("INTERVALO_AMPERES")
	private Integer INTERVALO_AMPERES;
	@JsonProperty("DATA_PREVISTA")
	private Date DATA_PREVISTA;
	@JsonProperty("HORA_PREVISTA")
	private Time HORA_PREVISTA;
	@JsonProperty("DATA_EXECUCAO")
	private Date DATA_EXECUCAO;
	@JsonProperty("HORA_EXECUCAO")
	private Time HORA_EXECUCAO;
	@JsonProperty("UTZ_EXECUCAO")
	private Integer UTZ_EXECUCAO;
	@JsonProperty("DOSEADOR_AB_NIVEL")
	private BigDecimal DOSEADOR_AB_NIVEL;
	@JsonProperty("DOSEADOR_AB_REPOSICAO")
	private BigDecimal DOSEADOR_AB_REPOSICAO;
	@JsonProperty("DOSEADOR_NIV_NIVEL")
	private BigDecimal DOSEADOR_NIV_NIVEL;
	@JsonProperty("DOSEADOR_NIV_REPOSICAO")
	private BigDecimal DOSEADOR_NIV_REPOSICAO;
	@JsonProperty("AMPERES")
	private Integer AMPERES;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;

	public Integer getID() {
		return ID;
	}

	public Integer getID_MANUTENCAO() {
		return ID_MANUTENCAO;
	}

	public Integer getID_TINA() {
		return ID_TINA;
	}

	public Integer getINTERVALO_AMPERES() {
		return INTERVALO_AMPERES;
	}

	public Date getDATA_PREVISTA() {
		return DATA_PREVISTA;
	}

	public Time getHORA_PREVISTA() {
		return HORA_PREVISTA;
	}

	public Date getDATA_EXECUCAO() {
		return DATA_EXECUCAO;
	}

	public Time getHORA_EXECUCAO() {
		return HORA_EXECUCAO;
	}

	public Integer getUTZ_EXECUCAO() {
		return UTZ_EXECUCAO;
	}

	public BigDecimal getDOSEADOR_AB_NIVEL() {
		return DOSEADOR_AB_NIVEL;
	}

	public BigDecimal getDOSEADOR_AB_REPOSICAO() {
		return DOSEADOR_AB_REPOSICAO;
	}

	public BigDecimal getDOSEADOR_NIV_NIVEL() {
		return DOSEADOR_NIV_NIVEL;
	}

	public BigDecimal getDOSEADOR_NIV_REPOSICAO() {
		return DOSEADOR_NIV_REPOSICAO;
	}

	public Integer getAMPERES() {
		return AMPERES;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_MANUTENCAO(Integer iD_MANUTENCAO) {
		ID_MANUTENCAO = iD_MANUTENCAO;
	}

	public void setID_TINA(Integer iD_TINA) {
		ID_TINA = iD_TINA;
	}

	public void setINTERVALO_AMPERES(Integer iNTERVALO_AMPERES) {
		INTERVALO_AMPERES = iNTERVALO_AMPERES;
	}

	public void setDATA_PREVISTA(Date dATA_PREVISTA) {
		DATA_PREVISTA = dATA_PREVISTA;
	}

	public void setHORA_PREVISTA(Time hORA_PREVISTA) {
		HORA_PREVISTA = hORA_PREVISTA;
	}

	public void setDATA_EXECUCAO(Date dATA_EXECUCAO) {
		DATA_EXECUCAO = dATA_EXECUCAO;
	}

	public void setHORA_EXECUCAO(Time hORA_EXECUCAO) {
		HORA_EXECUCAO = hORA_EXECUCAO;
	}

	public void setUTZ_EXECUCAO(Integer uTZ_EXECUCAO) {
		UTZ_EXECUCAO = uTZ_EXECUCAO;
	}

	public void setDOSEADOR_AB_NIVEL(BigDecimal dOSEADOR_AB_NIVEL) {
		DOSEADOR_AB_NIVEL = dOSEADOR_AB_NIVEL;
	}

	public void setDOSEADOR_AB_REPOSICAO(BigDecimal dOSEADOR_AB_REPOSICAO) {
		DOSEADOR_AB_REPOSICAO = dOSEADOR_AB_REPOSICAO;
	}

	public void setDOSEADOR_NIV_NIVEL(BigDecimal dOSEADOR_NIV_NIVEL) {
		DOSEADOR_NIV_NIVEL = dOSEADOR_NIV_NIVEL;
	}

	public void setDOSEADOR_NIV_REPOSICAO(BigDecimal dOSEADOR_NIV_REPOSICAO) {
		DOSEADOR_NIV_REPOSICAO = dOSEADOR_NIV_REPOSICAO;
	}

	public void setAMPERES(Integer aMPERES) {
		AMPERES = aMPERES;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

}
