package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Melhorias 2026-08 - Assinaturas dos trabalhadores em postos/tarefas de
 * trabalho similares ("Tomei Conhecimento" + Data), pedidas na pag. 3 do
 * relatorio de investigacao de acidentes.
 */
@Entity
@Table(name = "AT_ASSINATURAS_SIMILARES")
public class AT_ASSINATURAS_SIMILARES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_OCORRENCIA")
	private Integer ID_OCORRENCIA;
	@Column(name = "NOME")
	private String NOME;
	@Column(name = "NUMERO")
	private String NUMERO;
	@Column(name = "FUNCAO")
	private String FUNCAO;
	@Column(name = "DATA_CONHECIMENTO")
	private Date DATA_CONHECIMENTO;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;

	public Integer getID() {
		return ID;
	}

	public Integer getID_OCORRENCIA() {
		return ID_OCORRENCIA;
	}

	public String getNOME() {
		return NOME;
	}

	public String getNUMERO() {
		return NUMERO;
	}

	public String getFUNCAO() {
		return FUNCAO;
	}

	public Date getDATA_CONHECIMENTO() {
		return DATA_CONHECIMENTO;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
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

	public void setNUMERO(String nUMERO) {
		NUMERO = nUMERO;
	}

	public void setFUNCAO(String fUNCAO) {
		FUNCAO = fUNCAO;
	}

	public void setDATA_CONHECIMENTO(Date dATA_CONHECIMENTO) {
		DATA_CONHECIMENTO = dATA_CONHECIMENTO;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

}
