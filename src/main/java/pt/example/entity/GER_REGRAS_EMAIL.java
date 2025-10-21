package pt.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "GER_REGRAS_EMAIL")
public class GER_REGRAS_EMAIL {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_CAIXA")
	private Integer ID_CAIXA;
	@JsonProperty("TIPO")
	private String TIPO;
	@JsonProperty("TEXTO")
	private String TEXTO;

	public Integer getID() {
		return ID;
	}

	public Integer getID_CAIXA() {
		return ID_CAIXA;
	}

	public String getTIPO() {
		return TIPO;
	}

	public String getTEXTO() {
		return TEXTO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_CAIXA(Integer iD_CAIXA) {
		ID_CAIXA = iD_CAIXA;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public void setTEXTO(String tEXTO) {
		TEXTO = tEXTO;
	}

}
