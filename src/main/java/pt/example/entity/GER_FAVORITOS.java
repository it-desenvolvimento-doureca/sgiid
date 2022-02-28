package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_FAVORITOS")
public class GER_FAVORITOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FAVORITO")
	private Integer ID_FAVORITO;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "URL")
	private String URL;
	@Column(name = "ORDEM")
	private Integer ORDEM;
	@Column(name = "ID_UTILIZADOR")
	private Integer ID_UTILIZADOR;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;

	public Integer getID_FAVORITO() {
		return ID_FAVORITO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getURL() {
		return URL;
	}

	public Integer getORDEM() {
		return ORDEM;
	}

	public void setID_FAVORITO(Integer iD_FAVORITO) {
		ID_FAVORITO = iD_FAVORITO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
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
