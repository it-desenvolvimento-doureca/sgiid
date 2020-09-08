package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_DIC_PROGRAMA")
public class GER_DIC_PROGRAMA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROGRAMA")
	private Integer ID_PROGRAMA;
	@Column(name = "ID_VEICULO")
	private Integer ID_VEICULO;
	@Column(name = "NOME")
	private String NOME;

	public Integer getID_PROGRAMA() {
		return ID_PROGRAMA;
	}

	public Integer getID_VEICULO() {
		return ID_VEICULO;
	}

	public String getNOME() {
		return NOME;
	}

	public void setID_PROGRAMA(Integer iD_PROGRAMA) {
		ID_PROGRAMA = iD_PROGRAMA;
	}

	public void setID_VEICULO(Integer iD_VEICULO) {
		ID_VEICULO = iD_VEICULO;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

}
