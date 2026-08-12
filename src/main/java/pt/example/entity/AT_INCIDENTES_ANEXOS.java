package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Melhorias 2026-08 - Anexos de um incidente industrial/tecnologico.
 * Conteudo na base de dados como data URL base64 partida em duas colunas,
 * como nas restantes tabelas de ficheiros do SGIID.
 */
@Entity
@Table(name = "AT_INCIDENTES_ANEXOS")
public class AT_INCIDENTES_ANEXOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_INCIDENTE")
	private Integer ID_INCIDENTE;
	@Column(name = "NOME")
	private String NOME;
	@Column(name = "CAMINHO")
	private String CAMINHO;
	/** Classe para o preview: pdf | img | excel | word | txt | extensao */
	@Column(name = "TIPO")
	private String TIPO;
	/** Mime type original devolvido pelo browser. */
	@Column(name = "DATATYPE")
	private String DATATYPE;
	@Column(name = "TAMANHO")
	private Double TAMANHO;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "CATEGORIA")
	private String CATEGORIA;
	@Column(name = "FICHEIRO_1")
	private String FICHEIRO_1;
	@Column(name = "FICHEIRO_2")
	private String FICHEIRO_2;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@Column(name = "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@Column(name = "INATIVO")
	private Boolean INATIVO;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getID_INCIDENTE() {
		return ID_INCIDENTE;
	}

	public void setID_INCIDENTE(Integer iD_INCIDENTE) {
		ID_INCIDENTE = iD_INCIDENTE;
	}

	public String getNOME() {
		return NOME;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public String getCAMINHO() {
		return CAMINHO;
	}

	public void setCAMINHO(String cAMINHO) {
		CAMINHO = cAMINHO;
	}

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public String getDATATYPE() {
		return DATATYPE;
	}

	public void setDATATYPE(String dATATYPE) {
		DATATYPE = dATATYPE;
	}

	public Double getTAMANHO() {
		return TAMANHO;
	}

	public void setTAMANHO(Double tAMANHO) {
		TAMANHO = tAMANHO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public String getCATEGORIA() {
		return CATEGORIA;
	}

	public void setCATEGORIA(String cATEGORIA) {
		CATEGORIA = cATEGORIA;
	}

	public String getFICHEIRO_1() {
		return FICHEIRO_1;
	}

	public void setFICHEIRO_1(String fICHEIRO_1) {
		FICHEIRO_1 = fICHEIRO_1;
	}

	public String getFICHEIRO_2() {
		return FICHEIRO_2;
	}

	public void setFICHEIRO_2(String fICHEIRO_2) {
		FICHEIRO_2 = fICHEIRO_2;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

}
