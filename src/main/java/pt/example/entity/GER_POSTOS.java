package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_POSTOS")
public class GER_POSTOS {

	private Integer ID_POSTO;
	private String DESCRICAO;
	private String IMPRESSORA;
	private String IP_POSTO;

	@Id
	@Column(name = "ID_POSTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_POSTO() {
		return ID_POSTO;
	}

	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}

	@Column(name = "IMPRESSORA")
	public String getIMPRESSORA() {
		return IMPRESSORA;
	}

	@Column(name = "IP_POSTO")
	public String getIP_POSTO() {
		return IP_POSTO;
	}

	public void setID_POSTO(Integer iD_POSTO) {
		ID_POSTO = iD_POSTO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setIMPRESSORA(String iMPRESSORA) {
		IMPRESSORA = iMPRESSORA;
	}

	public void setIP_POSTO(String iP_POSTO) {
		IP_POSTO = iP_POSTO;
	}

}
