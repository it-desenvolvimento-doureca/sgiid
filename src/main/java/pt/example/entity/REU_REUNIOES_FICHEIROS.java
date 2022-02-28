package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REU_REUNIOES_FICHEIROS")
public class REU_REUNIOES_FICHEIROS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_REUNIAO")
	private Integer ID_REUNIAO;
	@Column(name = "TAMANHO")
	private double TAMANHO;
	@Column(name = "NOME")
	private String NOME;
	@Column(name = "CAMINHO")
	private String CAMINHO;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "TIPO")
	private String TIPO;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "DATATYPE")
	private String DATATYPE;
	@Column(name = "FICHEIRO_1")
	private String FICHEIRO_1;
	@Column(name = "FICHEIRO_2")
	private String FICHEIRO_2;

	public Integer getID() {
		return ID;
	}

	public Integer getID_REUNIAO() {
		return ID_REUNIAO;
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

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
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

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_REUNIAO(Integer iD_REUNIAO) {
		ID_REUNIAO = iD_REUNIAO;
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

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
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

}
