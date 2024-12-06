package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_MEDICINA_TRABALHO_DOCUMENTOS")
public class RH_MEDICINA_TRABALHO_DOCUMENTOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("TAMANHO")
	private double TAMANHO;
	@JsonProperty("NOME")
	private String NOME;
	@JsonProperty("CAMINHO")
	private String CAMINHO;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("TIPO")
	private String TIPO;
	@JsonProperty("ID_MEDICINA_TRABALHO")
	private Integer ID_MEDICINA_TRABALHO;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("DATATYPE")
	private String DATATYPE;
	@JsonProperty("FICHEIRO_1")
	private String FICHEIRO_1;
	@JsonProperty("FICHEIRO_2")
	private String FICHEIRO_2;
	@JsonProperty("ORDEM")
	private Integer ORDEM;

	public Integer getID() {
		return ID;
	}

	public double getTAMANHO() {
		return TAMANHO;
	}

	public String getNOME() {
		return NOME;
	}

	public String getCAMINHO() {
		return CAMINHO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getTIPO() {
		return TIPO;
	}

	public Integer getID_MEDICINA_TRABALHO() {
		return ID_MEDICINA_TRABALHO;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public String getDATATYPE() {
		return DATATYPE;
	}

	public String getFICHEIRO_1() {
		return FICHEIRO_1;
	}

	public String getFICHEIRO_2() {
		return FICHEIRO_2;
	}

	public Integer getORDEM() {
		return ORDEM;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setTAMANHO(double tAMANHO) {
		TAMANHO = tAMANHO;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setCAMINHO(String cAMINHO) {
		CAMINHO = cAMINHO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public void setID_MEDICINA_TRABALHO(Integer iD_MEDICINA_TRABALHO) {
		ID_MEDICINA_TRABALHO = iD_MEDICINA_TRABALHO;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setDATATYPE(String dATATYPE) {
		DATATYPE = dATATYPE;
	}

	public void setFICHEIRO_1(String fICHEIRO_1) {
		FICHEIRO_1 = fICHEIRO_1;
	}

	public void setFICHEIRO_2(String fICHEIRO_2) {
		FICHEIRO_2 = fICHEIRO_2;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
	}


}