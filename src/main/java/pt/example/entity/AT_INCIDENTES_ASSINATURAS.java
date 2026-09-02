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
 * Melhorias 2026-08-24 - Assinaturas dos trabalhadores em postos/tarefas de
 * trabalho similares ("Tomei Conhecimento" + Data) no fecho do relatorio de
 * investigacao de incidentes.
 *
 * Tabela propria e nao reaproveitamento de AT_ASSINATURAS_SIMILARES: a chave
 * e o incidente e nao a ocorrencia, e os dois relatorios sao independentes.
 *
 * Sem FUNCAO de proposito - foi retirada dos dois ecras.
 */
@Entity
@Table(name = "AT_INCIDENTES_ASSINATURAS")
public class AT_INCIDENTES_ASSINATURAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_INCIDENTE")
	private Integer ID_INCIDENTE;
	@Column(name = "NOME")
	private String NOME;
	@Column(name = "NUMERO")
	private String NUMERO;
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

	public String getNUMERO() {
		return NUMERO;
	}

	public void setNUMERO(String nUMERO) {
		NUMERO = nUMERO;
	}

	public Date getDATA_CONHECIMENTO() {
		return DATA_CONHECIMENTO;
	}

	public void setDATA_CONHECIMENTO(Date dATA_CONHECIMENTO) {
		DATA_CONHECIMENTO = dATA_CONHECIMENTO;
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

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}
}
