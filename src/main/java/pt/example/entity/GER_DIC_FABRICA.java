package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_DIC_FABRICA")
public class GER_DIC_FABRICA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FABRICA")
	private Integer ID_FABRICA;
	@Column(name = "NOME")
	private String NOME;

	public Integer getID_FABRICA() {
		return ID_FABRICA;
	}

	public String getNOME() {
		return NOME;
	}

	public void setID_FABRICA(Integer iD_FABRICA) {
		ID_FABRICA = iD_FABRICA;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

}
