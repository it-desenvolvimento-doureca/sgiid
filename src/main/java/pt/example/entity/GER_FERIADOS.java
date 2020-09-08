package pt.example.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_FERIADOS")
public class GER_FERIADOS {
	private Integer ID_FERIADO;
	private String DESCRICAO;
	private Date DATA;
	private Boolean CONTA_FATURACAO;

	@Id
	@Column(name = "ID_FERIADO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_FERIADO() {
		return ID_FERIADO;
	}

	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}

	@Column(name = "DATA")
	public Date getDATA() {
		return DATA;
	}

	public void setID_FERIADO(Integer iD_FERIADO) {
		ID_FERIADO = iD_FERIADO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setDATA(Date dATA) {
		DATA = dATA;
	}

	@Column(name = "CONTA_FATURACAO")
	public Boolean getCONTA_FATURACAO() {
		return CONTA_FATURACAO;
	}

	public void setCONTA_FATURACAO(Boolean cONTA_FATURACAO) {
		CONTA_FATURACAO = cONTA_FATURACAO;
	}
}