package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "FIN_DIC_TIPO_MOVIMENTO_STOCK")
public class FIN_DIC_TIPO_MOVIMENTO_STOCK {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("TIPO_MOVIMENTO")
	private String TIPO_MOVIMENTO;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;

	public Integer getID() {
		return ID;
	}

	public String getTIPO_MOVIMENTO() {
		return TIPO_MOVIMENTO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setTIPO_MOVIMENTO(String tIPO_MOVIMENTO) {
		TIPO_MOVIMENTO = tIPO_MOVIMENTO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

}
