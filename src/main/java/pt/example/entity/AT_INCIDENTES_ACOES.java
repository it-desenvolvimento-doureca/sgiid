package pt.example.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Melhorias 2026-08 - Acoes Corretivas e Medidas Preventivas de um incidente.
 *
 * Mesma forma das acoes do modulo de acidentes (AT_ACCOES): descricao,
 * responsavel pela implementacao e data. TIPO distingue as duas listas:
 * 'C' = Acao Corretiva, 'P' = Medida Preventiva.
 */
@Entity
@Table(name = "AT_INCIDENTES_ACOES")
public class AT_INCIDENTES_ACOES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_INCIDENTE")
	private Integer ID_INCIDENTE;
	@Column(name = "TIPO")
	private String TIPO;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "RESPONSAVEL")
	private String RESPONSAVEL;
	@Column(name = "DATA_IMPLEMENTACAO")
	private Date DATA_IMPLEMENTACAO;

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

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public String getRESPONSAVEL() {
		return RESPONSAVEL;
	}

	public void setRESPONSAVEL(String rESPONSAVEL) {
		RESPONSAVEL = rESPONSAVEL;
	}

	public Date getDATA_IMPLEMENTACAO() {
		return DATA_IMPLEMENTACAO;
	}

	public void setDATA_IMPLEMENTACAO(Date dATA_IMPLEMENTACAO) {
		DATA_IMPLEMENTACAO = dATA_IMPLEMENTACAO;
	}

}
