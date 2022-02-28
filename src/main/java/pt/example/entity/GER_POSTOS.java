package pt.example.entity;

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
	private String IP_IMPRESSORA;
	private String NOME_IMPRESSORA;
	private String NOME_IMPRESSORA_SILVER;
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
	
	@Column(name = "IP_IMPRESSORA")
	public String getIP_IMPRESSORA() {
		return IP_IMPRESSORA;
	}
	
	@Column(name = "NOME_IMPRESSORA")
	public String getNOME_IMPRESSORA() {
		return NOME_IMPRESSORA;
	}

	public void setIP_IMPRESSORA(String iP_IMPRESSORA) {
		IP_IMPRESSORA = iP_IMPRESSORA;
	}

	public void setNOME_IMPRESSORA(String nOME_IMPRESSORA) {
		NOME_IMPRESSORA = nOME_IMPRESSORA;
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

	@Column(name = "NOME_IMPRESSORA_SILVER")
	public String getNOME_IMPRESSORA_SILVER() {
		return NOME_IMPRESSORA_SILVER;
	}

	public void setNOME_IMPRESSORA_SILVER(String nOME_IMPRESSORA_SILVER) {
		NOME_IMPRESSORA_SILVER = nOME_IMPRESSORA_SILVER;
	}

}
