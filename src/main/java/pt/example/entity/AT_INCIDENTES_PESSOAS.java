package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Melhorias 2026-08 - Pessoas presentes num incidente industrial/tecnologico.
 */
@Entity
@Table(name = "AT_INCIDENTES_PESSOAS")
public class AT_INCIDENTES_PESSOAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_INCIDENTE")
	private Integer ID_INCIDENTE;
	@Column(name = "NOME")
	private String NOME;
	/** N.o mecanografico da pessoa. */
	@Column(name = "NUMERO")
	private String NUMERO;
	@Column(name = "SETOR")
	private String SETOR;
	@Column(name = "CARGO_FUNCAO")
	private String CARGO_FUNCAO;

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

	public String getSETOR() {
		return SETOR;
	}

	public void setSETOR(String sETOR) {
		SETOR = sETOR;
	}

	public String getCARGO_FUNCAO() {
		return CARGO_FUNCAO;
	}

	public void setCARGO_FUNCAO(String cARGO_FUNCAO) {
		CARGO_FUNCAO = cARGO_FUNCAO;
	}

}
