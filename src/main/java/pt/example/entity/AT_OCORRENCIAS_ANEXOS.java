package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Melhorias 2026-08 - Anexos de uma ocorrencia. Segue o padrao de
 * MAN_MOV_MANUTENCAO_ANEXOS. Serve o "Relatorio Assinado - Anexar" pedido pelo
 * cliente (CATEGORIA = 'REL_ASSINADO') e qualquer anexo futuro (fotografias,
 * esquema do local, ficha de aptidao digitalizada).
 */
@Entity
@Table(name = "AT_OCORRENCIAS_ANEXOS")
public class AT_OCORRENCIAS_ANEXOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_OCORRENCIA")
	private Integer ID_OCORRENCIA;
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
	/* Conteudo do ficheiro como data URL base64, partido em duas colunas
	 * (convencao usada em todas as tabelas de ficheiros do SGIID). */
	@Column(name = "FICHEIRO_1")
	private String FICHEIRO_1;
	@Column(name = "FICHEIRO_2")
	private String FICHEIRO_2;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "CATEGORIA")
	private String CATEGORIA;
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

	public Integer getID_OCORRENCIA() {
		return ID_OCORRENCIA;
	}

	public String getNOME() {
		return NOME;
	}

	public String getCAMINHO() {
		return CAMINHO;
	}

	public String getTIPO() {
		return TIPO;
	}

	public Double getTAMANHO() {
		return TAMANHO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getCATEGORIA() {
		return CATEGORIA;
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

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_OCORRENCIA(Integer iD_OCORRENCIA) {
		ID_OCORRENCIA = iD_OCORRENCIA;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setCAMINHO(String cAMINHO) {
		CAMINHO = cAMINHO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public void setTAMANHO(Double tAMANHO) {
		TAMANHO = tAMANHO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setCATEGORIA(String cATEGORIA) {
		CATEGORIA = cATEGORIA;
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

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public String getDATATYPE() {
		return DATATYPE;
	}

	public void setDATATYPE(String dATATYPE) {
		DATATYPE = dATATYPE;
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

}
