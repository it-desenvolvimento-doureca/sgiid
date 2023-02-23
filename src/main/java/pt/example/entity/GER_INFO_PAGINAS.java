package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_INFO_PAGINAS")
public class GER_INFO_PAGINAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "URL")
	private String URL;
	@Column(name = "ID_UTILIZADOR")
	private Integer ID_UTILIZADOR;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;

	public Integer getID() {
		return ID;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getURL() {
		return URL;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Integer getID_UTILIZADOR() {
		return ID_UTILIZADOR;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setID_UTILIZADOR(Integer iD_UTILIZADOR) {
		ID_UTILIZADOR = iD_UTILIZADOR;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

}
