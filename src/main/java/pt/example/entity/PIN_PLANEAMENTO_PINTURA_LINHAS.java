package pt.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_PLANEAMENTO_PINTURA_LINHAS")
public class PIN_PLANEAMENTO_PINTURA_LINHAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_CAB")
	private Integer ID_CAB;
	@JsonProperty("ID_POTE")
	private Integer ID_POTE;
	@JsonProperty("ID_COR_ACABAMENTO")
	private Integer ID_COR_ACABAMENTO;
	@JsonProperty("ORDEM")
	private Integer ORDEM;

	public Integer getID() {
		return ID;
	}

	public Integer getID_CAB() {
		return ID_CAB;
	}

	public Integer getID_POTE() {
		return ID_POTE;
	}

	public Integer getID_COR_ACABAMENTO() {
		return ID_COR_ACABAMENTO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_CAB(Integer iD_CAB) {
		ID_CAB = iD_CAB;
	}

	public void setID_POTE(Integer iD_POTE) {
		ID_POTE = iD_POTE;
	}

	public void setID_COR_ACABAMENTO(Integer iD_COR_ACABAMENTO) {
		ID_COR_ACABAMENTO = iD_COR_ACABAMENTO;
	}

	public Integer getORDEM() {
		return ORDEM;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
	}

}
