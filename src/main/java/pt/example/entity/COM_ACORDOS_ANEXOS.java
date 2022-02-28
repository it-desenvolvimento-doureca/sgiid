package pt.example.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "COM_ACORDOS_ANEXOS")
public class COM_ACORDOS_ANEXOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_ACORDO")
	private Integer ID_ACORDO;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("TAMANHO_FICHEIRO")
	private double TAMANHO_FICHEIRO;
	@JsonProperty("NOME_FICHEIRO")
	private String NOME_FICHEIRO;
	@JsonProperty("DATATYPE_FICHEIRO")
	private String DATATYPE_FICHEIRO;
	@JsonProperty("FICHEIRO")
	private String FICHEIRO;
	@JsonProperty("FICHEIRO_2")
	private String FICHEIRO_2;
	@JsonProperty("TYPE_FICHEIRO")
	private String TYPE_FICHEIRO;
	@JsonProperty("VERSAO")
	private Integer VERSAO;
	
	public Integer getID() {
		return ID;
	}

	public Integer getID_ACORDO() {
		return ID_ACORDO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public double getTAMANHO_FICHEIRO() {
		return TAMANHO_FICHEIRO;
	}

	public String getNOME_FICHEIRO() {
		return NOME_FICHEIRO;
	}

	public String getDATATYPE_FICHEIRO() {
		return DATATYPE_FICHEIRO;
	}

	public String getFICHEIRO() {
		return FICHEIRO;
	}

	public String getFICHEIRO_2() {
		return FICHEIRO_2;
	}

	public String getTYPE_FICHEIRO() {
		return TYPE_FICHEIRO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_ACORDO(Integer iD_ACORDO) {
		ID_ACORDO = iD_ACORDO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setTAMANHO_FICHEIRO(double tAMANHO_FICHEIRO) {
		TAMANHO_FICHEIRO = tAMANHO_FICHEIRO;
	}

	public void setNOME_FICHEIRO(String nOME_FICHEIRO) {
		NOME_FICHEIRO = nOME_FICHEIRO;
	}

	public void setDATATYPE_FICHEIRO(String dATATYPE_FICHEIRO) {
		DATATYPE_FICHEIRO = dATATYPE_FICHEIRO;
	}

	public void setFICHEIRO(String fICHEIRO) {
		FICHEIRO = fICHEIRO;
	}

	public void setFICHEIRO_2(String fICHEIRO_2) {
		FICHEIRO_2 = fICHEIRO_2;
	}

	public void setTYPE_FICHEIRO(String tYPE_FICHEIRO) {
		TYPE_FICHEIRO = tYPE_FICHEIRO;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

}
