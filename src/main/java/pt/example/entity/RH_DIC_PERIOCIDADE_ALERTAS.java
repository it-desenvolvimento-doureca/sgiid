package pt.example.entity;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_DIC_PERIOCIDADE_ALERTAS")
public class RH_DIC_PERIOCIDADE_ALERTAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ALERTA_CONSULTA_MEDICA")
	private Integer ALERTA_CONSULTA_MEDICA;
	@JsonProperty("ALERTA_DASHBOARD")
	private Integer ALERTA_DASHBOARD;

	public Integer getID() {
		return ID;
	}

	public Integer getALERTA_CONSULTA_MEDICA() {
		return ALERTA_CONSULTA_MEDICA;
	}

	public Integer getALERTA_DASHBOARD() {
		return ALERTA_DASHBOARD;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setALERTA_CONSULTA_MEDICA(Integer aLERTA_CONSULTA_MEDICA) {
		ALERTA_CONSULTA_MEDICA = aLERTA_CONSULTA_MEDICA;
	}

	public void setALERTA_DASHBOARD(Integer aLERTA_DASHBOARD) {
		ALERTA_DASHBOARD = aLERTA_DASHBOARD;
	}

}