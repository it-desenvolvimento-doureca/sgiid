package pt.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_DIC_PARAMETROS")
public class RH_DIC_PARAMETROS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("PERECENTAGEM_MAX_PREMIO")
	private String PERECENTAGEM_MAX_PREMIO;

	@JsonProperty("DATA_MODIF")
	private Date DATA_MODIF;

	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	public Integer getID() {
		return ID;
	}

	public String getPERECENTAGEM_MAX_PREMIO() {
		return PERECENTAGEM_MAX_PREMIO;
	}

	public Date getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setPERECENTAGEM_MAX_PREMIO(String pERECENTAGEM_MAX_PREMIO) {
		PERECENTAGEM_MAX_PREMIO = pERECENTAGEM_MAX_PREMIO;
	}

	public void setDATA_MODIF(Date dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

}
