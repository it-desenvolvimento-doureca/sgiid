package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_LOG_EVENTOS")
public class GER_LOG_EVENTOS {

	private Integer ID_LOG_EVENTOS;
	private Integer ID_EVENTO;
	private Integer UTZ_ENVIO;
	private String TEXTO;
	private Timestamp DATA_ENVIO;

	@Id
	@Column(name = "ID_LOG_EVENTOS")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_LOG_EVENTOS() {
		return ID_LOG_EVENTOS;
	}

	@Column(name = "ID_EVENTO")
	public Integer getID_EVENTO() {
		return ID_EVENTO;
	}

	@Column(name = "UTZ_ENVIO")
	public Integer getUTZ_ENVIO() {
		return UTZ_ENVIO;
	}

	@Column(name = "TEXTO")
	public String getTEXTO() {
		return TEXTO;
	}

	@Column(name = "DATA_ENVIO")
	public Timestamp getDATA_ENVIO() {
		return DATA_ENVIO;
	}

	public void setID_LOG_EVENTOS(Integer iD_LOG_EVENTOS) {
		ID_LOG_EVENTOS = iD_LOG_EVENTOS;
	}

	public void setID_EVENTO(Integer iD_EVENTO) {
		ID_EVENTO = iD_EVENTO;
	}

	public void setUTZ_ENVIO(Integer uTZ_ENVIO) {
		UTZ_ENVIO = uTZ_ENVIO;
	}

	public void setTEXTO(String tEXTO) {
		TEXTO = tEXTO;
	}

	public void setDATA_ENVIO(Timestamp dATA_ENVIO) {
		DATA_ENVIO = dATA_ENVIO;
	}

}
