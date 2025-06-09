package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_DIC_MAQUINAS_MATRIX")
public class PR_DIC_MAQUINAS_MATRIX {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("NOME_MAQUINA")
	private String NOME_MAQUINA;
	@JsonProperty("IP_MAQUINA")
	private String IP_MAQUINA;
	@JsonProperty("USERNAME")
	private String USERNAME;
	@JsonProperty("PASSWORD")
	private String PASSWORD;
	@JsonProperty("VARIAVEL_PROGRAMAS")
	private String VARIAVEL_PROGRAMAS;
	@JsonProperty("VARIAVEL_START")
	private String VARIAVEL_START;
	@JsonProperty("VARIAVEL_PROGRAMA_ATUAL")
	private String VARIAVEL_PROGRAMA_ATUAL;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("TIPO_MAQUINA")
	private String TIPO_MAQUINA;
	@JsonProperty("MAQUINA_SILVER")
	private String MAQUINA_SILVER;
	@JsonProperty("VARIAVEL_AGUARDA_SINAL")
	private String VARIAVEL_AGUARDA_SINAL;

	public Integer getID() {
		return ID;
	}

	public String getNOME_MAQUINA() {
		return NOME_MAQUINA;
	}

	public String getIP_MAQUINA() {
		return IP_MAQUINA;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public String getVARIAVEL_PROGRAMAS() {
		return VARIAVEL_PROGRAMAS;
	}

	public String getVARIAVEL_START() {
		return VARIAVEL_START;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setNOME_MAQUINA(String nOME_MAQUINA) {
		NOME_MAQUINA = nOME_MAQUINA;
	}

	public void setIP_MAQUINA(String iP_MAQUINA) {
		IP_MAQUINA = iP_MAQUINA;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public void setVARIAVEL_PROGRAMAS(String vARIAVEL_PROGRAMAS) {
		VARIAVEL_PROGRAMAS = vARIAVEL_PROGRAMAS;
	}

	public void setVARIAVEL_START(String vARIAVEL_START) {
		VARIAVEL_START = vARIAVEL_START;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public String getTIPO_MAQUINA() {
		return TIPO_MAQUINA;
	}

	public void setTIPO_MAQUINA(String tIPO_MAQUINA) {
		TIPO_MAQUINA = tIPO_MAQUINA;
	}

	public String getVARIAVEL_PROGRAMA_ATUAL() {
		return VARIAVEL_PROGRAMA_ATUAL;
	}

	public void setVARIAVEL_PROGRAMA_ATUAL(String vARIAVEL_PROGRAMA_ATUAL) {
		VARIAVEL_PROGRAMA_ATUAL = vARIAVEL_PROGRAMA_ATUAL;
	}

	public String getMAQUINA_SILVER() {
		return MAQUINA_SILVER;
	}

	public void setMAQUINA_SILVER(String mAQUINA_SILVER) {
		MAQUINA_SILVER = mAQUINA_SILVER;
	}

	public String getVARIAVEL_AGUARDA_SINAL() {
		return VARIAVEL_AGUARDA_SINAL;
	}

	public void setVARIAVEL_AGUARDA_SINAL(String vARIAVEL_AGUARDA_SINAL) {
		VARIAVEL_AGUARDA_SINAL = vARIAVEL_AGUARDA_SINAL;
	}

	
}
